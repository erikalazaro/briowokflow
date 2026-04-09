package com.briomax.briobpm.data.test.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.data.test.base.AbstractDataTest;
import com.briomax.briobpm.persistence.entity.CrCedulaDetCuotas;
import com.briomax.briobpm.persistence.entity.CrCedulaDetCuotasPK;
import com.briomax.briobpm.persistence.repository.ICrCedulaDetCuotasRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class CrCedulaDetCuotasRepositoryTest.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Septiembre 18, 2024 Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class CrCedulaDetCuotasRepositoryTest extends AbstractDataTest {

	/** El atributo o variable repository. */
	@Autowired
	private ICrCedulaDetCuotasRepository repository;

	/**
	 * Calendario all test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void crCedulaDetCuotasAllTest() throws BrioBPMException {
		List<CrCedulaDetCuotas> list = repository.findAll();
		log.info("COUNT: {}", list.size());
		if (list.size() == 0) {
			log.info("No existen folio Proces.");
		}
	}
	
	
	/**
	 * crCedulaDetCuotas ID test.
	 * 
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void crCedulaDetCuotasId() throws BrioBPMException {
		CrCedulaDetCuotasPK id = CrCedulaDetCuotasPK.builder()
	            .cveEntidad("BRIOMAX")
	            .cveProceso("DUMMY")
	            .rfcContratista("")
	            .numeroContrato(" ")
	            .fechaCarga(new Date())
	            .build();
	    log.info("----->id: " + id.toString());
	    
	    Optional<CrCedulaDetCuotas> procesoOptional = repository.findById(id);
	    
	    if (!procesoOptional.isPresent()) {
	        log.info("No existe el proceso con el ID proporcionado.");
	    }
	}
}
