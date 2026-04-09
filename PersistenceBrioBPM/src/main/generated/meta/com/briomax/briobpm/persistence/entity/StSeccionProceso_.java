package com.briomax.briobpm.persistence.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(StSeccionProceso.class)
public abstract class StSeccionProceso_ {

	public static volatile SingularAttribute<StSeccionProceso, String> descripcion;
	public static volatile SingularAttribute<StSeccionProceso, String> etiqueta;
	public static volatile SingularAttribute<StSeccionProceso, StProceso> stProceso;
	public static volatile SingularAttribute<StSeccionProceso, String> tipoPresentacion;
	public static volatile SingularAttribute<StSeccionProceso, StSeccionProcesoPK> id;
	public static volatile ListAttribute<StSeccionProceso, StSeccionNodo> listOfStSeccionNodo;

}

