package se.kth.iv1201.boblaghei.rest.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.kth.iv1201.boblaghei.entity.Application;
import se.kth.iv1201.boblaghei.exception.NoUserLoggedInException;
import se.kth.iv1201.boblaghei.rest.util.HttpPath;
import se.kth.iv1201.boblaghei.service.CreateApplicationService;
import se.kth.iv1201.boblaghei.service.ListApplicationService;
import se.kth.iv1201.boblaghei.util.ApplicationSearchDTO;

import java.util.List;

@RestController
@RequestMapping(HttpPath.APPLICATIONS_PATH)
public class ApplicationResource {

    @Autowired
    ListApplicationService listApplicationService;

    @Autowired
    CreateApplicationService createApplicationService;

    @GetMapping
    public List<Application> getAllApplications(@RequestBody ApplicationSearchDTO applicationSearchDTO) {
        return listApplicationService.findApplications(applicationSearchDTO);
    }

    @PostMapping
    public Application createApplication(@RequestBody Application application) throws NoUserLoggedInException {
        return createApplicationService.createApplicationForCurrentUser(application);
    }

}
