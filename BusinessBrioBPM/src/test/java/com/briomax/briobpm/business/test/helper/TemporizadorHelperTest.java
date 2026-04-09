package com.briomax.briobpm.business.test.helper;

import java.text.ParseException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.briomax.briobpm.business.helpers.base.ITemporizadorHelper;
import com.briomax.briobpm.business.test.base.AbstractCoreTest;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TemporizadorHelperTest extends AbstractCoreTest{
	
	@Autowired
	private ITemporizadorHelper helper;
	
	// SP_TEMPORIZADOR_ACTIVIDADES
	@Test
	public void temporizadorActivadesTest() throws BrioBPMException, ParseException {
		log.info("INICIA TEST SP_TEMPORIZADOR_ACTIVIDADES");
		
		RetMsg msg = helper.temporizadorActivades();
		
		log.info(msg.getMessage());
		log.info(msg.getStatus());
	}

}
