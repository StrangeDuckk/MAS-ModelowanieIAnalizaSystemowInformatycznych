package edupjamas.s30338.entity.Wielodziedziczenie;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class Employee extends Person{

    @NotNull
    @PositiveOrZero
    private Double salary;

    @NotBlank
    private String currentOccupationPosition;

    // ===================== METODY =======================
    @Override
    public String getCurrentOccupation() {
        return Position();
    }
    public String Position() {
        return currentOccupationPosition;
    }
}
