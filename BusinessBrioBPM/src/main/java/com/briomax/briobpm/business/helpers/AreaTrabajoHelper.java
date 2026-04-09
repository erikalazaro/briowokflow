package com.briomax.briobpm.business.helpers;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.business.helpers.base.IAreaTrabajoHelper;
import com.briomax.briobpm.business.service.catalogs.core.IMessagesService;
import com.briomax.briobpm.common.core.Constants;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.dao.StNodoProcesoDAO;
import com.briomax.briobpm.persistence.dao.VwDatoActividadDAO;
import com.briomax.briobpm.persistence.dao.base.IAreaTrabajoDAO;
import com.briomax.briobpm.persistence.entity.ComposicionCorreo;
import com.briomax.briobpm.persistence.entity.DatoProcesoNodo;
import com.briomax.briobpm.persistence.entity.Entidad;
import com.briomax.briobpm.persistence.entity.InNodoProceso;
import com.briomax.briobpm.persistence.entity.InNodoProcesoPK;
import com.briomax.briobpm.persistence.entity.LocalidadEntidad;
import com.briomax.briobpm.persistence.entity.LocalidadEntidadPK;
import com.briomax.briobpm.persistence.entity.StNodoProceso;
import com.briomax.briobpm.persistence.entity.StNodoProcesoPK;
import com.briomax.briobpm.persistence.entity.StVariableProceso;
import com.briomax.briobpm.persistence.entity.StVariableProcesoPK;
import com.briomax.briobpm.persistence.entity.VariableEntidad;
import com.briomax.briobpm.persistence.entity.VariableEntidadPK;
import com.briomax.briobpm.persistence.entity.VariableLocalidad;
import com.briomax.briobpm.persistence.entity.VariableLocalidadPK;
import com.briomax.briobpm.persistence.entity.VariableSistema;
import com.briomax.briobpm.persistence.entity.VwDatoActividad;
import com.briomax.briobpm.persistence.entity.VwDatoActividadPK;
import com.briomax.briobpm.persistence.entity.namedquery.DatoActividad;
import com.briomax.briobpm.persistence.repository.IComposicionCorreoRepository;
import com.briomax.briobpm.persistence.repository.IDatoAreaTrabajoRepository;
import com.briomax.briobpm.persistence.repository.IDatoProcesoNodoRepository;
import com.briomax.briobpm.persistence.repository.IEntidadRepository;
import com.briomax.briobpm.persistence.repository.IInNodoProcesoRepository;
import com.briomax.briobpm.persistence.repository.ILocalidadEntidadRepository;
import com.briomax.briobpm.persistence.repository.IStNodoProcesoRepository;
import com.briomax.briobpm.persistence.repository.IStVariableProcesoRepository;
import com.briomax.briobpm.persistence.repository.IVariableEntidadRepository;
import com.briomax.briobpm.persistence.repository.IVariableLocalidadRepository;
import com.briomax.briobpm.persistence.repository.IVariableSistemaRepository;
import com.briomax.briobpm.persistence.repository.IVwDatoActividadRepository;
import com.briomax.briobpm.transferobjects.InformacionAreaTrabajoTO;
import com.briomax.briobpm.transferobjects.emun.MagicNumber;
import com.briomax.briobpm.transferobjects.in.ColumnasAreaTrabajoTO;
import com.briomax.briobpm.transferobjects.in.DatoAreaTrabajoTO;
import com.briomax.briobpm.transferobjects.in.DatosAreaTrabajoTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.EstatusAccionesTO;
import com.briomax.briobpm.transferobjects.in.EstatusAreaTrabajoTO;
import com.briomax.briobpm.transferobjects.in.EstatusAtributosDatoTO;
import com.briomax.briobpm.transferobjects.in.EstatusAtributosMonedaTO;
import com.briomax.briobpm.transferobjects.in.EstatusTO;
import com.briomax.briobpm.transferobjects.in.EstatusVariablesTO;
import com.briomax.briobpm.transferobjects.in.NodoTO;

import lombok.var;
import lombok.extern.slf4j.Slf4j;
/**
 * El objetivo de la clase AreaTrabajoHelper.java es proporcionar métodos de ayuda relacionados con el area de trabajo.
 *
 * @author Alexis Zamora
 * @ver 1.0
 * @fecha Mar 14, 2024 4:12:01 PM
 * @since JDK 1.8
 */

@Service
@Transactional
@Slf4j
public class AreaTrabajoHelper implements IAreaTrabajoHelper{
	
	/** El atributo o variable Nodo Helper. */ 
	@Autowired
	NodoHelper nodoHelper;
	
	/** El atributo o variable Dato Proceso Repository. */
	@Autowired
	private IDatoProcesoNodoRepository iDatoProcesoNodoRepository;
	
	/** El atributo o variable entidad repository. */
	@Autowired
	private IEntidadRepository entidadRepository;
	
	/** El atributo o variable Localidad entidad repository. */
	@Autowired
	private ILocalidadEntidadRepository localidadEntidadRepository;

	/** El atributo o variable messages service. */
	@Autowired
	private IMessagesService messagesService;
	
	/** El atributo o variable St Nodo Proceso Repository. */
	@Autowired
	private IStNodoProcesoRepository stNodoProcesoRepository;

	/** El atributo o variable In Nodo Proceso Repository. */
	@Autowired
	private IInNodoProcesoRepository iInNodoProcesoRepository;
	
	/** El atributo o variable Dato Area Trabajo Repository. */
	@Autowired 
	private IDatoAreaTrabajoRepository iDatoAreaTrabajoRepository;
	
	/** El atributo o Variable Entidad Repository. */
	@Autowired
	private IVariableEntidadRepository iVariableEntidadRepository;
	
	/** El atributo o Variable Variable Localidad Repository. */
	@Autowired
	private IVariableLocalidadRepository iVariableLocalidadRepository;
	
	/** El atributo o Variable St Variable Proceso Repository. */
	@Autowired
	private IStVariableProcesoRepository iStVariableProcesoRepository;
	
	/** El atributo o Variable Sistema Repository. */
	@Autowired
	private IVariableSistemaRepository iVariableSistemaRepository;
	
	/** El atributo o variable Vista Dato Actividad Repository. */
	@Autowired 
	private IVwDatoActividadRepository iVwDatoActividadRepository;
	
	/** El atributo o variable Vista Dato Actividad DAO. */
	@Autowired 
	VwDatoActividadDAO vwDatoActividadDAO;
	
	/** El atributo o variable St Nodo Proceso DAO. */
	@Autowired 
	StNodoProcesoDAO stNodoProcesoDAO;
	
	/** El atributo o variable AreaTrabajo DAO. */
	@Autowired 
	IAreaTrabajoDAO areaTrabajoDAO;
	
	// SP_LEE_ACCIONES_ACTIVIDAD
	@Override
	public EstatusAccionesTO leeAccionesActividad(DatosAutenticacionTO session, NodoTO nodo) throws BrioBPMException {
		
		String cveUsuario = session.getCveUsuario();
		String cveEntidad = session.getCveEntidad();
		String cveInstancia = nodo.getCveInstancia();
		String cveNodo = nodo.getCveNodo();
		String cveProceso = nodo.getCveProceso();
		Integer idNodo = nodo.getIdNodo();
		Integer secuenciaNodo = nodo.getSecuenciaNodo();
		BigDecimal version = nodo.getVersion();
		String botonCancelar = "";
		String botonConsultar = "";
		String botonEjecutar = "";
		String botonLiberar = "";
		String botonTerminar = "";
		String botonTomar = "";
		String botonBitacora = "";
		String botonGuardar = "";
		String cveUsuarioBloquea;
		String estado;
		Date fechaBloquea;
		String idProceso;

		
		// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		EstatusAccionesTO result = EstatusAccionesTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();
		idProceso = Constants.LEE_ACCIONES_ACTIVIDAD;
		nodo.setIdProceso(idProceso);
		
		// VALIDA LA EXISTENCIA DE LA ACTIVIDAD ESPECIFICADA
		EstatusTO valDIN = nodoHelper.valDatosIn(session, nodo);
		
		if(valDIN.getTipoExcepcion().equals(Constants.ERROR)) {
			result.setMensaje(valDIN.getMensaje());
			result.setTipoExcepcion(Constants.ERROR);
			return result;
		}
		//OBTENER LA CONFIGURACIÓN DE ACCIONES Y ETIQUETAS DEL NODO
		String habilitarTomar = " ";
		String etiquetaTomar = " ";
		String habilitarLiberar = " ";
		String etiquetaLiberar = " ";
		String habilitarEjecutar = " ";
		String etiquetaEjecutar = " ";
		String habilitarTerminar = " ";
		String etiquetaTerminar = " ";
		String habilitarCancelar = " ";
		String etiquetaCancelar = " ";
		String habilitarConsultar = " ";
		String etiquetaConsultar = " ";
		String habilitarBitacora = " ";
		String etiquetaBitacora = " ";
		String habilitarGuardar = " ";
		String etiquetaGuardar = " ";
		
		StNodoProcesoPK id = StNodoProcesoPK.builder()
				.cveEntidad(cveEntidad)
				.cveProceso(cveProceso)
				.version(version)
				.cveNodo(cveNodo)
				.idNodo(idNodo)
				.build();
		
		Optional<StNodoProceso> entity = stNodoProcesoRepository.findById(id);
		if (entity.isPresent()) {
		    StNodoProceso nodoProceso = entity.get();
		    botonTomar = Optional.ofNullable(nodoProceso.getBotonTomar()).orElse("");
		    etiquetaTomar = Optional.ofNullable(nodoProceso.getEtiquetaBotonTomar()).orElse("");
		    botonLiberar = Optional.ofNullable(nodoProceso.getBotonLiberar()).orElse("");
		    etiquetaLiberar = Optional.ofNullable(nodoProceso.getEtiquetaBotonLiberar()).orElse("");
		    botonEjecutar = Optional.ofNullable(nodoProceso.getBotonEjecutar()).orElse("");
		    etiquetaEjecutar = Optional.ofNullable(nodoProceso.getEtiquetaBotonEjecutar()).orElse("");
		    botonTerminar = Optional.ofNullable(nodoProceso.getBotonTerminar()).orElse("");
		    etiquetaTerminar = Optional.ofNullable(nodoProceso.getEtiquetaBotonTerminar()).orElse("");
		    botonCancelar = Optional.ofNullable(nodoProceso.getBotonCancelar()).orElse("");
		    etiquetaCancelar = Optional.ofNullable(nodoProceso.getEtiquetaBotonCancelar()).orElse("");
		    botonConsultar = Optional.ofNullable(nodoProceso.getBotonConsultar()).orElse("");
		    etiquetaConsultar = Optional.ofNullable(nodoProceso.getEtiquetaBotonConsultar()).orElse("");
		    botonBitacora = Optional.ofNullable(nodoProceso.getBotonBitacora()).orElse("");
		    etiquetaBitacora = Optional.ofNullable(nodoProceso.getEtiquetaBotonBitacora()).orElse("");
		    botonGuardar = Optional.ofNullable(nodoProceso.getBotonGuardar()).orElse("NO");
		    etiquetaGuardar = Optional.ofNullable(nodoProceso.getEtiquetaBotonGuardar()).orElse("");
		}

		// OBTIENE LA SITUACIÓN ACTUAL DEL NODO, ASÍ COMO EL USUARIO Y LA FECHA DE BLOQUEO
		estado = " ";
		cveUsuarioBloquea = " ";
		fechaBloquea = null;
		
		InNodoProcesoPK id2 = InNodoProcesoPK.builder()
				.cveEntidad(cveEntidad)
				.cveProceso(cveProceso)
				.version(version)
				.cveInstancia(cveInstancia)
				.cveNodo(cveNodo)
				.idNodo(idNodo)
				.secuenciaNodo(secuenciaNodo)
				.build();
		
		log.debug("--- inNodoProcesoRepository.findById(id) leeAccionesActividad");
		Optional<InNodoProceso> entity2 = iInNodoProcesoRepository.findById(id2);
		
		if (entity2.isPresent()) {
		    InNodoProceso inNodoProceso = entity2.get();

		    // Obtener el estado y asignar un valor por defecto si es nulo
		    estado = Optional.ofNullable(inNodoProceso.getEstado()).orElse(" ");

		    // Obtener valores directamente
		    cveUsuarioBloquea = inNodoProceso.getUsuarioBloquea();
		    fechaBloquea = inNodoProceso.getFechaBloquea();
		}
		
		// HABILITANDO, DESHABILITANDO LA ACCIÓN "CONSULTAR"
		if (botonConsultar.equals(Constants.SI)) {
			habilitarConsultar = Constants.SI;
		} else {
			habilitarConsultar = Constants.NO;
		}
		
		// DETERMINA EL RESTO DE LAS ACCIONES QUE SE HABILITAN Y DESHABILITAN
		if(estado.equals(Constants.REGISTRO) && fechaBloquea == null) {
		    // TOMAR
		    habilitarTomar = botonTomar.equals(Constants.SI) ? Constants.SI : Constants.NO;

		    // LIBERAR
		    habilitarLiberar = Constants.NO;

		    // EJECUTAR
		    habilitarEjecutar = botonEjecutar.equals(Constants.SI) ? Constants.SI : Constants.NO;

		    // TERMINAR
		    habilitarTerminar = botonTerminar.equals(Constants.SI) ? Constants.SI : Constants.NO;
		    habilitarGuardar = botonTerminar.equals(Constants.SI) ? Constants.SI : Constants.NO;

		    // CANCELAR
		    habilitarCancelar = Constants.NO;
			
		} else if(estado.equals(Constants.REGISTRO) && fechaBloquea != null){
			
			// Lógica para habilitar cada botón según las condiciones
			// TOMAR
			habilitarTomar = Constants.NO;

			// LIBERAR
			if (botonLiberar.equals(Constants.SI) && cveUsuario.equals(cveUsuarioBloquea)) {
			    habilitarLiberar = Constants.SI;
			}

			// EJECUTAR
			if (botonEjecutar.equals(Constants.SI) && cveUsuario.equals(cveUsuarioBloquea)) {
			    habilitarEjecutar = Constants.SI;
			}

			// TERMINAR
			if (botonTerminar.equals(Constants.SI) && cveUsuario.equals(cveUsuarioBloquea)) {
			    habilitarTerminar = Constants.SI;
			    habilitarGuardar = Constants.SI;
			}

			// CANCELAR
			if (botonCancelar.equals(Constants.SI) && cveUsuario.equals(cveUsuarioBloquea)) {
			    habilitarCancelar = Constants.SI;
			}
		} else if(estado.equals(Constants.VENCIDA_POR_TIEMPO)){
			
			// TOMAR
		    habilitarTomar = botonTomar.equals(Constants.SI) ? Constants.SI : Constants.NO;

		    // LIBERAR
		    habilitarLiberar = Constants.NO;

		    // EJECUTAR
		    habilitarEjecutar = botonEjecutar.equals(Constants.SI) ? Constants.SI : Constants.NO;

		    // TERMINAR
		    habilitarTerminar = botonTerminar.equals(Constants.SI) ? Constants.SI : Constants.NO;
		    habilitarGuardar = botonTerminar.equals(Constants.SI) ? Constants.SI : Constants.NO;

		    // CANCELAR
		    habilitarCancelar = Constants.NO;
			
			
		} else if (estado.equals(Constants.TERMINADA) || estado.equals(Constants.CANCELADA)) {
		    // Asignar NO a todas las variables cuando el estado es TERMINADA o CANCELADA
		    habilitarTomar = Constants.NO;
		    habilitarLiberar = Constants.NO;
		    habilitarEjecutar = Constants.NO;
		    habilitarTerminar = Constants.NO;
		    habilitarCancelar = Constants.NO;
		    habilitarGuardar = Constants.NO;
		}
		
		// PUESTO QUE SE TRATA DE UNA CONSULTA, SE QUEDA TAL CUAL ES REPORTADA POR LA CONFIGURACIÓN DEL NODO
		 habilitarBitacora = botonBitacora;
		 
		// Asignación de valores de salida en el objeto result
		 result.setHabilitarTomar(habilitarTomar);
		 result.setEtiquetaTomar(etiquetaTomar);
		 result.setHabilitarLiberar(habilitarLiberar);
		 result.setEtiquetaLiberar(etiquetaLiberar);
		 result.setHabilitarEjecutar(habilitarEjecutar);
		 result.setEtiquetaEjecutar(etiquetaEjecutar);
		 result.setHabilitarTerminar(habilitarTerminar);
		 result.setEtiquetaTerminar(etiquetaTerminar);
		 result.setHabilitarConsultar(habilitarConsultar);
		 result.setEtiquetaConsultar(etiquetaConsultar);
		 result.setHabilitarCancelar(habilitarCancelar);
		 result.setEtiquetaCancelar(etiquetaCancelar);
		 result.setHabilitarBitacora(habilitarBitacora);
		 result.setEtiquetaBitacora(etiquetaBitacora);
		 result.setEtiquetaGuardar(etiquetaGuardar);
		 result.setHabilitarGuardar(habilitarGuardar);
		return result;
	}

