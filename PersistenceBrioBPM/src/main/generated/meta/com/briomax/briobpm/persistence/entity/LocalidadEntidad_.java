package com.briomax.briobpm.persistence.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(LocalidadEntidad.class)
public abstract class LocalidadEntidad_ {

	public static volatile SingularAttribute<LocalidadEntidad, String> descripcion;
	public static volatile SingularAttribute<LocalidadEntidad, HusoHorario> husoHorario;
	public static volatile SingularAttribute<LocalidadEntidad, String> tipo;
	public static volatile SingularAttribute<LocalidadEntidad, String> extension;
	public static volatile SingularAttribute<LocalidadEntidad, String> codigoPostal;
	public static volatile SingularAttribute<LocalidadEntidad, String> calle;
	public static volatile SingularAttribute<LocalidadEntidad, Idioma> idioma;
	public static volatile SingularAttribute<LocalidadEntidad, String> numeroInterior;
	public static volatile ListAttribute<LocalidadEntidad, CalendarioLocalidad> listOfCalendarioLocalidad;
	public static volatile SingularAttribute<LocalidadEntidad, String> colonia;
	public static volatile SingularAttribute<LocalidadEntidad, String> pais;
	public static volatile ListAttribute<LocalidadEntidad, VariableLocalidad> listOfVariableLocalidad;
	public static volatile ListAttribute<LocalidadEntidad, PropositoCorreo> listOfPropositoCorreo;
	public static volatile SingularAttribute<LocalidadEntidad, String> delegacionMunicipio;
	public static volatile SingularAttribute<LocalidadEntidad, Entidad> entidad;
	public static volatile SingularAttribute<LocalidadEntidad, Moneda> moneda;
	public static volatile SingularAttribute<LocalidadEntidad, String> numeroExterior;
	public static volatile ListAttribute<LocalidadEntidad, UsuarioLocalidad> listOfUsuarioLocalidad;
	public static volatile SingularAttribute<LocalidadEntidad, LocalidadEntidadPK> id;
	public static volatile SingularAttribute<LocalidadEntidad, String> telefono;
	public static volatile SingularAttribute<LocalidadEntidad, String> correoElectronico;

}

