package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Clase CrConsultaRepseTO.java es ...
 * 
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion May 10, 2024 6:05:58 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "CrConsultaRepseTO", title = "CrConsultaRepseTO")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrConsultaRepseTO implements Serializable {/**
	
	/** serial Version UID. */
	private static final long serialVersionUID = 1L;

    /** El atributo o variable CVE_ENTIDAD. */
    @Schema(name = "cveEntidad", description = "Clave de la entidad.", example = "", implementation = String.class)
    private String cveEntidad;

    /** El atributo o variable CVE_PROCESO. */
    @Schema(name = "cveProceso", description = "Clave del proceso.", example = "", implementation = String.class)
    private String cveProceso;
    
    /** El atributo o variable RFC Contratista. */
    @Schema(name = "rfcContratista", description = "RFC del Contratista.", example = "", implementation = String.class)
    private String rfcContratista;
    
    /** El atributo o variable NUMERO_CONTRATO. */
    @Schema(name = "numContrato", description = "Número del contrato.", example = "", implementation = String.class)
    private String numContrato;
    
    /** El atributo o variable FECHA_CONSULTA. */
    @Schema(name = "fechaConsulta", description = "Fecha de la consulta.", example = "")
    private String fechaConsulta;

    /** El atributo o variable resultadoConsulta. */
    @Schema(name = "resultadoConsulta", description = "Resultado de la Consulta.", example = "", implementation = String.class)
    private String resultadoConsulta;

    /** El atributo o variable razonSocial. */
    @Schema(name = "razonSocial", description = "Razón Social.", example = "", implementation = String.class)
    private String razonSocial;

    /** El atributo o variable folio. */
    @Schema(name = "folio", description = "Folio.", example = "", implementation = String.class)
    private String folio;

    /** El atributo o variable entidadMunicipio. */
    @Schema(name = "entidadMunicipio", description = "Entidad Municipio.", example = "", implementation = String.class)
    private String entidadMunicipio;

    /** El atributo o variable avisoFechaRegistro. */
    @Schema(name = "avisoFechaRegistro", description = "Aviso Fecha Registro.", example = "", implementation = String.class)
    private String avisoFechaRegistro;

    /** El atributo o variable imagenConsulta. */
    @Schema(name = "imagenConsulta", description = "Imagen Consulta.", example = "")
    private byte[] imagenConsulta;

	/** El atributo o variable descripcion Servicio. */
    @Schema(name = "descripcionServicio", description = "Descripcion Servicio.", example = "", implementation = String.class)
	private String descripcionServicio;
    
	@Schema(name = "boton", implementation = String.class)
	private String boton;
}