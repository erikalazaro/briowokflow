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
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DATO_AT_PROCESO_NODO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DatoAtProcesoNodo implements IEntity {
	
	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DatoAtProcesoNodoPK id;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
        @JoinColumn(name="CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
        @JoinColumn(name="VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false),
        @JoinColumn(name="CVE_AREA_TRABAJO", referencedColumnName = "CVE_AREA_TRABAJO", insertable = false, updatable = false),
        @JoinColumn(name="SECUENCIA_TARJETA", referencedColumnName = "SECUENCIA_TARJETA", insertable = false, updatable = false),
        @JoinColumn(name="SECUENCIA_DATO", referencedColumnName = "SECUENCIA_DATO", insertable = false, updatable = false)
    })
    private DatoAreaTrabajo datoAreaTrabajo;
    
}
