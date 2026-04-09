package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ContenidoConsulta.class)
public abstract class ContenidoConsulta_ {

	public static volatile SingularAttribute<ContenidoConsulta, String> tipoColumna;
	public static volatile SingularAttribute<ContenidoConsulta, String> columna;
	public static volatile SingularAttribute<ContenidoConsulta, BigDecimal> ordenColumna;
	public static volatile SingularAttribute<ContenidoConsulta, ContenidoConsultaPK> id;
	public static volatile SingularAttribute<ContenidoConsulta, Consulta> consulta;

}

