/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.transferobjects.catalogs;

import java.util.Date;

import com.briomax.briobpm.transferobjects.core.ITransferObject;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class StRolProcesoTO.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jun 25, 2020 7:19:05 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "RolProcesoTO", title = "Roles de un Proceso de una Entidad.")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RolProcesoTO implements ITransferObject {

	/** Serial Version UID. */
	private static final long serialVersionUID = -6419313456146901424L;

	@JsonIgnore
	/** El atributo o variable cve entidad. */
	private String cveEntidad;

	/** El atributo o variable cve proceso. */
	@Schema(name = "cveProceso", description = "Clave del Proceso.", example = "DUMMY", implementation = String.class)
	private String cveProceso;

	/** El atributo o variable version. */
	@Schema(name = "version", description = "VErsion del Proceso.", example = "1.00", implementation = String.class)
	private String version;

	/** El atributo o variable cve rol. */
	@Schema(name = "cveRol", description = "Clave del Rol.", example = "ROL DUMMY 1", implementation = String.class)
	private String cveRol;

	/** El atributo o variable descripcion. */
	@Schema(name = "descripcion", description = "Descripcion del Rol.", example = "Rol Dummy 1",
			implementation = String.class)
	private String descripcion;

	/** El atributo o variable situacion. */
	@Schema(name = "situacion", description = "Situacion HABILITADO/DESHABILITADO", example = "HABILITADO",
			implementation = String.class)
	private String situacion;

	/** El atributo o variable fec ult cambio. */
	@Schema(name = "fecUltCambio", description = "Fecha Ultimo Cambio", implementation = Date.class)
	private Date fecUltCambio;

}
