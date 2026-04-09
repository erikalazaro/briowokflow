/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.transferobjects.dashboards;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class OperativoIn.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion May 11, 2021 8:53:19 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "Operativo.", description = "Filtros Operativo.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperativoIn implements Serializable {

	/** serial Version UID. */
	private static final long serialVersionUID = 4052557278214782534L;

	/** El atributo o variable fec inicial. */
	@Schema(name = "fecInicial", description = "Fecha Inicial.", example = "DD/MM/YYYY", implementation = String.class)
	private String fecInicial;

	/** El atributo o variable fec final. */
	@Schema(name = "fecFinal", description = "Fecha Final.", example = "DD/MM/YYYY", implementation = String.class)
	private String fecFinal;

}
