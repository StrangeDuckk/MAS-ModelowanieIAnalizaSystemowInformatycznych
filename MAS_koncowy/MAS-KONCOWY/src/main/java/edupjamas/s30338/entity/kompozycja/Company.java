package edupjamas.s30338.entity.kompozycja;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int companyId; //todo relacja z jobOffer

    @NotBlank(message = "field is mandatory")
    private String name;

    @PositiveOrZero
    private static double minCountrySalary;

    @PositiveOrZero
    private double minCompanySalary;// zawsze >= minCountrySalary


    //klucz obcy do adressHistory // todo pozniej
//    private int adressHistoryId;

    //kompozycja: company calosc 1 --- 0..1 NormalOffice czesc
    @OneToOne(
            mappedBy = "company",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "normalOfficeId")
    private NormalOffice normalOffice;

    //kompozycja: company calosc 1 --- 0..1 StateOffice czesc
    @OneToOne(
            mappedBy = "company",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "stateOfficeId")
    private StateOffice stateOffice;

    private Company(String name, Double salary) {
        setName(name);
        setMinCompanySalary(salary);
    }

    public static Company createCompanyNormalOffice(
            String name,
//            String adressHistoryId,
            Double minCompanySalary,
            String field,
            String ceo,
            double capital
    ) {
        Company company = new Company(name,minCompanySalary);

//        setAdress(adressHistoryId);

        company.normalOffice = new NormalOffice(field,ceo,capital,company);

        return company;
    }
    public static Company createCompanyStateOffice(
            String name,
//            String adressHistoryId,
            Double minCompanySalary,
            String state,
            String country,
            String minister,
            boolean military
    ) {
        Company company = new Company(name,minCompanySalary);

//        setAdress(adressHistoryId);

        company.stateOffice = new StateOffice(state,country,minister, military,company);

        return company;
    }
    public static Company createCompanyNormalAndStateOffice(
            String name,
//            String adressHistoryId,
            Double minCompanySalary,
            String field,
            String ceo,
            double capital,
            String state,
            String country,
            String minister,
            boolean military
    ) {
        Company company = new Company(name,minCompanySalary);

//        setAdress(adressHistoryId);

        company.normalOffice = new NormalOffice(field,ceo,capital,company);
        company.stateOffice = new StateOffice(state,country,minister, military,company);

        return company;
    }
    // ===================== Validacja =======================
    @PrePersist
    @PreUpdate
    private void validateComposition(){
        if(normalOffice == null && stateOffice == null){
            throw new IllegalStateException("Company must have at least one type of office");
        }
    }

    // ===================== SETTERY =======================
    public static void setMinCountrySalary(double salary){
        if(salary <0.0){
            throw new IllegalArgumentException("min country Salary cannot be <0.0");
        }
        minCountrySalary = salary;
    }
    public void setMinCompanySalary(double salary){
        if(salary <0.0){
            throw new IllegalArgumentException("min company Salary cannot be <0.0");
        }
        if(salary < minCountrySalary){
            throw new IllegalArgumentException("min company salary cannot be < minCountrySalary");
        }
        this.minCompanySalary = salary;
    }
    private void setName(String name) {
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("name cannot be null or blank");
        }
        this.name = name;
    }
}