	// SP_LEE_INF_AREA_TRABAJO_VS
	@Override
	public EstatusAreaTrabajoTO leeInfAreaTrabajoVS(DatosAutenticacionTO session, NodoTO nodo, 
			List <DatosAreaTrabajoTO> datosAreaTrabajo, String cveAreaTrabajo, Integer idNodo) throws BrioBPMException {
		
		String cveEntidad = session.getCveEntidad();
		String cveInstancia = nodo.getCveInstancia();
		String cveProceso = nodo.getCveProceso();
		String cveNodo = nodo.getCveNodo();
		BigDecimal version = nodo.getVersion();
		Integer numColumnas;
		String cveDatoMoneda;
		String estadoActividad;
		Integer ordenDato;
		String origenDatoVS;
		String sufijoMoneda;
		Integer secuenciaNodo;
		String tipoDato;
		String valorBaseDatos;
		String valorPantalla;
		String vsCveVariable;
		BigDecimal vsValorDecimal;
		Date vsValorFecha;
		Integer vsValorEntero;
		String vsValorAlfanumerico;
		
		// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		EstatusAreaTrabajoTO result = EstatusAreaTrabajoTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.datosAreaTrabajo(datosAreaTrabajo)
				.build();
		origenDatoVS = Constants.VARIABLE_SISTEMA;
		sufijoMoneda = Constants.MON;

		//SVM se ajustan llaves y manejo se seguridad por instancia
		// CURSOR DE DATOS PARA EL ÁREA DE TRABAJO SOLICITADA
		List<DatoAreaTrabajoTO> datosAtVs = new ArrayList<DatoAreaTrabajoTO>();
		List<Object> listaDatos = iDatoAreaTrabajoRepository.obtenerDatosATSistema(
						cveEntidad, cveProceso, version, cveAreaTrabajo, 
						origenDatoVS, cveNodo, idNodo, session.getCveUsuario());
		if(!listaDatos.isEmpty()) {
			listaDatos.forEach(item -> {
				Object[] row = (Object[]) item;
				var itemSelected = DatoAreaTrabajoTO.builder()
						.ordenDato((Integer) Arrays.asList(row).get(MagicNumber.ZERO.getValue()))
						.cveVariable((String) Arrays.asList(row).get(MagicNumber.ONE.getValue()))
						.cveInstancia((String) Arrays.asList(row).get(MagicNumber.TWO.getValue()))
						.secuenciaNodo((Integer) Arrays.asList(row).get(MagicNumber.THREE.getValue()))
						.estado((String) Arrays.asList(row).get(MagicNumber.FOUR.getValue()))
						.build();
				datosAtVs.add(itemSelected);
				}
			);
		}
		// CARGA EN TABLA TEMPORAL LOS DATOS DEL PROCESO
		numColumnas = 0;
		log.info("datosAtVs " + datosAtVs.size());
		for(DatoAreaTrabajoTO dato: datosAtVs) {
			ordenDato = dato.getOrdenDato();
			vsCveVariable = dato.getCveVariable();
			cveInstancia = dato.getCveInstancia();
			secuenciaNodo = dato.getSecuenciaNodo();
			estadoActividad = dato.getEstado();
			// LEE VALOR DE LA VARIABLE
			EstatusVariablesTO leeVVsis = nodoHelper.leerValorVsis(session, cveProceso, version, vsCveVariable);
			
			tipoDato = leeVVsis.getTipoDato();
			vsValorAlfanumerico = leeVVsis.getValorAlfanumerico();
			vsValorEntero = leeVVsis.getValorEntero();
			vsValorDecimal = leeVVsis.getValorDecimal();
			vsValorFecha = leeVVsis.getValorFecha();
			result.setMensaje(leeVVsis.getMensaje());
			
			if(leeVVsis.getTipoExcepcion().equals(Constants.ERROR)) {
				result.setTipoExcepcion(Constants.ERROR);
				return result;
			}
			valorBaseDatos = "";
			if(tipoDato.equals(Constants.ALFANUMERICO)) {
				valorBaseDatos = vsValorAlfanumerico;
			} else if(tipoDato.equals(Constants.DECIMAL) || tipoDato.equals(Constants.MONEDA)) {
				valorBaseDatos = vsValorDecimal.toString();
			} else if(tipoDato.equals(Constants.ENTERO)) {
				valorBaseDatos = vsValorEntero.toString();
			} else if(tipoDato.equals(Constants.FECHA)) {
				valorBaseDatos = nodoHelper.formatFecha(vsValorFecha, "yyyy-MM-dd"); 
			}
			valorPantalla = "";
			if(tipoDato.equals(Constants.ALFANUMERICO)) {
				valorPantalla = vsValorAlfanumerico;
			} else if(tipoDato.equals(Constants.DECIMAL) || tipoDato.equals(Constants.MONEDA)) {
				valorPantalla = ""; // falta funcion decimales
			} else if(tipoDato.equals(Constants.ENTERO)) {
				valorPantalla = vsValorEntero.toString();
			} else if(tipoDato.equals(Constants.FECHA)) {
				valorPantalla = nodoHelper.formatFecha(vsValorFecha, "yyyy-MM-dd");
			}
			
			// SENTENCIA INSERT PARA LA TABLA TEMPORAL QUE CONTIENE LOS VALORES DE LOS DATOS DEL
			// AREA DE TRABAJO
			numColumnas = numColumnas + 1;
			DatosAreaTrabajoTO dto = DatosAreaTrabajoTO.builder()
					.cveInstancia(cveInstancia)
					.secuenciaNodo(secuenciaNodo)
					.secuenciaDato(numColumnas)
					.ordenDato(ordenDato)
					.cveDato(vsCveVariable)
					.situacion(estadoActividad)
					.valorBaseDatos(valorBaseDatos)
					.valorPantalla(valorPantalla)
					.build();
			//datosAreaTrabajo.add(dto);
			
			if (tipoDato.equals(Constants.FECHA)) {
				cveDatoMoneda = vsCveVariable + sufijoMoneda;
				valorBaseDatos = "";
				valorBaseDatos = leeVVsis.getValorAlfanumerico();
				
				valorPantalla = "";
				valorPantalla = leeVVsis.getValorAlfanumerico();
				numColumnas = numColumnas + 1;
				
				dto.setCveInstancia(cveInstancia);
				dto.setSecuenciaNodo(secuenciaNodo);
				dto.setSecuenciaDato(numColumnas);
				dto.setCveDato(cveDatoMoneda);
				dto.setSituacion(estadoActividad);
				dto.setValorBaseDatos(valorBaseDatos);
				dto.setValorPantalla(valorPantalla);
				//datosAreaTrabajo.add(dto);
			}
			
			if (!datosAreaTrabajo.contains(dto)) {
				datosAreaTrabajo.add(dto);
			}
		}
		

		result.setDatosAreaTrabajo(datosAreaTrabajo);
		return result;
	}

