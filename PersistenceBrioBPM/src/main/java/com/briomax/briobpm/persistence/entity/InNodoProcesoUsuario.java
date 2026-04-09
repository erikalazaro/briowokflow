/**
 * 
 */
package com.briomax.briobpm.persistence.entity;

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
 * El objetivo de la Class StProceso.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Nov 21, 2023 12:24:43 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "IN_NODO_PROCESO_USUARIO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {})
@EqualsAndHashCode(callSuper = false)
public class InNodoProcesoUsuario implements IEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable id. */
	@EmbeddedId
	private InNodoProcesoUsuarioPK id;
	
	/** El atributo o variable IN_NODO_PROCESO. */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name="CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
		@JoinColumn(name="VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false),
		@JoinColumn(name="CVE_INSTANCIA", referencedColumnName = "CVE_INSTANCIA", insertable = false, updatable = false),
		@JoinColumn(name="CVE_NODO", referencedColumnName = "CVE_NODO", insertable = false, updatable = false),
		@JoinColumn(name="ID_NODO", referencedColumnName = "ID_NODO", insertable = false, updatable = false),
		@JoinColumn(name="SECUENCIA_NODO", referencedColumnName = "SECUENCIA_NODO", insertable = false, updatable = false)
	})
	private InNodoProceso inNodoProceso;
	
	/** El atributo o variable USUARIO. */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name="CVE_USUARIO", referencedColumnName = "CVE_USUARIO", insertable = false, updatable = false)
	})
	private Usuario usuario;

}




















