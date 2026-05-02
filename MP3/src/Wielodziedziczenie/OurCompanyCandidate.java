package Wielodziedziczenie;

import Wielodziedziczenie.Candidate;
import Wielodziedziczenie.Employee;

import java.time.LocalDate;
import java.util.List;

public class OurCompanyCandidate extends Employee
implements ICandidate {
    //todo do sprawdzenia
    private Candidate candidate;
    private String coverLetter;

    // ===================== KONSTRUKTOR =======================
    public OurCompanyCandidate(
            List<String> name,
            String surname,
            String email,
            String phoneNumber,
            LocalDate dateOfBirth,
            Double salary,
            String currentOccupationPosition,
            Candidate candidate,
            String coverLetter) {
        super(name, surname, email, phoneNumber, dateOfBirth, salary, currentOccupationPosition);
        setCandidate(candidate);
        setCoverLetter(coverLetter);
        //todo tutaj tworzenie nowego kandydata
    }

    // ===================== METORY =======================
    @Override
    public Candidate getCandidate() {
        return candidate;
    }

    // ===================== SETTERY =======================
    private void setCoverLetter(String coverLetter) {
        if(coverLetter == null || coverLetter.isBlank()){
            throw new IllegalArgumentException("cover letter cannot be null or blank");
        }
        this.coverLetter = coverLetter;
    }
    private void setCandidate(Candidate candidate) {
        if(candidate == null)
        {
            throw new IllegalArgumentException("Candidate cannot be null");
        }
        //todo dopytac czy sprawdzenei zgodnosci info z person
        this.candidate = candidate;
    }

    // ===================== GETTERY =======================
    public String getCoverLetter() {
        return coverLetter;
    }

    // ===================== TOSTRING =======================
    @Override
    public String toString() {
        return super.toString()+
                "OurCompanyCandidate{" +
                "candidate=" + candidate +
                ", coverLetter='" + coverLetter + '\'' +
                '}';
    }
}
