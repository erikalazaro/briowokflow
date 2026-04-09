package com.briomax.briobpm.business.helpers.base;

import java.util.Date;
import java.util.List;

import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.in.ColumnasAreaTrabajoTO;
import com.briomax.briobpm.transferobjects.in.DatosAreaTrabajoTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.EstatusAccionesTO;
import com.briomax.briobpm.transferobjects.in.EstatusAreaTrabajoTO;
import com.briomax.briobpm.transferobjects.in.EstatusAtributosDatoTO;
import com.briomax.briobpm.transferobjects.in.EstatusAtributosMonedaTO;
import com.briomax.briobpm.transferobjects.in.NodoTO;

public interface IAreaTrabajoHelper {

	// SP_LEE_ACCIONES_ACTIVIDAD
	/**
	 * Lee las acciones de la Actividad. O
	 * 
	 * @param session      Datos de autenticación del usuario.
	 * @param claveProceso Clave del proceso.
	 * @param version      Versión del proceso.
	 * @return EstatusTO con el resultado de la validación de datos y estado del
	 *         proceso.
	 * @throws BrioBPMException En caso de error durante la validación de datos.
	 */
	EstatusAccionesTO leeAccionesActividad(DatosAutenticacionTO session, NodoTO nodo) throws BrioBPMException;

	// SP_LEE_INF_AREA_TRABAJO_VS
	EstatusAreaTrabajoTO leeInfAreaTrabajoVS(DatosAutenticacionTO session, NodoTO nodo,
			List<DatosAreaTrabajoTO> datosAreaTrabajo, String cveAreaTrabajo, Integer idNodo) throws BrioBPMException;

	// SP_LEE_INF_AREA_TRABAJO
	DAORet<EstatusAreaTrabajoTO, RetMsg> leeInfAreaTrabajo(DatosAutenticacionTO session, NodoTO nodo,
			String cveAreaTrabajo, String cveAreaTrabajoTarjeta) throws BrioBPMException;

	// SP_LEE_INF_AREA_TRABAJO_DPN
	EstatusAreaTrabajoTO leeInfAreaTrabajoDPN(DatosAutenticacionTO session, NodoTO nodo,
			List<DatosAreaTrabajoTO> datosAreaTrabajo, String cveAreaTrabajo, Integer idNodo) throws BrioBPMException;

	// SP_LEE_INF_AREA_TRABAJO_VP
	EstatusAreaTrabajoTO leeInfAreaTrabajoVP(DatosAutenticacionTO session, NodoTO nodo,
			List<DatosAreaTrabajoTO> datosAreaTrabajo, String cveAreaTrabajo, Integer idNodo,
			String cveAreaTrabajoTarjeta) throws BrioBPMException;

	// SP_LEE_INF_AREA_TRABAJO_VL
	EstatusAreaTrabajoTO leeInfAreaTrabajoVL(DatosAutenticacionTO session, NodoTO nodo,
			List<DatosAreaTrabajoTO> datosAreaTrabajo, String cveAreaTrabajo, Integer idNodo) throws BrioBPMException;

	// SP_LEE_INF_AREA_TRABAJO_VE
	EstatusAreaTrabajoTO leeInfAreaTrabajoVE(DatosAutenticacionTO session, NodoTO nodo,
			List<DatosAreaTrabajoTO> datosAreaTrabajo, String cveAreaTrabajo, Integer idNodo) throws BrioBPMException;

	// FN_LEE_ESTILO_NIVEL_SERVICIO
	String leeEstiloNivelServicio(Date fechaCreacionActividad, Date fechaLimite, String estadoActividad,
			Date fechaEstadoActual) throws BrioBPMException;

	// SP_LEE_ATRIBUTOS_DATO
	EstatusAtributosDatoTO leeAtributosDato(DatosAutenticacionTO session, NodoTO nodo, String cveDato,
			String origenDato) throws BrioBPMException;

	// SP_LEE_ATRIBUTOS_MONEDA
	EstatusAtributosMonedaTO leeAtributosMoneda(DatosAutenticacionTO session, NodoTO nodo, String cveDato,
			String origenDato) throws BrioBPMException;

	// SP_LEE_COLUMNAS_AREA_TRABAJO
	DAORet<List<ColumnasAreaTrabajoTO>, RetMsg> leeColumnaAreaTrabajo(DatosAutenticacionTO session, NodoTO nodo,
			String cveAreaTrabajo) throws BrioBPMException;

}
