/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.service.catalogs.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.briomax.briobpm.business.service.catalogs.core.IRolProcesoService;
import com.briomax.briobpm.business.service.catalogs.core.ISemiFijosService;
import com.briomax.briobpm.business.test.base.AbstractCoreTest;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.transferobjects.ComboBoxTO;
import com.briomax.briobpm.transferobjects.catalogs.UsuariosRol;
import com.briomax.briobpm.transferobjects.catalogs.RolProcesoTO;
import com.briomax.briobpm.transferobjects.catalogs.UsuarioRol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class MttoStRolProcesoServiceTest.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jun 26, 2020 12:44:48 PM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class RolProcesoServiceTest extends AbstractCoreTest {

	/** El atributo o variable service. */
	@Autowired
	private IRolProcesoService service;

	/** El atributo o variable service. */
	@Autowired
	private ISemiFijosService semiFijosService;

	/**
	 * Crear una nueva instancia del objeto mtto st rol proceso service test.
	 */
	public RolProcesoServiceTest() {
	}

	/**
	 * Obtener el valor de all by id parent test.
	 * @return el all by id parent test.
	 */
	@Test
	public void getAllByIdParentTest() {
		printerJson(getDatosAutenticacionTO());
		List<RolProcesoTO> list = service.getAll(getDatosAutenticacionTO(), "DUMMY", getVersion());
		for (RolProcesoTO stRolProcesoTO : list) {
			log.info("{}", stRolProcesoTO);
		}
		printerJson(list);
	}

	/**
	 * Insert test.
	 */
	@Test
	@Rollback(value = true)
	public void insertTest() {
		RolProcesoTO to = builderStRolProcesoTO();
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
		RolProcesoTO to = builderStRolProcesoTO();
		to.setSituacion("DESACTIVADO");
		printerJson(to);
		RetMsg response = service.update(getDatosAutenticacionTO(), to);
		log.info("RESPONSE UPDATE: {}", response);
		printerJson(response);
	}

	/**
	 * Builder st rol proceso TO.
	 * @return el st rol proceso TO.
	 */
	private RolProcesoTO builderStRolProcesoTO() {
		return RolProcesoTO.builder()
				.cveProceso("DUMMY")
				.version("1.00")
				.cveRol("PROMOTOR")
				.descripcion("PROMOTOR-99")
				.situacion("HABILITADO")
				.build();
	}

	/**
	 * Obtener el valor de situacion test.
	 * @return el situacion test.
	 */
	@Test
	public void getSituacionTest() {
		List<ComboBoxTO> situaciones = semiFijosService.getCmbSituacionRolProceso(getDatosAutenticacionTO());
		log.info(" # OPTIONS {}", situaciones.size());
		for (ComboBoxTO item : situaciones) {
			log.info("getSituacionTest {}", item);
		}
	}

	/**
	 * Obtener el valor de usuarios rol procesos test.
	 * @return el usuarios rol procesos test.
	 */
	@Test
	public void getUsuariosRolProcesosTest() {
		List<UsuarioRol> list =
			service.getUsuariosRolProcesos(getDatosAutenticacionTO(), "G2 ACF", "1.00", "ROL_ADMIN_OPERA");
		log.info(" # USUARIO-ROL-PROCESO {}", list.size());
		for (UsuarioRol item : list) {
			log.info("{}", item);
		}
	}

	@Test
	public void asignarUsariosRolTest() {
		String cveProceso = "G2 ACF";
		String version = "1.00";
		String cveRol = "ROL_ADMIN_OPERA";
		List<String> cveUsuarios = new ArrayList<>();
		cveUsuarios.add("Admin.Opera");
		cveUsuarios.add("Brio.Workflow");
		cveUsuarios.add("Francisco.Rodriguez");
		cveUsuarios.add("Miguel.Garcia.Saavedra");
		cveUsuarios.add("Kala.Mohamed");
		UsuariosRol to = UsuariosRol.builder()
				.cveProceso(cveProceso)
				.version(version)
				.cveRol(cveRol)
				.cveUsuarios(cveUsuarios)
				.build();
		log.info(" # USUARIO-ROL-PROCESO {}", to.getCveUsuarios().size());
		RetMsg response = service.asignarUsariosRol(getDatosAutenticacionTO(), to);
		log.info("{}", response);
		List<UsuarioRol> list =
				service.getUsuariosRolProcesos(getDatosAutenticacionTO(), cveProceso, version, cveRol);
			log.info(" # ASIGNAR USUARIO-ROL-PROCESO {}", list.size());
			for (UsuarioRol item : list) {
				log.info("{}", item);
			}
	}

}
