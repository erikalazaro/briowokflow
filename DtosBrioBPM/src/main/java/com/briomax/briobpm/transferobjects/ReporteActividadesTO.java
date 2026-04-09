/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.transferobjects;

import com.briomax.briobpm.transferobjects.core.ITransferObject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class ReporteActividadTO.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Oct 6, 2020 6:31:07 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "ReporteActividades", description = "Reporte por Actividades.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReporteActividadesTO implements ITransferObject {

	/** Serial Version UID. */
	private static final long serialVersionUID = 3383376665081612394L;

	/** El atributo o variable id proceso. */
	@Schema(name = "idProceso", description = "Id del Proceso", implementation = String.class)
	private String idProceso;

	/** El atributo o variable nom proceso. */
	@Schema(name = "nomProceso", description = "Nombre del Proceso", implementation = String.class)
	private String nomProceso;

	/** El atributo o variable instancia. */
	@Schema(name = "instancia", description = "Clave de la Instancia", implementation = String.class)
	private String instancia;

	/** El atributo o variable nom actividad. */
	@Schema(name = "nomActividad", description = "Nombre de la Actividad.", implementation = String.class)
	private String nomActividad;

	/** El atributo o variable rol actividad. */
	@Schema(name = "rolActividad", description = "Rol del Usuario de la Actividad.", implementation = String.class)
	private String rolActividad;

	/** El atributo o variable usu actividad. */
	@Schema(name = "usuActividad", description = "Usuario de la Actividad.", implementation = String.class)
	private String usuActividad;

	/** El atributo o variable fec asignacion. */
	@Schema(name = "fecAsignacion", description = "Fecha de Asignacion.", implementation = String.class)
	private String fecAsignacion;

	/** El atributo o variable fec hor terminacion. */
	@Schema(name = "fecHorTerminacion", description = "Fecha y Hora de Terminacion", implementation = String.class)
	private String fecHorTerminacion;

	/** El atributo o variable duracion. */
	@Schema(name = "duracion", description = "Duracion", implementation = String.class)
	private String duracion;

	/** El atributo o variable fec lim SLA. */
	@Schema(name = "fecLimSLA", description = "Fecha Limite SLA", implementation = String.class)
	private String fecLimSLA;

	/** El atributo o variable variacion. */
	@Schema(name = "variacion", description = "Variacion", implementation = String.class)
	private String variacion;

	/** El atributo o variable situacion. */
	@Schema(name = "situacion", description = "Situacion", implementation = String.class)
	private String situacion;

}
