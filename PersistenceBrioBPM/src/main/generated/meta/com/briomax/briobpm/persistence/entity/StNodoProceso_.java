package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(StNodoProceso.class)
public abstract class StNodoProceso_ {

	public static volatile SingularAttribute<StNodoProceso, String> descripcion;
	public static volatile ListAttribute<StNodoProceso, StDocumentoNodo> listOfStDocumentoNodo;
	public static volatile SingularAttribute<StNodoProceso, String> baseHorarioNivelSer;
	public static volatile SingularAttribute<StNodoProceso, StProceso> stProceso;
	public static volatile SingularAttribute<StNodoProceso, BigDecimal> avanceAcumulado;
	public static volatile ListAttribute<StNodoProceso, StTareaNodo> listOfStTareaNodo;
	public static volatile ListAttribute<StNodoProceso, Consulta> listOfConsulta;
	public static volatile SingularAttribute<StNodoProceso, BigDecimal> plazoNivelServicio;
	public static volatile SingularAttribute<StNodoProceso, BigDecimal> tiempoEspera;
	public static volatile ListAttribute<StNodoProceso, InFolioNodo> listOfInFolioNodo;
	public static volatile ListAttribute<StNodoProceso, InNodoProceso> listOfInNodoProceso;
	public static volatile ListAttribute<StNodoProceso, StSeccionNodo> listOfStSeccionNodo;
	public static volatile SingularAttribute<StNodoProceso, String> baseCalculoNivelSer;
	public static volatile ListAttribute<StNodoProceso, StRolProceso> listOfStRolProceso;
	public static volatile SingularAttribute<StNodoProceso, String> cveReglaCiclicidad;
	public static volatile SingularAttribute<StNodoProceso, BigDecimal> instanciasCiclicidad;
	public static volatile SingularAttribute<StNodoProceso, TipoNodo> tipoNodo;
	public static volatile SingularAttribute<StNodoProceso, String> situacion;
	public static volatile ListAttribute<StNodoProceso, StNodoSiguiente> listOfStNodoSiguiente;
	public static volatile ListAttribute<StNodoProceso, StNodoSiguiente> listOfStNodoSiguiente2;
	public static volatile SingularAttribute<StNodoProceso, String> sincrono;
	public static volatile SingularAttribute<StNodoProceso, StNodoProcesoPK> id;
	public static volatile SingularAttribute<StNodoProceso, String> unidadTiempoEspera;
	public static volatile SingularAttribute<StNodoProceso, AreaTrabajo> areaTrabajo;

}

