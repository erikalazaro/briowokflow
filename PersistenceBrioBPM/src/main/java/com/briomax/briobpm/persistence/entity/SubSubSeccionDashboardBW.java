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
 * El objetivo de la Class SubSubSeccionDashboardBW.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Oct 30, 2024 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */

@Entity
@Table(name = "SUB_SUB_SECCION_DASHBOARD_BW")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"listOfDatoDashboardBW", "listOfBotonSubSubSeccionBW"})
@EqualsAndHashCode(callSuper = false, exclude = {"listOfDatoDashboardBW" ,"listOfBotonSubSubSeccionBW"})
public class SubSubSeccionDashboardBW implements IEntity {
	
	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable id*/
	@EmbeddedId
	private SubSubSeccionDashboardBWPK id;
	
	/** El atributo o dashboardHabilitado. */	
	@Column(name = "ORDEN_SUB_SUB_SECCION", nullable = false, length = 3)
	private Integer ordenSubSubSeccion;	
	
	/** El atributo o descripcion. */	
	@Column(name = "DESCRIPCION", nullable = false, length = 100)
	private String descripcion;	
	
	/** El atributo o titulo. */	
	@Column(name = "TITULO", length = 40)
	private String titulo;	
	
	/** El atributo o tituloPresentacion. */	
	@Column(name = "TIPO_PRESENTACION", nullable = false, length = 40)
	private String tipoPresentacion;	
	
	/** El atributo o etiquetaEjeX. */	
	@Column(name = "ETIQUETA_EJE_X", length = 40)
	private String etiquetaEjeX;	
	
	/** El atributo o etiquetaEjeY1. */	
	@Column(name = "ETIQUETA_EJE_Y1", length = 40)
	private String etiquetaEjeY1;	
	
	/** El atributo o etiquetaEjeY2. */	
	@Column(name = "ETIQUETA_EJE_Y2", length = 40)
	private String etiquetaEjeY2;	
	
	/** El atributo o variable subSeccionDashboardBW. */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name="CVE_DASHBOARD", referencedColumnName = "CVE_DASHBOARD", insertable = false, updatable = false),
		@JoinColumn(name="SECUENCIA_SECCION", referencedColumnName = "SECUENCIA_SECCION", insertable = false, updatable = false),
		@JoinColumn(name="SECUENCIA_SUB_SECCION", referencedColumnName = "SECUENCIA_SUB_SECCION", insertable = false, updatable = false)
	})
	private SubSeccionDashboardBW subSeccionDashboardBW;
	
	/** El atributo o variable list of DatoDashboardBW. */
	@OneToMany(mappedBy = "subSubSeccionDashboardBW", targetEntity = DatoDashboardBW.class)
	private List<DatoDashboardBW> listOfDatoDashboardBW;
	
	/** El atributo o variable list of DatoDashboardBW. */
	@OneToMany(mappedBy = "subSubSeccionDashboardBW", targetEntity = BotonSubSubSeccionBW.class)
	private List<BotonSubSubSeccionBW> listOfBotonSubSubSeccionBW;
	
}
