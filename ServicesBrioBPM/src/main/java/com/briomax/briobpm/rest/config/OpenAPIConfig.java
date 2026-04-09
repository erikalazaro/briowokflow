/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.rest.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

/**
 * The objetive the Class OpenAPIConfig.java is ...
 * @author Rigoberto Olvera (Briomax Consulting).
 * @version 1.0 date creation Aug 5, 2020 7:05:02 PM
 * @since JDK 1.8
 */
@Configuration
public class OpenAPIConfig {

	/**
	 * Custom open API.
	 * @param appDesciption the app desciption
	 * @param appVersion the app version
	 * @return the open API
	 */
	@Bean
	public OpenAPI customOpenAPI(@Value("${application-description}") String appDesciption,
		@Value("${application-version}") String appVersion) {
		OpenAPI openApi = new OpenAPI()
				.info(new Info()
					.description(appDesciption)
					.version(appVersion)
					.title("BrioWorkflow")
					.termsOfService("http://swagger.io/terms/")
					.contact(new Contact()
						.name("Briomax Workflow")
						.email("brioworkflow@briomax.com"))
					.license(new License()
						.name("Apache 2.0")
						.url("http://www.apache.org/licenses/LICENSE-2.0.html")
						)
					);

			openApi.components(new Components()
				.addSecuritySchemes("cookieAuth", new SecurityScheme()
					.type(SecurityScheme.Type.APIKEY)
					.in(SecurityScheme.In.COOKIE).name("JSESSIONID")
				)
				// 200
				.addResponses("OK", new ApiResponse()
					.description("OK - Successful operation."))
				// 201
				.addResponses("Created", new ApiResponse()
					.description("Created - The petition has been completed and has resulted" +
							" in the creation of a new resource."))
				// 202
				.addResponses("Accepted", new ApiResponse()
					.description("Accepted - The request has been accepted for processing but has" +
							" not been completed. The request may possibly not be met," +
							" as it may be prohibited or prohibited when processing takes place."))
				// 204
				.addResponses("NoContent", new ApiResponse()
					.description("No Content - The server successfully processed the request," +
						" and is not returning any content."))
				// 400
				.addResponses("BadRequestError", new ApiResponse()
					.description("Bad Request - The request contains erroneous syntax and should not be repeated."))
				// 401
				.addResponses("UnauthorizedError", new ApiResponse()
					.description("Unauthorized - Authentication failure."))
				// 403
				.addResponses("ForbiddenError", new ApiResponse()
					.description("Forbidden - The request was legal, but the server refuses to respond" +
							" because the client does not have the privileges to do so."))
				// 404
				.addResponses("NotFoundError", new ApiResponse()
					.description("Not Found - Resource not found."))
				// 405
				.addResponses("MethodNotAllowedError", new ApiResponse()
					.description("Method Not Allowed - A request method is not supported for the requested resource."))
				// 408
				.addResponses("RequestTimeoutError", new ApiResponse()
					.description("Request Timeout - The server timed out waiting for the request."))
				// 500
				.addResponses("InternalServerError", new ApiResponse()
					.description("Internal Server Error - Processing error."))
				// 502
				.addResponses("BadGatewayError", new ApiResponse()
					.description("Bad Gateway - The server was acting as a gateway or proxy and received an" +
						" invalid response from the upstream server."))
				// 503
				.addResponses("ServiceUnavailableError", new ApiResponse()
					.description("Service Unavailable - The server cannot handle the request " +
						"(because it is overloaded or down for maintenance). Generally, this is a temporary state."))
			);

			openApi.addSecurityItem(
				new SecurityRequirement().addList("cookieAuth", Arrays.asList())
			);

		return openApi;
	}

}
