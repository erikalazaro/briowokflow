package com.briomax.briobpm.data.test.repository;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.data.test.base.AbstractDataTest;
import com.briomax.briobpm.persistence.entity.StNodoInicioProceso;
import com.briomax.briobpm.persistence.entity.StNodoInicioProcesoPK;
import com.briomax.briobpm.persistence.repository.IStNodoInicioProcesoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StNodoInicioProcesoTest extends AbstractDataTest {

	/** El atributo o variable st Nodo Inicio ProcesoRepository. */
	@Autowired
	IStNodoInicioProcesoRepository repository;


	/**
	 * StNodoProceso all test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void stNodoInicioProcesoAllTest() throws BrioBPMException {
		List<StNodoInicioProceso> list = repository.findAll();
		log.info("COUNT: {}", list.size());
		if (list.size() == 0) {
			fail("No existen folio Proces.");
		}

	}
	
	/**
	 * StNodoInicioProceso ID test.
	 * 
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void stNodoInicioProcesoIdTest() throws BrioBPMException {
		StNodoInicioProcesoPK id = StNodoInicioProcesoPK
				.builder()
				.cveEntidad("BRIOMAX")
				.cveProceso("DUMMY")
				.version(new BigDecimal(1.0))
				.cveNodo("EVENTO-INICIO")
				.idNodo(1)
				.build();
		log.info("------>ID: " + id.toString());
		Optional<StNodoInicioProceso> entidad = repository.findById(id);

		if (!entidad.isPresent()) {
			log.info("No existen St Nodo INICIO Proceso.");
			fail("No existen St Nodo INICIO Proceso.");
		}
	}
	

}
