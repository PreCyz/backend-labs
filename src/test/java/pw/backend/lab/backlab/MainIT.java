package pw.backend.lab.backlab;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pw.backend.lab.backlab.dao.CompanyRepository;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackLabApplication.class)
@ActiveProfiles(profiles = {"it"})
public class MainIT {

    @Autowired
    private CompanyRepository repository;

    @Test
    public void firstTest() {
        assertThat(repository.findAll()).hasSize(3);
        assertThat(repository.findById(1L).orElseThrow(IllegalAccessError::new).getName()).isEqualTo("Aliko");
    }
}
