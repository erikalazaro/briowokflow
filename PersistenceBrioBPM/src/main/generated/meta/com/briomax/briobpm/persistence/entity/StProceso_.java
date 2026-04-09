package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(StProceso.class)
public abstract class StProceso_ {

	public static volatile SingularAttribute<StProceso, String> descripcion;
	public static volatile ListAttribute<StProceso, StSeccionProceso> listOfStSeccionProceso;
	public static volatile ListAttribute<StProceso, Consulta> listOfConsulta;
	public static volatile ListAttribute<StProceso, StFolioNodo> listOfStFolioNodo;
	public static volatile SingularAttribute<StProceso, BigDecimal> instanciasSemana;
	public static volatile ListAttribute<StProceso, InFolioProceso> listOfInFolioProceso;
	public static volatile ListAttribute<StProceso, StReglaInstanciaProceso> listOfStReglaInstanciaProceso;
	public static volatile ListAttribute<StProceso, CtInstanciaSemana> listOfCtInstanciaSemana;
	public static volatile ListAttribute<StProceso, CtInstanciaDiaMes> listOfCtInstanciaDiaMes;
	public static volatile SingularAttribute<StProceso, BigDecimal> idNodoFin;
	public static volatile SingularAttribute<StProceso, TipoNodo> tipoNodo2;
	public static volatile ListAttribute<StProceso, StRolProceso> listOfStRolProceso;
	public static volatile ListAttribute<StProceso, StReglaValidacionProceso> listOfStReglaValidacionProceso;
	public static volatile SingularAttribute<StProceso, BigDecimal> idNodoInicio;
	public static volatile ListAttribute<StProceso, InProceso> listOfInProceso;
	public static volatile SingularAttribute<StProceso, BigDecimal> instanciasDia;
	public static volatile SingularAttribute<StProceso, TipoNodo> tipoNodo;
	public static volatile SingularAttribute<StProceso, String> situacion;
	public static volatile SingularAttribute<StProceso, BigDecimal> instanciasMes;
	public static volatile ListAttribute<StProceso, CtInstanciaDiaProceso> listOfCtInstanciaDiaProceso;
	public static volatile ListAttribute<StProceso, StProgramacionProceso> listOfStProgramacionProceso;
	public static volatile SingularAttribute<StProceso, StProcesoPK> id;
	public static volatile ListAttribute<StProceso, StVariableProceso> listOfStVariableProceso;
	public static volatile ListAttribute<StProceso, StNodoProceso> listOfStNodoProceso;

}

