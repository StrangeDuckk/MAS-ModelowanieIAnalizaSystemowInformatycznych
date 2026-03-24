import java.io.*;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * MP1: kandydat do pracy na konkretne stanowisko,
 * system dla HR'ów do zarzadzania kandydatami
 * */

public class Main {
    public static void main(String[] args) {
        System.out.println("===== Poczatek MP1 MAS =====\n");
        // ------- odczyt z pliku bin jesli istnieje ------
        File extentFile = new File("ekstensjeMP1.bin");

        if (extentFile.exists()) {

            try (ObjectInputStream in = new ObjectInputStream((new FileInputStream(extentFile)))) {

            Candidate.readExtentCandidates(in);
            Adress.readExtentAddresses(in);
            Job.readExtentJobs(in);

            System.out.println("Wczytano ekstnsje z pliku");
            } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
            }
        }
        else
        {
            System.out.println("Brak pliku z ekstensjami -> tworzenie obiektow");

            // ------- tworzenie obiektow -------
            try{
                Candidate c1 = new Candidate(//ze wszystkim
                        autoListaStr("Jan", "Adam"),
                        "Kowalski",
                        "Kwiatowa", 10, 2,
                        "00-123", "Warszawa", "Polska",
                        "jan.kowalski1@example.com",
                        "+48 111-222-333",
                        LocalDate.of(1990, 3, 15),
                        "Java Developer", "IT", "Backend dev", "mid",
                        autoListaInt(1, 2),
                        autoListaStr("Google - Junior - 2 years", "Amazon - Mid - 3 years")
                );

                Candidate c2 = new Candidate(//apartmentNumber=0
                        autoListaStr("Piotr", "Marek"),
                        "Wiśniewski",
                        "Długa", 7,
                        0,
                        "22-333", "Gdańsk", "Polska",
                        "piotr.w@example.com",
                        "+48 333-444-555",
                        LocalDate.of(1985, 12, 1),
                        "DevOps Engineer", "IT", "Cloud & CI/CD", "senior",
                        autoListaInt(4,5),
                        autoListaStr("IBM - Senior - 5 years")
                );

                Candidate c3 = new Candidate( // bez apartment i experience
                        autoListaStr("Kasia"),
                        "Zielińska",
                        "Krótka", 3,0,
                        "33-444", "Poznań", "Polska",
                        "kasia.z@example.com",
                        "+48 444-555-666",
                        LocalDate.of(2000, 5, 10),
                        "QA Tester", "IT", "Manual testing", "junior",
                        autoListaInt(6)
                );

                Candidate c4 = new Candidate(//bez phone number i experience
                        autoListaStr("Jan","Artur"),
                        "Kowalski",
                        "Kwiatowa",
                        12,
                        4,
                        "00-111",
                        "Warszawa",
                        "Polska",
                        "jan.kowalski@gramil.com",
                        null,
                        LocalDate.of(2000,1,1),
                        "Java Developer",
                        "IT",
                        "Backend",
                        "mid",
                        autoListaInt(111,112)
                );

                Candidate c5 = new Candidate(//apartment number = 0
                        autoListaStr("Anna"),
                        "Nowak",
                        "Lipowa",
                        7,0,
                        "11-222",
                        "Kraków",
                        "Polska",
                        "anna.nowak@example.com",
                        "+48 987-654-321",
                        LocalDate.of(2000, 1, 15),
                        "HR Specialist",
                        "HR",
                        "Recruitment",
                        "junior",
                        autoListaInt(113)
                );
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        // ------- dzialanie na obiektach --------
        System.out.println("============== Posiadanie doświadczenia ==============");
        for (Candidate c: Candidate.getCandidates()) {
            System.out.println("Czy " + c.getName() + " posiada doswiadczenie: " + c.hasAnyExperience());
        }

        System.out.println("============== Sprawdzenie wieków kandydatow ==============");
        for (Candidate c: Candidate.getCandidates()){
            System.out.println(c.getName()+" wiek: " + c.getAge());
        }

        System.out.println("============== Pobieranie CV kandydata ==============");

        Candidate c1 = Candidate.getCandidates().get(1);
        System.out.println(c1.getName()+" CV: "+ c1.getCvNumber());


        System.out.println("============== Wypisanie ofert pracy w IT ==============");
        for (Candidate c: Candidate.getCandidates()){
            if(c.getJobinfo().getDepartment().equals("IT")){
                System.out.println("Oferta: " + c.getJobinfo().toString());
            }
        }

        // ------- zapis do pliku binarnego -------
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(extentFile))){
            Candidate.writeExtentCandidates(out);
            Adress.writeExtentAddresses(out);
            Job.writeExtentJobs(out);

            System.out.println("Zapisano ekstensje do pliku");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //---- Automatyczne listy ----
    static ArrayList<String> autoListaStr(String... arg){
        return new ArrayList<>(List.of(arg));
    }
    static ArrayList<Integer> autoListaInt(Integer... args){
        return new ArrayList<>(List.of(args));
    }
}

//zamiast build system intelij to maven

//testy jednostkowe
//prawym> generate> test JUnit to testy jednostkowe
// maly test i na koniec assertEquals(arg1:czego oczekujemy w danej sytuacji, arg2:co nam zwroci ktora metoda)
//assertthrows