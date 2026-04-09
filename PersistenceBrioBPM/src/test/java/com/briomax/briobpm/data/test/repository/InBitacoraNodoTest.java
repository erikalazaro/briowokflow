package com.briomax.briobpm.data.test.repository;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.data.test.base.AbstractDataTest;
import com.briomax.briobpm.persistence.entity.InBitacoraNodo;
import com.briomax.briobpm.persistence.entity.InBitacoraNodoPK;
import com.briomax.briobpm.persistence.repository.IInBitacoraNodoRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class InNodoProcesoTest.java es ...
 * 
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Dic 05, 2023 3:13:43 PM :
 * @since JDK 1.8
 */
@Slf4j
public class InBitacoraNodoTest extends AbstractDataTest {

	/** El atributo o variable repository. */
	@Autowired
	private IInBitacoraNodoRepository repository;

	/**
	 * InBitacoraNodo all test.
	 * 
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void inBitacoraNodoAllTest() throws BrioBPMException {
	    List<InBitacoraNodo> list = repository.findAll();
	    log.info("COUNT: {}", list.size());
	    if (list.size() == 0) {
	        fail("No existen IN BITACORA NODO.");
	    }
	}

	/**
	 * InBitacoraNodo ID test.
	 * 
	 * @throws BrioBPMException la brio BPM exception.
	 * @throws ParseException
	 */
	@Test
	public void inBitacoraNodoIdTest() throws BrioBPMException, ParseException {
	    Date fecha = new Date();
	    
	    String fechaString = "2021-10-19 12:05:44.660";
	    String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	    
	    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
	    
	    fecha = sdf.parse(fechaString);
	    
	    InBitacoraNodoPK id = InBitacoraNodoPK.builder()
	            .cveEntidad("BRIOMAX")
	            .cveProceso("DUMMY")
	            .version(new BigDecimal(1.0))
	            .cveInstancia("202110-000001")
	            .cveNodo("ACTIVIDAD-USUARIO")
	            .idNodo(1)
	            .secuenciaNodo(1)
	            .fechaEvento(fecha)
	            .build();
	    log.info("-----------> ID: " + id.toString());
	    Optional<InBitacoraNodo> entidad = repository.findById(id);
	    log.info("-----------> FECHA CON FORMATO: " + fecha);
	    
	    if (!entidad.isPresent()) {
	        log.info("No existen IN BITACORA NODO.");
	        fail("No existen IN BITACORA NODO.");
	    }
	}

}
