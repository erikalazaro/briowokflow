/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * El objetivo de la Class LocalidadEntidadPK.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jul 27, 2020 1:25:00 PM Modificaciones:
 * @since JDK 1.8
 */
@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InhabilCalendarioPK implements IPrimaryKey {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;

	/** El atributo o variable cve entidad. */
	@Column(name = "CVE_ENTIDAD", nullable = false, length = 40)
	private String cveEntidad;

	/** El atributo o variable cve localidad. */
	@Column(name = "CVE_LOCALIDAD", nullable = false, length = 40)
	private String cveLocalidad;
	
	/** El atributo o variable fecha inhabil. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_INHABIL")
	private Date fechaInhabil;

}
