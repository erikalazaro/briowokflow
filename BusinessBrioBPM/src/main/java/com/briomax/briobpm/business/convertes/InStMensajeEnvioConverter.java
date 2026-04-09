package com.briomax.briobpm.business.convertes;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.function.Function;


import com.briomax.briobpm.transferobjects.emun.MagicNumber;
import com.briomax.briobpm.transferobjects.in.StInMensajeTO;

public interface InStMensajeEnvioConverter {

	/** Converter Entity to DTO. */
	Function<Object, StInMensajeTO> converterEntityToDTO = (entity) -> {
		//List<StInMensajeTO> stInMensajeTO = new ArrayList<StInMensajeTO>();		
		Object[] row = (Object[]) entity;
		StInMensajeTO itemSelected = StInMensajeTO.builder()
				.cveEntidad((String) Arrays.asList(row).get(MagicNumber.ZERO.getValue()))
				.cveProceso((String) Arrays.asList(row).get(MagicNumber.ONE.getValue()))
				.version(((BigDecimal) Arrays.asList(row).get(MagicNumber.TWO.getValue())).toString())
				.cveInstancia((String) Arrays.asList(row).get(MagicNumber.THREE.getValue()))
				.cveNodo((String) Arrays.asList(row).get(MagicNumber.FOUR.getValue()))				
				.idNodo((Integer) Arrays.asList(row).get(MagicNumber.FIVE.getValue()))
				.secNodo((Integer) Arrays.asList(row).get(MagicNumber.SIX.getValue()))
				.folioMensaje((Integer) Arrays.asList(row).get(MagicNumber.SEVEN.getValue()))
				.valoresReferenciaEnvio((String) Arrays.asList(row).get(MagicNumber.EIGHT.getValue()))
				.inicioProcesoDestino((String) Arrays.asList(row).get(MagicNumber.NINE.getValue()))
				.cveEntidadDestino((String) Arrays.asList(row).get(MagicNumber.TEN.getValue()))				
				.cveProcesodDestino((String) Arrays.asList(row).get(MagicNumber.ELEVEN.getValue()))
				.versionDestino((BigDecimal) Arrays.asList(row).get(MagicNumber.TWELVE.getValue()))
				.cveNodoDestino((String) Arrays.asList(row).get(MagicNumber.THIRTEEN.getValue()))
				.idNodoDestino((BigDecimal) Arrays.asList(row).get(MagicNumber.FOURTEEN.getValue()))
				.build();

		return itemSelected;
	};



}
