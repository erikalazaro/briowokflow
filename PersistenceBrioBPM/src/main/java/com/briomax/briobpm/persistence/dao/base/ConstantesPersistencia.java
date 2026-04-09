
package com.briomax.briobpm.persistence.dao.base;

import java.text.SimpleDateFormat;

public interface ConstantesPersistencia {

	/** La Constante FORMATO_FECHA. */
	public static final SimpleDateFormat FORMATO_FECHA = new SimpleDateFormat("dd/MM/yyyy");

	/** La Constante FORMATO_FECHA. */
	public static final String FORMATO_FECHA_ORACLE = "DD/MM/YYYY";

	/** La Constante VACIO. */
	public static final String VACIO = "";

	/** La Constante ESPACIO. */
	public static final String ESPACIO = " ";

	/** La Constante CERO. */
	public static final int CERO = 0;

	/** La Constante UNO. */
	public static final int UNO = 1;

	/** La Constante DOS. */
	public static final int DOS = 2;

	/** La Constante TRES. */
	public static final int TRES = 3;

	/** La Constante CUATRO. */
	public static final int CUATRO = 4;

	/** La Constante CINCO. */
	public static final int CINCO = 5;

	/** La Constante SEIS. */
	public static final int SEIS = 6;

	/** La Constante SIETE. */
	public static final int SIETE = 7;

	/** La Constante OCHO. */
	public static final int OCHO = 8;

	/** La Constante NUEVE. */
	public static final int NUEVE = 9;

	/** La Constante DIEZ. */
	public static final int DIEZ = 10;

	/** La Constante ONCE. */
	public static final int ONCE = 11;

	/** La Constante DOCE. */
	public static final int DOCE = 12;

	/** La Constante TRECE. */
	public static final int TRECE = 13;

	/** La Constante CATORCE. */
	public static final int CATORCE = 14;

	/** La Constante QUINCE. */
	public static final int QUINCE = 15;

	/** La Constante DATA_ACCESS_EXCEPTION. */
	public static final int DATA_ACCESS_EXCEPTION = 90001;

	/** La Constante HIBERNATE_EXCEPTION. */
	public static final int HIBERNATE_EXCEPTION = 90002;

	/** La Constante UNCATEGORIZED_SQLEXCEPTION. */
	public static final int UNCATEGORIZED_SQLEXCEPTION = 90003;

	/** La Constante INVALIDDATAACCESSRESOURCEUSAGE_EXCEPTION. */
	public static final int INVALIDDATAACCESSRESOURCEUSAGE_EXCEPTION = 90004;

	/** La Constante NULL_POINTER_EXCEPTION. */
	public static final int NULL_POINTER_EXCEPTION = 90005;

	/** La Constante DATA_INTEGRITY_VIOLATION_EXCEPTION. */
	public static final int DATA_INTEGRITY_VIOLATION_EXCEPTION = 90006;

	/** La Constante CONSTRAINT_VIOLATION_EXCEPTION. */
	public static final int CONSTRAINT_VIOLATION_EXCEPTION = 90007;

	/** La Constante INSTANTATION_EXCEPTION . */
	public static final int INSTANTIATION_EXCEPTION = 90008;

	/** La Constante ILLEGALACCESS_EXCEPTION . */
	public static final int ILLEGALACCESS_EXCEPTION = 90009;

	/** La Constante PERSISTENCE_EXCEPTION . */
	public static final int PERSISTENCE_EXCEPTION = 90010;

	/** La Constante ILLEGALSTATE_EXCEPTION . */
	public static final int ILLEGALSTATE_EXCEPTION = 90011;

	/** La Constante QUERYTIMEOUT_EXCEPTION . */
	public static final int QUERYTIMEOUT_EXCEPTION = 90012;

	/** La Constante UNEXPECTED_EXCEPTION . */
	public static final int UNEXPECTED_EXCEPTION = 90013;

	/** La Constante CANNOTADQUIRELOCK_EXCEPTION . */
	public static final int CANNOT_ADQUIRE_LOCK_EXCEPTION = 90014;

	/** La Constante PESSIMISTIC_LOCKING_FAILURE_EXCEPTION . */
	public static final int PESSIMISTIC_LOCKING_FAILURE_EXCEPTION = 90015;

	/** La Constante INCORRECT_RESOURCE_SIZE_DATA_ACCESS_EXCEPTION . */
	public static final int INCORRECT_RESOURCE_SIZE_DATA_ACCESS_EXCEPTION = 90016;

	/** La Constante DUPLICATE_KEY_EXCEPTION . */
	public static final int DUPLICATE_KEY_EXCEPTION = 90017;

	/** La Constante INVALID_DATA_ACCESS_API_USAGE_EXCEPTION. */
	public static final int INVALID_DATA_ACCESS_API_USAGE_EXCEPTION = 90018;

	/** La Constante OBJECT_RETRIEVAL_FAILURE_EXCEPTION. */
	public static final int OBJECT_RETRIEVAL_FAILURE_EXCEPTION = 90019;

	/** La Constante OBJECT_OPTIMISTIC_LOCKING_FAILURE_EXCEPTION. */
	public static final int OBJECT_OPTIMISTIC_LOCKING_FAILURE_EXCEPTION = 90020;

	/** La Constante JPASYSTEM_EXCEPTION. */
	public static final int JPASYSTEM_EXCEPTION = 90021;

	/** La Constante ENTITY_EXISTS_EXCEPTION. */
	public static final int ENTITY_EXISTS_EXCEPTION = 90022;

}
