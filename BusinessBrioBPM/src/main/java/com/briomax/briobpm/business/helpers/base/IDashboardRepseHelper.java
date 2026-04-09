package com.briomax.briobpm.business.helpers.base;

import java.text.ParseException;
import java.util.List;

import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.transferobjects.app.GraficaTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.DatosContratista;
import com.briomax.briobpm.transferobjects.in.DatosContrato;

public interface IDashboardRepseHelper {

	List<DatosContratista> obtieneContratosPorRFC(DatosAutenticacionTO session, String destino, String cveDashboard,
			Boolean isSeccion, int cveSeccion) throws ParseException;

	List<DatosContrato> obtieneContratosPorRFCyNUM(DatosAutenticacionTO session, String destino, String cveDashboard,
			Boolean isSeccion, int cveSeccions) throws ParseException;

	GraficaTO obtieneGraficoPorRFCyNUM(DatosAutenticacionTO session, String destino, String cveDashboard, String rfc,
			String numContrato, Boolean isSeccion, int cveSeccion) throws ParseException;

	DAORet<GraficaTO, RetMsg> generaGrafico(DatosAutenticacionTO session, String destino, String cveDashboard,
			List<String> datos, Boolean isSeccion, int cveSeccion) throws ParseException;

}
