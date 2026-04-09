package com.briomax.briobpm.business.service.catalogs;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.business.service.catalogs.core.ICrProgramacionProcesoService;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.CrPdfFiles;
import com.briomax.briobpm.persistence.entity.CrPdfFilesPK;
import com.briomax.briobpm.persistence.entity.CrProgramacionProceso;
import com.briomax.briobpm.persistence.entity.InVariableProceso;
import com.briomax.briobpm.persistence.entity.ParametroGeneral;
import com.briomax.briobpm.persistence.entity.StValorVariable;
import com.briomax.briobpm.persistence.repository.ICrPdfFilesRepository;
import com.briomax.briobpm.persistence.repository.ICrProgramacionProcesoRepository;
import com.briomax.briobpm.persistence.repository.IInVariableProcesoRepository;
import com.briomax.briobpm.persistence.repository.IParametroGeneralRepository;
import com.briomax.briobpm.persistence.repository.IStValorVariableRepository;
import com.briomax.briobpm.transferobjects.PdfGridTO;
import com.briomax.briobpm.transferobjects.dynamicForm.Properties;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.repse.ComboBoxProcesosTO;
import com.briomax.briobpm.transferobjects.repse.ConsultaPdfTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.extern.slf4j.Slf4j;


@Service
@Transactional
@Slf4j
public class CrProgamacionProcesoService implements ICrProgramacionProcesoService {

	@Autowired
	private ICrProgramacionProcesoRepository crProgramacionProcesoRepository;
	
	@Autowired
	private IInVariableProcesoRepository variableProcesoRepository;
	
	@Autowired
	private IStValorVariableRepository valorVariableRepository;
	
	@Autowired
	private ICrPdfFilesRepository crPdfFilesRepository;
	
	@Autowired
	private IParametroGeneralRepository parametroGeneralRepository;
	

	@Override
	public List<ComboBoxProcesosTO> getContrato(DatosAutenticacionTO session, String tipoAcceso)
			throws BrioBPMException {
		List<ComboBoxProcesosTO> result = new ArrayList<ComboBoxProcesosTO>();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		 
	    List<Object[]> registros = new ArrayList<Object[]>();
	    if (tipoAcceso.equals("CARGA")) {
	    	registros = crProgramacionProcesoRepository.getContratoContratista(session.getCveEntidad(), 
	    			 session.getCveLocalidad(), session.getCveIdioma().toUpperCase(), session.getCveUsuario());
	    } else {
	    	registros = crProgramacionProcesoRepository.getContratoGeneral(session.getCveEntidad(), 
					session.getCveLocalidad(), session.getCveIdioma().toUpperCase(), session.getCveUsuario());
	    }
		
		log.info("Tamaño Lista: " + registros.size());
		
		if (registros != null || registros.size() > 0) {
			String personalidad= "";
			String personalidadValor = "";
			String razonSocial = "";
			
			for (Object[] dato : registros) {
				String rfc = dato[0] != null ? dato[0].toString() : "";
				String contrato = dato[1] != null ? dato[1].toString() : "";
				//BigInteger secuenciaNodo = dato[2] != null ? (BigInteger) dato[2] : BigInteger.ZERO;
				Integer secuenciaNodo = 0;
				
				if (dato[2] instanceof BigDecimal) { 
					BigDecimal secuencia = (BigDecimal) dato[2];
					secuenciaNodo = secuencia.intValue();
				} else if (dato[2] instanceof Integer) {
					secuenciaNodo = (Integer) dato[2];
			    }
				
				
				log.info("rfc: " + rfc);
				log.info("contrato: " + contrato);
				
				List<Object[]> variables = variableProcesoRepository.getObtienInfoProcesos(session.getCveEntidad(), contrato, rfc);

				
				String objetoContrato = "";
				String periodoAl = "";
				String periodoDel = "";
				String nuevoPeriodo = "";
				String razon = "";
				String justificacion = "";
				
				
				if (variables != null || variables.size() > 0) {
					for (Object[] var : variables) {
						if (var[0].toString().equals("VPRO_01_NOMBRE_RAZON_SOCIAL_CONTRATISTA")) {
							razonSocial = var[1] != null ? var[1].toString() : razonSocial;
						} else if (var[0].toString().equals("VPRO_01_PERSONALIDAD_CONTRATISTA")) {
							personalidad = var[1] != null ? var[1].toString() : personalidad;

						} else if (var[0].toString().equals("VPRO_01_OBJETO_CONTRATISTA")) {
							objetoContrato = var[1] != null ? var[1].toString() : "";
						} else if (var[0].toString().equals("VPRO_01_PERIODO_EJECUCION_AL_CONTRATISTA")) {
							periodoAl = var[2] != null ? formato.format( (Date) var[2]) : "";
						} else if (var[0].toString().equals("VPRO_01_PERIODO_EJECUCION_DEL_CONTRATISTA")) {
							periodoDel = var[2] != null ? formato.format((Date) var[2]) : "";
						} else if (var[0].toString().equals("VPRO_01_NVO_PER_AL")) {
							nuevoPeriodo = var[2] != null ? formato.format((Date) var[2]): "";
						} else if (var[0].toString().equals("VPRO_01_RAZON")) {
							razon = var[1] != null ? var[1].toString() : "";
						}  else if (var[0].toString().equals("VPRO_01_JUSTIFICACION")) {
							justificacion = var[1] != null ? var[1].toString() : "";
						}
					}
				}
				if (!razon.isEmpty()) {
					contrato = contrato + " - " + razon;
				}
				ComboBoxProcesosTO to = ComboBoxProcesosTO.builder()
							.id(contrato)
							.descripcion(contrato)
							.situacionEjecucion (secuenciaNodo.intValue() == 0 ? true : false)
							.rfc(rfc)
							.razonSocial(razonSocial)
							.personalidad(personalidadValor)
							.objetoContrato(objetoContrato)
							.periodoAl(periodoAl)
							.periodoDel(periodoDel)
							.nuevoPeriodo(nuevoPeriodo)
							.razon(razon)
							.justificacion(justificacion)
							.build();
				log.info("ComboBoxProcesosTO: " + to.toString());
				
				result.add(to);
			}
			
			if (!personalidad.isEmpty()) {
				StValorVariable valor = valorVariableRepository.encuentraEtiqueta(session.getCveEntidad(), "ALTA_CONTRATISTA_CON_CONTRATO", new BigDecimal (1.0), 
						"VPRO_01_PERSONALIDAD_CONTRATISTA", personalidad);
				if (valor != null) {
					personalidadValor = valor.getEtiquetaLista();
				}
			}
			
			for (ComboBoxProcesosTO to : result) {
				to.setPersonalidad(personalidadValor);
				to.setRazonSocial(razonSocial);
			}
		}


		
		return result;
	}
	
