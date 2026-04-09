package com.briomax.briobpm.persistence.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.CrPdfFiles;
import com.briomax.briobpm.persistence.entity.CrPdfFilesPK;


public interface ICrPdfFilesRepository extends JpaRepository<CrPdfFiles, CrPdfFilesPK> {
	
	@Query("SELECT c FROM CrPdfFiles c WHERE DATE(c.id.fechaCarga) <= DATE(:fecha)")
    List<CrPdfFiles> findByFechaCargaBeforeOrEqual(@Param("fecha") Date fecha);

	
	@Query("SELECT MAX (c.id.fechaCarga) FROM CrPdfFiles c WHERE c.id.cveEntidad =:cveEntidad  AND " +
			" c.id.cveProcesoPeriodico = :cveProcesoPeriodico AND " +
			" c.id.rfc = :rfc AND c.id.numeroContrato = :numeroContrato "	)
	 Date getMaximaFecha(@Param("cveEntidad") String cveEntidad, @Param("cveProcesoPeriodico") String cveProcesoPeriodico,             
		    	@Param("rfc") String rfc, @Param("numeroContrato") String numeroContrato);

	@Query("SELECT c FROM CrPdfFiles c " +
			" WHERE c.id.cveEntidad =:cveEntidad  AND" +
			" c.id.cveProcesoPeriodico = :cveProcesoPeriodico AND " +
			" (MONTH(c.id.fechaCarga) = MONTH(:fechaCarga) AND YEAR(c.id.fechaCarga) = YEAR(:fechaCarga)) AND " +
			" c.id.rfc = :rfc AND "	+
			" c.id.numeroContrato = :numeroContrato ")
	List <CrPdfFiles> getDocumento (@Param("cveEntidad") String cveEntidad, @Param("cveProcesoPeriodico") String cveProcesoPeriodico,             
		    	@Param("fechaCarga") Date fechaCarga, @Param("rfc") String rfc, @Param("numeroContrato") String numeroContrato);
	
	@Query("SELECT c FROM CrPdfFiles c " +
			" WHERE c.id.cveEntidad =:cveEntidad  AND" +
			" c.id.cveProcesoPeriodico = :cveProcesoPeriodico AND " +
			" (MONTH(c.id.fechaCarga) = MONTH(:fechaCarga) AND YEAR(c.id.fechaCarga) = YEAR(:fechaCarga)) AND " +
			" c.id.rfc = :rfc AND "	+
			" c.id.numeroContrato = :numeroContrato AND " +
			" c.nombreDocumento = :nombreDocumento ")
	CrPdfFiles getDocumentoByNombre (@Param("cveEntidad") String cveEntidad, @Param("cveProcesoPeriodico") String cveProcesoPeriodico,             
		    	@Param("fechaCarga") Date fechaCarga, @Param("rfc") String rfc, @Param("numeroContrato") String numeroContrato,
		    	@Param("nombreDocumento") String nombreDocumento);
	
	@Query("SELECT distinct c.nombreDocumento FROM CrPdfFiles c " +
			" WHERE c.id.cveEntidad =:cveEntidad  AND" +
			" c.id.cveProcesoPeriodico = :cveProcesoPeriodico AND " +
			" (MONTH(c.id.fechaCarga) = MONTH(:fechaCarga) AND YEAR(c.id.fechaCarga) = YEAR(:fechaCarga)) AND" +
			" c.id.rfc = :rfc AND "	+
			" c.id.numeroContrato = :numeroContrato ")
	List<String> getSecuencias (@Param("cveEntidad") String cveEntidad, @Param("cveProcesoPeriodico") String cveProcesoPeriodico,             
		    	@Param("fechaCarga") Date fechaCarga, @Param("rfc") String rfc, @Param("numeroContrato") String numeroContrato);
	
	
	@Query("SELECT distinct c.id.tipoArchivo FROM CrPdfFiles c " +
			" WHERE c.id.cveEntidad =:cveEntidad  AND" +
			" c.id.cveProcesoPeriodico = :cveProcesoPeriodico AND " +
			" (MONTH(c.id.fechaCarga) = MONTH(:fechaCarga) AND YEAR(c.id.fechaCarga) = YEAR(:fechaCarga)) AND" +
			" c.id.rfc = :rfc AND "	+
			" c.id.numeroContrato = :numeroContrato ")
	List<String> getExtensiones (@Param("cveEntidad") String cveEntidad, @Param("cveProcesoPeriodico") String cveProcesoPeriodico,             
		    	@Param("fechaCarga") Date fechaCarga, @Param("rfc") String rfc, @Param("numeroContrato") String numeroContrato);

	
	
	
}
