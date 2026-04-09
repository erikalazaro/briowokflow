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

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * El objetivo de la Class LeeMensajesReglas.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion May 24, 2021 12:16:48 PM Modificaciones:
 * @since JDK 1.8
 */
/*
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(name = "leeMensajesReglas",
		procedureName = "SP_LEE_MENSAJES_REGLAS",
		parameters = {
			@StoredProcedureParameter(name = "CH_CVE_USUARIO", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_ENTIDAD", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_LOCALIDAD", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_IDIOMA", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_ROL", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_PROCESO", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "C_VERSION", type = BigDecimal.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_NODO", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "I_ID_NODO", type = Integer.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_TIPO_EXCEPCION", type = String.class, mode = ParameterMode.OUT),
			@StoredProcedureParameter(name = "CH_MENSAJE", type = String.class, mode = ParameterMode.OUT)},
		resultClasses = LeeMensajesReglas.class
	)
})*/
@Entity
@Getter
@Setter
@ToString
@Builder
public class LeeMensajesReglas implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = -7620613306467114629L;

	/** El atributo o variable numero mensaje. */
	@Id
	@Column(name = "NUMERO_MENSAJE")
	private Integer numeroMensaje;

	/** El atributo o variable cve idioma. */
	@Column(name = "CVE_IDIOMA")
	private String cveIdioma;

	/** El atributo o variable mensaje. */
	@Column(name = "MENSAJE")
	private String mensaje;

}
