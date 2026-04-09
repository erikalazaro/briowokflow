package com.briomax.briobpm.persistence.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
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
@Table(name = "ST_ACCION_SECCION_NODO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {})
@EqualsAndHashCode(callSuper = false, exclude = {})
public class StAccionSeccionNodo implements IEntity {
	
	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable id. */
	@EmbeddedId
	private StAccionNodoProcesoPK id;
	
	/** El atributo o variable descripcion. */
	@Column(name = "ORDEN_ACCION", nullable = false, precision = 4, scale = 0)
	private Integer ordenAccion;

	/** El atributo o variable etiqueta. */
	@Column(name = "ETIQUETA_ACCION", nullable = false, length = 30)
	private String etiquetaAccion;

	/** El atributo o variable botonCrearRenglon. */
	@Column(name = "URL_ACCION", length = 1000)
	private String urlAccion;

	/** El atributo o variable etiquetaCrearRenglon. */
	@Column(name = "FUNCION_ACCION", length = 1000)
	private String funcionAccion;

	/** El atributo o variable botonCrearRenglon. */
	@Column(name = "ATRIBUTOS", length = 1000)
	private String atributos;

	/** El atributo o variable etiquetaCrearRenglon. */
	@Column(name = "VARIABLES", length = 1000)
	private String variables;
	
	/** El atributo o variable stSeccionProceso. */
	/*@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name="CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
		@JoinColumn(name="VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false),
		@JoinColumn(name="CVE_SECCION", referencedColumnName = "CVE_SECCION", insertable = false, updatable = false)
	})
	private StSeccionNodo stSeccionNodo;*/
	
}
