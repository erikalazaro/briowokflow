/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.helpers;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.briomax.briobpm.business.convertes.ICrAvisoICSOEConverter;
import com.briomax.briobpm.business.convertes.ICrAvisoSISUBConverter;
import com.briomax.briobpm.business.convertes.ICrCedulaDetCuotasConverter;
import com.briomax.briobpm.business.convertes.ICrComprobanteCuotaOPConverter;
import com.briomax.briobpm.business.convertes.ICrDeclaracionComplementarioConverter;
import com.briomax.briobpm.business.convertes.ICrDeclaracionProvicionalConverter;
import com.briomax.briobpm.business.convertes.ICrFormatoCuotaOPConverter;
import com.briomax.briobpm.business.convertes.ICrPagoBancarioConverter;
import com.briomax.briobpm.business.convertes.ICrReciboNominaConverter;
import com.briomax.briobpm.business.convertes.ICrRegistroObraConverter;
import com.briomax.briobpm.business.convertes.IRepseConverter;
import com.briomax.briobpm.business.helpers.base.ICargaPdfHelper;
import com.briomax.briobpm.business.helpers.base.IConsultaPdfHelper;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.CrAvisoICSOE;
import com.briomax.briobpm.persistence.entity.CrAvisoICSOEPK;
import com.briomax.briobpm.persistence.entity.CrAvisoSISUB;
import com.briomax.briobpm.persistence.entity.CrAvisoSISUBPK;
import com.briomax.briobpm.persistence.entity.CrCedulaDetCuotas;
import com.briomax.briobpm.persistence.entity.CrCedulaDetCuotasPK;
import com.briomax.briobpm.persistence.entity.CrComprobanteCuotasOP;
import com.briomax.briobpm.persistence.entity.CrComprobanteCuotasOPPK;
import com.briomax.briobpm.persistence.entity.CrConsultaRepse;
import com.briomax.briobpm.persistence.entity.CrDeclaracionComplementario;
import com.briomax.briobpm.persistence.entity.CrDeclaracionProvisional;
import com.briomax.briobpm.persistence.entity.CrDeclaracionProvisionalPK;
import com.briomax.briobpm.persistence.entity.CrFormatoCuotasOP;
import com.briomax.briobpm.persistence.entity.CrFormatoCuotasOPPK;
import com.briomax.briobpm.persistence.entity.CrPagoBancario;
import com.briomax.briobpm.persistence.entity.CrPagoBancarioPK;
import com.briomax.briobpm.persistence.entity.CrPdfFiles;
import com.briomax.briobpm.persistence.entity.CrProgramacionProceso;
import com.briomax.briobpm.persistence.entity.CrProgramacionProcesoPK;
import com.briomax.briobpm.persistence.entity.CrReciboNomina;
import com.briomax.briobpm.persistence.entity.CrReciboNominaPK;
import com.briomax.briobpm.persistence.entity.CrRegistroObra;
import com.briomax.briobpm.persistence.repository.ICrAvisoICSOERepository;
import com.briomax.briobpm.persistence.repository.ICrAvisoSISUBRepository;
import com.briomax.briobpm.persistence.repository.ICrCedulaDetCuotasRepository;
import com.briomax.briobpm.persistence.repository.ICrComprobanteCuotaOPRepository;
import com.briomax.briobpm.persistence.repository.ICrConsultaRepseRepository;
import com.briomax.briobpm.persistence.repository.ICrDeclaracionComplementarioRepository;
import com.briomax.briobpm.persistence.repository.ICrDeclaracionProvisionalRepository;
import com.briomax.briobpm.persistence.repository.ICrFormatoCuotaOPRepository;
import com.briomax.briobpm.persistence.repository.ICrPagoBancarioRepository;
import com.briomax.briobpm.persistence.repository.ICrPdfFilesRepository;
import com.briomax.briobpm.persistence.repository.ICrProgramacionProcesoRepository;
import com.briomax.briobpm.persistence.repository.ICrReciboNominaRepository;
import com.briomax.briobpm.persistence.repository.ICrRegistroObraRepository;
import com.briomax.briobpm.transferobjects.PdfGridTO;
import com.briomax.briobpm.transferobjects.dynamicForm.Properties;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.repse.ListaDocumentosTrabajadorTO;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class CargaPdfHelper.java es ...
 * 
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Feb 25, 2020 6:59:06 PM Modificaciones:
 * @since JDK 1.8
 */
@Service
@Transactional
@Slf4j
public class CargaPdfHelper implements ICargaPdfHelper {

	/** El atributo o variable. */
	@Autowired
	private ICrReciboNominaRepository crReciboNominaRepository;

	/** El atributo o variable. */
	@Autowired
	private ICrDeclaracionProvisionalRepository crDeclaracionProvisionalRepository;

	/** El atributo o variable. */
	@Autowired
	private ICrPagoBancarioRepository crPagoBancarioRepository;

	/** El atributo o variable. */
	@Autowired
	private ICrRegistroObraRepository crRegistroObraRepository;

	/** El atributo o variable. */
	@Autowired
	private ICrComprobanteCuotaOPRepository crComprobanteCuotaOPRepository;
	
	/** El atributo o variable. */
	@Autowired
	private ICrFormatoCuotaOPRepository crFormatoCuotaOPRepository;
	
	/** El atributo o variable repository. */
	@Autowired
	private ICrCedulaDetCuotasRepository crCedulaDetCuotasRepository;

	/** El atributo o variable. */
	@Autowired
	private ICrDeclaracionComplementarioRepository crDeclaracionComplementarioRepository;
	
	/** El atributo o variable. */
	@Autowired
	private ICrAvisoICSOERepository crAvisoICSOERepository;
	
	/** El atributo o variable. */
	@Autowired
	private ICrAvisoSISUBRepository crAvisoSISUBRepository;
	
	/** El atributo o variable repository. */
	@Autowired
	private ICrConsultaRepseRepository crConsultaRepseRepository;
	
	/** El atributo o variable repository. */
	@Autowired
	private ICrProgramacionProcesoRepository crProgramacionProcesoRepository;
	
	/** El atributo o variable repository. */
	@Autowired
	private IConsultaPdfHelper consultaPdfHelper;
	
	/** El atributo o variable repository. */
	@Autowired
	private ICrPdfFilesRepository crPdfFilesRepository;
	
	
	/** El atributo o variable lectorPDFRecibodeNomina. */
	@Value("${path.python}")
    private String rutaPython;
	
	/** El atributo o variable lectorPDFRecibodeNomina. */
	@Value("${spring.profiles.active}")
    private String environment;
	
	@Value("${spring.datasource.active.manager}")
    private String ambiente;
	
	private static final String ACUSE_SIROC = "REGISTRO_SIROC";
	private static final String REGISTRO_SIROC_BIM = "REGISTRO_SIROC_BIM";
	private static final String REGISTRO_SIROC_TERM_OBRA = "REGISTRO_SIROC_TERM_OBRA";
	private static final String INCIDENCIAS_SIROC = "INCIDENCIAS_SIROC";
	private static final String ACT_DATOS_SIROC = "ACT_DATOS_SIROC";
	private static final String DEC_IVA_CONTRATISTA = "DEC_IVA_CONTRATISTA";    
	private static final String PAGO_BANCARIO = "COMPROB_PAGO_IVA_E_ISR";
	private static final String ENVIO_RECIBOS_NOMINA = "ENVIO_RECIBOS_NOMINA";
	private static final String SISTEMA_UNICO_AUTODETERMINACION = "SUA_MENSUAL";
	private static final String SISTEMA_UNICO_AUTODETERMINACION_BIM = "SUA_BIMESTRAL";
	private static final String FORMATO_CUOTA_OBRERO_PATRONALES = "LINEA_CAP_SIPARE_COP";    	
	private static final String COMPROBANTE_CUOTAS_OBRERO_PATRONALES = "COMPROBANTE_PAGO_SIPARE";
	private static final String AVISO_ICSOE = "ACUSE_ICSOE_IMSS";  
	private static final String AVISO_SISUB = "ACUSE_SISUB_INFONAVIT";
	private static final String AVISO_REPSE = "AVISO_REPSE";
	private static final String DEC_IVA_CONTRATISTA_COMPLEMENTARIO = "DEC_IVA_CONTRATISTA_COMPLEMENTARIO";
	
