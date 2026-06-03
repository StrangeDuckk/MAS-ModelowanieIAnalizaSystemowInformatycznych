package edupjamas.s30338.entity.kwalifikowana;

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
    private int applicationId;

    @NotNull
    private LocalDate data;

    @NotNull
    private double candidatesSalaryProposition;

    //cv * - 1 application
    @OneToMany(mappedBy = "application")//wskazanie na nazwe zmiennej w cv
    @MapKey(name = "cvNumber")//id z cv
    private Map<String, CV> cvs = new HashMap<>();//{surname_name_number}
}
