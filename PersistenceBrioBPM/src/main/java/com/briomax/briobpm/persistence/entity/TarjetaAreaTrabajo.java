package com.briomax.briobpm.persistence.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "TARJETA_AREA_TRABAJO")
@NamedQuery(name="TarjetaAreaTrabajo.findAll", query="SELECT t FROM TarjetaAreaTrabajo t ORDER BY t.id.secuenciaTarjeta")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"listOfDatoAreaTrabajo", "listOfIconoEstadoAT"})
@EqualsAndHashCode(callSuper = false, exclude = {"listOfDatoAreaTrabajo", "listOfIconoEstadoAT"})
public class TarjetaAreaTrabajo implements IEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable id. */
	@EmbeddedId
	private TarjetaAreaTrabajoPK id;
	
	/** El atributo o variable origen. */
	@Column(name = "TITULO_TARJETA", length = 80)
	private String tituloTarjeta;	
	
	/** El atributo o variable tarjeta area trabajo. */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name="CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
		@JoinColumn(name="VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false),
		@JoinColumn(name="CVE_AREA_TRABAJO", referencedColumnName = "CVE_AREA_TRABAJO", insertable = false, updatable = false)
	})
	private AreaTrabajo areaTrabajo;
	
	/** Asociacion bidireccional a tarjeta area trabajo. */
	@OneToMany(mappedBy = "tarjetaAreaTrabajo", targetEntity = DatoAreaTrabajo.class)
	private List<DatoAreaTrabajo> listOfDatoAreaTrabajo;
	
	/** Asociacion bidireccional a tarjeta area trabajo. */
	@OneToMany(mappedBy = "tarjetaAreaTrabajo", targetEntity = IconoEstadoAT.class)
	private List<IconoEstadoAT> listOfIconoEstadoAT;
	
}

