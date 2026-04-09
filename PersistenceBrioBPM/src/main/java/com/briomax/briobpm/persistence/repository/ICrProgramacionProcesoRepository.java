package com.briomax.briobpm.persistence.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.persistence.entity.CrProgramacionProceso;
import com.briomax.briobpm.persistence.entity.CrProgramacionProcesoPK;

@Repository
public interface ICrProgramacionProcesoRepository extends JpaRepository<CrProgramacionProceso, CrProgramacionProcesoPK>{

	
	@Query(value = "SELECT DISTINCT CDP.RFC, CDP.CONTRATO , (SELECT count(1)  " + 
			"				FROM CR_PROGRAMACION_PROCESO pp                      " + 
			"                WHERE pp.CVE_ENTIDAD = CRAS.CVE_ENTIDAD  " + 
			"				   AND pp.CVE_LOCALIDAD = CDP.CVE_LOCALIDAD  " + 
			"				   AND pp.CVE_IDIOMA = CDP.CVE_IDIOMA  " + 
			"				   AND pp.SITUACION_EJECUCION = 'PROGRAMADO' " + 
			"				   AND pp.RFC = CDP.RFC " + 
			"				   AND pp.CONTRATO = CDP.CONTRATO ) as PENDIENTE " + 
			"FROM CR_ACCESO_USUARIO CRAS, CR_DEFINICION_PERIODICIDAD CDP " + 
			"WHERE CRAS.CVE_ENTIDAD = :cveEntidad " + 
			"AND CRAS.CVE_USUARIO = :usuario " + 
			"AND CRAS.TIPO_ACCESO_CR = 'CONTRATISTA' " + 
			"AND CDP.CVE_ENTIDAD = CRAS.CVE_ENTIDAD " + 
			"AND CDP.CVE_LOCALIDAD = :cveLocalidad " + 
			"AND CDP.CVE_IDIOMA = :cveIdioma " + 
			
			"AND CDP.RFC = CRAS.RFC " + 
			"AND CDP.SITUACION = 'PROGRAMADO' ", nativeQuery = true)
	List<Object[]> getContratoContratista( @Param("cveEntidad") String cveEntidad, @Param("cveLocalidad") String cveLocalidad,
				@Param("cveIdioma") String cveIdioma, @Param("usuario") String usuario);

	@Query(value = "SELECT DISTINCT CDP.RFC, CDP.CONTRATO , (SELECT count(1)  " + 
			"				FROM CR_PROGRAMACION_PROCESO pp                      " + 
			"                WHERE pp.CVE_ENTIDAD = CRAS.CVE_ENTIDAD  " + 
			"				   AND pp.CVE_LOCALIDAD = CDP.CVE_LOCALIDAD  " + 
			"				   AND pp.CVE_IDIOMA = CDP.CVE_IDIOMA  " + 
			"				   AND pp.SITUACION_EJECUCION = 'PROGRAMADO' " + 
			"				   AND pp.RFC = CDP.RFC " + 
			"				   AND pp.CONTRATO = CDP.CONTRATO ) as PENDIENTE " + 
			"FROM CR_ACCESO_USUARIO CRAS, CR_DEFINICION_PERIODICIDAD CDP " + 
			"WHERE CRAS.CVE_ENTIDAD = :cveEntidad " + 
			"AND CRAS.CVE_USUARIO = :usuario " + 
			"AND CDP.CVE_ENTIDAD = CRAS.CVE_ENTIDAD " + 
			"AND CDP.CVE_LOCALIDAD = :cveLocalidad " + 
			"AND CDP.CVE_IDIOMA = :cveIdioma " + 
			"AND CDP.RFC = CRAS.RFC " + 
			"AND CDP.SITUACION = 'PROGRAMADO' " +
			"   UNION  " +
			" SELECT DISTINCT CDP.RFC, CDP.CONTRATO , (SELECT count(1)  " + 
			"				FROM CR_PROGRAMACION_PROCESO pp                      " + 
			"                WHERE pp.CVE_ENTIDAD = CRAS.CVE_ENTIDAD  " + 
			"				   AND pp.CVE_LOCALIDAD = CDP.CVE_LOCALIDAD  " + 
			"				   AND pp.CVE_IDIOMA = CDP.CVE_IDIOMA  " + 
			"				   AND pp.SITUACION_EJECUCION = 'PROGRAMADO' " + 
			"				   AND pp.RFC = CDP.RFC " + 
			"				   AND pp.CONTRATO = CDP.CONTRATO ) as PENDIENTE " + 
			" FROM CR_ACCESO_USUARIO CRAS, CR_DEFINICION_PERIODICIDAD CDP " + 
			" WHERE CRAS.CVE_ENTIDAD = :cveEntidad " + 
			" AND CRAS.CVE_USUARIO = :usuario " + 
			" AND CDP.CVE_ENTIDAD = CRAS.CVE_ENTIDAD " + 
			" AND CDP.CVE_LOCALIDAD = :cveLocalidad " + 
			" AND CDP.CVE_IDIOMA = :cveIdioma " + 
			" AND CRAS.TIPO_ACCESO_CR = 'CONTRATANTE' " +
			" AND CDP.SITUACION = 'PROGRAMADO' ", nativeQuery = true)
	List<Object[]> getContratoGeneral( @Param("cveEntidad") String cveEntidad, @Param("cveLocalidad") String cveLocalidad,
				@Param("cveIdioma") String cveIdioma, @Param("usuario") String usuario);


