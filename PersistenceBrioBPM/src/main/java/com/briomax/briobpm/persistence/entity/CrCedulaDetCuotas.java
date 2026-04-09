package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class CrCedulaDetCuotas.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion September 18, 2024 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "CR_CEDULA_DET_CUOTAS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrCedulaDetCuotas implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable id. */
	@EmbeddedId
	private CrCedulaDetCuotasPK id;
	
	/** El atributo o variable. */	
	@Column(name = "NOMBRE_DOCUMENTO", nullable = false, length = 200)
	private String nombreDocumento;	
	
	/** El atributo o variable. */	
	@Column(name = "RAZON_SOCIAL", nullable = false, length = 200)
	private String razonSocial;

	/** El atributo o variable. */
	@Column(name = "REGISTRO_PATRONAL", nullable = false, length = 20)
	private String registroPatronal;

	/** El atributo o variable. */	
	@Column(name = "NSS_TRABAJADOR", nullable = false, length = 20)
	private String nssTrabajador;
	
	/** El atributo o variable. */	
	@Column(name = "NOMBRE_TRABAJADOR", nullable = false, length = 100)
	private String nombreTrabajador;
	
	/** El atributo o variable. */	
	@Column(name = "RFC_CURP_TRABAJADOR", nullable = false, length = 20)
	private String rfcCurpTrabajador;
	
	/** El atributo o variable. */	
	@Column(name = "SALARIO_BASE_COTIZACION", nullable = false, length = 12, precision = 12, scale = 2)
	private BigDecimal salarioBaseCotizacion;
	
	/** El atributo o variable. */	
	@Column(name = "PERIODO", nullable = false, length = 60)
	private String periodo;
	
	/** El atributo o variable. */	
	@Column(name = "SITUACION_CARGA", nullable = false, length = 20)
	private String situacionCarga;
	
	/** El atributo o variable. */	
	@Column(name = "ESTATUS_REGISTRO", nullable = false, length = 200)
	private String estatusRegistro;
	
}
