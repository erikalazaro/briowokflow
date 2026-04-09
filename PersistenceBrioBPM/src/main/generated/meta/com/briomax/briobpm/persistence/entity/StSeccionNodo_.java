package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(StSeccionNodo.class)
public abstract class StSeccionNodo_ {

	public static volatile SingularAttribute<StSeccionNodo, String> etiqueta;
	public static volatile SingularAttribute<StSeccionNodo, StSeccionProceso> stSeccionProceso;
	public static volatile ListAttribute<StSeccionNodo, StVariableSeccion> listOfStVariableSeccion;
	public static volatile SingularAttribute<StSeccionNodo, BigDecimal> ordenPresentacion;
	public static volatile SingularAttribute<StSeccionNodo, StNodoProceso> stNodoProceso;
	public static volatile SingularAttribute<StSeccionNodo, StSeccionNodoPK> id;

}

