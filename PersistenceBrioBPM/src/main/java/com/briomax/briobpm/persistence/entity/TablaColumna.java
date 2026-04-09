package com.briomax.briobpm.persistence.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;

public class TablaColumna {
	
	@EmbeddedId
	private TablaColumnaPK id;
	
	@Column(name = "TIPO_ORDENAMIENTO", length = 20, nullable = false)
    private String tipoOrdenamiento;

   

}