	 @Query(value = "  SELECT * FROM ( " +
			 " SELECT pp.* FROM CR_PROGRAMACION_PROCESO pp, CR_DEFINICION_PERIODICIDAD dp " +
             " WHERE " +
             "  pp.CVE_ENTIDAD = dp.CVE_ENTIDAD  " +
             "  AND pp.CVE_LOCALIDAD = dp.CVE_LOCALIDAD " +
             "  AND pp.CVE_IDIOMA = dp.CVE_IDIOMA " +
             "  AND pp.CVE_PROCESO_PERIODICO = dp.CVE_PROCESO_PERIODICO " +
             "  AND pp.CVE_PERIODICIDAD = dp.CVE_PERIODICIDAD " +
             "  AND pp.RFC = dp.RFC " +
             "  AND pp.CONTRATO = dp.CONTRATO " +
             "  AND dp.NOTIFICACION_CONTINUA = 'SI' " +
             "  AND dp.SITUACION = :situacionEjecucion " +
                         
             "  AND pp.CVE_ENTIDAD = :cveEntidad " +
             "  AND pp.CVE_LOCALIDAD = :cveLocalidad " +
             "  AND pp.CVE_IDIOMA = :cveIdioma " +
             "  AND :fechaActual >= pp.FECHA_INICIAL_NOTIFICACION  " +
             "  AND :fechaActual < pp.FECHA_PROGRAMACION  " +
             "  AND (pp.FECHA_ULTIMA_NOTIFICACION IS NULL OR pp.FECHA_ULTIMA_NOTIFICACION <> :fechaActual) " +
             "  AND pp.SITUACION_EJECUCION = :situacionEjecucion "  +
             "  AND dp.TIPO_EJECUCION = :tipoEjecucion " +
             
             " UNION ALL " +
             
             " SELECT pp.* FROM CR_PROGRAMACION_PROCESO pp, CR_DEFINICION_PERIODICIDAD dp " + 
             " WHERE " + 
             "   pp.CVE_ENTIDAD = dp.CVE_ENTIDAD " + 
             "   AND pp.CVE_LOCALIDAD = dp.CVE_LOCALIDAD" + 
             "   AND pp.CVE_IDIOMA = dp.CVE_IDIOMA " + 
             "   AND pp.CVE_PROCESO_PERIODICO = dp.CVE_PROCESO_PERIODICO " + 
             "   AND pp.CVE_PERIODICIDAD = dp.CVE_PERIODICIDAD " + 
             "   AND pp.RFC = dp.RFC " + 
             "   AND pp.CONTRATO = dp.CONTRATO " + 
             "	 AND dp.NOTIFICACION_CONTINUA = 'NO'" + 
             "   AND dp.SITUACION = :situacionEjecucion " + 
             
 			 "   AND pp.CVE_ENTIDAD = :cveEntidad " + 
             "   AND pp.CVE_LOCALIDAD = :cveLocalidad " + 
             "   AND pp.CVE_IDIOMA = :cveIdioma " + 
             "   AND pp.FECHA_INICIAL_NOTIFICACION = :fechaActual " + 
             "   AND (pp.FECHA_ULTIMA_NOTIFICACION IS NULL OR pp.FECHA_ULTIMA_NOTIFICACION <> :fechaActual ) " + 
             "   AND pp.SITUACION_EJECUCION = :situacionEjecucion " +
             "   AND dp.TIPO_EJECUCION = :tipoEjecucion " +
             " ) a ORDER BY a.RFC, a.CONTRATO ",
     nativeQuery = true)
	List<CrProgramacionProceso> encuentraFechaAntesNotificacion(
	  @Param("cveEntidad") String cveEntidad,
	  @Param("cveLocalidad") String cveLocalidad,
	  @Param("cveIdioma") String cveIdioma,
	  @Param("situacionEjecucion") String situacionEjecucion,
	  @Param("fechaActual") LocalDate fechaActual,
	  @Param("tipoEjecucion") String tipoEjecucion);
	 
