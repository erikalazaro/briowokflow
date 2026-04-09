package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class InBitacoraNodoPK.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Dic 04, 2023 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InBitacoraNodoPK implements IPrimaryKey {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable cve Entidad.*/
	@Column(name = "CVE_ENTIDAD", nullable = false, length = 40)
	private String cveEntidad;
	
	/** El atributo o variable cve Proceso*/
	@Column(name = "CVE_PROCESO", nullable = false, length = 40)
	private String cveProceso;
	
	/** El atributo o variable version*/
	@Column(name = "VERSION", nullable = false, length = 5, precision = 5, scale = 2)
	private BigDecimal version;
	
	/** El atributo o variable cve Area Trabajo*/
	@Column(name = "CVE_INSTANCIA", nullable = false, length = 40)
	private String cveInstancia;
	
	/** El atributo o variable secuencia Dato*/
	@Column(name = "CVE_NODO", nullable = false, length = 40)
	private String cveNodo;
	
	/** El atributo o variable cve Variable*/
	@Column(name = "ID_NODO", nullable = false, precision = 5, scale = 0)
	private Integer idNodo;
	
	/** El atributo o variable secuencia Nodo*/
	@Column(name = "SECUENCIA_NODO", nullable = false, precision = 5, scale = 0)
	private Integer secuenciaNodo;
	
	/** El atributo o variable fecha Eventoo*/
	@Column(name = "FECHA_EVENTO", nullable = false)
	private Date fechaEvento;

}
