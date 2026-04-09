package com.briomax.briobpm.persistence.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(StReglaInstanciaProceso.class)
public abstract class StReglaInstanciaProceso_ {

	public static volatile SingularAttribute<StReglaInstanciaProceso, String> descripcion;
	public static volatile SingularAttribute<StReglaInstanciaProceso, StProceso> stProceso;
	public static volatile ListAttribute<StReglaInstanciaProceso, StProgramacionSemanalPro> listOfStProgramacionSemanalPro;
	public static volatile SingularAttribute<StReglaInstanciaProceso, String> formula;
	public static volatile SingularAttribute<StReglaInstanciaProceso, StReglaInstanciaProcesoPK> id;
	public static volatile SingularAttribute<StReglaInstanciaProceso, String> funcion;
	public static volatile ListAttribute<StReglaInstanciaProceso, StProgramacionMensualPro> listOfStProgramacionMensualPro;

}

