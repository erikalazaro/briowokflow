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
 * El objetivo de la Class StNodoInicioProceso.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Feb 16, 2024 12:24:43 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "ST_NODO_INICIO_PROCESO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StNodoInicioProceso implements IEntity {
	
	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable id. */
	@EmbeddedId
	private StNodoInicioProcesoPK id;
	
	/** El atributo o variable descripcion. */
	@Column(name = "VARIABLES_REFERENCIA_REC", nullable = true, length = 500)
	private String variablesReferenciaRec;

	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name="CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
		@JoinColumn(name="VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false)
	})
	private StProceso stProceso;
}
