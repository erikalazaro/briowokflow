/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.rest.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.briomax.briobpm.business.helpers.base.ICriptography;
import com.briomax.briobpm.business.helpers.base.IUsuariosHelper;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.transferobjects.DatosUsuarioTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component("authenticationProviderBPM")
@Slf4j
public class BpmAuthenticationProvider implements AuthenticationProvider {

	/** El atributo o variable usuarios helper. */
	@Autowired
	private IUsuariosHelper usuariosHelper;

	/** El atributo o variable criptography. */
	@Autowired
	private ICriptography criptography;

	public BpmAuthenticationProvider() {
		log.info("BrioBPMAuthenticationProvider ... ");
	}
		
	/**
	 * {@inheritDoc}
	 * @see org.springframework.security.authentication.AuthenticationProvider#authenticate(org.springframework.security.core.Authentication)
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Optional<String> username = Optional.ofNullable(authentication.getName());
		Optional<String> password = Optional.ofNullable(String.valueOf(authentication.getCredentials()));
		log.info("USERNAME: {} PASSWORD:{}", username.get(), "**********"/*password.get()*/);
		String error = "Error de autenticación. Revise su usuario o contraseña";
		
		if (!username.isPresent() || !password.isPresent()) {
			log.error("USERNAME: {} ERROR: {}", username, error);
			throw new BadCredentialsException(error);
		}

		String hashPassword = criptography.hashText(password.get());
		log.debug("\t\tHASH-PASSWORD:{}", hashPassword);
		String[] paramsForm = username.get().split("\\|");
		String cveEntidad = paramsForm[0];
		String cveLocalidad = paramsForm[1];
		String cveIdioma = paramsForm[2];
		String userName = paramsForm[3];
		
		UsernamePasswordAuthenticationToken user = null;
		// DETALLES DEL USUARIO.
		DAORet<DatosUsuarioTO, RetMsg> res =
			usuariosHelper.authenticate(cveEntidad, cveLocalidad, userName, hashPassword, cveIdioma);
		if (res.getMeta().getStatus().toUpperCase().equals("OK")) {
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			// TODO: Roles del Usuario
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			BpmUser userBpm = BpmUser.builder()
					.username(userName)
					.password(hashPassword)
					.accountNonExpired(true)
					.accountNonLocked(true)
					.credentialsNonExpired(true)
					.enabled(true)
					.authorities(authorities)
					.userData(res.getContent())
					.build();
			user = new UsernamePasswordAuthenticationToken(userName, hashPassword, authorities);
			user.setDetails(userBpm);
			log.info("\tUSUARIO BPM ### {}", userBpm);
		} else {
			log.error("Username {} Not Found ", username.get());
			throw new UsernameNotFoundException("Username Not Found " + username.get());
		}
		log.info("User successfully authenticated");
		return user;
	}

	/**
	 * {@inheritDoc}
	 * @see org.springframework.security.authentication.AuthenticationProvider#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
