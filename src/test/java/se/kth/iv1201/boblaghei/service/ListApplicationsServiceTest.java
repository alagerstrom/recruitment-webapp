package se.kth.iv1201.boblaghei.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import se.kth.iv1201.boblaghei.dto.ApplicationSearchDTO;
import se.kth.iv1201.boblaghei.entity.Application;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ListApplicationsServiceTest {

    @Autowired
    ListApplicationService listApplicationService;


    @Test
    public void testFindAllApplications() {
        ApplicationSearchDTO applicationSearchDTO = new ApplicationSearchDTO();
        List<Application> applications = listApplicationService.findApplications(applicationSearchDTO);
        assertThat(applications.size()).isEqualTo(2);
    }

    @Test
    public void testFindApplicationById() {
       Application application = listApplicationService.findApplicationById(1);
       assertThat(application.getPerson().getFirstName()).isEqualToIgnoringCase("Kalle");
        assertThat(application.getPerson().getLastName()).isEqualToIgnoringCase("Kula");
    }
}
