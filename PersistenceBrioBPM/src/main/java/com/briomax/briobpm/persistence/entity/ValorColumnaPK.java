package com.briomax.briobpm.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValorColumnaPK implements IPrimaryKey {
	@Column(name = "NOMBRE_TABLA", length = 30, nullable = false)
    private String nombreTabla;
	
	@Column(name = "NOMBRE_COLUMNA", length = 30, nullable = false)
    private String nombreColumna;
    
	@Column(name = "SECUENCIA_VALOR", precision = 5, scale = 0, nullable = false)
    private BigDecimal secuenciaValor;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValorColumnaPK that = (ValorColumnaPK) o;
        return Objects.equals(nombreTabla, that.nombreTabla) &&
               Objects.equals(nombreColumna, that.nombreColumna) &&
               Objects.equals(secuenciaValor, that.secuenciaValor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombreTabla, nombreColumna, secuenciaValor);
    }

}

