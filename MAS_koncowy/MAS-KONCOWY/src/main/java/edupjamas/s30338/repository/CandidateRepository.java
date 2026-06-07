package edupjamas.s30338.repository;

import edupjamas.s30338.entity.Wielodziedziczenie.Candidate;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    @Query("SELECT DISTINCT c FROM Candidate c " +
            "LEFT JOIN FETCH c.applications a " +
            "LEFT JOIN FETCH a.jobOffer ")
    List<Candidate> findAllWithApplications();
}
