
package com.briomax.briobpm.persistence.entity.namedquery;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

import com.briomax.briobpm.persistence.entity.IPrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DatoTrabajadorPK implements IPrimaryKey {

	private static final long serialVersionUID = 1L;

	/** El atributo o variable. */
	@Column(name = "RFC")
	private String rfc;

	/** El atributo o variable. */
	@Column(name = "NUMERO_CONTRATO")
	private String numContrato;
	
	/** El atributo o variable instancia. */	
	@Column(name = "INSTANCIA")
	private String instancia;
	
	/** El atributo o variable instancia. */	
	@Column(name = "OCURRENCIA")
	private Integer ocurrencia;
	
	/** El atributo o variable. */
	@Column(name = "CVE_VARIABLE")
	private String cveVariable;


}
