
package com.briomax.briobpm.persistence.entity;

import java.io.Serializable;

public interface IEntity extends Serializable {

	public boolean equals(Object object);

	public int hashCode();

	public String toString();
}
