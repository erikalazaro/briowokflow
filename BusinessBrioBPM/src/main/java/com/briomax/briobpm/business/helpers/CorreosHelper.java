package com.briomax.briobpm.business.helpers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.briomax.briobpm.business.helpers.base.ICorreosHelper;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.namedquery.LeeCorreosPorEnviar;
import com.briomax.briobpm.persistence.entity.namedquery.LeeCorreosPorEnviarPK;
import com.briomax.briobpm.persistence.repository.ICorreosEnviarRepository;
import com.briomax.briobpm.transferobjects.emun.MagicNumber;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j

public class CorreosHelper implements ICorreosHelper {
	/** El atributo o variable Nodo Helper. */ 
	@Autowired
	ICorreosEnviarRepository correosenviar;
	@Override
	public List<LeeCorreosPorEnviar> leeCorreos() throws BrioBPMException {
		List<LeeCorreosPorEnviar> datosCorreo = new ArrayList<LeeCorreosPorEnviar>();
		//llama SP_LEE_CORREOS_POR_ENVIAR
		List<Object >listaDAT=correosenviar.regresaEtiquetas();
	   if(!listaDAT.isEmpty()) {
		   
	   
			listaDAT.forEach(item -> {
				Object[] row = (Object[]) item;
				LeeCorreosPorEnviar itemSelected = new LeeCorreosPorEnviar();
				LeeCorreosPorEnviarPK pk = new LeeCorreosPorEnviarPK();
				pk.setCveProceso(((String) Arrays.asList(row).get(MagicNumber.ZERO.getValue())));
				pk.setVersion ((BigDecimal) Arrays.asList(row).get(MagicNumber.ONE.getValue())) ;
				pk.setNumCorreo((((BigDecimal) Arrays.asList(row).get(MagicNumber.TWO.getValue())).longValue()));
				itemSelected.setId(pk);
				itemSelected.setPara(((String) Arrays.asList(row).get(MagicNumber.THREE.getValue())));
				itemSelected.setConCopiaPara(((String) Arrays.asList(row).get(MagicNumber.FOUR.getValue())));
				itemSelected.setAsunto(((String) Arrays.asList(row).get(MagicNumber.FIVE.getValue())));
				itemSelected.setMensaje(((String) Arrays.asList(row).get(MagicNumber.SIX.getValue())));
				datosCorreo.add(itemSelected);
				}
			);	
	   }
			return datosCorreo;
		
	}

}
