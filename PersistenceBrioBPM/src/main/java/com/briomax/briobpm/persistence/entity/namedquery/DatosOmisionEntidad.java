
package com.briomax.briobpm.persistence.entity.namedquery;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.ParameterMode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@NamedStoredProcedureQueries({
//	@NamedStoredProcedureQuery(name = "obtenerDatosOmision",
//		procedureName = "SP_LEE_DATOS_OMISION_ENTIDAD",
//		parameters = {
//			@StoredProcedureParameter(name = "CH_CVE_ENTIDAD", type = String.class, mode = ParameterMode.IN),
//			@StoredProcedureParameter(name = "CH_TIPO_EXCEPCION", type = String.class, mode = ParameterMode.OUT),
//			@StoredProcedureParameter(name = "CH_MENSAJE", type = String.class, mode = ParameterMode.OUT)},
//		resultClasses = DatosOmisionEntidad.class
//	)
//})
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class DatosOmisionEntidad {
	@Id
	private String cve_idioma;

	private String logotipo;
}
