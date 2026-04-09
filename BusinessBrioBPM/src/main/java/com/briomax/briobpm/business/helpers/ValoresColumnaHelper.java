package com.briomax.briobpm.business.helpers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.business.convertes.ValoresColumnaConverter;
import com.briomax.briobpm.business.helpers.base.IValoresColumnaHelper;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.namedquery.LeeValoresColumna;
import com.briomax.briobpm.persistence.repository.IValorColumnaRepository;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class ValoresColumnaHelper implements IValoresColumnaHelper {
	
	@Autowired
	private IValorColumnaRepository valorColumnaRepository;

	@Override
	public DAORet<List<LeeValoresColumna>, RetMsg> getValoresColumna(String cveUsuario, String cveEntidad,
			String cveLocalidad, String cveIdioma, String cveProceso, BigDecimal version, String nombreTabla,
			String nombreColumna) throws BrioBPMException {
			
		RetMsg msg = RetMsg.builder().status("OK").message("").build();
		
		List<Object> valoresColumnaO = valorColumnaRepository.getValoresColumna(cveEntidad, cveIdioma, nombreTabla, nombreColumna);
		List<LeeValoresColumna> valoresColumna = new ArrayList<LeeValoresColumna>();
		
		valoresColumna.addAll(valoresColumnaO.stream().map(ValoresColumnaConverter.converterValoresColumna).collect(Collectors.toList()));	

		
		if(valoresColumna.size() == 0) {
			msg.setStatus("ERROR");
			msg.setMessage("ERROR_OBTENCION_VALORES_COLUMNA");
		}
				
		DAORet<List<LeeValoresColumna>, RetMsg> response = new DAORet<List<LeeValoresColumna>, RetMsg>(valoresColumna, msg);
		log.info("getValoresColumna Response {} ",response);
		return response;
	}

}
