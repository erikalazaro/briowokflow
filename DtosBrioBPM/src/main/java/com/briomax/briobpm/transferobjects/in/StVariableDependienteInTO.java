package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;
import java.math.BigDecimal;

import com.briomax.briobpm.transferobjects.StNodoSiguienteTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Schema(title = "StVariableDependienteInTO.", description = "Datos de la Variable Dependiente.")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StVariableDependienteInTO extends StNodoSiguienteTO implements Serializable{
	
	
private static final long serialVersionUID = 1L;
	
	/** El atributo o variable cve rol. */
	@Schema(name = "cveSeccion", description = "Clave de la Seccion.", example = "Variable Dependiente", implementation = String.class)
	private String cveSeccion;
	
	/** El atributo o variable cve rol. */
	@Schema(name = "cveVariable", description = "Clave de la Variable.", example = "Variable Dependiente", implementation = String.class)
	private String cveVariable;
	
	/** El atributo o variable cve rol. */
	@Schema(name = "cveSeccionDependiente", description = "Clave de Seccion Dependiente.", example = "Variable Dependiente", implementation = String.class)
	private String cveSeccionDependiente;
	
	/** El atributo o variable cve rol. */
	@Schema(name = "etiquetaLista", description = "Clave o rfc.", example = "Variable Dependiente", implementation = String.class)
	private String etiquetaLista;
	
	/** El atributo o cveTienda. 
	@Schema(name = "cveTienda", description = "Clave de la tienda.", example = "Variable Dependiente", implementation = String.class)
	private String cveTienda;*/
	
	
	
	/**
	 * Crear una nueva instancia del objeto documento TO.
	 * @param cveEntidad el cve entidad.
	 * @param cveProceso el cve proceso.
	 * @param version el version.
	 * @param cveNodo el cve nodo.
	 * @param idNodo el id nodo.
	 * @param tipoNodoSiguiente el cve area trabajo.
	 * @param secuencia el cve instancia.
	 * @param cveNodoSiguiente el sec nodo.
	 * @param idNodoSiguiente el sec documento.
	 * @param condicion el nom archivo.
	 * @param cveSeccion el data archivo.
	 * @param cveVariable el content type.
	 * @param cveSeccionDependiente el content type.
	 * @param etiquetaLista el content type.
	 */
	@Builder(builderMethodName = "builderVD")
	public StVariableDependienteInTO (String cveEntidad, String cveProceso, BigDecimal version, String cveNodo, Integer idNodo,
		String tipoNodoSiguiente, Integer secuencia, String cveNodoSiguiente , Integer idNodoSiguiente, String condicion,
		String cveSeccion, String cveVariable, String cveSeccionDependiente, String etiquetaLista){
		
		super(cveEntidad, cveProceso, version, cveNodo, idNodo,tipoNodoSiguiente, secuencia, cveNodoSiguiente , 
				idNodoSiguiente, condicion);
		this.cveSeccion = cveSeccion;
		//this.cveSeccionDependiente = cveSeccionDependiente;
		this.cveVariable = cveVariable;
		//this.cveSeccionDependiente = cveSeccionDependiente;
		this.etiquetaLista = etiquetaLista;
	}

}
