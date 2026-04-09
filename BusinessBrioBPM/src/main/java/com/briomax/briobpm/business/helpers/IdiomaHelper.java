package com.briomax.briobpm.business.helpers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.briomax.briobpm.business.helpers.base.IIdiomaHelper;
import com.briomax.briobpm.business.service.catalogs.core.IMessagesService;
import com.briomax.briobpm.common.core.Constants;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.Idioma;
import com.briomax.briobpm.persistence.entity.namedquery.LeeIdiomas;
import com.briomax.briobpm.persistence.repository.IIdiomaRepository;
import com.briomax.briobpm.persistence.repository.ILeeidomasRepository;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
@Service
public class IdiomaHelper implements IIdiomaHelper {
	/** El atributo o variable idioma Repository. */
	@Autowired
	private IIdiomaRepository idiomaRepository;
	
	/** El atributo o variable messages service. */
	@Autowired
	private IMessagesService messagesService;
    
	//EE_IDIOMAS
	@Override 
	public DAORet<List<LeeIdiomas>, RetMsg>  leeIdioma(DatosAutenticacionTO session) throws BrioBPMException {
		String variableValor;
		String idProceso;
		String tipoexcepcionmensaje;
		// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		RetMsg msg = RetMsg.builder().status("OK").message("").build();
		idProceso="LEE_IDIOMAS";
		Sort sort = Sort.by(Sort.Direction.ASC, "descripcion");
		List<Idioma> leeIdiomas=idiomaRepository.findAll(sort);
		if(leeIdiomas.isEmpty()) {
			String mensaje = messagesService.getMessage(session,
					idProceso,
					"NO_EXISTEN_IDIOMAS",
					" ");
			msg = RetMsg.builder().status(Constants.ERROR).message(mensaje).build();
		}
		List<LeeIdiomas>idiomas=new ArrayList <LeeIdiomas>();
		for(Idioma idioma:leeIdiomas) {
			LeeIdiomas dto=LeeIdiomas.builder()
					.cveIdioma(idioma.getCveIdioma())
					.descripcion(idioma.getDescripcion())
					.build();
		idiomas.add(dto);
		}
				return new DAORet<List<LeeIdiomas>, RetMsg>(idiomas, msg);
	}

}
