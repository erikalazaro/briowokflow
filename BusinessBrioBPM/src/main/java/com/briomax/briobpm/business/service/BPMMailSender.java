/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.business.service;
import java.io.File;
import java.util.Arrays;
import java.util.Base64;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;

import com.briomax.briobpm.business.service.core.IBPMMailSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class BPMMailSender.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Apr 13, 2020 10:13:40 AM Modificaciones:
 * @since JDK 1.8
 */
@Component
@Slf4j
public class BPMMailSender implements IBPMMailSender {

	/** El atributo o variable java mail sender. */
	@Autowired
    private JavaMailSender javaMailSender;
	
	/** El atributo o variable from. */
	@Value("${brio.bpm.mail.from}")
	private String from;

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.service.core.IBPMMailSender#sendSimpleMailMessage(java.lang.String[], java.lang.String[], java.lang.String, java.lang.String)
	 */
	@Override
	public boolean sendSimpleMailMessage(String[] to, String[] cc, String subject, String text) {
		boolean successfully = false;		
		try {
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setFrom(from);
			msg.setTo(to);
			if (cc != null && cc.length > 0) {
				msg.setCc(cc);
			}
			msg.setSubject(subject);
			msg.setText(text);
			javaMailSender.send(msg);
			successfully = true;
		}
		catch (MailException exMail) {
			log.error(exMail.getMessage(), exMail);
		}
		log.info("############### RETURN sendSimpleMailMessage: " + successfully);
        return successfully;
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.service.core.IBPMMailSender#sendHtmlMailMessage(java.lang.String[], java.lang.String[], java.lang.String, java.lang.String)
	 */
	@Override
	public boolean sendHtmlMailMessage(String[] to, String[] cc, String subject, String body) {
	    // Inicializa una variable para indicar el éxito del envío del correo
	    boolean successfully = false;

	    // Registra en el log la entrada al método
	    log.info("---------> ENTRA A sendHtmlMailMessage");
	    log.info("Destinatarios (TO): {}", Arrays.toString(to));
	    log.info("Con Copia (CC): {}", Arrays.toString(cc));
	    log.info("Asunto: {}", subject);
	    log.info("Cuerpo del mensaje: {}", body);
	    try {
	        // Crea un objeto MimeMessage utilizando el JavaMailSender
	        MimeMessage message = javaMailSender.createMimeMessage();

	        // Usa MimeMessageHelper para configurar los detalles del mensaje
	        // El segundo parámetro 'true' indica que el mensaje será multipart para manejar HTML
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);

	        // Establece el remitente del correo electrónico
	        log.info("FROM : " + from);
	        helper.setFrom(from);

	        // Establece los destinatarios principales (a quién se envía el correo)
	        log.info("TO : " + Arrays.toString(to));
	        helper.setTo(to);

	        // Si hay destinatarios en copia (CC), los añade al mensaje
	        if (cc != null && cc.length > 0) {
	            log.info("Direcciones de CC: " + Arrays.toString(cc));
	            helper.setCc(cc);
	        }

	        // Verifica si el asunto del correo es nulo o está en blanco
	        if (StringUtils.isBlank(subject)) {
	            // Si el asunto está vacío, establece un asunto predeterminado
	            helper.setSubject("Workflow");
	        } else {
	            // Si hay un asunto proporcionado, lo utiliza
	            helper.setSubject(subject);
	        }

	        log.info("BODY : " + body);
	        
	        // Establece el cuerpo del mensaje, indicando que es HTML con el segundo parámetro 'true'
	        helper.setText(body, true);

	        // Registra en el log que el texto del mensaje ha sido configurado
	        log.info("---------> SET TEXT ");

	        // Envía el mensaje a través de JavaMailSender
	        log.info("MENSAJE : " + message.toString());
	        javaMailSender.send(message); //se comenta por error 12/09/2024 Sara Ventura

	        // Registra en el log que el mensaje ha sido enviado
	        log.info("---------> SEND ");

	        // Indica que el envío fue exitoso
	        successfully = true;
	    }
	    // Captura las excepciones que pueden ocurrir durante el envío del correo
	    catch (IllegalArgumentException | MessagingException | MailException exMail) {
	        // Registra el error en el log
	        log.error(exMail.getMessage(), exMail);
	    }

	    // Registra en el log el resultado del envío del mensaje (éxito o fracaso)
	    log.info("############### RETURN sendHtmlMailMessage: " + successfully);

	    // Devuelve el estado del envío del correo
	    return successfully;
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.service.core.IBPMMailSender#sendHtmlMailMessage(java.lang.String[], java.lang.String[], java.lang.String, java.lang.String)
	 */
	@Override
	public boolean sendHtmlMailMessage(String[] to, String[] cc, String subject, String body, byte[] imagenBytes) {
	    // Inicializa una variable para indicar el éxito del envío del correo
	    boolean successfully = false;

	    // Registra en el log la entrada al método
	    log.info("---------> ENTRA A sendHtmlMailMessage");
	    log.info("Destinatarios (TO): {}", Arrays.toString(to));
	    log.info("Con Copia (CC): {}", Arrays.toString(cc));
	    log.info("Asunto: {}", subject);
	    log.info("Cuerpo del mensaje: {}", body);
	    try {
	        // Crea un objeto MimeMessage utilizando el JavaMailSender
	        MimeMessage message = javaMailSender.createMimeMessage();

	        // Usa MimeMessageHelper para configurar los detalles del mensaje
	        // El segundo parámetro 'true' indica que el mensaje será multipart para manejar HTML
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);

	        // Establece el remitente del correo electrónico
	        log.info("FROM : " + from);
	        helper.setFrom(from);

	        // Establece los destinatarios principales (a quién se envía el correo)
	        log.info("TO : " + Arrays.toString(to));
	        helper.setTo(to);

	        // Si hay destinatarios en copia (CC), los añade al mensaje
	        if (cc != null && cc.length > 0) {
	            log.info("Direcciones de CC: " + Arrays.toString(cc));
	            helper.setCc(cc);
	        }

	        // Verifica si el asunto del correo es nulo o está en blanco
	        if (StringUtils.isBlank(subject)) {
	            // Si el asunto está vacío, establece un asunto predeterminado
	            helper.setSubject("Workflow");
	        } else {
	            // Si hay un asunto proporcionado, lo utiliza
	            helper.setSubject(subject);
	        }

	        //cuero del mensaje
	        String imagenBase64 = Base64.getEncoder().encodeToString(imagenBytes);
	        // Agregar la imagen como recurso en línea
	        //ByteArrayResource imagenResource = new ByteArrayResource(imagenBytes);
	        //helper.addInline("imagen", imagenResource, "image/png");
	        
       
	        log.info("BODY : " + body);
	        
	        body = body.replace("@imagen@", imagenBase64);
	        log.info("BODY final : " + body);
	        
	        // Establece el cuerpo del mensaje, indicando que es HTML con el segundo parámetro 'true'
	        helper.setText(body, true);

	        // Registra en el log que el texto del mensaje ha sido configurado
	        log.info("---------> SET TEXT ");

	        // Envía el mensaje a través de JavaMailSender
	        log.info("MENSAJE : " + message.toString());
	        javaMailSender.send(message); //se comenta por error 12/09/2024 Sara Ventura

	        // Registra en el log que el mensaje ha sido enviado
	        log.info("---------> SEND ");

	        // Indica que el envío fue exitoso
	        successfully = true;
	    }
	    // Captura las excepciones que pueden ocurrir durante el envío del correo
	    catch (IllegalArgumentException | MessagingException | MailException exMail) {
	        // Registra el error en el log
	        log.error(exMail.getMessage(), exMail);
	    }

	    // Registra en el log el resultado del envío del mensaje (éxito o fracaso)
	    log.info("############### RETURN sendHtmlMailMessage: " + successfully);

	    // Devuelve el estado del envío del correo
	    return successfully;
	}

