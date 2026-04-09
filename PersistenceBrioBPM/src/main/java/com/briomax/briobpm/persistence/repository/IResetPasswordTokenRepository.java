package com.briomax.briobpm.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.persistence.entity.ResetPasswordToken;
import com.briomax.briobpm.persistence.entity.ResetPasswordTokenPK;

@Repository
public interface IResetPasswordTokenRepository extends JpaRepository<ResetPasswordToken, ResetPasswordTokenPK>{

	
	@Query(value = "SELECT * " +
    		"FROM RESET_PASSWORD_TOKEN RPT " +
            "WHERE RPT.CVE_USUARIO = :cveUsuario" +
    		"   AND RPT.TOKEN_HASH = :token " +
    		"   AND RPT.USUARIO_INACTIVO = 1 " 
    		, nativeQuery = true)
	ResetPasswordToken validaToken(
			 @Param("cveUsuario") String cveUsuario,
			 @Param("token") String token);
	
	@Query(value = "SELECT * " +
    		"FROM RESET_PASSWORD_TOKEN RPT " +
            "WHERE RPT.CVE_USUARIO = :cveUsuario" +
    		"   AND RPT.USUARIO_INACTIVO = 1 " +
    		"   ORDER BY FECHA_EXPIRACION DESC LIMIT 1", nativeQuery = true)
	ResetPasswordToken validaUsuario(
			 @Param("cveUsuario") String cveUsuario);
	
	@Modifying
    @Transactional
    @Query(value = "DELETE FROM RESET_PASSWORD_TOKEN " + 
    		"            WHERE TOKEN_HASH NOT IN ( " + 
    		"                SELECT TOKEN_HASH FROM RESET_PASSWORD_TOKEN " + 
    		"                WHERE USUARIO_INACTIVO = 0 " + 
    		"            ) ", nativeQuery = true)
    void depuraToken();
	

	
}
