package com.briomax.briobpm.persistence.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.dao.base.AbstractBaseDAO;
import com.briomax.briobpm.persistence.dao.base.IMenuDinamicoDAO;
import com.briomax.briobpm.persistence.entity.namedquery.ActividadProceso;
import com.briomax.briobpm.persistence.entity.namedquery.LeeMenuEstatico;
import com.briomax.briobpm.persistence.entity.namedquery.MenuPrincipal;
import com.briomax.briobpm.persistence.entity.namedquery.MenuProcesos;

/**
 * El objetivo de la clase VwDatoActividadDAO.java es proporcionar métodos 
 * para acceder y manipular datos relacionados con la vista VW_DATO_ACTIVIDAD en la base de datos.
 *
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Feb 14, 2024 Modificaciones:
 * @since JDK 1.8
 */
@Repository("menuDinamicoDAO")
public class MenuDinamicoDAO extends AbstractBaseDAO implements IMenuDinamicoDAO{ 
	
	/** 
	 * Inyeccion de EntityManager, es la interfaz principal a traves de la
	 * cual se realizan operaciones de base de datos en JPA
	 */
	@PersistenceContext 
    private EntityManager entityManager;
    
    /**
     * Metodo para obtener el valor maximo de una columna
     *  especifica con datos completos
     *  
     * @param cveColumna
     * @param cveEntidad
     * @param cveProceso
     * @param version
     * @param cveInstancia
     * @param cveNodo
     * @param idNodo
     * @param secuenciaNodo
     * @return
     * @throws BrioBPMException la brio BPM exception.
     */
	@Override
    public List<MenuPrincipal> obtenerMenu(String cveEntidad, String cveUsuario, String cveidioma) throws BrioBPMException {
        
    	/** 
    	 * Construccion de la consulta SQL dinamicamente utilizando el nombre
    	 * de la columna pasada como parametro
    	 */
        String query =  "WITH  IdiomaEntidad AS ("
        		+ "    SELECT UPPER(CVE_IDIOMA) AS CVE_IDIOMA"
        		+ "    FROM ENTIDAD"
        		+ "    WHERE CVE_ENTIDAD = 'BRIOMAX'"
        		+ "),"
        		+ " Traducciones AS ( "
        		+ "    SELECT UPPER(CVE_IDIOMA) AS CVE_IDIOMA, PALABRA_ORIGINAL, PALABRA_TRADUCIDA "
        		+ "    FROM TRADUCCION "
        		+ "    WHERE UPPER(CVE_IDIOMA) = UPPER(:cveidioma)  "
        		+ ") "
        		+ "SELECT ROW_NUMBER() OVER(ORDER BY  "
        		+ "         COALESCE(TP.PALABRA_TRADUCIDA, CONCAT('*', STP.DESCRIPCION)), "
        		+ "         COALESCE(TN.PALABRA_TRADUCIDA, CONCAT('*', STNP.DESCRIPCION)) "
        		+ "       ) AS ORDENAMIENTO, "
        		+ "       STNP.CVE_PROCESO, "
        		+ "       STNP.VERSION, "
        		+ "       COALESCE(TP.PALABRA_TRADUCIDA, CONCAT('*', STP.DESCRIPCION)) AS DESCRIPCION_PROCESO, "
        		+ "       STNP.CVE_NODO, "
        		+ "       STNP.ID_NODO, "
        		+ "       COALESCE(TN.PALABRA_TRADUCIDA, CONCAT('*', STNP.DESCRIPCION)) AS DESCRIPCION_NODO, "
        		+ "       STNP.BOTON_INICIO_PROCESO AS INICIAR_PROCESO, "
        		+ "       STNP.CVE_AREA_TRABAJO, "
        		+ "       STRN.CVE_ROL, "
        		+ "       COALESCE(TB.PALABRA_TRADUCIDA, CONCAT('*', STNP.ETIQUETA_BOTON)) AS ETIQUETA_BOTON, "
        		+ "       CASE WHEN STNP.ETIQUETA_BOTON_INICIO IS NOT NULL THEN 'SI' ELSE 'NO' END AS EJECUCION_AUTOMATICA, "
        		+ "       COALESCE(TBI.PALABRA_TRADUCIDA, CONCAT('*', STNP.ETIQUETA_BOTON_INICIO)) AS ETIQUETA_BOTON_EJECUCION_AUTOMATICA, "
        		+ "       STP.ICONO_PROCESO "
        		+ "FROM ST_PROCESO STP "
        		+ "JOIN ST_NODO_PROCESO STNP ON STP.CVE_ENTIDAD = STNP.CVE_ENTIDAD "
        		+ "                         AND STP.CVE_PROCESO = STNP.CVE_PROCESO "
        		+ "                         AND STP.VERSION = STNP.VERSION "
        		+ "JOIN ST_ROL_NODO STRN ON STRN.CVE_ENTIDAD = STNP.CVE_ENTIDAD "
        		+ "                     AND STRN.CVE_PROCESO = STNP.CVE_PROCESO "
        		+ "                     AND STRN.VERSION = STNP.VERSION "
        		+ "                     AND STRN.CVE_NODO = STNP.CVE_NODO "
        		+ "                     AND STRN.ID_NODO = STNP.ID_NODO "
        		+ "JOIN USUARIO_ROL UR ON UR.CVE_ENTIDAD = :cveEntidad "
        		+ "                   AND UR.CVE_USUARIO =  :cveUsuario "
        		+ "                   AND UR.CVE_PROCESO = STRN.CVE_PROCESO "
        		+ "                   AND UR.VERSION = STRN.VERSION "
        		+ "                   AND UR.CVE_ROL = STRN.CVE_ROL "
        		+ "JOIN IdiomaEntidad IE ON 1=1 "
        		+ "LEFT JOIN Traducciones TP ON TP.PALABRA_ORIGINAL = STP.DESCRIPCION "
        		+ "LEFT JOIN Traducciones TN ON TN.PALABRA_ORIGINAL = STNP.DESCRIPCION "
        		+ "LEFT JOIN Traducciones TB ON TB.PALABRA_ORIGINAL = STNP.ETIQUETA_BOTON "
        		+ "LEFT JOIN Traducciones TBI ON TBI.PALABRA_ORIGINAL = STNP.ETIQUETA_BOTON_INICIO "
        		+ "WHERE STNP.CVE_NODO IN ('ACTIVIDAD-USUARIO', 'ACTIVIDAD-USUARIO-TEMPORIZACION')  ";
        

        /*List result =  (List) entityManager.createNativeQuery(query)
        .setParameter("cveEntidad", cveEntidad)
        .setParameter("cveUsuario", cveUsuario)
        .setParameter("cveidioma", cveidioma)
        .getResultList();*/
        
        return executeAndTransform(query, new String[] {"cveEntidad", "cveUsuario", "cveidioma"},
    			new Object[] {cveEntidad, cveUsuario, cveidioma}, MenuPrincipal.class);
    }

