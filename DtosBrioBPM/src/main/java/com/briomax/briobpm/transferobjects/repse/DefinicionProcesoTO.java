/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.transferobjects.repse;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;

import com.briomax.briobpm.transferobjects.core.ITransferObject;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * El objetivo de la Class DefinicionProcesoTO.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Aug 12, 2020 11:09:51 AM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "DefinicionProcesoTO", title = "DefinicionProcesoTO.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DefinicionProcesoTO implements ITransferObject {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;

	/** El atributo o variable cve entidad. */
	@Schema(name = "cveEntidad", description = "Clave de la Entidad.", example = "BRIOMAX",
			implementation = String.class)
	private String cveEntidad;
	
	/** El atributo o variable localidades. */
	@Schema(name = "cveLocalidad", description = "Clave de Localidad.", example = "BRIOMAX-CENTRAL",
			implementation = String.class)
	private String cveLocalidad;

	/** El atributo o variable idioma. */
	@Schema(name = "cveIdioma", description = "Clave de Idioma.", example = "ES-MX",
			implementation = String.class)
	private String cveIdioma;
	
	/** El atributo o variable cve proceso. */
	@Column(name = "CVE_PROCESO", nullable = false, length = 40)
	private String cveProceso;

	/** El atributo o variable version. */
	@Column(name = "VERSION", nullable = false,  precision = 5, scale = 2)
	private BigDecimal version;
	
	/** El atributo o variable rfc. */
	@Schema(name = "rfc", description = "Clave de la Entidad.", example = "RFC",
			implementation = String.class)
	private String rfc;
	
	/** El atributo o variable contrato. */
	@Schema(name = "contrato", description = "Clave de la Entidad.", example = "0001",
			implementation = String.class)
	private String contrato;
	
	/** El atributo o variable contrato. */
	@Schema(name = "fechaIncio", description = "Fecha Incio Contrato.", example = "2025-01-31",
			implementation = Date.class)
	private Date fechaIncio;

	/** El atributo o variable contrato. */
	@Schema(name = "fechaFin", description = "Fecha Fin Contrato.", example = "2025-09-31",
			implementation = Date.class)
	private Date fechaFin;
}
