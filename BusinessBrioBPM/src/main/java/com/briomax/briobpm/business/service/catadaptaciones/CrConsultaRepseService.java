package com.briomax.briobpm.business.service.catadaptaciones;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.business.convertes.IRepseConverter;
import com.briomax.briobpm.business.helpers.base.INodoHelper;
import com.briomax.briobpm.business.service.catadaptaciones.core.ICrConsultaRepseService;
import com.briomax.briobpm.business.service.catalogs.core.IMessagesService;
import com.briomax.briobpm.business.service.core.IBPMMailSender;
import com.briomax.briobpm.common.core.Constants;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.CorreoProceso;
import com.briomax.briobpm.persistence.entity.CrConsultaRepse;
import com.briomax.briobpm.persistence.repository.ICorreoProcesoRepository;
import com.briomax.briobpm.persistence.repository.ICrConsultaRepseRepository;
import com.briomax.briobpm.persistence.repository.IDestinarioCorreoRepository;
import com.briomax.briobpm.persistence.repository.IInVariableProcesoRepository;
import com.briomax.briobpm.transferobjects.ComboBoxTO;
import com.briomax.briobpm.transferobjects.in.CrConsultaRepseTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.repse.ConsultaPdfTO;
import com.briomax.briobpm.transferobjects.repse.FiltroValidaRepseTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class CrConsultaRepseService implements ICrConsultaRepseService {

	/** El atributo o variable in Variable Proceso Repository. */
	@Autowired
	IInVariableProcesoRepository inVariableProcesoRepository;
	
	@Autowired
	private ICrConsultaRepseRepository crConsultaRepseRepository;
	
	@Autowired
	private IBPMMailSender mailSender;

	/** El atributo o variable Valor Variable Proceso repository. */
	@Autowired
	private ICorreoProcesoRepository iCorreoProcesoRepository;
	
	/** El atributo o variable Valor Destinario Correo Repository. */
	@Autowired
	private IDestinarioCorreoRepository iDestinarioCorreoRepository;
	
	/** El atributo o variable Valor Destinario Correo Repository. */
	@Autowired
	private INodoHelper iNodoHelper;
	
	/** El atributo o variable messages Repository. */
	@Autowired
	private IMessagesService messagesService;
	
	
	/** El atributo o variable lectorPDFRecibodeNomina. */
	@Value("${path.python}")
    private String rutaPython;
	
	/** El atributo o variable lectorPDFRecibodeNomina. */
	@Value("${spring.profiles.active}")
    private String environment;

	@Value("${spring.datasource.active.manager}")
    private String ambiente;
	
	@Value("${path.slash}")
    private String slash;
	
	@Override
	public List<ComboBoxTO> getRfcProcesosPeriodicos(DatosAutenticacionTO session, ConsultaPdfTO filtro)
			throws BrioBPMException {
		List<ComboBoxTO> result = new ArrayList<ComboBoxTO>();
		/*List<String> listRfc = crConsultaRepseRepository.getRfc(session.getCveEntidad(), filtro.getFechaIncio(), filtro.getFechaFin(),
				filtro.getTipo());
		if (listRfc != null && listRfc.size() > 0) {
			for (String rfc : listRfc) {
				
				ComboBoxTO to = ComboBoxTO.builder().id(rfc)
							.descripcion(rfc)
							.build();
				result.add(to);
			}
		}*/
		return result;
	}

	@Override
	public List<CrConsultaRepseTO> consultarRegistros(DatosAutenticacionTO session, String razonSocial,
			String resultadoColumna, Date fecha) throws BrioBPMException {
		
		String fechaFormateada;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		fechaFormateada = null;
		if(fecha != null) {
			fechaFormateada = sdf.format(fecha);
		} 
		
		log.info("FECHA FORMATEADA: " + fechaFormateada);

		List<CrConsultaRepseTO> registrosTO = new ArrayList<CrConsultaRepseTO>();
		List<CrConsultaRepse> registros = crConsultaRepseRepository.findByAll(razonSocial, resultadoColumna, fechaFormateada);
	
		registrosTO.addAll(registros.stream().map(IRepseConverter.converterEntityToDTO).collect(Collectors.toList()));
		log.info("Tamaño Lista datosATDPN: " + registrosTO.size());
		
		return registrosTO;
	}

	@Override
	public List<CrConsultaRepseTO> consultaTodosRegistros() {

		List<CrConsultaRepseTO> registrosTO = new ArrayList<>();
		List<CrConsultaRepse> registros = crConsultaRepseRepository.findAll();

		registrosTO.addAll(registros.stream().map(IRepseConverter.converterEntityToDTO).collect(Collectors.toList()));		
		log.info("Tamaño Lista datosATDPN: " + registrosTO.size());

		return registrosTO;
	}


	@Override
	public RetMsg correEjecutablePython(String data) throws BrioBPMException, IOException, InterruptedException {
	    String nombreEjecutable = Constants.NOMBRE_EJECUABLE_REPSE;
	    String executablePath = Constants.PATH_EJECUTABLE_REPSE + nombreEjecutable;

	    File scriptFile = new File(executablePath);
	    if (!scriptFile.exists()) {
	        log.info("El script no se encuentra en la ruta especificada: " + executablePath);
	        return RetMsg.builder()
	                    .message("El script no se encuentra en la ruta especificada: " + executablePath)
	                    .status(Constants.ERROR)
	                    .build();
	    }

	    log.info("EJECUTABLE ENCONTRADO: " + executablePath);

	    // entrego argumento en la variable "data" al ejecutable de windows (.exe) 
	    // Configura el proceso para ejecutar el script de Python con los argumentos necesarios
	    ProcessBuilder processBuilder = new ProcessBuilder(executablePath, data);
	    Process process = processBuilder.start();  // Inicia el proceso

	    StringBuilder output = new StringBuilder();
	    StringBuilder errorOutput = new StringBuilder();

	    try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
	         BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {

	        String line;
	        while ((line = reader.readLine()) != null) {
	            output.append(line).append("\n");
	        }

	        while ((line = errorReader.readLine()) != null) {
	            errorOutput.append(line).append("\n");
	        }

	        process.waitFor();
	        log.info("SALIDA : " + output.toString());
	        log.info("ERROR : " + errorOutput.toString());
	        log.info("STATUS : " + process.exitValue());

	        if (process.exitValue() == 0) {
	            return RetMsg.builder()
	                        .message(output.toString())
	                        .status(Constants.OK)
	                        .build();
	        } if (process.exitValue() == -1) {
	            return RetMsg.builder()
                        .message("La Razon Social no esta regstrada.")
                        .status(Constants.ERROR)
                        .build();            
	        } else {
	            return RetMsg.builder()
	                        .message("Error en la ejecución del script: " + errorOutput.toString())
	                        .status(Constants.ERROR)
	                        .build();
	        }
	    } catch (IOException ioException) {
	        ioException.printStackTrace();
	        throw ioException;
	    } catch (InterruptedException interruptedException) {
	        interruptedException.printStackTrace();
	        throw interruptedException;
	    } finally {
	        process.destroy();
	    }
	}
	
	@Override
	public RetMsg validaRepseLinea(DatosAutenticacionTO session, FiltroValidaRepseTO datos) throws BrioBPMException, IOException, InterruptedException {
		
		String tipoDestinatarioPara = "PARA";
		String tipoDestinatarioCcp = "CCP";
		String conCopiaPara = "";
		String conPara = "";
		RetMsg result = RetMsg.builder().message("La Razon Social " + datos.getRazonSocial() + " es vigente.").status("OK").build();  
		
        // Definir el formato esperado
		Date fechaConsulta = new Date();
        // Definir el formato esperado
        SimpleDateFormat formatoDia = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
        
        // Cadena de texto con la fecha
        String fechaStr =  formatoDia.format(fechaConsulta);
        
        // Convertir la cadena a Date
        Date fecha = null;
		try {
			
			//obtenr el rfc, razon del contratista
			List<String> datosInVariable = inVariableProcesoRepository.encuentraValorAlfanumerico(session.getCveEntidad(),
					datos.getCveProceso(), new BigDecimal(datos.getVersion()), datos.getCveInstancia(), "VPRO_01_RFC_CONTRATISTA");
			
			datos.setRfc(datosInVariable.get(0).toString());
			String razon = datosInVariable.get(1).toString();
			razon = razon.replaceAll("\\.", "");
			datos.setRazonSocial(razon);
			
			//ejecuta phyton
			String lectorRepse = rutaPython + "repse_consulta_avanzada.py";
			log.error(">>>>>>>>>>>>>>>>>>>> lectorPDFRecibodeNomina {}  nombreArchivo {}", lectorRepse);
			RetMsg res = pythonExecutor (lectorRepse, session, datos);
			log.error(">>>>>>>>>>>>>>>>>>>> res {} ", res);
			
			if (res.getStatus().equals("ERROR")) {
				result = res;
				return result;
			}
			
			fecha = formatoDia.parse(fechaStr);
			
			//Se procesa la validacion de Repse
			CrConsultaRepse registro = crConsultaRepseRepository.getAvisoRepseFiltro(session.getCveEntidad(), fecha, datos.getRazonSocial());
			List<CorreoProceso> listaCorreos = new ArrayList<CorreoProceso>();
			
			if (registro != null && registro.getResultadoConsulta().equals("ENCONTRADO")) {
				//Obtiene los correos para envio con exito
				listaCorreos = iCorreoProcesoRepository.obtenerCorreos(
						session.getCveEntidad(),
						datos.getCveProceso(),
						new BigDecimal(datos.getVersion()),
						"REPSE_VALIDO",
						datos.getCveNodo(),
						datos.getIdNodo());
				
				String fechaVigencia = registro.getAvisoFechaRegistro();
				Date fecVigencia = formatoDia.parse(fechaVigencia);
				String fecVigStr = formato.format(fecVigencia);
				String fecActStr = formato.format(fecha);
				
				int act = Integer.parseInt(fecActStr);
				int vig = Integer.parseInt(fecVigStr);
				if (act <= vig) {
					result = RetMsg.builder().message(messagesService.getMessage("ERROR_REPSE_VALIDO", session.getCveIdioma())).status("ERROR").build();
				} else {
					result = RetMsg.builder().message(messagesService.getMessage("ERROR_REPSE_NO_VALIDO", session.getCveIdioma())).status("ERROR").build();
				}
			} 
			
			if (registro != null && registro.getResultadoConsulta().equals("NO_ENCONTRADO")) {
				result = RetMsg.builder().message(messagesService.getMessage("ERROR_REPSE_NO_ENCONTRADO", session.getCveIdioma())).status("ERROR").build();
				//Obtiene los correos para envio con exito
				listaCorreos = iCorreoProcesoRepository.obtenerCorreos(
						session.getCveEntidad(),
						datos.getCveProceso(),
						new BigDecimal(datos.getVersion()),
						"REPSE_NO_VALIDO",
						datos.getCveNodo(),
						datos.getIdNodo());
				
			}
			
			if (listaCorreos.size() > 0) {
				for(CorreoProceso correoPro : listaCorreos) {
					Integer secuenciaCorreo = correoPro.getId().getSecuenciaCorreo();
					String asunto = correoPro.getAsunto();			
					String cuerpo = correoPro.getCuerpo();

			        // Obtiene la lista de destinatarios del correo para la secuencia actual.
					List<Object[]> destinatarios = iDestinarioCorreoRepository.obtenerDestinatariosCorreo(
							session.getCveEntidad(),
							datos.getCveProceso(),
							new BigDecimal(datos.getVersion()),
							"VALIDA_REPSE",
							secuenciaCorreo,
							tipoDestinatarioPara,
							datos.getCveRol(),
							session.getCveUsuario());
					
					if (destinatarios.size() > 1) {
				        // Recorre la lista de destinatarios para configurar los correos a enviar.
						for (Object[] ite : destinatarios) {
							String grupoCorreo = (String) ite[0];
							BigDecimal secCorreo = (BigDecimal) ite[1];
							String tipoDestinatario = (String) ite[2];
							String usoCveUsuarioCreador = (String) ite[3];
							String cveRolDestinatario = (String) ite[4];
							String cveUsuarioDestinatario = (String) ite[5];
						
							if(tipoDestinatario.equals(tipoDestinatarioPara) && grupoCorreo.equals("UNICO")) {
								String direccionCorreo = iNodoHelper.fnLeeDireccionCorreo(
										session.getCveEntidad(),
										datos.getCveProceso(),
										new BigDecimal(datos.getVersion()),
										cveUsuarioDestinatario,
										cveRolDestinatario);
								
			                    conPara = conCopiaPara.isEmpty() ? direccionCorreo : conCopiaPara + "|" + direccionCorreo;
								
							} else {
								if(tipoDestinatario.equals(tipoDestinatarioCcp) && grupoCorreo.equals("UNICO")) {
									String direccionCorreo = iNodoHelper.fnLeeDireccionCorreo(
											session.getCveEntidad(),
											datos.getCveProceso(),
											new BigDecimal(datos.getVersion()),
											cveUsuarioDestinatario,
											cveRolDestinatario);
									
				                    conCopiaPara = conCopiaPara.isEmpty() ? direccionCorreo : conCopiaPara + "|" + direccionCorreo;

								}
							}							
						}
					} else {
						Object[] ite = destinatarios.get(0);
						
						String cveRolDestinatario = (String) ite[4];
						String cveUsuarioDestinatario = (String) ite[5];
						
						String direccionCorreo = iNodoHelper.fnLeeDireccionCorreo(
								session.getCveEntidad(),
								datos.getCveProceso(),
								new BigDecimal(datos.getVersion()),
								cveUsuarioDestinatario,
								cveRolDestinatario);
						
	                    conPara = conCopiaPara.isEmpty() ? direccionCorreo : conCopiaPara + "|" + direccionCorreo;
					}
					String[] to = conPara.split("\\|");
					String[] cc = null;
					
					if (!conCopiaPara.isEmpty()) {
						cc = conCopiaPara.split("\\|");
					}
					asunto = asunto.replace("@RazonSocial@", datos.getRazonSocial());
					cuerpo = cuerpo.replace("@RazonSocial@", datos.getRazonSocial());
					cuerpo = cuerpo.replace("@Folio@", registro.getFolio());
					cuerpo = cuerpo.replace("@DescripcionServicio@", registro.getDescripcionServicio());
					cuerpo = cuerpo.replace("@AvisoFechaRegistro@", registro.getAvisoFechaRegistro());
					cuerpo = cuerpo.replace("@EntidadMunicipio@", registro.getEntidadMunicipio());
					cuerpo = cuerpo.replace("@Rfc@", registro.getId().getRfcContratista());
					
					mailSender.sendHtmlMailMessage(to, cc, asunto, cuerpo, registro.getImagenConsulta());
				}						
			}
		} catch (ParseException e) {
			result = RetMsg.builder().message(messagesService.getMessage("ERROR_REPSE_GENERAL", session.getCveIdioma())).status("ERROR").build();
		}

		return result;
	}
	
	private RetMsg pythonExecutor(String scriptPath, DatosAutenticacionTO session, FiltroValidaRepseTO filtro) {
		log.debug(">>>>>>>>>>>>>>>>>>>> Entrada del rutaPython: " + rutaPython);
		log.debug(">>>>>>>>>>>>>>>>>>>> Entrada del scriptPath: " + scriptPath);
		log.debug(">>>>>>>>>>>>>>>>>>>> Entrada del razonSocial: " + filtro.getRazonSocial());
		log.info(">>>>>>>>>>>>>>>>>>>> Entrada del environment: " + environment);
		boolean resultado = false;
		String rutaDirectorio = rutaPython + "Aviso_REPSE" + slash;
		String nombreArchivo = "resultado_ejecucion_repse.log";
		File directorio = new File(rutaDirectorio);
		File archivo = new File(directorio, nombreArchivo);
		RetMsg result = RetMsg.builder().message("").status("OK").build(); 
		

		try {

			// Crear el archivo si no existe
			if (!archivo.exists()) {
				archivo.createNewFile();
			}

			ProcessBuilder pb = new ProcessBuilder("python", scriptPath, filtro.getRazonSocial(), session.getCveEntidad(),
					filtro.getCveProceso(), filtro.getRfc(), filtro.getContrato(), environment, ambiente);
			File outputFile = new File(rutaDirectorio + "resultado_ejecucion_repse.log");
			log.info("%%%%%%%%% Entra al proceso: " + scriptPath);

			// Archivo para guardar la salida del proceso
			// Construir el comando para ejecutar Python

			pb.redirectErrorStream(true);
			pb.redirectOutput(outputFile);

			Process process = pb.start();

			// Esperar a que el proceso termine
			int exitCode = process.waitFor();
			log.error("%%%%%%%%% Salida del proceso: " + exitCode);
			
			resultado = false;
			if (exitCode == 0) {
				resultado = true;
			}			
			
			if (exitCode == -1 || exitCode == -3) {
				result.setStatus("ERROR");
				result.setMessage(messagesService.getMessage("ERROR_REPSE_GENERAL", session.getCveIdioma()));
				log.error(">>>>>>>>>>>>>>>>>>>> Error en la ejecución pythonExecutor: Error al botener la información. ");
			}
			
			if (exitCode == -2) {
				result.setStatus("ERROR_ALTERNO");
				result.setMessage(messagesService.getMessage("ERROR_REPSE_NO_ENCONTRADO", session.getCveIdioma()));
				log.error(">>>>>>>>>>>>>>>>>>>> Error en la ejecución pythonExecutor: NO HAY DATOS. ");
			}

			
			log.info("%%%%%%%%% Resultado guardado en: " + outputFile.getAbsolutePath());
		} catch (Exception e) {
			log.error(">>>>>>>>>>>>>>>>>>>> Error en la ejecución pythonExecutor: " + e.getMessage());
		}
		//archivo.delete();
		log.info(">>>>>>>>>>>>>>>>>>>> pythonExecutor <<<<<<<<<<<<<<<<<< {}:", result);
		return result;
	}
}


