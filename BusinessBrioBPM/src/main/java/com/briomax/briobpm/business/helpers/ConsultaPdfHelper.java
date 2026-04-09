/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.helpers;

import java.sql.Blob;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.sql.rowset.serial.SerialBlob;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briomax.briobpm.business.convertes.ICrAvisoICSOEConverter;
import com.briomax.briobpm.business.convertes.ICrAvisoSISUBConverter;
import com.briomax.briobpm.business.convertes.ICrCedulaDetCuotasConverter;
import com.briomax.briobpm.business.convertes.ICrComprobanteCuotaOPConverter;
import com.briomax.briobpm.business.convertes.ICrDeclaracionComplementarioConverter;
import com.briomax.briobpm.business.convertes.ICrDeclaracionProvicionalConverter;
import com.briomax.briobpm.business.convertes.ICrFormatoCuotaOPConverter;
import com.briomax.briobpm.business.convertes.ICrPagoBancarioConverter;
import com.briomax.briobpm.business.convertes.ICrReciboNominaConverter;
import com.briomax.briobpm.business.convertes.ICrRegistroObraConverter;
import com.briomax.briobpm.business.convertes.IRepseConverter;
import com.briomax.briobpm.business.helpers.base.IConsultaPdfHelper;
import com.briomax.briobpm.common.core.Constants;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.CrAvisoICSOE;
import com.briomax.briobpm.persistence.entity.CrAvisoSISUB;
import com.briomax.briobpm.persistence.entity.CrCedulaDetCuotas;
import com.briomax.briobpm.persistence.entity.CrComprobanteCuotasOP;
import com.briomax.briobpm.persistence.entity.CrConsultaRepse;
import com.briomax.briobpm.persistence.entity.CrDeclaracionComplementario;
import com.briomax.briobpm.persistence.entity.CrDeclaracionProvisional;
import com.briomax.briobpm.persistence.entity.CrFormatoCuotasOP;
import com.briomax.briobpm.persistence.entity.CrPagoBancario;
import com.briomax.briobpm.persistence.entity.CrPdfFiles;
import com.briomax.briobpm.persistence.entity.CrPdfFilesPK;
import com.briomax.briobpm.persistence.entity.CrProgramacionProceso;
import com.briomax.briobpm.persistence.entity.CrReciboNomina;
import com.briomax.briobpm.persistence.entity.CrRegistroObra;
import com.briomax.briobpm.persistence.entity.ParametroGeneral;
import com.briomax.briobpm.persistence.jdto.Documento;
import com.briomax.briobpm.persistence.repository.ICrAvisoICSOERepository;
import com.briomax.briobpm.persistence.repository.ICrAvisoSISUBRepository;
import com.briomax.briobpm.persistence.repository.ICrCedulaDetCuotasRepository;
import com.briomax.briobpm.persistence.repository.ICrComprobanteCuotaOPRepository;
import com.briomax.briobpm.persistence.repository.ICrConsultaRepseRepository;
import com.briomax.briobpm.persistence.repository.ICrDeclaracionComplementarioRepository;
import com.briomax.briobpm.persistence.repository.ICrDeclaracionProvisionalRepository;
import com.briomax.briobpm.persistence.repository.ICrFormatoCuotaOPRepository;
import com.briomax.briobpm.persistence.repository.ICrPagoBancarioRepository;
import com.briomax.briobpm.persistence.repository.ICrPdfFilesRepository;
import com.briomax.briobpm.persistence.repository.ICrReciboNominaRepository;
import com.briomax.briobpm.persistence.repository.ICrRegistroObraRepository;
import com.briomax.briobpm.persistence.repository.IInVariableProcesoRepository;
import com.briomax.briobpm.persistence.repository.IParametroGeneralRepository;
import com.briomax.briobpm.transferobjects.PdfGridTO;
import com.briomax.briobpm.transferobjects.dynamicForm.Properties;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.DocumentoTO;
import com.briomax.briobpm.transferobjects.repse.ConsultaPdfTO;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class ICargaPdfHelper.java es ...
 * 
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Nov 12, 2024 6:59:06 PM Modificaciones:
 * @since JDK 1.8
 */
@Service
@Transactional
@Slf4j
public class ConsultaPdfHelper implements IConsultaPdfHelper {

	/** El atributo o variable. */
	@Autowired
	private ICrReciboNominaRepository crReciboNominaRepository;

	/** El atributo o variable. */
	@Autowired
	private ICrDeclaracionProvisionalRepository crDeclaracionProvisionalRepository;

