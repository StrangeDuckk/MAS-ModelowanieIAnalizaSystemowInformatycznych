import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JobOffer {
    private static List<JobOffer> jobOffers = new ArrayList<>();

    // ===================== POLA =======================
    private String name;
    private String field;
    private String position;

    // <<dynamic>> -> splaszczenie hierarchii, typy: Actice, Finished
    private ENUMS.JobOfferTypeEnum jobOfferTypeENUM;

    //TYPE: ACTIVE
    private LocalDate plannedFinish;
    private int answersFromCandidates;

    //TYPE: FINISHED
    private LocalDate endDate;
    private int candidateNumbers;

    // ===================== KONSTRUKTORY =======================
    private JobOffer(){}//dla wymuszenia uzycia metod
    public static JobOffer createActiveJobOffer( //type: ACTIVE
                     String name,
                     String field,
                     String position,
                     LocalDate plannedFinish,
                     int answersNumber
    ){
        JobOffer offer = new JobOffer();
        offer.jobOfferTypeENUM = ENUMS.JobOfferTypeEnum.ACTIVE;

        offer.setName(name);
        offer.setField(field);
        offer.setPosition(position);

        offer.setPlannedFinish(plannedFinish);
        offer.setAnswersFromCandidatesNumbers(answersNumber);
        //czyszczenie wartosci nie przez settery
        offer.endDate = null;
        offer.candidateNumbers = 0;

        jobOffers.add(offer);
        return offer;
    }
    public static JobOffer createFinishedJobOffer( //type: FINISHED
                     String name,
                     String field,
                     String position,
                     LocalDate endDate,
                     int candidateNumbers
    ){
        JobOffer offer = new JobOffer();
        offer.jobOfferTypeENUM = ENUMS.JobOfferTypeEnum.FINISHED;

        offer.setName(name);
        offer.setField(field);
        offer.setPosition(position);

        offer.setEndDate(endDate);
        offer.setCandidatesNumbers(candidateNumbers);
        //czyszczenie wartosci nie przez settery
        offer.plannedFinish = null;
        offer.answersFromCandidates = 0;

        jobOffers.add(offer);
        return offer;
    }
    // ===================== ZMIANA STANU =======================
    public void changeToFinished(
            LocalDate endDate,
            int candidateNumbers
    ){
        if(this.jobOfferTypeENUM != ENUMS.JobOfferTypeEnum.ACTIVE){
            throw new IllegalStateException("You can convert only ACTIVE types into FINISHED");
        }

        this.jobOfferTypeENUM = ENUMS.JobOfferTypeEnum.FINISHED;

        // czyszczenie starego stanu
        this.plannedFinish = null;
        this.answersFromCandidates = 0;
        // nowe pola
        this.setEndDate(endDate);
        this.setCandidatesNumbers(candidateNumbers);
    }

    // ===================== SETTERY =======================
    private void setName(String name) {
        if (name == null || name.isBlank()){
            throw new IllegalArgumentException("Offer name cannot be null or blank");
        }
        this.name = name;
    }
    private void setPosition(String position) {
        if(position == null || position.isBlank()){
            throw new IllegalArgumentException("Position cannot be null or black");
        }
        this.position = position;
    }
    private void setField(String field) {
        if(field == null || field.isBlank()){
            throw new IllegalArgumentException("Field cannot be null or black");
        }
        this.field = field;
    }
    // ---------- ACTIVE -----------
    private void setPlannedFinish(LocalDate plannedFinish) {
        if(this.jobOfferTypeENUM != ENUMS.JobOfferTypeEnum.ACTIVE){
            if(plannedFinish != null){
                throw new IllegalStateException("Only ACTIVE can have plannedFinish");
            }
            this.plannedFinish = null;
            return;
        }

        if(plannedFinish == null){
            throw new IllegalArgumentException("When ACTIVE Planned finnish date cannot be null");
        }
        if(plannedFinish.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("Planned finish date cannot before now");
        }

        this.plannedFinish = plannedFinish;
    }
    private void setAnswersFromCandidatesNumbers(int answersNumber) {
        if(answersNumber<0){
            throw new IllegalArgumentException("Anserers Number cannot be < 0");
        }
        this.answersFromCandidates = answersNumber;
    }
    // ---------- FINISHED -----------
    private void setEndDate(LocalDate endDate) {
        if(this.jobOfferTypeENUM != ENUMS.JobOfferTypeEnum.FINISHED){
            if(endDate != null){
                throw new IllegalStateException("Only FINISHED can have endDate");
            }
            this.endDate = null;
            return;
        }

        if(endDate == null){
            throw new IllegalArgumentException("EndDate cannot be null");
        }
        if(endDate.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("End date cannot be after today");
        }

        this.endDate = endDate;
    }
    private void setCandidatesNumbers(int i) {
        if(i<0){
            throw new IllegalArgumentException("Number cannot be < 0");
        }
        this.candidateNumbers= i;
    }

    // ===================== GETTERY =======================
    public static List<JobOffer> getJobOffers() {
        return Collections.unmodifiableList(jobOffers);
    }
    public String getName() {
        return name;
    }
    public String getField() {
        return field;
    }
    public String getPosition() {
        return position;
    }
    public ENUMS.JobOfferTypeEnum getJobOfferTypeENUM() {
        return jobOfferTypeENUM;
    }

    // ------------ ACTIVE ----------------
    public LocalDate getPlannedFinish() {
        if(this.jobOfferTypeENUM != ENUMS.JobOfferTypeEnum.ACTIVE)
            throw new IllegalStateException("Cannot get Planned Finnish date if state isn't ACTIVE");

        return this.plannedFinish;
    }
    public int getAnswersFromCandidates() {
        if(this.jobOfferTypeENUM != ENUMS.JobOfferTypeEnum.ACTIVE)
            throw new IllegalStateException("Cannot get number of answers from candidates if state isn't ACTIVE");

        return this.answersFromCandidates;
    }

    // ------------ FINISHED ----------------
    public LocalDate getEndDate() {
        if(this.jobOfferTypeENUM != ENUMS.JobOfferTypeEnum.FINISHED)
            throw new IllegalStateException("Cannot get End Date if state isn't ACTIVE");

        return this.endDate;
    }
    public int getCandidateNumbers() {
        if(this.jobOfferTypeENUM != ENUMS.JobOfferTypeEnum.FINISHED)
            throw new IllegalStateException("Cannot get number of candiates if state isn't ACTIVE");

        return this.candidateNumbers;
    }
}
