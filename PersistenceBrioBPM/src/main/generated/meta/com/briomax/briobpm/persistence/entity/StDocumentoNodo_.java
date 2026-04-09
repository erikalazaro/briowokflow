package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(StDocumentoNodo.class)
public abstract class StDocumentoNodo_ {

	public static volatile SingularAttribute<StDocumentoNodo, String> descripcion;
	public static volatile ListAttribute<StDocumentoNodo, InDocumentoNodo> listOfInDocumentoNodo;
	public static volatile SingularAttribute<StDocumentoNodo, BigDecimal> ordenPresentacion;
	public static volatile SingularAttribute<StDocumentoNodo, String> requerida;
	public static volatile SingularAttribute<StDocumentoNodo, StNodoProceso> stNodoProceso;
	public static volatile SingularAttribute<StDocumentoNodo, StDocumentoNodoPK> id;

}

