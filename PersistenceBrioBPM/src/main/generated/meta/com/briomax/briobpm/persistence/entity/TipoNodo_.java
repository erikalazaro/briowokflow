package com.briomax.briobpm.persistence.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TipoNodo.class)
public abstract class TipoNodo_ {

	public static volatile SingularAttribute<TipoNodo, String> descripcion;
	public static volatile ListAttribute<TipoNodo, StProceso> listOfStProceso2;
	public static volatile ListAttribute<TipoNodo, StProceso> listOfStProceso;
	public static volatile ListAttribute<TipoNodo, InProceso> listOfInProceso2;
	public static volatile ListAttribute<TipoNodo, InProceso> listOfInProceso;
	public static volatile SingularAttribute<TipoNodo, String> tipoNodo;
	public static volatile ListAttribute<TipoNodo, StFolioNodo> listOfStFolioNodo;
	public static volatile SingularAttribute<TipoNodo, String> cveNodo;
	public static volatile SingularAttribute<TipoNodo, String> situacion;
	public static volatile ListAttribute<TipoNodo, StNodoProceso> listOfStNodoProceso;

}

