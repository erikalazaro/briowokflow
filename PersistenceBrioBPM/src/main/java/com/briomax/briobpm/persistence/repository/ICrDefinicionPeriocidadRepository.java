package com.briomax.briobpm.persistence.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.persistence.entity.CrDefinicionPeriocidad;
import com.briomax.briobpm.persistence.entity.CrDefinicionPeriocidadPK;

@Repository
public interface ICrDefinicionPeriocidadRepository extends JpaRepository<CrDefinicionPeriocidad, CrDefinicionPeriocidadPK>{

	@Query("SELECT d FROM CrDefinicionPeriocidad d " + 
			"WHERE d.situacion = :situacion " +
			" AND d.id.cveEntidad= :cveEntidad " +
			" AND d.id.cveLocalidad = :cveLocalidad " +
			" AND d.id.cveIdioma = :cveIdioma " )
    List<CrDefinicionPeriocidad> procesosDefinidos(
			 @Param("cveEntidad") String cveEntidad,
			 @Param("cveLocalidad") String cveLocalidad,
			 @Param("cveIdioma") String cveIdioma,
    		 @Param("situacion") String situacion);
	
/*	@Query(value =" SELECT crp.* FROM CR_DEFINICION_PERIODICIDAD crp, " + 
			" IN_VARIABLE_PROCESO iv, " + 
			" IN_VARIABLE_PROCESO ivr " + 
			" WHERE crp.CVE_ENTIDAD = iv.CVE_ENTIDAD " + 
			" AND iv.CVE_PROCESO in ('CONT_SERV_ESPE_CTE_CC', 'CONT_SERV_ESPE_CTE') " + 
			" AND iv.CVE_VARIABLE = 'VPRO_01_NUM_CONTRATO'  " + 
			" AND iv.VALOR_ALFANUMERICO = crp.CONTRATO " + 
			" AND iv.CVE_ENTIDAD = ivr.CVE_ENTIDAD " +
			" AND iv.CVE_PROCESO = ivr.CVE_PROCESO " +
			" AND iv.CVE_INSTANCIA = ivr.CVE_INSTANCIA " +
			" AND ivr.CVE_VARIABLE = 'VPRO_01_RAZON'  " +
			" AND ivr.VALOR_ALFANUMERICO = :razon", nativeQuery = true )
    List<CrDefinicionPeriocidad> obtenProcesosConAjuste(String razon);*/
	
	@Query("SELECT crp " +
		       " FROM CrDefinicionPeriocidad crp, "
		       + " InVariableProceso iv, "
		       + " InVariableProceso ivr,"
		       + " InVariableProceso ivp " +
		       " WHERE crp.id.cveEntidad= iv.id.cveEntidad" +
		       " AND iv.id.cveProceso IN ('CONT_SERV_ESPE_CTE_CC', 'CONT_SERV_ESPE_CTE') " +
		       " AND iv.id.cveVariable = 'VPRO_01_NUM_CONTRATO' " +
		       " AND iv.valorAlfanumerico = crp.id.contrato " + 
		       " AND iv.id.cveEntidad = ivr.id.cveEntidad" +
		       " AND iv.id.cveProceso = ivr.id.cveProceso " +
		       " AND iv.id.cveInstancia = ivr.id.cveInstancia " +
		       " AND ivr.id.cveEntidad = ivp.id.cveEntidad " +
		       " AND ivr.id.cveProceso = ivp.id.cveProceso " +
		       " AND ivr.id.cveInstancia = ivp.id.cveInstancia " +
		       " AND ivr.id.cveVariable = 'VPRO_01_RAZON' " +
		       " AND ivp.id.cveVariable = 'VPRO_01_NVO_PER_AL' " +
		       " AND crp.hasta <> ivp.valorFecha   " +
		       " AND ivr.valorAlfanumerico in (:razon)")
		List<CrDefinicionPeriocidad> obtenProcesosConAjuste(@Param("razon") String razon);
	
	@Query(value ="SELECT crp.* FROM CR_DEFINICION_PERIODICIDAD crp, IN_VARIABLE_PROCESO iv, " + 
			" IN_VARIABLE_PROCESO ivr " +
			"WHERE crp.CVE_ENTIDAD = iv.CVE_ENTIDAD " + 
			"AND iv.CVE_PROCESO in ('CONT_SERV_ESPE_CTE_CC', 'CONT_SERV_ESPE_CTE') " + 
			"AND iv.CVE_VARIABLE = 'VPRO_01_NUM_CONTRATO'  " + 
			"AND iv.VALOR_ALFANUMERICO = crp.CONTRATO " +
			" AND iv.CVE_ENTIDAD = ivr.CVE_ENTIDAD" +
		    " AND iv.CVE_PROCESO = ivr.CVE_PROCESO " +
		    " AND iv.CVE_INSTANCIA = ivr.CVE_INSTANCIA " +
			" AND ivr.CVE_VARIABLE = 'VPRO_01_RAZON' "  +
			" AND ivr.VALOR_ALFANUMERICO not in( 'POSTERGACION', 'REANUDACION') " +
			" AND crp.SITUACION_ENVIO = 'HABILITADO' "
			, nativeQuery = true )
    List<CrDefinicionPeriocidad> obtenOtrosProcesos();
	
