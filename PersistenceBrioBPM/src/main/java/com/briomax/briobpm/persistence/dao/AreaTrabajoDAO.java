/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.persistence.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityTransaction;
import javax.persistence.ParameterMode;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.briomax.briobpm.common.core.Constants;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.dao.base.AbstractBaseDAO;
import com.briomax.briobpm.persistence.dao.base.IAreaTrabajoDAO;
import com.briomax.briobpm.persistence.entity.namedquery.ColumnasAreaTrabajo;
import com.briomax.briobpm.persistence.entity.namedquery.DatoActividad;
import com.briomax.briobpm.persistence.entity.namedquery.DatoActividadDate;
import com.briomax.briobpm.persistence.entity.namedquery.InformacionAreaTrabajo;
import com.briomax.briobpm.persistence.entity.namedquery.LeeBitacoraNodo;
import com.briomax.briobpm.persistence.entity.namedquery.LeeMenuEstatico;
import com.briomax.briobpm.persistence.entity.namedquery.MenuAreaTrabajo;
import com.briomax.briobpm.persistence.jdto.InicioProceso;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class AreaTrabajoDAO.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Mar 9, 2020 3:31:56 PM Modificaciones:
 * @since JDK 1.8
 */
@Repository("areaTrabajoDAO")
@Slf4j
public class AreaTrabajoDAO extends AbstractBaseDAO implements IAreaTrabajoDAO {

