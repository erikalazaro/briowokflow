package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(StVariableSeccionPK.class)
public abstract class StVariableSeccionPK_ {

	public static volatile SingularAttribute<StVariableSeccionPK, BigDecimal> idNodo;
	public static volatile SingularAttribute<StVariableSeccionPK, String> cveVariable;
	public static volatile SingularAttribute<StVariableSeccionPK, String> cveProceso;
	public static volatile SingularAttribute<StVariableSeccionPK, String> cveNodo;
	public static volatile SingularAttribute<StVariableSeccionPK, String> cveSeccion;
	public static volatile SingularAttribute<StVariableSeccionPK, BigDecimal> version;

}

