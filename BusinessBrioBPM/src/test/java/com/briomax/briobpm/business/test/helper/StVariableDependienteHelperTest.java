package com.briomax.briobpm.business.test.helper;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.briomax.briobpm.business.helpers.StVariableDependienteHelper;
import com.briomax.briobpm.business.test.base.AbstractCoreTest;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class UsuariosHelperTest.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion May 15, 2020 11:57:26 AM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class StVariableDependienteHelperTest extends AbstractCoreTest{
		
	/** El atributo o variable usarios helper. */
		@Autowired
		private StVariableDependienteHelper stVariableDependienteHelper;
		
		
		@Test
		public void obtenerConsultasVD() throws BrioBPMException {
			
			
			log.info("INICIA TEST");
			DatosAutenticacionTO datosAutenticacion = DatosAutenticacionTO.builder()
					.cveEntidad("BRIOMAX")
					.cveLocalidad("BRIOMAX-CENTRAL")
					.cveUsuario("USUARIO.CONTRATISTA")
					.cveIdioma("ES-MX")
					.build();
					
			// Ejecución del método a probar
			/*StVariableDependienteInTO Vd = StVariableDependienteInTO.builderVD()		
										.cveEntidad("BRIOMAX")
										.cveProceso("FACT_SERV_CONT")
										.version(BigDecimal.ONE)
										.cveNodo("ACTIVIDAD-USUARIO")
										.idNodo(1)
										//.cveSeccion("DATO_CONTRATISTA")
										//.cveVariable("VPRO_01_NUM_CONTRATO")
										//.cveSeccionDependiente("OBJ_CONTRATO")
										//.etiquetaLista("AAE10000000000ALEX01")
										//.condicion("ASE030509UJ6")
										.build();
			*/
			/*gList<List<String>> valores = stVariableDependienteHelper.obtenerVariable(datosAutenticacion, Vd);	
			
			for (List<String> comboBoxTO : valores) {
				log.info("Resultado: " + comboBoxTO.toString());
			}
			
		
			Vd.setCveVariable("VPRO_01_NUM_CONTRATO");
			
			
			for (ComboBoxTO comboBoxTO : valores) {
			//	log.info(comboBoxTO.getId() + " " + comboBoxTO.getDescripcion());
			}*/
			
			log.info("TERMINA TEST");
		}
}
