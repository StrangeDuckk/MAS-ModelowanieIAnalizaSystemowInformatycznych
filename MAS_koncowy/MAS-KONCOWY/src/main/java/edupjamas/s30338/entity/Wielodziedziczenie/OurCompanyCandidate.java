package edupjamas.s30338.entity.Wielodziedziczenie;

import edupjamas.s30338.entity.kwalifikowana.Application;
import edupjamas.s30338.entity.zAtrybutem.Adress;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Entity
@SuperBuilder
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OurCompanyCandidate extends Employee
implements ICandidate{
    @NotBlank
    private String coverLetter;

//    @OneToOne(optional = false,
//            cascade = CascadeType.ALL) //przy usunieciu kandydata naszego chcemy od razu usunac tez kandydata ogolem
//    @JoinColumn(name = "personId")//todo sprawdzic czy zadziala
//    private Candidate candidate;

    @OneToOne
    @JoinColumn(name = "candidate_person_id")
    private Candidate candidate;

    public OurCompanyCandidate(
            Candidate candidate,
            double salary,
            String currentOccupationPosition,
            String coverLetter
            )
    {
        super(
                candidate.getName(),
                candidate.getSurname(),
                candidate.getEmail(),
                candidate.getPhoneNumber(),
                candidate.getDateOfBirth(),
                candidate.getAdress(),
                candidate.getApplications(),
                salary,
                currentOccupationPosition
        );

        setCandidate(candidate);
        setCoverLetter(coverLetter);
    }

    @Override
    public Candidate getCandidate() {
        return this.candidate;
    }

    private void setCoverLetter(String coverLetter) {
        if (coverLetter == null || coverLetter.isBlank()) {
            throw new IllegalArgumentException(
                    "Cover letter cannot be null or blank"
            );
        }
        this.coverLetter = coverLetter;
    }

    private void setCandidate(Candidate candidate) {
        if (candidate == null) {
            throw new IllegalArgumentException(
                    "Candidate cannot be null"
            );
        }
        this.candidate = candidate;
    }
}
