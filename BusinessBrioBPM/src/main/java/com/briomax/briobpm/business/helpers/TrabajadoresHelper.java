/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.helpers;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.briomax.briobpm.business.convertes.ITrabajadorConverter;
import com.briomax.briobpm.business.convertes.ITrabajadorHistoricoConverter;
import com.briomax.briobpm.business.helpers.base.ITrabajadoresHelper;
import com.briomax.briobpm.business.service.catalogs.core.IMessagesService;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.common.exception.ConverterExcepcion;
import com.briomax.briobpm.persistence.dao.base.ITrabajadoresDAO;
import com.briomax.briobpm.persistence.entity.CrTrabajadorHistorico;
import com.briomax.briobpm.persistence.entity.CrTrabajadorHistoricoPK;
import com.briomax.briobpm.persistence.entity.InVariableProceso;
import com.briomax.briobpm.persistence.entity.InVariableProcesoPK;
import com.briomax.briobpm.persistence.entity.namedquery.DatoTrabajador;
import com.briomax.briobpm.persistence.repository.ICrCedulaDetCuotasRepository;
import com.briomax.briobpm.persistence.repository.ICrPdfFilesRepository;
import com.briomax.briobpm.persistence.repository.ICrTrabajadoresHistoricoRepository;
import com.briomax.briobpm.persistence.repository.IInVariableProcesoRepository;
import com.briomax.briobpm.transferobjects.CargaGridTO;
import com.briomax.briobpm.transferobjects.CargaTrabajadoresTO;
import com.briomax.briobpm.transferobjects.ComboBoxTO;
import com.briomax.briobpm.transferobjects.TrabajadoresTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

import lombok.extern.slf4j.Slf4j;


/**
 * El objetivo de la Class UsuariosHelper.java es ...
 * 
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Feb 25, 2020 6:59:06 PM Modificaciones:
 * @since JDK 1.8
 */
@Service
@Transactional
@Slf4j
public class TrabajadoresHelper implements ITrabajadoresHelper {

	/** El atributo o variable usuario dao. */
	@Autowired
	private ITrabajadoresDAO trabajadorDao;

	/** El atributo o variable messages service. */
	@Autowired
	private IMessagesService messagesService;
	
	/** El atributo o variable messages service. */
	@Autowired
	private ICrTrabajadoresHistoricoRepository trabajadoresHistoricoRepository;

	/** El atributo o variable messages service. */
	@Autowired
	private IInVariableProcesoRepository inVariableProcesoRepository;
	
	/** El atributo o variable. */
	@Autowired
	private ICrPdfFilesRepository cCrPdfFilesRepository;
	
	/** El atributo o variable. */
	@Autowired
	private ICrCedulaDetCuotasRepository cedulaDetCuotasRepository;
	
	/** Crear una nueva instancia del objeto usuarios helper. */
	public TrabajadoresHelper() {
	}

