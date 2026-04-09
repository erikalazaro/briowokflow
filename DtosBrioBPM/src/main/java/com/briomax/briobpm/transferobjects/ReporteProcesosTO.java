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
 * El objetivo de la Class ReporteProcesosTO.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Oct 6, 2020 5:48:43 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "ReporteProcesos", description = "Reporte por Procesos.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReporteProcesosTO implements ITransferObject {

	/** Serial Version UID. */
	private static final long serialVersionUID = 8648753675829842698L;

	/** El atributo o variable id proceso. */
	@Schema(name = "idProceso", description = "Id del Proceso", implementation = String.class)
	private String idProceso;

	/** El atributo o variable nom proceso. */
	@Schema(name = "nomProceso", description = "Nombre del Proceso", implementation = String.class)
	private String nomProceso;

	/** El atributo o variable instancia. */
	@Schema(name = "instancia", description = "Clave de la Instancia", implementation = String.class)
	private String instancia;

	/** El atributo o variable fec ho inicio. */
	@Schema(name = "fecHorInicio", description = "Fecha y Hora de Inicio", implementation = String.class)
	private String fecHorInicio;

	/** El atributo o variable fec hor termino. */
	@Schema(name = "fecHorTermino", description = "Fecha y Hora de Termino", implementation = String.class)
	private String fecHorTermino;

	/** El atributo o variable situacion. */
	@Schema(name = "situacion", description = "Situacion", implementation = String.class)
	private String situacion;

}
