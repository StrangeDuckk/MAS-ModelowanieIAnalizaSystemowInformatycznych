package edupjamas.s30338.entity.kwalifikowana;

import edupjamas.s30338.entity.Wielodziedziczenie.Person;
import edupjamas.s30338.entity.kompozycja.JobOffer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;

    @NotNull
    private LocalDate data;

    @NotNull
    private double candidatesSalaryProposition;

    // ===================== RELACJE =======================

    //cv * - 1 application
    @OneToMany(mappedBy = "application")//wskazanie na nazwe zmiennej w cv
    @MapKey(name = "cvNumber")//id z cv
    private Map<String, CV> cvs = new HashMap<>();//{surname_name_number}

    //(jobOffer 1 ---- 1..*) Application 1..* ---- 1 Person
    @ManyToOne(optional = false)
    @JoinColumn(name = "personId")
    @ToString.Exclude
    private Person person;

    //jobOffer 1 ---- 1..* Application( 1..* ---- 1 Person)
    @ManyToOne(optional = false)
    @JoinColumn(name = "jobOfferId")
    @ToString.Exclude
    private JobOffer jobOffer;
}
