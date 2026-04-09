/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.transferobjects.emun;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * El objetivo de la Enum TipoPresentacionEnum.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Feb 21, 2020 11:46:54 AM Modificaciones:
 * @since JDK 1.8
 */
@AllArgsConstructor
public enum TipoPresentacionEnum {

	SECUENCIAL("SECUENCIAL"),
	GRID("GRID"),
	NINGUNA("");

	/** El atributo o variable value. */
	private @Getter String value;

	/**
	 * Obtener el valor de tipo Presentacion enum.
	 * @param tipoPresentacion el Tipo Presentacion.
	 * @return el tipo lista enum.
	 */
	public static TipoPresentacionEnum getTipoPresentacionEnum(final String tipoPresentacion) {
		if (tipoPresentacion != null) {
			for (TipoPresentacionEnum function : TipoPresentacionEnum.values()) {
				if (tipoPresentacion.toUpperCase().equals(function.getValue())) {
					return function;
				}
			}
		}
		return TipoPresentacionEnum.NINGUNA;
	}

}
