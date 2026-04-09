package com.briomax.briobpm.business.test.helper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.briomax.briobpm.business.helpers.base.IDatosLocalidadHelper;
import com.briomax.briobpm.business.test.base.AbstractCoreTest;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.Entidad;
import com.briomax.briobpm.persistence.entity.namedquery.DatosOmisionEntidad;
import com.briomax.briobpm.persistence.entity.namedquery.Localidad;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LocalidadHelperTest extends AbstractCoreTest {
	
	@Autowired
	private IDatosLocalidadHelper iDatosLocalidadHelper;
	
	/**
	 * Lee datos localidad test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void leeDatosLocalidadTest() throws BrioBPMException {
		DatosAutenticacionTO session = DatosAutenticacionTO.builder()
				.cveUsuario("Admin.Opera")
				.cveEntidad("BRIOMAX")
				.cveLocalidad("BRIOMAX-CENTRAL")
				.cveIdioma("ES-MX")
				.build();
		DAORet<Localidad, RetMsg> ret = iDatosLocalidadHelper.leeDatoLocalidad(session);
		log.info("{}", ret);
		assertEquals("OK", ret.getMeta().getStatus().toUpperCase());
	}
	

	/**
	 * Lee datos localidad test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void leeDatosOmisionEntidadTest() throws BrioBPMException {
		DatosAutenticacionTO session = DatosAutenticacionTO.builder()
				.cveEntidad("BRIOMAX")
				.build();
		
		DAORet<DatosOmisionEntidad, RetMsg> ret = iDatosLocalidadHelper.leeDatosOmisionEntidad(session);
		log.info("{}", ret);
		assertEquals("OK", ret.getMeta().getStatus().toUpperCase());
	}


}
