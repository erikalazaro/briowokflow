package com.briomax.briobpm.business.helpers.base;

import java.math.BigDecimal;
import java.util.List;

import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.namedquery.LeeValoresColumna;

public interface IValoresColumnaHelper {
	
	public DAORet<List<LeeValoresColumna>, RetMsg> getValoresColumna(String cveUsuario, String cveEntidad,
			String cveLocalidad, String cveIdioma, String cveProceso, BigDecimal version, String nombreTabla,
			String nombreColumna) throws BrioBPMException;

}
