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
 * El objetivo de la Class CrDeclaracionProvisionalTO.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jun 25, 2020 7:14:58 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "CrDeclaracionProvisionalTO", title = "Declaracion Provisional.")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrDeclaracionProvisionalTO implements ITransferObject {

	/** Serial Version UID. */
	private static final long serialVersionUID = -615666193340270463L;

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
	
	@Schema(name = "secuenciaDeclaracion", implementation = String.class)
	private Integer secuenciaDeclaracion;
	
	@Schema(name = "nombreDocumento", implementation = String.class)
	private String nombreDocumento;
	
	@Schema(name = "razonSocial", implementation = String.class)
	private String razonSocial;
	
	@Schema(name = "rfc", implementation = String.class)
	private String rfc;
	
	@Schema(name = "tipoDeclaracion", implementation = String.class)
	private String tipoDeclaracion;
	
	@Schema(name = "periodoDeclaracion", implementation = String.class)
	private String periodoDeclaracion;
	
	@Schema(name = "ejercicio", implementation = String.class)
	private String ejercicio;
	
	@Schema(name = "fechaHoraPresentacion", implementation = String.class)
	private String fechaHoraPresentacion;
	
	@Schema(name = "lineaCaptura", implementation = String.class)
	private String lineaCaptura;
	
	@Schema(name = "conceptoPago", implementation = String.class)
	private String conceptoPago;
	
	@Schema(name = "importeApagar", implementation = String.class)
	private String importeApagar;
	 
	@Schema(name = "situacionCarga", implementation = String.class)
	private String situacionCarga;

	@Schema(name = "boton", implementation = String.class)
	private String boton;
}
