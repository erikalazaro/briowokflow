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
 * El objetivo de la Class CrDeclaracionProvisional.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion August 30, 2024 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "CR_DECLARACION_PROVISIONAL")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrDeclaracionComplementario implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable id. */
	@EmbeddedId
	private CrDeclaracionComplementarioPK id;
	
	/** El atributo o variable. */	
	@Column(name = "NOMBRE_DOCUMENTO")
	private String nombreDocumento;	
	
	/** El atributo o variable. */	
	@Column(name = "RAZON_SOCIAL")
	private String razonSocial;
	
	/** El atributo o variable. */
	@Column(name = "RFC")
	private String rfc;
	
	/** El atributo o variable. */
	@Column(name = "TIPO_DECLARACION")
	private String tipoDeclaracion;
	
	/** El atributo o variable. */
	@Column(name = "PERIODO_DECLARACION")
	private String periodoDeclaracion;

	/** El atributo o variable. */
	@Column(name = "EJERCICIO")
	private String ejercicio;

	/** El atributo o variable. */
	@Column(name = "FECHA_HORA_PRESENTACION")
	private Date fechaHoraPresentacion;
	
	/** El atributo o variable. */	
	@Column(name = "LINEA_CAPTUTA")
	private String lineaCaptura;	
	
	/** El atributo o variable. */	
	@Column(name = "CONCEPTO_PAGO")
	private String conceptoPago;	
	
	/** El atributo o variable. */	
    @Column(name = "IMPORTE_A_PAGAR", precision = 16, scale = 2)
    private BigDecimal importeApagar;
   
	/** El atributo o variable. */	
	@Column(name = "SITUACION_CARGA")
	private String situacionCarga;
}