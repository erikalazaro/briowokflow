/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.persistence.dao.base;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.jpa.QueryHints;
import org.hibernate.query.procedure.ProcedureParameter;

import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.IOutsParams;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.stereotype.Repository;


@SuppressWarnings({ "unchecked", "rawtypes" })
@Repository
public abstract class AbstractBaseDAO implements ConstantesPersistencia {

	/** La Constante ACCION. */
	private static final String ACCION = "Contactar al Administrador de Bases de Datos.";

	/** El atributo o variable entity manager. */
	protected EntityManager entityManager;

	/**
	 * Establecer el valor de entity manager factory.
	 * @param em el nuevo entity manager factory.
	 */
	@PersistenceContext
	public void setEntityManagerFactory(EntityManager em) {
		entityManager = em;
	}
	 

	@Transactional
	public <T> IOutsParams persist(T transientInstance) throws BrioBPMException {
		try {
			entityManager.persist(transientInstance);
			return constructMeta(IOutsParams.OK, "");
		}  catch (InstantiationException e) {
			throw new BrioBPMException(true, INSTANTIATION_EXCEPTION, e.getMessage(), ACCION, e);
		} catch (IllegalAccessException e) {
			throw new BrioBPMException(true, ILLEGALACCESS_EXCEPTION, e.getMessage(), ACCION, e);
		}
	}

	protected IOutsParams constructMeta(String status, String message) throws InstantiationException, IllegalAccessException {
		Class<? extends IOutsParams> meta = RetMsg.class;
		IOutsParams routs = meta.newInstance();
		routs.setStatus(status);
		routs.setMessage(message);
		return routs;
	}

	/**
	 * Fusiona el estado de la entidad de entrada al estado de la entidad
	 * correspondiete en el contexto persistente, Si no existe se crea en el
	 * contexto persistente.
	 * 
	 * @param entity La entidad de entrada.
	 *            <P>
	 *            Objeto.
	 * @throws BrioBPMException
	 */
	public <T> IOutsParams merge(T entity) throws BrioBPMException {
		try {
			entityManager.merge(entity);
			//entityManager.flush();
			return constructMeta(IOutsParams.OK, "");
		} catch (InstantiationException e) {
			throw new BrioBPMException(true, INSTANTIATION_EXCEPTION, e.getMessage(), ACCION, e);
		} catch (IllegalAccessException e) {
			throw new BrioBPMException(true, ILLEGALACCESS_EXCEPTION, e.getMessage(), ACCION, e);
		}
	}

	/**
	 * Sobrescribe la entidad de entrada con el estado de la entidad
	 * correspondiente en el contexto persistente. Solo tiene sentido para
	 * cuando la entidad de entrada es "managed" No tiene sentido para entidades
	 * new, detached o removed
	 * 
	 * @param entity the instance.
	 */
	public <T> void refresh(T managedEntity) {
		entityManager.refresh(managedEntity);
	}

	/**
	 * Elimina una entidad del contexto persistente. Solo tiene sentido para
	 * entidades "managed" No tiene sentido para entidades new, detached o
	 * removed
	 * 
	 * @param managedEntity La entidad a ser eliminada.
	 * 
	 *            <P>
	 *            DTO a eliminar en la tabla.
	 */
	public <T> void delete(T managedEntity) {
		entityManager.remove(managedEntity);
	}
	
	/**
	 * Elimina una entidad del contexto persistente. Solo tiene sentido para
	 * entidades "managed" No tiene sentido para entidades new, detached o
	 * removed
	 * 
	 * @param managedEntity La entidad a ser eliminada.
	 * 
	 *            <P>
	 *            DTO a eliminar en la tabla.
	 */
	public <T> void delete(Set<T> managedEntities) {
		for (T entity: managedEntities){
			entityManager.remove(entity);
		}
	}
	
	public <T> void flush() {
		entityManager.flush();
	}

	/**
	 * <P>
	 * Finds persistent objects from the store by Example provided.
	 * 
	 * @param instance the persistent object to use an as example.
	 * @return a List of all objects found.
	 */
	public <T> T findByPrimaryKey(Class<T> entityClass, Object primaryKey) {
		return entityManager.find(entityClass, primaryKey);
	}

