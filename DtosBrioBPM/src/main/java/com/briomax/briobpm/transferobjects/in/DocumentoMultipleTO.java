package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * La clase `DocumentoMultipleTO` agrupa una lista de documentos asociados a una actividad específica en el sistema.
 * Implementa `Serializable` para permitir la serialización de sus instancias.
 * 
 * @author Alexis Zamora
 * @version 1.0
 * @since JDK 1.8
 */
@Schema(title = "DocumentoMultipleTO.", description = "Documento de una Actividad.")
@Getter
@Setter
@NoArgsConstructor
public class DocumentoMultipleTO extends ActividadTO implements Serializable {

    /** Serial Version UID. */
    private static final long serialVersionUID = 1;

    /** Lista de documentos asociados a la actividad. */
    private List<DocumentoTO> documentos;

}