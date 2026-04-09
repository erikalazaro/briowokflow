package com.briomax.briobpm.data.test.repository;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.data.test.base.AbstractDataTest;
import com.briomax.briobpm.persistence.entity.StMensajeRecepcion;
import com.briomax.briobpm.persistence.repository.IStMensajeRecepcionRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class StMensajeRecepcionRepositoryTest.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Nov 07, 2023 3:13:43 PM :
 * @since JDK 1.8
 */
@Slf4j
public class StMensajeRecepcionRepositoryTest extends AbstractDataTest {

	/** El atributo o variable repository. */
	@Autowired
	private IStMensajeRecepcionRepository repository;
	/**
	 * Calendario all test.
	 * @throws BrioBPMException la brio BPM exception.
	 * 
	 */
	@Test
	public void StMensajeRecepcionAllTest() throws BrioBPMException {
		List<StMensajeRecepcion> list = repository.findAll();
		log.info("COUNT: {}", list.size());
		if (list.size() == 0) {
			fail("No existen folio Proces.");
		}
	}
}
