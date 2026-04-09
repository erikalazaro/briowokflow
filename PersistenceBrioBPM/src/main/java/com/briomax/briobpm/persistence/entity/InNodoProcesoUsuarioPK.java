/**
 * 
 */
package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class StProcesoPK.java es ...
 * @author alfredo alejandro
 * @version 1.0
 * @since JDK 11.0.22 LTS
 */

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InNodoProcesoUsuarioPK implements IPrimaryKey {

	/**
	 * serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	
	
	/** El atributo o variable cve entidad. */
	@Column(name = "CVE_ENTIDAD", nullable = false, length = 40)
	private String cveEntidad;
	
	/** El atributo o variable cve proceso. */
	@Column(name = "CVE_PROCESO", nullable = false, length = 40)
	private String cveProceso;

	/** El atributo o variable version. */
	@Column(name = "VERSION", nullable = false,  precision = 5, scale = 2)
	private BigDecimal version;
	
	/** El atributo o variable cve instancia. */
	@Column(name = "CVE_INSTANCIA", nullable = false, length = 40)
	private String cveInstancia;
	
	/** El atributo o variable cve nodo. */
	@Column(name = "CVE_NODO", nullable = false, length = 40)
	private String cveNodo;

	/** El atributo o variable id nodo. */
	@Column(name = "ID_NODO", nullable = false,  precision = 5, scale = 0, length = 5)
	private Integer idNodo;
	
	/** El atributo o variable secuencia nodo. */
	@Column(name = "SECUENCIA_NODO", nullable = false, precision = 5,  scale = 0, length = 5)
	private Integer secuenciaNodo;
	
	/** El atributo o variable cve usuario. */
	@Column(name = "CVE_USUARIO", nullable = false, length = 30)
	private String cveUsuario;
	


}
