package edupjamas.s30338.entity.Wielodziedziczenie;

import edupjamas.s30338.entity.kwalifikowana.Application;
import edupjamas.s30338.entity.zAtrybutem.Adress;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Entity
@SuperBuilder
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Employee extends Person{

    @NotNull
    @PositiveOrZero
    private Double salary;

    @NotBlank
    private String currentOccupationPosition;

    public Employee(List<String> names,
                    String surname,
                    String mail,
                    String phoneNumber,
                    LocalDate dateOfBirth,
                    List<Adress> adress,
                    List<Application> applications,
                    double salary,
                    String currentOccupationPosition)
    {
        super(names,surname,mail,phoneNumber,dateOfBirth, adress, applications);
        setSalary(salary);
        setCurrentOccupationPosition(currentOccupationPosition);
    }

    // ===================== METODY =======================
    @Override
    public String getCurrentOccupation() {
        return Position();
    }
    public String Position() {
        return currentOccupationPosition;
    }


    // ===================== Settery =======================

    private void setSalary(Double salary) {
        if (salary == null) {
            throw new IllegalArgumentException("Salary cannot be null");
        }
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be < 0");
        }
        this.salary = salary;
    }

    private void setCurrentOccupationPosition(String position) {
        if (position == null || position.isBlank()) {
            throw new IllegalArgumentException(
                    "Current occupation position cannot be null or blank"
            );
        }
        this.currentOccupationPosition = position;
    }
}
