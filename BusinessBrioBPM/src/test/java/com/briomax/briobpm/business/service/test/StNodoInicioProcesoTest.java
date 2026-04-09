package com.briomax.briobpm.business.service.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.briomax.briobpm.business.helpers.base.INodoHelper;
import com.briomax.briobpm.business.test.base.AbstractCoreTest;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.InNodoProcesoTO;
import com.briomax.briobpm.transferobjects.NodoInicioProcesoTO;
import com.briomax.briobpm.transferobjects.NodoInicioTO;
import com.briomax.briobpm.transferobjects.ReporteProcesosTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class ReportesOperativosServiceTest.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Oct 6, 2020 7:04:10 PM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class StNodoInicioProcesoTest  extends AbstractCoreTest{
	
	@Autowired
	private INodoHelper nodoHelper;
	@Test
	public void reporteProcesosTest() throws BrioBPMException {
		DatosAutenticacionTO datosAutenticacion = getDatosAutenticacionTO();
		printerJson(datosAutenticacion);
		NodoInicioProcesoTO to = NodoInicioProcesoTO.builder().build();
//		DAORet<List<NodoInicioProcesoTO>, RetMsg> res =
//				nodoHelper.leeNodoInicioProceso(datosAutenticacion, to, "USUARIO");
//		res.getContent().forEach((item) -> {
//			log.info("{}", item);
//		});
//		printerJson(res.getContent());
	}

}
