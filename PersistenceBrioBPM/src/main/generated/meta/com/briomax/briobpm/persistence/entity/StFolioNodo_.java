package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(StFolioNodo.class)
public abstract class StFolioNodo_ {

	public static volatile SingularAttribute<StFolioNodo, StProceso> stProceso;
	public static volatile SingularAttribute<StFolioNodo, TipoNodo> tipoNodo;
	public static volatile SingularAttribute<StFolioNodo, BigDecimal> folio;
	public static volatile SingularAttribute<StFolioNodo, StFolioNodoPK> id;

}

