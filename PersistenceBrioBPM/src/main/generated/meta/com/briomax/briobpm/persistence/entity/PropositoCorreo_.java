package com.briomax.briobpm.persistence.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PropositoCorreo.class)
public abstract class PropositoCorreo_ {

	public static volatile SingularAttribute<PropositoCorreo, String> descripcion;
	public static volatile SingularAttribute<PropositoCorreo, LocalidadEntidad> localidadEntidad;
	public static volatile ListAttribute<PropositoCorreo, UsuarioCorreo> listOfUsuarioCorreo;
	public static volatile SingularAttribute<PropositoCorreo, PropositoCorreoPK> id;

}

