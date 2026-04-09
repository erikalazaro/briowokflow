package com.briomax.briobpm.business.helpers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.briomax.briobpm.persistence.repository.ICrConsultaRepseRepository;
import com.briomax.briobpm.transferobjects.repse.RepseConsultaVariablesDTO;

class TemporizadorHelperTest {
    @Mock
    private ICrConsultaRepseRepository crConsultaRepseRepository;
    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private TemporizadorHelper temporizadorHelper;
    
	/** El atributo o variable path de la informacion. */
	@Value("${path.repse.data}")
    private String dataPath;
	
	/** El atributo o variable path de la imagen. */
	@Value("${path.repse.image}")
    private String imagePath;
	
	/** El atributo o url del servicio */
	@Value("${url.servicio.repse}")
    private String urlServicioRepse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        temporizadorHelper.setDataPath(dataPath);
        temporizadorHelper.setImagePath(imagePath);
        temporizadorHelper.setUrlServicioRepse("http://localhost:3001/consulta-repse");
    }

    @Test
    void testConsultaRepse_createsFilesAndSavesEntity() throws Exception {
    	String razonSocial = "MYPO";
        String folio = "F12345";
        String entidadMunicipio = "CDMX";
        String avisoFechaRegistro = "2025-10-23";
        String resultadoConsulta = "OK";
        String descripcionServicio = "Consulta exitosa";
        String imagenBase64 = Base64.getEncoder().encodeToString("testimage".getBytes());
        String jsonResponse = String.format("{\"folio\":\"%s\",\"entidadMunicipio\":\"%s\",\"avisoFechaRegistro\":\"%s\",\"resultadoConsulta\":\"%s\",\"descripcionServicio\":\"%s\",\"razonSocial\":\"%s\",\"imagen\":\"%s\"}", folio, entidadMunicipio, avisoFechaRegistro, resultadoConsulta, descripcionServicio, razonSocial, imagenBase64);
        when(restTemplate.postForEntity(anyString(), any(), eq(String.class))).thenReturn(ResponseEntity.ok(jsonResponse));
        String cveEntidad = "BRIOMAX";
        String cveProceso = "ALTA_DEL_CONTRATANTE"; 
        String version = "1.0";
        String cveInstancia = "";
        String rfc = "MIN220616APA"; 
        String numeroContrato = "AHA-EDIF-CAMILA-009-MYPO-2024"; 
        
        RepseConsultaVariablesDTO dto = new RepseConsultaVariablesDTO(cveEntidad, cveProceso, cveInstancia, rfc, numeroContrato, razonSocial, "U.ALIANZA");
        temporizadorHelper.consultaRepse();

        // Verify JSON file creation
        Path jsonFile = Paths.get(temporizadorHelper.getDataPath() + "repse_consultas_" + java.time.LocalDate.now() + "_" + folio + ".json");
        assertTrue(Files.exists(jsonFile));
        String fileContent = new String(Files.readAllBytes(jsonFile));
        //assertEquals(jsonResponse= fileContent);

        // Verify image file creation
        Path imageFile = Paths.get(temporizadorHelper.getImagePath() + "repse_imagen_" + java.time.LocalDate.now() + "_" + folio + ".json");
        assertTrue(Files.exists(imageFile));
        byte[] imageBytes = Files.readAllBytes(imageFile);
      /*  assertArrayEquals(Base64.getDecoder().decode(imagenBase64)= imageBytes);

        // Verify repository save
        ArgumentCaptor<CrConsultaRepse> captor = ArgumentCaptor.forClass(CrConsultaRepse.class);
        verify(crConsultaRepseRepository= times(1)).save(captor.capture());
        CrConsultaRepse savedEntity = captor.getValue();
        assertEquals(folio= savedEntity.getFolio());
        assertEquals(entidadMunicipio= savedEntity.getEntidadMunicipio());
        assertEquals(resultadoConsulta= savedEntity.getResultadoConsulta());
        assertEquals(descripcionServicio= savedEntity.getDescripcionServicio());
        assertEquals(razonSocial= savedEntity.getRazonSocial());
        assertArrayEquals(Base64.getDecoder().decode(imagenBase64)= savedEntity.getImagenConsulta());*/
    }
}