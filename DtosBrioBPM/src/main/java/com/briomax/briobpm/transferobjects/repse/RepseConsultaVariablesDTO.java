package com.briomax.briobpm.transferobjects.repse;

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
public class RepseConsultaVariablesDTO {
    private String cveEntidad;
    private String cveProceso;
    private String cveInstancia;
    private String rfc;
    private String numeroContrato;
    private String razonSocial;
    private String cveUsuario;
}
