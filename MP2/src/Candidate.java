import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class Candidate implements Serializable {
    //todo polaczenia
    private Adress Adress;//info like road, house number
    private Map<String, CV> Cvs = new HashMap<>();//Name_Surname_number
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
            LocalDate dateOfBirth) {
        setName(name);
        setSurname(surname);
        setEmail(email);
        setAdress(road,houseNumber,apartamentNumber,postalCode,town,country);
        setPhoneNumber(phoneNumber);
        setDateOfBirth(dateOfBirth);


        Candidates.add(this);
    }


    // ====================== Funkcje ======================

    @Override
    public String toString() {
        String temp = "\n----------------------\n"+this.name.toString();
        temp += " " +this.surname + ", living in " + this.Adress.toString();
        temp += "\nemail: " + this.email + "\nphone: " + this.phoneNumber+ "\nborn " + this.dateOfBirth.toString()+ " ("+
                Period.between(
                        this.dateOfBirth,
                        LocalDate.now())
                .getYears()+")";
//        temp += "\nCv's numbers: " + this.cvNumber.toString();

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
        return Adress;
    }
    public void setAdress(
            String road,
            int houseNumber,
            int apartamentNumber,
            String postalCode,
            String town,
            String country) {
        //todo zrobic to poprawnie z relacja
        this.Adress = new Adress(road, houseNumber, apartamentNumber, postalCode, town, country);
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
//    public List<Integer> getCvNumber() {
//        return Collections.unmodifiableList(this.cvNumber);
//    }
//    public void setCvNumber(ArrayList<Integer> AcvNumber) {
//        if(AcvNumber == null || AcvNumber.isEmpty()){
//            throw new IllegalArgumentException("Array can't be null or empty");
//        }
//        if (AcvNumber.size() >3){
//            throw new IllegalArgumentException("Maximum length for CvNumbers table is 3");
//        }
//        for (Integer i : AcvNumber)
//            addCvNumber(i);
//    }
//    public void removeCvNumber(int index){
//        if(this.cvNumber.size() == 1){
//            throw new IllegalArgumentException("Available lenght for Candidates CvNumber table is [1..3]");
//        } else if (index<0 || index >= this.cvNumber.size()) {
//            throw new IllegalArgumentException("Available indexes for Candidates CvNumber table is 0.."+(this.cvNumber.size()-1));
//        }
//        this.cvNumber.remove(index);
//    }
//    public void addCvNumber(int number){
//        if(this.cvNumber.size()>=3){
//            throw new IllegalArgumentException("Table is full (length:"+this.cvNumber.size()+")");
//        }
//        if(this.cvNumber.contains(number)){
//            throw new IllegalArgumentException("This table already has number "+number);
//        }
//        if(number <= 0) {
//            throw new IllegalArgumentException("Number has to be >0");
//        }
//        this.cvNumber.add(number);
//    }
    public static List<Candidate> getCandidates() {
        return Collections.unmodifiableList(Candidates);
    }
}
/*
6. Dla każdej asocjacji należy utworzyć metody w obu powiązanych klasach, które umożliwią:
todo 6.1. Pobranie powiązanego obiektu lub obiektów (getter). W przypadku kolekcji należy
zapewnić, że nie będzie ona modyfikowana poza klasą, podobnie jak w przypadku ekstensji
lub atrybutu powtarzalnego.
todo 6.2. Utworzenie nowego powiązania. Metoda ta powinna automatycznie ustawić referencję
zwrotną.
todo 6.3. Usunięcie istniejącego powiązania. Metoda ta powinna automatycznie usunąć referencję
zwrotną.
todo 6.4. Jeżeli istnieje metoda do zastąpienia istniejącego powiązania z na inny obiekt, należy
upewnić się, że obie referencje ze starego powiązania zostaną usunięte przed utworzeniem
nowej relacji.

 */