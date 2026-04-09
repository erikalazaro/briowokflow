/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.transferobjects.repse;

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * El objetivo de la Class DocumentoTrabajadorTO.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Mar 9, 2020 11:35:41 AM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "ListaDocumentosTrabajadorTO.", description = "Documentos de una proceso.")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ListaDocumentosTrabajadorTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/** El atributo o variable nom archivo. */
	@Schema(name = "lista", description = "lista de documentos.")
	private List<DocumentoTrabajadorTO> lista;
	

}