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
public class NormalOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int normalOfficeId;

    @NotBlank
    private String field;

    @NotBlank
    private String ceo;

    @NotNull
    private Double companyCapital;

    // relacja:
    @OneToOne(optional = false)
    @JoinColumn(name = "companyId")
    @ToString.Exclude
    private Company company;

    protected NormalOffice(String field, String ceo, double capital, Company company) {
        setField(field);
        setCeo(ceo);
        setCapital(capital);
        this.company = company;
    }

    // ===================== Settery =======================
    private void setField(String field) {
        if(field == null || field.isBlank()){
            throw new IllegalArgumentException("Field canoot be null or blank");
        }
        this.field = field;
    }
    private void setCeo(String ceo) {
        if(ceo == null || ceo.isBlank()){
            throw new IllegalArgumentException("Ceo cannot be null or blank");
        }
        this.ceo = ceo;
    }
    private void setCapital(double capital) {
        if(capital <=0){
            throw new IllegalArgumentException("Company capital cannot be <=0");
        }
        this.companyCapital = capital;
    }
}
