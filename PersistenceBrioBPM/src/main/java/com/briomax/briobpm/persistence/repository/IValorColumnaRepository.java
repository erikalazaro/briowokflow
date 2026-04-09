package com.briomax.briobpm.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.briomax.briobpm.persistence.entity.ValorColumna;
import com.briomax.briobpm.persistence.entity.ValorColumnaPK;
import com.briomax.briobpm.persistence.entity.namedquery.LeeValoresColumna;

public interface IValorColumnaRepository extends JpaRepository<ValorColumna, ValorColumnaPK> {

	
	@Query(value = "SELECT	RS.SECUENCIA_VALOR,"
			+ "				RS.ORDENAMIENTO_SECUENCIA,"
			+ "				RS.VALOR_BASE_DATOS,"
			+ "				RS.VALOR_PANTALLA"
			+ "			FROM"
			+ "			("
			+ "				SELECT		VC.SECUENCIA_VALOR				AS	SECUENCIA_VALOR,"
			+ "							CASE	TC.TIPO_ORDENAMIENTO"
			+ "								WHEN	'SECUENCIA'"
			+ "									THEN		VC.SECUENCIA_VALOR"
			+ "								ELSE	0"
			+ "							END							AS	ORDENAMIENTO_SECUENCIA,"
			+ "							VC.VALOR_BASE_DATOS				AS	VALOR_BASE_DATOS,							"
			+ "                            CASE  "
			+ "                            WHEN "
			+ "                                (SELECT	UPPER(E.CVE_IDIOMA)  "
			+ "                                FROM	ENTIDAD			E  "
			+ "                                WHERE	CVE_ENTIDAD = :cveEntidad) =   :cveIdioma  THEN VC.VALOR_PANTALLA"
			+ "                            WHEN   "
			+ "                                (SELECT COUNT(1)   "
			+ "                                FROM   TRADUCCION  "
			+ "                                WHERE  UPPER(CVE_IDIOMA) =  :cveIdioma "
			+ "                                AND PALABRA_ORIGINAL = VC.VALOR_PANTALLA) = 0 THEN  CONCAT('*', VC.VALOR_PANTALLA)  "
			+ "                            ELSE   "
			+ "                                (SELECT PALABRA_TRADUCIDA  "
			+ "                                FROM   TRADUCCION  "
			+ "                                WHERE  UPPER(CVE_IDIOMA) = :cveIdioma"
			+ "                                AND PALABRA_ORIGINAL = VC.VALOR_PANTALLA)  "
			+ "                        END  "
			+ "        						AS 	VALOR_PANTALLA"
			+ "					FROM		VALOR_COLUMNA	VC,"
			+ "							TABLA_COLUMNA	TC"
			+ "					WHERE"
			+ "							VC.NOMBRE_TABLA = :tablaColumna	AND"
			+ "							VC.NOMBRE_COLUMNA = :columna	AND"
			+ "							TC.NOMBRE_TABLA = VC.NOMBRE_TABLA		AND"
			+ "							TC.NOMBRE_COLUMNA = VC.NOMBRE_COLUMNA"
			+ "			)	RS"
			+ "			ORDER BY 	RS.ORDENAMIENTO_SECUENCIA,"
			+ "					RS.VALOR_PANTALLA", nativeQuery = true)
	List<Object>  getValoresColumna(
			@Param("cveEntidad") String cveEntidad,
			@Param("cveIdioma") String cveIdioma,
		    @Param("tablaColumna") String tablaColumna,
		    @Param("columna") String columna);
	
	
	
	

}