	@Override
	public PdfGridTO getTabla(DatosAutenticacionTO session, ConsultaPdfTO datos)throws BrioBPMException {
        String tipoConsulta = datos.getActividad();
        String contrato = datos.getContrato().split(" - ")[0].trim();
        String rfc = datos.getRfc();
        String cveProceso = datos.getCveProceso();
        PdfGridTO result = null;
         
        List<CrProgramacionProceso> registros = new ArrayList<CrProgramacionProceso>();
        List<Object[]> proecsosPeriodos = new ArrayList<Object[]>();
        ObjectMapper mapper = new ObjectMapper();
        
        SimpleDateFormat formato = new SimpleDateFormat("MMMM yyyy", new Locale("es", "ES"));
        DateTimeFormatter forTitulo = DateTimeFormatter.ofPattern("MMMM yyyy", new Locale("es", "ES"));	 
        
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
        DateTimeFormatter sdf4 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
        String Multicarga = "NO";
        Optional<ParametroGeneral> parametro = parametroGeneralRepository.findById("DOC_UNICOS_SIN_CARGA");
        ParametroGeneral parGeneral = null;
        String[] docUnicos = null;
        if (parametro.isPresent()) {
        	parGeneral = parametro.get();
        	docUnicos = parGeneral.getValorAlfanumerico().split("\\|");
        }
        
        Optional<ParametroGeneral> tipoArchivo = parametroGeneralRepository.findById("DOC_TIPO_ARCHIVO");
        parGeneral = null;
        String[] parGeneralXml = null;
        if (tipoArchivo.isPresent()) {
        	parGeneral = tipoArchivo.get();
        	parGeneralXml = parGeneral.getValorAlfanumerico().split("\\#");
        }
        
        Optional<ParametroGeneral> facturas = parametroGeneralRepository.findById("DOC_FACTURAS_CFDI");
        parGeneral = null;
        String[] docFacturas = null;
        if (facturas.isPresent()) {
        	parGeneral = facturas.get();
        	docFacturas = parGeneral.getValorAlfanumerico().split("\\|");
        }
        String[] xml = null;
        List<String> ext = new ArrayList<String>();
        Date fechaSistema = new Date();
        String yearMonthDistema = formato.format(fechaSistema); 

        
        if ("CARGA".equals(tipoConsulta)) {
            if (cveProceso.isEmpty()) {
    	        
    	        proecsosPeriodos = crProgramacionProcesoRepository.procesoPeriodo(session.getCveEntidad(), session.getCveLocalidad(), session.getCveIdioma().toUpperCase(),
    	        		rfc, contrato);
            } else {
            	registros = crProgramacionProcesoRepository.obtenerInfoContratoRfcProceso(session.getCveEntidad(), session.getCveLocalidad(), session.getCveIdioma().toUpperCase(),
            			cveProceso, rfc, contrato);
            }
        } else {
            if (cveProceso.isEmpty()) {
    	        
    	        proecsosPeriodos = crProgramacionProcesoRepository.procesoPeriodoSinMulticarga(session.getCveEntidad(), session.getCveLocalidad(), session.getCveIdioma().toUpperCase(),
    	        		rfc, contrato);
            } else {
            	registros = crProgramacionProcesoRepository.obtenerInfoContRfcProcSinMulCarga(session.getCveEntidad(), session.getCveLocalidad(), session.getCveIdioma().toUpperCase(),
            			cveProceso, rfc, contrato);
            }
        }
        
        List<Properties> columns = new ArrayList<Properties>();
        List<Object> cells = new ArrayList<Object>();
      //Titulo
		Properties column = Properties.builder().width(10).cveVariable("cveProcesoPeriodico").etiqueta("Clave del documento")
				.tipoDato("ALFANUMERICO").visible("No").build();
		columns.add(column);

		column = Properties.builder().width(300).cveVariable("nombreDocumento").etiqueta("Nombre de Documento")
				.tipoDato("ALFANUMERICO").visible("Si").build();
		columns.add(column);
		
		column = Properties.builder().width(10).cveVariable("cargaMultiple").etiqueta("Proceso de carga")
				.tipoDato("ALFANUMERICO").visible("No").build();
		columns.add(column);
        
		boolean primera = true;
		String secuenciaDoc = "";
		String secuenciaMes = "";
		String mesAnnio = "";
		String mesAnnioAnt = "";
		String mesAnnioCancela = "";
		int cuenta = 1;
		int regMes = 0;
		int iDoc = 0;
		ObjectNode obj = mapper.createObjectNode();
		String documento = "";
		String situacionEnvio = "";
		Date fechaCancela = null;
		Map<String, LocalDate[]> fechasProgramadas = new HashMap<>();
		//HashMap<String, Date> fechasProgramadasOrig = new HashMap<>();
		Map<String, LocalDate[]> fechasProgramadasOrig = new TreeMap<>();
		try {

	    	if (datos.getCveProceso().isEmpty()) {
	    		int generaTitutlos = 1;
	    		
	    		if (proecsosPeriodos.size() > 0) {
	    			
    				List<Object[]> fechasGenerales = crProgramacionProcesoRepository.obtenerFechasGeneral(session.getCveEntidad(), session.getCveLocalidad(), session.getCveIdioma().toUpperCase(),
    	        			datos.getRfc(), datos.getContrato().split(" - ")[0].trim());

    				List<YearMonth> keys = new  ArrayList<YearMonth>();
    				Map<YearMonth, Map<String, List<LocalDate>>> porMesTipo = new TreeMap<>();
    				YearMonth anterior = null;
    				for  (Object[] rto : fechasGenerales) {
    					String proceso = (String) rto[1];
    					Date fecha = (Date) rto[0];   					 
        				String fecEjecuta = sdf3.format(fecha);
        		        LocalDate fechaEjecuta = LocalDate.parse(fecEjecuta, formatter);
        		        YearMonth ym = YearMonth.from(fechaEjecuta);
    			        porMesTipo
    			                .computeIfAbsent(ym, k -> new HashMap<>())
    			                .computeIfAbsent(proceso, k -> new ArrayList<>())
    			                .add(fechaEjecuta);
    			        if (anterior == null || !ym.equals(anterior)) {
    			        	keys.add(ym);
    			        	anterior = ym;
    			        }    			        
					}

    			    int numFecha = 0;
    			    String proceso = "";
    			    cuenta = 1;
	    			iDoc = 1;
    			    for (YearMonth key : keys) {
    			    	Map<String, List<LocalDate>> info = porMesTipo.get(key); 
    			    	Iterator<Map.Entry<String, List<LocalDate>>> itFecTipos = new TreeMap<>(info).entrySet().iterator();
    			    	List<LocalDate> grupoFechas = new ArrayList<LocalDate>();
    			    	numFecha = 0;
    			    	while (itFecTipos.hasNext()) {
    			            Map.Entry<String, List<LocalDate>> entry = itFecTipos.next();
    			            String tipo = entry.getKey();
    			            List<LocalDate> fechas = entry.getValue();
    			            if (numFecha < fechas.size()) {
    			            	numFecha = fechas.size();
    			            	proceso = tipo;
    			            }
    			            
        			    	if (numFecha == 1) {
        			    		grupoFechas.addAll(fechas);
        			    	}
     			        }
    			    	
    			    	LocalDate mesTitulo = key.atDay(1);
    			    	mesAnnio = mesTitulo.format(forTitulo);
    			    	if (numFecha == 1) {
    						column = Properties.builder().width(10).cveVariable("objeto" + iDoc).etiqueta(mesAnnio)
    								.tipoDato("OBJETO").visible("Si").build();
    						columns.add(column);
    			    		fechasProgramadasOrig.put(String.valueOf(iDoc) , grupoFechas.stream().distinct().toArray(LocalDate[]::new));
    						iDoc++;
    			    	} else {
    			    		cuenta = 1;
    			   			    		    			    		
    			    		for (int i = 0; i < numFecha; i++) {
    			    			
    			    			List<LocalDate> d = new ArrayList<LocalDate>();
	    						column = Properties.builder().width(10).cveVariable("objeto" + iDoc).etiqueta(mesAnnio + " - " + cuenta)
	    								.tipoDato("OBJETO").visible("Si").build();
	    						columns.add(column);
	    						
	    						itFecTipos = new TreeMap<>(info).entrySet().iterator();
            			    	while (itFecTipos.hasNext()) {
            			            Map.Entry<String, List<LocalDate>> entry = itFecTipos.next();
            			            String tipo = entry.getKey();
            			            List<LocalDate> fechas = entry.getValue();
            			            
            			            if (fechas.size() >= (i+1)) {
            			            	d.add(fechas.get(i));
            			            }        			            	

             			        }
            			    	fechasProgramadasOrig.put(String.valueOf(iDoc) , d.stream().distinct().toArray(LocalDate[]::new));
            			    	iDoc++;
            			    	cuenta++;
							} 
    			    	}
    			    }
    			    		
	    			
	        		for (Object[] dato : proecsosPeriodos) {        			
	        			//int totalRegostros = Integer.parseInt(dato[1].toString());

    					fechasProgramadas = new HashMap<>(fechasProgramadasOrig);
    					int totalFechasTitulos = fechasProgramadas.size(); // se puede eliminar
    					primera = true;
    					int iAlterno = 1;
    					String botonEliminar = "NO";
    					
	    	        	registros = crProgramacionProcesoRepository.obtenerInfoContratoRfcProceso(session.getCveEntidad(), session.getCveLocalidad(), session.getCveIdioma().toUpperCase(),
	    	        			dato[0].toString(), datos.getRfc(), datos.getContrato().split(" - ")[0].trim());
    					boolean inicio = true;
    					String cvePeriocidad = "";
    					boolean salir = false;
    					if (registros != null && registros.size() > 0) {
        					for (CrProgramacionProceso ite : registros) {
            					if (primera) {
            						Multicarga = ite.getCrProcesoPeriodico().getCargaMultiple();
            						//valor
            						obj = mapper.createObjectNode();
            						obj.put("cveProcesoPeriodico", ite.getId().getCveProcesoPeriodico());
            	 					//valor
            						obj.put("nombreDocumento", ite.getCrProcesoPeriodico().getDescripcion());
            						obj.put("cargaMultiple", Multicarga);
            						
            						for (String s : parGeneralXml) {
            				        	xml = s.split("\\|");
            				        	// boolean existe = Arrays.asList(xml).contains(ite.getId().getCveProcesoPeriodico());
            				        	boolean existe = false;
            				        	String tipoExtension = "";
            				        	for (String tipo : xml) {
            				        		log.info("Tipo: " + tipo + " - " + ite.getId().getCveProcesoPeriodico());	
            				        		if (tipo.equals(ite.getId().getCveProcesoPeriodico())) {
            				        			if (!tipoExtension.equals(tipo)) {
            				        				existe = true;
            					        			tipoExtension = tipo;
            					        			
            				        			} else {
            				        				tipoExtension = tipo;
            				        				existe = false;
            				        			}
            				        			
            				        		} else {
            				        			if (existe) {
            				        				obj.put(tipo, tipo);
            				        				ext.add(tipo);
            				        			}				        							        			
            				        		}
            				        	}
            				        }          						
            						            							    	        					
    	        					mesAnnioAnt = formato.format(ite.getId().getFechaProgramacion());  
    	        					iAlterno = 1;
    	        					primera = false;	
    	        					situacionEnvio = ite.getCrDefinicionPeriocidad().getSituacionEnvio();
    	        					fechaCancela = ite.getCrDefinicionPeriocidad().getFechaCancelaEnvio();
    	        					cvePeriocidad = ite.getId().getCveProcesoPeriodico();
    	        					mesAnnioCancela = "";
    	        					if (fechaCancela != null) {
    	            					mesAnnioCancela = formato.format(fechaCancela); 
    	        					}
    	        					if (!situacionEnvio.contentEquals("HABILITADO")) {
    	        						salir = false;
		        						if (docUnicos.length > 0) {
			        						for (String clave : docUnicos) {
												if (cvePeriocidad.equals(clave)) {
													salir = true;
													break;
												}
											} 
		        						} 
		        						if (salir) {
		        							break;
		        						}
    	        					}

            					}
            					
            					Date fechaActual = ite.getId().getFechaProgramacion();
            					String strFecProgramada =  sdf2.format(fechaActual);
            					mesAnnio = formato.format(fechaActual); 
            					boolean encontrado = false;
            					            					
        			        	for (int i = iAlterno; i < iDoc; i++) {
        			        		String claveTitulo = String.valueOf(i);
        			        		encontrado = false;
        			        		if (fechasProgramadas.containsKey(claveTitulo)) {
        			        			LocalDate[] fecTitulos = fechasProgramadas.get(claveTitulo);     			        			
    			        				for (int j = 0; j < fecTitulos.length; j++) {
    			        					String strFec =  fecTitulos[j].format(sdf4);
        			        				if (strFecProgramada.equals(strFec)) { 
        			        					encontrado = true;
        			        					iAlterno = i;
        			        					if (!situacionEnvio.contentEquals("HABILITADO")) {	
        			        						
        			        						Boolean aplica = false;
        			        						if (docUnicos.length > 0) {
            			        						for (String clave : docUnicos) {
        													if (cvePeriocidad.equals(clave)) {
        														aplica = true;
        														break;		
        													}
        												} 
        			        						} 

        			        						
        			        						if (aplica) {
        			        							encontrado = false;
        			        						} else {
        			        							if (mesAnnioCancela .equals(mesAnnio)) {
        			        								encontrado = true;
        			        							} else if(fechaActual.after(fechaCancela)) {
                			        						if (inicio) {
                    			        						encontrado = true;
                    			        						inicio = false;
                			        						} else {
                			        							encontrado = false;
                			        						}
        			        							}
        			        						}
        			        					}
        			        					break;
        			        				}   			        				
    									}
        			        		}
     			        			
    			        			 if (encontrado) { //fechaActual es igual fecTitulos
    			     					documento = "";
    			    					if (tipoConsulta.equals("CONSULTA") && Multicarga.equals("NO") 
    			    							&& ite.getSituacionEjecucion().equals("EJECUTADO") && situacionEnvio.contentEquals("HABILITADO")) {
    			    						List<CrPdfFiles> docs = crPdfFilesRepository.getDocumento(session.getCveEntidad(), ite.getId().getCveProcesoPeriodico(), 
    			    								ite.getId().getFechaProgramacion(), ite.getId().getRfc(), ite.getId().getContrato());
    			    						CrPdfFiles docIte = docs.isEmpty() ? null : docs.get(0);
    			    						if (docIte != null) {
    			    							documento = docIte.getNombreDocumento();
    			    						} 
    			    					}
    			    					
    			    					if (tipoConsulta.equals("CONSULTA") && Multicarga.equals("SI") 
    			    							&& ite.getSituacionEjecucion().equals("EJECUTADO") && situacionEnvio.contentEquals("HABILITADO")) {
    			    						List<CrPdfFiles> docs = crPdfFilesRepository.getDocumento(session.getCveEntidad(), ite.getId().getCveProcesoPeriodico(), 
    			    								ite.getId().getFechaProgramacion(), ite.getId().getRfc(), ite.getId().getContrato());
    			    						CrPdfFiles docIte = docs.isEmpty() ? null : docs.get(0);
    			    						if (docIte != null) {
    			    							documento = docIte.getNombreDocumento();
    			    						} /*else {
    			    							List<String> archivos = getArchivo( ite) ;
    			    							//docIte = archivos.isEmpty() ? null : archivos.get(0);
    			    							if (archivos != null) {
    			    								documento = archivos.get(0);
    			    							}
    			    						}*/
    			    					}

    		        					List<String> docList = new ArrayList<String>();
    		        					ObjectNode objDetalle = mapper.createObjectNode();
    		        					ArrayNode array = objDetalle.putArray("nombresDocumentos");
    		        					if (tipoConsulta.equals("CONSULTA") && Multicarga.equals("SI") 
    		        							&& ite.getSituacionEjecucion().equals("EJECUTADO") && situacionEnvio.contentEquals("HABILITADO")) {
    		        						docList = crPdfFilesRepository.getSecuencias (session.getCveEntidad(), ite.getId().getCveProcesoPeriodico(), 
    		        								ite.getId().getFechaProgramacion(), ite.getId().getRfc(), ite.getId().getContrato());
    		        						if (Arrays.asList(docFacturas)
    		        						        .contains(ite.getId().getCveProcesoPeriodico())) {
			        							
			        							/*docList = variableProcesoRepository.getExtensionesFacturas(session.getCveEntidad(), ite.getId().getCveProcesoPeriodico(), 
			        									 ite.getId().getRfc(), ite.getId().getContrato());*/
			        							
			        							List<CrPdfFiles> docs = getArchivo( ite) ;
			        							docList = new ArrayList<String>();
			        							if (docs != null) {
			        								for (CrPdfFiles doc : docs) {
			        									docList.add(doc.getNombreDocumento());
			        								}
			        							}
	
    		        					} else {
			        						docList = crPdfFilesRepository.getExtensiones(session.getCveEntidad(), ite.getId().getCveProcesoPeriodico(), 
			        								ite.getId().getFechaProgramacion(), ite.getId().getRfc(), ite.getId().getContrato());
			        						
			        						}
			        						for (String ex : ext) {
			        							boolean exists = docList.stream()
			        							        .anyMatch(s -> s != null && s.toUpperCase().equals(ex.toUpperCase()));
			        						    if (exists) {
			        						        objDetalle.put(ex, "SI");
			        						    } else {
			        						        objDetalle.put(ex, "NO");
			        						    }
			        						}

			        					}
    		        					
    		        					//activa boton eliminat
    		        					botonEliminar = "NO";
    		        					if (tipoConsulta.equals("CARGA")  && ite.getSituacionEjecucion().equals("EJECUTADO") ) {
    		        						
    		        						
    		            					if (yearMonthDistema == mesAnnio && fechaActual.getDay() <= fechaSistema.getDay()) {
    		            						 botonEliminar = "SI";
    		            					}
    		            					
    		            					if (fechaSistema.before(fechaActual)) {
    		            						botonEliminar = "SI";
    		            					}
    		            					
    		            					if (botonEliminar.equals("NO")) {
    		            						int valor = variableProcesoRepository.encuentraValorAlfanumericoEliminaDoc(session.getCveEntidad(), 
    		            								ite.getId().getFechaProgramacion(), ite.getId().getCveProcesoPeriodico(), ite.getId().getRfc(),
    		            								ite.getId().getContrato());
    		            						
    		            						if (valor > 0) {
    		            							botonEliminar = "SI";
    		            						}
    		            					}
    		        					}    	            					   			    					
    			    					
    		        					docList.forEach(array::add);
    			        					         	                   					
                    					objDetalle.put("estatus" , ite.getSituacionEjecucion()  );
                    					objDetalle.put("nombreDocumento" , documento);
                    					objDetalle.put("fechacarga", strFecProgramada);
                    					objDetalle.put("tipoPeriodo", ite.getId().getCvePeriodicidad());
                    					objDetalle.put("secuencia", ite.getId().getSecuenciaProgramacion());
                    					objDetalle.put("isEliminar", botonEliminar);
                    					
                    					JsonNode detalle = null;
        								detalle = mapper.readTree(objDetalle.toString() );
                    					obj.set("objeto" + claveTitulo, detalle);
                    					//fechasProgramadas.remove(claveTitulo);
                    					//cells.add(obj);
                    					iAlterno++;
                    					break;
                			        } else {
                			        	botonEliminar = "NO";
                			        	
                       					ObjectNode objDetalle = mapper.createObjectNode();
                    					objDetalle.put("estatus" , "");
                    					objDetalle.put("nombreDocumento" , "");
                    					objDetalle.put("fechacarga", "");
                    					objDetalle.set("nombresDocumentos", null);
                    					objDetalle.put("tipoPeriodo", ite.getId().getCvePeriodicidad());
                    					objDetalle.put("isEliminar", botonEliminar);

                    					JsonNode detalle = null;
        								detalle = mapper.readTree(objDetalle.toString() );
                    					obj.set("objeto" + claveTitulo, detalle);
                    					
                    					//fechasProgramadas.remove(claveTitulo);   
                    					//cells.add(obj);
                			        }
    			        				
    			        		}    			        	
        					}
        					
        					if (!salir) {
        						botonEliminar = "NO";
	    			        	for (int i = iAlterno; i < iDoc; i++) {
	    		        			ObjectNode objDetalle = mapper.createObjectNode();
	             					objDetalle.put("estatus" , "");
	             					objDetalle.put("nombreDocumento" , "");
	             					objDetalle.put("fechacarga", "");
	             					
	
	             					JsonNode detalle = null;
	    								detalle = mapper.readTree(objDetalle.toString() );
	             					obj.set("objeto" + i, detalle);
	             					obj.put("isEliminar", botonEliminar);
	    			        	} 					
	        					cells.add(obj);	
	    		    			
	        					generaTitutlos++;	
        					}
    					}
    					

	        		}
	    		}	
		    			
	    		
	    	} else {
	    		
	    		// se busca un documento
	    		Boolean inicio = true;
	    		Boolean encontrado = true;
	        	for (CrProgramacionProceso ite : registros) {
	        		String cvePeriocidad = "";
					if (primera) {
						Multicarga = ite.getCrProcesoPeriodico().getCargaMultiple();
						//valor
						obj = mapper.createObjectNode();
						obj.put("cveProcesoPeriodico", ite.getId().getCveProcesoPeriodico());
	 					//valor
						obj.put("nombreDocumento", ite.getCrProcesoPeriodico().getDescripcion());
						obj.put("cargaMultiple", Multicarga);
						
						for (String s : parGeneralXml) {
				        	xml = s.split("\\|");
				        	// boolean existe = Arrays.asList(xml).contains(ite.getId().getCveProcesoPeriodico());
				        	boolean existe = false;
				        	String tipoExtension = "";
				        	for (String tipo : xml) {
				        		log.info("Tipo: " + tipo + " - " + ite.getId().getCveProcesoPeriodico());	
				        		if (tipo.equals(ite.getId().getCveProcesoPeriodico())) {
				        			if (!tipoExtension.equals(tipo)) {
				        				existe = true;
					        			tipoExtension = tipo;
					        			
				        			} else {
				        				tipoExtension = tipo;
				        				existe = false;
				        			}
				        			
				        		} else {
				        			if (existe) {
				        				obj.put(tipo, tipo);
				        				ext.add(tipo);
				        			}				        							        			
				        		}
				        	}
				        }
						 

						mesAnnioAnt = formato.format(ite.getId().getFechaProgramacion());
						primera = false;
						cvePeriocidad = ite.getId().getCvePeriodicidad();
    					mesAnnioCancela = "";
    					if (fechaCancela != null) {;
        					mesAnnioCancela = formato.format(fechaCancela); 
    					}
					}

					mesAnnio = formato.format(ite.getId().getFechaProgramacion());
					regMes = crProgramacionProcesoRepository.totalRegistrosProceso(session.getCveEntidad(), session.getCveLocalidad(), session.getCveIdioma().toUpperCase(),
							ite.getId().getCveProcesoPeriodico(), datos.getRfc(), datos.getContrato(), ite.getId().getFechaProgramacion());
					
					if  (regMes == 1) {
						secuenciaMes = "";
					} else {
						
						if (mesAnnioAnt.equals(mesAnnio)) {
    						secuenciaMes =" - " + cuenta;
    						cuenta++;
						} else {
							mesAnnioAnt = mesAnnio;
							cuenta = 1;
							secuenciaMes =" - " + cuenta;
							cuenta++;
						}
					}
					
					secuenciaDoc = ite.getId().getSecuenciaProgramacion().toString();
					//TITULO
					column = Properties.builder().width(10).cveVariable("objeto" + secuenciaDoc).etiqueta(mesAnnio + secuenciaMes)
							.tipoDato("OBJETO").visible("Si").build();
					columns.add(column);  
					
					Date fechaActual = ite.getId().getFechaProgramacion();
					
					situacionEnvio = ite.getCrDefinicionPeriocidad().getSituacionEnvio();				

    				if (!situacionEnvio.contentEquals("HABILITADO")) {
							
						Boolean aplica = false;
						for (String clave : docUnicos) {
							if (cvePeriocidad.equals(clave)) {
								aplica = true;
								break;		
							}
						} 
						
						if (aplica) {
							encontrado = false;
						} else {
							if (mesAnnioCancela .equals(mesAnnio)) {
								encontrado = true;
							} else if(fechaActual.after(fechaCancela)) {
        						if (inicio) {
	        						encontrado = true;
	        						inicio = false;
        						} else {
        							encontrado = false;
        						}
							}
						}

   					} 
    				String botonEliminar = "NO";
					if (encontrado) {
						//DATOS
						ObjectNode objDetalle = mapper.createObjectNode();
						objDetalle.put("estatus", ite.getSituacionEjecucion());
						documento = "";    					
    					
    					Date fechaActual1 = ite.getId().getFechaProgramacion();
    					mesAnnio = formato.format(fechaActual1); 
						
    					if (tipoConsulta.equals("CONSULTA") && Multicarga.equals("NO") 
								&& ite.getSituacionEjecucion().equals("EJECUTADO")) {
							List<CrPdfFiles> docs = crPdfFilesRepository.getDocumento(session.getCveEntidad(), ite.getId().getCveProcesoPeriodico(), 
									ite.getId().getFechaProgramacion(), ite.getId().getRfc(), ite.getId().getContrato());
							CrPdfFiles docIte = docs.isEmpty() ? null : docs.get(0);
							
							if (docIte != null) {
								documento = docIte.getNombreDocumento();
							} else {
	    						List<String> docList = crPdfFilesRepository.getExtensiones(session.getCveEntidad(), ite.getId().getCveProcesoPeriodico(), 
	    								ite.getId().getFechaProgramacion(), ite.getId().getRfc(), ite.getId().getContrato());
	    						for (String ex : ext) {
	    						    if (docList.contains(ex)) {
	    						        objDetalle.put(ex, "SI");
	    						    } else {
	    						        objDetalle.put(ex, "NO");
	    						    }
	    						}
	    					}
						}
						List<String> docIte = new ArrayList<String>();
						if (tipoConsulta.equals("CONSULTA") && Multicarga.equals("SI") 
								&& ite.getSituacionEjecucion().equals("EJECUTADO")) {
							docIte = crPdfFilesRepository.getSecuencias (session.getCveEntidad(), ite.getId().getCveProcesoPeriodico(), 
									ite.getId().getFechaProgramacion(), ite.getId().getRfc(), ite.getId().getContrato());
						}  else {
    						List <String> docList = crPdfFilesRepository.getExtensiones(session.getCveEntidad(), ite.getId().getCveProcesoPeriodico(), 
    								ite.getId().getFechaProgramacion(), ite.getId().getRfc(), ite.getId().getContrato());
    						for (String ex : ext) {
    						    if (docList.contains(ex)) {
    						        objDetalle.put(ex, "SI");
    						    } else {
    						        objDetalle.put(ex, "NO");
    						    }
    						}
    					}
						
					
    					//activa boton eliminat
						botonEliminar = "NO";
    					if (tipoConsulta.equals("CARGA") && ite.getSituacionEjecucion().equals("EJECUTADO") ) {
    						
    						
        					if (yearMonthDistema == mesAnnio && fechaActual.getDay() <= fechaSistema.getDay()) {
        						 botonEliminar = "SI";
        					}
        					
        					if (fechaSistema.before(fechaActual)) {
        						botonEliminar = "SI";
        					}
        					
        					if (botonEliminar.equals("NO")) {
        						int valor = variableProcesoRepository.encuentraValorAlfanumericoEliminaDoc(session.getCveEntidad(), 
        								ite.getId().getFechaProgramacion(), ite.getId().getCveProcesoPeriodico(), ite.getId().getRfc(),
        								ite.getId().getContrato());
        						
        						if (valor > 0) {
        							botonEliminar = "SI";
        						}
        					}
    					}
						ArrayNode array = objDetalle.putArray("nombresDocumentos");
						docIte.forEach(array::add);
							
						objDetalle.put("nombreDocumento", documento);
						objDetalle.put("fechacarga", sdf2.format(ite.getId().getFechaProgramacion()));
						objDetalle.put("isEliminar", botonEliminar);
						
						JsonNode detalle = null;
						detalle = mapper.readTree(objDetalle.toString() );
						obj.set("objeto" + secuenciaDoc, detalle);
	
		        	} else {
	   					ObjectNode objDetalle = mapper.createObjectNode();
						objDetalle.put("estatus" , "");
						objDetalle.put("nombreDocumento" , "");
						objDetalle.put("fechacarga", "");
						objDetalle.set("nombresDocumentos", null);
						objDetalle.put("tipoPeriodo", ite.getId().getCvePeriodicidad());
						objDetalle.put("isEliminar", botonEliminar);
	
						JsonNode detalle = null;
						detalle = mapper.readTree(objDetalle.toString() );
						obj.set("objeto" + secuenciaDoc, detalle);
						//fechasProgramadas.remove(claveTitulo);   
						//cells.add(obj);
			        }

	        	}
	        	cells.add(obj);
	    	}
    	
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			log.error(">>>>>>>>>>>>>>>>>>>> error en JsonProcessingException : {}", e.getMessage());
		}
		result = PdfGridTO.builder().columns(columns).cells(cells).build();
		
        
        return result;
	}
	
