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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class OperativoGerenteIn.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion May 11, 2021 8:53:34 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "Operativo Gerente.", description = "Filtros Operativo Gerente.")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class OperativoGerenteIn extends OperativoIn implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = 79864710952813804L;

	/** El atributo o variable entidad. */
	@Schema(name = "entidad", description = "Entidad", example = "BRIOMAX", implementation = String.class)
	private String entidad;

	/** El atributo o variable top. */
	@Schema(name = "topSolicitado", description = "Top Solicitado.", example = "EFICIENTE=Eficientes, RETRASADO=Retrasados",
			implementation = String.class)
	private String topSolicitado;

	/** El atributo o variable num registros. */
	@Schema(name = "numRegistros", description = "Numero de Registros.", example = "5", implementation = Integer.class)
	private Integer numRegistros;

	/** El atributo o variable status. */
	@Schema(name = "situacion", description = "Situacion", example = "RETRASADAS=Retrasadas, VIGENTES=Vigentes, TODAS=Todas", implementation = String.class)
	private String situacion;

	/**
	 * Crear una nueva instancia del objeto operativo gerente in.
	 * @param fecInicial el fec inicial.
	 * @param fecFinal el fec final.
	 * @param entidad el entidad.
	 * @param topSolicitado el top solicitado.
	 * @param numRegistros el num registros.
	 * @param situacion el situacion.
	 */
	@Builder(builderMethodName = "builderOperativoGerenteIn")
	public OperativoGerenteIn(String fecInicial, String fecFinal, String entidad, String topSolicitado,
		Integer numRegistros, String situacion) {
		super(fecInicial, fecFinal);
		this.entidad = entidad;
		this.topSolicitado = topSolicitado;
		this.numRegistros = numRegistros;
		this.situacion = situacion;
	}

}
