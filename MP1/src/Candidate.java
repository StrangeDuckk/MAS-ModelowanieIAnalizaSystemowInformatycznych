import java.util.ArrayList;
import java.util.Date;

public class Candidate {
    private static ArrayList<Candidate> Candidates = new ArrayList<>();
    //todo private, jedyna metoda modyfikujaca ekstensje to metoda usuwajaca obiekt z ekstensji
    //+ wtedy usuniecie z pliku bin

    private static double CompanyMinSalary;

    private String name;
    private String surname;
    private Adress adress;
    private String email; //todo regex
    private String phoneNumber;
    private Date dateOfBirth;
    private Job jobinfo;
    private ArrayList<Integer> cvNumber;
    private ArrayList<Experience> experience;

    //todo przy nullowalnych trzeba z duzej czyli Integer a nie int, zeby mogl przyjac null

    public Candidate(
            String name,
            String surname,
            Adress adress,
            String email,
            String phoneNumber,
            Date dateOfBirth,
            Job jobinfo,
            ArrayList<Integer> cvNumber,
            ArrayList<Experience> experience) {

        //todo wszystko dodawac przez metody setter i getter

        //w jaki sposob zrobic atrybut powtarzalny z ograniczeniem gornym
        // -> przez tablica.size() == x to zwrocic informacje nie trzeba wyjatku

        //w jaki sposob zrobic atrybut zlozony przez obiekt bez asocjacji
        // -> wewnatrz konstruktora wstawic do obiektu, tak jak myslalam

        //mozemy przeciazac konstruktory


        Candidates.add(this);
        //todo zapis do pliku binarnego
    }
}