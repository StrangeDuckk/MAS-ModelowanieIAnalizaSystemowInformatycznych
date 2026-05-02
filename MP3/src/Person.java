import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//todo do sprawdzenia
public abstract class Person {
    private static List<Person> personList = new ArrayList<>();

    private List<String> name;
    private String surname;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;

    // ===================== KONSTRUKTOR =======================
    protected Person(List<String> name, String surname, String email, String phoneNumber, LocalDate dateOfBirth) {
        setName(name);
        setSurname(surname);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        setDateOfBirth(dateOfBirth);

        personList.add(this);
    }

    // ===================== METODY =======================
    public abstract String getCurrentOccupation();

    // ===================== SETTERY =======================
    public void setName(List<String> Aname) {
        if(Aname == null||Aname.isEmpty()){
            throw new IllegalArgumentException("Array can't be null or empty");
        }
        if(Aname.size()>2){
            throw new IllegalArgumentException("Maximum length for Candidates names table is 2");
        }
        this.name = new ArrayList<>();
        for (String s : Aname) {
            addName(s);
        }
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
    public void setSurname(String surname) {
        if(surname == null || surname.isEmpty()) {
            throw new IllegalArgumentException("Argument has to have any value");
        }
        this.surname = surname;
    }
    public void setEmail(String email) {
        if(!email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")){
            throw new IllegalArgumentException("Invalid Email Format, expected X...X@X...X.X...X ");
        }
        this.email = email;
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
    public void setDateOfBirth(LocalDate dateOfBirth) {
        if(dateOfBirth == null){
            throw new IllegalArgumentException("Candidate has to have any birth date");
        }
        if (dateOfBirth.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Candidate has to be outside his mother :)");
        }
        this.dateOfBirth = dateOfBirth;
    }

    // ===================== GETTERY =======================
    public static List<Person> getPersonList() {
        return Collections.unmodifiableList(personList);
    }
    public List<String> getName() {
        return Collections.unmodifiableList(name);
    }
    public String getSurname() {
        return surname;
    }
    public String getEmail() {
        return email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    // ===================== TOSTRING =======================

    @Override
    public String toString() {
        return "Person{" +
                "name=" + name +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
