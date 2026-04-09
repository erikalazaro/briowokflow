package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InNodoProceso.class)
public abstract class InNodoProceso_ {

	public static volatile SingularAttribute<InNodoProceso, String> rolBloquea;
	public static volatile SingularAttribute<InNodoProceso, String> estado;
	public static volatile ListAttribute<InNodoProceso, InDocumentoNodo> listOfInDocumentoNodo;
	public static volatile SingularAttribute<InNodoProceso, Date> fechaFinEspera;
	public static volatile SingularAttribute<InNodoProceso, String> usuarioBloquea;
	public static volatile SingularAttribute<InNodoProceso, InNodoProceso> inNodoProceso;
	public static volatile SingularAttribute<InNodoProceso, Date> fechaBloquea;
	public static volatile SingularAttribute<InNodoProceso, String> origen;
	public static volatile SingularAttribute<InNodoProceso, String> rolCreador;
	public static volatile ListAttribute<InNodoProceso, InNodoProceso> listOfInNodoProceso;
	public static volatile SingularAttribute<InNodoProceso, String> comentario;
	public static volatile SingularAttribute<InNodoProceso, String> usuarioCreador;
	public static volatile SingularAttribute<InNodoProceso, BigDecimal> prioridad;
	public static volatile SingularAttribute<InNodoProceso, Date> fechaEstadoActual;
	public static volatile SingularAttribute<InNodoProceso, Date> fechaLimite;
	public static volatile SingularAttribute<InNodoProceso, Date> fechaCreacion;
	public static volatile SingularAttribute<InNodoProceso, StNodoProceso> stNodoProceso;
	public static volatile SingularAttribute<InNodoProceso, InNodoProcesoPK> id;
	public static volatile SingularAttribute<InNodoProceso, InProceso> inProceso;
	public static volatile ListAttribute<InNodoProceso, InTareaNodo> listOfInTareaNodo;

}

