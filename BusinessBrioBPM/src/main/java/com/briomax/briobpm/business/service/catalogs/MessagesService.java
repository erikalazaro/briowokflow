/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.service.catalogs;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.business.service.catalogs.core.IMessagesService;
import com.briomax.briobpm.common.core.Constants;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.dao.base.IMensajesDAO;
import com.briomax.briobpm.persistence.entity.ComposicionCorreo;
import com.briomax.briobpm.persistence.entity.MensajeIdioma;
import com.briomax.briobpm.persistence.entity.MensajeIdiomaPK;
import com.briomax.briobpm.persistence.entity.Traduccion;
import com.briomax.briobpm.persistence.entity.TraduccionPK;
import com.briomax.briobpm.persistence.entity.namedquery.LeeMensajesReglas;
import com.briomax.briobpm.persistence.repository.IComposicionCorreoRepository;
import com.briomax.briobpm.persistence.repository.IMensajeIdiomaRepository;
import com.briomax.briobpm.persistence.repository.IMensajeReglaRepository;
import com.briomax.briobpm.persistence.repository.ITraduccionRepository;
import com.briomax.briobpm.transferobjects.emun.MagicNumber;
import com.briomax.briobpm.transferobjects.in.ActividadTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

import lombok.var;
import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Clase/Iterfarce/Enumeracion MessagesService.java es ...
 * 
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jun 26, 2020 11:15:42 AM Modificaciones:
 * @since JDK 1.8
 */
@Service
@Transactional
@Slf4j
@Repository
public class MessagesService implements IMessagesService {

	/** El atributo o variable mensaje idioma repository. */
	@Autowired
	private IMensajeIdiomaRepository mensajeIdiomaRepository;

	/** El atributo o variable traduccion repository. */
	@Autowired
	private ITraduccionRepository traduccionRepository;

	/** El atributo o variable mensajes dao. */
	@Autowired
	private IMensajesDAO mensajesDAO;
	
	@Autowired
	private IComposicionCorreoRepository composicionCorreoRepository;
	
	@Autowired
	private IMensajeReglaRepository mensajeReglaRepository;

	/**
	 * Crear una nueva instancia del objeto messages service.
	 */
	public MessagesService() {
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.briomax.briobpm.business.service.catalogs.core.IMessagesService#getMessage(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public String getMessage(String codigoMensaje, String cveIdioma) {
		log.debug("Find Message by Id {}-{}", codigoMensaje, cveIdioma);
		MensajeIdiomaPK id = MensajeIdiomaPK.builder().codigoMensaje(codigoMensaje).cveIdioma(cveIdioma).build();
		String message = "";
		Optional<MensajeIdioma> mensajeIdioma = mensajeIdiomaRepository.findById(id);
		if (mensajeIdioma.isPresent()) {
			message = mensajeIdioma.get().getMensaje();
		}
		return message;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.briomax.briobpm.business.service.catalogs.core.IMessagesService#getMessage(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String getMessage(DatosAutenticacionTO session, String idProceso, String codigoMensaje,
			String variablesValores) {

    // Construye la clave primaria del mensaje con el código del mensaje y el idioma del usuario
	MensajeIdiomaPK id = MensajeIdiomaPK.builder()
			.codigoMensaje(codigoMensaje) // Código del mensaje que se busca
			.cveIdioma(session.getCveIdioma().toUpperCase()) // Idioma del usuario autenticado
			.build();

	String message = ""; // Inicializa la variable que contendrá el mensaje resultante

    // Busca el mensaje en la base de datos usando el repositorio de mensajeIdioma
	Optional<MensajeIdioma> mensajeIdioma = mensajeIdiomaRepository.findById(id);
	
	// Verifica si el mensaje se encontró
	if (mensajeIdioma.isPresent()) {
		// Si el mensaje existe, lo asigna a la variable message
		message = mensajeIdioma.get().getMensaje();
		
		// Verifica si hay valores de variables para reemplazar en el mensaje
		if (variablesValores.trim().length() > 0) {
			// Separa los valores en pares (clave, valor) usando el delimitador '|'
			String[] valores = variablesValores.split("\\|");
			
			// Itera sobre los pares de claves y valores
			for (int i = 0; i < valores.length; i += 2) {
				String clave = valores[i];    // La clave que se debe reemplazar en el mensaje
				String valor = valores[i + 1]; // El valor que reemplazará a la clave
				// Reemplaza la clave en el mensaje con su valor correspondiente
				message = message.replace(clave, valor);
			}
		}
	} else {
		// Si el mensaje no fue encontrado, se maneja el error
		// Se busca una traducción para el mensaje "CODIGO DE MENSAJE NO ENCONTRADO"
		TraduccionPK tid = TraduccionPK.builder()
				.cveIdioma(session.getCveIdioma()) // Idioma del usuario
				.palabraOriginal("CODIGO DE MENSAJE NO ENCONTRADO") // Texto a traducir
				.build();
		
		// Intenta recuperar la traducción correspondiente
		Optional<Traduccion> traduccion = traduccionRepository.findById(tid);
		
		// Si la traducción está presente, usa la palabra traducida como mensaje de error
		if (traduccion.isPresent()) {
			message = traduccion.get().getPalabraTraducida();
		} else {
			// Si no se encuentra una traducción, genera un mensaje de error manualmente
			message = idProceso.trim() + "- OBTEN_MENSAJE_CODIGO -"
					+ " Error al recuperar el mensaje del siguiente código (" + codigoMensaje.trim() + ")"
					+ " Entidad (" + session.getCveEntidad() + ") Localidad (" + session.getCveLocalidad() + ")"
					+ " Idioma (" + session.getCveIdioma() + ")";
		}
	}

	// Retorna el mensaje final, ya sea el encontrado o el mensaje de error
	return message;
}

