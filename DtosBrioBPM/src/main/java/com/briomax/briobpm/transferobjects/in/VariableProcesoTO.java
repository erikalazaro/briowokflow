package com.briomax.briobpm.transferobjects.in;

import java.math.BigDecimal;
import java.util.Date;

import com.briomax.briobpm.transferobjects.core.ITransferObject;
import com.briomax.briobpm.transferobjects.in.DocumentoNodoTO.DocumentoNodoTOBuilder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/**
 * El objetivo de la Class VariableProcesoTO.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Abr 30, 2024 11:09:51 AM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "VariableProcesoTO", title = "VariableProcesoTO.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VariableProcesoTO implements ITransferObject {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;

	public Integer ocurrencia;
	
	public Integer secuenciaDocumento;
	
	public Integer orden;
	
	public String cveVariable;
	
	public String tipo;
	
	public Integer secuencia;
	
	public String alfanumerico;
	
	public String archivoDoc;
	
	public String requeridoDoc;

    public Integer entero;
	
	public BigDecimal decimal;
	
	public Date fecha;
	
	public String formula;
	
	private byte[] imagen;
	
	
}
