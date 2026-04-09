/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.service.catalogs.test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.briomax.briobpm.business.service.catalogs.core.ISemiFijosService;
import com.briomax.briobpm.business.service.catalogs.core.IUsuarioService;
import com.briomax.briobpm.business.test.base.AbstractCoreTest;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.transferobjects.ComboBoxTO;
import com.briomax.briobpm.transferobjects.catalogs.UsuarioTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class UsuarioServiceTest.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jul 27, 2020 3:40:17 PM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class UsuarioServiceTest extends AbstractCoreTest {

	/** El atributo o variable service. */
	@Autowired
	private IUsuarioService service;

	/** El atributo o variable service. */
	@Autowired
	private ISemiFijosService semiFijosService;

	/**
	 * Crear una nueva instancia del objeto usuario service test.
	 */
	public UsuarioServiceTest() {
	}

	/**
	 * Obtener el valor de all by id parent test.
	 * @return el all by id parent test.
	 */
	@Test
	public void getAllByIdParentTest() {
		printerJson(getDatosAutenticacionTO());
		List<UsuarioTO> list = service.getAll(getDatosAutenticacionTO());
		for (UsuarioTO usuario : list) {
			log.info("{}", usuario);
		}
		printerJson(list);
	}

	/**
	 * Insert test.
	 */
	@Test
	@Rollback(value = true)
	public void insertTest() {
		UsuarioTO to = builderUsuarioTO();
		to.setCveUsuario("DUMMY-BRIO-BPM");
		printerJson(to);
		RetMsg response = service.insert(getDatosAutenticacionTO(), to);
		log.info("RESPONSE INSERT: {}", response);
		printerJson(response);
	}

	/**
	 * Update test.
	 */
	@Test
	@Rollback(value = true)
	public void updateTest() {
		UsuarioTO to = builderUsuarioTO();
		to.setCveUsuario("BRIO-BPM");
		to.setSituacion("DESACTIVADO");
		printerJson(to);
		RetMsg response = service.update(getDatosAutenticacionTO(), to);
		log.info("RESPONSE UPDATE: {}", response);
		printerJson(response);
	}

	/**
	 * Obtener el valor de situacion test.
	 * @return el situacion test.
	 */
	@Test
	public void getSituacionTest() {
		List<ComboBoxTO> situaciones = semiFijosService.getCmbSituacionUsuario(getDatosAutenticacionTO());
		log.info(" # OPTIONS {}", situaciones.size());
		for (ComboBoxTO item : situaciones) {
			log.info("{}", item);
		}
		printerJson(situaciones);
	}

	/**
	 * Obtener el valor de tipo test.
	 * @return el tipo test.
	 */
	@Test
	public void getTipoTest() {
		List<ComboBoxTO> tipos = semiFijosService.getCmbTipoUsuario(getDatosAutenticacionTO());
		log.info(" # OPTIONS {}", tipos.size());
		for (ComboBoxTO item : tipos) {
			log.info("{}", item);
		}
		printerJson(tipos);
	}

	/**
	 * Builder usuario TO.
	 * @return el usuario TO.
	 */
	private UsuarioTO builderUsuarioTO() {
		return UsuarioTO.builder()
				.cveEntidad("BRIOMAX")
				.cveUsuario("DUMMY-BRIO-BPM")
				.password("3efc3b05dbd0963af796964d99659b35cd3d5dcd4f05fe38107c12dab0e5b889")
				.fecPassword(Date.from(LocalDate.now().plusDays(30).atStartOfDay(ZoneId.systemDefault()).toInstant()))
				.nombre("DUMMY BRIO BPM")
				.tipo("INTERNO")
				.fotografia("BRIO-BPM.jpg")
				.cveLocalidad("BRIOMAX-CENTRAL")
				.situacion("HABILITADO")
				.email("dummy@briomax.com")
				.build();
	}

}
