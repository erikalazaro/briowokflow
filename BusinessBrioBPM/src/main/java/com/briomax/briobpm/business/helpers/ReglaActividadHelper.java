package com.briomax.briobpm.business.helpers;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.business.convertes.ReglaActividadConverter;
import com.briomax.briobpm.business.helpers.base.INodoHelper;
import com.briomax.briobpm.business.helpers.base.IReglaActividadHelper;
import com.briomax.briobpm.common.core.Constants;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.MensajeRegla;
import com.briomax.briobpm.persistence.entity.VariableResultado;
import com.briomax.briobpm.persistence.entity.namedquery.LeeReglasActividad;
import com.briomax.briobpm.persistence.repository.IMensajeReglaRepository;
import com.briomax.briobpm.persistence.repository.IReglaVariableSeccionRepository;
import com.briomax.briobpm.persistence.repository.IStVariableSeccionRepository;
import com.briomax.briobpm.persistence.repository.IVariableResultadoRepository;
import com.briomax.briobpm.transferobjects.ReglasActividadTO;
import com.briomax.briobpm.transferobjects.VariableCadenaTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.EstatusVariableCadenaTO;
import com.briomax.briobpm.transferobjects.in.EstatusVariablesTO;
import com.briomax.briobpm.transferobjects.in.NodoTO;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la clase ReglaVariableHelper.java es obtener las reglas asociadas a una actividad.
 *
 * @author Erika Vazquez
 * @ver 1.0
 * @fecha Sep 10, 2024 4:12:01 PM
 * @since JDK 11
 */
@Slf4j
@Service
@Transactional
public class ReglaActividadHelper implements IReglaActividadHelper {
	
	/** El atributo variable messages service. */
	@Autowired
	private IReglaVariableSeccionRepository reglaVariableSeccionRepository;
	
	/** El atributo variable messages service. */
	@Autowired
	private IVariableResultadoRepository variableResultadoRepository;
	
	/** El atributo variable mensaje Regla. */
	@Autowired
	private IMensajeReglaRepository mensajeReglaRepository;
	
	/** El atributo variable nodo Helper. */
	@Autowired
	private INodoHelper nodoHelper;
	
	/** El atributo Variable Seccion Repository. */
	@Autowired
	private IStVariableSeccionRepository variableSeccionRepository;
	
