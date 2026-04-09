package com.briomax.briobpm.persistence.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Facultad.class)
public abstract class Facultad_ {

	public static volatile SingularAttribute<Facultad, String> descripcion;
	public static volatile SingularAttribute<Facultad, String> descripcionCorta;
	public static volatile SingularAttribute<Facultad, String> cveFacultad;
	public static volatile ListAttribute<Facultad, UsuarioLocalidad> listOfUsuarioLocalidad;
	public static volatile ListAttribute<Facultad, FacultadFuncion> listOfFacultadFuncion;

}

