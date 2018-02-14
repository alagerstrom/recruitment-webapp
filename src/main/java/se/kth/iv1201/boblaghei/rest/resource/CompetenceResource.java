package se.kth.iv1201.boblaghei.rest.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.kth.iv1201.boblaghei.entity.Competence;
import se.kth.iv1201.boblaghei.rest.util.HttpPath;
import se.kth.iv1201.boblaghei.service.CreateApplicationService;

import java.util.Set;

@RestController
@RequestMapping(HttpPath.COMPETENCES_PATH)
public class CompetenceResource {

    @Autowired
    CreateApplicationService createApplicationService;

    @GetMapping
    public Set<Competence> getAllCompetences(){
        return createApplicationService.listAllCompetences();
    }
}
