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
 * El objetivo de la Class StMensajeEnvio.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion D	ic 22, 2023 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "ST_MENSAJE_ENVIO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"listOfStVariableEnvio"})
@EqualsAndHashCode(callSuper = false, exclude = {"listOfStVariableEnvio"})

public class StMensajeEnvio implements IEntity {
	
	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private StMensajeEnvioPK id;
	
    @Column(name = "AMBITO_ENVIO", nullable = false, length = 20)
    private String ambitoEnvio;

    @Column(name = "DESCRIPCION_ENVIO", nullable = false, length = 100)
    private String descripcionEnvio;

    @Column(name = "INICIAR_PROCESO_DESTINO", nullable = false, length = 4)
    private String iniciarProcesoDestino;

    @Column(name = "CVE_ENTIDAD_DESTINO", length = 40)
    private String cveEntidadDestino;

    @Column(name = "CVE_PROCESO_DESTINO", length = 40)
    private String cveProcesoDestino;

    @Column(name = "VERSION_DESTINO", precision = 5, scale = 2)
    private BigDecimal versionDestino;

    @Column(name = "CVE_NODO_DESTINO", length = 40)
    private String cveNodoDestino;

    @Column(name = "ID_NODO_DESTINO", precision = 5, scale = 0)
    private BigDecimal idNodoDestino;

    @Column(name = "VARIABLES_REFERENCIA_ENVIO", length = 500)
    private String variablesReferenciaEnvio;
    
	/** El atributo o variable areaTrabajo. */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name="CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
		@JoinColumn(name="VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false),
		@JoinColumn(name="CVE_NODO", referencedColumnName = "CVE_NODO", insertable = false, updatable = false),
		@JoinColumn(name="ID_NODO", referencedColumnName = "ID_NODO", insertable = false, updatable = false)
	})
	private StNodoProceso stNodoProceso;
	
	/** Asociacion bidireccional a StVariableEnvio. */
	@OneToMany(mappedBy = "stMensajeEnvio", targetEntity = StVariableEnvio.class)
	private List<StVariableEnvio> listOfStVariableEnvio;
}
