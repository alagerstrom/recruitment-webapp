package se.kth.iv1201.boblaghei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kth.iv1201.boblaghei.dto.ApplicationDTO;
import se.kth.iv1201.boblaghei.entity.Application;
import se.kth.iv1201.boblaghei.repository.ApplicationRepository;
import se.kth.iv1201.boblaghei.util.ApplicationSearchDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListApplicationService {

    @Autowired
    ApplicationRepository applicationRepository;

    //TODO Decide if specification should be done here by calling several repositories and creating the parameters here
    //or in the applicationRepository, and create a specific query that can handle the param applicationSearchDTO directly.
    public List<ApplicationDTO> findApplications(ApplicationSearchDTO applicationSearchDTO) {
        List<ApplicationDTO> applications = new ArrayList<>();
        for(Application application : applicationRepository.findAll()) {
            applications.add(application.getDTO());
        }
        return applications;
    }
}
