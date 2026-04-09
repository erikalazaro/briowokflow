/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */


package com.briomax.briobpm.business.helpers;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.briomax.briobpm.business.helpers.base.ICriptography;
import com.briomax.briobpm.common.exception.BrioBPMException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Clase/Iterfarce/Enumeracion Criptography.java es ...
 *
 * @author Rigoberto Olvera
 * @since JDK 1.8
 * @version 1.0 Fecha de creacion Jul 27, 2020 5:48:30 PM
 * Modificaciones:
 */
@Component
@Slf4j
public class Criptography implements ICriptography {

	/** El atributo o variable password encoder. */
	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * Crear una nueva instancia del objeto Criptography.
	 */
	public Criptography() {
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.helpers.base.ICriptography#cryptText(java.lang.String)
	 */
	@Override
	public String cryptText(String text) throws BrioBPMException {
//		String pw_hash = BCrypt.hashpw(textPassword, BCrypt.gensalt(10));
		String pw_hash = passwordEncoder.encode(text);
		log.info("PWD HASH={}", pw_hash);
		return pw_hash;
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.helpers.base.ICriptography#hashText(java.lang.String)
	 */
	@Override
	public String hashText(String text) throws BrioBPMException {
		return generateHash(text);
	}

	/**
	 * Generate hash.
	 * @param text el text.
	 * @return el Hash.
	 */
	private String generateHash(String text) {
		if (text == null) {
			return "";
		}
		log.debug("{}", text);
		byte[] digest = null;
		byte[] buffer = text.getBytes(StandardCharsets.UTF_8);
		String hash = "";
		try {
			// MessageDigest con el algoritmo apropiado
			MessageDigest md = MessageDigest.getInstance("SHA-256");
//			MessageDigest md = MessageDigest.getInstance("SHA3_256");
			// Resetear el digest ya que pueda existir en el objeto
			md.reset();
			// Enviar el mensaje a encriptar
			md.update(buffer);
			// Obtener el Digest del Mensaje
			digest = md.digest();
			// Obtener la cadena del hash en valores hexadecimales.
			hash = toHexadecimal(digest);
		}
		catch (NoSuchAlgorithmException exC) {
			log.error(exC.getMessage(), exC);
		}
		return hash;
	}

	/**
	 * Para los algoritmos que tienen hash, hace la conversion del arreglo de bytes[] generado a una cadena String.
	 * @param digest the digest
	 * @return hash.
	 */
	private String toHexadecimal(byte[] digest) {
		StringBuffer sb = new StringBuffer();
		for (byte aux : digest) {
			sb.append(String.format("%02x", aux));
		}
		log.debug("{}", sb.toString());
		return sb.toString();
	}

}
