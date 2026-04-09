
package com.briomax.briobpm.rest.logback;

import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.classic.Logger;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Logger", description = "Log de la Aplicación.")
@RestController
public class LoggerController {

	/** La Constante rootlogger. */
	private static final Logger rootlogger = (Logger) LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);

	/** El atributo o variable circular appender. */
	CircularAppender circularAppender = (CircularAppender) rootlogger.getAppender("CircularBuffer");

	/**
	 * logs. Regresa logs en buffer
	 * @return el logn
	 */
	@Operation(summary = "Log", description = "Log.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation.",
				content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
						schema = @Schema(implementation = String.class))}),
		@ApiResponse(responseCode = "400", ref = "BadRequestError"),
		@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
		@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
		@ApiResponse(responseCode = "404", ref = "NotFoundError"),
		@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
		@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
		@ApiResponse(responseCode = "500", ref = "InternalServerError")
	})
	@GetMapping("/logs")
	public ResponseEntity<String> logs() {
		return new ResponseEntity<String>(circularAppender.getLogContent(), HttpStatus.OK);
	}

}
