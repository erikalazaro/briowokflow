/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.jobs;

import java.text.ParseException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.briomax.briobpm.business.helpers.base.IDocumentoHelper;
import com.briomax.briobpm.business.helpers.base.IProcesoHelper;
import com.briomax.briobpm.business.helpers.base.ITemporizadorHelper;
import com.briomax.briobpm.business.helpers.base.ITemporizadorRepseHelper;
import com.briomax.briobpm.business.jobs.core.ITemporizador;
import com.briomax.briobpm.business.service.core.IEmailsService;
import com.briomax.briobpm.common.core.Constants;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.LocalidadEntidad;
import com.briomax.briobpm.persistence.repository.IInVariableProcesoRepository;
import com.briomax.briobpm.persistence.repository.ILocalidadEntidadRepository;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class Temporizador.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Oct 12, 2020 7:36:40 PM Modificaciones:
 * @since JDK 1.8
 */
@Component
@Transactional
@Slf4j
public class Temporizador implements ITemporizador {

	/** El atributo o variable email service. */
	@Autowired
	private IEmailsService emailService;

	@Autowired
	private ILocalidadEntidadRepository localidadRepository;
	
	@Autowired
	private IProcesoHelper procesoHelper;
	
	@Autowired
	private IDocumentoHelper documentoHelper;
	
	@Autowired
	private ITemporizadorHelper temporizadorHelper;
	
	@Autowired
	private ITemporizadorRepseHelper temporizadorRepseHelper;
	
	/** El atributo o variable Entidad service. */
	@Autowired
	private ILocalidadEntidadRepository localidadEntidadRepository;
	
	@Autowired
    private IInVariableProcesoRepository inVariableProcesoRepository;


	
	
	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.jobs.core.ITemporizador#actividades()
	 */
	@Scheduled(cron = "${brio.bpm.temporizador.actividades.job.cron}")
	@Override
	public void actividades() {
		log.debug(">>>>>>>>>> Temporizador de Actividades.");
		temporizadorActividaes();
	}

