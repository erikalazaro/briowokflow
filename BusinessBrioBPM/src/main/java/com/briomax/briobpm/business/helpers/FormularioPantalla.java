package com.briomax.briobpm.business.helpers;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.business.helpers.base.IAreaTrabajoHelper;
import com.briomax.briobpm.business.helpers.base.IFormularioPantalla;
import com.briomax.briobpm.business.helpers.base.INodoHelper;
import com.briomax.briobpm.business.service.catalogs.core.IMessagesService;
import com.briomax.briobpm.common.core.Constants;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.dao.base.IAreaTrabajoDAO;
import com.briomax.briobpm.persistence.entity.VariableEntidad;
import com.briomax.briobpm.persistence.entity.VariableEntidadPK;
import com.briomax.briobpm.persistence.entity.VariableLocalidad;
import com.briomax.briobpm.persistence.entity.VariableLocalidadPK;
import com.briomax.briobpm.persistence.entity.VariableSistema;
import com.briomax.briobpm.persistence.entity.namedquery.LeeEtiquetasTabla;
import com.briomax.briobpm.persistence.repository.ICatalogoEtiquetaRepository;
import com.briomax.briobpm.persistence.repository.IInDocumentoProcesoOcRepository;
import com.briomax.briobpm.persistence.repository.IStDocumentoSeccionRepository;
import com.briomax.briobpm.persistence.repository.IStProcesoRepository;
import com.briomax.briobpm.persistence.repository.IStSeccionNodoRepository;
import com.briomax.briobpm.persistence.repository.IStValorVariableRepository;
import com.briomax.briobpm.persistence.repository.IStVariableProcesoRepository;
import com.briomax.briobpm.persistence.repository.IStVariableSeccionRepository;
import com.briomax.briobpm.persistence.repository.IVariableEntidadRepository;
import com.briomax.briobpm.persistence.repository.IVariableLocalidadRepository;
import com.briomax.briobpm.persistence.repository.IVariableSistemaRepository;
import com.briomax.briobpm.transferobjects.emun.MagicNumber;
import com.briomax.briobpm.transferobjects.in.CatalogoEtiquetaTO;
import com.briomax.briobpm.transferobjects.in.ColumasGridEjecucionTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.DatosSeccionOCUTO;
import com.briomax.briobpm.transferobjects.in.EstatusAtributosMonedaTO;
import com.briomax.briobpm.transferobjects.in.EstatusEvaluaFormulaTO;
import com.briomax.briobpm.transferobjects.in.EstatusInfSeccionVPTO;
import com.briomax.briobpm.transferobjects.in.EstatusVariablesTO;
import com.briomax.briobpm.transferobjects.in.ListaTO;
import com.briomax.briobpm.transferobjects.in.NodoTO;
import com.briomax.briobpm.transferobjects.in.RsOcurrenciaTO;
import com.briomax.briobpm.transferobjects.in.VariableProcesoTO;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Interface Repository.java es ...
 * @author Alexis Zamoea 
 * @version 1.0 Fecha de creacion Abr 10, 2024 Modificaciones:
 * @since JDK 1.8
 */
@Service
@Transactional
@Slf4j
public class FormularioPantalla implements IFormularioPantalla{

	/** El atributo o variable messages service. */
	@Autowired
	private IMessagesService messagesService;
	
	/** El atributo o variable St Seccion Nodo Repository. */
	@Autowired
	IStSeccionNodoRepository stSeccionNodoRepository;
	
	/** El atributo o variable Catalogo Etiqueta Repository. */
	@Autowired
	ICatalogoEtiquetaRepository catalogoEtiquetaRepository;
	
	/** El atributo o variable St Valor Variable Repository. */
	@Autowired
	IStValorVariableRepository stValorVariableRepository;
	
	/** El atributo o variable St Variable Seccion sRepository. */
	@Autowired
	IStVariableSeccionRepository stVariableSeccionRepository;
	
	/** El atributo o variable St Documento Seccion Repository. */
	@Autowired
	IStDocumentoSeccionRepository stDocumentoSeccionRepository;
	
	/** El atributo o Variable Sistema Repository. */
	@Autowired
	IVariableSistemaRepository variableSistemaRepository;
	
	/** El atributo o Variable Entidad Repository. */
	@Autowired
	IVariableEntidadRepository variableEntidadRepository;
	
	/** El atributo o Variable St Variable Proceso Repository. */
	@Autowired
	IStVariableProcesoRepository stVariableProcesoRepository;
	
	/** El atributo o Variable Variable Localidad Repository. */
	@Autowired
	IVariableLocalidadRepository variableLocalidadRepository;
	
	/** El atributo o Variable In Documento ProcesoOc Repository. */
	@Autowired
	IInDocumentoProcesoOcRepository iInDocumentoProcesoOCRepository;
	
	/** El atributo o variable Nodo Helper. */
	@Autowired
	private INodoHelper nodoHelper;
	
	/** El atributo o variable Area Trabjo Helper. */
	@Autowired
	private IAreaTrabajoHelper areaTrabajoHelper;

	/** El atributo o variable St_Proceso repository. */
	@Autowired
	private IStProcesoRepository stProcesoRepository;

	/** El atributo o variable para ejecutar querys. */
	@Autowired  
	private IAreaTrabajoDAO areaTrabajoDAO;
	
	/** 
	 * Inyeccion de EntityManager, es la interfaz principal a traves de la
	 * cual se realizan operaciones de base de datos en JPA
	 */
	@PersistenceContext 
    private EntityManager entityManager;
	
	
	// SP_LEE_ETIQUETAS_TABLA
	@Override
	public DAORet<List<LeeEtiquetasTabla>, RetMsg> leeEtiquetasTabla(DatosAutenticacionTO session, NodoTO nodo, String cveDato, ListaTO listaTO)
	        throws BrioBPMException, ParseException {
	    
		log.debug("~~~~~~~~~~~~~~~~~~~~leeEtiquetasTabla~~~~~~~~~~~~~~~~~~~~");
		log.debug("-------------> nodo: " + nodo.toString());
		log.debug("-------------> cveDato: " + cveDato);
		log.debug("-------------> listaTO: " + listaTO.toString());
		
	    String cveInstancia = nodo.getCveInstancia();
	    String cveNodo = nodo.getCveNodo();
	    String cveProceso = nodo.getCveProceso();
	    Integer idNodo = nodo.getIdNodo();
	    Integer secuenciaNodo = nodo.getSecuenciaNodo();
	    BigDecimal version = nodo.getVersion();
	    String descripcionLista = listaTO.getDescripcionLista();
	    String tablaLista = listaTO.getTablaLista();
	    String valorLista = listaTO.getValorLista();
	    String whereLista = listaTO.getWhereLista();
	    
	    String idProceso;
	    String mensaje;
	    String qry;
	    String variableValor;
	    
    
	    //INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		RetMsg msg = RetMsg.builder().status("OK").message("").build();
	    idProceso =  "LEE_ETIQUETAS_TABLA";
	    
	    // CONCATENA LA LISTA DE LOS PARÁMETROS PARA DESPLEGARLOS EN CASO DE ERROR
	    variableValor = Constants.CVE_PROCESO + cveProceso + "|" +
	            Constants.VERSION + version + "|" +
	            Constants.CVE_INSTANCIA + cveInstancia + "|" +
	            Constants.CVE_NODO + cveNodo + "|" +
	            Constants.ID_NODO + idNodo + "|" +
	            Constants.SECUENCIA_NODO + secuenciaNodo + "|" +
	            Constants.CVE_DATO + cveDato;
	    
	    // VALIDA QUE EXISTAN SECCIONES PARA EL NODO PROPORCIONADO
	    if(valorLista == null || valorLista.isEmpty() ||
	            descripcionLista == null || descripcionLista.isEmpty() ||
	            tablaLista == null || tablaLista.isEmpty()) {
	        mensaje = messagesService.getMessage(
	                session,
	                idProceso,
	                "NO_EXISTEN_ETIQUETAS_PARA_DATO",
	                variableValor);

			msg.setStatus(Constants.ERROR);
			msg.setMessage(mensaje);
			return new DAORet<List<LeeEtiquetasTabla>, RetMsg>(null, msg);
	    }
	    
	    log.debug("----------> TABLA LISTA: " + tablaLista);
	    //  ARMA EL QUERY PARA OBTENER LA LISTA DE VALORES
	    qry = "SELECT DISTINCT '" +  cveDato + "' AS CVE_DATO, " +
	            valorLista + " AS VALOR_ALFANUMERICO, " +
	            descripcionLista + " AS ETIQUETA " +
	            " FROM " +    tablaLista;
	    if(whereLista != null && !whereLista.isEmpty()) {

	    	//se modifica esta sustitucion por el reemplazaVariables
	    	whereLista = whereLista.replace("@CVE_ENTIDAD@", session.getCveEntidad());
	    	whereLista = whereLista.replace("@CVE_INSTANCIA@", cveInstancia);
	    	//svm integra la sustitucion de variables
	    	EstatusVariablesTO info = nodoHelper.reemplazaVariables(session, nodo, null, whereLista);
	    	
	        qry = qry + " WHERE " + info.getCadenaSalida();
	    }
	    
	    log.info("----------> QUERY: " + qry);
	    
	    // EJECUTA LA SENTENCIA PARA OBTENER LA LISTA DE VARIABLES
	    List<LeeEtiquetasTabla> qryEjecutado = entityManager.createNativeQuery(qry, LeeEtiquetasTabla.class).getResultList();
	    log.info("------LISTA : " + qryEjecutado.toString());
	    if(qryEjecutado == null|| qryEjecutado.isEmpty()) {
	        mensaje = messagesService.getMessage(
	                session,
	                idProceso,
	                "ERROR_OBTENCION_ETIQUETAS_TABLA",
	                variableValor);
	    	msg.setStatus(Constants.ERROR);
			msg.setMessage(mensaje);
			return new DAORet<List<LeeEtiquetasTabla>, RetMsg>(null, msg);
	    }
	    log.debug("------> Tamaño Lista: " + qryEjecutado.size());
		return new DAORet<List<LeeEtiquetasTabla>, RetMsg>(qryEjecutado, msg);
	}


