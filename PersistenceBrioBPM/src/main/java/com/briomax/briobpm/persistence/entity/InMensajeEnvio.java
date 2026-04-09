package com.briomax.briobpm.persistence.entity;

import java.util.Date;
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
 * El objetivo de la Class InMensajeEnvio.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Dic 01, 2023 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "IN_MENSAJE_ENVIO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"listOfInVariableEnvio"})
@EqualsAndHashCode(callSuper = false, exclude = {"listOfInVariableEnvio"})
public class InMensajeEnvio implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 3008509815141573530L;
	
	/** El atributo o variable Id*/
	@EmbeddedId
	private InMensajeEnvioPK id;

	/** El atributo o variable folio Mensaje Envio*/
	@Column(name = "FOLIO_MENSAJE_ENVIO", nullable = false, precision = 9, scale = 0)
	private Integer folioMensajeEnvio;
	
	/** El atributo o variable valores Referencia Envio*/
	@Column(name = "VALORES_REFERENCIA_ENVIO", length = 500)
	private String valoresReferenciaEnvio;
	
	/** El atributo o variable fecha Creacion*/
	@Column(name = "FECHA_CREACION", nullable = false, precision = 2, scale = 0)
	private Date fechaCreacion;
	
	/** El atributo o variable fecha Envio*/
	@Column(name = "FECHA_ENVIO")
	private Date fechaEnvio;
	
	/** El atributo o variable situacion Envio*/
	@Column(name = "SITUACION_ENVIO", nullable = false, length = 20)
	private String situacionEnvio;
	
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

	
	/** El atributo o variable list of InVariableEnvio. */
	@OneToMany(mappedBy = "inMensajeEnvio", targetEntity = InVariableEnvio.class)
	private List<InVariableEnvio> listOfInVariableEnvio;
}
