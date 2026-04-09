package com.briomax.briobpm.persistence.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Consulta.class)
public abstract class Consulta_ {

	public static volatile SingularAttribute<Consulta, String> descripcion;
	public static volatile SingularAttribute<Consulta, String> situacionConsulta;
	public static volatile SingularAttribute<Consulta, StProceso> stProceso;
	public static volatile SingularAttribute<Consulta, StNodoProceso> stNodoProceso;
	public static volatile SingularAttribute<Consulta, ConsultaPK> id;
	public static volatile ListAttribute<Consulta, ContenidoConsulta> listOfContenidoConsulta;
	public static volatile SingularAttribute<Consulta, UsuarioLocalidad> usuarioLocalidad;

}

