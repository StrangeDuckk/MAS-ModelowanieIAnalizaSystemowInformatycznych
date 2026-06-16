package edupjamas.s30338.entity.kompozycja;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class StateOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stateOfficeId;

    @NotBlank
    private String state;

    @NotBlank
    private String country;

    @NotBlank
    private String minister;

    private boolean military;

    //relacja:
    @OneToOne(optional = false)
    @JoinColumn(name = "companyId")
    @ToString.Exclude
    private Company company;
    public StateOffice(String state, String country, String minister, boolean military, Company company) {
        setState(state);
        setContry(country);
        setMinister(minister);
        this.military = military;
        this.company = company;
    }

    // ===================== Settery =======================
    private void setState(String state) {
        if(state == null || state.isBlank()){
            throw new IllegalArgumentException("State canoot be null or blank");
        }
        this.state = state;
    }
    private void setContry(String country) {
        if(country == null || country.isBlank()){
            throw new IllegalArgumentException("Country canoot be null or blank");
        }
        this.country = country;
    }
    private void setMinister(String minister) {
        if(minister == null || minister.isBlank()){
            throw new IllegalArgumentException("minister canoot be null or blank");
        }
        this.minister = minister;
    }

}