	/** Crear una nueva instancia del objeto usuarios helper. */
	public CargaPdfHelper() {
	}

	@Override
	public PdfGridTO execEnvioRecibosNomina(DatosAutenticacionTO session, String rfc, String contrato,
			String cveProceso, String nombreArchivo, Date fechaCarga, String nombrePython) throws BrioBPMException {
		log.info(">>>>>>>>>>>>>>>>>>>> execEnvioRecibosNomina <<<<<<<<<<<<<<<<<<");

		String lectorPDFRecibodeNomina = rutaPython + "LectorPDFRecibodeNomina.py";
		if (nombrePython != null && !nombrePython.isEmpty()) {
			lectorPDFRecibodeNomina = rutaPython + nombrePython;
		}
		log.info(">>>>>>>>>>>>>>>>>>>> lectorPDFRecibodeNomina {}  nombreArchivo {}", lectorPDFRecibodeNomina , nombreArchivo);
		boolean res = pythonExecutor (lectorPDFRecibodeNomina,  nombreArchivo);
		log.info(">>>>>>>>>>>>>>>>>>>> res {} ", res);
		PdfGridTO result = null;
		if (res) {
	
			List<Properties> columns = new ArrayList<Properties>();
	
			Properties column = Properties.builder().width(300).cveVariable("situacionCarga").etiqueta("Estatus de Carga")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
			
			column = Properties.builder().width(200).cveVariable("fechaCarga").etiqueta("Fecha de Carga")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
			
			column = Properties.builder().width(200).cveVariable("rfcContratista").etiqueta("RFC - Patrón")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(500).cveVariable("razonSocialPatron").etiqueta("Razón Social - Patrón")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("registroPatronal").etiqueta("Registro Patronal")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("fechaInicioPeriodo").etiqueta("Fecha Inicio")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("fechaFinalPeriodo").etiqueta("Fecha Final")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("fechaPago").etiqueta("Fecha de Pago")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("fechaTimbrado").etiqueta("Fecha de Timbrado")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("tipoNomina").etiqueta("Tipo de Nómina")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(500).cveVariable("nombreTrabajdor").etiqueta("Nombre del Empleado")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("curpTrabajdor").etiqueta("CURP - Empleado")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("rfcTrabajador").etiqueta("RFC - Empleado")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("nssTrabajdor").etiqueta("NSS").tipoDato("ALFANUMERICO")
					.visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("salarioBase").etiqueta("Salario Base de Cotización")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			List<Object> listDto = new ArrayList<Object>();
			//Date objDate = new Date();
			List<Date> limitesFecha = consultaPdfHelper.obtieneRangoFechas(fechaCarga, fechaCarga);
			Date fechaInicio = limitesFecha.get(0);
			Date fechaFin = limitesFecha.get(1);
	
			List<CrReciboNomina> listEntity = crReciboNominaRepository.getCargaReciboNimina(session.getCveEntidad(),
					cveProceso, rfc, contrato, fechaInicio, fechaFin );
			log.debug(">>>>>>>>>>>>>>>>>>>> listEntity:" + listEntity.size());
			if (listEntity.size() > 0) {
				listDto.addAll(listEntity.stream().map(ICrReciboNominaConverter.converterEntityToDTO)
						.collect(Collectors.toList()));
			}
	
			 result = PdfGridTO.builder().cells(listDto).columns(columns).build();
		}
		
		return result;
	}

	@Override
	public PdfGridTO execDeclaracionProvicionalContratista(DatosAutenticacionTO session, String rfc, String contrato,
			String proceso, String nombreArchivo, Date fechaCarga, String nombrePython) throws BrioBPMException {
		log.info(">>>>>>>>>>>>>>>>>>>> execDeclaracionProvicionalContratista <<<<<<<<<<<<<<<<<<");

		//crDeclaracionProvisionalRepository.deleteCrDeclaracionProvisional(session.getCveEntidad(), proceso, rfc, contrato, fechaCarga);
		
		// Crear el ProcessBuilder con la ruta del archivo .bat
		//ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "start", "DeclaracionProvisional.bat", nombreArchivo);	
		String lectorPDFAcusedeRecibo = rutaPython + "LectorPDFAcusedeRecibo.py";
		if (nombrePython != null && !nombrePython.isEmpty()) {
			lectorPDFAcusedeRecibo = rutaPython + nombrePython;
		}
		boolean res = pythonExecutor (lectorPDFAcusedeRecibo,  nombreArchivo);
		PdfGridTO result = null;
		

		List<Properties> columns = new ArrayList<Properties>();
		
		if (res) {
			Properties column = Properties.builder().width(300).cveVariable("situacionCarga").etiqueta("Estatus de Carga")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
			
			column = Properties.builder().width(200).cveVariable("fechaCarga").etiqueta("Fecha de Carga")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
			
			column = Properties.builder().width(200).cveVariable("rfc").etiqueta("RFC").tipoDato("ALFANUMERICO")
					.visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("razonSocial").etiqueta("Razón Social")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("tipoDeclaracion").etiqueta("Tipo de Declaración")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("periodoDeclaracion").etiqueta("Período de la Declaración")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("ejercicio").etiqueta("Ejercicio").tipoDato("ALFANUMERICO")
					.visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("fechaHoraPresentacion")
					.etiqueta("Fecha y Hora de Presentación").tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(500).cveVariable("lineaCaptura").etiqueta("Línea de Captura")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(500).cveVariable("conceptoPago").etiqueta("Concepto de Pago")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("importeApagar").etiqueta("Importe Total a Pagar")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			List<Object> listDto = new ArrayList<Object>();
					
			//Date objDate = new Date();
			List<Date> limitesFecha = consultaPdfHelper.obtieneRangoFechas(fechaCarga, fechaCarga);
			Date fechaInicio = limitesFecha.get(0);
			Date fechaFin = limitesFecha.get(1);
	
			List<CrDeclaracionProvisional> listEntity = crDeclaracionProvisionalRepository
					.getDeclaracionProvisional(session.getCveEntidad(), proceso, rfc, contrato, fechaInicio, fechaFin);
			log.debug(">>>>>>>>>>>>>>>>>>>> listEntity:" + listEntity.size());
			if (listEntity.size() > 0) {
				listDto.addAll(listEntity.stream().map(ICrDeclaracionProvicionalConverter.converterEntityToDTO)
						.collect(Collectors.toList()));
			}
	
			result = PdfGridTO.builder().cells(listDto).columns(columns).build();
		}

		return result;
	}

