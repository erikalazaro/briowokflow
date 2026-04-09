package com.briomax.briobpm.persistence.entity;

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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * El objetivo de la Class StNodoProceso.java es ...
 * 
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Oct 22, 2024 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "ST_BOTON_NODO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {})
@EqualsAndHashCode(callSuper = false, exclude = {})
public class StBotonNodo implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 3008509815141573530L;

	/** El atributo o variable id. */
	@EmbeddedId
	private StBotonNodoPK id;

	/** El atributo o variable cve Nodo */
	@Column(name = "ORDEN_BOTON", nullable = false, precision = 3, scale = 0)
	private Integer ordenBoton;

	/** El atributo o variable cve Instancia */
	@Column(name = "ACCION_BOTON", nullable = false, length = 20)
	private String accionBoton;

	/** El atributo o variable cve Nodo */
	@Column(name = "ETIQUETA_BOTON", nullable = false, length = 30)
	private String etiquetaBoton;

	/** El atributo o variable cve Instancia */
	@Column(name = "REQUIERE_CONFIRMACION", nullable = false, length = 2)
	private String requiereConfirmacion;

	/** El atributo o variable cve Nodo */
	@Column(name = "MENSAJE_CONFIRMACION", length = 300)
	private String mensajeConfirmacion;

	/** El atributo o variable cve Instancia */
	@Column(name = "ACCION_NO_CONFIRMACION", length = 20)
	private String accionNoConfirmacion;

	/** El atributo o variable cve Instancia */
	@Column(name = "MENSAJE_NO_CONFIRMACION", length = 300)
	private String mensajeNoConfirmacion;

	/** El atributo o variable cve Nodo */
	@Column(name = "SERVICIO", length = 300)
	private String servicio;
	
	/** El atributo o variable stProceso. */
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
			@JoinColumn(name = "CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
			@JoinColumn(name = "VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false),
			@JoinColumn(name = "CVE_NODO", referencedColumnName = "CVE_NODO", insertable = false, updatable = false),
			@JoinColumn(name = "ID_NODO", referencedColumnName = "ID_NODO", insertable = false, updatable = false)})
	private StNodoProceso stNodoProceso;

}
