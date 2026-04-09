package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class InNodoProcesoTO.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion 11/03/2024 05:14:09 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "InNodoProcesoTO.", description = "In Nodo Proceso DTO.")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class InNodoProcesoTO implements Serializable {

	/** serial Version UID. */
	private static final long serialVersionUID = 1L;
	
    /** El atributo o variable cve Entidad.*/
	@Schema(name = "cveEntidad", description = "Clave de la Entidad.", example = "EJEMPLO-01")
	private String cveEntidad;
	
	/** El atributo o variable cve Proceso*/
	@Schema(name = "cveProceso", description = "Clave del Proceso.", example = "PROCESO-01")
	private String cveProceso;
	
	/** El atributo o variable version*/
	@Schema(name = "version", description = "Versión.", example = "1.0")
	private BigDecimal version;
	
	/** El atributo o variable cve Instancia*/
	@Schema(name = "cveInstancia", description = "Clave de la Instancia.", example = "INSTANCIA-01")
	private String cveInstancia;
	
	/** El atributo o variable cve Nodo*/
	@Schema(name = "cveNodo", description = "Clave del Nodo.", example = "NODO-01")
	private String cveNodo;
	
	/** El atributo o variable id Nodo*/
	@Schema(name = "idNodo", description = "ID del Nodo.", example = "1")
	private BigDecimal idNodo;

	/** El atributo o variable secuencia Nodo*/
	@Schema(name = "secuenciaNodo", description = "Secuencia del Nodo.", example = "1")
	private Integer secuenciaNodo;

	/** El atributo o variable estado*/
	@Schema(name = "estado", description = "Estado.", example = "ACTIVO")
	private String estado;

	/** El atributo o variable fechaCreacion*/
	@Schema(name = "fechaCreacion", description = "Fecha de Creación.", example = "2022-03-10")
	private Date fechaCreacion;
	
	/** El atributo o variable origen*/
	@Schema(name = "origen", description = "Origen.", example = "SISTEMA")
	private String origen;
	
	/** El atributo o variable cve Proceso Origen*/
	@Schema(name = "cveProcesoOrigen", description = "Clave del Proceso Origen.", example = "PROCESO-01")
	private String cveProcesoOrigen;
	
	/** El atributo o variable version Origen*/
	@Schema(name = "versionOrigen", description = "Versión Origen.", example = "1.0")
	private BigDecimal versionOrigen;
	
	/** El atributo o variable cve Instancia Origen*/
	@Schema(name = "cveInstanciaOrigen", description = "Clave de la Instancia Origen.", example = "INSTANCIA-01")
	private String cveInstanciaOrigen;
	
	/** El atributo o variable cve Nodo Origen*/
	@Schema(name = "cveNodoOrigen", description = "Clave del Nodo Origen.", example = "NODO-01")
	private String cveNodoOrigen;
	
	/** El atributo o variable id Nodo Origen*/
	@Schema(name = "idNodoOrigen", description = "ID del Nodo Origen.", example = "1")
	private BigDecimal idNodoOrigen;
	
	/** El atributo o variable secuencia Nodo Origen*/
	@Schema(name = "secuenciaNodoOrigen", description = "Secuencia del Nodo Origen.", example = "1")
	private Integer secuenciaNodoOrigen;
		
	/** El atributo o variable rolCreador*/
	@Schema(name = "rolCreador", description = "Rol Creador.", example = "ADMIN")
	private String rolCreador;
	
	/** El atributo o variable cve Entidad Creadora*/
	@Schema(name = "cveEntidadCreadora", description = "Clave de la Entidad Creadora.", example = "EJEMPLO-01")
	private String cveEntidadCreadora;
	
	/** El atributo o variable cve Localidad Creadora*/
	@Schema(name = "cveLocalidadCreadora", description = "Clave de la Localidad Creadora.", example = "LOCALIDAD-01")
	private String cveLocalidadCreadora;
		
	/** El atributo o variable usuarioCreador*/
	@Schema(name = "usuarioCreador", description = "Usuario Creador.", example = "admin")
	private String usuarioCreador;
	
	/** El atributo o variable rolBloquea*/
	@Schema(name = "rolBloquea", description = "Rol que Bloquea.", example = "ADMIN")
	private String rolBloquea;
	
	/** El atributo o variable cve Entidad Bloquea*/
	@Schema(name = "cveEntidadBloquea", description = "Clave de la Entidad que Bloquea.", example = "EJEMPLO-01")
	private String cveEntidadBloquea;
	
	/** El atributo o variable cve Localidad Bloquea*/
	@Schema(name = "cveLocalidadBloquea", description = "Clave de la Localidad que Bloquea.", example = "LOCALIDAD-01")
	private String cveLocalidadBloquea;
		
	/** El atributo o variable usuarioBloquea*/
	@Schema(name = "usuarioBloquea", description = "Usuario que Bloquea.", example = "admin")
	private String usuarioBloquea;
	
	/** El atributo o variable fechaBloquea*/
	@Schema(name = "fechaBloquea", description = "Fecha de Bloqueo.", example = "2022-03-10")
	private Date fechaBloquea;
	
	/** El atributo o variable fechaLimite*/
	@Schema(name = "fechaLimite", description = "Fecha Límite.", example = "2022-03-10")
	private Date fechaLimite;
	
	/** El atributo o variable fechaFinEspera*/
	@Schema(name = "fechaFinEspera", description = "Fecha de Fin de Espera.", example = "2022-03-10")
	private Date fechaFinEspera;
	
	/** El atributo o variable fechaEstadoActual*/
	@Schema(name = "fechaEstadoActual", description = "Fecha del Estado Actual.", example = "2022-03-10")
	private Date fechaEstadoActual;
	
	/** El atributo o variable comentario*/
	@Schema(name = "comentario", description = "Comentario.", example = "Este es un comentario")
	private String comentario;
	
	/** El atributo o variable prioridad*/
	@Schema(name = "prioridad", description = "Prioridad.", example = "1")
	private Integer prioridad;

}
