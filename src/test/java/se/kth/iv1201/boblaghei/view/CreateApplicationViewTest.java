package se.kth.iv1201.boblaghei.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreateApplicationViewTest {

    @Autowired
    WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.
                webAppContextSetup(wac).build();
    }

    @Test
    public void testGetApply() throws Exception {
        this.mockMvc.perform(get("/apply"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("availabilities"))
                .andExpect(model().attributeExists("availableCompetences"))
                .andExpect(model().attributeExists("selectedCompetences"));
    }

//    @Test
//    public void testPostAddAvailability() throws Exception {
//        this.mockMvc.perform(post("/apply/add-availability"))
//                .andExpect(status().isOk())
//                .andExpect(model().attributeExists("availabilities"))
//                .andExpect(forwardedUrl("/apply"));
//    }

    @Test
    public void testPostSubmitApplication() throws Exception {
        this.mockMvc.perform(post("/apply/submit-application"))
//                .andExpect(status().isOk())
                .andExpect(redirectedUrl("/"));
    }
}
