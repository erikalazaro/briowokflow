/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.persistence.entity.namedquery;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.briomax.briobpm.persistence.entity.IPrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class LeeMenuEstaticoPK.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Oct 18, 2021 6:22:14 PM Modificaciones:
 * @since JDK 1.8
 */
@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeeMenuEstaticoPK implements IPrimaryKey {

	/** serial Version UID. */
	private static final long serialVersionUID = 1L;

	/** El atributo o variable secuencia. */
	@Column(name="ORDENAMIENTO")
	private Integer ordenamiento;
	
	/** El atributo o variable cve menu. */
	@Column(name="CVE_MENU")
	private String cveMenu;

	/** El atributo o variable cve opcion. */
	@Column(name="CVE_OPCION")
	private String cveOpcion;

	/** El atributo o variable url opcion. */
	@Column(name="ID_OPCION")
	private String idOpcion;

}

