package edupjamas.s30338.entity.kompozycja;
import edupjamas.s30338.ENUMS.*;
import edupjamas.s30338.entity.kwalifikowana.Application;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JobOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobOfferId;

    // ===================== POLA =======================
    @NotBlank(message = "name is mandatory")
    private String name;

    @NotBlank(message = "field is mandatory")
    private String field;

    @NotBlank(message = "field is mandatory")
    private String position;

    @PositiveOrZero
    private int salary;//per month

    // <<dynamic>> -> splaszczenie hierarchii, typy: Actice, Finished, Archived
    @NotNull(message = "job offer type is mandatory")
    @Enumerated(EnumType.STRING)
    private JobOfferTypeEnum jobOfferTypeENUM;

    //TYPE: ACTIVE
    @FutureOrPresent(message = "planned finish has to be in furute or present")
    private LocalDate plannedFinish;

    @PositiveOrZero
    private int expectedAnswersFromCandidates;

    //TYPE: FINISHED
    @PastOrPresent(message = "end date has to be in past or present")
    private LocalDate endDate;

    @PositiveOrZero
    private int candidateNumbers;

    //TYPE: ARCHIVED
    @Past(message = "archive date has to be in past")
    private LocalDate archiveDate;

    // ===================== RELACJE =======================
    //kompozycja JobOffer0..* --- 1 Company
    @ManyToOne
    @JoinColumn(name = "companyId")
    @ToString.Exclude
    private Company company;

    //jobOffer 1 ---- 1..* Application( 1..* ---- 1 Person)
    @OneToMany(
            mappedBy = "jobOffer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @ToString.Exclude
    private List<Application> applications = new ArrayList<>();

    // ===================== Fabrykatory =======================
    protected static JobOffer createJobOfferActive(
            String name,
            String field,
            String position,
            int salary,
            LocalDate plannedFinish,
            int expectedAnswersNumber,
            Company company
    ){
        JobOffer offer = new JobOffer();
        offer.jobOfferTypeENUM = JobOfferTypeEnum.ACTIVE;

        offer.setName(name);
        offer.setField(field);
        offer.setPosition(position);
        offer.setSalary(salary);
        offer.setCompany(company);

        offer.setPlannedFinish(plannedFinish);
        offer.setExpectedAnswersFromCandidates(expectedAnswersNumber);
        //czyszczenie wartosci nie przez settery
        offer.endDate = null;
        offer.candidateNumbers = 0;
        offer.archiveDate = null;

        return offer;
    }
    protected static JobOffer createJobOfferFinished(
            String name,
            String field,
            String position,
            int salary,
            LocalDate endDate,
            int candidateNumbers,
            Company company
    ){
        JobOffer offer = new JobOffer();
        offer.jobOfferTypeENUM = JobOfferTypeEnum.FINISHED;

        offer.setName(name);
        offer.setField(field);
        offer.setPosition(position);
        offer.setSalary(salary);
        offer.setCompany(company);

        offer.setEndDate(endDate);
        offer.setCandidateNumbers(candidateNumbers);
        //czyszczenie wartosci nie przez settery
        offer.plannedFinish = null;
        offer.expectedAnswersFromCandidates = 0;
        offer.archiveDate = null;

        return offer;
    }
    protected static JobOffer createJobOfferArchived(
            String name,
            String field,
            String position,
            int salary,
            LocalDate archideDate,
            Company company
    ){
        JobOffer offer = new JobOffer();
        offer.jobOfferTypeENUM = JobOfferTypeEnum.ARCHIVED;

        offer.setName(name);
        offer.setField(field);
        offer.setPosition(position);
        offer.setSalary(salary);
        offer.setCompany(company);

        offer.setArchiveDate(archideDate);
        //czyszczenie wartosci nie przez settery
        offer.plannedFinish = null;
        offer.expectedAnswersFromCandidates = 0;
        offer.endDate = null;
        offer.candidateNumbers = 0;

        return offer;
    }
    // ===================== walidacja zmiennych stnanu =======================
    @PrePersist
    @PreUpdate
    private void validateState(){
        if(company == null){
            throw new IllegalStateException("JobOffer must belong to Company");
        }
        switch(this.jobOfferTypeENUM){
            case ACTIVE -> {
                if(this.plannedFinish  == null || this.plannedFinish.isBefore(LocalDate.now())){
                    throw new IllegalStateException("Active this has to have planned finish and it has to be after now");
                }
                if(this.expectedAnswersFromCandidates <0){
                    throw new IllegalStateException("Active this has to have expectedAnswersFromCandiates >=0");
                }
                //null albo 0:
                if(this.endDate != null || this.archiveDate != null){
                    throw new IllegalStateException("Active this has to have end date and archive date set to null");
                }
                if(this.candidateNumbers != 0){
                    throw new IllegalStateException("Active this has to have candidates numbers set to 0");
                }
            }
            case FINISHED -> {
                if(this.endDate  == null || this.endDate.isAfter(LocalDate.now())){
                    throw new IllegalStateException("Finished this has to have end date and it has to be after now");
                }
                if(this.candidateNumbers <0){
                    throw new IllegalStateException("Finished this has to have candidates numbers >=0");
                }
                //null albo 0:
                if(this.plannedFinish != null || this.archiveDate != null){
                    throw new IllegalStateException("Finished this has to have planned finish and archive date set to null");
                }
                if(this.expectedAnswersFromCandidates != 0){
                    throw new IllegalStateException("Finished this has to have expected answers from candidates numbers set to 0");
                }
            }
            case ARCHIVED -> {
                if(this.archiveDate  == null || this.archiveDate.isAfter(LocalDate.now())){
                    throw new IllegalStateException("Archived this has to have end date and it has to be earlier than now");
                }
                //null albo 0:
                if(this.plannedFinish != null || this.endDate != null){
                    throw new IllegalStateException("Finished this has to have planned finish and archive date set to null");
                }
                if(this.expectedAnswersFromCandidates != 0 || this.candidateNumbers != 0){
                    throw new IllegalStateException("Finished this has to have expected answers from candidates and candidate numbers set to 0");
                }
            }
            default -> throw new IllegalStateException("Unknown state");
        }
    }

    // ===================== ZMIANA STANU =======================
    public void changeToFinished(
            LocalDate endDate,
            int candidateNumbers
    ){
        if(this.jobOfferTypeENUM != JobOfferTypeEnum.ACTIVE){
            throw new IllegalStateException("You can convert only ACTIVE types into FINISHED");
        }

        this.jobOfferTypeENUM = JobOfferTypeEnum.FINISHED;

        // czyszczenie starego stanu
        this.plannedFinish = null;
        this.expectedAnswersFromCandidates = 0;
        this.archiveDate = null;
        // nowe pola
        this.endDate = endDate;
        this.candidateNumbers = candidateNumbers;
    }
    public void changeToArchived(
            LocalDate archiveDate
    ){
        if(this.jobOfferTypeENUM != JobOfferTypeEnum.FINISHED){
            throw new IllegalStateException("You can convert only Finished types into Archived");
        }

        this.jobOfferTypeENUM = JobOfferTypeEnum.ARCHIVED;

        // czyszczenie starego stanu
        this.plannedFinish = null;
        this.expectedAnswersFromCandidates = 0;
        this.endDate = null;
        this.candidateNumbers = 0;
        // nowe pola
        this.archiveDate = archiveDate;
    }

    // ===================== Settery =======================
    private void setName(String name){
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException("JobOffer has to have a name");
        }
        this.name = name;
    }
    private void setField(String field){
        if(field == null || field.isEmpty()){
            throw new IllegalArgumentException("JobOffer has to have a field");
        }
        this.field = field;
    }
    private void setPosition(String position){
        if(position == null || position.isEmpty()){
            throw new IllegalArgumentException("JobOffer has to have a position");
        }
        this.position = position;
    }
    private void setSalary(int salary){
        if(salary <0){
            throw new IllegalArgumentException("Salary cannot be <0");
        }
        this.salary = salary;
    }
    private void setCompany(Company company) {
        if(company == null){
            throw new IllegalStateException("Job offer has to be added to existing company");
        }
        this.company = company;
    }
    private void setPlannedFinish(LocalDate plannedFinish){
        if(plannedFinish == null){
            throw new IllegalArgumentException("Planned finnish cannot be null");
        }
        this.plannedFinish = plannedFinish;
    }
    private void setExpectedAnswersFromCandidates(int expected){
        if(expected < 0){
            throw new IllegalArgumentException("Expected answers cannot be < 0");
        }
        this.expectedAnswersFromCandidates = expected;
    }
    private void setEndDate(LocalDate endDate){
        if(endDate == null){
            throw new IllegalArgumentException("end date cannot be null");
        }
        this.endDate = endDate;
    }
    private void setCandidateNumbers(int numbers){
        if(numbers < 0){
            throw new IllegalArgumentException("Numbers of answers cannot be < 0");
        }
        this.candidateNumbers = numbers;
    }
    private void setArchiveDate(LocalDate archiveDate){
        if (archiveDate == null){
            throw new IllegalArgumentException("Archive date cannot be null");
        }
        this.archiveDate = archiveDate;
    }
}
