package edupjamas.s30338.entity.Wielodziedziczenie;

import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Candidate extends Person
implements ICandidate{

    @PositiveOrZero
    private int yearOfStudying;

    @NotBlank
    private String currentOccupationStudyDirection;


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