	@Override
	public List<TrabajadoresTO> getTrabajadorActual(DatosAutenticacionTO session, String rfc, String contrato, String instancia, String cveProces) throws BrioBPMException {
		List<TrabajadoresTO> result = new ArrayList<TrabajadoresTO>();
		List<DatoTrabajador> listEntity = new ArrayList<DatoTrabajador>();
		try {
			int ocurrenciaAnterior = 1;
			String nombre = "";
			String curp = "";
			String nss = "";
			String rfcTrabajador = "";
			log.info("rfc: {}", rfc);
			log.info("contrato: {}", contrato);
			log.info("instancia: {}", instancia);
			log.info("cveProces: {}", cveProces);
			log.info("session.getCveEntidad: {}", session.getCveEntidad());
			listEntity = trabajadorDao.obtenerTrabajadoresActivos(session.getCveEntidad(), rfc, contrato, instancia, cveProces);
			if (listEntity.size() > 0) {
				for (DatoTrabajador datoTrabajador : listEntity) {

					if (ocurrenciaAnterior != datoTrabajador.getId().getOcurrencia()) {
						TrabajadoresTO to = TrabajadoresTO.builder()
								.curp(curp)
								.nombreTrabajador(nombre)
								.seguroSocial(nss)
								.rfcTrabajador(rfcTrabajador)
								.rfc(rfc)
								.ocurrencia(ocurrenciaAnterior)
								.instancia(instancia)
								.fechaFinal("")
								.fechaFinal("")
								.build();
						result.add(to);
						nombre = "";
						curp = "";
						nss = "";
						ocurrenciaAnterior = datoTrabajador.getId().getOcurrencia();
					}
					
					if (datoTrabajador.getId().getCveVariable().equals("VPRO_01_CURP_TE") ) {
						curp = datoTrabajador.getValorAlfamerico();
					}
					
					if (datoTrabajador.getId().getCveVariable().equals("VPRO_01_NOMBRE_TRABAJADOR_TE") ) {
						nombre = datoTrabajador.getValorAlfamerico();
					}
					
					if (datoTrabajador.getId().getCveVariable().equals("VPRO_01_NSS_TE") ) {
						nss = datoTrabajador.getValorAlfamerico();
					}
					
					if (datoTrabajador.getId().getCveVariable().equals("VPRO_01_RFC_CTA_TRAB_ESP") ) {
						rfcTrabajador = datoTrabajador.getValorAlfamerico();
					}
				}

				TrabajadoresTO to = TrabajadoresTO.builder()
						.curp(curp)
						.nombreTrabajador(nombre)
						.seguroSocial(nss)
						.rfcTrabajador(rfcTrabajador)
						.rfc(rfc)
						.ocurrencia(ocurrenciaAnterior)
						.instancia(instancia)
						.fechaFinal("")
						.fechaFinal("")
						.build();
				result.add(to);
			}

			//result.addAll(listEntity.stream().map(ITrabajadorConverter.converterEntityToDTO).collect(Collectors.toList()));
			log.info("<<<<<<<<<<< numero de registros result: {}", result.size());
		} catch (DataAccessException exData) {
			log.error("###DATA-ERROR### : {}  ", exData.getMessage());
			throw new BrioBPMException(messagesService.getMessage(session, "IN_VARIABLE_PROCESO",
					"MANTENIMIENTO_TRABAJADORESACTIVOS_CONSULTAR_ERROR", ""), exData);
		}
		return result;
	}

	@Override
	public List<TrabajadoresTO> getTrabajadorHistorico(DatosAutenticacionTO session, String rfc, String contrato, String fecha, String cveProces)
			throws BrioBPMException {
		List<TrabajadoresTO> registrosTO = new ArrayList<TrabajadoresTO>();
		try {
			List<CrTrabajadorHistorico> listEntity = trabajadoresHistoricoRepository.obtieneTrabajo(session.getCveEntidad(), cveProces, rfc, contrato, fecha);

			registrosTO.addAll(listEntity.stream().map(ITrabajadorHistoricoConverter.converterEntityToDTO).collect(Collectors.toList()));

		} catch (DataAccessException exData) {
			log.error("###DATA-ERROR### : {}  ", exData.getMessage());
			throw new BrioBPMException(messagesService.getMessage(session, "IN_VARIABLE_PROCESO",
					"MANTENIMIENTO_TRABAJADORESACTIVOS_CONSULTAR_ERROR", ""), exData);
		}
		return registrosTO;
	}

