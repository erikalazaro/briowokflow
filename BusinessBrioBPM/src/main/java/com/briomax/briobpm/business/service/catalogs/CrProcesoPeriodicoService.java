package com.briomax.briobpm.business.service.catalogs;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.business.service.catalogs.core.ICrProcesoPeriodicoService;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.CrProcesoPeriodico;
import com.briomax.briobpm.persistence.repository.ICrDestinatarioCorreoRepository;
import com.briomax.briobpm.persistence.repository.ICrPeriodicidadRepository;
import com.briomax.briobpm.persistence.repository.ICrProcesoPeriodicoRepository;
import com.briomax.briobpm.persistence.repository.IParametroGeneralRepository;
import com.briomax.briobpm.transferobjects.ComboBoxTO;
import com.briomax.briobpm.transferobjects.PdfGridTO;
import com.briomax.briobpm.transferobjects.dynamicForm.Properties;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.repse.ConsultaPdfTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class CrProcesoPeriodicoService implements ICrProcesoPeriodicoService {

	@Autowired
	private ICrDestinatarioCorreoRepository crDestinatarioCorreoRepository;
	
	@Autowired
	private ICrProcesoPeriodicoRepository procesoPeriodicoRepository;
	
	@Autowired
	private IParametroGeneralRepository parametroGeneralRepository;
	
	@Autowired
	private ICrPeriodicidadRepository periodicidadRepository;
	
	@Override
	public List<ComboBoxTO> getProcesosPeriodicos(DatosAutenticacionTO session) throws BrioBPMException {
		
		log.info(">>>>>>>>>>>>>>>>>>>>>>>>> Entro en la función de getProcesosPeriodicos <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		
		List<ComboBoxTO> result = new ArrayList<ComboBoxTO>();
		List<CrProcesoPeriodico> procesosPeriodicos = procesoPeriodicoRepository.getProcesosPeriodicos(session.getCveEntidad(), 
				session.getCveLocalidad(), session.getCveIdioma().toUpperCase());
		
		if (procesosPeriodicos.size() > 0) {
			ComboBoxTO to = ComboBoxTO.builder().id("").descripcion("Sin selección").build();
			result.add(to);
			for (CrProcesoPeriodico procesoPeriodico : procesosPeriodicos) {
				String id = procesoPeriodico.getId().getCveProcesoPeriodico();
				to = ComboBoxTO.builder().id(id)
							.descripcion(procesoPeriodico.getDescripcion())
							.build();
				result.add(to);
			}
		}


		log.info("Tamaño Lista getProcesosPeriodicos: " + result.size());
		return result;
	}

	@Override
	public List<ComboBoxTO> getProcesosPeriodicosByRfcContrato(DatosAutenticacionTO session, ConsultaPdfTO filtro) throws BrioBPMException {
		
		log.info(">>>>>>>>>>>>>>>>>>>>>>>>> Entro en la función de getProcesosPeriodicosByrfcContrato <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		String [] contrato = filtro.getContrato().split(" - ");
		List<ComboBoxTO> result = new ArrayList<ComboBoxTO>();
		List<CrProcesoPeriodico> procesosPeriodicos = procesoPeriodicoRepository.getProcesosPeriodicosByRfcContrato(session.getCveEntidad(), 
				session.getCveLocalidad(), session.getCveIdioma().toUpperCase(), filtro.getRfc(), contrato[0].trim());
		
		if (procesosPeriodicos.size() > 0) {
			ComboBoxTO to = ComboBoxTO.builder().id("").descripcion("Sin selección").build();
			result.add(to);
			for (CrProcesoPeriodico procesoPeriodico : procesosPeriodicos) {
				String id = procesoPeriodico.getId().getCveProcesoPeriodico(); // + "|" + procesoPeriodico.getCargaMultiple() 
				to = ComboBoxTO.builder().id(id)
							.descripcion(procesoPeriodico.getDescripcion())
							.build();
				result.add(to);
			}
		}


		log.info("Tamaño Lista getProcesosPeriodicos: " + result.size());
		return result;
	}

	@Override
	public PdfGridTO getTabProcesos(DatosAutenticacionTO session) throws BrioBPMException {
		List<Object> listaProcesos = procesoPeriodicoRepository.getProPerTabla(session.getCveEntidad(), 
				session.getCveLocalidad(), session.getCveIdioma().toUpperCase(), session.getCveUsuario());
			
        List<Properties> columns = new ArrayList<Properties>();
        List<Object> cells = new ArrayList<Object>();
      //Titulo
		Properties column = Properties.builder().width(10).cveVariable("cveProcesoPeriodico").etiqueta("Clave del documento")
				.tipoDato("ALFANUMERICO").visible("No").build();
		columns.add(column);
		
		column = Properties.builder().width(300).cveVariable("periodicida").etiqueta("Periodicida")
				.tipoDato("ALFANUMERICO").visible("No").build();
		columns.add(column);
		
		column = Properties.builder().width(300).cveVariable("nombreDocumento").etiqueta("Nombre de Documento")
				.tipoDato("ALFANUMERICO").visible("Si").build();
		columns.add(column);

		column = Properties.builder().width(100).cveVariable("periodoTiempo").etiqueta("Periodo de Carga")
				.tipoDato("ALFANUMERICO").visible("Si").build();
		columns.add(column);

		column = Properties.builder().width(100).cveVariable("dia").etiqueta("Día Limite de Carga")
				.tipoDato("ALFANUMERICO").visible("Si").build();
		columns.add(column);
		
		column = Properties.builder().width(100).cveVariable("diasAnticipacion").etiqueta("Días Previos para Notificación")
				.tipoDato("ALFANUMERICO").visible("Si").build();
		columns.add(column);
		
		column = Properties.builder().width(100).cveVariable("aplicaInicio").etiqueta("¿Notificación Diaria Hasta Día Limite?")
				.tipoDato("ALFANUMERICO").visible("Si").build();
		columns.add(column);
		
		column = Properties.builder().width(100).cveVariable("notContinua").etiqueta("¿Notificación Después del Día Limite Hasta la Carga del Documento?")
				.tipoDato("ALFANUMERICO").visible("Si").build();
		columns.add(column);
		
		column = Properties.builder().width(100).cveVariable("direcciones").etiqueta("Correo Electrónico para Notificación")
				.tipoDato("ALFANUMERICO").visible("Si").build();
		columns.add(column);
		
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode obj = mapper.createObjectNode();
		for (int i = 0; i < listaProcesos.size(); i++) {
			Object[] rowDetail = (Object[]) listaProcesos.get(i);
			
			String cveProceso = (String) Arrays.asList(rowDetail).get(0);
			String cvePeriocidad = (String) Arrays.asList(rowDetail).get(1);
			String descripcion = (String) Arrays.asList(rowDetail).get(2);
			String desPeriodo = (String) Arrays.asList(rowDetail).get(3);
			String dia	= (String) Arrays.asList(rowDetail).get(4);
			BigDecimal diasAnticipacion = (BigDecimal) Arrays.asList(rowDetail).get(5);
			String notContinua = (String) Arrays.asList(rowDetail).get(6);
			String apliInicio = (String) Arrays.asList(rowDetail).get(7);
			int valor = diasAnticipacion.intValue();
			

			obj = mapper.createObjectNode();
			obj.put("cveProcesoPeriodico", cveProceso);
			obj.put("periodicida", cvePeriocidad);
			obj.put("nombreDocumento", descripcion);
			obj.put("periodoTiempo", desPeriodo);
			obj.put("dia", dia);
			obj.put("diasAnticipacion", String.valueOf(valor));
			
			List<String[]> correoList = new ArrayList<>();
			if ("SI".equals(notContinua)) {
			    correoList.add(new String[]{"SI", "Sí", "SI"});
			    correoList.add(new String[]{"NO", "NO", "NO"});
			} else {
			    correoList.add(new String[]{"NO", "NO", "SI"});
			    correoList.add(new String[]{"SI", "Sí", "NO"});
			}

			// Convertir directamente a JsonNode
			JsonNode detalle = mapper.valueToTree(correoList);
			// Agregar al objeto JSON
			obj.put("notContinua", detalle);
			
			correoList = new ArrayList<>();
			if ("SI".equals(apliInicio)) {
			    correoList.add(new String[]{"SI", "Sí", "SI"});
			    correoList.add(new String[]{"NO", "NO", "NO"});
			} else {
			    correoList.add(new String[]{"NO", "NO", "SI"});
			    correoList.add(new String[]{"SI", "Sí", "NO"});
			}

			// Convertir directamente a JsonNode
			detalle = null;
			detalle = mapper.valueToTree(correoList);				
			obj.put("apliInicio", detalle);
			
			List<String> listUsuario = crDestinatarioCorreoRepository.buscarDestProceso(session.getCveEntidad(), 
				session.getCveLocalidad(), session.getCveIdioma().toUpperCase(), cveProceso, 1, session.getCveUsuario());
			String para = "";
			if (listUsuario.size() >= 1) {
				para = listUsuario.get(0);
			}		
			obj.put("correo", para);
			cells.add(obj);	
		}	
		
		PdfGridTO result = PdfGridTO.builder().columns(columns).cells(cells).build();
		return result;
	}



	
}


