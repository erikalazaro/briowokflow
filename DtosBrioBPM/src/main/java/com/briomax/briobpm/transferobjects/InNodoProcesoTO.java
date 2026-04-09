package com.briomax.briobpm.transferobjects;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class ProcesoTO.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Nov 17 12:29:01 PM :
 * @since JDK 1.8
 */
@Schema(title = "InNodoProcesoTO.", description = "In Nodo Proceso.")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InNodoProcesoTO implements Serializable {
	
	/** El atributo o variable cve Entidad.*/
	@Schema(name = "CVE_ENTIDAD", description = "", example = "", implementation = String.class)
	private String cveEntidad;
	
	/** El atributo o variable cve Proceso*/
	@Schema(name = "CVE_PROCESO", description = "", example = "", implementation = String.class)
	private String cveProceso;
	
	/** El atributo o variable version*/
	@Schema(name = "VERSION", nullable = false, description = "", example = "", implementation = String.class)
	private BigDecimal version;
	
	/** El atributo o variable cve Instancia*/
	@Schema(name = "CVE_INSTANCIA", description = "", example = "", implementation = String.class)
	private String cveInstancia;
	
	/** El atributo o variable cve Nodo*/
	@Schema(name = "CVE_NODO", description = "", example = "", implementation = String.class)
	private String cveNodo;
	
	/** El atributo o variable id Nodo*/
	@Schema(name = "ID_NODO", description = "", example = "", implementation = String.class)
	private BigDecimal idNodo;
	
	/** El atributo o variable folio*/
	@Schema(name = "SECUENCIA_NODO", description = "", example = "", implementation = String.class)
	private Integer secuenciaNodo;
	
	/** El atributo o variable estado*/
	@Schema(name = "ESTADO", description = "", example = "", implementation = String.class)
	private String estado;

	/** El atributo o variable estado*/
	@Schema(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	/** El atributo o variable estado*/
	@Schema(name = "ORIGEN", description = "", example = "", implementation = String.class)
	private String origen;
	
	/** El atributo o variable estado*/
	@Schema(name = "CVE_PROCESO_ORIGEN", description = "", example = "", implementation = String.class)
	private String cveProcesoOrigen;
	
	/** El atributo o variable estado*/
	@Schema(name = "VERSION_ORIGEN", description = "", example = "", implementation = String.class)
	private String versionOrigen;
	
	/** El atributo o variable estado*/
	@Schema(name = "CVE_INSTANCIA_ORIGEN", description = "", example = "", implementation = String.class)
	private String cveInstanciaOrigen;
	
	/** El atributo o variable estado*/
	@Schema(name = "CVE_NODO_ORIGEN", description = "", example = "", implementation = String.class)
	private String cveNodoOrigen;
	
	/** El atributo o variable estado*/
	@Schema(name = "ID_NODO_ORIGEN", description = "", example = "", implementation = String.class)
	private BigDecimal idNodoOrigen;
	
	/** El atributo o variable estado*/
	@Schema(name = "SECUENCIA_NODO_ORIGEN", description = "", example = "", implementation = String.class)
	private String secuenciaNodoOrigen;
		
	/** El atributo o variable estado*/
	@Schema(name = "ROL_CREADOR", description = "", example = "", implementation = String.class)
	private String rolCreador;
	
	/** El atributo o variable estado*/
	@Schema(name = "CVE_ENTIDAD_CREADORA", description = "", example = "", implementation = String.class)
	private String cveEntidadCreadora;
	
	/** El atributo o variable estado*/
	@Schema(name = "CVE_LOCALIDAD_CREADORA", description = "", example = "", implementation = String.class)
	private String cveLocalidadCreadora;
		
	/** El atributo o variable estado*/
	@Schema(name = "USUARIO_CREADOR", description = "", example = "", implementation = String.class)
	private String usuarioCreador;
	
	/** El atributo o variable estado*/
	@Schema(name = "ROL_BLOQUEA", description = "", example = "", implementation = String.class)
	private String rolBloquea;
		
	/** El atributo o variable estado*/
	@Schema(name = "USUARIO_BLOQUEA", description = "", example = "", implementation = String.class)
	private String usuarioBloquea;
	
	/** El atributo o variable estado*/
	@Schema(name = "FECHA_BLOQUEA")
	private Date fechaBloquea;
	
	/** El atributo o variable estado*/
	@Schema(name = "FECHA_LIMITE")
	private Date fechaLimite;
	
	/** El atributo o variable estado*/
	@Schema(name = "FECHA_FIN_ESPERA")
	private Date fechaFinEspera;
	
	/** El atributo o variable estado*/
	@Schema(name = "FECHA_ESTADO_ACTUAL")
	private Date fechaEstadoActual;
	
	/** El atributo o variable estado*/
	@Schema(name = "COMENTARIO", description = "", example = "", implementation = String.class)
	private String comentario;
}
