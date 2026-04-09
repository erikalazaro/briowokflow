package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * El objetivo de la Interface IInFolioNodoHelper.java es ...
 * 
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Ene 31, 2024 6:05:58 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "NodoProcesoTO", title = "NodoProcesoTO")
@Data
@Builder
public class NodoProcesoTO implements Serializable {

	private static final long serialVersionUID = -564984068778186706L;

	@Schema(name = "estado", description = "Estado.", example = "EN_PROCESO", implementation = String.class)
	private String estado;

	@Schema(name = "fechaCreacion", description = "Fecha de Creación.", example = "2024-01-29", implementation = Date.class)
	private Date fechaCreacion;

	@Schema(name = "origen", description = "Origen.", example = "SISTEMA", implementation = String.class)
	private String origen;

	@Schema(name = "cveProcesoOrigen", description = "Clave del Proceso Origen.", example = "PROCESO_ORIGEN", implementation = String.class)
	private String cveProcesoOrigen;

	@Schema(name = "versionOrigen", description = "Versión del Proceso Origen.", example = "1.00", implementation = BigDecimal.class)
	private BigDecimal versionOrigen;

	@Schema(name = "cveInstanciaOrigen", description = "Clave de la Instancia Origen.", example = "INSTANCIA_ORIGEN", implementation = String.class)
	private String cveInstanciaOrigen;

	@Schema(name = "cveNodoOrigen", description = "Clave del Nodo Origen.", example = "NODO_ORIGEN", implementation = String.class)
	private String cveNodoOrigen;

	@Schema(name = "idNodoOrigen", description = "Id del Nodo Origen.", example = "2", implementation = Integer.class)
	private Integer idNodoOrigen;

	@Schema(name = "secuenciaNodoOrigen", description = "Secuencia del Nodo Origen.", example = "2", implementation = Integer.class)
	private Integer secuenciaNodoOrigen;

	@Schema(name = "rolCreador", description = "Rol del Creador.", example = "CREADOR", implementation = String.class)
	private String rolCreador;

	@Schema(name = "rolBloquea", description = "Rol que Bloquea.", example = "BLOQUEADOR", implementation = String.class)
	private String rolBloquea;

	@Schema(name = "usuarioBloquea", description = "Usuario que Bloquea.", example = "usuario_bloqueador", implementation = String.class)
	private String usuarioBloquea;

	@Schema(name = "fechaBloquea", description = "Fecha de Bloqueo.", example = "2024-01-30", implementation = Date.class)
	private Date fechaBloquea;

	@Schema(name = "fechaLimite", description = "Fecha Límite.", example = "2024-02-15", implementation = Date.class)
	private Date fechaLimite;

	@Schema(name = "fechaEstadoActual", description = "Fecha del Estado Actual.", example = "2024-02-01", implementation = Date.class)
	private Date fechaEstadoActual;

	@Schema(name = "fechaFinEspera", description = "Fecha de Fin de Espera.", example = "2024-02-10", implementation = Date.class)
	private Date fechaFinEspera;

	@Schema(name = "comentario", description = "Comentario.", example = "Este es un comentario.", implementation = String.class)
	private String comentario;
}