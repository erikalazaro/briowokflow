package com.briomax.briobpm.business.test.helper;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.briomax.briobpm.business.helpers.IdiomaHelper;
import com.briomax.briobpm.business.helpers.InitHelper;
import com.briomax.briobpm.business.test.base.AbstractCoreTest;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.namedquery.LeeIdiomas;
import com.briomax.briobpm.transferobjects.ComboBoxTO;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class IdiomasHelperTest extends AbstractCoreTest {

	@Autowired
	private IdiomaHelper idiomaHelper;
	
	@Autowired
	private InitHelper initHelper;
	
	@Test
	public void spLeeIdiomaHelp() throws BrioBPMException {

		DAORet<List<LeeIdiomas>, RetMsg> status=idiomaHelper.leeIdioma(getDatosAutenticacionTO());
		List<LeeIdiomas> lista = status.getContent();

				log.info(" LISTA IDIOMAS: " + lista.size() +  " " + lista.toString());
	}
	@Test
	public void getIdiomaTest() throws BrioBPMException {

		DAORet<List<ComboBoxTO>, RetMsg>status=initHelper.getIdiomas(getDatosAutenticacionTO());
		List<ComboBoxTO> lista = status.getContent();

				log.info(" LISTA IDIOMAS: " + lista.size() +  " " + lista.toString());
	}
}
