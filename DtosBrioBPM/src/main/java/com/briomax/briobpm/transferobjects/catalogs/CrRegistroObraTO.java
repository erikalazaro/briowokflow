/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.transferobjects.catalogs;

import com.briomax.briobpm.transferobjects.core.ITransferObject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class CrRegistroObraTO.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Sept 03, 2024 7:14:58 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "CrRegistroObraTO", title = "Registro Obra (SIROC).")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrRegistroObraTO implements ITransferObject {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;

	@Schema(name = "cveEntidad", implementation = String.class)
	private String cveEntidad;
	
	/** El atributo o variable cve proceso. */
	@Schema(name = "cveProceso", implementation = String.class)
	private String cveProceso;

	@Schema(name = "rfcContratista", implementation = String.class)
	private String rfcContratista;
	
	@Schema(name = "numContrato", implementation = String.class)
	private String numContrato;
	
	@Schema(name = "fechaCarga", implementation = String.class)
	private String fechaCarga;
	
	@Schema(name = "nombreDocumento", implementation = String.class)
	private String nombreDocumento;
	
	@Schema(name = "razonSocial", implementation = String.class)
	private String razonSocial;
	
	@Schema(name = "registroPatronal", implementation = String.class)
	private String registroPatronal;
	
	@Schema(name = "numeroRepse", implementation = String.class)
	private String numeroRepse;
	
	@Schema(name = "registroObra", implementation = String.class)
	private String registroObra;
		
	@Schema(name = "montoObra", implementation = String.class)
	private String montoObra;
	
	@Schema(name = "ubicacionObra", implementation = String.class)
	private String ubicacionObra;
	
	@Schema(name = "fechaRegistro", implementation = String.class)
	private String fechaRegistro;
	 
	@Schema(name = "situacionCarga", implementation = String.class)
	private String situacionCarga;

	@Schema(name = "boton", implementation = String.class)
	private String boton;
}
