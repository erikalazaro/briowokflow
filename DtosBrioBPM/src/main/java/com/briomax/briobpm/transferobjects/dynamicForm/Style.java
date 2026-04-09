/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados. Este software contiene informacion
 * confidencial propiedad de Briomax Consulting por lo cual no puede ser reproducido, distribuido o alterado sin el
 * consentimiento previo de Briomax Consulting.
 */

package com.briomax.briobpm.transferobjects.dynamicForm;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class Style.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion May 15, 2020 2:21:00 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "Style", title = "Style css.")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Style implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = 8678847050779422380L;
	
	/** El atributo o variable column. */
	@Schema(name = "column", description = "Columna a aplicar css.", example = "")
	private String column;
	
	/** El atributo o variable estyle. */
	@Schema(name = "estyle", description = "Style css sobre la celda.", example = "")
	private String estyle;

}
