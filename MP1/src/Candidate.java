import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
//todo serializacja

public class Candidate implements Serializable {
    private static List<Candidate> Candidates = new ArrayList<>();

    private static double CompanyMinSalary;

    private ArrayList<String> name = new ArrayList<>();//list of names, from 1 to max 2 names per candidate
    private String surname;
    private Adress adress;//info like road, house number
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private Job jobinfo;//info like jobTitle, department
    private List<Integer> cvNumber = new ArrayList<>();//list of just cv numbers, from 1 to max 3
    private List<String> experience = new ArrayList<>(); //list of experiences, from 0 to *, info like companyName, Occupation, Level, Years

    // ====================== Konstruktory ======================
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
            String jobTitle,
            String department,
            String shortInfo,
            String degree,
            ArrayList<Integer> cvNumber,
            ArrayList<String> experience) {
        setName(name);
        setSurname(surname);
        setEmail(email);
        setAdress(road,houseNumber,apartamentNumber,postalCode,town,country);
        setPhoneNumber(phoneNumber);
        setDateOfBirth(dateOfBirth);
        setJobinfo(jobTitle, department, shortInfo, degree);
        setCvNumber(cvNumber);
        setExperience(experience);

        Candidates.add(this);
    }

    public Candidate( //bez experience
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
            String jobTitle,
            String department,
            String shortInfo,
            String degree,
            ArrayList<Integer> cvNumber) {
        setName(name);
        setSurname(surname);
        setEmail(email);
        setAdress(road,houseNumber,apartamentNumber,postalCode,town,country);
        setPhoneNumber(phoneNumber);
        setDateOfBirth(dateOfBirth);
        setJobinfo(jobTitle, department, shortInfo, degree);
        setCvNumber(cvNumber);

        Candidates.add(this);
    }

    public Candidate(//bez adress apartmentNumber
            ArrayList<String> name,
            String surname,
            String road,
            int houseNumber,
            String postalCode,
            String town,
            String country,
            String email,
            String phoneNumber,
            LocalDate dateOfBirth,
            String jobTitle,
            String department,
            String shortInfo,
            String degree,
            ArrayList<Integer> cvNumber,
            ArrayList<String> experience) {
        setName(name);
        setSurname(surname);
        setEmail(email);
        setAdress(road,houseNumber,postalCode,town,country);
        setPhoneNumber(phoneNumber);
        setDateOfBirth(dateOfBirth);
        setJobinfo(jobTitle, department, shortInfo, degree);
        setCvNumber(cvNumber);
        setExperience(experience);

        Candidates.add(this);
    }
    public Candidate(//bez adress apartmentNumber i experience
                     ArrayList<String> name,
                     String surname,
                     String road,
                     int houseNumber,
                     String postalCode,
                     String town,
                     String country,
                     String email,
                     String phoneNumber,
                     LocalDate dateOfBirth,
                     String jobTitle,
                     String department,
                     String shortInfo,
                     String degree,
                     ArrayList<Integer> cvNumber) {
        setName(name);
        setSurname(surname);
        setEmail(email);
        setAdress(road,houseNumber,postalCode,town,country);
        setPhoneNumber(phoneNumber);
        setDateOfBirth(dateOfBirth);
        setJobinfo(jobTitle, department, shortInfo, degree);
        setCvNumber(cvNumber);

        Candidates.add(this);
    }

    public Candidate(//bez job degree
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
            String jobTitle,
            String department,
            String shortInfo,
            ArrayList<Integer> cvNumber,
            ArrayList<String> experience) {
        setName(name);
        setSurname(surname);
        setEmail(email);
        setAdress(road,houseNumber,apartamentNumber,postalCode,town,country);
        setPhoneNumber(phoneNumber);
        setDateOfBirth(dateOfBirth);
        setJobinfo(jobTitle, department, shortInfo);
        setCvNumber(cvNumber);
        setExperience(experience);

        Candidates.add(this);
    }
    public Candidate(//bez job degree i experience
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
                     String jobTitle,
                     String department,
                     String shortInfo,
                     ArrayList<Integer> cvNumber) {
        setName(name);
        setSurname(surname);
        setEmail(email);
        setAdress(road,houseNumber,apartamentNumber,postalCode,town,country);
        setPhoneNumber(phoneNumber);
        setDateOfBirth(dateOfBirth);
        setJobinfo(jobTitle, department, shortInfo);
        setCvNumber(cvNumber);

        Candidates.add(this);
    }

    public Candidate(//bez job degree i apartmentNumber
                     ArrayList<String> name,
                     String surname,
                     String road,
                     int houseNumber,
                     String postalCode,
                     String town,
                     String country,
                     String email,
                     String phoneNumber,
                     LocalDate dateOfBirth,
                     String jobTitle,
                     String department,
                     String shortInfo,
                     ArrayList<Integer> cvNumber,
                     ArrayList<String> experience) {
        setName(name);
        setSurname(surname);
        setEmail(email);
        setAdress(road,houseNumber,postalCode,town,country);
        setPhoneNumber(phoneNumber);
        setDateOfBirth(dateOfBirth);
        setJobinfo(jobTitle, department, shortInfo);
        setCvNumber(cvNumber);
        setExperience(experience);

        Candidates.add(this);
    }
    public Candidate(//bez job degree i apartmentNumber i experience
                     ArrayList<String> name,
                     String surname,
                     String road,
                     int houseNumber,
                     String postalCode,
                     String town,
                     String country,
                     String email,
                     String phoneNumber,
                     LocalDate dateOfBirth,
                     String jobTitle,
                     String department,
                     String shortInfo,
                     ArrayList<Integer> cvNumber) {
        setName(name);
        setSurname(surname);
        setEmail(email);
        setAdress(road,houseNumber,postalCode,town,country);
        setPhoneNumber(phoneNumber);
        setDateOfBirth(dateOfBirth);
        setJobinfo(jobTitle, department, shortInfo);
        setCvNumber(cvNumber);

        Candidates.add(this);
    }

    // ====================== Funkcje ======================
    public int getAge(){
        return Period.between(
                this.dateOfBirth,
                LocalDate.now())
                .getYears();
    }
    public boolean hasAnyExperience(){
        return !this.experience.isEmpty();
    }

    @Override
    public String toString() {
        String temp = "\n----------------------\n"+this.name.toString();
        temp += " " +this.surname + ", living in " + this.adress.toString();
        temp += "\nemail: " + this.email + "\nphone: " + this.phoneNumber+ "\nborn " + this.dateOfBirth.toString()+ " ("+this.getAge()+")";
        temp += "\nApplyes for job: " + this.jobinfo.toString() + "\nCv's numbers: " + this.cvNumber.toString();
        temp += (this.hasAnyExperience()?"":"\n has experience: " + this.experience.toString());

        return temp;
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
    public Adress getAdress() {
        return adress;
    }
    public void setAdress(
            String road,
            int houseNumber,
            int apartamentNumber,
            String postalCode,
            String town,
            String country) {
        this.adress = new Adress(road, houseNumber, apartamentNumber, postalCode, town, country);
    }
    public void setAdress(
            String road,
            int houseNumber,
            String postalCode,
            String town,
            String country) {
        this.adress = new Adress(road, houseNumber, postalCode, town, country);
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
        if(!phoneNumber.matches("\\+48 ?\\d{3}-\\d{3}-\\d{3}")){
            throw new IllegalArgumentException("Invalid Phone number Format, expected +48 XXX-XXX-XXX");
        }
        this.phoneNumber = phoneNumber;
    }
    public static double getCompanyMinSalary() {
        return CompanyMinSalary;
    }
    public static void setCompanyMinSalary(double companyMinSalary) {
        if(companyMinSalary <= 0){
            throw new IllegalArgumentException("Minimum salary has to be >0");
        }
        CompanyMinSalary = companyMinSalary;
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
    public Job getJobinfo() {
        return jobinfo;
    }
    public void setJobinfo(
            String jobTitle,
            String department,
            String shortInfo,
            String degree) {
        this.jobinfo = new Job(jobTitle,department,shortInfo,degree);
    }
    public void setJobinfo(
            String jobTitle,
            String department,
            String shortInfo) {
        this.jobinfo = new Job(jobTitle,department,shortInfo);
    }
    public List<Integer> getCvNumber() {
        return Collections.unmodifiableList(this.cvNumber);
    }
    public void setCvNumber(ArrayList<Integer> AcvNumber) {
        if(AcvNumber == null || AcvNumber.isEmpty()){
            throw new IllegalArgumentException("Array can't be null or empty");
        }
        if (AcvNumber.size() >3){
            throw new IllegalArgumentException("Maximum length for CvNumbers table is 3");
        }
        for (Integer i : AcvNumber)
            addCvNumber(i);
    }
    public void removeCvNumber(int index){
        if(this.cvNumber.size() == 1){
            throw new IllegalArgumentException("Available lenght for Candidates CvNumber table is [1..3]");
        } else if (index<0 || index >= this.cvNumber.size()) {
            throw new IllegalArgumentException("Available indexes for Candidates CvNumber table is 0.."+(this.cvNumber.size()-1));
        }
        this.cvNumber.remove(index);
    }
    public void addCvNumber(int number){
        if(this.cvNumber.size()>=3){
            throw new IllegalArgumentException("Table is full (length:"+this.cvNumber.size()+")");
        }
        if(this.cvNumber.contains(number)){
            throw new IllegalArgumentException("This table already has number "+number);
        }
        if(number <= 0) {
            throw new IllegalArgumentException("Number has to be >0");
        }
        this.cvNumber.add(number);
    }
    public List<String> getExperience() {
        return Collections.unmodifiableList(this.experience);
    }
    public void setExperience(ArrayList<String> aexperience) {
        if(aexperience == null || aexperience.isEmpty()){
            throw new IllegalArgumentException("Table has to have any argument or not have any at all");
        }
        for (String s : aexperience) {
            addExperience(s);
        }
    }
    public void removeExperience(int index){
        if(!this.hasAnyExperience()){
            throw new IllegalArgumentException("You can't remove argument from empty table");
        }
        if(index<0 || index>this.experience.size()){
            throw new IllegalArgumentException("You can't remove "+index+" index from table size "+this.experience.size());
        }
        this.experience.remove(index);
    }
    public void addExperience(String exp){
        if(this.experience.contains(exp)){
            throw new IllegalArgumentException("This table already has this experience");
        }
        if(exp == null || exp.isEmpty()){
            throw new IllegalArgumentException("You can't add empty/null experience as argument");
        }
        this.experience.add(exp);
    }
    public static List<Candidate> getCandidates() {
        return Collections.unmodifiableList(Candidates);
    }

    // ------------------ Serializacja -------------------
    public static void writeExtentCandidates(ObjectOutputStream stream) throws IOException{
        stream.writeObject(Candidates);
    }
    public static void readExtentCandidates(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        Candidates = (ArrayList<Candidate>) stream.readObject();
    }
}