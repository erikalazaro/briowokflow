
package com.briomax.briobpm.persistence.dao.base;

public interface IBaseDAO {
	<T> T findByPrimaryKey(Class<T> entityClass, Object primaryKey);
}
