package edu.hm.crm.controller;

import edu.hm.crm.service.OpportunityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for opportunity controller.
 *
 * @author Simon Hirner
 */
@WebMvcTest(OpportunityController.class)
public class OpportunityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OpportunityService opportunityService;

    @Test
    public void opportunitiesShouldReturnOK() throws Exception {
        mockMvc.perform(get("/opportunities")).andExpect(status().isOk());
    }

    @Test
    public void opportunitiesShouldReturnf() throws Exception {
        mockMvc.perform(get("/opportunities/1")).andExpect(status().isNotFound());
    }

}
