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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * El objetivo de la Class StTareaProceso.java es ...
 * 
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Nov 15, 2023 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "ST_TAREA_PROCESO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {})
@EqualsAndHashCode(callSuper = false, exclude = {})
public class StTareaProceso implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;

	/** El atributo o variable id. */
	@EmbeddedId
	private StTareaProcesoPK id;
	
	/** El atributo o variable cve Nodo */
	@Column(name = "DESCRIPCION", nullable = false, length = 100)
	private String descripcion;
	
	/** El atributo o variable stProceso. */
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
			@JoinColumn(name = "CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
			@JoinColumn(name = "VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false) })
	private StProceso stProceso;
}
