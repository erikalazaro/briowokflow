package com.briomax.briobpm.business.helpers.base;

import java.text.ParseException;
import java.util.List;

import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.namedquery.LeeEtiquetasTabla;
import com.briomax.briobpm.transferobjects.in.CatalogoEtiquetaTO;
import com.briomax.briobpm.transferobjects.in.ColumasGridEjecucionTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.DatosSeccionOCUTO;
import com.briomax.briobpm.transferobjects.in.EstatusEvaluaFormulaTO;
import com.briomax.briobpm.transferobjects.in.EstatusInfSeccionVPTO;
import com.briomax.briobpm.transferobjects.in.ListaTO;
import com.briomax.briobpm.transferobjects.in.NodoTO;
import com.briomax.briobpm.transferobjects.in.RsOcurrenciaTO;

/**
 * El objetivo de la Interface IFormularioPantalla.java es ...
 * 
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Abr 09, 2024 6:05:58 PM Modificaciones:
 * @since JDK 1.8
 */
public interface IFormularioPantalla {

	// SP_LEE_ETIQUETAS_TABLA
	public DAORet<List<LeeEtiquetasTabla>, RetMsg> leeEtiquetasTabla(DatosAutenticacionTO session, NodoTO nodo,
			String cveDato, ListaTO listaTO) throws BrioBPMException, ParseException ;

	// SP_LEE_ETIQUETAS_CATALOGO
	public DAORet<List<CatalogoEtiquetaTO>, RetMsg> leeEtiquetasCatalogo(DatosAutenticacionTO session, NodoTO nodo,
			String cveDato) throws BrioBPMException;

	// SP_LEE_COLUMNAS_SECCION_OCU
	public DAORet<List<ColumasGridEjecucionTO>, RetMsg> leeColumnasSeccionOCU(DatosAutenticacionTO session, NodoTO nodo,
			String cveSeccion) throws BrioBPMException;

	// SP_EVALUA_FORMULA
	public EstatusEvaluaFormulaTO evaluaFormula(DatosAutenticacionTO session, NodoTO nodo, String tipo, String formula)
			throws BrioBPMException;

	// SP_LEE_INF_SECCION_OCU
	public DAORet<List<RsOcurrenciaTO>, RetMsg> leeInfSeccionOcu(DatosAutenticacionTO session, NodoTO nodo,
			String cveSeccion) throws BrioBPMException;

	// SP_LEE_INF_SECCION_VP
	public EstatusInfSeccionVPTO leeInfSeccionVP(DatosAutenticacionTO session, NodoTO nodo, String cveSeccion,
			List<DatosSeccionOCUTO> datosSeccionOCU) throws BrioBPMException;
}
