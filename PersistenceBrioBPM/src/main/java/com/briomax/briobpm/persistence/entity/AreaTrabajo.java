package com.briomax.briobpm.persistence.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;
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
 * El objetivo de la Class Entidad.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Nov 03, 2023 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */

@Entity
@Table(name = "AREA_TRABAJO")
@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"listOfStNodoProceso", "listOfTarjetaAreaTrabajo"})
@EqualsAndHashCode(callSuper = false, exclude = {"listOfStNodoProceso", "listOfTarjetaAreaTrabajo"})
public class AreaTrabajo implements IEntity{

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private AreaTrabajoPK id;
	
	/** El atributo o variable description. */
	@Column(name = "DESCRIPCION", nullable = false, length = 40)
	private String description;

	/** El atributo o variable orden Area Trabajo.*/
	@Column(name = "ORDEN_AREA_TRABAJO", nullable = false, precision = 3, scale = 0)
	private Integer ordenAreaTrabajo;
	
	/** El atributo o variable situacion Area Trabajo. */
	@Column(name = "SITUACION_AREA_TRABAJO", nullable = false, length = 12)
	private String situacionAreaTrabajo;
	
	/** El atributo o variable Tipo Area Trabajo. */
	@Column(name = "TIPO_AREA", nullable = false, length = 10)
	private String tipoArea;
		
	/** El atributo o variable stProceso. */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name="CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
		@JoinColumn(name="VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false)
	})
	private StProceso stProceso;

	/** Asociacion bidireccional a StNodoProceso. */
	@OneToMany(mappedBy = "areaTrabajo", targetEntity = StNodoProceso.class)
	private List<StNodoProceso> listOfStNodoProceso;
	
	
	/** Asociacion bidireccional a tarjeta area trabajo. */
	@OneToMany(mappedBy = "areaTrabajo", targetEntity = TarjetaAreaTrabajo.class)
	private List<TarjetaAreaTrabajo> listOfTarjetaAreaTrabajo;
	
}
