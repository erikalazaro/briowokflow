/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */


package com.briomax.briobpm.business.service.catalogs;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.business.convertes.IUsuariosConverter;
import com.briomax.briobpm.business.helpers.base.ICriptography;
import com.briomax.briobpm.business.service.catalogs.core.IMessagesService;
import com.briomax.briobpm.business.service.catalogs.core.IUsuarioService;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.Notificacion;
import com.briomax.briobpm.persistence.entity.PropositoCorreo;
import com.briomax.briobpm.persistence.entity.PropositoCorreoPK;
import com.briomax.briobpm.persistence.entity.ResetPasswordToken;
import com.briomax.briobpm.persistence.entity.ResetPasswordTokenPK;
import com.briomax.briobpm.persistence.entity.Usuario;
import com.briomax.briobpm.persistence.entity.UsuarioFacultad;
import com.briomax.briobpm.persistence.entity.UsuarioFacultadPK;
import com.briomax.briobpm.persistence.entity.UsuarioPK;
import com.briomax.briobpm.persistence.repository.INotificacionRepository;
import com.briomax.briobpm.persistence.repository.IPropositoCorreoRepository;
import com.briomax.briobpm.persistence.repository.IResetPasswordTokenRepository;
import com.briomax.briobpm.persistence.repository.IUsuarioFacultad;
import com.briomax.briobpm.persistence.repository.IUsuarioRepository;
import com.briomax.briobpm.transferobjects.catalogs.UsuarioResetTO;
import com.briomax.briobpm.transferobjects.catalogs.UsuarioTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Clase/Iterfarce/Enumeracion UsarioService.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jul 27, 2020 2:08:36 PM Modificaciones:
 * @since JDK 1.8
 */
@Service
@Transactional
@Slf4j
public class UsuarioService implements IUsuarioService {

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
	
	/** El atributo o variable PropositoCorreo. */
	@Autowired
	private IPropositoCorreoRepository propositoCorreoRepository;
	
	/** El atributo o variable Notificacio. */
	@Autowired
	private  INotificacionRepository notificacionRepository;

	/** El atributo o variable ResetPasswordTokenRepository. */
	@Autowired
	private  IResetPasswordTokenRepository resetPasswordTokenRepository;
	
	@Value("${url.front}")
	private String url;
	
