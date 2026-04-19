import Models.*;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        // ============== Asocjacja z atrybutem =============
        System.out.println("==============Asocjacja z atrybutem=============");
        // ------------- tworzenie Obiektow -------------
        Company company1 = new Company("Crayon", "Provider od Microsoft software");
        Adress adress1 = new Adress("Domaniewska", 50, 16, "03-336", "Warszawa", "Polska");

        // ------------- tworzenie relacji i Obiektow-------------
        ComAdr ca1 = adress1.createComAdr(company1,adress1, LocalDate.of(2020,1,1), null);
//        ComAdr ca1 = company1.createComAdr(company1,adress1, LocalDate.of(2020,1,1), null);//mozliwe do stworzenia i z klasy Adress i Company

        // ------------- sprawdzenie -------------
        System.out.println(company1.getName()+": "+company1.getComAdr());
        System.out.println(adress1.getRoad()+": "+adress1.getComAdr());
        System.out.println("Polaczenie: "+ ca1.toString()+"\n");


        // ============= Kompozycja =============
        System.out.println("============= Kompozycja =============");

        // ------------- Tworzenie relacji JobOffer -------------
        company1.createJobOffer("Modern Work intern", 5000.0, 0);

        // ------------- Sprawdzenie -------------
        System.out.println(company1.getName()+": "+company1.getJobOffers());
        System.out.println("Polaczenie: "+company1.getJobOffers().get(0).toString());


        // ============== Asocjacja kwalifikowana i zwykla =============
        // ------------- tworzenie Obiektow i relacji -------------
        Candidate candidate1 = new Candidate(
                new ArrayList<>(List.of("Tomasz", "Julian")),
                "Kownacki",
                "Koszykowa",
                86,
                216,
                "02-336",
                "Warszawa",
                "Polska",
                "TJ.Kownacki@wp.pl",
                null,
                LocalDate.of(2000,1,1),
                new ArrayList<>(List.of("ILO im. Wislawy Szymborskiej w Nowym Dworze")),
                null
        );

        // ------------- sprawdzenie -> kwalifikowana -------------
        System.out.println("============== Asocjacja kwalifikowana i zwykla =============");
        System.out.println(candidate1.getName()+", \nCv: " + candidate1.getCvs()+"\nAdress: "+candidate1.getAdresses().get(0).toString());
        System.out.println("------------- Asocjacja kwalifikowana -------------");
        System.out.println("CV: "+ candidate1.getCvs().get("Tomasz_Kownacki_0").getCandidate().getName());
        System.out.println("------------- Asocjacja zwykla -------------");
        System.out.println("Adress: " + candidate1.getAdresses().get(0).getCandidate().getName());
    }
}