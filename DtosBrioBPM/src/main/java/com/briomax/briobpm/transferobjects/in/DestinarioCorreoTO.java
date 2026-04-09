package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class DatosAreaTrabajoTO.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion 12 Mar 2024 12:34:39 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "DestinarioCorreoTO", description = "Correo Destinatario TO.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DestinarioCorreoTO implements Serializable {

	/** serial Version UID. */
	private static final long serialVersionUID = 1L;

	/** Clave de la entidad. */
	@Schema(name = "cveEntidad", description = "Clave de la Entidad.", example = "12345", implementation = String.class)
	private String cveEntidad;

	/** Clave del proceso. */
	@Schema(name = "cveProceso", description = "Clave del Proceso.", example = "12345", implementation = String.class)
	private String cveProceso;

	/** Versión. */
	@Schema(name = "version", description = "Versión.", example = "1.00", implementation = BigDecimal.class)
	private BigDecimal version;

	/** Clave del evento de correo. */
	@Schema(name = "cveEventoCorreo", description = "Clave del Evento de Correo.", example = "12345", implementation = String.class)
	private String cveEventoCorreo;

	/** Secuencia del correo. */
	@Schema(name = "secuenciaCorreo", description = "Secuencia del Correo.", example = "1", implementation = Integer.class)
	private Integer secuenciaCorreo;

	/** Grupo de correo. */
	@Schema(name = "grupoCorreo", description = "Grupo de Correo.", example = "grupo1", implementation = String.class)
	private String grupoCorreo;

	/** Secuencia del destinatario. */
	@Schema(name = "secuenciaDestinatario", description = "Secuencia del Destinatario.", example = "1", implementation = Integer.class)
	private Integer secuenciaDestinatario;

	/** Tipo de destinatario. */
	@Schema(name = "tipoDestinatario", description = "Tipo de Destinatario.", example = "tipo1", implementation = String.class)
	private String tipoDestinatario;

	/** Uso de la clave de usuario creador. */
	@Schema(name = "usoCveUsuarioCreador", description = "Uso de la Clave de Usuario Creador.", example = "uso1", implementation = String.class)
	private String usoCveUsuarioCreador;

	/** Clave del rol. */
	@Schema(name = "cveRol", description = "Clave del Rol.", example = "rol1", implementation = String.class)
	private String cveRol;

	/** Clave del usuario. */
	@Schema(name = "cveUsuario", description = "Clave del Usuario.", example = "usuario1", implementation = String.class)
	private String cveUsuario;

}
