package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(VariableSistema.class)
public abstract class VariableSistema_ {

	public static volatile SingularAttribute<VariableSistema, BigDecimal> enteros;
	public static volatile SingularAttribute<VariableSistema, String> valorAlfanumerico;
	public static volatile SingularAttribute<VariableSistema, String> tipo;
	public static volatile SingularAttribute<VariableSistema, String> origenVariable;
	public static volatile SingularAttribute<VariableSistema, String> cveVariable;
	public static volatile SingularAttribute<VariableSistema, String> formatoFecha;
	public static volatile SingularAttribute<VariableSistema, BigDecimal> decimales;
	public static volatile SingularAttribute<VariableSistema, String> tipoControl;
	public static volatile SingularAttribute<VariableSistema, BigDecimal> valorDecimal;
	public static volatile SingularAttribute<VariableSistema, String> etiqueta;
	public static volatile SingularAttribute<VariableSistema, BigDecimal> longitud;
	public static volatile SingularAttribute<VariableSistema, BigDecimal> valorEntero;
	public static volatile ListAttribute<VariableSistema, DatoAreaTrabajo> listOfDatoAreaTrabajo;
	public static volatile SingularAttribute<VariableSistema, Date> valorFecha;
	public static volatile SingularAttribute<VariableSistema, String> situacion;
	public static volatile SingularAttribute<VariableSistema, String> tieneEtiqueta;

}