	@Override
    public List<MenuPrincipal> obtenerMenuDos(String cveEntidad, String cveUsuario, String cveidioma, String temporizador) throws BrioBPMException {
        
    	/** 
    	 * Construccion de la consulta SQL dinamicamente utilizando el nombre
    	 * de la columna pasada como parametro
    	 */
        String query = "SELECT " +
        		"    ROW_NUMBER() OVER (ORDER BY T.DESCRIPCION_PROCESO, T.DESCRIPCION_NODO) AS ORDENAMIENTO, T.* " +
        		  "FROM (    SELECT DISTINCT " + 
        		    "    STNP.CVE_PROCESO, " +
        		    "    STNP.VERSION, " +
        		    "    CONCAT( " +
        		    "        COALESCE(( " +
        		    "            SELECT CASE " + 
        		    "                WHEN UPPER(E.CVE_IDIOMA) = UPPER(:cveidioma) THEN STP.DESCRIPCION " +
        		    "                WHEN NOT EXISTS ( " +
        		    "                    SELECT 1 FROM TRADUCCION " +
        		    "                    WHERE UPPER(CVE_IDIOMA) = UPPER(:cveidioma) " +
        		    "                      AND PALABRA_ORIGINAL = STNP.ETIQUETA_BOTON_INICIO " +
        		    "                ) THEN CONCAT('*', STP.DESCRIPCION) " +
        		    "                ELSE ( " +
        		    "                    SELECT PALABRA_TRADUCIDA " +
        		    "                    FROM TRADUCCION  " +
        		    "                    WHERE UPPER(CVE_IDIOMA) = UPPER(:cveidioma) " +
        		    "                      AND PALABRA_ORIGINAL = STP.DESCRIPCION " +
        		    "                ) " +
        		    "            END " +
        		    "            FROM ENTIDAD E  " +
        		    "            WHERE E.CVE_ENTIDAD = STNP.CVE_ENTIDAD " +
        		    "        ), ''), " +
        		    "        (SELECT COUNT(1) " +
        		    "            FROM IN_NODO_PROCESO INNP " +
        		    "            JOIN ST_NODO_PROCESO STNP2 ON " + 
        		    "                INNP.CVE_ENTIDAD = STNP2.CVE_ENTIDAD AND " +
        		    "                INNP.CVE_PROCESO = STNP2.CVE_PROCESO AND " +
        		    "                INNP.VERSION = STNP2.VERSION AND " +
        		    "                INNP.CVE_NODO = STNP2.CVE_NODO AND " +
        		    "                INNP.ID_NODO = STNP2.ID_NODO " +
        		    "            WHERE INNP.ESTADO = 'REGISTRO' " +
        		    "              AND ( " +
        		    "                  (STNP2.TIPO_ACCESO = 'ROL_USUARIO' AND INNP.USUARIO_CREADOR = :cveUsuario) " +
        		    "                  OR (STNP2.TIPO_ACCESO = 'ROL' AND EXISTS ( " +
        		    "                      SELECT 1 " +
        		    "                      FROM USUARIO_ROL UR " +
        		    "                      JOIN ST_ROL_NODO STRN ON  " +
        		    "                          STRN.CVE_ENTIDAD = STNP2.CVE_ENTIDAD AND " +
        		    "                          STRN.CVE_PROCESO = STNP2.CVE_PROCESO AND " +
        		    "                          STRN.VERSION = STNP2.VERSION AND " +
        		    "                          STRN.CVE_NODO = INNP.CVE_NODO AND " +
        		    "                          STRN.ID_NODO = INNP.ID_NODO AND " +
        		    "                          UR.CVE_ENTIDAD = STRN.CVE_ENTIDAD AND " +
        		    "                          UR.CVE_USUARIO = :cveUsuario AND " +
        		    "                          UR.CVE_PROCESO = STRN.CVE_PROCESO AND " +
        		    "                          UR.VERSION = STRN.VERSION AND " +
        		    "                          UR.CVE_ROL = STRN.CVE_ROL " +
        		    "                  )) " +
        		    "                  OR EXISTS ( " +
        		    "                      SELECT 1  " +
        		    "                      FROM IN_NODO_PROCESO_USUARIO " +
        		    "                      WHERE CVE_ENTIDAD = INNP.CVE_ENTIDAD AND " +
        		    "                            CVE_PROCESO = INNP.CVE_PROCESO AND " +
        		    "                            VERSION = INNP.VERSION AND " +
        		    "                            CVE_INSTANCIA = INNP.CVE_INSTANCIA AND " +
        		    "                            CVE_NODO = INNP.CVE_NODO AND " +
        		    "                            ID_NODO = INNP.ID_NODO AND " +
        		    "                            SECUENCIA_NODO = INNP.SECUENCIA_NODO AND " +
        		    "                            CVE_USUARIO = :cveUsuario " +
        		    "                  ) " +
        		    "              ) " +
        		    "        ) " +
        		    "    ) AS DESCRIPCION_PROCESO, " +
        		    "    STNP.CVE_NODO, " +
        		    "    1 AS ID_NODO, " +
        		    "    CONCAT( " +
        		    "        COALESCE((  " +
        		    "            SELECT CASE  " + 
        		    "                WHEN UPPER(E.CVE_IDIOMA) = UPPER(:cveidioma) THEN STNP.DESCRIPCION " +
        		    "                WHEN NOT EXISTS ( " +
        		    "                    SELECT 1 FROM TRADUCCION  " +
        		    "                    WHERE UPPER(CVE_IDIOMA) = UPPER(:cveidioma) " +
        		    "                      AND PALABRA_ORIGINAL = STNP.DESCRIPCION " +
        		    "                ) THEN CONCAT('*', STNP.DESCRIPCION) " +
        			"                ELSE ( " +
        		    "                    SELECT PALABRA_TRADUCIDA " +
        		    "                    FROM TRADUCCION  " +
        		    "                    WHERE UPPER(CVE_IDIOMA) = UPPER(:cveidioma) " +
        		    "                      AND PALABRA_ORIGINAL = STNP.DESCRIPCION " +
        		    "                ) " +
        		    "            END " +
        		    "            FROM ENTIDAD E  " +
        		    "            WHERE E.CVE_ENTIDAD = :cveEntidad  " +
        		    "        ), ''),  " +
        		    "        (  " +
        		    "            SELECT CONCAT(COUNT(1), ' ') " +
        		    "            FROM IN_NODO_PROCESO INNP  " +
        		    "            JOIN ST_NODO_PROCESO STNP2 ON  " +
        		    "                INNP.CVE_ENTIDAD = STNP2.CVE_ENTIDAD AND  " +
        		    "                INNP.CVE_PROCESO = STNP2.CVE_PROCESO AND  " +
        		    "                INNP.VERSION = STNP2.VERSION AND  " +
        		    "                INNP.CVE_NODO = STNP2.CVE_NODO AND  " +
        		    "                INNP.ID_NODO = STNP2.ID_NODO  " +
        		    "            WHERE INNP.ESTADO = 'REGISTRO' " +
        		    "              AND (  " +
        		    "                  (STNP2.TIPO_ACCESO = 'ROL_USUARIO' AND INNP.USUARIO_CREADOR = :cveUsuario) " +
        		    "                  OR (STNP2.TIPO_ACCESO = 'ROL' AND EXISTS ( " +
        		    "                      SELECT 1 " +
        		    "                      FROM USUARIO_ROL UR " +
        		    "                      JOIN ST_ROL_NODO STRN ON  " +
        		    "                          STRN.CVE_ENTIDAD = STNP2.CVE_ENTIDAD AND " +
        		    "                          STRN.CVE_PROCESO = STNP2.CVE_PROCESO AND " +
        		    "                          STRN.VERSION = STNP2.VERSION AND " +
        		    "                         STRN.CVE_NODO = INNP.CVE_NODO AND  " +
        		    "                         STRN.ID_NODO = INNP.ID_NODO AND " +
        		    "                          UR.CVE_ENTIDAD = STRN.CVE_ENTIDAD AND " +
        		    "                          UR.CVE_USUARIO = :cveUsuario AND " +
        		    "                          UR.CVE_PROCESO = STRN.CVE_PROCESO AND " +
        		    "                          UR.VERSION = STRN.VERSION AND " +
        		    "                          UR.CVE_ROL = STRN.CVE_ROL " +
        		    "                  )) " +
        		    "                  OR EXISTS ( " +
        		    "                      SELECT 1  " +
        		    "                      FROM IN_NODO_PROCESO_USUARIO  " +
        		    "                      WHERE CVE_ENTIDAD = INNP.CVE_ENTIDAD AND " +
        		    "                            CVE_PROCESO = INNP.CVE_PROCESO AND " +
        		    "                            VERSION = INNP.VERSION AND " +
        		    "                            CVE_INSTANCIA = INNP.CVE_INSTANCIA AND " +
        		    "                            CVE_NODO = INNP.CVE_NODO AND " +
        		    "                            ID_NODO = INNP.ID_NODO AND  " +
        		    "                            SECUENCIA_NODO = INNP.SECUENCIA_NODO AND " +
        		    "                            CVE_USUARIO = :cveUsuario ))) " +
        		    "    ) AS DESCRIPCION_NODO, " +
        		    "    NULL AS INICIAR_PROCESO, " +
        		    "    STNP.CVE_AREA_TRABAJO, " +
        		    "    STRN.CVE_ROL, " +
        		    "    ' ' AS ETIQUETA_BOTON, " +
        		    "    'NO' AS EJECUCION_AUTOMATICA, " +
        		    "    ' ' AS ETIQUETA_BOTON_EJECUCION_AUTOMATICA " +
        		    "    ,STP.ICONO_PROCESO " +
        		"    FROM ST_PROCESO STP " +
        		"    JOIN ST_NODO_PROCESO STNP ON STP.CVE_ENTIDAD = STNP.CVE_ENTIDAD AND STP.CVE_PROCESO = STNP.CVE_PROCESO" + "    AND STP.VERSION = STNP.VERSION " +
        		"    JOIN TIPO_NODO TN ON TN.CVE_NODO = STNP.CVE_NODO " +
        		"    JOIN ST_ROL_NODO STRN ON STRN.CVE_ENTIDAD = STNP.CVE_ENTIDAD AND STRN.CVE_PROCESO = STNP.CVE_PROCESO " + "    AND STRN.VERSION = STNP.VERSION AND STRN.CVE_NODO = STNP.CVE_NODO AND STRN.ID_NODO = STNP.ID_NODO " +
        		"    JOIN USUARIO_ROL UR ON UR.CVE_ENTIDAD = :cveEntidad AND UR.CVE_USUARIO = :cveUsuario AND UR.CVE_PROCESO = STRN.CVE_PROCESO AND UR.VERSION = STRN.VERSION AND UR.CVE_ROL = STRN.CVE_ROL " +
        		"    WHERE STNP.CVE_NODO = :temporizador " +
        		") T; ";
        

        /*Object[] result = (Object[]) entityManager.createNativeQuery(query)
        .setParameter("cveEntidad", cveEntidad)
        .setParameter("cveUsuario", cveUsuario)
        .setParameter("cveidioma", cveidioma)
        .setParameter("temporizador", temporizador)
        .getSingleResult();*/
        
        return executeAndTransform(query, new String[] {"cveEntidad", "cveUsuario", "cveidioma", "temporizador"},
    			new Object[] {cveEntidad, cveUsuario, cveidioma, temporizador}, MenuPrincipal.class);
    }
	
