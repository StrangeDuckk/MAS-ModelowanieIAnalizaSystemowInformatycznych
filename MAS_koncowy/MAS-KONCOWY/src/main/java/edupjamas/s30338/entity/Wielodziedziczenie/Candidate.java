package edupjamas.s30338.entity.Wielodziedziczenie;

import edupjamas.s30338.entity.kwalifikowana.Application;
import edupjamas.s30338.entity.zAtrybutem.Adress;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Entity
@SuperBuilder
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Candidate extends Person
implements ICandidate{

    @PositiveOrZero
    private int yearOfStudying;

    @NotBlank
    private String currentOccupationStudyDirection;

    @OneToOne(mappedBy = "candidate", cascade = CascadeType.ALL)
    private OurCompanyCandidate ourCompanyCandidate;
//ostatnio bez tego wgl ale nie dzialalo

    public Candidate(
            List<String> names,
            String surname,
            String mail,
            String phoneNumber,
            LocalDate dateOfBirth,
            List<Adress> adress,
            List<Application> applications,
            int yearOfStudying,
            String currentOccupationStudyDirection) {
        super(names,surname,mail,phoneNumber,dateOfBirth, adress, applications);
        this.setYearOfStudying(yearOfStudying);
        this.setCurrentOccupationStudyDirection(currentOccupationStudyDirection);
    }


    public String getStudyDirection() {
        return currentOccupationStudyDirection;
    }
    @Override
    public String getCurrentOccupation() {
        return getStudyDirection();
    }
    @Transient//bo wyrzuca blad ze to getter
    @Override
    public Candidate getCandidate() {
        return this;
    }
}
