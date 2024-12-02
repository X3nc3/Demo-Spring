package fr.diginamic.hello.restControleurs;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.diginamic.hello.dto.VilleDto;
import fr.diginamic.hello.entity.Departement;
import fr.diginamic.hello.entity.Ville;
import fr.diginamic.hello.repository.DepartementRepository;
import fr.diginamic.hello.service.VilleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class VilleControleursTest {

    @MockitoBean
    VilleService villeService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    DepartementRepository departementRepository;

    @Test
    void addVilleOk() throws Exception {

        Departement departement = new Departement("Paris", "75");
        Ville nvVille = new Ville("Paris", 3_190_327, departement);

        when(departementRepository.findByCode(anyString())).thenReturn(departement);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/villes")
                .content(objectMapper.writeValueAsString(new VilleDto(nvVille)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .andExpect(status().isOk()));
    }

    @Test
    void addVilleKo() throws Exception {

        Departement departement = new Departement("Paris", "75");
        Ville nvVille = new Ville("Paris", 3_190_327, departement);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/villes")
            .content(objectMapper.writeValueAsString(new VilleDto(nvVille)))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }
}
