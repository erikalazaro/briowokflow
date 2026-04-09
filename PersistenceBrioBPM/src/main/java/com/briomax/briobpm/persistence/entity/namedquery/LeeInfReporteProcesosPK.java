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
 * El objetivo de la Class LeeInfReporteProcesosPK.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Oct 6, 2020 5:39:46 PM Modificaciones:
 * @since JDK 1.8
 */
@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeeInfReporteProcesosPK implements IPrimaryKey {

	/** Serial Version UID. */
	private static final long serialVersionUID = 8268126817254919987L;

	/** El atributo o variable id proceso. */
	@Column(name = "ID_PROCESO")
	private String idProceso;

	/** El atributo o variable nombre proceso. */
	@Column(name = "NOMBRE_PROCESO")
	private String nombreProceso;

	/** El atributo o variable instancia. */
	@Column(name = "INSTANCIA")
	private String instancia;

}
