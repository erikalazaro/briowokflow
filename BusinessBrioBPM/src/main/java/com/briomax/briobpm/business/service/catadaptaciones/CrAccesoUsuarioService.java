/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */


package com.briomax.briobpm.business.service.catadaptaciones;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.business.convertes.IAccesoUsuariosConverter;
import com.briomax.briobpm.business.helpers.base.ICriptography;
import com.briomax.briobpm.business.service.catadaptaciones.core.ICrAccesoUsuarioService;
import com.briomax.briobpm.business.service.catalogs.core.IMessagesService;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.CrAccesoUsuario;
import com.briomax.briobpm.persistence.entity.CrAccesoUsuarioPK;
import com.briomax.briobpm.persistence.entity.Notificacion;
import com.briomax.briobpm.persistence.entity.ParametroGeneral;
import com.briomax.briobpm.persistence.entity.PropositoCorreo;
import com.briomax.briobpm.persistence.entity.PropositoCorreoPK;
import com.briomax.briobpm.persistence.entity.StRolProceso;
import com.briomax.briobpm.persistence.entity.Usuario;
import com.briomax.briobpm.persistence.entity.UsuarioFacultad;
import com.briomax.briobpm.persistence.entity.UsuarioFacultadPK;
import com.briomax.briobpm.persistence.entity.UsuarioPK;
import com.briomax.briobpm.persistence.entity.UsuarioRol;
import com.briomax.briobpm.persistence.entity.UsuarioRolPK;
import com.briomax.briobpm.persistence.repository.ICrAccesoUsuarioRepository;
import com.briomax.briobpm.persistence.repository.INotificacionRepository;
import com.briomax.briobpm.persistence.repository.IParametroGeneralRepository;
import com.briomax.briobpm.persistence.repository.IPropositoCorreoRepository;
import com.briomax.briobpm.persistence.repository.IStRolProcesoRepository;
import com.briomax.briobpm.persistence.repository.IUsuarioFacultad;
import com.briomax.briobpm.persistence.repository.IUsuarioRepository;
import com.briomax.briobpm.persistence.repository.IUsuarioRolRepository;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.repse.CrUsuarioTO;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Clase/Iterfarce/Enumeracion CrAccesoUsuarioService.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Nov 25, 2025 12:08:29 Modificaciones:
 * @since JDK 1.8
 */
@Service
@Transactional
@Slf4j
public class CrAccesoUsuarioService implements ICrAccesoUsuarioService {

	/** La Constante ROL_PROCESO. */
	private static final String USUARIO = "USUARIO";

	/** La Constante DATA_ERROR. */
	private static final String DATA_ERROR = "###DATA-ERROR### : {} ";

	/** La Constante VACIO. */
	private static final String VACIO = "";

	/** El atributo o variable repository. */
	@Autowired
	private IUsuarioRepository repository;

	/** El atributo o variable messages service. */
	@Autowired
	private IMessagesService messagesService;

	/** El atributo o variable criptography. */
	@Autowired
	private ICriptography criptography;
	
	/** El atributo o variable UsuraioFacultad. */
	@Autowired
	private IUsuarioFacultad usuarioFacultad;
	
	/** El atributo o variable Usuraio. */
	@Autowired
	private ICrAccesoUsuarioRepository accesoUsuarioRepository;
	
	/** El atributo o variable Parametro General. */
	@Autowired
	private IParametroGeneralRepository parametroGeneralRepository;
	
	/** El atributo o variable PropositoCorreo. */
	@Autowired
	private IPropositoCorreoRepository propositoCorreoRepository;
	
	/** El atributo o variable Notificacio. */
	@Autowired
	private INotificacionRepository notificacionRepository;

	/** El atributo o variable Usuario Rol. */
	@Autowired
	private IUsuarioRolRepository usuarioRolRepository;
	
	/** El atributo o variable Usuario Rol. */
	@Autowired
	private IStRolProcesoRepository rolProcesoRepository;
	
