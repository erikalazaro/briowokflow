package com.briomax.briobpm.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class ProcesoTStMensajeRecepcionPKO.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Dic 22 12:29:01 PM :
 * @since JDK 1.8
 */
@Schema(description = "VariableCadenaTO", title = "Variable Cadena DTO.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StMensajeRecepcionPK implements Serializable {
	
	/** serial Version UID. */
	private static final long serialVersionUID = 1L;

	 @Column(name = "CVE_ENTIDAD", nullable = false, length = 40)
	    private String cveEntidad;

	    @Column(name = "CVE_PROCESO", nullable = false, length = 40)
	    private String cveProceso;

	    @Column(name = "VERSION", nullable = false, precision = 5, scale = 2)
	    private BigDecimal version;

	    @Column(name = "CVE_NODO", nullable = false, length = 40)
	    private String cveNodo;

	    @Column(name = "ID_NODO", nullable = false, precision = 5, scale = 0)
	    private Integer idNodo;

}
