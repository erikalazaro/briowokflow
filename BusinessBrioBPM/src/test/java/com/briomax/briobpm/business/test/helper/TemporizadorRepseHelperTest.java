package com.briomax.briobpm.business.test.helper;

import java.text.ParseException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.briomax.briobpm.business.helpers.base.ITemporizadorRepseHelper;
import com.briomax.briobpm.business.test.base.AbstractCoreTest;
import com.briomax.briobpm.common.exception.BrioBPMException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TemporizadorRepseHelperTest extends AbstractCoreTest{
	
	@Autowired
	private ITemporizadorRepseHelper helper;
	
	// SP_TEMPORIZADOR_ACTIVIDADES
	@Test
	public void temporizadorActivadesTest() throws BrioBPMException, ParseException {
		log.info("INICIA TEST SP_TEMPORIZADOR_ACTIVIDADES");

		//SERVICIOS  OBRA
		helper.agregarNuevosProcesos("SERVICIOS");

		
	}

}
