
package com.briomax.briobpm.common.exception;

public class ConverterExcepcion extends Exception {

	private static final long serialVersionUID = 5546731600766965685L;

	/**
	 * Arroja la excepcion personalizada.
	 * @param causaFinal Causa de la excepcion.
	 */
	public ConverterExcepcion(final Throwable causaFinal) {
		super(causaFinal);
	}

	/**
	 * Arroja la excepcion personalizada.
	 * @param mensajeFinal the message.
	 */
	public ConverterExcepcion(final String mensajeFinal) {
		super(mensajeFinal);
	}

	/**
	 * Arroja la excepcion personalizada.
	 * @param mensajeFinal Mensaje a especificar para la excepcion.
	 * @param causaFinal Causa de la excepcion.
	 */
	public ConverterExcepcion(final String mensajeFinal, final Throwable causaFinal) {
		super(mensajeFinal, causaFinal);
	}

	/**
	 * Arroja la excepcion personalizada.
	 * @param mensaje Mensaje a especificar en la excepcion.
	 * @param causaFinal Causa de la excepcion.
	 * @param activaSupresionFinal Especifica si se deben suprimir las advertencias.
	 * @param trazaStackEscribableFinal Especifica si se debe imprimir el stack de la excepcion.
	 */
	public ConverterExcepcion(final String mensaje, final Throwable causaFinal, final boolean activaSupresionFinal,
		final boolean trazaStackEscribableFinal) {
		super(mensaje, causaFinal, activaSupresionFinal, trazaStackEscribableFinal);
	}

}
