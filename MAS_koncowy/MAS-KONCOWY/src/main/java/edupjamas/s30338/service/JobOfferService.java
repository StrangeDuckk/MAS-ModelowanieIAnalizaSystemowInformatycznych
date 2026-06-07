package edupjamas.s30338.service;

import edupjamas.s30338.entity.kompozycja.JobOffer;
import edupjamas.s30338.repository.JobOfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobOfferService {
    private final JobOfferRepository jobOfferRepository;
    public List<JobOffer> getAllJobOffers() {
        return jobOfferRepository.findAll();
    }
}
