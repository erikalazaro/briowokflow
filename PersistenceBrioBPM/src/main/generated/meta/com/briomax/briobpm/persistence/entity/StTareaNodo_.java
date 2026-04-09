package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(StTareaNodo.class)
public abstract class StTareaNodo_ {

	public static volatile SingularAttribute<StTareaNodo, String> descripcion;
	public static volatile SingularAttribute<StTareaNodo, BigDecimal> ordenPresentacion;
	public static volatile SingularAttribute<StTareaNodo, String> requerida;
	public static volatile SingularAttribute<StTareaNodo, StNodoProceso> stNodoProceso;
	public static volatile SingularAttribute<StTareaNodo, StTareaNodoPK> id;
	public static volatile ListAttribute<StTareaNodo, InTareaNodo> listOfInTareaNodo;

}

