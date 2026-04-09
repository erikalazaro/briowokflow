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
@Table(name = "SECCION_DASHBOARD_BW")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"listOfSubSeccionDashboardBW", "listOfQueryServicioBotonBW"})
@EqualsAndHashCode(callSuper = false, exclude = {"listOfSubSeccionDashboardBW", "listOfQueryServicioBotonBW"})
public class SeccionDashboardBW implements IEntity {
	
	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable id*/
	@EmbeddedId
	private SeccionDashboardBWPK id;
	
	/** El atributo o dashboardHabilitado. */	
	@Column(name = "ORDEN_SECCION", nullable = false, length = 3)
	private Integer ordenSeccion;	
	
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
		@JoinColumn(name="CVE_DASHBOARD", referencedColumnName = "CVE_DASHBOARD", insertable = false, updatable = false)
	})
	private DashboardBW dashboardBW;
	
	/** El atributo o variable list of SubSeccionDashboardBW. */
	@OneToMany(mappedBy = "seccionDashboardBW", targetEntity = SubSeccionDashboardBW.class)
	private List<SubSeccionDashboardBW> listOfSubSeccionDashboardBW;
	
	/** El atributo o variable list of SubSeccionDashboardBW. */
	@OneToMany(mappedBy = "seccionDashboardBW", targetEntity = BotonSeccionBW.class)
	private List<BotonSeccionBW> listOfBotonSeccionBW;
	
	/** El atributo o variable list of SubSeccionDashboardBW. */
	@OneToMany(mappedBy = "seccionDashboardBW", targetEntity = QueryServicioBotonBW.class)
	private List<QueryServicioBotonBW> listOfQueryServicioBotonBW;
	
}