	 @Query(value =  " SELECT pp.* FROM CR_PROGRAMACION_PROCESO pp, CR_DEFINICION_PERIODICIDAD dp " +
             "WHERE " +
             "  pp.CVE_ENTIDAD = dp.CVE_ENTIDAD  " +
             "  AND pp.CVE_LOCALIDAD = dp.CVE_LOCALIDAD " +
             "  AND pp.CVE_IDIOMA = dp.CVE_IDIOMA " +
             "  AND pp.CVE_PROCESO_PERIODICO = dp.CVE_PROCESO_PERIODICO " +
             "  AND pp.CVE_PERIODICIDAD = dp.CVE_PERIODICIDAD " +
             "  AND pp.RFC = dp.RFC " +
             "  AND pp.CONTRATO = dp.CONTRATO " +
             "  AND dp.SITUACION = :situacionEjecucion " +
             
             "  AND pp.CVE_ENTIDAD = :cveEntidad " +
             "  AND pp.CVE_LOCALIDAD = :cveLocalidad " +
             "  AND pp.CVE_IDIOMA = :cveIdioma " +
             "  AND pp.FECHA_PROGRAMACION = :fechaActual " +
             "  AND (pp.FECHA_ULTIMA_NOTIFICACION IS NULL OR pp.FECHA_ULTIMA_NOTIFICACION <> :fechaActual) " +
             "  AND pp.SITUACION_EJECUCION = :situacionEjecucion " +
             "  AND dp.TIPO_EJECUCION = :tipoEjecucion " +
             " ORDER BY pp.RFC, pp.CONTRATO ", nativeQuery = true)
	List<CrProgramacionProceso> encuentraFechaNotificacion(
	 @Param("cveEntidad") String cveEntidad,
	 @Param("cveLocalidad") String cveLocalidad,
	 @Param("cveIdioma") String cveIdioma,
	 @Param("situacionEjecucion") String situacionEjecucion,
	 @Param("fechaActual") LocalDate fechaActual,
	 @Param("tipoEjecucion") String tipoEjecucion);
	 
	 @Query(value = " SELECT pp.* FROM CR_PROGRAMACION_PROCESO pp, CR_DEFINICION_PERIODICIDAD dp " +
             "WHERE " +
             "  pp.CVE_ENTIDAD = dp.CVE_ENTIDAD  " +
             "  AND pp.CVE_LOCALIDAD = dp.CVE_LOCALIDAD " +
             "  AND pp.CVE_IDIOMA = dp.CVE_IDIOMA " +
             "  AND pp.CVE_PROCESO_PERIODICO = dp.CVE_PROCESO_PERIODICO " +
             "  AND pp.CVE_PERIODICIDAD = dp.CVE_PERIODICIDAD " +
             "  AND pp.RFC = dp.RFC " +
             "  AND pp.CONTRATO = dp.CONTRATO " +
             "  AND dp.SITUACION = :situacionEjecucion " +                         
             "  AND pp.CVE_ENTIDAD = :cveEntidad " +
             "  AND pp.CVE_LOCALIDAD = :cveLocalidad " +
             "  AND pp.CVE_IDIOMA = :cveIdioma " +
             "  AND (pp.FECHA_ULTIMA_NOTIFICACION IS NULL or pp.FECHA_ULTIMA_NOTIFICACION <> :fechaActual) " +
             "  AND pp.SITUACION_EJECUCION = :situacionEjecucion " +
             "  AND pp.FECHA_PROGRAMACION < :fechaActual " +
             "  AND dp.TIPO_EJECUCION = :tipoEjecucion " +
             " ORDER BY pp.RFC, pp.CONTRATO ",
			 nativeQuery = true)
	List<CrProgramacionProceso> encuentraProcesoVencido(
			 @Param("cveEntidad") String cveEntidad,
			 @Param("cveLocalidad") String cveLocalidad,
			 @Param("cveIdioma") String cveIdioma,
			 @Param("situacionEjecucion") String situacionEjecucion,
			 @Param("fechaActual") LocalDate fechaActual,
			 @Param("tipoEjecucion") String tipoEjecucion);
	 	 
	 
	 @Query(value = "SELECT count(1) FROM CR_PROGRAMACION_PROCESO " +
             "WHERE " +
             "  CVE_ENTIDAD = :cveEntidad " +
             "  AND CVE_PROCESO_PERIODICO = :cveProcesoPeriodico " +
             "  AND FECHA_PROGRAMACION < :fecha " +
             "  AND RFC = :rfc " +      
             "  AND CVE_PROCESO_PERIODICO = 'DEC_IVA_CONTRATISTA_COMPLEMENTARIO' " +
             "  AND CONTRATO = :contrato ",     nativeQuery = true)
	 Integer verificaProcesoComplementario(
		 @Param("cveEntidad") String cveEntidad,
		 @Param("cveProcesoPeriodico") String cveProcesoPeriodico,
		 @Param("fecha") LocalDate fecha,
		 @Param("rfc") String rfc,
		 @Param("contrato") String contrato );
	 
