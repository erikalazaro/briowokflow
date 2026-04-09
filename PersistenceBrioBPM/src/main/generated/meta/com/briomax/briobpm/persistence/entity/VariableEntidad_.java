package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(VariableEntidad.class)
public abstract class VariableEntidad_ {

	public static volatile SingularAttribute<VariableEntidad, BigDecimal> enteros;
	public static volatile SingularAttribute<VariableEntidad, String> valorAlfanumerico;
	public static volatile SingularAttribute<VariableEntidad, String> tipo;
	public static volatile SingularAttribute<VariableEntidad, String> origenVariable;
	public static volatile SingularAttribute<VariableEntidad, String> formatoFecha;
	public static volatile SingularAttribute<VariableEntidad, BigDecimal> decimales;
	public static volatile SingularAttribute<VariableEntidad, BigDecimal> valorDecimal;
	public static volatile SingularAttribute<VariableEntidad, String> etiqueta;
	public static volatile SingularAttribute<VariableEntidad, BigDecimal> longitud;
	public static volatile SingularAttribute<VariableEntidad, BigDecimal> valorEntero;
	public static volatile SingularAttribute<VariableEntidad, Date> valorFecha;
	public static volatile SingularAttribute<VariableEntidad, String> situacion;
	public static volatile SingularAttribute<VariableEntidad, Entidad> entidad;
	public static volatile SingularAttribute<VariableEntidad, String> formula;
	public static volatile SingularAttribute<VariableEntidad, VariableEntidadPK> id;
	public static volatile SingularAttribute<VariableEntidad, String> funcion;

}

