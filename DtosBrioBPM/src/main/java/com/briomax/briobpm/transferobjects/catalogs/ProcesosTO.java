/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.transferobjects.catalogs;

import java.math.BigDecimal;

import com.briomax.briobpm.transferobjects.core.ITransferObject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class ProcesoTO.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jun 25, 2020 7:14:58 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "ProcesosTO", title = "Procesos de una Entidad.")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcesosTO implements ITransferObject {

	/** Serial Version UID. */
	private static final long serialVersionUID = -615666193340270463L;

	/** El atributo o variable cve proceso. */
	@Schema(name = "cveProceso", description = "Clave del Proceso.", example = "DUMMY", implementation = String.class)
	private String cveProceso;

	/** El atributo o variable version. */
	@Schema(name = "version", description = "Version del Proceso.", example = "1.00", implementation = String.class)
	private String version;

	/** El atributo o variable descripcion. */
	@Schema(name = "descripcion", description = "Descripcion del Proceso.", example = "Proceso Dummy",
			implementation = String.class)
	private String descripcion;

	/** El atributo o variable situacion. */
	@Schema(name = "situacion", description = "Situacion HABILITADO/DESHABILITADO", example = "HABILITADO",
			implementation = String.class)
	private String situacion;

	/** El atributo o variable instancias dia. */
	@Schema(name = "instanciasDia", description = "Instancias Dia", example = "", implementation = BigDecimal.class)
	private BigDecimal instanciasDia;

	/** El atributo o variable instancias semana. */
	@Schema(name = "instanciasSemana", description = "Instancias Semana", example = "",
			implementation = BigDecimal.class)
	private BigDecimal instanciasSemana;

	/** El atributo o variable id nodo inicio. */
	@Schema(name = "idNodoInicio", description = "ID Nodo Inicio", example = "1", implementation = Integer.class)
	private Integer idNodoInicio;

	/** El atributo o variable cve nodo inicio. */
	@Schema(name = "tipoNodoInicio", description = "Tipo Nodo Inicio", implementation = TipoNodoTO.class)
	private TipoNodoTO tipoNodoInicio;

	/** El atributo o variable id nodo fin. */
	@Schema(name = "idNodoFin", description = "ID Nodo Fin", example = "4", implementation = Integer.class)
	private Integer idNodoFin;

	/** El atributo o variable cve nodo fin. */
	@Schema(name = "tipoNodoFin", description = "Tipo Nodo Fin.", implementation = TipoNodoTO.class)
	private TipoNodoTO tipoNodoFin;

}
