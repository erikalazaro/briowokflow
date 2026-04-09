package com.briomax.briobpm.persistence.entity;

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
 * El objetivo de la Class StProceso.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Nov 21, 2023 12:24:43 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "ST_SECCION_NODO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"listOfStDocumentoSeccion"})
@EqualsAndHashCode(callSuper = false, exclude = {"listOfStDocumentoSeccion"})
public class StSeccionNodo implements IEntity {
	
	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable id. */
	@EmbeddedId
	private StSeccionNodoPK id;
	
	/** El atributo o variable descripcion. */
	@Column(name = "ORDEN_PRESENTACION", nullable = false, precision = 4, scale = 0)
	private Integer ordenPresentacion;

	/** El atributo o variable etiqueta. */
	@Column(name = "ETIQUETA", nullable = false, length = 100)
	private String etiqueta;

	/** El atributo o variable botonCrearRenglon. */
	@Column(name = "BOTON_CREAR_RENGLON", length = 2)
	private String botonCrearRenglon;

	/** El atributo o variable etiquetaCrearRenglon. */
	@Column(name = "ETIQUETA_CREAR_RENGLON", length = 20)
	private String etiquetaCrearRenglon;

	/** El atributo o variable botonEliminarRenglon. */
	@Column(name = "BOTON_ELIMINAR_RENGLON", length = 2)
	private String botonEliminarRenglon;

	/** El atributo o variable etiquetaEliminarRenglon. */
	@Column(name = "ETIQUETA_ELIMINAR_RENGLON", length = 20)
	private String etiquetaEliminarRenglon;
	
	/** El atributo o variable descripcion. */
	@Column(name = "REGISTROS_SELECCIONABLES", nullable = false, precision = 4, scale = 0)
	private Integer regsitrosSeleccionables;
	
	/** El atributo o variable stNodoProceso. */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name="CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
		@JoinColumn(name="VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false),
		@JoinColumn(name="CVE_NODO", referencedColumnName = "CVE_NODO", insertable = false, updatable = false),
		@JoinColumn(name="ID_NODO", referencedColumnName = "ID_NODO", insertable = false, updatable = false)
	})
	private StNodoProceso stNodoProceso;
	
	/** El atributo o variable stSeccionProceso. */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name="CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
		@JoinColumn(name="VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false),
		@JoinColumn(name="CVE_SECCION", referencedColumnName = "CVE_SECCION", insertable = false, updatable = false)
	})
	private StSeccionProceso stSeccionProceso;
	
	/** El atributo o variable list of usuario. */
	@OneToMany(mappedBy = "stSeccionNodo", targetEntity = StDocumentoSeccion.class)
	private List<StDocumentoSeccion> listOfStDocumentoSeccion;

}
