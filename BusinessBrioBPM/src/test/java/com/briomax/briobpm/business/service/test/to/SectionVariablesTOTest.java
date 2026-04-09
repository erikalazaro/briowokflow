/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.service.test.to;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class SectionVariablesTOTest.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Nov 9, 2020 7:52:50 PM Modificaciones:
 * @since JDK 1.8
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SectionVariablesTOTest implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = 6035594771371002960L;

	private String cveVariable;

	private String envioGrabar;
	
	private int index;

}
