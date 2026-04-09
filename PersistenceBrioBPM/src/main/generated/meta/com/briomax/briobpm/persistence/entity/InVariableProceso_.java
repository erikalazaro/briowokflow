package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InVariableProceso.class)
public abstract class InVariableProceso_ {

	public static volatile SingularAttribute<InVariableProceso, String> valorAlfanumerico;
	public static volatile SingularAttribute<InVariableProceso, BigDecimal> valorDecimal;
	public static volatile SingularAttribute<InVariableProceso, StVariableProceso> stVariableProceso;
	public static volatile SingularAttribute<InVariableProceso, BigDecimal> valorEntero;
	public static volatile SingularAttribute<InVariableProceso, Date> valorFecha;
	public static volatile SingularAttribute<InVariableProceso, InVariableProcesoPK> id;
	public static volatile SingularAttribute<InVariableProceso, InProceso> inProceso;

}

