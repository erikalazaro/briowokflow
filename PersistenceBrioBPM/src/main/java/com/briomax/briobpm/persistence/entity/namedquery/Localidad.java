
package com.briomax.briobpm.persistence.entity.namedquery;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/*
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(name = "leeDatosLocalidad",
		procedureName = "SP_LEE_DATOS_LOCALIDAD",
		parameters = {@StoredProcedureParameter(name = "CH_CVE_USUARIO", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_ENTIDAD", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_LOCALIDAD", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_IDIOMA", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_TIPO_EXCEPCION", type = String.class, mode = ParameterMode.OUT),
			@StoredProcedureParameter(name = "CH_MENSAJE", type = String.class, mode = ParameterMode.OUT)},
		resultClasses = Localidad.class)
	})
	*/
@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Localidad implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CLAVE")
	private String clave;

	@Column(name = "TIPO")
	private String tipo;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "DIRECCION")
	private String direccion;

	@Column(name = "TELEFONO")
	private String telefono;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "MONEDA")
	private String moneda;

	@Column(name = "HUSOHORARIO")
	private String husohorario;

	@Column(name = "FECHA")
	private String fecha;

	@Column(name = "HORA")
	private String hora;
}
