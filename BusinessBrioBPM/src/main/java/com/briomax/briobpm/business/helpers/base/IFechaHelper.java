package com.briomax.briobpm.business.helpers.base;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.CrDefinicionPeriocidad;
import com.briomax.briobpm.persistence.entity.CrPeriodicidad;
import com.briomax.briobpm.transferobjects.CalendarioLocalidadTO;
import com.briomax.briobpm.transferobjects.in.CalculoFechaLimiteTO;
import com.briomax.briobpm.transferobjects.in.CalculoFechaMasPlazoTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.EstatusFechaTO;
import com.briomax.briobpm.transferobjects.in.EstatusTO;
import com.briomax.briobpm.transferobjects.repse.IntervaloFechaTO;
import com.briomax.briobpm.transferobjects.repse.OrdenFechaIntervalosTO;

/**
 * El objetivo de la Interface IInFolioNodoHelper.java es ...
 * 
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Nov 17, 2023 6:05:58 PM Modificaciones:
 * @since JDK 1.8
 */
public interface IFechaHelper {

	// SP_CALCULA_FECHA_LIMITE
	/**
	 * Calcula la fecha límite del proceso utilizando información de autenticación y datos de fecha específicos.
	 *
	 * @param session      Datos de autenticación de usuario.
	 * @param datosFecha   Información necesaria para el cálculo de la fecha límite.
	 * @return EstatusFechaTO con la fecha límite calculada y estado del proceso.
	 * @throws BrioBPMException En caso de error durante el cálculo de la fecha límite.
	 * @throws ParseException  En caso de error al parsear las fechas.
	 */
	EstatusFechaTO calculaFechaLimite(DatosAutenticacionTO session, CalculoFechaLimiteTO datosFecha)
			throws BrioBPMException, ParseException;

	// SP_CALCULA_HORA_LOCALIDAD
	/**
	 * Calcula la hora local de una localidad específica a partir de una fecha y hora original.
	 *
	 * @param session         Datos de autenticación de usuario.
	 * @param fechaHoraOriginal Fecha y hora original a ajustar según la localidad.
	 * @return EstatusFechaTO con la hora local calculada y estado del proceso.
	 * @throws BrioBPMException En caso de error durante el cálculo de la hora local.
	 */
	EstatusFechaTO calculaHoraLocalidad(DatosAutenticacionTO session, Date fechaHoraOriginal) throws BrioBPMException;

	// SP_CARGA_CALENDARIO_LOCALIDAD
	/**
	 * Carga el calendario de una localidad específica para cálculos de fechas.
	 *
	 * @param session      Datos de autenticación de usuario.
	 * @param datosFecha   Información necesaria para la carga del calendario de la localidad.
	 * @return EstatusTO con el resultado de la carga del calendario y estado del proceso.
	 * @throws BrioBPMException En caso de error durante la carga del calendario.
	 */
	EstatusTO cargaCalendarioLocalidad(DatosAutenticacionTO session, CalculoFechaLimiteTO datosFecha, List<CalendarioLocalidadTO> calendarioLocalidad)
			throws BrioBPMException;

	// SP_CALCULA_FECHA_MAS_PLAZO_HMS
	/**
	 * Calcula la fecha límite del proceso considerando un plazo específico en horas, minutos y segundos.
	 *
	 * @param session      Datos de autenticación de usuario.
	 * @param datosFecha   Información necesaria para el cálculo de la fecha límite con plazo en HMS.
	 * @return EstatusFechaTO con la fecha límite calculada y estado del proceso.
	 * @throws BrioBPMException En caso de error durante el cálculo de la fecha límite.
	 * @throws ParseException  En caso de error al parsear las fechas.
	 */
	EstatusFechaTO calculaFechaMasPlazoHMS(DatosAutenticacionTO session, CalculoFechaMasPlazoTO datosFecha, List<CalendarioLocalidadTO> calendarioLocalidad)
			throws BrioBPMException, ParseException;

	// SP_CALCULA_FECHA_MAS_PLAZO_DSM
	/**
	 * Calcula la fecha límite del proceso considerando un plazo específico en días, semanas y meses.
	 *
	 * @param session      Datos de autenticación de usuario.
	 * @param datosFecha   Información necesaria para el cálculo de la fecha límite con plazo en DSM.
	 * @return EstatusFechaTO con la fecha límite calculada y estado del proceso.
	 * @throws BrioBPMException En caso de error durante el cálculo de la fecha límite.
	 * @throws ParseException  En caso de error al parsear las fechas.
	 */
	EstatusFechaTO calculaFechaMasPlazoDSM(DatosAutenticacionTO session, CalculoFechaMasPlazoTO datosFecha, List<CalendarioLocalidadTO> calendarioLocalidad)
			throws BrioBPMException, ParseException;

	// SP_VALIDA_FECHA_HABIL
	/**
	 * Valida si una fecha específica es hábil o no, considerando la entidad y localidad base.
	 *
	 * @param cveEntidadBase      Clave de la entidad base.
	 * @param cveLocalidadBase    Clave de la localidad base.
	 * @param fechaRevision       Fecha que se desea validar.
	 * @return Cadena indicando si la fecha es hábil ("SI") o no ("NO").
	 * @throws BrioBPMException En caso de error durante la validación de la fecha hábil.
	 * @throws ParseException  En caso de error al parsear las fechas.
	 */
	String validaFechaHabil(String cveEntidadBase, String cveLocalidadBase, Date fechaRevision, List<CalendarioLocalidadTO> calendarioLocalidad)
			throws BrioBPMException, ParseException;