	/** El atributo o variable. */
	@Autowired
	private ICrPagoBancarioRepository crPagoBancarioRepository;

	/** El atributo o variable. */
	@Autowired
	private ICrRegistroObraRepository crRegistroObraRepository;

	/** El atributo o variable. */
	@Autowired
	private ICrComprobanteCuotaOPRepository crComprobanteCuotaOPRepository;

	/** El atributo o variable. */
	@Autowired
	private ICrFormatoCuotaOPRepository crFormatoCuotaOPRepository;

	/** El atributo o variable repository. */
	@Autowired
	private ICrCedulaDetCuotasRepository crCedulaDetCuotasRepository;

	/** El atributo o variable. */
	@Autowired
	private ICrDeclaracionComplementarioRepository crDeclaracionComplementarioRepository;

	/** El atributo o variable repository. */
	@Autowired
	private ICrConsultaRepseRepository crConsultaRepseRepository;
	
	/** El atributo o variable. */
	@Autowired
	private ICrAvisoICSOERepository crAvisoICSOERepository;
	
	/** El atributo o variable. */
	@Autowired
	private ICrAvisoSISUBRepository crAvisoSISUBRepository;
	
	/** El atributo o variable. */
	@Autowired
	private ICrPdfFilesRepository crCrPdfFilesRepository;
	
	@Autowired
	private IParametroGeneralRepository parametroGeneralRepository;
	
	@Autowired
	private IInVariableProcesoRepository variableProcesoRepository;

	/** Crear una nueva instancia del objeto usuarios helper. */
	public ConsultaPdfHelper() {
	}

	@Override
	public PdfGridTO execEnvioRecibosNomina(DatosAutenticacionTO session, Date fechaLimiteInferior, Date fechaLimiteSuperior, String rfc)
	        throws BrioBPMException {
		    
		log.info(">>>>>>>>>>>>>>>>>>>> execEnvioRecibosNomina <<<<<<<<<<<<<<<<<<");
		
		List<Date> limitesFecha = obtieneRangoFechas(fechaLimiteInferior, fechaLimiteSuperior);
		
		Date fechaInicio = limitesFecha.get(0);
		Date fechaFin = limitesFecha.get(1);
		
		List<Properties> columns = new ArrayList<Properties>();

		Properties column = Properties.builder().width(300).cveVariable("situacionCarga").etiqueta("Estatus de Carga")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("fechaCarga").etiqueta("Fecha de Carga")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("rfcContratista").etiqueta("RFC - Patrón")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(500).cveVariable("razonSocialPatron").etiqueta("Razón Social - Patrón")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("registroPatronal").etiqueta("Registro Patronal")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("fechaInicioPeriodo").etiqueta("Fecha Inicio")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("fechaFinalPeriodo").etiqueta("Fecha Final")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("fechaPago").etiqueta("Fecha de Pago")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("fechaTimbrado").etiqueta("Fecha de Timbrado")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("tipoNomina").etiqueta("Tipo de Nómina")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(500).cveVariable("nombreTrabajdor").etiqueta("Nombre del Empleado")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("curpTrabajdor").etiqueta("CURP - Empleado")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("rfcTrabajador").etiqueta("RFC - Empleado")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("nssTrabajdor").etiqueta("NSS").tipoDato("ALFANUMERICO")
				.visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("salarioBase").etiqueta("Salario Base de Cotización")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(150).cveVariable("boton").etiqueta("Descarga")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		List<Object> listDto = new ArrayList<Object>();
		
		List<CrReciboNomina> listEntity = new ArrayList<CrReciboNomina>();
		
		if (rfc.isEmpty()) {
			listEntity = crReciboNominaRepository.getCargaReciboNominaFiltrado(
					session.getCveEntidad(), fechaInicio, fechaFin);
		} else {
			listEntity = crReciboNominaRepository
					.getCargaReciboNominaFiltrados(session.getCveEntidad(), fechaInicio, fechaFin, rfc);			
		}
				
		log.info(">>>>>>>>>>>>>>>>>>>> listEntity:" + listEntity.size());
		if (listEntity.size() > 0) {
			listDto.addAll(listEntity.stream().map(ICrReciboNominaConverter.converterEntityToDTO)
					.collect(Collectors.toList()));
		}

		PdfGridTO result = PdfGridTO.builder().cells(listDto).columns(columns).build();

		return result;
	}

