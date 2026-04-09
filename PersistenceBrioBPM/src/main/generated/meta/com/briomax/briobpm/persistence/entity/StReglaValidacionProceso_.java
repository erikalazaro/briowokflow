package com.briomax.briobpm.persistence.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(StReglaValidacionProceso.class)
public abstract class StReglaValidacionProceso_ {

	public static volatile SingularAttribute<StReglaValidacionProceso, String> descripcion;
	public static volatile SingularAttribute<StReglaValidacionProceso, StProceso> stProceso;
	public static volatile ListAttribute<StReglaValidacionProceso, StVariableSeccion> listOfStVariableSeccion;
	public static volatile SingularAttribute<StReglaValidacionProceso, String> formula;
	public static volatile SingularAttribute<StReglaValidacionProceso, StReglaValidacionProcesoPK> id;
	public static volatile SingularAttribute<StReglaValidacionProceso, String> funcion;

}