	/**
	 * Execute and transform.
	 * 
	 * @param <D> the generic type.
	 * @param qry the qry.
	 * @param retClass the ret class.
	 * @return the list.
	 * @throws BrioBPMException the contratacion linea exception.
	 */
	public <D> List<D> executeAndTransform(final String sqlqry, final Class<D> retClass) throws BrioBPMException {

		try {
			return entityManager.createNativeQuery(sqlqry, retClass).getResultList();
		} catch (NullPointerException npe) {
			throw new BrioBPMException(true, NULL_POINTER_EXCEPTION, npe.getMessage(), ACCION, npe);
		} catch (InvalidDataAccessResourceUsageException idarue) {
			throw new BrioBPMException(true, INVALIDDATAACCESSRESOURCEUSAGE_EXCEPTION, idarue.getMessage(), ACCION, idarue);
		} catch (DataAccessException dae) {
			throw new BrioBPMException(true, DATA_ACCESS_EXCEPTION, dae.getMessage(), ACCION, dae);
		} catch (HibernateException he) {
			throw new BrioBPMException(true, HIBERNATE_EXCEPTION, he.getMessage(), ACCION, he);
		} catch (Throwable throwable) {
			throw new BrioBPMException(true, UNEXPECTED_EXCEPTION, throwable.getMessage(), ACCION, throwable);
		}
	}

	/**
	 * Execute named query sin parametros y sin EntityGraph.
	 * 
	 * @param R tipo generico de la clase con meta información sobre el
	 *            resultado.
	 * @param T tipo generico de los datos recuperados por el Query
	 * @param namedqry Named Query.
	 * @param outcls Clase de metainformación sobre el resultado.
	 * @return Objeto DAORet conteniendo una lista con los resultados del query
	 *         y meta informacion sobre el resultado.
	 * @throws BrioBPMException Diferentes excepciones no cacheables se traducen
	 *             a una BrioBPMException.
	 */
	public <R extends IOutsParams, T> DAORet<List<T>, R> executeNamedQuery(final String namedqry, Class<R> outcls) throws BrioBPMException {
		return executeNamedQuery(namedqry, null, null, null, outcls);
	}

	/**
	 * Execute named query con parametros y sin EntityGraph.
	 * 
	 * @param R tipo generico de la clase con meta información sobre el
	 *            resultado.
	 * @param T tipo generico de los datos recuperados por el Query
	 * @param namedqry Named Query.
	 * @param params Nombre de parametros.
	 * @param values Valores de parametros.
	 * @param outcls Clase de metainformación sobre el resultado.
	 * @return Objeto DAORet conteniendo una lista con los resultados del query
	 *         y meta informacion sobre el resultado.
	 * @throws BrioBPMException Diferentes excepciones no cacheables se traducen
	 *             a una BrioBPMException.
	 */
	public <R extends IOutsParams, T> DAORet<List<T>, R> executeNamedQuery(final String namedqry, String[] params, Object[] values, Class<R> outcls) throws BrioBPMException {
		return executeNamedQuery(namedqry, params, values, null, outcls);
	}

	/**
	 * Execute named query sin parametros y con EntityGraph.
	 * 
	 * @param R tipo generico de la clase con meta información sobre el
	 *            resultado.
	 * @param T tipo generico de los datos recuperados por el Query
	 * @param namedqry Named Query.
	 * @param egraph Nombre del entity graph.
	 * @param outcls Clase de metainformación sobre el resultado.
	 * @return Objeto DAORet conteniendo una lista con los resultados del query
	 *         y meta informacion sobre el resultado.
	 * @throws BrioBPMException Diferentes excepciones no cacheables se traducen
	 *             a una BrioBPMException.
	 */
	public <R extends IOutsParams, T> DAORet<List<T>, R> executeNamedQuery(final String namedqry, String egraph, Class<R> outcls) throws BrioBPMException {
		return executeNamedQuery(namedqry, null, null, egraph, outcls);
	}

