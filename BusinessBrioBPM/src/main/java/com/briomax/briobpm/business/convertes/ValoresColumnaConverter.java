package com.briomax.briobpm.business.convertes;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.function.Function;

import com.briomax.briobpm.persistence.entity.namedquery.LeeValoresColumna;
import com.briomax.briobpm.transferobjects.emun.MagicNumber;

public interface ValoresColumnaConverter {
	
	Function<Object, LeeValoresColumna> converterValoresColumna = (entity) -> {
		LeeValoresColumna to = null;
		Object[] row = (Object[]) entity;
		if (row != null) {
			to = LeeValoresColumna.builder() 
				.secuenciaValor((BigDecimal) Arrays.asList(row).get(MagicNumber.ZERO.getValue()))
				.ordenamientoSecuencia( ((BigDecimal) Arrays.asList(row).get(MagicNumber.ONE.getValue())).toString())
				.valorBaseDatos((String) Arrays.asList(row).get(MagicNumber.TWO.getValue()))
				.ValorPantalla((String) Arrays.asList(row).get(MagicNumber.THREE.getValue()))				
				.build();
		}
		return to;
	};

}
