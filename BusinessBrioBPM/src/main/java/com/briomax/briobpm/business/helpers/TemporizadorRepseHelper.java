package com.briomax.briobpm.business.helpers;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.business.helpers.base.IFechaHelper;
import com.briomax.briobpm.business.helpers.base.ITemporizadorRepseHelper;
import com.briomax.briobpm.common.core.Constants;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.CrCorreo;
import com.briomax.briobpm.persistence.entity.CrCorreoPK;
import com.briomax.briobpm.persistence.entity.CrDefinicionPeriocidad;
import com.briomax.briobpm.persistence.entity.CrDefinicionPeriocidadPK;
import com.briomax.briobpm.persistence.entity.CrNotificacion;
import com.briomax.briobpm.persistence.entity.CrNotificacionPK;
import com.briomax.briobpm.persistence.entity.CrPeriodicidad;
import com.briomax.briobpm.persistence.entity.CrProcesoPeriodico;
import com.briomax.briobpm.persistence.entity.CrProcesoPeriodicoPK;
import com.briomax.briobpm.persistence.entity.CrProgramacionProceso;
import com.briomax.briobpm.persistence.entity.CrProgramacionProcesoPK;
import com.briomax.briobpm.persistence.entity.InVariableProceso;
import com.briomax.briobpm.persistence.entity.LocalidadEntidad;
import com.briomax.briobpm.persistence.entity.ParametroGeneral;
import com.briomax.briobpm.persistence.repository.ICrCorreoRepository;
import com.briomax.briobpm.persistence.repository.ICrDefinicionPeriocidadRepository;
import com.briomax.briobpm.persistence.repository.ICrNotificacionRepository;
import com.briomax.briobpm.persistence.repository.ICrPeriodicidadRepository;
import com.briomax.briobpm.persistence.repository.ICrProcesoPeriodicoRepository;
import com.briomax.briobpm.persistence.repository.ICrProgramacionProcesoRepository;
import com.briomax.briobpm.persistence.repository.IInVariableProcesoRepository;
import com.briomax.briobpm.persistence.repository.ILocalidadEntidadRepository;
import com.briomax.briobpm.persistence.repository.IParametroGeneralRepository;
import com.briomax.briobpm.transferobjects.repse.DefinicionProcesoTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class TemporizadorRepseHelper implements ITemporizadorRepseHelper {


	/** El atributo o variable Entidad service. */
	@Autowired
	private ICrDefinicionPeriocidadRepository crDefinicionPeriocidadRepository;
	
	/** El atributo Parametro General Repository service. */
	@Autowired
	private IParametroGeneralRepository parametroGeneralRepository;
	
	/** El atributo o variable Entidad service. */
	@Autowired
	private ILocalidadEntidadRepository localidadEntidadRepository;
	
	/** El atributo o variable in Variable Proceso Repository. */
	@Autowired
	private IInVariableProcesoRepository inVariableProcesoRepository;
	
	/** El atributo o variable Programacion Proceso Repository. */
	@Autowired
	private ICrProgramacionProcesoRepository crProgramacionProcesoRepository;

	/** El atributo o variable Programacion Proceso Repository. */
	@Autowired
	private ICrProcesoPeriodicoRepository crProcesoPeriodicoRepository;
	
	/** El atributo o variable Parametro General Repository. */
	@Autowired
    private ICrPeriodicidadRepository periodicidadRepository;
	
	@Autowired
    private ICrNotificacionRepository crNotificacionRepository;
    
	@Autowired
    private ICrCorreoRepository crCorreoRepository;
	
	@Autowired
    private IFechaHelper fechaHelper;

	private List<DefinicionProcesoTO> nuevosProcesos;

	@Override
	public void agregarNuevosProcesos(String claseContrato){
		/*
		 * ============================================================================
		 * = TEMPORIZADOR_CREAR_PERIODOS_CONTRATOS *
		 * ============================================================================
		 */
		try {
			Integer diasConfigurados = obtenerDiasLimite();
	
	        // Buscar el parámetro general de días límite
			List<ParametroGeneral> documentos = new ArrayList<ParametroGeneral>();
			if (claseContrato.contentEquals("OBRA")) { 
				documentos = parametroGeneralRepository.documetosObligatorios("DOC_OBLIGATORIO_OBRA%"); 
			} else  {
				documentos = parametroGeneralRepository.documetosObligatorios("DOC_OBLIGATORIO_SERV%");
			}
			
		    
			String stHabilitado = "HABILITADO";
			String situacionProgramacion = "PROGRAMADO";
	
	        
	        // Verificar si el parámetro general está
	        if (documentos.size() == 0) {
	        	log.error("No se tiene documentos en parametros generales pde documentos obligatorios");
	        	return;
	        }
			
			//Obtiene las entidades
			List<LocalidadEntidad> lista = localidadEntidadRepository.entidadesHabilitadas();
			for (LocalidadEntidad ite : lista) {
				log.debug("---------- Entidad a procesar: " + ite.getId().getCveEntidad() + ", " +
						ite.getId().getCveLocalidad() + ", " + ite.getIdioma().getCveIdioma() );	
										
				for (ParametroGeneral doc : documentos) {
					String[] datos = doc.getValorAlfanumerico().split("\\|");
					String periodicida = datos[1];
					String procesoPeriodico = datos[0];
					String tipoProceso =  datos[2];
					String aplicaInicio = datos[3];
					if (doc.getCveParametro() != null && doc.getCveParametro().contains("CONSULTA")) {
						situacionProgramacion = "CONSULTA";
					} else {
						situacionProgramacion = "PROGRAMADO";
					}
					
				
					nuevosProcesos = new ArrayList<DefinicionProcesoTO>();		
					// Obtengo el rango de fechas para ver si esta dentro de contrato	
					getContratosValidos(ite.getId().getCveEntidad(), ite.getId().getCveLocalidad(), ite.getIdioma().getCveIdioma(), claseContrato, procesoPeriodico);

					CrProcesoPeriodicoPK idPp = CrProcesoPeriodicoPK.builder()
							.cveEntidad(ite.getId().getCveEntidad())
							.cveIdioma(ite.getIdioma().getCveIdioma())
							.cveLocalidad(ite.getId().getCveLocalidad())
							.cveProcesoPeriodico(procesoPeriodico)
							.build();
					Optional<CrProcesoPeriodico> procesoP = crProcesoPeriodicoRepository.findById(idPp);
					
					if (!procesoP.isPresent()) {
						log.debug("No se tiene CrProcesoPeriodico definidos para: " + ite.getId().getCveEntidad() + ", " +
								ite.getId().getCveLocalidad() + ", " + ite.getIdioma().getCveIdioma() );
			        	//return;
					} else {
					
					log.info("---------- 1.1 Crea peridos . --------");	
					CrProcesoPeriodico proPeriodico = procesoP.get();
					
			        // Buscar la periodicidad para generar la defición y ejecutar los periodos
			        Optional<CrPeriodicidad> periodicidadOp = periodicidadRepository.findById(periodicida);		       
			        if (periodicidadOp.isPresent()) {
			        	CrPeriodicidad periodicidad = periodicidadOp.get();
						
			        	log.info("---------- 1.2 nuevosProcesos: " + nuevosProcesos.size());
			        	for (DefinicionProcesoTO to : nuevosProcesos) {
			        		
			        		
				        	CrDefinicionPeriocidadPK id = CrDefinicionPeriocidadPK.builder()			    
				        					.cveEntidad(to.getCveEntidad())			        					
				        					.cveLocalidad(to.getCveLocalidad())
				        					.cveIdioma(to.getCveIdioma())
				        					.cvePeriodicidad(periodicida)
				        					.cveProcesoPeriodico(procesoPeriodico)
				        					.contrato(to.getContrato())
				        					.rfc(to.getRfc())
				        					.build();
				        	log.info("---------- 1.3 busca configuración: " + id.toString());
				        	Optional<CrDefinicionPeriocidad> opDefinicionPeriocidad = crDefinicionPeriocidadRepository.findById(id);
				        	
				        	if (!opDefinicionPeriocidad.isPresent()) {	
								CrDefinicionPeriocidad entity = CrDefinicionPeriocidad.builder()
					        				.id(id)
					        				.notificaccionContinua("NO")
					        				.diasParaNotificar(diasConfigurados)
					        				.desde(to.getFechaIncio())
					        				.hasta(to.getFechaFin())
					        				.secDefinicionAntes(1)
					        				.secDefinicionDia(2)
					        				.secDefinicionDespues(3)
					        				.situacion(stHabilitado)
					        				.aplicaInicio(aplicaInicio)
					        				.build();
								
								crDefinicionPeriocidadRepository.saveAndFlush(entity);
								log.info("---------- 1.3.1 Se crea crDefinicionPeriocida: " + entity.toString());
								log.info("---------- 1.3.1 Crea Notificación ");
								
								for (int i = 1; i < 4; i++) {
									CrCorreoPK idc = CrCorreoPK.builder()
											.cveEntidad(to.getCveEntidad())
											.cveIdioma(to.getCveIdioma())
											.cveLocalidad(to.getCveLocalidad())
											.secuenciaCorreo(i)
											.build();
									Optional<CrCorreo> correo = crCorreoRepository.findById(idc);
									if (correo.isPresent()) {
										String quienAplica = "CONTRATANTE";
										if (proPeriodico.getAplicaContratista().equals("SI")){
											quienAplica = "CONTRATISTA";
										}
										
										CrNotificacionPK idcrn = CrNotificacionPK.builder()
												.cveEntidad(to.getCveEntidad())
												.cveIdioma(to.getCveIdioma())
												.cveLocalidad(to.getCveLocalidad())
												.cveProcesoPeriodico(procesoPeriodico)
												.contrato(to.getContrato())
												.secuenciaCorreo(i)
												.ejecutor(quienAplica)
												.rfc(to.getRfc())
												.build();
										CrNotificacion crn = CrNotificacion.builder()
												.id(idcrn)
												.asunto(correo.get().getAsunto())
												.cuerpo(correo.get().getCuerpo())
												.descripcion(correo.get().getAsunto())
												.build();
										
										crNotificacionRepository.saveAndFlush(crn);										
									}
								}							
								opDefinicionPeriocidad = crDefinicionPeriocidadRepository.findById(id);
				        	}
							
							if (opDefinicionPeriocidad.isPresent()) {							
								
								CrDefinicionPeriocidad definicionPeriocidad = opDefinicionPeriocidad.get();
								
								String quienAplica = "CONTRATANTE";
								if (proPeriodico.getAplicaContratista().equals("SI")){
									quienAplica = "CONTRATISTA";
								}
								
								//Se llama a la repeticion de fechas
								String cveProceso =  to.getCveProceso();
								BigDecimal version = to.getVersion();
								int secuencia = 1;
								List<Date> fechas;
	
								fechas = fechaHelper.leeFechaRepeticion(periodicidad, definicionPeriocidad, 
										cveProceso, version, tipoProceso, aplicaInicio);
								log.info("---------- 1.4 periodos definidos: " + fechas.size());
								if (fechas.size() > 0) {
									for (Date fecha : fechas) {
										
								        Calendar calendar = Calendar.getInstance();
								        calendar.setTime(fecha);
								        calendar.add(Calendar.DAY_OF_MONTH, -diasConfigurados); 
		
								        Date inicialNotificacion = calendar.getTime();
								        
										CrProgramacionProcesoPK idPP = CrProgramacionProcesoPK.builder()
					        					.cveEntidad(to.getCveEntidad())			        					
					        					.cveLocalidad(to.getCveLocalidad())
					        					.cveIdioma(to.getCveIdioma())
					        					.cvePeriodicidad(periodicida)
					        					.cveProcesoPeriodico(procesoPeriodico)
					        					.contrato(to.getContrato())
					        					.rfc(to.getRfc())
					        					.fechaProgramacion(fecha)
					        					.secuenciaProgramacion(secuencia)
					        					.build();
										CrProgramacionProceso entityPP = CrProgramacionProceso.builder()
												.id(idPP)
												.fechaInicialNotificacion(inicialNotificacion)
												.ejecutor(quienAplica)
												.situacionUltimaNotificacion("PENDIENTE")
												.situacionEjecucion(situacionProgramacion)
												.build();
										crProgramacionProcesoRepository.saveAndFlush(entityPP);
										log.info("---------- 1.5 inserto en crProgramacionProcesoRepository: " + secuencia);
										secuencia++;
										
									}
									
									definicionPeriocidad.setSituacion("PROGRAMADO");
									definicionPeriocidad.setSituacionEnvio("HABILITADO");
									definicionPeriocidad.setAplicaInicio(aplicaInicio);
									definicionPeriocidad.setTipoEjecucion(tipoProceso);
									crDefinicionPeriocidadRepository.saveAndFlush(definicionPeriocidad);
									
								}
							}							
			        	}
			        	
			        }	
				 }	

				} 
			
			}
		} catch (BrioBPMException e) {
			log.error("Error al procesar la información error: " + e.getMensaje());
		} catch (ParseException er) {
			log.error("Error al procesar la información error: " + er.getMessage());
		}
	}
	@Override
	public void ajustaReanudacion(){
		/*
		 * ============================================================================
		 * = TEMPORIZADOR_JUSTA_PROCESOS_PERIODICOS *
		 * ============================================================================
		 */
		// Ajuste de procesos que generar depuración
		String razon = "REANUDACION"; 
		List<CrDefinicionPeriocidad> procesos = crDefinicionPeriocidadRepository.obtenProcesosReanudacion();
		procesaAjustes( procesos, razon) ;
	}

	@Override
	public void ajustaProcesos(){
		/*
		 * ============================================================================
		 * = TEMPORIZADOR_JUSTA_PROCESOS_PERIODICOS *
		 * ============================================================================
		 */
		// Ajuste de procesos que generar depuración
		/*
		 * String razon = ""; try { String cveEntidad = ""; String cveLocalidad = "";
		 * String cveIdioma = ""; String rfc = ""; String contrato = ""; String
		 * cveProcesoPeriodico = ""; String cvePeriodicidad = ""; String ejecutro= "";
		 * BigDecimal version = BigDecimal.ONE; Integer diasConfigurados =
		 * obtenerDiasLimite(); String[] parametros = null; String[]
		 * parametroNoConsiderados = null; razon = "POSTERGACION";
		 * 
		 * // Ajuste de procesos que generar depuración List<CrDefinicionPeriocidad>
		 * procesos = crDefinicionPeriocidadRepository.obtenProcesosConAjuste(razon);
		 * Optional<ParametroGeneral> docDepura =
		 * parametroGeneralRepository.findById("DOC_UNICOS_DEPURA_PROCESO"); if
		 * (docDepura.isPresent()) { parametros =
		 * docDepura.get().getValorAlfanumerico().split("\\|"); }
		 * 
		 * // Ajuste de procesos que no se consideran en ajuste de fechas
		 * 
		 * Optional<ParametroGeneral> docNoConsiderados =
		 * parametroGeneralRepository.findById("DOC_NO_APLICA_AJUSTE"); if
		 * (docNoConsiderados.isPresent()) { parametroNoConsiderados =
		 * docNoConsiderados.get().getValorAlfanumerico().split("\\|"); }
		 * 
		 * 
		 * for (CrDefinicionPeriocidad definicionPeriocidad : procesos) {
		 * 
		 * cveEntidad = definicionPeriocidad.getId().getCveEntidad(); cveLocalidad =
		 * definicionPeriocidad.getId().getCveLocalidad(); cveIdioma =
		 * definicionPeriocidad.getId().getCveIdioma(); rfc =
		 * definicionPeriocidad.getId().getRfc(); contrato =
		 * definicionPeriocidad.getId().getContrato(); cveProcesoPeriodico =
		 * definicionPeriocidad.getId().getCveProcesoPeriodico(); cvePeriodicidad =
		 * definicionPeriocidad.getId().getCvePeriodicidad();
		 * log.debug("---------- 1.1 Proceso: {} ", cveProcesoPeriodico);
		 * 
		 * Boolean aplica = true; if (parametroNoConsiderados.length > 0) { for (String
		 * clave : parametroNoConsiderados) { if (cveProcesoPeriodico.equals(clave)) {
		 * aplica = false; log.debug("---------- 1.2 No aplica ajuste "); break; } } }
		 * 
		 * if (aplica) { log.debug("---------- 1.2 Inicia aplica ajuste "); ejecutro =
		 * "CONTRATANTE"; if
		 * (definicionPeriocidad.getCrProcesoPeriodico().getAplicaContratista().equals(
		 * "SI")){ ejecutro = "CONTRATISTA"; }
		 * 
		 * log.debug("---------- 1.3 Contrato: {} - rfc: {}",contrato, rfc); boolean
		 * depura = false; List<InVariableProceso> nueVariable =
		 * inVariableProcesoRepository.obternNueFecProceso(cveEntidad, contrato,razon);
		 * if (nueVariable.size() > 0) {
		 * log.debug("---------- 1.4 Contrato con POSTERGACION");
		 * 
		 * Date fechaHastaProceso = null;
		 * 
		 * for (InVariableProceso nto : nueVariable) { if
		 * (nto.getId().getCveVariable().equals("VPRO_01_NVO_PER_AL")) {
		 * fechaHastaProceso = nto.getValorFecha(); } }
		 * 
		 * Date fechaDesde = definicionPeriocidad.getDesde(); Date fechaFinContrato =
		 * definicionPeriocidad.getHasta();
		 * 
		 * if (fechaHastaProceso.after(fechaFinContrato)) { //fechaHastaProceso esta
		 * DESPUES que fechaFinContrato List<Date> fechas; if (parametros != null) { if
		 * (parametros.length == 1 &&
		 * parametros[0].equals(definicionPeriocidad.getId().getCveProcesoPeriodico()) )
		 * { depura = true; } if (parametros.length > 1) { for (int i = 0; i <
		 * parametros.length; i++) { if
		 * (parametros[i].equals(definicionPeriocidad.getId().getCveProcesoPeriodico()))
		 * { depura = true; break; } } } }
		 * 
		 * definicionPeriocidad.setDesde(fechaFinContrato);
		 * definicionPeriocidad.setHasta(fechaHastaProceso);
		 * 
		 * // Buscar la periodicidad para generar la defición y ejecutar los periodos
		 * Optional<CrPeriodicidad> periodicidadOp =
		 * periodicidadRepository.findById(cvePeriodicidad); if
		 * (periodicidadOp.isPresent()) { CrPeriodicidad periodicidad =
		 * periodicidadOp.get();
		 * 
		 * fechas = fechaHelper.leeFechaRepeticion(periodicidad, definicionPeriocidad,
		 * cveProcesoPeriodico, version, definicionPeriocidad.getTipoEjecucion(),
		 * definicionPeriocidad.getAplicaInicio()); boolean activaDepura = true; for
		 * (Date fecha : fechas) { int secuencia = 0;
		 * 
		 * CrProgramacionProceso to =
		 * crProgramacionProcesoRepository.validaProgramacion( cveEntidad, cveLocalidad,
		 * cveIdioma, cveProcesoPeriodico, cvePeriodicidad, rfc, contrato, fecha);
		 * 
		 * if (to != null) { secuencia = to.getId().getSecuenciaProgramacion();
		 * secuencia++; } else { if (depura && activaDepura) {
		 * List<CrProgramacionProceso> borra =
		 * crProgramacionProcesoRepository.depuraProgramacion( cveEntidad, cveLocalidad,
		 * cveIdioma, cveProcesoPeriodico, cvePeriodicidad, rfc, contrato);
		 * crProgramacionProcesoRepository.deleteAll(borra); secuencia = 1; activaDepura
		 * = false; } else if (secuencia == 0) { int sec =
		 * crProgramacionProcesoRepository.maximaProgramacion( cveEntidad, cveLocalidad,
		 * cveIdioma, cveProcesoPeriodico, cvePeriodicidad, rfc, contrato);
		 * 
		 * secuencia = sec + 1; }
		 * 
		 * Calendar calendar = Calendar.getInstance(); calendar.setTime(fecha);
		 * calendar.add(Calendar.DAY_OF_MONTH, -diasConfigurados);
		 * 
		 * Date inicialNotificacion = calendar.getTime();
		 * 
		 * CrProgramacionProcesoPK idPP = CrProgramacionProcesoPK.builder()
		 * .cveEntidad(cveEntidad) .cveLocalidad(cveLocalidad) .cveIdioma(cveIdioma)
		 * .cvePeriodicidad(cvePeriodicidad) .cveProcesoPeriodico(cveProcesoPeriodico)
		 * .contrato(contrato) .rfc(rfc) .fechaProgramacion(fecha)
		 * .secuenciaProgramacion(secuencia) .build(); CrProgramacionProceso entityPP =
		 * CrProgramacionProceso.builder() .id(idPP)
		 * .fechaInicialNotificacion(inicialNotificacion) .ejecutor(ejecutro)
		 * .situacionUltimaNotificacion("PENDIENTE") .situacionEjecucion("PROGRAMADO")
		 * .build();
		 * 
		 * crProgramacionProcesoRepository.saveAndFlush(entityPP); secuencia++; }
		 * 
		 * } //actualiza con la fecha del nuevo periodo
		 * definicionPeriocidad.setDesde(fechaDesde);
		 * crDefinicionPeriocidadRepository.saveAndFlush(definicionPeriocidad); } } } }
		 * }
		 * 
		 */
		String cveEntidad = ""; 
		String contrato = "";
		String razon = "POSTERGACION";
		
		List<CrDefinicionPeriocidad> procesos = crDefinicionPeriocidadRepository.obtenProcesosConAjuste(razon);		
		if(procesos.size()>0)
			procesaAjustes( procesos, razon);
		
    			// Ajuste de procesos que generar depuración
				List<CrDefinicionPeriocidad> otrosProcesos = crDefinicionPeriocidadRepository.obtenOtrosProcesos();
	            for (CrDefinicionPeriocidad definicionPeriocidad1 : otrosProcesos) {
	            	cveEntidad = definicionPeriocidad1.getId().getCveEntidad();
	            	contrato = definicionPeriocidad1.getId().getContrato();
	            	
            		List<InVariableProceso> cancelaciones = inVariableProcesoRepository.obternCalcelaProceso(cveEntidad, contrato);
    				if (cancelaciones.size() > 0) {
    					log.debug("---------- 1.4 entro en procesos de cancelación");
    					String nueEstatus = "CANCELACION";
    					Date fechaCancela = null;

    					for (InVariableProceso nto : cancelaciones) {
    					    if (nto.getId().getCveVariable().equals("VPRO_01_NVO_PER_AL")) {
    					    	fechaCancela = nto.getValorFecha();
    					    }
    					    if (nto.getId().getCveVariable().equals("VPRO_01_RAZON")) {
    					    	nueEstatus = nto.getValorAlfanumerico();
    					    }
    					}    					

    					if (validar(fechaCancela, definicionPeriocidad1.getDesde())) {
    					    List<CrProgramacionProceso> to = crProgramacionProcesoRepository.depuraProgramacion(
    					        definicionPeriocidad1.getId().getCveEntidad(),
    					        definicionPeriocidad1.getId().getCveLocalidad(),
    					        definicionPeriocidad1.getId().getCveIdioma(),
    					        definicionPeriocidad1.getId().getCveProcesoPeriodico(),
    					        definicionPeriocidad1.getId().getCvePeriodicidad(),
    					        definicionPeriocidad1.getId().getRfc(),
    					        definicionPeriocidad1.getId().getContrato()
    					    );

    					    for (CrProgramacionProceso proceso : to) {
    					        proceso.setSituacionEjecucion(nueEstatus);
    					        crProgramacionProcesoRepository.saveAndFlush(proceso);
    					    }
    					}
    						
    					if (fechaCancela != null) {
    						definicionPeriocidad1.setSituacionEnvio(nueEstatus);
    						definicionPeriocidad1.setFechaCancelaEnvio(fechaCancela);
    						crDefinicionPeriocidadRepository.saveAndFlush(definicionPeriocidad1);
    					}
    				}
    			}			
		
	}	
	            
	   private  boolean validar(Date fechaCancela, Date fechaDesde) {
	                // Sumar 1 día a fechaDesde
	                long unDiaEnMs = 24L * 60 * 60 * 1000;
	                Date fechaDesdeMasUnDia = new Date(fechaDesde.getTime() + unDiaEnMs);

	                // Validar
	                return !fechaCancela.after(fechaDesdeMasUnDia);
	  }

	@Override
	public void procesosManuales(){
		/*
		 * ============================================================================
		 * = TEMPORIZADOR_CREA_PROCESOS_PERIODICOS_MANUALES *
		 * ============================================================================
		 */
		try {
			String cveEntidad = "";
			String cveLocalidad = "";
			String cveIdioma = "";
			String rfc = "";
			String contrato = "";
			String cveProcesoPeriodico = "";
			String cvePeriodicidad = "";
			String ejecutor = "";
			BigDecimal version = BigDecimal.ONE;
			
			List<CrDefinicionPeriocidad> procesos = crDefinicionPeriocidadRepository.obtenProcesosProgramados();
			
        	for (CrDefinicionPeriocidad definicionPeriocidad : procesos) {
        		
    			cveEntidad = definicionPeriocidad.getId().getCveEntidad();
    			cveLocalidad = definicionPeriocidad.getId().getCveLocalidad();
    			cveIdioma = definicionPeriocidad.getId().getCveIdioma();
    			rfc = definicionPeriocidad.getId().getRfc();
    			contrato = definicionPeriocidad.getId().getContrato();
    			cveProcesoPeriodico = definicionPeriocidad.getId().getCveProcesoPeriodico();
    			cvePeriodicidad = definicionPeriocidad.getId().getCvePeriodicidad();
				ejecutor	 = "CONTRATANTE";
				if (definicionPeriocidad.getCrProcesoPeriodico().getAplicaContratista().equals("SI")){
					ejecutor = "CONTRATISTA";
				}

				Date fechaDesde = definicionPeriocidad.getDesde();
				Date fechaHasta = definicionPeriocidad.getHasta();
				
				log.debug("---------- 1 Contrato: {} - rfc: {}",contrato, rfc);
				
				if (fechaDesde != null && fechaHasta != null) {
					
			        Optional<CrPeriodicidad> periodicidadOp = periodicidadRepository.findById(cvePeriodicidad);		       
			        if (periodicidadOp.isPresent()) {
			        	CrPeriodicidad periodicidad = periodicidadOp.get();
			        	
			        	List<Date> fechas = fechaHelper.leeFechaRepeticion(periodicidad, definicionPeriocidad, 
							cveProcesoPeriodico, version, definicionPeriocidad.getTipoEjecucion(), definicionPeriocidad.getAplicaInicio());	
						log.info("---------- 1.1 periodos definidos: " + fechas.size());
						if (fechas.size() > 0) {
							int secuencia = 1;
							for (Date fecha : fechas) {
								
						        Calendar calendar = Calendar.getInstance();
						        calendar.setTime(fecha);
						        calendar.add(Calendar.DAY_OF_MONTH, - definicionPeriocidad.getDiasParaNotificar()); 

						        Date inicialNotificacion = calendar.getTime();
						        
								CrProgramacionProcesoPK idPP = CrProgramacionProcesoPK.builder()
			        					.cveEntidad(cveEntidad)			        					
			        					.cveLocalidad(cveLocalidad)
			        					.cveIdioma(cveIdioma)
			        					.cvePeriodicidad(cvePeriodicidad)
			        					.cveProcesoPeriodico(cveProcesoPeriodico)
			        					.contrato(contrato)
			        					.rfc(rfc)
			        					.fechaProgramacion(fecha)
			        					.secuenciaProgramacion(secuencia)
			        					.build();
								CrProgramacionProceso entityPP = CrProgramacionProceso.builder()
										.id(idPP)
										.fechaInicialNotificacion(inicialNotificacion)
										.ejecutor(ejecutor)
										.situacionUltimaNotificacion("PENDIENTE")
										.situacionEjecucion("PROGRAMADO")
										.build();
								crProgramacionProcesoRepository.saveAndFlush(entityPP);
								log.info("---------- 1.5 inserto en crProgramacionProcesoRepository: " + secuencia);
								secuencia++;
								
							}
							
							definicionPeriocidad.setSituacion("PROGRAMADO");
							definicionPeriocidad.setSituacionEnvio("HABILITADO");
							crDefinicionPeriocidadRepository.saveAndFlush(definicionPeriocidad);
							
						}
			        }
				}
        	}
				
			
			
		} catch (BrioBPMException e) {
			log.error("Error al procesar la información error: " + e.getMensaje());
		} catch (ParseException er) {
			log.error("Error al procesar la información error: " + er.getMessage());
		}
	}	

	
	
	private void getContratosValidos(String cveEntidad, String cveLocalidad, String  cveIdioma, String claseContrato, String procesoPeriodico) {
		List<InVariableProceso> inVariablePros = inVariableProcesoRepository.obtenerContratosValidos(cveEntidad, claseContrato);

		Date fechaIncio = null;
		Date fechaFin = null;
		String contrato = "";
		String rfc = "";
		String instancia = "";
		String cveProceso = "";
		String variable = "";
		BigDecimal ver = null;
		
		if (inVariablePros.size() > 0) {
			log.info("---------- 1. Carga información . --------");		
			for(InVariableProceso ite: inVariablePros) {
				
				if (!instancia.equals(ite.getId().getCveInstancia())) {					
					if (!instancia.equals("")) {						
						log.info("---------- 2. Valida Contrato: {} y RFC: {}. --------", contrato, rfc);
						if (fechaIncio != null  && fechaFin != null ) {
							Integer proProgramados = crProgramacionProcesoRepository.
									existeContratoProgramnado(cveEntidad, cveLocalidad,  
											rfc, contrato, procesoPeriodico);
							DefinicionProcesoTO proceso = DefinicionProcesoTO.builder()
									.cveEntidad(cveEntidad)
									.cveLocalidad(cveLocalidad)
									.cveIdioma(cveIdioma)
									.rfc(rfc)
									.contrato(contrato)
									.fechaIncio(fechaIncio)
									.fechaFin(fechaFin)
									.cveProceso(cveProceso)
									.version(ver)
									.build();
							
							if (proProgramados == 0) {
								nuevosProcesos.add(proceso);
							} 
						}				

					}
					instancia = ite.getId().getCveInstancia();
					cveProceso = ite.getId().getCveProceso();
				} 

				ver = ite.getId().getVersion();
				variable = ite.getId().getCveVariable();
				if (variable.equals("VPRO_01_NUM_CONTRATO")) {
					contrato = ite.getValorAlfanumerico();		
				}
				if (variable.equals("VPRO_01_PERIODO_EJECUCION_DEL_CONTRATISTA")) {
					fechaIncio = ite.getValorFecha();
				}
				if (variable.equals("VPRO_01_PERIODO_EJECUCION_AL_CONTRATISTA")) {
					fechaFin = ite.getValorFecha();
				}
				if (variable.equals("VPRO_01_RFC_CONTRATISTA")) {
					rfc = ite.getValorAlfanumerico();
				}	
				
	        }	
			
			log.info("---------- 2. Valida Contrato: {} y RFC: {}. --------", contrato, rfc);	
			Integer proProgramados =crProgramacionProcesoRepository.existeContratoProgramnado(cveEntidad, cveLocalidad,  rfc, contrato, procesoPeriodico);
			DefinicionProcesoTO proceso = DefinicionProcesoTO.builder()
					.cveEntidad(cveEntidad)
					.cveLocalidad(cveLocalidad)
					.cveIdioma(cveIdioma)
					.rfc(rfc)
					.contrato(contrato)
					.fechaIncio(fechaIncio)
					.fechaFin(fechaFin)
					.cveProceso(cveProceso)
					.version(ver)
					.build();
			
			if (proProgramados == 0) {
				nuevosProcesos.add(proceso);
			} 
		}
	}
	
	// Obtiene los días límite para notificación
	private Integer obtenerDiasLimite() {
	    // Obtener el valor de días para notificar de la definición
	    Integer diasLimite = 5;

        // Buscar el parámetro general de días límite
        Optional<ParametroGeneral> parametroGral = parametroGeneralRepository.findById(Constants.FECHA_LIMITE_DIAS);
       
        // Verificar si el parámetro general está
        if (parametroGral.isPresent()) {
            // Usar el valor configurado en parámetros generales si está presente
            diasLimite = parametroGral.get().getValorEntero();
        }

	    // Retornar el valor de días límite
	    return diasLimite;
	}
	
	private void procesaAjustes(List<CrDefinicionPeriocidad> procesos, String razon) {
		try {
			String cveEntidad = "";
			String cveLocalidad = "";
			String cveIdioma = "";
			String rfc = "";
			String contrato = "";
			String cveProcesoPeriodico = "";
			String cvePeriodicidad = "";
			String ejecutro= "";
			BigDecimal version = BigDecimal.ONE;
			Integer diasConfigurados = obtenerDiasLimite();
			String[] parametros = null;
			String[] parametroNoConsiderados = null;
			String opcion1 = "REANUDACION";
			String opcion2 = "POSTERGACION";
			String opcion3 = "SUSPENSION";
			String vpro = "VPRO_01_NVO_PER_AL";

			Optional<ParametroGeneral> docDepura = parametroGeneralRepository.findById("DOC_UNICOS_DEPURA_PROCESO");
			if (docDepura.isPresent()) {
				parametros = docDepura.get().getValorAlfanumerico().split("\\|");
			}
			
			// Ajuste de procesos que no se consideran en ajuste de fechas
			
			Optional<ParametroGeneral> docNoConsiderados = parametroGeneralRepository.findById("DOC_NO_APLICA_AJUSTE");
			if (docNoConsiderados.isPresent()) {
				parametroNoConsiderados = docNoConsiderados.get().getValorAlfanumerico().split("\\|");
			}
			
			
        	for (CrDefinicionPeriocidad definicionPeriocidad : procesos) {
        		
    			cveEntidad = definicionPeriocidad.getId().getCveEntidad();
    			cveLocalidad = definicionPeriocidad.getId().getCveLocalidad();
    			cveIdioma = definicionPeriocidad.getId().getCveIdioma();
    			rfc = definicionPeriocidad.getId().getRfc();
    			contrato = definicionPeriocidad.getId().getContrato();
    			cveProcesoPeriodico = definicionPeriocidad.getId().getCveProcesoPeriodico();
    			cvePeriodicidad = definicionPeriocidad.getId().getCvePeriodicidad();
    			log.info("---------- 1.1 Proceso:  {} ---------- \n----------  "
    					+ "contrato {} ---------- \n ----------  razon {} ----------  ",
    					cveProcesoPeriodico, contrato, razon);
    			
				Boolean aplica = true;
				if (parametroNoConsiderados.length > 0) {
					for (String clave : parametroNoConsiderados) {
						if (cveProcesoPeriodico.equals(clave)) {
							aplica = false;
							log.debug("---------- 1.2 No aplica ajuste ");
							break;		
						}
					} 
				}
    			
    			if (aplica) {
    				log.debug("---------- 1.2 Inicia aplica ajuste ");
    				ejecutro = "CONTRATANTE";
    				if (definicionPeriocidad.getCrProcesoPeriodico().getAplicaContratista().equals("SI")){
    					ejecutro = "CONTRATISTA";
    				}
            		
            		log.info("---------- 1.3 Contrato: {} - rfc: {}",contrato, rfc);
            		boolean depura = false;
            		List<InVariableProceso> nueVariable = inVariableProcesoRepository.obternNueFecProceso(cveEntidad, contrato,razon);
    				if (nueVariable.size() > 0) {
    					log.debug("---------- 1.4 Contrato con razon: {}", razon);
    									
    					Date fechaHastaProceso = null;
    					Date fechaDesdeOri = definicionPeriocidad.getDesde();
    					Date fechaFinContrato = definicionPeriocidad.getHasta();
    					Date fechaDesdeSuspension  = null;
    					Date fechaDesde  = definicionPeriocidad.getDesde();
    							
    					for (InVariableProceso nto : nueVariable) {
    					    if (nto.getId().getCveVariable().equals(vpro)) {
    					    	if (razon.equals(opcion2)) {
						    		fechaHastaProceso = nto.getValorFecha();
    					    	} else if (razon.equals(opcion1)) {
    					    		List<InVariableProceso> nueVariableSuspension = inVariableProcesoRepository.obternNueFecProceso(cveEntidad, contrato,"SUSPENSION");
    					    		fechaDesdeSuspension = nueVariableSuspension.get(0).getValorFecha();
    					    		fechaDesde = nto.getValorFecha();
    					    		fechaHastaProceso = fechaFinContrato;
						    	}    					    	
    					    }
    					}   
    					log.info("---------- 1.3 fechaDesde {} fechaHastaProceso: {} - fechaFinContrato: {}",fechaDesde, fechaHastaProceso, fechaFinContrato);
    					
    						
    					if ((fechaHastaProceso != null && 
    						fechaHastaProceso.after(fechaFinContrato)) 
    							|| razon.equals(opcion1)) { //fechaHastaProceso esta DESPUES que fechaFinContrato
    						List<Date> fechas;
    						if (parametros != null) {
    							if (parametros.length == 1 && parametros[0].equals(definicionPeriocidad.getId().getCveProcesoPeriodico()) ) {
    								depura = true;
    							}
    							if (parametros.length > 1) {
    								for (int i = 0; i < parametros.length; i++) {
    									if (parametros[i].equals(definicionPeriocidad.getId().getCveProcesoPeriodico())){
    										depura = true;
    										break;
    									} 
    								} 
    							}
    						}
    							
    							definicionPeriocidad.setDesde(fechaDesde);
    							definicionPeriocidad.setHasta(fechaHastaProceso);

    					        // Buscar la periodicidad para generar la defición y ejecutar los periodos
    					        Optional<CrPeriodicidad> periodicidadOp = periodicidadRepository.findById(cvePeriodicidad);	
    					        Calendar cal = Calendar.getInstance();
    					        cal.setTime(fechaDesdeSuspension);
    					        int diaSusp = cal.get(Calendar.DAY_OF_MONTH);
    					        int mesSusp = cal.get(Calendar.MONTH);
    					        cal.setTime(fechaDesdeOri);
    					        int diaDesde = cal.get(Calendar.DAY_OF_MONTH);
    					        int mesDesde = cal.get(Calendar.MONTH);
    					        if (periodicidadOp.isPresent()) {
    					        	CrPeriodicidad periodicidad = periodicidadOp.get();
    					        	if (opcion1.equals(razon)) {
    					        		fechas = crProgramacionProcesoRepository.obtenerFechasProceso(cveEntidad, cveLocalidad, 
    					        				cveIdioma,  rfc, contrato, cveProcesoPeriodico);
    					        		String periodicidadStr = definicionPeriocidad.getId().getCvePeriodicidad().split("_")[0];
    					        		int periodo = 0;
    					        		 try {
    					        			 periodo = Integer.parseInt(periodicidadStr);    					                    
    					                 } catch (NumberFormatException e) {
    					                     continue;
    					                 }
    					        		
    					        		Date fechaHastaProcesoMasUnMes = obtenerFechaFinal(fechaDesde, periodo, Integer.parseInt(periodicidad.getDiasDelMes()));
					        			Date fechaDesdeSuspensionMasUnMes = obtenerFechaFinal(fechaDesdeSuspension, periodo, diaSusp);
    					        		int j = 0;
    					        		for (Date fecha : fechas) { 
    					        			cal.setTime(fecha);
    		    					        int diaFecha = cal.get(Calendar.DAY_OF_MONTH);
    		    					        int mesFecha = cal.get(Calendar.MONTH);
    					        			
	    					        		boolean dentroDelRango =  ( fecha.after(fechaDesdeSuspensionMasUnMes) 
	    					        				&& fecha.before(fechaHastaProcesoMasUnMes));
	    					        		if(j == 0 && (mesSusp == mesDesde  )) {
	    					        			dentroDelRango = !dentroDelRango;
	    					        		}	
	    					        		j++;
	    					        		
	    					        		if ( dentroDelRango  ) {
	    					        			CrProgramacionProceso to = crProgramacionProcesoRepository.validaProgramacion(
	        											cveEntidad,
	        											cveLocalidad,
	        											cveIdioma,
	        											cveProcesoPeriodico,
	        											cvePeriodicidad, 
	        											rfc, 
	        											contrato, 
	        											fecha);
	    					    				to.setSituacionEjecucion(opcion3);
	    					    				crProgramacionProcesoRepository.saveAndFlush(to);
	    					    			}
    					        		}
    					        	} else {
    					        		fechas = fechaHelper.leeFechaRepeticion(periodicidad, definicionPeriocidad, 
    										cveProcesoPeriodico, version, definicionPeriocidad.getTipoEjecucion(), definicionPeriocidad.getAplicaInicio());	
    					        		boolean activaDepura = true;
        								for (Date fecha : fechas) {
        									int secuencia = 0;
        	
        									CrProgramacionProceso to = crProgramacionProcesoRepository.validaProgramacion(
        											cveEntidad,
        											cveLocalidad,
        											cveIdioma,
        											cveProcesoPeriodico,
        											cvePeriodicidad, 
        											rfc, 
        											contrato, 
        											fecha);
        									
        									if (to != null) {
        										secuencia = to.getId().getSecuenciaProgramacion();
        										secuencia++;
        										
        										log.info("---------- 1.4 Proceso ya programado: \n fechaDesdeSuspension {} - \n to.getId().getFechaProgramacion(){} -\n fechaDesde {} - -------",
        												fechaDesdeSuspension, to.getId().getFechaProgramacion(),fechaDesde);
        										
        									} else {
        										if (depura && activaDepura) {
        											List<CrProgramacionProceso> borra = crProgramacionProcesoRepository.depuraProgramacion(
        													cveEntidad,
        													cveLocalidad,
        													cveIdioma,
        													cveProcesoPeriodico,
        													cvePeriodicidad, 
        													rfc, 
        													contrato);
        											crProgramacionProcesoRepository.deleteAll(borra);
        											secuencia = 1;
        											activaDepura = false;
        										} else if (secuencia == 0) {
        												int sec =	crProgramacionProcesoRepository.maximaProgramacion(
        														cveEntidad,
        														cveLocalidad,
        														cveIdioma,
        														cveProcesoPeriodico,
        														cvePeriodicidad, 
        														rfc, 
        														contrato);
        												
        												secuencia = sec + 1;
        										}
        																				
        								        Calendar calendar = Calendar.getInstance();
        								        calendar.setTime(fecha);
        								        calendar.add(Calendar.DAY_OF_MONTH, -diasConfigurados); 
        		
        								        Date inicialNotificacion = calendar.getTime();
        								        
        										CrProgramacionProcesoPK idPP = CrProgramacionProcesoPK.builder()
        					        					.cveEntidad(cveEntidad)			        					
        					        					.cveLocalidad(cveLocalidad)
        					        					.cveIdioma(cveIdioma)
        					        					.cvePeriodicidad(cvePeriodicidad)
        					        					.cveProcesoPeriodico(cveProcesoPeriodico)
        					        					.contrato(contrato)
        					        					.rfc(rfc)
        					        					.fechaProgramacion(fecha)
        					        					.secuenciaProgramacion(secuencia)
        					        					.build();
        										CrProgramacionProceso entityPP = CrProgramacionProceso.builder()
        												.id(idPP)
        												.fechaInicialNotificacion(inicialNotificacion)
        												.ejecutor(ejecutro)
        												.situacionUltimaNotificacion("PENDIENTE")
        												.situacionEjecucion("PROGRAMADO")
        												.build();
        										
        										crProgramacionProcesoRepository.saveAndFlush(entityPP);
        										secuencia++;
        									}

        								}
        								
    					        	}
    						
    								//actualiza con la fecha del nuevo periodo
    							
    					        	definicionPeriocidad.setDesde(fechaDesde);
    								if (razon.equals(opcion1)) {
										definicionPeriocidad.setSituacion("PROGRAMADO");
										definicionPeriocidad.setSituacionEnvio("HABILITADO");
    								}
    								crDefinicionPeriocidadRepository.saveAndFlush(definicionPeriocidad);
    					        }		
    						}	
    				}	
    			}
        	}
	}
		catch (BrioBPMException e) {
			log.error("Error al procesar la información error: " + e.getMensaje());
		} catch (ParseException er) {
			log.error("Error al procesar la información error: " + er.getMessage());
		}
}
	
	public Date obtenerFechaFinal(Date fecha, int periodo, int dias) {
	    LocalDate localDate = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	    int mes = localDate.getMonthValue();
	    int anio = localDate.getYear();
	    int mesFinal = mes;
	    int anioFinal = anio;

	    switch (periodo) {
	        case 4:
	            if (mes >= 1 && mes <= 4) {
	                mesFinal = 5;
	            } else if (mes >= 5 && mes <= 8) {
	                mesFinal = 9;
	            } else {
	                mesFinal = 1;
	                anioFinal = anio + 1;
	            }
	            break;

	        case 2:
	            if (mes >= 1 && mes <= 2) {
	                mesFinal = 3;
	            } else if (mes >= 3 && mes <= 4) {
	                mesFinal = 5;
	            } else if (mes >= 5 && mes <= 6) {
	                mesFinal = 7;
	            } else if (mes >= 7 && mes <= 8) {
	                mesFinal = 9;
	            } else if (mes >= 9 && mes <= 10) {
	                mesFinal = 11;
	            } else {
	                mesFinal = 1;
	                anioFinal = anio + 1;
	            }
	            break;

	        case 1:
	            mesFinal = mes + 1;
	            if (mesFinal == 13) {
	                mesFinal = 1;
	                anioFinal = anio + 1;
	            }
	            break;

	        default:
	            return fecha;
	    }

	    // último día del mes_final
	    YearMonth ym = YearMonth.of(anioFinal, mesFinal);

	    // si 'dias' es mayor al último día del mes, se ajusta al último día
	    int diaValido = Math.min(dias, ym.lengthOfMonth());

	    LocalDate fechaFinal = ym.atDay(diaValido);
	    return Date.from(fechaFinal.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}


}
