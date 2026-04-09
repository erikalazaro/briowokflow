/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.transferobjects.dashboards;

/**
 * El objetivo de la Interface IGraph.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion May 12, 2021 1:37:49 PM Modificaciones:
 * @since JDK 1.8
 */
public interface IGraph {

	/**
	 * Obtener el valor de tipo.
	 * @return el tipo.
	 */
	public abstract TypeChartEnum getTipo();
	
	/**
	 * Obtener el valor de order.
	 * @return el order.
	 */
	public abstract int getOrder();

	/**
	 * Convertir a String el Object.
	 * @return the string
	 */
	public abstract String toString();

}