	@Override
	public PdfGridTO execPagoBancario(DatosAutenticacionTO session, String rfc, String contrato, 
			String cveProceso, String nombreArchivo, Date fechaCarga, String nombrePython) throws BrioBPMException {
		log.info(">>>>>>>>>>>>>>>>>>>> execPagoBancario <<<<<<<<<<<<<<<<<<");

		//crPagoBancarioRepository.deleteCrPagoBancarioRepository(session.getCveEntidad(), cveProceso, rfc, contrato, fechaCarga );
		
		// Crear el ProcessBuilder con la ruta del archivo .bat
		//ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "start", "PagoBancario.bat", nombreArchivo);
		String pagoBancario = rutaPython + "LectorPDFPagoBancario.py";
		if (nombrePython != null && !nombrePython.isEmpty()) {
			pagoBancario = rutaPython + nombrePython;
		}
		boolean res = pythonExecutor (pagoBancario,  nombreArchivo);
		PdfGridTO result = null;
		

		List<Properties> columns = new ArrayList<Properties>();
		
		if (res) {
			Properties 	column = Properties.builder().width(300).cveVariable("situacionCarga").etiqueta("Estatus de Carga")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
			
			column = Properties.builder().width(200).cveVariable("fechaCarga").etiqueta("Fecha de Carga")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
			
			column = Properties.builder().width(200).cveVariable("fechaPago").etiqueta("Fecha de pago")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(500).cveVariable("lineaCaptura").etiqueta("Linea de Captura")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			List<Object> listDto = new ArrayList<Object>();
			//Date objDate = new Date();
			List<Date> limitesFecha = consultaPdfHelper.obtieneRangoFechas(fechaCarga, fechaCarga);
			Date fechaInicio = limitesFecha.get(0);
			Date fechaFin = limitesFecha.get(1);
	
			List<CrPagoBancario> listEntity = crPagoBancarioRepository.getCargaPagoBancario(session.getCveEntidad(),
					cveProceso, rfc, contrato, fechaInicio, fechaFin );
			log.debug(">>>>>>>>>>>>>>>>>>>> listEntity:" + listEntity.size());
			if (listEntity.size() > 0) {
				listDto.addAll(listEntity.stream().map(ICrPagoBancarioConverter.converterEntityToDTO)
						.collect(Collectors.toList()));
			}
	
			result = PdfGridTO.builder().cells(listDto).columns(columns).build();
		}

		return result;

	}

	@Override
	public PdfGridTO execRegistroObra(DatosAutenticacionTO session, String rfc, String contrato, 
			String cveProceso, String nombreArchivo, Date fechaCarga, String nombrePython)	throws BrioBPMException {
		log.info(">>>>>>>>>>>>>>>>>>>> execRegistroObra <<<<<<<<<<<<<<<<<<");

		//crRegistroObraRepository.deleteCrRegistroObra(session.getCveEntidad(), cveProceso, rfc, contrato, fechaCarga);
		
		// Crear el ProcessBuilder con la ruta del archivo .bat
		//ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "start", "RegistroObra.bat", nombreArchivo); 
		String registroObra = rutaPython + "LectorPDFRegistroObra.py";
		if (nombrePython != null && !nombrePython.isEmpty()) {
			registroObra = rutaPython + nombrePython;
		}
		boolean res = pythonExecutor (registroObra,  nombreArchivo);
		PdfGridTO result = null;
		

		List<Properties> columns = new ArrayList<Properties>();
		
		if (res) {

			Properties column = Properties.builder().width(300).cveVariable("situacionCarga").etiqueta("Estatus de Carga")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
			
			column = Properties.builder().width(200).cveVariable("fechaCarga").etiqueta("Fecha de Carga")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
			
			column = Properties.builder().width(200).cveVariable("razonSocial").etiqueta("Razón Social")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("registroPatronal").etiqueta("Registro Patronal")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("numeroRepse").etiqueta("Número REPSE")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("registroObra").etiqueta("Registro Obra")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("montoObra").etiqueta("Monto").tipoDato("ALFANUMERICO")
					.visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(400).cveVariable("ubicacionObra").etiqueta("Ubicación de la obra")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("fechaRegistro").etiqueta("Fecha de registro")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			List<Object> listDto = new ArrayList<Object>();
			//Date objDate = new Date();
			List<Date> limitesFecha = consultaPdfHelper.obtieneRangoFechas(fechaCarga, fechaCarga);
			Date fechaInicio = limitesFecha.get(0);
			Date fechaFin = limitesFecha.get(1);
	
			List<CrRegistroObra> listEntity = crRegistroObraRepository.getCargaRegistroObra(session.getCveEntidad(),
					cveProceso, rfc, contrato, fechaInicio,fechaFin );
			log.debug(">>>>>>>>>>>>>>>>>>>> listEntity:" + listEntity.size());
			if (listEntity.size() > 0) {
				listDto.addAll(listEntity.stream().map(ICrRegistroObraConverter.converterEntityToDTO)
						.collect(Collectors.toList()));
			}
	
			result = PdfGridTO.builder().cells(listDto).columns(columns).build();
		}

		return result;
	}

	@Override
	public PdfGridTO execComprobanteCuotaOP(DatosAutenticacionTO session, String rfc, String contrato,
			String cveProceso, String nombreArchivo, Date fechaCarga, String nombrePython) throws BrioBPMException {
		log.info(">>>>>>>>>>>>>>>>>>>> execComprobanteCuotaOP <<<<<<<<<<<<<<<<<<");
		
		//crComprobanteCuotaOPRepository.deleteCrComprobanteCuotaOP(session.getCveEntidad(), cveProceso, rfc, contrato, fechaCarga);

		// Crear el ProcessBuilder con la ruta del archivo .bat
		//ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "start", "ComprobanteCuotaOP.bat", nombreArchivo);
		String comprobanteCuotaOP = rutaPython + "LectorPDFComprobanteCuotaOP.py";
		if (nombrePython != null && !nombrePython.isEmpty()) {
			comprobanteCuotaOP = rutaPython + nombrePython;
		}
		boolean res = pythonExecutor (comprobanteCuotaOP,  nombreArchivo);
		PdfGridTO result = null;
		

		List<Properties> columns = new ArrayList<Properties>();
		
		if (res) {

			Properties column = Properties.builder().width(300).cveVariable("situacionCarga").etiqueta("Estatus de Carga")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
			
			column = Properties.builder().width(200).cveVariable("fechaCarga").etiqueta("Fecha de Carga")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
			
			column = Properties.builder().width(200).cveVariable("razonSocial").etiqueta("Razón Social")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("registroPatronal").etiqueta("Registro Patronal")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("fechaPago").etiqueta("Fecha de pago")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("periodoPagoinfonavit")
					.etiqueta("Periodo de pago INFONAVIT").tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("periodoPagoimss").etiqueta("Periodo de pago IMSS")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			List<Object> listDto = new ArrayList<Object>();
			//Date objDate = new Date();
			List<Date> limitesFecha = consultaPdfHelper.obtieneRangoFechas(fechaCarga, fechaCarga);
			Date fechaInicio = limitesFecha.get(0);
			Date fechaFin = limitesFecha.get(1);
	
			List<CrComprobanteCuotasOP> listEntity = crComprobanteCuotaOPRepository
					.getCargaComprobanteCuotaOP(session.getCveEntidad(), cveProceso, rfc, contrato, fechaInicio, fechaFin);
			log.debug(">>>>>>>>>>>>>>>>>>>> listEntity:" + listEntity.size());
			if (listEntity.size() > 0) {
				listDto.addAll(listEntity.stream().map(ICrComprobanteCuotaOPConverter.converterEntityToDTO)
						.collect(Collectors.toList()));
			}
	
			result = PdfGridTO.builder().cells(listDto).columns(columns).build();
		}

		return result;
	}

