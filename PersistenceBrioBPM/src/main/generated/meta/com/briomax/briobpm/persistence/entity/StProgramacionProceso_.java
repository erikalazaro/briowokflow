package com.briomax.briobpm.persistence.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(StProgramacionProceso.class)
public abstract class StProgramacionProceso_ {

	public static volatile SingularAttribute<StProgramacionProceso, String> descripcion;
	public static volatile SingularAttribute<StProgramacionProceso, Date> hasta;
	public static volatile SingularAttribute<StProgramacionProceso, StProceso> stProceso;
	public static volatile SingularAttribute<StProgramacionProceso, CalendarioLocalidad> calendarioLocalidad;
	public static volatile SingularAttribute<StProgramacionProceso, Date> desde;
	public static volatile SingularAttribute<StProgramacionProceso, String> situacion;
	public static volatile ListAttribute<StProgramacionProceso, StProgramacionSemanalPro> listOfStProgramacionSemanalPro;
	public static volatile SingularAttribute<StProgramacionProceso, StProgramacionProcesoPK> id;
	public static volatile ListAttribute<StProgramacionProceso, StProgramacionMensualPro> listOfStProgramacionMensualPro;

}