	// SP_LEE_ETIQUETAS_CATALOGO
	// Método leeEtiquetasCatalogo
	// Este método consulta las etiquetas de un catálogo y las traduce según sea necesario.
	// @param session Información de autenticación.
	// @param nodo Información del nodo.
	// @param cveDato Clave del dato que se desea consultar.
	// @return Un objeto DAORet que contiene una lista de objetos CatalogoEtiquetaTO y un mensaje de retorno RetMsg.
	// @throws BrioBPMException Excepción personalizada en caso de error.
	@Override
	public DAORet<List<CatalogoEtiquetaTO>, RetMsg> leeEtiquetasCatalogo(DatosAutenticacionTO session, NodoTO nodo, String cveDato)
	        throws BrioBPMException {
		
		log.debug("~~~~~~~~~~~~~~~~~~~~leeEtiquetasCatalogo~~~~~~~~~~~~~~~~~~~~");
	    
	    // Obtiene las claves y valores necesarios desde los objetos session y nodo.
	    String cveEntidad = session.getCveEntidad();
	    String cveInstancia = nodo.getCveInstancia();
	    String cveNodo = nodo.getCveNodo();
	    String cveProceso = nodo.getCveProceso();
	    Integer idNodo = nodo.getIdNodo();
	    Integer secuenciaNodo = nodo.getSecuenciaNodo();
	    BigDecimal version = nodo.getVersion();
	    String idProceso;
	    String mensaje;
	    String variableValor;
	    
	    // Inicializa un objeto RetMsg con el estado "OK" y un mensaje vacío.
	    RetMsg msg = RetMsg.builder().status("OK").message("").build();
	    idProceso =  "LEE_ETIQUETAS_CATALOGO"; // Nombre del proceso para seguimiento de errores.
	    
	    // Concatena los parámetros de entrada en una cadena para mostrarlos en caso de error.
	    variableValor = Constants.CVE_PROCESO + cveProceso + "|" +
	            Constants.VERSION + version + "|" +
	            Constants.CVE_INSTANCIA + cveInstancia + "|" +
	            Constants.CVE_NODO + cveNodo + "|" +
	            Constants.ID_NODO + idNodo + "|" +
	            Constants.SECUENCIA_NODO + secuenciaNodo + "|" +
	            Constants.CVE_DATO + cveDato;
	    
	    // Inicializa la lista de catálogo de etiquetas.
	    List<CatalogoEtiquetaTO> catalogo = new ArrayList<>();

	    log.debug("---cveDato en leeEtiquetasCatalogo: " + cveDato);
	    
	    // Valida si existen valores en la tabla CATALOGO_ETIQUETA para la clave dada.
	    Integer validaCatalogo = catalogoEtiquetaRepository.validaCatalogo(cveDato);
	    
	    log.debug("---validaCatalogo: " + validaCatalogo);
	    
	    if(validaCatalogo != null) {
	    	
	    	log.debug("--------> REGRESA ETIQUETAS");
	        // Si el catálogo es válido, obtiene las etiquetas y las asigna a la lista catalogo.
	        catalogo = catalogoEtiquetaRepository.regresaEtiquetas(cveDato)
	                .stream()
	                .map(item -> {
	                    Object[] row = (Object[]) item; // Obtiene cada fila de resultados.
	                    return CatalogoEtiquetaTO.builder()
	                            .cveVariable(cveDato) // Asigna la clave de variable.
	                            .valorAlfanumerico((String) Arrays.asList(row).get(MagicNumber.ZERO.getValue())) // Asigna el valor alfanumérico.
	                            .etiqueta((String) Arrays.asList(row).get(MagicNumber.ONE.getValue())) // Asigna la etiqueta.
	                            .build();
	                })
	                .peek(to -> to.setEtiqueta(nodoHelper.traducirEtiqueta(session, to.getEtiqueta()))) // Traduce la etiqueta si es necesario.
	                .collect(Collectors.toList());
	        log.debug("----------> CATALOGO DE ETIQUETAS: " + catalogo.size() + "" + catalogo.toString()); // Muestra en el log el tamaño y contenido del catálogo.

	    } else {
	    	log.debug("--------> NO REGRESA ETIQUETAS");
	    	
	        // Si no existen etiquetas en el catálogo, valida si existen valores de variables.
	        Integer validaVV = stValorVariableRepository.validaValorVariabless(cveEntidad, cveProceso, version, cveDato);
	        if(validaVV != null) {
	            // Si existen valores de variables, los asigna a la lista catalogo.
	            log.debug("--------------> REGRESA ST VALOR VARIABLES ");
	            catalogo = stValorVariableRepository.regresaValorVariables(cveEntidad, cveProceso, version, cveDato)
	                    .stream()
	                    .map(item -> {
	                        Object[] row = (Object[]) item; // Obtiene cada fila de resultados.
	                        return CatalogoEtiquetaTO.builder()
	                                .cveVariable(cveDato) // Asigna la clave de variable.
	                                .valorAlfanumerico((String) Arrays.asList(row).get(MagicNumber.ONE.getValue())) // Asigna el valor alfanumérico.
	                                .etiqueta((String) Arrays.asList(row).get(MagicNumber.TWO.getValue())) // Asigna la etiqueta.
	                                .build();
	                    })
	                    .peek(to -> to.setEtiqueta(nodoHelper.traducirEtiqueta(session, to.getEtiqueta()))) // Traduce la etiqueta si es necesario.
	                    .collect(Collectors.toList());
	            log.debug("----------> CATALOGO DE VALORES VARIABLES: " + catalogo.size() + "" + catalogo.toString()); // Muestra en el log el tamaño y contenido del catálogo.

	        } else {
	            // Si no se encuentran ni etiquetas ni valores de variables, se genera un mensaje de error.
	            mensaje = messagesService.getMessage(
	                    session,
	                    idProceso,
	                    "NO_EXISTEN_ETIQUETAS_PARA_DATO",
	                    variableValor);
	            msg.setStatus(Constants.ERROR); // Se establece el estado del mensaje como "ERROR".
	            msg.setMessage(mensaje); // Se asigna el mensaje de error.
	            return new DAORet<List<CatalogoEtiquetaTO>, RetMsg>(catalogo, msg); // Se retorna el resultado con la lista vacía y el mensaje de error.
	        }
	    }
	    
	    // Retorna el resultado con la lista de etiquetas del catálogo y el mensaje.
	    return new DAORet<List<CatalogoEtiquetaTO>, RetMsg>(catalogo, msg);
	}


