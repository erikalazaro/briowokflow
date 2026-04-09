package com.briomax.briobpm.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * El objetivo de la Class VwDatoActividad.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Feb 13, 2024 12:24:43 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "VW_DATO_ACTIVIDAD")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {})
@EqualsAndHashCode(callSuper = false, exclude = {})
public class VwDatoActividad implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;

	/** El atributo o variable id. */
	@EmbeddedId
	private VwDatoActividadPK id;

	/** La descripción de la actividad. */
	@Column(name = "DESCRIPCION_ACTIVIDAD")
	private String descripcionActividad;

	/** La descripción del proceso. */
	@Column(name = "DESCRIPCION_PROCESO")
	private String descripcionProceso;

	/** El concepto del proceso. */
	@Column(name = "CONCEPTO_PROCESO")
	private String conceptoProceso;

	/** La fecha de creación del proceso. */
	@Column(name = "FECHA_CREACION_PROCESO")
	private Date fechaCreacionProceso;

	/** El origen del proceso. */
	@Column(name = "ORIGEN_PROCESO")
	private String origenProceso;

	/** La entidad creadora del proceso. */
	@Column(name = "ENTIDAD_CREADORA_PROCESO")
	private String entidadCreadoraProceso;

	/** La localidad creadora del proceso. */
	@Column(name = "LOCALIDAD_CREADORA_PROCESO")
	private String localidadCreadoraProceso;

	/** La clave del rol creador del proceso. */
	@Column(name = "CVE_ROL_CREADOR_PROCESO")
	private String cveRolCreadorProceso;

	/** El rol creador del proceso. */
	@Column(name = "ROL_CREADOR_PROCESO")
	private String rolCreadorProceso;

	/** La clave del usuario creador del proceso. */
	@Column(name = "CVE_USUARIO_CREADOR_PROCESO")
	private String cveUsuarioCreadorProceso;

	/** El usuario creador del proceso. */
	@Column(name = "USUARIO_CREADOR_PROCESO")
	private String usuarioCreadorProceso;

	/** El estado de la actividad. */
	@Column(name = "ESTADO_ACTIVIDAD")
	private String estadoActividad;

	/** La fecha de creación de la actividad. */
	@Column(name = "FECHA_CREACION_ACTIVIDAD")
	private Date fechaCreacionActividad;

	/** La fecha y hora de creación de la actividad. */
	@Column(name = "FECHA_HORA_CREACION_ACTIVIDAD")
	private String fechaHoraCreacionActividad;

	/** La fecha y hora del estado actual de la actividad. */
	@Column(name = "FECHA_HORA_ESTADO_ACTUAL")
	private String fechaHoraEstadoActual;

	/** El origen de la actividad. */
	@Column(name = "ORIGEN_ACTIVIDAD")
	private String origenActividad;

	/** La clave del proceso origen de la actividad. */
	@Column(name = "CVE_PROCESO_ORIGEN_ACTIVIDAD")
	private String cveProcesoOrigenActividad;

	/** La versión origen de la actividad. */
	@Column(name = "VERSION_ORIGEN_ACTIVIDAD")
	private Integer versionOrigenActividad;

	/** La descripción del proceso origen de la actividad. */
	@Column(name = "DESCRIPCION_PROCESO_ORIGEN")
	private String descripcionProcesoOrigen;

	/** La clave de la instancia origen de la actividad. */
	@Column(name = "CVE_INSTANCIA_ORIGEN_ACTIVIDAD")
	private String cveInstanciaOrigenActividad;

	/** La clave del nodo origen de la actividad. */
	@Column(name = "CVE_NODO_ORIGEN")
	private String cveNodoOrigen;

	/** El ID del nodo origen de la actividad. */
	@Column(name = "ID_NODO_ORIGEN")
	private String idNodoOrigen;

	/** La descripción de la actividad anterior. */
	@Column(name = "DESCRIPCION_ACT_ANTERIOR")
	private String descripcionActAnterior;

	/** La clave del rol creador de la actividad. */
	@Column(name = "CVE_ROL_CREADOR_ACTIVIDAD")
	private String cveRolCreadorActividad;

	/** El rol creador de la actividad. */
	@Column(name = "ROL_CREADOR_ACTIVIDAD")
	private String rolCreadorActividad;

	/** La clave del usuario creador de la actividad. */
	@Column(name = "CVE_USUARIO_CREADOR_ACTIVIDAD")
	private String cveUsuarioCreadorActividad;

	/** El usuario creador de la actividad. */
	@Column(name = "USUARIO_CREADOR_ACTIVIDAD")
	private String usuarioCreadorActividad;

	/** La entidad que bloquea. */
	@Column(name = "ENTIDAD_BLOQUEA")
	private String entidadBloquea;

	/** La localidad que bloquea. */
	@Column(name = "LOCALIDAD_BLOQUEA")
	private String localidadBloquea;

	/** La clave del rol que bloquea. */
	@Column(name = "CVE_ROL_BLOQUEA")
	private String cveRolBloquea;

	/** El rol que bloquea. */
	@Column(name = "ROL_BLOQUEA")
	private String rolBloquea;

	/** El usuario que bloquea. */
	@Column(name = "USUARIO_BLOQUEA")
	private String usuarioBloquea;

	/** El nombre del usuario que bloquea. */
	@Column(name = "NOMBRE_USUARIO_BLOQUEA")
	private String nombreUsuarioBloquea;

	/** La fecha de bloqueo. */
	@Column(name = "FECHA_BLOQUEO")
	private Date fechaBloqueo;

	/** La fecha límite. */
	@Column(name = "FECHA_LIMITE")
	private Date fechaLimite;

	/** La fecha y hora límite. */
	@Column(name = "FECHA_HORA_LIMITE")
	private String fechaHoraLimite;

	/** La fecha de fin de espera. */
	@Column(name = "FECHA_FIN_ESPERA")
	private Date fechaFinEspera;

	/** La fecha y hora de fin de espera. */
	@Column(name = "FECHA_HORA_FIN_ESPERA")
	private String fechaHoraFinEspera;
	
	/** La hora del estado actual. */
	@Column(name = "FECHA_ESTADO_ACTUAL")
	private Date fechoEstadoActual;

	/** La hora del estado actual. */
	@Column(name = "HORA_ESTADO_ACTUAL")
	private Date horaEstadoActual;

	/** El comentario. */
	@Column(name = "COMENTARIO")
	private String comentario;

}