	//SP_LEE_REGLAS_ACTIVIDAD
	@Override
	public DAORet<List<LeeReglasActividad>, RetMsg>  leeReglasActividad(DatosAutenticacionTO session, NodoTO nodo) 
			throws BrioBPMException, ParseException {

		log.debug(">>>>> leeReglasActividad");
		List<Object> reglasActividadList = reglaVariableSeccionRepository.especificacionReglas(
				session.getCveEntidad(), nodo.getCveProceso(), 
				nodo.getVersion(), nodo.getCveNodo(),  nodo.getIdNodo());

		List<ReglasActividadTO> reglasList = new ArrayList<ReglasActividadTO>();		
		List<ReglasActividadTO> reglasResultante = new ArrayList<ReglasActividadTO>();	
		reglasList.addAll(reglasActividadList.stream().map(ReglaActividadConverter.converterReglas).collect(Collectors.toList()));	
		int consecutivo = 0;
		RetMsg result = RetMsg.builder()
				.status(Constants.OK)
				.message("")
				.build();
		
		for(ReglasActividadTO regla: reglasList) {
			// PROCESA VARIABLES RESULTANTES
			ReglasActividadTO reglaResultante = regla;
			// CURSOR QUE OBTIENE LAS VARIABLES RESULTANTES DE UNA REGLA
			List<VariableResultado>  variables = variableResultadoRepository.findByParameters(session.getCveEntidad(),
					nodo.getCveProceso(), nodo.getVersion(), regla.getCveRegla());

			for(VariableResultado variable: variables) {
				String valorAsignar = asignarValor(variable.getValorAlfanumerico(), variable.getValorEntero(),
						variable.getValorDecimal(), variable.getValorFecha());
				//Construir notacion polaca
				String notacionPolaca = construirNotacionPolaca(regla.getNotacionPolaca(), valorAsignar, variable.getId().getCveVariable());
				//CREANDO REGISTRO DE REGLA PARA VARIABLES RESULTANTES
				consecutivo++;
				reglaResultante.setNotacionPolaca(notacionPolaca);
				reglaResultante.setValorAlfanumerico(variable.getValorAlfanumerico());
				reglaResultante.setValorDecimal(variable.getValorDecimal());
				reglaResultante.setValorEntero(variable.getValorEntero());
				reglaResultante.setValorFecha(variable.getValorFecha());
				reglaResultante.setConsecutivo(consecutivo);

				reglasResultante.add(reglaResultante);
			}
			//}
			//PROCESA MENSAJES DE LA REGLA
			List<MensajeRegla> mensajeRegla = mensajeReglaRepository.findByParameters(session.getCveEntidad(),
					nodo.getCveProceso(), nodo.getVersion(), regla.getCveRegla(), session.getCveIdioma());

			//CURSOR QUE OBTIENE LOS MENSAJES DE UNA REGLA
			for(MensajeRegla mensaje: mensajeRegla) {				
				String tipoMensajeSalida =  asignarTipoMensaje( mensaje.getId().getTipoMensaje()); 
				String notacionPolaca2 = construirNotacionPolaca2(regla.getNotacionPolaca(), mensaje.getNumeroMensaje(),tipoMensajeSalida);
				//CREANDO REGISTRO DE REGLA PARA MENSAJES DE LA REGLA
				consecutivo++;
				reglaResultante.setNotacionPolaca(notacionPolaca2);
				reglaResultante.setCveIdioma(mensaje.getId().getCveIdioma());
				reglaResultante.setNumeroMensaje(mensaje.getNumeroMensaje());
				reglaResultante.setConsecutivo(consecutivo);
				reglasResultante.add(reglaResultante);
			}
			
		}
		//CREA TABLA TEMPORAL PARA ALMACENAR LISTA DE VARIABLES USADAS EN LA NOTACIÓN POLACA VARIABLE_CADENA - VariableCadenaTO
		List<VariableCadenaTO> lista = new ArrayList<VariableCadenaTO>();
		// Obtener una lista de nombres únicos (distinct)
        List<String> variables = reglasResultante.stream()
            .map(ReglasActividadTO::getNotacionPolaca) 
            .distinct()               // Filtrar solo valores únicos
            .collect(Collectors.toList());  // Recoger los resultados en una lista
		//CREA TABLA TEMPORAL CON LA LISTA CONSOLIDADA DE VARIABLES CONTENIDAS EN LAS NOTACIONES    CONSOLIDADO_VARIABLE 
        
        for (String cadenaEntrada: variables) {        	
        	log.info(cadenaEntrada);
        	EstatusVariableCadenaTO estatus = nodoHelper.leeVariablesCadena(cadenaEntrada, lista);
        	result.setMessage(estatus.getMensaje());
    	    result.setStatus(estatus.getTipoExcepcion());
        }
        
        //	LISTA DE VARIABLES QUE ESTÁN EN LA NOTACIÓN POLACA PERO NO EN EL FORMULARIO
   
        List<String> listaCveVariable = lista.stream()
                .map(VariableCadenaTO::getCveVariable)  // Extraer el valor de cveVariable
                .collect(Collectors.toList()); 
        
        List<String> variablesSeccion = variableSeccionRepository.getVariables(session.getCveEntidad(),
				nodo.getCveProceso(), nodo.getVersion(), nodo.getCveNodo(),new BigDecimal( nodo.getIdNodo()));
        
     // Encuentra las variables que están en notacionpolaca pero no en formulario
        List<String> variablesNoEnFormulario = new ArrayList<>(listaCveVariable);
        variablesNoEnFormulario.removeAll(variablesSeccion);

      // ObTIENE EL  VALOR DE LA VARIABLE
       
       for (String variable: variablesNoEnFormulario) {
    	   log.info(" - ----------> estatus: variable {} ", variable);
    	   EstatusVariablesTO estatus = null;
    	   try {
    		   if(variable != null) {
    			   estatus = nodoHelper.leeValorVariable(session, nodo, variable, 1);
    		   } else {
    			   //log.info(" - ----------> estatus: variable {} ", variable);
    	    	   
    		   }
    	   }
           catch (Exception e) {
        	   e.printStackTrace();
        	   
           }
    	   //log.info("estatus: getTipo {} ", estatus.getTipo());
    	   String valorResultante = "'" +  obtenerValorResultante(estatus) + "'" ;
    	   
    	   String cadenaBuscar =     "{@" + variable + "@}";
    	   int i = 0;
    	   for (String regla : variables) {
               // Si el valor de notacionpolaca coincide con 'aBuscar', lo reemplaza
    		   variables.set(i,regla.replace(cadenaBuscar, valorResultante));
    		   i++;
    	   }
       }       
       
       
       
       List<LeeReglasActividad> listaNotacion = new ArrayList<LeeReglasActividad>();		
		
       listaNotacion.addAll(variables.stream().map(ReglaActividadConverter.convertirAListaLeeReglas).collect(Collectors.toList()));        
       for (LeeReglasActividad regla : listaNotacion) {
           // Si el valor de notacionpolaca coincide con 'aBuscar', lo reemplaza
		   regla.setNotacionPolaca(regla.getNotacionPolaca().replace("@", ""));
	   }
       return new DAORet<List<LeeReglasActividad>, RetMsg> (listaNotacion, result);
	}
	
