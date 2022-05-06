package edu.hm.crm.controller;

import edu.hm.crm.service.InteractionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for interaction controller.
 *
 * @author Simon Hirner
 */
@WebMvcTest(InteractionController.class)
public class InteractionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InteractionService interactionService;

    @Test
    public void interactionsShouldReturnOK() throws Exception {
        mockMvc.perform(get("/interactions")).andExpect(status().isOk());
    }

    @Test
    public void interactionsShouldReturnf() throws Exception {
        mockMvc.perform(get("/interactions/1")).andExpect(status().isNotFound());
    }

}
