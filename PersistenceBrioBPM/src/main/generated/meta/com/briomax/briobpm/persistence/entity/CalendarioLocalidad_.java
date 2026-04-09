package com.briomax.briobpm.persistence.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CalendarioLocalidad.class)
public abstract class CalendarioLocalidad_ {

	public static volatile SingularAttribute<CalendarioLocalidad, LocalidadEntidad> localidadEntidad;
	public static volatile SingularAttribute<CalendarioLocalidad, Date> horaInicioLun;
	public static volatile SingularAttribute<CalendarioLocalidad, Date> horaInicioMie;
	public static volatile SingularAttribute<CalendarioLocalidad, String> habilJue;
	public static volatile SingularAttribute<CalendarioLocalidad, Date> horaInicioDom;
	public static volatile SingularAttribute<CalendarioLocalidad, String> habilLun;
	public static volatile SingularAttribute<CalendarioLocalidad, Date> horaInicioSab;
	public static volatile SingularAttribute<CalendarioLocalidad, String> habilMie;
	public static volatile ListAttribute<CalendarioLocalidad, StProgramacionProceso> listOfStProgramacionProceso;
	public static volatile SingularAttribute<CalendarioLocalidad, CalendarioLocalidadPK> id;
	public static volatile SingularAttribute<CalendarioLocalidad, Date> horaFinMar;
	public static volatile SingularAttribute<CalendarioLocalidad, Date> horaInicioVie;
	public static volatile SingularAttribute<CalendarioLocalidad, String> habilDom;
	public static volatile SingularAttribute<CalendarioLocalidad, Date> horaFinLun;
	public static volatile SingularAttribute<CalendarioLocalidad, String> habilSab;
	public static volatile SingularAttribute<CalendarioLocalidad, Date> horaFinMie;
	public static volatile SingularAttribute<CalendarioLocalidad, Date> horaInicioMar;
	public static volatile SingularAttribute<CalendarioLocalidad, String> habilVie;
	public static volatile SingularAttribute<CalendarioLocalidad, Date> horaFinSab;
	public static volatile SingularAttribute<CalendarioLocalidad, Date> horaFinVie;
	public static volatile SingularAttribute<CalendarioLocalidad, Date> horaInicioJue;
	public static volatile SingularAttribute<CalendarioLocalidad, Date> horaFinJue;
	public static volatile SingularAttribute<CalendarioLocalidad, String> habilMar;
	public static volatile ListAttribute<CalendarioLocalidad, InhabilCalendario> listOfInhabilCalendario;
	public static volatile SingularAttribute<CalendarioLocalidad, Date> horaFinDom;

}

