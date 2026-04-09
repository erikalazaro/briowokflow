package com.briomax.briobpm.business.helpers;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.business.helpers.base.IDatosLocalidadHelper;
import com.briomax.briobpm.business.service.catalogs.core.IMessagesService;
import com.briomax.briobpm.common.core.Constants;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.CatalogoEtiqueta;
import com.briomax.briobpm.persistence.entity.CatalogoEtiquetaPK;
import com.briomax.briobpm.persistence.entity.Entidad;
import com.briomax.briobpm.persistence.entity.LocalidadEntidad;
import com.briomax.briobpm.persistence.entity.namedquery.DatosOmisionEntidad;
import com.briomax.briobpm.persistence.entity.namedquery.Localidad;
import com.briomax.briobpm.persistence.repository.ICatalogoEtiquetaRepository;
import com.briomax.briobpm.persistence.repository.IEntidadRepository;
import com.briomax.briobpm.persistence.repository.ILocalidadEntidadRepository;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class DatosLocalidadHelper implements IDatosLocalidadHelper {

	/*El atributo o variable Datos_Localidad Repository*/
	@Autowired
	private ILocalidadEntidadRepository iLocalidadEntidadRepository;
	

	/*El atributo o variable Datos_Localidad Repository*/
	@Autowired
	private IEntidadRepository iEntidadRepository;
	

	/*El atributo o variable Datos_Localidad Repository*/
	@Autowired
	private IMessagesService iMessagesService;
	
	@Autowired
	private NodoHelper nodoHelper;
	
	@Autowired
	private ICatalogoEtiquetaRepository iCatalogoEtiquetaRepository;
	
	
	//SP_LEE_DATOS_LOCALIDAD
	@Override
	public DAORet<List<Localidad>, RetMsg> leeDatosLocalidad(DatosAutenticacionTO session ) throws BrioBPMException {
		
		String cveEntidad = session.getCveEntidad();
		String cvelocalidad = session.getCveLocalidad();
		String diaDeLaSemena = "DIA_DE_LA_SEMANA";
		String variableValor;
		String idProceso;
		String mensaje;
		
		List<Localidad> localidad = new ArrayList<Localidad>();
		
		/*Inicializa Código de Error, Mensaje y Sugerencias*/
		List<LocalidadEntidad> datosLocal = new ArrayList<LocalidadEntidad>();
		RetMsg msg = RetMsg.builder().status("OK").message("").build();
		
		idProceso = "LEE_DATOS_LOCALIDAD";
		
		/*CONCATENA LA LISTA DE LOS PARÁMETROS PARA DESPLEGARLOS EN CASO DE ERROR*/
		variableValor = Constants.CVE_ENTIDAD + cveEntidad + "|" +
						Constants.CVE_LOCALIDAD + cvelocalidad;
		
		/* VALIDA QUE EXISTAN SECCIONES PARA EL NODO PROPORCIONADO*/
//		Integer datoL = iLocalidadEntidadRepository.validaDatos(cveEntidad, cvelocalidad);
//		if(datoL != null) {
		
		List<LocalidadEntidad> datoslocales = iLocalidadEntidadRepository.recuperaDatosEntidad(cveEntidad, cvelocalidad);
		
		if(!datoslocales.isEmpty()) {
			datoslocales.forEach(item ->{
				LocalidadEntidad row = (LocalidadEntidad) item;
				LocalDateTime now = LocalDateTime.now(); 
				DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
				DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm"); 
				String formattedDate = now.format(dateFormatter); 
				String formattedTime = now.format(timeFormatter);
				DayOfWeek dayOfWeek = now.getDayOfWeek(); 
				// En Java, el primer día de la semana es el lunes (valor 1) y el domingo es el valor 7 
				int dayOfWeekValue = dayOfWeek.getValue(); 
				// Convertir el valor numérico a una cadena 
				String dayString = Integer.toString(dayOfWeekValue);
				
				Optional<CatalogoEtiqueta> catalogo = iCatalogoEtiquetaRepository.findById(CatalogoEtiquetaPK.builder()
											.nombreDato(diaDeLaSemena)
											.valorAlfanumerico(dayString)
											.build());
				String fecha = nodoHelper.traducirEtiqueta(session, catalogo.get().getEtiqueta());
				fecha = fecha != null ? fecha : " " ;
				
						Localidad itemSelected = Localidad.builder()
						.clave(item.getId().getCveLocalidad())
						.tipo(item.getTipo())
						.nombre(item.getDescripcion())
						.direccion(item.getCalle() + " " + item.getNumeroExterior() + " " + item.getNumeroInterior() + " " + item.getColonia() + " " 
						+ item.getDelegacionMunicipio() + " " + item.getCodigoPostal() + " " + item.getPais())
						.telefono(item.getTelefono() + " "+ item.getExtension())
						.email(item.getCorreoElectronico())
						.moneda(item.getMoneda().getDescripcion())
						.husohorario( item.getHusoHorario().getCveHusoHorario())
						.fecha(fecha + ", " + formattedDate)
						.hora(formattedTime)
						.build();
						
						localidad.add(itemSelected);		
						
			});
	}else {
		
		Localidad vacia = Localidad.builder()	
						.clave("")
						.tipo("")
						.nombre("")
						.direccion("")
						.telefono("")
						.email("")
						.moneda("")
						.husohorario("")
						.fecha("")
						.hora("")
						.build();	
		
		localidad.add(vacia);
		
	}
				
		return new DAORet<List<Localidad>, RetMsg>(localidad, msg);
	}
	
	@Override
	public DAORet<Localidad, RetMsg> leeDatoLocalidad(DatosAutenticacionTO session) throws BrioBPMException {
			DAORet<List<Localidad>, RetMsg> list = leeDatosLocalidad(session);
			if (list.getContent().isEmpty()) {
				return new DAORet<Localidad, RetMsg>(null, list.getMeta());
			}
			else {
				return new DAORet<Localidad, RetMsg>(list.getContent().get(0), list.getMeta());
			}
		}
	

	@Override
	public DAORet<DatosOmisionEntidad, RetMsg> leeDatosOmisionEntidad(DatosAutenticacionTO session)
			throws BrioBPMException {
		
		String cveEntidad = session.getCveEntidad();
		String cveLocalidad;
		String cveIdioma;
		String cveUsuario = "";
		
		String variableValor;
		String idProceso = "LEE_DATOS_OMISION_ENTIDAD";
		//String cveIdioma = "GENERAL";
		
		
		
		DatosOmisionEntidad datosOmision = new DatosOmisionEntidad();
		
		//INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		RetMsg msg = RetMsg.builder().status("OK").message("").build();
		
		/*CONCATENA LA LISTA DE LOS PARÁMETROS PARA DESPLEGARLOS EN CASO DE ERROR*/
		variableValor = Constants.CVE_ENTIDAD + cveEntidad;
		
		//RECUPERA LOS DATOS DE LA ENTIDAD
		Optional<Entidad> datosOmisionEntidad = iEntidadRepository.findById(cveEntidad);
		
		 if(datosOmisionEntidad.isPresent()) {
			 
			 //hacer un findbyid, opcional idioma, hacer consulta en repository cveidioma correponde a tal  
		    datosOmision = DatosOmisionEntidad.builder()
					.cve_idioma(datosOmisionEntidad.get().getIdioma().getCveIdioma())
					.logotipo(datosOmisionEntidad.get().getLogotipo())
					.build();
		    
			 
		 }else {
			 
			cveLocalidad = "";
			cveIdioma = "GENERAL";
			
			String mensaje = iMessagesService.getMessage(
					session,
					idProceso,
					"NO_HAY_DATOS_POR_OMISION",
					variableValor);
			msg.setStatus(Constants.ERROR);
			msg.setMessage(mensaje);
			
			datosOmision = DatosOmisionEntidad.builder()
					.cve_idioma("")
					.logotipo("")
					.build();
		 }
		
		return new DAORet<DatosOmisionEntidad, RetMsg>(datosOmision,msg);
	}

}























