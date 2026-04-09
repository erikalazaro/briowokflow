package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class CrReciboNomina.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion May 09, 2024 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "CR_RECIBO_NOMINA")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrReciboNomina implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable id. */
	@EmbeddedId
	private CrReciboNominaPK id;
	
	/** El atributo o variable. */	
	@Column(name = "NOMBRE_DOCUMENTO")
	private String nombreDocumento;	
	
	/** El atributo o variable. */	
	@Column(name = "RAZON_SOCIAL_PATRON")
	private String razonSocialPatron;
	
	/** El atributo o variable. */
	@Column(name = "RFC_PATRON")
	private String rfcPatron;
	
	/** El atributo o variable. */
	@Column(name = "REGISTRO_PATRONAL")
	private String registroPatronal;
	
	/** El atributo o variable. */
	@Column(name = "FECHA_INICIO_PERIODO")
	private Date fechaInicioPeriodo;

	/** El atributo o variable. */
	@Column(name = "FECHA_FINAL_PERIODO")
	private Date fechaFinalPeriodo;
	
	/** El atributo o variable. */
	@Column(name = "FECHA_PAGO")
	private Date fechaPago;
	
	/** El atributo o variable. */
	@Column(name = "FECHA_TIMBRADO")
	private Date fechaTimbrado;
	
	/** El atributo o variable. */	
	@Column(name = "NOMBRE_TRABAJADOR")
	private String nombreTrabajdor;	
	
	/** El atributo o variable. */	
	@Column(name = "CURP_TRABAJADOR")
	private String curpTrabajdor;	
	
	/** El atributo o variable. */	
	@Column(name = "NSS_TRABAJADOR")
	private String nssTrabajdor;
	
    @Column(name = "SALARIO_BASE", precision = 12, scale = 2)
    private BigDecimal salarioBase;
   
	/** El atributo o variable. */	
	@Column(name = "SITUACION_CARGA")
	private String situacionCarga;
}