	// SP_LEE_COLUMNAS_SECCION_OCU
	// Método leeColumnasSeccionOCU: Se encarga de leer las columnas de una sección en particular y devolver los datos formateados.
	@Override
	public 	DAORet<List<ColumasGridEjecucionTO>, RetMsg>  leeColumnasSeccionOCU(DatosAutenticacionTO session, NodoTO nodo, String cveSeccion)
			throws BrioBPMException {
		  // Log de entrada al método.
		
	    log.debug("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~leeColumnasSeccionOCU~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

	    
	    // Obtiene valores clave de la sesión y el nodo.
		String cveEntidad = session.getCveEntidad();
		String cveNodo = nodo.getCveNodo();
		String cveProceso = nodo.getCveProceso();
		Integer idNodo = nodo.getIdNodo();
		BigDecimal version = nodo.getVersion();
		
	    // Inicializa variables que se usarán más adelante.
		Float anchoColumna;
		Float anchoColumnaAuxiliar;
		Float c70;
		String cveDatoMoneda;
		String cveMonedaVisible;
		String cveVariable;
		BigDecimal decimales;
		String descripcionLista;
		String descripcionListaDesMon;
		BigDecimal enteros;
		String envioGrabar;
		String etiqueta;
		String etiquetaMoneda;
		String formatoFecha;
		String formula;
		String funcion;
		BigDecimal longitud;
		Float longitudMoneda;
		Integer numColumnas;
		String origenDatoVP;
		String origenMoneda = null;
		String origenMonedaSolicitar;
		String tablaLista;
		String tablaListaVwMoneda;
		String tipoControl;
		String tipoControlCombobox;
		String tipoControlTextbox;
		String tipoCveMoneda;
		String tipoDato;
		String tipoDatoAuxiliar;
		String tipoDatoDecimal;
		String tipoDatoMoneda;
		String tipoInteraccion;
		String tipoInteraccionEntrada;
		String tipoInteraccionSalida;
		String tipoLista;
		String tipoListaTabla;
		String valorLista;
		String valorListaCveMoneda;
		String visible;
		String whereLista;
		String whereListaMoneda;
		int numeroColumna = 0;
		int renglon = 0;
		
		
		boolean errorMoneda = false;
		
		//INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		RetMsg msg = RetMsg.builder().status("OK").message("").build();

		// CONSTANTES
		c70 = (float) 0.70; // Constante para cálculo de ancho de columna.
		descripcionListaDesMon = Constants.CVE_MONEDA;
		origenDatoVP = Constants.VARIABLE_PROCESO;
		origenMonedaSolicitar = Constants.SOLICITAR;
		tablaListaVwMoneda = Constants.VW_MONEDA_PROCESO;
		tipoControlCombobox = Constants.COMBOBOX;
		tipoControlTextbox = Constants.TEXTBOX;
		tipoDatoDecimal = Constants.DECIMAL;
		tipoDatoMoneda = Constants.MONEDA;
		tipoInteraccionEntrada = Constants.ENTRADA;
		tipoInteraccionSalida = Constants.SALIDA;
		tipoListaTabla = Constants.TABLA;
		valorListaCveMoneda = Constants.CVE_MONEDA;
		whereListaMoneda = null;
		
		log.debug("-------------> cveEntidad: " + cveEntidad);
		log.debug("-------------> cveProceso: " + cveProceso);
		log.debug("-------------> version: " + version);
		log.debug("-------------> cveNodo: " + cveNodo);
	    log.debug("-------------> idNodo: " + idNodo);
	    log.debug("-------------> cveSeccion: " + cveSeccion);
	    
		// CURSOR DE DATOS PARA LA SECCIÓN SOLICITADA
		// Obtiene datos de la sección solicitada desde el repositorio.
		List<Object[]> variablesSecc = stVariableSeccionRepository.leeDatosSeccion(cveEntidad, cveProceso, version, cveNodo, idNodo, cveSeccion);
		log.info("---------->> variablesSecc: " + variablesSecc.size());

		// CREANDO TABLA PARA INSERTAR LAS ESPECIFICACIONES DE LOS DATOS DEL ÁREA DE TRABAJO
		// Crea una lista para almacenar las especificaciones de las columnas del grid.
		List<ColumasGridEjecucionTO> columnasGridEjecucion = new ArrayList<ColumasGridEjecucionTO>();
		// RECORRE LOS DATOS PARA PREPARAR LA ESTRUCTURA DE SALIDA
		numColumnas = 0;	
		
		Map<Integer, Integer> maxColumnasPorRenglon = new HashMap<>();

		for (Object[] arr : variablesSecc) {

		    int numRenglon = ((Number) arr[0]).intValue();
		    int columna = ((Number) arr[arr.length - 1]).intValue();

		    maxColumnasPorRenglon.merge(numRenglon, columna, Math::max);
		}
		
		 // Verifica si la lista de variables no está vacía y procesa cada item.
		if (!variablesSecc.isEmpty()) {
			for (Object[] row : variablesSecc) {
				
				// Asigna valores a las variables a partir del objeto de sección.
		        //numColumnas = ((BigDecimal) row[MagicNumber.ZERO.getValue()]).intValue();
		        renglon = ((BigDecimal) row[MagicNumber.ZERO.getValue()]).intValue();
				cveVariable = (String) row[MagicNumber.ONE.getValue()];
				etiqueta =(String) row[MagicNumber.TWO.getValue()];
				log.debug("---------->> etiqueta" + etiqueta);
				
				anchoColumna = row[MagicNumber.THREE.getValue()] != null ? ((BigDecimal) row[MagicNumber.THREE.getValue()]).floatValue() : null;
				tipoDato = (String) row[MagicNumber.FOUR.getValue()];
				longitud = (BigDecimal) row[MagicNumber.FIVE.getValue()];
				enteros = (BigDecimal) row[MagicNumber.SIX.getValue()];
				decimales = (BigDecimal) row[MagicNumber.SEVEN.getValue()];
				formatoFecha = (String) row[MagicNumber.EIGHT.getValue()];
				origenMoneda = (String) row[MagicNumber.NINE.getValue()];
				cveMonedaVisible = (String) row[MagicNumber.TEN.getValue()];
				tipoInteraccion  = (String) row[MagicNumber.ELEVEN.getValue()];
				envioGrabar = (String) row[MagicNumber.TWELVE.getValue()];
				visible = (String) row[MagicNumber.THIRTEEN.getValue()];
				tipoControl = (String) row[MagicNumber.FOURTEEN.getValue()];
				formula = (String) row[MagicNumber.FIFTEEN.getValue()];
				funcion = (String) row[MagicNumber.SIXTEEN.getValue()];
				tipoLista = (String) row[MagicNumber.SEVENTEEN.getValue()];
				valorLista =(String) row[MagicNumber.EIGHTEEN.getValue()];
				descripcionLista = (String) row[MagicNumber.NINETEEN.getValue()];
				tablaLista = (String) row[MagicNumber.TWENTY.getValue()];
				whereLista = (String) row[MagicNumber.TWENTY_ONE.getValue()];
				numeroColumna = ((BigDecimal) row[MagicNumber.TWENTY_TWO.getValue()]).intValue();
				
				// Asigna el tipo de dato auxiliar al tipo de dato original.
		        tipoDatoAuxiliar = tipoDato;

		        // Log de ancho de columna.
		        log.debug("-----------> anchoColumna: " + anchoColumna);

		        // Calcula el ancho de columna auxiliar.
				if (anchoColumna != null) {
				    anchoColumnaAuxiliar = (float) anchoColumna.intValue();
				} else {
				    anchoColumnaAuxiliar = null;
				}
				log.debug("-----------> anchoColumnaAuxiliar: " + anchoColumnaAuxiliar);
				
				 // Determina el tipo de interacción basado en constantes.
				if(tipoInteraccion.equals(Constants.SI)) {
					tipoInteraccion = Constants.SALIDA;
				}else if(tipoInteraccion.equals(Constants.NO)) {
					tipoInteraccion = Constants.ENTRADA;
				}
				
				// Ajusta el tipo de dato auxiliar si es una moneda.
				if(tipoDato.equals(tipoDatoMoneda)) {
					tipoDatoAuxiliar = tipoDatoDecimal;
					if(anchoColumnaAuxiliar != null) {
						anchoColumnaAuxiliar = anchoColumna * c70;
					}
				}
				
				// Incrementa el contador de columnas.
		        numColumnas++;

		        // Construye un objeto ColumasGridEjecucionTO y lo añade a la lista de columnas del grid.
		        ColumasGridEjecucionTO ins1 = ColumasGridEjecucionTO.builder()
						.secuencia(numColumnas)
						.cveVariable(cveVariable)
						.etiqueta(nodoHelper.traducirEtiqueta(session, etiqueta))
				        .anchoColumna(anchoColumnaAuxiliar != null ? anchoColumnaAuxiliar.intValue() : null)
						.tipoDato(tipoDatoAuxiliar)
						.longitud(longitud.intValue())
						.enteros(enteros)
						.decimales(decimales)
						.formatoFecha(formatoFecha)
						.tipoInteraccion(tipoInteraccion)
						.envioGrabar(envioGrabar)
						.visible(visible)
						.tipoControl(tipoControl)
						.formula(formula)
						.funcion(funcion)
						.tipoLista(tipoLista)
						.valorLista(valorLista)
						.descripcionLista(descripcionLista)
						.tablaLista(tablaLista)
						.whereLista(whereLista)
						.numeroColumna(numeroColumna)
						.numColumnas(maxColumnasPorRenglon.get(renglon))
						.renglon(renglon)
						.build();
				columnasGridEjecucion.add(ins1);
				
				log.debug("############### ----- columnasGridEjecucion sin moneda : " + ins1.toString());
				
				if(tipoDato.equals(tipoDatoMoneda)) {
					//Lee atributos moneda
					EstatusAtributosMonedaTO leeAM = areaTrabajoHelper.leeAtributosMoneda(session, nodo, cveVariable, origenDatoVP);
					log.debug("----------> LEE ATRUBYTOS MONEDA: " + leeAM.toString());
					msg.setMessage((leeAM.getMensaje()));
					if(leeAM.getTipoExcepcion().equals(Constants.ERROR)) {
						msg.setStatus(Constants.ERROR);
						errorMoneda = true;
						break;
					}
					cveDatoMoneda = leeAM.getCveDatoMoneda();
					tipoCveMoneda = leeAM.getTipoCveMoneda();
					longitudMoneda = (float) leeAM.getLongitudMoneda().intValue();
					etiquetaMoneda = leeAM.getEtiquetaMoneda();
					
					numColumnas = numColumnas + 1;
					
					if (anchoColumna != null) {
						anchoColumnaAuxiliar = anchoColumna.intValue() - anchoColumnaAuxiliar;
					}
					if(origenMoneda.equals(origenMonedaSolicitar)) {
						tipoInteraccion = tipoInteraccionEntrada;
						envioGrabar = Constants.SI;
						tipoControl = tipoControlCombobox;
						tipoLista = tipoListaTabla;
						valorLista = valorListaCveMoneda;
						descripcionLista = descripcionListaDesMon;
						tablaLista = tablaListaVwMoneda;
						whereLista = whereListaMoneda;
						
					} else {
						tipoInteraccion = tipoInteraccionSalida;
						envioGrabar = Constants.SI;
						tipoControl = tipoControlTextbox;
						tipoLista = null;
						valorLista = null;
						descripcionLista = null;
						tablaLista = null;
						whereLista = null;
					}
					ColumasGridEjecucionTO ins2 = ColumasGridEjecucionTO.builder()
							.secuencia(numColumnas)
							.cveVariable(cveDatoMoneda)
							.etiqueta(nodoHelper.traducirEtiqueta(session, etiqueta))
							.anchoColumna(anchoColumnaAuxiliar != null ? anchoColumnaAuxiliar.intValue() : null)
							.tipoDato(tipoCveMoneda)
							.longitud(longitudMoneda.intValue())
							.enteros(null)
							.decimales(null)
							.formatoFecha(null)
							.tipoInteraccion(tipoInteraccion)
							.envioGrabar(envioGrabar)
							.visible(cveMonedaVisible)
							.tipoControl(tipoControl)
							.formula(null)
							.funcion(null)
							.tipoLista(tipoLista)
							.valorLista(valorLista)
							.descripcionLista(descripcionLista)
							.tablaLista(tablaLista)
							.whereLista(whereLista)
							.renglon(renglon)
							.build();
					columnasGridEjecucion.add(ins2);
				}
		    }
		}
		
		if (errorMoneda) {
			columnasGridEjecucion = null;
		}

		log.debug("############### RETURN SP_LEE_COLUMNAS_SECCION_OCU: " + msg.toString());
		return new DAORet<List<ColumasGridEjecucionTO>, RetMsg>(columnasGridEjecucion, msg);
	}

	
	
	// SP_EVALUA_FORMULA
	@Override
	public EstatusEvaluaFormulaTO evaluaFormula(DatosAutenticacionTO session, NodoTO nodo, String tipo, String formula)
			throws BrioBPMException {
		
		String cveEntidad = session.getCveEntidad();
		String cveLocalidad = session.getCveLocalidad();
		String cveInstancia = nodo.getCveInstancia();
		String cveProceso = nodo.getCveProceso();
		String idProceso = nodo.getIdProceso();
		Integer ocurrencia = nodo.getOcurrencia();
		BigDecimal version = nodo.getVersion();
		Integer fin;
		boolean finBusqueda;
		String formulaProceso;
		String formulaSalida;
		Integer inicio;
		Integer longitud;
		String mensaje;
		String valorAlfanumerico = null;
		BigDecimal valorDecimal = null;
		Integer valorEntero = null;
		Date valorFecha = null;
		String valorReemplazar = null;
		String variable;
		String variableValor;
		String variableEncontrada;
		String versionS;
		
		// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		EstatusEvaluaFormulaTO result = EstatusEvaluaFormulaTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();
		
		// INICIALIZA VARIABLES
		finBusqueda = false;
		inicio = 0;
		formulaSalida = formula;
		formulaProceso = formula;
		
		// REEMPLAZA VARIABLES DE IDENTIFICACIÓN DEL PROCESO EN LA FÓRMULA
		log.debug(">>>>>>>>>>>>>>>>>>svm evaluaFormula :" + formulaProceso);
		formulaProceso = formulaProceso.replace("@CVE_ENTIDAD@", cveEntidad);
		log.debug(">>>>>>>>>>>>>>>>>>svm evaluaFormula :" + formulaProceso);
		formulaProceso = formulaProceso.replace("@CVE_PROCESO@", cveProceso);
		formulaProceso = formulaProceso.replace("@VERSION@", version.toString());
		formulaProceso = formulaProceso.replace("@CVE_INSTANCIA@", cveInstancia);
		formulaProceso = ocurrencia == null? formulaProceso :formulaProceso.replace("@CVE_PROCESO@", ocurrencia.toString() );
		
		// RECORRE LA FÓRMULA PARA REEMPLAZAR VARIABLES
		while(!finBusqueda) {
			inicio = formulaProceso.indexOf("@", inicio);
			if(inicio == -1) {
				finBusqueda = true;
			} else {
				fin = formulaProceso.indexOf("@", inicio + 1);
				if(fin == 0) {
					finBusqueda = true;
				} else {
					longitud = fin - inicio - 1;
					variable = formulaProceso.substring(inicio + 1, longitud);
					inicio = fin + 1;
					variableEncontrada = Constants.NO;
					log.debug("------> variable: " + variable);
					switch(variable.substring(1, 4)) {
					case Constants.VENT:
						log.debug("------> VENT: " + Constants.VENT);
						VariableEntidadPK idVENT = VariableEntidadPK.builder()
								.cveEntidad(cveEntidad)
								.cveVariable(variable)
								.build();
						Optional<VariableEntidad> eVENT = variableEntidadRepository.findById(idVENT);
						if(eVENT.isPresent()) {
							variableEncontrada = Constants.SI;
							valorAlfanumerico = eVENT.get().getValorAlfanumerico();
							valorEntero = eVENT.get().getValorEntero();
							valorDecimal = eVENT.get().getValorDecimal();
							valorFecha = eVENT.get().getValorFecha();
							tipo = eVENT.get().getTipo();
						}
						break;
					case Constants.VLOC:
						log.debug("------> VLOC: " + Constants.VLOC);
						VariableLocalidadPK idVLOC = VariableLocalidadPK.builder()
								.cveEntidad(cveEntidad)
								.cveLocalidad(cveLocalidad)
								.cveVariable(variable)
								.build();
						Optional<VariableLocalidad> eVLOC = variableLocalidadRepository.findById(idVLOC);
						if(eVLOC.isPresent()) {
							variableEncontrada = Constants.SI;
							valorAlfanumerico = eVLOC.get().getValorAlfanumerico();
							valorEntero = eVLOC.get().getValorEntero();
							valorDecimal = eVLOC.get().getValorDecimal();
							valorFecha = eVLOC.get().getValorFecha();
							tipo = eVLOC.get().getTipo();
						}
						break;
					case Constants.VSIS:
						log.debug("------> VSIS: " + Constants.VSIS);
						Optional<VariableSistema> eVSIS = variableSistemaRepository.findById(variable);
						if(eVSIS.isPresent()) {
							variableEncontrada = Constants.SI;
							valorAlfanumerico = eVSIS.get().getValorAlfanumerico();
							valorEntero = eVSIS.get().getValorEntero();
							valorDecimal = eVSIS.get().getValorDecimal();
							valorFecha = eVSIS.get().getValorFecha();
							tipo = eVSIS.get().getTipo();
						}
						break;
					}
					
					if(variableEncontrada.equals(Constants.SI)) {
						log.debug("------> tipo: " + tipo);
						switch(tipo) {
						case Constants.ALFANUMERICO:
							valorReemplazar = "\"" + valorAlfanumerico.trim().replace("\"", "\"\"") + "\"";
							break;
						case Constants.ENTERO:
							valorReemplazar = valorEntero.toString();
							break;
						case Constants.DECIMAL:
							valorReemplazar = valorDecimal.toString();
							break;
						case Constants.FECHA:
							valorReemplazar = valorFecha.toString();
							break;
						}
						
						formulaSalida = formulaSalida.replace("@" + variable + "@", valorReemplazar);
					}
				}
			}
		}
		

		// REEMPLAZA VARIABLES QUE IDENTIFICAN AL PROCESO EN LA SENTENCIA
		versionS = version.toString();
		log.debug(">>>>>>>>>>>>>>>>>>svm evaluaFormula :" + formulaSalida);
		formulaSalida = formulaSalida.replace("@CVE_ENTIDAD@", cveEntidad);
		log.debug(">>>>>>>>>>>>>>>>>>svm evaluaFormula :" + formulaSalida);
		formulaSalida = formulaSalida.replace("@CVE_PROCESO@", cveProceso);
		formulaSalida = formulaSalida.replace("@VERSION@", versionS);
		formulaSalida = formulaSalida.replace("@CVE_INSTANCIA@", cveInstancia);
		
		switch(tipo) {
		case Constants.ALFANUMERICO:
			valorAlfanumerico = formulaSalida;
			result.setValorAlfanumerico(valorAlfanumerico);
			break;
		case Constants.ENTERO:
			valorEntero = Integer.parseInt(formulaSalida);
			result.setValorEntero(valorEntero);
			break;
		case Constants.DECIMAL:
			valorDecimal = new BigDecimal(formulaSalida);
			result.setValorDecimal(valorDecimal);
			break;
		case Constants.FECHA:
			//valorFecha = formulaSalida;
			break;
		default:
			variableValor = Constants.FORMULA + formula.trim() + "|" +
							Constants.SENTENCIA + formulaSalida.trim();
			mensaje = messagesService.getMessage(
					session,
					idProceso,
					"ERR-EVALUACION-FORMULA",
					variableValor);
			result.setTipoExcepcion(Constants.ERROR);
			result.setMensaje(mensaje);
			return result;
		}
        log.debug("############### RETURN SP_EVALUA_FORMULA: " + result.toString());
		return result;
	}

	// SP_LEE_INF_SECCION_OCU
	/**
	 * Este método se encarga de obtener y procesar la información de una sección de tipo ocurrencia o grid en la aplicación.
	 * 
	 * - Primero, inicializa las variables necesarias y registra información de depuración en los logs.
	 * - Luego, obtiene las variables de proceso correspondientes a la sección solicitada a través del método `leeInfSeccionVP`.
	 * - Si ocurre algún error durante la obtención de las variables, se retorna un objeto DAORet con un mensaje de error.
	 * - Posteriormente, el método organiza y ordena los datos de la sección usando un comparador personalizado que los clasifica por ocurrencia, orden de dato, clave de dato, y secuencia de valor.
	 * - A continuación, se procesa cada dato en la lista ordenada para generar objetos `RsOcurrenciaTO`, que representan las ocurrencias y sus valores asociados.
	 * - Si se detectan cambios en la ocurrencia o en la clave de dato, se actualiza la lista de datos de la ocurrencia correspondiente.
	 * - Finalmente, se devuelve un objeto `DAORet` que contiene la lista de `RsOcurrenciaTO` y un mensaje con el estado de la operación.
	 * 
	 * Este método es fundamental para la correcta gestión y visualización de datos en secciones de tipo ocurrencia en la aplicación, asegurando que los datos sean procesados y organizados adecuadamente.
	 */
	@Override
	public DAORet<List<RsOcurrenciaTO>, RetMsg> leeInfSeccionOcu(DatosAutenticacionTO session, NodoTO nodo, String cveSeccion)
			throws BrioBPMException {
		log.info("-----------------------------leeInfSeccionOcu----------------------------------");
		log.info("--------> SESSION: " + session.toString());
		log.info("--------> NODO: " + nodo.toString());
		log.info("--------> CVE_SECCION: " + cveSeccion);
		
		// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		RetMsg msg = RetMsg.builder().status("OK").message("").build();

		// CREANDO TABLA QUE ALMACENA LA LISTA DE VARIABLES QUE FORMAN LA SECCIÓN DEL GRID
		List<DatosSeccionOCUTO> datosSeccionOCU = new ArrayList<DatosSeccionOCUTO>();
		log.debug("----------> LISTA DATOS SECCION OCU: " + datosSeccionOCU.size() + datosSeccionOCU.toString());
		
		// OBTIENE INFORMACIÓN DE LAS VARIABLES DE PROCESO
		EstatusInfSeccionVPTO leeInfSVP = leeInfSeccionVP(session, nodo, cveSeccion, datosSeccionOCU);
		log.debug("----------> DATOS SECCION OCU: " + datosSeccionOCU.size() + datosSeccionOCU.toString());
		
	    // Verifica si ocurrió algún error durante la obtención de las variables de proceso.
		if(!leeInfSVP.getTipoExcepcion().equals("OK")) {
			log.debug("--------> ERROR EN LEE_INF_SECCION_VP: " + leeInfSVP.getMensaje());
			msg.setMessage(leeInfSVP.getMensaje());
			msg.setStatus(leeInfSVP.getTipoExcepcion());
			return new DAORet<List<RsOcurrenciaTO>, RetMsg>(null, msg);
		}
		
		// Listado que almacenará los resultados finales
		List<RsOcurrenciaTO> rsOcurrenciaTO = new ArrayList<RsOcurrenciaTO>();
		
		
		// CURSOR QUE OBTIENE LAS VARIABLES DE LA SECCIÓN Y SUS VALORES
	

		// Creo variable para identificar que tipo trabajare
		String tipo = leeInfSVP.getTipo();
		log.debug("´´´ TIPO: " + tipo);
		msg.setMessage(tipo);
		
		// Define si es tipo Documento o Variable
		if("VARIABLE".equals(tipo)) {
			leeInfSeccionOcuVariable(cveSeccion, datosSeccionOCU, rsOcurrenciaTO);
			log.debug("´´´ VARIABLE: " + rsOcurrenciaTO.size());
		} else if ("DOCUMENTO".equals(tipo)) {
			leeInfSeccionOcuDocumento(cveSeccion, datosSeccionOCU, rsOcurrenciaTO);
			log.debug("´´´ DOCUMENTO: " + rsOcurrenciaTO.size());

		}

		
		log.debug("############### RETURN SP_LEE_INF_SECCION_OCU: " + cveSeccion);
		return new DAORet<List<RsOcurrenciaTO>, RetMsg>(rsOcurrenciaTO, msg);
	}


	private void leeInfSeccionOcuVariable(String cveSeccion, List<DatosSeccionOCUTO> datosSeccionOCU,
			List<RsOcurrenciaTO> rsOcurrenciaTO) {
		
		log.debug("--------> leeInfSeccionOcuVariable");
		
		String cveDato;
		String cveDatoPrevio;
		String datosOcurrencia;
		Integer numValores;
		Integer ocurrencia;
		Integer ocurrenciaPrevia;
		Integer secuenciaValor;
		String valorBaseDatos;
		String valorPantalla;
		List<byte[]> imagenes = new ArrayList<byte[]>();
		
		// CURSOR QUE OBTIENE LAS VARIABLES DE LA SECCIÓN Y SUS VALORES
		// Genera una lista de resultados que contiene los datos requeridos y el número de valores para cada combinación única de ocurrencia, orden de dato y clave de dato
		log.debug("--------> COMIENZA GENERANDO CURSOR");
		
		// Definir el comparador fuera de la expresión lambda
		Comparator<DatosSeccionOCUTO> comparator = Comparator.comparing((DatosSeccionOCUTO dato) -> dato.getOcurrencia(), Comparator.nullsFirst(Comparator.naturalOrder()))
			    .thenComparing(dato -> dato.getOrdenDato(), Comparator.nullsFirst(Comparator.naturalOrder()))
			    .thenComparing(dato -> dato.getCveDato(), Comparator.nullsFirst(Comparator.naturalOrder()))
			    .thenComparing(dato -> dato.getSecuenciaValor(), Comparator.nullsFirst(Comparator.naturalOrder()));

		log.debug("-----> datosSeccionOCU: " + datosSeccionOCU.size());
		
			// Genera una lista de variables de la sección organizadas y ordenadas por ocurrencia, orden de dato, clave de dato y secuencia de valor.
			List<DatosSeccionOCUTO> varSeccion = datosSeccionOCU.stream()
			    .map(dato -> {
			        Integer ocu = dato.getOcurrencia();
			        Integer secuencia = dato.getSecuenciaValor();

		            // Calcula el número de valores para cada combinación única de ocurrencia, orden de dato y clave de dato
			        long numVal = datosSeccionOCU.stream()
			                .filter(d -> Objects.equals(d.getOcurrencia(), ocu) &&
			                             Objects.equals(d.getOrdenDato(), dato.getOrdenDato()) &&
			                             Objects.equals(d.getCveDato(), dato.getCveDato()))
			                .count();

			        Integer numeroValores = numVal >= 1 ? (int) numVal : 0;

		            // Retorna un nuevo objeto DatosSeccionOCUTO con el número de valores calculado.
			        return new DatosSeccionOCUTO(
			            ocu,
			            dato.getSecuenciaDocumento(),
			            dato.getOrdenDato(),
			            dato.getCveDato(),
			            numeroValores,
			            secuencia,
			            dato.getValorBaseDatos(),
			            dato.getValorPantalla(),
			            dato.getValorBaseDatosImagen(),
			            dato.getValorPantallaImagen(),
			            dato.getTipoDato()
			            );
			    })
			    .sorted(comparator) // Ordena la lista usando el comparador definido.
			    .collect(Collectors.toList());

			
		log.debug("----------> CURSOR variables seccion: "  + cveSeccion + " " + varSeccion.size() + " " +  varSeccion.toString());
		log.debug("---------> TERMINA GENERAR LISTA");
		datosOcurrencia = " ";
		log.debug("--------> datosOcurrencia : " + datosOcurrencia);
		ocurrenciaPrevia = 0;
		cveDatoPrevio = " ";
		log.debug("--------> varSeccion : " + varSeccion.size());
		
	    // Itera sobre la lista ordenada para procesar cada elemento y generar los objetos RsOcurrenciaTO.
//		for(DatosSeccionOCUTO dato : varSeccion) {
//			log.info("------->> ocurrencia: " + dato.getOcurrencia());
//			log.info("------->> secuenciaDocumento: " +  dato.getSecuenciaDocumento());
//			log.info("--------> valorBaseDatos   : " + dato.getValorBaseDatos());
//			log.info("--------> valorPantalla    : " + dato.getValorPantalla() );
//			log.info("--------> cveDato    : " + dato.getCveDato() );
//
//		}
		for(DatosSeccionOCUTO dato : varSeccion) {
			//log.info("---------------------------------------------------------------------");
			ocurrencia = dato.getOcurrencia();
			cveDato = dato.getCveDato();
			numValores = dato.getNumeroValores();
			secuenciaValor = dato.getSecuenciaValor();
			valorBaseDatos = dato.getValorBaseDatos();
			valorPantalla = dato.getValorPantalla();
			
			//log.debug("--------> ocurrencia       : " + ocurrencia);
			//log.debug("--------> ocurrenciaPrevia : " + ocurrenciaPrevia);
			
			
			
			//log.debug("--------> cveDato          : " + cveDato);
			//log.debug("--------> cveDatoPrevio    : " + cveDatoPrevio);
			//log.debug("--------> numValores       : " + numValores);
			//log.debug("--------> datosOcurrencia  : " + datosOcurrencia);
			//log.debug("--------> secuenciaValor   : " + secuenciaValor);
			//log.debug("--------> valorBaseDatos   : " + valorBaseDatos + " |--");
			//log.debug("--------> valorPantalla    : " + valorPantalla + " |--");
			
	        // Si la ocurrencia es distinta de la ocurrencia previa, se añade el conjunto de datos anterior a la lista rsOcurrenciaTO.
			if(ocurrencia != null && ocurrenciaPrevia!= null && ocurrenciaPrevia != ocurrencia) {
				
				if(ocurrenciaPrevia != 0) {
					log.debug("ocurrenciaPrevia != 0");
					rsOcurrenciaTO.add(RsOcurrenciaTO.builder()
							.ocurrencia(ocurrenciaPrevia)
							.datosOcurrencia(datosOcurrencia)
							.imagenes(imagenes)
							.build());
					}
				  // Inicializar datosOcurrencia con el primer conjunto de datos
		        datosOcurrencia = cveDato.trim() + "|" + numValores.toString().trim();
		        log.debug("--------> datosOcurrencia ocurrenciaPrevia != 0   : " + datosOcurrencia);
		        imagenes = new ArrayList<byte[]>();
			} else {
				log.debug("ocurrenciaPrevia == ocurrencia");
				
	            // Si la clave de dato actual es distinta de la clave de dato previa, se concatenan los nuevos datos a datosOcurrencia.
		        if(!cveDatoPrevio.equals(cveDato)) {
		        	log.debug("cveDatoPrevio != cveDato");
		            datosOcurrencia += "#" +  cveDato + "|" + numValores.toString().trim();
		            if (dato.getValorBaseDatosImagen() != null) {
		            	
		            }
		            log.debug("--------> datosOcurrencia cveDatoPrevio != cveDato: " + datosOcurrencia);
		        }
			}
			
	        // Manejo de valores nulos o vacíos para valorBaseDatos y valorPantalla.
			valorBaseDatos = valorBaseDatos == null || "".equals(valorBaseDatos) ? " " : valorBaseDatos;
			valorBaseDatos = valorBaseDatos.replace("#", "\\#");
		    valorPantalla = valorPantalla == null || "".equals(valorPantalla) ? " " : valorPantalla;
		    valorPantalla = valorPantalla.replace("#", "\\#");
		    // Concatenar el resto de los datos a datosOcurrencia
		    
		    //svm ajuste de imagen
		    log.debug("-------------->>>>>>>> getTipoDato   : " + dato.getTipoDato());
		    if(dato.getTipoDato().equals(Constants.IMAGEN) && dato.getValorBaseDatosImagen() != null) {
		    	 log.debug("-------------->>>>>>>> entre en imagen   : " + dato.getValorBaseDatosImagen().length);
		    	imagenes.add(dato.getValorBaseDatosImagen());
		    	Integer posicion = imagenes.size() - 1;
		    	valorPantalla = posicion.toString();
		    	valorBaseDatos = posicion.toString();
		    }
		    
		    datosOcurrencia += "|" + secuenciaValor + "|" + valorBaseDatos + "|" + valorPantalla;
		    
		    if(datosOcurrencia.contains("null")) {
		    	datosOcurrencia = null;
		    }
		    ocurrenciaPrevia = ocurrencia;
		    cveDatoPrevio = cveDato;
			
			log.debug("--------> valorBaseDatos   : " + valorBaseDatos);
			log.debug("--------> valorPantalla    : " + valorPantalla);
			log.debug("--------> datosOcurrencia* : " + datosOcurrencia);
			log.debug("--------> ocurrenciaPrevia : " + ocurrenciaPrevia);
			log.debug("--------> cveDatoPrevio    : " + cveDatoPrevio);
		}
		
		log.info("---  ajsute avm para  tema de imagen datosOcurrencia: " + datosOcurrencia);
		// PROCESA DATOS PENDIENTES
		if(datosOcurrencia!= null && !" ".equals(datosOcurrencia)) {
			log.debug("datos pendientes");
			rsOcurrenciaTO.add(RsOcurrenciaTO.builder()
					.ocurrencia(ocurrenciaPrevia)
					.datosOcurrencia(datosOcurrencia) //svm ajuste de imagen
					.imagenes(imagenes)
					.build());
		}
		
		// REGRESA EL RESULT SET FINAL
		// Itera sobre la lista y establece el atributo en cada elemento.
		log.info("--------> rsOcurrenciaTO: " + rsOcurrenciaTO.size() + " " + rsOcurrenciaTO.toString());
		for (RsOcurrenciaTO rsO : rsOcurrenciaTO) {
			rsO.setCveSeccion(cveSeccion);
			log.debug("--------> RSO: " + rsO);
			log.debug("-----DATOS OCURRENCIA: " + rsO.getDatosOcurrencia());
			log.debug("-----DATOS SECUENCIA DOCUMENTO: " + rsO.getSecuenciaDocumento());
			}
		Collections.sort(rsOcurrenciaTO, (o1, o2) -> {
		    // Maneja los casos donde ocurrencia es null
		    if (o1.getOcurrencia() == null && o2.getOcurrencia() == null) {
		        return 0;
		    } else if (o1.getOcurrencia() == null) {
		        return 1; // Los valores null van al final
		    } else if (o2.getOcurrencia() == null) {
		        return -1; // Los valores null van al final
		    } else {
		        return Integer.compare(o1.getOcurrencia(), o2.getOcurrencia());
		    }
		});

		log.debug("CLAVE DE SECCIÓN: " + cveSeccion);
//		for (RsOcurrenciaTO rsO : rsOcurrenciaTO) {
//			log.info("--------> OCURRENCIA: " + rsO.getOcurrencia());
//			log.info("--------> DATOS OCURRENCIA: " + rsO.getDatosOcurrencia());
//			log.info("-----DATOS SECUENCIA DOCUMENTO: " + rsO.getSecuenciaDocumento());
//		}
	}

	
	
	private void leeInfSeccionOcuDocumento(String cveSeccion, List<DatosSeccionOCUTO> datosSeccionOCU,
			List<RsOcurrenciaTO> rsOcurrenciaTO) {
		String cveDato;
		String cveDatoPrevio;
		String datosOcurrencia;
		Integer numValores;
		Integer ocurrencia;
		Integer ocurrenciaPrevia;
		Integer secuenciaValor;
		Integer secuenciaDocumento;
		Integer secuenciaDocumentoPrevia;
		String valorBaseDatos;
		String valorPantalla;
		List<byte[]> imagenes = null;
		// CURSOR QUE OBTIENE LAS VARIABLES DE LA SECCIÓN Y SUS VALORES
		// Genera una lista de resultados que contiene los datos requeridos y el número de valores para cada combinación única de ocurrencia, orden de dato y clave de dato
		log.info("--------> COMIENZA GENERANDO CURSOR DOCUMENTO");
		
		// Definir el comparador fuera de la expresión lambda
		Comparator<DatosSeccionOCUTO> comparator = Comparator.comparing(
		        (DatosSeccionOCUTO dato) -> dato.getSecuenciaDocumento(), Comparator.nullsFirst(Comparator.naturalOrder()))
		        .thenComparing(dato -> dato.getOcurrencia(), Comparator.nullsFirst(Comparator.naturalOrder()))
		        .thenComparing(dato -> dato.getOrdenDato(), Comparator.nullsFirst(Comparator.naturalOrder()))
		        .thenComparing(dato -> dato.getCveDato(), Comparator.nullsFirst(Comparator.naturalOrder()))
		        .thenComparing(dato -> dato.getSecuenciaValor(), Comparator.nullsFirst(Comparator.naturalOrder()));

		// Genera una lista de variables de la sección organizadas y ordenadas por ocurrencia, orden de dato, clave de dato y secuencia de valor.
		List<DatosSeccionOCUTO> varSeccion = datosSeccionOCU.stream()
		    .map(dato -> {
		        Integer ocu = dato.getOcurrencia();
		        Integer secuencia = dato.getSecuenciaValor();

		        // Calcula el número de valores para cada combinación única de ocurrencia, orden de dato y clave de dato
		        long numVal = datosSeccionOCU.stream()
		                .filter(d -> Objects.equals(d.getSecuenciaDocumento(), dato.getSecuenciaDocumento()) &&
		                             Objects.equals(d.getOcurrencia(), ocu) &&
		                             Objects.equals(d.getOrdenDato(), dato.getOrdenDato()) &&
		                             Objects.equals(d.getCveDato(), dato.getCveDato()))
		                .count();

		        Integer numeroValores = (int) numVal;

		        // Retorna un nuevo objeto DatosSeccionOCUTO con el número de valores calculado.
		        // svm ajuste de imagen
		        return new DatosSeccionOCUTO(
		            ocu,
		            dato.getSecuenciaDocumento(),
		            dato.getOrdenDato(),
		            dato.getCveDato(),
		            numeroValores,
		            secuencia,
		            dato.getValorBaseDatos(),
		            dato.getValorPantalla(),
		            dato.getValorBaseDatosImagen(),
		            dato.getValorPantallaImagen(),
		            dato.getTipoDato()
		            );
		    })
		    .sorted(comparator) // Ordena la lista usando el comparador definido.
		    .collect(Collectors.toList());


			// Imprimir la lista ordenada para depuración
		//log.debug("----PREPARA PARA IMPRIMIR SU CONTENIDO DE DATOS SECCION");


			//log.debug("----------> CURSOR variables seccion DOCUMENTOS: "  + cveSeccion + " " + varSeccion.size() + " " +  varSeccion.toString());
		//log.debug("---------> TERMINA GENERAR LISTA");
		datosOcurrencia = " ";
		//log.debug("--------> datosOcurrencia : " + datosOcurrencia);
		ocurrenciaPrevia = 0;
		secuenciaDocumentoPrevia = 0;
		cveDatoPrevio = " ";
		//log.debug("--------> varSeccion VER ORDEN : " + varSeccion.size());
		
	    // Itera sobre la lista ordenada para procesar cada elemento y generar los objetos RsOcurrenciaTO.
		/*for(DatosSeccionOCUTO dato : varSeccion) {
			log.debug("-------------------------------******------------------------------------");
			log.debug("------->> ocurrencia: " + dato.getOcurrencia());
			log.debug("------->> secuenciaDocumento: " +  dato.getSecuenciaDocumento());
			log.debug("--------> valorBaseDatos   : " + dato.getValorBaseDatos());
			log.debug("--------> valorPantalla    : " + dato.getValorPantalla() );
			log.debug("--------> cveDato    : " + dato.getCveDato() );

		}*/
		
		for(DatosSeccionOCUTO dato : varSeccion) {
			//log.debug("---------------------------------------------------------------------");
			ocurrencia = dato.getOcurrencia();
			secuenciaDocumento = dato.getSecuenciaDocumento();
			cveDato = dato.getCveDato();
			numValores = dato.getNumeroValores();
			secuenciaValor = dato.getSecuenciaValor();
			valorBaseDatos = dato.getValorBaseDatos();
			valorPantalla = dato.getValorPantalla();
			//log.debug("--------> ocurrencia       : " + ocurrencia);
			//log.debug("--------> ocurrenciaPrevia : " + ocurrenciaPrevia);
			
			
			
			//log.debug("--------> cveDato          : " + cveDato);
			//log.debug("--------> cveDatoPrevio    : " + cveDatoPrevio);
			//log.debug("--------> numValores       : " + numValores);
			//log.debug("--------> datosOcurrencia  : " + datosOcurrencia);
			//log.debug("--------> secuenciaValor   : " + secuenciaValor);
			//log.debug("--------> valorBaseDatos   : " + valorBaseDatos + " |--");
			//log.debug("--------> valorPantalla    : " + valorPantalla + " |--");
			
	        // Si la ocurrencia es distinta de la ocurrencia previa, se añade el conjunto de datos anterior a la lista rsOcurrenciaTO.
			if(ocurrencia != null && ocurrenciaPrevia!= null && (ocurrencia != ocurrenciaPrevia && secuenciaDocumento != secuenciaDocumentoPrevia) ||
						(ocurrencia != ocurrenciaPrevia && secuenciaDocumento == secuenciaDocumentoPrevia) ||
						(ocurrencia == 1 && secuenciaDocumento != secuenciaDocumentoPrevia)) {
				
				if(ocurrenciaPrevia != 0) {
					rsOcurrenciaTO.add(RsOcurrenciaTO.builder()
							.secuenciaDocumento(secuenciaDocumento)
							.ocurrencia(ocurrenciaPrevia)
							.datosOcurrencia(datosOcurrencia)
							.build());
					}
				  // Inicializar datosOcurrencia con el primer conjunto de datos
		        datosOcurrencia = cveDato.trim() + "|" + numValores.toString().trim();
		        log.debug("--------> datosOcurrencia ocurrenciaPrevia != 0   : " + datosOcurrencia);
		        imagenes = new ArrayList<byte[]>();
			} else {
	            // Si la clave de dato actual es distinta de la clave de dato previa, se concatenan los nuevos datos a datosOcurrencia.
		        if(!cveDatoPrevio.equals(cveDato)) {
		            datosOcurrencia += "#" +  cveDato + "|" + numValores.toString().trim();
		            log.debug("--------> datosOcurrencia cveDatoPrevio != cveDato: " + datosOcurrencia);
		        }
			}
			
		    //svm ajuste de imagen
		    if(dato.getValorBaseDatosImagen() != null) {
		    	imagenes.add(dato.getValorBaseDatosImagen());
		    	Integer posicion = imagenes.size() - 1;
		    	valorPantalla = posicion.toString();
		    	valorBaseDatos = posicion.toString();
		    }
		    
	        // Manejo de valores nulos o vacíos para valorBaseDatos y valorPantalla.
			valorBaseDatos = valorBaseDatos == null || "".equals(valorBaseDatos) ? " " : valorBaseDatos;
		    valorPantalla = valorPantalla == null || "".equals(valorPantalla) ? " " : valorPantalla;
		    // Concatenar el resto de los datos a datosOcurrencia
		    
		    datosOcurrencia += "|" + secuenciaValor + "|" + valorBaseDatos + "|" + valorPantalla;
		    
		    if(datosOcurrencia.contains("null")) {
		    	datosOcurrencia = null;
		    }
		    ocurrenciaPrevia = ocurrencia;
		    secuenciaDocumentoPrevia = secuenciaDocumento;
		    cveDatoPrevio = cveDato;
			
			log.debug("--------> valorBaseDatos   : " + valorBaseDatos);
			log.debug("--------> valorPantalla    : " + valorPantalla);
			log.info("--------> datosOcurrencia* : " + datosOcurrencia);
			log.debug("--------> ocurrenciaPrevia : " + ocurrenciaPrevia);
			log.debug("--------> cveDatoPrevio    : " + cveDatoPrevio);
		}
		
		
		// PROCESA DATOS PENDIENTES
		if(datosOcurrencia!= null && !" ".equals(datosOcurrencia)) {
			//log.debug("datos pendientes");
			rsOcurrenciaTO.add(RsOcurrenciaTO.builder()
					.secuenciaDocumento(secuenciaDocumentoPrevia)
					.ocurrencia(ocurrenciaPrevia)
					.datosOcurrencia(datosOcurrencia)
					.imagenes(imagenes)
					.build());
		}
		
		// REGRESA EL RESULT SET FINAL
		// Itera sobre la lista y establece el atributo en cada elemento.
					log.debug("--------> rsOcurrenciaTO: " + rsOcurrenciaTO.size() + " " + rsOcurrenciaTO.toString());
			for (RsOcurrenciaTO rsO : rsOcurrenciaTO) {
			    rsO.setCveSeccion(cveSeccion);
			    log.debug("--------> RSO: " + rsO);
			    log.debug("-----DATOS OCURRENCIA: " + rsO.getDatosOcurrencia());
			    log.debug("-----DATOS SECUENCIA DOCUMENTO: " + rsO.getSecuenciaDocumento());
			}
		Collections.sort(rsOcurrenciaTO, (o1, o2) -> {
		    // Maneja los casos donde ocurrencia es null
		    if (o1.getOcurrencia() == null && o2.getOcurrencia() == null) {
		        return 0;
		    } else if (o1.getOcurrencia() == null) {
		        return 1; // Los valores null van al final
		    } else if (o2.getOcurrencia() == null) {
		        return -1; // Los valores null van al final
		    } else {
		        return Integer.compare(o1.getOcurrencia(), o2.getOcurrencia());
		    }
		});

		
		/*for (RsOcurrenciaTO rsO : rsOcurrenciaTO) {
			log.debug("--------> OCURRENCIA: " + rsO.getOcurrencia());
			log.debug("--------> DATOS OCURRENCIA: " + rsO.getDatosOcurrencia());
			log.debug("-----DATOS SECUENCIA DOCUMENTO: " + rsO.getSecuenciaDocumento());
		}*/
	}

	// SP_LEE_INF_SECCION_VP
	// CARGA UNA TABLA TEMPORAL CON LOS DATOS DE LAS VARIABLES DE PROCESO PARA UNA SECCIÓN DE TIPO GRID.
	/**
	 * Este método se encarga de leer la información de una sección de un proceso en particular.
	 * 
	 * 1. Se inicializan varias variables que se utilizarán para procesar la información.
	 * 2. Se registra la sesión y el nodo que se están procesando en los logs.
	 * 3. Se cargan las variables del proceso desde la base de datos a través de una consulta.
	 * 4. Se recorren las variables obtenidas para construir objetos `VariableProcesoTO` que 
	 *    contienen la información de cada variable.
	 * 5. Se procesan las variables según su tipo (alfanumérico, entero, decimal, fecha, etc.)
	 *    y se realizan operaciones específicas, como la evaluación de fórmulas o la búsqueda 
	 *    de documentos asociados.
	 * 6. Finalmente, se construye una lista de objetos `DatosSeccionOCUTO` que contiene la 
	 *    información procesada de la sección, la cual se devuelve al final del método.
	 * 
	 * Si ocurre algún error en el proceso, el método retorna un objeto `EstatusInfSeccionVPTO` 
	 * con el tipo de excepción y el mensaje correspondiente.
	
	 * @param session Información de autenticación del usuario.
	 * @param nodo Información del nodo del proceso.
	 * @param cveSeccion Clave de la sección que se está procesando.
	 * @param datosSeccionOCU Lista donde se almacenarán los objetos `DatosSeccionOCUTO` resultantes.
	 * @return Un objeto `EstatusInfSeccionVPTO` que contiene el resultado del procesamiento y posibles mensajes de error.
	 * @throws BrioBPMException Si ocurre un error durante el procesamiento.
	 */
	@Override
	public EstatusInfSeccionVPTO leeInfSeccionVP(DatosAutenticacionTO session, NodoTO nodo, String cveSeccion,
			List<DatosSeccionOCUTO> datosSeccionOCU) throws BrioBPMException {
		// REVISAR OCURRENCIA
		String cveEntidad = session.getCveEntidad();
		String cveInstancia = nodo.getCveInstancia();
		String cveNodo = nodo.getCveNodo(); 
		String cveProceso = nodo.getCveProceso();
		Integer idNodo = nodo.getIdNodo();
		BigDecimal version = nodo.getVersion();
		String cveMoneda = null;
		String cveVariable;
		String cveVariableMoneda;
		Integer decimales = 0; // revisar inicializacion
		String formula;
		Integer numColumnas = null;
		Integer ocurrencia;
		Integer ocurrenciaAnterior;
		Integer secuenciaValor;
		Integer secuenciaDocumentoAnterior;
		Integer secuenciaDocumento;
		String tipo;
		String tipoAlfanumerico;
		String tipoDato;
		String tipoDecimal;
		String tipoEntero;
		String tipoFecha;
		String tipoMoneda;
		String tipoImagen;
		String valorAlfanumerico;
		String valorBaseDatos;
		Integer valorEntero;
		BigDecimal valorDecimal;
		Date valorFecha;
		String valorPantalla;
		byte[] valorImagen = null;
		byte[] valorPantallaImagen = null;
		byte[] valorBaseImagen = null;

		
		log.debug("-----------------------------leeInfSeccionVP----------------------------------");
		log.debug("--------> SESSION: " + session.toString());
		log.debug("--------> NODO: " + nodo.toString());
		log.debug("--------> CVE_SECCION: " + cveSeccion);
		
	    // INICIALIZA CÓDIGO DE ERROR, MENSAJE
		EstatusInfSeccionVPTO result = EstatusInfSeccionVPTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();
		
		tipoAlfanumerico = Constants.ALFANUMERICO;
		tipoEntero = Constants.ENTERO;
		tipoDecimal = Constants.DECIMAL;
		tipoFecha = Constants.FECHA;
		tipoMoneda = Constants.MONEDA;
		tipoImagen = Constants.IMAGEN;
		
	    
		// Ejecuta la consulta para cargar las variables del proceso
//		List<Object> variableSeccion = stVariableSeccionRepository.cargaVariablesProcesoMultiple(cveEntidad, cveProceso, 
//				version, cveInstancia, cveNodo, idNodo, cveSeccion);
		List<Object> variableSeccion = new ArrayList<Object>();
		//ERROR---2
		
		variableSeccion = stVariableSeccionRepository.cargaVariablesProcesoSinDocumentos(cveEntidad, cveProceso, 
				version, cveInstancia, cveNodo, idNodo, cveSeccion);
		log.debug("----------- CARGA VARIABLES PROCESO SIN DOCUMENTOS");
		tipo = "VARIABLE";
		
		log.debug("-------------LISTA DE TIPO VARIABLE : " + cveSeccion + " " + variableSeccion.size());
		
		if(variableSeccion.isEmpty()){
			variableSeccion = stVariableSeccionRepository.cargaDocumentosProceso(cveEntidad, cveProceso, 
					version, cveInstancia, cveNodo, idNodo, cveSeccion);
			tipo = "DOCUMENTO";
			log.debug("-------------LISTA DE TIPO DOCUMENTO : " + cveSeccion + " " + variableSeccion.size());
			
		}
		
		log.info("--------> TIPO: " + tipo);
			
		List<VariableProcesoTO>	 var = new ArrayList<VariableProcesoTO>();
		
		log.debug("SE PREPARA PARA TRANSFORMAR");

			variableSeccion.forEach(item -> {
			    Object[] row = (Object[]) item;
		
			    // Construye objetos VariableProcesoTO a partir de los resultados de la consulta
			    //SVM ajuste de imagen
			    VariableProcesoTO itemSelected = VariableProcesoTO.builder()
			        .ocurrencia(row[MagicNumber.ZERO.getValue()] != null ? ((Number) row[MagicNumber.ZERO.getValue()]).intValue() : null)
			        .secuenciaDocumento(row[MagicNumber.ONE.getValue()] != null ? ((Number) row[MagicNumber.ONE.getValue()]).intValue() : null)
			        .orden(row[MagicNumber.TWO.getValue()] != null ? ((Number) row[MagicNumber.TWO.getValue()]).intValue() : null)
			        .cveVariable((String) row[MagicNumber.THREE.getValue()])
			        .tipo((String) row[MagicNumber.FOUR.getValue()])
			        .secuencia(row[MagicNumber.FIVE.getValue()] != null ? ((Number) row[MagicNumber.FIVE.getValue()]).intValue() : null)
			        .alfanumerico((String) row[MagicNumber.SIX.getValue()])
			        .archivoDoc((String) row[MagicNumber.SEVEN.getValue()])
			        .requeridoDoc((String) row[MagicNumber.EIGHT.getValue()])
			        .entero(row[MagicNumber.NINE.getValue()] == null || "".equals(row[MagicNumber.NINE.getValue()])  ? null: ((Number) row[MagicNumber.NINE.getValue()]).intValue())
	        	    .decimal(row[MagicNumber.TEN.getValue()] == null || "".equals(row[MagicNumber.TEN.getValue()]) ? null: ((BigDecimal) row[MagicNumber.TEN.getValue()]))
			        .fecha(row[MagicNumber.ELEVEN.getValue()] == null || "".equals(row[MagicNumber.ELEVEN.getValue()]) ? null: (Date) row[MagicNumber.ELEVEN.getValue()])
			        .formula((String) row[MagicNumber.TWELVE.getValue()])
			        .imagen(row[MagicNumber.THIRTEEN.getValue()] == null || "".equals(row[MagicNumber.THIRTEEN.getValue()]) ? null : (byte[])  Arrays.asList(row).get(MagicNumber.THIRTEEN.getValue()))
			        .build();
			        
			    var.add(itemSelected);
			    log.debug("var: {}", itemSelected);
			});

		
			// Registra la cantidad de resultados obtenidos
			log.debug("-----> CANTIDAD CONSULTA COMPUESTA VP: " + var.size() + " " + var.toString());
			
			if("DOCUMENTO".equals(tipo)) {
				/// CARGA EN TABLA TEMPORAL LOS DATOS DEL PROCESO
				log.debug("-----DOCUMENTO_");
				ocurrenciaAnterior = 0;
				secuenciaDocumentoAnterior = 0;
				numColumnas = null;
				for(VariableProcesoTO varTO: var) {
					log.debug("------------------------------------X--------------------------------");
					ocurrencia = varTO.getOcurrencia();
					secuenciaDocumento = varTO.getSecuenciaDocumento();
					cveVariable = varTO.getCveVariable();
					secuenciaValor = varTO.getSecuencia();
					tipoDato = varTO.getTipo();
					valorEntero = varTO.getEntero();
					valorDecimal = varTO.getDecimal() != null ? varTO.getDecimal() : null;
					valorFecha = varTO.getFecha();
					formula = varTO.getFormula(); 

					log.debug("------> secuenciaDocumentoAnterior: " + secuenciaDocumentoAnterior);
					log.debug("------> secuenciaDocumento: " + secuenciaDocumento);
					
					log.debug("------> secuenciaValor: " + secuenciaValor);
					log.debug("------> cveVariable: " + cveVariable);
					
					 // Asigna valores según el tipo de variable
					switch (cveVariable) {
				        case Constants.VPRO_01_DESCRIPCION_DOCUMENTO:
				            valorAlfanumerico = varTO.getAlfanumerico();
				            break;
				        case Constants.VPRO_01_ARCHIVO_DOCUMENTO:
				        	List<String> archivosDocumentos = iInDocumentoProcesoOCRepository.encuentraNombreDocumentos(cveEntidad, cveProceso, version, cveInstancia, secuenciaDocumento, ocurrencia);
			                // Concatenar los nombres de documentos con un asterisco * entre cada uno
				        	valorAlfanumerico = String.join(" * ", archivosDocumentos);
				        	log.debug("VALOR PANTALLA CONCATENADO: " + valorAlfanumerico);
				        	if (archivosDocumentos.isEmpty() || archivosDocumentos == null || varTO.getArchivoDoc() == null) {
				        	    valorAlfanumerico = " "; // O cualquier valor por defecto que necesites
				        	}
				            break;
				        case Constants.VPRO_01_REQUERIDO_DOCUMENTO:
				            valorAlfanumerico = varTO.getRequeridoDoc();
				            break;
				        default:
				        	valorAlfanumerico = varTO.getAlfanumerico();
			            break;
					}
					

					
					log.debug("------> valorAlfanumerico: " + valorAlfanumerico);
					if(cveVariable.equals(Constants.VPRO_01_SECUENCIA_DOCUMENTO)) {
						valorEntero = secuenciaDocumento;
					} 
					

					log.debug("------> cveSeccion: " + cveSeccion + " ****");
					log.debug("------> ocurrencia: " + ocurrencia);
					log.debug("------> ocurrenciaAnterior: " + ocurrenciaAnterior);
					log.debug("------> secuenciaDocumento: " + secuenciaDocumento);
					log.debug("------> secuenciaDocumentoAnterior: " + secuenciaDocumentoAnterior);
					log.debug("------> valorEntero: " + valorEntero);
					
					if ((ocurrencia != ocurrenciaAnterior && secuenciaDocumento != secuenciaDocumentoAnterior) ||
							(ocurrencia != ocurrenciaAnterior && secuenciaDocumento == secuenciaDocumentoAnterior) ||
							(ocurrencia == 1 && secuenciaDocumento != secuenciaDocumentoAnterior)) {
						log.debug("------> Diferenia de OCURRENCIAS a partir de aqui se genera un nuevo renglon");
						numColumnas = 0;
						log.debug("------> numColumnas: " + numColumnas);

					}
					
			        // Evaluar fórmula si existe y actualizar los valores en consecuencia
					log.debug("------> formula: " + formula);
					if(formula != null && !"".equals(formula)) {
						log.debug("------> formula diferente de null");
						EstatusEvaluaFormulaTO evaluaF = evaluaFormula(session, nodo, tipoDato, formula);
						if(evaluaF.getTipoExcepcion().equals(Constants.ERROR)) {
							result.setMensaje(evaluaF.getMensaje());
							return result;
						}
						valorAlfanumerico = evaluaF.getValorAlfanumerico();
						log.debug("------> valorAlfanumerico despues de evaluar formula: "  + valorAlfanumerico);
						valorEntero = evaluaF.getValorEntero();
						valorDecimal = evaluaF.getValorDecimal();
						valorFecha = evaluaF.getValorFecha();
					}
					
					// Determinar el valor base de datos y valor pantalla según el tipo de dato
					valorBaseDatos = " ";
					log.debug("------> tipoDato: " + tipoDato);
					if(tipoDato.equals(tipoAlfanumerico)) {
			            log.debug("valor alfanumerico cuando tipo de dato es igual a tipo alfanumerico: ");
						valorBaseDatos = valorAlfanumerico;
						log.debug("valor Base de datos = valorAlfanumerico: " + valorBaseDatos);
						
					} else if(tipoDato.equals(tipoDecimal) || tipoDato.equals(tipoMoneda)) {
						log.debug("------> valorDecimal: " + valorDecimal);
						valorBaseDatos = valorDecimal != null ? valorDecimal.toString() : null;
						// OBTIENE LA CLAVE DE LA MONEDA
						cveMoneda = valorAlfanumerico;
						if(cveMoneda == null || " ".equals(cveMoneda)) {
							EstatusAtributosMonedaTO leeAM = areaTrabajoHelper.leeAtributosMoneda(session, nodo, cveVariable, Constants.VARIABLE_PROCESO);
							if(leeAM.getTipoExcepcion().equals(Constants.ERROR)) {
								result.setMensaje(leeAM.getMensaje());
								return result;
							}
							cveMoneda = leeAM.getCveMoneda();
						}
						
					} else if(tipoDato.equals(tipoEntero)) {
						valorBaseDatos = valorEntero != null ? valorEntero.toString() : null;
					} else if(tipoDato.equals(tipoImagen)) {
						valorBaseImagen = valorImagen;
					}
					
					valorPantalla = " ";
					if(tipoDato.equals(tipoAlfanumerico)) {
						log.debug("tipo dato igual a tipo alfanumerico cuando valor pantalla = ' ' ");
						valorPantalla = valorAlfanumerico;
						log.debug("valor pantalla: " + valorPantalla);
					} else if(tipoDato.equals(tipoDecimal) || tipoDato.equals(tipoMoneda)) {
						//ST_VARIBLE_PROCESO
						
						decimales = stVariableProcesoRepository.obtenerDecimales(cveEntidad, cveProceso, version, cveVariable);
						
						decimales = decimales != null ? decimales : 0;
						log.info("------> decimales: " + decimales);
						
						valorPantalla = nodoHelper.truncarDecimales(valorDecimal, decimales);
						
						log.info("***** valor pantalla: " + valorPantalla);
						
					} else if(tipoDato.equals(tipoEntero)) {
						valorPantalla = valorEntero != null ? valorEntero.toString() : null;
					} else if(tipoDato.equals(tipoFecha)) {
						valorPantalla = nodoHelper.formatFecha(valorFecha, "yyyy-MM-dd");
					} else if(tipoDato.equals(tipoImagen)) {
						valorPantallaImagen = valorImagen;
					}
					
					valorBaseDatos = valorBaseDatos == null ? " " : valorBaseDatos;
					valorPantalla = valorPantalla == null ? " " : valorPantalla;
					
					log.debug("-----> numero Columnas antes de guardar: " + numColumnas);
					numColumnas = numColumnas != null ? numColumnas + 1 : null;
					//numColumnas = numColumnas + 1;
					 // svm ajuste de imagen
					datosSeccionOCU.add(
							DatosSeccionOCUTO.builder()
							.ocurrencia(ocurrencia)
							.secuenciaDocumento(secuenciaDocumento)
							.ordenDato(numColumnas)
							.cveDato(cveVariable)
							.secuenciaValor(secuenciaValor)
							.valorBaseDatos(valorBaseDatos)
							.valorPantalla(valorPantalla)
							.valorBaseDatosImagen(valorBaseImagen)
							.valorPantallaImagen(valorPantallaImagen)
							.tipoDato(tipoDato)
							.build()
							);
					
					log.debug("------> OCURRENCIA INF SECCION VP: " + ocurrencia);
					log.debug("------> ORDEN INF SECCION VP     : " + numColumnas);
					log.debug("------> ORDEN DATO VP1           : " + numColumnas);
					log.debug("------> CVE DATO INF SECCION VP  : " + cveVariable);
					log.debug("------> SECUENCIA INF SECCION VP : " + secuenciaValor);
					
					
					if(tipoDato.equals(tipoMoneda)) {
						cveVariableMoneda = cveVariable + Constants.MON;
						valorBaseDatos = " ";
						valorBaseDatos = cveMoneda;
						
						valorPantalla = " ";
						valorPantalla = cveMoneda;
						
						log.debug("-----> numero Columnas antes de guardar Moneda: " + numColumnas);
						numColumnas = numColumnas != null ? numColumnas + 1 : null;
						log.debug("-----> Se rompio despues de la suma: " + numColumnas);

						//numColumnas = numColumnas + 1;
						datosSeccionOCU.add(
								DatosSeccionOCUTO.builder()
								.ocurrencia(ocurrencia)
								.secuenciaDocumento(secuenciaDocumento)
								.ordenDato(numColumnas)
								.cveDato(cveVariableMoneda)
								.secuenciaValor(secuenciaValor)
								.valorBaseDatos(valorBaseDatos)
								.valorPantalla(valorPantalla)
								.valorBaseDatosImagen(valorBaseImagen)
								.valorPantallaImagen(valorPantallaImagen)
								.tipoDato(tipoDato)
								.build()
								);
						
						log.debug("------> OCURRENCIA INF SECCION VP : " + ocurrencia);
						log.debug("------> ORDEN INF SECCION VP      : " + numColumnas);
						log.debug("------> ORDEN DATO VP             : " + numColumnas);
						log.debug("------> CVE DATO INF SECCION VP   : " + cveVariableMoneda);
						log.debug("------> SECUENCIA INF SECCION VP  : " + secuenciaValor);
					}
					
					
					ocurrenciaAnterior = ocurrencia;
					secuenciaDocumentoAnterior = secuenciaDocumento;
					log.debug("------> OCURRENCIA: " + ocurrencia);
					log.debug("------> OCURRENCIA ANTERIOR * : " + ocurrenciaAnterior);
					
					log.debug("------> SECUENCIA DOC: " + secuenciaDocumento);
					log.debug("------> SECUENCIA DOC ANTERIOR * : " + secuenciaDocumentoAnterior);
					
				}
			} else if ("VARIABLE".equals(tipo)) {
				log.debug("-----> CARGA DE VARIABLES");
				
				/// CARGA EN TABLA TEMPORAL LOS DATOS DEL PROCESO
				ocurrenciaAnterior = 0;
				secuenciaDocumentoAnterior = 0;
				numColumnas = 0;
				log.debug("-----> VAR {} {}", var.size(), var.toString());
				
				for(VariableProcesoTO varTO: var) {
					log.debug("--------------------------------------------------------------------");
					ocurrencia = varTO.getOcurrencia();
					cveVariable = varTO.getCveVariable();
					secuenciaValor = varTO.getSecuencia();
					tipoDato = varTO.getTipo();
					valorEntero = varTO.getEntero();
					valorDecimal = varTO.getDecimal() != null ? varTO.getDecimal() : null;
					valorFecha = varTO.getFecha();
					valorImagen = varTO.getImagen();
					formula = varTO.getFormula(); 
					
					numColumnas = varTO.getOrden();
					
					log.debug("------> secuenciaValor: " + secuenciaValor);
					log.debug("------> cveVariable: " + cveVariable);
					
					log.debug("------> cveSeccion: " + cveSeccion + " ****");
					log.debug("------> ocurrencia: " + ocurrencia);
					log.debug("------> ocurrenciaAnterior: " + ocurrenciaAnterior);
					log.debug("------> valorEntero: " + valorEntero);					
				
				    valorAlfanumerico = varTO.getAlfanumerico();

					
					/*if (ocurrencia != null && ocurrenciaAnterior != null && ocurrenciaAnterior != ocurrencia) {
						log.debug("------> Diferenia de ocurrencias");
						numColumnas = 0;
						log.debug("------> numColumnas: " + numColumnas);

					} SVM SE METE EL ORDEN EN numColumnas */
					
			        // Evaluar fórmula si existe y actualizar los valores en consecuencia
					log.debug("------> formula: " + formula);
					if(formula != null) {
						log.debug("------> formula diferente de null");
						EstatusEvaluaFormulaTO evaluaF = evaluaFormula(session, nodo, tipoDato, formula);
						if(evaluaF.getTipoExcepcion().equals(Constants.ERROR)) {
							result.setMensaje(evaluaF.getMensaje());
							return result;
						}
						valorAlfanumerico = evaluaF.getValorAlfanumerico();
						log.debug("------> valorAlfanumerico despues de evaluar formula: "  + valorAlfanumerico);
						valorEntero = evaluaF.getValorEntero();
						valorDecimal = evaluaF.getValorDecimal();
						valorFecha = evaluaF.getValorFecha();
					}
					
					// Determinar el valor base de datos y valor pantalla según el tipo de dato
					valorBaseDatos = " ";
					log.debug("------> tipoDato: " + tipoDato);
					if(tipoDato.equals(tipoAlfanumerico)) {
			            log.debug("valor alfanumerico cuando tipo de dato es igual a tipo alfanumerico: ");
						valorBaseDatos = valorAlfanumerico;
						log.debug("valor Base de datos = valorAlfanumerico: " + valorBaseDatos);
						
					} else if(tipoDato.equals(tipoDecimal) || tipoDato.equals(tipoMoneda)) {
						log.debug("------> valorDecimal: " + valorDecimal);
						valorBaseDatos = valorDecimal != null ? valorDecimal.toString() : null;
						// OBTIENE LA CLAVE DE LA MONEDA
						cveMoneda = valorAlfanumerico;
						if(cveMoneda == null || " ".equals(cveMoneda)) {
							EstatusAtributosMonedaTO leeAM = areaTrabajoHelper.leeAtributosMoneda(session, nodo, cveVariable, Constants.VARIABLE_PROCESO);
							if(leeAM.getTipoExcepcion().equals(Constants.ERROR)) {
								result.setMensaje(leeAM.getMensaje());
								return result;
							}
							cveMoneda = leeAM.getCveMoneda();
						}
						
					} else if(tipoDato.equals(tipoEntero)) {
						valorBaseDatos = valorEntero != null ? valorEntero.toString() : null;
					} else if(tipoDato.equals(tipoFecha)) {
						log.debug("------> valorFecha: " + valorFecha);
						valorBaseDatos = nodoHelper.formatFecha(valorFecha, "yyyy-MM-dd");
						log.debug("------> valorBaseDatos: " + valorBaseDatos);
					} else if(tipoDato.equals(tipoFecha)) {
						
					} else if(tipoDato.equals(tipoImagen)) {
						valorBaseImagen = valorImagen;
					}
					
					valorPantalla = " ";
					if(tipoDato.equals(tipoAlfanumerico)) {
						log.debug("tipo dato igual a tipo alfanumerico cuando valor pantalla = ' ' ");
						valorPantalla = valorAlfanumerico;
						log.debug("valor pantalla: " + valorPantalla);
					} else if(tipoDato.equals(tipoDecimal) || tipoDato.equals(tipoMoneda)) {
						//ST_VARIBLE_PROCESO
						
						decimales = stVariableProcesoRepository.obtenerDecimales(cveEntidad, cveProceso, version, cveVariable);
						
						decimales = decimales != null ? decimales : 0;
						log.info("------> decimales: " + decimales);
						
						valorPantalla = nodoHelper.truncarDecimales(valorDecimal, decimales);
						
						log.info("***** valor pantalla: " + valorPantalla);
					} else if(tipoDato.equals(tipoEntero)) {
						valorPantalla = valorEntero != null ? valorEntero.toString() : null;
					} else if(tipoDato.equals(tipoFecha)) {
						valorPantalla = nodoHelper.formatFecha(valorFecha, "yyyy-MM-dd");
					} else if(tipoDato.equals(tipoImagen)) {
						valorPantallaImagen = valorImagen;
					}
					
					valorBaseDatos = valorBaseDatos == null ? " " : valorBaseDatos;
					valorPantalla = valorPantalla == null ? " " : valorPantalla;
					
					log.debug("-----> numero Columnas antes de guardar: " + numColumnas);
					//numColumnas = numColumnas != null ? numColumnas + 1 : null; SVM SE AJUSTE POR EL ORDEN DE LA TABLA
					//numColumnas = numColumnas + 1;
					datosSeccionOCU.add(
							DatosSeccionOCUTO.builder()
							.ocurrencia(ocurrencia)
							.ordenDato(numColumnas)
							.cveDato(cveVariable)
							.secuenciaValor(secuenciaValor)
							.valorBaseDatos(valorBaseDatos)
							.valorPantalla(valorPantalla)
							.valorBaseDatosImagen(valorBaseImagen)
							.valorPantallaImagen(valorPantallaImagen)
							.tipoDato(tipoDato)
							.build()
							);
					
					log.debug("------> OCURRENCIA INF SECCION VP: " + ocurrencia);
					log.debug("------> ORDEN INF SECCION VP     : " + numColumnas);
					log.debug("------> ORDEN DATO VP1           : " + numColumnas);
					log.debug("------> CVE DATO INF SECCION VP  : " + cveVariable);
					log.debug("------> SECUENCIA INF SECCION VP : " + secuenciaValor);
					
					
					if(tipoDato.equals(tipoMoneda)) {
						cveVariableMoneda = cveVariable + Constants.MON;
						valorBaseDatos = " ";
						valorBaseDatos = cveMoneda;
						
						valorPantalla = " ";
						valorPantalla = cveMoneda;
						
						log.debug("-----> numero Columnas antes de guardar Moneda: " + numColumnas);
						numColumnas = numColumnas != null ? numColumnas + 1 : null;
						log.debug("-----> Se rompio despues de la suma: " + numColumnas);

						//numColumnas = numColumnas + 1;
						datosSeccionOCU.add(
								DatosSeccionOCUTO.builder()
								.ocurrencia(ocurrencia)
								.ordenDato(numColumnas)
								.cveDato(cveVariableMoneda)
								.secuenciaValor(secuenciaValor)
								.valorBaseDatos(valorBaseDatos)
								.valorPantalla(valorPantalla)
								.valorBaseDatosImagen(valorBaseImagen)
								.valorPantallaImagen(valorPantallaImagen)
								.tipoDato(tipoDato)
								.build()
								);
						
						log.debug("------> OCURRENCIA INF SECCION VP : " + ocurrencia);
						log.debug("------> ORDEN INF SECCION VP      : " + numColumnas);
						log.debug("------> ORDEN DATO VP             : " + numColumnas);
						log.debug("------> CVE DATO INF SECCION VP   : " + cveVariableMoneda);
						log.debug("------> SECUENCIA INF SECCION VP  : " + secuenciaValor);
					}
					ocurrenciaAnterior = ocurrencia;
					
				}
			}
			
		
		result.setTipo(tipo);
		log.debug("--------> DATOS SECCION: " + datosSeccionOCU.size());
		result.setDatosSeccionOCU(datosSeccionOCU);
		log.debug("############### RETURN SP_LEE_INF_SECCION_VP: ");
		return result;
	}	
}
