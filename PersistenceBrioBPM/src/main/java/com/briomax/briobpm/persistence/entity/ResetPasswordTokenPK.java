/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * El objetivo de la Class ResetPasswordTokenPK.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion 10/01/2026 Modificaciones:
 * @since JDK 1.8
 */
@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordTokenPK implements IPrimaryKey {

	/** Serial Version UID. */
	private static final long serialVersionUID = 34338223936681891L;

	/** El atributo o variable cve usuario. */
	@Column(name = "ID", nullable = false)
	private Integer id;
	
	/** El atributo o variable cve entidad. */
	@Column(name = "CVE_ENTIDAD", nullable = false, length = 40)
	private String cveEntidad;

	/** El atributo o variable cve usuario. */
	@Column(name = "CVE_USUARIO", nullable = false, length = 30)
	private String cveUsuario;

}
