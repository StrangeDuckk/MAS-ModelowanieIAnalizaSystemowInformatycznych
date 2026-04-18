import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Adress implements Serializable {
    //todo zrobic zeby firma nie mogla byc w tym samym adresie co candidate
    private Candidate Candidate;
    private List<ComAdr> comAdr = new ArrayList<>();

    private static List<Adress> addresses = new ArrayList<>();
    private String road;
    private int houseNumber;
    private int apartmentNumber;
    private String postalCode;
    private String town;
    private String country;

    // =============== konstruktor ===============
    public Adress(
            String road,
            int houseNumber,
            int apartmentNumber,
            String postalCode,
            String town,
            String country
    ){
        setRoad(road);
        setHouseNumber(houseNumber);
        setApartmentNumber(apartmentNumber);
        setPostalCode(postalCode);
        setTown(town);
        setCountry(country);

        addresses.add(this);
    }

    // ============== relacje =================
    public Candidate getCandidate(){
        return Candidate;
    }
    public void addCandidate(Candidate candidate) {
        if(candidate == null){
            throw new IllegalArgumentException("Candidate cannot be null");
        }
        if(this.Candidate != null && this.Candidate != candidate){
            throw new IllegalArgumentException("Adress already assigned to another Candidate");
        }
        if(this.Candidate != null){
            return;//zatrzymanie referencji zwrotnej
        }

        this.Candidate = candidate;
        candidate.addAdress(this);//referencja zwrotna
    }

    public void removeCandidate(Candidate candidate) {
        if(this.Candidate != candidate){
            throw new IllegalArgumentException("This adress isn't assigned for this candidate");
        }

        this.Candidate = null;
        if(candidate.getAdresses().contains(this)){ //nie dopuszczenie do zapetlenia usuwania
            candidate.removeAdress(this);//referencja zwrotna
        }
    }

    public List<ComAdr> getComAdr(){
        return Collections.unmodifiableList(this.comAdr);
    }
    public void setComAdr(List<ComAdr> comAdrs){
        if(comAdrs == null){
            throw new IllegalArgumentException("Cannot add null comAdr list");
        }
        for(ComAdr ca: comAdrs){
            addComAdr(ca);
        }
    }
    protected void addComAdr(ComAdr comAdr){
        if(comAdr == null){
            throw new IllegalArgumentException("ComAdr cannot be null");
        }
        if(comAdr.getAdress() == this || this.comAdr.contains(comAdr)){
            return; //zakonczenie
        }
        this.comAdr.add(comAdr);
    }
    protected void removeComAdr(ComAdr comAdr) {
        if(comAdr == null){
            throw new IllegalArgumentException("Cannot remove null ComAdr connection");
        }
        if(this.comAdr == null || !this.comAdr.contains(comAdr)){
            return;
        }

        ComAdr tempComAdr = comAdr;
        this.comAdr.remove(comAdr);
        tempComAdr.removeAllConnections();
    }

    // ================= gettery i settery =================


    public String getRoad() {
        return road;
    }
    public void setRoad(String road) {
        if(road == null || road.isEmpty()){
            throw new IllegalArgumentException("Argument has to have any value");
        }
        this.road = road;
    }
    public int getHouseNumber() {
        return houseNumber;
    }
    public void setHouseNumber(int houseNumber) {
        if(houseNumber <=0 ){
            throw new IllegalArgumentException("Argument has to be >0");
        }
        this.houseNumber = houseNumber;
    }
    public int getApartmentNumber() {
        return apartmentNumber;
    }
    public void setApartmentNumber(int apartmentNumber) {
        if(apartmentNumber <0 ){
            throw new IllegalArgumentException("Argument has to be >=0");
        }
        this.apartmentNumber = apartmentNumber;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        if (!postalCode.matches("\\d{2}-\\d{3}")) {
            throw new IllegalArgumentException("Invalid postal code, expected: XX-XXX");
        }
        this.postalCode = postalCode;
    }
    public void setTown(String town) {
        if(town == null || town.isEmpty()){
            throw new IllegalArgumentException("Argument has to have any value");
        }
        this.town = town;
    }
    public String getTown() {
        return town;
    }
    public void setCountry(String country) {
        if(country == null || country.isEmpty()){
            throw new IllegalArgumentException("Argument has to have any value");
        }
        this.country = country;
    }
    public String getCountry() {
        return country;
    }

    public static List<Adress> getAddresses() {
        return Collections.unmodifiableList(addresses);
    }
    @Override
    public String toString() {
        String temp = "Address:" + this.road+" " + this.houseNumber;

        if(this.apartmentNumber > 0){
            temp +="/"+this.apartmentNumber;
        }

        temp += ", "+this.postalCode+" "+this.town+", "+this.country;

        return temp;
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