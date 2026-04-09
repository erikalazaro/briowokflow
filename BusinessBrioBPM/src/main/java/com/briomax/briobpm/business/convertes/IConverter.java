/*
 * Copyright (c) 2017 Briomax Consulting S.A. de C.V.; Todos los derechos reservados. Este software contiene informacion
 * confidencial propiedad de Briomax Consulting por lo cual no puede ser reproducido, distribuido o alterado sin el
 * consentimiento previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.convertes;

import com.briomax.briobpm.common.exception.ConverterExcepcion;
import com.briomax.briobpm.persistence.entity.IEntity;
import com.briomax.briobpm.transferobjects.core.ITransferObject;

/**
 * Definicion de metodos para la conversion de Transfer Object a Entities y vice versa.
 * @author Rigoberto Olvera C.
 * @version 1.0 Fecha de creacion 26/09/2017 01:13:01 PM.
 * @param <E> entity de conversión
 * @param <T> Transfer Object de conversión
 * @since JDK 1.8
 */
public interface IConverter<E extends IEntity, T extends ITransferObject> {

	/**
	 * Convierte un Transfer Object a un entity.
	 * @param to Transfer Object desde el cual se desea crear un Entity
	 * @return Entity obtenido a través del Transfer Object
	 * @throws ConverterExcepcion cuando la conversión no se puede efectuar de una manera correcta
	 */
	E convert(T to) throws ConverterExcepcion;

	/**
	 * Convierte un Entity a un Transfer Object.
	 * @param entity desde el cual se desea crear un Transfer Object
	 * @return TransferObject obtenido a través del Entity
	 * @throws ConverterExcepcion cuando la conversión no se puede efectuar de una manera correcta.
	 */
	T convert(E entity) throws ConverterExcepcion;

}
