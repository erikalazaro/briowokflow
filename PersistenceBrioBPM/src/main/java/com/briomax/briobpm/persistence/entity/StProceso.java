/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * El objetivo de la Class StProceso.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jun 25, 2020 12:24:43 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "ST_PROCESO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"listOfAreaTrabajo", "listOfStNodoProceso", "listOfStProceso", "listOfInFolioProceso", 
		"listOfInProceso", "listOfStSeccionProceso", "listOfStDocumentoProceso", "listOfComposicionCorreo", "listOfStNodoInicioProceso", "listOfStReglaValidacionProceso", "listOfStTareaProceso" })
@EqualsAndHashCode(callSuper = false, exclude = {"listOfAreaTrabajo", "listOfStNodoProceso", "listOfStProceso", "listOfInFolioProceso", 
		"listOfInProceso", "listOfStSeccionProceso", "listOfStDocumentoProceso", "listOfComposicionCorreo", "listOfStNodoInicioProceso", "listOfStReglaValidacionProceso", "listOfStTareaProceso"})
public class StProceso implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 3008509815141573530L;

	/** El atributo o variable id. */
	@EmbeddedId
	private StProcesoPK id;

	/** El atributo o variable descripcion. */
	@Column(name = "DESCRIPCION", nullable = false, length = 100)
	private String descripcion;

	/** El atributo o variable situacion. */
	@Column(name = "SITUACION", nullable = false, length = 12)
	private String situacion;

	/** El atributo o variable instancias dia. */
	@Column(name = "INSTANCIAS_DIA", length = 5, precision = 5, scale = 0)
	private BigDecimal instanciasDia;

	/** El atributo o variable instancias semana. */
	@Column(name = "INSTANCIAS_SEMANA", length = 5, precision = 5, scale = 0)
	private BigDecimal instanciasSemana;

	/** El atributo o variable instancias mes. */
	@Column(name = "INSTANCIAS_MES", length = 5, precision = 5, scale = 0)
	private BigDecimal instanciasMes;

	/** El atributo o variable id nodo inicio. */
	@Column(name = "ID_NODO_INICIO", length = 5, precision = 5, scale = 0)
	private Integer idNodoInicio;

	// "cveNodoInicio" (column "CVE_NODO_INICIO") is not defined by itself because used as FK in a link
	/** El atributo o variable id nodo fin. */
	@Column(name = "ID_NODO_FIN", length = 5, precision = 5, scale = 0)
	private Integer idNodoFin;

	/** El atributo o variable color Rgb Relleno*/
	@Column(name = "COLOR_RGB_RELLENO", length = 20)
	private String colorRgbRelleno;
	
	/** El atributo o variable color Rgb Contorno*/
	@Column(name = "COLOR_RGB_CONTORNO", length = 20)
	private String colorRgbContorno;
	
	/** El atributo o variable color Rgb Sombra*/
	@Column(name = "COLOR_RGB_SOMBRA", length = 20)
	private String colorRgbSombra;
	
	/** El atributo o variable del icono del proceso*/
	@Column(name = "ICONO_PROCESO")
	private byte[] iconoProceso;	
	
	// "cveNodoFin" (column "CVE_NODO_FIN") is not defined by itself because used as FK in a link
	/** El atributo o variable cve nodo inicio. */
	@ManyToOne
	@JoinColumn(name = "CVE_NODO_INICIO", referencedColumnName = "CVE_NODO")
	private TipoNodo cveNodoInicio;

	/** El atributo o variable cve nodo fin. */
	@ManyToOne
	@JoinColumn(name = "CVE_NODO_FIN", referencedColumnName = "CVE_NODO")
	private TipoNodo cveNodoFin;

	/** El atributo o variable entidad. */
	@ManyToOne
	@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false)
	private Entidad entidad;
	
	/** Asociacion bidireccional a AreaTrabajo. */
	@OneToMany(mappedBy = "stProceso", targetEntity = AreaTrabajo.class)
	private List<AreaTrabajo> listOfAreaTrabajo;

	/** Asociacion bidireccional a StNodoProceso. */
	@OneToMany(mappedBy = "stProceso", targetEntity = StNodoProceso.class)
	private List<StNodoProceso> listOfStNodoProceso;
	
	/** Asociacion bidireccional a StVariableProceso. */
	@OneToMany(mappedBy = "stProceso", targetEntity = StVariableProceso.class)
	private List<StVariableProceso> listOfStProceso;
	
	/** Asociacion bidireccional a InFolioProceso. */
	@OneToMany(mappedBy = "stProceso", targetEntity = InFolioProceso.class)
	private List<InFolioProceso> listOfInFolioProceso;
	
	/** Asociacion bidireccional a InProceso. */
	@OneToMany(mappedBy = "stProceso", targetEntity = InProceso.class)
	private List<InProceso> listOfInProceso;
	
	/** Asociacion bidireccional a StSeccionProceso. */
	@OneToMany(mappedBy = "stProceso", targetEntity = StSeccionProceso.class)
	private List<StSeccionProceso> listOfStSeccionProceso;
		
	/** Asociacion bidireccional a StDocumentoProceso. */
	@OneToMany(mappedBy = "stProceso", targetEntity = StDocumentoProceso.class)
	private List<StDocumentoProceso> listOfStDocumentoProceso;
	
	/** Asociacion bidireccional a StDocumentoProceso. */
	@OneToMany(mappedBy = "stProceso", targetEntity = ComposicionCorreo.class)
	private List<ComposicionCorreo> listOfComposicionCorreo;
	
	/** Asociacion bidireccional a StDocumentoProceso. */
	@OneToMany(mappedBy = "stProceso", targetEntity = StNodoInicioProceso.class)
	private List<StNodoInicioProceso> listOfStNodoInicioProceso;
	
	/** Asociacion bidireccional a StReglaValidacionProceso. */
	@OneToMany(mappedBy = "stProceso", targetEntity = StReglaValidacionProceso.class)
	private List<StReglaValidacionProceso> listOfStReglaValidacionProceso;
	
	/** Asociacion bidireccional a StReglaValidacionProceso. */
	@OneToMany(mappedBy = "stProceso", targetEntity = StTareaProceso.class)
	private List<StTareaProceso> listOfStTareaProceso;

}
