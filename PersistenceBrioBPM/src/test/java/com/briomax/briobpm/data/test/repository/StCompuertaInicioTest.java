package com.briomax.briobpm.data.test.repository;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.data.test.base.AbstractDataTest;
import com.briomax.briobpm.persistence.entity.StCompuertaInicio;
import com.briomax.briobpm.persistence.entity.StCompuertaInicioPK;
import com.briomax.briobpm.persistence.repository.IStCompuertaInicioRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class ILocalidadEntidadRepository.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Nov 28, 2023 Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class StCompuertaInicioTest  extends AbstractDataTest {

	/** El atributo o variable repository. */
	@Autowired
	private IStCompuertaInicioRepository repository;
	
	/**
	 * Calendario all test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void StCompuertaInicioAllTest() throws BrioBPMException {
		List<StCompuertaInicio> list = repository.findAll();
		log.info("COUNT: {}", list.size());
		if (list.size() == 0) {
			fail("No existen folio Proces.");
		}
	}
	
	@Test
	public void stCompuertaInicioByIdTest() throws BrioBPMException {
		StCompuertaInicioPK id = StCompuertaInicioPK.builder()
				.cveEntidad("BRIOMAX")
				.cveProceso("DUMMY")
				.version(new BigDecimal(1.0))
				.idNodoInicio(1)
				.cveNodoInicio("EVENTO-INICIO")
				.build();
		Optional<StCompuertaInicio> result = repository.findById(id );

		if (!result.isPresent()) {
			log.info("No existen St Compuerta Inicio.");
			fail("No existen St Compuerta Inicio.");
		}
	}
}
