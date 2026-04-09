package com.briomax.briobpm.transferobjects.dynamicForm;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data/*lombok*/
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IconoAtTO implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;

	private String condicion;

	private byte[] iconoEstado;
}
