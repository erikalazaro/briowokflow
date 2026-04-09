/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.transferobjects.dynamicForm;

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class DataGrid.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Mar 4, 2020 4:31:50 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "DataGrid", title = "Configuracion del Grid.")
@Data/*lombok*/
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataGrid implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = -2720263948175388532L;

	/** El atributo o variable type. */
	@Schema(name = "type", description = "Tipo FORM(Editable), GRID(Consulta)", example = "GRID")
	private String type;

	/** El atributo o variable config. */
	@Schema(name = "config", description = "Configuración de las columnas del Grid.", implementation = Config.class)
	private Config config;

	/** El atributo o variable tail. */
	@ArraySchema(schema = @Schema(name = "tail", description = "Datos del Grid.", implementation = Row.class))
	private List<Row> tail;

	/** El atributo o variable footer. */
	@Schema(name = "footer", description = "Footer.", implementation = Footer.class)
	private Footer footer;
	
	/** El atributo o variable gráfico. */
	@Schema(name = "grafico", description = "contiene tipo de área y datos", implementation = Grafico.class)
	private Grafico grafico;
	
	/** El atributo o variable para las tarjetas. */
	@Schema(name = "renglonConTarjeta", description = "contiene los renglones o instancias con sus tarjetas", implementation = StNodoProcesoT.class)
	private List<StNodoProcesoT>  renglonConTarjeta;

}