	// SP_LEE_INF_AREA_TRABAJO
	@Override
	public DAORet<EstatusAreaTrabajoTO, RetMsg> leeInfAreaTrabajo(DatosAutenticacionTO session, NodoTO nodo, String cveAreaTrabajo, String cveAreaTrabajoTarjeta) throws BrioBPMException {
		//log.info("\t-----DATOS leeInfAreaTrabajo {}{}{}{}", session, nodo, cveAreaTrabajo);
		
		String cveEntidad = session.getCveEntidad();
		String cveNodo = nodo.getCveNodo();
		String cveProceso = nodo.getCveProceso();
		Integer idNodo = nodo.getIdNodo();
		BigDecimal version =  nodo.getVersion();
		String cveFechaLimite;
		String cveFechaFinEspera;
		String cveInstancia;
		String cveNodoTemporizador;
		String cveUsuarioBloquea;
		String datosActividad;
		String etiquetaCancelar = null;
		String etiquetaConsultar = null;
		String etiquetaEjecutar = null;
		String etiquetaLiberar = null;
		String etiquetaTerminar = null;
		String etiquetaTomar = null;
		String etiquetaBitacora = null;
		String estiloDato = null;
		String habilitarBitacora = null;
		String habilitarCancelar = null;
		String habilitarConsultar = null;
		String habilitarEjecutar = null;
		String habilitarLiberar = null;
		String habilitarTerminar = null;
		String habilitarTomar = null;
		String noAplica;
		Integer ordenDato;

		String secuencia;
		String secuenciaDatoEstilo = null;
		String secuenciaDatoEstiloObt = null;
		Integer secuenciaNodo;

		String valorBaseDatos;
		String valorPantalla;
		RetMsg msg = null;
		
		// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		EstatusAreaTrabajoTO result = EstatusAreaTrabajoTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();

		cveFechaLimite = Constants.DA_FECHA_HORA_LIMITE;
		cveFechaFinEspera = Constants.DA_FECHA_HORA_FIN_ESPERA;
		noAplica = Constants.NO_APLICA;
				
		// INICIALIZA LOS TIPOS DE ACCESO A LAS ACTIVIDADES DEL NODO
		cveNodoTemporizador = Constants.TEMPORIZADOR;
		
	    // Crea listas para almacenar la información del área de trabajo y el resultado final		
		// CREANDO TABLA QUE ALMACENA LA LISTA DE VALORES DE LOS NODOS QUE FORMAN EL ÁREA DE TRABAJO
		List <DatosAreaTrabajoTO> datosAreaTrabajo = new ArrayList<DatosAreaTrabajoTO>();

		// TABLA QUE CONTIENE EL RESULT SET FINAL DE INFORMACIÓN DEL ÁREA DE TRABAJO
		List <InformacionAreaTrabajoTO> infAreaTrabajo = new ArrayList<InformacionAreaTrabajoTO>();
		
		EstatusAreaTrabajoTO estatusAT = null;

		
		// LISTA DE ID NODOS
		List<Integer> crIdNodos = new ArrayList<Integer>();
		crIdNodos = stNodoProcesoRepository.obtenerIdNodos(cveEntidad, cveProceso, version, cveNodo, session.getCveUsuario(), cveNodoTemporizador);  // cambiar a dinamica cveNodoTemporizador
			
	    // Si el nodo actual no es un temporizador, agrega su ID a la lista de nodos
		if(!cveNodo.equals(cveNodoTemporizador)) {
			crIdNodos.add(idNodo);
		}
		log.info("crIdNodos: " + crIdNodos.size() + " " + crIdNodos.toString());
		// Mapa que almacenará los datos agrupados por clave de instancia
		Map<String, List<DatosAreaTrabajoTO>> mapDatos = new HashMap<String, List<DatosAreaTrabajoTO>>();
		
		// RECORRE LA LISTA DE ID_NODO
		// Procesa cada nodo de la lista
		for(Integer idNodoProcesar: crIdNodos) {
			datosAreaTrabajo = new ArrayList<DatosAreaTrabajoTO>();
			
			// OBTIENE INFORMACIÓN DE LOS DATOS NATIVOS DE LAS ACTIVIDADES DE PROCESOS
			estatusAT = leeInfAreaTrabajoDPN(session, nodo, datosAreaTrabajo, cveAreaTrabajo, idNodoProcesar);			
			if(!estatusAT.getTipoExcepcion().equals(Constants.OK)) {
				msg = RetMsg.builder().status(result.getTipoExcepcion()).message(result.getMensaje()).build();
				result.setTipoExcepcion(Constants.ERROR);
				return new DAORet<EstatusAreaTrabajoTO, RetMsg>(result, msg);
			}
			datosAreaTrabajo = estatusAT.getDatosAreaTrabajo();
			log.debug("datosAreaTrabajo despues de datos nativos: " + datosAreaTrabajo.size() + " " + datosAreaTrabajo.toString());	
			
			// OBTIENE INFORMACIÓN DE LAS VARIABLES DE PROCESO
			estatusAT = leeInfAreaTrabajoVP(session, nodo, datosAreaTrabajo, cveAreaTrabajo, idNodoProcesar, cveAreaTrabajoTarjeta);

			if(!estatusAT.getTipoExcepcion().equals(Constants.OK)) {
				msg = RetMsg.builder().status(result.getTipoExcepcion()).message(result.getMensaje()).build();
				result.setTipoExcepcion(Constants.ERROR);
				return new DAORet<EstatusAreaTrabajoTO, RetMsg>(result, msg);
			}
			datosAreaTrabajo = estatusAT.getDatosAreaTrabajo();
			log.debug("datosAreaTrabajo despues de variables de proceso: " + datosAreaTrabajo.size() + " " + datosAreaTrabajo.toString());	

			// OBTIENE INFORMACIÓN DE LAS VARIABLES A NIVEL DE LOCALIDAD
			estatusAT = leeInfAreaTrabajoVL(session, nodo, datosAreaTrabajo, cveAreaTrabajo, idNodoProcesar);
			if(!estatusAT.getTipoExcepcion().equals(Constants.OK)) {
				msg = RetMsg.builder().status(result.getTipoExcepcion()).message(result.getMensaje()).build();
				result.setTipoExcepcion(Constants.ERROR);
				return new DAORet<EstatusAreaTrabajoTO, RetMsg>(result, msg);
			}
			datosAreaTrabajo = estatusAT.getDatosAreaTrabajo();
			log.debug("datosAreaTrabajo despues de variables de nivel de localidad: " + datosAreaTrabajo.size() + " " + datosAreaTrabajo.toString());	
			
			// OBTIENE INFORMACIÓN DE LAS VARIABLES A NIVEL DE ENTIDAD
			estatusAT = leeInfAreaTrabajoVE(session, nodo, datosAreaTrabajo, cveAreaTrabajo, idNodoProcesar);
			if(!estatusAT.getTipoExcepcion().equals(Constants.OK)) {
				msg = RetMsg.builder().status(result.getTipoExcepcion()).message(result.getMensaje()).build();
				result.setTipoExcepcion(Constants.ERROR);
				return new DAORet<EstatusAreaTrabajoTO, RetMsg>(result, msg);
			}
			datosAreaTrabajo = estatusAT.getDatosAreaTrabajo();
			log.debug("datosAreaTrabajo despues de variables a nivel de entidad: " + datosAreaTrabajo.size() + " " + datosAreaTrabajo.toString());	

			// OBTIENE INFORMACIÓN DE LAS VARIABLES A NIVEL DE SISTEMA
			estatusAT = leeInfAreaTrabajoVS(session, nodo, datosAreaTrabajo, cveAreaTrabajo, idNodoProcesar);
			if(!estatusAT.getTipoExcepcion().equals(Constants.OK)) {
				msg = RetMsg.builder().status(result.getTipoExcepcion()).message(result.getMensaje()).build();
				result.setTipoExcepcion(Constants.ERROR);
				return new DAORet<EstatusAreaTrabajoTO, RetMsg>(result, msg);
			}
			datosAreaTrabajo = estatusAT.getDatosAreaTrabajo();
			log.debug("datosAreaTrabajo despues de variables a nivel de sistema: " + datosAreaTrabajo.size() + " " + datosAreaTrabajo.toString());	

			// PREPARA EL CURSOR PARA GENERAR EL RESULT SET DE SALIDA
			// Filtra y agrupa los datos por clave de instancia y los agrega al mapa de datos
			List<DatosAreaTrabajoTO> listaFiltrada = new ArrayList<DatosAreaTrabajoTO>();
			List<DatosAreaTrabajoTO> listaFiltrada2 = new ArrayList<DatosAreaTrabajoTO>();
			//-List<ValueType> listaSecuencia = new ArrayList<ValueType>();
			HashMap<String, String> hashMap = new HashMap<>();
			
			
			listaFiltrada2.addAll(datosAreaTrabajo);
			
			List<DatosAreaTrabajoTO> eliminados = new ArrayList<DatosAreaTrabajoTO>(); // Lista para almacenar elementos eliminados
			List<Integer> encontrados = new ArrayList<>(); // Lista para almacenar índices encontrados
			int j = 0;
			cveInstancia = "";
			log.info("datosAreaTrabajo: " + datosAreaTrabajo.size());
			
			for (DatosAreaTrabajoTO ite : datosAreaTrabajo) {
				j = 0;	
				datosActividad = "";
				DatosAreaTrabajoTO datofinal = null; // Variable para almacenar el dato final
				List<DatosAreaTrabajoTO> datoPorInstancia = new ArrayList<DatosAreaTrabajoTO>(); // Lista para almacenar datos por instancia
				int iOrdenDato = 0;
				
				//log.info("listaFiltrada2: " + listaFiltrada2.size());

				 // Itera sobre cada elemento en 'listaFiltrada2'
				for(int i = 0 ; i < listaFiltrada2.size() ; i++) {
					DatosAreaTrabajoTO dato = listaFiltrada2.get(i); // Obtiene el dato actual
					
					 // Verifica si el dato actual pertenece a la misma instancia y es un GRID
					//log.info("------ZZZ DATO COMPLETO: " + dato.toString());
					//log.debug("dato CveInstancia: " + dato.getCveInstancia() + " ite cveInstancia: " + ite.getCveInstancia() + " tipo: " + dato.getTipoTarjeta());
					if(dato.getCveInstancia().equals(ite.getCveInstancia()) && !encontrados.contains(dato.getOrdenDato()) && "GRID".equals(dato.getTipoTarjeta())) {
						//log.info("Es tipo Grid");
						ordenDato = dato.getOrdenDato(); // Asigna el orden del dato
			            valorBaseDatos = dato.getValorBaseDatos(); // Asigna el valor base de datos
			            valorPantalla = dato.getValorPantalla(); // Asigna el valor en pantalla
			            cveInstancia = dato.getCveInstancia(); // Asigna la clave de la instancia

			            // Formatea la secuencia de acuerdo al orden del dato
			            if(ordenDato < 10) {
			                secuencia = "0" + ordenDato.toString(); // Si el orden es menor a 10, agrega un 0 al inicio
			            } else {
			                secuencia = ordenDato.toString(); // Si no, convierte el orden a cadena
			            }

			            // Genera la cadena de datos actividad
			            if(datosActividad.isEmpty()) {
			                datosActividad = Constants.SECUENCIA; // Si 'datosActividad' está vacío, inicia con la secuencia
			            } else {
			                datosActividad = datosActividad + "|" + Constants.SECUENCIA; // Si no, agrega la secuencia al final
			            }

	                    // Ajusta el valor base de datos y valor pantalla
						if ( valorBaseDatos != null ) {
							valorBaseDatos = valorBaseDatos.trim();
							valorPantalla = valorBaseDatos.trim();
						} else {
							valorBaseDatos = "";
							valorPantalla = "";
						}
						
						if (j < ordenDato) {
							j = ordenDato;
						}
						String datos = "|" + valorBaseDatos + "|" + valorPantalla;						
						hashMap.put(secuencia, datos);
						
						datofinal = dato;
						
						// Marca si el dato es una fecha límite
						datofinal.setFechaHoraLimite(false);
						secuenciaDatoEstilo = noAplica;
						estiloDato = noAplica;
						if (dato.getCveDatoProcesoNodo() != null) {
							log.info(">>> dato.getCveDatoProcesoNodo(): " + dato.getCveDatoProcesoNodo());
							if (dato.getCveDatoProcesoNodo().equals("DA.FECHA_HORA_LIMITE") ||
									dato.getCveDatoProcesoNodo().equals("DA.FECHA_HORA_FIN_ESPERA")) {
								datofinal.setFechaHoraLimite(true); // Marca como fecha límite si el dato es 'DA.FECHA_HORA_LIMITE'
								
								secuenciaDatoEstiloObt = dato.getCveDatoProcesoNodo().equals(cveFechaLimite) ? cveFechaLimite: cveFechaFinEspera;
    							
			                    if (iOrdenDato < dato.getOrdenDato()) {
			                        iOrdenDato = dato.getOrdenDato(); // Asigna el orden del dato a 'iOrdenDato'
								}
								datofinal.setOrdenDatoLimite(iOrdenDato);
							} 							
						}
						eliminados.add(dato);
						//iOrdenDato = dato.getOrdenDato();
						encontrados.add(ordenDato);
						
					}
					
					 // Si el dato pertenece a la misma instancia y es una TARJETA
					log.debug(">>> dato.getTipoTarjeta(): " + dato.getTipoTarjeta());
					log.debug(">>> dato.getCveInstancia(): " + dato.getCveInstancia());
					log.debug(">>> ite.getCveInstancia(): " + ite.getCveInstancia());
			        if(dato.getCveInstancia().equals(ite.getCveInstancia()) && dato.getTipoTarjeta().equals("TARJETA")) {
			        	//log.debug(">>>>>> Es tipo Tarjeta: " + dato.toString());
			        	eliminados.add(dato); // Agrega el dato a la lista de eliminados
			            datoPorInstancia.add(dato); // Agrega el dato a la lista 'datoPorInstancia'
			        }
				}	
				
				//log.info("*** eliminados: " + eliminados.size());
				if (eliminados.size() > 0) {
					boolean isFechaHora = false;
					for (DatosAreaTrabajoTO dato : eliminados) {
					    if (dato.isFechaHoraLimite()) {
					    	isFechaHora = true;
					        break;
					    }
					}
					listaFiltrada2.removeAll(eliminados);
					eliminados.clear();
					mapDatos.put(cveInstancia, datoPorInstancia);

					datosActividad = "";
					secuencia = "";
					for (int i = 1; i <= j; i++) {
						if(i < 10) {
							secuencia = "0" + i;
						} else {
							secuencia = String.valueOf(i);
						}

						if (hashMap.containsKey(secuencia)) {
							if(datosActividad.isEmpty()) {
								datosActividad = Constants.SECUENCIA;
							} else {
								datosActividad = datosActividad + "|" + Constants.SECUENCIA;
							}						
							datosActividad = datosActividad + secuencia + hashMap.get(secuencia);
						}						
					}
					datofinal.setDatosActividad(datosActividad);
					datofinal.setFechaHoraLimite(isFechaHora);
					datofinal.setOrdenDatoLimite(iOrdenDato);
					listaFiltrada.add(datofinal);
					encontrados = new ArrayList<>();
					
				}
				
				
			}
			   
			//log.info("--- CLAVE INSTANCIA:;:: " + cveInstancia);
			//log.info("--- HASH MAP DATOS: " + mapDatos.size() + " " + mapDatos.toString());
			result.setMapDatos(mapDatos);
			
			for(DatosAreaTrabajoTO datoTO : listaFiltrada) {
				cveInstancia = datoTO.getCveInstancia();
				secuenciaNodo = datoTO.getSecuenciaNodo();

				cveUsuarioBloquea = iVwDatoActividadRepository.encontrarUsuarioBloquea(cveEntidad, cveProceso, version, cveInstancia,
						cveNodo, idNodo, secuenciaNodo);		 // BUSCAR EN VISTA							    
				
				if (datoTO.isFechaHoraLimite()) {
					    
					// EJECUTA LA FUNCIÓN QUE RECUPERA EL ESTILO PARA LA FECHA LÍMITE SEGÚN EL NIVEL DE SERVICIO
					if (datoTO.getOrdenDatoLimite() != 0) {	
					    	VwDatoActividadPK datoActividadId = VwDatoActividadPK.builder()
					    			.cveEntidad(cveEntidad)
					    			.cveProceso(cveProceso)
					    			.version(version)
					    			.cveInstancia(cveInstancia)
					    			.cveNodo(cveNodo)
					    			.idNodo(idNodoProcesar)
					    			.secuenciaNodo(secuenciaNodo)
					    			.build(); // RASTREAR DE AQUI
					    	Optional<VwDatoActividad> datoActividadEntidad = iVwDatoActividadRepository.findById(datoActividadId);
					    	log.info("datoActividadEntidad encontrado: " +datoActividadEntidad);
					    	
					    	Date fecha = secuenciaDatoEstiloObt.equals(cveFechaLimite) ? datoActividadEntidad.get().getFechaLimite() : datoActividadEntidad.get().getFechaFinEspera();
					    	estiloDato = leeEstiloNivelServicio(datoActividadEntidad.get().getFechaCreacionActividad(),
					    										fecha,
												    			datoActividadEntidad.get().getEstadoActividad(),
												    			datoActividadEntidad.get().getFechoEstadoActual());
							secuenciaDatoEstilo = secuenciaDatoEstiloObt;
						}

					} else {
						secuenciaDatoEstilo = noAplica;
						estiloDato = noAplica;
					}	
						// OBTIENE LA SITUACIÓN DE LAS ACCIONES: HABILITAR, LIBERAR, EJECUTAR, TERMINAR, CONSULTAR;
						// ASÍ COMO LAS ETIQUETAS DE DICHAS ACCIONES PARA LA ACTIVIDAD ACTUAL
					NodoTO nodoActividad = NodoTO.builder()
								.cveProceso(cveProceso)
								.version(version)
								.cveInstancia(cveInstancia)
								.cveNodo(cveNodo)
								.idNodo(idNodoProcesar)
								.secuenciaNodo(secuenciaNodo)
								.build();
					EstatusAccionesTO leeAC = leeAccionesActividad(session, nodoActividad);
					
					if(leeAC.getTipoExcepcion().equals(Constants.ERROR)) {
							msg = RetMsg.builder().status(Constants.ERROR).message("Error general").build();
							return new DAORet<EstatusAreaTrabajoTO, RetMsg>(result, msg);
						}
					
					habilitarTomar = leeAC.getHabilitarTomar();
					etiquetaTomar = leeAC.getEtiquetaTomar();
					habilitarLiberar = leeAC.getHabilitarLiberar();
					etiquetaLiberar = leeAC.getEtiquetaLiberar();
					habilitarEjecutar = leeAC.getHabilitarEjecutar();
					etiquetaEjecutar = leeAC.getEtiquetaEjecutar();
					habilitarTerminar = leeAC.getHabilitarTerminar();
					etiquetaTerminar  = leeAC.getEtiquetaTerminar();
					habilitarConsultar = leeAC.getHabilitarConsultar();
					etiquetaConsultar = leeAC.getEtiquetaConsultar();
					habilitarCancelar = leeAC.getHabilitarCancelar();
					etiquetaCancelar = leeAC.getEtiquetaCancelar();
					habilitarBitacora = leeAC.getHabilitarBitacora();
					etiquetaBitacora = leeAC.getEtiquetaBitacora();
					result.setMensaje(leeAC.getMensaje());
					result.setTipoExcepcion(leeAC.getTipoExcepcion());
					
					//log.debug("----> cveUsuarioBloquea: " + cveUsuarioBloquea);
					
					InformacionAreaTrabajoTO infATTO = InformacionAreaTrabajoTO.builder()
								.cveInstancia(cveInstancia)
								.secuenciaNodo(secuenciaNodo)
								.situacion(datoTO.getSituacion())
								.usuarioBloquea(cveUsuarioBloquea)
								.secuenciaDatoEstilo(secuenciaDatoEstilo)
								.estilo(estiloDato)
								.habilitarTomar(habilitarTomar)
								.etiquetaTomar(etiquetaTomar)
								.habilitarLiberar(habilitarLiberar)
								.etiquetaLiberar(etiquetaLiberar)
								.habilitarEjecutar(habilitarEjecutar)
								.etiquetaEjecutar(etiquetaEjecutar)
								.habilitarTerminar(habilitarTerminar) // revisar traduccion
								.etiquetaTerminar(etiquetaTerminar)
								.habilitarConsultar(habilitarConsultar)
								.etiquetaConsultar(etiquetaConsultar)
								.habilitarCancelar(habilitarCancelar)
								.etiquetaCancelar(etiquetaCancelar)
								.habilitarBitacora(habilitarBitacora)
								.etiquetaBitacora(etiquetaBitacora)
								.habilitarGuardar(leeAC.getHabilitarGuardar())
								.etiquetaGuardar(leeAC.getEtiquetaGuardar())
								.datosActividad(datoTO.getDatosActividad())
								.build();
					
						infAreaTrabajo.add(infATTO);
			
						
						// SE VERIFICA LA EXISTENCIA DE ESTILO DE ACUERDO A LA FECHA LÍMITE Y EL NIVEL DE SERVICIO
						// SE COLOCA EL DATO EN EL ESTILO CONFIGURADO Y LA SECUENCIA DEL DATO AL QUE APLICA
						// EN CASO DE NO EXISTIR EL DATO, SE COLOCA 'NO APLICA' Y EL DATO DEL COLOR COMO 'NO APLICA'

						estiloDato = " ";

					
				}

		}	

		log.info("############### RETURN SP_LEE_INF_AREA_TRABAJO: " + result.toString());

		// revisar Consulta
		msg = RetMsg.builder().status(result.getTipoExcepcion()).message(result.getMensaje()).build();
		result.setDatosAreaTrabajo(datosAreaTrabajo);
		result.setInfAreaTrabajo(infAreaTrabajo);
		// REGRESA EL RESULT SET FINAL
		return new DAORet<EstatusAreaTrabajoTO, RetMsg>(result, msg);
	}