	@Override
	public PdfGridTO  execDeclaracionProvicionalContratista(DatosAutenticacionTO session, Date fechaLimiteInferior, Date fechaLimiteSuperior, String rfc)
	        throws BrioBPMException {
		log.info(">>>>>>>>>>>>>>>>>>>> execDeclaracionProvicionalContratista <<<<<<<<<<<<<<<<<<");

		List<Date> limitesFecha = obtieneRangoFechas(fechaLimiteInferior, fechaLimiteSuperior);
		
		Date fechaInicio = limitesFecha.get(0);
		Date fechaFin = limitesFecha.get(1);
		
		List<Properties> columns = new ArrayList<Properties>();

		Properties column = Properties.builder().width(300).cveVariable("situacionCarga").etiqueta("Estatus de Carga")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("fechaCarga").etiqueta("Fecha de Carga")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("rfc").etiqueta("RFC").tipoDato("ALFANUMERICO")
				.visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("razonSocial").etiqueta("Razón Social")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("tipoDeclaracion").etiqueta("Tipo de Declaración")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("periodoDeclaracion").etiqueta("Período de la Declaración")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("ejercicio").etiqueta("Ejercicio").tipoDato("ALFANUMERICO")
				.visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("fechaHoraPresentacion")
				.etiqueta("Fecha y Hora de Presentación").tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(500).cveVariable("lineaCaptura").etiqueta("Línea de Captura")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(500).cveVariable("conceptoPago").etiqueta("Concepto de Pago")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("importeApagar").etiqueta("Importe Total a Pagar")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(150).cveVariable("boton").etiqueta("Descarga")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);
		
		List<Object> listDto = new ArrayList<Object>();
		List<CrDeclaracionProvisional> listEntity = new ArrayList<CrDeclaracionProvisional>();
		
		if (rfc.isEmpty()) {
			listEntity = crDeclaracionProvisionalRepository
					.getDeclaracionProvisionalFiltrado(session.getCveEntidad(), fechaInicio, fechaFin);
		} else {
			listEntity = crDeclaracionProvisionalRepository
					.getDeclaracionProvisionalFiltrados(session.getCveEntidad(), fechaInicio, fechaFin, rfc);			
		}

				
		log.info(">>>>>>>>>>>>>>>>>>>> listEntity:" + listEntity.size());
		if (listEntity.size() > 0) {
			listDto.addAll(listEntity.stream().map(ICrDeclaracionProvicionalConverter.converterEntityToDTO)
					.collect(Collectors.toList()));
		}

		PdfGridTO result = PdfGridTO.builder().cells(listDto).columns(columns).build();

