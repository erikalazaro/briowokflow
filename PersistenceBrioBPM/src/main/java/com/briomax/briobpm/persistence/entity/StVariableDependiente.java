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

@Entity
@Table(name = "ST_VARIABLE_DEPENDIENTE")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StVariableDependiente implements IEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable id. */
	@EmbeddedId
	private StVariableDependientePK id;
		
	/** El atributo o variable seccion selec.*/
	@Column(name = "SECCION_SELECT", nullable = true, length = 100)
	private String seccionSelect;
	
	/** El atributo o variable seccion from.*/
	@Column(name = "SECCION_FROM", nullable = true, length = 200)
	private String seccionFrom;
	
	/** El atributo o variable seccion where.*/
	@Column(name = "SECCION_WHERE", nullable = true, length = 300)
	private String seccionWhere;
	
	/** El atributo o variable StVariableDependiente. */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name="CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
		@JoinColumn(name="VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false),
		@JoinColumn(name="CVE_NODO", referencedColumnName = "CVE_NODO", insertable = false, updatable = false),
		@JoinColumn(name="ID_NODO", referencedColumnName = "ID_NODO", insertable = false, updatable = false),
		@JoinColumn(name="CVE_SECCION", referencedColumnName = "CVE_SECCION", insertable = false, updatable = false),
		@JoinColumn(name="CVE_VARIABLE", referencedColumnName = "CVE_VARIABLE", insertable = false, updatable = false)
	})
	private  StVariableSeccion stVariableSeccion;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name="CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
		@JoinColumn(name="VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false),
		@JoinColumn(name="CVE_NODO", referencedColumnName = "CVE_NODO", insertable = false, updatable = false),
		@JoinColumn(name="ID_NODO", referencedColumnName = "ID_NODO", insertable = false, updatable = false),
		@JoinColumn(name="CVE_SECCION_DEPENDIENTE", referencedColumnName = "CVE_SECCION", insertable = false, updatable = false),
		@JoinColumn(name="CVE_VARIABLE_DEPENDIENTE", referencedColumnName = "CVE_VARIABLE", insertable = false, updatable = false)
	})
	private  StVariableSeccion stVariableSeccion2;
	
}






