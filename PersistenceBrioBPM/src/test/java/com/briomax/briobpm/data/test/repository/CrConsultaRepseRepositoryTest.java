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
import com.briomax.briobpm.persistence.entity.CrConsultaRepse;
import com.briomax.briobpm.persistence.entity.CrConsultaRepsePK;
import com.briomax.briobpm.persistence.repository.ICrCedulaDetCuotasRepository;
import com.briomax.briobpm.persistence.repository.ICrConsultaRepseRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class CrConsultaRepseRepositoryTest.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Septiembre 19, 2024 Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class CrConsultaRepseRepositoryTest extends AbstractDataTest {

	/** El atributo o variable repository. */
	@Autowired
	private ICrConsultaRepseRepository repository;

	/**
	 * Calendario all test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void crConsultaRepseAllTest() throws BrioBPMException {
		List<CrConsultaRepse> list = repository.findAll();
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
	public void crConsultaRepseId() throws BrioBPMException {
		CrConsultaRepsePK id = CrConsultaRepsePK.builder()
	            .cveEntidad("BRIOMAX")
	            .cveProceso("DUMMY")
	            .rfcContratista("")
	            .numContrato(" ")
	            .fechaConsulta(new Date())
	            .build();
	    log.info("----->id: " + id.toString());
	    
	    Optional<CrConsultaRepse> procesoOptional = repository.findById(id);
	    
	    if (!procesoOptional.isPresent()) {
	        log.info("No existe el proceso con el ID proporcionado.");
	    }
	}
}
