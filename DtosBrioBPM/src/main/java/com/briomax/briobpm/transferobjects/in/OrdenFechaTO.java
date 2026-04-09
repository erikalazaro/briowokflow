package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Schema(description = "OrdenFechaTO", title = "OrdenFechaTO")
@Data
@Builder
public class OrdenFechaTO  implements Serializable {

	/** serial Version UID. */
	private static final long serialVersionUID = 1L;

	/** The type. */
	
	private Date fecha;

}
