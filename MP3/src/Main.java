import Dynamic.JobOffer;
import Normalne.B2BContract;
import Normalne.Contract;
import Normalne.FullTimeContract;
import Normalne.InternshipContract;
import Overlapping.Company;
import Overlapping.NormalOffice;
import Overlapping.StateOffice;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

public class Main {
    public static void main(String[] args) {
        // ====================== Normalne dziedziczenie ======================
        {
            System.out.println("\n====================== NORMALNE ======================\n");
            // -------------- Tworzenie obiektow ----------------
            Contract c1 = new B2BContract(
                    1,
                    LocalDate.of(2000,12,12),
                    100.0,
                    "1234567900"
            );
            Contract c2 = new FullTimeContract(
                    2,
                    LocalDate.now(),
                    8000.0,
                    23
            );
            Contract c3 = new InternshipContract(
                    3,
                    LocalDate.of(2026,5,2),
                    "PJATK",
                    7
            );

            // -------------- Uzycie obiektow ----------------
            for (Contract c:  Contract.getContractList()) {
                System.out.println("TYP: "+c.getContractType()+"\n" +
                        "Brutto: " +c.countBrutto() + "\n" +
                        c.toString()+"\n----------------------------");
            }
        }

        // ====================== Dynamic ======================
        {
            System.out.println("\n====================== DYNAMIC ======================\n");
            // -------------- Tworzenie obiektow ----------------
            // ACTIVE
            JobOffer o1 = JobOffer.createActiveJobOffer(
                    "Java Developer",
                    "IT",
                    "Backend",
                    LocalDate.now().plusMonths(1),
                    5
            );
            // FINISHED
            JobOffer o2 = JobOffer.createFinishedJobOffer(
                    "HR Intern",
                    "HR",
                    "Recruiter",
                    LocalDate.now().minusMonths(1),
                    20
            );

            // -------------- Użycie obiektow ----------------
            System.out.println("ACTIVE -> \n" +
                    "Planned finish: " + o1.getPlannedFinish()+"\n" +
                    "Answers: " + o1.getAnswersFromCandidates()+"\n");
            System.out.println("Finished -> \n" +
                    "End date: " + o2.getEndDate()+"\n" +
                    "Candidates: " + o2.getCandidateNumbers()+"\n");

            // Zmiana stanu
            System.out.println("Before: "+o1.toString());
            o1.changeToFinished(LocalDate.now(), 100);
            System.out.println("AFTER: "+ o1.toString());
        }

        // ====================== Overlapping ======================
        {
            System.out.println("\n====================== OVERLAPPING ======================\n");
            // -------------- Tworzenie obiektow ----------------
            // Normal office
            NormalOffice o1 = new NormalOffice(
                    "IT",
                    "Jan Nowakowski",
                    10000.0
            );
            //State office
            StateOffice s1 = new StateOffice(
                    "Mazowieskie",
                    "Poland",
                    "Trusk",
                    false
            );
            //Company na normal office
            Company c1 = new Company(
                    "TECHSTACK",
                    "Warszawa",
                    List.of("DevOps", "Network administrator"),
                    o1,
                    s1
            );

            // -------------- Użycie obiektow ----------------
            System.out.println("\n"+o1.toString());
            System.out.println("\n"+s1.toString());
            System.out.println("\n"+c1.toString());
        }

        // ====================== Wieloaspektowe ======================
        {
            System.out.println("\n====================== WIELOAPEKTOWE ======================\n");
            // -------------- Tworzenie obiektow ----------------
            // -------------- Użycie obiektow ----------------
        }

        // ====================== Wielodziedziczenie ======================
        {
            System.out.println("\n====================== WIELODZIEDZICZENIE ======================\n");
            // -------------- Tworzenie obiektow ----------------
            // -------------- Użycie obiektow ----------------
        }

    }
}