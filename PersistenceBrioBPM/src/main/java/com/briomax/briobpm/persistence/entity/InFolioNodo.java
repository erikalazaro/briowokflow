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
 * El objetivo de la Class InFolioNodo.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Nov 07, 2023 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "IN_FOLIO_NODO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InFolioNodo implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 3008509815141573530L;
	
	/** El atributo o variable id*/
	@EmbeddedId
	private InFolioNodoPK id;

	/** El atributo o variable folio*/
	@Column(name = "FOLIO", nullable = false, precision = 5, scale = 0)
	private Integer folio;
	
	/** El atributo o variable stProceso. */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name="CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
		@JoinColumn(name="VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false),
		@JoinColumn(name="CVE_NODO", referencedColumnName = "CVE_NODO", insertable = false, updatable = false),
		@JoinColumn(name="ID_NODO", referencedColumnName = "ID_NODO", insertable = false, updatable = false)
	})
	private StNodoProceso stNodoProceso;
}
