package com.briomax.briobpm.persistence.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.InNodoProceso;
import com.briomax.briobpm.persistence.entity.InNodoProcesoPK;

/**
 * El objetivo de la Interface IInNodoProcesoRepository.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Nov 07, 2023 12:30:03 PM:
 * @since JDK 1.8
 */
@Repository
public interface IInNodoProcesoRepository extends JpaRepository<InNodoProceso, InNodoProcesoPK> {

    @Query(value = "SELECT MAX(INNP.SECUENCIA_NODO) " +
            "FROM IN_NODO_PROCESO INNP " +
            "WHERE INNP.CVE_ENTIDAD = :cveEntidad " +
            "  AND INNP.CVE_PROCESO = :cveProceso " +
            "  AND INNP.VERSION = :version " +
            "  AND INNP.CVE_INSTANCIA = :cveInstancia " +
            "  AND INNP.CVE_NODO = :cveNodoCompuerta " +
            "  AND INNP.ID_NODO = :idNodoCompuerta " +
            "  AND INNP.ESTADO = :estadoRegistro", nativeQuery = true)
	Integer encontrarMaxSecuenciaNodo(
			@Param("cveEntidad") String cveEntidad,
		    @Param("cveProceso") String cveProceso,
		    @Param("version") BigDecimal version,
		    @Param("cveInstancia") String cveInstancia,
		    @Param("cveNodoCompuerta") String cveNodoCompuerta,
		    @Param("idNodoCompuerta") Integer idNodoCompuerta,
		    @Param("estadoRegistro") String estadoRegistro);
    
    @Query(value = "SELECT COUNT(1) " +
            "FROM IN_NODO_PROCESO " +
            "WHERE CVE_ENTIDAD = :cveEntidad " +
            "  AND CVE_PROCESO = :cveProceso " +
            "  AND VERSION = :version " +
            "  AND CVE_INSTANCIA = :cveInstancia " +
            "  AND CVE_NODO = :cveNodoActual " +
            "  AND ID_NODO = :idNodoActual", nativeQuery = true)
	int encontrarRegistroExistente(
	     @Param("cveEntidad") String cveEntidad,
	     @Param("cveProceso") String cveProceso,
	     @Param("version") BigDecimal version,
	     @Param("cveInstancia") String cveInstancia,
	     @Param("cveNodoActual") String cveNodoActual,
	     @Param("idNodoActual") Integer idNodoActual);
    
    @Query(value = "SELECT MAX(INNP.SECUENCIA_NODO) " +
            "FROM IN_NODO_PROCESO INNP " +
            "WHERE INNP.CVE_ENTIDAD = :cveEntidad " +
            "  AND INNP.CVE_PROCESO = :cveProceso " +
            "  AND INNP.VERSION = :version " +
            "  AND INNP.CVE_INSTANCIA = :cveInstancia " +
            "  AND INNP.CVE_NODO = :cveNodoSiguiente " +
            "  AND INNP.ID_NODO = :idNodoSiguiente " +
            "  AND INNP.ESTADO = :estadoRegistro", nativeQuery = true)
   Integer encontrarSecuenciaNodoSiguiente(
	     @Param("cveEntidad") String cveEntidad,
	     @Param("cveProceso") String cveProceso,
	     @Param("version") BigDecimal version,
	     @Param("cveInstancia") String cveInstancia,
	     @Param("cveNodoSiguiente") String cveNodoSiguiente,
	     @Param("idNodoSiguiente") Integer idNodoSiguiente,
	     @Param("estadoRegistro") String estadoRegistro);


    @Query(value = "SELECT * " +
            "FROM IN_NODO_PROCESO " +
            "WHERE CVE_ENTIDAD = :cveEntidad " +
            "  AND CVE_PROCESO = :cveProceso " +
            "  AND VERSION = :version " +
            "  AND CVE_INSTANCIA = :cveInstancia " +
            "  AND CVE_NODO IN :actividades", nativeQuery = true)
    List<InNodoProceso> encuentraNodosActividades(
         @Param("cveEntidad") String cveEntidad,
         @Param("cveProceso") String cveProceso,
         @Param("version") BigDecimal version,
         @Param("cveInstancia") String cveInstancia,
         @Param("actividades") List<String> actividades);
    
