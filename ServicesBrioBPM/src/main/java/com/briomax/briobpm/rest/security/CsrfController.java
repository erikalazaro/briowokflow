/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.rest.security;

import org.springframework.http.MediaType;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class CsrfController.java es ...
 * @author RIGOBERTO OLVERA
 * @version 1.0 Fecha de creacion 28/01/2020 05:40:23 PM Modificaciones:
 * @since JDK 1.8
 */
@Tag(name = "CSRF", description = "Cross Site Request Forgery.")
@RestController
@Slf4j
public class CsrfController {

	/**
	 * Csrf.
	 * @param token el token.
	 * @return el csrf token.
	 */
	@Operation(operationId = "csrf", summary = "Obtener Token CSRF", description = "Obtener Token CSRF", hidden = true)
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation.",
				content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
						schema = @Schema(implementation = CsrfToken.class))}),
		@ApiResponse(responseCode = "400", ref = "BadRequestError"),
		@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
		@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
		@ApiResponse(responseCode = "404", ref = "NotFoundError"),
		@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
		@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
		@ApiResponse(responseCode = "500", ref = "InternalServerError")
	})
	@RequestMapping("/csrf")
	public CsrfToken csrf(CsrfToken token) {
		log.info("Token: {}", token.getToken());
		return token;
	}

}
