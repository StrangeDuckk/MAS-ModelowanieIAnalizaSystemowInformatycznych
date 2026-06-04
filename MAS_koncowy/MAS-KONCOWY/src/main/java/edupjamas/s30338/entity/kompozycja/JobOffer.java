package edupjamas.s30338.entity.kompozycja;
import edupjamas.s30338.ENUMS.*;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JobOffer {
    @Id
    private int jobOfferId;

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

    public static JobOffer createJobOfferActive(
            String name,
            String field,
            String position,
            int salary,
            LocalDate plannedFinish,
            int expectedAnswersNumber
    ){
        JobOffer offer = new JobOffer();
        offer.jobOfferTypeENUM = JobOfferTypeEnum.ACTIVE;

        offer.setName(name);
        offer.setField(field);
        offer.setPosition(position);
        offer.setSalary(salary);

        offer.setPlannedFinish(plannedFinish);
        offer.setExpectedAnswersFromCandidates(expectedAnswersNumber);
        //czyszczenie wartosci nie przez settery
        offer.endDate = null;
        offer.candidateNumbers = 0;
        offer.archiveDate = null;

        validateState(offer);//validacja dla zmiennych zaleznych od stanu

        return offer;
    }

    public static JobOffer createJobOfferFinished(
            String name,
            String field,
            String position,
            int salary,
            LocalDate endDate,
            int candidateNumbers
    ){
        JobOffer offer = new JobOffer();
        offer.jobOfferTypeENUM = JobOfferTypeEnum.FINISHED;

        offer.setName(name);
        offer.setField(field);
        offer.setPosition(position);
        offer.setSalary(salary);

        offer.setEndDate(endDate);
        offer.setCandidateNumbers(candidateNumbers);
        //czyszczenie wartosci nie przez settery
        offer.plannedFinish = null;
        offer.expectedAnswersFromCandidates = 0;
        offer.archiveDate = null;

        validateState(offer);

        return offer;
    }

    public static JobOffer createJobOfferArchived(
            String name,
            String field,
            String position,
            int salary,
            LocalDate archideDate
    ){
        JobOffer offer = new JobOffer();
        offer.jobOfferTypeENUM = JobOfferTypeEnum.ARCHIVED;

        offer.setName(name);
        offer.setField(field);
        offer.setPosition(position);
        offer.setSalary(salary);

        offer.setArchiveDate(archideDate);
        //czyszczenie wartosci nie przez settery
        offer.plannedFinish = null;
        offer.expectedAnswersFromCandidates = 0;
        offer.endDate = null;
        offer.candidateNumbers = 0;

        validateState(offer);

        return offer;
    }
    // ===================== walidacja zmiennych stnanu =======================
    private static void validateState(JobOffer offer){
        switch(offer.jobOfferTypeENUM){
            case ACTIVE -> {
                if(offer.plannedFinish  == null || offer.plannedFinish.isBefore(LocalDate.now())){
                    throw new IllegalStateException("Active offer has to have planned finish and it has to be after now");
                }
                if(offer.expectedAnswersFromCandidates <0){
                    throw new IllegalStateException("Active offer has to have expectedAnswersFromCandiates >=0");
                }
                //null albo 0:
                if(offer.endDate != null || offer.archiveDate != null){
                    throw new IllegalStateException("Active offer has to have end date and archive date set to null");
                }
                if(offer.candidateNumbers != 0){
                    throw new IllegalStateException("Active offer has to have candidates numbers set to 0");
                }
            }
            case FINISHED -> {
                if(offer.endDate  == null || offer.endDate.isAfter(LocalDate.now())){
                    throw new IllegalStateException("Finished offer has to have end date and it has to be after now");
                }
                if(offer.candidateNumbers <0){
                    throw new IllegalStateException("Finished offer has to have candidates numbers >=0");
                }
                //null albo 0:
                if(offer.plannedFinish != null || offer.archiveDate != null){
                    throw new IllegalStateException("Finished offer has to have planned finish and archive date set to null");
                }
                if(offer.expectedAnswersFromCandidates != 0){
                    throw new IllegalStateException("Finished offer has to have expected answers from candidates numbers set to 0");
                }
            }
            case ARCHIVED -> {
                if(offer.archiveDate  == null || offer.archiveDate.isAfter(LocalDate.now())){
                    throw new IllegalStateException("Archived offer has to have end date and it has to be earlier than now");
                }
                //null albo 0:
                if(offer.plannedFinish != null || offer.endDate != null){
                    throw new IllegalStateException("Finished offer has to have planned finish and archive date set to null");
                }
                if(offer.expectedAnswersFromCandidates != 0 || offer.candidateNumbers != 0){
                    throw new IllegalStateException("Finished offer has to have expected answers from candidates and candidate numbers set to 0");
                }
            }
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

        validateState(this);
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

        validateState(this);
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
