import java.util.ArrayList;
import java.util.List;

public class CV {
    private Candidate candidate;//1 candidate can have multiple cv's, 1 cv can have 1 candidate

    private static List<CV> Cvs = new ArrayList<>();
    private String cvNumber; //Name_Surname_number // 9.1
    private List<String> education = new ArrayList<>(); //only names
    private List<String> experience = new ArrayList<>();//short info

    // ================= konstruktor =======================
    public CV(String cvNumber, List<String> education, List<String> experience, Candidate candidate){
        setCvNumber(cvNumber);
        setEducation(education);
        setExperience(experience);

        setCandidate(candidate);
    }
    // ================= relacje =================
    public Candidate getCandidate() {// 6.1
        return candidate;
    }
    public void setCandidate(Candidate candidate) {//6.2 i 6.4
        if(candidate == null){
            throw new IllegalArgumentException("Cannot add null candidate to CV");
        }

        if(this.candidate == candidate){
            return; // zakonczenie relacji zwrotnej
        }

        //usuniecie starej relacji manualnie zeby nie strigerowac wyjatku
        //spelnienei 6.4
        if(this.candidate!= null){
            Candidate old = this.candidate;
            this.candidate = null;
            old.removeCv(this);
        }

        //nowa relacja
        this.candidate = candidate;

        if(!candidate.getCvs().containsValue(this)){
            candidate.addCv(this);
        }
    }
    public void removeCandidate(Candidate candidate){//6.3
        if(this.candidate != candidate){
            throw new IllegalArgumentException("Cannot remove candidate. Candidates doesn't match");
        }

        if(this.candidate == null){
            return; // zakonczenie referencji
        }

        Candidate old = this.candidate;
        this.candidate = null;
        old.removeCv(this);//usuniecie polaczenia
    }

    // ================= gettery i settery ==============
    public void setCvNumber(String cvNumber) {
        if(cvNumber == null || cvNumber.isBlank()){
            throw new IllegalArgumentException("CvNumber cannot be null");
        }
        if(!cvNumber.matches("^[^@\\s]+_[^@\\s]+_[0-9]+$")){//Name_Surname_number
            throw new IllegalArgumentException("Invalid CvNumber format, expected: Name_Surname_Number");
        }
        if(this.cvNumber.equals(cvNumber)){
            return; // zakonczenie referencji zwrotnej
        }

        if(candidate!= null) {//9.3
            this.candidate.updateCvKey(this.cvNumber,cvNumber);
        }
        this.cvNumber = cvNumber;
    }
    public String getCvNumber() {
        return cvNumber;
    }

    public void setEducation(List<String> education) {
        if(education == null){
            this.education = null;
            return;
        }
        if(education.isEmpty()){//can be null
            throw new IllegalArgumentException("Education list cannot be empty");
        }
        for (String e: education)
            addEducation(e);
    }
    public void removeEducation(String e){
        if(e == null || e.isBlank()){
            throw new IllegalArgumentException("Cannot remove null or blank education");
        }
        if(!this.education.contains(e)){
            throw new IllegalArgumentException("Education list doesn't contains record");
        }
        this.education.remove(e);
    }
    private void addEducation(String e) {
        if(e == null){//it can be null, but wont add it
            return;
        }
        if(e.isBlank()){
            throw new IllegalArgumentException("Education cannot be blank");
        }
        if(this.education.contains(e)){
            throw new IllegalArgumentException("Education already contains this education record");
        }
        this.education.add(e);
    }
    public void setExperience(List<String> experience) {
        if(experience == null){
            this.experience=null;
            return;
        }
        if(experience.isEmpty()){
            throw new IllegalArgumentException("Experience list cannot be empty");
        }
        for (String e: experience)
            addExperience(e);
    }
    public void removeExperience(String e){
        if(e == null || e.isBlank()){
            throw new IllegalArgumentException("Cannot remove null or blank experience");
        }
        if(!this.experience.contains(e)){
            throw new IllegalArgumentException("Experience list doesn't contains record");
        }
        this.experience.remove(e);
    }

    private void addExperience(String e) {
        if(e == null){ //can be null but wont add it
            return;
        }
        if(e.isBlank()){
            throw new IllegalArgumentException("Experience cannot be null or blank");
        }
        if(this.experience.contains(e)){
            throw new IllegalArgumentException("Experience already contains this education record");
        }
        this.experience.add(e);
    }

    // ================= funkcje ====================

    @Override
    public String toString() {
        String temp = "\nCvNumber: "+this.cvNumber+"\n";
        if (this.experience != null){
            temp += "Experience: ";
            for (String e: this.experience) {
                temp += e+"\n";
            }
        }
        if(this.education != null){
            temp += "Education: ";
            for(String e: this.education){
                temp += e+"\n";
            }
        }

        return temp;
    }
}