	/* @Query(value = "SELECT count(1)  " + 
	 		" FROM CR_PROGRAMACION_PROCESO  " + 
	 		" WHERE CVE_ENTIDAD = :cveEntidad " + 
            "  AND CVE_LOCALIDAD = :cveLocalidad " +
            "  AND CVE_IDIOMA = :cveIdioma " +
	 		"  AND RFC = :rfc " + 
	 		"  AND CONTRATO =  :contrato " +
	 		" AND CVE_PROCESO_PERIODICO =  :procesoPeriodico ", nativeQuery = true)
	 Integer existeContratoProgramnado(
			 @Param("cveEntidad") String cveEntidad,
			 @Param("cveLocalidad") String cveLocalidad,
			 @Param("cveIdioma") String cveIdioma,
			 @Param("rfc") String rfc,
			 @Param("contrato") String contrato,
			 @Param("procesoPeriodico") String procesoPeriodico);*/
	 
	 @Query(value = "SELECT count(1)  " + 
		       " FROM CR_PROGRAMACION_PROCESO  " + 
		       " WHERE CVE_ENTIDAD = :cveEntidad " + 
		            "  AND CVE_LOCALIDAD = :cveLocalidad " +
		       "  AND RFC = :rfc " + 
		       "  AND CONTRATO =  :contrato " +
		       " AND CVE_PROCESO_PERIODICO =  :procesoPeriodico ", nativeQuery = true)
		   Integer existeContratoProgramnado(
		       @Param("cveEntidad") String cveEntidad,
		       @Param("cveLocalidad") String cveLocalidad,
		       @Param("rfc") String rfc,
		       @Param("contrato") String contrato,
		       @Param("procesoPeriodico") String procesoPeriodico);
	 
	 
	 
	 @Query(value = " SELECT dp.* FROM CR_PROGRAMACION_PROCESO pp " +
             "WHERE " +        
             "  pp.CVE_ENTIDAD = :cveEntidad " +
             "  AND pp.CVE_LOCALIDAD = :cveLocalidad " +
             "  AND pp.CVE_IDIOMA = :cveIdioma " +
             "  AND pp.FECHA_PROGRAMACION > :fechaFin " +
             "  AND pp.SITUACION_EJECUCION = :situacionEjecucion ",			 
			 nativeQuery = true)
	List<CrProgramacionProceso> encuentraProcesoElimniar(
			 @Param("cveEntidad") String cveEntidad,
			 @Param("cveLocalidad") String cveLocalidad,
			 @Param("cveIdioma") String cveIdioma,
			 @Param("situacionEjecucion") String situacionEjecucion,
			 @Param("fechaFin") Date fechaFin );
	 
