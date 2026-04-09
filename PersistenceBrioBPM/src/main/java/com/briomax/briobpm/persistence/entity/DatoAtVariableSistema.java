package com.briomax.briobpm.persistence.entity;

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
 * El objetivo de la Class DatoAtVariableSistema.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Mar 07, 2024 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */

@Entity
@Table(name = "DATO_AT_VARIABLE_SISTEMA")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DatoAtVariableSistema implements IEntity {
	
	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable id*/
	@EmbeddedId
	private DatoAtVariableSistemaPK id;

	/** El atributo o variable datoAreaTrabajo. */
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
			@JoinColumn(name = "CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
			@JoinColumn(name = "VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false),
			@JoinColumn(name = "CVE_AREA_TRABAJO", referencedColumnName = "CVE_AREA_TRABAJO", insertable = false, updatable = false),
			@JoinColumn(name = "SECUENCIA_TARJETA", referencedColumnName = "SECUENCIA_TARJETA", insertable = false, updatable = false),
			@JoinColumn(name = "SECUENCIA_DATO", referencedColumnName = "SECUENCIA_DATO", insertable = false, updatable = false)})
	private DatoAreaTrabajo datoAreaTrabajo;

	/** El atributo o variable variableSistema. */
	@ManyToOne
	@JoinColumn(name = "CVE_VARIABLE", referencedColumnName = "CVE_VARIABLE", insertable = false, updatable = false)
	private VariableSistema variableSistema;
}