    @Query(value = "SELECT " + 
    		"	DA.USUARIO_BLOQUEA " +
            "FROM IN_NODO_PROCESO INNP, ST_NODO_PROCESO STNP, VW_DATO_ACTIVIDAD DA " +
            "WHERE INNP.CVE_ENTIDAD = :cveEntidad " +
            "	AND INNP.CVE_PROCESO = :cveProceso " +
            "	AND INNP.VERSION = :version " +
            "	AND INNP.CVE_NODO = :cveNodo " +
            "	AND INNP.ID_NODO = :idNodoProcesar " +
            "	AND INNP.CVE_INSTANCIA = :cveInstancia " +
            "	AND INNP.SECUENCIA_NODO = :secNodo "+
            
            "	AND STNP.CVE_ENTIDAD = INNP.CVE_ENTIDAD " +
            "	AND STNP.CVE_PROCESO = INNP.CVE_PROCESO " +
            "	AND STNP.VERSION = INNP.VERSION " +
            "	AND STNP.CVE_NODO = INNP.CVE_NODO " +
            "	AND STNP.ID_NODO = INNP.ID_NODO " +
            "	AND (STNP.TIPO_ACCESO = :tipoAccRol " +
            "	OR (STNP.TIPO_ACCESO = :tipoAccRolUsuario AND INNP.USUARIO_CREADOR = :cveUsuario)) " +
            "	AND DA.CVE_ENTIDAD = INNP.CVE_ENTIDAD " +
            "	AND DA.CVE_PROCESO = INNP.CVE_PROCESO " +
            "	AND DA.VERSION = INNP.VERSION " +
            "	AND DA.CVE_INSTANCIA = INNP.CVE_INSTANCIA " +
            "	AND DA.CVE_NODO = INNP.CVE_NODO " +
            "	AND DA.ID_NODO = INNP.ID_NODO " +
            "	AND DA.SECUENCIA_NODO = INNP.SECUENCIA_NODO " , nativeQuery = true)
		List<String> generarSetSalida(
		     @Param("cveEntidad") String cveEntidad,
		     @Param("cveProceso") String cveProceso,
		     @Param("version") BigDecimal version,
		     @Param("cveNodo") String cveNodo,
		     @Param("idNodoProcesar") BigDecimal idNodoProcesar,
		     @Param("tipoAccRol") String tipoAccRol,
		     @Param("tipoAccRolUsuario") String tipoAccRolUsuario,
		     @Param("cveUsuario") String cveUsuario,
		     @Param("cveInstancia") String cveInstancia,
		     @Param("secNodo") Integer secNodo);
    
    
    @Query(value = "SELECT * " +
    		"FROM IN_NODO_PROCESO        	       INP " +
            "WHERE INP.CVE_ENTIDAD =       :cveEntidad " +
            "	AND INP.CVE_PROCESO =      :cveProceso " +
            "	AND INP.VERSION =             :version " +
            "	AND INP.CVE_NODO =            :cveNodo " +
            "	AND INP.ID_NODO = :idNodo ", nativeQuery = true)
    List<InNodoProceso> obtieneInNodoProceso(
    		@Param("cveEntidad") String cveEntidad,
             @Param("cveProceso") String cveProceso,
             @Param("version") BigDecimal version,
             @Param("cveNodo") String cveNodo,
             @Param("idNodo") BigDecimal idNodo);
          