		return result;
	}

	@Override
	public PdfGridTO execPagoBancario(DatosAutenticacionTO session, Date fechaLimiteInferior, Date fechaLimiteSuperior, String rfc)
	        throws BrioBPMException {
		log.info(">>>>>>>>>>>>>>>>>>>> execPagoBancario <<<<<<<<<<<<<<<<<<");

		List<Date> limitesFecha = obtieneRangoFechas(fechaLimiteInferior, fechaLimiteSuperior);
		
		Date fechaInicio = limitesFecha.get(0);
		Date fechaFin = limitesFecha.get(1);
		
		List<Properties> columns = new ArrayList<Properties>();

		Properties column = Properties.builder().width(300).cveVariable("situacionCarga").etiqueta("Estatus de Carga")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("fechaCarga").etiqueta("Fecha de Carga")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("fechaPago").etiqueta("Fecha de pago")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(500).cveVariable("lineaCaptura").etiqueta("Linea de Captura")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);
		
		column = Properties.builder().width(200).cveVariable("descarga").etiqueta("Descarga Documento")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(150).cveVariable("boton").etiqueta("Descarga")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);
		
		List<Object> listDto = new ArrayList<Object>();
		
		List<CrPagoBancario> listEntity = new ArrayList<CrPagoBancario>();
		
		if (rfc.isEmpty()) {
			listEntity = crPagoBancarioRepository.getCargaPagoBancarioFiltrado(session.getCveEntidad(), fechaInicio, fechaFin);
		} else {
			listEntity = crPagoBancarioRepository.getCargaPagoBancarioFiltrados(session.getCveEntidad(), fechaInicio, fechaFin, rfc);			
		}
		
		log.info(">>>>>>>>>>>>>>>>>>>> listEntity:" + listEntity.size());
		if (listEntity.size() > 0) {
			listDto.addAll(listEntity.stream().map(ICrPagoBancarioConverter.converterEntityToDTO)
					.collect(Collectors.toList()));
		}

		PdfGridTO result = PdfGridTO.builder().cells(listDto).columns(columns).build();

		return result;

	}

	@Override
	public PdfGridTO execRegistroObra(DatosAutenticacionTO session, Date fechaLimiteInferior, Date fechaLimiteSuperior, String rfc)
	        throws BrioBPMException {
		log.info(">>>>>>>>>>>>>>>>>>>> execRegistroObra <<<<<<<<<<<<<<<<<<");

		List<Date> limitesFecha = obtieneRangoFechas(fechaLimiteInferior, fechaLimiteSuperior);
		
		Date fechaInicio = limitesFecha.get(0);
		Date fechaFin = limitesFecha.get(1);
		
		List<Properties> columns = new ArrayList<Properties>();

		Properties column = Properties.builder().width(300).cveVariable("situacionCarga").etiqueta("Estatus de Carga")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("fechaCarga").etiqueta("Fecha de Carga")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("razonSocial").etiqueta("Razón Social")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("registroPatronal").etiqueta("Registro Patronal")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("numeroRepse").etiqueta("Número REPSE")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("registroObra").etiqueta("Registro Obra")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("montoObra").etiqueta("Monto").tipoDato("ALFANUMERICO")
				.visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(400).cveVariable("ubicacionObra").etiqueta("Ubicación de la obra")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("fechaRegistro").etiqueta("Fecha de registro")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(150).cveVariable("boton").etiqueta("Descarga")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);
		
		List<Object> listDto = new ArrayList<Object>();
		
		List<CrRegistroObra> listEntity = new ArrayList<CrRegistroObra>();
		
		if (rfc.isEmpty()) {
			listEntity = crRegistroObraRepository.getCargaRegistroObraFiltrado(
					session.getCveEntidad(), fechaInicio, fechaFin);
		} else {
			listEntity = crRegistroObraRepository
					.getCargaRegistroObraFiltrados(session.getCveEntidad(), fechaInicio, fechaFin, rfc);			
		}
		
		
		log.info(">>>>>>>>>>>>>>>>>>>> listEntity:" + listEntity.size());
		if (listEntity.size() > 0) {
			listDto.addAll(listEntity.stream().map(ICrRegistroObraConverter.converterEntityToDTO)
					.collect(Collectors.toList()));
		}

		PdfGridTO result = PdfGridTO.builder().cells(listDto).columns(columns).build();

		return result;
	}

	@Override
	public PdfGridTO execComprobanteCuotaOP(DatosAutenticacionTO session, Date fechaLimiteInferior, Date fechaLimiteSuperior, String rfc)
	        throws BrioBPMException {
		log.info(">>>>>>>>>>>>>>>>>>>> execComprobanteCuotaOP <<<<<<<<<<<<<<<<<<");

		List<Date> limitesFecha = obtieneRangoFechas(fechaLimiteInferior, fechaLimiteSuperior);
		
		Date fechaInicio = limitesFecha.get(0);
		Date fechaFin = limitesFecha.get(1);
		
		List<Properties> columns = new ArrayList<Properties>();

		Properties column = Properties.builder().width(300).cveVariable("situacionCarga").etiqueta("Estatus de Carga")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("fechaCarga").etiqueta("Fecha de Carga")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("razonSocial").etiqueta("Razón Social")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("registroPatronal").etiqueta("Registro Patronal")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("fechaPago").etiqueta("Fecha de pago")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("periodoPagoinfonavit")
				.etiqueta("Periodo de pago INFONAVIT").tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("periodoPagoimss").etiqueta("Periodo de pago IMSS")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(150).cveVariable("boton").etiqueta("Descarga")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);
		
		List<Object> listDto = new ArrayList<Object>();
		
		List<CrComprobanteCuotasOP> listEntity = new ArrayList<CrComprobanteCuotasOP>();
		
		if (rfc.isEmpty()) {
			listEntity = crComprobanteCuotaOPRepository.getCargaComprobanteCuotaOPFiltrado(
					session.getCveEntidad(), fechaInicio, fechaFin);
		} else {
			listEntity = crComprobanteCuotaOPRepository.getCargaComprobanteCuotaOPFiltrados(
					session.getCveEntidad(), fechaInicio, fechaFin, rfc);			
		}
		
		log.info(">>>>>>>>>>>>>>>>>>>> listEntity:" + listEntity.size());
		if (listEntity.size() > 0) {
			listDto.addAll(listEntity.stream().map(ICrComprobanteCuotaOPConverter.converterEntityToDTO)
					.collect(Collectors.toList()));
		}

		PdfGridTO result = PdfGridTO.builder().cells(listDto).columns(columns).build();

		return result;
	}

	@Override
	public PdfGridTO execFormatoCuotaOP(DatosAutenticacionTO session, Date fechaLimiteInferior, Date fechaLimiteSuperior, String rfc)
	        throws BrioBPMException {
		log.info(">>>>>>>>>>>>>>>>>>>> execFormatoCuotaOP <<<<<<<<<<<<<<<<<<");

		List<Date> limitesFecha = obtieneRangoFechas(fechaLimiteInferior, fechaLimiteSuperior);
		
		Date fechaInicio = limitesFecha.get(0);
		Date fechaFin = limitesFecha.get(1);
		
		List<Properties> columns = new ArrayList<Properties>();

		Properties column = Properties.builder().width(300).cveVariable("situacionCarga").etiqueta("Estatus de Carga")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("fechaCarga").etiqueta("Fecha de Carga")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("razonSocial").etiqueta("Razón Social")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("registroPatronal").etiqueta("Registro Patronal")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("fechaPago").etiqueta("Fecha de pago")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("periodoPagoInfonavit")
				.etiqueta("Periodo de pago INFONAVIT").tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("periodoPagoImss").etiqueta("Periodo de pago IMSS")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(150).cveVariable("boton").etiqueta("Descarga")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);
		
		List<Object> listDto = new ArrayList<Object>();
			
		List<CrFormatoCuotasOP> listEntity = new ArrayList<CrFormatoCuotasOP>();
		
		if (rfc.isEmpty()) {
			listEntity = crFormatoCuotaOPRepository.getCargaFormatoCuotasOPFiltrado(
					session.getCveEntidad(), fechaInicio, fechaFin);
		} else {
			listEntity = crFormatoCuotaOPRepository.getCargaFormatoCuotasOPFiltrados(
					session.getCveEntidad(), fechaInicio, fechaFin, rfc);			
		}
		
		log.info(">>>>>>>>>>>>>>>>>>>> listEntity:" + listEntity.size());
		if (listEntity.size() > 0) {
			listDto.addAll(listEntity.stream().map(ICrFormatoCuotaOPConverter.converterEntityToDTO)
					.collect(Collectors.toList()));
		}

		PdfGridTO result = PdfGridTO.builder().cells(listDto).columns(columns).build();

		return result;
	}

	@Override
	public PdfGridTO execSUA(DatosAutenticacionTO session, Date fechaLimiteInferior, Date fechaLimiteSuperior, String rfc)
	        throws BrioBPMException {

		log.info(">>>>>>>>>>>>>>>>>>>> execSUA <<<<<<<<<<<<<<<<<<");
		
		List<Date> limitesFecha = obtieneRangoFechas(fechaLimiteInferior, fechaLimiteSuperior);
		
		Date fechaInicio = limitesFecha.get(0);
		Date fechaFin = limitesFecha.get(1);
		
		List<Properties> columns = new ArrayList<Properties>();

		Properties column = Properties.builder().width(200).cveVariable("situacionCarga").etiqueta("Estatus de Carga")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("fechaCarga").etiqueta("Fecha de Carga")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("razonSocial").etiqueta("Razón Social")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("registroPatronal").etiqueta("Registro Patronal")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("nssTrabajador").etiqueta("Nss Trabajador")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("nombreTrabajador").etiqueta("Nombre Trabajador")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("rfcCurpTrabajador").etiqueta("RFC CURP Trabajador")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("salarioBaseCotizacion")
				.etiqueta("Salario Base Cotización").tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("periodo").etiqueta("Periodo").tipoDato("ALFANUMERICO")
				.visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(150).cveVariable("boton").etiqueta("Descarga")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);
		
		log.info("columnas " + columns.size());

		List<Object> listDto = new ArrayList<Object>();
		 
		List<CrCedulaDetCuotas> listEntity = new ArrayList<CrCedulaDetCuotas>();
		
		if (rfc.isEmpty()) {
			listEntity = crCedulaDetCuotasRepository.getCrCedulaDetCuotasFiltrado(
					session.getCveEntidad(), fechaInicio, fechaFin);
		} else {
			listEntity = crCedulaDetCuotasRepository.getCrCedulaDetCuotasFiltrados(
					session.getCveEntidad(), fechaInicio, fechaFin, rfc);			
		}
		
		log.info(">>>>>>>>>>>>>>>>>>>> listEntity:" + listEntity.size());
		if (listEntity.size() > 0) {
			listDto.addAll(listEntity.stream().map(ICrCedulaDetCuotasConverter.converterEntityToDTO)
					.collect(Collectors.toList()));
		}

		PdfGridTO result = PdfGridTO.builder().cells(listDto).columns(columns).build();

		return result;
	}

	@Override
	public PdfGridTO execAvisoRepse(DatosAutenticacionTO session, Date fechaLimiteInferior, Date fechaLimiteSuperior, String rfc)
	        throws BrioBPMException {
		log.info(">>>>>>>>>>>>>>>>>>>> execConsultaRepse <<<<<<<<<<<<<<<<<<");

		List<Date> limitesFecha = obtieneRangoFechas(fechaLimiteInferior, fechaLimiteSuperior);
		
		Date fechaInicio = limitesFecha.get(0);
		Date fechaFin = limitesFecha.get(1);
		
		List<Properties> columns = new ArrayList<Properties>();

		Properties column = Properties.builder().width(200).cveVariable("razonSocial").etiqueta("Razón Social")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("folio").etiqueta("Folio").tipoDato("ALFANUMERICO")
				.visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("entidadMunicipio").etiqueta("Entidad Municipio")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("avisoFechaRegistro").etiqueta("Aviso Fecha Registro")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("imagenConsulta").etiqueta("Imagen Consulta")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column); // queda pendiente

		column = Properties.builder().width(200).cveVariable("descripcionServicio").etiqueta("Descripcion Servicio")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(150).cveVariable("boton").etiqueta("Descarga")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);
		
		List<Object> listDto = new ArrayList<Object>();
		
		List<CrConsultaRepse> listEntity = new ArrayList<CrConsultaRepse>();
		
		if (rfc.isEmpty()) {
			listEntity = crConsultaRepseRepository.getAvisoRepseFiltrado(session.getCveEntidad(), fechaInicio, fechaFin);
		} else {
			listEntity = crConsultaRepseRepository.getAvisoRepseFiltrados(
					session.getCveEntidad(), fechaInicio, fechaFin, rfc);			
		}
		
		if (listEntity.size() > 0) {
			listDto.addAll(listEntity.stream().map(IRepseConverter.converterEntityToDTO).collect(Collectors.toList()));
		}

		PdfGridTO result = PdfGridTO.builder().cells(listDto).columns(columns).build();

		return result;
	}

	@Override
	public PdfGridTO execDeclaracionProvicionalContratistaComplementario(DatosAutenticacionTO session, Date fechaLimiteInferior, 
			Date fechaLimiteSuperior, String rfc) throws BrioBPMException {
		log.info(">>>>>>>>>>>>>>>>>>>> execDeclaracionProvicionalContratistaComplementario <<<<<<<<<<<<<<<<<<");

		List<Date> limitesFecha = obtieneRangoFechas(fechaLimiteInferior, fechaLimiteSuperior);
		
		Date fechaInicio = limitesFecha.get(0);
		Date fechaFin = limitesFecha.get(1);
		
		List<Properties> columns = new ArrayList<Properties>();

		Properties column = Properties.builder().width(300).cveVariable("situacionCarga").etiqueta("Estatus de Carga")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("fechaCarga").etiqueta("Fecha de Carga")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("rfc").etiqueta("RFC").tipoDato("ALFANUMERICO")
				.visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("razonSocial").etiqueta("Razón Social")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("tipoDeclaracion").etiqueta("Tipo de Declaración")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("periodoDeclaracion").etiqueta("Período de la Declaración")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("ejercicio").etiqueta("Ejercicio").tipoDato("ALFANUMERICO")
				.visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("fechaHoraPresentacion")
				.etiqueta("Fecha y Hora de Presentación").tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(500).cveVariable("lineaCaptura").etiqueta("Línea de Captura")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(500).cveVariable("conceptoPago").etiqueta("Concepto de Pago")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("importeApagar").etiqueta("Importe Total a Pagar")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(150).cveVariable("boton").etiqueta("Descarga")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);
		
		List<Object> listDto = new ArrayList<Object>();

		List<CrDeclaracionComplementario> listEntity = new ArrayList<CrDeclaracionComplementario>();
		
		if (rfc.isEmpty()) {
			listEntity = crDeclaracionComplementarioRepository
					.getDeclaracionComplementarioFiltrado(session.getCveEntidad(), fechaInicio, fechaFin);
		} else {
			listEntity = crDeclaracionComplementarioRepository
					.getDeclaracionComplementarioFiltrados(session.getCveEntidad(), fechaInicio, fechaFin, rfc);			
		}
				
		log.info(">>>>>>>>>>>>>>>>>>>> listEntity:" + listEntity.size());
		if (listEntity.size() > 0) {
			listDto.addAll(listEntity.stream().map(ICrDeclaracionComplementarioConverter.converterEntityToDTO)
					.collect(Collectors.toList()));
		}

		PdfGridTO result = PdfGridTO.builder().cells(listDto).columns(columns).build();

		return result;
	}
	
	public static Date getInicioDelDia(Date fecha) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getFinDelDia(Date fecha) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }
    @Override
    public List<Date> obtieneRangoFechas(Date fechaLimiteInferior, Date fechaLimiteSuperior) {
        
        // Calculamos el inicio del día para la fecha límite inferior y el fin del día para la fecha límite superior
        Date inicioDelDia = getInicioDelDia(fechaLimiteInferior);
        Date finDelDia = getFinDelDia(fechaLimiteSuperior);

        // Creamos la lista para almacenar ambas fechas
        List<Date> rangoFechas = new ArrayList<>();
        rangoFechas.add(inicioDelDia);
        rangoFechas.add(finDelDia);

        return rangoFechas;
    }

	@Override
	public PdfGridTO execAvisoSisub(DatosAutenticacionTO session, Date fechaLimiteInferior, Date fechaLimiteSuperior, String rfc) {
		log.info(">>>>>>>>>>>>>>>>>>>> execAvisoSisub <<<<<<<<<<<<<<<<<<");

		List<Date> limitesFecha = obtieneRangoFechas(fechaLimiteInferior, fechaLimiteSuperior);
		
		Date fechaInicio = limitesFecha.get(0);
		Date fechaFin = limitesFecha.get(1);
		
		List<Properties> columns = new ArrayList<Properties>();

		Properties column = Properties.builder().width(300).cveVariable("situacionCarga").etiqueta("Estatus de Carga")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);
		
		column = Properties.builder().width(200).cveVariable("fechaCarga").etiqueta("Fecha de Carga")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);
		
		column = Properties.builder().width(200).cveVariable("fechaDocumento").etiqueta("RFC").tipoDato("Fecha")
				.visible("SI").build();
		columns.add(column);
		
		column = Properties.builder().width(200).cveVariable("rfc").etiqueta("RFC").tipoDato("ALFANUMERICO")
				.visible("SI").build();
		columns.add(column);
		
		column = Properties.builder().width(200).cveVariable("razonSocial").etiqueta("Razon Social")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("numeroRepse").etiqueta("Número REPSE")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("cuatrimestre").etiqueta("Cuatrimestre")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(150).cveVariable("boton").etiqueta("Descarga")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);
		
		List<Object> listDto = new ArrayList<Object>();

		List<CrAvisoSISUB> listEntity = new ArrayList<CrAvisoSISUB>();
		
		if (rfc.isEmpty()) {
			listEntity = crAvisoSISUBRepository
					.getAvisoSISUBFiltrado(session.getCveEntidad(), fechaInicio, fechaFin);
		} else {
			listEntity =  crAvisoSISUBRepository
					.getAvisoSISUBFiltrados(session.getCveEntidad(), fechaInicio, fechaFin, rfc);			
		}
				
		log.info(">>>>>>>>>>>>>>>>>>>> listEntity:" + listEntity.size());
		if (listEntity.size() > 0) {
			listDto.addAll(listEntity.stream().map(ICrAvisoSISUBConverter.converterEntityToDTO)
					.collect(Collectors.toList()));
		}

		PdfGridTO result = PdfGridTO.builder().cells(listDto).columns(columns).build();
		
		return result;
	}

	@Override
	public PdfGridTO execAvisoIcsoe(DatosAutenticacionTO session, Date fechaLimiteInferior, Date fechaLimiteSuperior, String rfc) {
		log.info(">>>>>>>>>>>>>>>>>>>> execAvisoIcsoe <<<<<<<<<<<<<<<<<<");

		List<Date> limitesFecha = obtieneRangoFechas(fechaLimiteInferior, fechaLimiteSuperior);
		
		Date fechaInicio = limitesFecha.get(0);
		Date fechaFin = limitesFecha.get(1);
		
		List<Properties> columns = new ArrayList<Properties>();


		Properties column = Properties.builder().width(300).cveVariable("situacionCarga").etiqueta("Estatus de Carga")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);
		
		column = Properties.builder().width(200).cveVariable("fechaCarga").etiqueta("Fecha de Carga")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);
		
		column = Properties.builder().width(200).cveVariable("fechaDocumento").etiqueta("RFC").tipoDato("Fecha")
				.visible("SI").build();
		columns.add(column);
		
		column = Properties.builder().width(200).cveVariable("rfc").etiqueta("RFC").tipoDato("ALFANUMERICO")
				.visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("año").etiqueta("Año")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("cuatrimestre").etiqueta("Cuatrimestre")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(200).cveVariable("tipoInformativa").etiqueta("Tipo de Informativa")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);

		column = Properties.builder().width(150).cveVariable("boton").etiqueta("Descarga")
				.tipoDato("ALFANUMERICO").visible("SI").build();
		columns.add(column);
		
		List<Object> listDto = new ArrayList<Object>();

		List<CrAvisoICSOE> listEntity = new ArrayList<CrAvisoICSOE>();
		
		if (rfc.isEmpty()) {
			listEntity = crAvisoICSOERepository.
					getAvisoICSOEFiltrado(session.getCveEntidad(), fechaInicio, fechaFin);
		} else {
			listEntity =  crAvisoICSOERepository.
					getAvisoICSOEFiltrados(session.getCveEntidad(), fechaInicio, fechaFin, rfc);			
		}
		
				
		log.info(">>>>>>>>>>>>>>>>>>>> listEntity:" + listEntity.size());
		if (listEntity.size() > 0) {
			listDto.addAll(listEntity.stream().map(ICrAvisoICSOEConverter.converterEntityToDTO)
					.collect(Collectors.toList()));
		}

		PdfGridTO result = PdfGridTO.builder().cells(listDto).columns(columns).build();
		return result;
	}

	@Override
	public DAORet<CrPdfFiles, RetMsg> leeDocumentoBinario(DatosAutenticacionTO session, ConsultaPdfTO datos) throws BrioBPMException {
	    // Inicializa las variables de contenido del documento
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    Date fecha = null;
	    Optional<ParametroGeneral> facturas = parametroGeneralRepository.findById("DOC_FACTURAS_CFDI");
	    ParametroGeneral parGeneral = null;
        String[] docFacturas = null;
        if (facturas.isPresent()) {
        	parGeneral = facturas.get();
        	docFacturas = parGeneral.getValorAlfanumerico().split("\\|");
        }
		try {
			fecha = sdf.parse(datos.getFechaCarga()); 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			log.error("Error al crear la fecha: {}", e.getMessage());
		}
		log.error (">>>>>>>>> fecha final: {} " + fecha);
	    // Busca todos los documentos que coincidan con los criterios dados
		CrPdfFiles documentoProceso = null;
		if (Arrays.asList(docFacturas)
		        .contains(datos.getCveProceso())) {
			documentoProceso = getArchivo( datos, session);
		}
		else {
			documentoProceso = crCrPdfFilesRepository.getDocumentoByNombre(session.getCveEntidad(), datos.getCveProceso(), fecha,
				datos.getRfc(), datos.getContrato(), datos.getNombreDocumento());
		}
	    
		
	    // Construye un objeto RetMsg con el estado y mensaje de la respuesta.
	    RetMsg meta = RetMsg.builder().status("").message("").build();
		if (documentoProceso != null) {
			meta.setStatus("OK");
		} else {
			meta.setStatus("ERROR");
			meta.setMessage("Sin Archivo");
		}

			    
	    // Retorna un objeto DAORet con el documento actualizado y la meta información.
	    return new DAORet<CrPdfFiles, RetMsg>(documentoProceso, meta);

	}
	
	private CrPdfFiles  getArchivo( ConsultaPdfTO datos, DatosAutenticacionTO session) {
		List<Object[]> docs = variableProcesoRepository.getDocumentoByNombreContratoRFC(
				session.getCveEntidad(),
			    datos.getContrato(),
			    datos.getRfc(),
			    datos.getCveProceso(),			   
			    datos.getNombreDocumento()
			);

			CrPdfFiles resultado = null;
			
			for (Object[] row : docs) {
				CrPdfFiles entity = new CrPdfFiles();
			    CrPdfFilesPK pk = new CrPdfFilesPK();
			    // PK
			    pk.setCveEntidad(session.getCveEntidad());
			    pk.setCveProcesoPeriodico(datos.getCveProceso());

			    // fecha (validar null)
			   // pk.setFechaCarga(ite.getId().getFechaProgramacion());

			    pk.setSecuenciaCarga(row[3] != null ? ((Number) row[3]).intValue() : null);
			    pk.setNumeroContrato(datos.getContrato());
			    pk.setRfc(datos.getRfc());
			    pk.setTipoArchivo((String) row[4]);

			    entity.setId(pk);

			    // Datos
			    entity.setNombreDocumento((String) row[5]);
			    entity.setArchivoBinario((byte[]) row[6]);
			    
			    resultado = entity;
			    break; // Si solo se espera un resultado, se puede salir del bucle después de obtener el primero
			}
			return resultado;
	}
}
	


