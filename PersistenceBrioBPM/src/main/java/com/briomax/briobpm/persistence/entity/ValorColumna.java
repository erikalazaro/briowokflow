package com.briomax.briobpm.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "VALOR_COLUMNA")
public class ValorColumna 	implements Serializable {

		/** El atributo o variable id. */
		@EmbeddedId
		private ValorColumnaPK id;

	    @Column(name = "VALOR_BASE_DATOS", length = 300, nullable = false)
	    private String valorBaseDatos;

	    @Column(name = "VALOR_PANTALLA", length = 300, nullable = false)
	    private String valorPantalla;

	    /*@ManyToOne
	    @JoinColumns({
	        @JoinColumn(name = "NOMBRE_TABLA", referencedColumnName = "NOMBRE_TABLA", insertable = false, updatable = false),
	        @JoinColumn(name = "NOMBRE_COLUMNA", referencedColumnName = "NOMBRE_COLUMNA", insertable = false, updatable = false)
	    })
	    private TablaColumna tablaColumna;*/

}