    @Query(value = "SELECT INNP.CVE_ENTIDAD, " +
            "       INNP.CVE_PROCESO, " +
            "       INNP.VERSION, " +
            "       INNP.CVE_INSTANCIA, " +
            "       INNP.CVE_NODO, " +
            "       INNP.ID_NODO, " +
            "       INNP.SECUENCIA_NODO, " +
            "       INNP.ROL_CREADOR, " +
            "       STNP.TIPO_TEMPORIZACION, " +
            "       E.CVE_IDIOMA " +
            "FROM   IN_NODO_PROCESO INNP, " +
            "       ST_NODO_PROCESO STNP, " +
            "       ENTIDAD E " +
            "WHERE  (INNP.CVE_NODO = :actividadUsuTem " +
            "        OR INNP.CVE_NODO = :temporizador) " +
            "AND    INNP.ESTADO = 'REGISTRO' " +
            "AND  (  INNP.FECHA_FIN_ESPERA < :fechaActual " +
            "OR    INNP.FECHA_LIMITE < :fechaActual )" +
            "AND    STNP.CVE_ENTIDAD = INNP.CVE_ENTIDAD " +
            "AND    STNP.CVE_PROCESO = INNP.CVE_PROCESO " +
            "AND    STNP.VERSION = INNP.VERSION " +
            "AND    STNP.CVE_NODO = INNP.CVE_NODO " +
            "AND    STNP.ID_NODO = INNP.ID_NODO " +
            "AND   E.CVE_ENTIDAD = INNP.CVE_ENTIDAD ", 
    nativeQuery = true)
    List<Object[]> encuentraNodosTemporizados(@Param("actividadUsuTem") String actividadUsuTem, 
                                       @Param("temporizador") String temporizador, 
                                       @Param("fechaActual") Date fechaActual);
    
    @Query(value = "SELECT INNP.CVE_ENTIDAD, " +
            "       INNP.CVE_PROCESO, " +
            "       INNP.VERSION, " +
            "       INNP.CVE_INSTANCIA, " +
            "       INNP.CVE_NODO, " +
            "       INNP.ID_NODO, " +
            "       INNP.SECUENCIA_NODO, " +
            "       INNP.ROL_CREADOR, " +
            "       STNP.TIPO_TEMPORIZACION, " +
            "       E.CVE_IDIOMA " +
            "FROM   IN_NODO_PROCESO INNP, " +
            "       ST_NODO_PROCESO STNP, " +
            "       ENTIDAD E " +
            "WHERE  INNP.ESTADO = 'REGISTRO' " +
            "AND   (INNP.FECHA_FIN_ESPERA < :fechaActual " +
            "       OR INNP.FECHA_LIMITE < :fechaActual ) " +
            "AND    STNP.CVE_ENTIDAD = INNP.CVE_ENTIDAD " +
            "AND    STNP.CVE_PROCESO = INNP.CVE_PROCESO " +
            "AND    STNP.VERSION = INNP.VERSION " +
            "AND    STNP.CVE_NODO = INNP.CVE_NODO " +
            "AND    STNP.ID_NODO = INNP.ID_NODO " +
            "AND    E.CVE_ENTIDAD = INNP.CVE_ENTIDAD " +
            "AND NOT EXISTS ( " +
            "       SELECT 1 " +
            "       FROM COMPOSICION_CORREO CC " +
            "       WHERE CC.CVE_ENTIDAD = INNP.CVE_ENTIDAD " +
            "       AND   CC.CVE_PROCESO = INNP.CVE_PROCESO " +
            "       AND   CC.VERSION = INNP.VERSION " +
            "       AND   CC.CVE_NODO = INNP.CVE_NODO " +
            "       AND   CC.ID_NODO = INNP.ID_NODO " +
            "       AND   CC.REFERENCIA = 'ACTIVIDAD_PENDIENTE' " +
            "       AND   CC.FECHA_COMPOSICION >=  CURDATE() " +
            "       AND   CC.FECHA_COMPOSICION <  CURDATE()  + INTERVAL 1 DAY " +
            ")",
    nativeQuery = true)
   
    List<Object[]> encuentraActividadPendiente(
                                       @Param("fechaActual") Date fechaActual);

  }
