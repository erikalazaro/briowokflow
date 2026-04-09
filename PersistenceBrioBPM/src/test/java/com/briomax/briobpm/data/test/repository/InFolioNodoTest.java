/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.data.test.repository;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.data.test.base.AbstractDataTest;
import com.briomax.briobpm.persistence.entity.InFolioNodo;
import com.briomax.briobpm.persistence.entity.InFolioNodoPK;
import com.briomax.briobpm.persistence.repository.IInFolioNodoRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class IInFolioNodoRepositorys.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Nov 28, 2023 Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class InFolioNodoTest extends AbstractDataTest {

	/** El atributo o variable repository. */
	@Autowired
	private IInFolioNodoRepository repository;
	
	/**
	 * InFolioProceso all test.
	 * 
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void inFolioProcesoAllTest() throws BrioBPMException {
	    List<InFolioNodo> list = repository.findAll();
	    log.info("COUNT: {}", list.size());

	    if (list.size() == 0) {
	        fail("No existen folio Proces.");
	    }
	}

	/**
	 * InFolioNodo ID test.
	 * 
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void inFolioNodoId() throws BrioBPMException {

	    InFolioNodoPK id = InFolioNodoPK.builder()
	            .cveEntidad("BRIOMAX")
	            .cveProceso("DUMMY")
	            .version(new BigDecimal(1.0))
	            .cveInstancia("202110-000001")
	            .cveNodo("ACTIVIDAD-USUARIO")
	            .idNodo(1)
	            .build();
	    log.info("----->id: " + id.toString());
	    
	    Optional<InFolioNodo> folioNodoOptional = repository.findById(id);
	    
	    if (!folioNodoOptional.isPresent()) {
	        fail("No existen in Folio Nodo.");
	    }
	}
}
