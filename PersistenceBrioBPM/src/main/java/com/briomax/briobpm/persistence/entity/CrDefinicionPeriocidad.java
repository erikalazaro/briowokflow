package com.briomax.briobpm.persistence.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class CrConsultaRepse.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion May 09, 2024 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "CR_DEFINICION_PERIODICIDAD")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, exclude = {"listOfCrProgramacionProceso"})
public class CrDefinicionPeriocidad implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable id. */
	@EmbeddedId
	private CrDefinicionPeriocidadPK id;
	
	/** El atributo o variable notificacion continua. */	
	@Column(name = "NOTIFICACION_CONTINUA", length = 2)
	private String notificaccionContinua;

	/** El atributo o variable dias para notificar. */
	@Column(name = "DIAS_PARA_NOTIFICAR")
	private Integer diasParaNotificar;

	/** El atributo o variable Secuencia Definicion Antes. */
	@Column(name = "SECUENCIA_DEFINICION_ANTES")
	private Integer secDefinicionAntes;	
	
	/** El atributo o variable Secuencia Definicion dia. */
	@Column(name = "SECUENCIA_DEFINICION_DIA")
	private Integer secDefinicionDia;	
	
	/** El atributo o variable Secuencia Definicion Despues. */
	@Column(name = "SECUENCIA_DEFINICION_DESPUES")
	private Integer secDefinicionDespues;	
	
	/** El atributo o variable desde. */
	@Column(name = "DESDE")
	private Date desde;
	
	/** El atributo o variable hasta. */
	@Column(name = "HASTA")
	private Date hasta;
	
	/** El atributo o variable situacion. */	
	@Column(name = "SITUACION", nullable = false, length = 12)
	private String situacion;	
	
	/** El atributo o variable situacion envio. */	
	@Column(name = "SITUACION_ENVIO", nullable = false, length = 20)
	private String situacionEnvio;	

	/** El atributo o variable desde. */
	@Column(name = "FECHA_CANCELA_ENVIO")
	private Date fechaCancelaEnvio;
	
	/** El atributo o variable situacion envio. */	
	@Column(name = "TIPO_EJECUCION", nullable = false, length = 10)
	private String tipoEjecucion;
	
	/** El atributo o variable situacion envio. */	
	@Column(name = "APLICA_INICIO", nullable = false, length = 2)
	private String aplicaInicio;
	
	@ManyToOne
	@JoinColumns({
	    @JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
	    @JoinColumn(name="CVE_LOCALIDAD", referencedColumnName = "CVE_LOCALIDAD", insertable = false, updatable = false),
	    @JoinColumn(name="CVE_IDIOMA", referencedColumnName = "CVE_IDIOMA", insertable = false, updatable = false),
	    @JoinColumn(name="CVE_PROCESO_PERIODICO", referencedColumnName = "CVE_PROCESO_PERIODICO", insertable = false, updatable = false),
	})
	private CrProcesoPeriodico crProcesoPeriodico;
	
	
	@ManyToOne
	@JoinColumns({
	    @JoinColumn(name="CVE_PERIODICIDAD", referencedColumnName = "CVE_PERIODICIDAD", insertable = false, updatable = false)
	})
	private CrPeriodicidad crPeriodicidad;
	
	
   /** El atributo o variable list of CrProgramacionProceso. */
	@OneToMany(mappedBy = "crDefinicionPeriocidad", targetEntity = CrProgramacionProceso.class)
	private List<CrProgramacionProceso> listOfCrProgramacionProceso;

}