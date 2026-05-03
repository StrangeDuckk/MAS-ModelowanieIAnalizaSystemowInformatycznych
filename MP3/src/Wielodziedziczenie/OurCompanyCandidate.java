package Wielodziedziczenie;

import java.time.LocalDate;
import java.util.List;

public class OurCompanyCandidate extends Employee
implements ICandidate {
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
            String coverLetter) {
        super(name, surname, email, phoneNumber, dateOfBirth, salary, currentOccupationPosition);

        setCandidate(name,surname,email);
        setCoverLetter(coverLetter);
    }

    // ===================== METORY =======================
    @Override
    public Candidate getCandidate() {
        return this.candidate;
    }

    // ===================== SETTERY =======================
    private void setCoverLetter(String coverLetter) {
        if (coverLetter == null || coverLetter.isBlank()) {
            throw new IllegalArgumentException("cover letter cannot be null or blank");
        }
        this.coverLetter = coverLetter;
    }
    private void setCandidate(List<String> name, String surname, String email) {
        for (Person p: Candidate.getPersonList()) {
            if(p instanceof Candidate) {
                Candidate c = (Candidate) p;

                if (
                        c.getName().equals(name)
                        && c.getSurname().equals(surname)
                        && c.getEmail().equals(email)
                ){
                    this.candidate = c;
                    return;
                }
            }
        }
        throw new IllegalArgumentException("This candidate doesn't exist");
    }

    // ===================== GETTERY =======================
    public String getCoverLetter() {
        return coverLetter;
    }

    // ===================== TOSTRING =======================
    @Override
    public String toString() {
        return super.toString()+
                "\nOurCompanyCandidate{" +
                "candidate=" + candidate.getName()+" "+candidate.getSurname() +
                ", coverLetter='" + coverLetter + '\'' +
                "}";
    }
}
