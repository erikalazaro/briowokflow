package com.briomax.briobpm.transferobjects.catalogs;

import java.io.Serializable;
import java.util.List;

import com.briomax.briobpm.transferobjects.BitacoraTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class BitacoraNodo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<BitacoraTO> listaBitacoraNodo;
	String mensaje;

}
