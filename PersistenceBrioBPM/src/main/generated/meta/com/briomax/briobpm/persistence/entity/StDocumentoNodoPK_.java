package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(StDocumentoNodoPK.class)
public abstract class StDocumentoNodoPK_ {

	public static volatile SingularAttribute<StDocumentoNodoPK, BigDecimal> idNodo;
	public static volatile SingularAttribute<StDocumentoNodoPK, String> cveProceso;
	public static volatile SingularAttribute<StDocumentoNodoPK, String> cveNodo;
	public static volatile SingularAttribute<StDocumentoNodoPK, BigDecimal> secuenciaDocumento;
	public static volatile SingularAttribute<StDocumentoNodoPK, BigDecimal> version;

}

