/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.common.exception;

/**
 * El objetivo de la Class BrioBPMException.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jan 27, 2021 2:02:58 PM Modificaciones:
 * @since JDK 1.8
 */
public class BrioBPMException extends RuntimeException {

	/** La Constante SUGERENCIA. */
	private static final String SUGERENCIA = "\tACCION:";

	/** La Constante MENSSAGE. */
	private static final String MENSSAGE = "\tMENSAJE:";

	/** La Constante CODIGO_ERROR. */
	private static final String CODIGO_ERROR = "CODIGO ERROR:";

	/** serial Version UID. */
	private static final long serialVersionUID = 1L;

	/** El atributo o variable codigo error. */
	private int codigoError;

	/** El atributo o variable mensaje. */
	private String mensaje;

	/** El atributo o variable accion. */
	private String accion;

	/** El atributo o variable data access. */
	private boolean dataAccess;

	/**
	 * Instantiates a new contratacion linea exception.
	 * @param e the e.
	 */
	public BrioBPMException(Exception e) {
		super(e);
		this.mensaje = e.getMessage();
	}

	/**
	 * Instantiates a new contratacion linea exception.
	 * @param mensaje the mensaje.
	 * @param e the e.
	 */
	public BrioBPMException(String mensaje, Exception e) {
		super(MENSSAGE + mensaje, e);
		this.mensaje = mensaje;
	}

	/**
	 * Instantiates a new contratacion linea exception.
	 * @param dataAccess el data access.
	 * @param codigoError the codigo error.
	 * @param mensaje the mensaje.
	 * @param e the e.
	 */
	public BrioBPMException(boolean dataAccess, int codigoError, String mensaje, Exception e) {
		super(CODIGO_ERROR + codigoError + MENSSAGE + mensaje, e);
		this.codigoError = codigoError;
		this.mensaje = mensaje;
		this.dataAccess = dataAccess;
	}

	/**
	 * Crear una nueva instancia del objeto brio BPM exception.
	 * @param dataAccess el data access.
	 * @param codigoError el codigo error.
	 * @param mensaje el mensaje.
	 * @param accion el accion.
	 * @param e el e.
	 */
	public BrioBPMException(boolean dataAccess, int codigoError, String mensaje, String accion, Exception e) {
		super(CODIGO_ERROR + codigoError + MENSSAGE + mensaje, e);
		this.codigoError = codigoError;
		this.mensaje = mensaje;
		this.accion = accion;
		this.dataAccess = dataAccess;
	}

	/**
	 * Crear una nueva instancia del objeto brio BPM exception.
	 * @param dataAccess el data access.
	 * @param codigoError el codigo error.
	 * @param mensaje el mensaje.
	 * @param e el e.
	 */
	public BrioBPMException(boolean dataAccess, int codigoError, String mensaje, String accion, Throwable e) {
		super(CODIGO_ERROR + codigoError + MENSSAGE + mensaje, e);
		this.codigoError = codigoError;
		this.mensaje = mensaje;
		this.accion = accion;
		this.dataAccess = dataAccess;
	}

	/**
	 * Instantiates a new contratacion linea exception.
	 * @param codigoError the codigo error.
	 * @param mensaje the mensaje.
	 * @param accion the accion.
	 */
	public BrioBPMException(int codigoError, String mensaje, String accion) {
		super(CODIGO_ERROR + codigoError + MENSSAGE + mensaje + SUGERENCIA + accion);
		this.codigoError = codigoError;
		this.mensaje = mensaje;
		this.accion = accion;
	}

	/**
	 * Gets the el atributo o variable codigo error.
	 * @return the el atributo o variable codigo error
	 */
	public int getCodigoError() {
		return codigoError;
	}

	/**
	 * Sets the el atributo o variable codigo error.
	 * @param codigoError the new el atributo o variable codigo error
	 */
	public void setCodigoError(int codigoError) {
		this.codigoError = codigoError;
	}

	/**
	 * Gets the el atributo o variable mensaje.
	 * @return the el atributo o variable mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Sets the el atributo o variable mensaje.
	 * @param mensaje the new el atributo o variable mensaje
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * Gets the el atributo o variable accion.
	 * @return the el atributo o variable accion
	 */
	public String getAccion() {
		return accion;
	}

	/**
	 * Sets the el atributo o variable accion.
	 * @param accion the new el atributo o variable accion
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}

	/**
	 * Comprueba si es data access.
	 * @return verdadero, si es data access.
	 */
	public boolean isDataAccess() {
		return dataAccess;
	}
}
