/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * El objetivo de la Class ResetPasswordToken.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion 10/01/2026 Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "RESET_PASSWORD_TOKEN")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {})
@EqualsAndHashCode(callSuper = false, exclude = {})
public class ResetPasswordToken implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = -4183674351860382471L;

	/** El atributo o variable id. */
	@EmbeddedId
	private ResetPasswordTokenPK id;

	/** El atributo o variable password. */
	@Column(name = "TOKEN_HASH", nullable = false, length = 512)
	private String tokenHash;

	/** El atributo o variable password. */
	@Column(name = "TIPO_ACCION", nullable = false, length = 50)
	private String tipoAccion;
	
	/** El atributo o variable fecha password. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_EXPIRACION", nullable = false)
	private Date fechaExpiracion;
	
	/** El atributo o variable fecha password. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_CREACION", nullable = false)
	private Date fechaCreacion;

	/** El atributo o variable situacion. */
	@Column(name = "USUARIO_INACTIVO", nullable = false, length = 1)
	private Integer usaruiInactivo;

	/** El atributo o variable correo electronico. */
	@Column(name = "CORREO_ELECTRONICO", nullable = false, length = 150)
	private String correoElectronico;
	
}
