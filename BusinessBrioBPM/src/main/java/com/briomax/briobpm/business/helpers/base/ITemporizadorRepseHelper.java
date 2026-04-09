package com.briomax.briobpm.business.helpers.base;

/**
 * El objetivo de la Interface ITemporizadorHelper.java es ...
 * 
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion 30 de Septiembre del 2024
 * @since JDK 1.8
 */
public interface ITemporizadorRepseHelper {

		
	/**
	 * Genera procesos considerando los definidos en parametrso generales
	 * @param claseContrato
	 */
	void agregarNuevosProcesos(String claseContrato);
	
	/**
	 * Ajusta los procesos de acuerdo a los flujos
	 */
	void ajustaProcesos();
	
	/**
	 * Ajusta los procesos de acuerdo a los flujos
	 */
	void ajustaReanudacion();
	
	/**
	 * Crea procesos configurados de forma manual
	 */
	void procesosManuales();

}
