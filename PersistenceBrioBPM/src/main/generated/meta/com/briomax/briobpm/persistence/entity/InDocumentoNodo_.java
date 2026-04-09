package com.briomax.briobpm.persistence.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InDocumentoNodo.class)
public abstract class InDocumentoNodo_ {

	public static volatile SingularAttribute<InDocumentoNodo, String> nombreArchivo;
	public static volatile SingularAttribute<InDocumentoNodo, InNodoProceso> inNodoProceso;
	public static volatile SingularAttribute<InDocumentoNodo, InDocumentoNodoPK> id;
	public static volatile SingularAttribute<InDocumentoNodo, String> completado;
	public static volatile SingularAttribute<InDocumentoNodo, StDocumentoNodo> stDocumentoNodo;

}

