package com.briomax.briobpm.persistence.entity;

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
@Table(name = "CR_PROCESO_PERIODICO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, exclude = {"listCrDefinicionPeriocidad", "listOfCrNotificacion", "listCrProgramacionProceso"})
public class CrProcesoPeriodico implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable id. */
	@EmbeddedId
	private CrProcesoPeriodicoPK id;
	
	/** El atributo o variable descripcion. */	
	@Column(name = "DESCRIPCION", nullable = false, length = 100)
	private String descripcion;
	
	/** El atributo o variable descripcion. */	
	@Column(name = "APLICA_CONTRATANTE", length = 2)
	private String aplicaContratante;
	
	/** El atributo o variable descripcion. */	
	@Column(name = "APLICA_CONTRATISTA", length = 2)
	private String aplicaContratista;
	
	/** El atributo o variable descripcion. */	
	@Column(name = "CARGA_MULTIPLE", length = 2)
	private String cargaMultiple;
	
	/** El atributo o variable descripcion. */	
	@Column(name = "REQUERIDO", length = 2)
	private String requerido;

	
	
	@ManyToOne
	@JoinColumns({
	    @JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
	    @JoinColumn(name="CVE_LOCALIDAD", referencedColumnName = "CVE_LOCALIDAD", insertable = false, updatable = false)
	})
	private LocalidadEntidad localidadEntidad;
	
	/** El atributo o variable list of InMensajeRecepcion. */
	@OneToMany(mappedBy = "crProcesoPeriodico", targetEntity = CrDefinicionPeriocidad.class)
	private List<CrDefinicionPeriocidad> listCrDefinicionPeriocidad;

	/** El atributo o variable list of InMensajeRecepcion. */
	@OneToMany(mappedBy = "crProcesoPeriodico", targetEntity = CrNotificacion.class)
	private List<CrNotificacion> listOfCrNotificacion;

	/** El atributo o variable list of InMensajeRecepcion. */
	@OneToMany(mappedBy = "crProcesoPeriodico", targetEntity = CrProgramacionProceso.class)
	private List<CrProgramacionProceso> listCrProgramacionProceso;
}