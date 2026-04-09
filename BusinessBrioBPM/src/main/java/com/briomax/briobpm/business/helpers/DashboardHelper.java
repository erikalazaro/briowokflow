package com.briomax.briobpm.business.helpers;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.business.helpers.base.IDashboardHelper;
import com.briomax.briobpm.business.helpers.base.INodoHelper;
import com.briomax.briobpm.common.core.Constants;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.DatoDashboardBW;
import com.briomax.briobpm.persistence.repository.IDashboardBWRepository;
import com.briomax.briobpm.persistence.repository.IDatoDashboardBWRepository;
import com.briomax.briobpm.persistence.repository.ISeccionDashboardBWRepository;
import com.briomax.briobpm.transferobjects.VariableCadenaTO;
import com.briomax.briobpm.transferobjects.app.Datos;
import com.briomax.briobpm.transferobjects.app.GraficaTO;
import com.briomax.briobpm.transferobjects.app.Grafico;
import com.briomax.briobpm.transferobjects.app.Seccion;
import com.briomax.briobpm.transferobjects.in.DatoDashboardTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.EstatusVariableCadenaTO;
import com.briomax.briobpm.transferobjects.in.EstatusVariablesTO;
import com.briomax.briobpm.transferobjects.in.InNodoProcesoTO;
import com.briomax.briobpm.transferobjects.in.SeccionDashboardTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class DashboardHelper implements IDashboardHelper{
	
	@Autowired
	private IDashboardBWRepository dashboardBWRepository;
	
	@Autowired
	private ISeccionDashboardBWRepository seccionDashboardBWRepository;

	@Autowired
	private IDatoDashboardBWRepository datoDashboardBWRepository;
	
	@Autowired
	private INodoHelper nodoHelper;

    @PersistenceContext
    EntityManager entityManager;
	
	//FN_LEE_CATEGORIA_ACTIVIDAD
		@Override
		public Integer fnLeeCategoriaActividad(String estadoActividad, InNodoProcesoTO session, Date fechaEvaluacion, 
				String categoriaConsultar) throws BrioBPMException {
			
			
			Date fechaCreacion = session.getFechaCreacion();
			Date fechaEstadoActual = session.getFechaEstadoActual();
			Date fechaLimite = session.getFechaLimite();
			
			int  categoria;
			Date fechaEvaluacionAux 	 =  null;
			Date fechaCreacionAux 		 =  null;
			Date fechaEstadoActualAux =  null;
			String categoriaPrevia 		  	 = "PREVIA";
			String categoriaNueva 		  	 = "NUEVA";
			String categoriaAtendida 	  	 = "ATENDIDA";
			String categoriaCancelada 	 = "CANCELADA";
			String categoriaEnTiempo 	 = "EN_TIEMPO";
			String categoriaRetrasada 	 = "RETRASADA";
			String categoriaVencida 	 	 = "VENCIDA";
			String estadoRegistro 		 	 = "REGISTRO";
			String estadoTerminada 	 	 = "TERMINADA";
			String estadoCancelada 	 	 = "CANCELADA";
			
			// Ajustar la fecha a medianoche usando Calendar
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(fechaEvaluacion);
	        calendar.set(Calendar.HOUR_OF_DAY, 0);
	        calendar.set(Calendar.MINUTE, 0);
	        calendar.set(Calendar.SECOND, 0);
	        calendar.set(Calendar.MILLISECOND, 0);
	        fechaEvaluacionAux = calendar.getTime();
	        
	        log.info("fechaEvaluacionAux: {}", fechaEvaluacionAux);
	        
	        calendar.setTime(fechaCreacion);
	        calendar.set(Calendar.HOUR_OF_DAY, 0);
	        calendar.set(Calendar.MINUTE, 0);
	        calendar.set(Calendar.SECOND, 0);
	        calendar.set(Calendar.MILLISECOND, 0);
	        fechaCreacionAux = calendar.getTime();
	        
	        log.info("fechaCreacionAux: {}", fechaCreacionAux);
	        
	        calendar.setTime(fechaEstadoActual);
	        calendar.set(Calendar.HOUR_OF_DAY, 0);
	        calendar.set(Calendar.MINUTE, 0);
	        calendar.set(Calendar.SECOND, 0);
	        calendar.set(Calendar.MILLISECOND, 0);
	        fechaEstadoActualAux = calendar.getTime();
	        
	        log.info("fechaEstadoActualAux: {}", fechaEstadoActualAux);
	        
	        
			
			categoria = 0;	
			
			if(categoriaConsultar.equals(categoriaPrevia)) {//1	
				if(fechaCreacionAux.before(fechaEvaluacionAux)) {
							categoria = 1;
					}
			}else if(categoriaConsultar.equals(categoriaNueva)) {//2
				if(fechaCreacionAux.compareTo(fechaEvaluacionAux) == 0) {
							categoria = 1;				
				}
			}else if(categoriaConsultar.equals(categoriaAtendida)) {//3
				if(estadoActividad.equals(estadoTerminada)) {
							categoria = 1;				
				}
			}else if(categoriaConsultar.equals(categoriaCancelada)) {//4
				if(estadoActividad.equals( estadoCancelada)) {	
							categoria = 1;
				}
			}else if(categoriaConsultar.equals(categoriaEnTiempo)) {//5
				if(fechaLimite == null) {
							categoria=1;
							
				}else if(estadoActividad.equals(estadoRegistro)) {
					if(fechaEvaluacion.compareTo(fechaLimite) <= 0){
						categoria = 1;
						
					}else if(estadoActividad.equals(estadoTerminada)) {
						if(fechaEstadoActual.after(fechaLimite)) {
							categoria =1;
						}
					}
				}
			}else if(categoriaConsultar.equals(categoriaRetrasada)) {//6
				if(fechaLimite != null) {
					if(estadoActividad.equals(estadoRegistro)) {
						if(fechaEvaluacion.after(fechaLimite)) {
							categoria = 1;
							
						}else if(fechaEstadoActual.after(fechaLimite)) {
							categoria = 1;
						}
					}
				}
			}else if(categoriaConsultar.equals(categoriaVencida)) {//7
				if((fechaLimite != null) && (fechaLimite.before(fechaEvaluacion)) && (estadoActividad.equals(estadoRegistro))) {
						categoria = 1;
				}
			}
			 log.info("categoria: {}", categoria);
			return categoria;
		}

		
		// SP_LEE_LISTA_DASHBOARD
		@Override
		public DAORet<HashMap<String, String>, RetMsg> leeListaDashboard(DatosAutenticacionTO session, String destino, String ubicacionLista) {

		    // INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		    // Crea un objeto RetMsg para manejar el mensaje de respuesta.
		    RetMsg msg = RetMsg.builder()
		            .message("") // Mensaje inicial vacío.
		            .status(Constants.OK) // Estado de éxito.
		            .build();

		    // CONSTANTES PARA DESTINOS Y UBICACIONES
		    // Definición de constantes que representan los destinos y ubicaciones válidas.
		    String destinoWEB = "WEB";
		    String destinoAPP = "APP";
		    String ubicacionInicio = "INICIO";
		    String ubicacionMenu = "MENU";
		    String ubicacionInicioMenu = "INICIO-MENU";

		    // INICIALIZA EL HASHMAP PARA ALMACENAR RESULTADOS
		    // Se utiliza para almacenar pares clave-valor donde la clave es cveDashboard y el valor es el título traducido.
		    HashMap<String, String> dashboardData = new HashMap<>();

		    // REALIZA LA CONSULTA A LA BASE DE DATOS
		    // Llama al repositorio para obtener la lista de dashboards basada en los parámetros proporcionados.
		    List<Object[]> listaDashboard = dashboardBWRepository.encuentraDashboards(
		            session.getCveEntidad(), Constants.SI, session.getCveUsuario(), destino,
		            destinoWEB, destinoAPP, ubicacionLista, ubicacionInicio, ubicacionMenu, ubicacionInicioMenu);
		    
		    log.info("Consulta realizada: Número de dashboards encontrados = {}", listaDashboard.size()); // Registro del número de dashboards encontrados.

		    // PROCESA LOS RESULTADOS OBTENIDOS
		    // Recorre cada elemento en la lista de dashboards obtenida.
		    for (Object[] dashboard : listaDashboard) {
		        String cveDashboard = (String) dashboard[0]; // Captura el código del dashboard.
		        String titulo = (String) dashboard[1];       // Captura el título del dashboard.

		        // APLICA LA TRADUCCIÓN AL TÍTULO
		        // Utiliza el helper para traducir el título según el idioma del usuario.
		        titulo = nodoHelper.traducirEtiqueta(session, titulo);
		        
		        // AGREGA ENTRADA AL HASHMAP
		        // Se añade el par clave-valor al HashMap.
		        dashboardData.put(cveDashboard, titulo);
		        log.info("Agregado al HashMap: CVE_DASHBOARD = {}, TÍTULO = {}", cveDashboard, titulo); // Registro de cada entrada añadida al HashMap.
		    }

		    // ORDENAMIENTO DEL HASHMAP POR TÍTULO
		    // Ordena el HashMap por el valor (título) de manera alfabética y crea un nuevo HashMap.
		    HashMap<String, String> sortedDashboardData = dashboardData.entrySet().stream()
		            .sorted((entry1, entry2) -> entry1.getValue().compareToIgnoreCase(entry2.getValue())) // Comparador para el orden alfabético.
		            .collect(LinkedHashMap::new, (map, entry) -> map.put(entry.getKey(), entry.getValue()), LinkedHashMap::putAll); // Recolecta en un LinkedHashMap.

		    log.info("HashMap ordenado por títulos. Número de elementos en el HashMap ordenado = {}", sortedDashboardData.size()); // Registro del tamaño del HashMap ordenado.

		    // RETORNA LOS RESULTADOS
		    // Devuelve un DAORet que contiene el HashMap ordenado y el mensaje de estado.
		    return new DAORet<>(sortedDashboardData, msg);
		}
		

		
		// SP_LEE_SECCIONES_DASHBOARD
		@Override
		public RetMsg leeSeccionesDashboard (DatosAutenticacionTO session, String cveDashboard, List<SeccionDashboardTO> seccionDashboard, Boolean isSeccion, int cveSeccion) {

			// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
			RetMsg result = RetMsg.builder()
					.message("")
					.status(Constants.OK)
					.build();

			List<Object[]> seccionesDashboard = new ArrayList<>();
			
			//LECTURA DE LAS SECCIONES, SUB_SECCIONES Y SUB_SUB_SECCIONES DEL DASHBOARD
			if (isSeccion) {
				seccionesDashboard = seccionDashboardBWRepository.obtieneDatosPorSeccion(session.getCveEntidad(), cveDashboard, cveSeccion);
			} else {
				seccionesDashboard = seccionDashboardBWRepository.obtieneSecciones(session.getCveEntidad(), cveDashboard);
			}
			log.info("seccionDashboard Objeto: {}" , seccionesDashboard.size());
			
			for (Object[] seccion : seccionesDashboard) {
				// Procesamiento de SECCION_DASHBOARD_BW
			    BigDecimal ordenSeccionBD = (BigDecimal) seccion[0];
			    Integer ordenSeccion = ordenSeccionBD != null ? ordenSeccionBD.intValue() : null;

			    BigDecimal secuenciaSeccionBD = (BigDecimal) seccion[1];
			    Integer secuenciaSeccion = secuenciaSeccionBD != null ? secuenciaSeccionBD.intValue() : null;

			    String tituloSeccion = (String) seccion[2];

			    // Procesamiento de SUB_SECCION_DASHBOARD_BW
			    BigDecimal ordenSubSeccionBD = (BigDecimal) seccion[3];
			    Integer ordenSubSeccion = ordenSubSeccionBD != null ? ordenSubSeccionBD.intValue() : null;

			    BigDecimal secuenciaSubSeccionBD = (BigDecimal) seccion[4];
			    Integer secuenciaSubSeccion = secuenciaSubSeccionBD != null ? secuenciaSubSeccionBD.intValue() : null;

			    String tituloSubSeccion = (String) seccion[5];

			    // Procesamiento de SUB_SUB_SECCION_DASHBOARD_BW
			    BigDecimal ordenSubSubSeccionBD = (BigDecimal) seccion[6];
			    Integer ordenSubSubSeccion = ordenSubSubSeccionBD != null ? ordenSubSubSeccionBD.intValue() : null;

			    BigDecimal secuenciaSubSubSeccionBD = (BigDecimal) seccion[7];
			    Integer secuenciaSubSubSeccion = secuenciaSubSubSeccionBD != null ? secuenciaSubSubSeccionBD.intValue() : null;

			    String tituloSubSubSeccion = (String) seccion[8];
			    String tipoPresentacion = (String) seccion[9];
			    String etiquetaEjeX = (String) seccion[10];
			    String etiquetaEjeY1 = (String) seccion[11];
			    String etiquetaEjeY2 = (String) seccion[12];
			    String tituloAcotaciones = (String) seccion[13];

			    // Procesamiento de SERIE_DASHBOARD_BW
			    BigDecimal ordenSerieBD = (BigDecimal) seccion[14];
			    Integer ordenSerie = ordenSerieBD != null ? ordenSerieBD.intValue() : null;

			    BigDecimal numeroSerieBD = (BigDecimal) seccion[15];
			    Integer numeroSerie = numeroSerieBD != null ? numeroSerieBD.intValue() : null;

			    String tituloSerie = (String) seccion[16];

			    BigDecimal escalaBD = (BigDecimal) seccion[17];
			    Integer escala = escalaBD != null ? escalaBD.intValue() : null;
				
			    // Construyendo el objeto SeccionDashboardTO con el builder
			    SeccionDashboardTO seccionDashboardTO = SeccionDashboardTO.builder()
			    		.cveDashboard(cveDashboard)
			    		.secuenciaSeccion(secuenciaSeccion)
			    		.ordenSeccion(ordenSeccion)
			            .tituloSeccion(tituloSeccion)
			            
			            .secuenciaSubSeccion(secuenciaSubSeccion)
			            .ordenSubSeccion(ordenSubSeccion)
			            .tituloSubSeccion(tituloSubSeccion)
			            
			            .secuenciaSubSubSeccion(secuenciaSubSubSeccion)
			            .ordenSubSubSeccion(ordenSubSubSeccion)
			            .tituloSubSubSeccion(tituloSubSubSeccion)
			            
			            .tipoPresentacion(tipoPresentacion)
			            .etiquetaEjeX(etiquetaEjeX)
			            .etiquetaEjeY1(etiquetaEjeY1)
			            .etiquetaEjeY2(etiquetaEjeY2)
			            .tituloAcotaciones(tituloAcotaciones)
			            
			            .ordenSerie(ordenSerie)
			            .numeroSerie(numeroSerie)
			            .tituloSerie(tituloSerie)
			            .escala(escala)			            
			            .build();
			    seccionDashboard.add(seccionDashboardTO);
			    
			}
			
			log.info("TERMINA leeSeccionesDashboard : " + result.getStatus());
			return result;
		}

		// SP_OBTEN_FECHA_PRIMER_DIA_MES
		@Override
		public DAORet<Date, RetMsg>  obtenFechaPrimerDiaMes (DatosAutenticacionTO session, Integer mes, Integer anio) throws ParseException {
			
			String mesS;
			String fecha;
			Date fechaSalida;
			// INICIALIZA CÓDIGO DE ERROR, MENSAJE
			RetMsg msg = RetMsg.builder()
					.message("")
					.status(Constants.OK)
					.build();
			
			// DETERMINA LA FECHA DEL PRIMER DIA DEL MES Y AÑO DE ENTRADA
			if(mes < 10) {
				mesS = "0" + mes; 
			} else {
				mesS = mes.toString();
			}
			
			fecha = anio + mesS + "01";
			
			// Define el formato de la fecha y conviértelo a tipo Date
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		    fechaSalida = dateFormat.parse(fecha);
		    
		    
		 // Retorna un DAORet con la fecha y el mensaje
		 return new DAORet<>(fechaSalida, msg);
		}
			
		// SP_OBTEN_FECHA_ULTIMO_DIA_MES
		@Override
		public DAORet<Date, RetMsg>  obtenFechaUltimoDiaMes (DatosAutenticacionTO session, Integer mes, Integer anio) throws ParseException {
			
			String dia = "";
			String mesS;
			String fecha;
			Date fechaSalida;
			// INICIALIZA CÓDIGO DE ERROR, MENSAJE
			RetMsg msg = RetMsg.builder()
					.message("")
					.status(Constants.OK)
					.build();
			
			// DETERMINA LA FECHA DEL PRIMER DIA DEL MES Y AÑO DE ENTRADA
			if (Arrays.asList(1, 3, 5, 7, 8, 10, 12).contains(mes)) {
				dia = "31"; 
			} else {
				if (Arrays.asList(4, 6, 9, 11).contains(mes)) {
					dia = "30"; 
				} else {
					if(2 == mes) {
						if(anio%4 == 0) {
							dia = "29"; 
						} else {
							dia = "28"; 
						}
					}
				}
			}
			
			if(mes < 10) {
				mesS = "0" + mes; 
			} else {
				mesS = mes.toString();
			}
			
			fecha = anio + mesS + dia;
			
			// Define el formato de la fecha y conviértelo a tipo Date
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		    fechaSalida = dateFormat.parse(fecha);
		    
		    
		 // Retorna un DAORet con la fecha y el mensaje
		 return new DAORet<>(fechaSalida, msg);
		}
		
		
		// SP_LEE_VALOR_VARIABLE_ESPECIAL
		@Override
		public EstatusVariablesTO leeValorVariableEspecial(DatosAutenticacionTO session, String cveVariable) throws ParseException {
			
			String fecha;
			// INICIALIZA 'CONSTANTES'
			String tipoFecha = Constants.FECHA;
			
			// INICIALIZA CÓDIGO DE ERROR, MENSAJE
			EstatusVariablesTO result = EstatusVariablesTO.builder()
					.mensaje("")
					.tipoExcepcion(Constants.OK)
					.build();
			String idProceso = "LEE_VALOR_VPN";
			
			String tipoRestaMes = "MESES";
			String tipoRestaSemana = "SEMANAS";
			
			// INICIALIZA LAS VARIABLES DE SALIDA
			String tipo = tipoFecha;
			Date valorFecha = null;
			
			// OBTIENE EL TIPO DE DATO DE LA VARIABLE. CONTEMPLAR QUE SE PUEDEN IR AGREGANDO VARIABLES SIMILARES
			// Lista de variables que queremos excluir
			List<String> variablesExcluidas = Arrays.asList(
			    "FECHA_ACTUAL",     
			    "FECHA_INI_MES_ACTUAL", "FECHA_FIN_MES_ACTUAL",
			    "FECHA_INI_MES_ANTERIOR", "FECHA_FIN_MES_ANTERIOR",
			    "FECHA_INI_ANIO_ACTUAL", "FECHA_FIN_ANIO_ACTUAL",
			    "FECHA_INI_ANIO_ANTERIOR", "FECHA_FIN_ANIO_ANTERIOR"
			);

			// Verificar que cveVariable no está en la lista exacta ni coincide con los patrones
			if (!variablesExcluidas.contains(cveVariable) &&
			    !cveVariable.startsWith("FECHA_ACTUAL_MENOS_") &&
			    !cveVariable.startsWith("FEC_INI_SEM_FEC_ACT_MENOS_") &&
			    !cveVariable.startsWith("FEC_FIN_SEM_FEC_ACT_MENOS_") &&
			    !cveVariable.startsWith("FEC_INI_MES_FEC_ACT_MENOS_") &&
			    !cveVariable.startsWith("FEC_FIN_MES_FEC_ACT_MENOS_")) {
				
				// AQUI HAY QUE MANDAR A LA RUTINA QUE OBTIENE EL MENSAJE DE ERROR
				result.setTipoExcepcion(Constants.ERROR);
				return result;
			}

			// OBTIENE EL VALOR DE LA VARIABLE. BUSCAR UNA CODIFICACIÓN ADECUADA, ENTRE OTRAS COSAS CONSIDERAR QUE VAN A APARECER MÁS VARIABLES
			
			// Obtener la fecha actual en el formato yyyyMMdd
	        LocalDate fechaActual = LocalDate.now();
	        
	        // Obtener la fecha actual
            Date fechaActualDate = new Date();
            
	        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyyMMdd");
	        String chFecha = fechaActual.format(formato);
	        // Extraer el mes y el año como números enteros
	        Integer mes = Integer.parseInt(chFecha.substring(4, 6));  // Mes en formato numérico (2 dígitos)
	        Integer anio = Integer.parseInt(chFecha.substring(0, 4)); // Año en formato numérico (4 dígitos)
	        DAORet<Date, RetMsg> fechaPrimerDiaMes = null;
	        DAORet<Date, RetMsg> fechaUltimoDiaMes = null;
	        Integer iniElemento;
	        Integer finElemento;
	        Integer lonElemento;
	        String sustraendo;
	        Integer sustraendoI;
	        String tipoResta;
	        LocalDate fechaActualMenosMeses;
	        
			switch(cveVariable) {
			case "FECHA_ACTUAL":
				log.info("------FECHA ACUTAL");
				// Formatear la fecha actual al formato "yyyyMMdd" (equivalente al estilo 112 en SQL)
	            SimpleDateFormat formatoSQL = new SimpleDateFormat("yyyyMMdd");
	            chFecha = formatoSQL.format(fechaActualDate);
	            
	            // Convertir la cadena formateada nuevamente a un objeto Date
	            valorFecha = formatoSQL.parse(chFecha);

	            log.info("------FECHA ACUTAL: {}" , valorFecha);
				break;
				
			case "FECHA_INI_MES_ACTUAL":
		        fechaPrimerDiaMes = obtenFechaPrimerDiaMes(session, mes, anio);
		        valorFecha = fechaPrimerDiaMes.getContent();
		        
		        result.setMensaje(fechaPrimerDiaMes.getMeta().getMessage());
		        result.setTipoExcepcion(fechaPrimerDiaMes.getMeta().getStatus());
		        break;
				
			case "FECHA_FIN_MES_ACTUAL":
				//log.error("FECHA_FIN_MES_ACTUAL: {} {}", mes, anio);
		        fechaUltimoDiaMes = obtenFechaUltimoDiaMes(session, mes, anio);
		        valorFecha = fechaUltimoDiaMes.getContent();
		        
		        result.setMensaje(fechaUltimoDiaMes.getMeta().getMessage());
		        result.setTipoExcepcion(fechaUltimoDiaMes.getMeta().getStatus());
		        break;
		        
			case "FECHA_INI_MES_ANTERIOR":
				//log.info("FECHA_INI_MES_ANTERIOR: {} {}", mes, anio);
				if(1 == mes) {
					anio = anio - 1;
					mes = 12;
				} else {
					mes = mes - 1;
				}
				
				fechaPrimerDiaMes = obtenFechaPrimerDiaMes(session, mes, anio);
				//log.info("FECHA_INI_MES_ANTERIOR: {} {}", mes, anio);
				valorFecha = fechaPrimerDiaMes.getContent();
		        
				result.setMensaje(fechaPrimerDiaMes.getMeta().getMessage());
		        result.setTipoExcepcion(fechaPrimerDiaMes.getMeta().getStatus()); 
		        break;
				
			case "FECHA_FIN_MES_ANTERIOR":
				
				//log.info("FECHA_FIN_MES_ANTERIOR: {} {}", mes, anio);
				
				if(1 == mes) {
					anio = anio - 1;
					mes = 12;
				} else {
					mes = mes - 1;
				}
				
				fechaUltimoDiaMes = obtenFechaUltimoDiaMes(session, mes, anio);
		        valorFecha = fechaUltimoDiaMes.getContent();
		        
		        result.setMensaje(fechaUltimoDiaMes.getMeta().getMessage());
		        result.setTipoExcepcion(fechaUltimoDiaMes.getMeta().getStatus());
		        break;
		        
			case "FECHA_INI_ANIO_ACTUAL":
				mes = 1;
				
				fechaPrimerDiaMes = obtenFechaUltimoDiaMes(session, mes, anio);
		        valorFecha = fechaPrimerDiaMes.getContent();
		        
		        result.setMensaje(fechaPrimerDiaMes.getMeta().getMessage());
		        result.setTipoExcepcion(fechaPrimerDiaMes.getMeta().getStatus());
		        break;
				
			case "FECHA_FIN_ANIO_ACTUAL":
				mes = 12;
				
				fechaUltimoDiaMes = obtenFechaUltimoDiaMes(session, mes, anio);
		        valorFecha = fechaUltimoDiaMes.getContent();
		        
		        result.setMensaje(fechaUltimoDiaMes.getMeta().getMessage());
		        result.setTipoExcepcion(fechaUltimoDiaMes.getMeta().getStatus());
		        break;
				
			case "FECHA_INI_ANIO_ANTERIOR":
				mes = 1;
				anio = anio - 1;
				
				fechaPrimerDiaMes = obtenFechaUltimoDiaMes(session, mes, anio);
		        valorFecha = fechaPrimerDiaMes.getContent();
		        
		        result.setMensaje(fechaPrimerDiaMes.getMeta().getMessage());
		        result.setTipoExcepcion(fechaPrimerDiaMes.getMeta().getStatus());
		        break;
				
			case "FECHA_FIN_ANIO_ANTERIOR":
				mes = 12;
				anio = anio - 1;
				
				fechaUltimoDiaMes = obtenFechaUltimoDiaMes(session, mes, anio);
		        valorFecha = fechaUltimoDiaMes.getContent();
		        
		        result.setMensaje(fechaUltimoDiaMes.getMeta().getMessage());
		        result.setTipoExcepcion(fechaUltimoDiaMes.getMeta().getStatus());
		        break;
		        
	        default:
	        	
        		log.info(cveVariable);

	        	if (cveVariable.startsWith("FECHA_ACTUAL_MENOS_")) {
	        		iniElemento = 19;
	        	    finElemento = cveVariable.indexOf('_', iniElemento); // Encuentra el próximo '_'
	        	    lonElemento = finElemento - iniElemento;
	        	    
	        	    // Obtiene el número de meses/semanas a restar
	        	    sustraendo = cveVariable.substring(iniElemento, finElemento);
	        	    sustraendoI = Integer.parseInt(sustraendo) * -1; // Convierte a entero y lo hace negativo
	        	    
	        	    // Determina el tipo de resta (MES o SEMANA)
	        	    tipoResta = cveVariable.substring(finElemento + 1);
	        	    
	        	    // Calcula la fecha según el tipo de resta
	        	    if (tipoRestaMes.equals(tipoResta)) {
	        	        // Restar meses
	        	        fechaActualMenosMeses = LocalDate.now().plusMonths(sustraendoI); // `sustraendoI` ya es negativo
	        	        valorFecha = Date.from(fechaActualMenosMeses.atStartOfDay(ZoneId.systemDefault()).toInstant());
	        	    } else if (tipoRestaSemana.equals(tipoResta)) {
	        	        // Restar semanas
	        	        LocalDate fechaActualMenosSemanas = LocalDate.now().plusWeeks(sustraendoI);
	        	        valorFecha = Date.from(fechaActualMenosSemanas.atStartOfDay(ZoneId.systemDefault()).toInstant());
	        	    } else {
	        	        // Caso no válido
	        	        result.setTipoExcepcion(Constants.ERROR);
	        	        result.setMensaje("Tipo de resta no válido en cveVariable: " + tipoResta);
	        	        return result;
	        	    }

	        	    
	        	    break;
	        	    
	        	} else if (cveVariable.startsWith("FEC_INI_SEM_FEC_ACT_MENOS_")) {
	        		// Extraer el número de semanas a restar
			        iniElemento = 26;
			        finElemento = cveVariable.indexOf('_', iniElemento);
			        lonElemento = finElemento - iniElemento;
			        String chSustraendo = cveVariable.substring(iniElemento, iniElemento + lonElemento);

			        // Convertir a entero
			        int iSustraendo = Integer.parseInt(chSustraendo);

			        // Calcular la fecha base
			        LocalDate fechaL = LocalDate.now().minusWeeks(iSustraendo);

			        // Extraer el día de inicio de la semana
			        iniElemento = finElemento + 1;
			        iniElemento = cveVariable.indexOf('I', iniElemento) + 1;
			        finElemento = cveVariable.length();
			        lonElemento = finElemento - iniElemento;
			        String chInicioSemana = cveVariable.substring(iniElemento, iniElemento + lonElemento);

			        // Mapeo de días de la semana
			        int diaBuscar;

			        switch (chInicioSemana) {
			        case "D":
			            diaBuscar = 7;
			            break;
			        case "L":
			            diaBuscar = 1;
			            break;
			        case "M":
			            diaBuscar = 2;
			            break;
			        case "W":
			            diaBuscar = 3;
			            break;
			        case "J":
			            diaBuscar = 4;
			            break;
			        case "V":
			            diaBuscar = 5;
			            break;
			        case "S":
			            diaBuscar = 6;
			            break;
			        default:
			            throw new IllegalArgumentException("Día de la semana inválido: " + chInicioSemana);
			        }

			        // Normalización del ciclo de búsqueda
			        String chFinBusqueda = "NO";
			        while ("NO".equals(chFinBusqueda)) {
			            int diaSemana = fechaL.getDayOfWeek().getValue(); // Obtener día de la semana

			            if (diaSemana == diaBuscar) {
			                chFinBusqueda = "SI";
			            } else {
			                fechaL = fechaL.minusDays(1);
			            }
			        }

			        // Resultado final
			        valorFecha = java.util.Date.from(fechaL.atStartOfDay(ZoneId.systemDefault()).toInstant());
			        
	        	} else if (cveVariable.startsWith("FEC_FIN_SEM_FEC_ACT_MENOS_")) {
	        		// Extraer el número de semanas a restar
			        iniElemento = 26;
			        finElemento = cveVariable.indexOf('_', iniElemento);
			        lonElemento = finElemento - iniElemento;
			        String chSustraendo = cveVariable.substring(iniElemento, iniElemento + lonElemento);

			        // Convertir a entero
			        int iSustraendo = Integer.parseInt(chSustraendo);

			        // Calcular la fecha base
			        LocalDate fechaL = LocalDate.now().minusWeeks(iSustraendo);

			        // Extraer el día de inicio de la semana
			        iniElemento = finElemento + 1;
			        iniElemento = cveVariable.indexOf('I', iniElemento) + 1;
			        finElemento = cveVariable.length();
			        lonElemento = finElemento - iniElemento;
			        String chInicioSemana = cveVariable.substring(iniElemento, iniElemento + lonElemento);

			        // Mapeo de días de la semana
			        int diaBuscar;

			        switch (chInicioSemana) {
			        case "D":
			            diaBuscar = 7;
			            break;
			        case "L":
			            diaBuscar = 1;
			            break;
			        case "M":
			            diaBuscar = 2;
			            break;
			        case "W":
			            diaBuscar = 3;
			            break;
			        case "J":
			            diaBuscar = 4;
			            break;
			        case "V":
			            diaBuscar = 5;
			            break;
			        case "S":
			            diaBuscar = 6;
			            break;
			        default:
			            throw new IllegalArgumentException("Día de la semana inválido: " + chInicioSemana);
			        }

			        // Normalización del ciclo de búsqueda
			        String chFinBusqueda = "NO";
			        while ("NO".equals(chFinBusqueda)) {
			            int diaSemana = fechaL.getDayOfWeek().getValue(); // Obtener día de la semana

			            if (diaSemana == diaBuscar) {
			                chFinBusqueda = "SI";
			            } else {
			                fechaL = fechaL.plusDays(1);
			            }
			        }

			        // Resultado final
			        valorFecha = java.util.Date.from(fechaL.atStartOfDay(ZoneId.systemDefault()).toInstant());
			        
			        break;
	        	} else if (cveVariable.startsWith("FEC_INI_MES_FEC_ACT_MENOS_")) {
	        		// Posición inicial y final para extraer el número de meses
				    iniElemento = 26;
				    finElemento = cveVariable.indexOf('_', iniElemento);

				    // Longitud del elemento extraído
				    lonElemento = finElemento - iniElemento;

				    // Obtener el número de meses a sustraer como cadena
				    sustraendo = cveVariable.substring(iniElemento, iniElemento + lonElemento);

				    // Convertir el número a entero y multiplicarlo por -1 para sustraer meses
				    sustraendoI = Integer.valueOf(sustraendo) * -1;

				    // Calcular la nueva fecha restando meses a la fecha actual
				    fechaActualMenosMeses = LocalDate.now().plusMonths(sustraendoI);

				    // Extraer el año y mes de la nueva fecha
				    anio = fechaActualMenosMeses.getYear();
				    mes = fechaActualMenosMeses.getMonthValue();

				    // Llamar al método para obtener la primera fecha del mes calculado
				    fechaPrimerDiaMes = obtenFechaPrimerDiaMes(session, mes, anio);

				    // Configurar el resultado
				    valorFecha = fechaPrimerDiaMes.getContent();
				    
				    result.setMensaje(fechaPrimerDiaMes.getMeta().getMessage());
				    result.setTipoExcepcion(fechaPrimerDiaMes.getMeta().getStatus());
				    break;
				    
	        	} else if (cveVariable.startsWith("FEC_FIN_MES_FEC_ACT_MENOS_")) {
	        			        		
	        		// Posiciones inicial y final para extraer el número del string
				    iniElemento = 26;
				    finElemento = cveVariable.indexOf('_', iniElemento);
				    log.info("finElemento: {} ", finElemento);
				    
				    // Obtener el número de meses a sustraer como cadena
				    sustraendo = cveVariable.substring(iniElemento, finElemento);
				    log.info("sustraendo {}: ", sustraendo);

				    // Convertir el número a entero y multiplicarlo por -1 para sustraer meses
				    sustraendoI = Integer.valueOf(sustraendo) * -1;
				    log.info("sustraendoI {}: ", sustraendoI);

				    // Calcular la nueva fecha restando meses a la fecha actual
				    fechaActualMenosMeses = LocalDate.now().plusMonths(sustraendoI);

				    // Extraer el año y mes de la nueva fecha
				    anio = fechaActualMenosMeses.getYear();
				    mes = fechaActualMenosMeses.getMonthValue();

				    // Obtener la última fecha del mes calculado
				    fechaUltimoDiaMes = obtenFechaUltimoDiaMes(session, mes, anio);

				    // Configurar el resultado
				    valorFecha = fechaUltimoDiaMes.getContent();
				    
				    result.setMensaje(fechaUltimoDiaMes.getMeta().getMessage());
				    result.setTipoExcepcion(fechaUltimoDiaMes.getMeta().getStatus());
				    break;
				    
	        	} else {
	        	    throw new IllegalArgumentException("Clave no reconocida: " + cveVariable);
	        	}

	        	break;
		}
			
			log.info("-----XXX valorFecha: {} {}", cveVariable ,valorFecha);

			result.setTipo(tipo);
			result.setValorFecha(valorFecha);
			return result;
			
		}
		
		// SP_REEMPLAZA_VARIABLES_BW
		@Override
		public DAORet<String, RetMsg> reemplazaVariablesBW (DatosAutenticacionTO session, String idProceso, String cadenaEntrada) throws ParseException{
			
			String tipo;
			Integer decimales;
			String cveVariableReemplazar;
			String formatoFecha = "YYYY-MM-DD";
			String valorAlfanumerico;
			Integer valorEntero;
			BigDecimal valorDecimal;
			Date valorFecha;
			String valorResultante;
			
			// INICIALIZA CÓDIGO DE ERROR, MENSAJE
			RetMsg msg = RetMsg.builder()
					.message("")
					.status(Constants.OK)
					.build();
			
			String cadenaSalida = cadenaEntrada;
			
			// CONSTANTES
			String prefijoVENT = Constants.VENT;
			String prefijoVLOC = Constants.VLOC;
			String prefijoVSIS = Constants.VSIS;
			
			String formatoFechaAMS = "yyyyMMdd";
			
			// SI LA CADENA DE ENTRADA ES NULA O VACÍA, TERMINA EL PROCESO
			if(cadenaEntrada == null || " ".equals(cadenaEntrada) || cadenaEntrada.isEmpty()){
				return new DAORet<>(cadenaSalida, msg);
			}
			
			// CREA TABLA TEMPORAL PARA ALMACENAR LISTA DE VARIABLES CONTENIDAS EN LA CADENA DE ENTRADA
			List<VariableCadenaTO> variableCadena = new ArrayList<VariableCadenaTO>();
			
			// EXTRAE VARIABLES DE LA CADENA DE ENTRADA
			EstatusVariableCadenaTO variableCade = nodoHelper.leeVariablesCadena(cadenaEntrada, variableCadena);
			
			log.info("variableCadena: {} {}", variableCadena.size(), variableCadena.toString());
			
			msg.setMessage(variableCade.getMensaje());
			msg.setStatus(variableCade.getTipoExcepcion());
			EstatusVariablesTO leeV = null;
			
			
			// LISTA DE VARIABLES PARA APLICAR EL REEMPLAZO
			for(VariableCadenaTO variableReemplazar : variableCadena) {
				
				// OBTIENE LOS VALORES PARA LA LISTA DE VARIABLES
				cveVariableReemplazar = variableReemplazar.getCveVariable();	
				
				log.info("cveVariableReemplazar: {} ", cveVariableReemplazar);
			
				 if (cveVariableReemplazar.startsWith(prefijoVLOC)) {
			    	log.info("----> VLOC: " + Constants.VLOC);
			    	EstatusVariablesTO leeVLOC = nodoHelper.leerValorVeloc(session, null, null, cveVariableReemplazar);
			    	leeV = leeVLOC;
			    	
			    } else if (cveVariableReemplazar.startsWith(prefijoVENT)) {
			    	log.info("----> VENT: " + Constants.VENT);
			    	EstatusVariablesTO leeVENT = nodoHelper.leerValorVent(session, null, null, cveVariableReemplazar);
			    	leeV = leeVENT;
			    	
			    } else if (cveVariableReemplazar.startsWith(prefijoVSIS)) {
			    	log.info("----> VSIS: " + Constants.VSIS);
			    	EstatusVariablesTO leeVSIS = nodoHelper.leerValorVsis(session, null, null, cveVariableReemplazar);
			    	leeV = leeVSIS;
			    	
			    } else {
			    	
			    	// varible especial
			    	log.info("llama a SP_LEE_VALOR_VARIABLE_ESPECIAL: {} ", cveVariableReemplazar);
			    	EstatusVariablesTO leeVVE = leeValorVariableEspecial(session, cveVariableReemplazar);
			    	leeV = leeVVE;
			    }
				 
				 tipo = leeV.getTipo();
				 decimales = leeV.getDecimales();
				 formatoFecha = leeV.getFormatoFecha();
				 valorAlfanumerico = leeV.getValorAlfanumerico();
				 valorEntero = leeV.getValorEntero();
				 valorDecimal = leeV.getValorDecimal();
				 valorFecha = leeV.getValorFecha();
				 
				 log.info("DATOS: {} {} {} {} {} {}", tipo, decimales, formatoFecha, valorAlfanumerico, valorEntero, valorFecha);
				 
				// ACTUALIZANDO EL VALOR DE LA VARIABLE EN LA TABLA TEMPORAL
				for(VariableCadenaTO variableEditar: variableCadena) {
					if(variableEditar.getCveVariable().equals(cveVariableReemplazar)) {
						variableEditar.setTipo(tipo);
						variableEditar.setDecimales(decimales);
						variableEditar.setFormatoFecha(formatoFecha);
						variableEditar.setValorAlfanumerico(valorAlfanumerico);
						variableEditar.setValorEntero(valorEntero);
						variableEditar.setValorDecimal(valorDecimal);
						variableEditar.setValorFecha(valorFecha);
					}
				}
			}
			
			// REEMPLAZANDO LOS VALORES DE LAS VARIABLES EN LA CADENA DE SALIDA
			
			// LISTA DE VALORES DE VARIABLES
			for(VariableCadenaTO variableReemplazar : variableCadena) {
				cveVariableReemplazar = variableReemplazar.getCveVariable();
				tipo = variableReemplazar.getTipo();
				decimales = variableReemplazar.getDecimales();
				formatoFecha = variableReemplazar.getFormatoFecha();
				valorAlfanumerico = variableReemplazar.getValorAlfanumerico();
				valorEntero = variableReemplazar.getValorEntero();
				valorDecimal = variableReemplazar.getValorDecimal();
				valorFecha = variableReemplazar.getValorFecha();			
							
				switch(tipo) {
				case Constants.ALFANUMERICO:
					valorResultante = valorAlfanumerico;
					break;
				case Constants.ENTERO:
					valorResultante = valorEntero.toString();
					break;
				case Constants.DECIMAL:
					valorResultante = nodoHelper.truncarDecimales(valorDecimal, decimales);
					break;
				case Constants.FECHA:
					log.info("valorFecha ANTES : {} {}" , cveVariableReemplazar, valorFecha);
					valorResultante = nodoHelper.formatFecha(valorFecha, formatoFechaAMS);
					log.info("valorFecha DESPUES : {} {}" , cveVariableReemplazar, valorFecha);
					break;
				default:
					valorResultante = "";
					break;
				}
				log.info("TIPO: {} | VALOR RESULTANTE: {} ", tipo, valorResultante);
				
				
				// REEMPLAZA LA VARIABLE DE LA CADENA POR EL VALOR RESULTANTE
				log.info("cadenaSalida ANTES: {} {}", cveVariableReemplazar , cadenaSalida);
				cadenaSalida = cadenaSalida.replace(
		                "@" + cveVariableReemplazar + "@",
		                valorResultante != null ? valorResultante : " "
		        );
				log.info("cadenaSalida DESPUES: {} ", cadenaSalida);

				
				log.info("cadenaSalida {}: ", cadenaSalida);
				
			}
			
			return new DAORet<>(cadenaSalida, msg);
		}
		
		
		// SP_EJECUTA_QUERY_DASHBOARD
		@Override
		public DAORet<String, RetMsg>  ejecutaQueryDashboard(DatosAutenticacionTO session, String cveDashboard, String servicioQuery, BigDecimal factorConversionEsacala2, List<String> datos) throws ParseException {
			
			
			log.info("---->>> ejecutaQueryDashboard ");
			//INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
			String queryServicio = "";
			RetMsg msg = RetMsg.builder()
					.message("")
					.status(Constants.OK)
					.build();
			String idProceso = "EJECUTA_QUERY_DASHBOARD";
			String servicioQuerySalida;
			
			// SE REEMPLAZAN LAS VARIABLES DEL SERVICIO_QUERY POR SUS VALORES
			//DAORet<String, RetMsg> variableRemplazada = reemplazaVariablesBW(session, idProceso, servicioQuery);
			log.info("--------->>>SERVICIO QUERY ENTRADA {}: ", servicioQuery);
			
			EstatusVariablesTO variableRemplazada = new EstatusVariablesTO();
			Boolean bandera = false;
			if (datos != null && datos.size() > 0) {
				bandera = true;
			}
			
			if(bandera) {
				servicioQuerySalida = reemplazaVariablesLista(session, servicioQuery, datos);
				variableRemplazada = nodoHelper.reemplazaVariables(session, null, null, servicioQuerySalida); 
				servicioQuerySalida = variableRemplazada.getCadenaSalida();

			} else {
				variableRemplazada = nodoHelper.reemplazaVariables(session, null, null, servicioQuery); 
                servicioQuerySalida = variableRemplazada.getCadenaSalida();
			}
			
			log.info("SERVICIO QUERY SALIDA {}: ", servicioQuerySalida);
			
			msg.setMessage(variableRemplazada.getMensaje());
			msg.setStatus(variableRemplazada.getTipoExcepcion());
			
		    Query query = entityManager.createNativeQuery(servicioQuerySalida);
			
			
			

		    // Verificar el tipo de resultados
		    List<?> results = query.getResultList();

		    log.info("RESULTADOS: {} {}", results.size(), results.toString());
		    for (Object resultado : results) {
		        // Verificar si el resultado es un arreglo (Object[]) o un String
		        if (resultado instanceof Object[]) {
		            // Manejo cuando es un Object[]
		            Object[] resultadoArray = (Object[]) resultado;
		            String cadenaDatosQS = (String) resultadoArray[1];
		            
		            queryServicio =  cadenaDatosQS;
		            log.info("queryServicio: {} ", queryServicio);
		        } else if (resultado instanceof String) {
		            // Manejo cuando es un String
		            String cadenaDatosQS = (String) resultado;
		            String[] partes = cadenaDatosQS.split("\\|"); // Divide por el separador '|'
		            
		            if (partes.length >= 2) {
		            	queryServicio =  cadenaDatosQS;
		            }
		        }
		        
		    }
		    
		    return new DAORet<>(queryServicio, msg);
		}
		
		
		// SP_LEE_INFORMACION_DASHBOARD
		/*
		 * leeInformacionDashboard hace la lectura de la información de un dashboard y la regresa en una lista de objetos DatoDashboardTO
		 * @param session DatosAutenticacionTO
		 * @param destino String
		 * @param cveDashboard String
		 * @param datos List<String>
		 * @param isSeccion Boolean
		 * @param cveSeccion int
		 * @return DAORet<List<DatoDashboardTO>, RetMsg>
		 * @throws ParseException
		 * 
		 */
		@Override
		public DAORet<List<DatoDashboardTO>, RetMsg> leeInformacionDashboard(DatosAutenticacionTO session, String destino, String cveDashboard, List<String> datos, Boolean isSeccion, int cveSeccion) throws ParseException {
			// INICIALIZA VARIABLES
			String tipoConsultaQuery = "QUERY";
			
			// INICIALIZA CÓDIGO DE ERROR Y MENSAJE
			RetMsg msg = RetMsg.builder()
					.message("")
					.status(Constants.OK)
					.build();
			
			// OBTIENE LAS SECCIONES DEL DASHBOARD
			List<SeccionDashboardTO> seccionDashboard = new ArrayList<SeccionDashboardTO>();
			
			// TABLA QUE CONTIENE LOS DATOS DE SALIDA EN FORMA "HORIZONTAL"
			List<DatoDashboardTO> datoDashboard = new ArrayList<DatoDashboardTO>();
						
		     // Crear el objeto DAORet
		     DAORet<List<DatoDashboardTO>, RetMsg> resultado = new DAORet<>(datoDashboard, msg);

			/* PROCESO QUE OBTIENE LA LISTA DE SECCIONES, SUB-SECCIONES, SUB-SUB-SECCIONES Y SERIES DE UN DASHBOARD
			--      RECORDAR QUE CUALQUIER DASHBOARD DEBE TENER LOS TRES NIVELES DE SECCIONES Y SU LISTA DE SERIES (LA CUAL PUEDE SER UNA SOLA)
			--      EN AMBIENTE WEB PUEDE HABER TÍTULOS PARA SECCIONES, SUB-SECCIONES Y SUB-SUB-SECCIONES,
			--      SIN EMBARGO, PARA LA APP, SÓLO PUEDE HABER UN TÍTULO Y EL QUE TIENE PREFERENC */
			
			msg = leeSeccionesDashboard(session, cveDashboard, seccionDashboard, isSeccion, cveSeccion);
			if(Constants.ERROR.equals(msg.getStatus())) {
				//  AQUI MOSTRAR EL MENSAJE DE ERROR, IMPLEMENTAR POSIBLES ERRORES EN LA LECTURA DE SECCIONES
				return resultado;
			}
			
			/* TABLA QUE CONTIENE EL RESULTADO DE UN QUERY-SERVICIO
    		EN CADENA_DATOS_QS SE ENCUENTRA EL RESULTADO DE EJECUTAR EL QUERY-SERVICIO EN FORMATO:
            ETIQUETA-1|DATO-1|ETIQUETA-2|DATO-2|ETIQUETA-3|DATO-3|...|ETIQUETA-N|DATO-N */
			HashMap<Integer, String> resultadoQueryServicio = new HashMap<Integer, String>();
			
			log.info("seccionDashboard A: {} {}" , seccionDashboard.size(), seccionDashboard.toString());
			
			// PROCESANDO LAS SECCIONES DEL DASHBOARD
	        seccionDashboard.sort(Comparator
	            .comparing(SeccionDashboardTO::getOrdenSeccion)
	            .thenComparing(SeccionDashboardTO::getOrdenSubSeccion)
	            .thenComparing(SeccionDashboardTO::getOrdenSubSubSeccion)
	            .thenComparing(SeccionDashboardTO::getOrdenSerie));
	        
			log.info("seccionDashboard B: {} {}" , seccionDashboard.size(), seccionDashboard.toString());

	        
	        // Inicializa el consecutivo
	        int consecutivo = 1;

	        log.info("seccionDashboard 2: {}" , seccionDashboard.size());
	        
	        for (SeccionDashboardTO seccion : seccionDashboard) {
	        	String tipoPresentacion = seccion.getTipoPresentacion();
	        	Integer escala = seccion.getEscala();
	        	
	            Integer secuenciaSeccion = seccion.getSecuenciaSeccion();
	            Integer ordenSecccion = seccion.getOrdenSeccion();
	            String tituloSeccion = seccion.getTituloSeccion();

	            Integer secuenciaSubSeccion = seccion.getSecuenciaSubSeccion();
	            Integer ordenSubSecccion = seccion.getOrdenSubSeccion();
	            String tituloSubSeccion = seccion.getTituloSubSeccion();

	            Integer secuenciaSubSubSeccion = seccion.getSecuenciaSubSubSeccion();
	            Integer ordenSubSubSecccion = seccion.getOrdenSubSubSeccion();
	            String tituloSubSubSeccion = seccion.getTituloSubSubSeccion();

	            Integer numeroSerie = seccion.getNumeroSerie();
	            Integer ordenSerie = seccion.getOrdenSerie();
	            String tituloSerie = seccion.getTituloSerie();
	            
	            
	            List<DatoDashboardBW> datosDashboard = datoDashboardBWRepository.encuentraDatosDashboard(
	                    session.getCveEntidad(), cveDashboard, secuenciaSeccion,
	                    secuenciaSubSeccion, secuenciaSubSubSeccion, numeroSerie);

	            resultadoQueryServicio.clear();
	            
	            log.info("datosDashboard: {} {}" , datosDashboard.size(), datosDashboard.toString());
	            
	            int consecutivoDato = 1;
	            for (DatoDashboardBW datoDASH : datosDashboard) {
	                Integer secuenciaDato = datoDASH.getId().getSecuenciaDato();
	                Integer ordenDato = datoDASH.getOrdenDato();
	                BigDecimal factorConversionEscala2 = datoDASH.getFactorConversionEscala2();
	                String tipoConsulta = datoDASH.getTipoConsulta();
	                String servicioQuery = datoDASH.getServicioQuery();

	                if (tipoConsulta.equals(tipoConsultaQuery)) {
	                	
	                	String queryServicio = null;
	                     DAORet<String, RetMsg> queryEjecutado = ejecutaQueryDashboard(session, cveDashboard, servicioQuery, factorConversionEscala2, datos);

	                     msg = queryEjecutado.getMeta();
	                     queryServicio = queryEjecutado.getContent();
	                     
	                    log.info("consecutivoDato: {} {}" , consecutivoDato, queryServicio);
	                    if (Constants.ERROR.equals(msg.getStatus())) {
	                        return resultado;
	                    }
	                    
	                    resultadoQueryServicio.put(consecutivoDato, queryServicio);
	                }
	                consecutivoDato++;
	            }
	            log.info("---ZZZZZZ resultadoQueryServicio: {} {}" , resultadoQueryServicio.size(), resultadoQueryServicio.toString());
	            
	            // ARMANDO LA SALIDA QUE INCLUYE LA SECCIÓN, SUB-SECCIÓN, SUB-SUB-SECCIÓN, SERIE Y ETIQUETA-DATO

	            LinkedHashMap<Integer, String> resultadoQueryServicioOrdenado = resultadoQueryServicio.entrySet().stream()
	            	    .sorted(Map.Entry.<Integer, String>comparingByKey()) // Orden ascendente por clave
	            	    .collect(Collectors.toMap(
	            	        Map.Entry::getKey,              // Clave
	            	        Map.Entry::getValue,            // Valor
	            	        (oldValue, newValue) -> oldValue, // En caso de conflicto, mantener el valor existente
	            	        LinkedHashMap::new              // Usar LinkedHashMap para preservar el orden
	            	    ));


	            String datoEtiquetaSerie = "";
	            log.info("resultadoQueryServicioOrdenado: {} {}" , resultadoQueryServicioOrdenado.size(), resultadoQueryServicioOrdenado.toString());
	            for (Map.Entry<Integer, String> entry : resultadoQueryServicioOrdenado.entrySet()) {
	                Integer consecutivoQs = entry.getKey();
	                String cadenaDatosQs = entry.getValue();

	                if (datoEtiquetaSerie.isEmpty()) {
	                    datoEtiquetaSerie = cadenaDatosQs;
	                    log.info("datoEtiquetaSerie A: {} ", datoEtiquetaSerie);
	                } else {
	                    datoEtiquetaSerie = datoEtiquetaSerie + "|" + cadenaDatosQs;
	                    log.info("datoEtiquetaSerie B: {} ", datoEtiquetaSerie);
	                }
	            }
	            log.info("DATO ETIQUETA SERIE: {} ", datoEtiquetaSerie);
	            
	            
	            DatoDashboardTO datoDashboardTO = DatoDashboardTO.builder()
	                    .secuenciaSeccion(secuenciaSeccion)
	                    .tituloSeccion(tituloSeccion)
	                    .secuenciaSubSeccion(secuenciaSubSeccion)
	                    .tituloSubSeccion(tituloSubSeccion)
	                    .secuenciaSubSubSeccion(secuenciaSubSubSeccion)
	                    .tituloSubSubSeccion(tituloSubSubSeccion)
	                    .numeroSerie(numeroSerie)
	                    .tituloSerie(tituloSerie)
	                    .datoEtiquetaSerie(datoEtiquetaSerie)
	                    .consecutivo(consecutivo) // Asigna el consecutivo
	                    .tipoPresentacion(tipoPresentacion)
	                    .escala(escala)
	                    //.factorConversionEscala2(factorConversionEscala2))
	                    .build();

	            datoDashboard.add(datoDashboardTO);
	            consecutivo++; // Incrementa el consecutivo para el siguiente dato
	        }

	        
	     // Ordenar por el atributo consecutivo en orden ascendente
	     datoDashboard.sort(Comparator.comparing(DatoDashboardTO::getConsecutivo));

	     log.info("datoDashboard: {} {}", datoDashboard.size(), datoDashboard.toString());
	     return resultado;

		}
		
		
		
		@Override
		public DAORet<GraficaTO, RetMsg> generaGrafico(DatosAutenticacionTO session, String destino, String cveDashboard, List<String> datos, Boolean isSeccion, int cveSeccion) throws ParseException {
			
			log.info("---->>> generaGrafico ");
			
			//INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
			RetMsg msg = RetMsg.builder()
					.message("")
					.status(Constants.OK)
					.build();
			
			DAORet<List<DatoDashboardTO>, RetMsg> informacionDashboard = leeInformacionDashboard(session, destino, cveDashboard, datos, isSeccion, cveSeccion);
			
			List<DatoDashboardTO> infoDashboard = informacionDashboard.getContent();
			
			List<Seccion> graficas = new ArrayList<Seccion>();
			
			log.debug("infoDashboard: {} ", infoDashboard.size());
			
			for (DatoDashboardTO informacion : infoDashboard) {
				
				log.info("------>>> informacion: {} ", informacion.getDatoEtiquetaSerie());

			    String[] datosEtiquetas = informacion.getDatoEtiquetaSerie().split("\\|");
			    String titulo = informacion.getTituloSeccion();
			    String subTitulo = informacion.getTituloSubSeccion();
			    Integer orden = informacion.getSecuenciaSubSeccion();
			    String tipoPresentacion = informacion.getTipoPresentacion();
			    String[] labels = null; // Declaración inicial
			    
			    log.debug("tipoPresentacion: {} ", tipoPresentacion);

			    if ("CIRCULAR".equals(tipoPresentacion)) {
			        labels = new String[1]; // Inicializamos el arreglo con tamaño 1
			        labels[0] = informacion.getTituloSubSubSeccion(); // Asignamos el valor específico
			    }
			   
			     BigDecimal factorConversionEscala2 = informacion.getFactorConversionEscala2();
			     Integer escala = informacion.getEscala();
			    
			    String idGrafico;
			    idGrafico = informacion.getSecuenciaSubSeccion() + "|" + informacion.getSecuenciaSubSubSeccion();

			    // Lista para almacenar los datos
			    List<Datos> data = new ArrayList<>();
			    
			    
			    log.info("datosEtiquetas: {}", datosEtiquetas.length);

			    // Procesar los pares de datos (x, y)
			    for (int j = 0; j < datosEtiquetas.length; j += 2) {
			        String x = datosEtiquetas[j]; // Texto
			        int y = (j + 1 < datosEtiquetas.length) ? Integer.parseInt(datosEtiquetas[j + 1]) : 0; // Número

			        log.info("x: {} y: {}", x, y);
			        
			        Datos dato = Datos.builder()
			                .id(informacion.getSecuenciaSubSubSeccion().toString()) // Usa el ID proporcionado
			                .x(x) // Asignamos el valor de x
			                .y(y) // Asignamos el valor de y
			                .build();

			        data.add(dato);
			    }

			    // Crear el gráfico
			    List<Grafico> graphics = new ArrayList<>();
			    Grafico graphic = Grafico.builder()
			            .id(idGrafico)
			            .tipo(tipoPresentacion)
			            .labels(labels)
			            .data(data)
			            .build();

			    graphics.add(graphic);

			    // Crear la sección
			    Seccion grafica = Seccion.builder()
			            .title(titulo)
			            .subTitle(subTitulo)
			            .order(orden)
			            .tieneEscala(null)
			            .rightScale(null)
			            .graphics(graphics)
			            .build();

			    graficas.add(grafica);
			}
	
			GraficaTO grafico = GraficaTO.builder()
					.sections(graficas)
					.build();
			
			log.debug("grafico: {} ", grafico.toString());
			
			return new DAORet<>(grafico, msg);
		}	



		/**
		 * Método para reemplazar variables en una cadena de consulta basada en una lista de variables y valores.
		 * 
		 * @param session        Objeto de autenticación (no se utiliza en este ejemplo, pero puede ser útil en el futuro).
		 * @param servicioQuery  Cadena de consulta que contiene las variables a reemplazar (por ejemplo, "@RFC@").
		 * @param datos          Lista de variables y valores en el formato "@VARIABLE@|VALOR".
		 * @return               La cadena de consulta con las variables reemplazadas por sus valores correspondientes.
		 */
		String reemplazaVariablesLista(DatosAutenticacionTO session, String servicioQuery, List<String> datos) {
		    // Inicializamos la cadena de salida con el contenido de la consulta original
		    String cadenaSalida = servicioQuery;

		    // Iteramos sobre cada elemento de la lista de datos
		    for (String dato : datos) {
		        // Dividimos el dato en dos partes: variable y valor, usando el delimitador "|"
		        String[] partes = dato.split("\\|");
		        
		        // Validamos que el dato tenga exactamente dos partes (variable y valor)
		        if (partes.length == 2) {
		            String variable = partes[0];  // La variable, por ejemplo, "@RFC@"
		            String valor = partes[1];    // El valor asociado, por ejemplo, "RFCJJ"
		            
		            // Verificamos si la cadena contiene la variable
		            if (cadenaSalida.contains(variable)) {
		                // Reemplazamos la variable con su valor correspondiente
		                // Si el valor es nulo, se reemplaza por un espacio en blanco
		                cadenaSalida = cadenaSalida.replace(variable, valor != null ? valor : " ");
		            }
		        }
		    }
		    
		    log.info("cadenaSalida LISTA: {} ", cadenaSalida);
		    
		    // Retornamos la cadena de salida con los reemplazos realizados
		    return cadenaSalida;
		}
				
		@Override
		public void procesarSeccion(Seccion seccion, int id) {
		    log.info("MODFIYING GRAPHICS SECCION");
		    for (Grafico grafico : seccion.getGraphics()) {
		        procesarGrafico(grafico, id);
		    }
		}
		
		private void procesarGrafico(Grafico grafico, int id) {
		    log.info("MODFIYING GRAPHICS GRAFICO");
		    grafico.setId(String.valueOf(id));
		    log.info("---> MODFIYING GRAPHICS TIPO: {}", grafico.getTipo());
		    
		    switch (grafico.getTipo()) {
		        case "CIRCULAR":
		            modificarGrafico(grafico, "PIE");
		            break;
		        case "BARRAS":
		            modificarGrafico(grafico, "BAR");
		            break;
		        default:
		            modificarGrafico(grafico, "LINEAL");
		            break;
		    }
		    
		    log.info("---->TIPO FINAL : {}", grafico.getTipo());
		}

		private void modificarGrafico(Grafico grafico, String nuevoTipo) {
			grafico.setTipo(nuevoTipo);
			int index = 1;
			for (Datos dato : grafico.getData()) {
				log.info("---->Datos: {}", dato.getId());
				log.info("MODFIYING GRAPHICS DATOS");
				dato.setId(String.valueOf(index++));
			}
		}
}


