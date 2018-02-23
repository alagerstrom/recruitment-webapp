package se.kth.iv1201.boblaghei.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.kth.iv1201.boblaghei.entity.Competence;
import se.kth.iv1201.boblaghei.util.HttpPath;
import se.kth.iv1201.boblaghei.service.CreateApplicationService;

import java.util.Set;

/**
 * CompetenceResource
 *
 * Defines the mapping of the REST resource /api/competences
 *
 * To list all competences, send a GET to /api/competences
 * The response will be a JSON representation of a list of Competences
 */
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
