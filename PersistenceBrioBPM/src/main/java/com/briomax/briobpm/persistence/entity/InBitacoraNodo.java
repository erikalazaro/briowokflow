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
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class InBitacoraNodo.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Dic 04, 2023 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "IN_BITACORA_NODO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InBitacoraNodo implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private InBitacoraNodoPK id;

	/** El atributo o variable descripcion Evento*/
	@Column(name = "DESCRIPCION_EVENTO", nullable = false, length = 60)
	private String descripcionEvento;
	
	/** El atributo o variable cve Usuario Evento*/
	@Column(name = "CVE_USUARIO_EVENTO", nullable = false, length = 30)
	private String cveUsuarioEvento;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name="CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
		@JoinColumn(name="VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false),
		@JoinColumn(name="CVE_INSTANCIA", referencedColumnName = "CVE_INSTANCIA", insertable = false, updatable = false),
		@JoinColumn(name="CVE_NODO", referencedColumnName = "CVE_NODO", insertable = false, updatable = false),
		@JoinColumn(name="ID_NODO", referencedColumnName = "ID_NODO", insertable = false, updatable = false),
		@JoinColumn(name="SECUENCIA_NODO", referencedColumnName = "SECUENCIA_NODO", insertable = false, updatable = false) })
	private InNodoProceso inNodoProceso;
}
