package com.briomax.briobpm.business.convertes;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import com.briomax.briobpm.persistence.entity.namedquery.LeeReglasActividad;
import com.briomax.briobpm.transferobjects.ReglasActividadTO;
import com.briomax.briobpm.transferobjects.emun.MagicNumber;
/**
 * El objetivo de la clase ReglaActividadConverter.java es obtener las reglas asociadas a una actividad.
 *
 * @author Erika Vazquez
 * @ver 1.0
 * @fecha Sep 10, 2024 4:12:01 PM
 * @since JDK 11
 */
public interface ReglaActividadConverter {
	
	Function<Object, ReglasActividadTO> converterReglas = (entity) -> {
		ReglasActividadTO to = null;
		Object[] row = (Object[]) entity;
		if (row != null) {
			to = ReglasActividadTO.builder()
				.cveRegla((String) Arrays.asList(row).get(MagicNumber.ZERO.getValue()))
				.tipoExpresion((String) Arrays.asList(row).get(MagicNumber.ONE.getValue()))
				.cveVariable((String) Arrays.asList(row).get(MagicNumber.TWO.getValue()))
				.aplicarCaptura((String) Arrays.asList(row).get(MagicNumber.THREE.getValue()))
				.aplicarGuardado((String) Arrays.asList(row).get(MagicNumber.FOUR.getValue()))
				.aplicarTerminacion((String) Arrays.asList(row).get(MagicNumber.FIVE.getValue()))
				.notacionPolaca((String) Arrays.asList(row).get(MagicNumber.SIX.getValue()))
					.build();
		}
		return to;
	};
	
	
	Function<String, LeeReglasActividad> convertirAListaLeeReglas = (entity) -> {
		LeeReglasActividad to = null;	
		
			to = LeeReglasActividad.builder()
					.notacionPolaca(entity)			
					.build();
		
		return to;
	};

}
