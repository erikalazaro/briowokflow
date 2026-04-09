package com.briomax.briobpm.business.convertes;

import java.util.Map;

import com.briomax.briobpm.transferobjects.app.DatoGuardar;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IDatoGuardarConverter {

	  /**
     * Valida y procesa un objeto genérico para asegurarse de que sea una instancia válida de DatoGuardar.
     * 
     * @param obj el objeto a validar, esperado en formato DatoGuardar.
     * @return una instancia de DatoGuardar válida o null si no es compatible.
     */
    public static DatoGuardar convertirObjetoADatoGuardar(Object obj) {
        if (obj instanceof DatoGuardar) {
            DatoGuardar datoGuardar = (DatoGuardar) obj;

            // Validar atributos del objeto
            if (datoGuardar.getSeccionVariable() == null || datoGuardar.getNumeroDato() == null 
                || datoGuardar.getTipoControl() == null) {
                log.info("El objeto DatoGuardar no tiene los campos requeridos: {}", datoGuardar);
                return null;
            }

            log.info("DatoGuardar recibido correctamente: {}", datoGuardar);
            return datoGuardar;
        }

        log.info("El objeto no es una instancia de DatoGuardar: {}", obj != null ? obj.getClass().getName() : "null");
        return null;
    }
}
