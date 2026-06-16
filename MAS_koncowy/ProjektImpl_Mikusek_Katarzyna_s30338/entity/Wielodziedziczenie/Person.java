package edupjamas.s30338.entity.Wielodziedziczenie;

import edupjamas.s30338.entity.kwalifikowana.Application;
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
    @ToString.Exclude
    private List<Adress> adress = new ArrayList<>();

    //(jobOffer 1 ---- 1..*) Application 1..* ---- 1 Person
    @OneToMany(
            mappedBy = "person",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @ToString.Exclude
    private List<Application> applications = new ArrayList<>();

    public Person(List<String> name, String surname, String email, String phoneNumber, LocalDate dateOfBirth, List<Adress> adress, List<Application> applications) {
        this.setName(name);
        this.setSurname(surname);
        this.setEmail( email);
        this.setPhoneNumber(phoneNumber);
        this.setDateOfBirth(dateOfBirth);
        this.setAdress(adress);
        this.setApplications(applications);
    }

    // ===================== METODY =======================
    public abstract String getCurrentOccupation();
    public int countAge(){
        return Period.between(
                        this.dateOfBirth,
                        LocalDate.now())
                .getYears();
    }


    // ===================== Settery =======================

    public void setName(List<String> name) {
        if (name == null || name.isEmpty()){
            throw new IllegalArgumentException("Cannot add empty name list");
        }
        this.name = name;
    }

    public void setSurname(String surname) {
        if(surname==null || surname.isBlank()){
            throw new IllegalArgumentException("Cannot add null or blank surname");
        }
        this.surname = surname;
    }

    public void setEmail(String email) {
        if(email == null || email.isBlank()){
            throw new IllegalArgumentException("Cannot add null or blank email");
        }
        if(!email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$"))
        {
            throw new IllegalArgumentException("Invalid Email Format, expected X...X@X...X.X...X");
        }
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        if(phoneNumber == null){
            return;
        }
        if(phoneNumber.isBlank() || !phoneNumber.matches("\\+48 ?\\d{3}-\\d{3}-\\d{3}"))
        {
            throw new IllegalArgumentException("Phone number has to be null or format +48 XXX-XXX-XXX");
        }
        this.phoneNumber = phoneNumber;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        if(dateOfBirth == null || dateOfBirth.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Date of birth cannot be null or after now");
        }
        this.dateOfBirth = dateOfBirth;
    }

    public void setAdress(List<Adress> adress) {
        if(adress == null){
            throw new IllegalArgumentException("Adress cannot be null");
        }
        this.adress = adress;
        //relacje
        for (Adress adr:adress ) {
            adr.setPerson(this);
        }
    }

    public void setApplications(List<Application> applications) {
        if(applications== null){
            return;
        }
        this.applications = applications;
        //relacje
        for (Application app : applications){
            app.setPerson(this);
        }
    }
}
