/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.persistence.entity.namedquery;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.briomax.briobpm.persistence.entity.IPrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class LeeCorreosPorEnviarPK.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Apr 13, 2020 10:17:01 AM Modificaciones:
 * @since JDK 1.8
 */
@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeeCorreosPorEnviarPK implements IPrimaryKey {

	/** Serial Version UID. */
	private static final long serialVersionUID = 9200429373650909256L;

	/** El atributo o variable cve proceso. */
	@Column(name = "CVE_PROCESO")
	private String cveProceso;

	/** El atributo o variable version. */
	@Column(name = "VERSION")
	private BigDecimal version;

	/** El atributo o variable num correo. */
	@Column(name = "NUMERO_CORREO")
	private Long numCorreo;
	
}
