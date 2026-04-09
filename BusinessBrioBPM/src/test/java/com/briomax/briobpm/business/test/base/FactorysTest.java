/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.business.test.base;

import org.junit.Test;

import static org.junit.Assert.fail;

import com.briomax.briobpm.business.service.test.factory.SaveSectionTOFactory;
import com.briomax.briobpm.transferobjects.SaveSectionTO;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class FactorysTest.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Mar 5, 2020 2:33:31 PM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class FactorysTest extends AbstractCoreTest {

	/**
	 * Builder save section TO test.
	 */
	@Test
	public void builderSaveSectionToTest() {
		try {
			SaveSectionTO object = SaveSectionTOFactory.builderSaveSectionTO();
			String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
			log.info("\n{}", json);
		}
		catch (JsonProcessingException e) {
			fail("Parse JSON SaveSectionTO");
		}
	}

}
