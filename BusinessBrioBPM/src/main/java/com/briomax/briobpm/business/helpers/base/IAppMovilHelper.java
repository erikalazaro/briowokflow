package com.briomax.briobpm.business.helpers.base;

public interface IAppMovilHelper {

	/*
	 * Método que castea los datos
	 * @param tipoDato Tipo de dato
	 * @param requerido Indica si es requerido
	 * @param tipoControl Tipo de control
	 * @param interaccion Interacción
	 * @return String
	 */
	String casteaDatos(String tipoDato, String requerido, String tipoControl, String interaccion);

}
