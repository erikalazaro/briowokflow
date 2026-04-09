package com.briomax.briobpm.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * El objetivo de la Class CrConsultaRepse.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion May 09, 2024 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "CR_PROGRAMACION_PROCESO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"entidad","idioma","localidadEntidad"})
public class CrProgramacionProceso implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable id. */
	@EmbeddedId
	private CrProgramacionProcesoPK id;
	
	@Column(name = "EJECUTOR", nullable = false, length = 12)
    private String ejecutor;

    @Column(name = "FECHA_EJECUCION")
    private Date fechaEjecucion;

    @Column(name = "FECHA_INICIAL_NOTIFICACION")
    private Date fechaInicialNotificacion;
    
    @Column(name = "FECHA_ULTIMA_NOTIFICACION")
    private Date fechaUltimaNotificacion; 

    @Column(name = "SITUACION_ULTIMA_NOTIFICACION", nullable = false, length = 12)
    private String situacionUltimaNotificacion;

    @Column(name = "SITUACION_EJECUCION", nullable = false, length = 12)
    private String situacionEjecucion;
    
    @ManyToOne
	@JoinColumns({
	    @JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
	    @JoinColumn(name="CVE_LOCALIDAD", referencedColumnName = "CVE_LOCALIDAD", insertable = false, updatable = false),
	    @JoinColumn(name="CVE_IDIOMA", referencedColumnName = "CVE_IDIOMA", insertable = false, updatable = false),
	    @JoinColumn(name="CVE_PROCESO_PERIODICO", referencedColumnName = "CVE_PROCESO_PERIODICO", insertable = false, updatable = false),
	    @JoinColumn(name="CVE_PERIODICIDAD", referencedColumnName = "CVE_PERIODICIDAD", insertable = false, updatable = false),
	    @JoinColumn(name="RFC", referencedColumnName = "RFC", insertable = false, updatable = false),
	    @JoinColumn(name="CONTRATO", referencedColumnName = "CONTRATO", insertable = false, updatable = false)
	})
	private CrDefinicionPeriocidad crDefinicionPeriocidad;

    
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
	    @JoinColumn(name="CVE_PERIODICIDAD", referencedColumnName = "CVE_PERIODICIDAD", insertable = false, updatable = false),
	})
	private CrPeriodicidad crPeriodicidad;
}