package com.briomax.briobpm.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.namedquery.LeeCorreosPorEnviar;
import com.briomax.briobpm.persistence.entity.namedquery.LeeCorreosPorEnviarPK;

/**
 * El objetivo de la Interface ICatalogoEtiquetaRepository.java es ...
 * @author 
 * @version 1.0 Fecha de creacion Abr 10, 2024 Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface ICorreosEnviarRepository extends JpaRepository <LeeCorreosPorEnviar, LeeCorreosPorEnviarPK>{
	//SP_LEE_CORREOS_POR_ENVIAR
	@Query(value = "SELECT  CC.CVE_PROCESO, " + 
			"				CC.VERSION, " +
			"				CC.NUMERO_CORREO, " +
			"               CC.PARA, "+
			"               CC.CON_COPIA_PARA, "+
			"               CC.ASUNTO,   "+
			"               CC.MENSAJE "+
    		"		FROM COMPOSICION_CORREO			CC " +
            "		WHERE  CC.SITUACION_CORREO = 'CREADO' " + 
            "		AND  (CC.REFERENCIA != 'REPSE_VALIDO' AND CC.REFERENCIA != 'REPSE_NO_VALIDO')  " + 
            "		ORDER BY FECHA_COMPOSICION ", nativeQuery = true)
    	List<Object> regresaEtiquetas();
	
	//SP_LEE_CORREOS_POR_ENVIAR
	@Query(value = "SELECT  CC.CVE_PROCESO, " + 
			"				CC.VERSION, " +
			"				CC.NUMERO_CORREO, " +
			"               CC.PARA, "+
			"               CC.CON_COPIA_PARA, "+
			"               CC.ASUNTO,   "+
			"               CC.MENSAJE, "+
			"               CC.FECHA_COMPOSICION "+
    		"		FROM COMPOSICION_CORREO			CC " +
            "		WHERE  CC.SITUACION_CORREO = 'CREADO' " + 
            "		AND  CC.CVE_PROCESO = 'FACT_SERV_CONT'  " + 
            "		AND  (CC.REFERENCIA = 'REPSE_VALIDO' OR CC.REFERENCIA = 'REPSE_NO_VALIDO') " + 
            "		ORDER BY FECHA_COMPOSICION ", nativeQuery = true)
    	List<Object> regresaEstatusRepse();
}
