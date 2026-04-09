package com.briomax.briobpm.data.test.repository;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.data.test.base.AbstractDataTest;
import com.briomax.briobpm.persistence.entity.CorreoProceso;
import com.briomax.briobpm.persistence.entity.CorreoProcesoPK;
import com.briomax.briobpm.persistence.repository.ICorreoProcesoRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class CorreoProcesoTest.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Feb 09, 2024 Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class CorreoProcesoTest  extends AbstractDataTest {

	/** El atributo o variable repository. */
	@Autowired
	private ICorreoProcesoRepository repository;
	
	
	/**
	 * Calendario all test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void correoProcesAllTest() throws BrioBPMException {
		List<CorreoProceso> list = repository.findAll();
		log.info("COUNT: {}", list.size());
		if (list.size() == 0) {
			fail("No existen CORREO PROCESO.");
		}

	}
	
	@Test
	public void correoProcesoId() throws BrioBPMException {

		CorreoProcesoPK id = CorreoProcesoPK.builder()
				.cveEntidad("BRIOMAX")
				.cveProceso("DUMMY")
				.version(new BigDecimal(1.0))
				.cveEventoCorreo("ASIGNACION_ACTIVIDAD")
				.secuenciaCorreo(1)
				.build();
		log.info("----->id: " + id.toString());
		
		Optional<CorreoProceso> result = repository.findById(id);
		
		if (!result.isPresent()) {
			fail("No existen Calendario Especial.");
		}

	}

}
