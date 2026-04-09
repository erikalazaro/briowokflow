package com.briomax.briobpm.persistence.repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.InBitacoraNodo;
import com.briomax.briobpm.persistence.entity.InBitacoraNodoPK;

/**
 * El objetivo de la Interface InVariableProcesoRepository.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Dic 05, 2023 Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface IInBitacoraNodoRepository extends JpaRepository<InBitacoraNodo, InBitacoraNodoPK> {
	@Query(value = " SELECT INBN.FECHA_EVENTO	FECHA_ACCION,  " +                         
			"		INBN.DESCRIPCION_EVENTO	AS	ACCION, " +
			"		CASE  " +
			"			WHEN	U.CVE_USUARIO IS NULL " +
			"				THEN	INBN.CVE_USUARIO_EVENTO " +
			"				ELSE	U.NOMBRE " +
			"		END 	AS	USUARIO_ACCION " +
			"		FROM	IN_BITACORA_NODO INBN " +
			"				LEFT OUTER JOIN USUARIO	U " +
			"					ON	INBN.CVE_ENTIDAD = U.CVE_ENTIDAD AND " +
			"						INBN.CVE_USUARIO_EVENTO = U.CVE_USUARIO " +
			"		WHERE " +
			"			INBN.CVE_ENTIDAD = :cveEntidad AND " +
			" 			INBN.CVE_PROCESO = :cveProceso AND " +
			" 			INBN.VERSION = :version  AND " +
			" 			INBN.CVE_INSTANCIA = :cveInstancia AND " +
			" 			INBN.CVE_NODO = :cveNodo  AND " +
			" 			INBN.ID_NODO = :idNodo  AND " +
			" 			INBN.SECUENCIA_NODO = :secNodo " +
			" ORDER BY INBN.FECHA_EVENTO ", nativeQuery = true)
		List<Object> leeBitacoraNodo(@Param("cveEntidad") String cveEntidad,
                                       @Param("cveProceso") String cveProceso,
                                       @Param("version") BigDecimal version,
                                       @Param("cveInstancia") String cveInstancia,
                                       @Param("cveNodo") String cveNodo,
                                       @Param("idNodo") BigInteger idNodo,
                                       @Param("secNodo") BigInteger secNodo);
}