    private String asignarValor(String valorAlfanumerico, BigInteger valorEntero, BigDecimal valorDecimal, Date valorFecha) {
        String valorAAsignar = "";

        if (valorAlfanumerico != null) {
            // Si el valor alfanumérico no es nulo
            valorAAsignar = valorAlfanumerico;
        } else if (valorEntero != null) {
            // Si el valor entero no es nulo, convertirlo a String
            valorAAsignar = valorEntero.toString();
        } else if (valorDecimal != null) {
            // Si el valor decimal no es nulo, convertirlo a String
            valorAAsignar = valorDecimal.toString();
        } else if (valorFecha != null) {
            // Si el valor fecha no es nulo, formatear la fecha
            valorAAsignar = formatearFecha(valorFecha, "yyyy-MM-dd'T'HH:mm:ss");
        }
        return valorAAsignar;
    }
	
    
    // Método para formatear la fecha
    private String formatearFecha(Date fecha, String formato) {
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        return sdf.format(fecha);
    }
    
    //Construir notacion polaca
    public String construirNotacionPolaca(String notacionPolaca, String valorAAsignar, String cveVariableResultante) {
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.PAR_ABRE)
          .append(notacionPolaca)
          .append(Constants.PAR_CIERRE + Constants.VACIO)
          .append(Constants.COR_ABRE)
          .append(Constants.COMILLA )
          .append(valorAAsignar)
          .append(Constants.COMILLA  +  Constants.VACIO)
          .append(Constants.DOS_PUNTOS)
          .append(cveVariableResultante)
          .append(Constants.DOS_PUNTOS + Constants.VACIO)
          .append(Constants.SET)
          .append(Constants.COR_CIERRE);
        return sb.toString();
    }
    
    private String asignarTipoMensaje(String tipoMensaje) {
        String tipoMensajeSalida = "";

        switch (tipoMensaje) {
        case Constants.INFORMATIVO:
            tipoMensajeSalida = Constants.INF;
            break;
        case Constants.AVISO:
            tipoMensajeSalida = Constants.WAR;
            break;
        case Constants.ERROR:
            tipoMensajeSalida = Constants.ERR;
            break;
        default:
            tipoMensajeSalida = "";
            break;
    }


        return tipoMensajeSalida;
    }
    
    public String construirNotacionPolaca2(String notacionPolaca, Integer numeroMensaje, String tipoMensajeSalida) {
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.PAR_ABRE)
          .append(notacionPolaca)
          .append(Constants.PAR_CIERRE)
          .append(Constants.VACIO)
          .append(Constants.PAR_ABRE +  Constants.COMILLA + Constants.VACIO +  Constants.COMILLA + Constants.VACIO)
          .append(String.valueOf(numeroMensaje))  // Conversión del número a cadena
          .append(Constants.VACIO)
          .append(tipoMensajeSalida)
          .append(Constants.COR_CIERRE);
        return sb.toString();
    }
    
    private String obtenerValorResultante(EstatusVariablesTO estatus) {
        String valorResultante = " ";  // Valor por defecto
        Object valor = null;
        if (estatus.getTipo() != null) {
        	
        if (estatus.getValorAlfanumerico()!= null) {
            // Si el valor alfanumérico no es nulo
        	valor = estatus.getValorAlfanumerico();
        } else if (estatus.getValorEntero() != null) {
            // Si el valor entero no es nulo, convertirlo a String
        	valor = estatus.getValorAlfanumerico();
        } else if (estatus.getValorDecimal()!= null) {
            // Si el valor decimal no es nulo, convertirlo a String
        	valor = estatus.getValorDecimal();
        } else if (estatus.getValorFecha() != null) {
            // Si el valor fecha no es nulo, formatear la fecha
        	valor = estatus.getValorFecha();
        }
        
        switch (estatus.getTipo()) {
            case Constants.ALFANUMERICO:
                if (valor != null && valor instanceof String) {
                    valorResultante = (String) valor;
                }
                break;

            case Constants.ENTERO:
                if (valor != null && valor instanceof Integer) {
                    valorResultante = String.valueOf(valor);
                }
                break;

            case Constants.DECIMAL:
                if (valor != null && valor instanceof BigDecimal) {
                    valorResultante = String.valueOf(valor);
                }
                break;

            case Constants.FECHA:
                if (valor != null && valor instanceof Date) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    valorResultante = sdf.format((Date) valor);
                }
                break;

            default:
                // Puedes agregar manejo para otros tipos o lanzar una excepción si lo deseas
                throw new IllegalArgumentException("Tipo no soportado: } " + estatus.getTipo());
        }
        }

        return valorResultante;
    }

}
