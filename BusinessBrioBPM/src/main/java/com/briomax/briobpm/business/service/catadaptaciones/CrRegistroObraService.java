package com.briomax.briobpm.business.service.catadaptaciones;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.business.service.catadaptaciones.core.ICrRegistroObraService;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.repository.ICrRegistroObraRepository;
import com.briomax.briobpm.transferobjects.ComboBoxTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.repse.ConsultaPdfTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class CrRegistroObraService implements ICrRegistroObraService {

	
	
	@Autowired
	private ICrRegistroObraRepository registroObraRepository;


	@Override
	public List<ComboBoxTO> getRfcProcesosPeriodicos(DatosAutenticacionTO session, ConsultaPdfTO filtro)
			throws BrioBPMException {
		List<ComboBoxTO> result = new ArrayList<ComboBoxTO>();
		/*List<String> listRfc = registroObraRepository.getRfc(session.getCveEntidad(), filtro.getFechaIncio(), filtro.getFechaFin(),
				filtro.getTipo());
		if (listRfc != null && listRfc.size() > 0) {
			for (String rfc : listRfc) {
				
				ComboBoxTO to = ComboBoxTO.builder().id(rfc)
							.descripcion(rfc)
							.build();
				result.add(to);
			}
		}*/
		return result;
	}

}


