/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */


package com.briomax.briobpm.business.service.catalogs.core;

import java.util.List;

import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.catalogs.UsuariosRol;
import com.briomax.briobpm.transferobjects.catalogs.RolProcesoTO;
import com.briomax.briobpm.transferobjects.catalogs.UsuarioRol;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;


/**
 * El objetivo de la Interface IRolProcesoService.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jun 26, 2020 12:26:05 PM Modificaciones:
 * @since JDK 1.8
 */
public interface IRolProcesoService {

	/**
	 * Obtener el valor de all.
	 * @param session el session.
	 * @param cveProceso el cve proceso.
	 * @param version el version.
	 * @return el all.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	List<RolProcesoTO> getAll(DatosAutenticacionTO session, String cveProceso, String version)
		throws BrioBPMException;

	/**
	 * Insert.
	 * @param session el session.
	 * @param rolProcesoTO el rol proceso TO.
	 * @return el ret msg.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	RetMsg insert(DatosAutenticacionTO session, RolProcesoTO rolProcesoTO) throws BrioBPMException;

	/**
	 * Update.
	 * @param session el session.
	 * @param rolProcesoTO el rol proceso TO.
	 * @return el ret msg.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	RetMsg update(DatosAutenticacionTO session, RolProcesoTO rolProcesoTO) throws BrioBPMException;

	/**
	 * Obtener el valor de usuarios rol procesos.
	 * @param session el session.
	 * @param cveProceso el cve proceso.
	 * @param version el version.
	 * @param cveRol el cve rol.
	 * @return el usuarios rol procesos.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	List<UsuarioRol> getUsuariosRolProcesos(DatosAutenticacionTO session, String cveProceso, String version,
		String cveRol) throws BrioBPMException;

	/**
	 * Asignar usarios rol.
	 * @param session el session.
	 * @param rolUsuarios el rol usuarios.
	 * @return el ret msg.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	RetMsg asignarUsariosRol(DatosAutenticacionTO session, UsuariosRol rolUsuarios) throws BrioBPMException;

}
