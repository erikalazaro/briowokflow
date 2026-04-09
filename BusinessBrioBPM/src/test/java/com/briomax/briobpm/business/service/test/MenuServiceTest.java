package com.briomax.briobpm.business.service.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.briomax.briobpm.business.service.core.IMenuService;
import com.briomax.briobpm.business.test.base.AbstractCoreTest;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.MenuAreaTrabajoTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MenuServiceTest extends AbstractCoreTest{
	
	@Autowired
	private IMenuService menuService;
	

	@Test
	public void menuTest() throws BrioBPMException {
		DatosAutenticacionTO datosAutenticacion = getDatosAutenticacionTO();
		datosAutenticacion.setCveUsuario("Admin.Opera");
		printerJson(datosAutenticacion);
		DAORet<List<MenuAreaTrabajoTO>, RetMsg> res =
			menuService.obtenerMenuAreaTrabajo(datosAutenticacion);
		res.getContent().forEach((item) -> {
			log.info("{}", item);
		});
		printerJson(res.getContent());
	}

}
