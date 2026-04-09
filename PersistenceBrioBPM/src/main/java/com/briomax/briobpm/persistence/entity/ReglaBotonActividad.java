package com.briomax.briobpm.persistence.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.briomax.briobpm.persistence.entity.CrConsultaRepse.CrConsultaRepseBuilder;
import com.briomax.briobpm.persistence.entity.ReglaProceso.ReglaProcesoBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "REGLA_BOTON_ACTIVIDAD")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReglaBotonActividad implements IEntity {
	
	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ReglaBotonActividadPK id;
	
	/** El atributo o variable cve Regla*/
	@Column(name = "ORDEN_APLICACION", nullable = false,  precision = 2 )
	private Integer ordenAplicacion;
	
	/** El atributo o variable cve Proceso*/
	@Column(name = "DESCRIPCION_REGLA", nullable = false, length = 100)
	private String descripcionRegla;
	
	/** El atributo o variable version*/
	@Column(name = "TIPO_EXPRESION", nullable = false, length = 20)
	private String tipoExpresion;
	
	/** El atributo o variable cve Regla*/
	@Column(name = "EXPRESION", nullable = false, length = 1500)
	private String expresion;
	
	/** El atributo o variable tipo Query*/
	@Column(name = "TIPO_QUERY", nullable = false, length = 10)
	private String tipoQuery;
	
	/** El atributo o variable resultado Logico*/
	@Column(name = "RESULTADO_LOGICO", nullable = false, length = 12)
	private String resultadoLogico;
	
	/** El atributo o variable codigo mensaje error*/
	@Column(name = "CODIGO_MENSAJE_ERROR", length = 69)
	private String codMjsError;
	
	/** El atributo o variable origen Formulario*/
	@Column(name = "ORIGEN_FORMULARIO", nullable = false, length = 10)
	private String origenFormulario;
	
}
