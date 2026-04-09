package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(StSeccionNodoPK.class)
public abstract class StSeccionNodoPK_ {

	public static volatile SingularAttribute<StSeccionNodoPK, BigDecimal> idNodo;
	public static volatile SingularAttribute<StSeccionNodoPK, String> cveProceso;
	public static volatile SingularAttribute<StSeccionNodoPK, String> cveNodo;
	public static volatile SingularAttribute<StSeccionNodoPK, String> cveSeccion;
	public static volatile SingularAttribute<StSeccionNodoPK, BigDecimal> version;

}