	@Override
	public PdfGridTO execFormatoCuotaOP(DatosAutenticacionTO session, String rfc, String contrato,
			String cveProceso, String nombreArchivo, Date fechaCarga, String nombrePython) throws BrioBPMException {
		log.info(">>>>>>>>>>>>>>>>>>>> execFormatoCuotaOP <<<<<<<<<<<<<<<<<<");

		//crFormatoCuotaOPRepository.deleteCrFormatoCuotaOP(session.getCveEntidad(), cveProceso, rfc, contrato, fechaCarga);
		
		// Crear el ProcessBuilder con la ruta del archivo .bat
		//ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "start", "FormatoCuotaOP.bat", nombreArchivo);
		String formatoCuotaOP = rutaPython + "LectorPDFFormatoCuotaOP.py";
		if (nombrePython != null && !nombrePython.isEmpty()) {
			formatoCuotaOP = rutaPython + nombrePython;
		}
		boolean res = pythonExecutor (formatoCuotaOP,  nombreArchivo);
		PdfGridTO result = null;
		

		List<Properties> columns = new ArrayList<Properties>();
		
		if (res) {
		
			Properties column = Properties.builder().width(300).cveVariable("situacionCarga").etiqueta("Estatus de Carga")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
			
			column = Properties.builder().width(200).cveVariable("fechaCarga").etiqueta("Fecha de Carga")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
			
			column = Properties.builder().width(200).cveVariable("razonSocial").etiqueta("Razón Social")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
			
			column = Properties.builder().width(200).cveVariable("registroPatronal").etiqueta("Registro Patronal")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("fechaPago").etiqueta("Fecha de pago")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("periodoPagoInfonavit")
					.etiqueta("Periodo de pago INFONAVIT").tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("periodoPagoImss").etiqueta("Periodo de pago IMSS")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			List<Object> listDto = new ArrayList<Object>();
			//Date objDate = new Date();
			List<Date> limitesFecha = consultaPdfHelper.obtieneRangoFechas(fechaCarga, fechaCarga);
			Date fechaInicio = limitesFecha.get(0);
			Date fechaFin = limitesFecha.get(1);
	
			List<CrFormatoCuotasOP> listEntity = crFormatoCuotaOPRepository
					.getCargaFormatoCuotasOP(session.getCveEntidad(), cveProceso, rfc, contrato, fechaInicio,fechaFin );
			log.info(">>>>>>>>>>>>>>>>>>>> listEntity:" + listEntity.size());
			if (listEntity.size() > 0) {
				listDto.addAll(listEntity.stream().map(ICrFormatoCuotaOPConverter.converterEntityToDTO)
						.collect(Collectors.toList()));
			}
	
			result = PdfGridTO.builder().cells(listDto).columns(columns).build();
		}

		return result;
	}

	@Override
	public PdfGridTO execSUA(DatosAutenticacionTO session, String rfc, String contrato, 
			String cveProceso, String nombreArchivo, Date fechaCarga, String nombrePython) throws BrioBPMException {
		
		log.info(">>>>>>>>>>>>>>>>>>>> execSUA <<<<<<<<<<<<<<<<<<");
		//crCedulaDetCuotasRepository.deleteCrCedulaDetCuotas(session.getCveEntidad(), cveProceso, rfc, contrato, fechaCarga);

		// Crear el ProcessBuilder con la ruta del archivo .bat
		//ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "start", "SUA.bat", nombreArchivo);
		String SUA = rutaPython + "LectorPDFSUA.py";
		if (nombrePython != null && !nombrePython.isEmpty()) {
			SUA = rutaPython + nombrePython;
		}
		boolean res = pythonExecutor (SUA,  nombreArchivo);
		PdfGridTO result = null;
		

		List<Properties> columns = new ArrayList<Properties>();
		
		if (res) {
			Properties column = Properties.builder().width(200).cveVariable("situacionCarga").etiqueta("Estatus de Carga")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
			
			column = Properties.builder().width(200).cveVariable("situacionTrabajador").etiqueta("Estatus del Trabajador")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
			
			column = Properties.builder().width(200).cveVariable("fechaCarga").etiqueta("Fecha de Carga")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
			
			column = Properties.builder().width(200).cveVariable("razonSocial").etiqueta("Razón Social")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("registroPatronal").etiqueta("Registro Patronal")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("nssTrabajador").etiqueta("Nss Trabajador")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("nombreTrabajador").etiqueta("Nombre Trabajador")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("rfcCurpTrabajador")
					.etiqueta("RFC CURP Trabajador").tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("salarioBaseCotizacion").etiqueta("Salario Base Cotización")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("periodo").etiqueta("Periodo")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			log.info("columnas "+columns.size());
			
			List<Object> listDto = new ArrayList<Object>();
			//Date objDate = new Date();
			List<Date> limitesFecha = consultaPdfHelper.obtieneRangoFechas(fechaCarga, fechaCarga);
			Date fechaInicio = limitesFecha.get(0);
			Date fechaFin = limitesFecha.get(1);
	
			List<CrCedulaDetCuotas> listEntity = crCedulaDetCuotasRepository
					.getCrCedulaDetCuotas(session.getCveEntidad(), cveProceso, rfc, contrato, fechaInicio,fechaFin );
			log.info(">>>>>>>>>>>>>>>>>>>> listEntity:" + listEntity.size());
			if (listEntity.size() > 0) {
				listDto.addAll(listEntity.stream().map(ICrCedulaDetCuotasConverter.converterEntityToDTO)
						.collect(Collectors.toList()));
			}
	
			result = PdfGridTO.builder().cells(listDto).columns(columns).build();
		}

		return result;
	}

	@Override
	public PdfGridTO execAvisoRepse(DatosAutenticacionTO session, String rfc, String contrato, 
			String cveProceso, String nombreArchivo, Date fechaCarga, String nombrePython) throws BrioBPMException {
		log.info(">>>>>>>>>>>>>>>>>>>> execConsultaRepse <<<<<<<<<<<<<<<<<<");
		//crConsultaRepseRepository.deleteCrConsultaRepse(session.getCveEntidad(), cveProceso, rfc, contrato, fechaCarga);

		// Crear el ProcessBuilder con la ruta del archivo .bat
		//ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "start", "AvisoRepse.bat", nombreArchivo);
		String avisoRepse = rutaPython + "LectorPDFAvisodeREPSE.py";
		if (nombrePython != null && !nombrePython.isEmpty()) {
			avisoRepse = rutaPython + nombrePython;
		}
		boolean res = pythonExecutor (avisoRepse,  nombreArchivo);
		PdfGridTO result = null;
		

		List<Properties> columns = new ArrayList<Properties>();
		
		if (res) {

		
			Properties column = Properties.builder().width(200).cveVariable("razonSocial").etiqueta("Razón Social")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("folio").etiqueta("Folio")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("entidadMunicipio").etiqueta("Entidad Municipio")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("avisoFechaRegistro")
					.etiqueta("Aviso Fecha Registro").tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("imagenConsulta").etiqueta("Imagen Consulta")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column); //queda pendiente
	
			column = Properties.builder().width(200).cveVariable("descripcionServicio").etiqueta("Descripcion Servicio")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			List<Object> listDto = new ArrayList<Object>();
			//Date objDate = new Date();
			List<Date> limitesFecha = consultaPdfHelper.obtieneRangoFechas(fechaCarga, fechaCarga);
			Date fechaInicio = limitesFecha.get(0);
			Date fechaFin = limitesFecha.get(1);
	
			Optional<CrConsultaRepse> entity = crConsultaRepseRepository
					.getAvisoRepse(session.getCveEntidad(), cveProceso, rfc, contrato, fechaInicio,fechaFin);
			if (!entity.isEmpty()){
				listDto.addAll(entity.stream().map(IRepseConverter.converterEntityToDTO)
						.collect(Collectors.toList()));
			}
	
			 result = PdfGridTO.builder().cells(listDto).columns(columns).build();
		}