	/**
	 * Crear una nueva instancia del objeto area trabajo DAO.
	 */
	public AreaTrabajoDAO() {
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.persistence.dao.base.IAreaTrabajoDAO#leeMenuEstatico(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public DAORet<List<LeeMenuEstatico>, RetMsg> leeMenuEstatico(String cveUsuario, String cveEntidad,
		String cveLocalidad, String cveIdioma) throws BrioBPMException {
		log.debug("\t\t <DATABASE> EXEC SP_LEE_MENU_ESTATICO '{}', '{}', '{}', '{}', @CH_TIPO_EXCEPCION, @CH_MENSAJE",
			cveUsuario, cveEntidad, cveLocalidad, cveIdioma);
		return executeNamedStored("leeMenuEstatico",
			new String[] {"CH_CVE_USUARIO", "CH_CVE_ENTIDAD", "CH_CVE_LOCALIDAD", "CH_CVE_IDIOMA"},
			new String[] {cveUsuario, cveEntidad, cveLocalidad, cveIdioma}, RetMsg.class);
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.persistence.dao.base.IAreaTrabajoDAO#leeMenuAreaTrabajo(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public DAORet<List<MenuAreaTrabajo>, RetMsg> leeMenuAreaTrabajo(String cveUsuario, String cveEntidad,
		String cveLocalidad, String cveIdioma) throws BrioBPMException {
		log.debug("\t\t <DATABASE> EXEC SP_LEE_MENU_AREA_TRABAJO '{}', '{}', '{}', '{}', @CH_TIPO_EXCEPCION, @CH_MENSAJE",
			cveUsuario, cveEntidad, cveLocalidad, cveIdioma);
		return executeNamedStored("leeMenuAreaTrabajo",
			new String[] {"CH_CVE_USUARIO", "CH_CVE_ENTIDAD", "CH_CVE_LOCALIDAD", "CH_CVE_IDIOMA"},
			new String[] {cveUsuario, cveEntidad, cveLocalidad, cveIdioma}, RetMsg.class);
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.persistence.dao.base.IAreaTrabajoDAO#leeColumnasAreaTrabajo(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.math.BigDecimal, java.lang.String, java.lang.Integer, java.lang.String)
	 */
	/*@Override
	public DAORet<List<ColumnasAreaTrabajo>, RetMsg> leeColumnasAreaTrabajo(String cveUsuario, String cveEntidad,
		String cveLocalidad, String cveIdioma, String rol, String cveProceso, BigDecimal version, String cveNodo,
		Integer idNodo, String cveAreaTrabajo) throws BrioBPMException {
		log.debug("\t\t <DATABASE> EXEC SP_LEE_COLUMNAS_AREA_TRABAJO '{}', '{}', '{}', '{}', '{}', '{}', {}, '{}'," +
			" {}, '{}', @CH_TIPO_EXCEPCION, @CH_MENSAJE  ", cveUsuario, cveEntidad, cveLocalidad, cveIdioma, rol,
			cveProceso, version, cveNodo, idNodo, cveAreaTrabajo);
		return executeNamedStored("leeColumnasAreaTrabajo",
			new String[] {"CH_CVE_USUARIO", "CH_CVE_ENTIDAD", "CH_CVE_LOCALIDAD", "CH_CVE_IDIOMA", "CH_ROL",
				"CH_CVE_PROCESO", "C_VERSION", "CH_CVE_NODO", "I_ID_NODO", "CH_CVE_AREA_TRABAJO" },
			new Object[] {cveUsuario, cveEntidad, cveLocalidad, cveIdioma, rol,
				cveProceso, version, cveNodo, idNodo, cveAreaTrabajo}, RetMsg.class);
	}*/

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.persistence.dao.base.IAreaTrabajoDAO#leeInformacionAreaTrabajo(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.math.BigDecimal, java.lang.String, java.lang.Integer, java.lang.String)
	 */
	/*@Override
	public DAORet<List<InformacionAreaTrabajo>, RetMsg> leeInformacionAreaTrabajo(String cveUsuario, String cveEntidad,
		String cveLocalidad, String cveIdioma, String rol, String cveProceso, BigDecimal version, String cveNodo,
		Integer idNodo, String cveAreaTrabajo) throws BrioBPMException {
		log.debug("\t\t <DATABASE> EXEC SP_LEE_INF_AREA_TRABAJO '{}', '{}', '{}', '{}', '{}', '{}', {}, '{}', {}, '{}'," +
			" @CH_TIPO_EXCEPCION, @CH_MENSAJE", cveUsuario, cveEntidad, cveLocalidad, cveIdioma, rol, cveProceso,
			version, cveNodo, idNodo, cveAreaTrabajo);
		return executeNamedStored("leeInformacionAreaTrabajo",
			new String[] {"CH_CVE_USUARIO", "CH_CVE_ENTIDAD", "CH_CVE_LOCALIDAD", "CH_CVE_IDIOMA", "CH_ROL",
				"CH_CVE_PROCESO", "C_VERSION", "CH_CVE_NODO", "I_ID_NODO", "CH_CVE_AREA_TRABAJO" },
			new Object[] {cveUsuario, cveEntidad, cveLocalidad, cveIdioma, rol,
				cveProceso, version, cveNodo, idNodo, cveAreaTrabajo}, RetMsg.class);
	}*/

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.persistence.dao.base.IAreaTrabajoDAO#creaInstanciaProceso(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.math.BigDecimal, java.lang.String, java.lang.String)
	 */
	/*@Override
	public InicioProceso creaInstanciaProceso(String cveUsuario, String cveEntidad, String cveLocalidad,
		String cveIdioma, String rol, String cveProceso, BigDecimal version, String concepto, String origen)
		throws BrioBPMException {
		log.debug("\t\t <DATABASE> EXEC SP_CREA_INSTANCIA_PROCESO '{}', '{}', '{}', '{}', '{}', {}, '{}', '{}', {}, " +
			" null, @CH_CVE_INSTANCIA, @CH_CVE_NODO_ACTIVIDAD, @I_ID_NODO_ACTIVIDAD, @I_SECUENCIA_NODO_ACTIVIDAD," +
			" @CH_TIPO_EXCEPCION, @CH_MENSAJE", cveUsuario, cveEntidad, cveLocalidad, cveIdioma, cveProceso, version,
			concepto, origen, rol);
		return executeNoResulsetStored("SP_CREA_INSTANCIA_PROCESO",
			new String[] {"CH_USUARIO", "CH_CVE_ENTIDAD", "CH_CVE_LOCALIDAD", "CH_CVE_IDIOMA", "CH_CVE_PROCESO",
				"C_VERSION", "CH_CONCEPTO", "CH_ORIGEN", "CH_ROL_CREADOR", "CH_VALORES_REFERENCIA_ENVIO",
				"I_FOLIO_MENSAJE_ENVIO", "CH_CVE_INSTANCIA", "CH_CVE_NODO_ACTIVIDAD", "I_ID_NODO_ACTIVIDAD",
				"I_SECUENCIA_NODO_ACTIVIDAD", "CH_TIPO_EXCEPCION", "CH_MENSAJE"},
			new Class[] {String.class, String.class, String.class, String.class, String.class, BigDecimal.class,
				String.class, String.class, String.class, Integer.class, String.class, String.class,
				String.class, Integer.class, Integer.class, String.class, String.class},
			new ParameterMode[] {ParameterMode.IN, ParameterMode.IN, ParameterMode.IN, ParameterMode.IN, ParameterMode.IN,
				ParameterMode.IN, ParameterMode.IN, ParameterMode.IN, ParameterMode.IN, ParameterMode.IN, ParameterMode.IN,
				ParameterMode.OUT, ParameterMode.OUT, ParameterMode.OUT, ParameterMode.OUT, ParameterMode.OUT,
				ParameterMode.OUT},
			new Object[] {cveUsuario, cveEntidad, cveLocalidad, cveIdioma, cveProceso, version, concepto, origen, rol,
				null, null},
			InicioProceso.class);
	}*/

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.persistence.dao.base.IAreaTrabajoDAO#leeBitacoraNodo(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.math.BigDecimal, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	/*@Override
	public DAORet<List<LeeBitacoraNodo>, RetMsg> leeBitacoraNodo(String cveUsuario, String cveEntidad,
		String cveLocalidad, String cveIdioma, String cveProceso, BigDecimal version, String cveInstancia,
		String cveNodo, Integer idNodo, Integer secNodo) throws BrioBPMException {
		log.debug("\t\t <DATABASE> EXEC SP_LEE_BITACORA_NODO '{}', '{}', '{}', '{}', '{}', {}, '{}', '{}', {}, {}," +
			" @CH_TIPO_EXCEPCION, @CH_MENSAJE", cveUsuario, cveEntidad, cveLocalidad, cveIdioma, cveProceso, version,
			cveInstancia, cveNodo, idNodo, secNodo);
		return executeNamedStored("leeBitacoraNodo",
			new String[] {"CH_CVE_USUARIO", "CH_CVE_ENTIDAD", "CH_CVE_LOCALIDAD", "CH_CVE_IDIOMA",
				"CH_CVE_PROCESO", "C_VERSION", "CH_CVE_INSTANCIA", "CH_CVE_NODO", "I_ID_NODO", "I_SECUENCIA_NODO" },
			new Object[] {cveUsuario, cveEntidad, cveLocalidad, cveIdioma, cveProceso, version, cveInstancia, cveNodo,
				idNodo, secNodo},
			RetMsg.class);
	}*/

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.persistence.dao.base.IAreaTrabajoDAO#leeMenuDashboard(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public DAORet<List<LeeMenuEstatico>, RetMsg> leeMenuDashboard(String cveUsuario, String cveEntidad,
		String cveLocalidad, String cveIdioma) throws BrioBPMException {
		entityManager.flush();
		log.debug("\t\t <DATABASE> EXEC SP_LEE_MENU_DASHBOARD '{}', '{}', '{}', '{}', @CH_TIPO_EXCEPCION, @CH_MENSAJE",
			cveUsuario, cveEntidad, cveLocalidad, cveIdioma);
		return executeNamedStored("leeMenuDashboard",
			new String[] {"CH_CVE_USUARIO", "CH_CVE_ENTIDAD", "CH_CVE_LOCALIDAD", "CH_CVE_IDIOMA"},
			new String[] {cveUsuario, cveEntidad, cveLocalidad, cveIdioma}, RetMsg.class);
	}
	
	
	@Override
    public List<String> obtenerUsuarioAsignado(String query) throws BrioBPMException {
             
	    Object result = entityManager.createNativeQuery(query);	    		
		List<String> datos = entityManager.createNativeQuery(query).getResultList();//.getSingleResult();
	    
	    if (datos != null) {
	        return datos;
	    } else {
	        return null;
	    }
    }
	
	@Override
    public String ejecutaQuerySimple(String query) throws BrioBPMException {
             
	    Object result = entityManager.createNativeQuery(query);	    		
		result = entityManager.createNativeQuery(query).getSingleResult();
	    
	    if (result != null) {
	        return result.toString();
	    } else {
	        return null;
	    }
    }
	
	@Override
    public String ejecutaQuery(String query) throws BrioBPMException {
             
		Query queryResul = entityManager.createNativeQuery(query);
		List<Object> result = queryResul.getResultList();
	    
		if (!result.isEmpty()) {
		    Object queryResult = result.get(0);
		    if (queryResult != null) {
		    	return queryResult.toString();
		    } else {
		    	return null;
		    }
		} else {
			return null;
		}
		
    }

	
	
}
