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
@Table(name = "ST_VARIABLE_SECCION")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {})
@EqualsAndHashCode(callSuper = false)
public class StVariableSeccion implements IEntity {
	
	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable id. */
	@EmbeddedId
	private StVariableSeccionPK id;
	
	/** El atributo o variable version. */
	@Column(name = "NUMERO_RENGLON", nullable = false, precision = 4, scale = 0)
	private Integer numeroRenglon;
	
	/** El atributo o variable cve entidad. */
	@Column(name = "REQUERIDA", nullable = false, length = 10)
	private String requerida;

	/** El atributo o variable cve proceso. */
	@Column(name = "CVE_VALIDACION", length = 40)
	private String cveVlidacion;

	/** El atributo o variable cve entidad. */
	@Column(name = "SOLO_CONSULTA", length = 2)
	private String soloConsulta;

	/** El atributo o variable cve proceso. */
	@Column(name = "ANCHO_COLUMNA", precision = 3, scale = 0)
	private Integer anchoColumna;

	/** El atributo o variable cve entidad. */
	@Column(name = "ENVIO_GRABAR", length = 2)
	private String envioGrabar;

	/** El atributo o variable cve proceso. */
	@Column(name = "VISIBLE", length = 2)
	private String visible;
	
	/** El atributo o variable stSeccionNodo. */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name="CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
		@JoinColumn(name="VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false),
		@JoinColumn(name="CVE_NODO", referencedColumnName = "CVE_NODO", insertable = false, updatable = false),
		@JoinColumn(name="ID_NODO", referencedColumnName = "ID_NODO", insertable = false, updatable = false),
		@JoinColumn(name="CVE_SECCION", referencedColumnName = "CVE_SECCION", insertable = false, updatable = false)
	})
	private StSeccionNodo stSeccionNodo;

	/** El atributo o variable stVariableProceso. */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name="CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
		@JoinColumn(name="VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false),
		@JoinColumn(name="CVE_VARIABLE", referencedColumnName = "CVE_VARIABLE", insertable = false, updatable = false)
	})
	private StVariableProceso stVariableProceso;
	
	/** El atributo o variable StReglaValidacionProceso. */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name="CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
		@JoinColumn(name="VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false),
		@JoinColumn(name="CVE_VALIDACION", referencedColumnName = "CVE_VALIDACION", insertable = false, updatable = false)
	})
	private StReglaValidacionProceso stReglaValidacionProceso;
}
