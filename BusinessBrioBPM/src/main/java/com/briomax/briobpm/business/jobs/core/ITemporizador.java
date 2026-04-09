/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.business.jobs.core;

/**
 * El objetivo de la Interface ITemporizador.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Oct 12, 2020 7:01:44 PM Modificaciones:
 * @since JDK 1.8
 */
public interface ITemporizador {

	/**
	 * Temporizador de Actividades.
	 */
	public void actividades();

	/**
	 * Vigencia documentos.
	 */
	public void vigenciaDocumentos();

	/**
	 * Procesa mensajes.
	 */
	public void procesaMensajes();

	/**
	 * Procesa procesa Repse.
	 */
	public void procesaRepse();

	/**
	 * Método para notificación de usuarios.
	 */
	public void notifiacionUsuariosJob();
	
	/**
	 * Método para notificación de usuarios nuevos.
	 */
	public void notifiacionNewUsuariosJob();
	
	/**
	 * Método para depuración de documentos Compliance REPSE.
	 */
	public void depurarDocumentosJob();
	
	/**
	 * Método para generar ddeclaracion complementaria.
	 */
	void generaDefincionesProcesos();

	/**
	 * Método para ajusta ddeclaracion complementaria.
	 */
	void ajustaDefincionesProcesos();
	

	/**
	 * Método para ajusta declaracion complementaria.
	 */
	void ajustaProcesoReanudacion();

	/**
     * Consulta REPSE Web y guarda resultado en base de datos y archivos.
     */
    void consultaRepseWeb();
    
	/**
	 * Temporizador de Actividades.
	 */
	public void actividadesPendientes();

	
}