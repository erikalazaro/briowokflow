/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.service;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.business.helpers.base.INodoHelper;
import com.briomax.briobpm.business.service.catalogs.core.IMessagesService;
import com.briomax.briobpm.business.service.core.IDocumentosService;
import com.briomax.briobpm.common.core.Constants;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.InDocumentoProcesoOc;
import com.briomax.briobpm.persistence.entity.InDocumentoProcesoOcPK;
import com.briomax.briobpm.persistence.jdto.Documento;
import com.briomax.briobpm.persistence.repository.IInDocumentoProcesoOcRepository;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.DocumentoTO;
import com.briomax.briobpm.transferobjects.in.NodoTO;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class DocumentosService.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Mar 9, 2020 11:40:41 AM Modificaciones:
 * @since JDK 1.8
 */
@Service
@Transactional
@Slf4j
public class DocumentosService implements IDocumentosService {

	/** El atributo o variable messages service. */
	@Autowired
	private IMessagesService messagesService;
	
	/** El atributo o variable nodo helper repository. */
	@Autowired
	private INodoHelper iNodoHelper;
	
	/** El atributo o variable documentos repository. */
	@Autowired
	private IInDocumentoProcesoOcRepository documentoProcesoOcRepository;
	

	/**
	 * Crear una nueva instancia del objeto documentos service.
	 */
	public DocumentosService() {
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.service.core.IDocumentosService#leeDocumentoBinarioNodo(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO, com.briomax.briobpm.transferobjects.in.DocumentoTO)
	 */
	private DAORet<List<DocumentoTO>, RetMsg> leeDocumentoBinarioNodoM(DatosAutenticacionTO session, DocumentoTO document)
		    throws BrioBPMException {
		    
		    // Log para depuración, muestra la información de la sesión y el documento que se va a leer.
		    log.debug("\t----- LEER DOCUMENTO {}\t{}", session, document);
		    
		    // Log informativo que indica que se está ejecutando el procedimiento para leer el documento binario.
		    log.info("DESACOPLE SP_LEE_DOCUMENTO_BINARIO_NODO");
		    
		    List<DocumentoTO> resultList = new ArrayList<>();
		    RetMsg meta = RetMsg.builder().status(Constants.OK).message("").build();
		    
		    NodoTO nodoLeeDocumento = NodoTO.builder()
		            .cveProceso(document.getCveProceso())
		            .version(new BigDecimal(document.getVersion()))
		            .cveInstancia(document.getCveInstancia())
		            .cveNodo(document.getCveNodo())
		            .idNodo(document.getIdNodo())
		            .secuenciaNodo(document.getSecNodo())
		            .build();

		    //List<Documento> responseList = iNodoHelper.leeDocumentoBinarioNodoMultiple(session, nodoLeeDocumento, document.getSecDocumento(), document.getNomArchivo());
		    List<Documento> responseList = new ArrayList<>();
		    log.info("responseList : " + responseList.size() + " " + responseList.toString());

		    // Verificar si la lista contiene un error
		    Documento response = responseList.get(0);
		    if (!response.getStatus().equalsIgnoreCase("OK")) {
		        meta = RetMsg.builder().status(response.getStatus()).message(response.getMessage()).build();
		    } else {
		        for (Documento doc : responseList) {
		            DocumentoTO newDocument = new DocumentoTO(); // Crear una nueva instancia para cada documento
		            newDocument.setNomArchivo(doc.getNombre());
		            newDocument.setContentType(doc.getContentType());

		            if (doc.getContenido() != null) {
		                try (InputStream inputStream = doc.getContenido().getBinaryStream()) {
		                    byte[] data = inputStream.readAllBytes(); // Lee todo el contenido del InputStream en un array de bytes
		                    newDocument.setData(data);
		                } catch (SQLException | IOException exGral) {
		                    log.error(exGral.getMessage());
		                    meta = RetMsg.builder().status(Constants.ERROR).message("Error al procesar documento.").build();
		                }
		            }

		            resultList.add(newDocument); // Agregar la nueva instancia al resultado
		        }
		    }

		    log.debug("\t----- {}", meta);
		    log.info("resultList : " + resultList.size() + " " + resultList.toString());
		    return new DAORet<>(resultList, meta);
		}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.service.core.IDocumentosService#borraDocumentoBinarioNodo(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO, com.briomax.briobpm.transferobjects.in.DocumentoTO)
	 */
	@Override
	public RetMsg borraDocumentoBinarioNodo(DatosAutenticacionTO session, DocumentoTO document)
		throws BrioBPMException {
		log.debug("\t----- BORRAR DOCUMENTO {}\t{}", session, document);
		
		log.info("DESACOPLE SP_BORRA_DOCUMENTO_BINARIO_NODO");
		
		/* depurar codigo NodoTO nodoBorraDocumento = NodoTO.builder()
				.cveProceso(document.getCveProceso())
				.version(new BigDecimal(document.getVersion()))
				.cveInstancia( document.getCveInstancia())
				.cveNodo(document.getCveNodo())
				.idNodo(document.getIdNodo())
				.secuenciaNodo(document.getSecNodo())
				.build();*/
		RetMsg response = new RetMsg().builder().status("OK").message("").build();
		
		
		documentoProcesoOcRepository.deleteMulDocumentoProceso(session.getCveEntidad(), 
				document.getCveProceso(), new BigDecimal(document.getVersion()), 
				document.getCveInstancia(), document.getSecDocumento(), document.getOcurrencia());
		
        // Crea una nueva clave primaria (PK) para el nuevo registro de documento.
        InDocumentoProcesoOcPK id = InDocumentoProcesoOcPK.builder()
                .cveEntidad(session.getCveEntidad())                
                .cveProceso(document.getCveProceso())
                .version(new BigDecimal(document.getVersion()))
                .cveInstancia(document.getCveInstancia())
                .ocurrencia(1)
                .secuenciaDocumento(document.getSecDocumento())
                .secuenciaArchivo(1)
                .build();

        // Crea un nuevo objeto documento con la información recopilada y el archivo binario.
        InDocumentoProcesoOc documentoProcesoOc = InDocumentoProcesoOc.builder()
                .id(id)
                .nombreArchivo(null)
                .contentType(null)
                .archivoBinario(null)
                .build();

        // Guarda y confirma el nuevo documento en la base de datos.
        documentoProcesoOcRepository.saveAndFlush(documentoProcesoOc);
		
		
//		if(document.getOcurrencia() == null || document.getSecuenciasArchivo() == null) {
//			response = iNodoHelper.borraDocumentoBinarioNodo(session, nodoBorraDocumento, document.getSecDocumento());
//		} else {
	
//			response = iNodoHelper.deleteMulDocumentoProceso(session, nodoBorraDocumento,
//				document.getSecDocumento());
	
//		}
		//Borrado anterior
		/*RetMsg response = documentosRepository.borraDocumentoBinarioNodo(session.getCveUsuario(),
			session.getCveEntidad(), session.getCveLocalidad(), session.getCveIdioma(), document.getCveProceso(),
			new BigDecimal(document.getVersion()), document.getCveInstancia(), document.getCveNodo(), document.getIdNodo(),
			document.getSecNodo(), document.getSecDocumento()); */
		log.debug("\t----- {}", response);
		return response;
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.service.core.IDocumentosService#guardaDocumentoBinarioNodo(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO, com.briomax.briobpm.transferobjects.in.DocumentoTO)
	 */
	@Override
	public RetMsg guardaDocumentoBinarioNodo(DatosAutenticacionTO session, List<DocumentoTO> documents)
	    throws BrioBPMException {
	    
	    // Log para depuración, muestra la información de la sesión y el documento que se va a guardar.
	    log.info("\t----- GUARDAR DOCUMENTO {}\t{}", session, documents);
	    
	    // Log informativo que indica que se está ejecutando el procedimiento para guardar el documento binario.
	    log.info("DESACOPLE SP_GUARDA_DOCUMENTO_BINARIO_NODO");
	    
	    // Inicializa un objeto RetMsg con estado "OK" y un mensaje vacío, que se usará para retornar el resultado de la operación.
	    RetMsg response = RetMsg.builder().status("OK").message("").build();
	    
        // Si alguno de estos valores es nulo, llama al método guardaDocumentoBinarioNodo del helper para guardar el documento.
        //response = iNodoHelper.guardaDocumentoBinarioNodo(session, document);
	 
        // Si ambos valores no son nulos, llama al método guardaDocumentoBinarioNodoMultiple del helper para guardar el documento.
        response = iNodoHelper.guardaDocumentoBinarioNodoMultiple(session, documents);
	    
	    /* 
	     * Código comentado que hace referencia a cómo originalmente se guardaba el documento binario en el nodo
	     * usando un repositorio. El código guarda el documento binario junto con otros detalles como
	     * el usuario, entidad, localidad, idioma, proceso, versión, instancia, nodo, idNodo, secNodo, secDocumento,
	     * nombre del archivo, datos y tipo de contenido.
	     *
	     * documentRepository.guardaDocumentoBinarioNodo(session.getCveUsuario(), session.getCveEntidad(),
	     * session.getCveLocalidad(), session.getCveIdioma(), document.getCveProceso(),
	     * new BigDecimal(document.getVersion()), document.getCveInstancia(), document.getCveNodo(),
	     * document.getIdNodo(), document.getSecNodo(), document.getSecDocumento(), document.getNomArchivo(),
	     * document.getData(), document.getContentType());
	     */
	    
	    // Log de depuración que muestra el estado final del proceso.
	    log.debug("\t----- {}", response);
	    
	    // Retorna el objeto RetMsg con el estado y mensaje de la operación.
	    return response;
	}

	

	
	// guarda documento multiple
	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.service.core.IDocumentosService#guardaDocumentoBinarioNodo(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO, com.briomax.briobpm.transferobjects.in.DocumentoTO)
	 */
	@Override
	public RetMsg guardaDocumentoBinarioNodoMultiple(DatosAutenticacionTO session, DocumentoTO document)
		throws BrioBPMException {
		log.debug("\t----- GUARDAR DOCUMENTO {}\t{}", session, document);
		
		log.info("DESAOPLE SP_GUARDA_DOCUMENTO_BINARIO_NODO");
		RetMsg response = RetMsg.builder().status("OK").message("").build();
		//for(DocumentoTO document : document) {
		//	response = iNodoHelper.guardaDocumentoBinarioNodoMultiple(session, document);
		//}
		
//				documentosRepository.guardaDocumentoBinarioNodo(session.getCveUsuario(), session.getCveEntidad(),
//				session.getCveLocalidad(), session.getCveIdioma(), document.getCveProceso(),
//				new BigDecimal(document.getVersion()), document.getCveInstancia(), document.getCveNodo(),
//				document.getIdNodo(), document.getSecNodo(), document.getSecDocumento(), document.getNomArchivo(),
//				document.getData(), document.getContentType());
		
		log.debug("\t----- {}", response);
		return response;
	}
	
	
	/**
	 * Este método se encarga de crear un nuevo renglón para un documento dentro de un proceso
	 * específico en la base de datos, asignando una nueva ocurrencia y estableciendo la secuencia
	 * del archivo correspondiente.
	 * 
	 * - Primero, se inicializa un mensaje de retorno con el estado "OK".
	 * - Luego, se extrae la información relevante del documento, como clave de instancia, clave de proceso,
	 *   secuencia del documento, y versión.
	 * - Se busca la máxima ocurrencia existente para el documento y se asigna una nueva ocurrencia 
	 *   incrementándola en uno, si no existe una ocurrencia previa, se asigna la ocurrencia 1.
	 * - Se crea una clave primaria (PK) única para el nuevo registro de documento.
	 * - Se construye un objeto `InDocumentoProcesoOc` con la información recopilada y se guarda en la base de datos.
	 * - Finalmente, el método retorna un mensaje de estado que indica el resultado de la operación.
	 *
	 * @param cveEntidad La clave de la entidad a la que pertenece el documento.
	 * @param documento Objeto `DocumentoTO` que contiene los datos del documento a ser guardado.
	 * @return RetMsg Mensaje de retorno con el estado de la operación.
	 */
	@Override
	public RetMsg creaNuevoRenglon(String cveEntidad, DocumentoTO documento) {
		
	   log.info("-----------------creaNuevoRenglon-------------------");
	   // Inicializa el mensaje de retorno con un estado "OK" por defecto.
	   RetMsg msg = RetMsg.builder().status("OK").message("").build();

	   // Recupera información del documento.
	   String cveInstancia = documento.getCveInstancia();
       String cveProceso = documento.getCveProceso();
       Integer secuenciaDocumento = documento.getSecDocumento();
       BigDecimal version = new BigDecimal(documento.getVersion());
       Integer ocurrencia;
	   
       // Busca la máxima ocurrencia existente para el documento y asigna una nueva ocurrencia.
       ocurrencia = documentoProcesoOcRepository.encuentraMaxOcurrenciaNueva(cveEntidad, cveProceso, version, cveInstancia, secuenciaDocumento);
       ocurrencia = ocurrencia != null ? ocurrencia + 1 : 1;
       
       // Crea una nueva clave primaria (PK) para el nuevo registro de documento, asignando valores específicos.
       InDocumentoProcesoOcPK id = InDocumentoProcesoOcPK.builder()
               .cveEntidad(cveEntidad)               
               .cveProceso(cveProceso)
               .version(version)
               .cveInstancia(cveInstancia)
               .ocurrencia(ocurrencia)
               .secuenciaDocumento(secuenciaDocumento)
               .secuenciaArchivo(1)
               .build();

       // Crea un nuevo objeto documento con la información recopilada y el archivo binario.
       InDocumentoProcesoOc documentoProcesoOc = InDocumentoProcesoOc.builder()
               .id(id)
               .build();

       // Guarda el nuevo documento en la base de datos y confirma los cambios.
       documentoProcesoOcRepository.saveAndFlush(documentoProcesoOc);
       
       // Retorna el mensaje con el estado y el mensaje especificados.
		return msg;
	}

	@Override
	public RetMsg eliminarRenglon(String cveEntidad, DocumentoTO documento) {
		  log.info("-----------------eliminarRenglon-------------------");
		   // Inicializa el mensaje de retorno con un estado "OK" por defecto.
		   RetMsg msg = RetMsg.builder().status("OK").message("").build();

		   // Recupera información del documento.
		   String cveInstancia = documento.getCveInstancia();
	       String cveProceso = documento.getCveProceso();
	       Integer secuenciaDocumento = documento.getSecDocumento();
	       BigDecimal version = new BigDecimal(documento.getVersion());
	       Integer ocurrencia = documento.getOcurrencia();
	       
	       if(1 == ocurrencia) {
	    	   msg.setStatus(Constants.ERROR);
	       } else {
	            // Borra los documentos con secuencias mayores.
             documentoProcesoOcRepository.deleteRenglonDocumentoProceso(
          		   cveEntidad, cveProceso, version, cveInstancia, secuenciaDocumento, ocurrencia);;
	       }
	    
	    // Retorna el mensaje con el estado y el mensaje especificados.
		return msg;
	}

	/*
	 * Obtiene la lista de los nombres que tiene un renglon del documento
	 * @see com.briomax.briobpm.business.service.core.IDocumentosService#leeNombreDocumentos(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO, com.briomax.briobpm.transferobjects.in.DocumentoTO)
	 */
	@Override
	public List<String> leeNombreDocumentos(DatosAutenticacionTO session, DocumentoTO document) {

		List<String> list = documentoProcesoOcRepository.encuentraNombreDocumentos (session.getCveEntidad(), 
				document.getCveProceso(), new BigDecimal(document.getVersion()),
				document.getCveInstancia(), document.getSecDocumento(), document.getOcurrencia());
		
		return list;
	}
	
	@Override
	public DAORet<DocumentoTO, RetMsg> leeDocumentoBinarioNodo(DatosAutenticacionTO session, DocumentoTO document)
	    throws BrioBPMException {
	    
	    // Log para depuración, muestra la información de la sesión y el documento que se va a leer.
	    log.debug("\t----- LEER DOCUMENTO {}\t{}", session, document);
	    
	    // Log informativo que indica que se está ejecutando el procedimiento para leer el documento binario.
	    log.info("DESACOPLE SP_LEE_DOCUMENTO_BINARIO_NODO");
	    
	    // Crea un objeto NodoTO con la información relevante del documento.
	    NodoTO nodoLeeDocumento = NodoTO.builder()
	        .cveProceso(document.getCveProceso())
	        .version(new BigDecimal(document.getVersion()))
	        .cveInstancia(document.getCveInstancia())
	        .cveNodo(document.getCveNodo())
	        .idNodo(document.getIdNodo())
	        .secuenciaNodo(document.getSecNodo())
	        .build();
	    
	    // Inicializa un objeto Documento para almacenar la respuesta.
	    Documento response = new Documento();	    
	    response = leeDocumentoBinarioNodo (session, nodoLeeDocumento, document.getSecDocumento(), 
	    		document.getNomArchivo(), document.getOcurrencia());
	    
	    // Construye un objeto RetMsg con el estado y mensaje de la respuesta.
	    RetMsg meta = RetMsg.builder().status(response.getStatus()).message(response.getMessage()).build();
	    
	    // Si el estado es "OK", se procede a actualizar el objeto DocumentoTO con los datos del documento leído.
	    if (meta.getStatus().equalsIgnoreCase("OK")) {
	        document.setNomArchivo(response.getNombre());
	        document.setContentType(response.getContentType());
	        
	        // Si el contenido del documento no es nulo, intenta extraer los bytes del contenido y los asigna al documento.
	        if (response.getContenido() != null) {
	            try {
	            	
	            	log.debug("\t-----ssssssssssssssssssss {}", (int) response.getContenido().length());
	                document.setData(response.getContenido().getBytes(1, (int) response.getContenido().length()));
	            }
	            catch (SQLException | ArrayIndexOutOfBoundsException exGral) {
	                log.error(exGral.getMessage());
	            }
	        }
	    } else {
	        // Si el estado no es "OK", se limpia la información del documento.
	        document.setNomArchivo(null);
	        document.setContentType(null);
	        document.setData(null);
	    }
	    
	    // Log de depuración que muestra el estado final del proceso.
	    log.debug("\t----- {}", meta);
	    
	    // Retorna un objeto DAORet con el documento actualizado y la meta información.
	    return new DAORet<DocumentoTO, RetMsg>(document, meta);
	}

	
	private Documento leeDocumentoBinarioNodo(DatosAutenticacionTO session, NodoTO nodo,
	        Integer secuenciaDocumento, String nomArchivo, Integer ocurrencia) throws BrioBPMException {

	    // Variables locales que almacenan información clave para el procesamiento del documento
	    String cveEntidad = session.getCveEntidad();
	    String cveInstancia = nodo.getCveInstancia();
	    String cveProceso = nodo.getCveProceso();
	    BigDecimal version = nodo.getVersion();

	    // Variables para almacenar el contenido del archivo y sus metadatos
	    byte[] archivoBinario;
	    Blob archivoBinarioBlob = null;
	    String contentType;
	    String idProceso;
	    boolean existeDocumento;
	    String mensaje;
	    String nombreArchivo;
	    String variableValor;

	    // INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
	    // Se crea un objeto Documento para devolver el resultado del proceso
	    Documento result = Documento.builder()
	            .status(Constants.OK) // Inicializa el estado como OK
	            .message("") // Inicializa el mensaje como vacío
	            .build();
	    idProceso = Constants.LEE_DOCUMENTO_BINARIO_NODO;
	    existeDocumento = false; // Inicializa el indicador de existencia del documento como falso

	    // CONCATENA LA LISTA DE LOS PARÁMETROS PARA DESPLEGARLOS EN CASO DE ERROR
	    // Se construye una cadena que representa la combinación de parámetros clave
	    variableValor = Constants.CVE_PROCESO + nodo.getCveProceso() + "|" +
	            Constants.VERSION + nodo.getVersion() + "|" +
	            Constants.CVE_INSTANCIA + nodo.getCveInstancia() + "|" +
	            Constants.CVE_NODO + nodo.getCveNodo() + "|" +
	            Constants.ID_NODO + nodo.getIdNodo() + "|" +
	            Constants.SECUENCIA_NODO + nodo.getSecuenciaNodo() + "|" +
	            Constants.SECUENCIA_DOCUMENTO + secuenciaDocumento;

	    // Inicializa las variables de contenido del documento
	    archivoBinario = null;
	    nombreArchivo = null;
	    contentType = null;


	    // Busca todos los documentos que coincidan con los criterios dados
	    List<InDocumentoProcesoOc> documentosProcesoOc = documentoProcesoOcRepository.getDocumento(
	    		cveEntidad, cveProceso, version, cveInstancia, secuenciaDocumento, nomArchivo, ocurrencia);	    

	    if (documentosProcesoOc.size() == 0) {
	        // Maneja el caso en que el documento no existe
	        // Si el documento no está presente, se devuelve un mensaje de error
	        mensaje = messagesService.getMessage(session, idProceso, Constants.NO_EXISTE_DOCUMENTO_PARA_NODO, variableValor);
	        result.setStatus(Constants.ERROR);
	        result.setMessage(mensaje);
	        return result;
	    }

	    // Obtiene el documento si está presente
	    InDocumentoProcesoOc documentoProceso = documentosProcesoOc.get(0);
	    if (documentoProceso == null || documentoProceso.getArchivoBinario() == null) {
	        // Maneja el caso en que el documento es nulo o no tiene contenido
	        // Si el documento es nulo o no tiene contenido, se devuelve un mensaje de error
	        mensaje = messagesService.getMessage(session, idProceso, Constants.NO_EXISTE_DOCUMENTO_PARA_NODO, variableValor);
	        result.setStatus(Constants.ERROR);
	        result.setMessage(mensaje);
	        return result;
	    }

	    // Si existe, obtiene sus datos
	    // Asigna los datos del documento a las variables correspondientes
	    archivoBinario = documentoProceso.getArchivoBinario();
	    nombreArchivo = documentoProceso.getNombreArchivo();
	    contentType = documentoProceso.getContentType();

	    try {
	        // Convierte el byte array a Blob para facilitar el manejo del contenido binario
	        archivoBinarioBlob = new SerialBlob(documentoProceso.getArchivoBinario());
	    } catch (SQLException e) {
	        // Manejo de la excepción en caso de error al convertir
	        throw new BrioBPMException("Error al convertir byte[] a Blob", e);
	    }

	    // Asigna los valores al resultado
	    // Establece el contenido y metadatos en el objeto result
	    result.setContenido(archivoBinarioBlob);
	    result.setContentType(contentType);
	    result.setNombre(nombreArchivo);
	    result.setData(documentoProceso.getArchivoBinario());


	    // Devuelve el resultado final con los datos del documento
	    return result;
	}


	
}
