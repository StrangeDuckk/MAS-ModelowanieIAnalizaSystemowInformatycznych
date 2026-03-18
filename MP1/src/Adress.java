import java.util.ArrayList;

public class Adress {
    private static ArrayList<Adress> Adresses = new ArrayList<>();
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

        Adresses.add(this);
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
        setPostalCode(postalCode);
        setTown(town);
        setCountry(country);

        Adresses.add(this);
    }

    public static ArrayList<Adress> getAdresses() {
        return Adresses;
    }

    public String getRoad() {
        return road;
    }
    public void setRoad(String road) {
        this.road = road;
    }
    public int getHouseNumber() {
        return houseNumber;
    }
    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }
    public int getApartmentNumber() {
        return apartmentNumber;
    }
    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        if (!postalCode.matches("\\d{2}-\\d{3}")) {
            System.out.println("Invalid postal code, expected: XX-XXX");
        }
        this.postalCode = postalCode;
    }
    public void setTown(String town) {
        this.town = town;
    }
    public String getTown() {
        return town;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    @Override
    public String toString() {
        String temp = "Adress:" + this.road+" " + this.houseNumber;

        if(this.apartmentNumber > 0){
            temp +="/"+this.apartmentNumber;
        }

        temp += ", "+this.postalCode+" "+this.town+", "+this.country;

        return temp;
    }
}