package com.betrybe.museumfinder.solution;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


/**
 * The type Museum controller test.
 */
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Museum Controller Test")
public class MuseumControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  MuseumService museumService;

  @Test
  @DisplayName("Get Museum by ID")
  void testGetById() throws Exception {
    Museum museumMock = new Museum();
    museumMock.setId(1L);
    museumMock.setName("Museu Casa Memória dos Ex-Combatentes da Segunda Guerra Mundial");
    Mockito.when(museumService.getMuseumById(1L)).thenReturn(museumMock);

    ResultActions result = mockMvc.perform(get("/museums/1"));

    result.andExpect(status().isOk())
        // Verificação do conteúdo da resposta
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.name").value("Museu Casa Memória dos Ex-Combatentes da Segunda Guerra Mundial"));
  }
}
