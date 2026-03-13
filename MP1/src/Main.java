import java.util.Scanner;

/**
 * MP1: kandydat do pracy na konkretne stanowisko,
 * system dla HR'ów do zarzadzania kandydatami
 *
 * */

public class Main {
    public static void main(String[] args) {
        System.out.println("===== Poczatek MP1 MAS =====\n");

        Scanner scanner = new Scanner(System.in);
        String hrInput = "";

        boolean hrSystem = true;
        String komunikacjaTekst = "What would you like to do? Insert number:\n" +
                "1. Add new Candidate,\n" +
                "2. Select Candidate from previously added Candidates,\n" +
                "3. Print all added Candidates,\n...";

        do {
            System.out.println(komunikacjaTekst);

            hrInput = scanner.nextLine();

            switch (hrInput){
                case "q":
                    hrSystem = false;
                    break;
                default:
                    System.out.println("Not recognized input");
            }

        } while(hrSystem);

    }
}


//todo wymagania:
// ekstensja klasy
// ekstensja zabezpieczenie i trwalosc
// atrybut zlozony
// atrybut opcjonalny
// atrybut powtarzalny
// atrybut klasowy
// atrybut pochodny
// metoda klasowa
// przeciazenie, przesloniecie