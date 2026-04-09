package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ParametroGeneral.class)
public abstract class ParametroGeneral_ {

	public static volatile SingularAttribute<ParametroGeneral, String> cveParametro;
	public static volatile SingularAttribute<ParametroGeneral, String> descripcion;
	public static volatile SingularAttribute<ParametroGeneral, String> valorAlfanumerico;
	public static volatile SingularAttribute<ParametroGeneral, BigDecimal> valorDecimal;
	public static volatile SingularAttribute<ParametroGeneral, String> tipo;
	public static volatile SingularAttribute<ParametroGeneral, BigDecimal> valorEntero;
	public static volatile SingularAttribute<ParametroGeneral, Date> valorFecha;

}