	// SP_LEE_INF_AREA_TRABAJO_DPN
	@Override
	public EstatusAreaTrabajoTO leeInfAreaTrabajoDPN(DatosAutenticacionTO session, NodoTO nodo,
			List<DatosAreaTrabajoTO> datosAreaTrabajo, String cveAreaTrabajo, Integer idNodo) throws BrioBPMException {
		
		// Se inicializan variables necesarias para la operación del método.
		String cveEntidad = session.getCveEntidad();
		String cveNodo = nodo.getCveNodo();
		String cveProceso = nodo.getCveProceso();
		BigDecimal version = nodo.getVersion();
		Integer decimales;
		String dpCveDatoProcesoNodo;
		Integer ordenDato;
		String qrySelect = null;
		String origenDatoDPN;
		Integer secuenciaDato;
		String tipoDato;
		String valorBaseDatos;
		String valorPantalla;
		boolean isFecha = false;
		String cveDatoProcesoNodo = "";
		
		// Se crean listas para almacenar resultados intermedios y finales.
		List<DatoActividad> list = new ArrayList<DatoActividad>();
		
		// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		EstatusAreaTrabajoTO result =EstatusAreaTrabajoTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();
		origenDatoDPN = Constants.DATO_PROCESO_NODO;
		//log.info("--- origenDatoDPN: " + origenDatoDPN);
		
		// CURSOR DE DATOS PARA EL ÁREA DE TRABAJO SOLICITADA
		List<DatoAreaTrabajoTO> datosATDPN = new ArrayList<DatoAreaTrabajoTO>();
		
		// Se obtienen los datos del proceso nodo desde el repositorio.
		List<Object> listaDAT = iDatoAreaTrabajoRepository.obtenerDatosProcesoNodo(
				cveEntidad, cveProceso, version, cveAreaTrabajo, origenDatoDPN, cveNodo, idNodo);
		//log.info("Tamaño Lista DAT: " + listaDAT.size());
		
		// Si la lista obtenida no está vacía, se procesan los resultados.
		if(!listaDAT.isEmpty()) {
			listaDAT.forEach(item -> {
				Object[] row = (Object[]) item;
				var itemSelected = DatoAreaTrabajoTO.builder()
					    .secuenciaDato(((BigDecimal) Arrays.asList(row).get(MagicNumber.ZERO.getValue())).intValue())
					    .ordenDato(((BigDecimal) Arrays.asList(row).get(MagicNumber.ONE.getValue())).intValue())
						.cveDatoProcesoNodo((String) Arrays.asList(row).get(MagicNumber.TWO.getValue()))
						.tipoTarjeta((String) Arrays.asList(row).get(MagicNumber.THREE.getValue()))
						//agregar tipo
						.build();
				datosATDPN.add(itemSelected);
				//log.info("Tamaño Lista datosATDPN: " + datosATDPN.size());
				}
			);
		}
		
		// CARGA EN TABLA TEMPORAL LOS DATOS DEL PROCESO
	    // Se recorren los datos obtenidos para generar una consulta SQL específica para cada dato.
		int j = 0;
		
		for(DatoAreaTrabajoTO dato : datosATDPN) {
			dpCveDatoProcesoNodo = dato.getCveDatoProcesoNodo();
			secuenciaDato = dato.getSecuenciaDato();
			ordenDato = dato.getOrdenDato();
			qrySelect = "";
			
			//log.info("--- dpCveDatoProcesoNodo: " + dpCveDatoProcesoNodo);
			origenDatoDPN = Constants.DATO_PROCESO_NODO;
			//log.info("--- origenDatoDPN: " + origenDatoDPN);
			
			// DE ACUERDO AL ORIGEN DEL DATO, OBTIENE LOS ATRIBUTOS NECESARIOS
			//log.info("leeAtributosDato desde leeInfAreaTrabajoDPN");
			EstatusAtributosDatoTO leeAD = leeAtributosDato(session, nodo, dpCveDatoProcesoNodo, origenDatoDPN);
			result.setMensaje(leeAD.getMensaje());
			result.setTipoExcepcion(leeAD.getTipoExcepcion());
			
	        // Si se produce un error al obtener los atributos, se retorna el resultado con el error.
			if(leeAD.getTipoExcepcion().equals(Constants.ERROR)) {
				result.setMensaje(leeAD.getMensaje());
				result.setTipoExcepcion(Constants.ERROR);
				return result;
			}
			
			// Se obtiene el número de decimales y tipo de dato para el procesamiento.
			decimales = leeAD.getDecimales();
			tipoDato = leeAD.getTipoDato();
			decimales = (decimales == null) ? 0 : decimales;
			
			// Se obtiene el número de decimales y tipo de dato para el procesamiento.
			
			 qrySelect =  "SELECT DA.CVE_INSTANCIA, " +
     				"	DA.SECUENCIA_NODO, " +
     				"	DAT.SECUENCIA_DATO, " +
     				"	DAT.ORDEN_DATO, " +
     				" " +	dpCveDatoProcesoNodo.trim() + " AS VALOR_BASE, " +
     				"	DA.ESTADO_ACTIVIDAD, ";
			 
			// Se definen los valores base de acuerdo al tipo de dato.
			valorBaseDatos = " ";
			valorPantalla = " ";
			cveDatoProcesoNodo = dpCveDatoProcesoNodo.trim();
			switch(tipoDato) {
			case Constants.ALFANUMERICO:
				isFecha = false;
				valorBaseDatos = dpCveDatoProcesoNodo;
				valorPantalla = dpCveDatoProcesoNodo;
				break;
			case Constants.DECIMAL:
				isFecha = false;
				valorBaseDatos = "CONVERT (VARCHAR (40), " + dpCveDatoProcesoNodo + ")";
				valorPantalla = "CONVERT (VARCHAR (40), " + dpCveDatoProcesoNodo + ")"; 
				//valorPantalla = "CONVERT (VARCHAR (40), dbo.FN_TRUNCA_DECIMALES ( " + dpCveDatoProcesoNodo + ", " +
				//		decimales.toString() + "))";
				break;
			case Constants.ENTERO:
				isFecha = false;
				valorBaseDatos = "CONVERT (VARCHAR (40), CONVERT (NUMERIC (20), " + dpCveDatoProcesoNodo + "))";
				valorPantalla = "CONVERT (VARCHAR (40), CONVERT (NUMERIC (20), " + dpCveDatoProcesoNodo + "))";
				break;
			case Constants.FECHA:
				isFecha = true;
				valorBaseDatos = dpCveDatoProcesoNodo;
				valorPantalla = dpCveDatoProcesoNodo;
				break;
			}
			
			// Se ajusta el origen del dato si es una fecha de creación de proceso.
			if("DA.FECHA_CREACION_PROCESO".equals(dpCveDatoProcesoNodo) ) {
			//log.info("---CAMBIO DE ORIFEN DATO DPN A VARIABLE PROCESO");
				origenDatoDPN = Constants.VARIABLE_PROCESO;
			}

			//SVM se ajustan llaves y manejo se seguridad por instancia
			// Se completa la consulta SQL con los valores de base y pantalla.
			qrySelect = qrySelect + valorBaseDatos + " AS VALOR_BASE_DATOS, ";			
			qrySelect = qrySelect + valorPantalla + " AS VALOR_PANTALLA, ";
			qrySelect = qrySelect + " ROW_NUMBER() OVER (ORDER BY DA.SECUENCIA_NODO, DAT.SECUENCIA_DATO) AS RNUMBER ";
			qrySelect = qrySelect + " FROM 	VW_DATO_ACTIVIDAD		DA, " +
								" 	DATO_AREA_TRABAJO	DAT, " +
								" 	DATO_PROCESO_NODO	DPN, " +
								" 	ST_NODO_PROCESO 	STNP, " +
								" 	IN_NODO_PROCESO		INNP "+
								"   WHERE DA.CVE_ENTIDAD = '" + cveEntidad + "' AND " +
								"	DA.CVE_PROCESO = '" + cveProceso + "' AND " +
				                "	DA.VERSION = " + version+ " AND " +
				                "	DA.CVE_NODO = '" + cveNodo + "' AND " +
				                "	DA.ID_NODO = " + idNodo + " AND " +				                
				                "	DAT.CVE_ENTIDAD = DA.CVE_ENTIDAD AND " +
				                "	DAT.CVE_PROCESO = DA.CVE_PROCESO AND " +
				                "	DAT.VERSION = DA.VERSION AND " +
				                "	DAT.CVE_AREA_TRABAJO = '" + cveAreaTrabajo + "' AND " +
				                "	DAT.SECUENCIA_TARJETA  = DAT.SECUENCIA_TARJETA AND " +
				                "	DAT.SECUENCIA_DATO = " + secuenciaDato + " AND " +
				                "	DAT.ORDEN_DATO = " + ordenDato + " AND " +
				                "	DAT.ORIGEN_DATO = '" + origenDatoDPN + "' AND " +
				                "	DPN.CVE_DATO_PROCESO_NODO = '" + dpCveDatoProcesoNodo + "' AND " +				                
				                "	STNP.CVE_ENTIDAD = DA.CVE_ENTIDAD AND " +
				                "	STNP.CVE_PROCESO = DA.CVE_PROCESO AND " +
				                "	STNP.VERSION = DA.VERSION AND " +
				                "	STNP.CVE_NODO = DA.CVE_NODO AND " +
				                "	STNP.ID_NODO = DA.ID_NODO AND " +				                
				                "	INNP.CVE_ENTIDAD = DA.CVE_ENTIDAD AND " +
				                "	INNP.CVE_PROCESO = DA.CVE_PROCESO AND " +
				                "	INNP.VERSION = DA.VERSION AND " +
				                "	INNP.CVE_INSTANCIA = DA.CVE_INSTANCIA AND " +
				                "	INNP.CVE_NODO = DA.CVE_NODO AND " +
				                "	INNP.ID_NODO = DA.ID_NODO AND " +
				                "   INNP.SECUENCIA_NODO = DA.SECUENCIA_NODO " +
						        " 	AND ( (STNP.TIPO_ACCESO = 'ROL' AND " +
								"		'" + session.getCveUsuario() + "' IN 	( " + 
								"			SELECT	UR.CVE_USUARIO " +  
								"				FROM	USUARIO_ROL			UR, " + 
								"						ST_ROL_NODO			STRN " + 
								"				WHERE	" +
								"					UR.CVE_ENTIDAD = STRN.CVE_ENTIDAD	AND " + 
								"					UR.CVE_PROCESO = STRN.CVE_PROCESO	AND " + 
								"					UR.VERSION = STRN.VERSION			AND " + 
								"					UR.CVE_ROL = STRN.CVE_ROL  AND" + 
								"                   STRN.CVE_ENTIDAD = INNP.CVE_ENTIDAD	AND " + 
								"					STRN.CVE_PROCESO = INNP.CVE_PROCESO		AND " + 
								"					STRN.VERSION = INNP.VERSION				AND " + 
								"					STRN.CVE_NODO = INNP.CVE_NODO		AND " + 
								"					STRN.ID_NODO = INNP.ID_NODO			 " + 
												"		)  ) " + 
				    	        " 	OR ( STNP.TIPO_ACCESO = 'ROL_USUARIO' AND INNP.USUARIO_CREADOR = '" + session.getCveUsuario() + "'  ) " +
								"   OR '" + session.getCveUsuario() + "' = " +
								" 		( SELECT CVE_USUARIO FROM IN_NODO_PROCESO_USUARIO " + 
								"					WHERE CVE_ENTIDAD = INNP.CVE_ENTIDAD	AND " + 
								"					CVE_PROCESO = INNP.CVE_PROCESO			AND " + 
								"					VERSION = INNP.VERSION 					AND " + 
								"					CVE_INSTANCIA = INNP.CVE_INSTANCIA 		AND " + 
								"					CVE_NODO = INNP.CVE_NODO				AND " + 
								"					ID_NODO = INNP.ID_NODO					AND " + 
								"					SECUENCIA_NODO = INNP.SECUENCIA_NODO    AND  " + 
								"                   CVE_USUARIO = '"+ session.getCveUsuario() + "'" +
								"		) ) ";
			

			  // Se procesan los datos obtenidos de la consulta y se añaden a la lista final.
				list.clear();
				list = new ArrayList<DatoActividad>();
				//log.info("listt tamaño: {}", list.size());
				log.info("qrySelect : " + qrySelect);
				list = vwDatoActividadDAO.obtenerDatoActividad(qrySelect);
				//log.info("xxxxxx list tamaño: {}  TIPO:{}", list.size(), dato.getTipoTarjeta());
				if(list.size() > 0) {
					for (DatoActividad item : list) {
						//log.info("------->> ITEM: " + item.toString() + " " + valorBaseDatos);
						
						DatosAreaTrabajoTO ins = DatosAreaTrabajoTO.builder()
						        .cveInstancia(item.getId().getCveInstancia())
						    	.secuenciaNodo(item.getId().getSecuenciaNodo())
						        .secuenciaDato(secuenciaDato)
						        .ordenDato(ordenDato)
						        .cveDato(item.getValorBase())
						        .situacion(item.getEstadoActividad())
						        .valorBaseDatos(item.getValorBaseDatos())
						        .valorPantalla(item.getValorPantalla())
						        .tipoTarjeta(dato.getTipoTarjeta())
						        .cveDatoProcesoNodo(cveDatoProcesoNodo)
						        .build();
						if(dpCveDatoProcesoNodo.equals("DA.ESTADO_ACTIVIDAD")) {
							//log.info("------------>>> CAMBIA ESTADO ACTIVIDAD");
							ins.setValorBaseDatos(item.getEstadoActividad());
							ins.setValorPantalla(item.getEstadoActividad());		
						}
					    
					    //log.info("Mostrar DatosAreaTrabajoTO INS ");
					    // Se verifica si el dato ya existe en la lista de datos de área de trabajo.
					    if (!datosAreaTrabajo.contains(ins)) {
						    datosAreaTrabajo.add(ins);
					    }
					}
				}
			
		}
		
		// Se establece el resultado final con los datos del área de trabajo.
	    result.setDatosAreaTrabajo(datosAreaTrabajo);
		return result;
	}

