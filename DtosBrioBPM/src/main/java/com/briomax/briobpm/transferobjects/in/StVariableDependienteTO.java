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
 * El objetivo de la Class ProcesoTO.java es ...
 * @author RIGOBERTO OLVERA
 * @version 1.0 Fecha de creacion 29/01/2020 12:29:01 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "StVariableDependienteTO.", description = "Datos de la Variable Dependiente.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StVariableDependienteTO implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//	/** El atributo o variable cve rol. */
//	@Schema(name = "cveSeccion", description = "Clave de la Seccion.", example = "Variable Dependiente", implementation = String.class)
//	private String cveSeccion;
//	
//	/** El atributo o variable cve rol. */
//	@Schema(name = "cveVariable", description = "Clave de la Variable.", example = "Variable Dependiente", implementation = String.class)
//	private String cveVariable;
//	
//	/** El atributo o variable cve rol. */
//	@Schema(name = "cveSeccionDependiente", description = "Clave de Seccion Dependiente.", example = "Variable Dependiente", implementation = String.class)
//	private String cveSeccionDependiente;
//	
//	/** El atributo o variable cve rol. */
//	@Schema(name = "cveVariableDependiente", description = "Clave Variable Dependiente.", example = "Variable Dependiente", implementation = String.class)
//	private String cveVariableDependiente;
	
	/** El atributo o variable cve rol. */
	@Schema(name = "seccionSelect", description = "Seccion Select.", example = "Variable Dependiente", implementation = String.class)
	private String seccionSelect;
	
	/** El atributo o variable cve rol. */
	@Schema(name = "seccionFrom", description = "Seccion From.", example = "Variable Dependiente", implementation = String.class)
	private String seccionFrom;
	
	/** El atributo o variable cve rol. */
	@Schema(name = "seccionWhere", description = "Seccion Where.", example = "Variable Dependiente", implementation = String.class)
	private String seccionWhere;

}
