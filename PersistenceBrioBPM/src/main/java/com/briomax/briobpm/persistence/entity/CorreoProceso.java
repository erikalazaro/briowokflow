package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;
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
 * El objetivo de la Class CorreoProceso.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Dic 22, 2023 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "CORREO_PROCESO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"listOfDestinarioCorreo"})
@EqualsAndHashCode(callSuper = false, exclude = {"listOfDestinarioCorreo"})
public class CorreoProceso implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CorreoProcesoPK id;

    @Column(name = "NIVEL_CORREO", nullable = false, length = 20)
    private String nivelCorreo;

    @Column(name = "CVE_NODO", length = 40)
    private String cveNodo;

    @Column(name = "ID_NODO", precision = 5, scale = 0)
    private Integer idNodo;

    @Column(name = "DESCRIPCION", nullable = false, length = 100)
    private String descripcion;

    @Column(name = "ASUNTO", nullable = false, length = 200)
    private String asunto;

    @Column(name = "CUERPO", nullable = false, length = 4000)
    private String cuerpo;

    @Column(name = "CONDICION", length = 4000)
    private String condicion;
    
	/** El atributo o variable entidad. */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name = "CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
		@JoinColumn(name = "VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false),
		@JoinColumn(name = "CVE_NODO", referencedColumnName = "CVE_NODO", insertable = false, updatable = false),
		@JoinColumn(name = "ID_NODO", referencedColumnName = "ID_NODO", insertable = false, updatable = false)
	})
	private StNodoProceso stNodoProceso;
	
	/** El atributo o variable entidad. */
	@ManyToOne
	@JoinColumn(name = "CVE_EVENTO_CORREO", referencedColumnName = "CVE_EVENTO_CORREO", insertable = false, updatable = false)
	private EventoCorreo eventoCorreo;    

	@OneToMany(mappedBy = "correoProceso", targetEntity = DestinarioCorreo.class)
	private List<DestinarioCorreo> listOfDestinarioCorreo;
    
}