	 @Query(value = " SELECT pp.*  "
	 		+ "FROM CR_PROGRAMACION_PROCESO pp "
	 		+ "JOIN CR_DEFINICION_PERIODICIDAD dp  "
	 		+ "  ON pp.CVE_ENTIDAD = dp.CVE_ENTIDAD "
	 		+ " AND pp.CVE_PROCESO_PERIODICO = dp.CVE_PROCESO_PERIODICO "
	 		+ " AND pp.CVE_PERIODICIDAD = dp.CVE_PERIODICIDAD "
	 		+ " AND pp.RFC = dp.RFC "
	 		+ " AND pp.CONTRATO = dp.CONTRATO "
	 		+ "WHERE pp.CVE_ENTIDAD = :cveEntidad "
	 		+ "  AND dp.SITUACION = 'PROGRAMADO' "
	 		+ "  AND pp.SITUACION_EJECUCION = 'PROGRAMADO' "
	 		+ "  AND pp.CVE_PROCESO_PERIODICO = 'DEC_IVA_CONTRATISTA' "
	 		+ "  AND dp.RFC = :rfc  "
	 		+ "  AND pp.CONTRATO = :contrato " 
            + "  AND pp.FECHA_PROGRAMACION < :fechaActual ",
			 nativeQuery = true)
	List<CrProgramacionProceso> encuentraProcesoiIVAVencido(
			 @Param("cveEntidad") String cveEntidad,
			 @Param("rfc") String rfc,
			 @Param("contrato") String contrato,
			 @Param("fechaActual") LocalDate fechaActual
			 /*,
			 @Param("situacionEjecucion") String situacionEjecucion,
			 @Param("procesoPeriodico") String procesoPeriodico,
			 @Param("rfc") String rfc
			 */
			 );
	 
	 
 
	 @Query(value = " SELECT * FROM CR_PROGRAMACION_PROCESO CDP  " + 
		 		" WHERE CDP.CVE_ENTIDAD = :cveEntidad    " + 
		 		"	AND CDP.CVE_LOCALIDAD = :cveLocalidad  " + 
		 		"	AND CDP.CVE_IDIOMA = :cveIdioma        " + 
		 		"	AND CDP.RFC = :rfc  " + 
		 		"	AND CDP.CONTRATO = :contrato  " + 
		 		"	AND CDP.CVE_PROCESO_PERIODICO = :cveProceso  " + 
		 		" ORDER BY CDP.CVE_PROCESO_PERIODICO, CDP.SECUENCIA_PROGRAMACION  " , nativeQuery = true)
	 List<CrProgramacionProceso> obtenerInfoContratoRfcProceso(
			 @Param("cveEntidad") String cveEntidad,
			 @Param("cveLocalidad") String cveLocalidad,
			 @Param("cveIdioma") String cveIdioma,
			 @Param("cveProceso") String cveProceso,
			 @Param("rfc") String rfc,
			 @Param("contrato") String contrato);
	 
	 @Query(value = " SELECT count(1) FROM CR_PROGRAMACION_PROCESO CDP  " + 
		 		" WHERE CDP.CVE_ENTIDAD = :cveEntidad    " + 
		 		"	AND CDP.CVE_LOCALIDAD = :cveLocalidad  " + 
		 		"	AND CDP.CVE_IDIOMA = :cveIdioma        " + 
		 		"	AND CDP.RFC = :rfc  " + 
		 		"	AND CDP.CONTRATO = :contrato  " + 
		 		"	AND CDP.CVE_PROCESO_PERIODICO = :cveProceso  " + 
		 		"	AND MONTH(CDP.FECHA_PROGRAMACION) = MONTH(:fechaActual) " +
		 		"	AND YEAR(CDP.FECHA_PROGRAMACION) = YEAR(:fechaActual) ", nativeQuery = true)
	 int totalRegistrosProceso(
			 @Param("cveEntidad") String cveEntidad,
			 @Param("cveLocalidad") String cveLocalidad,
			 @Param("cveIdioma") String cveIdioma,
			 @Param("cveProceso") String cveProceso,
			 @Param("rfc") String rfc,
			 @Param("contrato") String contrato,
			 @Param("fechaActual") Date fechaActual);
	 
	 @Query(value = " SELECT  CDP.CVE_PROCESO_PERIODICO, count(1) AS TOTAL " +
	 		    " FROM CR_PROGRAMACION_PROCESO CDP  " + 
		 		" WHERE CDP.CVE_ENTIDAD = :cveEntidad    " + 
		 		"	AND CDP.CVE_LOCALIDAD = :cveLocalidad  " + 
		 		"	AND CDP.CVE_IDIOMA = :cveIdioma        " + 
		 		"	AND CDP.RFC = :rfc  " + 
		 		"	AND CDP.CONTRATO = :contrato  " + 
		 		" GROUP BY CVE_PROCESO_PERIODICO " +
		 		" ORDER BY TOTAL DESC ", nativeQuery = true)	 
	 List<Object[]> procesoPeriodo(
			 @Param("cveEntidad") String cveEntidad,
			 @Param("cveLocalidad") String cveLocalidad,
			 @Param("cveIdioma") String cveIdioma,
			 @Param("rfc") String rfc,
			 @Param("contrato") String contrato);
	 
