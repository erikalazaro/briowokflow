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
 * @version 1.0 Fecha de creacion Nov 24, 2023 12:24:43 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "ST_DOCUMENTO_SECCION")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {})
@EqualsAndHashCode(callSuper = false, exclude = {})
public class StDocumentoSeccion implements IEntity {
	
	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable id. */
	@EmbeddedId
	private StDocumentoSeccionPK id;
	
	/** El atributo o variable ordenPresentacion. */
	@Column(name = "ORDEN_PRESENTACION", nullable = false, precision = 4, scale = 0)
	private Integer ordenPresentacion;
	
	/** El atributo o variable requerida. */
	@Column(name = "REQUERIDA", nullable = false,  length = 10)
	private String requerida;

	/** El atributo o variable soloConsulta. */
	@Column(name = "SOLO_CONSULTA", nullable = false,  length = 2)
	private String soloConsulta;

	/** El atributo o variable envioGrabar. */
	@Column(name = "ENVIO_GRABAR", nullable = false,  length = 2)
	private String envioGrabar;
	
	/** El atributo o variable areaTrabajo. */
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
	
	/** El atributo o variable stDocumentoProceso. */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name="CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
		@JoinColumn(name="VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false),
		@JoinColumn(name="SECUENCIA_DOCUMENTO", referencedColumnName = "SECUENCIA_DOCUMENTO", insertable = false, updatable = false)
	})
	private StDocumentoProceso stDocumentoProceso;
}
