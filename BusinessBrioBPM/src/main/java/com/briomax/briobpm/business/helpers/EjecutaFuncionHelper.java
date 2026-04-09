package com.briomax.briobpm.business.helpers;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.business.helpers.base.IEjecutaFuncionHelper;
import com.briomax.briobpm.persistence.entity.CrConsultaRepse;
import com.briomax.briobpm.persistence.entity.InImagenProceso;
import com.briomax.briobpm.persistence.entity.InImagenProcesoPK;
import com.briomax.briobpm.persistence.entity.InVariableProceso;
import com.briomax.briobpm.persistence.entity.InVariableProcesoPK;
import com.briomax.briobpm.persistence.repository.ICrConsultaRepseRepository;
import com.briomax.briobpm.persistence.repository.IInImagenProcesoRepository;
import com.briomax.briobpm.persistence.repository.IInVariableProcesoRepository;
import com.briomax.briobpm.transferobjects.in.NodoTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class EjecutaFuncionHelper implements IEjecutaFuncionHelper {

	@Autowired
	private ICrConsultaRepseRepository crConsultaRepseRepository;

	/** El atributo o variable in Variable Proceso Repository. */
	@Autowired
	IInVariableProcesoRepository inVariableProcesoRepository;

	/** El atributo o variable in Imagen Proceso Repository. */
	@Autowired
	IInImagenProcesoRepository inImagenProcesoRepository;

	/** El atributo o variable lectorPDFRecibodeNomina. */
	@Value("${path.python}")
	private String rutaPython;

	/** El atributo o variable lectorPDFRecibodeNomina. */
	@Value("${spring.profiles.active}")
	private String environment;

	@Value("${spring.datasource.active.manager}")
    private String ambiente;
	
	@Override
	public void validaEstatusREPSE(NodoTO datos) {

		/*
		 * ============================================================================
		 * TProceso que valida el servicio de REPSE *
		 * ============================================================================
		 */
		// Definir el formato esperado
		Date fechaConsulta = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat formatoDia = new SimpleDateFormat("yyyy-MM-dd");
		String fechaStr = formatoDia.format(fechaConsulta);
		String fecActStr = formato.format(fechaConsulta);

		// Se obtine la razon social de la instacio
		InVariableProceso valiable = inVariableProcesoRepository.encontraInVarProceso(datos.getCveEntidad(),
				datos.getCveProceso(), datos.getVersion(), datos.getCveInstancia(),
				"VPRO_01_NOMBRE_RAZON_SOCIAL_CONTRATISTA");
		String razonSocial = valiable.getValorAlfanumerico();

		valiable = inVariableProcesoRepository.encontraInVarProceso(datos.getCveEntidad(), datos.getCveProceso(),
				datos.getVersion(), datos.getCveInstancia(), "VPRO_01_RFC_CONTRATISTA");
		String rfcCopntratista = valiable.getValorAlfanumerico();

		valiable = inVariableProcesoRepository.encontraInVarProceso(datos.getCveEntidad(), datos.getCveProceso(),
				datos.getVersion(), datos.getCveInstancia(), "VPRO_01_NUM_CONTRATO");
		String contrato = valiable.getValorAlfanumerico();

		String lectorRepse = rutaPython + "repse_consulta_avanzada.py";
		log.info(">>>>>>>>>>>>>>>>>>>> lectorPDFRecibodeNomina {}  nombreArchivo {}", lectorRepse);
		boolean res = pythonExecutor(lectorRepse, datos.getCveEntidad(), datos.getCveProceso(), rfcCopntratista,
				contrato, razonSocial);
		log.info(">>>>>>>>>>>>>>>>>>>> res {} ", res);

		// Convertir la cadena a Date
		Date fecha = null;
		try {
			if (res) {
				fecha = formatoDia.parse(fechaStr);

				CrConsultaRepse registro = crConsultaRepseRepository.getAvisoRepseFiltro(datos.getCveEntidad(), fecha,
						razonSocial);

				if (registro != null) {
					InVariableProcesoPK id = InVariableProcesoPK.builder().cveEntidad(datos.getCveEntidad())
							.cveProceso(datos.getCveProceso()).cveInstancia(datos.getCveInstancia())
							.cveVariable("VPRO_01_DESCRIPCION_CAP").ocurrencia(datos.getOcurrencia()).secuenciaValor(1)
							.build();

					InVariableProceso inValida = InVariableProceso.builder().id(id)
							.valorAlfanumerico(registro.getAvisoFechaRegistro()).build();
					inVariableProcesoRepository.saveAndFlush(inValida);
					id.setCveVariable("fecha_vigente");

					InImagenProcesoPK id2 = InImagenProcesoPK.builder().cveEntidad(datos.getCveEntidad())
							.cveProceso(datos.getCveProceso()).cveInstancia(datos.getCveInstancia())
							.cveVariable("VPRO_01_IMAGEN_REPSE").ocurrencia(datos.getOcurrencia()).secuenciaValor(1)
							.build();

					InImagenProceso inValidaF = InImagenProceso.builder().id(id2)
							.valorImagen(registro.getImagenConsulta()).build();
					inImagenProcesoRepository.saveAndFlush(inValidaF);
				}
			}

		} catch (ParseException e) {
			log.error("Error al obtener la fecha");
		}

	}

	private boolean pythonExecutor(String scriptPath, String cveEntidad, String cveProceso, String rfcCopntratista,
			String contrato, String razonSocial) {
		log.debug(">>>>>>>>>>>>>>>>>>>> Entrada del rutaPython: " + rutaPython);
		log.debug(">>>>>>>>>>>>>>>>>>>> Entrada del scriptPath: " + scriptPath);
		log.debug(">>>>>>>>>>>>>>>>>>>> Entrada del razonSocial: " + razonSocial);
		log.info(">>>>>>>>>>>>>>>>>>>> Entrada del environment: " + environment);
		boolean resultado = false;
		String rutaDirectorio = rutaPython + "Aviso_REPSE";
		String nombreArchivo = "resultado_ejecucion_repse.log";
		File directorio = new File(rutaDirectorio);
		File archivo = new File(directorio, nombreArchivo);

		try {

			// Crear el archivo si no existe
			if (!archivo.exists()) {
				archivo.createNewFile();
			}

			ProcessBuilder pb = new ProcessBuilder("python", scriptPath, razonSocial, cveEntidad, cveProceso,
					rfcCopntratista, contrato, environment, ambiente);
			File outputFile = new File(rutaPython + "resultado_ejecucion_repse.log");
			log.info("%%%%%%%%% Entra al proceso: " + scriptPath);

			// Archivo para guardar la salida del proceso
			// Construir el comando para ejecutar Python

			pb.redirectErrorStream(true);
			pb.redirectOutput(outputFile);

			Process process = pb.start();

			// Esperar a que el proceso termine
			int exitCode = process.waitFor();
			resultado = false;
			if (exitCode == -2 || exitCode == 0) {
				resultado = true;
				log.error(">>>>>>>>>>>>>>>>>>>> Error en la ejecución pythonExecutor: NO HAY DATOS. ");
			}
		} catch (Exception e) {
			log.error(">>>>>>>>>>>>>>>>>>>> Error en la ejecución pythonExecutor: " + e.getMessage());
		}

		log.info(">>>>>>>>>>>>>>>>>>>> pythonExecutor <<<<<<<<<<<<<<<<<< {}:", resultado);
		return resultado;
	}
}
