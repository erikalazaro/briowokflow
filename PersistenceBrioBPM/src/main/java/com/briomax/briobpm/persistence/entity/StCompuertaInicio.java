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

/**
 * El objetivo de la Class StNodoProceso.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Nov 15, 2023 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "ST_COMPUERTA_INICIO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, exclude = {})

public class StCompuertaInicio implements IEntity {
	
	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private StCompuertaInicioPK id;

	/** El atributo o variable cve Nodo Cierre*/
	@Column(name = "CVE_NODO_CIERRE",nullable = false, length = 40)
	private String cveNodoCierre;
	
	/** El atributo o variable id Nodo Cierre*/
	@Column(name = "ID_NODO_CIERRE",nullable = false,  precision = 5, scale = 0)
	private Integer idNodoCierre;
	
	/** El atributo o variable areaTrabajo. */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name="CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
		@JoinColumn(name="VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false),
		@JoinColumn(name="CVE_NODO_INICIO", referencedColumnName = "CVE_NODO", insertable = false, updatable = false),
		@JoinColumn(name="ID_NODO_INICIO", referencedColumnName = "ID_NODO", insertable = false, updatable = false)
	})
	private StNodoProceso stNodoProcesoInicio;
	
	/** El atributo o variable areaTrabajo. */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name="CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
		@JoinColumn(name="VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false),
		@JoinColumn(name="CVE_NODO_CIERRE", referencedColumnName = "CVE_NODO", insertable = false, updatable = false),
		@JoinColumn(name="ID_NODO_CIERRE", referencedColumnName = "ID_NODO", insertable = false, updatable = false)
	})
	private StNodoProceso stNodoProcesoCierre;
	
}
