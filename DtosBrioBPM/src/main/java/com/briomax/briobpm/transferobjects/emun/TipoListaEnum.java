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
 * Crear una nueva instancia del objeto tipo lista enum.
 * @author Rigoberto Olvera C.
 * @version 1.0 Fecha de creacion 31/01/2020 12:10:17 PM Modificaciones:
 * @since JDK 1.8
 */
@AllArgsConstructor
public enum TipoListaEnum {

	CATALOGO_ETIQUETA("CATALOGO_ETIQUETA"),
	TABLA("TABLA"),
	NINGUNA("");

	/** El atributo o variable value. */
	private @Getter String value;


	/**
	 * Obtener el valor de tipo lista enum.
	 * @param funcion el funcion.
	 * @return el tipo lista enum.
	 */
	public static TipoListaEnum getTipoListaEnum(final String funcion) {
		if (funcion != null) {
			for (TipoListaEnum function : TipoListaEnum.values()) {
				if (funcion.toUpperCase().equals(function.getValue())) {
					return function;
				}
			}
		}
		return TipoListaEnum.NINGUNA;
	}

}
