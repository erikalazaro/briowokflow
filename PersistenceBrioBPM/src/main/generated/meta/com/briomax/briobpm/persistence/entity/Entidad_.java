package com.briomax.briobpm.persistence.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Entidad.class)
public abstract class Entidad_ {

	public static volatile SingularAttribute<Entidad, String> descripcion;
	public static volatile SingularAttribute<Entidad, String> extension;
	public static volatile SingularAttribute<Entidad, String> codigoPostal;
	public static volatile ListAttribute<Entidad, VariableEntidad> listOfVariableEntidad;
	public static volatile SingularAttribute<Entidad, String> calle;
	public static volatile SingularAttribute<Entidad, String> cveEntidad;
	public static volatile SingularAttribute<Entidad, Idioma> idioma;
	public static volatile SingularAttribute<Entidad, String> numeroInterior;
	public static volatile SingularAttribute<Entidad, String> colonia;
	public static volatile SingularAttribute<Entidad, String> pais;
	public static volatile ListAttribute<Entidad, LocalidadEntidad> listOfLocalidadEntidad;
	public static volatile SingularAttribute<Entidad, String> delegacionMunicipio;
	public static volatile SingularAttribute<Entidad, String> situacion;
	public static volatile SingularAttribute<Entidad, String> logotipo;
	public static volatile SingularAttribute<Entidad, Moneda> moneda;
	public static volatile SingularAttribute<Entidad, String> numeroExterior;
	public static volatile SingularAttribute<Entidad, String> telefono;
	public static volatile SingularAttribute<Entidad, String> correoElectronico;

}

