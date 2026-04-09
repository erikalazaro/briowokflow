package com.briomax.briobpm.business.helpers;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.business.helpers.base.IStVariableDependienteHelper;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.repository.IStVariableDependienteRepository;
import com.briomax.briobpm.transferobjects.ComboBoxTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.StVariableDependienteInTO;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la clase NodoHelper.java es proporcionar métodos de ayuda relacionados con los nodos.
 *
 * @author Alexis Zamora
 * @ver 1.0
 * @fecha Feb 14, 2024 4:12:01 PM
 * @since JDK 1.8
 */

@Service
@Builder
@Transactional
@Slf4j
public class StVariableDependienteHelper implements IStVariableDependienteHelper{
	
	/** 
	 * Inyeccion de EntityManager, es la interfaz principal a traves de la
	 * cual se realizan operaciones de base de datos en JPA
	 */
	@PersistenceContext 
    private EntityManager entityManager;
	
	@Autowired
	private IStVariableDependienteRepository istVariableDependienteRepository;
	
	@Override
	public List<List<String>> obtenerVariable(DatosAutenticacionTO session, StVariableDependienteInTO in) throws BrioBPMException {
		
	
		String consulta = "";
		String objeto1 = "";
		String objeto2 = "";
		String nomVariable = "";

		
		
		List<List<String>> comboList = new ArrayList<List<String>>();
		List<String> comboList2 = new ArrayList<String>();
	
		List<Object[]> datos = istVariableDependienteRepository.obtieneVariableDependiente(in.getCveEntidad(),in.getCveProceso()
				, in.getCveVariable()/*, 
				in.getVersion(), in.getCveNodo(), in.getIdNodo(), in.getCveSeccion(), in.getCveVariable(), in.getCveSeccionDependiente()*/);
		
	
		
		for (Object[] object : datos) {
		    // Base del WHERE (sin reemplazar aún)
		    String condicion = (String) object[2];

		    // Reemplazo acumulativo
		    if (in.getCveEntidad() != null) {
		        condicion = condicion.replace("@CVE_ENTIDAD@", in.getCveEntidad());
		    }
		    if (in.getEtiquetaLista() != null) {
		        condicion = condicion.replace("@ETIQUETA_LISTA@", in.getEtiquetaLista());
		    }
		    if (in.getCondicion() != null) {
		        condicion = condicion.replace("@CONDICION@", in.getCondicion());
		    }

		    if (session.getCveUsuario() != null) {
		        condicion = condicion.replace("@CVE_USUARIO@", session.getCveUsuario());
		    }
		    
		    nomVariable = (String) object[3];
		    consulta = "SELECT " + object[0] + " FROM " + object[1] + " WHERE " + condicion;

		    //log.info("--------------Consulta compuesta: " + consulta);
		    if (in.getCveEntidad() != null) {
		    	consulta = consulta.replace("@CVE_ENTIDAD@", in.getCveEntidad());
		    }
		    log.error("--------------Consulta compuesta final: " + consulta);
		
				List<Object> resultado = entityManager.createNativeQuery(consulta).getResultList();
				log.error("--------------Resultado consulta: " + resultado);
				Object  valorCombo = resultado.get(0);
				List<String> valores = new ArrayList<String>();

				// Consultar el primer dato de la lista
				if (!resultado.isEmpty()) {
					for (Object varDependiente: resultado) {
					    Object primerDato = varDependiente;  // Obtener el segundo valor de la primera fila
					    comboList2 = new ArrayList<String>();
					    // Convertir a String dependiendo del tipo de dato
					    String valorConvertido = null;
					    
					    if (primerDato instanceof Number) {
					        // Si es BigDecimal, lo convertimos a String
					        valorConvertido = primerDato.toString();
					        valores.add(valorConvertido);
					    } else if (primerDato instanceof String) {
					        // Si ya es un String, lo usamos tal cual
					        valorConvertido = (String) primerDato;
					        valores.add(valorConvertido);
					    } else if (primerDato instanceof Date) {
					        // Si es Date, lo formateamos a String
					        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					        valorConvertido = sdf.format((Date) primerDato);
					        valores.add(valorConvertido);
					    } else if (valorCombo instanceof Object[]) {
					        // Si es Date, lo formateamos a String
					        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					        //valorConvertido = sdf.format((Date) primerDato);
					    	//log.info("primerDato: {} ", primerDato[0];
					    	Object[] objeto = (Object[])primerDato;
					    	ComboBoxTO comboobj = new ComboBoxTO(objeto[0].toString(),objeto[1].toString());
					    	String val = objeto[0].toString()+ "," + objeto[1].toString();
					    	log.info("val: {} ", val);
					        valores.add(comboobj.toString());
					        valorConvertido = val;
					    } else {
					        // Otras conversiones si es necesario
					        valorConvertido = String.valueOf(primerDato);
					        valores.add(valorConvertido);
					    }
					    comboList2.add(nomVariable + " ," + valorConvertido);
					    comboList.add(comboList2);
					}
			log.info("valores: {} ", valores);
			
				}
			}
			
			log.info("------------------lista de RFC: " + comboList2.size() + " " + comboList2 );
			
		return comboList;
		
	}
		
}
