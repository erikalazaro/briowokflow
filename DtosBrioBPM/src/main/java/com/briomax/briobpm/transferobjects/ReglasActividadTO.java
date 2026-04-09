package com.briomax.briobpm.transferobjects;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
/**
 * El objetivo de la clase ReglasActividadTO.java es almacenar las reglas asociadas a una actividad.
 *
 * @author Erika Vazquez
 * @ver 1.0
 * @fecha Sep 10, 2024 4:12:01 PM
 * @since JDK 11
 */
@Schema(title = "ReglasActividadTO.", description = "Resultado reglas DTO.")
@Data
@Builder
public class ReglasActividadTO implements Serializable {
	
	/** serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** Clave regla. */
	@Schema(name = "consecutivo", description = "", example = "", implementation = String.class)
	private int consecutivo;

	/** Clave regla. */
	@Schema(name = "CVE_REGLA", description = "", example = "", implementation = String.class)
	private String cveRegla;
	
	/** Tipo expresion. */
	@Schema(name = "TIPO_EXPRESION", description = "", example = "LOGICA, CALCULO, RECUPERAR-VALOR", implementation = String.class)
	private String tipoExpresion;
	
	/** Tipo expresion. */
	@Schema(name = "CVE_VARIABLE", description = "", example = "", implementation = String.class)
	private String cveVariable;
	
	/** Aplicar Captura. */
	@Schema(name = "APLICAR_EN_CAPTURA", description = "", example = "", implementation = String.class)
	private String aplicarCaptura;
	
	/** Aplicar Guardado. */
	@Schema(name = "APLICAR_EN_GUARDADO", description = "", example = "", implementation = String.class)
	private String aplicarGuardado;
	
	/** Tipo expresion. */
	@Schema(name = "APLICAR_EN_TERMINACION", description = "", example = "", implementation = String.class)
	private String aplicarTerminacion;
	
	/** Tipo expresion. */
	@Schema(name = "NOTACION_POLACA", description = "", example = "", implementation = String.class)
	private String notacionPolaca;	
	
	@Schema(name = "RESULTADO_LOGICO_EXPRESION", description = "", example = "", implementation = String.class)
	private String resultadoLogico;	
	
	@Schema(name = "CVE_VARIABLE_RESULTADO", description = "", example = "", implementation = String.class)
	private String cveVariableResultante;	
	
	@Schema(name = "VALOR_ALFANUMERICO", description = "", example = "", implementation = String.class)
	private String valorAlfanumerico;	
	
	@Schema(name = "VALOR_ENTERO", description = "", example = "", implementation = String.class)
	private BigInteger valorEntero;	
	
	@Schema(name = "VALOR_DECIMAL", description = "", example = "", implementation = String.class)
	private BigDecimal valorDecimal;	
	
	@Schema(name = "VALOR_FECHA", description = "", example = "", implementation = String.class)
	private Date valorFecha;
	
	@Schema(name = "CVE_IDIOMA", description = "", example = "", implementation = String.class)
	private String cveIdioma;	
	
	@Schema(name = "TIPO_MENSAJE", description = "", example = "", implementation = String.class)
	private String tipoMensaje;	
	
	@Schema(name = "NUMERO_MENSAJE", description = "", example = "", implementation = String.class)
	private Integer numeroMensaje;	
	
	

}
