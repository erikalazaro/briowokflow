package com.briomax.briobpm.business.helpers;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briomax.briobpm.business.helpers.base.IDashboardHelper;
import com.briomax.briobpm.business.helpers.base.IDashboardRepseHelper;
import com.briomax.briobpm.common.core.Constants;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.persistence.repository.IInVariableProcesoRepository;
import com.briomax.briobpm.transferobjects.app.Datos;
import com.briomax.briobpm.transferobjects.app.GraficaTO;
import com.briomax.briobpm.transferobjects.app.Grafico;
import com.briomax.briobpm.transferobjects.app.Seccion;
import com.briomax.briobpm.transferobjects.in.DatoDashboardTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.DatosContratista;
import com.briomax.briobpm.transferobjects.in.DatosContrato;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class DashboardRepseHelper implements IDashboardRepseHelper {
	
	@Autowired
	private IInVariableProcesoRepository inVariableProcesoRepository;
	
	@Autowired
	private IDashboardHelper dashboardHelper;
	
	@Override
	public DAORet<GraficaTO, RetMsg> generaGrafico(DatosAutenticacionTO session, String destino, String cveDashboard, List<String> datos, Boolean isSeccion, int cveSeccion) throws ParseException {
		
		log.info("---->>> generaGrafico ");
		
		//INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		RetMsg msg = RetMsg.builder()
				.message("")
				.status(Constants.OK)
				.build();
		Date fechaSystema = new Date();
		String contratos = "";
	    List<Object[]> nombresYrfcs = inVariableProcesoRepository.getAllRfcAndNombreByProcess(session.getCveEntidad(), fechaSystema, session.getCveUsuario());
	    datos = new ArrayList<String>(); 
	    String anterior = "";
	    String rfcs = "";
	    
	    for (int i = 0; i < nombresYrfcs.size(); i++) {
	    	Object[] ite = nombresYrfcs.get(i);
        	if (i == nombresYrfcs.size() - 1 ) {
        		contratos = contratos + "'" + (String) ite[2] + "'";
        	} else {
        		contratos = contratos + "'" + (String) ite[2] + "',";
        	}
        	String rfc = (String) ite[0];
        	if (!anterior.isEmpty() && !rfc.equals(anterior)) {
        		rfcs = rfcs + "'" + anterior + "',";
        	}
        	anterior = rfc;
	    }
	    
	    rfcs = rfcs + "'" + anterior + "'";
		String datoRFC = "@RFC@|" + rfcs;
        datos.add(datoRFC);
        
        String datoNUM = "@CONTRATO@|" + contratos;
        datos.add(datoNUM);

        String datoEnt = "@CVE_ENTIDAD@|" + session.getCveEntidad();
        datos.add(datoEnt);
        
		DAORet<List<DatoDashboardTO>, RetMsg> informacionDashboard = dashboardHelper.leeInformacionDashboard(session, destino, cveDashboard, datos, isSeccion, cveSeccion);
		
		List<DatoDashboardTO> infoDashboard = informacionDashboard.getContent();
		
		List<Seccion> graficas = new ArrayList<Seccion>();
		
		log.debug("infoDashboard: {} ", infoDashboard.size());
		
		for (DatoDashboardTO informacion : infoDashboard) {
			
			log.info("------>>> informacion: {} ", informacion.getDatoEtiquetaSerie());

		    String[] datosEtiquetas = informacion.getDatoEtiquetaSerie().split("\\|");
		    String titulo = informacion.getTituloSeccion();
		    String subTitulo = informacion.getTituloSubSeccion();
		    Integer orden = informacion.getSecuenciaSubSeccion();
		    String tipoPresentacion = informacion.getTipoPresentacion();
		    String[] labels = null; // Declaración inicial
		    
		    log.debug("tipoPresentacion: {} ", tipoPresentacion);

		    if ("CIRCULAR".equals(tipoPresentacion)) {
		        labels = new String[1]; // Inicializamos el arreglo con tamaño 1
		        labels[0] = informacion.getTituloSubSubSeccion(); // Asignamos el valor específico
		    }
		   
		     BigDecimal factorConversionEscala2 = informacion.getFactorConversionEscala2();
		     Integer escala = informacion.getEscala();
		    
		    String idGrafico;
		    idGrafico = informacion.getSecuenciaSubSeccion() + "|" + informacion.getSecuenciaSubSubSeccion();

		    // Lista para almacenar los datos
		    List<Datos> data = new ArrayList<>();
		    
		    
		    log.info("datosEtiquetas: {}", datosEtiquetas.length);

		    // Procesar los pares de datos (x, y)
		    for (int j = 0; j < datosEtiquetas.length; j += 2) {
		        String x = datosEtiquetas[j]; // Texto
		        int y = (j + 1 < datosEtiquetas.length) ? Integer.parseInt(datosEtiquetas[j + 1]) : 0; // Número

		        log.info("x: {} y: {}", x, y);
		        
		        Datos dato = Datos.builder()
		                .id(informacion.getSecuenciaSubSubSeccion().toString()) // Usa el ID proporcionado
		                .x(x) // Asignamos el valor de x
		                .y(y) // Asignamos el valor de y
		                .build();

		        data.add(dato);
		    }

		    // Crear el gráfico
		    List<Grafico> graphics = new ArrayList<>();
		    Grafico graphic = Grafico.builder()
		            .id(idGrafico)
		            .tipo(tipoPresentacion)
		            .labels(labels)
		            .data(data)
		            .build();

		    graphics.add(graphic);

		    // Crear la sección
		    Seccion grafica = Seccion.builder()
		            .title(titulo)
		            .subTitle(subTitulo)
		            .order(orden)
		            .tieneEscala(null)
		            .rightScale(null)
		            .graphics(graphics)
		            .build();

		    graficas.add(grafica);
		}

		GraficaTO grafico = GraficaTO.builder()
				.sections(graficas)
				.build();
		
		log.debug("grafico: {} ", grafico.toString());
		
		return new DAORet<>(grafico, msg);
	}	

	
	@Override
	public List<DatosContratista> obtieneContratosPorRFC(DatosAutenticacionTO session, String destino, String cveDashboard,  Boolean isSeccion, int cveSeccion) throws ParseException {
		
		String entidad = session.getCveEntidad();
		Date fechaSystema = new Date();

	    // Obtener todos los RFCs para la entidad especificada
	    List<Object[]> nombresYrfcs = inVariableProcesoRepository.getAllRfcAndNombreByProcess(entidad, fechaSystema, session.getCveUsuario());
	    
	    // Lista para almacenar el resultado final de DatosContrato
	    List<DatosContratista> result = new ArrayList<>();
	    List<String> numContratos = new ArrayList<String>();
    	String rfc = "";
    	String nombre = "";
    	String numContrato = "";
    	String rfcAnterior= "";
    	String contratos = "";
    	
	    // Iterar sobre cada RFC
	    for (Object[] nombreYrfc : nombresYrfcs) {
	    	if (!rfcAnterior.isEmpty() && !rfcAnterior.equals(nombreYrfc [0])) {
		        List<String> datos = new ArrayList<String>();
		        String datoRFC = "@RFC@|"+rfc;
		        datos.add(datoRFC);
	            contratos = "";
	            for (int i = 0; i < numContratos.size(); i++) {
	            	if (i == numContratos.size() - 1 ) {
	            		contratos = contratos + "'" + numContratos.get(i) + "'";
	            	} else {
	            		contratos = contratos + "'" + numContratos.get(i) + "',";
	            	}	            	
				}
		        // Obtenemos el primer número de contrato
	            String datoNUM = "@CONTRATO@|" + contratos;
	            datos.add(datoNUM);
	            
		        DAORet<GraficaTO, RetMsg> grafica = dashboardHelper.generaGrafico(session, destino, cveDashboard, datos, isSeccion, cveSeccion);
		     	
	        	DatosContratista datoTO = DatosContratista.builder()
	            		.razonSocial(nombre)
	            		.sections(grafica.getContent().getSections())
	            		.build();
	            
	            result.add(datoTO);	

	    	}
	    	rfc = (String) nombreYrfc [0];
	    	nombre = (String) nombreYrfc [1];
	    	numContrato = (String) nombreYrfc [2];       
	    	numContratos.add( numContrato);
	    	rfcAnterior = rfc;
	    }
        List<String> datos = new ArrayList<String>();
        String datoRFC = "@RFC@|"+rfc;
        datos.add(datoRFC);
        contratos = "";
        for (int i = 0; i < numContratos.size(); i++) {
        	if (i == numContratos.size() - 1 ) {
        		contratos = contratos + "'" + numContratos.get(i) + "'";
        	} else {
        		contratos = contratos + "'" + numContratos.get(i) + "',";
        	}
        	
		}
        // Obtenemos el primer número de contrato
        String datoNUM = "@CONTRATO@|" + contratos;
        datos.add(datoNUM);
        
        DAORet<GraficaTO, RetMsg> grafica = dashboardHelper.generaGrafico(session, destino, cveDashboard, datos, isSeccion, cveSeccion);
     	
    	DatosContratista datoTO = DatosContratista.builder()
        		.razonSocial(nombre)
        		.sections(grafica.getContent().getSections())
        		.build();
        
        result.add(datoTO);	

	    return result;
	}
	
	
	@Override
	public List<DatosContrato> obtieneContratosPorRFCyNUM(DatosAutenticacionTO session, String destino, String cveDashboard, Boolean isSeccion, int cveSeccions) throws ParseException {
		
		String entidad = session.getCveEntidad();
		Date fechaSystema = new Date();

	    // Obtener todos los RFCs para la entidad especificada
	    List<Object[]> nombresYrfcs = inVariableProcesoRepository.getAllRfcAndNombreByProcess(entidad, fechaSystema, session.getCveUsuario());
	    
	    // Lista para almacenar el resultado final de DatosContrato
	    List<DatosContrato> result = new ArrayList<>();
	    String rfcAnterior= "";
	    List<String> datos = new ArrayList<String>();
	    List<String> numContratos = new ArrayList<String>();
    	String rfc = "";
    	String nombre = "";
    	String numContrato = "";
    	
	    // Iterar sobre cada RFC
	    for (Object[] nombreYrfc : nombresYrfcs) {	
	    	if (!rfcAnterior.isEmpty() && !rfcAnterior.equals(nombreYrfc [0])) {
	    		DatosContrato datoTO = DatosContrato.builder()
	            		.rfc(rfc)
	                    .nombre(nombre)
	                    .numerosContrato(numContratos)
	                    .build();
	              
				String datoRFC = "@RFC@|" + rfc;
		        datos.add(datoRFC);
		        
		        // Obtenemos el primer número de contrato
	            String datoNUM = "@CONTRATO@|" + numContratos.get(0);
	            datos.add(datoNUM);
	            
		        DAORet<GraficaTO, RetMsg> grafica = dashboardHelper.generaGrafico(session, destino, cveDashboard,
		        		datos, isSeccion, cveSeccions);
	            datoTO.setGraficas(grafica.getContent());
	            result.add(datoTO);
	            
	    		// Lista temporal para almacenar los números de contrato de cada RFC
	    		numContratos = new ArrayList<String>();
	    	}
	    	rfc = (String) nombreYrfc [0];
	    	nombre = (String) nombreYrfc [1];
	    	numContrato = (String) nombreYrfc [2];
	    	numContratos.add(numContrato);
	        rfcAnterior = rfc;
	    }
	    
		DatosContrato datoTO = DatosContrato.builder()
        		.rfc(rfc)
                .nombre(nombre)
                .numerosContrato(numContratos)
                .build();
          
		String datoRFC = "@RFC@|" + rfc;
        datos.add(datoRFC);
        
        // Obtenemos el primer número de contrato
        if (numContratos.isEmpty()) {
			numContratos.add("");
		} else {
			String datoNUM = "@CONTRATO@|" + numContratos.get(0);
            datos.add(datoNUM);
		}
        
        DAORet<GraficaTO, RetMsg> grafica = dashboardHelper.generaGrafico(session, destino, cveDashboard,
        		datos, isSeccion, cveSeccions);
        datoTO.setGraficas(grafica.getContent());
        result.add(datoTO);

	    return result;
	}

	
	@Override
	public GraficaTO obtieneGraficoPorRFCyNUM(DatosAutenticacionTO session, String destino, String cveDashboard,
			String rfc, String numContrato, Boolean isSeccion, int cveSeccion) throws ParseException {
		
		// Lista temporal para almacenar los datos
		List<String> datos = new ArrayList<String>();
		
		String datoRFC = "@RFC@|" + rfc;
        datos.add(datoRFC);
        
        String datoNUM = "@CONTRATO@|" + numContrato;
        datos.add(datoNUM);
        
        // Obtenemos el gráfico
        DAORet<GraficaTO, RetMsg> grafica = dashboardHelper.generaGrafico(session, destino, cveDashboard, 
        		datos, isSeccion, cveSeccion);
		
		return grafica.getContent();
	}

}
