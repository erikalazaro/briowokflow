package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Interface NodoTO.java es ...
 * 
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Ene 30, 2024 6:05:58 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "NodoTO", title = "NodoTO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NodoTO implements Serializable {

	private static final long serialVersionUID = -564984068778186706L;

	@Schema(name = "idProceso", description = "Id del Proceso.", example = "IDENTIFICADOR DEL PROCESO QUE EJECUTA AL PRESENTE", implementation = String.class)
	private String idProceso;

	@Schema(name = "cveProceso", description = "Clave del Proceso.", example = "CLAVE DEL PROCESO", implementation = String.class)
	private String cveProceso;

	@Schema(name = "version", description = "Versión del proceso", example = "1.00", implementation = BigDecimal.class)
	private BigDecimal version;

	@Schema(name = "cveInstancia", description = "Clave de la instancia.", example = "", implementation = String.class)
	private String cveInstancia;

	@Schema(name = "cveNodo", description = "Clave del Nodo.", example = "", implementation = String.class)
	private String cveNodo;

	@Schema(name = "idNodo", description = "Id del Nodo.", example = "1", implementation = Integer.class)
	private Integer idNodo;

	@Schema(name = "secuenciaNodo", description = "Secuencia del Nodo.", example = "1", implementation = Integer.class)
	private Integer secuenciaNodo;

	@Schema(name = "tipoGeneracion", description = "Tipo de generación.", example = "", implementation = String.class)
	private String tipoGeneracion;

	@Schema(name = "tipoNodoSiguiente", description = "Tipo de Nodo Siguiente.", example = "", implementation = String.class)
	private String tipoNodoSiguiente;

	@Schema(name = "cveNodoInicio", description = "Clave del Nodo Actual.", example = "CLAVE DEL NODO ACTUAL", implementation = String.class)
	private String cveNodoInicio;

	@Schema(name = "idNodoInicio", description = "Id del Nodo Actual.", example = "IDENTIFICADOR DEL NODO ACTUAL", implementation = Integer.class)
	private Integer idNodoInicio;

	@Schema(name = "rol", description = "Rol.", example = "ADMINISTRADOR", implementation = String.class)
	private String rol;

	@Schema(name = "folioMensajeEnvio", description = "Folio del Mensaje de Envío.", example = "12345", implementation = Integer.class)
	private Integer folioMensajeEnvio;

	@Schema(name = "cveVariable", description = "clave variable.", example = "", implementation = String.class)
	private String cveVariable;

	@Schema(name = "ocurrencia", description = "Ocurrencia.", example = "125", implementation = Integer.class)
	private Integer ocurrencia;

	@Schema(name = "cveEventoCorreo", description = "Clave del Evento de Correo.", example = "EVENTO_CORREO_001", implementation = String.class)
	private String cveEventoCorreo;

	@Schema(name = "cveUsuarioDestinatario", description = "Clave del Usuario Destinatario.", example = "USUARIO_DEST_001", implementation = String.class)
	private String cveUsuarioDestinatario;

	@Schema(name = "cveRolDestinatario", description = "Clave del Rol Destinatario.", example = "ROL_DEST_001", implementation = String.class)
	private String cveRolDestinatario;
	
	@Schema(name = "usoSeccion", description = "Uso de la sección según sea web o movil.", example = "APP", implementation = String.class)
	private String usoSeccion;

	@Schema(name = "tipoConsulta", description = "Tipo de consulta puede ser query.", example = "select usuario from ...", implementation = String.class)
	private String tipoConsulta;
	
	@Schema(name = "sentenciaUsuarioAsignado", description = "Sentencia para obtener el usuario asignado.", example = "usuario", implementation = String.class)
	private String sentenciaUsuarioAsignado;
	
	@Schema(name = "usuarioAsignado", description = "clave del usuario asignado.", example = "usuario.uno")
	private List<String> cveUsuarioAsignado;

	@Schema(name = "cveEntidad", description = "entidad", example = "usuario.uno", implementation = String.class)
	private String cveEntidad;
	
	@Schema(name = "origen", description = "origen", example = "MENSAJE_SERVICIO", implementation = String.class)
	private String origen;
	
	@Schema(name = "pathImage", description = "ruta evidencia REPSE", example = "imagenrepse.png", implementation = String.class)
	private String pathImage;	
	
}