	/**
	 * Temporizador actividaes.
	 */
	@Async
	private void temporizadorActividaes() {
		RetMsg ret =  RetMsg.builder().status("ERROR").build();
		try {
			ret = temporizadorHelper.temporizadorActivades();
			log.error("TERMINA EL JOB DE TEMPORIZADOR DE ACTIVIDADES: " + ret.toString());				
		} catch (BrioBPMException | ParseException e) {
			log.error(">>>>>>>>>> Temporizador de Actividades. {} ", e.getMessage());
			ret.setMessage(e.getMessage());
		}
		
		// desacople SP_TEMPORIZADOR_ACTIVIDADES
		//RetMsg ret = temporizadorDAO.actividades();
		
		log.debug("<<<<<<<<<< Temporizador de Actividades: {}", ret);
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.jobs.core.ITemporizador#vigenciaDocumentos()
	 */
	@Scheduled(cron = "${brio.bpm.temporizador.vigenciadocumentos.job.cron}")
	@Override
	public void vigenciaDocumentos() {
		log.debug(">>>>>>>>>> Temporizador de Vigencia de Documentos.");
		try {
			temporizadorVigenciaDocuemntos();
		} catch (BrioBPMException e) {
			log.error(">>>>>>>>>> Temporizador BrioBPMException de vigenciaDocumentos. {} ", e.getMessage());
		} catch (ParseException e) {
			log.error(">>>>>>>>>> Temporizador de ParseException vigenciaDocumentos. {} ", e.getMessage());
		}
	}

	/**
	 * Temporizador vigencia docuemntos.
	 * @throws ParseException 
	 * @throws BrioBPMException 
	 */
	@Async
	private void temporizadorVigenciaDocuemntos() throws BrioBPMException, ParseException {
		// desacople SP_VIGENCIA_DOCUMENTOS
		//RetMsg ret = temporizadorDAO.vencimientoDocumentos();
		RetMsg ret = documentoHelper.vencimientoDocumentos();
		emailService.sendEmails("", "", "", "");
		log.debug("<<<<<<<<<< Temporizador de Vigencia de Documentos: {}", ret);
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.jobs.core.ITemporizador#procesaMensajes()
	 */
	@Scheduled(cron = "${brio.bpm.temporizador.procesamensajes.job.cron}")
	@Override
	public void procesaMensajes() {
		//RetMsg ret =  temporizadorDAO.procesaMensajes();
		DatosAutenticacionTO session = DatosAutenticacionTO.
				builder()
				.cveUsuario(Constants.USUARIO_MENSAJE).build();
		RetMsg ret = null;
		try {
			log.error("<<<<<<<<<< Temporizador de Procesa Mensajes ");
			ret = procesoHelper.procesaMensajes(session);
		} catch (BrioBPMException e) {
			log.error("<<<<<<<<<< BrioBPMException Temporizador de Procesa Mensajes: {}", e.getMensaje());
		} catch (ParseException e) {
			log.error("<<<<<<<<<< ParseException Temporizador de Procesa Mensajes: {}", e.getMessage());
		}
		emailService.sendEmails("", "", "", "");
		log.debug("<<<<<<<<<< Temporizador de Procesa Mensajes: {}", ret);
	}



	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.jobs.core.ITemporizador#procesaRepse()
	 */
	@Scheduled(cron = "${brio.bpm.temporizador.procesarepse.job.cron}")
	@Override
	public void procesaRepse() {
		log.debug("<<<<<<<<<< brio.bpm.temporizador.procesarepse.job.cron >>>>>>>>>>>>>");
		consultaRepseWeb();
	}
	
	
    /**
     * Método para notificación de usuarios.
     */
    @Scheduled(cron = "${brio.bpm.temporizador.notificacionusuarios.job.cron}") // Configura el cron en application.propertie
    @Override
    public void notifiacionUsuariosJob(){
    	log.info("******||||******");
		log.info("<<<<<<<<<< brio.bpm.temporizador.notificacionusuarios.job.cron >>>>>>>>>>>>>");
		List<LocalidadEntidad> lista = localidadEntidadRepository.entidadesHabilitadas();
    	for(LocalidadEntidad localidad :  lista) {
    		String entidad = localidad.getId().getCveEntidad();
    		String cveLocalidad = localidad.getId().getCveLocalidad();
    		String idioma = localidad.getIdioma().getCveIdioma();
    		emailService.notifiacionUsuarios(entidad, cveLocalidad, idioma);
    		emailService.notifiacionUsuariosRepse(entidad, cveLocalidad, idioma);
    	}
    }
    
    /**
     * Método para notificación de usuarios.
     */
    @Scheduled(cron = "${brio.bpm.temporizador.notificacionnewusuarios.job.cron}") // Configura el cron en application.propertie
    @Override
    public void notifiacionNewUsuariosJob(){
    	log.info("******||||******");
		log.info("<<<<<<<<<< brio.bpm.temporizador.notificacionnewusuarios.job.cron >>>>>>>>>>>>>");
		emailService.notifiacionNewUsuarios();

    }
    /**
     * Método para depurar documentos compliance repse.
     */
    @Scheduled(cron = "${brio.bpm.temporizador.depurardocumentos.job.cron}")
	@Override
	public void depurarDocumentosJob() {
		temporizadorHelper.depurarDocumentos();
		
	}
    

	 /**
     * Método para generar procesos .
     */
    @Scheduled(cron = "${brio.bpm.temporizador.generaDefincionesProcesos.job.cron}")
	@Override
	public void generaDefincionesProcesos() {
    	
		log.info("<<<<<<<<<< brio.bpm.temporizador.generaDefincionesProcesos.job.cron Obra >>>>>>>>>>>>>");
		temporizadorRepseHelper.agregarNuevosProcesos("SERVICIOS");
		
		log.info("<<<<<<<<<< brio.bpm.temporizador.generaDefincionesProcesos.job.cron Servicio >>>>>>>>>>>>>");
		temporizadorRepseHelper.agregarNuevosProcesos("OBRA");
		
	}
    
	 /**
     * Método para generar procesos .
     */
    @Scheduled(cron = "${brio.bpm.temporizador.ajustaDefincionesProcesos.job.cron}")
	@Override
	public void ajustaDefincionesProcesos() {   	
		
		log.info("<<<<<<<<<< brio.bpm.temporizador.ajustaDefincionesProcesos.job.cron Ajusta Procesos >>>>>>>>>>>>>");
		temporizadorRepseHelper.ajustaProcesos();
		
		log.info("<<<<<<<<<< brio.bpm.temporizador.ajustaDefincionesProcesos.job.cron Procesos Manuales >>>>>>>>>>>>>");
		temporizadorRepseHelper.procesosManuales();
	}
    
	 /**
     * Método para generar procesos .
     */
    @Scheduled(cron = "${brio.bpm.temporizador.ajustaProcesoReanudacion.job.cron}")
	@Override
	public void ajustaProcesoReanudacion() {   	
		
		log.info("<<<<<<<<<< brio.bpm.temporizador.ajustaProcesoReanudacion.job.cron Ajusta Procesos >>>>>>>>>>>>>");
		temporizadorRepseHelper.ajustaReanudacion();
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.jobs.core.ITemporizador#consultaRepseWeb()
	 */
    @Scheduled(cron = "${brio.bpm.temporizador.ajustaProcesoReanudacion.job.cron}")
	@Override
	public void consultaRepseWeb() {
    		log.info("<<<<<<<<<< CONSULTA REPSE POR RAZON SOCIAL >>>>>>>>>>>>>");
            temporizadorHelper.consultaRepse(); // Step 2: Call REPSE service for each razonSocial
        }
    
	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.jobs.core.ITemporizador#actividades()
	 */
	@Scheduled(cron = "${brio.bpm.temporizador.actividadespendientes.job.cron}")
	@Override
	public void actividadesPendientes() {
		log.debug(">>>>>>>>>> Temporizador de Actividades Pendientes.");
		temporizadorActividadesPendientes();
	}

	/**
	 * Temporizador actividaes.
	 */
	@Async
	private void temporizadorActividadesPendientes() {
		RetMsg ret =  RetMsg.builder().status("ERROR").build();
		try {
			ret = temporizadorHelper.activadesPendientes();
			log.error("TERMINA EL JOB DE NOTIFICACION DE ACTIVIDADES PENDIENTES: " + ret.toString());				
		} catch (BrioBPMException | ParseException e) {
			log.error(">>>>>>>>>> Notificacion de Actividades Pendientes. {} ", e.getMessage());
			ret.setMessage(e.getMessage());
		}
        
    }
}


