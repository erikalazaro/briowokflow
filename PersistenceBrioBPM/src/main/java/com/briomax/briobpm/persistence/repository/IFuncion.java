package com.briomax.briobpm.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.Funcion;

/**
 * El objetivo de la Interface IFuncion.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Nov 22, 2024 Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface IFuncion extends JpaRepository<Funcion, String>{

	@Query(value = "SELECT distinct	F.* " + 
			"	FROM FUNCION F, " + 
			"		USUARIO_FACULTAD		UF, " + 
			"		FACULTAD_FUNCION_ACCION	FFA " + 
			"	WHERE F.CVE_FUNCION = FFA.CVE_FUNCION	AND " + 
			"		F.SITUACION = 'HABILITADO'			AND " + 
			"		UF.CVE_ENTIDAD =:cveEntidad			AND " + 
			"		UF.CVE_USUARIO =:cveUsuario			AND " + 
			"		UF.CVE_FACULTAD = FFA.CVE_FACULTAD	AND " + 
			"		F.CVE_FUNCION = FFA.CVE_FUNCION 	AND " + 
			"		F.DES_TIPO_FUNCION IS NOT NULL " +
			"	ORDER BY F.TIPO_FUNCION, DES_TIPO_FUNCION, DESCRIPCION_CORTA  ", nativeQuery = true)
    	List<Funcion> obtenFuncionUsuario(@Param("cveEntidad") String cveEntidad, @Param("cveUsuario") String cveUsuario);

	@Query(value = "SELECT distinct	F.* " + 
			"	FROM FUNCION F, " + 
			"		USUARIO_FACULTAD		UF, " + 
			"		FACULTAD_FUNCION_ACCION	FFA " + 
			"	WHERE F.CVE_FUNCION = FFA.CVE_FUNCION	AND " + 
			"		F.SITUACION = 'HABILITADO'			AND " + 
			"		UF.CVE_ENTIDAD =:cveEntidad			AND " + 
			"		UF.CVE_USUARIO =:cveUsuario			AND " + 
			"		UF.CVE_FACULTAD = FFA.CVE_FACULTAD	AND " + 
			"		F.CVE_FUNCION = FFA.CVE_FUNCION 	AND " +
			"		F.TIPO_FUNCION = 'DASHBOARD' 	AND " +
			"		F.DES_TIPO_FUNCION IS NOT NULL " +
			"	ORDER BY F.TIPO_FUNCION ", nativeQuery = true)
    	List<Funcion> obtenFuncionUsuarioDashboard(@Param("cveEntidad") String cveEntidad, @Param("cveUsuario") String cveUsuario);

}
