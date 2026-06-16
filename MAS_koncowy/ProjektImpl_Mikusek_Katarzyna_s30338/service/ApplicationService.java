package edupjamas.s30338.service;

import edupjamas.s30338.entity.Wielodziedziczenie.Candidate;
import edupjamas.s30338.entity.kwalifikowana.Application;
import edupjamas.s30338.repository.ApplicationRepository;
import edupjamas.s30338.repository.CandidateRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final CandidateRepository candidateRepository;
    @Transactional
    public Application saveApplication(Application application) {
        Candidate candidate = candidateRepository.findById(application.getPerson().getPersonId())
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        application.setPerson(candidate);

        return applicationRepository.save(application);
    }
}
