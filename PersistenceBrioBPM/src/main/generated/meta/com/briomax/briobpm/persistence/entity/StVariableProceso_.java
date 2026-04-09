package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(StVariableProceso.class)
public abstract class StVariableProceso_ {

	public static volatile SingularAttribute<StVariableProceso, BigDecimal> enteros;
	public static volatile SingularAttribute<StVariableProceso, StProceso> stProceso;
	public static volatile SingularAttribute<StVariableProceso, String> tipo;
	public static volatile SingularAttribute<StVariableProceso, String> formatoFecha;
	public static volatile SingularAttribute<StVariableProceso, String> tipoInteraccion;
	public static volatile ListAttribute<StVariableProceso, StVariableSeccion> listOfStVariableSeccion;
	public static volatile SingularAttribute<StVariableProceso, BigDecimal> decimales;
	public static volatile SingularAttribute<StVariableProceso, String> tipoControl;
	public static volatile ListAttribute<StVariableProceso, InVariableProceso> listOfInVariableProceso;
	public static volatile SingularAttribute<StVariableProceso, String> etiqueta;
	public static volatile SingularAttribute<StVariableProceso, BigDecimal> longitud;
	public static volatile ListAttribute<StVariableProceso, StValorVariable> listOfStValorVariable;
	public static volatile SingularAttribute<StVariableProceso, String> situacion;
	public static volatile SingularAttribute<StVariableProceso, String> formula;
	public static volatile SingularAttribute<StVariableProceso, StVariableProcesoPK> id;
	public static volatile SingularAttribute<StVariableProceso, String> funcion;
	public static volatile SingularAttribute<StVariableProceso, String> tieneEtiqueta;

}

