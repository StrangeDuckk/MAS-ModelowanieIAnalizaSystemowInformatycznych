import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Adress implements Serializable {
    private static List<Adress> Addresses = new ArrayList<>();
    private String road;
    private int houseNumber;
    private int apartmentNumber;
    private String postalCode;
    private String town;
    private String country;

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

        Addresses.add(this);
    }

    public Adress(//bez apartmentNumber
            String road,
            int houseNumber,
            String postalCode,
            String town,
            String country
    ){
        setRoad(road);
        setHouseNumber(houseNumber);
        setApartmentNumber(0); //dla braku nr mieszkania i poprawnego wypisywania w toString
        //Integer to typ zlozony (nei spelnia wymagan MP1 ale przetrzymuje null)
        setPostalCode(postalCode);
        setTown(town);
        setCountry(country);

        Addresses.add(this);
    }

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
            throw new IllegalArgumentException("Argument has to be >0");
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
        return Collections.unmodifiableList(Addresses);
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

    // ------------------ Serializacja -------------------
    public static void writeExtentAddresses(ObjectOutputStream stream) throws IOException {
        stream.writeObject(Addresses);
    }
    public static void readExtentAddresses(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        Addresses = (ArrayList<Adress>) stream.readObject();
    }
}