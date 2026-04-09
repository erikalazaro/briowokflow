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
 * El objetivo de la Enum TipoContenidoEnum.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Mar 4, 2020 4:10:03 PM Modificaciones:
 * @since JDK 1.8
 */
@AllArgsConstructor
public enum TipoContenidoEnum {

	VARIABLES("VARIABLES"),
	TAREAS("TAREAS"),
	DOCUMENTOS("DOCUMENTOS"),
	NINGUNO("");

	/** El atributo o variable value. */
	private @Getter String value;

	/**
	 * Obtener el valor de Tipo Contenido enum.
	 * @param tipoContenido el Tipo Presentacion.
	 * @return el tipo lista enum.
	 */
	public static TipoContenidoEnum getTipoContenidoEnum(final String tipoContenido) {
		if (tipoContenido != null) {
			for (TipoContenidoEnum function : TipoContenidoEnum.values()) {
				if (tipoContenido.toUpperCase().equals(function.getValue())) {
					return function;
				}
			}
		}
		return TipoContenidoEnum.NINGUNO;
	}

}
