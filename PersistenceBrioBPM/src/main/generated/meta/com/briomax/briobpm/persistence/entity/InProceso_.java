package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InProceso.class)
public abstract class InProceso_ {

	public static volatile SingularAttribute<InProceso, StProceso> stProceso;
	public static volatile SingularAttribute<InProceso, String> origen;
	public static volatile ListAttribute<InProceso, InNodoProceso> listOfInNodoProceso;
	public static volatile ListAttribute<InProceso, InVariableProceso> listOfInVariableProceso;
	public static volatile SingularAttribute<InProceso, String> usuarioCreador;
	public static volatile SingularAttribute<InProceso, BigDecimal> idNodoFin;
	public static volatile SingularAttribute<InProceso, TipoNodo> tipoNodo2;
	public static volatile SingularAttribute<InProceso, BigDecimal> idNodoInicio;
	public static volatile SingularAttribute<InProceso, TipoNodo> tipoNodo;
	public static volatile SingularAttribute<InProceso, String> situacion;
	public static volatile SingularAttribute<InProceso, String> concepto;
	public static volatile SingularAttribute<InProceso, StRolProceso> stRolProceso;
	public static volatile SingularAttribute<InProceso, Date> fechaCreacion;
	public static volatile SingularAttribute<InProceso, InProcesoPK> id;

}

