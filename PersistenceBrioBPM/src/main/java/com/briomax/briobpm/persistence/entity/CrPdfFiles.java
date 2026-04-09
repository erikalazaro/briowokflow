package com.briomax.briobpm.persistence.entity;


import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class CrConsultaRepse.java es ...
 * @author Erika Vázquez
 * @version 1.0 Fecha de creacion Enero 31, 2025 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "CR_PDF_FILES")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrPdfFiles implements IEntity {
	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CrPdfFilesPK id;
	

	@Column(name = "NOMBRE_DOCUMENTO", nullable = false, length = 200)
    private String nombreDocumento;
	
	/** El atributo o variable archivo Binario*/ //check
	@Column(name = "ARCHIVO_BINARIO", columnDefinition = "varbinary(max)")
	private byte[] archivoBinario;

}
