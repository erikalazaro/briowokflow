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
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class BotonDashboardBW.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Oct 30, 2024 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */

@Entity
@Table(name = "BOTON_DASHBOARD_BW")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BotonDashboardBW implements IEntity {
	
	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable id*/
	@EmbeddedId
	private BotonDashboardBWPK id;
	
	/** El atributo o ordenBoton. */	
	@Column(name = "ORDEN_BOTON", nullable = false, length = 3)
	private Integer ordenBoton;	
	
	/** El atributo o descripcion. */	
	@Column(name = "DESCRIPCION", nullable = false, length = 100)
	private String descripcion;	
	
	/** El atributo o titulo. */	
	@Column(name = "ETIQUETA", length = 40)
	private String etiqueta;
	
	/** El atributo o variable dashboardBW. */
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name="CVE_DASHBOARD", referencedColumnName = "CVE_DASHBOARD", insertable = false, updatable = false)
	})
	private DashboardBW dashboardBW;
}
