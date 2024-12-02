package fr.diginamic.hello.service;
import fr.diginamic.hello.dto.VilleDto;
import fr.diginamic.hello.entity.Departement;
import fr.diginamic.hello.entity.Ville;
import fr.diginamic.hello.exception.Controle;
import fr.diginamic.hello.mapper.VilleMapper;
import fr.diginamic.hello.repository.DepartementRepository;
import fr.diginamic.hello.repository.VilleRepository;

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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class VilleServiceTest {

    @Autowired
    VilleService villeService;

    @MockitoBean
    VilleRepository villeRepository;

    @Test
    void getAllVilles() {

        when(villeRepository.findAll()).thenReturn(List.of(new Ville("Paris", 3_190_327, new Departement("Paris", "75"))));

        Iterable<Ville> villes = villeService.getAllVilles();
        assertTrue(villes.iterator().hasNext());
    }

    @Test
    void getVille() throws Controle {

        when(villeRepository.findAll()).thenReturn(List.of(new Ville(13321, "Paris", 3_190_327, new Departement(37, "Paris", "75"))));

        Ville villeTest = villeService.getVille(13321);
        assertTrue(villeTest.getNom().equals("Paris") && villeTest.getDepartement().getId() == 37);
    }

    @Test
    void getVilleByNom() throws Controle {

        when(villeRepository.findAll()).thenReturn(List.of(new Ville(13321, "Paris", 3_190_327, new Departement(37, "Paris", "75"))));

        Ville villeTest = villeService.getVilleByNom("Paris");
        assertTrue(villeTest.getDepartement().getId() == 37 && villeTest.getId() == 13321);
    }

    @Test
    void addVille() {

        when(villeRepository.findAll()).thenReturn(List.of(new Ville(13321, "Paris", 3_190_327, new Departement(37, "Paris", "75"))));

        Ville villeTest = new Ville("Paris", 3_190_327, new Departement("Paris", "75"));

        assertThrows(Controle.class, () -> villeService.addVille(villeTest));
    }
}
