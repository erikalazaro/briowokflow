/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */


package com.briomax.briobpm.business.service.catalogs;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.business.service.catalogs.core.IInVariableProcesoService;
import com.briomax.briobpm.business.service.catalogs.core.IMessagesService;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.InVariableProceso;
import com.briomax.briobpm.persistence.entity.InVariableProcesoPK;
import com.briomax.briobpm.persistence.repository.IInVariableProcesoRepository;
import com.briomax.briobpm.transferobjects.ComboBoxTO;
import com.briomax.briobpm.transferobjects.TrabajadoresTO;
import com.briomax.briobpm.transferobjects.emun.MagicNumber;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class RolProcesoService.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jun 26, 2020 12:39:56 PM Modificaciones:
 * @since JDK 1.8
 */
@Service
@Transactional
@Slf4j
public class InVariableProcesoService implements IInVariableProcesoService {

	/** La Constante ROL_PROCESO. */
	private static final String IN_VARIABLE_PROCESO = "IN_VARIABLE_PROCESO";

	/** La Constante DATA_ERROR. */
	private static final String DATA_ERROR = "###DATA-ERROR### : {} ";

	/** La Constante VACIO. */
	private static final String VACIO = "";

	/** El atributo o variable repository. */
	@Autowired
	private IInVariableProcesoRepository repository;



	/** El atributo o variable messages service. */
	@Autowired
	private IMessagesService messagesService;

