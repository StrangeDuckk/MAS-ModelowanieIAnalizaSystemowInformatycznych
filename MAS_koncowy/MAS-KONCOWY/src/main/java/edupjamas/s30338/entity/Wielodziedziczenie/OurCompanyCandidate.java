package edupjamas.s30338.entity.Wielodziedziczenie;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class OurCompanyCandidate extends Employee
implements ICandidate{
    @NotBlank
    private String coverLetter;

    @OneToOne(optional = false,cascade = CascadeType.ALL) //przy usunieciu kandydata naszego chcemy od razu usunac tez kandydata ogolem
    @JoinColumn(name = "personId")//todo sprawdzic czy zadziala
    private Candidate candidate;
    @Override
    public Candidate getCandidate() {
        return this.candidate;
    }
}