	/**
	 * Execute named query con parametros y con EntityGraph.
	 * 
	 * @param R tipo generico de la clase con meta información sobre el
	 *            resultado.
	 * @param T tipo generico de los datos recuperados por el Query
	 * @param namedqry Named Query.
	 * @param egraph Nombre del entity graph.
	 * @param outcls Clase de metainformación sobre el resultado.
	 * @return Objeto DAORet conteniendo una lista con los resultados del query
	 *         y meta informacion sobre el resultado.
	 * @throws BrioBPMException Diferentes excepciones no cacheables se traducen
	 *             a una BrioBPMException.
	 */
	public <R extends IOutsParams, T> DAORet<List<T>, R> executeNamedQuery(final String namedqry, String[] params, Object[] values, String egraph, Class<R> outcls) throws BrioBPMException {

		try {
			Query qry = entityManager.createNamedQuery(namedqry);
			if (egraph != null) {
				EntityGraph<?> graph = entityManager.getEntityGraph(egraph);
				qry.setHint(QueryHints.HINT_FETCHGRAPH, graph);
			}
			if (params != null) {
				final HashMap<String, Object> parametersMap = generarMap(params, values);
				setNamedParameters(qry, parametersMap);
			}
			List<T> rs = qry.getResultList();
			IOutsParams routs = outcls.newInstance();
			routs.setStatus("OK");
			routs.setMessage("");
			return new DAORet(rs, routs);
		} catch (NullPointerException npe) {
			throw new BrioBPMException(true, NULL_POINTER_EXCEPTION, npe.getMessage(), ACCION, npe);
		} catch (InvalidDataAccessResourceUsageException idarue) {
			throw new BrioBPMException(true, INVALIDDATAACCESSRESOURCEUSAGE_EXCEPTION, idarue.getMessage(), ACCION, idarue);
		} catch (DataAccessException dae) {
			throw new BrioBPMException(true, DATA_ACCESS_EXCEPTION, dae.getMessage(), ACCION, dae);
		} catch (HibernateException he) {
			throw new BrioBPMException(true, HIBERNATE_EXCEPTION, he.getMessage(), ACCION, he);
		} catch (InstantiationException e) {
			throw new BrioBPMException(true, INSTANTIATION_EXCEPTION, e.getMessage(), ACCION, e);
		} catch (IllegalAccessException e) {
			throw new BrioBPMException(true, ILLEGALACCESS_EXCEPTION, e.getMessage(), ACCION, e);
		} catch (PersistenceException cv) {
			if (cv.getCause() instanceof ConstraintViolationException) {
				throw new BrioBPMException(true, CONSTRAINT_VIOLATION_EXCEPTION, cv.getMessage(), ACCION, cv);
			} else {
				throw new BrioBPMException(true, PERSISTENCE_EXCEPTION, cv.getMessage(), ACCION, cv);
			}
		} catch (Throwable throwable) {
				throw new BrioBPMException(true, UNEXPECTED_EXCEPTION, throwable.getMessage(), ACCION, throwable);
			}
	}

	/**
	 * Execute named update query con parametros.
	 * 
	 * @param namedupdqry Named Query.
	 * @throws BrioBPMException Diferentes excepciones no cacheables se traducen
	 *         a una BrioBPMException.
	 */
	public IOutsParams executeNamedUpdateQuery(final String namedupdqry) throws BrioBPMException {
		return executeNamedUpdateQuery(namedupdqry, null, null);
	}
		
	
	/**
	 * Execute named update query con parametros.
	 * 
	 * @param namedupdqry Named Query.
	 * @param params Nombre de los parametros al query.
	 * @param values Valores de los parametros al query.
	 * @throws BrioBPMException Diferentes excepciones no cacheables se traducen
	 *         a una BrioBPMException.
	 */
	public IOutsParams executeNamedUpdateQuery(final String namedupdqry, String[] params, Object[] values) throws BrioBPMException {

		try {
			Query qry = entityManager.createNamedQuery(namedupdqry);
			if (params != null) {
				final HashMap<String, Object> parametersMap = generarMap(params, values);
				setNamedParameters(qry, parametersMap);
			}
			int cnt = qry.executeUpdate();
			return constructMeta(IOutsParams.OK, String.valueOf(cnt));
		} catch (IllegalStateException e) {
			throw new BrioBPMException(true, ILLEGALSTATE_EXCEPTION, e.getMessage(), ACCION, e);
		} catch (PersistenceException e) {
			throw new BrioBPMException(true, PERSISTENCE_EXCEPTION, e.getMessage(), ACCION, e);
		} catch (QueryTimeoutException dae) {
			throw new BrioBPMException(true, QUERYTIMEOUT_EXCEPTION, dae.getMessage(), ACCION, dae);
		} catch (InstantiationException e) {
			throw new BrioBPMException(true, INSTANTIATION_EXCEPTION, e.getMessage(), ACCION, e);
		} catch (IllegalAccessException e) {
			throw new BrioBPMException(true, ILLEGALACCESS_EXCEPTION, e.getMessage(), ACCION, e);
		} catch (Throwable throwable) {
			throw new BrioBPMException(true, UNEXPECTED_EXCEPTION, throwable.getMessage(), ACCION, throwable);
		}
	}

