package edupjamas.s30338.entity.zAtrybutem;

import edupjamas.s30338.entity.Wielodziedziczenie.Person;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Adress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adressId;

    @NotBlank
    private String road;

    @PositiveOrZero
    private int houseNumber;

    @PositiveOrZero
    private int apartmentNumber;

    @NotBlank
    @Pattern(
            regexp = "^\\d{2}-\\d{3}$",
            message = "Postal code must be in format 00-000"
    )
    private String postalCode;

    @NotBlank
    private String town;

    @NotBlank
    private String country;

    // ===================== RELACJE =======================
    //(Company 1 ---- 1..* )AdressHistory 1..* ---- 1 Adress
    @OneToMany(
            mappedBy = "adress"//brak usuwania kaskadowego
    )
    private List<AdressHistory> adressHistoryList = new ArrayList<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "personId")
    private Person person;
}
