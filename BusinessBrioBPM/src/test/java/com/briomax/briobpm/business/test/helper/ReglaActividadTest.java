package com.briomax.briobpm.business.test.helper;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.briomax.briobpm.business.helpers.base.IReglaActividadHelper;
import com.briomax.briobpm.business.test.base.AbstractCoreTest;

import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.namedquery.LeeReglasActividad;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

import com.briomax.briobpm.transferobjects.in.NodoTO;


import lombok.extern.slf4j.Slf4j;
/**
 * El objetivo de la Class UsuariosHelperTest.java es ...
 * @author Erika Vazquez
 * @version 1.0 Fecha de creacion Sept 12, 2020 11:57:26 AM Modificaciones:
 * @since JDK 11
 */
@Slf4j
public class ReglaActividadTest extends AbstractCoreTest {
	
	/** El atributo o variable usarios helper. */
	@Autowired
	private IReglaActividadHelper reglaActividadHelper;
	
	//SP_LEE_REGLAS_ACTIVIDAD
		@Test
		public void testLeeReglasActividad() throws BrioBPMException {
			log.info("INICIA TEST");
			// Ejecución del método a probar
			DatosAutenticacionTO datosAutenticacion = DatosAutenticacionTO.builder()
					.cveEntidad("BRIOMAX")
					.cveLocalidad("BRIOMAX-CENTRAL")
					.cveUsuario("Miguel.Garcia.Saavedra")
					.cveIdioma("ES-MX")
					
					
					.build();
			 NodoTO nodo = NodoTO.builder()
					 .cveNodo("ACTIVIDAD-USUARIO")
					 .cveInstancia("202408-000003")
					 .cveProceso("INCIDENCIA-SMI")
					 .version( BigDecimal.valueOf(1.00))
					 .idNodo(2)
					 .secuenciaNodo(2)
					 .ocurrencia(1)
					 .build();
			 
			 
					 
					 
			 try {
				DAORet<List<LeeReglasActividad>, RetMsg> mensaje = reglaActividadHelper.leeReglasActividad(datosAutenticacion, nodo);
				log.info("mensaje: " + mensaje.getContent().size());
				for(LeeReglasActividad reglas:mensaje.getContent()) {
					log.info("polaca: " + reglas.getNotacionPolaca());
				}
			 } catch (BrioBPMException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		}

}
