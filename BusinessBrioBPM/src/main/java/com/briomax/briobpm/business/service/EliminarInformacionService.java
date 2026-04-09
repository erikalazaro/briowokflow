package com.briomax.briobpm.business.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.business.service.core.IEliminarInformacionService;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.persistence.entity.ParametroGeneral;
import com.briomax.briobpm.persistence.repository.IEliminarInformacionRepository;
import com.briomax.briobpm.persistence.repository.IParametroGeneralRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class EliminarInformacionService implements IEliminarInformacionService {
	/** El atributo o variable repository. */
	@Autowired
	private IEliminarInformacionRepository repository;
	
	/** El atributo o variable repository. */
	@Autowired
	private IParametroGeneralRepository parametroGeneralRepository;
	
	
	@Override
	public RetMsg eliminarProcesosPeriodicos() {
		 RetMsg response = new RetMsg();

		    try {

		        repository.deleteProcesosPeriodicos();

		        response.setStatus("OK");
		        response.setMessage("Eliminación realizada correctamente.");
		        log.info("Eliminación de procesos periódicos realizada correctamente.");
		       

		    } catch (Exception e) {
		        response.setStatus("ERROR");
		        response.setMessage("Error al eliminar procesos: " + e.getMessage());
		        log.error("Error al eliminar procesos periódicos. Detalles del error: {}", e.getMessage());
		    }

		    return response;
	}

	@Override
	public RetMsg eliminarProcesoByCve(List<String> procesos) {
		 RetMsg response = new RetMsg();
		    try {
		        repository.eliminarProcesos(procesos);
		        response.setStatus("OK");
		        response.setMessage("Eliminación realizada correctamente.");		
		        log.info("Eliminación de procesos realizada correctamente para los procesos: {}", procesos);

		    } catch (Exception e) {
		        response.setStatus("ERROR");
		        response.setMessage("Error al eliminar procesos: " + e.getMessage());		
		        log.error("Error al eliminar procesos para los procesos: {}. Detalles del error: {}", procesos, e.getMessage());
		    }
		    return response;
	}

	@Override
	public List<String> getParametroGeneralRepository(String clave) {

	    List<ParametroGeneral> parametros =
	            parametroGeneralRepository.documetosObligatorios(clave);

	    if (parametros.isEmpty()) {
	        log.warn("No se encontraron parámetros para la clave: {}", clave);
	        return List.of(); // Esto sí funciona en Java 11
	    }

	    log.info("Parámetros encontrados para la clave {}", clave);

	    return parametros.stream()
	            .map(ParametroGeneral::getValorAlfanumerico)
	            .collect(Collectors.toList());  
	}

}