	/**
	 * Crear una nueva instancia del objeto usuario service.
	 */
	public CrAccesoUsuarioService() {
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.service.catalogs.core.IUsuarioService#getAll(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO)
	 */
	@Override
	public List<CrUsuarioTO> getAll(DatosAutenticacionTO session) throws BrioBPMException {
		List<CrUsuarioTO> listDto = new ArrayList<CrUsuarioTO>();
		try {
			List<CrAccesoUsuario> lista = accesoUsuarioRepository.obtenContratista(session.getCveEntidad(), "%" + session.getCveUsuario() + "%");
			
			log.debug("Converte Entity (Entity) -> Dto (UsuarioTO) ");
			listDto
				.addAll(lista.stream().map(IAccesoUsuariosConverter.converterEntityToDTO).collect(Collectors.toList()));
		}
		catch (DataAccessException exData) {
			log.error(DATA_ERROR, exData.getMessage());
			throw new BrioBPMException(
				messagesService.getMessage(session, USUARIO, "MANTENIMIENTO_USUARIO_CONSULTAR_ERROR", VACIO), exData);
		}
		return listDto;
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.service.catalogs.core.IUsuarioService#insert(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO, com.briomax.briobpm.transferobjects.catalogs.UsuarioTO)
	 */
	public RetMsg insert(DatosAutenticacionTO session, CrUsuarioTO usuario) throws BrioBPMException {
		UsuarioPK id = UsuarioPK.builder()
		.cveEntidad(session.getCveEntidad())
		.cveUsuario(usuario.getCveUsuario())
		.build();
		RetMsg response = RetMsg.builder().status("ERROR").build();
		
		try  {
			if(repository.existsById(id)) {
				response.setMessage(messagesService.getMessage(session, USUARIO,
						"MANTENIMIENTO_USUARIO_EXISTE", VACIO));
			} else {

					Optional<ParametroGeneral> pFacultad = parametroGeneralRepository.findById("CLAVE_FACULTAD_REPSE");
					
					
					
					//ajuste de la fecha para el password
					LocalDate fechaActual = LocalDate.now();
			        LocalDate fechaMas6Anios = fechaActual.plusYears(6);
			        Date fechaDate = Date.from(
			                fechaMas6Anios
			                    .atStartOfDay(ZoneId.systemDefault())
			                    .toInstant()
			            );
			        log.error("[ALTA DE USUARIO	] fecha de password = {} ",fechaDate);
					repository.insertFecha(session.getCveEntidad(), session.getCveLocalidad(), 
							usuario.getCveUsuario(), criptography.hashText(usuario.getPassword()), fechaDate,
							usuario.getNombre(), "EXTERNO", "HABILITADO", usuario.getEmail());
					log.error("[ALTA DE USUARIO	] Inserto usuario en tabla USUARIO ");
				
					UsuarioFacultadPK ufId = UsuarioFacultadPK.builder()
							.cveEntidad(session.getCveEntidad())
							.cveUsuario(usuario.getCveUsuario())
							.cveFacultad(pFacultad.get().getValorAlfanumerico())
							.build();
					
					UsuarioFacultad entity = UsuarioFacultad.builder().id(ufId).build();
					
					usuarioFacultad.saveAndFlush(entity);
					log.error("[ALTA DE USUARIO	] Inserto usuario Facultad {}", pFacultad.get().getValorAlfanumerico());
					
					//obtener de parametros generales los procesos y el rol
					Optional<ParametroGeneral> pRol = parametroGeneralRepository.findById("CLAVE_ROL_REPSE");	
					if (pRol.isPresent()) {
						List<StRolProceso> listProcesos = rolProcesoRepository.listaRpese(session.getCveEntidad(), pRol.get().getValorAlfanumerico());
						
						for (StRolProceso stRolProceso : listProcesos) {
							
							UsuarioRolPK urId = UsuarioRolPK.builder()
									.cveEntidad(session.getCveEntidad())
									.cveRol(pRol.get().getValorAlfanumerico())
									.cveUsuario(usuario.getCveUsuario())
									.version(stRolProceso.getId().getVersion())
									.cveProceso(stRolProceso.getId().getCveProceso())		
									.build();
							
							UsuarioRol usEntity = UsuarioRol.builder()
									.id(urId)
									.cveEntidadRol(session.getCveEntidad())
									.build();
							
							usuarioRolRepository.saveAndFlush(usEntity);
							log.error("[ALTA DE USUARIO	] Inserto usuario rol {}", pRol.get().getValorAlfanumerico());
							log.error("[ALTA DE USUARIO	] Inserto usuario proceso {}", stRolProceso.getId().getCveProceso());
						}
					}

					
					CrAccesoUsuarioPK auPK = CrAccesoUsuarioPK.builder()
					.cveEntidad(session.getCveEntidad())
					.cveUsuario(usuario.getCveUsuario())
					.rfc(usuario.getRfc())
					.tipoAccesoCr("CONTRATISTA")
					.build();
					
					Optional<CrAccesoUsuario> crUsuario = accesoUsuarioRepository.findById(auPK);					
							
					if (crUsuario.isPresent()) {
						log.error("[ALTA DE USUARIO	] Inserto exite en tabla acceso ");
						String contratante = crUsuario.get().getUsuarioContratante();
						CrAccesoUsuario auEntity = CrAccesoUsuario.builder()
						.id(auPK)
						.usuarioContratante(contratante + "|" + session.getCveUsuario())
						.build();
						accesoUsuarioRepository.saveAndFlush(auEntity);
						
						log.error("[ALTA DE USUARIO	] Inserto contratantes: {}", auEntity.getUsuarioContratante());
						
					} else {
						log.error("[ALTA DE USUARIO	] Inserto NO exite en tabla acceso ");
						CrAccesoUsuario auEntity = CrAccesoUsuario.builder()
						.id(auPK)
						.usuarioContratante(session.getCveUsuario())
						.build();
						accesoUsuarioRepository.saveAndFlush(auEntity);
					}
									
					 PropositoCorreoPK idPro = PropositoCorreoPK.builder()
							 .cveEntidad(session.getCveEntidad())
							 .cveProposito("NUEVO_USUARIO")
							 .build();
					 Optional<PropositoCorreo> proposito = propositoCorreoRepository.findById(idPro);
					 
					 if (proposito.isPresent()) {
						 PropositoCorreo info = proposito.get();
						 String cuerpoConfigurado = info.getCuerpoCorreo().replace("@USUARIO@", usuario.getCveUsuario());

						 Integer numeroCorreo = notificacionRepository.obtieneNumeroCorreo();
						 Integer secuenciaCorreo = (numeroCorreo != null ? numeroCorreo : 0) + 1;
						 
						 Notificacion datos = Notificacion.builder()
								 .secuenciaCorreo(secuenciaCorreo)
								 .para(usuario.getEmail())
								 .cuerpo(cuerpoConfigurado)
								 .asunto(info.getAsunto())
								 .ccp("")
								 .build();
						 notificacionRepository.saveAndFlush(datos);
					 }
					 log.error("[ALTA DE USUARIO	] Notifica al correo ");

                    
				response.setMessage(messagesService.getMessage(session, USUARIO,
						"MANTENIMIENTO_USUARIO_REGISTRAR_OK", VACIO));
			
			}
		} catch (DataAccessException exData) {
			log.error(DATA_ERROR, exData.getMessage());
			throw new BrioBPMException(messagesService.getMessage(session, USUARIO,
					"MANTENIMIENTO_USUARIO_REGISTRAR_ERROR", VACIO), exData);
		}
		
		return response;
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.service.catalogs.core.IUsuarioService#update(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO, com.briomax.briobpm.transferobjects.catalogs.UsuarioTO)
	 */
	@Override
	public RetMsg update(DatosAutenticacionTO session, CrUsuarioTO usuario) throws BrioBPMException {
		return process(session, usuario, "MANTENIMIENTO_USUARIO_MODIFICAR_OK", "MANTENIMIENTO_USUARIO_MODIFICAR_ERROR",
			VACIO);
	}

	/**
	 * Process.
	 * @param session el session.
	 * @param usuario el usuario.
	 * @param codOk el cod ok.
	 * @param codError el cod error.
	 * @param variablesValores el variables valores.
	 * @return el ret msg.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	private RetMsg process(DatosAutenticacionTO session, CrUsuarioTO usuario, String codOk, String codError,
		String variablesValores) throws BrioBPMException {
		RetMsg response = RetMsg.builder().status("ERROR").build();
		try {
			
			UsuarioPK id = UsuarioPK.builder()
					.cveEntidad(session.getCveEntidad())
					.cveUsuario(usuario.getCveUsuario())
					.build();
			
			Optional<Usuario> datos = repository.findById(id);
			if (datos.isPresent()) {
				Usuario item = datos.get();
				item.setNombre(usuario.getNombre());
				item.setCorreoElectronico(usuario.getEmail());
				repository.saveAndFlush(item);
				
				response.setStatus("OK");
				response.setMessage(messagesService.getMessage(session, USUARIO,
					codOk, VACIO));
			} else {
				response.setMessage(messagesService.getMessage(session, USUARIO,
						"MANTENIMIENTO_USUARIO_NO_EXISTE", VACIO));
			}
		

		}
		catch (DataAccessException exData) {
			log.error(DATA_ERROR, exData.getMessage());
			throw new BrioBPMException(messagesService.getMessage(session, USUARIO,
				codError, VACIO), exData);
		}
		return response;
	}

}
