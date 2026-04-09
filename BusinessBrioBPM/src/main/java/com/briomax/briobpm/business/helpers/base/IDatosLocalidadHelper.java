package com.briomax.briobpm.business.helpers.base;

import java.sql.Struct;
import java.util.List;

import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.Entidad;
import com.briomax.briobpm.persistence.entity.namedquery.DatosOmisionEntidad;
import com.briomax.briobpm.persistence.entity.namedquery.Localidad;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

/**
 * El objetivo de la Interface INodoHelper.java es ...
 * 
 * @author Alfredo Alejandro
 * @version 1.0 Fecha de creacion Jul 12, 2024 15:00 PM Modificaciones:
 * @since JDK 11
 */

public interface IDatosLocalidadHelper {

	// SP_LEE_DATOS_LOCALIDAD
		/**
		 * RECUPERAR LOS DATOS DE LA LOCALIDAD PARA LA CLAVE PROPORCIONADA:
		 *	LA CLAVE,
		 *	EL NOMBRE,
		 *	LA DIRECCIÓN,
		 *	EL TELÉFONO 1,
		 *	EL TELÉFONO 2,
		 *	EL CORREO ELECTRÓNICO,
		 *	LA DESCRIPCIÓN DE LA MONEDA,
		 *	EL HUSO HORARIO,
		 *	LA FECHA ACTUAL,
		 *	LA HORA ACTUAL.
		 * 
		 * @param session Datos de autenticacion del usuario.
		 * @param 
		 * @return 
		 * @throws BrioBPMException En caso de error durante la lectura de las
		 *                          variables.
		 */
		public DAORet<List<Localidad>, RetMsg> leeDatosLocalidad(DatosAutenticacionTO session)throws BrioBPMException;
		
		public DAORet<Localidad, RetMsg> leeDatoLocalidad(DatosAutenticacionTO session) throws BrioBPMException;
		
		
		// SP_LEE_DATOS_OMISION_ENTIDAD
				/**
				  * OBTIENE LOS DATOS POR OMISIÓN DE LA ENTIDAD PROPORCIONADA:
				  *	DESCRIPCIÓN DE LA ENTIDAD,
				  *	IDIOMA POR OMISIÓN,
				  *	LOGOTIPO.
				  * 
				  * @param session Datos de autenticacion del usuario.
				  * @param 
				  * @return 
				  * @throws BrioBPMException En caso de error durante la lectura de las
				  *                          variables.
				  */
		public DAORet<DatosOmisionEntidad, RetMsg> leeDatosOmisionEntidad(DatosAutenticacionTO session)throws BrioBPMException;
}