	// SP_LEE_INF_AREA_TRABAJO_VP
	@Override
	public EstatusAreaTrabajoTO leeInfAreaTrabajoVP(DatosAutenticacionTO session, NodoTO nodo,
			List<DatosAreaTrabajoTO> datosAreaTrabajo, String cveAreaTrabajo, Integer idNodo, String cveAreaTrabajoTarjeta) throws BrioBPMException {
		
		//log.info("\t-----DATOS leeInfAreaTrabajoVP {}{}{}{}", session, nodo, cveAreaTrabajo, idNodo);
		
		String cveEntidad = session.getCveEntidad();
		String cveNodo = nodo.getCveNodo();
		String cveProceso = nodo.getCveProceso();
		BigDecimal version = nodo.getVersion();
		Integer decimales;
		String estadoActividad;
		Integer numColumnas;
		String origenDatoVP;
		Integer ordenDato;
		String tipoDato;
		String tipoTarjeta;
		String valorBaseDatos;
		String valorPantalla;
		String vpAgregacion;
		String vpCveVariable;
		BigDecimal vpValorDecimal;
		Date vpValorFecha;
		Integer vpValorEntero;
		String vpValorAlfanumerico;
		String cveDatoMoneda = null;
		String cveInstancia;
		String cveMoneda = null;
		Integer secuenciaNodo;
		String cveDatoProcesoNodo = "";
		
		// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		EstatusAreaTrabajoTO result =EstatusAreaTrabajoTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();
		origenDatoVP = Constants.VARIABLE_PROCESO;
		
		// CURSOR DE DATOS PARA EL ÁREA DE TRABAJO SOLICITADA
		List<DatoAreaTrabajoTO> datosATVP = new ArrayList<>();
		
		log.info("cveEntidad: {} | cveProceso: {} | version: {} | cveAreaTrabajo: {} | origenDatoVP: {} | cveNodo: {} | idNodo: {} | cveAreaTrabajoTarjeta: {} | cveUsuario: {}",
				cveEntidad, cveProceso, version, cveAreaTrabajo, origenDatoVP, cveNodo, idNodo, cveAreaTrabajoTarjeta,
				session.getCveUsuario());
		
		List<Object[]> listaDAT = new ArrayList<>();
		//SVM se ajustan llaves y manejo se seguridad por instancia
		listaDAT = iDatoAreaTrabajoRepository.obtenerDatosATProceso(
				cveEntidad, cveProceso, version, cveAreaTrabajo, 
				origenDatoVP, cveNodo, idNodo, cveAreaTrabajoTarjeta, session.getCveUsuario());
		
		
//			listaDAT.forEach(item -> {
//				Object[] row = (Object[]) item;
//				var itemSelected = DatoAreaTrabajoTO.builder()
//						.ordenDato(((BigDecimal) Arrays.asList(row).get(MagicNumber.ZERO.getValue())).intValue())
//						.cveVariable((String) Arrays.asList(row).get(MagicNumber.ONE.getValue()))
//						.agregacion((String) Arrays.asList(row).get(MagicNumber.TWO.getValue()))
//						.cveInstancia((String) Arrays.asList(row).get(MagicNumber.THREE.getValue()))
//						.secuenciaNodo(((BigDecimal) Arrays.asList(row).get(MagicNumber.FOUR.getValue())).intValue())
//						.estado((String) Arrays.asList(row).get(MagicNumber.FIVE.getValue()))
//						.tipoTarjeta((String) Arrays.asList(row).get(MagicNumber.SIX.getValue()))
//						//agregar tipo
//						.build();
//				datosATVP.add(itemSelected);
//				}
//			);
		// CARGA EN TABLA TEMPORAL LOS DATOS DEL PROCESO
		numColumnas = 0;
		
		//log.info("------->listaDAT: " + listaDAT.size());
		
		for (Object[] dato : listaDAT) {
		        
				BigDecimal orden = (dato[0] != null) ? (BigDecimal) dato[0] : null;
			    ordenDato = orden != null ? orden.intValue() : null;
			    vpCveVariable = (dato[1] != null) ? dato[1].toString() : null;
			    vpAgregacion = (dato[2] != null) ? dato[2].toString() : null;
			    cveInstancia = (dato[3] != null) ? dato[3].toString() : null;
			    BigDecimal secuencia = (dato[4] != null) ? (BigDecimal) dato[4] : null;
			    secuenciaNodo = secuencia != null ? secuencia.intValue() : null;
			    estadoActividad = (dato[5] != null) ? dato[5].toString() : null;
			    tipoTarjeta = (dato[6] != null) ? dato[6].toString() : null;
			    
			    log.info("-------> tipoTarjeta: " + tipoTarjeta);

		        //if(vpCveVariable != null) {
		    
				NodoTO nodoVPRO = NodoTO.builder()
						.cveProceso(cveProceso)
						.version(version)
						.cveInstancia(cveInstancia)
						.cveVariable(vpCveVariable)
						.ocurrencia(1)
						.build();
				
				// 
				EstatusVariablesTO leeVVPRO = nodoHelper.leerValorVpro(session, nodoVPRO, vpAgregacion, null); // revisar ENTRADA
				//log.debug("------->leeVVPRO: " + leeVVPRO.toString());
				//log.debug("------->Sale de la funcion leerValorVpro");
				tipoDato = leeVVPRO.getTipo();
				//log.info("Tipo Dato EVL: {} ", tipoDato);
				vpValorAlfanumerico = leeVVPRO.getValorAlfanumerico();
				vpValorEntero = leeVVPRO.getValorEntero();
				vpValorDecimal = leeVVPRO.getValorDecimal();
				vpValorFecha = leeVVPRO.getValorFecha();
				decimales = leeVVPRO.getDecimales(); // revisar tipo de dato
				
				if(leeVVPRO.getTipoExcepcion().equals(Constants.ERROR)) {
					return result;
				}
				result.setTipoExcepcion(leeVVPRO.getTipoExcepcion());
				result.setMensaje(leeVVPRO.getMensaje());
				
				numColumnas = numColumnas + 1;
				valorBaseDatos = " ";
				
				//log.debug("------->tipoDato areaTrabajo: " + tipoDato);
				if (Constants.ALFANUMERICO.equals(tipoDato)) {
				    valorBaseDatos = vpValorAlfanumerico;
				} else if (Constants.DECIMAL.equals(tipoDato) || Constants.MONEDA.equals(tipoDato)) {
				    valorBaseDatos = vpValorDecimal != null ? vpValorDecimal.toString() : "";
				} else if (Constants.ENTERO.equals(tipoDato)) {
				    valorBaseDatos = vpValorEntero != null ? vpValorEntero.toString() : "";
				} else if (Constants.FECHA.equals(tipoDato)) {
				    valorBaseDatos = nodoHelper.formatFecha(vpValorFecha, "yyyy-MM-dd");
				    //log.debug("------->Sale de la funcion formatFecha");
				}

				valorPantalla = "";
				if (Constants.ALFANUMERICO.equals(tipoDato)) {
				    valorPantalla = vpValorAlfanumerico;
				} else if (Constants.DECIMAL.equals(tipoDato) || Constants.MONEDA.equals(tipoDato)) {
				    valorPantalla = nodoHelper.truncarDecimales(vpValorDecimal, decimales);
				} else if (Constants.ENTERO.equals(tipoDato)) {
				    valorPantalla = vpValorEntero != null ? vpValorEntero.toString() : "";
				} else if (Constants.FECHA.equals(tipoDato)) {
				    valorPantalla = nodoHelper.formatFecha(vpValorFecha, "yyyy-MM-dd");
				    //log.info("------->Sale de la funcion formatFecha");
				}
				
				// SENTENCIA INSERT PARA LA TABLA TEMPORAL QUE CONTIENE LOS VALORES DE LOS DATOS DEL
				// AREA DE TRABAJO
				DatosAreaTrabajoTO dAT = DatosAreaTrabajoTO.builder()
						.cveInstancia(cveInstancia)
						.secuenciaNodo(secuenciaNodo)
						.secuenciaDato(numColumnas)
						.ordenDato(ordenDato)
						.cveDato(vpCveVariable)
						.situacion(estadoActividad)
						.valorBaseDatos(valorBaseDatos)
						.valorPantalla(valorPantalla)
						.tipoTarjeta(tipoTarjeta)
						.build();
				
				// SI SE TRATA DE UN TIPO DE DATO "MONEDA", SE AGREGA LA PSEUDOVARIABLE PARA LA CLAVE DE MONEDA
				if(tipoDato.equals(Constants.MONEDA)) {
					//SP_LEE_ATRIBUTOS_MONEDA
					EstatusAtributosMonedaTO leeAM = leeAtributosMoneda(session, nodo, vpCveVariable, origenDatoVP);
					//log.debug("------->Sale de la funcion leeAtributosMoneda");
					cveDatoMoneda = leeAM.getCveDatoMoneda();
					cveMoneda = leeAM.getCveMoneda();
					
					valorBaseDatos = "";
					valorBaseDatos = cveMoneda;
					valorPantalla = "";
					valorPantalla = cveMoneda;
					
					numColumnas = numColumnas + 1;
					
					//DatosAreaTrabajoTO dAT1 = DatosAreaTrabajoTO.builder()
					dAT = DatosAreaTrabajoTO.builder()
							.cveInstancia(cveInstancia)
							.secuenciaNodo(secuenciaNodo)
							.secuenciaDato(numColumnas)
							.ordenDato(ordenDato)
							.cveDato(cveDatoMoneda)
							.situacion(estadoActividad)
							.valorBaseDatos(valorBaseDatos)
							.valorPantalla(valorPantalla)
							.tipoTarjeta(tipoTarjeta)
							.cveDatoProcesoNodo(cveDatoProcesoNodo)
							.build();
					//log.debug("------->construye registro dAT1");
					//datosAreaTrabajo.add(dAT1);
				}
				
				//log.debug("------->construye registro dAT");
				
				if (!datosAreaTrabajo.contains(dAT)) {
					datosAreaTrabajo.add(dAT);
				}
		}
			
		//}
		log.debug("------->retorna valores leeInfAreaTrabajoVP");
		result.setDatosAreaTrabajo(datosAreaTrabajo);
		return result;
	}
	// SP_LEE_INF_AREA_TRABAJO_VL
	@Override
	public EstatusAreaTrabajoTO leeInfAreaTrabajoVL(DatosAutenticacionTO session, NodoTO nodo,
			List<DatosAreaTrabajoTO> datosAreaTrabajo, String cveAreaTrabajo, Integer idNodo) throws BrioBPMException {
		
		String cveEntidad = session.getCveEntidad();
		String cveNodo = nodo.getCveNodo();
		String cveProceso = nodo.getCveProceso();
		BigDecimal version = nodo.getVersion();
		String cveDatoMoneda;
		String cveInstancia;
		Integer numColumnas;
		Integer ordenDato;
		String estadoActividad;
		String origenDatoVL;
		Integer secuenciaNodo;
		String sufijoMoneda;
		String tipoDato;
		String valorBaseDatos;
		String valorPantalla;
		String vlCveVariable;
		BigDecimal vlValorDecimal;
		Integer vlValorEntero;
		Date vlValorFecha;
		String vlValorAlfanumerico;
		
		// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		EstatusAreaTrabajoTO result =EstatusAreaTrabajoTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();
		origenDatoVL = Constants.VARIABLE_LOCALIDAD;
		sufijoMoneda = Constants.MON;
		
		// CURSOR DE DATOS PARA EL ÁREA DE TRABAJO SOLICITADA
		List<DatoAreaTrabajoTO> datosATVL = new ArrayList<>();
		log.info("cveEntidad: {} | cveProceso: {} | version: {} | cveAreaTrabajo: {} | origenDatoVL: {} | cveNodo: {} | idNodo: {} | cveUsuario: {}",
				cveEntidad, cveProceso, version, cveAreaTrabajo, origenDatoVL, cveNodo, idNodo,
				session.getCveUsuario());	
		//SVM se ajustan llaves y manejo se seguridad por instancia
		List<Object> listaDAT = iDatoAreaTrabajoRepository.obtenerDatosASLocalidad(
				cveEntidad, cveProceso, version,
				cveAreaTrabajo, origenDatoVL, cveNodo, idNodo, session.getCveUsuario());
		
		if(!listaDAT.isEmpty()) {
			listaDAT.forEach(item -> {
				Object[] row = (Object[]) item;
				var itemSelected = DatoAreaTrabajoTO.builder()
						.secuenciaDato((Integer) Arrays.asList(row).get(MagicNumber.ZERO.getValue()))
						.ordenDato((Integer) Arrays.asList(row).get(MagicNumber.ONE.getValue()))
						.cveVariable((String) Arrays.asList(row).get(MagicNumber.TWO.getValue()))
						.cveInstancia((String) Arrays.asList(row).get(MagicNumber.THREE.getValue()))
						.secuenciaNodo((Integer) Arrays.asList(row).get(MagicNumber.FOUR.getValue()))
						.estado((String) Arrays.asList(row).get(MagicNumber.FIVE.getValue()))
						.build();
				datosATVL.add(itemSelected);
				}
			);
		}
		// CARGA EN TABLA TEMPORAL LOS DATOS DEL PROCESO
		numColumnas = 0;
		for(DatoAreaTrabajoTO dato : datosATVL) {
			ordenDato = dato.getOrdenDato();
			vlCveVariable = dato.getCveVariable();
			cveInstancia = dato.getCveInstancia();
			secuenciaNodo = dato.getSecuenciaNodo();
			estadoActividad = dato.getEstado();
			
			// LEE VALOR DE LA VARIABLE
			EstatusVariablesTO leeVVLOC = nodoHelper.leerValorVeloc(
					session, cveProceso, version, vlCveVariable);
			
			result.setMensaje(leeVVLOC.getMensaje());
			result.setTipoExcepcion(leeVVLOC.getTipoExcepcion());
			if(leeVVLOC.getTipoExcepcion().equals(Constants.ERROR)) {
				result.setTipoExcepcion(Constants.ERROR);
				return result;
			}
			tipoDato = leeVVLOC.getTipoDato();
			vlValorAlfanumerico = leeVVLOC.getValorAlfanumerico();
			vlValorEntero = leeVVLOC.getValorEntero();
			vlValorDecimal = leeVVLOC.getValorDecimal();
			vlValorFecha = leeVVLOC.getValorFecha();
			
			valorBaseDatos = "";
			if(tipoDato.equals(Constants.ALFANUMERICO)) {
				valorBaseDatos = vlValorAlfanumerico;
			} else if(tipoDato.equals(Constants.DECIMAL) || tipoDato.equals(Constants.MONEDA)) {
				valorBaseDatos = vlValorDecimal.toString();
			} else if(tipoDato.equals(Constants.ENTERO)) {
				valorBaseDatos = vlValorEntero.toString();
			} else if(tipoDato.equals(Constants.FECHA)) {
				valorBaseDatos = nodoHelper.formatFecha(vlValorFecha, "yyyy-MM-dd"); 
			}
			valorPantalla = "";
			if(tipoDato.equals(Constants.ALFANUMERICO)) {
				valorPantalla = vlValorAlfanumerico;
			} else if(tipoDato.equals(Constants.DECIMAL) || tipoDato.equals(Constants.MONEDA)) {
				valorPantalla = ""; // falta funcion decimales
			} else if(tipoDato.equals(Constants.ENTERO)) {
				valorPantalla = vlValorEntero.toString();
			} else if(tipoDato.equals(Constants.FECHA)) {
				valorPantalla = nodoHelper.formatFecha(vlValorFecha, "yyyy-MM-dd");
			}
			
		// SENTENCIA INSERT PARA LA TABLA TEMPORAL QUE CONTIENE LOS VALORES DE LOS DATOS DEL
		// AREA DE TRABAJO
			numColumnas = numColumnas + 1;
			DatosAreaTrabajoTO dto = DatosAreaTrabajoTO.builder()
					.cveInstancia(cveInstancia)
					.secuenciaNodo(secuenciaNodo)
					.secuenciaDato(numColumnas)
					.ordenDato(ordenDato)
					.cveDato(vlCveVariable)
					.situacion(estadoActividad)
					.valorBaseDatos(valorBaseDatos)
					.valorPantalla(valorPantalla)
					.build();
			//datosAreaTrabajo.add(dto);
			
			// SE AGREGA LA PSEUDOVARIABLE PARA LA MONEDA
			if (tipoDato.equals(Constants.MONEDA)) {
				cveDatoMoneda = vlCveVariable + sufijoMoneda;
				valorBaseDatos = "";
				valorBaseDatos = vlValorAlfanumerico;
				valorPantalla = "";
				valorPantalla = vlValorAlfanumerico;
				
				numColumnas = numColumnas + 1;
				dto.setCveInstancia(cveInstancia);
				dto.setSecuenciaNodo(secuenciaNodo);
				dto.setSecuenciaDato(numColumnas);
				dto.setCveDato(cveDatoMoneda);
				dto.setSituacion(estadoActividad);
				dto.setValorBaseDatos(valorBaseDatos);
				dto.setValorPantalla(valorPantalla);
				//datosAreaTrabajo.add(dto);
			}
			
			if (!datosAreaTrabajo.contains(dto)) {
				datosAreaTrabajo.add(dto);
			}
		}
		result.setDatosAreaTrabajo(datosAreaTrabajo);
		return result;
	}

	// SP_LEE_INF_AREA_TRABAJO_VE
	@Override
	public EstatusAreaTrabajoTO leeInfAreaTrabajoVE(DatosAutenticacionTO session, NodoTO nodo,
			List<DatosAreaTrabajoTO> datosAreaTrabajo, String cveAreaTrabajo, Integer idNodo) throws BrioBPMException {

		String cveEntidad = session.getCveEntidad();
		String cveNodo = nodo.getCveNodo();
		String cveProceso = nodo.getCveProceso();
		BigDecimal version = nodo.getVersion();
		String cveDatoMoneda;
		String cveInstancia;
		Integer numColumnas;
		Integer ordenDato;
		String estadoActividad;
		String origenDatoVE;
		Integer secuenciaNodo;
		String sufijoMoneda;
		String tipoDato;
		String valorBaseDatos;
		String valorPantalla;
		String veCveVariable;
		BigDecimal veValorDecimal;
		Integer veValorEntero;
		Date veValorFecha;
		String veValorAlfanumerico;
		
		// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		EstatusAreaTrabajoTO result =EstatusAreaTrabajoTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();
		origenDatoVE = Constants.VARIABLE_ENTIDAD;
		sufijoMoneda = Constants.MON;
		
		// CURSOR DE DATOS PARA EL ÁREA DE TRABAJO SOLICITADA
		List<DatoAreaTrabajoTO> datosATVE = new ArrayList<>();
		
		//SVM se ajustan llaves y manejo se seguridad por instancia
		List<Object> listaDAT = iDatoAreaTrabajoRepository.obtenerDatosASEntidad(
				cveEntidad, cveProceso, version,
				cveAreaTrabajo, origenDatoVE, cveNodo, idNodo, session.getCveUsuario());
		
		if(!listaDAT.isEmpty()) {
			listaDAT.forEach(item -> {
				Object[] row = (Object[]) item;
				var itemSelected = DatoAreaTrabajoTO.builder()
						.ordenDato((Integer) Arrays.asList(row).get(MagicNumber.ZERO.getValue()))
						.cveVariable((String) Arrays.asList(row).get(MagicNumber.ONE.getValue()))
						.cveInstancia((String) Arrays.asList(row).get(MagicNumber.TWO.getValue()))
						.secuenciaNodo((Integer) Arrays.asList(row).get(MagicNumber.THREE.getValue()))
						.estado((String) Arrays.asList(row).get(MagicNumber.FOUR.getValue()))
						.build();
				datosATVE.add(itemSelected);
				}
			);
		}
		// CARGA EN TABLA TEMPORAL LOS DATOS DEL PROCESO
		numColumnas = 0;
		for(DatoAreaTrabajoTO dato : datosATVE) {
			ordenDato = dato.getOrdenDato();
			veCveVariable = dato.getCveVariable();
			cveInstancia = dato.getCveInstancia();
			secuenciaNodo = dato.getSecuenciaNodo();
			estadoActividad = dato.getEstado();
			
			// LEE VALOR DE LA VARIABLE
			EstatusVariablesTO leeVVENT = nodoHelper.leerValorVent(session, cveProceso, version, veCveVariable); // revisar ROL
			result.setMensaje(leeVVENT.getMensaje());
			result.setTipoExcepcion(leeVVENT.getTipoExcepcion());
			if(leeVVENT.getTipoExcepcion().equals(Constants.ERROR)) {
				result.setTipoExcepcion(Constants.ERROR);
				return result;
			}
			tipoDato = leeVVENT.getTipoDato();
			veValorAlfanumerico = leeVVENT.getValorAlfanumerico();
			veValorEntero = leeVVENT.getValorEntero();
			veValorDecimal = leeVVENT.getValorDecimal();
			veValorFecha = leeVVENT.getValorFecha();
			
			valorBaseDatos = "";
			valorBaseDatos = "";
			if(tipoDato.equals(Constants.ALFANUMERICO)) {
				valorBaseDatos = veValorAlfanumerico;
			} else if(tipoDato.equals(Constants.DECIMAL) || tipoDato.equals(Constants.MONEDA)) {
				valorBaseDatos = veValorDecimal.toString();
			} else if(tipoDato.equals(Constants.ENTERO)) {
				valorBaseDatos = veValorEntero.toString();
			} else if(tipoDato.equals(Constants.FECHA)) {
				valorBaseDatos = nodoHelper.formatFecha(veValorFecha, "yyyy-MM-dd"); 
			}
			valorPantalla = "";
			if(tipoDato.equals(Constants.ALFANUMERICO)) {
				valorPantalla = veValorAlfanumerico;
			} else if(tipoDato.equals(Constants.DECIMAL) || tipoDato.equals(Constants.MONEDA)) {
				valorPantalla = ""; // falta funcion decimales
			} else if(tipoDato.equals(Constants.ENTERO)) {
				valorPantalla = veValorEntero.toString();
			} else if(tipoDato.equals(Constants.FECHA)) {
				valorPantalla = nodoHelper.formatFecha(veValorFecha, "yyyy-MM-dd");
			}
			
		// SENTENCIA INSERT PARA LA TABLA TEMPORAL QUE CONTIENE LOS VALORES DE LOS DATOS DEL
		// AREA DE TRABAJO
			numColumnas = numColumnas + 1;
			DatosAreaTrabajoTO dto = DatosAreaTrabajoTO.builder()
					.cveInstancia(cveInstancia)
					.secuenciaNodo(secuenciaNodo)
					.secuenciaDato(numColumnas)
					.ordenDato(ordenDato)
					.cveDato(veCveVariable)
					.situacion(estadoActividad)
					.valorBaseDatos(valorBaseDatos)
					.valorPantalla(valorPantalla)
					.build();
			//datosAreaTrabajo.add(dto);
			
			// SE AGREGA LA PSEUDOVARIABLE PARA LA MONEDA
			if (tipoDato.equals(Constants.MONEDA)) {
				cveDatoMoneda = veCveVariable + sufijoMoneda;
				valorBaseDatos = "";
				valorBaseDatos = veValorAlfanumerico;
				valorPantalla = "";
				valorPantalla = veValorAlfanumerico;
				
				numColumnas = numColumnas + 1;
				dto.setCveInstancia(cveInstancia);
				dto.setSecuenciaNodo(secuenciaNodo);
				dto.setSecuenciaDato(numColumnas);
				dto.setCveDato(cveDatoMoneda);
				dto.setSituacion(estadoActividad);
				dto.setValorBaseDatos(valorBaseDatos);
				dto.setValorPantalla(valorPantalla);
				//datosAreaTrabajo.add(dto);
			}
			
			if (!datosAreaTrabajo.contains(dto)) {
				datosAreaTrabajo.add(dto);
			}
		}
		result.setDatosAreaTrabajo(datosAreaTrabajo);
		return result;
	}

