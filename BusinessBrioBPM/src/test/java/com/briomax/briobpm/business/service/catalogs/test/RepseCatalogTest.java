package com.briomax.briobpm.business.service.catalogs.test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.briomax.briobpm.business.service.catadaptaciones.CrConsultaRepseService;
import com.briomax.briobpm.business.test.base.AbstractCoreTest;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.in.CrConsultaRepseTO;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class RepseCatalogTest.java es ...
 * 
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion May 14, 2024 11:50:00 AM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class RepseCatalogTest extends AbstractCoreTest {

	@Autowired
	private CrConsultaRepseService service;
	
	// TODOS
	@Test
	//@Ignore
	public void obtenerTodos() throws BrioBPMException {
		log.info("INICIA TEST CONSULTAR TODO");

		List<CrConsultaRepseTO> registros = service.consultaTodosRegistros();
	    // Log del contenido devuelto
	    log.info("-----> CANTIDAD DE REGISTROS : " + registros.size());
	    
	    for(CrConsultaRepseTO registro : registros) {
	    	log.info("REGISTRO: " + registro.toString());
	    }

	}	

	// FILTRO
	//@Ignore
	@Test
	public void obtieneRegistros() throws BrioBPMException, ParseException {
		log.info("INICIA TEST CONSULTAR TODO");
		
		List<CrConsultaRepseTO> registros = 
				service.consultarRegistros(getDatosAutenticacionTO(), null, null, null);
	    // Log del contenido devuelto
	    log.info("-----> Datos NULL : {} {}", registros.size(), registros);

	    registros = 
				service.consultarRegistros(getDatosAutenticacionTO(), "BRIOMAX CONSULTING SA DE CV", null, null);
	    // Log del contenido devuelto
	    log.info("-----> Datos RAZON SOCIAL: {} {}", registros.size(), registros);
   
	    registros = 
				service.consultarRegistros(getDatosAutenticacionTO(), null, "ENCONTRADO", null);
	    // Log del contenido devuelto
	    log.info("-----> Datos SITUACION: {} {}", registros.size(), registros);
	    
	    
	    
	    String fechaString = "05/09/2024";
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = formato.parse(fechaString);
	    registros =
				service.consultarRegistros(getDatosAutenticacionTO(), null, null, fecha);
	    // Log del contenido devuelto
	    log.info("-----> Datos FECHA: {} {}", registros.size(), registros);

	}
	
	
	//@Ignore
	@Test
	public void ejecutaPythonTest() throws BrioBPMException, ParseException, IOException, InterruptedException {
		log.info("INICIA TEST");
		
		
		String data = "BRIOMAX CONSULTING SA DE CV";
		RetMsg result = service.correEjecutablePython(data);
		
		log.info(result.getStatus());
		log.info(result.getMessage());
		

	}
}