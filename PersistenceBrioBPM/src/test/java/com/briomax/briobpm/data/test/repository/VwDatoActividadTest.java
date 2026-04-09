package com.briomax.briobpm.data.test.repository;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.data.test.base.AbstractDataTest;
import com.briomax.briobpm.persistence.entity.VwDatoActividad;
import com.briomax.briobpm.persistence.entity.VwDatoActividadPK;
import com.briomax.briobpm.persistence.repository.IVwDatoActividadRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class InNodoProcesoTest.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Mar 11, 2024 3:13:43 PM :
 * @since JDK 1.8
 */
@Slf4j
public class VwDatoActividadTest extends AbstractDataTest {

	/** El atributo o variable repository. */
	@Autowired
	private IVwDatoActividadRepository repository;

	/**
	 * Find all test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void inProcesoAllTest() throws BrioBPMException {
		List<VwDatoActividad> list = repository.findAll();
		log.info("COUNT: {}", list.size());
		if (list.size() == 0) {
			fail("No existen Proces.");
		}

	}
	
	/**
	 * Find by Id.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void stInProcesoByIdTest() throws BrioBPMException {
		VwDatoActividadPK id = VwDatoActividadPK.builder()
				.cveEntidad("BRIOMAX")
				.cveProceso("DUMMY")
				.version(new BigDecimal(1.0))
				.cveInstancia("202110-000001")
				.cveNodo("ACTIVIDAD-USUARIO")
				.idNodo(1)
				.secuenciaNodo(1)
				.build();
		Optional<VwDatoActividad> result = repository.findById(id );

		if (!result.isPresent()) {
			log.info("No existen in PROCESO.");
			fail("No existen in PROCESO.");
		}
	}
}
