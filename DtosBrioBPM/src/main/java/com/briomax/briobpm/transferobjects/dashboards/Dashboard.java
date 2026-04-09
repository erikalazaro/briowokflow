/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.transferobjects.dashboards;

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class Dashboard.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion May 12, 2021 1:28:45 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "Dashboard.", description = "Dashboard.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dashboard implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = -2457630818135979304L;

	/** El atributo o variable sections. */
	@ArraySchema(schema = @Schema(name = "sections", description = "Secciones del Dashboard.", implementation = Section.class))
	private List<Section> sections;

}
