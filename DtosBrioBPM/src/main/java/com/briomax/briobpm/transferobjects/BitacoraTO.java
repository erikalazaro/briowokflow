/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.transferobjects;

import java.util.Date;

import com.briomax.briobpm.transferobjects.core.ITransferObject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class BitacoraTO.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Dec 31, 2020 4:45:26 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "BitacoraTO", description = "Bitacora.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BitacoraTO implements ITransferObject {

	/** Serial Version UID. */
	private static final long serialVersionUID = 865125046274019561L;

	/** El atributo o variable accion. */
	@Schema(name = "accion", description = "Accion.", implementation = String.class)
	private String accion;

	/** El atributo o variable fecha. */
	@Schema(name = "fecha", description = "Fecha Accion.", implementation = String.class)
	private String fecha;

	/** El atributo o variable usuario. */
	@Schema(name = "usuario", description = "Usuario Accion.", implementation = String.class)
	private String usuario;

}
