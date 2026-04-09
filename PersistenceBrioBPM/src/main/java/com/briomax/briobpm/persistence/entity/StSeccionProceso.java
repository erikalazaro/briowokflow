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
import lombok.ToString;

/**
 * El objetivo de la Class StProceso.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Nov 21, 2023 12:24:43 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "ST_SECCION_PROCESO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"listOfStSeccionNodo"})
@EqualsAndHashCode(callSuper = false, exclude = {"listOfStSeccionNodo"})
public class StSeccionProceso implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 3008509815141573530L;

	/** El atributo o variable id. */
	@EmbeddedId
	private StSeccionProcesoPK id;

	/** El atributo o variable descripcion. */
	@Column(name = "DESCRIPCION", nullable = false, length = 100)
	private String descripcion;

	/** El atributo o variable etiqueta. */
	@Column(name = "ETIQUETA", nullable = false, length = 60)
	private String etiqueta;

	/** El atributo o variable tipoPresentacion. */
	@Column(name = "TIPO_PRESENTACION", length = 12)
	private String tipoPresentacion;

	/** El atributo o variable crearRenglones. */
	@Column(name = "CREAR_RENGLONES", length = 2)
	private String crearRenglones;

	/** El atributo o variable tipoCreacionRenglones. */
	@Column(name = "TIPO_CREACION_RENGLONES", length = 12)
	private String tipoCreacionRenglones;

	/** El atributo o variable numeroRenglones. */
	@Column(name = "NUMERO_RENGLONES", precision = 5, scale = 0)
	private Integer numeroRenglones;

	/** El atributo o variable minimoRenglones. */
	@Column(name = "MINIMO_RENGLONES", precision = 5, scale = 0)
	private Integer minimoRenglones;

	/** El atributo o variable maximoRenglones. */
	@Column(name = "MAXIMO_RENGLONES", precision = 5, scale = 0)
	private Integer maximoRenglones;

	/** El atributo o variable contenido. */
	@Column(name = "CONTENIDO", length = 20)
	private String contenido;
	
	/** El atributo o variable stProceso. */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name="CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
		@JoinColumn(name="VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false)
		})
	private StProceso stProceso;
	
	/** El atributo o variable list of StSeccionNodo. */
	@OneToMany(mappedBy = "stSeccionProceso", targetEntity = StSeccionNodo.class)
	private List<StSeccionNodo> listOfStSeccionNodo;
}
