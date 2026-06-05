package edupjamas.s30338.entity.zAtrybutem;

import edupjamas.s30338.entity.kompozycja.Company;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import org.aspectj.lang.annotation.Before;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class AdressHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adressHistoryId;

    @NotNull
    @PastOrPresent
    private LocalDate from;

    private LocalDate to;

    // ===================== RELACJE =======================
    // Company 1 --- 1..* AdressHistory
    @ManyToOne(optional = false)
    @JoinColumn(name = "companyId")
    @ToString.Exclude
    private Company company;

    //AdressHistory 1..* ----- 1 Adress
    @ManyToOne(optional = false)
    @JoinColumn(name = "adressId")
    @ToString.Exclude
    private Adress adress;

    public AdressHistory(LocalDate from, LocalDate to, Company company, Adress adress) {
        this.setFrom( from);
        this.setTo(to);
        this.setCompany(company);
        this.setAdress(adress);
    }

    private void setFrom(LocalDate from) {
        if(from == null){
            throw new IllegalArgumentException("date from in adress history for company cannot be null");
        }
        this.from = from;
    }
    private void setTo(LocalDate to) {
        if(to != null && this.from != null && to.isBefore(this.from)){
            throw new IllegalArgumentException("date to is exist has to be after from date");
        }
        this.to = to;
    }
    private void setCompany(Company company) {
        if(company == null){
            throw new IllegalArgumentException("Company cannot be null");
        }
        this.company = company;
    }
    private void setAdress(Adress adress) {
        if(adress == null){
            throw new IllegalArgumentException("Adress cannot be null");
        }
        this.adress = adress;
    }
}
