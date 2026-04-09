package com.briomax.briobpm.business.helpers;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.business.helpers.base.IFechaHelper;
import com.briomax.briobpm.business.service.catalogs.core.IMessagesService;
import com.briomax.briobpm.common.core.Constants;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.CalendarioLocalidad;
import com.briomax.briobpm.persistence.entity.CalendarioLocalidadPK;
import com.briomax.briobpm.persistence.entity.CrDefinicionPeriocidad;
import com.briomax.briobpm.persistence.entity.CrPeriodicidad;
import com.briomax.briobpm.persistence.entity.HusoHorario;
import com.briomax.briobpm.persistence.entity.InhabilCalendarioPK;
import com.briomax.briobpm.persistence.entity.LocalidadEntidad;
import com.briomax.briobpm.persistence.entity.LocalidadEntidadPK;
import com.briomax.briobpm.persistence.entity.ParametroGeneral;
import com.briomax.briobpm.persistence.entity.StNodoProceso;
import com.briomax.briobpm.persistence.repository.ICalendarioLocalidadRepository;
import com.briomax.briobpm.persistence.repository.ICatalogoEtiquetaRepository;
import com.briomax.briobpm.persistence.repository.IInhabilCalendarioRepository;
import com.briomax.briobpm.persistence.repository.ILocalidadEntidadRepository;
import com.briomax.briobpm.persistence.repository.IParametroGeneralRepository;
import com.briomax.briobpm.persistence.repository.IStNodoProcesoRepository;
import com.briomax.briobpm.transferobjects.CalendarioLocalidadTO;
import com.briomax.briobpm.transferobjects.in.CalculoFechaLimiteTO;
import com.briomax.briobpm.transferobjects.in.CalculoFechaMasPlazoTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.EstatusFechaTO;
import com.briomax.briobpm.transferobjects.in.EstatusTO;
import com.briomax.briobpm.transferobjects.repse.GrupoFechaIntervalosTO;
import com.briomax.briobpm.transferobjects.repse.IntervaloFechaTO;
import com.briomax.briobpm.transferobjects.repse.OrdenFechaIntervalosTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class FechaHelper implements IFechaHelper {

	/** El atributo o variable messages service. */
	@Autowired
	private IMessagesService messagesService;

	/** El atributo o variable folio Proceso. */
	@Autowired
	private IStNodoProcesoRepository stNodoProcesoRepository;

	/** El atributo o variable folio Calendario Localidad. */
	@Autowired
	private ICalendarioLocalidadRepository calendarioLocalidadRepository;

	/** El atributo o variable flocalidad Entidad */
	@Autowired
	private ILocalidadEntidadRepository localidadEntidadRepository;

	/** El atributo o variable flocalidad Entidad */
	@Autowired
	private IParametroGeneralRepository parametroGeneralRepository;

	/** El atributo o variable flocalidad Entidad */
	@Autowired
	private IInhabilCalendarioRepository inhabilCalendarioRepository;
	
	/** El atributo o variable catalogo Etiqueta */
	@Autowired
	private ICatalogoEtiquetaRepository iCatalogoEtiquetaRepository;

	/** La tabla temporal Calendario Localidad */
	//List<CalendarioLocalidadTO> calendarioLocalidad = new ArrayList<>();
	

	// SP_LEE_FECHAS_REPETICION
	@Override
	public List<Date> leeFechaRepeticion(CrPeriodicidad periodicidad, CrDefinicionPeriocidad difinicion, String cveProceso, BigDecimal version, String tipoProceso, String aplicaInicio)
			throws BrioBPMException, ParseException {
		
		String cveEntidad = difinicion.getId().getCveEntidad();
		String cveLocalidad = difinicion.getId().getCveLocalidad();
		String cveIdioma = difinicion.getId().getCveIdioma();

		
		IntervaloFechaTO ifTo = IntervaloFechaTO.builder()
				.cveProceso(cveProceso)
				.version(version)
				.cadaNperiodos(periodicidad.getCadaNPeriodos())
				.periodoTiempo(periodicidad.getPeriodoTiempo())
				.detallePeriodo(periodicidad.getDetallePeriodo())
				.diasDeLaSemana( periodicidad.getDiasDeLaSemana())
				.diasDelMes(periodicidad.getDiasDelMes())
				.tipoDia(periodicidad.getTipoDia())
				.desde(difinicion.getDesde())
				.hasta(difinicion.getHasta())
				.build();
		
		//SP_LEE_INTERVALO_FECHAS
		List<OrdenFechaIntervalosTO> interFech = new ArrayList<OrdenFechaIntervalosTO>();	
		List<OrdenFechaIntervalosTO> fechasIntervalo  = new ArrayList<OrdenFechaIntervalosTO>();
		List<Date> fechaRepeticion = new ArrayList<Date>();
		
		if (tipoProceso.equals("NORMAL")) {
			interFech = leeIntervaloFecha(cveEntidad, ifTo);
			
			fechasIntervalo  = generaFechasIntervalo(ifTo, interFech);
			
			fechaRepeticion =  ajustaFechasIntervalo(cveEntidad, cveLocalidad, cveIdioma, cveProceso, 
					version, periodicidad.getTipoDia(), fechasIntervalo);	
		} else {
			interFech = leeIntervaloFechaRepse(cveEntidad, ifTo, aplicaInicio);

			if (ifTo.getPeriodoTiempo().equals("MES")) {
				fechasIntervalo  = generaFechasIntervaloRepse(ifTo, interFech);
			} else {
				fechasIntervalo  = generaFechasIntervalo(ifTo, interFech);
			}
			
			fechaRepeticion =  ajustaFechasIntervalo(cveEntidad, cveLocalidad, cveIdioma, cveProceso, 
					version, periodicidad.getTipoDia(), fechasIntervalo);
		}
	

		return fechaRepeticion;
	}
	
	//SP_LEE_INTERVALO_FECHAS
	@Override
	public List<OrdenFechaIntervalosTO> leeIntervaloFecha(String cveEntidad, IntervaloFechaTO datos)
			throws BrioBPMException {
		List<OrdenFechaIntervalosTO> interFech = new ArrayList<OrdenFechaIntervalosTO>();	
		Date desde = datos.getDesde();
		Date hasta = datos.getHasta();
		Integer nPeriodos = datos.getCadaNperiodos();
		String periodoTiempo = datos.getPeriodoTiempo();
		
		String tipoPeriodoDia = "DIA";
		String tipoPeriodoSemana = "SEMANA";
		String tipoPeriodoMes = "MES";
		Boolean finCiclo;
		Date   fechaProcesar;
		Date   fechaIniProcesar = null;
		Date   fechaFinProcesar = null;
		int secuencia = 1;
		
		finCiclo = false;
		fechaProcesar = desde;
		
		//GENERA LOS INTERVALOS DE FECHAS
		while (!finCiclo) {
			
			LocalDateTime now = LocalDateTime.now(); 
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
			DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm"); 
			String formattedDate = now.format(dateFormatter); 
			String formattedTime = now.format(timeFormatter);
			DayOfWeek dayOfWeek =  now.getDayOfWeek(); 
			// En Java, el primer día de la semana es el lunes (valor 1) y el domingo es el valor 7 
			int dayOfWeekValue = dayOfWeek.getValue(); 
			// Convertir el valor numérico a una cadena 
			String dayString = Integer.toString(dayOfWeekValue);
			
			Calendar calendar = Calendar.getInstance();
			
			//DETERMINA FECHA INICIAL Y FINAL DEL PERIODO DE REPETICIÓN (DIA, SEMANA, MES)
			fechaIniProcesar = fechaProcesar;
			
			
			
			if (periodoTiempo.equals(tipoPeriodoDia)) {
				
				calendar.setTime(fechaIniProcesar);
				calendar.add(Calendar.DAY_OF_MONTH, (nPeriodos -1));
				
				fechaFinProcesar = calendar.getTime();
				
			}else if(periodoTiempo.equals(tipoPeriodoSemana)){
				
				
				calendar.setTime(fechaProcesar);
				calendar.add(Calendar.WEEK_OF_YEAR, nPeriodos);
								
				fechaFinProcesar = calendar.getTime();
				
				calendar.setTime(fechaFinProcesar);
				calendar.add(Calendar.DAY_OF_MONTH, -1);
								
				fechaFinProcesar = calendar.getTime();
						
				
			}else if(periodoTiempo.equals(tipoPeriodoMes)) {
				
				calendar.setTime(fechaProcesar);
				calendar.add(Calendar.MONTH, nPeriodos);
								
				fechaFinProcesar = calendar.getTime();
				
				calendar.setTime(fechaFinProcesar);
				calendar.add(Calendar.DAY_OF_MONTH, -1);
								
				fechaFinProcesar = calendar.getTime();
			}
		
			OrdenFechaIntervalosTO intFech = OrdenFechaIntervalosTO.builder()
					.secuencia(secuencia)
					.fechaInicial(fechaIniProcesar)
					.fechaFinal(fechaFinProcesar)					
					.build();
			interFech.add(intFech);	
			
			secuencia++;
			
			calendar.setTime(fechaFinProcesar);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
							
			fechaProcesar = calendar.getTime();
			
			
			if(fechaProcesar.after(hasta)) {
				finCiclo = true;			}
		}	
			
		return interFech;
	}
	
	
	//SP_GENERA_FECHAS_INTERVALO
	@Override
	public List<OrdenFechaIntervalosTO> generaFechasIntervalo(IntervaloFechaTO datos, List<OrdenFechaIntervalosTO> interFech)
			throws BrioBPMException {
		
		String tipoLista = "NINGUNA";
		String listaDias = "";
		String[] dias = null;
		String identificadorFceha= "";
		List<OrdenFechaIntervalosTO> newList = new ArrayList<OrdenFechaIntervalosTO>();
		List<GrupoFechaIntervalosTO> fechaList = new ArrayList<GrupoFechaIntervalosTO>();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		int detallePeriodo = 0;
		
		if (datos.getDiasDeLaSemana() != null && !datos.getDiasDeLaSemana().isEmpty() && !datos.getDiasDeLaSemana().equals("") ) {
			tipoLista = "DIAS_DE_LA_SEMANA";
			listaDias = datos.getDiasDeLaSemana();
			
			
			listaDias = listaDias.replace(Constants.LUNES, Constants.CH_1);
			listaDias = listaDias.replace(Constants.MARTES, Constants.CH_2);
			listaDias = listaDias.replace(Constants.MIERCOLES, Constants.CH_3);
			listaDias = listaDias.replace(Constants.JUEVES, Constants.CH_4);
			listaDias = listaDias.replace(Constants.VIERNES, Constants.CH_5);
			listaDias = listaDias.replace(Constants.SABADO, Constants.CH_6);
			listaDias = listaDias.replace(Constants.DOMINGO, Constants.CH_7);
		}
		
		if (datos.getDiasDelMes() != null && !datos.getDiasDelMes().isEmpty() && !datos.getDiasDelMes().equals("") ) {
			tipoLista = "DIAS_DEL_MES";
			listaDias = datos.getDiasDelMes();
		}
		
		if (!tipoLista.equals("NINGUNA")) {
			dias = listaDias.split("\\,");
		}
		
		//PROCESA LISTA DE INTERVALOS DE FECHAS
		if (datos.getDetallePeriodo() != null && !datos.getDetallePeriodo().isEmpty()) {
			identificadorFceha = datos.getDetallePeriodo();
		}
		
		String InsertaFecha = "NO";
		int numDiasBuscar = 0;
		boolean primerMes = true;
		LocalDate fechaIniEvaluar = null;


		for (OrdenFechaIntervalosTO to : interFech) {
			//crea lista de fechas
			String fechaIni = formato.format(to.getFechaInicial()) ;
			String fechaFin = formato.format(to.getFechaFinal());
		     
			LocalDate fechaEvaluar = LocalDate.parse(fechaIni, fmt);
			LocalDate fechaFinEvaluar = LocalDate.parse(fechaFin, fmt);
			fechaIniEvaluar = LocalDate.parse(fechaIni, fmt);
			
			fechaList = new ArrayList<GrupoFechaIntervalosTO>();
			int ordenFcha = 1;
			boolean salir = false;
			do {
				InsertaFecha = "NO";
				
				if (tipoLista.equals("NINGUNA")) {
					LocalDate ultimoDiaDelMes = fechaEvaluar.with(TemporalAdjusters.lastDayOfMonth());
									
					if (datos.getDetallePeriodo().equals(Constants.DETALLE_PERIODO_ULTIMO) && 
							fechaEvaluar.getYear() == ultimoDiaDelMes.getYear() && 
							fechaEvaluar.getMonthValue() == ultimoDiaDelMes.getMonthValue() && 
							fechaEvaluar.getDayOfMonth() == ultimoDiaDelMes.getDayOfMonth() )  {
						InsertaFecha = "SI";
					} else if (datos.getDetallePeriodo().equals(Constants.DETALLE_PERIODO_PENULTIMO) ) {
						int penultimoDiaDelMes = ultimoDiaDelMes.getDayOfMonth() - 1;
						if (fechaEvaluar.getYear() == ultimoDiaDelMes.getYear() && 
								fechaEvaluar.getMonthValue() == ultimoDiaDelMes.getMonthValue() && 
								fechaEvaluar.getDayOfMonth() == penultimoDiaDelMes) {
							InsertaFecha = "SI";
						}
					} else  {
						if (datos.getDetallePeriodo() != null && datos.getDetallePeriodo().isEmpty() && 
								datos.getDetallePeriodo().equals(fechaEvaluar.getDayOfMonth())  ) {
							InsertaFecha = "SI";
						}
					}				
					numDiasBuscar = 0;
				} else {

					
					if (tipoLista.equals("DIAS_DE_LA_SEMANA")) {
						 // Obtener el día de la semana
						DayOfWeek diaDeLaSemana = fechaEvaluar.getDayOfWeek();
					     // Esto da el número del día de la semana (1=Lunes, 7=Domingo)
					    numDiasBuscar = diaDeLaSemana.getValue();
					}
					
					if (tipoLista.equals("DIAS_DEL_MES")) {
						numDiasBuscar = fechaEvaluar.getDayOfMonth();
					}
					
					for (String dia : dias) {
						String diasBuscar = String.valueOf(numDiasBuscar);
					    if (dia.equals(diasBuscar)) {
					    	InsertaFecha = "SI";
					        break;
					    }
					}				
				}
				
				if (datos.getPeriodoTiempo().equals("MES") && datos.getCadaNperiodos() > 1 ) {
					InsertaFecha = "NO";
					if (datos.getDetallePeriodo().equals(Constants.DETALLE_PERIODO_ULTIMO)) {

						
						if(	fechaEvaluar.getYear() == fechaFinEvaluar.getYear() && 
							fechaEvaluar.getMonthValue() == fechaFinEvaluar.getMonthValue())  {	
							numDiasBuscar = fechaEvaluar.getDayOfMonth();
							for (String dia : dias) {
								String diasBuscar = String.valueOf(numDiasBuscar);
							    if (dia.equals(diasBuscar)) {
							    	InsertaFecha = "SI";
							        break;
							    }
							}
							//InsertaFecha = "SI";
						}
					} else if (datos.getDetallePeriodo().equals(Constants.DETALLE_PERIODO_PENULTIMO) ) {
						
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(to.getFechaFinal());
						calendar.add(Calendar.MONTH, -1);

						Date fechaAplica = calendar.getTime();
						
						String fecIniAplica = formato.format(fechaAplica);							     
						LocalDate localfechaAplica = LocalDate.parse(fecIniAplica, fmt);
						
						//int penultimoDiaDelMes = localfechaAplica.getMonthValue() - 1;
						if (fechaEvaluar.getYear() == localfechaAplica.getYear() && 
								fechaEvaluar.getMonthValue() == localfechaAplica.getMonthValue() ) {
							numDiasBuscar = fechaEvaluar.getDayOfMonth();
							for (String dia : dias) {
								String diasBuscar = String.valueOf(numDiasBuscar);
							    if (dia.equals(diasBuscar)) {
							    	InsertaFecha = "SI";
							        break;
							    }
							}
							//InsertaFecha = "SI";
						}
					} else  {
						
						if (datos.getDetallePeriodo() != null && !datos.getDetallePeriodo().isEmpty() ) {
							detallePeriodo = Integer.valueOf(datos.getDetallePeriodo());
							if (detallePeriodo == 1) {
								if (fechaEvaluar.getYear() == fechaIniEvaluar.getYear() && 
										fechaEvaluar.getMonthValue() == fechaIniEvaluar.getMonthValue() ) {
									numDiasBuscar = fechaEvaluar.getDayOfMonth();
									for (String dia : dias) {
										String diasBuscar = String.valueOf(numDiasBuscar);
									    if (dia.equals(diasBuscar)) {
									    	InsertaFecha = "SI";
									        break;
									    }

									}
								}
							} else  {
								int mesAplica = detallePeriodo - 1;
								Calendar calendar = Calendar.getInstance();
								calendar.setTime(to.getFechaInicial());
								calendar.add(Calendar.MONTH, mesAplica);

								Date fechaAplica = calendar.getTime();
								
								String fecIniAplica = formato.format(fechaAplica);							     
								LocalDate localfechaAplica = LocalDate.parse(fecIniAplica, fmt);
								
								if (fechaEvaluar.getYear() == localfechaAplica.getYear() && 
										fechaEvaluar.getMonthValue() == localfechaAplica.getMonthValue() ) {
									numDiasBuscar = fechaEvaluar.getDayOfMonth();
									for (String dia : dias) {
										String diasBuscar = String.valueOf(numDiasBuscar);
									    if (dia.equals(diasBuscar)) {
									    	InsertaFecha = "SI";
									        break;
									    }
									}
								}
							}
						}
					}

				}
				
				if (InsertaFecha.equals("SI")) {
					//agrega fecha 
					String strFechaEvaluar = fechaEvaluar.format(fmt);
					Date fecEvaluar = null;
					try {
						fecEvaluar = formato.parse(strFechaEvaluar);
					} catch (ParseException e) {
						log.error("-- error al convertir la fecha");
					}
					
					GrupoFechaIntervalosTO fechaFinal =  GrupoFechaIntervalosTO.builder()
							.fecha(fecEvaluar)
							.ordenFecha(ordenFcha)
							.identificadorFecha(identificadorFceha)
							.build();
					fechaList.add(fechaFinal);
					ordenFcha++;
					InsertaFecha = "NO";
				}
				
				
				salir = false;
				String fecEvaluar = fechaEvaluar.toString();
				
				if (fecEvaluar.equals(fechaFin)) {
					salir = true;
				}
				fechaEvaluar = fechaEvaluar.plusDays(1);
				
			} while (salir == false);
			// agrega lista al periodo
			

		    if (fechaList.size() == 0) {
		    	
				String strFechaEvaluar = fechaIniEvaluar.format(fmt);
				Date fecEvaluar = null;
				try {
					fecEvaluar = formato.parse(strFechaEvaluar);
				} catch (ParseException e) {
					log.error("-- error al convertir la fecha");
				}
		    	
				GrupoFechaIntervalosTO fechaFinal =  GrupoFechaIntervalosTO.builder()
						.fecha(fecEvaluar)
						.ordenFecha(ordenFcha)
						.identificadorFecha(identificadorFceha)
						.build();
				fechaList.add(fechaFinal);
		    }

			
			to.setGrupoFecha(fechaList);
			newList.add(to);
		}
		
		
		return newList;
	}
	

	// SP_CALCULA_FECHA_LIMITE
	@Override
	public EstatusFechaTO calculaFechaLimite(DatosAutenticacionTO session, CalculoFechaLimiteTO datosFecha)
			throws BrioBPMException, ParseException {

		log.info("-----------------calculaFechaLimite--------------------");

		String cveEntidad = session.getCveEntidad();
		BigDecimal version = datosFecha.getVersion();
		String cveInstancia = datosFecha.getCveInstancia();
		String cveNodo = datosFecha.getCveNodo();
		Integer idNodo = datosFecha.getIdNodo();
		Date fechaCreacion = datosFecha.getFechaCreacion();
		Integer plazoNivelServicio;
		String baseCalculoNivelSer;
		String cveLocalidadDestino;
		String cveProceso = datosFecha.getCveProceso();

		// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		EstatusFechaTO result = EstatusFechaTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();

		// INICIALIZA LA FECHA LÍMITE EN NULO
		result.setFechaLimite(null);

		// OBTIENE LOS PARÁMETROS DE NIVEL DE SERVICIO DE LA ACTIVIDAD
		plazoNivelServicio = null;
		baseCalculoNivelSer = null;
		cveLocalidadDestino = null;

		Optional<StNodoProceso> item = stNodoProcesoRepository.obtieneParametrosActividad(
				cveProceso,
				version,
				cveNodo,
				idNodo);

		plazoNivelServicio = item.get().getPlazoNivelServicio();
		baseCalculoNivelSer = item.get().getBaseCalculoNivelSer();
		cveLocalidadDestino = item.get().getCveLocalidadNivelServicio();
		/*log.info("-------> PLAZO NIVEL SERVICIO: " + plazoNivelServicio);
		log.info("-------> BASE CALCULO NIVEL SER: " + baseCalculoNivelSer);
		log.info("-------> CLAVE LOCALIDAD DESTINO: " + cveLocalidadDestino);*/

		// SI NO EXISTE NIVEL DE SERVICIO, ENTONCES NO ES NECESARIO EL CÁLCULO DE LA
		// FECHA LÍMITE
		if (plazoNivelServicio == null || plazoNivelServicio == 0) {
			return result;
		}

		// EJECUTA EL CÁLCULO DE LA FECHA LÍMITE CON LOS PARÁMETROS OBTENIDOS
		CalculoFechaMasPlazoTO datosFechaMasPlazo = CalculoFechaMasPlazoTO.builder()
				.cveProceso(cveProceso)
				.version(version)
				.cveInstancia(cveInstancia)
				.cveNodo(cveNodo)
				.idNodo(idNodo)
				.cveEntidadBase(cveEntidad)
				.cveLocalidadBase(cveLocalidadDestino)
				.fechaBase(fechaCreacion)
				.plazo(plazoNivelServicio)
				.baseCalculo(baseCalculoNivelSer)
				.build();

		log.info("-----------------calculaFechaLimite--------------------");
		EstatusFechaTO cFMP = calculaFechaMasPlazo(session, datosFechaMasPlazo);

		log.info("XXXXXX TERMINA SP_CALCULA_FECHA_MAS_PLAZO : ");
		log.info("@D_FECHA_LIMITE: " + cFMP.getFechaLimite());
		
		result.setFechaLimite(cFMP.getFechaLimite());
		result.setMensaje(cFMP.getMensaje());

		if (cFMP.getTipoExcepcion().equals(Constants.ERROR)) {
			result.setTipoExcepcion(Constants.ERROR);
		}
		return result;
	}

	// SP_CALCULA_HORA_LOCALIDAD
	/**
	 * Método para calcular la hora local de una localidad específica.
	 * 
	 * @param session           Datos de autenticación del usuario que contiene la entidad y localidad.
	 * @param fechaHoraOriginal Fecha y hora original que se desea convertir.
	 * @return                  Objeto EstatusFechaTO que contiene la hora calculada o un mensaje de error.
	 * @throws BrioBPMException Excepción en caso de que ocurra algún error durante el cálculo.
	 */
	@Override
	public EstatusFechaTO calculaHoraLocalidad(DatosAutenticacionTO session, Date fechaHoraOriginal)
	        throws BrioBPMException {

		log.info("-----------------------calculaHoraLocalidad------------------------");
		String idProceso;
	    String mensaje;
	    String cveHusoHorarioStr;
	    Integer cveHusoHorario;
	    Integer cveHusoHorarioBase;
	    Integer horasTotales;
	    Calendar calendar = Calendar.getInstance();
	    Date fechaHoraConvertida;

	    // Construye el valor de las variables con entidad y localidad del usuario
	    String variablesValores = 
	            Constants.CVE_ENTIDAD + session.getCveEntidad() + "|" + 
	            Constants.CVE_LOCALIDAD + session.getCveLocalidad();

	    // Inicializa el objeto resultado con el estado OK y un mensaje vacío
	    EstatusFechaTO result = EstatusFechaTO.builder()
	            .tipoExcepcion(Constants.OK)
	            .mensaje("")
	            .build();
	    idProceso = "CALCULA_HORA_LOCALIDAD";

	    cveHusoHorarioStr = "";
	    // Obtiene la clave del huso horario para la localidad del usuario
	    LocalidadEntidadPK id = LocalidadEntidadPK.builder()
	            .cveEntidad(session.getCveEntidad())
	            .cveLocalidad(session.getCveLocalidad())
	            .build();
	    Optional<LocalidadEntidad> datos = localidadEntidadRepository.findById(id);
	    cveHusoHorarioStr = datos.get().getHusoHorario().getCveHusoHorario();
	    log.info("----> cveHusoHorarioStr: " + cveHusoHorarioStr);
	    cveHusoHorarioStr = (cveHusoHorarioStr != null) ? cveHusoHorarioStr : "";
	    log.info("----> cveHusoHorarioStr: " + cveHusoHorarioStr);

	    // Si no se puede obtener la clave del huso horario, se lanza un error y se retorna el resultado
	    if (cveHusoHorarioStr.isEmpty()) {
	        mensaje = messagesService.getMessage(
	                session,
	                idProceso,
	                "HUSO_HORARIO_NO_CONFIGURADO",
	                variablesValores);
	        result.setTipoExcepcion(Constants.ERROR);
	        result.setMensaje(mensaje);
	        log.info("----> tipoEXCEPCION: " + result.getTipoExcepcion());
	        return result;
	    }

	    // Convierte la clave del huso horario a número entero
	    cveHusoHorario = Integer.parseInt(cveHusoHorarioStr);

	    cveHusoHorarioBase = null;
	    // Obtiene el huso horario base del sistema
	    Optional<ParametroGeneral> husoHorarioBase = parametroGeneralRepository.findById("HUSO_HORARIO_BASE");
	    cveHusoHorarioBase = husoHorarioBase.get().getValorEntero();
	    log.debug("----> cveHusoHorarioBase: " + cveHusoHorarioBase);
	    cveHusoHorarioBase = (cveHusoHorarioBase != null) ? cveHusoHorarioBase : 0;
	    log.debug("----> cveHusoHorarioBase: " + cveHusoHorarioBase);

	    // Si no se puede obtener el huso horario base, se lanza un error
	    /*if (cveHusoHorarioBase == 0) {
	        mensaje = messagesService.getMessage(session, idProceso, "HUSO_HORARIO_BASE_INCORRECTO", "");
	        result.setTipoExcepcion(Constants.ERROR);
	        return result;
	    }*/
	    fechaHoraConvertida = null;
	    // Si el huso horario base es igual al de la localidad, se retorna la fecha original
	    if (cveHusoHorarioBase == cveHusoHorario) {
	    	fechaHoraConvertida = fechaHoraOriginal;
	        result.setFechaLimite(fechaHoraConvertida);
	        return result;
	    }
	    log.debug("----> 1 fechaHoraConvertida: {} ", fechaHoraConvertida);
	    

	    // Calcula la diferencia de horas entre los dos husos horarios
	    if (cveHusoHorarioBase < 0) {
	        cveHusoHorarioBase = -(cveHusoHorarioBase);
	        log.debug("---->2 cveHusoHorarioBase: " + cveHusoHorarioBase);
	    }

	    // Suma las horas totales de desviación entre los husos horarios
	    horasTotales = cveHusoHorarioBase + cveHusoHorario;
	    log.debug("---->3 horasTotales: " + horasTotales);
	    // Calcula la fecha y hora ajustada agregando la diferencia de horas
	    calendar.setTime(fechaHoraOriginal);
	    calendar.add(Calendar.HOUR, horasTotales);
	    fechaHoraConvertida = calendar.getTime();
	    log.debug("----> 4 fechaHoraConvertida: " + fechaHoraConvertida);
	    log.info("fecha hora convertida: " + fechaHoraConvertida);
	    
	    result.setFechaLimite(fechaHoraConvertida);

	    log.debug(" 5 -----> calculaHoraLocalidad TIPO EXCEPCION: " + result.getTipoExcepcion());
	    log.debug("6 -----> calculaHoraLocalidad MENSAJE: " + result.getMensaje());
	    return result;
	}


	// SP_CARGA_CALENDARIO_LOCALIDAD
	@Override
	public EstatusTO cargaCalendarioLocalidad(DatosAutenticacionTO session, CalculoFechaLimiteTO datosFecha, List<CalendarioLocalidadTO> calendarioLocalidad)
			throws BrioBPMException{

		log.info("--------------cargaCalendarioLocalidad-------------");
		String cveEntidad = session.getCveEntidad();
		String cveLocalidad = session.getCveLocalidad();
		String cveProceso = datosFecha.getCveProceso();
		BigDecimal version = datosFecha.getVersion();
		String idProceso;
		String variablesValores;
		Date fechaHoraConvertida;

		// INICIALIZA TIPO DE EXCEPCIÓN, MENSAJE E ID_PROCESO
		EstatusTO result = EstatusTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();
		idProceso = "CARGA_CALENDARIO_LOCALIDAD";
		
		variablesValores = 
				Constants.CVE_ENTIDAD + cveEntidad + "|" + 
				Constants.CVE_LOCALIDAD + cveLocalidad + "|" + 
				Constants.CVE_PROCESO + cveProceso + "|" + 
				Constants.VERSION + version;
		
		/*log.info("----> CLAVE ENTIDAD : " + cveEntidad);
		log.info("----> CLAVE LOCALIDAD : " + cveLocalidad);*/
		
		CalendarioLocalidadPK id = CalendarioLocalidadPK.builder()
				.cveEntidad(cveEntidad)
				.cveLocalidad(cveLocalidad)
				.build();
		Optional<CalendarioLocalidad> calendarioLocal = calendarioLocalidadRepository.findById(id);

		// VALIDA EXISTENCIA DEL CALENDARIO DE LA LOCALIDAD
		if (!calendarioLocal.isPresent()) {
			String mensaje = messagesService.getMessage(
					session, idProceso,
					"NO_EXISTE_CALENDARIO_LOCALIDAD",
					variablesValores);
			result.setTipoExcepcion(Constants.ERROR);
			result.setMensaje(mensaje);
			return result;
		}

		log.info("TABLA TEMPORAL #CALENDARIOLOCALIDAD : " + calendarioLocalidad.toString());
		calendarioLocalidad.clear();
		log.info("TABLA TEMPORAL #CALENDARIOLOCALIDAD ELIMINADA: " + calendarioLocalidad.toString());
		CalendarioLocalidad iteCalenLoc = calendarioLocal.get();
		
		CalendarioLocalidadTO to;

		to = CalendarioLocalidadTO.builder().diaSemana(1).esHabil(Constants.NO).build();
		if (iteCalenLoc.getHoraInicioDom() != null && iteCalenLoc.getHoraFinDom() != null) {
			log.info("***** DOMINGO *****");
			to = CalendarioLocalidadTO.builder()
					.diaSemana(1)
					.esHabil(Constants.SI)
					.horaFinDia(iteCalenLoc.getHoraInicioDom().toString())
					.horaFinDia(iteCalenLoc.getHoraFinDom().toString())
					.build();
		}
		calendarioLocalidad.add(to);

		to = CalendarioLocalidadTO.builder().diaSemana(2).esHabil(Constants.NO).build();
		if (iteCalenLoc.getHoraInicioLun() != null && iteCalenLoc.getHoraFinLun() != null) {
			log.info("***** LUNES *****");
			to = CalendarioLocalidadTO.builder()
					.diaSemana(2)
					.esHabil(Constants.SI)
					.horaInicioDia(iteCalenLoc.getHoraInicioLun().toString())
					.horaFinDia(iteCalenLoc.getHoraFinLun().toString())
					.build();
		}
		calendarioLocalidad.add(to);

		to = CalendarioLocalidadTO.builder().diaSemana(3).esHabil(Constants.NO).build();
		if (iteCalenLoc.getHoraInicioMar() != null && iteCalenLoc.getHoraFinMar() != null) {
			log.info("***** MARTES *****");
			to = CalendarioLocalidadTO.builder()
					.diaSemana(3)
					.esHabil(Constants.SI)
					.horaInicioDia(iteCalenLoc.getHoraInicioMar().toString())
					.horaFinDia(iteCalenLoc.getHoraFinMar().toString())
					.build();
		}
		calendarioLocalidad.add(to);

		to = CalendarioLocalidadTO.builder().diaSemana(4).esHabil(Constants.NO).build();
		if (iteCalenLoc.getHoraInicioMie() != null && iteCalenLoc.getHoraFinMie() != null) {
			log.info("***** MIERCOLES *****");
			to = CalendarioLocalidadTO.builder()
					.diaSemana(4)
					.esHabil(Constants.SI)
					.horaInicioDia(iteCalenLoc.getHoraInicioMie().toString())
					.horaFinDia(iteCalenLoc.getHoraFinMie().toString())
					.build();
		}
		calendarioLocalidad.add(to);

		to = CalendarioLocalidadTO.builder().diaSemana(5).esHabil(Constants.NO).build();
		if (iteCalenLoc.getHoraInicioJue() != null && iteCalenLoc.getHoraFinJue() != null) {
			log.info("***** JUEVES *****");
			to = CalendarioLocalidadTO.builder()
					.diaSemana(5)
					.esHabil(Constants.SI)
					.horaInicioDia(iteCalenLoc.getHoraInicioJue().toString())
					.horaFinDia(iteCalenLoc.getHoraFinJue().toString())
					.build();
		}
		calendarioLocalidad.add(to);

		to = CalendarioLocalidadTO.builder().diaSemana(6).esHabil(Constants.NO).build();
		if (iteCalenLoc.getHoraInicioVie() != null && iteCalenLoc.getHoraFinVie() != null) {
			log.info("***** VIERNES *****");
			to = CalendarioLocalidadTO.builder()
					.diaSemana(6)
					.esHabil(Constants.SI)
					.horaInicioDia(iteCalenLoc.getHoraInicioVie().toString())
					.horaFinDia(iteCalenLoc.getHoraFinVie().toString())
					.build();
		}
		calendarioLocalidad.add(to);
		
		to = CalendarioLocalidadTO.builder().diaSemana(7).esHabil(Constants.NO).build();
		if (iteCalenLoc.getHoraInicioSab() != null && iteCalenLoc.getHoraFinSab() != null) {
			log.info("***** SABADO *****");
			to = CalendarioLocalidadTO.builder()
					.diaSemana(7)
					.esHabil(Constants.SI)
					.horaInicioDia(iteCalenLoc.getHoraInicioSab().toString())
					.horaFinDia(iteCalenLoc.getHoraFinSab().toString())
					.build();
		}
		calendarioLocalidad.add(to);

		log.info("------> INSERTA DTO : " + to.toString());		
		log.info("CALENDARIO LOCALIDAD SALIDA : " + calendarioLocalidad.size() + " " + calendarioLocalidad.toString());
		
		return result;
	}

	// SP_CALCULA_FECHA_MAS_PLAZO_HMS
	/**
	 * Calcula la fecha final sumando un plazo en segundos, minutos u horas a una
	 * fecha base, considerando horarios laborales y días hábiles.
	 * 
	 * @param session    Datos de autenticación del usuario.
	 * @param datosFecha Información necesaria para el cálculo de la fecha límite.
	 * @param calendarioLocalidad Calendario de la localidad para cálculos de fechas.
	 * @return Objeto EstatusFechaTO con la fecha límite calculada y estado del
	 *         proceso.
	 * @throws BrioBPMException En caso de error durante el cálculo de la fecha
	 *                          límite.
	 * @throws ParseException   En caso de error al parsear las fechas.
	 */

	@Override
	public EstatusFechaTO calculaFechaMasPlazoHMS(DatosAutenticacionTO session, CalculoFechaMasPlazoTO datosFecha, List<CalendarioLocalidadTO> calendarioLocalidad)
	        throws BrioBPMException, ParseException {

	    log.info("--------------calculaFechaMasPlazoHMS-------------");

	    Date fechaHoraBase = datosFecha.getFechaBase();
	    String baseCalculo = datosFecha.getBaseCalculo();
	    Integer plazo = datosFecha.getPlazo();
	    String cveEntidadBase = datosFecha.getCveEntidadBase();
	    String cveLocalidadBase = datosFecha.getCveLocalidadBase();

	    EstatusFechaTO result = EstatusFechaTO.builder()
	            .tipoExcepcion(Constants.OK)
	            .mensaje("")
	            .build();

	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(fechaHoraBase);

	    // Encontrar horario laboral del día base
	    String horaInicioLaboral = "", horaFinLaboral = "";
	    do {
	        for (CalendarioLocalidadTO to : calendarioLocalidad) {
	        	log.info("DIA DE LA SEMANA: " + to.getDiaSemana());
	            if (to.getDiaSemana() == calendar.get(Calendar.DAY_OF_WEEK)) {
	                log.info("DIA DE LA SEMANA CALENDAR: " + calendar.get(Calendar.DAY_OF_WEEK));
	                
	            	horaInicioLaboral = to.getHoraInicioDia();
	                horaFinLaboral = to.getHoraFinDia();
	                log.info("HORA INICIO LABORAL: " + horaInicioLaboral);
	                log.info("HORA FIN LABORAL: " + horaFinLaboral);
	                
	                break;
	            }
	        }
	        if (horaInicioLaboral == null || horaFinLaboral == null) {
	            calendar.add(Calendar.DAY_OF_MONTH, 1);
	            calendar.set(Calendar.HOUR_OF_DAY, 0);
	            calendar.set(Calendar.MINUTE, 0);
	            calendar.set(Calendar.SECOND, 0);
	        }
	    } while (horaInicioLaboral == null || horaFinLaboral == null);

	    int horaInicio = Integer.parseInt(horaInicioLaboral.substring(11, 13)) * 3600 + Integer.parseInt(horaInicioLaboral.substring(14, 16)) * 60;
	    int horaFin = Integer.parseInt(horaFinLaboral.substring(11, 13)) * 3600 + Integer.parseInt(horaFinLaboral.substring(14, 16)) * 60;
	    int segundosBase = (calendar.get(Calendar.HOUR_OF_DAY) * 3600) + (calendar.get(Calendar.MINUTE) * 60) + calendar.get(Calendar.SECOND);

	    // Ajustar la fecha base si está fuera del horario laboral
	    if (segundosBase >= horaFin) {
	        do {
	            calendar.add(Calendar.DAY_OF_MONTH, 1);
	        } while (!validaFechaHabil(cveEntidadBase, cveLocalidadBase, calendar.getTime(), calendarioLocalidad).equals(Constants.SI));

	        calendar.set(Calendar.HOUR_OF_DAY, horaInicio / 3600);
	        calendar.set(Calendar.MINUTE, (horaInicio % 3600) / 60);
	        calendar.set(Calendar.SECOND, 0);
	    } else if (segundosBase < horaInicio) {
	    	
	    	log.info("----> DIA NO HABIL - 1");	    	
	   
	        calendar.set(Calendar.HOUR_OF_DAY, horaInicio / 3600);
	        calendar.set(Calendar.MINUTE, (horaInicio % 3600) / 60);
	        calendar.set(Calendar.SECOND, 0);
	    }

	    // Convertir el plazo a segundos
	    int plazoRestante = 0;
	    switch (baseCalculo.toUpperCase()) {
	        case "SEGUNDOS":
	            plazoRestante = plazo;
	            break;
	        case "MINUTOS":
	            plazoRestante = plazo * 60;
	            break;
	        case "HORAS":
	            plazoRestante = plazo * 3600;
	            break;
	        default:
	            throw new IllegalArgumentException("Unidad de tiempo no válida");
	    }

	    while (plazoRestante > 0) {
	        if (validaFechaHabil(cveEntidadBase, cveLocalidadBase, calendar.getTime(), calendarioLocalidad).equals(Constants.SI)) {
	            int segundosDia = (calendar.get(Calendar.HOUR_OF_DAY) * 3600) + (calendar.get(Calendar.MINUTE) * 60) + calendar.get(Calendar.SECOND);
	            int segundosDisponibles = Math.max(0, horaFin - segundosDia);
	            if (segundosDisponibles >= plazoRestante) {
	                calendar.add(Calendar.SECOND, plazoRestante);
	                plazoRestante = 0;
	            } else {
	                plazoRestante -= segundosDisponibles;
	                do {
	                    calendar.add(Calendar.DAY_OF_MONTH, 1);
	                } while (!validaFechaHabil(cveEntidadBase, cveLocalidadBase, calendar.getTime(), calendarioLocalidad).equals(Constants.SI));
	                calendar.set(Calendar.HOUR_OF_DAY, horaInicio / 3600);
	                calendar.set(Calendar.MINUTE, (horaInicio % 3600) / 60);
	                calendar.set(Calendar.SECOND, 0);
	            }
	        } else {
	            calendar.add(Calendar.DAY_OF_MONTH, 1);
	        }
	    }
	    
	    log.info("xxxx FECHA LIMITE: " + calendar.getTime());

	    result.setFechaLimite(calendar.getTime());
	    return result;
	}


	// SP_CALCULA_FECHA_MAS_PLAZO_DSM
	@Override
	public EstatusFechaTO calculaFechaMasPlazoDSM(DatosAutenticacionTO session, CalculoFechaMasPlazoTO datosFecha, List<CalendarioLocalidadTO> calendarioLocalidad)
			throws BrioBPMException, ParseException {

		String cveEntidadBase = datosFecha.getCveEntidadBase();
		String cveLocalidadBase = datosFecha.getCveLocalidadBase();
		Date fechaBase = datosFecha.getFechaBase();
		Integer plazo = datosFecha.getPlazo();
		String baseCalculo = datosFecha.getBaseCalculo();
		Date fechaCalculada;
		Integer plazoRestante;
		Date fechaRevision;
		Date fechaHoraRevision;
		String fechaHoraRevisionStr;
		Date fechaRevisionFinal = null;
		Date fechaHoraBase;
		String horaBase;
		Integer diaSemana;
		Date fechaHoraInicial;
		String fechaHoraInicialStr;
		Date fechaHoraFinal;
		String fechaHoraFinalStr;
		String horaInicio;
		String horaFin;
		String esHabil;
		String finBusquedaHabil;
		String fechaRevisionStr;
		Date horaInicioDate;
		String horaInicioStr;

		SimpleDateFormat formatoDia = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
		SimpleDateFormat formatoHora1 = new SimpleDateFormat("HH:mm:ss.S");
		String patronFormato = "yyyy-MM-dd HH:mm:ss.S";
		SimpleDateFormat formatoFecha = new SimpleDateFormat(patronFormato);

		// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		EstatusFechaTO result = EstatusFechaTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();
		fechaCalculada = null;
		result.setFechaLimite(fechaCalculada);

		// BUSCA LA PRIMERA FECHA HÁBIL CON RESPECTO A LA FECHA BASE
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaBase);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		fechaRevision = calendar.getTime();
		fechaHoraBase = null;

		finBusquedaHabil = Constants.NO;

		while (finBusquedaHabil.equals(Constants.NO)) {
			log.info("LISTA calendarioLocalidad: " + calendarioLocalidad.toString());
			log.info("-----> CLAVE LOCALIDAD BASE: " + cveLocalidadBase);
			esHabil = validaFechaHabil(cveEntidadBase, cveLocalidadBase, fechaRevision, calendarioLocalidad);
			if (esHabil.equals(Constants.SI)) {

				// OBTIENE LAS FECHAS DE INICIO Y FIN PARA LA FECHA EN REVISIÓN
				calendar.setTime(fechaRevision);
				diaSemana = calendar.get(Calendar.DAY_OF_WEEK);
				fechaRevisionStr = formatoDia.format(fechaRevision);
				
				for (CalendarioLocalidadTO to : calendarioLocalidad) {
					if (diaSemana == to.getDiaSemana()) {
						
						horaInicio = to.getHoraInicioDia();
						log.info("-----> DHS horaInicio: " + horaInicio);
						horaFin = to.getHoraFinDia();
						log.info("-----> DHS horaFin: " + horaFin);

						horaInicioDate = formato.parse(horaInicio);
						horaInicioStr = formatoHora1.format(horaInicioDate);
						
						fechaHoraInicialStr = fechaRevisionStr + " " + horaInicioStr;
						log.info("-----> DHS fechaHoraInicialStr CONCATENADA: " + fechaHoraInicialStr);
						fechaHoraInicial = formatoFecha.parse(fechaHoraInicialStr.trim());
						log.info("-----> DHS fechaHoraInicial DATE: " + fechaHoraInicial);

						// OBTIENE LA FECHA Y HORA DE FIN DE LA FECHA DE REVISIÓN
						LocalTime hInicio = LocalTime.parse(horaInicio.trim(), formatter);
						log.info("-----> DHS hInicio: " + hInicio);
						LocalTime hFin = LocalTime.parse(horaFin.trim(), formatter);
						log.info("-----> DHS hFin: " + hFin);
						
						if (hFin.isBefore(hInicio)) {
							calendar.setTime(fechaRevision);
							calendar.add(Calendar.DAY_OF_MONTH, 1);
							fechaRevisionFinal = calendar.getTime();
						} else {
							fechaRevisionFinal = fechaRevision;
						}
						
						fechaHoraFinalStr = formatoDia.format(fechaRevisionFinal);
						log.info("-----> DHS fechaHoraFinalStr CON FORMATO: " + fechaHoraFinalStr);
						Date horaFinDate = formato.parse(horaFin);
						String horaFinStr = formatoHora1.format(horaFinDate);
						fechaHoraFinalStr = fechaHoraFinalStr + " " + horaFinStr;
						log.info("-----> DHS fechaHoraFinalStr: " + fechaHoraFinalStr);
						fechaHoraFinal = formatoFecha.parse(fechaHoraFinalStr.trim());
						log.info("-----> DHS fechaHoraFinal: " + fechaHoraFinal);

						// ASIGNA LA FECHA Y HORA BASE PARA EL CÁLCULO DE LA FECHA LÍMITE
						if ((fechaBase.after(fechaHoraInicial) || fechaBase.equals(fechaHoraInicial))
								&& (fechaBase.before(fechaHoraFinal) || fechaBase.equals(fechaHoraFinal))) {
							fechaHoraBase = fechaBase;
							finBusquedaHabil = Constants.SI;
						} else if (fechaBase.before(fechaHoraInicial)) {
							fechaHoraBase = fechaHoraInicial;
							finBusquedaHabil = Constants.SI;
						}
					}
				}
			}
			if(finBusquedaHabil.equals(Constants.NO)) {
				calendar.add(Calendar.DAY_OF_MONTH, 1);
				fechaRevision = calendar.getTime();
				log.info("-----> DHS fechaRevision: " + fechaRevision);
			}
			
		}

		// ASIGNA VALOR A VARIABLE DE PLAZO RESTANTE
		plazoRestante = plazo;

		// CALCULA FECHA LÍMITE PARA 'DÍAS'
		if (baseCalculo.equals(Constants.DIAS)) {
			while (plazoRestante > 0) {
				calendar.setTime(fechaRevision);
				calendar.add(Calendar.DAY_OF_MONTH, 1);
				fechaRevision = calendar.getTime();
				log.info("-----> DHS fechaRevision: " + fechaRevision);

				// VALIDA SI LA FECHA BASE ES HÁBIL
				esHabil = validaFechaHabil(cveEntidadBase, cveLocalidadBase, fechaRevision, calendarioLocalidad);

				if (esHabil.equals(Constants.SI)) {
					plazoRestante = plazoRestante - 1;
				}
			}
		}

		// CALCULA FECHA LÍMITE PARA 'SEMANAS' Y 'MESES'
		if (baseCalculo.equals(Constants.SEMANAS)) {
			calendar.setTime(fechaRevision);
			calendar.add(Calendar.WEEK_OF_YEAR, plazoRestante);
			fechaRevision = calendar.getTime();
			log.info("-----> DHS fechaRevision: " + fechaRevision);
		} else if (baseCalculo.equals(Constants.MESES)) {
			calendar.setTime(fechaRevision);
			calendar.add(Calendar.MONTH, plazoRestante);
			fechaRevision = calendar.getTime();
			log.info("-----> DHS fechaRevision: " + fechaRevision);
		}

		// BUSCA LA FECHA HÁBIL MÁS CERCANA, INCLUSIVE LA FECHA RESULTANTE ES TOMADA EN
		// CUENTA
		finBusquedaHabil = Constants.NO;
		while (finBusquedaHabil.equals(Constants.NO)) {
			esHabil = validaFechaHabil(cveEntidadBase, cveLocalidadBase, fechaRevision, calendarioLocalidad);
			if (esHabil.equals(Constants.SI)) {
				finBusquedaHabil = Constants.SI;
			} else {
				calendar.setTime(fechaRevision);
				calendar.add(Calendar.DAY_OF_MONTH, 1);
				fechaRevision = calendar.getTime();
				log.info("-----> DHS fechaRevision: " + fechaRevision);
			}
		}

		// CALCULA LA HORA LÍMITE
		calendar.setTime(fechaRevision);
		diaSemana = calendar.get(Calendar.DAY_OF_WEEK);
		log.info("-----> DHS diaSemana: " + diaSemana);
		for (CalendarioLocalidadTO to : calendarioLocalidad) {
			if (to.getDiaSemana() == diaSemana) {
				horaInicio = to.getHoraInicioDia();
				horaFin = to.getHoraFinDia();

				horaInicioDate = formato.parse(horaInicio);
				//log.info("-----> DHS horaInicioDate: " + horaInicioDate);
				horaInicioStr = formatoHora1.format(horaInicioDate);
				//log.info("-----> DHS horaInicioStr: " + horaInicioStr);
				fechaHoraInicialStr = formatoDia.format(fechaRevision);
				//log.info("-----> DHS fechaHoraInicialStr: " + fechaHoraInicialStr);
				fechaHoraInicialStr = fechaHoraInicialStr + " " + horaInicioStr;
				//log.info("-----> DHS fechaHoraInicialStr: " + fechaHoraInicialStr);
				fechaHoraInicial = formatoFecha.parse(fechaHoraInicialStr.trim());
				//log.info("-----> DHS fechaHoraInicial: " + fechaHoraInicial);
				
				// OBTIENE LA FECHA Y HORA DE FIN DE LA FECHA DE REVISIÓN
				LocalTime hInicio = LocalTime.parse(horaInicio.trim(), formatter);
				//log.info("-----> DHS hInicio: " + hInicio);
				LocalTime hFin = LocalTime.parse(horaFin.trim(), formatter);
				//log.info("-----> DHS hFin: " + hFin);
				
				if (hFin.isBefore(hInicio)) {
					calendar.setTime(fechaRevision);
					calendar.add(Calendar.DAY_OF_MONTH, 1);
					fechaRevisionFinal = calendar.getTime();
					//log.info("-----> fechaRevisionFinal: " + fechaRevisionFinal);
				} else {
					fechaRevisionFinal = fechaRevision;
					//log.info("-----> fechaRevisionFinal: " + fechaRevisionFinal);
				}
				
				Date horaFinDate = formato.parse(horaFin);
				//log.info("-----> DHS horaFinDate: " + horaFinDate);
				String horaFinStr = formatoHora1.format(horaFinDate);
				//log.info("-----> DHS horaFinStr: " + horaFinStr);
				
				fechaHoraFinalStr = formatoDia.format(fechaRevisionFinal);
				//log.info("-----> DHS fechaHoraFinalStr CON FORMATO: " + fechaHoraFinalStr);
				fechaHoraFinalStr = fechaHoraFinalStr + " " + horaFinStr;
				//log.info("-----> DHS fechaHoraFinalStr CONCATENADA: " + fechaHoraFinalStr);
				fechaHoraFinal = formatoFecha.parse(fechaHoraFinalStr.trim());
				//log.info("-----> DHS fechaHoraFinal: " + fechaHoraFinal);
				
				horaBase = formatoHora1.format(fechaHoraBase);
				//log.info("-----> DHS horaBase: " + horaBase);
				fechaHoraRevisionStr = formatoDia.format(fechaRevision);
				//log.info("-----> DHS fechaHoraRevisionStr: " + fechaHoraRevisionStr);
				
				fechaHoraRevisionStr = fechaHoraRevisionStr + " " + horaBase;
				//log.info("-----> DHS fechaHoraRevisionStr con Hora Base: " + fechaHoraRevisionStr);
				fechaHoraRevision = formatoFecha.parse(fechaHoraRevisionStr.trim());
				//log.info("-----> DHS fechaHoraRevision: " + fechaHoraRevision);

				if (fechaHoraRevision.before(fechaHoraInicial) || fechaHoraRevision.equals(fechaHoraInicial)) {
					fechaHoraRevision = fechaHoraInicial;
				} else {
					if ((fechaHoraRevision.after(fechaHoraInicial) || 
							fechaHoraRevision.equals(fechaHoraInicial))
							&& (fechaHoraRevision.before(fechaHoraFinal) ||
									fechaHoraRevision.equals(fechaHoraFinal))) {
						
						fechaHoraRevision = fechaHoraRevision;
						//log.info("-----> DHS fechaHoraRevision: " + fechaHoraRevision);
					} else {
						fechaHoraRevision = fechaHoraFinal;
						//log.info("-----> DHS fechaHoraRevision: " + fechaHoraRevision);
					}
				}
				fechaCalculada = fechaHoraRevision;
				log.info("-----> DHS fechaCalculada: " + fechaCalculada);
				result.setFechaLimite(fechaCalculada);
			}
		}
		return result;
	}

	// SP_VALIDA_FECHA_HABIL
	/**
	 * Método que valida si una fecha es hábil en base a la entidad y localidad proporcionadas.
	 * 
	 * @param cveEntidadBase    Clave de la entidad.
	 * @param cveLocalidadBase  Clave de la localidad.
	 * @param fechaRevision     Fecha a revisar.
	 * @return Un String que indica si la fecha es hábil ('SI' o 'NO').
	 * @throws BrioBPMException Si ocurre un error en la validación.
	 * @throws ParseException Si no se puede parsear la fecha.
	 */
	@Override
	public String validaFechaHabil(String cveEntidadBase, String cveLocalidad, Date fechaRevision, List<CalendarioLocalidadTO> calendarioLocalidad)
	        throws BrioBPMException, ParseException {

	    // Variable para almacenar la fecha en formato de cadena y fecha sin hora
	    String fechaStr;
	    Date fechaSinHora;
	    
	    // Variable para almacenar el día de la semana
	    Integer diaSemana;
	    
	    // Variable para indicar si el día es hábil
	    String esDiaHabil;

	    // Log para verificar la clave de la localidad
	    log.info("----> CLAVE LOCALIDAD: " + cveLocalidad);

	    // Inicializa la variable que indica si la fecha es hábil con el valor 'SI'
	    String esHabil = Constants.SI;

	    // Convierte la fecha proporcionada a una cadena con formato 'yyyy-MM-dd'
	    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
	    fechaStr = formatoFecha.format(fechaRevision);
	    
	    // Log para mostrar la fecha en el nuevo formato
	    log.info("----> FECHA CON FORMATO: " + fechaStr);
	    
	    // Convierte la fecha sin incluir la hora
	    fechaSinHora = formatoFecha.parse(fechaStr);
	    
	    // Log para mostrar la fecha sin hora
	    log.info("----> FECHA SIN HORA: " + fechaSinHora);

	    // Crea una clave compuesta para la búsqueda de fechas inhábiles en el calendario
	    InhabilCalendarioPK id = InhabilCalendarioPK.builder()
	            .cveEntidad(cveEntidadBase)
	            .cveLocalidad(cveLocalidad)
	            .fechaInhabil(fechaSinHora)
	            .build();

	    // Si la fecha está registrada como inhábil en el repositorio, se indica que no es hábil
	    if (inhabilCalendarioRepository.existsById(id)) {
	        esHabil = Constants.NO;
	    } else {

	        // Si no está registrada como inhábil, valida que el día de la semana sea hábil
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(fechaRevision);
	        diaSemana = calendar.get(Calendar.DAY_OF_WEEK);

	        // Recorre el calendario de la localidad para verificar si el día es hábil o no
	        for (CalendarioLocalidadTO calendario : calendarioLocalidad) {
	            if (calendario.getDiaSemana().equals(diaSemana)) {
	                esDiaHabil = calendario.getEsHabil();
	                if (esDiaHabil.equals(Constants.NO)) {
	                    esHabil = Constants.NO;
	                }
	            }
	        }
	    }

	    // Retorna 'SI' o 'NO' dependiendo si la fecha es hábil
	    return esHabil;
	}


	// SP_CALCULA_FECHA_MAS_PLAZO
	@Override
	public EstatusFechaTO calculaFechaMasPlazo(DatosAutenticacionTO session, CalculoFechaMasPlazoTO datosFecha)
			throws BrioBPMException, ParseException {

		log.info("-----------------calculaFechaMasPlazo--------------------");

		String cveProceso = datosFecha.getCveProceso();
		BigDecimal version = datosFecha.getVersion();
		String cveInstancia = datosFecha.getCveInstancia();
		String cveNodo = datosFecha.getCveNodo();
		Integer idNodo = datosFecha.getIdNodo();
		String cveEntidadBase = datosFecha.getCveEntidadBase();
		String cveLocalidadBase = datosFecha.getCveLocalidadBase();
		Date fechaBase = datosFecha.getFechaBase();
		Integer plazo = datosFecha.getPlazo();
		String baseCalculo = datosFecha.getBaseCalculo();
		Date fechaBaseLocalidad;
		Calendar calendar = Calendar.getInstance();
		Date fechaCalculada;

		// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		EstatusFechaTO result = EstatusFechaTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();
		
		// INICIALIZA LA FECHA LÍMITE EN NULO
		fechaCalculada = null;
		
		// INICIALIZA VALORES DE VARIABLES PARA PROCESO DE MENSAJES DE ERROR
		// PARA EL CASO DE CÁLCULO SIN NECESIDAD DE LOCALIDAD, SIMPLEMENTE SE AUMENTA
		// plazo a fechaBase
		log.info("----> fechaBase: " + fechaBase);
		log.info("----> plazo: " + plazo);
		log.info("-----> LOCALIDAD BASE: " + cveLocalidadBase);

		if (fechaBase != null && plazo != null && plazo != 0 && (cveLocalidadBase == null || cveLocalidadBase == " ")) {
			calendar.setTime(fechaBase);
			
			log.info("**** Base Calculo: " + baseCalculo);
			switch (baseCalculo) {
			case Constants.SEGUNDOS:
				calendar.add(Calendar.SECOND, plazo);
				break;
			case Constants.MINUTOS:
				calendar.add(Calendar.MINUTE, plazo);
				break;
			case Constants.HORAS:
				log.info("----> HORAS");
				calendar.add(Calendar.HOUR, plazo);
				break;
			case Constants.DIAS:
				calendar.add(Calendar.DAY_OF_MONTH, plazo);
				break;
			case Constants.SEMANAS:
				calendar.add(Calendar.WEEK_OF_MONTH, plazo);
				break;
			case Constants.MESES:
				calendar.add(Calendar.MONTH, plazo);
				break;
			default:
				result.setFechaLimite(null);
				return result;
			}
			
			fechaCalculada = calendar.getTime();
			log.info("fechaBase despues del switch: "+ fechaBase.toString());
			result.setFechaLimite(fechaCalculada);
			return result;
		}

		// CONVIERTE LA FECHA BASE A LA FECHA EQUIVALENTE DE LA LOCALIDAD BASE
		EstatusFechaTO horaLocalidad = calculaHoraLocalidad(session, fechaBase);
		result.setMensaje(horaLocalidad.getMensaje());
		result.setTipoExcepcion(horaLocalidad.getTipoExcepcion());
		if (horaLocalidad.getTipoExcepcion().equals(Constants.ERROR)) {
			result.setTipoExcepcion(Constants.ERROR);
			return result;
		}
		fechaBaseLocalidad = horaLocalidad.getFechaLimite();
		log.info("------> fechaBaseLocalidad  @D_FECHA_BASE_LOCALIDAD: " + fechaBaseLocalidad);
		
		// CREA TABLA TEMPORAL PARA ALMACENAR LAS HORAS DE INICIO Y FIN DE CADA DÍA DE LA LOCALIDAD
		List<CalendarioLocalidadTO> calendarioLocalidad = new ArrayList<CalendarioLocalidadTO>();
		
		// CARGA LA TABLA DE DIAS DE LA SEMANA Y LOS HORARIOS DE INICIO Y FIN
		log.info("----> CLAVE LOCALIDAD DESTINO ANTES DE CALENDARIO LOCALIDAD: " + cveLocalidadBase);
		CalculoFechaLimiteTO datos = CalculoFechaLimiteTO.builder()
				.cveEntidadBase(cveEntidadBase)
				.cveLocalidaDestino(cveLocalidadBase)
				.cveProceso(cveProceso)
				.version(version)
				.build();
		EstatusTO calendarioLocalidadMetodo = cargaCalendarioLocalidad(session, datos, calendarioLocalidad);
		
		if (calendarioLocalidadMetodo.getTipoExcepcion().equals(Constants.ERROR)) {
			result.setTipoExcepcion(Constants.ERROR);
			result.setMensaje(calendarioLocalidadMetodo.getMensaje());
			return result;
		}

		// DE ACUERDO AL PLAZO, EJECUTA EL CÁLCULO PARA HORAS, MINUTOS Y SEGUNDOS; O EL
		// DE DIAS, SEMANAS Y MESES
		Set<String> basesValidas = new HashSet<>(Arrays.asList(Constants.HORAS, Constants.MINUTOS, Constants.SEGUNDOS));

		if (basesValidas.contains(baseCalculo)) {
			CalculoFechaMasPlazoTO datosFechaHMS = CalculoFechaMasPlazoTO.builder()
					.cveProceso(cveProceso)
					.version(version)
					.cveInstancia(cveInstancia)
					.cveNodo(cveNodo)
					.idNodo(idNodo)
					.cveEntidadBase(cveEntidadBase)
					.cveLocalidadBase(cveLocalidadBase)
					.fechaBase(fechaBaseLocalidad)
					.plazo(plazo)
					.baseCalculo(baseCalculo)
					.build();
			log.info("-----> LOCALIDAD BASE: " + datosFechaHMS.getCveLocalidadBase());
			EstatusFechaTO fechaMasPlazoHMS = calculaFechaMasPlazoHMS(session, datosFechaHMS, calendarioLocalidad); // revisar
			result.setTipoExcepcion(fechaMasPlazoHMS.getTipoExcepcion());
			result.setTipoExcepcion(fechaMasPlazoHMS.getTipoExcepcion());
			fechaCalculada = fechaMasPlazoHMS.getFechaLimite();
		} else {
			CalculoFechaMasPlazoTO datosFechaDMS = CalculoFechaMasPlazoTO.builder()
					.cveProceso(cveProceso)
					.version(version)
					.cveInstancia(cveInstancia)
					.cveNodo(cveNodo)
					.idNodo(idNodo)
					.cveEntidadBase(cveEntidadBase)
					.cveLocalidadBase(cveLocalidadBase)
					.fechaBase(fechaBaseLocalidad)
					.plazo(plazo)
					.baseCalculo(baseCalculo)
					.build();
			log.info("-----> CLAVE LOCALIDAD BASE: " + datosFechaDMS.getCveLocalidadBase());
			EstatusFechaTO fechaMasPlazoDSM = calculaFechaMasPlazoDSM(session, datosFechaDMS, calendarioLocalidad);
			result.setTipoExcepcion(fechaMasPlazoDSM.getTipoExcepcion());
			result.setTipoExcepcion(fechaMasPlazoDSM.getTipoExcepcion());
			fechaCalculada = fechaMasPlazoDSM.getFechaLimite();
		}
		
		log.info("@D_FECHA_CALCULADA: " + fechaCalculada);
		result.setFechaLimite(fechaCalculada);
		return result;

	}

	// SP_CALCULA_FECHA_TEMPORIZADOR
	@Override
	public EstatusFechaTO calculaFechaTemporizador(DatosAutenticacionTO session, CalculoFechaLimiteTO datos)
			throws BrioBPMException, ParseException {
		
		log.info("-----------------calculaFechaTemporizador--------------------");

		String cveEntidad = session.getCveEntidad();
		String cveProceso = datos.getCveProceso();
		BigDecimal version = datos.getVersion();
		String cveInstancia = datos.getCveInstancia();
		Integer idNodo = datos.getIdNodo();
		String cveNodo = datos.getCveNodo();
		Date fechaCreacion = datos.getFechaCreacion();
		Date fechaEspera;
		Integer tiempoEspera;
		String unidadTiempoEspera;
		String cveLocalidadDestino;

		// INICIALIZA TIPO DE EXCEPCIÓN, MENSAJE 
		EstatusFechaTO result = EstatusFechaTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();
		fechaEspera = null;
		
		// OBTIENE LOS PARÁMETROS DE NIVEL DE SERVICIO O TIEMPO DE TEMPORIZACIÓN DEL
		// NODO  revisar clave Localidad Destino
		tiempoEspera = null;
		unidadTiempoEspera = null;
		cveLocalidadDestino = null;

		log.info("----> invoca SP_CALCULA_FECHA_TEMPORIZADOR");

		Optional<StNodoProceso> stNodoProceso = stNodoProcesoRepository.obtieneParametrosActividad(
				cveProceso, version, cveNodo, idNodo);
		
		if(!stNodoProceso.isPresent()) {
			result.setTipoExcepcion(Constants.ERROR);
			return result;
		}
		
		tiempoEspera = stNodoProceso.get().getTiempoEspera();
		unidadTiempoEspera = stNodoProceso.get().getUnidadTiempoEspera();
		cveLocalidadDestino = stNodoProceso.get().getCveLocalidadTiempoEspera();
		log.debug("-------> TIEMPO ESPERA: " , tiempoEspera);
		log.debug("------->UNIDAD TIEMPO ESPERA:{} " , unidadTiempoEspera);
		log.debug("-------> CLAVE LOCALIDAD DESTINO:{} " , cveLocalidadDestino);

		// SI NO EXISTE TIEMPO DE ESPERA, ENTONCES NO ES NECESARIO EL CÁLCULO DE LA
		// FECHA DE ESPERA
		if (tiempoEspera == null || tiempoEspera == 0) {
			return result;
		}
		// EJECUTA EL CÁLCULO DE LA FECHA LÍMITE CON LOS PARÁMETROS OBTENIDOS
		CalculoFechaMasPlazoTO datosFecha = CalculoFechaMasPlazoTO.builder()
				.cveProceso(cveProceso)
				.version(version)
				.cveInstancia(cveInstancia)
				.cveNodo(cveNodo)
				.idNodo(idNodo)
				.cveEntidadBase(cveEntidad)
				.cveLocalidadBase(cveLocalidadDestino)
				.fechaBase(fechaCreacion)
				.plazo(tiempoEspera)
				.baseCalculo(unidadTiempoEspera)
				.build();

		log.info("-------> CLAVE LOCALIDAD DESTINO getCveLocalidadBase: {} " + datosFecha.getCveLocalidadBase());
		EstatusFechaTO fechaMasPlazo = calculaFechaMasPlazo(session, datosFecha);
		result.setMensaje(fechaMasPlazo.getMensaje());
		
		if (fechaMasPlazo.getTipoExcepcion().equals(Constants.ERROR)) {
			result.setTipoExcepcion(Constants.ERROR);
		}
		
		fechaEspera = fechaMasPlazo.getFechaLimite();
		result.setFechaLimite(fechaEspera);
		return result;
	}

	

	//SP_AJUSTA_FECHAS_INTERVALO
	@Override
	public List<Date> ajustaFechasIntervalo(String cveEntidad, String cveLocalidad, String cveIdioma,
								String cveProceso, BigDecimal version, String tipoDia,
								List<OrdenFechaIntervalosTO> ordenFecha) 
	                                    throws BrioBPMException, ParseException {
		List<Date> fechaRepeticion = new ArrayList<Date>();
		
	    // Identificador del proceso, inicializado con el nombre del método
	    String idProceso;

	    // Variable para almacenar mensajes
	    String mensaje;

	    // Variable para concatenar valores de variables en caso de error
	    String variableValor;

	    // Indica si se debe buscar un día hábil
	    boolean buscarHabil = false;

	    // Indica si se encontró una fecha válida (hábil)
	    boolean fechaEncontrada;

	    // Variable para la fecha que se está procesando
	    Date fechaProcesar = null;

	    // Variable que indica el número de días a recorrer (-1, 0, 1)
	    Integer recorrido = 0;

	    // Indica si el día es hábil ("SI" o "NO")
	    String esHabil;

	    // Inicializa un objeto RetMsg con estado OK y mensaje vacío
	    RetMsg msg = RetMsg.builder().status(Constants.OK).message("").build();

	    // Asigna el identificador del proceso al nombre del método
	    idProceso = "AJUSTA_FECHAS_INTERVALO";

	    // Concatena información de la sesión y parámetros del proceso para el manejo de errores
	    variableValor = Constants.CVE_ENTIDAD + cveEntidad + "|" + Constants.CVE_LOCALIDAD + cveLocalidad + "|"
	                    + Constants.CVE_PROCESO + cveProceso + "|" + Constants.VERSION + version;
	    
	    // Crear una lista temporal para almacenar las horas de inicio y fin de cada día en la localidad
	    List<CalendarioLocalidadTO> calendarioLocalidad = new ArrayList<CalendarioLocalidadTO>();

	    // Cargar la tabla de días de la semana y los horarios de inicio y fin (pendiente de implementación)
	    msg = cargaCalendarioLocalidad(cveEntidad, cveLocalidad, cveProceso, version, calendarioLocalidad);
	    
	    if(Constants.ERROR.equals(msg.getStatus())) {
	    	return fechaRepeticion;
	    }

	    // Crear una nueva lista ordenada por fecha a partir de la lista de ordenFecha
	   /* List<OrdenFechaTO> listaDias = ordenFecha.stream()
	        .sorted((o1, o2) -> o1.getFecha().compareTo(o2.getFecha())) // Ordenar por fecha
	        .collect(Collectors.toList()); // Recoger resultados en una nueva lista
	        */	    	
		    for (OrdenFechaIntervalosTO ordenFechaTO : ordenFecha) {
		    	List<GrupoFechaIntervalosTO>  grupoFecha = ordenFechaTO.getGrupoFecha();
			    // Iterar sobre cada día en la lista ordenada
			    for (GrupoFechaIntervalosTO dia : grupoFecha) {
			        // Asignar la fecha del día actual a la variable fechaProcesar
			        fechaProcesar = dia.getFecha();
		
			        // Determina el tipo de ajuste para las fechas según el tipo de día solicitado
			        // Crear una instancia de Calendar para manipular la fecha
			        Calendar calendar = Calendar.getInstance();
			        
			        // Evaluar el tipo de día para determinar el ajuste necesario
			        switch (tipoDia) {
			            case "EXACTO":
			                // No se busca un día hábil, se usa la fecha exacta
			                buscarHabil = false;
			                break;
			                
			            case "HABIL_ANTERIOR":
			                // Buscar un día hábil anterior
			                buscarHabil = true;
		
			                // Restar un día de la fecha a procesar
			                calendar.setTime(fechaProcesar);
			                calendar.add(Calendar.DAY_OF_MONTH, -1);
			                
			                // Obtener la nueva fecha después de restar un día
			                fechaProcesar = calendar.getTime();
			                
			                // Establecer recorrido a -1 para buscar días hacia atrás
			                recorrido = -1;
			                break;	
		
			            case "HABIL_SIGUIENTE":
			                // Buscar un día hábil siguiente
			                buscarHabil = true;
		
			                // Sumar un día a la fecha a procesar
			                calendar.setTime(fechaProcesar);
			                calendar.add(Calendar.DAY_OF_MONTH, 1);
			                
			                // Obtener la nueva fecha después de sumar un día
			                fechaProcesar = calendar.getTime();
			                
			                // Establecer recorrido a 1 para buscar días hacia adelante
			                recorrido = 1;
			                break;
		
			            case "EXACTO_O_HABIL_ANTERIOR":
			                // Usar la fecha exacta o buscar un día hábil anterior
			                buscarHabil = true;
			                
			                // Establecer recorrido a -1 para buscar días hacia atrás si es necesario
			                recorrido = -1;
			                break;
		
			            case "EXACTO_O_HABIL_SIGUIENTE":
			                // Usar la fecha exacta o buscar un día hábil siguiente
			                buscarHabil = true;
			                
			                // Establecer recorrido a 1 para buscar días hacia adelante si es necesario
			                recorrido = 1;
			                break;
			        }
		
			        // Si es necesario buscar un día hábil
			        if (buscarHabil) {
			            // Inicializar la variable para indicar si se encontró una fecha hábil
			            fechaEncontrada = false;
		
			            // Mientras no se encuentre una fecha hábil
			            while (!fechaEncontrada) {
			                // Validar si la fecha actual es un día hábil
			                esHabil = validaFechaHabil(cveEntidad, cveLocalidad, fechaProcesar, calendarioLocalidad);
		
			                // Si es un día hábil
			                if (Constants.SI.equals(esHabil)) {
			                    // Marcar que se encontró una fecha hábil
			                    fechaEncontrada = true;
			                } else {
			                    // Si no es hábil, ajustar la fecha según el recorrido
			                    calendar.setTime(fechaProcesar);
			                    
			                    // Sumar o restar días según el valor de recorrido
			                    calendar.add(Calendar.DAY_OF_MONTH, recorrido);
			                    
			                    // Obtener la nueva fecha ajustada
			                    fechaProcesar = calendar.getTime();
			                }
			            }
			        }
		
			        // Agregar la fecha procesada a la lista de fechas de repetición
			        fechaRepeticion.add(fechaProcesar);
			    }				
			}
	    
	    // Devolver el mensaje de estado
	    return fechaRepeticion;
	}

	// SP_CARGA_CALENDARIO_LOCALIDAD
	 @Override
	public RetMsg cargaCalendarioLocalidad (String cveEntidad, String cveLocalidad, String cveProceso, BigDecimal version, List<CalendarioLocalidadTO> calendarioLocalidad) throws BrioBPMException {
		
		log.info("----------- cargaCalendarioLocalidad --------------");
		
		String idProceso;
		String mensaje;
		String variableValor;
		String esHabil;
		String horaInicioFormatted;
        String horaFinFormatted;
		
		// Formateador para extraer la hora en formato HH:mm:ss
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		
		// INICIALIZA TIPO DE EXCEPCIÓN, MENSAJE E ID_PROCESO
	    RetMsg msg = RetMsg.builder().status(Constants.OK).message("").build();
	    idProceso = "CARGA_CALENDARIO_LOCALIDAD";
	    
	    variableValor = Constants.CVE_ENTIDAD + cveEntidad + "|" + Constants.CVE_LOCALIDAD + cveLocalidad + "|"
                + Constants.CVE_PROCESO + cveProceso + "|" + Constants.VERSION + version;
	    
	    // VALIDA EXISTENCIA DEL CALENDARIO DE LA LOCALIDAD
	    CalendarioLocalidadPK id = CalendarioLocalidadPK.builder()
	    		.cveEntidad(cveEntidad)
	    		.cveLocalidad(cveLocalidad)
	    		.build();
	   
	    Optional<CalendarioLocalidad> calendarioEntidad= calendarioLocalidadRepository.findById(id);
	    if(!calendarioEntidad.isPresent()) {
	    	// ERROR 
	    	log.error("ERR_AJUSTE_DIA_NAT_HABIL_INHABIL");
	    	/*mensaje = messagesService.getMessage(session,		
					  idProceso,
					  "ERR_AJUSTE_DIA_NAT_HABIL_INHABIL",
					  variableValor);
					  msg.setMessage(mensaje);
					  msg.setStatus(Constants.ERROR);
					  return msg;*/
	    	msg = RetMsg.builder().status(Constants.ERROR).message("ERR_AJUSTE_DIA_NAT_HABIL_INHABIL").build();
	    	return msg;
	    }
	    
	    calendarioLocalidad.clear();
	    // CARGA LA TABLA TEMPORAL CON LOS HORARIOS DE INICIO Y FIN DE ACTIVIDAD
	    
	    // domingo
	    Date horaInicioDom = calendarioEntidad.get().getHoraInicioDom();
	    Date horaFinDom = calendarioEntidad.get().getHoraFinDom();
	    
	    if(horaInicioDom != null && horaFinDom != null) {
	    	esHabil = Constants.SI;
	    	// Obtener las horas de inicio y fin en el formato deseado
	    	horaInicioFormatted = sdf.format(horaInicioDom);
	    	horaFinFormatted = sdf.format(horaFinDom);
	    } else {
	    	esHabil = Constants.NO;
	    	// Obtener las horas de inicio y fin en el formato deseado
	    	horaInicioFormatted = null;
	    	horaFinFormatted = null;
	    }
	    
	    CalendarioLocalidadTO domingo = CalendarioLocalidadTO.builder()
	    		.diaSemana(1)
	    		.esHabil(esHabil)
	    		.horaInicioDia(horaInicioFormatted)
	    		.horaFinDia(horaFinFormatted)
	    		.build();
	    
	    calendarioLocalidad.add(domingo);
	    
	  // lunes
	    Date horaInicioLun = calendarioEntidad.get().getHoraInicioLun();
	    Date horaFinLun = calendarioEntidad.get().getHoraFinLun();
	    
	    if(horaInicioLun != null && horaFinLun != null) {
	    	esHabil = Constants.SI;
	    	// Obtener las horas de inicio y fin en el formato deseado
	    	horaInicioFormatted = sdf.format(horaInicioLun);
	    	horaFinFormatted = sdf.format(horaFinLun);
	    } else {
	    	esHabil = Constants.NO;
	    	horaInicioFormatted = null;
	    	horaFinFormatted = null;
	    }
	    
	    CalendarioLocalidadTO lunes = CalendarioLocalidadTO.builder()
	    		.diaSemana(2)
	    		.esHabil(esHabil)
	    		.horaInicioDia(horaInicioFormatted)
	    		.horaFinDia(horaFinFormatted)
	    		.build();
	    
	    calendarioLocalidad.add(lunes);
	    
	  // martes
	    Date horaInicioMar = calendarioEntidad.get().getHoraInicioMar();
	    Date horaFinMar = calendarioEntidad.get().getHoraFinMar();
	    if(horaInicioMar != null && horaFinMar != null) {
	    	esHabil = Constants.SI;
	    	// Obtener las horas de inicio y fin en el formato deseado
	    	horaInicioFormatted = sdf.format(horaInicioMar);
	    	horaFinFormatted = sdf.format(horaFinMar);
	    } else {
	    	esHabil = Constants.NO;
	    	horaInicioFormatted = null;
	    	horaFinFormatted = null;
	    }
	    
	    CalendarioLocalidadTO martes = CalendarioLocalidadTO.builder()
	    		.diaSemana(3)
	    		.esHabil(esHabil)
	    		.horaInicioDia(horaInicioFormatted)
	    		.horaFinDia(horaFinFormatted)
	    		.build();
	    
	    calendarioLocalidad.add(martes);  
	    
	  // miercoles
	    Date horaInicioMie = calendarioEntidad.get().getHoraInicioMie();
	    Date horaFinMie = calendarioEntidad.get().getHoraFinMie();
	    
	    if(horaInicioMie != null && horaFinMie != null) {
	    	esHabil = Constants.SI;
	    	// Obtener las horas de inicio y fin en el formato deseado
	    	horaInicioFormatted = sdf.format(horaInicioMie);
	    	horaFinFormatted = sdf.format(horaFinMie);
	    } else {
	    	esHabil = Constants.NO;
	    	horaInicioFormatted = null;
	    	horaFinFormatted = null;
	    }
	    
	    CalendarioLocalidadTO miercoles = CalendarioLocalidadTO.builder()
	    		.diaSemana(4)
	    		.esHabil(esHabil)
	    		.horaInicioDia(horaInicioFormatted)
	    		.horaFinDia(horaFinFormatted)
	    		.build();
	    
	    calendarioLocalidad.add(miercoles);
	    
	  // jueves
	    Date horaInicioJue = calendarioEntidad.get().getHoraInicioJue();
	    Date horaFinJue = calendarioEntidad.get().getHoraFinJue();
	    
	    if(horaInicioJue != null && horaFinJue != null) {
	    	esHabil = Constants.SI;
	    	// Obtener las horas de inicio y fin en el formato deseado
	    	horaInicioFormatted = sdf.format(horaInicioJue);
	    	horaFinFormatted = sdf.format(horaFinJue);
	    } else {
	    	esHabil = Constants.NO;
	    	horaInicioFormatted = null;
	    	horaFinFormatted = null;
	    }
	    
	    CalendarioLocalidadTO jueves = CalendarioLocalidadTO.builder()
	    		.diaSemana(5)
	    		.esHabil(esHabil)
	    		.horaInicioDia(horaInicioFormatted)
	    		.horaFinDia(horaFinFormatted)
	    		.build();
	    
	    calendarioLocalidad.add(jueves);
	    
	  // viernes
	    Date horaInicioVie = calendarioEntidad.get().getHoraInicioVie();
	    Date horaFinVie = calendarioEntidad.get().getHoraFinVie();
	    
	    if(horaInicioVie != null && horaFinVie != null) {
	    	esHabil = Constants.SI;
	    	// Obtener las horas de inicio y fin en el formato deseado
	    	horaInicioFormatted = sdf.format(horaInicioVie);
	    	horaFinFormatted = sdf.format(horaFinVie);
	    } else {
	    	esHabil = Constants.NO;
	    	horaInicioFormatted = null;
	    	horaFinFormatted = null;
	    }
	    
	    CalendarioLocalidadTO viernes = CalendarioLocalidadTO.builder()
	    		.diaSemana(6)
	    		.esHabil(esHabil)
	    		.horaInicioDia(horaInicioFormatted)
	    		.horaFinDia(horaFinFormatted)
	    		.build();
	    
	    calendarioLocalidad.add(viernes);
	    
	  // sabado
	    Date horaInicioSab = calendarioEntidad.get().getHoraInicioSab();
	    Date horaFinSab = calendarioEntidad.get().getHoraFinSab();
	    
	    if(horaInicioSab != null && horaFinSab != null) {
	    	esHabil = Constants.SI;
	    	// Obtener las horas de inicio y fin en el formato deseado
	    	horaInicioFormatted = sdf.format(horaInicioSab);
	    	horaFinFormatted = sdf.format(horaFinSab);
	    } else {
	    	esHabil = Constants.NO;
	    	horaInicioFormatted = null;
	    	horaFinFormatted = null;
	    }
	            
	    CalendarioLocalidadTO sabado = CalendarioLocalidadTO.builder()
	    		.diaSemana(7)
	    		.esHabil(esHabil)
	    		.horaInicioDia(horaInicioFormatted)
	    		.horaFinDia(horaFinFormatted)
	    		.build();
	    
	    calendarioLocalidad.add(sabado);
	    
		return msg;
		
	}

	 
	 @Override
	 public Date obtenerFechaCreacion(String cveEntidad, String cveLocalidad) {
		 
		 	log.info("----------- obtenerFechaCreacion --------------");
		    // ASIGNA LA FECHA DE CREACIÓN Y LA FECHA LÍMITE
		 	Date fechaCreacion = new Date();

		 	// log.info("1 Fecha de creación: " + fechaCreacion);

		    Optional<LocalidadEntidad> localidadEntidad = localidadEntidadRepository
		            .findById(LocalidadEntidadPK.builder().cveEntidad(cveEntidad).cveLocalidad(cveLocalidad).build());
		    // log.info("localidadEntidad de la entidad: {} " , localidadEntidad);
		    if (localidadEntidad.isPresent()) {
		        HusoHorario usoHorarioEntidad = localidadEntidad.get().getHusoHorario();
		        String usoHorario = usoHorarioEntidad.getCveHusoHorario();

		        log.info("Uso horario de la entidad: " + usoHorario);
		        
	            // Convertir el uso horario a entero (puede ser +5 o -5)
	            int offsetHoras = Integer.parseInt(usoHorario);

	            // Obtener la fecha actual en UTC y aplicarle el huso horario
	            ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(Instant.now(), ZoneOffset.UTC)
	                    .plusHours(offsetHoras);

	            // Convertir de ZonedDateTime a Date
	            fechaCreacion = Date.from(zonedDateTime.toInstant());
	          
		    }
		    
		    // log.info("2 Fecha de creación: " + fechaCreacion);

		    return fechaCreacion;
		}

		
	 @Override
	 public List<OrdenFechaIntervalosTO> leeIntervaloFechaRepse(String cveEntidad, IntervaloFechaTO datos, String aplicaInicio)
				throws BrioBPMException {
			List<OrdenFechaIntervalosTO> interFech = new ArrayList<OrdenFechaIntervalosTO>();	
			Date desde = datos.getDesde();
			Date hasta = datos.getHasta();
			Integer nPeriodos = datos.getCadaNperiodos();
			String periodoTiempo = datos.getPeriodoTiempo();
			String detallePeriodo = datos.getDetallePeriodo();
			
			String tipoPeriodoMes = "MES";
			int secuencia = 1;
			boolean c = false;
			
			 try {
				 
				SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
				String fecInicio = formato.format(desde);
				String fecFin = formato.format(hasta);
				
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
		        // Cambia aquí tus fechas
		        LocalDate fechaInicio = LocalDate.parse(fecInicio, formatter);
		        LocalDate fechaFin = LocalDate.parse(fecFin, formatter);
		        boolean activaInicio = true;
	       	        
				if (periodoTiempo.equals(tipoPeriodoMes)) {
					YearMonth mesActual = YearMonth.from(fechaInicio);					
					String datoIni = "";
					String datoFin = "";
					
					if (nPeriodos == 0) {						
						if (datos.getDetallePeriodo().equals(Constants.DETALLE_PERIODO_ULTIMO)) {							
							datoIni = fechaFin.format(formatter);
							fechaFin = fechaFin.plusMonths(1);
							datoFin = fechaFin.format(formatter);
							
						} else if (datos.getDetallePeriodo().equals(Constants.DETALLE_PERIODO_PENULTIMO) ) {

							Calendar calendar = Calendar.getInstance();
							calendar.setTime(hasta);
							calendar.add(Calendar.MONTH, -1);

							Date fechaAplica = calendar.getTime();
							
							datoIni = formato.format(fechaAplica);	
							datoFin = fechaFin.format(formatter);
							
						} else {
							Integer noDetallePeriodo =  Integer.parseInt(detallePeriodo); 
							if ( detallePeriodo.equals("1")) {
								if (aplicaInicio.equals("SI")) {
									datoIni = fechaInicio.format(formatter);
									fechaInicio = fechaInicio.plusMonths(1);
									datoFin = fechaInicio.format(formatter);
								} else {
									fechaInicio = fechaInicio.plusMonths(1);
									mesActual = YearMonth.from(fechaInicio);	
									LocalDate inicioMes = mesActual.atDay(1);
									datoIni = inicioMes.format(formatter);
									LocalDate finMes = mesActual.atEndOfMonth();
									datoFin = finMes.format(formatter);
								}
							} else {
								if (aplicaInicio.equals("SI")) {
									fechaInicio = fechaInicio.plusMonths(noDetallePeriodo - 1);									
								} else {
									fechaInicio = fechaInicio.plusMonths(noDetallePeriodo);
								}
								mesActual = YearMonth.from(fechaInicio);	
								LocalDate inicioMes = mesActual.atDay(1);									
								datoIni = inicioMes.format(formatter);
								LocalDate finMes = mesActual.atEndOfMonth();
								datoFin = finMes.format(formatter);
							}
														
						}

						OrdenFechaIntervalosTO intFech = OrdenFechaIntervalosTO.builder()
								.secuencia(secuencia)
								.fechaInicial(formato.parse(datoIni))
								.fechaFinal(formato.parse(datoFin))					
								.build();
						interFech.add(intFech);	
						
					} else if (nPeriodos == 1) {
						fechaFin = fechaFin.plusMonths(1);
						 
				        while (!mesActual.atEndOfMonth().isBefore(fechaInicio) && !mesActual.atDay(1).isAfter(fechaFin)) {
				        	
				        	if (aplicaInicio.equals("NO") && activaInicio) {
				        		mesActual = mesActual.plusMonths(1);
				        		activaInicio = false;
				        	}
				        	
				            LocalDate inicioMes = mesActual.atDay(1);
				            LocalDate finMes = mesActual.atEndOfMonth();

				            LocalDate inicioRango = fechaInicio.isAfter(inicioMes) ? fechaInicio : inicioMes;
				            LocalDate finRango = fechaFin.isBefore(finMes) ? fechaFin : finMes;
			            
				            mesActual = mesActual.plusMonths(1);
				            
				            datoIni = inicioRango.format(formatter);
				            datoFin = finRango.format(formatter);
				            
							OrdenFechaIntervalosTO intFech = OrdenFechaIntervalosTO.builder()
									.secuencia(secuencia)
									.fechaInicial(formato.parse(datoIni))
									.fechaFinal(formato.parse(datoFin))					
									.build();
							interFech.add(intFech);	
							secuencia++;
				        }
						
					} else {
						fechaFin = fechaFin.plusMonths(nPeriodos);

				        int triIgnora = 0;
				        int anioIgnora = 0;
				        int triAnterior = 0;
				        int trimestre = (mesActual.getMonthValue() - 1) / nPeriodos + 1;
				        
				        while (!mesActual.atDay(1).isAfter(fechaFin) ) {
				            int mes = mesActual.getMonthValue();
				            trimestre = (mes - 1) / nPeriodos + 1;
				            int anio = mesActual.getYear();

				        	if (aplicaInicio.equals("NO")) {
				        		if (activaInicio) {
					        		triIgnora = trimestre;
					        		anioIgnora = anio;
					        		activaInicio = false;
					        		
				        		} 

					        	if ((triIgnora != trimestre && triAnterior != trimestre) || (triIgnora == trimestre && anioIgnora !=  anio && triAnterior != trimestre) ){
						            LocalDate inicioMes = mesActual.atDay(1);
						            mesActual = mesActual.plusMonths(nPeriodos - 1);
						            LocalDate finMes = mesActual.atEndOfMonth();
					        		
						            datoIni = inicioMes.format(formatter);
						            datoFin = finMes.format(formatter);
						            
									OrdenFechaIntervalosTO intFech = OrdenFechaIntervalosTO.builder()
											.secuencia(secuencia)
											.fechaInicial(formato.parse(datoIni))
											.fechaFinal(formato.parse(datoFin))					
											.build();
									interFech.add(intFech);	
									secuencia++;
					        	} 
				 
					        	
				        	} else {
				        		
				        		if (triAnterior != trimestre) {
				        			datoIni = "";
				        			LocalDate inicioMes = mesActual.atDay(1);
				        			if (activaInicio) {
				        				activaInicio = false;
				        				datoIni = fechaInicio.format(formatter);
				        				
				        			} else {
				        				 datoIni = inicioMes.format(formatter);
				        				 mesActual = mesActual.plusMonths(nPeriodos - 1);
				        			}				        				
				        			
						            LocalDate finMes = mesActual.atEndOfMonth();
				        			
						            datoFin = finMes.format(formatter);
						            
									OrdenFechaIntervalosTO intFech = OrdenFechaIntervalosTO.builder()
											.secuencia(secuencia)
											.fechaInicial(formato.parse(datoIni))
											.fechaFinal(formato.parse(datoFin))					
											.build();
									interFech.add(intFech);	
				        			
				        		}
				        	}
				        	mesActual = mesActual.plusMonths(1);				        	
				        	triAnterior = trimestre;
				        	
				        }
				        if (aplicaInicio.equals("SI")) {
				        	LocalDate inicioMes = mesActual.atDay(1);
				        	datoIni = inicioMes.format(formatter);
				        	mesActual = mesActual.plusMonths(nPeriodos - 1);
				        	LocalDate finMes = mesActual.atEndOfMonth();
				        	datoFin = finMes.format(formatter);
				        	
							OrdenFechaIntervalosTO intFech = OrdenFechaIntervalosTO.builder()
									.secuencia(secuencia)
									.fechaInicial(formato.parse(datoIni))
									.fechaFinal(formato.parse(datoFin))					
									.build();
							interFech.add(intFech);	
				        }
					}
					
				}
			} catch (Exception e) {
				new BrioBPMException(e);
			}
			
				
			return interFech;
		}

		//SP_GENERA_FECHAS_INTERVALO ajustado mes Repse
	 @Override
	 public List<OrdenFechaIntervalosTO> generaFechasIntervaloRepse(IntervaloFechaTO datos, List<OrdenFechaIntervalosTO> interFech)
				throws BrioBPMException {
			
			String tipoLista = "NINGUNA";
			String listaDias = "";
			String[] dias = null;
			String identificadorFceha= "";
			List<OrdenFechaIntervalosTO> newList = new ArrayList<OrdenFechaIntervalosTO>();
			List<GrupoFechaIntervalosTO> fechaList = new ArrayList<GrupoFechaIntervalosTO>();
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			int detallePeriodo = 0;
			
			if (datos.getDiasDelMes() != null && !datos.getDiasDelMes().isEmpty() && !datos.getDiasDelMes().equals("") ) {
				tipoLista = "DIAS_DEL_MES";
				listaDias = datos.getDiasDelMes();
			}
			
			if (!tipoLista.equals("NINGUNA")) {
				dias = listaDias.split("\\,");
			}
			
			//PROCESA LISTA DE INTERVALOS DE FECHAS
			if (datos.getDetallePeriodo() != null && !datos.getDetallePeriodo().isEmpty()) {
				identificadorFceha = datos.getDetallePeriodo();
			}
			
			String InsertaFecha = "NO";
			int numDiasBuscar = 0;
			LocalDate fechaIniEvaluar = null;
			int cuentaDias = 1;


			for (OrdenFechaIntervalosTO to : interFech) {
				//crea lista de fechas
				String fechaIni = formato.format(to.getFechaInicial()) ;
				String fechaFin = formato.format(to.getFechaFinal());
			     
				LocalDate fechaEvaluar = LocalDate.parse(fechaIni, fmt);
				LocalDate fechaFinEvaluar = LocalDate.parse(fechaFin, fmt);
				fechaIniEvaluar = LocalDate.parse(fechaIni, fmt);
				
				fechaList = new ArrayList<GrupoFechaIntervalosTO>();
				int ordenFcha = 1;
				boolean salir = false;
				do {
					InsertaFecha = "NO";
					
					if (tipoLista.equals("NINGUNA")) {
						LocalDate ultimoDiaDelMes = fechaEvaluar.with(TemporalAdjusters.lastDayOfMonth());
										
						if (datos.getDetallePeriodo().equals(Constants.DETALLE_PERIODO_ULTIMO) && 
								fechaEvaluar.getYear() == ultimoDiaDelMes.getYear() && 
								fechaEvaluar.getMonthValue() == ultimoDiaDelMes.getMonthValue() && 
								fechaEvaluar.getDayOfMonth() == ultimoDiaDelMes.getDayOfMonth() )  {
							InsertaFecha = "SI";
						} else if (datos.getDetallePeriodo().equals(Constants.DETALLE_PERIODO_PENULTIMO) ) {
							int penultimoDiaDelMes = ultimoDiaDelMes.getDayOfMonth() - 1;
							if (fechaEvaluar.getYear() == ultimoDiaDelMes.getYear() && 
									fechaEvaluar.getMonthValue() == ultimoDiaDelMes.getMonthValue() && 
									fechaEvaluar.getDayOfMonth() == penultimoDiaDelMes) {
								InsertaFecha = "SI";
							}
						} else  {
							if (datos.getDetallePeriodo() != null && datos.getDetallePeriodo().isEmpty() && 
									datos.getDetallePeriodo().equals(fechaEvaluar.getDayOfMonth())  ) {
								InsertaFecha = "SI";
							}
						}				
						numDiasBuscar = 0;
					} else {
						
						numDiasBuscar = fechaEvaluar.getDayOfMonth();
						String diasBuscar = "";
						for (String dia : dias) {
						    if (datos.getCadaNperiodos() == 0) {
								diasBuscar = String.valueOf(cuentaDias);
							    if (dia.equals(diasBuscar)) {
							    	InsertaFecha = "SI";
							        break;
							    }
						    } else {
								diasBuscar = String.valueOf(numDiasBuscar);
							    if (dia.equals(diasBuscar)) {
							    	InsertaFecha = "SI";
							        break;
							    }						    	
						    }
						}				
					}
					
					if (datos.getPeriodoTiempo().equals("MES") && datos.getCadaNperiodos() > 1 ) {
						InsertaFecha = "NO";
						if (datos.getDetallePeriodo().equals(Constants.DETALLE_PERIODO_ULTIMO)) {

							
							if(	fechaEvaluar.getYear() == fechaFinEvaluar.getYear() && 
								fechaEvaluar.getMonthValue() == fechaFinEvaluar.getMonthValue())  {	
								numDiasBuscar = fechaEvaluar.getDayOfMonth();
								for (String dia : dias) {
									String diasBuscar = String.valueOf(numDiasBuscar);
								    if (dia.equals(diasBuscar)) {
								    	InsertaFecha = "SI";
								        break;
								    }
								}
								//InsertaFecha = "SI";
							}
						} else if (datos.getDetallePeriodo().equals(Constants.DETALLE_PERIODO_PENULTIMO) ) {
							
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(to.getFechaFinal());
							calendar.add(Calendar.MONTH, -1);

							Date fechaAplica = calendar.getTime();
							
							String fecIniAplica = formato.format(fechaAplica);							     
							LocalDate localfechaAplica = LocalDate.parse(fecIniAplica, fmt);
							
							//int penultimoDiaDelMes = localfechaAplica.getMonthValue() - 1;
							if (fechaEvaluar.getYear() == localfechaAplica.getYear() && 
									fechaEvaluar.getMonthValue() == localfechaAplica.getMonthValue() ) {
								numDiasBuscar = fechaEvaluar.getDayOfMonth();
								for (String dia : dias) {
									String diasBuscar = String.valueOf(numDiasBuscar);
								    if (dia.equals(diasBuscar)) {
								    	InsertaFecha = "SI";
								        break;
								    }
								}
								//InsertaFecha = "SI";
							}
						} else  {
							
							if (datos.getDetallePeriodo() != null && !datos.getDetallePeriodo().isEmpty() ) {
								detallePeriodo = Integer.valueOf(datos.getDetallePeriodo());
								if (detallePeriodo == 1) {
									if (fechaEvaluar.getYear() == fechaIniEvaluar.getYear() && 
											fechaEvaluar.getMonthValue() == fechaIniEvaluar.getMonthValue() ) {
										numDiasBuscar = fechaEvaluar.getDayOfMonth();
										for (String dia : dias) {
											String diasBuscar = String.valueOf(numDiasBuscar);
										    if (dia.equals(diasBuscar)) {
										    	InsertaFecha = "SI";
										        break;
										    }

										}
									}
								} else  {
									int mesAplica = detallePeriodo - 1;
									Calendar calendar = Calendar.getInstance();
									calendar.setTime(to.getFechaInicial());
									calendar.add(Calendar.MONTH, mesAplica);

									Date fechaAplica = calendar.getTime();
									
									String fecIniAplica = formato.format(fechaAplica);							     
									LocalDate localfechaAplica = LocalDate.parse(fecIniAplica, fmt);
									
									if (fechaEvaluar.getYear() == localfechaAplica.getYear() && 
											fechaEvaluar.getMonthValue() == localfechaAplica.getMonthValue() ) {
										numDiasBuscar = fechaEvaluar.getDayOfMonth();
										for (String dia : dias) {
											String diasBuscar = String.valueOf(numDiasBuscar);
										    if (dia.equals(diasBuscar)) {
										    	InsertaFecha = "SI";
										        break;
										    }
										}
									}
								}
							}
						}

					}
					
					if (InsertaFecha.equals("SI")) {
						//agrega fecha 
						String strFechaEvaluar = fechaEvaluar.format(fmt);
						Date fecEvaluar = null;
						try {
							fecEvaluar = formato.parse(strFechaEvaluar);
						} catch (ParseException e) {
							log.error("-- error al convertir la fecha");
						}
						
						GrupoFechaIntervalosTO fechaFinal =  GrupoFechaIntervalosTO.builder()
								.fecha(fecEvaluar)
								.ordenFecha(ordenFcha)
								.identificadorFecha(identificadorFceha)
								.build();
						fechaList.add(fechaFinal);
						ordenFcha++;
						InsertaFecha = "NO";
					}
					
					
					salir = false;
					String fecEvaluar = fechaEvaluar.toString();
					
					if (fecEvaluar.equals(fechaFin)) {
						salir = true;
					}
					fechaEvaluar = fechaEvaluar.plusDays(1);
					cuentaDias++;
					
				} while (salir == false);
				// agrega lista al periodo
				

			    if (fechaList.size() == 0) {
			    	
					String strFechaEvaluar = fechaIniEvaluar.format(fmt);
					Date fecEvaluar = null;
					try {
						fecEvaluar = formato.parse(strFechaEvaluar);
					} catch (ParseException e) {
						log.error("-- error al convertir la fecha");
					}
			    	
					GrupoFechaIntervalosTO fechaFinal =  GrupoFechaIntervalosTO.builder()
							.fecha(fecEvaluar)
							.ordenFecha(ordenFcha)
							.identificadorFecha(identificadorFceha)
							.build();
					fechaList.add(fechaFinal);
			    }

				
				to.setGrupoFecha(fechaList);
				newList.add(to);
			}
			
			
			return newList;
		}

	 @Override
	 public Date convierteCadenaToDate(String valor) {
		    if (valor == null || valor.trim().isEmpty()) {
		        return null;
		    }

		    // Lista de formatos posibles
		    String[] formatos = {
		        "yyyy-MM-dd",
		        "dd/MM/yyyy",
		        "MM/dd/yyyy",
		        "yyyy/MM/dd",
		        "dd-MM-yyyy",
		        "dd.MM.yyyy"
		    };

		    for (String formato : formatos) {
		        try {
		            SimpleDateFormat sdf = new SimpleDateFormat(formato);
		            sdf.setLenient(false); // Validación estricta
		            return sdf.parse(valor);
		        } catch (ParseException e) {
		            // Ignorar y probar el siguiente formato
		        }
		    }

		    // Si ningún formato coincide, retorna null o lanza excepción
		    return null;
		}


}
