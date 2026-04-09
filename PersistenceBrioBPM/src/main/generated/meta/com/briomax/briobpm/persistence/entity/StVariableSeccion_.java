package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(StVariableSeccion.class)
public abstract class StVariableSeccion_ {

	public static volatile SingularAttribute<StVariableSeccion, StSeccionNodo> stSeccionNodo;
	public static volatile SingularAttribute<StVariableSeccion, StVariableProceso> stVariableProceso;
	public static volatile SingularAttribute<StVariableSeccion, String> soloConsulta;
	public static volatile SingularAttribute<StVariableSeccion, BigDecimal> ordenPresentacion;
	public static volatile SingularAttribute<StVariableSeccion, String> requerida;
	public static volatile SingularAttribute<StVariableSeccion, StReglaValidacionProceso> stReglaValidacionProceso;
	public static volatile SingularAttribute<StVariableSeccion, StVariableSeccionPK> id;

}