	@Query(value ="SELECT crp.* FROM CR_DEFINICION_PERIODICIDAD crp, IN_VARIABLE_PROCESO iv, " + 
			" IN_VARIABLE_PROCESO ivr, " +
			" IN_VARIABLE_PROCESO ivp " +
			"WHERE crp.CVE_ENTIDAD = iv.CVE_ENTIDAD " + 
			"AND iv.CVE_PROCESO in ('CONT_SERV_ESPE_CTE_CC', 'CONT_SERV_ESPE_CTE') " + 
			"AND iv.CVE_VARIABLE = 'VPRO_01_NUM_CONTRATO'  " + 
			"AND iv.VALOR_ALFANUMERICO = crp.CONTRATO " +
			" AND iv.CVE_ENTIDAD = ivr.CVE_ENTIDAD" +
		    " AND iv.CVE_PROCESO = ivr.CVE_PROCESO " +
			" AND ivr.CVE_ENTIDAD = ivp.CVE_ENTIDAD " +
			"	AND ivr.CVE_PROCESO = ivp.CVE_PROCESO  " +
			"	AND ivr.CVE_INSTANCIA = ivp.CVE_INSTANCIA " +
		    " AND iv.CVE_INSTANCIA = ivr.CVE_INSTANCIA " +
			" AND ivr.CVE_VARIABLE = 'VPRO_01_RAZON' "  +

			" AND ivp.CVE_VARIABLE = 'VPRO_01_NVO_PER_AL' "  +
			" AND ivr.VALOR_ALFANUMERICO  in('REANUDACION') "
			, nativeQuery = true )
    List<CrDefinicionPeriocidad> obtenProcesosReanudacion();
	
	
	@Query(value ="SELECT * FROM CR_DEFINICION_PERIODICIDAD crp " + 
			"WHERE 0 = (SELECT COUNT(cpp.CONTRATO) FROM CR_PROGRAMACION_PROCESO cpp " + 
			"WHERE crp.CVE_ENTIDAD = cpp.CVE_ENTIDAD " + 
			"AND crp.CVE_LOCALIDAD = cpp.CVE_LOCALIDAD " + 
			"AND crp.CVE_IDIOMA = cpp.CVE_IDIOMA " + 
			"AND crp.CVE_PROCESO_PERIODICO = cpp.CVE_PROCESO_PERIODICO " + 
			"AND crp.CVE_PERIODICIDAD = cpp.CVE_PERIODICIDAD " + 
			"AND crp.RFC = cpp.RFC " + 
			"AND crp.CONTRATO = cpp.CONTRATO) ", nativeQuery = true )
    List<CrDefinicionPeriocidad> obtenProcesosProgramados();
	
	@Query("SELECT d FROM CrDefinicionPeriocidad d " + 
			"WHERE d.id.cveProcesoPeriodico = :cveProcesoPeriodico " +
			" AND d.id.cvePeriodicidad= :cvePeriodicidad " +
			" AND d.id.cveEntidad= :cveEntidad " +
			" AND d.id.cveLocalidad = :cveLocalidad " +
			" AND d.id.cveIdioma = :cveIdioma " )
    List<CrDefinicionPeriocidad> procesosDefByProceso(
			 @Param("cveEntidad") String cveEntidad,
			 @Param("cveLocalidad") String cveLocalidad,
			 @Param("cveIdioma") String cveIdioma,
    		 @Param("cveProcesoPeriodico") String cveProcesoPeriodico,
    		 @Param("cvePeriodicidad") String cvePeriodicidad);
	
	
	@Modifying
    @Transactional
    @Query(value = "  UPDATE CR_DEFINICION_PERIODICIDAD " + 
    		" SET NOTIFICACION_CONTINUA = :notContinua, DIAS_PARA_NOTIFICAR = :dia, APLICA_INICIO = :aplicaIni " + 
    		" WHERE CVE_ENTIDAD = :cveEntidad " + 
    		" AND CVE_LOCALIDAD = :cveLocalidad " + 
    		" AND CVE_IDIOMA = :cveIdioma " + 
    		" AND CVE_PROCESO_PERIODICO = :cveProceso " +
    		" AND RFC IN (SELECT DISTINCT cau.RFC FROM CR_ACCESO_USUARIO cau " + 
    	    "							WHERE cau.CVE_ENTIDAD = :cveEntidad  " + 
    	    "							AND cau.CVE_USUARIO = :cveUsuario) ", nativeQuery = true)
    void actualizaProcesos(@Param("cveEntidad") String cveEntidad,
			 @Param("cveLocalidad") String cveLocalidad,
			 @Param("cveIdioma") String cveIdioma,   		
			 @Param("cveProceso") String cveProceso,
			 @Param("cveUsuario") String cveUsuario,
			 @Param("notContinua") String notContinua,
			 @Param("aplicaIni") String aplicaIni,
			 @Param("dia") Integer dia);
	
	
	@Query(value ="SELECT DISTINCT CONTRATO, RFC FROM CR_DEFINICION_PERIODICIDAD " + 
			"WHERE CVE_PROCESO_PERIODICO = :cveProcesoPeriodico " +
			" AND CVE_PERIODICIDAD= :cvePeriodicidad " +
			" AND CVE_ENTIDAD = :cveEntidad " +
			" AND CVE_LOCALIDAD = :cveLocalidad " +
			" AND CVE_IDIOMA = :cveIdioma " +
    		" AND RFC IN (SELECT DISTINCT cau.RFC FROM CR_ACCESO_USUARIO cau " + 
    	    "							WHERE cau.CVE_ENTIDAD = :cveEntidad  " + 
    	    "							AND cau.CVE_USUARIO = :cveUsuario) ", nativeQuery = true)
    List<Object> contratoRfcProceso(
			 @Param("cveEntidad") String cveEntidad,
			 @Param("cveLocalidad") String cveLocalidad,
			 @Param("cveIdioma") String cveIdioma,
    		 @Param("cveProcesoPeriodico") String cveProcesoPeriodico,
    		 @Param("cvePeriodicidad") String cvePeriodicidad,
    		 @Param("cveUsuario") String cveUsuario);
}
