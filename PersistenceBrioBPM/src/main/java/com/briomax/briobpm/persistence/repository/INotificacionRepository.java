package com.briomax.briobpm.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.Notificacion;

@Repository
public interface INotificacionRepository extends JpaRepository<Notificacion, Integer>{

	@Query(value = "SELECT MAX(SECUENCIA_CORREO)  "+ 
            "FROM NOTIFICACION ", nativeQuery = true)
	Integer obtieneNumeroCorreo();
}
