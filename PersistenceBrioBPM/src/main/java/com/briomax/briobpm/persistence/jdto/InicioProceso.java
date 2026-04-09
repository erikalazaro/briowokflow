/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.persistence.jdto;

import java.io.Serializable;
import java.util.HashMap;

import com.briomax.briobpm.persistence.jdto.base.IInicioProceso;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class InicioProceso.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Mar 9, 2020 3:27:22 PM Modificaciones:
 * @since JDK 1.8
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InicioProceso implements IInicioProceso, Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = 3969856953691221211L;

	/** El atributo o variable status. */
	private String status;

	/** El atributo o variable message. */
	private String message;

	/** El atributo o variable cve instancia. */
	private String cveInstancia;

	/** El atributo o variable cve nodo actividad. */
	private String cveNodoActividad;

	/** El atributo o variable id nodo actividad. */
	private Integer idNodoActividad;

	/** El atributo o variable sec nodo actividad. */
	private Integer secNodoActividad;

	/**
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.common.core.IOutsParams#setOutParams(java.util.HashMap)
	 */
	@Override
	public void setOutParams(HashMap<String, Object> params) {
		this.status = (String) params.get("CH_TIPO_EXCEPCION");
		this.message = (String) params.get("CH_MENSAJE");
		this.cveInstancia = (String) params.get("CH_CVE_INSTANCIA");
		this.cveNodoActividad = (String) params.get("CH_CVE_NODO_ACTIVIDAD");
		this.idNodoActividad = (Integer) params.get("I_ID_NODO_ACTIVIDAD");
		this.secNodoActividad = (Integer) params.get("I_SECUENCIA_NODO_ACTIVIDAD");
	}

}
