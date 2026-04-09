package com.briomax.briobpm.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.CrProcesoPeriodico;
import com.briomax.briobpm.persistence.entity.CrProcesoPeriodicoPK;

@Repository
public interface ICrProcesoPeriodicoRepository extends JpaRepository<CrProcesoPeriodico, CrProcesoPeriodicoPK>{
	
	@Query(value = "SELECT * FROM CR_PROCESO_PERIODICO c " +
            "WHERE c.CVE_ENTIDAD = :cveEntidad " +
            "AND c.CVE_LOCALIDAD = :cveLocalidad " +
            "AND c.CVE_IDIOMA = :cveIdioma " +
            "AND EXISTS ( " +
            "    SELECT 1 FROM CR_DEFINICION_PERIODICIDAD d " +
            "    WHERE c.CVE_ENTIDAD = d.CVE_ENTIDAD " +
            "    AND c.CVE_LOCALIDAD = d.CVE_LOCALIDAD " +
            "    AND c.CVE_IDIOMA = d.CVE_IDIOMA " +
            "    AND c.CVE_PROCESO_PERIODICO = d.CVE_PROCESO_PERIODICO " +
            "    AND d.SITUACION = 'PROGRAMADO' " +
            ")", nativeQuery = true)
	List<CrProcesoPeriodico> getProcesosPeriodicos(@Param("cveEntidad") String cveEntidad, 
                                            @Param("cveLocalidad") String cveLocalidad,
                                            @Param("cveIdioma") String cveIdioma);
	
	
	@Query(value = "SELECT CPP.*  " + 
			" FROM CR_DEFINICION_PERIODICIDAD CDP, CR_PROCESO_PERIODICO CPP  " + 
			" WHERE CDP.CVE_ENTIDAD = :cveEntidad  " + 
			"	AND CDP.CVE_LOCALIDAD = :cveLocalidad  " + 
			"	AND CDP.CVE_IDIOMA = :cveIdioma  " + 
			"	AND CDP.RFC = :rfc  " + 
			"	AND CDP.CONTRATO = :contrato  " + 
			"	AND CDP.SITUACION = 'PROGRAMADO'  " + 

			"	AND CDP.CVE_ENTIDAD = CPP.CVE_ENTIDAD  " + 
			"	AND CDP.CVE_LOCALIDAD = CPP.CVE_LOCALIDAD  " + 
			"	AND CDP.CVE_IDIOMA = CPP.CVE_IDIOMA  " + 
			"	AND CDP.CVE_PROCESO_PERIODICO = CPP.CVE_PROCESO_PERIODICO ", nativeQuery = true)
	List<CrProcesoPeriodico> getProcesosPeriodicosByRfcContrato(@Param("cveEntidad") String cveEntidad, 
                                            @Param("cveLocalidad") String cveLocalidad,
                                            @Param("cveIdioma") String cveIdioma,
                                            @Param("rfc") String rfc,
                                            @Param("contrato") String contrato);
	
	
	@Query(value = "SELECT DISTINCT cdp.CVE_PROCESO_PERIODICO, cdp.CVE_PERIODICIDAD, cpp.DESCRIPCION, " + 
			"		cp.DESCRIPCION as desPeriodo, cp.DIAS_DEL_MES, " + 
			" 		cdp.DIAS_PARA_NOTIFICAR, cdp.APLICA_INICIO, cdp.NOTIFICACION_CONTINUA " + 
			"FROM CR_PERIODICIDAD cp, CR_PROCESO_PERIODICO cpp, CR_DEFINICION_PERIODICIDAD cdp " + 
			"WHERE cdp.CVE_ENTIDAD = :cveEntidad " + 
			"AND cdp.CVE_LOCALIDAD = :cveLocalidad " + 
			"AND cdp.CVE_IDIOMA = :cveIdioma " + 
			"AND cdp.RFC IN ( SELECT  DISTINCT cau.RFC   FROM CR_ACCESO_USUARIO cau  " + 
			"				WHERE cau.CVE_ENTIDAD = :cveEntidad " + 
			"				AND cau.CVE_USUARIO = :cveUsuario ) " + 
			"AND cp.CVE_PERIODICIDAD = cdp.CVE_PERIODICIDAD " + 
			"AND cpp.CVE_ENTIDAD = cdp.CVE_ENTIDAD  " + 
			"AND cpp.CVE_LOCALIDAD = cdp.CVE_LOCALIDAD " + 
			"AND cpp.CVE_IDIOMA = cdp.CVE_IDIOMA " + 
			"AND cpp.CVE_PROCESO_PERIODICO = cdp.CVE_PROCESO_PERIODICO ", nativeQuery = true)
	List<Object> getProPerTabla(@Param("cveEntidad") String cveEntidad, 
                                            @Param("cveLocalidad") String cveLocalidad,
                                            @Param("cveIdioma") String cveIdioma,
                                            @Param("cveUsuario") String cveUsuario);


}
