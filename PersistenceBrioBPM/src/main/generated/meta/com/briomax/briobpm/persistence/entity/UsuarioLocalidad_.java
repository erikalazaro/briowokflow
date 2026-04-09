package com.briomax.briobpm.persistence.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UsuarioLocalidad.class)
public abstract class UsuarioLocalidad_ {

	public static volatile SingularAttribute<UsuarioLocalidad, LocalidadEntidad> localidadEntidad;
	public static volatile ListAttribute<UsuarioLocalidad, Facultad> listOfFacultad;
	public static volatile SingularAttribute<UsuarioLocalidad, String> tipo;
	public static volatile ListAttribute<UsuarioLocalidad, Consulta> listOfConsulta;
	public static volatile ListAttribute<UsuarioLocalidad, UsuarioCorreo> listOfUsuarioCorreo;
	public static volatile ListAttribute<UsuarioLocalidad, UsuarioTelefono> listOfUsuarioTelefono;
	public static volatile SingularAttribute<UsuarioLocalidad, String> nombre;
	public static volatile SingularAttribute<UsuarioLocalidad, byte[]> fotografia;
	public static volatile SingularAttribute<UsuarioLocalidad, String> password;
	public static volatile ListAttribute<UsuarioLocalidad, StRolProceso> listOfStRolProceso;
	public static volatile SingularAttribute<UsuarioLocalidad, String> situacion;
	public static volatile SingularAttribute<UsuarioLocalidad, UsuarioLocalidadPK> id;
	public static volatile SingularAttribute<UsuarioLocalidad, Date> fechaPassword;

}

