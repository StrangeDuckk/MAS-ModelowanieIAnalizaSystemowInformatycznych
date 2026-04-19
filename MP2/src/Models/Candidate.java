package Models;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class Candidate implements Serializable {
    private List<Adress> adresses = new ArrayList<>();//Candidate -> wiele Adresses, Adress -> 1 Candidate
    private Map<String, CV> cvs = new HashMap<>();//Name_Surname_number // 9.2
    private static List<Candidate> Candidates = new ArrayList<>();

    private ArrayList<String> name = new ArrayList<>();//list of names, from 1 to max 2 names per candidate
    private String surname;
    private String email;
    private String phoneNumber;//atrybut opcjonalny
    private LocalDate dateOfBirth;

    // ====================== Konstruktor ======================
    public Candidate(
            ArrayList<String> name,
            String surname,
            String road,
            int houseNumber,
            int apartamentNumber,
            String postalCode,
            String town,
            String country,
            String email,
            String phoneNumber,
            LocalDate dateOfBirth,
            List<String> education,
            List<String> experience
    ) {
        setName(name);
        setSurname(surname);
        setEmail(email);
        addAdress(new Adress(road,houseNumber,apartamentNumber,postalCode,town,country));
        setPhoneNumber(phoneNumber);
        setDateOfBirth(dateOfBirth);
        String cvNumber = this.name.get(0)+"_"+this.surname+"_"+this.cvs.size();
        addCv(new CV(cvNumber, education, experience,this));

        Candidates.add(this);
    }


    // ====================== Funkcje ======================

    @Override
    public String toString() {
        String temp = "\n----------------------\n"+this.name.toString();
        temp += " " +this.surname + ", living in " + this.adresses.get(0).toString();
        temp += "\nemail: " + this.email + "\nphone: " + this.phoneNumber+ "\nborn " + this.dateOfBirth.toString()+ " ("+
                Period.between(
                        this.dateOfBirth,
                        LocalDate.now())
                .getYears()+")";
        if(this.cvs != null){
            for (int i = 0; i < this.cvs.size(); i++) {
                temp += this.cvs.get(this.name+"_"+this.surname+"_"+i).toString();
            }
        }

        return temp;
    }
    // ======================    relacje    ==========================
    public List<Adress> getAdresses() {//6.1
        return Collections.unmodifiableList(adresses);
    }
    public void addAdress(Adress adress) {//6.2
        if(adress == null){
            throw new IllegalArgumentException("Adress cannot be null");
        }
        if(adress.getCandidate() != null && adress.getCandidate() != this){
            throw new IllegalArgumentException("Adress already belongs to another Candidate");
        }
        if(adresses.contains(adress))
            return;//do zatrzymania referencji zwrotnej

        adresses.add(adress);
        adress.addCandidate(this);//referencja zwrotna 6.2
    }
    public void removeAdress(Adress adress){//6.3
        if(adress == null){
            throw new IllegalArgumentException("Adress cannot be null");
        }
        if(!adresses.contains(adress)){
            throw new IllegalArgumentException("You cannot remove adress that isn't in Adresses");//sprawdzic dla referencji zwrotnej
        }

        adresses.remove(adress);

        if(adress.getCandidate() == this) {//nie dopuszczenie do zapetlenia usuwania
            adress.removeCandidate(this);//referencja zwrotna
        }
    }
    public void replaceAdress(Adress oldAdr, Adress newAdr){//6.4
        if(oldAdr.equals(newAdr)){
            throw new IllegalArgumentException("You cannot replace adress by the same adress");
        }
        removeAdress(oldAdr);
        addAdress(newAdr);
    }

    public Map<String, CV> getCvs() {//6.1
        return Collections.unmodifiableMap(cvs);
    }
    public CV getByCvNumber(String cvNumber){//6.1
        if (cvNumber == null || cvNumber.isBlank()){
            throw new IllegalArgumentException("CvNumber cannot be null or blank");
        }
        if(!this.cvs.containsKey(cvNumber)){
            throw new IllegalArgumentException("CV table doesn't contain this CvNumber");
        }

        return cvs.get(cvNumber);
    }
    public void addCv(CV cv){//6.2
        if(cv == null || cv.getCvNumber() == null){
            throw new IllegalArgumentException("cv to add cannot be null");
        }
        if(this.cvs.containsKey(cv.getCvNumber())){
            return; // zakonczenie referencji
        }
        if(!cv.getCvNumber().matches("^[^@\\s]+_[^@\\s]+_[0-9]+$")){
            throw new IllegalArgumentException("Invalid CvNumber format, expected: Name_Surname_Number");
        }

        this.cvs.put(cv.getCvNumber(), cv);
        cv.setCandidate(this); //referencja
    }
    public void removeCv(CV cv){//6.3
        if(cv==null) {
            throw new IllegalArgumentException("Cv cannot be null");
        }
        if(!this.cvs.containsKey(cv.getCvNumber())){
            return; //dla zakonczenia referencji
        }

        cvs.remove(cv.getCvNumber());
        if(cv.getCandidate() == this){
            cv.removeCandidate(this); // zamkniecie polaczenia
        }
    }
    public void updateCvKey(String oldKey, String newKey){//6.4
        if(oldKey == null || oldKey.isBlank()){
            throw new IllegalArgumentException("Old cvKey cannot be null or blank");
        }
        if(newKey == null || newKey.isBlank()){
            throw new IllegalArgumentException("New cvKey cannot be null or blank");
        }

        if(oldKey.equals(newKey)){
            throw new IllegalArgumentException("Old key and new key are the same");
        }

        if(!this.cvs.containsKey(oldKey)){
            throw new IllegalArgumentException("Cv doesnt have old key");
        }
        if(this.cvs.containsKey(newKey)){
            throw new IllegalArgumentException("Cv already contains new key");
        }

        CV cv = cvs.get(oldKey);
        cvs.remove(oldKey);
        cv.setCvNumber(newKey);
        cvs.put(newKey,cv);
    }

    // ====================== Gettery i Settery ======================
    public List<String> getName() {
        return Collections.unmodifiableList(this.name);
    }
    public void setName(ArrayList<String> Aname) {
        if(Aname == null||Aname.isEmpty()){
            throw new IllegalArgumentException("Array can't be null or empty");
        }
        if(Aname.size()>2){
            throw new IllegalArgumentException("Maximum length for Candidates names table is 2");
        }
        for (String s : Aname) {
            addName(s);
        }
    }
    public void removeName(int index){
        if(this.name.size() == 1){
            throw new IllegalArgumentException("Available length for Candidate's names table is [1..2]");
        } else if (index < 0 || index >= name.size()) {
            throw  new IllegalArgumentException("Available indexes for Candidate's names table is 0 or 1");
        }
        this.name.remove(index);
    }
    public void addName(String aname){
        if(aname == null || aname.isEmpty()){
            throw new IllegalArgumentException("Argument has to have any value");
        }
        if(this.name.size()>=2){
            throw new IllegalArgumentException("Table is full (length:"+this.name.size()+")");
        }
        this.name.add(aname);
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        if(surname == null || surname.isEmpty()) {
            throw new IllegalArgumentException("Argument has to have any value");
        }
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        if(!email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")){
            throw new IllegalArgumentException("Invalid Email Format, expected X...X@X...X.X...X ");
        }
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        //opcjonalny - pozwolenie na null ale nie na blank
        if(phoneNumber!= null && phoneNumber.isBlank()){
            throw new IllegalArgumentException("Argument can not be blank");
        }
        if (phoneNumber != null && !phoneNumber.matches("\\+48 ?\\d{3}-\\d{3}-\\d{3}")) {
            throw new IllegalArgumentException("Invalid Phone number Format, expected +48 XXX-XXX-XXX");
        }
        this.phoneNumber = phoneNumber;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        if(dateOfBirth == null){
            throw new IllegalArgumentException("Candidate has to have any birth date");
        }
        if (dateOfBirth.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Candidate has to be outside his mother :)");
        }
        this.dateOfBirth = dateOfBirth;
    }

    public static List<Candidate> getCandidates() {
        return Collections.unmodifiableList(Candidates);
    }
}