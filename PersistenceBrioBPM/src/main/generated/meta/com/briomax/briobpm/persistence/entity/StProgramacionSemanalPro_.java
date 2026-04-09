package com.briomax.briobpm.persistence.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(StProgramacionSemanalPro.class)
public abstract class StProgramacionSemanalPro_ {

	public static volatile SingularAttribute<StProgramacionSemanalPro, Date> hora;
	public static volatile SingularAttribute<StProgramacionSemanalPro, String> tipoActivacion;
	public static volatile SingularAttribute<StProgramacionSemanalPro, StReglaInstanciaProceso> stReglaInstanciaProceso;
	public static volatile SingularAttribute<StProgramacionSemanalPro, StProgramacionSemanalProPK> id;
	public static volatile SingularAttribute<StProgramacionSemanalPro, StProgramacionProceso> stProgramacionProceso;

}

