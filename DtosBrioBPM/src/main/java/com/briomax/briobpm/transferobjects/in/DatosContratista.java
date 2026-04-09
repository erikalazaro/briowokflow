package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;
import java.util.List;

import com.briomax.briobpm.transferobjects.app.Seccion;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * El objetivo de la Interface VigenciaRepseTO.java es ...
 * 
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Oct 31 2024, 2024 6:05:58 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "DatosContrato", title = "DatosContrato")
@Data
@Builder
public class DatosContratista implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(name = "razonSocial", description = "razonSocial.", example = "razonSocial", implementation = String.class)
	private String razonSocial;
	
	@Schema(name = "sections", description = "Secciones.", example = "Object")
	private List<Seccion> sections;
}