	/**
	 * Execute and transform.
	 * 
	 * @param <D> the generic type.
	 * @param qry the qry.
	 * @param params the params.
	 * @param values the values.
	 * @param retClass the ret class.
	 * @return the list.
	 * @throws BrioBPMException the contratacion linea exception.
	 */
	public <D> List<D> executeAndTransform(final String sqlqry, String[] params, Object[] values, final Class<D> retClass) throws BrioBPMException {

		try {
			final HashMap<String, Object> parametersMap = generarMap(params, values);

			Query qry = entityManager.createNativeQuery(sqlqry, retClass);
			setNamedParameters(qry, parametersMap);
			return qry.getResultList();
		} catch (NullPointerException npe) {
			throw new BrioBPMException(true, NULL_POINTER_EXCEPTION, npe.getMessage(), ACCION, npe);
		} catch (InvalidDataAccessResourceUsageException idarue) {
			throw new BrioBPMException(true, INVALIDDATAACCESSRESOURCEUSAGE_EXCEPTION, idarue.getMessage(), ACCION, idarue);
		} catch (DataAccessException dae) {
			throw new BrioBPMException(true, DATA_ACCESS_EXCEPTION, dae.getMessage(), ACCION, dae);
		} catch (HibernateException he) {
			throw new BrioBPMException(true, HIBERNATE_EXCEPTION, he.getMessage(), ACCION, he);
		} catch (PersistenceException pe) {
			throw new BrioBPMException(true, PERSISTENCE_EXCEPTION, pe.getMessage(), ACCION, pe);
		} catch (Throwable throwable) {
			throw new BrioBPMException(true, UNEXPECTED_EXCEPTION, throwable.getMessage(), ACCION, throwable);
		}
	}

	/**
	 * Execute update.
	 * @param sqlqry el sqlqry.
	 * @param params el params.
	 * @param values el values.
	 * @return el integer.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	public Integer executeUpdate(final String sqlqry, String[] params, Object[] values) throws BrioBPMException {
		try {
			final HashMap<String, Object> parametersMap = generarMap(params, values);
			Query qry = entityManager.createNativeQuery(sqlqry);
			setNamedParameters(qry, parametersMap);
			return qry.executeUpdate();
		} catch (NullPointerException npe) {
			throw new BrioBPMException(true, NULL_POINTER_EXCEPTION, npe.getMessage(), ACCION, npe);
		} catch (InvalidDataAccessResourceUsageException idarue) {
			throw new BrioBPMException(true, INVALIDDATAACCESSRESOURCEUSAGE_EXCEPTION, idarue.getMessage(), ACCION, idarue);
		} catch (DataAccessException dae) {
			throw new BrioBPMException(true, DATA_ACCESS_EXCEPTION, dae.getMessage(), ACCION, dae);
		} catch (HibernateException he) {
			throw new BrioBPMException(true, HIBERNATE_EXCEPTION, he.getMessage(), ACCION, he);
		} catch (PersistenceException pe) {
			throw new BrioBPMException(true, PERSISTENCE_EXCEPTION, pe.getMessage(), ACCION, pe);
		} catch (Throwable throwable) {
			throw new BrioBPMException(true, UNEXPECTED_EXCEPTION, throwable.getMessage(), ACCION, throwable);
		}
	}

	/**
	 * Execute and transform.
	 * 
	 * @param <D> the generic type.
	 * @param qry the qry.
	 * @param retClass the ret class.
	 * @return the list.
	 * @throws BrioBPMException the contratacion linea exception.
	 */
	public <D> List<D> executeNamedStored(final String sqlqry, final Class<D> retClass) throws BrioBPMException {
		try {
			return entityManager.createNamedStoredProcedureQuery(sqlqry).getResultList();
		} catch (NullPointerException npe) {
			throw new BrioBPMException(true, NULL_POINTER_EXCEPTION, npe.getMessage(), ACCION, npe);
		} catch (InvalidDataAccessResourceUsageException idarue) {
			throw new BrioBPMException(true, INVALIDDATAACCESSRESOURCEUSAGE_EXCEPTION, idarue.getMessage(), ACCION, idarue);
		} catch (DataAccessException dae) {
			throw new BrioBPMException(true, DATA_ACCESS_EXCEPTION, dae.getMessage(), ACCION, dae);
		} catch (HibernateException he) {
			throw new BrioBPMException(true, HIBERNATE_EXCEPTION, he.getMessage(), ACCION, he);
		} catch (PersistenceException pe) {
			throw new BrioBPMException(true, PERSISTENCE_EXCEPTION, pe.getMessage(), ACCION, pe);
		} catch (Throwable throwable) {
			throw new BrioBPMException(true, UNEXPECTED_EXCEPTION, throwable.getMessage(), ACCION, throwable);
		}
	}

