package com.briomax.briobpm.data.test.repository;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.data.test.base.AbstractDataTest;
import com.briomax.briobpm.persistence.entity.StNodoSiguiente;
import com.briomax.briobpm.persistence.entity.StNodoSiguientePK;
import com.briomax.briobpm.persistence.repository.IStNodoSiguienteRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class StNodoSiguienteTest.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Feb 09, 2024 3:13:43 PM :
 * @since JDK 1.8
 */
@Slf4j
public class StNodoSiguienteTest extends AbstractDataTest {

	/** El atributo o variable repository. */
	@Autowired
	private IStNodoSiguienteRepository repository;
	
	/**
	 * Calendario all test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void stNodoSiguienteAllTest() throws BrioBPMException {
		List<StNodoSiguiente> list = repository.findAll();
		log.info("COUNT: {}", list.size());
		if (list.size() == 0) {
			fail("No existen folio Proces.");
		}
	}
	
	/**
	 * StNodoSiguiente ID test.
	 * 
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void stNodoSiguienteIdTest() throws BrioBPMException {
		StNodoSiguientePK id = StNodoSiguientePK
				.builder()
				.cveEntidad("BRIOMAX")
				.cveProceso("DUMMY")
				.version(new BigDecimal(1.0))
				.cveNodo("ACTIVIDAD-USUARIO")
				.idNodo(new BigDecimal(1.0))
				.secuencia(1)
				.tipoNodoSiguiente("NORMAL")
				.build();
		log.info("------>ID: " + id.toString());
		Optional<StNodoSiguiente> entidad = repository.findById(id);

		if (!entidad.isPresent()) {
			log.info("No existen St Nodo Siguiente.");
			fail("No existen St Nodo Siguiente.");
		}
	}

}
