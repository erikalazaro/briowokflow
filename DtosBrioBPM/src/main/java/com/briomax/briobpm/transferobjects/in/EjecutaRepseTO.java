package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Clase EjecutaRepseTO.java es ...
 * 
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion May 10, 2024 6:05:58 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "EjecutaRepseTO", title = "EjecutaRepseTO")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EjecutaRepseTO implements Serializable {/**
	
	/** serial Version UID. */
	private static final long serialVersionUID = 1L;

    /** El atributo o variable rfc Contratista. */
    @Schema(name = "razonSocial", description = "RFC del Contratista.", example = "", implementation = String.class)
    private String razonSocial;

}