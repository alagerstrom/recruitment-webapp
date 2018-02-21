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
import se.kth.iv1201.boblaghei.dto.ApplicationSearchDTO;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ListApplicationsViewTest  {

    @Autowired
    WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.
                webAppContextSetup(wac).build();
    }

    @Test
    public void testGetApplicationView() throws Exception {
        this.mockMvc.perform(get("/recruiter/applications"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("availableCompetences"))
                .andExpect(model().attributeExists("applicationSearchDto"))
                .andExpect(view().name("listApplications"));
    }

    @Test
    public void testSearch() throws Exception {
        ApplicationSearchDTO searchDTO = new ApplicationSearchDTO();
        this.mockMvc.perform(post("/recruiter/applications")
                .flashAttr("dto", searchDTO))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("listOfApplications"))
                .andExpect(view().name("listApplications"));
    }

    @Test
    public void testViewSingleApplication() throws Exception {
        long id = 1;
        this.mockMvc.perform(get("/recruiter/applications/{id}", id))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("singleApplication"))
                .andExpect(view().name("singleApplication"));
    }

    @Test
    public void testGetApplicationAsPDF() {

    }
}