	@Override
	public String getEstatusProcesoPeriodico(DatosAutenticacionTO session, ConsultaPdfTO datos)throws BrioBPMException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaCarga = null;
		try {
			fechaCarga = sdf.parse(datos.getFechaCarga());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			log.error(">>>>>>>>>>>>>>>>>>>> error en SimpleDateFormat : {}", e.getMessage());
		}
		
		String situacion = crProgramacionProcesoRepository.situacionEjecucion(session.getCveEntidad(), session.getCveLocalidad(),
				session.getCveIdioma().toUpperCase(), datos.getCveProceso(), datos.getRfc(), datos.getContrato(), fechaCarga);
		return situacion;
	}
	
	private List<CrPdfFiles>  getArchivo( CrProgramacionProceso ite ) {
		List<Object[]> docs = variableProcesoRepository.getDocumentoByContratoRFC(
			    ite.getId().getCveEntidad(),
			    ite.getCrDefinicionPeriocidad().getId().getContrato(),
			    ite.getCrDefinicionPeriocidad().getId().getRfc(),
			    ite.getCrProcesoPeriodico().getId().getCveProcesoPeriodico()
			);

			List<CrPdfFiles> resultado = new ArrayList<>();
			
			for (Object[] row : docs) {
				CrPdfFiles entity = new CrPdfFiles();
			    CrPdfFilesPK pk = new CrPdfFilesPK();
			    // PK
			    pk.setCveEntidad(ite.getId().getCveEntidad());
			    pk.setCveProcesoPeriodico(ite.getCrProcesoPeriodico().getId().getCveProcesoPeriodico());

			    // fecha (validar null)
			    pk.setFechaCarga(ite.getId().getFechaProgramacion());

			    pk.setSecuenciaCarga(row[3] != null ? ((Number) row[3]).intValue() : null);
			    pk.setNumeroContrato(ite.getCrDefinicionPeriocidad().getId().getContrato());
			    pk.setRfc(ite.getCrDefinicionPeriocidad().getId().getRfc());
			    pk.setTipoArchivo((String) row[4]);

			    entity.setId(pk);

			    // Datos
			    entity.setNombreDocumento((String) row[5]);
			   // entity.setArchivoBinario((byte[]) row[6]);
			    
			    resultado.add(entity);
			}
			return resultado;
	}
}
