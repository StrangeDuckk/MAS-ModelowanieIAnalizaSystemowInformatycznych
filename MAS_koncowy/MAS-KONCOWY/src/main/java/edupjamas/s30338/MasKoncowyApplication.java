package edupjamas.s30338;

import edupjamas.s30338.entity.kompozycja.Company;
import edupjamas.s30338.entity.kompozycja.JobOffer;
import edupjamas.s30338.entity.zAtrybutem.Adress;
import edupjamas.s30338.gui.START;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class MasKoncowyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MasKoncowyApplication.class, args);
        Company.setMinCountrySalary(4000.0);

        // ==================== dodanie podstawowych danych do bazy ==============================

        Company company1 = Company.createCompanyNormalOffice(
                "Crayon",
                4200.0,
                "IT",
                "Zbigniew Borgosz",
                1200000.0,
                new Adress(
                        "Domaniewska",
                        39,
                        0,
                        "02-672",
                        "Warszawa",
                        "Polska"
                ),
                LocalDate.of(2022,9,1),
                null
        );
        Company company2 = Company.createCompanyNormalAndStateOffice(
                "MATBUD",
                4000.0,
                "Budowlanka",
                "Mateusz Włodarski",
                100000,
                "Mazowieckie",
                "Polska",
                "Minister infrastruktury",
                false,
                new Adress(
                        "Odrodzenia",
                        26,
                        0,
                        "42-504",
                        "Będzin-Łagisza",
                        "Polska"
                ),
                LocalDate.of(2002,1,9),
                null
        );
        JobOffer jobOffer1 = company1.addActiveJobOffer(
                "Senior Administrator Chmury",
                "IT",
                "Serior",
                15000,
                LocalDate.of(2026,6,27),
                3
        );
        JobOffer jobOffer2 = company1.addActiveJobOffer(
                "Junior Java Developer",
                "IT",
                "Junior",
                5000,
                LocalDate.of(2026,7,1),
                20
        );
        JobOffer jobOffer3 = company1.addFinishedJobOffer(
                "Specjalista HR",
                "HR",
                "Senior",
                10000,
                LocalDate.of(2026,5,1),
                3
        );
        JobOffer jobOffer4 = company2.addActiveJobOffer(
                "Brukarz",
                "Budowlanka",
                "Junior",
                4000,
                LocalDate.of(2026,7,1),
                10
        );

        //todo zrobic obiekty PERSON i ich aplikacje z cvkami
        //todo wypisac wszystko

        Application.launch(START.class, args);

        //todo zdecydowac co z usuwaniem, czy tworzyc metody czy nie wgl
        //todo zawsze przy zamykaniu zapisywanie stanu do bazy, przy przerwaniu tez
    }
}


/*
todo
zmienic na koniec z ustawien w application.properties create-drop na cos innego zeby miec dane

todo klasy- encje -> pola + ID
todo relacje

todo hibernate
todo postgres
todo repozytoria i serwisy
todo FX
todo testy
 */