	@Override
    public List<LeeMenuEstatico> obtenerMenuDashbord(String cveEntidad, String cveUsuario) throws BrioBPMException {
        
    	/** 
    	 * Construccion de la consulta SQL dinamicamente utilizando el nombre
    	 * de la columna pasada como parametro
    	 */
        String query =  "SELECT ROW_NUMBER() OVER(ORDER BY T.CVE_MENU, T.DESCRIPCION_MENU ) as ORDENAMIENTO, T.* " + 
        		" FROM ( " +
        		" SELECT DISTINCT " + 
        		"	F.TIPO_FUNCION AS CVE_MENU, " + 
        		"	F.DESCRIPCION_CORTA AS DESCRIPCION_MENU, " + 
        		"	F.FUNCION AS CVE_OPCION, " + 
        		"	F.DESCRIPCION AS DESCRIPCION_OPCION, " + 
        		"	F.CVE_FUNCION AS ID_OPCION, " + 
        		"   ' ' AS PARAMETROS " +
        		"	FROM	USUARIO_FACULTAD UF, " + 
        		"		FACULTAD_FUNCION_ACCION	FFA, " + 
        		"		FUNCION	F " + 
        		"	WHERE	UF.CVE_ENTIDAD = :cveEntidad	AND " + 
        		"			UF.CVE_USUARIO = :cveUsuario	AND " +  
        		"		FFA.CVE_FACULTAD = UF.CVE_FACULTAD  AND " +  
        		"		F.CVE_FUNCION = FFA.CVE_FUNCION     AND " + 
        		"		F.TIPO_FUNCION = 'DASHBOARD' 		AND " +
        		" 		F.SITUACION = 'HABILITADO' "	+
        		"	) T ";
        
		return executeAndTransform(query,
				new String[] {"cveUsuario", "cveEntidad"},
				new String[] {cveUsuario, cveEntidad}, LeeMenuEstatico.class);
    }

	
    /**
     * Metodo para obtener el valor maximo de una columna
     *  especifica con datos completos
     *  
     * @param cveColumna
     * @param cveEntidad
     * @param cveProceso
     * @param version
     * @param cveInstancia
     * @param cveNodo
     * @param idNodo
     * @param secuenciaNodo
     * @return
     * @throws BrioBPMException la brio BPM exception.
     */
	@Override
    public List<MenuProcesos> obtenerMenuProceso(String cveEntidad, String cveUsuario) throws BrioBPMException {
        
    	/** 
    	 * Construccion de la consulta SQL dinamicamente utilizando el nombre
    	 * de la columna pasada como parametro
    	 */
        String query =  "SELECT ROW_NUMBER() OVER(ORDER BY T.DESCRIPCION_PROCESO)  as ORDENAMIENTO, T.*  " + 
        		"        		 FROM (  " + 
        		"        		 SELECT DISTINCT STP.CVE_PROCESO,  " + 
        		"        				STP.VERSION,  " + 
        		"        				STP.DESCRIPCION AS	DESCRIPCION_PROCESO  " + 
        		"        			FROM	ST_PROCESO		STP,  " + 
        		"        				ST_NODO_PROCESO	STNP,  " + 
        		"        				ST_ROL_NODO		STRN,  " + 
        		"        				USUARIO_ROL		UR  " + 
        		"        			WHERE  " + 
        		"        				STP.CVE_ENTIDAD = STNP.CVE_ENTIDAD				AND  " + 
        		"        				STP.CVE_PROCESO = STNP.CVE_PROCESO				AND  " + 
        		"        				STP.VERSION = STNP.VERSION						AND   " + 
        		"        				(	STNP.CVE_NODO = 'ACTIVIDAD-USUARIO'		OR  " + 
        		"        					STNP.CVE_NODO = 'ACTIVIDAD-USUARIO-TEMPORIZACION'  " + 
        		"        				)										AND  " + 
        		"        				STRN.CVE_ENTIDAD = STNP.CVE_ENTIDAD				AND  " + 
        		"        				STRN.CVE_PROCESO = STNP.CVE_PROCESO				AND  " + 
        		"        				STRN.VERSION = STNP.VERSION						AND  " + 
        		"        				STRN.CVE_NODO = STNP.CVE_NODO					AND  " + 
        		"        				STRN.ID_NODO = STNP.ID_NODO						AND  " + 
        		"        				UR.CVE_ENTIDAD = :cveEntidad					AND  " + 
        		"        				UR.CVE_USUARIO = :cveUsuario					AND  " + 
        		"        				UR.CVE_PROCESO = STRN.CVE_PROCESO				AND  " + 
        		"        				UR.VERSION = STRN.VERSION						AND  " + 
        		"        				UR.CVE_ROL = STRN.CVE_ROL						 " + 
        		"						)T";
        
        
        return executeAndTransform(query, new String[] {"cveEntidad", "cveUsuario"},
    			new Object[] {cveEntidad, cveUsuario}, MenuProcesos.class);
    }


