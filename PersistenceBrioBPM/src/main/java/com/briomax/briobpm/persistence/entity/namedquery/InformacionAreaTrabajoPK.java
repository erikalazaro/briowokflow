
package com.briomax.briobpm.persistence.entity.namedquery;

import javax.persistence.Column;
import javax.persistence.Embeddable;

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
public class InformacionAreaTrabajoPK implements IPrimaryKey {

	private static final long serialVersionUID = 1L;

	@Column(name = "CVE_INSTANCIA")
	private String cveInstancia;

	@Column(name = "SECUENCIA_NODO")
	private Integer secuenciaNodo;

}
