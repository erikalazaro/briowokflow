package com.briomax.briobpm.persistence.entity;

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
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class CorreoProceso.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Jul 22, 2024 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "CR_NOTIFICACION")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrNotificacion implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CrNotificacionPK id;
	
    @Column(name = "DESCRIPCION", nullable = false, length = 100)
    private String descripcion;

    @Column(name = "ASUNTO", nullable = false, length = 200)
    private String asunto;

    @Column(name = "CUERPO", nullable = false, length = 4000)
    private String cuerpo;
    
    @ManyToOne
	@JoinColumns({
	    @JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
	    @JoinColumn(name="CVE_LOCALIDAD", referencedColumnName = "CVE_LOCALIDAD", insertable = false, updatable = false),
	    @JoinColumn(name="CVE_IDIOMA", referencedColumnName = "CVE_IDIOMA", insertable = false, updatable = false),
	    @JoinColumn(name="CVE_PROCESO_PERIODICO", referencedColumnName = "CVE_PROCESO_PERIODICO", insertable = false, updatable = false),
	})
	private CrProcesoPeriodico crProcesoPeriodico;
    
    /** El atributo o variable list of CrDestinatarioCorreo. 
	@OneToMany(mappedBy = "crNotificacion", targetEntity = CrDestinatarioCorreo.class)
	private List<CrDestinatarioCorreo> listOfCrDestinatarioCorreo;*/

}
