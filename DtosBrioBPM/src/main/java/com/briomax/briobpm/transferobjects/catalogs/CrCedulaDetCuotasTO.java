/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.transferobjects.catalogs;

import java.math.BigDecimal;

import com.briomax.briobpm.transferobjects.core.ITransferObject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class CrCedulaDetCuotasTO.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Sept 18, 2024 7:14:58 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "CrCedulaDetCuotasTO", title = "Cr Cedula Det Cuotas DTO.")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrCedulaDetCuotasTO implements ITransferObject {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;

	@Schema(name = "cveEntidad", implementation = String.class)
	private String cveEntidad;

	@Schema(name = "cveProceso", implementation = String.class)
	private String cveProceso;

	@Schema(name = "rfcContratista", implementation = String.class)
	private String rfcContratista;

	@Schema(name = "numeroContrato", implementation = String.class)
	private String numeroContrato;

	@Schema(name = "fechaCarga", implementation = String.class)
	private String fechaCarga;

	@Schema(name = "nombreDocumento", implementation = String.class)
	private String nombreDocumento;

	@Schema(name = "razonSocial", implementation = String.class)
	private String razonSocial;

	@Schema(name = "registroPatronal", implementation = String.class)
	private String registroPatronal;

	@Schema(name = "nssTrabajador", implementation = String.class)
	private String nssTrabajador;

	@Schema(name = "nombreTrabajador", implementation = String.class)
	private String nombreTrabajador;

	@Schema(name = "rfcCurpTrabajador", implementation = String.class)
	private String rfcCurpTrabajador;

	@Schema(name = "salarioBaseCotizacion", implementation = BigDecimal.class)
	private BigDecimal salarioBaseCotizacion;

	@Schema(name = "periodo", implementation = String.class)
	private String periodo;

	@Schema(name = "situacionCarga", implementation = String.class)
	private String situacionCarga;
	
	@Schema(name = "situacionTrabajador", implementation = String.class)
	private String situacionTrabajador;

	@Schema(name = "boton", implementation = String.class)
	private String boton;

}


