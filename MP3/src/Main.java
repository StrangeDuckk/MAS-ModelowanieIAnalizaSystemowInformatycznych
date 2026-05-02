import Normalne.B2BContract;
import Normalne.Contract;
import Normalne.FullTimeContract;
import Normalne.InternshipContract;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // ====================== Normalne dziedziczenie ======================
        {
            // --------------  ----------------
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

            for (Contract c:  Contract.getContractList()) {
                System.out.println("TYP: "+c.getContractType()+"\n" +
                        "Brutto: " +c.countBrutto() + "\n" +
                        c.toString()+"\n----------------------------");
            }
        }

        // ====================== Dynamic ======================

        // ====================== Overlapping ======================

        // ====================== Wieloaspektowe ======================

        // ====================== Wielodziedziczenie ======================

    }
}