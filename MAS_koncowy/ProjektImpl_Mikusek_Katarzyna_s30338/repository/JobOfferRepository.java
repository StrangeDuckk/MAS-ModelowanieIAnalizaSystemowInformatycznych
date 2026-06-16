package edupjamas.s30338.repository;

import edupjamas.s30338.entity.kompozycja.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobOfferRepository extends JpaRepository<JobOffer, Long> {
    @Query("SELECT j FROM JobOffer j LEFT JOIN FETCH j.applications")
    List<JobOffer> findAllWithApplications();
}
