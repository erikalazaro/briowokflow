package com.briomax.briobpm.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.ComposicionCorreo;
import com.briomax.briobpm.persistence.entity.ComposicionCorreoPK;

/**
 * El objetivo de la Interface IStProcesoRepository.java es ...
 * 
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Ene 09, 2024 12:30:03 PM:
 * @since JDK 1.8
 */
@Repository
public interface IComposicionCorreoRepository extends JpaRepository<ComposicionCorreo, ComposicionCorreoPK>{

	@Query(value = "SELECT 1 "+ 
            "FROM COMPOSICION_CORREO CC " +
            "WHERE CC.CVE_PROCESO = :cveProceso " +
            "AND CC.VERSION = :version ", nativeQuery = true)
	Integer existeComposicionCorreo(
		        @Param("cveProceso") String cveProceso,
		        @Param("version") BigDecimal version);
	
	@Query(value = "SELECT MAX(NUMERO_CORREO)  "+ 
            "FROM COMPOSICION_CORREO CC " +
            "WHERE CC.CVE_PROCESO = :cveProceso " +
            "AND CC.VERSION = :version ", nativeQuery = true)
	Integer obtieneNumeroCorreo(
		        @Param("cveProceso") String cveProceso,
		        @Param("version") BigDecimal version);
	
	
	@Query(value = "SELECT * "+ 
            "FROM COMPOSICION_CORREO CC " +
            "WHERE CC.CVE_PROCESO = :cveProceso " +
            "AND CC.VERSION = :version " +
            "AND CC.NUMERO_CORREO = :numeroCorreo ", nativeQuery = true)
	List<ComposicionCorreo> existeCorreoRecibido(
			@Param("cveProceso")String cveProceso,
			@Param("version")BigDecimal version,
			@Param("numeroCorreo")Integer numeroCorreo);
}
