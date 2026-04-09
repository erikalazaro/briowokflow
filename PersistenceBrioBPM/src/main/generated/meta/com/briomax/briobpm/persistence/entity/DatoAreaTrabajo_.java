package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DatoAreaTrabajo.class)
public abstract class DatoAreaTrabajo_ {

	public static volatile ListAttribute<DatoAreaTrabajo, VariableLocalidad> listOfVariableLocalidad;
	public static volatile ListAttribute<DatoAreaTrabajo, VariableSistema> listOfVariableSistema;
	public static volatile SingularAttribute<DatoAreaTrabajo, String> origenDato;
	public static volatile ListAttribute<DatoAreaTrabajo, VariableEntidad> listOfVariableEntidad;
	public static volatile SingularAttribute<DatoAreaTrabajo, DatoAreaTrabajoPK> id;
	public static volatile SingularAttribute<DatoAreaTrabajo, BigDecimal> ordenDato;
	public static volatile ListAttribute<DatoAreaTrabajo, DatoProcesoAt> listOfDatoProcesoAt;
	public static volatile SingularAttribute<DatoAreaTrabajo, AreaTrabajo> areaTrabajo;

}

