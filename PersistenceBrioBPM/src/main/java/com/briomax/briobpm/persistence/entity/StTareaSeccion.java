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
 * El objetivo de la Class StProceso.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Nov 23, 2023 12:24:43 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "ST_TAREA_SECCION")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {})
@EqualsAndHashCode(callSuper = false, exclude = {})
public class StTareaSeccion implements IEntity {
	
	/** Serial Version UID. */
	private static final long serialVersionUID = 3008509815141573530L;
	
	/** El atributo o variable id. */
	@EmbeddedId
	private StTareaSeccionPK id;
	
	/** El atributo o variable cve rol. */
	@Column(name = "ORDEN_PRESENTACION", nullable = false, length = 5, precision = 5, scale = 0)
	private Integer ordenPresentacion;

	/** El atributo o variable cve proceso. */
	@Column(name = "REQUERIDA", nullable = false, length = 10)
	private String requerida;

	/** El atributo o variable version. */
	@Column(name = "SOLO_CONSULTA", nullable = false, length = 2)
	private String soloConsulta;

	/** El atributo o variable cve rol. */
	@Column(name = "ENVIO_GRABAR", nullable = false, length = 2)
	private String envioGrabar;
	
	/** El atributo o variable stProceso. */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name="CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
		@JoinColumn(name="VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false),
		@JoinColumn(name="CVE_NODO", referencedColumnName = "CVE_NODO", insertable = false, updatable = false),
		@JoinColumn(name="ID_NODO", referencedColumnName = "ID_NODO", insertable = false, updatable = false),
		@JoinColumn(name="CVE_SECCION", referencedColumnName = "CVE_SECCION", insertable = false, updatable = false)
		})
	private StSeccionNodo stSeccionNodo;
	
	/** El atributo o variable stProceso. */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name="CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
		@JoinColumn(name="VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false),
		@JoinColumn(name="SECUENCIA_TAREA", referencedColumnName = "SECUENCIA_TAREA", insertable = false, updatable = false)
		})
	private StTareaProceso StTareaProceso;
}
