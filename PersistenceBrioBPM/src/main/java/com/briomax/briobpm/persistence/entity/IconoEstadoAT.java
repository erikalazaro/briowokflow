package com.briomax.briobpm.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
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
 * El objetivo de la Class IconoEstadoAT.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Jun 18, 2024 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */

@Entity
@Table(name = "ICONO_ESTADO_AT")
@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {})
@EqualsAndHashCode(callSuper = false, exclude = {})

public class IconoEstadoAT implements IEntity{

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private IconoEstadoATPK id;
	
	/** El atributo o variable situacion Area Trabajo. */
	@Column(name = "ICONO_ESTADO")
	private byte[] iconoEstado;
	
	/** El atributo o variable Tipo Area Trabajo. */
	@Column(name = "CONDICION_ICONO_ESTADO", nullable = false, length = 500)
	private String condicionIconoEstado;
	
	/** El atributo o variable tarjetaAreaTrabajo. */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name="CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
		@JoinColumn(name="VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false),
		@JoinColumn(name="CVE_AREA_TRABAJO", referencedColumnName = "CVE_AREA_TRABAJO", insertable = false, updatable = false),
		@JoinColumn(name="SECUENCIA_TARJETA", referencedColumnName = "SECUENCIA_TARJETA", insertable = false, updatable = false)
	})
	private TarjetaAreaTrabajo tarjetaAreaTrabajo;

}
