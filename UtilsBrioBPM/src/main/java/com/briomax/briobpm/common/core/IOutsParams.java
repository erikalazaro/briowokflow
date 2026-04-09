
package com.briomax.briobpm.common.core;

import java.util.HashMap;

public interface IOutsParams {
	public void setOutParams(HashMap<String, Object> params);

	public void setStatus(String status);

	public void setMessage(String msg);

	public static final String OK = "OK";

	public static final String ERROR = "Error";

	public static final String AVISO = "Aviso";

	public static final String NODATA = "NoData";
}
