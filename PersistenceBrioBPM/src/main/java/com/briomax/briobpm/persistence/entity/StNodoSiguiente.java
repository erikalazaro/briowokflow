package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;

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
/**
 * El objetivo de la Class StNodoSiguiente.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Nov 24, 2023 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "ST_NODO_SIGUIENTE")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, exclude = {})

public class StNodoSiguiente implements IEntity {
	
	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private StNodoSiguientePK id;
	
	/** El atributo o variable cve Instancia*/
	@Column(name = "CVE_NODO_SIGUIENTE",nullable = false, length = 40)
	private String cveNodoSiguiente;
	
	/** El atributo o variable cve Nodo*/
	@Column(name = "ID_NODO_SIGUIENTE",nullable = false, precision = 5, scale = 0)
	private Integer idNodoSiguiente;
	
	/** El atributo o variable cve Instancia*/
	@Column(name = "CONDICION", length = 500)
	private String condicion;
	
	/** El atributo o variable cve Instancia*/
	@Column(name = "TIPO_CONSULTA", length = 20)
	private String tipoConsulta;
	
	/** El atributo o variable cve Instancia*/
	@Column(name = "SENTENCIA_USUARIO_ASIGNADO", length = 4000)
	private String sentenciaUsuarioAsignado;
	
	/** El atributo o variable stNodoProceso. */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name="CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
		@JoinColumn(name="VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false),
		@JoinColumn(name="CVE_NODO", referencedColumnName = "CVE_NODO", insertable = false, updatable = false),
		@JoinColumn(name="ID_NODO", referencedColumnName = "ID_NODO", insertable = false, updatable = false)
	})
	private StNodoProceso stNodoProceso;
	
	/** El atributo o variable stNodoProceso. */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name="CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
		@JoinColumn(name="VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false),
		@JoinColumn(name="CVE_NODO_SIGUIENTE", referencedColumnName = "CVE_NODO", insertable = false, updatable = false),
		@JoinColumn(name="ID_NODO_SIGUIENTE", referencedColumnName = "ID_NODO", insertable = false, updatable = false)
	})
	private StNodoProceso stNodoProcesoSiguiente;
}
