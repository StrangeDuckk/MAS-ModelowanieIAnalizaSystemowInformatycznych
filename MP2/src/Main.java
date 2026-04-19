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
        ComAdr ca1 = ComAdr.create(
                company1,
                adress1,
                LocalDate.of(2020,1,1),
                null
        );

        // ------------- sprawdzenie -------------
        System.out.println(company1.getName()+": "+company1.getComAdr());
        System.out.println(adress1.getRoad()+": "+adress1.getComAdr());
        System.out.println("Polaczenie: "+ ca1+"\n");


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
/*
nie trzeba robic ekstencsji ale przy kwalifikacyjnej trzeba zrobic usuwanie przy usuwaniu klasy

dzialanie na referencjach a nie oryginalach
jak sie uda to trwalosc (czyli zapisanie do binarki) ale nie bedzie oceniane

setterow i getterow i tostringow nie trzba pokazywac na diagramie
+ metod np potrzebnych do przechodzenia pomiedzy klasami nie trzeba
wszystko co nie wplywa na logike do historyjki to nie trzeba robic metod

metody biznesowe (logika biznesowa) -> metoda statyczna, nie robila nic z konstrukcja
ale obliczala cos i zwracala na podstawie ekstensji -> zawsze trzeba je pokazac

Kahoot:

asocjacja kwalifikowana mapa/slownik

kompozycja nie moze byc dzielona z innymi klasami np jedna sala w budynku B nie moze byc dzielona tez z budynkiem A

dodawanie 1>*
(zdjecie company employee), chcemy polaczyc je, tylko za pierwszym razem sie w ten sposob utworza. c1-*e.
asocjacja zadziala tylko za 1 razem, jak dodaje sie do pracownika inne employee to trzeba usunac go z listy w company
przez metode w company ktora usuwa pracownikow

referencja zwrotna -> jak tworzymy polaczenie to trzeba sie upewnic ze po dwoch stronach obiektu musi byc referencja

asocjacja z atrybutem -> musi miec 2 pola z referencjami do tabel pomiedzy ktorymi jest i potem
atrybuty z dodatkowymi informacjami

wiele do wiele -> drugie zdjecie. addCompany powinien byc public. takich rzeczy jak na zdjeciu nei chcemy

kompozycja -> 3 zdjecie -> sprawdzenei czy building jest nullem. w building jest rooms i to jest
zewnetrzna ekstensja sprawdzajaca jakie pokoje sa w building
kompozycja w ramach oddzielnej klasy to musi byc konstruktor prywatny. tworzenie tylko przez budynek

ogolnie: z obu stron musi byc mozliwosc tworzenia polaczen i ify zeby zapobiec np duplikatom




z UML Help:

z atrybutem -> asocjacja wiele-wiele, wiec 4 referencje, w kazdej klasie do kazdej i nie ma dodawanie dodatkowych polaczen do niej
jak usuwanie to po wylaczeniu polaczen po prostu zgubienei ich, zeby garbage collector je zebral


kwalifikowana -> slownik/hash mapa: w miare sensowne klucze sa potrzebne, nie uzywac ID

kompozycji nie robic jako klasa wewnetrzna

ONEX
 */
//todo dzialanie na referencjach a nei oryginalach