	// FN_LEE_ESTILO_NIVEL_SERVICIO
	@Override
	public String leeEstiloNivelServicio(Date fechaCreacionActividad, Date fechaLimite,
			String estadoActividad, Date fechaEstadoActual) throws BrioBPMException {
			
			String estilo = Constants.GDCELLGREEN; // revisar null
			Date fechaActual;
			Integer segundosPlazo;
			Integer segundosMitad;
			Integer segundosRestantes;
			
			fechaActual = new Date();
	        
			if(fechaLimite != null) {
				 // Convertir las fechas a instancias de Instant
				Instant instantFechaActual= fechaEstadoActual.toInstant();
		        Instant instantFechaCreacion = fechaCreacionActividad.toInstant();
		        Instant instantFechaLimite = fechaLimite.toInstant();
		        
				if(estadoActividad.equals(Constants.REGISTRO) || estadoActividad.equals(Constants.VENCIDA_POR_TIEMPO) ) {
					if(fechaActual.before(fechaLimite)) {
						
				        // Calcular la diferencia en segundos
						segundosPlazo = (int) Duration.between(instantFechaCreacion, instantFechaLimite).getSeconds();
						segundosMitad = segundosPlazo / 2;
						segundosRestantes = (int) Duration.between(instantFechaActual, instantFechaLimite).getSeconds();
						if(segundosRestantes < segundosMitad) {
							estilo = Constants.GDCELLAMBER;
						}
					} else {
						estilo = Constants.GDCELLRED;
					}
				} else {
					if(estadoActividad.equals(Constants.TERMINADA)) {
						if((fechaEstadoActual.before(fechaLimite) || (fechaEstadoActual.equals(fechaLimite)))) {
							estilo = Constants.GDCELLGREEN;
						} else {
							estilo = Constants.GDCELLORANGE;
						}
					}
				}
			}
		//log.info("----> ESTILO: " + estilo);
		return estilo;
	}

	// SP_LEE_ATRIBUTOS_DATO
	@Override
	public EstatusAtributosDatoTO leeAtributosDato(DatosAutenticacionTO session, NodoTO nodo, String cveDato,
			String origenDato) throws BrioBPMException {
		
		//log.info("---------> entra  leeAtributosDato <----------");
		//log.info("\t-----DATOS {}{}{}{}", session, nodo, cveDato, origenDato);
		//log.info("------cveDato : " + cveDato);
		//log.info("------origenDato : " + origenDato);
		
		String cveEntidad = session.getCveEntidad();
		String cveLocalidad = session.getCveLocalidad();
		String cveProceso = nodo.getCveProceso();
		BigDecimal version = nodo.getVersion();
		String atributosEncontrados;
		String idProceso;
		String variableValor;
		String mensaje;
		
		String tipoDato = null;
		Integer longitud = null;
		Integer enteros = null;
		Integer decimales = null;
		String formatoFecha = null;
		
		// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		EstatusAtributosDatoTO result =EstatusAtributosDatoTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();
		idProceso = Constants.LEE_ATRIBUTOS_DATO;
		
		variableValor = Constants.CVE_ENTIDAD + cveEntidad + "|" +
				Constants.CVE_LOCALIDAD + cveLocalidad + "|" +
				Constants.CVE_PROCESO + cveProceso + "|" +
				Constants.VERSION + version + "|" + 
				Constants.ORIGEN_DATO + origenDato + "|" +
				Constants.CVE_DATO + cveDato;
		
		// VALIDA QUE EL ORIGEN DEL DATO SEA CORRECTO
		if(!origenDato.equals(Constants.DATO_PROCESO_NODO) &&
				   !origenDato.equals(Constants.VARIABLE_PROCESO) &&
				   !origenDato.equals(Constants.VARIABLE_SISTEMA) &&
				   !origenDato.equals(Constants.VARIABLE_ENTIDAD) &&
				   !origenDato.equals(Constants.VARIABLE_LOCALIDAD)) {
			mensaje = messagesService.getMessage(
					session,
					idProceso,
					"ORIGEN_DATO_INVALIDO",
					variableValor);
			result.setTipoExcepcion(Constants.ERROR);
			result.setMensaje(mensaje);
			return result;
		}		
		
		// DE ACUERDO AL ORIGEN DEL DATO, SE OBTIENEN LOS ATRIBUTOS DEL DATO
		atributosEncontrados = Constants.NO;
		//log.info("origenDato en leeAtributosDato: " + origenDato);
		switch (origenDato) {
		    case Constants.DATO_PROCESO_NODO:
		    	//log.info("DATO_PROCESO_NODO ** ");
		        Optional<DatoProcesoNodo> datoPN = iDatoProcesoNodoRepository.findById(cveDato);
				
		        // EVALUA SI LOS ATRIBUTOS DEL DATO HAN SIDO ENCONTRADOS
		        if(datoPN.isPresent()) {
		        	atributosEncontrados = Constants.SI;
		        	tipoDato = datoPN.get().getTipo();
		        	longitud = datoPN.get().getLongitud();
		        	enteros = datoPN.get().getEnteros();
		        	decimales = datoPN.get().getDecimales();
		        	formatoFecha = datoPN.get().getFormatoFecha();
		        }
		        break;
		    case Constants.VARIABLE_PROCESO:
		    	//log.info("VARIABLE_PROCESO ** ");
		        Optional<StVariableProceso> vP = iStVariableProcesoRepository.obtenerStVariableP(
		        		cveProceso, version, cveDato);
		        //log.info("-----------buscar variable proceso");
		        
		        // EVALUA SI LOS ATRIBUTOS DEL DATO HAN SIDO ENCONTRADOS
		        if(vP.isPresent()) {
		        	//log.info("VARIABLE_PROCESO presente** ");
		        	atributosEncontrados = Constants.SI;
		        	tipoDato = vP.get().getTipo();
		        	longitud = vP.get().getLongitud();
		        	enteros = vP.get().getEnteros();
		        	decimales = vP.get().getDecimales();
		        	formatoFecha = vP.get().getFormatoFecha();
		        	
					//log.info("tipoDato: {}, longitud: {}, enteros: {}, decimales: {}, formatoFecha: {}", tipoDato,
					//		longitud, enteros, decimales, formatoFecha);;
		        }
		        break;
		    case Constants.VARIABLE_ENTIDAD:
		    	//log.info("VARIABLE_ENTIDAD ** ");
		    	VariableEntidadPK idVE = VariableEntidadPK.builder()
		    							.cveEntidad(cveEntidad)
		    							.cveVariable(cveDato)
		    							.build();
		    	//log.info("-----------buscar variable entidad");
		        Optional<VariableEntidad> vE = iVariableEntidadRepository.findById(idVE);
		        // EVALUA SI LOS ATRIBUTOS DEL DATO HAN SIDO ENCONTRADOS
		        if(vE.isPresent()) {
		        	//log.info("VARIABLE_ENTIDAD presente** ");
		        	atributosEncontrados = Constants.SI;
		        	tipoDato = vE.get().getTipo();
		        	longitud = vE.get().getLongitud();
		        	enteros = vE.get().getEnteros();
		        	decimales = vE.get().getDecimales();
		        	formatoFecha = vE.get().getFormatoFecha();
		        }
		        break;
		    case Constants.VARIABLE_LOCALIDAD:
		    	VariableLocalidadPK idVL = VariableLocalidadPK.builder()
									    	.cveEntidad(cveEntidad)
									    	.cveLocalidad(cveLocalidad)
									    	.cveVariable(cveDato)
									    	.build();
		        Optional<VariableLocalidad> vL = iVariableLocalidadRepository.findById(idVL);
		        // EVALUA SI LOS ATRIBUTOS DEL DATO HAN SIDO ENCONTRADOS
		        if(vL.isPresent()) {
		        	atributosEncontrados = Constants.SI;
		        	tipoDato = vL.get().getTipo();
		        	longitud = vL.get().getLongitud();
		        	enteros = vL.get().getEnteros();
		        	decimales = vL.get().getDecimales();
		        	formatoFecha = vL.get().getFormatoFecha();
		        }
		        break;
		    case Constants.VARIABLE_SISTEMA:
		        Optional<VariableSistema> vS = iVariableSistemaRepository.findById(cveDato);
		        // EVALUA SI LOS ATRIBUTOS DEL DATO HAN SIDO ENCONTRADOS
		        if(vS.isPresent()) {
		        	atributosEncontrados = Constants.SI;
		        	tipoDato = vS.get().getTipo();
		        	longitud = vS.get().getLongitud();
		        	enteros = vS.get().getEnteros();
		        	decimales = vS.get().getDecimales();
		        	formatoFecha = vS.get().getFormatoFecha();
		        }
		        break;
		    default:
		    	// EVALUA SI LOS ATRIBUTOS DEL DATO HAN SIDO ENCONTRADOS
	        	mensaje = messagesService.getMessage(
						session,
						idProceso,
						"ATRIBUTOS_NO_ENCONTRADOS",
						variableValor);
				result.setTipoExcepcion(Constants.ERROR);
				result.setMensaje(mensaje);
				return result;
		}
		
		if(atributosEncontrados.equals(Constants.NO)) {
			mensaje = messagesService.getMessage(
					session,
					idProceso,
					"ATRIBUTOS_NO_ENCONTRADOS",
					variableValor);
			result.setTipoExcepcion(Constants.ERROR);
			result.setMensaje(mensaje);
			return result;
		}
		result.setTipoDato(tipoDato);
		result.setLongitud(longitud);
		result.setEnteros(enteros);
		result.setDecimales(decimales);
		result.setFormatoFecha(formatoFecha);
		//log.info("---------> termina  leeAtributosDato <----------");
		return result;
	}

