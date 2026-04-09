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
 * El objetivo de la Class ProcesoTO.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jun 25, 2020 7:14:58 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "CrReciboNominaTO", title = "Recibos de Nomina.")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrReciboNominaTO implements ITransferObject {

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
	
	@Schema(name = "rfcTrabajador", implementation = String.class)
	private String rfcTrabajador;
	
	@Schema(name = "tipoNomina", implementation = String.class)
	private String tipoNomina;
	
	@Schema(name = "secuenciaRecibo", implementation = String.class)
	private Integer secuenciaRecibo;
	
	@Schema(name = "nombreDocumento", implementation = String.class)
	private String nombreDocumento;
	
	@Schema(name = "razonSocialPatron", implementation = String.class)
	private String razonSocialPatron;
	
	@Schema(name = "rfcPatron", implementation = String.class)
	private String rfcPatron;
	
	@Schema(name = "registroPatronal", implementation = String.class)
	private String registroPatronal;
	
	@Schema(name = "fechaInicioPeriodo", implementation = String.class)
	private String fechaInicioPeriodo;
	
	@Schema(name = "fechaFinalPeriodo", implementation = String.class)
	private String fechaFinalPeriodo;
	
	@Schema(name = "fechaPago", implementation = String.class)
	private String fechaPago;
	
	@Schema(name = "fechaTimbrado", implementation = String.class)
	private String fechaTimbrado;
	
	@Schema(name = "nombreTrabajdor", implementation = String.class)
	private String nombreTrabajdor;
	
	@Schema(name = "curpTrabajdor", implementation = String.class)
	private String curpTrabajdor;
	
	@Schema(name = "nssTrabajdor", implementation = String.class)
	private String nssTrabajdor;
	
	@Schema(name = "salarioBase", implementation = String.class) 
	private String salarioBase;
	 
	@Schema(name = "situacionCarga", implementation = String.class)
	private String situacionCarga;

	@Schema(name = "boton", implementation = String.class)
	private String boton;
}