	 @Query(value = " SELECT  CDP.CVE_PROCESO_PERIODICO, count(1) AS TOTAL " +
	 		    " FROM CR_PROGRAMACION_PROCESO CDP, CR_PROCESO_PERIODICO CPP  " + 
		 		" WHERE CDP.CVE_ENTIDAD = :cveEntidad    " + 
		 		"	AND CDP.CVE_LOCALIDAD = :cveLocalidad  " + 
		 		"	AND CDP.CVE_IDIOMA = :cveIdioma        " + 
		 		"	AND CDP.RFC = :rfc  " + 
		 		"	AND CDP.CONTRATO = :contrato  " + 
		 		"   AND CPP.CVE_ENTIDAD = CDP.CVE_ENTIDAD " + 
		 		"	AND CPP.CVE_LOCALIDAD = CDP.CVE_LOCALIDAD " + 
		 		"	AND CPP.CVE_IDIOMA = CDP.CVE_IDIOMA " + 
		 		"	AND CPP.CVE_PROCESO_PERIODICO = CDP.CVE_PROCESO_PERIODICO " + 		
		 		
		 		" GROUP BY CDP.CVE_PROCESO_PERIODICO " +
		 		" ORDER BY TOTAL DESC ", nativeQuery = true)	 
	 List<Object[]> procesoPeriodoSinMulticarga(
			 @Param("cveEntidad") String cveEntidad,
			 @Param("cveLocalidad") String cveLocalidad,
			 @Param("cveIdioma") String cveIdioma,
			 @Param("rfc") String rfc,
			 @Param("contrato") String contrato);
	 
	 @Query(value = " SELECT CDP.* FROM CR_PROGRAMACION_PROCESO CDP, CR_PROCESO_PERIODICO CPP  " + 
		 		" WHERE CDP.CVE_ENTIDAD = :cveEntidad    " + 
		 		"	AND CDP.CVE_LOCALIDAD = :cveLocalidad  " + 
		 		"	AND CDP.CVE_IDIOMA = :cveIdioma        " + 
		 		"	AND CDP.RFC = :rfc  " + 
		 		"	AND CDP.CONTRATO = :contrato  " + 
		 		"	AND CDP.CVE_PROCESO_PERIODICO = :cveProceso  " + 
		 		"   AND CPP.CVE_ENTIDAD = CDP.CVE_ENTIDAD " + 
		 		"	AND CPP.CVE_LOCALIDAD = CDP.CVE_LOCALIDAD " + 
		 		"	AND CPP.CVE_IDIOMA = CDP.CVE_IDIOMA " + 
		 		"	AND CPP.CVE_PROCESO_PERIODICO = CDP.CVE_PROCESO_PERIODICO " + 	 		

		 		" ORDER BY CDP.CVE_PROCESO_PERIODICO, CDP.SECUENCIA_PROGRAMACION  " , nativeQuery = true)
	 List<CrProgramacionProceso> obtenerInfoContRfcProcSinMulCarga(
			 @Param("cveEntidad") String cveEntidad,
			 @Param("cveLocalidad") String cveLocalidad,
			 @Param("cveIdioma") String cveIdioma,
			 @Param("cveProceso") String cveProceso,
			 @Param("rfc") String rfc,
			 @Param("contrato") String contrato);
	 
	 
	 @Query(value = " SELECT CDP.SITUACION_EJECUCION FROM CR_PROGRAMACION_PROCESO CDP  " + 
		 		" WHERE CDP.CVE_ENTIDAD = :cveEntidad    " + 
		 		"	AND CDP.CVE_LOCALIDAD = :cveLocalidad  " + 
		 		"	AND CDP.CVE_IDIOMA = :cveIdioma        " + 
		 		"	AND CDP.RFC = :rfc  " + 
		 		"	AND CDP.CONTRATO = :contrato  " + 
		 		"	AND CDP.CVE_PROCESO_PERIODICO = :cveProceso  " + 
		 		"	AND CDP.FECHA_PROGRAMACION = :fechaCarga " , nativeQuery = true)
	 String situacionEjecucion (
			 @Param("cveEntidad") String cveEntidad,
			 @Param("cveLocalidad") String cveLocalidad,
			 @Param("cveIdioma") String cveIdioma,
			 @Param("cveProceso") String cveProceso,
			 @Param("rfc") String rfc,
			 @Param("contrato") String contrato,
			 @Param("fechaCarga") Date fechaCarga);
	 
