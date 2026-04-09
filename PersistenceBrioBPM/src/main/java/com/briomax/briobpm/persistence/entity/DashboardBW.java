package com.briomax.briobpm.persistence.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
 * El objetivo de la Class DashboardBW.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Oct 30, 2024 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */

@Entity
@Table(name = "DASHBOARD_BW")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"listOfSeccionDashboardBW" , "SeccionDashboardBW"})
@EqualsAndHashCode(callSuper = false, exclude = {"listOfSeccionDashboardBW", "SeccionDashboardBW"})
public class DashboardBW implements IEntity {
	
	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable id*/
	@EmbeddedId
	private DashboardBWPK id;
	
	/** El atributo o descripcion. */	
	@Column(name = "DESCRIPCION", nullable = false, length = 100)
	private String descripcion;	
	
	/** El atributo o destinoWeb. */	
	@Column(name = "DESTINO_WEB", length = 30)
	private String destinoWeb;	
	
	/** El atributo o destinoApp. */	
	@Column(name = "DESTINO_APP", length = 30)
	private String destinoApp;	
	
	/** El atributo o titulo. */	
	@Column(name = "TITULO", nullable = false, length = 40)
	private String titulo;	
	
	/** El atributo o dashboardHabilitado. */	
	@Column(name = "DASHBOARD_HABILITADO", nullable = false, length = 2)
	private String dashboardHabilitado;	
	
	/** El atributo o variable entidad. */
	@ManyToOne
	@JoinColumn(name = "CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false)
	private Entidad entidad;
	
	/** El atributo o variable list of SeccionDashboardBW. */
	@OneToMany(mappedBy = "dashboardBW", targetEntity = SeccionDashboardBW.class)
	private List<SeccionDashboardBW> listOfSeccionDashboardBW;
	
	/** El atributo o variable list of BotonDashboardBW. */
	@OneToMany(mappedBy = "dashboardBW", targetEntity = BotonDashboardBW.class)
	private List<BotonDashboardBW> listOfBotonDashboardBW;
		
}
