package com.briomax.briobpm.business.helpers.base;

import java.util.Date;

import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

public interface GraficosHelper {

	Date periodoActual();
	
	Date periodoAnterior();
	
	int totalContratistas(DatosAutenticacionTO sesion, Date actual, Date anterior, String categoria);
	
	int actividadesContratistas(DatosAutenticacionTO sesion, Date actual, Date anterior, String categoria);
	
	
}
