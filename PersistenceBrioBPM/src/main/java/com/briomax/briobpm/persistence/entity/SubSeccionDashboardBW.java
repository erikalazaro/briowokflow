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
 * El objetivo de la Class SeccionDashboardBW.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Oct 30, 2024 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */

@Entity
@Table(name = "SUB_SECCION_DASHBOARD_BW")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"listOfSubSubSeccionDashboardBW", "listOfBotonSubSeccionBW"})
@EqualsAndHashCode(callSuper = false, exclude = {"listOfSubSubSeccionDashboardBW", "listOfBotonSubSeccionBW"})
public class SubSeccionDashboardBW implements IEntity {
	
	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable id*/
	@EmbeddedId
	private SubSeccionDashboardBWPK id;
	
	/** El atributo o dashboardHabilitado. */	
	@Column(name = "ORDEN_SUB_SECCION", nullable = false, length = 3)
	private Integer ordenSubSeccion;	
	
	/** El atributo o descripcion. */	
	@Column(name = "DESCRIPCION", nullable = false, length = 100)
	private String descripcion;	
	
	/** El atributo o titulo. */	
	@Column(name = "TITULO", length = 40)
	private String titulo;
	
	/** El atributo o variable entidad. */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name="CVE_DASHBOARD", referencedColumnName = "CVE_DASHBOARD", insertable = false, updatable = false),
		@JoinColumn(name="SECUENCIA_SECCION", referencedColumnName = "SECUENCIA_SECCION", insertable = false, updatable = false)
	})
	private SeccionDashboardBW seccionDashboardBW;
	
	/** El atributo o variable list of SubSeccionDashboardBW. */
	@OneToMany(mappedBy = "subSeccionDashboardBW", targetEntity = SubSubSeccionDashboardBW.class)
	private List<SubSubSeccionDashboardBW> listOfSubSubSeccionDashboardBW;
	
	/** El atributo o variable list of SubSeccionDashboardBW. */
	@OneToMany(mappedBy = "subSeccionDashboardBW", targetEntity = BotonSubSeccionBW.class)
	private List<BotonSubSeccionBW> listOfBotonSubSeccionBW;
}
