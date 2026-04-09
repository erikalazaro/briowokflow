package com.briomax.briobpm.data.test.repository;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.data.test.base.AbstractDataTest;
import com.briomax.briobpm.persistence.entity.StVariableEnvio;
import com.briomax.briobpm.persistence.entity.StVariableEnvioPK;
import com.briomax.briobpm.persistence.repository.IStVariableEnvioRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class IStVariableSeccionRepository.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Dic 11, 2023 Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class StVariableEnvioRepositoryTest extends AbstractDataTest {

	/** El atributo o variable repository. */
	@Autowired
	private IStVariableEnvioRepository repository;
	
	/**
	 * Calendario all test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void stVariableSeccionAllTest() throws BrioBPMException {
		List<StVariableEnvio> list = repository.findAll();
		log.info("COUNT: {}", list.size());
		if (list.size() == 0) {
			fail("No existen folio Proces.");
		}

	}

	@Test
	public void stVariableEnvioByIdTest() throws BrioBPMException {
		StVariableEnvioPK id = StVariableEnvioPK.builder()
				.cveEntidad("BRIOMAX")
				.cveProceso("DUMMY")
				.version(new BigDecimal(1.0))
				.cveNodo("ACTIVIDAD-USUARIO")
				.idNodo(new BigDecimal(1.0))
				.cveVariable("VPRO_HAY_REQUERIMIENTOS_PROCESO")
				.build();
		Optional<StVariableEnvio> result = repository.findById(id );

		if (!result.isPresent()) {
			log.info("No existen un St Variable Envio.");
			fail("No existen un St Variable Envio.");
		}
	}
}
