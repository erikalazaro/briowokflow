/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.business.convertes;

import java.util.function.Function;

import com.briomax.briobpm.persistence.entity.namedquery.LeeBitacoraNodo;
import com.briomax.briobpm.persistence.entity.namedquery.LeeMenuEstatico;
import com.briomax.briobpm.persistence.entity.namedquery.MenuAreaTrabajo;
import com.briomax.briobpm.transferobjects.BitacoraTO;
import com.briomax.briobpm.transferobjects.MenuAreaTrabajoTO;

public interface IAreaTrabajoConverter {

	Function<LeeMenuEstatico, MenuAreaTrabajoTO> converterFixedMenu = (entity) -> {
		MenuAreaTrabajoTO to = null;
		if (entity != null) {
			to = MenuAreaTrabajoTO.builder()
					.tipo("ESTATICO")
					.ordenamiento(entity.getId().getOrdenamiento())
					.cveProceso(entity.getId().getCveMenu())
//					.version(null)
					.desProceso(entity.getDescripcionMenu())
					.cveNodo(entity.getId().getCveOpcion())
//					.idNodo(null)
					.desNodo(entity.getDescripcionOpcion())
//					.iniProceso(null)
//					.etiBoton(null)
//					.cveRol(null)
//					.cveAreaTrabajo(null)
					.idOpcion(entity.getId().getIdOpcion())
					.parametros(entity.getParametros())
//					.ejeAutomatica(null)
//					.etiBotEjeAutomatica(null)
					.build();
		}
		return to;
	};

	Function<MenuAreaTrabajo, MenuAreaTrabajoTO> converterDynamicMenu = (entity) -> {
		MenuAreaTrabajoTO to = null;
		if (entity != null) {
			to = MenuAreaTrabajoTO.builder()
					.tipo("DINAMICO")
					.ordenamiento(entity.getOrdenamiento())
					.cveProceso(entity.getCveProceso())
					.version(String.valueOf(entity.getVersion()))
					.desProceso(entity.getDesProceso())
					.cveNodo(entity.getCveNodo())
					.idNodo(entity.getIdNodo())
					.desNodo(entity.getDesNodo())
					.iniProceso(entity.getIniProceso())
					.etiBoton(entity.getEtiquetaBoton())
					.cveRol(entity.getCveRol())
					.cveAreaTrabajo(entity.getCveAreaTrabajo())
//					.idOpcion(null)
//					.parametros(null)
					.ejeAutomatica(entity.getEjecucionAutomatica())
					.etiBotEjeAutomatica(entity.getEtiquetaBotonEjecucionAutomatica())
					.build();
		}
		return to;
	};

	/** El atributo o variable converter bitacora. */
	Function<LeeBitacoraNodo, BitacoraTO> converterBitacora = (entity) -> {
		BitacoraTO to = null;
		if (entity != null) {
			to = BitacoraTO.builder()
					.accion(entity.getAccion())
					.fecha(entity.getFechaAccion().toString())
					.usuario(entity.getUsuarioAccion())
					.build();
		}
		return to;
	};

}