	// SP_CALCULA_FECHA_MAS_PLAZO
	/**
	 * Calcula la fecha límite del proceso considerando un plazo específico en días, semanas y meses.
	 *
	 * @param session      Datos de autenticación de usuario.
	 * @param datosFecha   Información necesaria para el cálculo de la fecha límite con plazo en DSM.
	 * @return EstatusFechaTO con la fecha límite calculada y estado del proceso.
	 * @throws BrioBPMException En caso de error durante el cálculo de la fecha límite.
	 * @throws ParseException  En caso de error al parsear las fechas.
	 */
	EstatusFechaTO calculaFechaMasPlazo(DatosAutenticacionTO session, CalculoFechaMasPlazoTO datosFecha)
			throws BrioBPMException, ParseException;

	// SP_CALCULA_FECHA_TEMPORIZADOR
	/**
	 * Calcula la fecha límite del proceso considerando un temporizador específico.
	 *
	 * @param session Datos de autenticación de usuario.
	 * @param datos   Información necesaria para el cálculo de la fecha límite con temporizador.
	 * @return EstatusFechaTO con la fecha límite calculada y estado del proceso.
	 * @throws BrioBPMException En caso de error durante el cálculo de la fecha límite.
	 * @throws ParseException  En caso de error al parsear las fechas.
	 */
	EstatusFechaTO calculaFechaTemporizador(DatosAutenticacionTO session, CalculoFechaLimiteTO datos)
			throws BrioBPMException, ParseException;
	
	// SP_LEE_INTERVALO_FECHAS
		/**
		 * EL PROCESO GENERA LOS INTERVALOS DE FECHAS DE REPETICIÓN (FECHA INICIAL, FECHA FINAL).
		 *
		 * @param cveEntidad Datos de autenticación de usuario.
		 * @param datos   Información necesaria para el intervalo de fechas.
		 * @throws BrioBPMException En caso de error durante el cálculo de la fecha límite.
		 * @throws ParseException  En caso de error al parsear las fechas.
		 */
	List<OrdenFechaIntervalosTO> leeIntervaloFecha(String cveEntidad, IntervaloFechaTO datos);

	RetMsg cargaCalendarioLocalidad(String cveEntidad, String cveLocalidad, String cveProceso,
			BigDecimal version, List<CalendarioLocalidadTO> calendarioLocalidad) throws BrioBPMException;

	Date obtenerFechaCreacion(String cveEntidad, String cveLocalidad);

	/**
	 * store que genera leeFechaRepeticion
	 * @param periodicidad
	 * @param difinicion
	 * @param cveProceso
	 * @param version
	 * @param tipoProceso
	 * @param aplicaInicio
	 * @return
	 * @throws BrioBPMException
	 * @throws ParseException
	 */
	List<Date> leeFechaRepeticion(CrPeriodicidad periodicidad, CrDefinicionPeriocidad difinicion, String cveProceso, BigDecimal version, String tipoProceso, String aplicaInicio)
			throws BrioBPMException, ParseException;
	
	/**
	 * Genera fechas de intervalo
	 * @param datos
	 * @param interFech
	 * @return
	 * @throws BrioBPMException
	 */
	List<OrdenFechaIntervalosTO> generaFechasIntervalo(IntervaloFechaTO datos, List<OrdenFechaIntervalosTO> interFech)
			throws BrioBPMException;
	
	/**
	 * Ajuste de fechas por la determinación de dis exacto o habil
	 * @param cveEntidad
	 * @param cveLocalidad
	 * @param cveIdioma
	 * @param cveProceso
	 * @param version
	 * @param tipoDia
	 * @param ordenFecha
	 * @return
	 * @throws BrioBPMException
	 * @throws ParseException
	 */
	List<Date> ajustaFechasIntervalo(String cveEntidad, String cveLocalidad, String cveIdioma,
			String cveProceso, BigDecimal version, String tipoDia,
			List<OrdenFechaIntervalosTO> ordenFecha) 
            throws BrioBPMException, ParseException;

	/**
	 * Ajuste de crecion de periodos para REPSE
	 * @param cveEntidad
	 * @param datos
	 * @param aplicaInicio
	 * @return
	 * @throws BrioBPMException
	 */
	List<OrdenFechaIntervalosTO> leeIntervaloFechaRepse(String cveEntidad, IntervaloFechaTO datos, String aplicaInicio) 
			throws BrioBPMException;

	/**
	 * Ajuste de crecion de periodos para REPSE
	 * @param datos
	 * @param interFech
	 * @return
	 * @throws BrioBPMException
	 */
	List<OrdenFechaIntervalosTO> generaFechasIntervaloRepse(IntervaloFechaTO datos,
			List<OrdenFechaIntervalosTO> interFech) throws BrioBPMException;

	Date convierteCadenaToDate(String valor);
}












