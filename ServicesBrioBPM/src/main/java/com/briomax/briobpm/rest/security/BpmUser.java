/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.rest.security;

import java.io.Serializable;
import java.util.List;

import com.briomax.briobpm.transferobjects.DatosUsuarioTO;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class BpmUser.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion May 4, 2020 11:26:52 AM Modificaciones:
 * @since JDK 1.8
 */
@Getter
@Setter
@ToString
@Builder
public class BpmUser implements UserDetails, Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = -1901308324150963276L;

	/** The username. */
	private String username;

	/** El atributo o variable password. */
	private String password;

	/** The authorities. */
	private List<GrantedAuthority> authorities;

	/** El atributo o variable account non expired. */
	private boolean accountNonExpired;

	/** El atributo o variable account non locked. */
	private boolean accountNonLocked;
	
	/** El atributo o variable credentials non expired. */
	private boolean credentialsNonExpired;
	
	/** El atributo o variable enabled. */
	private boolean enabled;

	/** El atributo o variable User Data. */
	private DatosUsuarioTO userData;

}
