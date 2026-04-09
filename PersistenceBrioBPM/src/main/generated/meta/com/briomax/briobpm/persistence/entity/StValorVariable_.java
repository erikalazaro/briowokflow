package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(StValorVariable.class)
public abstract class StValorVariable_ {

	public static volatile SingularAttribute<StValorVariable, String> etiquetaLista;
	public static volatile SingularAttribute<StValorVariable, String> valorAlfanumerico;
	public static volatile SingularAttribute<StValorVariable, BigDecimal> valorDecimal;
	public static volatile SingularAttribute<StValorVariable, StVariableProceso> stVariableProceso;
	public static volatile SingularAttribute<StValorVariable, BigDecimal> valorEntero;
	public static volatile SingularAttribute<StValorVariable, Date> valorFecha;
	public static volatile SingularAttribute<StValorVariable, StValorVariablePK> id;

}

