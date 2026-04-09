package com.briomax.briobpm.persistence.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Idioma.class)
public abstract class Idioma_ {

	public static volatile SingularAttribute<Idioma, String> descripcion;
	public static volatile ListAttribute<Idioma, Entidad> listOfEntidad;
	public static volatile ListAttribute<Idioma, LocalidadEntidad> listOfLocalidadEntidad;
	public static volatile ListAttribute<Idioma, MensajeIdioma> listOfMensajeIdioma;
	public static volatile SingularAttribute<Idioma, String> cveIdioma;
	public static volatile ListAttribute<Idioma, Traduccion> listOfTraduccion;

}