	/**
	 * Crear una nueva instancia del objeto mtto st rol proceso.
	 */
	public InVariableProcesoService() {
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.service.catalogs.core.IRolProcesoService#getAll(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO, java.lang.String, java.lang.String)
	 */
	@Override
	public List<ComboBoxTO> getAllNombres(DatosAutenticacionTO session)
		throws BrioBPMException {
		List<ComboBoxTO> listDto = new ArrayList<ComboBoxTO>();
		try {
			log.debug("xxxxxxxxxxxxxxxxx  Entre para obtener getAllNombres");
			List<Object[]> listEntity = repository.getRfcAndNombreByProces(session.getCveEntidad(), session.getCveUsuario());
			log.debug("xxxxxxxxxxxxxxxxx  listEntity.size: " + listEntity.size());
			if (listEntity.size() > 0) {

				for (int i = 0; i < listEntity.size(); i++) {
					Object[] entity = listEntity.get(i);
					
					String id = (String) Arrays.asList(entity).get(MagicNumber.ZERO.getValue());
					String desc = (String) Arrays.asList(entity).get(MagicNumber.ONE.getValue());	

					ComboBoxTO to = ComboBoxTO.builder().id(id).descripcion(desc).build();
					listDto.add(to);

				}
			}
		}
		catch (DataAccessException exData) {
			log.error(DATA_ERROR, exData.getMessage());
			throw new BrioBPMException(messagesService.getMessage(session, IN_VARIABLE_PROCESO,
					"MANTENIMIENTO_INVARIABLEPROCESO_CONSULTAR_ERROR", VACIO), exData); 
		}
		return listDto;
	}

	@Override
	public List<ComboBoxTO> getContratosByRfc(DatosAutenticacionTO session, String rfc)
		throws BrioBPMException {
		List<ComboBoxTO> listDto = new ArrayList<ComboBoxTO>();
		try {
			
			List<Object> listEntity = repository.contratosByRfc(session.getCveEntidad(), rfc);
			
			if(!listEntity.isEmpty()) {
				listEntity.forEach(item -> {
					Object[] row = (Object[]) item;
					ComboBoxTO to = ComboBoxTO.builder()
							.id((String) Arrays.asList(row).get(MagicNumber.ZERO.getValue()) + 
									"|" + (String) Arrays.asList(row).get(MagicNumber.ONE.getValue()) +
									"|" + (String) Arrays.asList(row).get(MagicNumber.TWO.getValue()))
							.descripcion((String) Arrays.asList(row).get(MagicNumber.TWO.getValue()))
							.build();

					listDto.add(to);
				});
			};
		}
		catch (DataAccessException exData) {
			log.error(DATA_ERROR, exData.getMessage());
			throw new BrioBPMException(messagesService.getMessage(session, IN_VARIABLE_PROCESO,
					"MANTENIMIENTO_INVARIABLEPROCESO_CONSULTAR_ERROR", VACIO), exData); 
		}
		return listDto;
	}
	
	@Override
	public String eliminaTrabajador(DatosAutenticacionTO session, String cveInstancia, String cveProceso, Integer ocurrencia)
			throws BrioBPMException {
		String estatus = "OK";
		try {
			repository.deleteTrabajadorProceso(session.getCveEntidad(), cveProceso, cveInstancia, ocurrencia);
		}
		catch (DataAccessException exData) {
			log.error(DATA_ERROR, exData.getMessage());
			throw new BrioBPMException(messagesService.getMessage(session, IN_VARIABLE_PROCESO,
					"MANTENIMIENTO_INVARIABLEPROCESO_ELIMINAR_TRABAJADOR_ERROR", VACIO), exData); 
		}
		return estatus;
	}
	
	@Override
	public String ingresarTrabajador(DatosAutenticacionTO userSession, TrabajadoresTO to)
			throws BrioBPMException {
		String estatus = "OK";
		try {
			Integer ocurrencia;
			if (to.getOcurrencia() > 0) {
				ocurrencia = to.getOcurrencia();
			} else {
				ocurrencia = repository.maxOcurrenciaByContrato(userSession.getCveEntidad(), to.getRfc(), to.getNumContrato());
			}
			
			InVariableProcesoPK pk = InVariableProcesoPK.builder()
					.cveEntidad(userSession.getCveEntidad())
					.cveInstancia(to.getInstancia())
					.cveProceso("REGISTRO_DE_CONTRATO")
					.cveVariable("VPRO_01_NOMBRE_TRABAJADOR_TE")
					.secuenciaValor(1)
					.ocurrencia(ocurrencia)
					.version(new BigDecimal(1.0))
					.build();
		
			InVariableProceso entity = InVariableProceso.builder()
						.id(pk)
						.valorAlfanumerico(to.getNombreTrabajador())
						.build();
			repository.saveAndFlush(entity);
			
			pk = InVariableProcesoPK.builder()
					.cveEntidad(userSession.getCveEntidad())
					.cveInstancia(to.getInstancia())
					.cveProceso("REGISTRO_DE_CONTRATO")
					.cveVariable("VPRO_01_CURP_TE")
					.secuenciaValor(1)
					.ocurrencia(ocurrencia)
					.version(new BigDecimal(1.0))
					.build();
		
			entity = InVariableProceso.builder()
					.id(pk)
					.valorAlfanumerico(to.getCurp())
					.build();
			repository.saveAndFlush(entity);
			
			pk = InVariableProcesoPK.builder()
					.cveEntidad(userSession.getCveEntidad())
					.cveInstancia(to.getInstancia())
					.cveProceso("REGISTRO_DE_CONTRATO")
					.cveVariable("VPRO_01_SALARIO_BASE_COTIZACION_TE")
					.secuenciaValor(1)
					.ocurrencia(ocurrencia)
					.version(new BigDecimal(1.0))
					.build();
		
			entity = InVariableProceso.builder()
					.id(pk)
					.valorDecimal(new BigDecimal(to.getSalarioBase()))
					.build();
			repository.saveAndFlush(entity);
			
			pk = InVariableProcesoPK.builder()
					.cveEntidad(userSession.getCveEntidad())
					.cveInstancia(to.getInstancia())
					.cveProceso("REGISTRO_DE_CONTRATO")
					.cveVariable("VPRO_01_NSS_TE")
					.secuenciaValor(1)
					.ocurrencia(ocurrencia)
					.version(new BigDecimal(1.0))
					.build();
		
			entity = InVariableProceso.builder()
					.id(pk)
					.valorAlfanumerico(to.getSeguroSocial())
					.build();
			repository.saveAndFlush(entity);
			
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			Date fechaI = formato.parse(to.getFechaInicial());
			
			pk = InVariableProcesoPK.builder()
					.cveEntidad(userSession.getCveEntidad())
					.cveInstancia(to.getInstancia())
					.cveProceso("REGISTRO_DE_CONTRATO")
					.cveVariable("VPRO_01_FECHA_INICIO_DEL_TE")
					.secuenciaValor(1)
					.ocurrencia(ocurrencia)
					.version(new BigDecimal(1.0))
					.build();
		
			entity = InVariableProceso.builder()
					.id(pk)
					.valorFecha(fechaI)
					.build();
			repository.saveAndFlush(entity);
			
			Date fechaF = formato.parse(to.getFechaFinal());
			pk = InVariableProcesoPK.builder()
					.cveEntidad(userSession.getCveEntidad())
					.cveInstancia(to.getInstancia())
					.cveProceso("REGISTRO_DE_CONTRATO")
					.cveVariable("VPRO_01_FECHA_INICIO_AL_TE")
					.secuenciaValor(1)
					.ocurrencia(ocurrencia)
					.version(new BigDecimal(1.0))
					.build();
		
			entity = InVariableProceso.builder()
					.id(pk)
					.valorFecha(fechaF)
					.build();
			repository.saveAndFlush(entity);
		}
		catch (ParseException | DataAccessException exData) {
			log.error(DATA_ERROR, exData.getMessage());
			throw new BrioBPMException(messagesService.getMessage(userSession, IN_VARIABLE_PROCESO,
					"MANTENIMIENTO_INVARIABLEPROCESO_CREACION_ERROR", VACIO), exData); 
		} 
		return estatus;
	}

	@Override
	public List<ComboBoxTO> getContratosByRfcProceso(DatosAutenticacionTO session, String rfc, String cveProceso)
			throws BrioBPMException {
		List<ComboBoxTO> listDto = new ArrayList<ComboBoxTO>();
		try {
			
			List<Object> listEntity = repository.contratosByRfcProceso(session.getCveEntidad(), rfc, cveProceso);
			
			if(!listEntity.isEmpty()) {
				listEntity.forEach(item -> {
					Object[] row = (Object[]) item;
					ComboBoxTO to = ComboBoxTO.builder()
							.id((String) Arrays.asList(row).get(MagicNumber.ZERO.getValue()) + 
									"|" + (String) Arrays.asList(row).get(MagicNumber.ONE.getValue()) +
									"|" + (String) Arrays.asList(row).get(MagicNumber.TWO.getValue()))
							.descripcion((String) Arrays.asList(row).get(MagicNumber.TWO.getValue()))
							.build();

					listDto.add(to);
				});
			};
		}
		catch (DataAccessException exData) {
			log.error(DATA_ERROR, exData.getMessage());
			throw new BrioBPMException(messagesService.getMessage(session, IN_VARIABLE_PROCESO,
					"MANTENIMIENTO_INVARIABLEPROCESO_CONSULTAR_ERROR", VACIO), exData); 
		}
		return listDto;
	}
	
	@Override
	public List<ComboBoxTO> getContratosByRfcProcesos(DatosAutenticacionTO session, String rfc)
			throws BrioBPMException {
		List<ComboBoxTO> listDto = new ArrayList<ComboBoxTO>();
		try {

			List<Object> listEntity = repository.contratosByRfcProcesos(session.getCveEntidad(), rfc, session.getCveUsuario());
			
			if(!listEntity.isEmpty()) {
				listEntity.forEach(item -> {
					Object[] row = (Object[]) item;
					ComboBoxTO to = ComboBoxTO.builder()
							.id((String) Arrays.asList(row).get(MagicNumber.ZERO.getValue()) + 
									"|" + (String) Arrays.asList(row).get(MagicNumber.ONE.getValue()) +
									"|" + (String) Arrays.asList(row).get(MagicNumber.TWO.getValue()))
							.descripcion((String) Arrays.asList(row).get(MagicNumber.TWO.getValue()))
							.build();

					listDto.add(to);
				});
			};
		}
		catch (DataAccessException exData) {
			log.error(DATA_ERROR, exData.getMessage());
			throw new BrioBPMException(messagesService.getMessage(session, IN_VARIABLE_PROCESO,
					"MANTENIMIENTO_INVARIABLEPROCESO_CONSULTAR_ERROR", VACIO), exData); 
		}
		return listDto;
	}
}
