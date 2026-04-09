package com.briomax.briobpm.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class Dashboard.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Nov 03, 2023 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */

@Entity
@Table(name = "DATO_PROCESO_NODO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DatoProcesoNodo  implements IEntity{
	
	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;

	/** El atributo o variable cve Dato Proceso Nodo. */
	@Id
	@Column(name = "CVE_DATO_PROCESO_NODO", nullable = false, length = 80)
	private String cveDatoProcesoNodo;
	
	/** El atributo o variable tipo.*/
	@Column(name = "TIPO", nullable = false, length = 12)
	private String tipo;
		
	/** El atributo o variable column Sql.*/
	@Column(name = "COLUMNA_SQL", nullable = false, length = 80)
	private String columnSql;
	
	/** El atributo o variable description.*/
	@Column(name = "TABLA_SQL", nullable = false, length = 80)
	private String tablaSql;
		
	/** El atributo o variable alitas Tabla Sql.*/
	@Column(name = "ALIAS_TABLA_SQL", nullable = false, length = 80)
	private String alitasTablaSql;
	
	/** El atributo o variable where Sql.*/
	@Column(name = "WHERE_SQL", nullable = false, length = 80)
	private String whereSql;
		
	/** El atributo o variable longitud.*/
	@Column(name = "LONGITUD", nullable = false, precision = 4, scale = 0)
	private Integer longitud;
	
	/** El atributo o variable enteros.*/
	@Column(name = "ENTEROS", precision = 2, scale = 0)
	private Integer enteros;
		
	/** El atributo o variable decimales.*/
	@Column(name = "DECIMALES", precision = 2, scale = 0)
	private Integer decimales;
	
	/** El atributo o variable formato Fecha.*/
	@Column(name = "FORMATO_FECHA", length = 20)
	private String formatoFecha;
		
	/** El atributo o variable situacion.*/
	@Column(name = "SITUACION", nullable = false, length = 12)
	private String situacion;
	
}