	/**
	 * Execute and transform.
	 * 
	 * @param <D> the generic type.
	 * @param qry the qry.
	 * @param retClass the ret class.
	 * @return the list.
	 * @throws BrioBPMException the contratacion linea exception.
	 */
	public <R extends IOutsParams, T> DAORet<List<T>, R> executeNamedStored(final String sqlqry, String[] params, Object[] values, Class<R> outcls) throws BrioBPMException {

		try {
			final HashMap<String, Object> parametersMap = generarMap(params, values);
			StoredProcedureQuery qry = entityManager.createNamedStoredProcedureQuery(sqlqry);
			setNamedParameters(qry, parametersMap);
			HashMap<String, Object> outs = null;
			List<T> rs = null;
			/*
			if (qry.execute()) {
				outs = getProcedureOutParameters(qry);
				rs = qry.getResultList();
			}
			IOutsParams routs = outcls.newInstance();
			routs.setOutParams(outs);
			*/
			IOutsParams routs = outcls.newInstance();
			if (qry.execute()) {
				outs = getProcedureOutParameters(qry);
				rs = qry.getResultList();
			} else {
				outs = getProcedureOutParameters(qry);
				rs = new LinkedList<T>();
			}
			routs.setOutParams(outs);
			
			return new DAORet(rs, routs);
		} catch (NullPointerException npe) {
			throw new BrioBPMException(true, NULL_POINTER_EXCEPTION, npe.getMessage(), ACCION, npe);
		} catch (InvalidDataAccessResourceUsageException idarue) {
			throw new BrioBPMException(true, INVALIDDATAACCESSRESOURCEUSAGE_EXCEPTION, idarue.getMessage(), ACCION, idarue);
		} catch (DataAccessException dae) {
			throw new BrioBPMException(true, DATA_ACCESS_EXCEPTION, dae.getMessage(), ACCION, dae);
		} catch (HibernateException he) {
			throw new BrioBPMException(true, HIBERNATE_EXCEPTION, he.getMessage(), ACCION, he);
		} catch (InstantiationException e) {
			throw new BrioBPMException(true, INSTANTIATION_EXCEPTION, e.getMessage(), ACCION, e);
		} catch (IllegalAccessException e) {
			throw new BrioBPMException(true, ILLEGALACCESS_EXCEPTION, e.getMessage(), ACCION, e);
		} catch (PersistenceException cv) {
			if (cv.getCause() instanceof ConstraintViolationException) {
				throw new BrioBPMException(true, CONSTRAINT_VIOLATION_EXCEPTION, cv.getMessage(), ACCION, cv);
			} else {
				throw new BrioBPMException(true, PERSISTENCE_EXCEPTION, cv.getMessage(), ACCION, cv);
			}
		} catch (Throwable throwable) {
			throw new BrioBPMException(true, UNEXPECTED_EXCEPTION, throwable.getMessage(), ACCION, throwable);
		}
	}

