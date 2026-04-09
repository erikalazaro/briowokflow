
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
public class DatoActividadPK implements IPrimaryKey {

	private static final long serialVersionUID = 1L;

	/** El atributo o variable des nodo. */
	@Column(name = "CVE_INSTANCIA")
	private String cveInstancia;

	/** El atributo o variable ordenamiento. */
	@Column(name = "SECUENCIA_NODO")
	private Integer secuenciaNodo;
	
	/** El atributo o variable cve proceso. */
	@Column(name = "SECUENCIA_DATO")
	private Integer secuenciaDato;

	/** El atributo o variable version. */
	@Column(name = "ORDEN_DATO")
	private Integer ordenDato;

}
