package com.briomax.briobpm.business.helpers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.business.helpers.base.ITraductorHelper;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.Entidad;
import com.briomax.briobpm.persistence.entity.Traduccion;
import com.briomax.briobpm.persistence.entity.TraduccionPK;
import com.briomax.briobpm.persistence.repository.IEntidadRepository;
import com.briomax.briobpm.persistence.repository.ITraduccionRepository;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class TraductorHelper implements ITraductorHelper {


	/** El atributo o variable Entidad service. */
	@Autowired
	private IEntidadRepository entidadRepo;
	
	/** El atributo o variable Traducción service. */
	@Autowired
	private ITraduccionRepository traduccionRepo;

	// 
	@Override
	public String getTraducce(DatosAutenticacionTO session, String texto)
			throws BrioBPMException {
		String result = "";

		Optional<Entidad> to = entidadRepo.findById(session.getCveEntidad());

		
		if (to.isPresent()) {
			Entidad entidad = to.get();
			
			if (entidad.getIdioma().getCveIdioma().toUpperCase().equals(session.getCveIdioma().toUpperCase())) {
				result = texto;
			} else {

				TraduccionPK idTra = TraduccionPK.builder().cveIdioma(session.getCveIdioma())
						.palabraOriginal(texto).build();
				Optional<Traduccion> traduTo = traduccionRepo.findById(idTra);
				if (traduTo.isPresent()) {
					result = traduTo.get().getPalabraTraducida();
				} else {
					result = "* " + texto;
				}
			}			
		}
		return result;
	}
	
	@Override
	public String getTraducce(String cveEntidad, String cveIdioma, String texto)
			throws BrioBPMException {
		String result = "";

		Optional<Entidad> to = entidadRepo.findById(cveEntidad);

		
		if (to.isPresent()) {
			Entidad entidad = to.get();
			
			if (entidad.getIdioma().getCveIdioma().equals(cveIdioma)) {
				result = texto;
			} else {

				TraduccionPK idTra = TraduccionPK.builder().cveIdioma(cveIdioma)
						.palabraOriginal(texto).build();
				Optional<Traduccion> traduTo = traduccionRepo.findById(idTra);
				if (traduTo.isPresent()) {
					result = traduTo.get().getPalabraTraducida();
				} else {
					result = "* " + texto;
				}
			}			
		}
		return result;
	}
}
