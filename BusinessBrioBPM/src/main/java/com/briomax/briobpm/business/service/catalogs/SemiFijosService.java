/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */


package com.briomax.briobpm.business.service.catalogs;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.briomax.briobpm.business.helpers.base.IValoresColumnaHelper;
import com.briomax.briobpm.business.service.catalogs.core.IMessagesService;
import com.briomax.briobpm.business.service.catalogs.core.ISemiFijosService;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.dao.base.ISemiFijosDAO;
import com.briomax.briobpm.persistence.entity.namedquery.LeeValoresColumna;
import com.briomax.briobpm.transferobjects.ComboBoxTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class SemiFijosService.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jul 2, 2020 4:26:39 PM Modificaciones:
 * @since JDK 1.8
 */
@Service
@Transactional
@Slf4j
public class SemiFijosService implements ISemiFijosService {

	/** La Constante DATA_ERROR. */
	private static final String DATA_ERROR = "###DATA-ERROR### : {} ";

	/** La Constante VACIO. */
	private static final String VACIO = "";

	/** El atributo o variable mensajes dao. */
	@Autowired
	private IValoresColumnaHelper valoresColumnaHelper;

	/** El atributo o variable messages service. */
	@Autowired
	private IMessagesService messagesService;
	
	

	/**
	 * Crear una nueva instancia del objeto semi fijos service.
	 */
	public SemiFijosService() {
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.service.catalogs.core.ISemiFijosService#getCmbSituacionRolProceso(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO)
	 */
	@Override
	public List<ComboBoxTO> getCmbSituacionRolProceso(DatosAutenticacionTO session) throws BrioBPMException {
		return getCmb(session, "ROL_PROCESO", BigDecimal.ZERO, "ST_ROL_PROCESO", "SITUACION",
			"MANTENIMIENTO_ROLPROCESO_CMBSITUACION_ERROR", VACIO);
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.service.catalogs.core.ISemiFijosService#getCmbSituacionUsuario(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO)
	 */
	@Override
	public List<ComboBoxTO> getCmbSituacionUsuario(DatosAutenticacionTO session) throws BrioBPMException {
		return getCmb(session, "USUARIO", BigDecimal.ZERO, "USUARIO", "SITUACION",
			"MANTENIMIENTO_USUARIO_CMBSITUACION_ERROR", VACIO);
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.service.catalogs.core.ISemiFijosService#getCmbTipoUsuario(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO)
	 */
	@Override
	public List<ComboBoxTO> getCmbTipoUsuario(DatosAutenticacionTO session) throws BrioBPMException {
		return getCmb(session, "USUARIO", BigDecimal.ZERO, "USUARIO", "TIPO",
			"MANTENIMIENTO_USUARIO_CMBTIPO_ERROR", VACIO);
	}

	/**
	 * Obtener el valor de cmb.
	 * @param session el session.
	 * @param cveProceso el cve proceso.
	 * @param version el version.
	 * @param nombreTabla el nombre tabla.
	 * @param nombreColumna el nombre columna.
	 * @param codigoMensaje el codigo mensaje.
	 * @param variablesValores el variables valores.
	 * @return el cmb.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	private List<ComboBoxTO> getCmb(DatosAutenticacionTO session, String cveProceso, BigDecimal version,
		String nombreTabla, String nombreColumna, String codigoMensaje, String variablesValores)
		throws BrioBPMException {
		List<ComboBoxTO> list = new ArrayList<ComboBoxTO>();
		try {
			log.debug(" << {} {} >> {}", nombreTabla, nombreColumna, session);
			DAORet<List<LeeValoresColumna>, RetMsg> ret =
					valoresColumnaHelper.getValoresColumna(session.getCveUsuario(), session.getCveEntidad(),
					session.getCveLocalidad(), session.getCveIdioma(), cveProceso, version, nombreTabla, nombreColumna);
			log.info(" ### {} {} ### {}", nombreTabla, nombreColumna, ret.getMeta());
			if (ret.getMeta().getStatus().equalsIgnoreCase("OK")) {
				list = converter(ret.getContent());
			}
		}
		catch (DataAccessException exData) {
			log.error(DATA_ERROR, exData.getMessage());
			throw new BrioBPMException(messagesService.getMessage(session, cveProceso, codigoMensaje, variablesValores),
				exData);
		}
		return list;
	}

	/**
	 * Converter.
	 * @param entitys el entitys.
	 * @return el list.
	 */
	private List<ComboBoxTO> converter(List<LeeValoresColumna> entitys) {
		List<ComboBoxTO> list = new ArrayList<ComboBoxTO>();
		for (LeeValoresColumna entity : entitys) {
			list.add(
				ComboBoxTO.builder().id(entity.getValorBaseDatos()).descripcion(entity.getValorPantalla()).build());
		}
		return list;
	}

}
