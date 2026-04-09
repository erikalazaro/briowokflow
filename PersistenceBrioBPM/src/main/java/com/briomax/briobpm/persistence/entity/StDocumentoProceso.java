package com.briomax.briobpm.persistence.entity;

import java.util.List;

import javax.persistence.Column;
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
 * El objetivo de la Class StProceso.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Nov 24, 2023 12:24:43 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "ST_DOCUMENTO_PROCESO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"listOfStDocumentoSeccion", "listOfInDocumentoProceso"})
@EqualsAndHashCode(callSuper = false, exclude = {"listOfStDocumentoSeccion", "listOfInDocumentoProceso"})
public class StDocumentoProceso implements IEntity {
	
	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable id. */
	@EmbeddedId
	private StDocumentoProcesoPK id;
	
	/** El atributo o variable descripcion. */
	@Column(name = "DESCRIPCION", nullable = false,  length = 100)
	private String descripcion;
	
	/** El atributo o variable extensiones Permitidas. */
	@Column(name = "EXTENSIONES_PERMITIDAS", nullable = false,  length = 80)
	private String extensionesPermitidas;

	/** El atributo o variable vigencia Documento. */
	@Column(name = "VIGENCIA_DOCUMENTO", length = 4)
	private String vigenciaDocumento;

	/** El atributo o variable stProceso. */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name="CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
		@JoinColumn(name="VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false)
	})
	private StProceso stProceso;
	
	/** Asociacion bidireccional a StDocumentoSeccion. */
	@OneToMany(mappedBy = "stDocumentoProceso", targetEntity = StDocumentoSeccion.class)
	private List<StDocumentoSeccion> listOfStDocumentoSeccion;
	
	/** Asociacion bidireccional a StDocumentoSeccion. */
	@OneToMany(mappedBy = "stDocumentoProceso", targetEntity = InDocumentoProceso.class)
	private List<InDocumentoProceso> listOfInDocumentoProceso;
	
}