	/**
	 * Ejecuta un no named stored procedure que no regresa ResultSet.
	 * 
	 * @param procName el nombre del stored.
	 * @param params nombre de los parametros del stored.
	 * @param clases de los parametros, corresponden en orden con params.
	 * @param modes modo de los parametros del stored (IN, INOUT y OUT).
	 * @param invalues valos parametros IN se tomaran en el mismo orden de los
	 *            IN en params.
	 * @return
	 * @throws BrioBPMException the contratacion linea exception.
	 */
	public <R extends IOutsParams> R executeNoResulsetStored(final String procName, String[] params, Class[] classes, ParameterMode[] modes, Object[] invalues, Class<R> outcls) throws BrioBPMException {

		try {
			StoredProcedureQuery qry = entityManager.createStoredProcedureQuery(procName);
			int j = 0;
			for (int i = 0; i < params.length; i++) {
				qry.registerStoredProcedureParameter(params[i], classes[i], modes[i]);
				if (modes[i] == ParameterMode.IN || modes[i] == ParameterMode.INOUT) {
					qry.setParameter(params[i], invalues[j++]);
				}
			}
			HashMap<String, Object> outs = null;
			if (!qry.execute()) {
				outs = getProcedureOutParameters(qry);
			}
			R routs = outcls.newInstance();
			routs.setOutParams(outs);
			return routs;
		} catch (NullPointerException npe) {
			throw new BrioBPMException(true, NULL_POINTER_EXCEPTION, npe.getMessage(), ACCION, npe);
		} catch (InvalidDataAccessResourceUsageException idarue) {
			throw new BrioBPMException(true, INVALIDDATAACCESSRESOURCEUSAGE_EXCEPTION, idarue.getMessage(), ACCION, idarue);
		} catch (DataAccessException dae) {
			throw new BrioBPMException(true, DATA_ACCESS_EXCEPTION, dae.getMessage(), ACCION, dae);
		} catch (InstantiationException e) {
			throw new BrioBPMException(true, INSTANTIATION_EXCEPTION, e.getMessage(), ACCION, e);
		} catch (IllegalAccessException e) {
			throw new BrioBPMException(true, ILLEGALACCESS_EXCEPTION, e.getMessage(), ACCION, e);
		} catch (HibernateException he) {
			if (he.getCause() instanceof ConstraintViolationException) {
				throw new BrioBPMException(true, CONSTRAINT_VIOLATION_EXCEPTION, he.getMessage(), ACCION, he);
			} else {
				throw new BrioBPMException(true, PERSISTENCE_EXCEPTION, he.getMessage(), ACCION, he);
			}
		}
		catch (PersistenceException cv) {
			if (cv.getCause() instanceof ConstraintViolationException) {
				throw new BrioBPMException(true, CONSTRAINT_VIOLATION_EXCEPTION, cv.getMessage(), ACCION, cv);
			} else {
				throw new BrioBPMException(true, PERSISTENCE_EXCEPTION, cv.getMessage(), ACCION, cv);
			}
		} catch (Throwable throwable) {
			throw new BrioBPMException(true, UNEXPECTED_EXCEPTION, throwable.getMessage(), ACCION, throwable);
		}
	}

	/**** PRIVATES *****/

	/**
	 * Generar map.
	 * 
	 * @param params the params.
	 * @param values the values.
	 * @return the hash map.
	 */
	private HashMap<String, Object> generarMap(String[] params, Object[] values) {
		HashMap<String, Object> parametersMap = new HashMap<String, Object>();
		if (params == null || values == null) {
			return parametersMap;
		}

		int i = 0;
		for (String s : params) {
			parametersMap.put(s, values[i++]);
		}

		return parametersMap;
	}

	/**
	 * Inciailiza parametros con nombre en query.
	 * 
	 * @param qry El objeto Query.
	 * @param params Los parametros.
	 */

	private void setNamedParameters(Query qry, HashMap<String, Object> params) {
		for (String key : params.keySet()) {
			qry.setParameter(key, params.get(key));
		}
	}

	/**
	 * Incializa parametros posicionales en query.
	 * 
	 * @param qry El objeto Query.
	 * @param params Los parametros.
	 */
	private void setPositionalParameters(Query qry, Object[] params) {
		int i = 0;
		for (Object p : params) {
			qry.setParameter(i++, p);
		}
	}

	/**
	 * Incializa parametros posicionales de salida de stored en query.
	 * 
	 * @param qry El objeto Query.
	 * @param params Los parametros.
	 */
	private HashMap<String, Object> getProcedureOutParameters(StoredProcedureQuery spqry) {
		HashMap<String, Object> res = new HashMap<String, Object>();
		Set<Parameter<?>> declared = spqry.getParameters();
		for (Parameter p : declared) {
			ProcedureParameter pp = (ProcedureParameter) p;
			if (pp.getMode() == ParameterMode.OUT || pp.getMode() == ParameterMode.INOUT) {
				res.put(pp.getName(), spqry.getOutputParameterValue(pp.getName()));
			}
		}
		return res;
	}
}