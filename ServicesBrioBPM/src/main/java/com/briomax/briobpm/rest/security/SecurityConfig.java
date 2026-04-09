/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.rest.security;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.ForwardAuthenticationFailureHandler;
import org.springframework.security.web.authentication.ForwardAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.ForwardLogoutSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.Setter;

/**
 * El objetivo de la Class SecurityConfig.java es ...
 * @author Rigoberto Olvera C.
 * @version 1.0 Fecha de creacion 28/01/2020 05:39:34 PM Modificaciones:
 * @since JDK 1.8
 */
@Configuration
@EnableWebSecurity(debug = false)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	/** El atributo o variable cookie path. */
	@Value("${server.servlet.session.cookie.path:/}")
	private @Setter String cookiePath;

	/** El atributo o variable cookie domain. */
	@Value("${server.servlet.session.cookie.domain:localhost}")
	private @Setter String cookieDomain;

	/** El atributo o variable authentication provider. */
	@Resource(name = "authenticationProviderBPM")
	private AuthenticationProvider authenticationProvider;

	/** El atributo o variable expiredstrategy. */
	@Autowired
	private BpmSessionExpirationStrategy expiredstrategy;

	/** El atributo o variable invalidsessionstrategy. */
	@Autowired
	private BpmInvalidSessionStrategy invalidsessionstrategy;

	/** The logout handler. */
	private ForwardLogoutSuccessHandler logoutHandler;

	/** El atributo o variable success handler. */
	private ForwardAuthenticationSuccessHandler successHandler;

	/** El atributo o variable failure handler. */
	private ForwardAuthenticationFailureHandler failureHandler;

	/** Instantiates a new web security config. */
	public SecurityConfig() {
		super();
		successHandler = new ForwardAuthenticationSuccessHandler("/main");
		failureHandler = new ForwardAuthenticationFailureHandler("/authfail");
		logoutHandler = new ForwardLogoutSuccessHandler("/jslogout");
	}

	/**
	 * {@inheritDoc}
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 auth.authenticationProvider(authenticationProvider);
	}

	/**
	 * {@inheritDoc}
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.WebSecurity)
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.debug(false);
	}

	/** 
	 * {@inheritDoc}
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.sessionManagement()
				.sessionFixation().none()
				.maximumSessions(1)
				.expiredSessionStrategy(expiredstrategy).and()
				.invalidSessionStrategy(invalidsessionstrategy)
			.and().cors()
			//.and().sessionManagement().sessionFixation().none().maximumSessions(1)
			//.and().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
			.and().authorizeRequests()
				.antMatchers("/v3/api-docs", "/v3/api-docs/**").permitAll()
				.antMatchers("/login", "/authfail", "/csrf").permitAll()
				.antMatchers("/css/**", "/img/**", "/js/**").permitAll()
				.antMatchers("/logs", "/services/areatrabajo/inicioprocesoseguro").permitAll()
				.antMatchers("/services/init/datosinicio", "/services/init/etiquetas").permitAll()
				.antMatchers("/services/app/dashboardincial","/services/app/listaProcesos","/services/app/listaActividades",
							"/services/app/detalleActividad","/services/app/actSitActividad","/services/app/datosActividad",
							"/services/app/uploadImage", "/services/app/uploadImage2","/services/app/uploadImage3").permitAll()
				.antMatchers("/jslogout", "/logout").authenticated()
				.antMatchers("/services/session/getsessiondata", "/services/session/getuserdata",
							"/services/session/idioma").authenticated()
				.antMatchers("/services/init/menudinamico", "/services/init/getidiomas", "/services/init/getentidades",
							"/services/init/datosentidad", "/services/init/datoslocalidad",
							"/services/usuarios/datosusuario",
							"/services/areatrabajo/grid", "/services/areatrabajo/inicioproceso",
							"/services/ejecucionActividad/formularioDinamico",
							"/services/ejecucionActividad/guardaractividad",
							"/services/ejecucionActividad/terminaractividad",
							"/services/documentos/guardar", "/services/documentos/borrar", "/services/documentos/download",
							"/services/catalogos/procesos/",
							"/services/catalogos/rolprocesos/", "/services/catalogos/rolprocesos/registrar",
							"/services/catalogos/rolprocesos/actualizar", "/services/catalogos/rolprocesos/cmbSituacion",
							"/services/reportes/operativos/procesos", "/services/reportes/operativos/actividades",
							"/services/repse/getAll", "/services/repse/getAllRfc", "/services/repse/getContratoByRfcProceso",
							"/services/adaptaciones/getTrabajadorByFilter",
							"/services/adaptaciones/cargaExcelTrabajadores", "/services/adaptaciones/getContratoByRfc",
							"/services/adaptaciones/actualizaTrabajadores", "/services/adaptaciones/getFechasHistorico",
							"/services/ejecucionActividad/variabledependiente", 

							"/services/adaptaciones/getContratoProceso", "/services/adaptaciones/getProcesosPeriodicosByRfcContrato",
							"/services/adaptaciones/estatuProcesoPeriodico",
							
							
							"/services/documentos/listaNombreDocumento", "/services/adaptaciones/ejecutaRepse", 
							"/services/catalogos/usuarios/all", "/services/catalogos/usuarios/registrar", "/services/catalogos/usuarios/actualizar",
							"/services/catalogos/usuarios/cmbsituacion", "/services/catalogos/usuarios/cmbtipo", 
							"/services/catalogos/usuarios/getLocalidadesByEntidad", "/services/catalogos/usuarios/actualizarContrasena", 
							"/services/catalogos/usuarios/actualizaSituacion", "/services/catalogos/usuarios/cmbfacultad",
							"/services/adaptaciones/consultaPdf","/services/adaptaciones/consultaProcesosPeriodicos",
							"/services/repse/dashboardPorRFCyNumContrato", "/services/repse/dashboardPorContrato",
							"/services/repse/dashboardMonitorInicial", "/services/repse/dashboardCumplimientoPorContratista",
							"/services/adaptaciones/downloadProcesoPeriodicos",
							"/services/adaptaciones/cargaPdfs", "/services/adaptaciones/validaRepse",
							"/services/adaptaciones/eliminaPdf", "/services/repse/getUsuariosByContratante",
							"/services/repse/registrarUsuario", "/services/repse/actualizarUsuario",
							
							"/services/repse/getListProcesos", "/services/repse/getCorreoProcesos", "/services/repse/guardaCorreoProceso"
							).authenticated()
				.antMatchers("/services/app/**",
						"/services/catalogos/usuarios/generaResetPassword", 
						"/services/catalogos/usuarios/restauraPassword").permitAll()
				.anyRequest().fullyAuthenticated()
			.and().formLogin()
				.loginProcessingUrl("/login")
				.successForwardUrl("/main")
				.successHandler(successHandler)
				.failureForwardUrl("/authfail")
				.failureHandler(failureHandler)
			.and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/jslogout"))
				.logoutSuccessHandler(logoutHandler)
				.deleteCookies("JSESSIONID")
			.and().csrf()
				.ignoringAntMatchers("/login","/services/areatrabajo/inicioprocesoseguro", "/services/app/**", 
						"/services/catalogos/usuarios/generaResetPassword", "/services/catalogos/usuarios/restauraPassword")
				.csrfTokenRepository(getCsrfTokenRepository())
			.and().addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	} 

	/**
	 * Authentication filter.
	 * @return el bpm authentication filter.
	 * @throws Exception la exception.
	 */
	public BpmAuthenticationFilter authenticationFilter() throws Exception {
		BpmAuthenticationFilter filter = new BpmAuthenticationFilter();
		filter.setAuthenticationManager(authenticationManagerBean());
		filter.setAuthenticationSuccessHandler(successHandler);
		filter.setAuthenticationFailureHandler(failureHandler);
		return filter;
	}

	/**
	 * Obtener el valor de csrf token repository.
	 * @return el csrf token repository.
	 */
	private CsrfTokenRepository getCsrfTokenRepository() {
		CookieCsrfTokenRepository tokenRepository = CookieCsrfTokenRepository.withHttpOnlyFalse();
		tokenRepository.setCookiePath(cookiePath);
		tokenRepository.setCookieDomain(cookieDomain);
		return tokenRepository;
	}

	/**
	 * Password encoder.
	 * @return el password encoder.
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}

	/**
	 * Http session event publisher.
	 * @return el http session event publisher.
	 */
	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}

}
