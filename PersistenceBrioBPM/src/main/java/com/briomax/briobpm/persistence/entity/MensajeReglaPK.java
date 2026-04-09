package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MensajeReglaPK implements IPrimaryKey {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L; 
	
	/** El atributo o variable cve idioma. */
	@Column(name = "CVE_ENTIDAD", nullable = false, length = 40)
	private String cveEntidad;
	
	/** El atributo o variable cve idioma. */
	@Column(name = "CVE_PROCESO", nullable = false, length = 40)
	private String cveProceso;
	
	/** El atributo o variable cve idioma. */
	@Column(name = "VERSION", nullable = false, length = 5, precision = 5, scale = 2)
	private BigDecimal version;
	
	/** El atributo o variable cve idioma. */
	@Column(name = "CVE_REGLA", nullable = false, length = 40)
	private String cveRegla;
	
	/** El atributo o variable cve idioma. */
	@Column(name = "RESULTADO_LOGICO", nullable = false, length = 12)
	private String resultadoLogico;
	
	/** El atributo o variable cve idioma. */
	@Column(name = "TIPO_MENSAJE", nullable = false, length = 12)
	private String tipoMensaje;
	
	/** El atributo o variable cve idioma. */
	@Column(name = "CVE_IDIOMA", nullable = false, length = 40)
	private String cveIdioma;
}
