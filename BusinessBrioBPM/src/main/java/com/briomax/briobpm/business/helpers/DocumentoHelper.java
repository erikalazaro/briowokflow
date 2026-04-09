package com.briomax.briobpm.business.helpers;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.business.helpers.base.IDocumentoHelper;
import com.briomax.briobpm.business.helpers.base.INodoHelper;
import com.briomax.briobpm.common.core.Constants;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.ParametroGeneral;
import com.briomax.briobpm.persistence.repository.IInDocumentoProcesoRepository;
import com.briomax.briobpm.persistence.repository.IParametroGeneralRepository;
import com.briomax.briobpm.persistence.repository.IStDocumentoSeccionRepository;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.EstatusTO;
import com.briomax.briobpm.transferobjects.in.NodoTO;

import lombok.extern.slf4j.Slf4j;
/**
 * El objetivo de la Class FechaHelperTest.java es ...
 * 
 * @author Pamela Rodriguez 
 * @version 1.0 Fecha de creacion Agos 13, 2024 11:50:00 AM Modificaciones:
 * @since JDK 11
 */
@Service
@Transactional
@Slf4j
public class DocumentoHelper implements IDocumentoHelper {
	@Autowired
	private IParametroGeneralRepository iParametroGeneralRepository;

	@Autowired
	private IInDocumentoProcesoRepository inDocumentoProcesoRepository;

	@Autowired
	private IStDocumentoSeccionRepository stDocumentoSeccionRepository;

	@Autowired
	private INodoHelper nodoHelper;

	// SP_VENCIMIENTO_DOCUMENTOS
	@Override
	public RetMsg vencimientoDocumentos() throws BrioBPMException, ParseException {
		
		// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		RetMsg msg = RetMsg.builder()
				.status(Constants.OK)
				.message("")
				.build();
		// INICIALIZA VARIABLES
		String cveUsuarioBriowf = "Brio.Workflow";
		String cveUsuario = cveUsuarioBriowf;
		String idProceso = "Vencimiento_Documentos";
		String variableFechaVencimiento = "VPRO_01_FECHA_VIGENCIA_DOCUMENTO";
		String parDiasAvisoVencimiento = "DIAS_AVISO_VENCIMIENTO_DOCUMENTO";
		String situacionRegistrado = "REGISTRADO";
		int diasOmision = 30;
		String eventoVencimientoDocumento = "VENCIMIENTO_DOCUMENTO";
		
		// RECUPERA PARÁMETRO DE DÍAS PARA AVISO DE VENCIMIENTO
		Optional<ParametroGeneral> parametroGeneral = iParametroGeneralRepository.findById(parDiasAvisoVencimiento);
		Integer diasVencimiento = parametroGeneral.map(ParametroGeneral::getValorEntero) // Extrae el valor entero si
																							// está presente
				.orElse(null);

		// Verifica si el Optional está vacío o si el valor es nulo
		if (!parametroGeneral.isPresent() || diasVencimiento == null) {
			diasVencimiento = diasOmision;
		}
		//Obtén la fecha y hora actuales
		Date date = new Date();
		// Crear una instancia de Calendar
		Calendar calendar = Calendar.getInstance();

		// Establecer la fecha en el Calendar
		calendar.setTime(date);

		// Restar los días
		calendar.add(Calendar.DAY_OF_MONTH, -diasVencimiento);

		date = calendar.getTime();
		Timestamp fechaActual = new Timestamp(date.getTime());
		List<Object[]> crDocumentos = inDocumentoProcesoRepository.obtenerDocumentos(situacionRegistrado,
				variableFechaVencimiento, fechaActual);
		

		//Iterar sobre cada objeto en la lista
		for (Object[] objeto : crDocumentos) {
			// Extraer valores de acuerdo con la posición en el arreglo
			String cveEntidad = (String) objeto[0]; // INDP.CVE_ENTIDAD
			String cveProceso = (String) objeto[1]; // INDP.CVE_PROCESO
			BigDecimal version = (BigDecimal) objeto[2]; // INDP.VERSION
			String cveInstancia = (String) objeto[3]; // INDP.CVE_INSTANCIA
			Integer secuenciaDocumento = (Integer) objeto[4]; // INDP.SECUENCIA_DOCUMENTO
			String descripcion = (String) objeto[5]; // STDP.DESCRIPCION
			String nombreArchivo = (String) objeto[6]; // INDP.NOMBRE_ARCHIVO
			Date valorFecha = (Date) objeto[7]; // INVP.VALOR_FECHA
			
			//LISTA DE ROLES CON ACCESO A CAPTURA DEL DOCUMENTO POR VENCER
			List<String> crRoles = stDocumentoSeccionRepository.findRoles(cveEntidad, cveProceso, version,
					diasVencimiento, eventoVencimientoDocumento);
			for (String cveRol : crRoles) {
				// ENVÍA UN CORREO POR CADA ROL DEL DOCUMENTO POR VENCER
				DatosAutenticacionTO sesion = DatosAutenticacionTO.builder().cveUsuario(cveUsuario)
						.build(); /** REVISAR MAS VARIABLES */
				
				// CREA UN NODO PARA EL CORREO
				NodoTO nodo = NodoTO.builder()
						.cveProceso(cveProceso)
						.version(version)
						.cveEventoCorreo(eventoVencimientoDocumento)
						.rol(cveRol)
						.cveInstancia(cveInstancia)
						.ocurrencia(1)
						.cveUsuarioDestinatario(null)
			    		.cveRolDestinatario(cveRol)
						.build();
				
				// CREA EL CORREO DEL PROCESO
				EstatusTO creaCorreo = nodoHelper.creaCorreoProceso(
						sesion, nodo, secuenciaDocumento, null);
				
				// VERIFICA EL ESTADO DE LA OPERACIÓN
				msg.setMessage(creaCorreo.getMensaje());
				msg.setStatus(creaCorreo.getTipoExcepcion());
			}
		}
		return msg;

	}

}
