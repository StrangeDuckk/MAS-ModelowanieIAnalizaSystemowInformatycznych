package edupjamas.s30338.repository;

import edupjamas.s30338.entity.Wielodziedziczenie.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
