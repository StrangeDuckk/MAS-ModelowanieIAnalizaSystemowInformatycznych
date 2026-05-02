package Wielodziedziczenie;

import java.time.LocalDate;
import java.util.List;

public class Candidate extends Person
implements ICandidate {
    //todo do sprawdzenia
    private int cvNumber;
    private String currentOccupationStudyDirection;

    // ===================== KONSTRUKTOR =======================
    public Candidate(
            List<String> name,
            String surname,
            String email,
            String phoneNumber,
            LocalDate dateOfBirth,
            int cvNumber,
            String currentOccupationStudyDirection
    ) {
        super(name, surname, email, phoneNumber, dateOfBirth);

        setCvNumber(cvNumber);
        setCurrentOccupationStudyDirection(currentOccupationStudyDirection);
    }

    // ===================== METODY =======================
    public String getStudyDirection() {
        return currentOccupationStudyDirection;
    }
    @Override
    public String getCurrentOccupation() {
        return getStudyDirection();
    }
    @Override
    public Candidate getCandidate() {
        return this;
    }

    // ===================== SETTERY =======================
    private void setCvNumber(int cvNumber) {
        if(cvNumber <0){
            throw new IllegalArgumentException("cvNumber cannot be < 0");
        }
        this.cvNumber = cvNumber;
    }
    private void setCurrentOccupationStudyDirection(String currentOccupationStudyDirection) {
        if(currentOccupationStudyDirection == null || currentOccupationStudyDirection.isBlank()){
            throw new IllegalArgumentException("study direction cannot be null or blank");
        }
        this.currentOccupationStudyDirection = currentOccupationStudyDirection;
    }

    // ===================== GETTERY =======================
    public int getCvNumber() {
        return cvNumber;
    }

    // ===================== TOSTRING =======================

    @Override
    public String toString() {
        return super.toString()+ "Candidate{" +
                "cvNumber=" + cvNumber +
                ", currentOccupationStudyDirection='" + currentOccupationStudyDirection + '\'' +
                '}';
    }
}