	// SP_LEE_ATRIBUTOS_MONEDA
	@Override
	public EstatusAtributosMonedaTO leeAtributosMoneda(DatosAutenticacionTO session,  NodoTO nodo, String cveDato,
			String origenDato) throws BrioBPMException {
    	//log.info("<<<<<<<<<<<<--------------------------->>>>>>>>>>>>");
		//log.info("---------> ENTRA A  leeAtributosMoneda <----------");
		String cveEntidad = session.getCveEntidad();
		String cveLocalidad = session.getCveLocalidad();
		String cveProceso = nodo.getCveProceso();
		BigDecimal version = nodo.getVersion();
		String atributosEncontrados;
		Integer longitudEstaticaMoneda;
		String origenMoneda;
		String origenMonedaEntidad;
		String origenMonedaLocalidad;
		String origenMonedaSolicitar;
		String tipoControlTextbox;
		String tipoDatoAlfanumerico;
		String tipoCveMoneda;
		String idProceso;
		String mensaje;
		String sufijoMoneda;
		String variableValor;
		
		String cveDatoMoneda = null;
		String cveMoneda = null;
		String tipoControlMoneda = null;
		Integer longitudMoneda = null;
		String etiquetaMoneda = null;
		
		// INICIALIZACIÓN DE VARIABLES
		origenMonedaEntidad = Constants.ENTIDAD;
		origenMonedaLocalidad = Constants.LOCALIDAD;
		origenMonedaSolicitar = Constants.SOLICITAR;
		
		sufijoMoneda = Constants.MON;
		tipoDatoAlfanumerico = Constants.ALFANUMERICO;
		tipoControlTextbox = Constants.TEXTBOX;
		
		longitudEstaticaMoneda = 3;
	
		// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		EstatusAtributosMonedaTO result =EstatusAtributosMonedaTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();
		idProceso = Constants.LEE_ATRIBUTOS_MONEDA;
		
		variableValor = Constants.CVE_ENTIDAD + cveEntidad + "|" +
				Constants.CVE_LOCALIDAD + cveLocalidad + "|" +
				Constants.CVE_PROCESO + cveProceso + "|" +
				Constants.VERSION + version + "|" + 
				Constants.ORIGEN_DATO + origenDato + "|" +
				Constants.CVE_DATO + cveDato;
		//log.info("------------> ORIGEN DATO: " + origenDato);
		//log.info("------------> CLAVE DATO: " + cveDato);
		//log.info("------------> NODO: " + nodo.toString());
		
		// VALIDA QUE EL ORIGEN DEL DATO SEA CORRECTO
		if (!Arrays.asList(Constants.VARIABLE_PROCESO, Constants.VARIABLE_ENTIDAD,
				Constants.VARIABLE_SISTEMA, Constants.VARIABLE_LOCALIDAD).contains(origenDato)) {
			//log.info("XXXXXXXXXX- ORIGEN_DATO_INVALIDO -XXXXXXXXXX");
			mensaje = messagesService.getMessage(
					session,
					idProceso,
					"ORIGEN_DATO_INVALIDO",
					variableValor);
			result.setTipoExcepcion(Constants.ERROR);
			result.setMensaje(mensaje);
			return result;
		}
				
		// ESTABLECE ATRIBUTOS ESTÁTICOS
		cveDatoMoneda = cveDato + sufijoMoneda;
		tipoCveMoneda = tipoDatoAlfanumerico;
		tipoControlMoneda = tipoControlTextbox;
		longitudMoneda = longitudEstaticaMoneda;
		
		// DE ACUERDO AL ORIGEN DEL DATO, SE OBTIENEN LOS ATRIBUTOS DE LA VARIABLE
		atributosEncontrados = Constants.NO;
		origenMoneda = null;
		
		//log.info("------------> SWITCH: " + origenDato);
		switch (origenDato) {
		case Constants.VARIABLE_PROCESO:
			//log.info("------------> ETIQUETA VARIABLE_PROCESO: ");
			//log.info("----------> IDVP : " + cveEntidad + " " + cveProceso + " " + version + " " + cveDato);
			StVariableProcesoPK idVP = StVariableProcesoPK.builder()
										.cveEntidad(cveEntidad)
										.cveProceso(cveProceso)
										.version(version)
										.cveVariable(cveDato)
										.build();
	        Optional<StVariableProceso> vP = iStVariableProcesoRepository.findById(idVP);
	        if(vP.isPresent()) {
	        	origenMoneda = vP.get().getOrigenMoneda();
	        	etiquetaMoneda = vP.get().getEtiquetaMoneda();
	        	cveMoneda = vP.get().getCveMoneda();
	        	//log.info("---------> ETIQUETA MONEDA VP: " + etiquetaMoneda);
	        	//log.info("---------> ORIGEN MONEDA: " + origenMoneda);
	        	//log.info("---------> CLAVE MONEDA: " + cveMoneda);
	        	if(origenMoneda == null || cveMoneda != null) {
	        		atributosEncontrados = Constants.SI;
	        	} else if (origenMoneda.equals(origenMonedaEntidad)) {
	        		Optional<Entidad> ent = entidadRepository.findById(cveEntidad);
	        		if(ent.isPresent()) {
	    	        	cveMoneda = ent.get().getMoneda().getCveMoneda();
	    	        	//log.info("------> cveMoned DE ENTIDAD: " + cveMoneda);
	        			atributosEncontrados = Constants.SI;
	        		}
		        	
	        	} else if (origenMoneda.equals(origenMonedaLocalidad)) {
	        		LocalidadEntidadPK id = LocalidadEntidadPK.builder()
					        				.cveEntidad(cveEntidad)
					        				.cveLocalidad(cveLocalidad)
					        				.build();
	        		Optional<LocalidadEntidad> ent = localidadEntidadRepository.findById(id);
	        		if(ent.isPresent()) {
	        			cveMoneda = ent.get().getMoneda().getCveMoneda();
	        			atributosEncontrados = Constants.SI;
	        		}
	        	} else if (origenMoneda.equals(origenMonedaSolicitar)) {
		        	atributosEncontrados = Constants.SI;
	        	}
	        }
	        break;
		case Constants.VARIABLE_ENTIDAD:
			//log.info("------------> ETIQUETA VARIABLE_ENTIDAD: ");
			VariableEntidadPK idVE = VariableEntidadPK.builder()
										.cveEntidad(cveEntidad)
										.cveVariable(cveDato)
										.build();
	        Optional<VariableEntidad> vE = iVariableEntidadRepository.findById(idVE);
	        if(vE.isPresent()) {
	        	etiquetaMoneda = vE.get().getEtiquetaMoneda();
	        	atributosEncontrados = Constants.SI;
	        }
	        break;
	    case Constants.VARIABLE_LOCALIDAD:
	    	//log.info("------------> ETIQUETA VARIABLE_LOCALIDAD: ");
	    	VariableLocalidadPK idVL = VariableLocalidadPK.builder()
	    								.cveEntidad(cveEntidad)
	    								.cveLocalidad(cveLocalidad)
	    								.cveVariable(cveDato)
	    								.build();
	        Optional<VariableLocalidad> vL = iVariableLocalidadRepository.findById(idVL);
	        if(!vL.isPresent()) {
	        	etiquetaMoneda = vL.get().getEtiquetaMoneda();
	        	atributosEncontrados = Constants.SI;
	        }
	        break;
	    case Constants.VARIABLE_SISTEMA:
	    	//log.info("------------> ETIQUETA VARIABLE_SISTEMA: ");
	        Optional<VariableSistema> vS = iVariableSistemaRepository.findById(cveDato);
	        if(vS.isPresent()) {
	        	etiquetaMoneda = vS.get().getEtiquetaMoneda();
	        	atributosEncontrados = Constants.SI;
	        }
	        break;
		}
		
		// EVALUA SI LOS ATRIBUTOS DEL DATO HAN SIDO ENCONTRADOS
		if(atributosEncontrados.equals(Constants.NO)) {
			//log.info("XXXXXXXXXX ATRIBUTOS_NO_ENCONTRADOS XXXXXXXXXX");
			mensaje = messagesService.getMessage(
					session,
					idProceso,
					"ATRIBUTOS_NO_ENCONTRADOS",
					variableValor);
			result.setTipoExcepcion(Constants.ERROR);
			result.setMensaje(mensaje);
			return result;
		}
		
		result.setCveDatoMoneda(cveDatoMoneda);
		result.setCveMoneda(cveMoneda);
		result.setTipoCveMoneda(tipoCveMoneda);
		result.setTipoControlMoneda(tipoControlMoneda);
		result.setLongitudMoneda(longitudMoneda);
		result.setEtiquetaMoneda(etiquetaMoneda);
		//log.info("########## RETURN LEE MONEDA:" + result.toString());
		return result;
	}

	// SP_LEE_COLUMNAS_AREA_TRABAJO
	@Override
	public 	DAORet<List<ColumnasAreaTrabajoTO>, RetMsg> leeColumnaAreaTrabajo(DatosAutenticacionTO session, NodoTO nodo, String cveAreaTrabajo) {
		
		//log.info("---------> entra  leeColumnaAreaTrabajo <----------");
		//log.info("cveArea: " + cveAreaTrabajo);
		//log.info("cveProceso: " + nodo.getCveProceso());
		//log.info("version: " + nodo.getVersion());
		//log.info("NODO: " + nodo.toString());
		//log.info("SESION: " + session.toString());
		
		String cveEntidad = session.getCveEntidad();
		String cveProceso = nodo.getCveProceso();
		BigDecimal version = nodo.getVersion();
		float c70;
		int anchoColumna;
		Integer anchoColumnaOriginal = null; //revisar inicializacion 
		Integer anchoColumnaMoneda;
		String color;
		String colorDato = null; // revisar inicializacion
		String cveDato;
		String cveDatoMoneda;
		String cveListaFiltro;
		Integer decimales;
		String descripcionLista;
		String dpCveDatoProcesoNodo;
		Integer enteros;
		String etiqueta;
		String etiquetaMoneda;
		String etiquetaTodos;
		String filtro;
		String formatoFecha;
		Integer longitud;
		Integer longitudMoneda;
		String multiSeleccion;
		Integer numColumnas;
		String opcionTodos;
		String origenDato;
		String origenDatoVl;
		String origenDatoVs;
		String tablaLista;
		String tipoControl;
		String tipoControlMoneda;
		String tipoCveMoneda;
		String tipoDato;
		String tipoDatoAuxiliar;
		String tipoDatoDecimal;
		String tipoDatoMoneda;
		String tipoLista;
		String valorLista;
		String veCveVariable;
		String visible;
		String vpCveVariable;
		String whereLista;
		
		// CURSOR DE DATOS PARA EL ÁREA DE TRABAJO SOLICITADA
		List<DatoAreaTrabajoTO> datosAtVs = new ArrayList<DatoAreaTrabajoTO>();
		
		// CURSOR DE DATOS PARA EL ÁREA DE TRABAJO SOLICITADA
		List<Object> listaDatos = iDatoProcesoNodoRepository.obtenerDatos(cveEntidad, cveProceso, version, cveAreaTrabajo);
		
		if(!listaDatos.isEmpty()) {
			listaDatos.forEach(item -> {
				Object[] row = (Object[]) item;
				var itemSelected = DatoAreaTrabajoTO.builder()
						.ordenDato(((BigDecimal) Arrays.asList(row).get(MagicNumber.ZERO.getValue())).intValue())
						.etiqueta((String) Arrays.asList(row).get(MagicNumber.ONE.getValue()) != null ? (String) Arrays.asList(row).get(MagicNumber.ONE.getValue()) : "0")
					    .anchoColumna((BigDecimal) Arrays.asList(row).get(MagicNumber.TWO.getValue()) != null ? (BigDecimal) Arrays.asList(row).get(MagicNumber.TWO.getValue()) : BigDecimal.ZERO)
					    .tipoControl((String) Arrays.asList(row).get(MagicNumber.THREE.getValue()))
					    .origenDato((String) Arrays.asList(row).get(MagicNumber.FOUR.getValue()))
					    .visible((String) Arrays.asList(row).get(MagicNumber.FIVE.getValue()))
					    .color((String) Arrays.asList(row).get(MagicNumber.SIX.getValue()))
					    .filtro((String) Arrays.asList(row).get(MagicNumber.SEVEN.getValue()))
					    .tipoLista((String) Arrays.asList(row).get(MagicNumber.EIGHT.getValue()))
					    .multiSeleccion((String) Arrays.asList(row).get(MagicNumber.NINE.getValue()))
					    .opcionTodos((String) Arrays.asList(row).get(MagicNumber.TEN.getValue()))
					    .etiquetaTodos((String) Arrays.asList(row).get(MagicNumber.ELEVEN.getValue()))
					    .cveListaFiltro((String) Arrays.asList(row).get(MagicNumber.TWELVE.getValue()))
					    .columnaValor((String) Arrays.asList(row).get(MagicNumber.THIRTEEN.getValue()))
					    .columnaDescripcion((String) Arrays.asList(row).get(MagicNumber.FOURTEEN.getValue()))
					    .tablaLista((String) Arrays.asList(row).get(MagicNumber.FIFTEEN.getValue()))
					    .whereLista((String) Arrays.asList(row).get(MagicNumber.SIXTEEN.getValue()))
					    .cveDatoProcesoNodo((String) Arrays.asList(row).get(MagicNumber.SEVENTEEN.getValue()))
					    .cveProceso((String) Arrays.asList(row).get(MagicNumber.EIGHTEEN.getValue()))
					    .version((BigDecimal) Arrays.asList(row).get(MagicNumber.NINETEEN.getValue()))
					    .cveVariable((String) Arrays.asList(row).get(MagicNumber.TWENTY.getValue()))
					    .agregacion((String) Arrays.asList(row).get(MagicNumber.TWENTY_ONE.getValue()))
					    .cveEntidad((String) Arrays.asList(row).get(MagicNumber.TWENTY_TWO.getValue()))
					    .cveVariableEntidad((String) Arrays.asList(row).get(MagicNumber.TWENTY_THREE.getValue()))
					    .cveEntidadLocalidad((String) Arrays.asList(row).get(MagicNumber.TWENTY_FOUR.getValue()))
					    .cveLocalidad((String) Arrays.asList(row).get(MagicNumber.TWENTY_FIVE.getValue()))
					    .cveVariableLocalidad((String) Arrays.asList(row).get(MagicNumber.TWENTY_SIX.getValue()))
					    .cveVariableSistema((String) Arrays.asList(row).get(MagicNumber.TWENTY_SEVEN.getValue()))
					    .build();
				datosAtVs.add(itemSelected);
				}
			);
		}
		//log.info("-----------> datosAtVs:  " + datosAtVs.size());
		//log.info("SALIO CONSULTA");
		// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		RetMsg msg = RetMsg.builder().status("OK").message("").build();
		
		origenDatoVl = Constants.VARIABLE_LOCALIDAD;
		origenDatoVs = Constants.VARIABLE_SISTEMA;
		
		tipoDatoMoneda = Constants.MONEDA;
		tipoDatoDecimal = Constants.DECIMAL;
		
		c70 = (float)0.70;
		
		// CREANDO TABLA PARA INSERTAR LAS ESPECIFICACIONES DE LOS DATOS DEL ÁREA DE TRABAJO
		List<ColumnasAreaTrabajoTO> columnasAreaTrabajo = new ArrayList<ColumnasAreaTrabajoTO>();
		// RECORRE LOS DATOS PARA COMPLETAR LOS ATRIBUTOS DE LOS MISMOS
		numColumnas = 0;
		
		for(DatoAreaTrabajoTO dato : datosAtVs) {
			numColumnas = numColumnas + 1;
			log.debug("------->NUMERO COLUMNAS: " + numColumnas);
			
			etiqueta = dato.getEtiqueta();
			log.debug("------->ETIQUETA datosAtVs: " + etiqueta);
			anchoColumna = dato.getAnchoColumna().intValue();
			log.debug("-------- > ANCHO Columna Primer For  : " + anchoColumna);
			tipoControl = dato.getTipoControl();
			origenDato = dato.getOrigenDato();
			visible = dato.getVisible();
			color = dato.getColor();
			filtro = dato.getFiltro();
			tipoLista = dato.getTipoLista();
			multiSeleccion = dato.getMultiSeleccion();
			opcionTodos = dato.getOpcionTodos();
			etiquetaTodos = dato.getEtiquetaTodos();
			cveListaFiltro = dato.getCveListaFiltro();
			valorLista = dato.getColumnaValor();
			descripcionLista = dato.getColumnaDescripcion();
			tablaLista = dato.getTablaLista();
			whereLista = dato.getWhereLista();
			dpCveDatoProcesoNodo = dato.getCveDatoProcesoNodo();
			vpCveVariable = dato.getCveVariable();
			veCveVariable = dato.getCveVariableEntidad();
			
			//log.info("ANTES DEL SWITCH");
			// ASIGNA LA VARIABLE @CH_CVE_DATO DE ACUERDO AL ORIGEN
			switch (origenDato) {
			    case Constants.DATO_PROCESO_NODO:
			        cveDato = dpCveDatoProcesoNodo;
			        break;
			    case Constants.VARIABLE_PROCESO:
			        cveDato = vpCveVariable;
			        break;
			    case Constants.VARIABLE_ENTIDAD:
			        cveDato = veCveVariable;
			        break;
			    case Constants.VARIABLE_LOCALIDAD:
			        cveDato = origenDatoVl;
			        break;
			    case Constants.VARIABLE_SISTEMA:
			        cveDato = origenDatoVs;
			        break;
			    default:
			    	msg.setStatus((Constants.ERROR));
			    	return new DAORet<List<ColumnasAreaTrabajoTO>, RetMsg>(columnasAreaTrabajo, msg);
			}

			
			// DE ACUERDO AL ORIGEN DEL DATO, OBTIENE LOS ATRIBUTOS NECESARIOS
			//log.info("leeAtributosDato desde leeColumnaAreaTrabajo");
			EstatusAtributosDatoTO leeAD = leeAtributosDato(session, nodo, cveDato, origenDato);
			msg.setMessage((leeAD.getMensaje()));
			msg.setStatus((leeAD.getTipoExcepcion()));
			if(leeAD.getTipoExcepcion().equals(Constants.ERROR)) {
				log.info("----->> ERROR LEE ATRIBUTO DATO");
				return new DAORet<List<ColumnasAreaTrabajoTO>, RetMsg>(columnasAreaTrabajo, msg);
			}
			tipoDato = leeAD.getTipoDato();
			longitud = leeAD.getLongitud();
			enteros = leeAD.getEnteros();
			decimales = leeAD.getDecimales();
			formatoFecha = leeAD.getFormatoFecha();
			
			tipoDatoAuxiliar = tipoDato;
			if(tipoDato.equals(tipoDatoMoneda)) {
				anchoColumnaOriginal = anchoColumna;
				//log.info("-----------> ANCHO                 : " + anchoColumna);
				//log.info("-----------> ANCHO COLUMNA ORIGINAL: " + anchoColumnaOriginal);
		        float mul = anchoColumna * c70;
		        //log.info("-----------> MUL                   : " + mul);
				anchoColumna = (int) mul;				
				//log.info("-----------> ANCHO COLUMNA ORIGINAL:  " + anchoColumna);
				tipoDatoAuxiliar = tipoDatoDecimal;
				
			}
			
			// INSERT
			ColumnasAreaTrabajoTO ins1 = ColumnasAreaTrabajoTO.builder()
				    .secuencia(numColumnas)
				    .cveVariable(cveDato)
				    .visible(visible)
				    .etiqueta(etiqueta)
				    .anchoColumna(anchoColumna)
				    .tipoDato(tipoDatoAuxiliar)
				    .colorDato(colorDato)
				    .longitud(longitud)
				    .enteros(enteros)
				    .decimales(decimales)
				    .formatoFecha(formatoFecha)
				    .tipoControl(tipoControl)
				    .filtro(filtro)
				    .tipoLista(tipoLista)
				    .valorLista(valorLista)
				    .descripcionLista(descripcionLista)
				    .tablaLista(tablaLista)
				    .whereLista(whereLista)
				    .multiseleccion(multiSeleccion)
				    .opcionTodos(opcionTodos)
				    .etiquetaTodos(etiquetaTodos)
				    .cveListaFiltro(cveListaFiltro)
				    .build();
			log.debug("--------> INS 1: " + ins1);
			columnasAreaTrabajo.add(ins1);
			
			// ARMA VARIABLE AUXILIAR PARA UNA VARIABLE DE TIPO MONEDA
			if(tipoDato.equals(tipoDatoMoneda)) {
				EstatusAtributosMonedaTO leeAM = leeAtributosMoneda(session, nodo, cveDato, origenDato);
				//log.info("-------->> LEE ATRIBUTO MONEDA: " + leeAM.toString());
				msg.setMessage(leeAM.getMensaje());
				msg.setStatus(leeAM.getTipoExcepcion());
				if(leeAD.getTipoExcepcion().equals(Constants.ERROR)) {
					log.debug("----->> ERROR LEE ATRIBUTO MONEDA");
					return new DAORet<List<ColumnasAreaTrabajoTO>, RetMsg>(columnasAreaTrabajo, msg);
				}
				cveDatoMoneda = leeAM.getCveDatoMoneda();
				tipoCveMoneda = leeAM.getTipoCveMoneda();
				tipoControlMoneda = leeAM.getTipoControlMoneda();
				longitudMoneda = leeAM.getLongitudMoneda();
				etiquetaMoneda = leeAM.getEtiquetaMoneda();
				log.info("-------> ETIQUETA leeAtributosMoneda: " + etiquetaMoneda);
				
				// CREA EL REGISTRO PARA LAS VARIABLES DEL ÁREA DE TRABAJO
				numColumnas = numColumnas +1;
				//log.info("-------> CREA EL REGISTRO PARA LAS VARIABLES DEL ÁREA DE TRABAJO: " + numColumnas);
				//log.info("-------> NUMERO COLUMNAS: " + numColumnas);
				anchoColumnaMoneda = anchoColumnaOriginal - anchoColumna;
				
				// INSERT
				ColumnasAreaTrabajoTO ins2 = ColumnasAreaTrabajoTO.builder()
					    .secuencia(numColumnas)
					    .cveVariable(cveDatoMoneda)
					    .visible(visible)
					    .etiqueta(etiquetaMoneda)
					    .anchoColumna(anchoColumnaMoneda)
					    .tipoDato(tipoCveMoneda)
					    .colorDato(color)
					    .longitud(longitudMoneda)
					    .tipoControl(tipoControlMoneda)
					    .filtro(filtro)
					    .build();
				//log.debug("--------> INS 2: " + ins2);
				columnasAreaTrabajo.add(ins2);
			}
		}
		
		// GENERA EL RESULT SET
		for(ColumnasAreaTrabajoTO to : columnasAreaTrabajo) {
			to.setNumColumnas(numColumnas);
			//log.debug("---------> COLUMNA:           " + to.toString());
			//log.debug("<<<---------------------------------------->>>");
			//log.debug("---------> Secuencia:           " + to.getSecuencia());
			//log.debug("---------> Numero Columna:      " + to.getNumColumnas());
			//log.debug("---------> Etiqueta:            " + to.getEtiqueta());
			to.setEtiqueta((nodoHelper.traducirEtiqueta(session, to.getEtiqueta())));
			//log.debug("---------> Etiqueta formateada: " + to.getEtiqueta());
			//log.debug("-------- > ANCHO              : " + to.getAnchoColumna());
		}
		//log.debug("--------->LISTA columnasAreaTrabajo: " + columnasAreaTrabajo.size() + " " + columnasAreaTrabajo.toString());

		log.debug("-------- > TERMINA           leeColumnaAreaTrabajo    ");
		return new DAORet<List<ColumnasAreaTrabajoTO>, RetMsg>(columnasAreaTrabajo, msg);
	}

