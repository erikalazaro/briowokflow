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
 * El objetivo de la Class DatoAtVariableEntidad.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Nov 07, 2023 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "IN_DOCUMENTO_PROCESO_OC")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {})
@EqualsAndHashCode(callSuper = false, exclude = {})
public class InDocumentoProcesoOc implements IEntity {
	
	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private InDocumentoProcesoOcPK id;
	
	/** El atributo o variable nombre Archivo*/
	@Column(name = "NOMBRE_ARCHIVO",length = 100)
	private String nombreArchivo;

	/** El atributo o variable archivo Binario*/ //check
	@Column(name = "ARCHIVO_BINARIO")
	private byte[] archivoBinario;
	
	/** El atributo o variable content Type*/
	@Column(name = "CONTENT_TYPE", length = 40)
	private String contentType;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name="CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
		@JoinColumn(name="VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false),
		@JoinColumn(name="SECUENCIA_DOCUMENTO", referencedColumnName = "SECUENCIA_DOCUMENTO", insertable = false, updatable = false) })
	private StDocumentoProceso stDocumentoProceso;
}
