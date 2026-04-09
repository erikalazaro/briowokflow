package com.briomax.briobpm.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.CatalogoEtiqueta;
import com.briomax.briobpm.persistence.entity.CatalogoEtiquetaPK;

/**
 * El objetivo de la Interface ICatalogoEtiquetaRepository.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Abr 10, 2024 Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface ICatalogoEtiquetaRepository extends JpaRepository<CatalogoEtiqueta, CatalogoEtiquetaPK> {
	
	@Query(value = "SELECT 1 " +
    		"FROM CATALOGO_ETIQUETA		CE " +
            "WHERE CE.NOMBRE_DATO = :cveDato ", nativeQuery = true)
   Integer validaCatalogo(  @Param("cveDato") String cveDato);
	
	@Query(value = "SELECT :cveDato AS CVE_DATO, " + 
			"				CE.VALOR_ALFANUMERICO AS VALOR_ALFANUMERICO, " +
			"				CE.ETIQUETA " +
    		"		FROM CATALOGO_ETIQUETA			CE " +
            "		WHERE  CE.NOMBRE_DATO = :cveDato " + 
            "		ORDER BY ORDEN_PRESENTACION ", nativeQuery = true)
    	List<Object> regresaEtiquetas(@Param("cveDato") String cveDato);
}
