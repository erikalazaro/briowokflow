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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class LeeCorreosPorEnviar.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Apr 13, 2020 10:15:06 AM Modificaciones:
 * @since JDK 1.8
 */
/*@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(name = "obtenerCorreosPorEnviar",
		procedureName = "SP_LEE_CORREOS_POR_ENVIAR",
		parameters = {
			@StoredProcedureParameter(name = "CH_TIPO_EXCEPCION", type = String.class, mode = ParameterMode.OUT),
			@StoredProcedureParameter(name = "CH_MENSAJE", type = String.class, mode = ParameterMode.OUT)},
		resultClasses = LeeCorreosPorEnviar.class
	)
})*/
@Entity
@Getter
@Setter
@ToString 
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeeCorreosPorEnviar implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 8087033030874646215L;

	/** El atributo o variable id. */
	@EmbeddedId
	private LeeCorreosPorEnviarPK id;

	/** El atributo o variable para. */
	@Column(name = "PARA")
	private String para;
	
	/** El atributo o variable con copia para. */
	@Column(name = "CON_COPIA_PARA")
	private String conCopiaPara;
	
	/** El atributo o variable asunto. */
	@Column(name = "ASUNTO")
	private String asunto;
	
	/** El atributo o variable mensaje. */
	@Column(name = "MENSAJE")
	private String mensaje;

}
