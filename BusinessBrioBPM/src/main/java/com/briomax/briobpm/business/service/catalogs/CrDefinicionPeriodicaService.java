package com.briomax.briobpm.business.service.catalogs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.business.service.catalogs.core.ICrDefinicionPeriodicaService;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.CrCorreo;
import com.briomax.briobpm.persistence.entity.CrDefinicionPeriocidad;
import com.briomax.briobpm.persistence.entity.CrDefinicionPeriocidadPK;
import com.briomax.briobpm.persistence.entity.CrDestinatarioCorreo;
import com.briomax.briobpm.persistence.entity.CrDestinatarioCorreoPK;
import com.briomax.briobpm.persistence.entity.Usuario;
import com.briomax.briobpm.persistence.repository.ICrCorreoRepository;
import com.briomax.briobpm.persistence.repository.ICrDefinicionPeriocidadRepository;
import com.briomax.briobpm.persistence.repository.ICrDestinatarioCorreoRepository;
import com.briomax.briobpm.persistence.repository.IUsuarioRepository;
import com.briomax.briobpm.transferobjects.PdfGridTO;
import com.briomax.briobpm.transferobjects.dynamicForm.Properties;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.repse.CorreoTO;
import com.briomax.briobpm.transferobjects.repse.GuardaCorreoTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class CrDefinicionPeriodicaService implements ICrDefinicionPeriodicaService {

	@Autowired
	private ICrDefinicionPeriocidadRepository definicionPeriocidadRepository;
	
	@Autowired
	private ICrDestinatarioCorreoRepository destinatarioCorreoRepository;
	
	@Autowired
	private ICrCorreoRepository crCorreoRepository;

	/** El atributo o variable repository. */
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@Override
	public PdfGridTO getTabDetProcesos(DatosAutenticacionTO session, CorreoTO datos) throws BrioBPMException {

        List<Properties> columns = new ArrayList<Properties>();
        List<Object> cells = new ArrayList<Object>();
      //Titulo
		Properties column = Properties.builder().width(10).cveVariable("cveProcesoPeriodico").etiqueta("Clave del Proceso")
				.tipoDato("ALFANUMERICO").visible("No").build();
		columns.add(column);
		
		column = Properties.builder().width(10).cveVariable("periocidad").etiqueta("Clave de la Periocidad")
				.tipoDato("ALFANUMERICO").visible("No").build();
		columns.add(column);
		
		column = Properties.builder().width(10).cveVariable("rfc").etiqueta("RFC")
				.tipoDato("ALFANUMERICO").visible("Si").build();
		columns.add(column);
		
		column = Properties.builder().width(10).cveVariable("contrato").etiqueta("Contrato")
				.tipoDato("ALFANUMERICO").visible("Si").build();
		columns.add(column);

		column = Properties.builder().width(300).cveVariable("nombreDocumento").etiqueta("Nombre de Documento")
				.tipoDato("ALFANUMERICO").visible("Si").build();
		columns.add(column);
		
		column = Properties.builder().width(100).cveVariable("aplicara").etiqueta("Correo Dirigido")
				.tipoDato("ALFANUMERICO").visible("Si").build();
		columns.add(column);

		column = Properties.builder().width(100).cveVariable("aplicaInicio").etiqueta("Envía Correo de Recordatorio Diario")
				.tipoDato("ALFANUMERICO").visible("Si").build();
		columns.add(column);
		
		column = Properties.builder().width(100).cveVariable("notContinua").etiqueta("Solo Envía un Correo de Vencimiento ")
				.tipoDato("ALFANUMERICO").visible("Si").build();
		columns.add(column);
		
		column = Properties.builder().width(100).cveVariable("secAntes").etiqueta("Correo de Recordatorio")
				.tipoDato("ALFANUMERICO").visible("Si").build();
		columns.add(column);
		
		column = Properties.builder().width(100).cveVariable("secDia").etiqueta("Correo del Día de Carga")
				.tipoDato("ALFANUMERICO").visible("Si").build();
		columns.add(column);
				
		column = Properties.builder().width(100).cveVariable("secDespues").etiqueta("Correo de Vencimiento")
				.tipoDato("ALFANUMERICO").visible("Si").build();
		columns.add(column);
		
		column = Properties.builder().width(100).cveVariable("paraEmail").etiqueta("Direcciónes de Correo de Envío")
				.tipoDato("ALFANUMERICO").visible("Si").build();
		columns.add(column);

		List<CrDefinicionPeriocidad> lista = definicionPeriocidadRepository.procesosDefByProceso(session.getCveEntidad(), session.getCveLocalidad(), 
				session.getCveIdioma().toUpperCase(), datos.getCveProceso(), datos.getCvePeriodicidad());
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode obj = mapper.createObjectNode();
		
		try {

			for (CrDefinicionPeriocidad ite : lista) {
				obj = mapper.createObjectNode();
				obj.put("cveProcesoPeriodico", ite.getId().getCveProcesoPeriodico());
				obj.put("periocidad", ite.getId().getCvePeriodicidad());
				
				obj.put("rfc", ite.getId().getRfc());
				obj.put("contrato", ite.getId().getContrato());
				obj.put("nombreDocumento", ite.getCrProcesoPeriodico().getDescripcion());
				String acceso = "CONTRATISTA";
				if (ite.getCrProcesoPeriodico().getAplicaContratante().equals("SI")) {
					acceso = "CONTRATANTE";
				}
				obj.put("aplicara", acceso);
				
				List<String> listUsuario = usuarioRepository.buscaCorreos(ite.getId().getRfc(), session.getCveEntidad(), acceso);
				String para = "";
				for (String usu : listUsuario) {
	                int start = usu.indexOf("<");

	                // Busca la posición del carácter '>' en el email
	                int end = usu.indexOf(">");

	                // Verifica que ambos caracteres fueron encontrados y que '<' aparece antes que '>'
	                if (start != -1 && end != -1 && start < end) {
	                    // Extrae el email que se encuentra entre '<' y '>'
	                	String usuFinal = usu.substring(start + 1, end);

	                    para = para + ";" + usuFinal;
	                    
	                }
				}
				
				List<String[]> correoList = new ArrayList<>();
				List<String[]> correoList1 = new ArrayList<>();
				List<String[]> correoList2 = new ArrayList<>();

				if ("SI".equals(ite.getAplicaInicio())) {
				    correoList.add(new String[]{"SI", "Sí", "SI"});
				    correoList.add(new String[]{"NO", "NO", "NO"});
				} else {
				    correoList.add(new String[]{"NO", "NO", "SI"});
				    correoList.add(new String[]{"SI", "Sí", "NO"});
				}

				// Convertir directamente a JsonNode
				JsonNode detalle = mapper.valueToTree(correoList);
				// Agregar al objeto JSON
				obj.put("aplicaInicio", detalle);
				
				correoList = new ArrayList<>();
				if ("SI".equals(ite.getAplicaInicio())) {
				    correoList.add(new String[]{"SI", "Sí", "SI"});
				    correoList.add(new String[]{"NO", "NO", "NO"});
				} else {
				    correoList.add(new String[]{"NO", "NO", "SI"});
				    correoList.add(new String[]{"SI", "Sí", "NO"});
				}

				// Convertir directamente a JsonNode
				detalle = null;
				detalle = mapper.valueToTree(correoList);				
				obj.put("notContinua", detalle);
				
				List<CrCorreo> correos = crCorreoRepository.buscarCorreos(session.getCveEntidad(), 
						session.getCveLocalidad(), session.getCveIdioma().toUpperCase());
				correoList = new ArrayList<>();
				for (CrCorreo corIte : correos) {

					if (ite.getSecDefinicionAntes() == corIte.getId().getSecuenciaCorreo()) {
						correoList.add(new String[]{corIte.getId().getSecuenciaCorreo().toString(), corIte.getAsunto(), "SI"});
						correoList1.add(new String[]{corIte.getId().getSecuenciaCorreo().toString(), corIte.getAsunto(), "NO"});
						correoList2.add(new String[]{corIte.getId().getSecuenciaCorreo().toString(), corIte.getAsunto(), "NO"});
					} else if (ite.getSecDefinicionDia() == corIte.getId().getSecuenciaCorreo()) {
						correoList.add(new String[]{corIte.getId().getSecuenciaCorreo().toString(), corIte.getAsunto(), "NO"});
						correoList1.add(new String[]{corIte.getId().getSecuenciaCorreo().toString(), corIte.getAsunto(), "SI"});
						correoList2.add(new String[]{corIte.getId().getSecuenciaCorreo().toString(), corIte.getAsunto(), "NO"});
					} else if (ite.getSecDefinicionDespues() == corIte.getId().getSecuenciaCorreo() ) {
						correoList.add(new String[]{corIte.getId().getSecuenciaCorreo().toString(), corIte.getAsunto(), "NO"});
						correoList1.add(new String[]{corIte.getId().getSecuenciaCorreo().toString(), corIte.getAsunto(), "NO"});
						correoList2.add(new String[]{corIte.getId().getSecuenciaCorreo().toString(), corIte.getAsunto(), "SI"});
					} else {
						correoList.add(new String[]{corIte.getId().getSecuenciaCorreo().toString(), corIte.getAsunto(), "NO"});
						correoList1.add(new String[]{corIte.getId().getSecuenciaCorreo().toString(), corIte.getAsunto(), "NO"});
						correoList2.add(new String[]{corIte.getId().getSecuenciaCorreo().toString(), corIte.getAsunto(), "NO"});
					}
				}
				
				detalle = null;
				detalle = mapper.valueToTree(correoList);			
				obj.put("secAntes", detalle);

				detalle = null;
				detalle = mapper.valueToTree(correoList1);
				obj.put("secDia", detalle);

				detalle = null;
				detalle = mapper.valueToTree(correoList2);
				obj.put("secDespues", detalle);				

				obj.put("paraEmail", para);				
				cells.add(obj);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PdfGridTO result = PdfGridTO.builder().columns(columns).cells(cells).build();
		return result;
	}

	@Override
	public RetMsg guardaCorreo(DatosAutenticacionTO session, GuardaCorreoTO correo) throws BrioBPMException {
		RetMsg response = RetMsg.builder().status("OK").build();
		
		//Actualiza la defición de procesos
		definicionPeriocidadRepository.actualizaProcesos(session.getCveEntidad(), session.getCveLocalidad(),
				session.getCveIdioma().toUpperCase(), correo.getCveProcesoPeriodico(),
				session.getCveUsuario(), correo.getNotContinua(), correo.getAplicaInicio(), correo.getDia());
		
		//actualiza o crea el correo
		List<Object> datos = definicionPeriocidadRepository.contratoRfcProceso(session.getCveEntidad(),
				session.getCveLocalidad(), session.getCveIdioma().toUpperCase(), correo.getCveProcesoPeriodico(), 
				correo.getPeriocidad(), session.getCveUsuario());
		
		for (int i = 0; i < datos.size(); i++) {
			Object[] rowDetail = (Object[]) datos.get(i);			
			String contrato = (String) Arrays.asList(rowDetail).get(0);
			String rfc = (String) Arrays.asList(rowDetail).get(1);
			
			CrDestinatarioCorreoPK id = CrDestinatarioCorreoPK.builder()
					.cveEntidad(session.getCveEntidad())
					.cveIdioma(session.getCveIdioma().toUpperCase())
					.cveLocalidad(session.getCveLocalidad())
					.cveProcesoPeriodico(correo.getCveProcesoPeriodico())
					.secuenciaCorreo(1)
					.secuenciaDestinatario(1)
					.build();
			CrDestinatarioCorreo entity = CrDestinatarioCorreo.builder()
					.id(id )
					.direccionCorreo(correo.getDireccionCorreo())
					.tipoDestinatario("PARA")
					.rfcContratista(rfc)
					.numeroContrato(contrato)
					.build();
			destinatarioCorreoRepository.saveAndFlush(entity);

			id.setSecuenciaCorreo(2);
			entity.setId(id);
			destinatarioCorreoRepository.saveAndFlush(entity);
			
			id.setSecuenciaCorreo(3);
			entity.setId(id);
			destinatarioCorreoRepository.saveAndFlush(entity);
		}
		
		return response;
	}


}


