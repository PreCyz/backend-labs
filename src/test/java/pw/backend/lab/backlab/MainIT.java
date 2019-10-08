package pw.backend.lab.backlab;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pw.backend.lab.backlab.controller.CompanyController;
import pw.backend.lab.backlab.dao.CompanyRepository;
import pw.backend.lab.backlab.model.Company;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackLabApplication.class)
@ActiveProfiles(profiles = {"it"})
public class MainIT {

    private MockMvc mockMvc;

    @Autowired
    private CompanyRepository repository;
    @Autowired
    private CompanyController companyController;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.companyController).build();
    }

    @Test
    public void whenNoSecurityHeader_thenStatusCode403() throws Exception {
        MockHttpServletRequestBuilder getCompany = get("/companies/1");
        mockMvc.perform(getCompany).andExpect(status().isUnauthorized());
    }

    @Test
    public void whenSystemStartsUpAndLoadData_thenCanAccessTheseData() {
        assertThat(repository.findAll()).hasSize(3);
        assertThat(repository.findById(1L).orElseThrow(IllegalAccessError::new).getName()).isEqualTo("Aliko");
    }

    @Test
    public void whenPostNewCompany_thenThatCompanyIsInsertedIntoDatabase() throws Exception {
        String content = "[{  \n" +
                "   \"name\" : \"devComp\",\n" +
                "   \"boardMembers\" : 2,\n" +
                "   \"startDateTime\" : \"2019-10-05T21:34:21\"\n" +
                "}]";

        //POST
        MockHttpServletRequestBuilder postCompany = post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .header("security-header", "secureMe")
                .content(content);
        mockMvc.perform(postCompany)
                .andExpect(status().isOk())
                .andExpect(content().string("4"));

        Optional<Company> actualCompany = repository.findById(4L);
        assertThat(actualCompany.isPresent()).isTrue();
        assertThat(actualCompany.get().getName()).isEqualTo("devComp");

        content = "{  \n" +
                "   \"name\" : \"devCompChanged\",\n" +
                "   \"id\" : 4,\n" +
                "   \"startDateTime\" : \"2019-10-05T21:34:21\"\n" +
                "}";
        //PUT
        MockHttpServletRequestBuilder putCompany = put("/companies/4").contentType(MediaType.APPLICATION_JSON)
                .header("security-header", "secureMe")
                .content(content);
        mockMvc.perform(putCompany)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("devCompChanged")));


        actualCompany = repository.findById(4L);
        assertThat(actualCompany.isPresent()).isTrue();
        assertThat(actualCompany.get().getName()).isEqualTo("devCompChanged");

        //GET
        MockHttpServletRequestBuilder getCompany = get("/companies/4").header("security-header", "secureMe");
        mockMvc.perform(getCompany)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("devCompChanged")));

        //DELETE
        MockHttpServletRequestBuilder deleteCompany = delete("/companies/4").header("security-header", "secureMe");
        mockMvc.perform(deleteCompany)
                .andExpect(status().isOk())
                .andExpect(content().string("Company with id 4 deleted."));

        actualCompany = repository.findById(4L);
        assertThat(actualCompany.isPresent()).isFalse();
    }
}
