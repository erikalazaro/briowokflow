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
import com.briomax.briobpm.persistence.entity.InDocumentoProceso;
import com.briomax.briobpm.persistence.entity.InDocumentoProcesoPK;
import com.briomax.briobpm.persistence.repository.IInDocumentoProcesoRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class DocumentoProcesoTest.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Nov 28, 2023 Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class DocumentoProcesoTest extends AbstractDataTest {

	/** El atributo o variable repository. */
	@Autowired
	private IInDocumentoProcesoRepository repository;

	/**
	 * Calendario all test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void documentoProcesAllTest() throws BrioBPMException {
	    List<InDocumentoProceso> list = repository.findAll();
	    log.info("COUNT: {}", list.size());
	    if (list.size() == 0) {
	        fail("No existen Documento Proces.");
	    }
	}

	/**
	 * InDocumentoProcesoId test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void inDocumentoProcesoId() throws BrioBPMException {
	    InDocumentoProcesoPK id = InDocumentoProcesoPK.builder()
	            .cveEntidad("BRIOMAX")
	            .cveProceso("DUMMY")
	            .version(new BigDecimal(1.0))
	            .cveInstancia("202110-000001")
	            .secuenciaDocumento(1)
	            .build();
	    log.info("----->id: " + id.toString());
	    
	    Optional<InDocumentoProceso> result = repository.findById(id);
	    
	    if (!result.isPresent()) {
	        fail("No existen Calendario Especial.");
	    }
	}


}
