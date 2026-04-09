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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

import com.briomax.briobpm.persistence.entity.IEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/*
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(name = "obtenerTareasNodo",
		procedureName = "SP_LEE_TAREAS_NODO",
		parameters = {
			@StoredProcedureParameter(name = "CH_CVE_USUARIO", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_ENTIDAD", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_LOCALIDAD", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_IDIOMA", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_PROCESO", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "C_VERSION", type = BigDecimal.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_INSTANCIA", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_NODO", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "I_ID_NODO", type = Integer.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "I_SECUENCIA_NODO", type = Integer.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_SECCION", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_GENERA_TABLA_TEMPORAL", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_TIPO_EXCEPCION", type = String.class, mode = ParameterMode.OUT),
			@StoredProcedureParameter(name = "CH_MENSAJE", type = String.class, mode = ParameterMode.OUT)},
		resultClasses = LeeTareasNodo.class
	)
})
*/
/**
 * El objetivo de la Class LeeTareasNodo.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Mar 2, 2020 4:41:14 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Getter
@Setter
@ToString
public class LeeTareasNodo implements IEntity {

	/** serial Version UID. */
	private static final long serialVersionUID = 905119968423459460L;

	/** El atributo o variable secuencia tarea. */
	@Column(name = "SECUENCIA_TAREA")
	@Id
	private Integer secuenciaTarea;

	/** El atributo o variable descripcion tarea. */
	@Column(name = "DESCRIPCION_TAREA")
	private String descripcionTarea;
	
	/** El atributo o variable requerida. */
	@Column(name = "REQUERIDA")
	private String requerida;
	
	/** El atributo o variable completada. */
	@Column(name = "COMPLETADA")
	private String completada;
	
}
