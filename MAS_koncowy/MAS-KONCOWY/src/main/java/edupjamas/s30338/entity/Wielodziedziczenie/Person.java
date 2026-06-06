package edupjamas.s30338.entity.Wielodziedziczenie;

import edupjamas.s30338.entity.zAtrybutem.Adress;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity
@SuperBuilder
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "personType")//dla upewnienia sie jakim typem jest obiekt
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personId;

    @ElementCollection
    @NotNull
    @Size(min=1, max = 2)
    private List<String> name = new ArrayList<>();

    @NotBlank
    private String surname;

    @NotBlank
    @Pattern(
            regexp = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$",
            message = "Invalid Email Format, expected X...X@X...X.X...X"
    )
    private String email;

    @NotBlank
    @Pattern(
            regexp = "\\+48 ?\\d{3}-\\d{3}-\\d{3}",
            message = "Invalid Phone number Format, expected +48 XXX-XXX-XXX"
    )
    private String phoneNumber;

    @NotNull
    private LocalDate dateOfBirth;

    // ===================== RELACJE =======================
    @OneToMany(
            mappedBy = "person",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Adress> adress = new ArrayList<>();

    // ===================== METODY =======================
    public abstract String getCurrentOccupation();
    public int countAge(){
        return Period.between(
                        this.dateOfBirth,
                        LocalDate.now())
                .getYears();
    }
}
