package com.briomax.briobpm.persistence.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(StRolProceso.class)
public abstract class StRolProceso_ {

	public static volatile SingularAttribute<StRolProceso, String> descripcion;
	public static volatile SingularAttribute<StRolProceso, StProceso> stProceso;
	public static volatile ListAttribute<StRolProceso, InProceso> listOfInProceso;
	public static volatile SingularAttribute<StRolProceso, String> situacion;
	public static volatile ListAttribute<StRolProceso, UsuarioLocalidad> listOfUsuarioLocalidad;
	public static volatile SingularAttribute<StRolProceso, StRolProcesoPK> id;

}

