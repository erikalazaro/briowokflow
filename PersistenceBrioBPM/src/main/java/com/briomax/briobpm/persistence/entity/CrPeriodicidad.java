package com.briomax.briobpm.persistence.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class CrPeriocidad.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion 14/04/2025 Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "CR_PERIODICIDAD")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, exclude = {"listCrProgramacionProceso"})
public class CrPeriodicidad implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable id. */
	@Id
	@Column(name = "CVE_PERIODICIDAD", nullable = false, length = 40)
    private String cvePeriodicidad;
	
	/** El atributo o variable periodo Tiempo. */	
	@Column(name = "DESCRIPCION", length = 500)
	private String descripcion;
	
	/** El atributo o variable cada N Periodos. */
	@Column(name = "CADA_N_PERIODOS")
	private Integer cadaNPeriodos;

	/** El atributo o variable periodo Tiempo. */	
	@Column(name = "PERIODO_TIEMPO", length = 40)
	private String periodoTiempo;
	
	/** El atributo o variable detalle Periodo. */	
	@Column(name = "DETALLE_PERIODO", length = 30)
	private String detallePeriodo;
	
	/** El atributo o variable dias De La Semana. */	
	@Column(name = "DIAS_DE_LA_SEMANA", length = 15)
	private String diasDeLaSemana;
	
	/** El atributo o variable dias Del Mes. */	
	@Column(name = "DIAS_DEL_MES", length = 100)
	private String diasDelMes;
	
	/** El atributo o variable tipo Dia. */	
	@Column(name = "TIPO_DIA", length = 30)
	private String tipoDia;
	
	/** El atributo o variable list of InMensajeRecepcion. */
	@OneToMany(mappedBy = "crPeriodicidad", targetEntity = CrProgramacionProceso.class)
	private List<CrProgramacionProceso> listCrProgramacionProceso;
	
}