	/**
	 * Crear una nueva instancia del objeto usuario service.
	 */
	public UsuarioService() {
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.service.catalogs.core.IUsuarioService#getAll(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO)
	 */
	@Override
	public List<UsuarioTO> getAll(DatosAutenticacionTO session) throws BrioBPMException {
		List<UsuarioTO> listDto = new ArrayList<UsuarioTO>();
		try {
			Usuario usuario = Usuario.builder()
					.id(UsuarioPK.builder()
						.cveEntidad(session.getCveEntidad())
						.build())
					.build();
			List<Usuario> listEntity = repository.findAll(Example.of(usuario),
				Sort.by(Sort.Direction.ASC, "id.cveUsuario"));
			log.debug("Converte Entity (Entity) -> Dto (UsuarioTO) ");
			listDto
				.addAll(listEntity.stream().map(IUsuariosConverter.converterEntityToDTO).collect(Collectors.toList()));
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
	@Override
	public RetMsg insert(DatosAutenticacionTO session, UsuarioTO usuario) throws BrioBPMException {
		UsuarioPK id = UsuarioPK.builder()
		.cveEntidad(usuario.getCveEntidad())
		.cveUsuario(usuario.getCveUsuario())
		.build();
		RetMsg response = RetMsg.builder().status("ERROR").build();
		
		try  {
			if(repository.existsById(id)) {
				response.setMessage(messagesService.getMessage(session, USUARIO,
						"MANTENIMIENTO_USUARIO_EXISTE", VACIO));
			} else {

					repository.insertFecha(usuario.getCveEntidad(), usuario.getCveLocalidad(), 
							usuario.getCveUsuario(), criptography.hashText(usuario.getPassword()), 
							usuario.getFecPassword(), usuario.getNombre(), usuario.getTipo(), 
							usuario.getSituacion(), usuario.getEmail());
				
					UsuarioFacultadPK ufId = UsuarioFacultadPK.builder()
							.cveEntidad(usuario.getCveEntidad())
							.cveUsuario(usuario.getCveUsuario())
							.cveFacultad(usuario.getCveFacultad())
							.build();
					
					UsuarioFacultad entity = UsuarioFacultad.builder().id(ufId).build();
					
					usuarioFacultad.saveAndFlush(entity);
					
					 PropositoCorreoPK idPro = PropositoCorreoPK.builder()
							 .cveEntidad(usuario.getCveEntidad())
							 .cveProposito("NUEVO_USUARIO")
							 .build();
					 Optional<PropositoCorreo> proposito = propositoCorreoRepository.findById(idPro);
					 
					 if (proposito.isPresent()) {
						 PropositoCorreo info = proposito.get();
						 String cuerpoConfigurado = info.getCuerpoCorreo().replace("@USUARIO@", usuario.getCveUsuario());

						 Integer secuenciaCorreo = (notificacionRepository.obtieneNumeroCorreo() == null 
							        ? 1 
							        : notificacionRepository.obtieneNumeroCorreo() + 1);
						 
						 Notificacion datos = Notificacion.builder()
								 .secuenciaCorreo(secuenciaCorreo)
								 .para(usuario.getEmail())
								 .cuerpo(cuerpoConfigurado)
								 .asunto(info.getAsunto())
								 .build();
						 notificacionRepository.saveAndFlush(datos);
					 }
					 
					//generar consulta para obtener el cuerpo del correo
					/*String cuerpoConfigurado = notificacion.getCuerpo().replace("@FECHA_PROGRAMACION@", fechaProgramacion);
                	log.info("cuerpoConfigurado" + cuerpoConfigurado);
                	
                    boolean envio = bPMMailSender.sendHtmlMailMessage(destinatarios.getEmails(), destinatarios.getTipo(), notificacion.getAsunto(), cuerpoConfigurado);
*/
                    
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
	public RetMsg update(DatosAutenticacionTO session, UsuarioTO usuario) throws BrioBPMException {
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
	private RetMsg process(DatosAutenticacionTO session, UsuarioTO usuario, String codOk, String codError,
		String variablesValores) throws BrioBPMException {
		RetMsg response = RetMsg.builder().status("ERROR").build();
		try {
			
			UsuarioPK id = UsuarioPK.builder()
					.cveEntidad(usuario.getCveEntidad())
					.cveUsuario(usuario.getCveUsuario())
					.build();
			
			Optional<Usuario> datos = repository.findById(id);
			if (datos.isPresent()) {
				Usuario item = datos.get();
				item.setNombre(usuario.getNombre());
				item.setCorreoElectronico(usuario.getEmail());
				item.setTipo(usuario.getTipo());
				item.setSituacion(usuario.getSituacion());
				item.setFechaPassword(usuario.getFecPassword());
				repository.saveAndFlush(item);
				
				UsuarioFacultadPK ufId = UsuarioFacultadPK.builder()
						.cveEntidad(usuario.getCveEntidad())
						.cveUsuario(usuario.getCveUsuario())
						.cveFacultad(usuario.getCveFacultad())
						.build();
				
				UsuarioFacultad entity = UsuarioFacultad.builder().id(ufId).build();
				
				usuarioFacultad.saveAndFlush(entity);
				
				response.setStatus("OK");
				response.setMessage(messagesService.getMessage(session, USUARIO,
					codOk, VACIO));
			} else {
				response.setMessage(messagesService.getMessage(session, USUARIO,
						"MANTENIMIENTO_USUARIO_NO_EXISTE", VACIO));
			}
			
			
			
			//usuario.setFecPassword(new Date(System.currentTimeMillis()));
			log.debug("{}", usuario);
			/*usuario.setPassword(criptography.hashText(usuario.getPassword()));
			
			Usuario entity = IUsuariosConverter.converterDtoEntity.apply(usuario);
			entity.setCveEntidadUltimoCambio(session.getCveEntidad());
			entity.setCveUsuarioUltimoCambio(session.getCveUsuario());
			entity.setFechaUltimoCambio(new Date(System.currentTimeMillis()));
			log.debug("{}", entity);*/

		}
		catch (DataAccessException exData) {
			log.error(DATA_ERROR, exData.getMessage());
			throw new BrioBPMException(messagesService.getMessage(session, USUARIO,
				codError, VACIO), exData);
		}
		return response;
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.service.catalogs.core.IUsuarioService#actualizaPassword(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO, com.briomax.briobpm.transferobjects.catalogs.UsuarioTO)
	 */
	@Override
	public RetMsg actualizaPassword(DatosAutenticacionTO session, UsuarioTO usuario) throws BrioBPMException {
		RetMsg response = RetMsg.builder().status("ERROR").build();
		try {

			UsuarioPK id = UsuarioPK.builder()
					.cveEntidad(usuario.getCveEntidad())
					.cveUsuario(usuario.getCveUsuario())
					.build();
			String pass = criptography.hashText(usuario.getPassword());
			
			Optional<Usuario> datos = repository.findById(id);
			if (datos.isPresent()) {
				Usuario item = datos.get();

				if (pass.equals(item.getPassword()) ) {

					repository.actualizaPassword (usuario.getCveEntidad(), usuario.getCveLocalidad(), 
							usuario.getCveUsuario(), criptography.hashText(usuario.getActualPassword()));
					
					response.setStatus("OK");
					response.setMessage(messagesService.getMessage(session, USUARIO,
							"MANTENIMIENTO_USUARIO_MODIFICAR_OK", VACIO));
				} else {
					response.setMessage(messagesService.getMessage(session, USUARIO,
							"MANTENIMIENTO_USUARIO_PASSWORD_DIFERENTE", VACIO));					
				}
				
			} else {
				response.setMessage(messagesService.getMessage(session, USUARIO,
						"MANTENIMIENTO_USUARIO_NO_EXISTE", VACIO));
			}

		}
		catch (DataAccessException exData) {
			log.error(DATA_ERROR, exData.getMessage());
			throw new BrioBPMException(messagesService.getMessage(session, USUARIO,
					"MANTENIMIENTO_USUARIO_MODIFICAR_ERROR", VACIO), exData);
		}
		
		return response;
	}
	
	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.service.catalogs.core.IUsuarioService#actualizaSituacion(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO, com.briomax.briobpm.transferobjects.catalogs.UsuarioTO)
	 */
	@Override
	public RetMsg actualizaSituacion(DatosAutenticacionTO session, UsuarioTO usuario) throws BrioBPMException {
		RetMsg response = RetMsg.builder().status("ERROR").build();
		try {
			repository.actualizaSituacion(usuario.getCveEntidad(), usuario.getCveLocalidad(), 
					usuario.getCveUsuario(), usuario.getSituacion());
			
			response.setMessage(messagesService.getMessage(session, USUARIO,
					"MANTENIMIENTO_USUARIO_REGISTRAR_OK", VACIO));
		}
		catch (DataAccessException exData) {
			log.error(DATA_ERROR, exData.getMessage());
			throw new BrioBPMException(messagesService.getMessage(session, USUARIO,
					"MANTENIMIENTO_USUARIO_REGISTRAR_ERROR", VACIO), exData);
		}
		
		return response;
	}
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public RetMsg resetPassword(UsuarioResetTO usuario) throws BrioBPMException {
		RetMsg response = RetMsg.builder().status("ERROR").build();
		try {
			resetPasswordTokenRepository.depuraToken();
			String email = "%"+usuario.getEmail()+"%";
			List<Usuario> usuarios = repository.usuarioReset(usuario.getCveUsuario(), email);
			Usuario ite = null;
			ResetPasswordToken token = null;
			Date ahoraActual = new Date();
			String urlAplicacion = "";
			String cuerpoConfigurado = "";
			
			if (usuarios.size() > 0) { //Existe usuarios
				
				ite = usuarios.get(0);
				token = resetPasswordTokenRepository.validaUsuario (usuario.getCveUsuario());
				PropositoCorreo info = propositoCorreoRepository.correoReset("RESET_PASSWORD");
				urlAplicacion = url + "?token=" + usuario.getToken();
				cuerpoConfigurado = info.getCuerpoCorreo().replace("@url_reset@", urlAplicacion);
				Integer secuenciaCorreo =  0;
				
				if (token != null) {
					if (ahoraActual.before(token.getFechaExpiracion())) {
						// Token vigente, reenviar enlace
						secuenciaCorreo =  notificacionRepository.obtieneNumeroCorreo() + 1;
						 Notificacion datos = Notificacion.builder()
								 .secuenciaCorreo(secuenciaCorreo)
								 .para(usuario.getEmail())
								 .cuerpo(cuerpoConfigurado)
								 .asunto(info.getAsunto())
								 .build();
						 notificacionRepository.saveAndFlush(datos);	
					} else {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(ahoraActual);
						calendar.add(Calendar.HOUR_OF_DAY, 2);

						Date nuevaFecha = calendar.getTime();
						token.setFechaCreacion(ahoraActual);
						token.setFechaExpiracion(nuevaFecha);
						
						resetPasswordTokenRepository.saveAndFlush(token);
						Notificacion datos = Notificacion.builder()
								 .secuenciaCorreo(secuenciaCorreo)
								 .para(usuario.getEmail())
								 .ccp("")
								 .cuerpo(cuerpoConfigurado)
								 .asunto(info.getAsunto())
								 .build();
						notificacionRepository.saveAndFlush(datos);
					}
						
				} else {
					ite = usuarios.get(0);
				    byte[] tokenBytes = new byte[32];
				    SecureRandom secureRandom = new SecureRandom();
				    secureRandom.nextBytes(tokenBytes);

				    String cvetoken = Base64.getUrlEncoder()
				            .withoutPadding()
				            .encodeToString(tokenBytes);

				    String tokenHash = criptography.hashText(cvetoken);
				    urlAplicacion = url + "?token=" + tokenHash;
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(ahoraActual);
					calendar.add(Calendar.HOUR_OF_DAY, 2);

					Date nuevaFecha = calendar.getTime();
					
				    ResetPasswordTokenPK id = ResetPasswordTokenPK.builder()
				    		.cveEntidad(ite.getId().getCveEntidad())
				    		.cveUsuario(ite.getId().getCveUsuario())
				    		.build();
				    
					token = token.builder()
				    		.fechaCreacion(ahoraActual)
				    		.fechaExpiracion(nuevaFecha)
				    		.tipoAccion("RESER")
				    		.tokenHash(tokenHash)
				    		.id(id)
				    		.usaruiInactivo(1)
				    		.correoElectronico(usuario.getEmail())
				    		.build();
					resetPasswordTokenRepository.saveAndFlush(token);
					
					 cuerpoConfigurado = info.getCuerpoCorreo().replace("@url_reset@", urlAplicacion);
					 secuenciaCorreo =  notificacionRepository.obtieneNumeroCorreo() + 1;
					 Notificacion correo = Notificacion.builder()
							 .secuenciaCorreo(secuenciaCorreo)
							 .para(usuario.getEmail())
							 .ccp("")
							 .cuerpo(cuerpoConfigurado)
							 .asunto(info.getAsunto())
							 .build();
					 notificacionRepository.saveAndFlush(correo);
				}
									
				response.setStatus("OK");
				response.setMessage("Se le envio un mensaje al correo registrado, favor de validar.");				
			} else {
				response.setMessage("Se tiene un problema con el usuario, favor de validar con el administrador.");
			}
		
		} catch (DataAccessException exData) {
			log.error(DATA_ERROR, exData.getMessage());
			throw new BrioBPMException("Error al aplicar el reset al usuario. ", exData);
		}
		
		return response;
	}

	@Override
	public RetMsg restauraPassword(UsuarioResetTO usuario) throws BrioBPMException {
		RetMsg response = RetMsg.builder().status("ERROR").build();
		ResetPasswordToken token = resetPasswordTokenRepository.validaToken(usuario.getCveUsuario(), usuario.getToken());
		Date ahoraActual = new Date();
		try {
			
			if (token != null && token.getFechaExpiracion().after(ahoraActual)) {
				UsuarioPK id = UsuarioPK.builder()
						.cveEntidad(token.getId().getCveEntidad())
						.cveUsuario(token.getId().getCveUsuario())
						.build();
				
				Optional<Usuario> datos = repository.findById(id);
				if (datos.isPresent()) {
					String chPassword = criptography.hashText(usuario.getPassword());
					String email = "%"+token.getCorreoElectronico()+"%";
					repository.actualizaPasswordCorreo(email, token.getId().getCveUsuario(), chPassword);;
					
					token.setUsaruiInactivo(0);
					resetPasswordTokenRepository.saveAndFlush(token);
					
					response.setStatus("OK");
					response.setMessage("Contraseña restablecida con exito");
					
					
				} else {
					response.setMessage("No se encontro el usuario, valida con el administrador. ");
				}				
			} else {
				response.setMessage("Valida con el administrador tu usuario. ");
			}

		}
		catch (DataAccessException exData) {
			log.error(DATA_ERROR, exData.getMessage());
			throw new BrioBPMException("Error al actualizar el password, valida con el administrador. ", exData);
		}
		
		return response;
	}
}
