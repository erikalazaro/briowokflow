/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.rest.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class CorsConfig.java es ...
 * @author Rigoberto Ovlera C.
 * @version 1.0 Fecha de creacion 28/01/2020 05:38:56 PM Modificaciones:
 * @since JDK 1.8
 */
@Configuration
@EnableWebSecurity(debug = false)
@ConfigurationProperties(prefix="brio.bpm.allowed")
@Slf4j
public class CorsConfig {

	/** El atributo o variable origins. */
	private @Getter List<String> origins = new ArrayList<String>();

	/**
	 * Cors configuration source.
	 * @return the cors configuration source
	 */
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		origins.add("http://app.repsecompliance.com");
		origins.add("https://app.repsecompliance.com");
		CorsConfiguration configuration = new CorsConfiguration();
		log.info("Allowed Origins: {}", origins);
//		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedOrigins(origins);
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS"));
//		configuration.setAllowedHeaders(Arrays.asList("*"));
		configuration.setAllowedHeaders(Arrays.asList("x-csrf-token", "x-xsrf-token", "xhrfields", "Content-Type"));
		configuration.addAllowedHeader("X-Requested-With");
		configuration.addAllowedHeader("X-XSRF-TOKEN");
//		configuration.addAllowedHeader("Authorization");
//		configuration.addAllowedHeader("Cache-Control");
		configuration.setAllowCredentials(true);
		configuration.addExposedHeader("redirectloc");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

}