	@Override
    public List<ActividadProceso> obtenerActividaesByProceso(String cveEntidad, String cveProceso, BigDecimal version, String cveUsuario) throws BrioBPMException {
        
    	/** 
    	 * Construccion de la consulta SQL dinamicamente utilizando el nombre
    	 * de la columna pasada como parametro
    	 */
        String query =  "SELECT ROW_NUMBER() OVER(ORDER BY T.CVE_INSTANCIA, T.DESCRIPCION )  as ORDENAMIENTO, T.*  " + 
        		"        		 FROM (  " + 
        		"SELECT  INNP.CVE_ENTIDAD, INNP.CVE_PROCESO, INNP.VERSION, " + 
        		"        STP.DESCRIPCION AS DESPROCESO, INNP.CVE_INSTANCIA, INNP.CVE_NODO, " + 
        		"        INNP.ID_NODO, INNP.SECUENCIA_NODO, STNP.DESCRIPCION, " + 
        		"        INNP.FECHA_CREACION, INNP.FECHA_LIMITE, INNP.FECHA_ESTADO_ACTUAL " + 
        		"    FROM    ST_PROCESO      STP, " + 
        		"            ST_NODO_PROCESO STNP, " + 
        		"            ST_ROL_NODO     STRN, " + 
        		"            IN_NODO_PROCESO INNP, " + 
        		"            USUARIO_ROL     UR " + 
        		"    WHERE " + 
        		"        STP.CVE_ENTIDAD = STNP.CVE_ENTIDAD              AND " + 
        		"        STP.CVE_PROCESO = STNP.CVE_PROCESO              AND " + 
        		"        STP.VERSION = STNP.VERSION                      AND " + 
        		"        (   STNP.CVE_NODO = 'ACTIVIDAD-USUARIO'     OR " + 
        		"            STNP.CVE_NODO = 'ACTIVIDAD-USUARIO-TEMPORIZACION' " + 
        		"        )                                       AND " + 
        		"        STRN.CVE_ENTIDAD = STNP.CVE_ENTIDAD             AND " + 
        		"        STRN.CVE_PROCESO = STNP.CVE_PROCESO             AND " + 
        		"        STRN.VERSION = STNP.VERSION                     AND " + 
        		"        STRN.CVE_NODO = STNP.CVE_NODO                   AND " + 
        		"        STRN.ID_NODO = STNP.ID_NODO                     AND " + 
        		"        INNP.CVE_ENTIDAD = STNP.CVE_ENTIDAD             AND " + 
        		"        INNP.CVE_PROCESO = STNP.CVE_PROCESO             AND " + 
        		"        INNP.CVE_PROCESO = :cveProceso             	 AND " + 
        		"        INNP.VERSION = STNP.VERSION                     AND " + 
        		"		 INNP.VERSION = :version						 AND " + 
        		"        INNP.CVE_NODO = STNP.CVE_NODO                   AND " + 
        		"        INNP.ID_NODO = STNP.ID_NODO                     AND " + 
        		"        INNP.ESTADO = 'REGISTRO'                        AND " + 
        		"        UR.CVE_ENTIDAD = :cveEntidad                    AND " + 
        		"        UR.CVE_USUARIO = :cveUsuario                    AND " + 
        		"        UR.CVE_PROCESO = STRN.CVE_PROCESO               AND " + 
        		"        UR.VERSION = STRN.VERSION                       AND " + 
        		"        UR.CVE_ROL = STRN.CVE_ROL )T";
        
        
        return executeAndTransform(query, new String[] {"cveEntidad", "cveProceso", "version", "cveUsuario"},
    			new Object[] {cveEntidad, cveProceso, version, cveUsuario}, ActividadProceso.class);
    }
}
