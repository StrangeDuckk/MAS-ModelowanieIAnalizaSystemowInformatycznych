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

    @ManyToOne(cascade = CascadeType.ALL)//adres nie zawsze nalezy do osoby, czasem jest firmy
    @JoinColumn(name = "personId")
    @ToString.Exclude
    private Person person;
    public Adress(
            String road, int houseNumber, int apartmentNumber, String postalCode, String town, String country) {
        this.setRoad(road);
        this.setHouseNumber(houseNumber);
        this.setApartmentNumber(apartmentNumber);
        this.setPostalCode(postalCode);
        this.setTown(town);
        this.setCountry(country);
    }

    // ===================== SETTERY =======================
    private void setRoad(String road) {
        if (road == null || road.isBlank()) {
            throw new IllegalArgumentException("Road cannot be null or blank");
        }
        this.road = road;
    }
    private void setHouseNumber(int houseNumber) {
        if (houseNumber < 0) {
            throw new IllegalArgumentException("House number cannot be < 0");
        }
        this.houseNumber = houseNumber;
    }
    private void setApartmentNumber(int apartmentNumber) {
        if (apartmentNumber < 0) {
            throw new IllegalArgumentException("Apartment number cannot be < 0");
        }
        this.apartmentNumber = apartmentNumber;
    }
    private void setPostalCode(String postalCode) {
        if (postalCode == null || postalCode.isBlank()) {
            throw new IllegalArgumentException("Postal code cannot be null or blank");
        }

        if (!postalCode.matches("^\\d{2}-\\d{3}$")) {
            throw new IllegalArgumentException(
                    "Postal code must be in format XX-XXX"
            );
        }

        this.postalCode = postalCode;
    }
    private void setTown(String town) {
        if (town == null || town.isBlank()) {
            throw new IllegalArgumentException("Town cannot be null or blank");
        }
        this.town = town;
    }
    private void setCountry(String country) {
        if (country == null || country.isBlank()) {
            throw new IllegalArgumentException("Country cannot be null or blank");
        }
        this.country = country;
    }
    private void setAdressHistoryList(List<AdressHistory> adressHistoryList) {
        if (adressHistoryList == null) {
            throw new IllegalArgumentException(
                    "Adress history list cannot be null"
            );
        }
        this.adressHistoryList = adressHistoryList;
    }
    public void setPerson(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("Person cannot be null");
        }
        this.person = person;
    }
}
