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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * El objetivo de la Class DatoAreaTrabajo.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Mar 07, 2024 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */

@Entity
@Table(name = "DATO_AREA_TRABAJO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"listOfDatoAtProcesoNodo"})
@EqualsAndHashCode(callSuper = false, exclude = {"listOfDatoAtProcesoNodo"})
public class DatoAreaTrabajo implements IEntity {
	
	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable id*/
	@EmbeddedId
	private DatoAreaTrabajoPK id;
	
	 @Column(name = "ORDEN_DATO", nullable = false, precision = 2, scale = 0)
    private Integer ordenDato;

    @Column(name = "ORIGEN_DATO", nullable = false, length = 20)
    private String origenDato;

    @Column(name = "ETIQUETA", length = 80)
    private String etiqueta;

    @Column(name = "VISIBLE", nullable = false, length = 2)
    private String visible;

    @Column(name = "COLOR", nullable = false, length = 2)
    private String color;

    @Column(name = "FILTRO", nullable = false, length = 2)
    private String filtro;

    @Column(name = "TIPO_CONTROL", nullable = false, length = 12)
    private String tipoControl;

    @Column(name = "TIPO_LISTA", length = 20)
    private String tipoLista;

    @Column(name = "CVE_LISTA_FILTRO", length = 60)
    private String cveListaFiltro;

    @Column(name = "COLUMNA_VALOR", length = 40)
    private String columnaValor;

    @Column(name = "COLUMNA_DESCRIPCION", length = 40)
    private String columnaDescripcion;

    @Column(name = "TABLA_LISTA", length = 200)
    private String tablaLista;

    @Column(name = "WHERE_LISTA", length = 200)
    private String whereLista;

    @Column(name = "MULTI_SELECCION", length = 2)
    private String multiSeleccion;

    @Column(name = "OPCION_TODOS", length = 2)
    private String opcionTodos;

    @Column(name = "ETIQUETA_TODOS", length = 80)
    private String etiquetaTodos;

    @Column(name = "ANCHO_COLUMNA", precision = 3, scale = 0)
    private Integer anchoColumna;
    
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
        @JoinColumn(name="CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
        @JoinColumn(name="VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false),
        @JoinColumn(name="CVE_AREA_TRABAJO", referencedColumnName = "CVE_AREA_TRABAJO", insertable = false, updatable = false),
        @JoinColumn(name="SECUENCIA_TARJETA", referencedColumnName = "SECUENCIA_TARJETA", insertable = false, updatable = false)
    })
    private TarjetaAreaTrabajo tarjetaAreaTrabajo;

    /** Asociacion bidireccional a tarjeta area trabajo. */
	@OneToMany(mappedBy = "datoAreaTrabajo", targetEntity = DatoAtProcesoNodo.class)
	private List<DatoAtProcesoNodo> listOfDatoAtProcesoNodo;
}