		return result;
	}
	
	@Override
	public PdfGridTO execDeclaracionProvicionalContratistaComplementario(DatosAutenticacionTO session, String rfc, String contrato,
			String proceso, String nombreArchivo, Date fechaCarga, String nombrePython) throws BrioBPMException {
		log.info(">>>>>>>>>>>>>>>>>>>> execDeclaracionProvicionalContratistaComplementario <<<<<<<<<<<<<<<<<<");

		//crDeclaracionComplementarioRepository.deleteCrDeclaracionComplementario(session.getCveEntidad(), proceso, rfc, contrato, fechaCarga);
		
		// Crear el ProcessBuilder con la ruta del archivo .bat
		// ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "start", "DeclaracionComplementario.bat", nombreArchivo);	
		String declaracionComplementario = rutaPython + "LectorPDFAcuseComplementario.py";
		if (nombrePython != null && !nombrePython.isEmpty()) {
			declaracionComplementario = rutaPython + nombrePython;
		}
		boolean res = pythonExecutor (declaracionComplementario,  nombreArchivo);
		PdfGridTO result = null;
		

		List<Properties> columns = new ArrayList<Properties>();
		
		if (res) {
			Properties column = Properties.builder().width(300).cveVariable("situacionCarga").etiqueta("Estatus de Carga")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
			
			column = Properties.builder().width(200).cveVariable("fechaCarga").etiqueta("Fecha de Carga")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
			
			column = Properties.builder().width(200).cveVariable("rfc").etiqueta("RFC").tipoDato("ALFANUMERICO")
					.visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("razonSocial").etiqueta("Razón Social")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("tipoDeclaracion").etiqueta("Tipo de Declaración")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("periodoDeclaracion").etiqueta("Período de la Declaración")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("ejercicio").etiqueta("Ejercicio").tipoDato("ALFANUMERICO")
					.visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("fechaHoraPresentacion")
					.etiqueta("Fecha y Hora de Presentación").tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(500).cveVariable("lineaCaptura").etiqueta("Línea de Captura")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(500).cveVariable("conceptoPago").etiqueta("Concepto de Pago")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("importeApagar").etiqueta("Importe Total a Pagar")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			List<Object> listDto = new ArrayList<Object>();
			//Date objDate = new Date();
			List<Date> limitesFecha = consultaPdfHelper.obtieneRangoFechas(fechaCarga, fechaCarga);
			Date fechaInicio = limitesFecha.get(0);
			Date fechaFin = limitesFecha.get(1);
	
			List<CrDeclaracionComplementario> listEntity = crDeclaracionComplementarioRepository
					.getDeclaracionComplementario(session.getCveEntidad(), proceso, rfc, contrato, fechaInicio, fechaFin);
			log.debug(">>>>>>>>>>>>>>>>>>>> listEntity:" + listEntity.size());
			if (listEntity.size() > 0) {
				listDto.addAll(listEntity.stream().map(ICrDeclaracionComplementarioConverter.converterEntityToDTO)
						.collect(Collectors.toList()));
			}
	
			result = PdfGridTO.builder().cells(listDto).columns(columns).build();
		}

		return result;
	}
	
	@Override
	public PdfGridTO execAvisoIcsoe(DatosAutenticacionTO session, String rfc, String contrato,
			String proceso, String nombreArchivo, Date fechaCarga,String nombrePython) throws BrioBPMException {
		log.info(">>>>>>>>>>>>>>>>>>>> execAvisoIcsoe <<<<<<<<<<<<<<<<<<");
		//crAvisoICSOERepository.deleteCrAvisoICSOE(session.getCveEntidad(), proceso, rfc, contrato, fechaCarga);
		
		// Crear el ProcessBuilder con la ruta del archivo .bat
		//ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "start", "AvisoIcsoe.bat", nombreArchivo);	
		String avisoIcsoe = rutaPython + "LectorPDFAVISOICSOE.py";
		if (nombrePython != null && !nombrePython.isEmpty()) {
			avisoIcsoe = rutaPython + nombrePython;
		}
		boolean res = pythonExecutor (avisoIcsoe,  nombreArchivo);
		PdfGridTO result = null;
		
		log.error(">>>>>>>>>>>>>>>>>>>> paso el Python");
		List<Properties> columns = new ArrayList<Properties>();
		
		if (res) {		

			Properties column = Properties.builder().width(300).cveVariable("situacionCarga").etiqueta("Estatus de Carga")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
			
			column = Properties.builder().width(200).cveVariable("fechaCarga").etiqueta("Fecha de Carga")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
			
			column = Properties.builder().width(200).cveVariable("fechaDocumento").etiqueta("Fecha documento").tipoDato("Fecha")
					.visible("SI").build();
			columns.add(column);
			
			column = Properties.builder().width(200).cveVariable("rfc").etiqueta("RFC").tipoDato("ALFANUMERICO")
					.visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("año").etiqueta("Año")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("cuatrimestre").etiqueta("Cuatrimestre")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("tipoInformativa").etiqueta("Tipo de Informativa")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			List<Object> listDto = new ArrayList<Object>();
			//Date objDate = new Date();
			List<Date> limitesFecha = consultaPdfHelper.obtieneRangoFechas(fechaCarga, fechaCarga);
			Date fechaInicio = limitesFecha.get(0);
			Date fechaFin = limitesFecha.get(1);
	
			List<CrAvisoICSOE> listEntity = crAvisoICSOERepository
					.getAvisoICSOE(session.getCveEntidad(), proceso, rfc, contrato, fechaInicio, fechaFin);
			log.debug(">>>>>>>>>>>>>>>>>>>> listEntity:" + listEntity.size());
			if (listEntity.size() > 0) {
				listDto.addAll(listEntity.stream().map(ICrAvisoICSOEConverter.converterEntityToDTO)
						.collect(Collectors.toList()));
			}
	
			result = PdfGridTO.builder().cells(listDto).columns(columns).build();
		}

		return result;
	}

	@Override
	public PdfGridTO execAvisoSisub(DatosAutenticacionTO session, String rfc, String contrato,
			String proceso, String nombreArchivo, Date fechaCarga, String nombrePython) throws BrioBPMException {
		log.error(">>>>>>>>>>>>>>>>>>>> execAvisoSisub <<<<<<<<<<<<<<<<<<");
		//crAvisoSISUBRepository.deleteCrAvisoSISUB(session.getCveEntidad(), proceso, rfc, contrato, fechaCarga);
				
		// Crear el ProcessBuilder con la ruta del archivo .bat
		//ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "start", "AvisoSisub.bat", nombreArchivo);	
		String avisoSisub = rutaPython + "LectorPDFAVISOSISUB.py";
		if (nombrePython != null && !nombrePython.isEmpty()) {
			avisoSisub = rutaPython + nombrePython;
		}
		boolean res = pythonExecutor (avisoSisub,  nombreArchivo);
		PdfGridTO result = null;
		

		List<Properties> columns = new ArrayList<Properties>();
		
		if (res) {
			Properties column = Properties.builder().width(300).cveVariable("situacionCarga").etiqueta("Estatus de Carga")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
			
			column = Properties.builder().width(200).cveVariable("fechaCarga").etiqueta("Fecha de Carga")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
			
			column = Properties.builder().width(200).cveVariable("fechaDocumento").etiqueta("Fecha documento").tipoDato("Fecha")
					.visible("SI").build();
			columns.add(column);
			
			column = Properties.builder().width(200).cveVariable("rfc").etiqueta("RFC").tipoDato("ALFANUMERICO")
					.visible("SI").build();
			columns.add(column);
			
			column = Properties.builder().width(200).cveVariable("razonSocial").etiqueta("Razón Social")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("numeroRepse").etiqueta("Número REPSE")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			column = Properties.builder().width(200).cveVariable("cuatrimestre").etiqueta("Cuatrimestre")
					.tipoDato("ALFANUMERICO").visible("SI").build();
			columns.add(column);
	
			List<Object> listDto = new ArrayList<Object>();
			//Date objDate = new Date();
			List<Date> limitesFecha = consultaPdfHelper.obtieneRangoFechas(fechaCarga, fechaCarga);
			Date fechaInicio = limitesFecha.get(0);
			Date fechaFin = limitesFecha.get(1);
	
			List<CrAvisoSISUB> listEntity = crAvisoSISUBRepository
					.getAvisoSISUB(session.getCveEntidad(), proceso, rfc, contrato, fechaInicio,fechaFin);
			log.error(">>>>>>>>>>>>>>>>>>>> listEntity:" + listEntity.size());
			if (listEntity.size() > 0) {
				listDto.addAll(listEntity.stream().map(ICrAvisoSISUBConverter.converterEntityToDTO)
						.collect(Collectors.toList()));
			}
	
			result = PdfGridTO.builder().cells(listDto).columns(columns).build();
		}

		return result;
	}
	
	@Override
	public void moveFile(String file, String path)  throws BrioBPMException{
		String msg = "Error";
		// Crear un objeto File
		Path origenPath = Paths.get(file);
		Path destinoPath  = Paths.get(path);
		try {
            // Mover el archivo al destino, reemplazando si ya existe
            Files.move(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
            log.info("El archivo ha sido movido exitosamente.");
        } catch (Exception e) {
        	log.error("Ocurrió un error al mover el archivo.");
        	throw new BrioBPMException("Ocurrió un error al mover el archivo.: " + origenPath, e);
        }
     }
	
	@Override
    public boolean  pythonExecutor (String scriptPath, String nombreArchivo) {
	 log.info(">>>>>>>>>>>>>>>>>>>> Entrada del rutaPython: " + rutaPython);
   	 log.info(">>>>>>>>>>>>>>>>>>>> Entrada del scriptPath: " + scriptPath);
   	 log.info(">>>>>>>>>>>>>>>>>>>> Entrada del nombreArchivo: " + nombreArchivo);
   	 log.info(">>>>>>>>>>>>>>>>>>>> Entrada del environment: " + environment);
   	 log.info(">>>>>>>>>>>>>>>>>>>> Entrada del ambiente: " + ambiente);
   	 boolean resultado = false;
   	 ProcessBuilder pb = new ProcessBuilder("python", scriptPath , nombreArchivo, environment, ambiente);
   	 File outputFile = new File(rutaPython + "resultado_ejecucion.log");
   	 log.info("%%%%%%%%% Entra al proceso: " + scriptPath);
	        try {       
	        	// Archivo para guardar la salida del proceso
	            
	            // Construir el comando para ejecutar Python
	            
	            pb.redirectErrorStream(true);
	            pb.redirectOutput(outputFile);
	            //pb.directory(new File(Constants.PATH_RUTA_PDF));
	            Process process = pb.start();
	            
	            // Esperar a que el proceso termine
	            int exitCode = process.waitFor();
	            resultado = true;
	            log.info("%%%%%%%%% Salida del proceso: " + exitCode);
	        } catch (Exception e) {
	            log.error(">>>>>>>>>>>>>>>>>>>> Error en la ejecución pythonExecutor: " + e.getMessage());
	        }       
	        
	    	log.info(">>>>>>>>>>>>>>>>>>>> pythonExecutor <<<<<<<<<<<<<<<<<< {}:", resultado );
	    	log.debug("%%%%%%%%% Resultado guardado en: " + outputFile.getAbsolutePath());
			 
			return resultado;	    
	}
	
	@Override
	public boolean validaCopiaArchivo(Object cellValue) {		
		boolean copiar = false;
		String situacionCarga = "";

		try {
		    // Obtener el método getSituacionCarga mediante reflexión
		    java.lang.reflect.Method method = cellValue.getClass().getMethod("getSituacionCarga");
		    
		    // Invocar el método dinámicamente
		    situacionCarga = (String) method.invoke(cellValue);
		    log.debug("Situación de carga: " + situacionCarga);
		} catch (NoSuchMethodException e) {
			log.error("El método 'getSituacionCarga' no existe en la clase: " + cellValue.getClass().getName());
		} catch (Exception e) {
			log.error("Error al invocar el método: " + e.getMessage());
		}
		
		if ("CARGA EXITOSA".equals(situacionCarga)){
			copiar = true;
		}		
		return copiar;
	}

	@Override
	public boolean otorgaPermisos(String fileString) {
		boolean res = false;
		Path path = Paths.get(fileString);

        try {
            // Verificar si el archivo existe
            if (!Files.exists(path)) {
            	log.debug("El archivo o directorio no existe.");
                return false;
            }

            // Configurar permisos 777
            Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rwxrwxrwx");
            Files.setPosixFilePermissions(path, perms);

            log.info("Permisos 777 otorgados correctamente.");
            res = true;
        } catch (UnsupportedOperationException e) {
        	log.error("El sistema de archivos no soporta permisos POSIX.");
            res = false;
        } catch (Exception e) {
        	log.error("El sistema de archivos envio un error.", e.getMessage());
            res = false;
        }
        return res;
	}

	@Override
	public void deleteFile(String carpetaRuta) {		
		// Crear un objeto File
		// String carpetaRuta = "ruta/de/la/carpeta";

	        // Crear un objeto File para la carpeta
	        File carpeta = new File(carpetaRuta);

	        // Verificar si la ruta es una carpeta
	        if (carpeta.isDirectory()) {
	            // Obtener la lista de archivos en la carpeta
	            File[] archivos = carpeta.listFiles();

	            // Verificar si la carpeta contiene archivos
	            if (archivos != null) {
	                // Iterar sobre los archivos y eliminarlos
	                for (File archivo : archivos) {
	                    if (archivo.isFile()) {
	                        boolean eliminado = archivo.delete();
	                        if (!eliminado) {
	                        	log.error("No se pudo eliminar el archivo: " + archivo.getName());
	                        }
	                    }
	                }
	            } else {
	            	log.error("La carpeta está vacía o no se pudo leer.");
	            }
	        } else {
	        	log.error("La ruta especificada no es una carpeta.");
	        }
		
	}
	
	@Override
	public boolean eliminaCarga(DatosAutenticacionTO session, ListaDocumentosTrabajadorTO listaDocumentos)
			throws BrioBPMException {
		log.info("	>>>>>>>>>>>>>>>>>>>> eliminaCarga <<<<<<<<<<<<<<<<<<");
		boolean res = false;
		String rfc = listaDocumentos.getLista().get(0).getRfc();
		String contrato = listaDocumentos.getLista().get(0).getContrato();
		String cveProceso = listaDocumentos.getLista().get(0).getCveProcesoPeriodico();
		Integer secuenciaProgramacion = listaDocumentos.getLista().get(0).getSecuencia();
		String fechaStr = listaDocumentos.getLista().get(0).getFechaCarga(); // ejemplo: "2023-10-14"
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaCarga = null;
        log.info("	>>>>>>>>>>>>>>>>>>>> asigno valores <<<<<<<<<<<<<<<<<<");
        try {
        	fechaCarga = formato.parse(fechaStr);
        } catch (ParseException e) {
			log.error("Error al convertir la cadena a Date (dd/MM/yyyy): {} error: {}", fechaStr, e.getMessage());
			throw new BrioBPMException("Error al convertir la cadena a Date (dd/MM/yyyy): " + fechaStr + " - " + e.getMessage(), e);
        }
        	String tipoPeriodo = listaDocumentos.getLista().get(0).getTipoPeriodo();
		//ELIMINAR CR_PDF_FILES 
		//UPDATE CR_PROGRAMACION_PROCESO
        try {
        	log.info("	>>>>>>>>>>>>>>>>>>>> UPDATE CR_PROGRAMACION_PROCESO <<<<<<<<<<<<<<<<<<");
			
			CrProgramacionProcesoPK pkProg = CrProgramacionProcesoPK.builder()
					.cveEntidad(session.getCveEntidad())
					.cveIdioma(session.getCveIdioma().toUpperCase())
					.cveLocalidad(session.getCveLocalidad())
					.rfc(rfc)				
					.cveProcesoPeriodico(cveProceso)
					.secuenciaProgramacion(secuenciaProgramacion)		
					.contrato(contrato)	
					.cvePeriodicidad(tipoPeriodo)
					.fechaProgramacion(fechaCarga)
					.build();

			Optional<CrProgramacionProceso> prog = crProgramacionProcesoRepository.findById(pkProg);	
			prog.ifPresent(p -> {
				p.setSituacionEjecucion("PROGRAMADO");
				p.setFechaEjecucion(null)	;	
				crProgramacionProcesoRepository.save(p);
			});
			
			/*crProgramacionProcesoRepository.updateProceso(session.getCveEntidad(), 
					session.getCveIdioma(), session.getCveLocalidad(),  rfc, 
					contrato, cveProceso, secuenciaProgramacion, fechaCarga);*/
			log.info("	>>>>>>>>>>>>>>>>>>>> DELETE CR_PDFILES antes <<<<<<<<<<<<<<<<<<");
			List <CrPdfFiles> pdfFiles = crPdfFilesRepository.getDocumento(session.getCveEntidad(), cveProceso, fechaCarga, rfc, contrato);
			crPdfFilesRepository.deleteInBatch(pdfFiles);
			log.info("	>>>>>>>>>>>>>>>>>>>> UPDATE AND DELETE FINALIZADO <<<<<<<<<<<<<<<<<<");
        }
		catch (Exception e) {			
			log.error("Ocurrió un error al eliminar la carga.: " + e.getMessage());
			throw new BrioBPMException("Ocurrió un error al eliminar la carga.: " + e.getMessage(), e);
		}
        
        List<Object> listDto = new ArrayList<Object>();
		//Date objDate = new Date();
		List<Date> limitesFecha = consultaPdfHelper.obtieneRangoFechas(fechaCarga, fechaCarga);
		Date fechaInicio = limitesFecha.get(0);
		Date fechaFin = limitesFecha.get(1);
		
		try {		 
			log.info("	>>>>>>>>>>>>>>>>>>>> switch <<<<<<<<<<<<<<<<<<");
			 switch (cveProceso) {
			 	case ENVIO_RECIBOS_NOMINA:
			 		log.info("	>>>>>>>>>>>>>>>>>>>> ENVIO_RECIBOS_NOMINA <<<<<<<<<<<<<<<<<<");
			 		
			 		List<CrReciboNomina>  recibo = crReciboNominaRepository.getCargaReciboNimina(session.getCveEntidad(),cveProceso, 
			 				rfc, contrato, fechaInicio, fechaFin);
			 		for (CrReciboNomina r: recibo) {
			 			CrReciboNominaPK pkRecibo = r.getId();
			 			crReciboNominaRepository.deleteById(pkRecibo);
			 		}
			 		log.info("	>>>>>>>>>>>>>>>>>>>>FIN  ENVIO_RECIBOS_NOMINA <<<<<<<<<<<<<<<<<<");
			 		break;
			 	case DEC_IVA_CONTRATISTA:
			 		log.info("	>>>>>>>>>>>>>>>>>>>> DEC_IVA_CONTRATISTA <<<<<<<<<<<<<<<<<<");
			 		
			 		List<CrDeclaracionProvisional> dec = crDeclaracionProvisionalRepository.getDeclaracionProvisional(session.getCveEntidad(), cveProceso, rfc, contrato, fechaInicio, fechaFin);
			 		for (CrDeclaracionProvisional d: dec) {
			 			CrDeclaracionProvisionalPK pkDec = d.getId();
			 			crDeclaracionProvisionalRepository.deleteById(pkDec);
			 		}
			 		log.info("	>>>>>>>>>>>>>>>>>>>> FIN DEC_IVA_CONTRATISTA <<<<<<<<<<<<<<<<<<");
			 		break;
			 	case PAGO_BANCARIO: 
			 		log.info("	>>>>>>>>>>>>>>>>>>>> PAGO_BANCARIO <<<<<<<<<<<<<<<<<<");
			 		List<CrPagoBancario>  pago = crPagoBancarioRepository.getCargaPagoBancario(session.getCveEntidad(),cveProceso,rfc, contrato,  fechaInicio, fechaFin);
			 		for (CrPagoBancario p: pago) {
			 			CrPagoBancarioPK pkPago = p.getId();
			 			crPagoBancarioRepository.deleteById(pkPago);
			 		}
			 		
			 		log.info("	>>>>>>>>>>>>>>>>>>>> FIN PAGO_BANCARIO <<<<<<<<<<<<<<<<<<");
			 		break;
			 	case ACUSE_SIROC: //REGISTRO_SIROC_MENSUAL
			 		log.info("	>>>>>>>>>>>>>>>>>>>> ACUSE_SIROC <<<<<<<<<<<<<<<<<<");
			 		// Usar el método personalizado en lugar de deleteById para manejar fechas correctamente
			 		List<CrRegistroObra> reg = crRegistroObraRepository.getCargaRegistroObra(session.getCveEntidad(), cveProceso, rfc, contrato, fechaInicio, fechaFin);
			 		for (CrRegistroObra r: reg) {
			 			crRegistroObraRepository.deleteById(r.getId());			 			
			 		}
			 		log.info("	>>>>>>>>>>>>>>>>>>>> FIN ACUSE_SIROC <<<<<<<<<<<<<<<<<<");
			 		break;
			 	case REGISTRO_SIROC_BIM: //REGISTRO_SIROC_BIMESTRAL
			 		log.info("	>>>>>>>>>>>>>>>>>>>> REGISTRO_SIROC_BIMESTRAL <<<<<<<<<<<<<<<<<<");
			 		// Usar el método personalizado en lugar de deleteById para manejar fechas correctamente
			 		List<CrRegistroObra> regOb = crRegistroObraRepository.getCargaRegistroObra(session.getCveEntidad(), cveProceso, rfc, contrato, fechaInicio, fechaFin);
			 		for (CrRegistroObra r: regOb) {
			 			crRegistroObraRepository.deleteById(r.getId());			 			
			 		}
			 		log.info("	>>>>>>>>>>>>>>>>>>>> FIN REGISTRO_SIROC_BIMESTRAL <<<<<<<<<<<<<<<<<<");
			 		break;
			 	case REGISTRO_SIROC_TERM_OBRA: //REGISTRO_SIROC_TERMINO DE OBRA
			 		log.info("	>>>>>>>>>>>>>>>>>>>> ACUSE_SIROC <<<<<<<<<<<<<<<<<<");
			 		// Usar el método personalizado en lugar de deleteById para manejar fechas correctamente
			 		List<CrRegistroObra> regTer = crRegistroObraRepository.getCargaRegistroObra(session.getCveEntidad(), cveProceso, rfc, contrato, fechaInicio, fechaFin);
			 		for (CrRegistroObra r: regTer) {
			 			crRegistroObraRepository.deleteById(r.getId());			 			
			 		}
			 		break;
				
			 	case INCIDENCIAS_SIROC: // INCIDENCIAS SIROC 
			 		log.info("	>>>>>>>>>>>>>>>>>>>> ACUSE_SIROC <<<<<<<<<<<<<<<<<<");
			 		// Usar el método personalizado en lugar de deleteById para manejar fechas correctamente
			 		List<CrRegistroObra> regInc = crRegistroObraRepository.getCargaRegistroObra(session.getCveEntidad(), cveProceso, rfc, contrato, fechaInicio, fechaFin);
			 		for (CrRegistroObra r: regInc) {
			 			crRegistroObraRepository.deleteById(r.getId());			 			
			 		}
			 		log.info("	>>>>>>>>>>>>>>>>>>>> FIN ACUSE_SIROC <<<<<<<<<<<<<<<<<<");
			 		break;
				
			 	case ACT_DATOS_SIROC: //ACTUALIZACION DATOS SIROC
			 		log.info("	>>>>>>>>>>>>>>>>>>>> ACUSE_SIROC <<<<<<<<<<<<<<<<<<");
			 		// Usar el método personalizado en lugar de deleteById para manejar fechas correctamente
			 		List<CrRegistroObra> regAct = crRegistroObraRepository.getCargaRegistroObra(session.getCveEntidad(), cveProceso, rfc, contrato, fechaInicio, fechaFin);
			 		for (CrRegistroObra r: regAct) {
			 			crRegistroObraRepository.deleteById(r.getId());			 			
			 		}
			 		log.info("	>>>>>>>>>>>>>>>>>>>> FIN ACUSE_SIROC <<<<<<<<<<<<<<<<<<");
			 		break;
			 	case COMPROBANTE_CUOTAS_OBRERO_PATRONALES:
			 		log.info("	>>>>>>>>>>>>>>>>>>>> COMPROBANTE_CUOTAS_OBRERO_PATRONALES <<<<<<<<<<<<<<<<<<");
			 		CrComprobanteCuotasOPPK pkCuotas = null;			 					 		
			 		List<CrComprobanteCuotasOP> comprobante = crComprobanteCuotaOPRepository.getCargaComprobanteCuotaOP(session.getCveEntidad(), cveProceso, rfc, contrato, fechaInicio, fechaFin)	;
			 		for (CrComprobanteCuotasOP f: comprobante) {
			 			pkCuotas = f.getId();
			 			crComprobanteCuotaOPRepository.deleteById(pkCuotas);
			 		}
			 		crComprobanteCuotaOPRepository.deleteById(pkCuotas);
			 		log.info("	>>>>>>>>>>>>>>>>>>>> FIN COMPROBANTE_CUOTAS_OBRERO_PATRONALES<<<<<<<<<<<<<<<<<<");
			 		break;
			 	case FORMATO_CUOTA_OBRERO_PATRONALES:
			 		log.info("	>>>>>>>>>>>>>>>>>>>> FORMATO_CUOTA_OBRERO_PATRONALES <<<<<<<<<<<<<<<<<<");
			 		
			 		CrFormatoCuotasOPPK pkFormato = null;			 		
			 		List<CrFormatoCuotasOP> formatoC = crFormatoCuotaOPRepository.getCargaFormatoCuotasOP(session.getCveEntidad(), cveProceso, rfc, contrato, fechaInicio, fechaFin)	;
			 		for (CrFormatoCuotasOP f: formatoC) {
			 			pkFormato = f.getId();
			 			crFormatoCuotaOPRepository.deleteById(pkFormato);
			 		}
			 		log.info("	>>>>>>>>>>>>>>>>>>>> FIN FORMATO_CUOTA_OBRERO_PATRONALES <<<<<<<<<<<<<<<<<<");
			 		break;
			 	case SISTEMA_UNICO_AUTODETERMINACION: // SUA mensual- CEdula de determinaciOn de cuotas
			 		log.info("	>>>>>>>>>>>>>>>>>>>> SISTEMA_UNICO_AUTODETERMINACION <<<<<<<<<<<<<<<<<<");
			 		CrCedulaDetCuotasPK pkSUAB = null;
			 		 List<CrCedulaDetCuotas> cedula = crCedulaDetCuotasRepository.getCrCedulaDetCuotasByPeriodo(session.getCveEntidad(),cveProceso, rfc, contrato, fechaInicio, fechaFin, "MENSUAL");
					for (CrCedulaDetCuotas c: cedula) {
						pkSUAB = c.getId();
			 			crCedulaDetCuotasRepository.deleteById(pkSUAB);
					}
			 		log.info("	>>>>>>>>>>>>>>>>>>>>FIN SISTEMA_UNICO_AUTODETERMINACION <<<<<<<<<<<<<<<<<<");
			 		break;
			 	case SISTEMA_UNICO_AUTODETERMINACION_BIM:  //SUA bimestral-Cedula de determinacion COP, aportaciones y amortizaciones
			 		log.info("	>>>>>>>>>>>>>>>>>>>> SISTEMA_UNICO_AUTODETERMINACION_BIM <<<<<<<<<<<<<<<<<<");
			 		CrCedulaDetCuotasPK pkSUABBim = null;
			 		 List<CrCedulaDetCuotas> cedulaBim = crCedulaDetCuotasRepository.getCrCedulaDetCuotasByPeriodo(session.getCveEntidad(),cveProceso, rfc, contrato, fechaInicio, fechaFin, "BIMESTRAL");
					for (CrCedulaDetCuotas c: cedulaBim) {
						pkSUABBim = c.getId();
			 			crCedulaDetCuotasRepository.deleteById(pkSUABBim);
					}
					log.info("	>>>>>>>>>>>>>>>>>>>> SISTEMA_UNICO_AUTODETERMINACION_BIM <<<<<<<<<<<<<<<<<<");
					break;
			 	case AVISO_ICSOE:
			 		log.info("	>>>>>>>>>>>>>>>>>>>> AVISO_ICSOE <<<<<<<<<<<<<<<<<<");
			 		List<CrAvisoICSOE> getAvisoICSOE = crAvisoICSOERepository.getAvisoICSOE(session.getCveEntidad(), cveProceso, rfc, contrato, fechaInicio, fechaFin);
			 		CrAvisoICSOEPK pkAviso = null;
			 		for (CrAvisoICSOE aviso: getAvisoICSOE) {
			 			pkAviso = aviso.getId();
			 			crAvisoICSOERepository.deleteById(pkAviso);
			 		}
			 		log.info("	>>>>>>>>>>>>>>>>>>>>FIN AVISO_ICSOE <<<<<<<<<<<<<<<<<<");
				break;
			 	case AVISO_SISUB:
			 		log.info("	>>>>>>>>>>>>>>>>>>>> AVISO_SISUB <<<<<<<<<<<<<<<<<<");
			 		CrAvisoSISUBPK pkAvisoSISUB = null;
			 		List<CrAvisoSISUB> sisub = crAvisoSISUBRepository.getAvisoSISUB(session.getCveEntidad(), cveProceso, rfc, contrato, fechaInicio, fechaFin);
			 		for (CrAvisoSISUB sub: sisub) {
			 			pkAvisoSISUB = sub.getId();
			 			crAvisoSISUBRepository.deleteById(pkAvisoSISUB);
			 		}
			 		
			 		
			 		log.info("	>>>>>>>>>>>>>>>>>>>> AVISO_SISUB <<<<<<<<<<<<<<<<<<");
				break;
			 	default:
			 		log.info("	>>>>>>>>>>>>>>>>>>>> default NO HACE NADA <<<<<<<<<<<<<<<<<<");
				break;
			}
		
			 res = true;
	} catch (Exception ex) { // | ConverterExcepcion
		log.error("cargaPdf BrioException:", ex.getMessage());
		throw new BrioBPMException("Ocurrió un error al eliminar la carga.: " + ex.getMessage(), ex);
	}
			  //log.info(">>>>>>> listaDatos: {}", resultu.getColumns().size());
			  	    
				
			
		return res;
	}
	
}
