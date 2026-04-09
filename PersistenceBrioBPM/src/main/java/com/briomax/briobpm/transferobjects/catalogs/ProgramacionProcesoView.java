package com.briomax.briobpm.transferobjects.catalogs;

public interface ProgramacionProcesoView {
	   String getCveEntidad();
	    String getCveLocalidad();
	    String getCveIdioma();
	    String getCveProcesoPeriodico();
	    String getCvePeriodicidad();
	    String getRfc();
	    String getContrato();
	    java.util.Date getFechaProgramacion();
	    String getEjecutor();
	    Integer getSecuenciaProgramacion();
	    java.util.Date getFechaEjecucion();
	    java.util.Date getFechaInicialNotificacion();
	    java.util.Date getFechaUltimaNotificacion();
	    String getSituacionUltimaNotificacion();
	    String getSituacionEjecucion();
}
