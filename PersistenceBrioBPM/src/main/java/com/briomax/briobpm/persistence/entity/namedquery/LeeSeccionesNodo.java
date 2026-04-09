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
	@NamedStoredProcedureQuery(name = "obtenerSeccionesNodo",
		procedureName = "SP_LEE_SECCIONES_NODO",
		parameters = {
			@StoredProcedureParameter(name = "CH_CVE_USUARIO", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_ENTIDAD", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_LOCALIDAD", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_IDIOMA", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_PROCESO", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "C_VERSION", type = BigDecimal.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_NODO", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "I_ID_NODO", type = Integer.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_GENERA_TABLA_TEMPORAL", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_TIPO_EXCEPCION", type = String.class, mode = ParameterMode.OUT),
			@StoredProcedureParameter(name = "CH_MENSAJE", type = String.class, mode = ParameterMode.OUT)},
		resultClasses = LeeSeccionesNodo.class
	)
})
*/
/**
 * El objetivo de la Class LeeSeccionesNodo.java es ...
 * @author Rigoberto Olvera C.
 * @version 1.0 Fecha de creacion 4/02/2020 10:39:25 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Getter
@Setter
@ToString
public class LeeSeccionesNodo implements IEntity {

	/** serial Version UID. */
	private static final long serialVersionUID = 905119968423459460L;

	/** El atributo o variable orden. */
	@Column(name = "ORDEN")
	@Id
	private Integer orden;

	/** El atributo o variable cve seccion. */
	@Column(name = "CVE_SECCION")
	private String cveSeccion;
	
	/** El atributo o variable etiqueta seccion. */
	@Column(name = "ETIQUETA_SECCION")
	private String etiquetaSeccion;
	
	/** El atributo o variable tipo presentacion. */
	@Column(name = "TIPO_PRESENTACION")
	private String tipoPresentacion;

	/** El atributo o variable contenido. */
	@Column(name = "CONTENIDO")
	private String contenido;

	/** El atributo o variable boton crear renglon. */
	@Column(name = "BOTON_CREAR_RENGLON")
	private String botonCrearRenglon;

	/** El atributo o variable etiqueta crear renglon. */
	@Column(name = "ETIQUETA_CREAR_RENGLON")
	private String etiquetaCrearRenglon;

	/** El atributo o variable boton eliminar renglon. */
	@Column(name = "BOTON_ELIMINAR_RENGLON")
	private String botonEliminarRenglon;

	/** El atributo o variable etiqueta eliminar renglon. */
	@Column(name = "ETIQUETA_ELIMINAR_RENGLON")
	private String etiquetaEliminarRenglon;

}
