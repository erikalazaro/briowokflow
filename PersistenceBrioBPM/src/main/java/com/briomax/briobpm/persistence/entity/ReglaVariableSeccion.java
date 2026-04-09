package com.briomax.briobpm.persistence.entity;



import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "REGLA_VARIABLE_SECCION")
@Data
@Builder
public class ReglaVariableSeccion implements IEntity {
	
	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ReglaVariableSeccionPK id;
	
	/** El atributo o variable APLICAR EN CAPTURA*/
	@Column(name = "APLICAR_EN_CAPTURA", nullable = false, length = 4)
	private String aplicarEnCaptura;
	
	/** El atributo o variable APLICAR EN GUARDADO*/
	@Column(name = "APLICAR_EN_GUARDADO", nullable = false, length = 4)
	private String aplicarEnGuardado;
	
	/** El atributo o variable APLICAR_EN_TERMINACION*/
	@Column(name = "APLICAR_EN_TERMINACION", nullable = false, length = 4)
	private String apicarEnTerminacion;
	
	/**El atributo o variable Resultado Logico*/
	@Column(name = "RESULTADO_LOGICO", nullable = false, length = 12)
	private String resultadoLogico;
	
	/**El atributo o variable Hbilitar*/
	@Column(name = "HABILITAR", nullable = false, length = 12)
	private String habilitar;
}
