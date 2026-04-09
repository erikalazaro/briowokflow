/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.data.test.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.data.test.base.AbstractDataTest;
import com.briomax.briobpm.persistence.entity.CrFormatoCuotasOP;
import com.briomax.briobpm.persistence.entity.CrFormatoCuotasOPPK;
import com.briomax.briobpm.persistence.repository.ICrFormatoCuotaOPRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class ICrPagoBancarioRepository.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Sept 03, 2024 Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class CrFormatoCuotaORTest extends AbstractDataTest  {

	/** El atributo o variable repository. */
	@Autowired
	private ICrFormatoCuotaOPRepository repository;

	/**
	 * StProceso all test.
	 * 
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void crFormatoCuotasOPAllTest() throws BrioBPMException {
	    List<CrFormatoCuotasOP> list = repository.findAll();
	    log.info("COUNT: {}", list.size());

	    if (list.size() == 0) {
	        log.info("No existen procesos.");
	    }
	}

	/**
	 * StProceso ID test.
	 * 
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void crFormatoCuotasOPID() throws BrioBPMException {
		CrFormatoCuotasOPPK id = CrFormatoCuotasOPPK.builder()
	            .cveEntidad("BRIOMAX")
	            .cveProceso("DUMMY")
	            .build();
	    log.info("----->id: " + id.toString());
	    
	    Optional<CrFormatoCuotasOP> procesoOptional = repository.findById(id);
	    
	    if (!procesoOptional.isPresent()) {
	        log.info("No existe el proceso con el ID proporcionado.");
	    }
	}
	
	
	@Test
    public void testGetCargaFormatoCuotasOP() {

        // Calcula el inicio y el fin del día para la fecha de carga
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date()); // Usa la fecha actual o la que necesites
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date startOfDay = calendar.getTime();

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        Date endOfDay = calendar.getTime();

        // Llama al método que deseas probar
        //List<CrFormatoCuotasOP> actualList = repository.getCargaFormatoCuotasOP("BRIOMAX", "cveproceso", "rfcContratista", "numContrato", startOfDay, endOfDay);

        // Asegúrate de que el resultado es el esperado
        //log.info("CONTENIDO: " + actualList.size());
        // Aquí puedes añadir más aserciones para verificar los contenidos de actualList
    }
}
