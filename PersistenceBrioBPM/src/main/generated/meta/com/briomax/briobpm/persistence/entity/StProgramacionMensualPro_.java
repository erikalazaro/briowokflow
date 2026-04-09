package com.briomax.briobpm.persistence.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(StProgramacionMensualPro.class)
public abstract class StProgramacionMensualPro_ {

	public static volatile SingularAttribute<StProgramacionMensualPro, Date> hora;
	public static volatile SingularAttribute<StProgramacionMensualPro, String> tipoActivacion;
	public static volatile SingularAttribute<StProgramacionMensualPro, StReglaInstanciaProceso> stReglaInstanciaProceso;
	public static volatile SingularAttribute<StProgramacionMensualPro, StProgramacionMensualProPK> id;
	public static volatile SingularAttribute<StProgramacionMensualPro, StProgramacionProceso> stProgramacionProceso;

}

