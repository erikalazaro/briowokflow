package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(VariableLocalidad.class)
public abstract class VariableLocalidad_ {

	public static volatile SingularAttribute<VariableLocalidad, BigDecimal> enteros;
	public static volatile SingularAttribute<VariableLocalidad, String> valorAlfanumerico;
	public static volatile SingularAttribute<VariableLocalidad, LocalidadEntidad> localidadEntidad;
	public static volatile SingularAttribute<VariableLocalidad, String> tipo;
	public static volatile SingularAttribute<VariableLocalidad, String> origenVariable;
	public static volatile SingularAttribute<VariableLocalidad, String> formatoFecha;
	public static volatile SingularAttribute<VariableLocalidad, BigDecimal> decimales;
	public static volatile SingularAttribute<VariableLocalidad, String> tipoControl;
	public static volatile SingularAttribute<VariableLocalidad, BigDecimal> valorDecimal;
	public static volatile SingularAttribute<VariableLocalidad, String> etiqueta;
	public static volatile SingularAttribute<VariableLocalidad, BigDecimal> longitud;
	public static volatile SingularAttribute<VariableLocalidad, BigDecimal> valorEntero;
	public static volatile SingularAttribute<VariableLocalidad, Date> valorFecha;
	public static volatile SingularAttribute<VariableLocalidad, String> situacion;
	public static volatile SingularAttribute<VariableLocalidad, VariableLocalidadPK> id;
	public static volatile SingularAttribute<VariableLocalidad, String> tieneEtiqueta;

}

