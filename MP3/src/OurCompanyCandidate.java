import java.time.LocalDate;
import java.util.List;

public class OurCompanyCandidate extends Employee
implements ICandidate{
    //todo do sprawdzenia
    private Candidate candidate;
    private String coverLetter;

    // ===================== KONSTRUKTOR =======================
    protected OurCompanyCandidate(
            List<String> name,
            String surname,
            String email,
            String phoneNumber,
            LocalDate dateOfBirth,
            Double salary,
            String currentOccupationPosition,
            String coverLetter) {
        super(name, surname, email, phoneNumber, dateOfBirth, salary, currentOccupationPosition);
        this.candidate = getCandidate();//czy w ten sposob zostanie dodany candidate z klasy Candidate?
        setCoverLetter(coverLetter);
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
    }

    // ===================== GETTERY =======================
    public String getCoverLetter() {
        return coverLetter;
    }

    // ===================== TOSTRING =======================
    @Override
    public String toString() {
        return "OurCompanyCandidate{" +
                "candidate=" + candidate +
                ", coverLetter='" + coverLetter + '\'' +
                '}';
        //todo + info z candidate + info z person
    }
}
