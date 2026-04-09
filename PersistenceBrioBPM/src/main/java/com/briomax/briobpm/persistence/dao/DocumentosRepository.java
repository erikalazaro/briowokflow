/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados. Este software contiene informacion
 * confidencial propiedad de Briomax Consulting por lo cual no puede ser reproducido, distribuido o alterado sin el
 * consentimiento previo de Briomax Consulting.
 */

package com.briomax.briobpm.persistence.dao;

import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.dao.base.AbstractBaseDAO;
import com.briomax.briobpm.persistence.dao.base.IDocumentosRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class DocumentosRepository.java es ...
 * 
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Mar 9, 2020 9:42:24 AM Modificaciones:
 * @since JDK 1.8
 */
@Repository("documentosRepository")
@Slf4j
public class DocumentosRepository extends AbstractBaseDAO implements IDocumentosRepository {

	/*	*//**
			 * Crear una nueva instancia del objeto documentos repository.
			 */
	/*
	 * public DocumentosRepository() { }
	 * 
	 *//**
		 * {@inheritDoc}
		 * 
		 * @see com.briomax.briobpm.persistence.dao.base.IDocumentosRepository#leeDocumentosNodo(java.lang.String,
		 *      java.lang.String, java.lang.String, java.lang.String, java.lang.String,
		 *      java.math.BigDecimal, java.lang.String, java.lang.String,
		 *      java.lang.Integer, java.lang.Integer, java.lang.String,
		 *      java.lang.String)
		 */
	/*
	 * @Override public DAORet<List<LeeDocumentosNodo>, RetMsg>
	 * leeDocumentosNodo(String cveUsuario, String cveEntidad, String cveLocalidad,
	 * String cveIdioma, String cveProceso, BigDecimal version, String cveInstancia,
	 * String cveNodo, Integer idNodo, Integer secuenciaNodo, String cveSeccion,
	 * String banGenTabTmp) throws BrioBPMException { log.
	 * debug("\t\t <DATABASE> EXEC SP_LEE_DOCUMENTOS_NODO '{}', '{}', '{}', '{}', '{}', {}, '{}', '{}', {}, {},"
	 * + " '{}', @CH_TIPO_EXCEPCION, @CH_MENSAJE", cveUsuario, cveEntidad,
	 * cveLocalidad, cveIdioma, cveProceso, version, cveInstancia, cveNodo, idNodo,
	 * secuenciaNodo, cveSeccion, banGenTabTmp); return
	 * executeNamedStored("obtenerDocumentosNodo", new String[] {"CH_CVE_USUARIO",
	 * "CH_CVE_ENTIDAD", "CH_CVE_LOCALIDAD", "CH_CVE_IDIOMA", "CH_CVE_PROCESO",
	 * "C_VERSION", "CH_CVE_INSTANCIA", "CH_CVE_NODO", "I_ID_NODO",
	 * "I_SECUENCIA_NODO", "CH_CVE_SECCION", "CH_GENERA_TABLA_TEMPORAL"}, new
	 * Object[] {cveUsuario, cveEntidad, cveLocalidad, cveIdioma, cveProceso,
	 * version, cveInstancia, cveNodo, idNodo, secuenciaNodo, cveSeccion,
	 * banGenTabTmp}, RetMsg.class); }
	 * 
	 *//**
		 * {@inheritDoc}
		 * 
		 * @see com.briomax.briobpm.persistence.dao.base.IDocumentosRepository#leeDocumentoBinarioNodo(java.lang.String,
		 *      java.lang.String, java.lang.String, java.lang.String, java.lang.String,
		 *      java.math.BigDecimal, java.lang.String, java.lang.String,
		 *      java.lang.Integer, java.lang.Integer, java.lang.Integer)
		 */
	/*
	 * @Override public Documento leeDocumentoBinarioNodo(String cveUsuario, String
	 * cveEntidad, String cveLocalidad, String cveIdioma, String cveProceso,
	 * BigDecimal version, String cveInstancia, String cveNodo, Integer idNodo,
	 * Integer secNodo, Integer secDoc) throws BrioBPMException { log.
	 * debug("\t\t <DATABASE> EXEC SP_LEE_DOCUMENTO_BINARIO_NODO '{}', '{}', '{}', '{}', '{}', {}, '{}', '{}',"
	 * + " {}, {}, '{}', @CH_TIPO_EXCEPCION, @CH_MENSAJE", cveUsuario, cveEntidad,
	 * cveLocalidad, cveIdioma, cveProceso, version, cveInstancia, cveNodo, idNodo,
	 * secNodo, secDoc); return
	 * executeNoResulsetStored("SP_LEE_DOCUMENTO_BINARIO_NODO", new String[]
	 * {"CH_CVE_USUARIO", "CH_CVE_ENTIDAD", "CH_CVE_LOCALIDAD", "CH_CVE_IDIOMA",
	 * "CH_CVE_PROCESO", "C_VERSION", "CH_CVE_INSTANCIA", "CH_CVE_NODO",
	 * "I_ID_NODO", "I_SECUENCIA_NODO", "I_SECUENCIA_DOCUMENTO",
	 * "VB_ARCHIVO_BINARIO", "CH_NOMBRE_ARCHIVO", "CH_CONTENT_TYPE",
	 * "CH_TIPO_EXCEPCION", "CH_MENSAJE"}, new Class[] {String.class, String.class,
	 * String.class, String.class, String.class, BigDecimal.class, String.class,
	 * String.class, Integer.class, Integer.class, Integer.class, Blob.class,
	 * String.class, String.class, String.class, String.class}, new ParameterMode[]
	 * {ParameterMode.IN, ParameterMode.IN, ParameterMode.IN, ParameterMode.IN,
	 * ParameterMode.IN, ParameterMode.IN, ParameterMode.IN, ParameterMode.IN,
	 * ParameterMode.IN, ParameterMode.IN, ParameterMode.IN, ParameterMode.OUT,
	 * ParameterMode.OUT, ParameterMode.OUT, ParameterMode.OUT, ParameterMode.OUT},
	 * new Object[] {cveUsuario, cveEntidad, cveLocalidad, cveIdioma, cveProceso,
	 * version, cveInstancia, cveNodo, idNodo, secNodo, secDoc}, Documento.class); }
	 * 
	 *//**
		 * {@inheritDoc}
		 * 
		 * @see com.briomax.briobpm.persistence.dao.base.IDocumentosRepository#borraDocumentoBinarioNodo(java.lang.String,
		 *      java.lang.String, java.lang.String, java.lang.String, java.lang.String,
		 *      java.math.BigDecimal, java.lang.String, java.lang.String,
		 *      java.lang.Integer, java.lang.Integer, java.lang.Integer)
		 */
	/*
	 * @Override public RetMsg borraDocumentoBinarioNodo(String cveUsuario, String
	 * cveEntidad, String cveLocalidad, String cveIdioma, String cveProceso,
	 * BigDecimal version, String cveInstancia, String cveNodo, Integer idNodo,
	 * Integer secNodo, Integer secDoc) throws BrioBPMException { log.
	 * debug("\t\t <DATABASE> EXEC SP_BORRA_DOCUMENTO_BINARIO_NODO '{}', '{}', '{}', '{}', '{}', {}, '{}', '{}',"
	 * + " {}, {}, {}, @CH_TIPO_EXCEPCION, @CH_MENSAJE", cveUsuario, cveEntidad,
	 * cveLocalidad, cveIdioma, cveProceso, version, cveInstancia, cveNodo, idNodo,
	 * secNodo, secDoc); return
	 * executeNoResulsetStored("SP_BORRA_DOCUMENTO_BINARIO_NODO", new String[]
	 * {"CH_CVE_USUARIO", "CH_CVE_ENTIDAD", "CH_CVE_LOCALIDAD", "CH_CVE_IDIOMA",
	 * "CH_CVE_PROCESO", "C_VERSION", "CH_CVE_INSTANCIA", "CH_CVE_NODO",
	 * "I_ID_NODO", "I_SECUENCIA_NODO", "I_SECUENCIA_DOCUMENTO",
	 * "CH_TIPO_EXCEPCION", "CH_MENSAJE"}, new Class[] {String.class, String.class,
	 * String.class, String.class, String.class, BigDecimal.class, String.class,
	 * String.class, Integer.class, Integer.class, Integer.class, String.class,
	 * String.class}, new ParameterMode[] {ParameterMode.IN, ParameterMode.IN,
	 * ParameterMode.IN, ParameterMode.IN, ParameterMode.IN, ParameterMode.IN,
	 * ParameterMode.IN, ParameterMode.IN, ParameterMode.IN, ParameterMode.IN,
	 * ParameterMode.IN, ParameterMode.OUT, ParameterMode.OUT}, new Object[]
	 * {cveUsuario, cveEntidad, cveLocalidad, cveIdioma, cveProceso, version,
	 * cveInstancia, cveNodo, idNodo, secNodo, secDoc}, RetMsg.class); }
	 * 
	 *//**
		 * {@inheritDoc}
		 * 
		 * @see com.briomax.briobpm.persistence.dao.base.IDocumentosRepository#guardaDocumentoBinarioNodo(java.lang.String,
		 *      java.lang.String, java.lang.String, java.lang.String, java.lang.String,
		 *      java.math.BigDecimal, java.lang.String, java.lang.String,
		 *      java.lang.Integer, java.lang.Integer, java.lang.Integer,
		 *      java.lang.String, byte[], java.lang.String)
		 *//*
			 * @Override public RetMsg guardaDocumentoBinarioNodo(String cveUsuario, String
			 * cveEntidad, String cveLocalidad, String cveIdioma, String cveProceso,
			 * BigDecimal version, String cveInstancia, String cveNodo, Integer idNodo,
			 * Integer secNodo, Integer secDoc, String nomArchivo, byte[] arcBinario, String
			 * contenType) throws BrioBPMException { log.
			 * debug("\t\t <DATABASE> EXEC SP_GUARDA_DOCUMENTO_BINARIO_NODO '{}', '{}', '{}', '{}', '{}', {}, '{}', '{}',"
			 * + " {}, {}, {}, '{}', '{}', '{}', @CH_TIPO_EXCEPCION, @CH_MENSAJE",
			 * cveUsuario, cveEntidad, cveLocalidad, cveIdioma, cveProceso, version,
			 * cveInstancia, cveNodo, idNodo, secNodo, secDoc, nomArchivo,
			 * "ARCHIVO_BINARIO", contenType); return
			 * executeNoResulsetStored("SP_GUARDA_DOCUMENTO_BINARIO_NODO", new String[]
			 * {"CH_CVE_USUARIO", "CH_CVE_ENTIDAD", "CH_CVE_LOCALIDAD", "CH_CVE_IDIOMA",
			 * "CH_CVE_PROCESO", "C_VERSION", "CH_CVE_INSTANCIA", "CH_CVE_NODO",
			 * "I_ID_NODO", "I_SECUENCIA_NODO", "I_SECUENCIA_DOCUMENTO",
			 * "CH_NOMBRE_ARCHIVO", "VB_ARCHIVO_BINARIO", "CH_CONTENT_TYPE",
			 * "CH_TIPO_EXCEPCION", "CH_MENSAJE"}, new Class[] {String.class, String.class,
			 * String.class, String.class, String.class, BigDecimal.class, String.class,
			 * String.class, Integer.class, Integer.class, Integer.class, String.class,
			 * byte[].class, String.class, String.class, String.class}, new ParameterMode[]
			 * {ParameterMode.IN, ParameterMode.IN, ParameterMode.IN, ParameterMode.IN,
			 * ParameterMode.IN, ParameterMode.IN, ParameterMode.IN, ParameterMode.IN,
			 * ParameterMode.IN, ParameterMode.IN, ParameterMode.IN, ParameterMode.IN,
			 * ParameterMode.IN, ParameterMode.IN, ParameterMode.OUT, ParameterMode.OUT},
			 * new Object[] {cveUsuario, cveEntidad, cveLocalidad, cveIdioma, cveProceso,
			 * version, cveInstancia, cveNodo, idNodo, secNodo, secDoc, nomArchivo,
			 * arcBinario, contenType}, RetMsg.class); }
			 */

}
