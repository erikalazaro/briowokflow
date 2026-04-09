package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;
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
 * El objetivo de la Class StNodoProceso.java es ...
 * 
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Nov 15, 2023 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "ST_NODO_PROCESO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"listOfInFolioNodo", "listOfStSeccionNodo", "listOfStNodoSiguienteSiguiente",  "listOfStCompuertaInicioInicio",
		"listOfStCompuertaInicioCierre", "listOfInNodoProceso", "listOfCorreoProceso", "listOfStMensajeEnvio", "listOfStMensajeRecepcion", "listOfStBotonNodo"})
@EqualsAndHashCode(callSuper = false, exclude = { "listOfInFolioNodo", "listOfStSeccionNodo", "listOfStNodoSiguienteSiguiente", "listOfStCompuertaInicioInicio",
		"listOfStCompuertaInicioCierre", "listOfInNodoProceso", "listOfCorreoProceso", "listOfStMensajeEnvio", "listOfStMensajeRecepcion", "listOfStBotonNodo"})
public class StNodoProceso implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 3008509815141573530L;

	/** El atributo o variable id. */
	@EmbeddedId
	private StNodoProcesoPK id;

	/** El atributo o variable cve Instancia */
	@Column(name = "SINCRONO", nullable = false, length = 2)
	private String sincrono;

	/** El atributo o variable cve Nodo */
	@Column(name = "DESCRIPCION", nullable = false, length = 100)
	private String descripcion;

	/** El atributo o variable cve Instancia */
	@Column(name = "CVE_REGLA_CICLICIDAD", length = 40)
	private String cveReglaCiclicidad;

	/** El atributo o variable cve Nodo */
	@Column(name = "INSTANCIAS_CICLICIDAD", precision = 5, scale = 0)
	private Integer instanciasCiclicidad;

	/** El atributo o variable cve Instancia */
	@Column(name = "CVE_LOCALIDAD_NIVEL_SERVICIO", length = 40)
	private String cveLocalidadNivelServicio;

	/** El atributo o variable cve Nodo */
	@Column(name = "PLAZO_NIVEL_SERVICIO", precision = 5, scale = 0)
	private Integer plazoNivelServicio;

	/** El atributo o variable cve Instancia */
	@Column(name = "BASE_HORARIO_NIVEL_SER", length = 2)
	private String baseHorarioNivelSer;

	/** El atributo o variable cve Nodo */
	@Column(name = "BASE_CALCULO_NIVEL_SER", length = 20)
	private String baseCalculoNivelSer;

	/** El atributo o variable cve Instancia */
	@Column(name = "CVE_LOCALIDAD_TIEMPO_ESPERA", length = 40)
	private String cveLocalidadTiempoEspera;

	/** El atributo o variable cve Nodo */
	@Column(name = "TIEMPO_ESPERA", precision = 5, scale = 0)
	private Integer tiempoEspera;

	/** El atributo o variable cve Instancia */
	@Column(name = "UNIDAD_TIEMPO_ESPERA", length = 7)
	private String unidadTiempoEspera;

	/** El atributo o variable cve Nodo */
	@Column(name = "AVANCE_ACUMULADO", precision = 5, scale = 2)
	private BigDecimal avanceAcumulado;

	/** El atributo o variable cve Instancia */
	@Column(name = "SITUACION", length = 12)
	private String situacion;

	/** El atributo o variable cve Nodo */
	@Column(name = "CVE_AREA_TRABAJO", length = 40)
	private String cveAreaTrabajo;

	/** El atributo o variable cve Instancia */
	@Column(name = "ETIQUETA_BOTON", length = 40)
	private String etiquetaBoton;

	/** El atributo o variable cve Nodo */
	@Column(name = "BOTON_INICIO_PROCESO", length = 40)
	private String botonInicioProceso;

	/** El atributo o variable cve Instancia */
	@Column(name = "ETIQUETA_BOTON_INICIO", length = 40)
	private String etiquetaBotonInicio;

	/** El atributo o variable cve Nodo */
	@Column(name = "BOTON_EJECUTAR", length = 40)
	private String botonEjecutar;

	/** El atributo o variable cve Instancia */
	@Column(name = "ETIQUETA_BOTON_EJECUTAR", length = 50)
	private String etiquetaBotonEjecutar;

	/** El atributo o variable cve Nodo */
	@Column(name = "BOTON_TOMAR", length = 2)
	private String botonTomar;

	/** El atributo o variable cve Instancia */
	@Column(name = "ETIQUETA_BOTON_TOMAR", length = 50)
	private String etiquetaBotonTomar;

	/** El atributo o variable cve Nodo */
	@Column(name = "BOTON_LIBERAR", length = 2)
	private String botonLiberar;

	/** El atributo o variable cve Instancia */
	@Column(name = "ETIQUETA_BOTON_LIBERAR", length = 50)
	private String etiquetaBotonLiberar;

	/** El atributo o variable cve Nodo */
	@Column(name = "BOTON_TERMINAR", length = 2)
	private String botonTerminar;

	/** El atributo o variable cve Nodo */
	@Column(name = "ETIQUETA_BOTON_TERMINAR", length = 50)
	private String etiquetaBotonTerminar;

	/** El atributo o variable cve Instancia */
	@Column(name = "BOTON_CANCELAR", length = 2)
	private String botonCancelar;

	/** El atributo o variable cve Nodo */
	@Column(name = "ETIQUETA_BOTON_CANCELAR", length = 50)
	private String etiquetaBotonCancelar;

	/** El atributo o variable cve Instancia */
	@Column(name = "BOTON_CONSULTAR", length = 2)
	private String botonConsultar;

	/** El atributo o variable cve Nodo */
	@Column(name = "ETIQUETA_BOTON_CONSULTAR", length = 50)
	private String etiquetaBotonConsultar;

	/** El atributo o variable cve Instancia */
	@Column(name = "TIPO_ACCESO", length = 20)
	private String tipoAcceso;

	/** El atributo o variable cve Nodo */
	@Column(name = "TIPO_TEMPORIZACION", length = 40)
	private String tipoTemporizacion;

	/** El atributo o variable cve Instancia */
	@Column(name = "BOTON_BITACORA", length = 2)
	private String botonBitacora;

	/** El atributo o variable cve Nodo */
	@Column(name = "ETIQUETA_BOTON_BITACORA", length = 50)
	private String etiquetaBotonBitacora;
	
	/** El atributo o variable cve Instancia */
	@Column(name = "BOTON_GUARDAR", length = 2)
	private String botonGuardar;

	/** El atributo o variable cve Nodo */
	@Column(name = "ETIQUETA_BOTON_GUARDAR", length = 50)
	private String etiquetaBotonGuardar;

	/** El atributo o variable cve Instancia */
	@Column(name = "COLOR_RGB_RELLENO", length = 20)
	private String colorRgbRelleno;

	/** El atributo o variable cve Nodo */
	@Column(name = "COLOR_RGB_CONTORNO", length = 20)
	private String colorRgbContorno;

	/** El atributo o variable cve Nodo */
	@Column(name = "COLOR_RGB_SOMBRA", length = 20)
	private String colorRgbSombra;
	
	/** El atributo o variable icono nodo */
	@Column(name = "ICONO_NODO")
	private byte[] iconoNodo;
	
	/** El atributo o variable cve Nodo */
	@Column(name = "CVE_AREA_TRABAJO_TARJETA", length = 40)
	private String cveAreaTrabajoTarjeta;
	
	/** El atributo o variable condicion Inicializacion*/
	@Column(name = "CONDICION_INICIALIZACION_VAR", length = 1000)
	private String condicionInicializacionVar;
	
	/** El atributo o variable inicializar Variables*/
	@Column(name = "INICIALIZAR_VARIABLES", length = 4)
	private String inicializarVariables;

	/** El atributo o variable stProceso. */
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
			@JoinColumn(name = "CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
			@JoinColumn(name = "VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false) })
	private StProceso stProceso;

	/** El atributo o variable tipoNodo. */
	@ManyToOne
	@JoinColumn(name = "CVE_NODO", referencedColumnName = "CVE_NODO", insertable = false, updatable = false)
	private TipoNodo tipoNodo;

	/** El atributo o variable areaTrabajo. */
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
			@JoinColumn(name = "CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
			@JoinColumn(name = "VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false),
			@JoinColumn(name = "CVE_AREA_TRABAJO", referencedColumnName = "CVE_AREA_TRABAJO", insertable = false, updatable = false) })
	private AreaTrabajo areaTrabajo;

	/** El atributo o variable localidadEntidad. */
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
			@JoinColumn(name = "CVE_LOCALIDAD_NIVEL_SERVICIO", referencedColumnName = "CVE_LOCALIDAD", insertable = false, updatable = false) })
	private LocalidadEntidad localidadEntidadServicio;

	/** El atributo o variable localidadEntidad. */
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
			@JoinColumn(name = "CVE_LOCALIDAD_TIEMPO_ESPERA", referencedColumnName = "CVE_LOCALIDAD", insertable = false, updatable = false) })
	private LocalidadEntidad localidadEntidadEspera;

	/** El atributo o variable list of Folio Nodo. */
	@OneToMany(mappedBy = "stNodoProceso", targetEntity = InFolioNodo.class)
	private List<InFolioNodo> listOfInFolioNodo;

	/** El atributo o variable list of StSeccionNodo. */
	@OneToMany(mappedBy = "stNodoProceso", targetEntity = StSeccionNodo.class)
	private List<StSeccionNodo> listOfStSeccionNodo;

	/** El atributo o variable list of StNodoSiguiente. */
	@OneToMany(mappedBy = "stNodoProceso", targetEntity = StNodoSiguiente.class)
	private List<StNodoSiguiente> listOfStNodoSiguiente;

	/** El atributo o variable list of listOfStNodoSiguienteSiguiente. */
	@OneToMany(mappedBy = "stNodoProcesoSiguiente", targetEntity = StNodoSiguiente.class)
	private List<StNodoSiguiente> listOfStNodoSiguienteSiguiente; //revisar cardinalidad

	/** El atributo o variable list of StCompuertaInicio. */
	@OneToMany(mappedBy = "stNodoProcesoInicio", targetEntity = StCompuertaInicio.class)
	private List<StCompuertaInicio> listOfStCompuertaInicioInicio;

	/** El atributo o variable list of StCompuertaInicio. */
	@OneToMany(mappedBy = "stNodoProcesoCierre", targetEntity = StCompuertaInicio.class)
	private List<StCompuertaInicio> listOfStCompuertaInicioCierre;
	
	/** El atributo o variable list of InNodoProceso. */
	@OneToMany(mappedBy = "stNodoProceso", targetEntity = InNodoProceso.class)
	private List<InNodoProceso> listOfInNodoProceso;
	
	/** El atributo o variable list of CorreoProceso. */
	@OneToMany(mappedBy = "stNodoProceso", targetEntity = CorreoProceso.class)
	private List<CorreoProceso> listOfCorreoProceso;
	
	/** El atributo o variable list of StMensajeEnvio. */
	@OneToMany(mappedBy = "stNodoProceso", targetEntity = StMensajeEnvio.class)
	private List<StMensajeEnvio> listOfStMensajeEnvio;

	/** El atributo o variable list of StMensajeRecepcion. */
	@OneToMany(mappedBy = "stNodoProceso", targetEntity = StMensajeRecepcion.class)
	private List<StMensajeRecepcion> listOfStMensajeRecepcion;
	
	/** El atributo o variable list of StMensajeRecepcion. */
	@OneToMany(mappedBy = "stNodoProceso", targetEntity = StBotonNodo.class)
	private List<StBotonNodo> listOfStBotonNodo;
	

}
