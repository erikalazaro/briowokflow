package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InhabilCalendarioPK.class)
public abstract class InhabilCalendarioPK_ {

	public static volatile SingularAttribute<InhabilCalendarioPK, Date> fechaInhabil;
	public static volatile SingularAttribute<InhabilCalendarioPK, String> cveEntidad;
	public static volatile SingularAttribute<InhabilCalendarioPK, String> cveLocalidad;
	public static volatile SingularAttribute<InhabilCalendarioPK, BigDecimal> anio;

}

