/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class ProcesoSeguroTO.java es combinar los datos del proceso
 * con los datos de autenticación para realizar operaciones seguras.
 * @author RIGOBERTO OLVERA
 * @version 1.0 Fecha de creacion 06/10/2025
 * @since JDK 1.8
 */
@Schema(title = "ProcesoSeguroTO.", description = "Datos del Proceso con Autenticación Segura.")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProcesoSeguroTO extends ProcesoTO implements Serializable {

	/** serial Version UID. */
	private static final long serialVersionUID = -564984068778186707L;

	// === DATOS DE AUTENTICACIÓN ===
	
	/** El atributo o variable cve usuario. */
	@Schema(name = "cveUsuario", description = "Clave del Usuario.", example = "cveUsuario", implementation = String.class, required = true)
	private String cveUsuario;

	/** El atributo o variable cve usuario. */
	@Schema(name = "password", description = "password", example = "password", implementation = String.class, required = true)
	private String password;


	// === DATOS DEL PROCESO ===

	/** El atributo o variable cve proceso. 
	@Schema(name = "cveProceso", description = "Clave del Proceso.", example = "ATEN_INCI_TICK", implementation = String.class, required = true)
	private String cveProceso;
*/
	/** El atributo o variable datos adicionales. 
	@Schema(name = "valoresReferenciaEnvio", description = "Datos adicionales en formato JSON", example = "{\"campo1\":\"valor1\",\"campo2\":\"valor2\"}", implementation = String.class)
	private String valoresReferenciaEnvio;	*/

}
