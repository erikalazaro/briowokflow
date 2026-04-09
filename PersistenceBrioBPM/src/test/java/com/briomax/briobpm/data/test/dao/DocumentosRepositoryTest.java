/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.data.test.dao;

import com.briomax.briobpm.data.test.base.AbstractDataTest;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class DocumentosRepositoryTest.java es ...
 * 
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Mar 9, 2020 11:15:16 AM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class DocumentosRepositoryTest extends AbstractDataTest {

	/** El atributo o variable documentos repository. */
	/*
	 * @Autowired private IDocumentosRepository documentosRepository;
	 * 
	 *//**
		 * Lee documentos nodo test.
		 * 
		 * @throws BrioBPMException la brio BPM exception.
		 */
	/*
	 * @Test public void leeDocumentosNodoTest() throws BrioBPMException { final
	 * DAORet<List<LeeDocumentosNodo>, RetMsg> response =
	 * documentosRepository.leeDocumentosNodo(getUser(), getEntidad(),
	 * getLocalidad(), LOCALE_SPANISH, CVE_PROCESO, VERSION, "201807-000001",
	 * "ACTIVIDAD-USUARIO", 1, 2, "EVIDENCIA_INCIDENCIA", "NO");
	 * log.info("SP_LEE_DOCUMENTOS_NODO:"); imprimir(response); }
	 * 
	 *//**
		 * Lee documento binario nodo test.
		 * 
		 * @throws BrioBPMException la brio BPM exception.
		 */
	/*
	 * @Test
	 * 
	 * @Ignore public void leeDocumentoBinarioNodoTest() throws BrioBPMException {
	 * for (int secDoc = 1; secDoc < 3; secDoc++) { Documento resp =
	 * documentosRepository.leeDocumentoBinarioNodo(getUser(), getEntidad(),
	 * getLocalidad(), LOCALE_SPANISH, CVE_PROCESO, VERSION, "201807-000001",
	 * "ACTIVIDAD-USUARIO", 1, 2, secDoc);
	 * log.info("SP_LEE_DOCUMENTO_BINARIO_NODO {}", resp); try {
	 * Files.write(Paths.get("logs/" + resp.getNombre()),
	 * resp.getContenido().getBytes(1, (int) resp.getContenido().length())); } catch
	 * (IOException | SQLException | NullPointerException exGral) {
	 * fail(exGral.getMessage()); } } }
	 * 
	 *//**
		 * Borra documento binario nodo test.
		 * 
		 * @throws BrioBPMException la brio BPM exception.
		 */
	/*
	 * @Test public void borraDocumentoBinarioNodoTest() throws BrioBPMException {
	 * RetMsg resp = documentosRepository.borraDocumentoBinarioNodo(getUser(),
	 * getEntidad(), getLocalidad(), LOCALE_SPANISH, CVE_PROCESO, VERSION,
	 * "201807-000001", "ACTIVIDAD-USUARIO", 1, 2, 1);
	 * log.info("SP_BORRA_DOCUMENTO_BINARIO_NODO {}", resp); }
	 * 
	 *//**
		 * Guarda documento binario nodo test.
		 * 
		 * @throws BrioBPMException   la brio BPM exception.
		 * @throws IOException        Indica que se ha producido una excepción de E/S.
		 * @throws URISyntaxException la URI syntax exception.
		 *//*
			 * @Test public void guardaDocumentoBinarioNodoTest() throws BrioBPMException,
			 * IOException, URISyntaxException { String nomArchivo = "ComDom.jpg"; String
			 * contentType = "image/jpg"; Path path =
			 * Paths.get(getClass().getClassLoader().getResource(".\\docs\\" +
			 * nomArchivo).toURI()); byte[] contenido = Files.readAllBytes(path); RetMsg
			 * respDB = documentosRepository.guardaDocumentoBinarioNodo(getUser(),
			 * getEntidad(), getLocalidad(), LOCALE_SPANISH, CVE_PROCESO, VERSION,
			 * "201807-000001", "ACTIVIDAD-USUARIO", 1, 2, 1, nomArchivo, contenido,
			 * contentType); log.info("SP_GUARDA_DOCUMENTO_BINARIO_NODO {}", respDB);
			 * Documento respLee = documentosRepository.leeDocumentoBinarioNodo(getUser(),
			 * getEntidad(), getLocalidad(), LOCALE_SPANISH, CVE_PROCESO, VERSION,
			 * "201807-000001", "ACTIVIDAD-USUARIO", 1, 2, 1);
			 * log.info("SP_LEE_DOCUMENTO_BINARIO_NODO {}", respLee); }
			 */

}
