package com.briomax.briobpm.data.test.base;

import java.math.BigDecimal;
import java.util.List;

import org.junit.runner.RunWith;

import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.data.test.config.DataTestInitializer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataTestInitializer.class)
@Transactional
@Slf4j
public abstract class AbstractDataTest {

	/** La Constante LOCALE_SPANISH. */
	protected static final String LOCALE_SPANISH = "ES-MX";

	/** La Constante LOCALE_ENGLISH. */
	protected static final String LOCALE_ENGLISH = "EN-EU";

	/** La Constante CVE_PROCESO. */
	protected static final String CVE_PROCESO = "INCIDENCIA-SMI";

	/** La Constante VERSION. */
	protected static final BigDecimal VERSION = new BigDecimal("1.00");

	/** La Constante CVE_NODO. */
	protected static final String CVE_NODO = "ACTIVIDAD-USUARIO";

	/** La Constante ID_NODO. */
	protected static final int ID_NODO = 1;

	/** La Constante CVE_ROL. */
	protected static final String CVE_ROL = "FACTURACION";

	/** La Constante CVE_AREA_TRABAJO. */
	protected static final String CVE_AREA_TRABAJO = "SOLICITUD_SOPORTE";

	/** El atributo o variable entidad. */
	@Value("${brio.bpm.entidad}")
	public @Getter String entidad;

	/** El atributo o variable localidad. */
	@Value("${brio.bpm.localidad}")
	public @Getter String localidad;

	/** El atributo o variable idioma. */
	@Value("${brio.bpm.idioma}")
	public @Getter String idioma;

	/** El atributo o variable user. */
	@Value("${brio.bpm.user}")
	public @Getter String user;
	
	/** El atributo o variable password. */
	@Value("${brio.bpm.password}")
	public @Getter String password;
	
	/**
	 * Imprimir.
	 * @param Generic DAORet<List<T>, RetMsg>.
	 */
	protected <T> void imprimir(final DAORet<List<T>, RetMsg> ret) {
		int size = 0;
		if (ret != null && ret.getContent() != null) {
			size = ret.getContent().size();
		}
		log.info("Meta: {}", ret.getMeta());
		log.info("Content size[{}]", size);
		ret.getContent().forEach((item) -> {
			log.info("\t{}", item);
		});
	}

}
