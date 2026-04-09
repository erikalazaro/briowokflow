package com.briomax.briobpm.data.test.repository;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.data.test.base.AbstractDataTest;
import com.briomax.briobpm.persistence.entity.VariableSistema;
import com.briomax.briobpm.persistence.repository.IVariableSistemaRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class InNodoProcesoTest.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Dic 06, 2023 3:13:43 PM :
 * @since JDK 1.8
 */
@Slf4j
public class VariableSistemaTest extends AbstractDataTest {

	/** El atributo o variable repository. */
	@Autowired
	private IVariableSistemaRepository repository;
	
	/**
	 * Calendario all test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void inNodoProcesoAllTest() throws BrioBPMException {
		List<VariableSistema> list = repository.findAll();
		log.info("COUNT: {}", list.size());
		if (list.size() == 0) {
			fail("No existen folio Proces.");
		}

	}

}
