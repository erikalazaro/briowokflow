
package com.briomax.briobpm.persistence.entity.namedquery;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(name = "obtenerEtiquetas",
		procedureName = "SP_LEE_ETIQUETAS_IDIOMA",
		parameters = {
			@StoredProcedureParameter(name = "CH_CVE_ENTIDAD", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_IDIOMA", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_TIPO_EXCEPCION", type = String.class, mode = ParameterMode.OUT),
			@StoredProcedureParameter(name = "CH_MENSAJE", type = String.class, mode = ParameterMode.OUT)},
		resultClasses = Etiquetas.class
	)
})*/
@Entity
@Getter
@Setter
@ToString
public class Etiquetas {

	@Column(name = "ETIQUETA")
	@Id
	private String etiqueta;

	@Column(name = "TRADUCCION")
	private String traduccion;
}
