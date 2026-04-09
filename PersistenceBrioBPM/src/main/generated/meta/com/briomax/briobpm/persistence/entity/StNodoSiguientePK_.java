package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(StNodoSiguientePK.class)
public abstract class StNodoSiguientePK_ {

	public static volatile SingularAttribute<StNodoSiguientePK, BigDecimal> idNodo;
	public static volatile SingularAttribute<StNodoSiguientePK, String> cveProceso;
	public static volatile SingularAttribute<StNodoSiguientePK, String> cveNodo;
	public static volatile SingularAttribute<StNodoSiguientePK, BigDecimal> secuencia;
	public static volatile SingularAttribute<StNodoSiguientePK, BigDecimal> version;

}