	 @Query(value = " SELECT A.FECHA_PROGRAMACION, COUNT (A.FECHA_PROGRAMACION) FROM " + 
	 		"(SELECT DISTINCT FECHA_PROGRAMACION, MONTH(FECHA_PROGRAMACION) AS MES FROM CR_PROGRAMACION_PROCESO  " + 
	 		"WHERE CVE_ENTIDAD = :cveEntidad " + 
	 		"AND CVE_LOCALIDAD = :cveLocalidad " + 
	 		"AND CVE_IDIOMA = :cveIdioma " + 
	 		"AND RFC = :rfc " + 
	 		"AND CONTRATO = :contrato " + 
	 		") A GROUP BY FECHA_PROGRAMACION", nativeQuery = true)
	 List<Object[]> obtenerFechas (
			 @Param("cveEntidad") String cveEntidad,
			 @Param("cveLocalidad") String cveLocalidad,
			 @Param("cveIdioma") String cveIdioma,
			 @Param("rfc") String rfc,
			 @Param("contrato") String contrato);
	 
	 
	 @Query(value = "SELECT FECHA_PROGRAMACION,  CVE_PROCESO_PERIODICO FROM CR_PROGRAMACION_PROCESO  " + 
		 		"WHERE CVE_ENTIDAD = :cveEntidad " + 
		 		"AND CVE_LOCALIDAD = :cveLocalidad " + 
		 		"AND CVE_IDIOMA = :cveIdioma " + 
		 		"AND RFC = :rfc " + 
		 		"AND CONTRATO = :contrato ORDER BY FECHA_PROGRAMACION " , nativeQuery = true)
		 List<Object[]> obtenerFechasGeneral (
				 @Param("cveEntidad") String cveEntidad,
				 @Param("cveLocalidad") String cveLocalidad,
				 @Param("cveIdioma") String cveIdioma,
				 @Param("rfc") String rfc,
				 @Param("contrato") String contrato);
	 
		 @Query(value = " SELECT * FROM CR_PROGRAMACION_PROCESO pp " +
	             "WHERE pp.CVE_ENTIDAD = :cveEntidad " +
	             "  AND pp.CVE_LOCALIDAD = :cveLocalidad " +
	             "  AND pp.CVE_IDIOMA = :cveIdioma " +
	             "  AND pp.CVE_PROCESO_PERIODICO = :cveProceso " +
	             "  AND pp.CVE_PERIODICIDAD = :cvePeriodicidad " +
	             "  AND pp.RFC = :rfc " +
	             "  AND pp.CONTRATO = :contrato " +
	             "  AND pp.FECHA_PROGRAMACION = :fecha " ,			 
				 nativeQuery = true)
		 CrProgramacionProceso validaProgramacion(
				 @Param("cveEntidad") String cveEntidad,
				 @Param("cveLocalidad") String cveLocalidad,
				 @Param("cveIdioma") String cveIdioma,
				 @Param("cveProceso") String cveProceso,
				 @Param("cvePeriodicidad") String cvePeriodicidad,
				 @Param("rfc") String rfc,
				 @Param("contrato") String contrato, 
				 @Param("fecha") Date fecha);
		 
		 @Query(value = " SELECT MAX(SECUENCIA_PROGRAMACION) FROM CR_PROGRAMACION_PROCESO pp " +
	             "WHERE pp.CVE_ENTIDAD = :cveEntidad " +
	             "  AND pp.CVE_LOCALIDAD = :cveLocalidad " +
	             "  AND pp.CVE_IDIOMA = :cveIdioma " +
	             "  AND pp.CVE_PROCESO_PERIODICO = :cveProceso " +
	             "  AND pp.CVE_PERIODICIDAD = :cvePeriodicidad " +
	             "  AND pp.RFC = :rfc " +
	             "  AND pp.CONTRATO = :contrato " ,			 
				 nativeQuery = true)
		 int maximaProgramacion(
				 @Param("cveEntidad") String cveEntidad,
				 @Param("cveLocalidad") String cveLocalidad,
				 @Param("cveIdioma") String cveIdioma,
				 @Param("cveProceso") String cveProceso,
				 @Param("cvePeriodicidad") String cvePeriodicidad,
				 @Param("rfc") String rfc,
				 @Param("contrato") String contrato);
		 
