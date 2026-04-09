package com.briomax.briobpm.persistence.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.dao.base.AbstractBaseDAO;
import com.briomax.briobpm.persistence.dao.base.ITrabajadoresDAO;
import com.briomax.briobpm.persistence.dao.base.IVwDatoActividadDAO;
import com.briomax.briobpm.persistence.entity.VwDatoActividad;
import com.briomax.briobpm.persistence.entity.namedquery.DatoActividad;
import com.briomax.briobpm.persistence.entity.namedquery.DatoActividadDate;
import com.briomax.briobpm.persistence.entity.namedquery.DatoTrabajador;

/**
 * El objetivo de la clase TabajadoresDAO.java es proporcionar métodos 
 * para acceder y manipular datos relacionados con la vista TabajadoresDAO en la base de datos.
 *
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Feb 14, 2024 Modificaciones:
 * @since JDK 1.8
 */
@Repository("TabajadoresDAO")
public class TabajadoresDAO extends AbstractBaseDAO implements ITrabajadoresDAO{ 
	
	/** 
	 * Inyeccion de EntityManager, es la interfaz principal a traves de la
	 * cual se realizan operaciones de base de datos en JPA
	 */
	@PersistenceContext 
    private EntityManager entityManager;
    

	@Override
    public List<DatoTrabajador> obtenerTrabajadoresActivos(String entidad, String rfc, String contrato, String instancia, String cveProces) throws BrioBPMException {
		

	
		
		
		
		/*String query = "SELECT      DISTINCT " + 
				"            VPRFC.VALOR_ALFANUMERICO        RFC, " + 
				"            VPNC.VALOR_ALFANUMERICO         NUMERO_CONTRATO, 	" +
				"			 VPRFC.CVE_INSTANCIA             INSTANCIA,			" +
				"			 VPRFC.OCURRENCIA				 OCURRENCIA,		" +		
				"            VPNOM.VALOR_ALFANUMERICO        NOMBRE_TRABAJADOR, " + 
				"            VPCURP.VALOR_ALFANUMERICO       CURP, " + 
				"            VPNSS.VALOR_ALFANUMERICO        NUMERO_SEGURO_SOCIAL 		" + 

				"    FROM    IN_VARIABLE_PROCESO     VPRFC, 	" + 
				"            IN_VARIABLE_PROCESO     VPNC, 		" + 
				"            IN_VARIABLE_PROCESO     VPNOM, 	" + 
				"            IN_VARIABLE_PROCESO     VPCURP, 	" + 
				"            IN_VARIABLE_PROCESO     VPSALI, 	" + 
				"            IN_VARIABLE_PROCESO     VPNSS, 	" + 
				"            IN_VARIABLE_PROCESO     VPFI, 		" + 
				"            IN_VARIABLE_PROCESO     VPFF 		" + 

				"    WHERE   VPRFC.CVE_ENTIDAD = '" + entidad + "'                      AND " + 
				"            VPRFC.CVE_PROCESO = 'REGISTRO_DE_CONTRATO'                 AND " + 
				"            VPRFC.VERSION = 1                                          AND " +
				"            VPRFC.CVE_VARIABLE = 'VPRO_01_RFC_CONTRATISTA'             AND " + 
				"			 VPRFC.VALOR_ALFANUMERICO = '" + rfc + "'					AND " + 
				"			 VPRFC.CVE_INSTANCIA = '" + instancia + "'					AND " + 

				"            VPNC.CVE_ENTIDAD = VPRFC.CVE_ENTIDAD                       AND " + 
				"            VPNC.CVE_PROCESO = VPRFC.CVE_PROCESO                       AND " + 
				"            VPNC.VERSION = VPRFC.VERSION                               AND " + 
				"            VPNC.CVE_INSTANCIA = VPRFC.CVE_INSTANCIA                   AND " + 
				"            VPNC.CVE_VARIABLE = 'VPRO_01_NUM_CONTRATO'                 AND " + 
				"            VPNC.VALOR_ALFANUMERICO = '" + contrato + "'              	AND " + 

				"            VPNOM.CVE_ENTIDAD = VPRFC.CVE_ENTIDAD                      AND " + 
				"            VPNOM.CVE_PROCESO = VPRFC.CVE_PROCESO                      AND " + 
				"            VPNOM.VERSION = VPRFC.VERSION                              AND " + 
				"            VPNOM.CVE_INSTANCIA = VPRFC.CVE_INSTANCIA                  AND " + 
				"            VPNOM.CVE_VARIABLE = 'VPRO_01_NOMBRE_TRABAJADOR_TE'        AND " + 

				"            VPCURP.CVE_ENTIDAD = VPRFC.CVE_ENTIDAD                      AND " + 
				"            VPCURP.CVE_PROCESO = VPRFC.CVE_PROCESO                      AND " + 
				"            VPCURP.VERSION = VPRFC.VERSION                              AND " + 
				"            VPCURP.CVE_INSTANCIA = VPRFC.CVE_INSTANCIA                  AND " + 
				"            VPCURP.CVE_VARIABLE = 'VPRO_01_CURP_TE'                     AND " + 
				"            VPCURP.OCURRENCIA = VPNOM.OCURRENCIA                        AND " + 

				"            VPNSS.CVE_ENTIDAD = VPRFC.CVE_ENTIDAD                      AND " + 
				"            VPNSS.CVE_PROCESO = VPRFC.CVE_PROCESO                      AND " + 
				"            VPNSS.VERSION = VPRFC.VERSION                              AND " + 
				"            VPNSS.CVE_INSTANCIA = VPRFC.CVE_INSTANCIA                  AND " + 
				"            VPNSS.CVE_VARIABLE = 'VPRO_01_NSS_TE'                      AND " + 
				"            VPNSS.OCURRENCIA = VPNOM.OCURRENCIA";*/
		String query = "SELECT     VPRFC.VALOR_ALFANUMERICO RFC, VPNC.VALOR_ALFANUMERICO NUMERO_CONTRATO, " + 
				"	VPRFC.CVE_INSTANCIA INSTANCIA, VPTRA.OCURRENCIA OCURRENCIA, " + 
				"	VPTRA.CVE_VARIABLE, VPTRA.VALOR_ALFANUMERICO    " + 
				"FROM    IN_VARIABLE_PROCESO     VPRFC, 	            " + 
				"	IN_VARIABLE_PROCESO     VPNC, 		             " + 
				"	IN_VARIABLE_PROCESO     VPTRA	     " + 
				"WHERE   VPRFC.CVE_ENTIDAD = '" + entidad + "'  " + 
				"AND             VPRFC.CVE_PROCESO = '" + cveProces + "'      " + 
				"AND             VPRFC.VERSION = 1.0                             " + 
				"AND             VPRFC.CVE_VARIABLE = 'VPRO_01_RFC_CONTRATISTA'  " + 
				"AND 			 VPRFC.VALOR_ALFANUMERICO = '" + rfc + "'		 " + 
				"AND 			 VPRFC.CVE_INSTANCIA = '" + instancia + "'		 " + 
				"AND             VPNC.CVE_ENTIDAD = VPRFC.CVE_ENTIDAD            " + 
				"AND             VPNC.CVE_PROCESO = VPRFC.CVE_PROCESO            " + 
				"AND             VPNC.VERSION = VPRFC.VERSION                    " + 
				"AND             VPNC.CVE_INSTANCIA = VPRFC.CVE_INSTANCIA        " + 
				"AND             VPNC.CVE_VARIABLE = 'VPRO_01_NUM_CONTRATO'      " + 
				"AND             VPNC.VALOR_ALFANUMERICO = '" + contrato + "'    " + 
				"AND             VPTRA.CVE_ENTIDAD = VPRFC.CVE_ENTIDAD           " + 
				"AND             VPTRA.CVE_PROCESO = VPRFC.CVE_PROCESO           " + 
				"AND             VPTRA.VERSION = VPRFC.VERSION                   " + 
				"AND             VPTRA.CVE_INSTANCIA = VPRFC.CVE_INSTANCIA       " + 
				"AND             VPTRA.CVE_VARIABLE IN ('VPRO_01_NOMBRE_TRABAJADOR_TE', 'VPRO_01_CURP_TE', 'VPRO_01_NSS_TE', 'VPRO_01_RFC_CTA_TRAB_ESP') " +
				" ORDER BY VPTRA.OCURRENCIA  ";

		return entityManager.createNativeQuery(query, DatoTrabajador.class).getResultList();
    }

