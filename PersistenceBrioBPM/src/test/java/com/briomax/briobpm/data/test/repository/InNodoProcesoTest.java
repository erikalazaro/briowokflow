package com.briomax.briobpm.data.test.repository;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.data.test.base.AbstractDataTest;
import com.briomax.briobpm.persistence.entity.InNodoProceso;
import com.briomax.briobpm.persistence.entity.InNodoProcesoPK;
import com.briomax.briobpm.persistence.repository.IInNodoProcesoRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class InNodoProcesoTest.java es ...
 * 
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Nov 07, 2023 3:13:43 PM :
 * @since JDK 1.8
 */
@Slf4j
public class InNodoProcesoTest extends AbstractDataTest {

	/** El atributo o variable repository. */
	@Autowired
	private IInNodoProcesoRepository repository;

	/**
	 * Calendario all test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void inNodoProcesoAllTest() throws BrioBPMException {
		List<InNodoProceso> list = repository.findAll();
		log.info("COUNT: {}", list.size());
		if (list.size() == 0) {
			fail("No existen folio Proces.");
		}

	}

	/**
	 * Calendario all test.
	 * 
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void inNodoProcesoIdTest() throws BrioBPMException {
		InNodoProcesoPK id = InNodoProcesoPK
				.builder()
				.cveEntidad("BRIOMAX")
				.cveProceso("DUMMY")
				.version(new BigDecimal(1.0))
				.cveInstancia("202110-000001")
				.cveNodo("ACTIVIDAD-USUARIO")
				.idNodo(1)
				.secuenciaNodo(1)
				.build();
		log.info("------>ID: " + id.toString());
		Optional<InNodoProceso> entidad = repository.findById(id);

		log.info("------> SE ENCONTRO ENTIDAD: " + entidad.toString());
		if (!entidad.isPresent()) {
			log.info("No existen IN NODO PROCESO.");
			fail("No existen IN NODO PROCESO.");
		}
	}

}