	@Override
	public boolean enviarCorreoConImagen(String to, String subject, String bodyHtml, 
			String pathRepse) throws MessagingException {
	    boolean successfully = false;

	    // Registra en el log la entrada al método
	    log.info("---------> ENTRA A sendHtmlMailMessage");
	    log.info("Destinatarios (TO): {}", to);
	   
	    log.info("Asunto: {}", subject);
	    log.info("Cuerpo del mensaje: {}", bodyHtml);
	    try {
	        // Crea un objeto MimeMessage utilizando el JavaMailSender
	        MimeMessage message = javaMailSender.createMimeMessage();

	        // Usa MimeMessageHelper para configurar los detalles del mensaje
	        // El segundo parámetro 'true' indica que el mensaje será multipart para manejar HTML
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);

	        // Establece el remitente del correo electrónico
	        log.info("FROM : " + from);
	        helper.setFrom(from);

	        // Establece los destinatarios principales (a quién se envía el correo)
	        
	        helper.setTo(to);


	        // Verifica si el asunto del correo es nulo o está en blanco
	        if (StringUtils.isBlank(subject)) {
	            // Si el asunto está vacío, establece un asunto predeterminado
	            helper.setSubject("Workflow");
	        } else {
	            // Si hay un asunto proporcionado, lo utiliza
	            helper.setSubject(subject);
	        }
	     // Reemplaza el marcador con el cid
		    String htmlContent = bodyHtml.replace("@RUTA_IMAGEN_REPSE@", "cid:imagenRepse");
		    
	        
	        // Establece el cuerpo del mensaje, indicando que es HTML con el segundo parámetro 'true'
		    helper.setText(htmlContent, true);

	        // Registra en el log que el texto del mensaje ha sido configurado
	        log.info("---------> SET TEXT ");
	        
	     // Agrega la imagen como recurso inline
		    FileSystemResource res = new FileSystemResource(new File(pathRepse));
		    helper.addInline("imagenRepse", res);

	        // Envía el mensaje a través de JavaMailSender
	        log.info("MENSAJE : " + message.toString());
	        javaMailSender.send(message); //se comenta por error 12/09/2024 Sara Ventura

	        // Registra en el log que el mensaje ha sido enviado
	        log.info("---------> SEND ");

	        // Indica que el envío fue exitoso
	        successfully = true;
	    }
	    // Captura las excepciones que pueden ocurrir durante el envío del correo
	    catch (IllegalArgumentException | MessagingException | MailException exMail) {
	        // Registra el error en el log
	        log.error(exMail.getMessage(), exMail);
	    }

	    // Registra en el log el resultado del envío del mensaje (éxito o fracaso)
	    log.info("############### RETURN sendHtmlMailMessage: " + successfully);

	    // Devuelve el estado del envío del correo
	    return successfully;
	}


}
