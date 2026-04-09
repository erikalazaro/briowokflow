package com.briomax.briobpm.persistence.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Moneda.class)
public abstract class Moneda_ {

	public static volatile SingularAttribute<Moneda, String> descripcion;
	public static volatile ListAttribute<Moneda, Entidad> listOfEntidad;
	public static volatile ListAttribute<Moneda, LocalidadEntidad> listOfLocalidadEntidad;
	public static volatile SingularAttribute<Moneda, String> cveMoneda;

}