		 @Query(value = " SELECT * FROM CR_PROGRAMACION_PROCESO pp " +
	             "WHERE pp.CVE_ENTIDAD = :cveEntidad " +
	             "  AND pp.CVE_LOCALIDAD = :cveLocalidad " +
	             "  AND pp.CVE_IDIOMA = :cveIdioma " +
	             "  AND pp.CVE_PROCESO_PERIODICO = :cveProceso " +
	             "  AND pp.CVE_PERIODICIDAD = :cvePeriodicidad " +
	             "  AND pp.RFC = :rfc " +
	             "  AND pp.CONTRATO = :contrato " ,			 
				 nativeQuery = true)
		 List<CrProgramacionProceso> depuraProgramacion(
				 @Param("cveEntidad") String cveEntidad,
				 @Param("cveLocalidad") String cveLocalidad,
				 @Param("cveIdioma") String cveIdioma,
				 @Param("cveProceso") String cveProceso,
				 @Param("cvePeriodicidad") String cvePeriodicidad,
				 @Param("rfc") String rfc,
				 @Param("contrato") String contrato);
		 
		 @Query(value = " SELECT FECHA_PROGRAMACION FROM CR_PROGRAMACION_PROCESO  " + 
			 		" WHERE CVE_ENTIDAD = :cveEntidad " + 
			 		"  AND CVE_LOCALIDAD = :cveLocalidad " + 
			 		"  AND CVE_IDIOMA = :cveIdioma " + 
			 		"  AND CVE_PROCESO_PERIODICO = :cveProceso " +
			 		"  AND RFC = :rfc " + 
			 		"  AND CONTRATO = :contrato ORDER BY FECHA_PROGRAMACION " , nativeQuery = true)
		List<Date> obtenerFechasProceso (
					 @Param("cveEntidad") String cveEntidad,
					 @Param("cveLocalidad") String cveLocalidad,
					 @Param("cveIdioma") String cveIdioma,
					 @Param("rfc") String rfc,
					 @Param("contrato") String contrato,
					 @Param("cveProceso") String cveProceso);
		 @Modifying
		 @Transactional
		 @Query(value = " UPDATE CR_PROGRAMACION_PROCESO  " + 
				   	" SET FECHA_EJECUCION = null, SITUACION_EJECUCION = 'PROGRAMADO' " + 
			 		" WHERE CVE_ENTIDAD = :cveEntidad " + 
			 		"  AND CVE_LOCALIDAD = :cveLocalidad " + 
			 		"  AND CVE_IDIOMA = :cveIdioma " + 
			 		"  AND CVE_PROCESO_PERIODICO = :cveProceso " +
			 		"  AND RFC = :rfc " + 
			 		"  AND CONTRATO = :contrato  " +
			 		"  AND FECHA_PROGRAMACION = :fecha " +
			 		"  AND SECUENCIA_PROGRAMACION = :secuencia  "
			 		, nativeQuery = true)
		 void updateProceso (
					 @Param("cveEntidad") String cveEntidad,
					 @Param("cveLocalidad") String cveLocalidad,
					 @Param("cveIdioma") String cveIdioma,
					 @Param("cveProceso") String cveProceso,
					 @Param("rfc") String rfc,
					 @Param("contrato") String contrato,
					 @Param("fecha") int secuenciaProgramacion,
					 @Param("secuencia") Date fechaCarga
					);
		 
		 @Query(value = " SELECT * FROM CR_PROGRAMACION_PROCESO CDP  " + 
			 		" WHERE CDP.CVE_ENTIDAD = :cveEntidad    " + 
			 		"	AND CDP.CVE_LOCALIDAD = :cveLocalidad  " + 
			 		"	AND CDP.CVE_IDIOMA = UPPER(:cveIdioma)        " + 
			 		"	AND CDP.RFC = :rfc  " + 
			 		"	AND CDP.CONTRATO = :contrato  " + 			 		
			 		" AND CVE_PROCESO_PERIODICO IN ('COM_PAG_FACTURA','FACTURA','OTRAS_FACTURAS','VERIFICACION_REPSE')" +
			 		" ORDER BY CDP.CVE_PROCESO_PERIODICO, CDP.SECUENCIA_PROGRAMACION  " , nativeQuery = true)
		 List<CrProgramacionProceso> obtenerInfoContratoRfcProcesoFactura(
				 @Param("cveEntidad") String cveEntidad,
				 @Param("cveLocalidad") String cveLocalidad,
				 @Param("cveIdioma") String cveIdioma,				
				 @Param("rfc") String rfc,
				 @Param("contrato") String contrato);
		 
}