	// SP_ACTUALIZA_SITUACION_CORREO
	@Override
	public RetMsg actualizaSItuacionCorreo(DatosAutenticacionTO session, String cveProceso, BigDecimal version,
			Integer numeroCorreo, String situacion) throws BrioBPMException {
		  String idProceso;
		  String mensaje; 
		  String variableValor;  
		  // INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		  RetMsg msg = RetMsg.builder().status(Constants.OK).message("").build();
		  idProceso = "ACTUALIZA_SITUACION_CORREO";  
		  // CONCATENA LA LISTA DE LOS PARÁMETROS PARA DESPLEGARLOS EN CASO DE ERROR
		  variableValor = Constants.CVE_PROCESO + cveProceso + "|" +
		   Constants.VERSION + version + "|" +   "NUMERO_CORREO@|" + numeroCorreo;
		  //VALIDA LA EXISTENCIA DEL CORREO RECIBIDO
		  List<ComposicionCorreo> correosRecibidos = composicionCorreoRepository.existeCorreoRecibido(cveProceso,version,numeroCorreo);
		  if (correosRecibidos == null || correosRecibidos.isEmpty()) {
			  mensaje = getMessage(session,		
					  idProceso,
					  "ERR_CORREO_NO_EXISTE",
					  variableValor);
					  msg.setMessage(mensaje);
					  msg.setStatus(Constants.ERROR);
					  return msg;
		  }
		  //ACTUALIZA LA SITUACIÓN DEL CORREO 
		  for (ComposicionCorreo correo:correosRecibidos) {
			  correo.setSituacionCorreo(situacion);
			  composicionCorreoRepository.saveAndFlush(correo);
			  if (!situacion.equals(correo.getSituacionCorreo())) {
				  mensaje = getMessage(session,		
						  idProceso,
						  "ERR_ACTUALIZACION_CORREO",
						  variableValor);
						  msg.setMessage(mensaje);
						  msg.setStatus(Constants.ERROR);
						  return msg;
			  }  
		  }
		return msg;
	}

	/**
	 * Método que permite obtener los mensajes de reglas asociados a una actividad específica en base a los
	 * datos de autenticación de la sesión y los detalles de la actividad proporcionada. Los mensajes se obtienen 
	 * de la base de datos a través del repositorio y se almacenan en una lista de objetos LeeMensajesReglas.
	 * 
	 * @param session   Datos de autenticación del usuario, encapsulados en un objeto DatosAutenticacionTO.
	 * @param actividad Actividad específica para la cual se buscan los mensajes de reglas, representada por un objeto ActividadTO.
	 * @return          Un objeto DAORet que contiene una lista de objetos LeeMensajesReglas y un mensaje de retorno RetMsg.
	 */
	@Override
	public DAORet<List<LeeMensajesReglas>, RetMsg> leeMensajesReglas(DatosAutenticacionTO session, ActividadTO actividad) {
	    
	    // Inicializa el mensaje de retorno con un estado "OK" por defecto.
	    RetMsg msg = RetMsg.builder().status("OK").message("").build();
	    
	    // Crea una lista para almacenar los mensajes de reglas obtenidos.
	    List<LeeMensajesReglas> mensajesReglas = new ArrayList<>();
	    
	    // Realiza una consulta a la base de datos para obtener los mensajes de reglas de acuerdo con la entidad y los detalles de la actividad.
	    List<Object[]> datos = mensajeReglaRepository.obtenerMensajesRegla(session.getCveEntidad(), 
	            actividad.getCveProceso(), new BigDecimal(actividad.getVersion()), 
	            actividad.getCveNodo(), actividad.getIdNodo(), session.getCveIdioma().toUpperCase());

	    // Si hay resultados, se procesan y se agregan a la lista 'mensajesReglas'.
	    if (!datos.isEmpty()) {
	        datos.forEach(item -> {
	            Object[] row = (Object[]) item;
	            BigDecimal numero = (BigDecimal) Arrays.asList(row).get(MagicNumber.ZERO.getValue());
	            
	            // Se construye un objeto LeeMensajesReglas con los valores obtenidos de cada fila de la consulta.
	            var itemSelected = LeeMensajesReglas.builder()
	                    .numeroMensaje(numero.intValue())
	                    .cveIdioma((String) Arrays.asList(row).get(MagicNumber.ONE.getValue()))
	                    .mensaje((String) Arrays.asList(row).get(MagicNumber.TWO.getValue()))
	                    .build();
	            
	            // Se agrega el objeto LeeMensajesReglas a la lista 'mensajesReglas'.
	            mensajesReglas.add(itemSelected);
	        });
	    }
	    
	    // Retorna un objeto DAORet que contiene la lista de mensajes y el mensaje de retorno.
	    return new DAORet<List<LeeMensajesReglas>, RetMsg>(mensajesReglas, msg);
	}
	

}
