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
 * El objetivo de la Class LeeColumnasSeccionOcuPK.java es ...
 * @author Rigoberto Olvera C.
 * @version 1.0 Fecha de creacion 4/02/2020 10:40:38 PM Modificaciones:
 * @since JDK 1.8
 */
@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeeColumnasSeccionOcuPK implements IPrimaryKey {

	/** serial Version UID. */
	private static final long serialVersionUID = 968129640698815648L;

	/** El atributo o variable cve seccion. */
	@Column(name = "CVE_SECCION")
	private String cveSeccion;

	/** El atributo o variable cve variable. */
	@Column(name = "CVE_VARIABLE")
	private String cveVariable;
	
}
