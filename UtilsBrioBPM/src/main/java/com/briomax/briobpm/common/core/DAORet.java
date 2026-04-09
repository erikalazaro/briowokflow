
package com.briomax.briobpm.common.core;

import lombok.ToString;

@ToString
public class DAORet<T, U> {

	final U meta;

	final T obj;

	public DAORet(T obj, U meta) {
		this.obj = obj;
		this.meta = meta;
	}

	public T getContent() {
		return obj;
	}

	public U getMeta() {
		return meta;
	}
}
