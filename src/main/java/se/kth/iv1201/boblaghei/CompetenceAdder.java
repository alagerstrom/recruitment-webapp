package se.kth.iv1201.boblaghei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import se.kth.iv1201.boblaghei.entity.Competence;
import se.kth.iv1201.boblaghei.repository.CompetenceRepository;
import se.kth.iv1201.boblaghei.util.Constants;

@Component
public class CompetenceAdder implements ApplicationRunner {

    @Autowired
    private CompetenceRepository competenceRepository;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        competenceRepository.save(new Competence("Ballongförsäljare"));
        competenceRepository.save(new Competence("Maskinoperatör"));
        competenceRepository.save(new Competence("Biljettförsälare"));
        competenceRepository.save(new Competence("Reparatör"));
    }
}
