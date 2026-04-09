package com.briomax.briobpm.persistence.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class CrConsultaRepse.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion May 09, 2024 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "CR_CONSULTA_REPSE")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrConsultaRepse implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable id. */
	@EmbeddedId
	private CrConsultaRepsePK id;
	
	
	/** El atributo o variable Situacion Carga. */
	@Column(name = "SITUACION_CARGA", nullable = false, length = 20)
	private String situacionCarga;
	
	/** El atributo o variable Resultado Consulta. */
	@Column(name = "RESULTADO_CONSULTA", nullable = false, length = 20)
	private String resultadoConsulta;

	/** El atributo o variable Razon Social. */
	@Column(name = "RAZON_SOCIAL", length = 200)
	private String razonSocial;
	
	/** El atributo o variable Folio. */
	@Column(name = "FOLIO", length = 20)
	private String folio;
	
	/** El atributo o variable Entidad Municipio. */
	@Column(name = "ENTIDAD_MUNICIPIO", length = 200)
	private String entidadMunicipio;
	
	/** El atributo o variable Aviso Fecha Registro. */
	@Column(name = "AVISO_FECHA_REGISTRO", length = 30)
	private String avisoFechaRegistro;
	
	/** El atributo o variable Imagen Consulta. */
	@Column(name = "IMAGEN_CONSULTA")
	private byte[] imagenConsulta;
	
	/** El atributo o variable descripcion Servicio. */
	@Column(name = "DESCRIPCION_SERVICIO", length = 200)
	private String descripcionServicio;
	
	/** El atributo o variable Vigencia Registro. */
	@Column(name = "VIGENCIA_REGISTRO", length = 30)
	private String vigenciaRegistro;
	
}