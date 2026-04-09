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
 * El objetivo de la Class StReglaValidacionProceso.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Feb 06, 2024 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "ST_REGLA_VALIDACION_PROCESO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StReglaValidacionProceso implements IEntity {
	
	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private StReglaValidacionProcesoPK id;
	
	/** El atributo o variable descripcion. */
	@Column(name = "DESCRIPCION", nullable = false, length = 100)
	private String descripcion;

	/** El atributo o variable situacion. */
	@Column(name = "FORMULA", length = 500)
	private String formula;

	/** El atributo o variable situacion. */
	@Column(name = "FUNCION", length = 30)
	private String funcion;
	
	/** El atributo o variable stProceso. */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name="CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
		@JoinColumn(name="VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false)})
	private StProceso stProceso;
}