	// SP_LEE_ACCIONES_ACTIVIDAD
	private EstatusAccionesTO leeAccionesActividad2(DatosAutenticacionTO session, NodoTO nodo) throws BrioBPMException {
		
		String cveUsuario = session.getCveUsuario();
		String cveEntidad = session.getCveEntidad();
		String cveInstancia = nodo.getCveInstancia();
		String cveNodo = nodo.getCveNodo();
		String cveProceso = nodo.getCveProceso();
		Integer idNodo = nodo.getIdNodo();
		Integer secuenciaNodo = nodo.getSecuenciaNodo();
		BigDecimal version = nodo.getVersion();
		String botonCancelar = " ";
		String botonConsultar = " ";
		String botonEjecutar = " ";
		String botonLiberar = " ";
		String botonTerminar = " ";
		String botonTomar = " ";
		String botonBitacora = " ";
		String cveUsuarioBloquea = " ";
		String estado;
		Date fechaBloquea = null;
		String etiquetaCancelar = " ";
		String etiquetaConsultar = " ";
		String etiquetaEjecutar = " ";
		String etiquetaLiberar = " ";
		String etiquetaTerminar = " ";
		String etiquetaTomar = " ";
		String etiquetaBitacora = " ";
		String idProceso;

		
		// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		EstatusAccionesTO result = EstatusAccionesTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();
		idProceso = Constants.LEE_ACCIONES_ACTIVIDAD;
		nodo.setIdProceso(idProceso);
		
		
		//Proceso para obtener las etiquetas
		StNodoProcesoPK id = StNodoProcesoPK.builder()
				.cveEntidad(cveEntidad)
				.cveProceso(cveProceso)
				.version(version)
				.cveNodo(cveNodo)
				.idNodo(idNodo)
				.build();
		
		Optional<StNodoProceso> entity = stNodoProcesoRepository.findById(id);
		//OBTENER LA CONFIGURACIÓN DE ACCIONES Y ETIQUETAS DEL NODO
		result.setEtiquetaTomar("");
		result.setEtiquetaLiberar("");
		result.setEtiquetaEjecutar("");
		result.setEtiquetaTerminar(""); 
		result.setEtiquetaCancelar("");
		result.setEtiquetaConsultar("");
		result.setEtiquetaBitacora("");
		result.setHabilitarTomar(Constants.NO);
		result.setHabilitarLiberar(Constants.NO);
		result.setHabilitarEjecutar(Constants.NO);
		result.setHabilitarTerminar(Constants.NO);
		result.setHabilitarCancelar(Constants.NO);
		result.setHabilitarConsultar(Constants.NO);
		result.setHabilitarBitacora(Constants.NO);
		
		if (entity.isPresent()) {
			StNodoProceso ite = entity.get();

			botonTomar = ite.getBotonTomar();
			botonTomar = (botonTomar == null) ? "" : botonTomar;
			botonLiberar = ite.getBotonLiberar();
			botonLiberar = (botonLiberar == null) ? "" : botonLiberar;
			botonEjecutar = ite.getBotonEjecutar();
			botonEjecutar = (botonEjecutar == null) ? "" :  botonEjecutar;
			botonTerminar = ite.getBotonTerminar();
			botonTerminar = (botonTerminar == null) ? "" :  botonTerminar;
			botonCancelar = ite.getBotonCancelar();
			botonCancelar = (botonCancelar == null) ? "" :  botonCancelar;
			botonConsultar = ite.getBotonConsultar();
			botonConsultar = (botonConsultar == null) ? "" :  botonConsultar;
			botonBitacora = ite.getBotonBitacora();
			botonBitacora = (botonBitacora == null) ? "" :  botonBitacora;
		
			etiquetaTomar = ite.getEtiquetaBotonTomar();
			etiquetaTomar = (etiquetaTomar == null) ? " " : etiquetaTomar;
			etiquetaLiberar = ite.getEtiquetaBotonLiberar();
			etiquetaLiberar = (etiquetaLiberar == null) ? " " : etiquetaLiberar; 
			etiquetaEjecutar = ite.getEtiquetaBotonEjecutar();
			etiquetaEjecutar = (etiquetaEjecutar == null) ? " " : etiquetaEjecutar;
			etiquetaTerminar = ite.getEtiquetaBotonTerminar();
			etiquetaTerminar = (etiquetaTerminar == null) ? " " :  etiquetaTerminar;
			etiquetaCancelar = ite.getEtiquetaBotonCancelar();
			etiquetaCancelar = (etiquetaCancelar == null) ? " " :  etiquetaCancelar;
			etiquetaConsultar = ite.getEtiquetaBotonConsultar();
			etiquetaConsultar = (etiquetaConsultar == null) ? " " :  etiquetaConsultar;
			etiquetaBitacora = ite.getEtiquetaBotonBitacora();
			etiquetaBitacora = (etiquetaBitacora == null) ? " " :  etiquetaBitacora;

			//OBTENER LA CONFIGURACIÓN DE ACCIONES Y ETIQUETAS DEL NODO
			result.setEtiquetaTomar(etiquetaTomar);
			result.setEtiquetaLiberar(etiquetaLiberar);
			result.setEtiquetaEjecutar(etiquetaEjecutar);
			result.setEtiquetaTerminar(etiquetaTerminar); 
			result.setEtiquetaCancelar(etiquetaCancelar);
			result.setEtiquetaConsultar(etiquetaConsultar);
			result.setEtiquetaBitacora(etiquetaBitacora);
		}

		// HABILITANDO, DESHABILITANDO LA ACCIÓN "CONSULTAR"
		if (botonConsultar.equals(Constants.SI)) {
			result.setHabilitarConsultar(Constants.SI);
		}
		
		//PC
		//	OBTIENE LA SITUACIÓN ACTUAL DEL NODO, ASÍ COMO EL USUARIO Y LA FECHA DE BLOQUEO
		//
		
		// VALIDA LA EXISTENCIA DE LA ACTIVIDAD ESPECIFICADA
		InNodoProcesoPK idInNodoPro = InNodoProcesoPK.builder()
				.cveEntidad(cveEntidad)
				.cveProceso(cveProceso)
				.version(version)
				.cveInstancia(cveInstancia)
				.cveNodo(cveNodo)
				.idNodo(idNodo)
				.secuenciaNodo(secuenciaNodo)
				.build();
		//log.info("---->ID : " + idInNodoPro.toString());
		
		log.debug("--- inNodoProcesoRepository.findById(id) leeAccionesActividad2");
		Optional<InNodoProceso> existe = iInNodoProcesoRepository.findById(idInNodoPro);
		log.debug("---->ANTES DEL IF");
		if(existe.isPresent()) {
			estado = existe.get().getEstado();
			estado = (estado == null) ? "" : estado;
			cveUsuarioBloquea = existe.get().getUsuarioBloquea();
			fechaBloquea = existe.get().getFechaBloquea();
			log.debug("---->fechaBloquea: " + fechaBloquea);

			// DETERMINA EL RESTO DE LAS ACCIONES QUE SE HABILITAN Y DESHABILITAN
			if(estado.equals(Constants.REGISTRO) && fechaBloquea == null) {
				// TOMAR
				if(botonTomar.equals(Constants.SI)) {
					result.setHabilitarTomar(Constants.SI);
				} else {
					result.setHabilitarTomar(Constants.NO);
				}
				// LIBERAR
				result.setHabilitarLiberar(Constants.NO);
				// EJECUTAR
				if(botonEjecutar.equals(Constants.SI)) {
					result.setHabilitarEjecutar(Constants.SI);
				} else {
					result.setHabilitarEjecutar(Constants.NO);
				}
				// TERMINAR
				if(botonTerminar.equals(Constants.SI)) {
					result.setHabilitarEjecutar(Constants.SI);
				} else {
					result.setHabilitarEjecutar(Constants.NO);
				}
				// CANCELAR
				result.setHabilitarCancelar(Constants.NO);
			} else {
				if(estado.equals(Constants.REGISTRO) && fechaBloquea != null){
					// TOMAR
					result.setHabilitarTomar(Constants.NO);
					// LIBERAR
					result.setHabilitarLiberar(Constants.NO);
					if(botonLiberar.equals(Constants.SI)) {
						if(cveUsuario.equals(cveUsuarioBloquea)) {
							result.setHabilitarLiberar(Constants.SI);
						}
					}
					// EJECUTAR
					result.setHabilitarEjecutar(Constants.NO);
					if(botonEjecutar.equals(Constants.SI)) {
						if(cveUsuario.equals(cveUsuarioBloquea)) {
							result.setHabilitarEjecutar(Constants.SI);
						}
					}
					// TERMINAR
					result.setHabilitarTerminar(Constants.NO);
					if(botonTerminar.equals(Constants.SI)) {
						if(cveUsuario.equals(cveUsuarioBloquea)) {
							result.setHabilitarTerminar(Constants.SI);
						}
					}
					// CANCELAR
					result.setHabilitarCancelar(Constants.NO);
					if(botonCancelar.equals(Constants.SI)) {
						if(cveUsuario.equals(cveUsuarioBloquea)) {
							result.setHabilitarCancelar(Constants.SI);
						}
					}
				
				} else {
					if(estado.equals(Constants.TERMINADA) || estado.equals(Constants.CANCELADA)) {
						result.setHabilitarTomar(Constants.NO);
						result.setHabilitarLiberar(Constants.NO);
						result.setHabilitarEjecutar(Constants.NO);
						result.setHabilitarTerminar(Constants.NO);
						result.setHabilitarCancelar(Constants.NO);
					}
				}

			} 
		
		}
		


		// PUESTO QUE SE TRATA DE UNA CONSULTA, SE QUEDA TAL CUAL ES REPORTADA POR LA CONFIGURACIÓN DEL NODO
		result.setHabilitarBitacora(botonBitacora);
		return result;
	}
	
	
	
	@Autowired
	private IComposicionCorreoRepository composicionCorreoRepository;
	
	public RetMsg actualizaSItuacionCorreo(DatosAutenticacionTO session, String cveProceso, BigDecimal version,
			Integer numeroCorreo, String situacion) throws BrioBPMException {
		  String idProceso;
		  String mensaje; 
		  String variableValor;  
		  // INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		  RetMsg msg = RetMsg.builder().status(Constants.OK).message("").build();
		  idProceso = "ACTUALIZA_SITUACION_CORREO";  
		  // CONCATENA LA LISTA DE LOS PARÁMETROS PARA DESPLEGARLOS EN CASO DE ERROR
		  variableValor = Constants.CVE_PROCESO + cveProceso + "|" +
		   Constants.VERSION + version + "|" +  "@NUMERO_CORREO@|" + numeroCorreo;
		  //VALIDA LA EXISTENCIA DEL CORREO RECIBIDO
		  List<ComposicionCorreo> correosRecibidos = composicionCorreoRepository.existeCorreoRecibido(cveProceso,version,numeroCorreo);
		  if (correosRecibidos == null || correosRecibidos.isEmpty()) {
			  mensaje = messagesService.getMessage(session,		
					  idProceso,
					  "ERR_CORREO_NO_EXISTE",
					  variableValor);
					  msg.setMessage(mensaje);
					  msg.setStatus(Constants.ERROR);
					  return msg;
		  }
		  //ACTUALIZA LA SITUACIÓN DEL CORREO 
		  for (ComposicionCorreo correo:correosRecibidos) {
			  correo.setSituacionCorreo(situacion);
			  composicionCorreoRepository.saveAndFlush(correo);
			  if (!situacion.equals(correo.getSituacionCorreo())) {
				  mensaje = messagesService.getMessage(session,		
						  idProceso,
						  "ERR_ACTUALIZACION_CORREO",
						  variableValor);
						  msg.setMessage(mensaje);
						  msg.setStatus(Constants.ERROR);
						  return msg;
			  }  
		  }
		return msg;
	}
}
