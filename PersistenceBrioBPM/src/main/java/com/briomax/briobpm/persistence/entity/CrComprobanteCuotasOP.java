package com.briomax.briobpm.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class CrComprobanteCuotaOP.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion September 04, 2024 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "CR_COMPROBANTE_CUOTAS_OP")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrComprobanteCuotasOP implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable id. */
	@EmbeddedId
	private CrComprobanteCuotasOPPK id;
	
	/** El atributo o variable. */	
	@Column(name = "NOMBRE_DOCUMENTO")
	private String nombreDocumento;	
	
	/** El atributo o variable. */	
	@Column(name = "RAZON_SOCIAL")
	private String razonSocial;

	/** El atributo o variable. */
	@Column(name = "REGISTRO_PATRONAL")
	private String registroPatronal;

	/** El atributo o variable. */	
	@Column(name = "FECHA_PAGO")
	private Date fechaPago;
	
	/** El atributo o variable. */	
	@Column(name = "PERIODO_PAGO_INFONAVIT")
	private String periodoPagoinfonavit;
	
	/** El atributo o variable. */	
	@Column(name = "PERIODO_PAGO_IMSS")
	private String periodoPagoimss;
	
	/** El atributo o variable. */	
	@Column(name = "SITUACION_CARGA")
	private String situacionCarga;

}