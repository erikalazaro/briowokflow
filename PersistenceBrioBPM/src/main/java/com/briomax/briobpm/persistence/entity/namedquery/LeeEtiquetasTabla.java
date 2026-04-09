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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
	@NamedStoredProcedureQuery(name = "obtenerEtiquetasTabla",
		procedureName = "SP_LEE_ETIQUETAS_TABLA",
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
			@StoredProcedureParameter(name = "CH_CVE_DATO", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_VALOR_LISTA", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_DESCRIPCION_LISTA", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_TABLA_LISTA", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_WHERE_LISTA", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_TIPO_EXCEPCION", type = String.class, mode = ParameterMode.OUT),
			@StoredProcedureParameter(name = "CH_MENSAJE", type = String.class, mode = ParameterMode.OUT)},
		resultClasses = LeeEtiquetasTabla.class
	)
})*/
/**
 * El objetivo de la Class LeeEtiquetasTabla.java es ...
 * @author Rigoberto Olvera C.
 * @version 1.0 Fecha de creacion 4/02/2020 10:37:41 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Getter
@Setter
@ToString
public class LeeEtiquetasTabla implements IEntity {

	/** serial Version UID. */
	private static final long serialVersionUID = -655618959878351213L;

	/** El atributo o variable id. */
	@EmbeddedId
	private LeeEtiquetasTablaPK id;

	/** El atributo o variable etiqueta. */
	@Column(name = "ETIQUETA")
	private String etiqueta;

}