	@Override
    public List<DatoTrabajador> obtenerTrabajadoresActivosBk(String entidad, String rfc, String contrato, String instancia) throws BrioBPMException {
		
		String query = "SELECT      DISTINCT " + 
				"            VPRFC.VALOR_ALFANUMERICO        RFC, " + 
				"            VPNC.VALOR_ALFANUMERICO         NUMERO_CONTRATO, 	" +
				"			 VPRFC.CVE_INSTANCIA             INSTANCIA,			" +
				"			 VPRFC.OCURRENCIA				 OCURRENCIA,		" +		
				"            VPNOM.VALOR_ALFANUMERICO        NOMBRE_TRABAJADOR, " + 
				"            VPCURP.VALOR_ALFANUMERICO       CURP, " + 
				"            VPSALI.VALOR_DECIMAL            SALARIO_BASE_COTIZACION, 	" + 
				"            VPNSS.VALOR_ALFANUMERICO        NUMERO_SEGURO_SOCIAL, 		" + 
				"            VPFI.VALOR_FECHA                FECHA_INICIAL_CONTRATO, 	" + 
				"            VPFF.VALOR_FECHA                FECHA_FINAL_CONTRATO 		" + 
				"    FROM    IN_VARIABLE_PROCESO     VPRFC, 	" + 
				"            IN_VARIABLE_PROCESO     VPNC, 		" + 
				"            IN_VARIABLE_PROCESO     VPNOM, 	" + 
				"            IN_VARIABLE_PROCESO     VPCURP, 	" + 
				"            IN_VARIABLE_PROCESO     VPSALI, 	" + 
				"            IN_VARIABLE_PROCESO     VPNSS, 	" + 
				"            IN_VARIABLE_PROCESO     VPFI, 		" + 
				"            IN_VARIABLE_PROCESO     VPFF 		" + 

				"    WHERE   VPRFC.CVE_ENTIDAD = '" + entidad + "'                      AND " + 
				"            VPRFC.CVE_PROCESO = 'REGISTRO_DE_CONTRATO'                 AND " + 
				"            VPRFC.VERSION = 1                                          AND " +
				"            VPRFC.CVE_VARIABLE = 'VPRO_01_RFC_CONTRATISTA'             AND " + 
				"			 VPRFC.VALOR_ALFANUMERICO = '" + rfc + "'					AND " + 
				"			 VPRFC.CVE_INSTANCIA = '" + instancia + "'					AND " + 

				"            VPNC.CVE_ENTIDAD = VPRFC.CVE_ENTIDAD                       AND " + 
				"            VPNC.CVE_PROCESO = VPRFC.CVE_PROCESO                       AND " + 
				"            VPNC.VERSION = VPRFC.VERSION                               AND " + 
				"            VPNC.CVE_INSTANCIA = VPRFC.CVE_INSTANCIA                   AND " + 
				"            VPNC.CVE_VARIABLE = 'VPRO_01_NUM_CONTRATO'                 AND " + 
				"            VPNC.VALOR_ALFANUMERICO = '" + contrato + "'              	AND " + 

				"            VPNOM.CVE_ENTIDAD = VPRFC.CVE_ENTIDAD                      AND " + 
				"            VPNOM.CVE_PROCESO = VPRFC.CVE_PROCESO                      AND " + 
				"            VPNOM.VERSION = VPRFC.VERSION                              AND " + 
				"            VPNOM.CVE_INSTANCIA = VPRFC.CVE_INSTANCIA                  AND " + 
				"            VPNOM.CVE_VARIABLE = 'VPRO_01_NOMBRE_TRABAJADOR_TE'        AND " + 

				"            VPCURP.CVE_ENTIDAD = VPRFC.CVE_ENTIDAD                      AND " + 
				"            VPCURP.CVE_PROCESO = VPRFC.CVE_PROCESO                      AND " + 
				"            VPCURP.VERSION = VPRFC.VERSION                              AND " + 
				"            VPCURP.CVE_INSTANCIA = VPRFC.CVE_INSTANCIA                  AND " + 
				"            VPCURP.CVE_VARIABLE = 'VPRO_01_CURP_TE'                     AND " + 
				"            VPCURP.OCURRENCIA = VPNOM.OCURRENCIA                        AND " + 

				"            VPSALI.CVE_ENTIDAD = VPRFC.CVE_ENTIDAD                      AND " + 
				"            VPSALI.CVE_PROCESO = VPRFC.CVE_PROCESO                      AND " + 
				"            VPSALI.VERSION = VPRFC.VERSION                              AND " + 
				"            VPSALI.CVE_INSTANCIA = VPRFC.CVE_INSTANCIA                  AND " + 
				"            VPSALI.CVE_VARIABLE = 'VPRO_01_SALARIO_BASE_COTIZACION_TE'  AND " + 
				"            VPSALI.OCURRENCIA = VPNOM.OCURRENCIA                        AND " + 

				"            VPNSS.CVE_ENTIDAD = VPRFC.CVE_ENTIDAD                      AND " + 
				"            VPNSS.CVE_PROCESO = VPRFC.CVE_PROCESO                      AND " + 
				"            VPNSS.VERSION = VPRFC.VERSION                              AND " + 
				"            VPNSS.CVE_INSTANCIA = VPRFC.CVE_INSTANCIA                  AND " + 
				"            VPNSS.CVE_VARIABLE = 'VPRO_01_NSS_TE'                      AND " + 
				"            VPNSS.OCURRENCIA = VPNOM.OCURRENCIA                        AND " + 

				"            VPFI.CVE_ENTIDAD = VPRFC.CVE_ENTIDAD                      AND " + 
				"            VPFI.CVE_PROCESO = VPRFC.CVE_PROCESO                      AND " + 
				"            VPFI.VERSION = VPRFC.VERSION                              AND " + 
				"            VPFI.CVE_INSTANCIA = VPRFC.CVE_INSTANCIA                  AND " + 
				"            VPFI.CVE_VARIABLE = 'VPRO_01_FECHA_INICIO_DEL_TE'         AND " + 
				"            VPFI.OCURRENCIA = VPNOM.OCURRENCIA                        AND " + 

				"            VPFF.CVE_ENTIDAD = VPRFC.CVE_ENTIDAD                      AND " + 
				"            VPFF.CVE_PROCESO = VPRFC.CVE_PROCESO                      AND " + 
				"            VPFF.VERSION = VPRFC.VERSION                              AND " + 
				"            VPFF.CVE_INSTANCIA = VPRFC.CVE_INSTANCIA                  AND " + 
				"            VPFF.CVE_VARIABLE = 'VPRO_01_FECHA_INICIO_AL_TE'          AND " + 
				"            VPFF.OCURRENCIA = VPNOM.OCURRENCIA ";

		return entityManager.createNativeQuery(query, DatoTrabajador.class).getResultList();
    }


}
