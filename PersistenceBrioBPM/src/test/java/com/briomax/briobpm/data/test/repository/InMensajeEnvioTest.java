package com.briomax.briobpm.data.test.repository;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.data.test.base.AbstractDataTest;
import com.briomax.briobpm.persistence.entity.InMensajeEnvio;
import com.briomax.briobpm.persistence.entity.InMensajeEnvioPK;
import com.briomax.briobpm.persistence.repository.IInMensajeEnvioRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class InNodoProcesoTest.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Dic 01, 2023 3:13:43 PM :
 * @since JDK 1.8
 */
@Slf4j
public class InMensajeEnvioTest extends AbstractDataTest {
	
	/** El atributo o variable repository. */
	@Autowired
	private IInMensajeEnvioRepository repository;
	
	/**
	 * InMensajeEnvio all test.
	 * 
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void inMensajeEnvioAllTest() throws BrioBPMException {
	    List<InMensajeEnvio> list = repository.findAll();
	    log.info("COUNT: {}", list.size());

	    if (list.size() == 0) {
	        fail("No existen mensajes de envío.");
	    }
	}

	/**
	 * InMensajeEnvio ID test.
	 * 
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void inMensajeEnvioId() throws BrioBPMException {
	    InMensajeEnvioPK id = InMensajeEnvioPK.builder()
	    		.cveEntidad("BRIOMAX")
	            .cveProceso("DUMMY")
	            .version(new BigDecimal(1.0))
	            .cveInstancia("202110-000001")
	            .cveNodo("ACTIVIDAD-USUARIO")
	            .idNodo(1)
	            .secuenciaNodo(1)
	            .build();
	    log.info("----->id: " + id.toString());
	    
	    Optional<InMensajeEnvio> mensajeEnvioOptional = repository.findById(id);
	    
	    if (!mensajeEnvioOptional.isPresent()) {
	        fail("No existe el mensaje de envío con el ID proporcionado.");
	    }
	}
}
