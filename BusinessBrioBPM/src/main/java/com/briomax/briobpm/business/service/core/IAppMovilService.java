package com.briomax.briobpm.business.service.core;

import java.text.ParseException;
import java.util.List;

import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.app.ActividadGuardar;
import com.briomax.briobpm.transferobjects.app.ActividadTO;
import com.briomax.briobpm.transferobjects.app.DatoGuardar;
import com.briomax.briobpm.transferobjects.app.RespuestaFormularioTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.DocumentoAppTO;
import com.briomax.briobpm.transferobjects.in.EstatusTO;
import com.briomax.briobpm.transferobjects.in.NodoTO;

public interface IAppMovilService {

	/**
	 * Obtiene el formulario dinámico móvil para un nodo específico.
	 * 
	 * @param session Datos de autenticación del usuario.
	 * @param nodo Nodo sobre el cual se desea obtener el formulario dinámico.
	 * @return ActividadTO que contiene el formulario dinámico para el nodo.
	 */
	ActividadTO obtenerFormularioDinamicoMovil(DatosAutenticacionTO session, NodoTO nodo)throws BrioBPMException, ParseException;

	/**
	 * Obtiene una versión específica (01) del formulario dinámico móvil para un nodo.
	 * 
	 * @param session Datos de autenticación del usuario.
	 * @param nodo Nodo sobre el cual se desea obtener el formulario dinámico.
	 * @return ActividadTO que contiene el formulario dinámico para el nodo.
	 */
	ActividadTO obtenerFormularioDinamicoMovil01(DatosAutenticacionTO session, NodoTO nodo)  throws BrioBPMException, ParseException;


	/**
	 * Selecciona y ejecuta una acción específica en función de la clave del botón proporcionada (cveBoton).
	 * 
	 * @param userSession Datos de autenticación del usuario.
	 * @param nodo Nodo sobre el cual se realizará la acción.
	 * @param listaRespuestas Lista de objetos DatoGuardar que contienen las respuestas del usuario.
	 * @param cveBoton Clave del botón que indica la acción a ejecutar ("GUARDAR", "TERMINAR", etc.).
	 * @return EstatusTO que indica el estado y resultado de la acción ejecutada.
	 * @throws BrioBPMException En caso de error durante la ejecución de la acción.
	 * @throws ParseException En caso de error de formato de datos.
	 */
	EstatusTO seleccionaAccion(DatosAutenticacionTO userSession, NodoTO nodo, String cveBoton,
			List<RespuestaFormularioTO> respuestaFormulario) throws BrioBPMException, ParseException;

	/**
	 * Registra la respuesta de un usuario en una actividad dada.
	 * @param userSession
	 * @param nodo
	 * @param respuestaFormulario
	 * @return
	 * @throws ParseException 
	 */
	EstatusTO registraRespuestaUsuarioN(DatosAutenticacionTO userSession, NodoTO nodo,
			List<RespuestaFormularioTO> respuestasFormulario) throws ParseException;

	/**
	 * Obtiene la lista de nodos de un proceso.
	 * @param session
	 * @param documento
	 * @return
	 */
	EstatusTO uploadImageService(DatosAutenticacionTO session, DocumentoAppTO documento);

}
