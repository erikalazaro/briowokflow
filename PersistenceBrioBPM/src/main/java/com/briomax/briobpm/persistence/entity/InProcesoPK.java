/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class StProcesoPK.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jun 25, 2020 12:07:17 PM Modificaciones:
 * @since JDK 1.8
 */
@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InProcesoPK implements IPrimaryKey {

	/** Serial Version UID. */
	private static final long serialVersionUID = -4178752161863490994L;

	/** El atributo o variable cve entidad. */
	@Column(name = "CVE_ENTIDAD", nullable = false, length = 40)
	private String cveEntidad;

	/** El atributo o variable cve proceso. */
	@Column(name = "CVE_PROCESO", nullable = false, length = 40)
	private String cveProceso;

	/** El atributo o variable version. */
	@Column(name = "VERSION", nullable = false, length = 5, precision = 5, scale = 2)
	private BigDecimal version;

	/** El atributo o variable cve instancia. */
	@Column(name = "CVE_INSTANCIA", nullable = false, length = 40)
	private String cveInstancia;
}
