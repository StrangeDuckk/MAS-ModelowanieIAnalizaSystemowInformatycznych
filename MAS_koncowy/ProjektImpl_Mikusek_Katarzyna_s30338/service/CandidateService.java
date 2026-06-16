package edupjamas.s30338.service;

import edupjamas.s30338.entity.Wielodziedziczenie.Candidate;
import edupjamas.s30338.repository.CandidateRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidateService {
    private final CandidateRepository candidateRepository;

    public List<Candidate> getAllCandidatesWithApplications() {
        return candidateRepository.findAllWithApplications();
    }

    @Transactional
    public Candidate saveCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }
}