	@Override
	public CargaGridTO upload(DatosAutenticacionTO userSession, InputStream file, Integer numberOfSheet, 
			String contratoOrigen, String rfcOrigen)
			throws IOException, ConverterExcepcion, BrioBPMException {
        
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		if (numberOfSheet == null || numberOfSheet < 0) {
			numberOfSheet = workbook.getNumberOfSheets();
		}
		CargaTrabajadoresTO iteExcelto = null;
		List<CargaTrabajadoresTO> listaDatos = new ArrayList<CargaTrabajadoresTO>();

		XSSFSheet sheet = workbook.getSheetAt(0);
		
		// Create a DataFormatter to format and get each cell's value as String
		DataFormatter dataFormatter = new DataFormatter();

		Iterator<Row> rowIterator = sheet.rowIterator();
		List<String> encontradoCurp = new ArrayList<>();
		List<String> encontradoNombre = new ArrayList<>();
		List<String> encontradoRfc= new ArrayList<>();
		int iterator = 0;
		boolean actLectura = true;
		String errorGeneral = "OK";
		String descripcionGeneral = "";
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		//Valida si hay archivos cargados
		Date fechaCarga = cCrPdfFilesRepository.getMaximaFecha(userSession.getCveEntidad(),
				"SISTEMA_UNICO_AUTODETERMINACION", rfcOrigen, contratoOrigen);
		
		while (rowIterator.hasNext()) {
				//log.error("iterator: ", iterator);
				Row row = rowIterator.next();
				//log.error("row: ", row.getRowNum());
				// Now let's iterate over the columns of the current row
				if (iterator > 0 && actLectura) {
					Iterator<Cell> cellIterator = row.cellIterator();
					String rfc = "";
					String contrato = "";
					String nombre = "";
					String curp = "";
					String rfcTrabajador = "";
					String salario = "";
					double salarioBase = 1;
					String seguroSocial = "";
					String facheInicio = "";
					Date fecIni = null;
					String fechaFinal = "";
					Date fecFin = null;
					boolean requerido = true;					
					//Date fecActual = new Date();
					String desError = "OK";
					boolean error = false;
					
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						String cellValue = dataFormatter.formatCellValue(cell);
						switch (cell.getColumnIndex()) {
						case 0:
							rfc = cellValue;							
							if (rfc.isEmpty()) {
								requerido = false;
							}
							break;
						case 1:
							contrato = cellValue;
							if (contrato.isEmpty()) {
								requerido = false;
							}
							break;
						case 2:
							nombre = cellValue;
							if (nombre.isEmpty()) {
								requerido = false;
							} else {
								nombre = cellValue.trim();
							}
							break;
						case 3:
							curp = cellValue;
							if (curp.isEmpty()) {
								requerido = false;
							} else {
								curp = cellValue.trim();
							}
							break;
						case 4:
							rfcTrabajador = cellValue;
							if (rfcTrabajador.isEmpty()) {
								requerido = false;
							} else {
								rfcTrabajador = cellValue.trim();
							}
							/*if (!salario.isEmpty()) {
								salarioBase = cell.getNumericCellValue();
							}*/
							break;
						case 5:
							seguroSocial = cellValue;
							if (seguroSocial.isEmpty()) {
								requerido = false;
							} else {
								seguroSocial = cellValue.trim();
							}
							break;
						/*case 6:
							facheInicio = cellValue;
							if (facheInicio.isBlank()) {
								requerido = false;
							} else {
								fecIni = cell.getDateCellValue();
								facheInicio = sdf.format(fecIni);
							}
							
							break;
						case 7:
							fechaFinal = cellValue;
							if (fechaFinal.isBlank()) {
								requerido = false;
							} else {
								fecFin = cell.getDateCellValue();
								fechaFinal = sdf.format(fecFin);
							}					
							break;*/							
						default:
							break;
						}
						
					}
					
					if (requerido) {
						if(!rfcOrigen.trim().equals(rfc.trim())) {
							error = true;
							desError = "El RFC no es igual al del contratistas.";
						}
						

						if (!contratoOrigen.trim().equals(contrato.trim())) {
							if (error) {
								desError = desError + " El CONTRATO no pertenece a la carga asignada.";
							} else {
								log.debug("entre en diferencia de contrato");
								desError = "El CONTRATO no pertenece a la carga asignada.";
								error = true;	
							}
						}
						
			            /*if (fecIni.before(fecFin)) {
			            	//desError = "OK";
			            	if (fecIni.before(fecActual)) {
			            		if (error) {
			            			desError = desError + " La FECHA INICIAL CONTRATO no puede ser menor que la Fecha Actual.";
			            		} else {
			            			desError = "La FECHA INICIAL CONTRATO no puede ser menor que la Fecha Actual.";
			            			error = true;
			            		}
			            		
			            	}
			            } else if (fecIni.after(fecFin)) {
			            	if (error) {
			            		desError = desError + " La FECHA FINAL CONTRATO no es correcta.";
			            	} else {
			            		desError = "La FECHA FINAL CONTRATO no es correcta.";
			            		error = true;
			            	}
			            	
			            } else {
			            	if (error) {
			            		desError = desError + " La FECHA INICIAL y FINAL del CONTRATO son iguales.";
			            	} else {
			            		desError = "La FECHA INICIAL y FINAL del CONTRATO son iguales.";
			            		error = true;
			            	}
			            }*/
						
						if (encontradoCurp.contains(curp) || encontradoNombre.contains(nombre) || encontradoRfc.contains(rfcTrabajador)) {
							if (error) {
								desError = desError + " El NOMBRE ó CURP ó RFC ya exite en otro renglo.";
							} else {
			            		desError = "El NOMBRE ó CURP ó RFCC ya exite en otro renglo.";
			            		error = true;
			            	}	
						
						} 
						
						encontradoCurp.add(curp);
						encontradoNombre.add(nombre);
						encontradoRfc.add(rfcTrabajador);
						//SVM nueva validaon de trabajadores contra documento
						if (fechaCarga != null) {							
							if (validaNoExisteTrabajador (userSession, contratoOrigen, rfcOrigen,  nombre, curp, rfcTrabajador, seguroSocial)) {
								
								if (error) {
									desError = desError + "El trabajador NO existe en el archivo de SUA.";
								} else {
									desError = "El trabajador NO existe en el archivo de SUA.";
									error = true;									
								}
							}
						}
						
						if (error) {
							descripcionGeneral = "El archivo cargado tiene ERRORES";
						}
						
					} else {
						desError = "Todos los datos son requeridos.";
						error = true;
					}
					
					if (!rfc.isEmpty() && !contrato.isEmpty() && !nombre.isEmpty() ) {
						iteExcelto = CargaTrabajadoresTO.builder()
								.rfc(rfc)
								.contrato(contrato)
								.nombreTrabajador(nombre)
								.curp(curp)
								.rfcTrabajador(rfcTrabajador)
								.salario(salario)
								.salarioBase(salarioBase)
								.seguroSocial(seguroSocial)
								.fechaInicial(facheInicio)
								.fechaIni(fecIni)
								.fechaFinal(fechaFinal)
								.fechaFin(fecFin)
								.desError(desError).build();
						listaDatos.add(iteExcelto);
						
						if (!iteExcelto.getDesError().equals("OK")) {
							errorGeneral = "ERROR";
							descripcionGeneral = "Error en los registros.";
						}
					}
					
				} 

				if (iterator == 0) {
					Iterator<Cell> cellIterator = row.cellIterator();
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						String cellValue = dataFormatter.formatCellValue(cell);
						log.info("xxxxxxxxxxxxxxxxxcellValue: " + cellValue);
						String cabecera = "";
						switch (cell.getColumnIndex()) {
						case 0:
							cabecera = cellValue.trim();
							if (!cabecera.equals("RFC CONTRATISTA")){
								actLectura = false;
							}
							break;
						case 1:
							cabecera = cellValue.trim();
							if (!cabecera.equals("NUMERO CONTRATO")){
								actLectura = false;
							}
							break;
						case 2:
							cabecera = cellValue.trim();
							if (!cabecera.equals("NOMBRE TRABAJADOR")){
								actLectura = false;
							}
							break;
						case 3:
							cabecera = cellValue.trim();
							if (!cabecera.equals("CURP TRABAJADOR")){
								actLectura = false;
							}
							break;
						case 4:
							cabecera = cellValue.trim();
							if (!cabecera.equals("RFC TRABAJADOR")){
								actLectura = false;
							}
							break;
						case 5:
							cabecera = cellValue.trim();
							if (!cabecera.equals("NUMERO SEGURIDAD_SOCIAL")){
								actLectura = false;
							}
							break;
						/*case 6:
							cabecera = cellValue.trim();
							if (!cabecera.equals("FECHA INICIAL CONTRATO (DD/MM/YYYY)")){
								actLectura = false;
							}							
							break;
						case 7:
							cabecera = cellValue.trim();
							if (!cabecera.equals("FECHA FINAL CONTRATO (DD/MM/YYYY)")){
								actLectura = false;
							}
							break;*/							
						default:
							break;
						}
					}

					if (!actLectura) {
						errorGeneral = "ERROR";
						descripcionGeneral = "Error el formato del Excel no es el correcto.";
					}
				}
				iterator++;
			
			}
		

		if (fechaCarga != null) {
			Integer traArchivo =  cedulaDetCuotasRepository.getNumTrabajadores(userSession.getCveEntidad(), "SISTEMA_UNICO_AUTODETERMINACION", rfcOrigen, contratoOrigen);
			log.info(" >>>>>>>>>>>>>>>>> traArchivo: {}", traArchivo);
			if  (iterator < traArchivo) {
				errorGeneral = "ERROR";
				descripcionGeneral = "Error los trabajadores que se ingresaran son menos de los que tiene el archivo SUA.";
			}
			if  (iterator > traArchivo) {
				errorGeneral = "ERROR";
				descripcionGeneral = "Error los trabajadores que se ingresaran son más de los que tiene el archivo SUA.";
			}
						
		}

		workbook.close();
		CargaGridTO result = CargaGridTO.builder()
				.grid(listaDatos)
				.estatus(errorGeneral)
				.descripcion(descripcionGeneral)
				.build();
		
		return result;
	}
	
	@Override
	public String actualizaTrabajadores(DatosAutenticacionTO userSession, CargaGridTO datosExcel) {
		String estatus = "OK";
		
		List<DatoTrabajador> listEntity = new ArrayList<DatoTrabajador>();
		listEntity = trabajadorDao.obtenerTrabajadoresActivos(userSession.getCveEntidad(), datosExcel.getRfc(), datosExcel.getContrato(), datosExcel.getInstancia(), datosExcel.getProceso());
		Date fechaCarga = new Date();
		List<CrTrabajadorHistorico> listInt  = new ArrayList<CrTrabajadorHistorico>();
		try {
			//respaldo trabajadores
			if (listEntity.size() > 0) {
				
				int ocurrenciaAnterior = 1;
				String nombre = "";
				String curp = "";
				String nss = "";
				String rfcTrabajador = "";
				
				for (DatoTrabajador datoTrabajador : listEntity) {
					log.error("1. datoTrabajador: {}",datoTrabajador);
					
					if (ocurrenciaAnterior != datoTrabajador.getId().getOcurrencia()) {
						log.error("2. ocurrenciaAnterior: {}",ocurrenciaAnterior);
						CrTrabajadorHistoricoPK id = CrTrabajadorHistoricoPK.builder()
								.cveEntidad(userSession.getCveEntidad())
								.numContrato(datosExcel.getContrato())
								.rfcContratista(datosExcel.getRfc())
								.cveProceso(datosExcel.getProceso())
								.fechaCarga(fechaCarga)
								.curp(curp)
								.build();
						
						CrTrabajadorHistorico entity = CrTrabajadorHistorico.builder()
								.id(id)
								.nombreTrabajador(nombre)	
								.rfcTrabajador(rfcTrabajador)
								.seguroSocial(nss)
								.build();		
						
						listInt.add(entity);
						log.error("3. entity: {}",entity);
						trabajadoresHistoricoRepository.saveAndFlush(entity);
						ocurrenciaAnterior = datoTrabajador.getId().getOcurrencia();
						nombre = "";
						curp = "";
						nss = "";
						rfcTrabajador = "";
					}
					
					if (datoTrabajador.getId().getCveVariable().equals("VPRO_01_CURP_TE") ) {
						curp = datoTrabajador.getValorAlfamerico();
					}
					
					if (datoTrabajador.getId().getCveVariable().equals("VPRO_01_NOMBRE_TRABAJADOR_TE") ) {
						nombre = datoTrabajador.getValorAlfamerico();
					}
					
					if (datoTrabajador.getId().getCveVariable().equals("VPRO_01_NSS_TE") ) {
						nss = datoTrabajador.getValorAlfamerico();
					}
					if (datoTrabajador.getId().getCveVariable().equals("VPRO_01_RFC_CTA_TRAB_ESP") ) {
						rfcTrabajador = datoTrabajador.getValorAlfamerico();
					}				
					
				}
				
				CrTrabajadorHistoricoPK id = CrTrabajadorHistoricoPK.builder()
						.cveEntidad(userSession.getCveEntidad())
						.numContrato(datosExcel.getContrato())
						.rfcContratista(datosExcel.getRfc())
						.cveProceso(datosExcel.getProceso())
						.fechaCarga(fechaCarga)
						.curp(curp)
						.build();
				
				CrTrabajadorHistorico entity = CrTrabajadorHistorico.builder()
						.id(id)
						.nombreTrabajador(nombre)	
						.rfcTrabajador(rfcTrabajador)
						.seguroSocial(nss)
						.build();		
				log.error("4. entity: {}",entity);
				listInt.add(entity);
				
				trabajadoresHistoricoRepository.saveAndFlush(entity);
				log.error("5. saveAndFlush: {}");
				
				/*for (DatoTrabajador datoTrabajador : listEntity) {
	
					CrTrabajadorHistoricoPK id = CrTrabajadorHistoricoPK.builder()
							.cveEntidad(userSession.getCveEntidad())
							.numContrato(datoTrabajador.getId().getNumContrato())
							.rfcContratista(datosExcel.getRfc())
							.cveProceso(datosExcel.getProceso())
							.fechaCarga(fechaCarga)
							.curp(datoTrabajador.getId().getCurp())
							.build();
					
					CrTrabajadorHistorico entity = CrTrabajadorHistorico.builder()
							.id(id)
							.nombreTrabajador(datoTrabajador.getNombreTrabajador())						
							//.salarioBase(datoTrabajador.getSalarioBase())
							.seguroSocial(datoTrabajador.getSeguroSocial())
							//.fechaInicial(datoTrabajador.getFechaInicial())
							//.fechaFinal(datoTrabajador.getFechaFinal())
							.build();		
					
					listInt.add(entity); 				
					trabajadoresHistoricoRepository.saveAndFlush(entity);
					
				}*/
				log.error("5.1 deleteTrabajadoresProceso: {} - {} - {}",userSession.getCveEntidad(), 
						datosExcel.getProceso(), datosExcel.getInstancia());
				inVariableProcesoRepository.deleteTrabajadoresProceso(userSession.getCveEntidad(), 
						datosExcel.getProceso(), datosExcel.getInstancia());
				log.error("6. deleteTrabajadoresProceso: {}");
			}
			
			//inserto nuevos trabajadores
			List<CargaTrabajadoresTO> list = datosExcel.getGrid();
			int ocurrencia = 1;
			for (CargaTrabajadoresTO trabajador : list) {
				//InVariableProceso
				log.error("7. trabajador: {}", trabajador);
				InVariableProcesoPK pk = InVariableProcesoPK.builder()
							.cveEntidad(userSession.getCveEntidad())
							.cveInstancia(datosExcel.getInstancia())
							.cveProceso(datosExcel.getProceso())
							.cveVariable("VPRO_01_NOMBRE_TRABAJADOR_TE")
							.secuenciaValor(1)
							.ocurrencia(ocurrencia)
							.version(new BigDecimal(1.0))
							.build();
				
				InVariableProceso entity = InVariableProceso.builder()
							.id(pk)
							.valorAlfanumerico(trabajador.getNombreTrabajador())
							.build();
				inVariableProcesoRepository.saveAndFlush(entity);
				log.error("8. saveAndFlush: ");
				pk = InVariableProcesoPK.builder()
						.cveEntidad(userSession.getCveEntidad())
						.cveInstancia(datosExcel.getInstancia())
						.cveProceso(datosExcel.getProceso())
						.cveVariable("VPRO_01_CURP_TE")
						.secuenciaValor(1)
						.ocurrencia(ocurrencia)
						.version(new BigDecimal(1.0))
						.build();
			
				entity = InVariableProceso.builder()
						.id(pk)
						.valorAlfanumerico(trabajador.getCurp())
						.build();
				inVariableProcesoRepository.saveAndFlush(entity);
				log.error("9. saveAndFlush: {}");
				pk = InVariableProcesoPK.builder()
						.cveEntidad(userSession.getCveEntidad())
						.cveInstancia(datosExcel.getInstancia())
						.cveProceso(datosExcel.getProceso())
						.cveVariable("VPRO_01_RFC_CTA_TRAB_ESP")
						.secuenciaValor(1)
						.ocurrencia(ocurrencia)
						.version(new BigDecimal(1.0))
						.build();
			
				entity = InVariableProceso.builder()
						.id(pk)
						.valorAlfanumerico(trabajador.getRfcTrabajador())
						.build();
				inVariableProcesoRepository.saveAndFlush(entity);
				log.error("10. saveAndFlush: {}");
				/*pk = InVariableProcesoPK.builder()
						.cveEntidad(userSession.getCveEntidad())
						.cveInstancia(datosExcel.getInstancia())
						.cveProceso(datosExcel.getProceso())
						.cveVariable("VPRO_01_SALARIO_BASE_COTIZACION_TE")
						.secuenciaValor(1)
						.ocurrencia(ocurrencia)
						.version(new BigDecimal(1.0))
						.build();
			
				entity = InVariableProceso.builder()
						.id(pk)
						.valorDecimal(new BigDecimal(trabajador.getSalarioBase()))
						.build();
				inVariableProcesoRepository.saveAndFlush(entity);*/
				
				pk = InVariableProcesoPK.builder()
						.cveEntidad(userSession.getCveEntidad())
						.cveInstancia(datosExcel.getInstancia())
						.cveProceso(datosExcel.getProceso())
						.cveVariable("VPRO_01_NSS_TE")
						.secuenciaValor(1)
						.ocurrencia(ocurrencia)
						.version(new BigDecimal(1.0))
						.build();
			
				entity = InVariableProceso.builder()
						.id(pk)
						.valorAlfanumerico(trabajador.getSeguroSocial())
						.build();
				inVariableProcesoRepository.saveAndFlush(entity);
				log.error("11. saveAndFlush: {}");
				/*pk = InVariableProcesoPK.builder()
						.cveEntidad(userSession.getCveEntidad())
						.cveInstancia(datosExcel.getInstancia())
						.cveProceso(datosExcel.getProceso())
						.cveVariable("VPRO_01_FECHA_INICIO_DEL_TE")
						.secuenciaValor(1)
						.ocurrencia(ocurrencia)
						.version(new BigDecimal(1.0))
						.build();
			
				entity = InVariableProceso.builder()
						.id(pk)
						.valorFecha(trabajador.getFechaIni())
						.build();
				inVariableProcesoRepository.saveAndFlush(entity);
				
				pk = InVariableProcesoPK.builder()
						.cveEntidad(userSession.getCveEntidad())
						.cveInstancia(datosExcel.getInstancia())
						.cveProceso(datosExcel.getProceso())
						.cveVariable("VPRO_01_FECHA_INICIO_AL_TE")
						.secuenciaValor(1)
						.ocurrencia(ocurrencia)
						.version(new BigDecimal(1.0))
						.build();
			
				entity = InVariableProceso.builder()
						.id(pk)
						.valorFecha(trabajador.getFechaFin())
						.build();
				inVariableProcesoRepository.saveAndFlush(entity);*/
				
				ocurrencia++;
				log.error("12. ocurrencia: {}", ocurrencia);
			} 

		} catch (DataAccessException exData) {
			log.error("ERROR", exData.getMessage());
			estatus = "ERROR";
			throw new BrioBPMException("Error al registrar a los trabajadores. ", exData);
		}
		
		return estatus;
	}

	@Override
	public List<ComboBoxTO> getDiasHistorico(DatosAutenticacionTO session, String rfc, String contrato, String cveProceso)
			throws BrioBPMException {
		List<ComboBoxTO> registrosTO = new ArrayList<ComboBoxTO>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			List<Date> listEntity = trabajadoresHistoricoRepository.obtieneFechas(session.getCveEntidad(), cveProceso, rfc, contrato);
			
			for (Date date : listEntity) {
				ComboBoxTO to = ComboBoxTO.builder()
						.id(sdf.format(date))
						.descripcion(sdf.format(date))
						.build();
				registrosTO.add(to);
			}
			
			registrosTO = registrosTO.stream()
		            .distinct()
		            .collect(Collectors.toList());
			
		} catch (DataAccessException exData) {
			log.error("###DATA-ERROR### : {}  ", exData.getMessage());
			throw new BrioBPMException(messagesService.getMessage(session, "IN_VARIABLE_PROCESO",
					"MANTENIMIENTO_TRABAJADORESACTIVOS_CONSULTAR_ERROR", ""), exData);
		}
		return registrosTO;
	}
	
	private boolean validaNoExisteTrabajador(DatosAutenticacionTO userSession,
			String contratoOrigen, String rfcContratista, String nombre, String curpTrabajador, 
			String rfcTrabajador, String seguroSocial ) {
		boolean noExiste = false;

		
		String curp = cedulaDetCuotasRepository.getCurpTrabajador(userSession.getCveEntidad(), "SISTEMA_UNICO_AUTODETERMINACION", 
				rfcContratista, contratoOrigen, seguroSocial, nombre, curpTrabajador, rfcTrabajador);
		
		if (curp == null) {
			noExiste = true;
		} else {
			if (curp.isEmpty()) {
				noExiste = true;
			}
		}
		
		return noExiste;
	}
	
	 /*
		
	
	
	
*/
}
