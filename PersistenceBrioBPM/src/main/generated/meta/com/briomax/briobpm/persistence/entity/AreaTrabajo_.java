package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AreaTrabajo.class)
public abstract class AreaTrabajo_ {

	public static volatile SingularAttribute<AreaTrabajo, String> descripcion;
	public static volatile SingularAttribute<AreaTrabajo, String> nivelAreaTrabajo;
	public static volatile ListAttribute<AreaTrabajo, DatoAreaTrabajo> listOfDatoAreaTrabajo;
	public static volatile SingularAttribute<AreaTrabajo, String> situacionAreaTrabajo;
	public static volatile SingularAttribute<AreaTrabajo, String> cveAreaTrabajo;
	public static volatile SingularAttribute<AreaTrabajo, BigDecimal> ordenAreaTrabajo;
	public static volatile ListAttribute<AreaTrabajo, StNodoProceso> listOfStNodoProceso;

}

