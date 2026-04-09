package com.briomax.briobpm.data.test.dao;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.data.test.base.AbstractDataTest;
import com.briomax.briobpm.persistence.dao.base.IUsuarioDAO;
import com.briomax.briobpm.persistence.entity.namedquery.DatosUsuario;
import com.briomax.briobpm.persistence.entity.namedquery.UsuarioRolProceso;

import lombok.extern.slf4j.Slf4j;


/**
 * El objetivo de la Class UsuarioDAOTest.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion 27/01/2020 05:08:38 PM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class UsuarioDAOTest extends AbstractDataTest {

	/** El atributo o variable usuario DAO. */
	@Autowired
	private IUsuarioDAO usuarioDAO;

	/**
	 * Valida usuario test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void validaUsuarioTest() throws BrioBPMException {
		log.info("\tPARAMETERS: {} ", getUser());
		final DAORet<List<DatosUsuario>, RetMsg> ret = usuarioDAO.validaUsuario(getUser(), 
			getEntidad(), getLocalidad(), getIdioma(), getPassword());
		imprimir(ret);
		assertEquals("OK", ret.getMeta().getStatus().toUpperCase());
	}

	@Test
	public void getUsuariosRolTest() throws BrioBPMException {
		List<UsuarioRolProceso> list = usuarioDAO.getUsuariosRol("BRIOMAX", "G2 ACF", new BigDecimal("1.00"), "ROL_ADMIN_OPERA");
		log.info("COUNT: {}", list.size());
		for (UsuarioRolProceso usuarioRol : list) {
			log.info("{}", usuarioRol);
		}
	}
	
}
