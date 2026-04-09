package com.briomax.briobpm.persistence.repository;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
public class IEliminarInformacionRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void deleteProcesosPeriodicos() {

        em.createNativeQuery("DELETE FROM CR_PAGO_BANCARIO").executeUpdate();
        em.createNativeQuery("DELETE FROM CR_PDF_FILES").executeUpdate();
        em.createNativeQuery("DELETE FROM CR_AVISO_SISUB").executeUpdate();
        em.createNativeQuery("DELETE FROM CR_RECIBO_NOMINA").executeUpdate();
        em.createNativeQuery("DELETE FROM CR_AVISO_ICSOE").executeUpdate();
        em.createNativeQuery("DELETE FROM CR_REGISTRO_OBRA").executeUpdate();
        em.createNativeQuery("DELETE FROM CR_COMPROBANTE_CUOTAS_OP").executeUpdate();
        em.createNativeQuery("DELETE FROM CR_FORMATO_PAGO_CUOTAS_OP").executeUpdate();
        em.createNativeQuery("DELETE FROM CR_CEDULA_DET_CUOTAS").executeUpdate();
        em.createNativeQuery("DELETE FROM CR_DECLARACION_PROVISIONAL").executeUpdate();
        em.createNativeQuery("DELETE FROM CR_PROGRAMACION_PROCESO").executeUpdate();
        em.createNativeQuery("DELETE FROM CR_DEFINICION_PERIODICIDAD").executeUpdate();
        em.createNativeQuery("DELETE FROM CR_TRABAJADOR_HISTORICO").executeUpdate();
        em.createNativeQuery("DELETE FROM CR_DESTINATARIO_CORREO").executeUpdate();
        em.createNativeQuery("DELETE FROM CR_NOTIFICACION").executeUpdate();
    }
    

    public void eliminarProcesos(List<String> procesos) {

        String inClause = procesos.stream()
                .map(p -> "'" + p + "'")
                .collect(Collectors.joining(","));

        // PASO 1
        em.createNativeQuery("DELETE FROM IN_DOCUMENTO_ENVIO WHERE CVE_PROCESO IN (" + inClause + ")").executeUpdate();
        em.createNativeQuery("DELETE FROM IN_VARIABLE_ENVIO WHERE CVE_PROCESO IN (" + inClause + ")").executeUpdate();

        // PASO 2
        em.createNativeQuery("DELETE FROM IN_MENSAJE_ENVIO WHERE CVE_PROCESO IN (" + inClause + ")").executeUpdate();
        em.createNativeQuery("DELETE FROM IN_MENSAJE_RECEPCION WHERE CVE_PROCESO IN (" + inClause + ")").executeUpdate();

        // PASO 3
        em.createNativeQuery("DELETE FROM FOLIO_MENSAJE_PROCESO WHERE CVE_PROCESO IN (" + inClause + ")").executeUpdate();
        em.createNativeQuery("DELETE FROM IN_BITACORA_NODO WHERE CVE_PROCESO IN (" + inClause + ")").executeUpdate();
        em.createNativeQuery("DELETE FROM IN_NODO_PROCESO_USUARIO WHERE CVE_PROCESO IN (" + inClause + ")").executeUpdate();
        em.createNativeQuery("DELETE FROM IN_TAREA_PROCESO WHERE CVE_PROCESO IN (" + inClause + ")").executeUpdate();

        // PASO 4
        em.createNativeQuery("DELETE FROM IN_DOCUMENTO_PROCESO_OC WHERE CVE_PROCESO IN (" + inClause + ")").executeUpdate();

        // PASO 5
        em.createNativeQuery("DELETE FROM IN_FOLIO_NODO WHERE CVE_PROCESO IN (" + inClause + ")").executeUpdate();

        // PASO 6
        em.createNativeQuery("DELETE FROM IN_IMAGEN_PROCESO WHERE CVE_PROCESO IN (" + inClause + ")").executeUpdate();
        em.createNativeQuery("DELETE FROM IN_VARIABLE_PROCESO WHERE CVE_PROCESO IN (" + inClause + ")").executeUpdate();

        // PASO 7 (auto-referencia)
        em.createNativeQuery(
                "UPDATE IN_NODO_PROCESO SET " +
                        "CVE_PROCESO_ORIGEN = NULL, VERSION_ORIGEN = NULL, CVE_INSTANCIA_ORIGEN = NULL, " +
                        "CVE_NODO_ORIGEN = NULL, ID_NODO_ORIGEN = NULL, SECUENCIA_NODO_ORIGEN = NULL " +
                        "WHERE CVE_PROCESO IN (" + inClause + ") AND CVE_PROCESO_ORIGEN IS NOT NULL"
        ).executeUpdate();

        em.createNativeQuery("DELETE FROM IN_NODO_PROCESO WHERE CVE_PROCESO IN (" + inClause + ")").executeUpdate();

        // PASO 8
        em.createNativeQuery("DELETE FROM IN_FOLIO_PROCESO WHERE CVE_PROCESO IN (" + inClause + ")").executeUpdate();

        // PASO 9
        em.createNativeQuery("DELETE FROM IN_PROCESO WHERE CVE_PROCESO IN (" + inClause + ")").executeUpdate();

        // EXTRA
        em.createNativeQuery("DELETE FROM COMPOSICION_CORREO WHERE CVE_PROCESO IN (" + inClause + ")").executeUpdate();
        em.createNativeQuery("DELETE FROM CR_PROGRAMACION_PROCESO").executeUpdate();
        em.createNativeQuery("DELETE FROM CR_DEFINICION_PERIODICIDAD").executeUpdate();
    }
    
   
}
