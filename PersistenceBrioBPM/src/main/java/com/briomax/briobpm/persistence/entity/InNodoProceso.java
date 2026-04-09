package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * El objetivo de la Class InFolioNodo.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Nov 07, 2023 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "IN_NODO_PROCESO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"listOfInBitacoraNodo", "listOfFolioMensajeProceso", "listOfInMensajeEnvio", "listOfInMensajeRecepcion"})
@EqualsAndHashCode(callSuper = false, exclude = {"listOfInBitacoraNodo", "listOfFolioMensajeProceso", "listOfInMensajeEnvio", "listOfInMensajeRecepcion"})
public class InNodoProceso implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 3008509815141573530L;
	
	/** El atributo o variable id*/
	@EmbeddedId
	private  InNodoProcesoPK id;

	/** El atributo o variable estado*/
	@Column(name = "ESTADO", length = 40)
	private String estado;

	/** El atributo o variable estado*/
	@Column(name = "FECHA_CREACION", nullable = false)
	private Date fechaCreacion;
	
	/** El atributo o variable estado*/
	@Column(name = "ORIGEN", length = 20, nullable = false)
	private String origen;
	
	/** El atributo o variable estado*/
	@Column(name = "CVE_PROCESO_ORIGEN", length = 40)
	private String cveProcesoOrigen;
	
	/** El atributo o variable estado*/
	@Column(name = "VERSION_ORIGEN", length = 40)
	private BigDecimal versionOrigen;
	
	/** El atributo o variable estado*/
	@Column(name = "CVE_INSTANCIA_ORIGEN", length = 40)
	private String cveInstanciaOrigen;
	
	/** El atributo o variable estado*/
	@Column(name = "CVE_NODO_ORIGEN", length = 40)
	private String cveNodoOrigen;
	
	/** El atributo o variable estado*/
	@Column(name = "ID_NODO_ORIGEN", precision = 5, scale = 0)
	private Integer idNodoOrigen;
	
	/** El atributo o variable estado*/
	@Column(name = "SECUENCIA_NODO_ORIGEN", length = 40)
	private Integer secuenciaNodoOrigen;
		
	/** El atributo o variable estado*/
	@Column(name = "ROL_CREADOR", length = 40, nullable = false)
	private String rolCreador;
	
	/** El atributo o variable estado*/
	@Column(name = "CVE_ENTIDAD_CREADORA", length = 40)
	private String cveEntidadCreadora;
	
	/** El atributo o variable estado*/
	@Column(name = "CVE_LOCALIDAD_CREADORA", length = 40)
	private String cveLocalidadCreadora;
		
	/** El atributo o variable estado*/
	@Column(name = "USUARIO_CREADOR", length = 40, nullable = false)
	private String usuarioCreador;
	
	/** El atributo o variable estado*/
	@Column(name = "ROL_BLOQUEA", length = 40)
	private String rolBloquea;
	
	/** El atributo o variable Clave Entidad Bloquea*/
	@Column(name = "CVE_ENTIDAD_BLOQUEA", length = 40)
	private String cveEntidadBloquea;
	
	/** El atributo o variable Clave Localidad Bloquea*/
	@Column(name = "CVE_LOCALIDAD_BLOQUEA", length = 40)
	private String cveLocalidadBloquea;
		
	/** El atributo o variable estado*/
	@Column(name = "USUARIO_BLOQUEA", length = 40)
	private String usuarioBloquea;
	
	/** El atributo o variable estado*/
	@Column(name = "FECHA_BLOQUEA")
	private Date fechaBloquea;
	
	/** El atributo o variable estado*/
	@Column(name = "FECHA_LIMITE")
	private Date fechaLimite;
	
	/** El atributo o variable estado*/
	@Column(name = "FECHA_FIN_ESPERA")
	private Date fechaFinEspera;
	
	/** El atributo o variable estado*/
	@Column(name = "FECHA_ESTADO_ACTUAL", nullable = false)
	private Date fechaEstadoActual;
	
	/** El atributo o variable estado*/
	@Column(name = "COMENTARIO", length = 40)
	private String comentario;
	
	/** El atributo o variable folio*/
	@Column(name = "PRIORIDAD", precision = 2, scale = 0)
	private Integer prioridad;
	
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name="CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
		@JoinColumn(name="VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false),
		@JoinColumn(name="CVE_INSTANCIA", referencedColumnName = "CVE_INSTANCIA", insertable = false, updatable = false) })
	private InProceso inProceso;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name="CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
		@JoinColumn(name="VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false),
		@JoinColumn(name="CVE_NODO", referencedColumnName = "CVE_NODO", insertable = false, updatable = false),
		@JoinColumn(name="ID_NODO", referencedColumnName = "ID_NODO", insertable = false, updatable = false) })
	private StNodoProceso stNodoProceso;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name="CVE_PROCESO_ORIGEN", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
		@JoinColumn(name="VERSION_ORIGEN", referencedColumnName = "VERSION", insertable = false, updatable = false),
		@JoinColumn(name="CVE_INSTANCIA_ORIGEN", referencedColumnName = "CVE_INSTANCIA", insertable = false, updatable = false),
		@JoinColumn(name="CVE_NODO_ORIGEN", referencedColumnName = "CVE_NODO", insertable = false, updatable = false),
		@JoinColumn(name="ID_NODO_ORIGEN", referencedColumnName = "ID_NODO", insertable = false, updatable = false),
		@JoinColumn(name="SECUENCIA_NODO_ORIGEN", referencedColumnName = "SECUENCIA_NODO", insertable = false, updatable = false) })
	private InNodoProceso inNodoProceso;
	
	/** El atributo o variable list of InNodoProceso. */
	@OneToMany(mappedBy = "inNodoProceso", targetEntity = InNodoProceso.class)
	private List<InNodoProceso> listOfInNodoProceso;
	
	/** El atributo o variable list of InBitacoraNodo. */
	@OneToMany(mappedBy = "inNodoProceso", targetEntity = InBitacoraNodo.class)
	private List<InBitacoraNodo> listOfInBitacoraNodo;
	
	/** El atributo o variable list of FolioMensajeProceso. */
	@OneToMany(mappedBy = "inNodoProceso", targetEntity = FolioMensajeProceso.class)
	private List<FolioMensajeProceso> listOfFolioMensajeProceso;
	
	/** El atributo o variable list of InMensajeEnvio. */
	@OneToMany(mappedBy = "inNodoProceso", targetEntity = InMensajeEnvio.class)
	private List<InMensajeEnvio> listOfInMensajeEnvio;

	/** El atributo o variable list of InMensajeRecepcion. */
	@OneToMany(mappedBy = "inNodoProceso", targetEntity = InMensajeRecepcion.class)
	private List<InMensajeRecepcion> listOfInMensajeRecepcion;
}

