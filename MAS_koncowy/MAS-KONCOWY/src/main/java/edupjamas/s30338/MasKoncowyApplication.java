package edupjamas.s30338;

import edupjamas.s30338.entity.Wielodziedziczenie.Candidate;
import edupjamas.s30338.entity.Wielodziedziczenie.Employee;
import edupjamas.s30338.entity.Wielodziedziczenie.OurCompanyCandidate;
import edupjamas.s30338.entity.kompozycja.Company;
import edupjamas.s30338.entity.kompozycja.JobOffer;
import edupjamas.s30338.entity.kwalifikowana.CV;
import edupjamas.s30338.entity.zAtrybutem.Adress;
import edupjamas.s30338.gui.START;
import edupjamas.s30338.repository.*;
import javafx.application.Application;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class MasKoncowyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MasKoncowyApplication.class, args);
        Company.setMinCountrySalary(4000.0);
        //todo napisanie wszystkich setterow samemu z walidacja

        // ==================== dodanie podstawowych danych do bazy ==============================


        Application.launch(START.class, args);

    }

    @Bean
    CommandLineRunner init(
            CandidateRepository candidateRepository,
            CompanyRepository companyRepository,
            EmployeeRepository employeeRepository,
            AdressRepository adressRepository,
            OurCompanyCandidateRepository ourCompanyCandidateRepository
    ) {
        return args -> {
            Adress cadr1 = new Adress(
                    "Domaniewska",
                    39,
                    0,
                    "02-672",
                    "Warszawa",
                    "Polska"
            );
            Company company1 = Company.createCompanyNormalOffice(
                    "Crayon",
                    4200.0,
                    "IT",
                    "Zbigniew Borgosz",
                    1200000.0,
                    cadr1,
                    LocalDate.of(2022, 9, 1),
                    null
            );
            Adress cadr2 =  new Adress(
                    "Odrodzenia",
                    26,
                    0,
                    "42-504",
                    "Będzin-Łagisza",
                    "Polska"
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
                    cadr2,
                    LocalDate.of(2002, 1, 9),
                    null
            );
            JobOffer jobOffer1 = company1.addActiveJobOffer(
                    "Senior Administrator Chmury",
                    "IT",
                    "Serior",
                    15000,
                    LocalDate.of(2026, 6, 27),
                    3
            );
            JobOffer jobOffer2 = company1.addActiveJobOffer(
                    "Junior Java Developer",
                    "IT",
                    "Junior",
                    5000,
                    LocalDate.of(2026, 7, 1),
                    20
            );
            JobOffer jobOffer3 = company1.addFinishedJobOffer(
                    "Specjalista HR",
                    "HR",
                    "Senior",
                    10000,
                    LocalDate.of(2026, 5, 1),
                    3
            );
            JobOffer jobOffer4 = company2.addActiveJobOffer(
                    "Brukarz",
                    "Budowlanka",
                    "Junior",
                    4000,
                    LocalDate.of(2026, 7, 1),
                    10
            );

            Adress canadr1 = new Adress(
                    "Płocka",
                    59,
                    1,
                    "09-100",
                    "Płońsk",
                    "Polska"
            );
            Candidate candidate1 = new Candidate(
                    List.of("Zofia", "Maria"),
                    "Wrońska",
                    "zofia.wronska@gmail.com",
                    null,
                    LocalDate.of(2004, 1, 1),
                    List.of(canadr1),
                    null,
                    3,
                    "Informatic"
            );
            canadr1.setPerson(candidate1);

            Adress empadr1 = new Adress(
                    "Kazimierska",
                    12,
                    5,
                    "00-123",
                    "Krakow",
                    "Polska"
            );
            Employee employee1 = new Employee(
                    List.of("Anna"),
                    "Wesolowska",
                    "aw@wp.pl",
                    "+48 555-555-555",
                    LocalDate.of(1990, 5, 16),
                    List.of(empadr1),
                    null,
                    4040.4,
                    "Hr specialist"
            );
            empadr1.setPerson(employee1);

            Adress canadr2 = new Adress(
                    "Towarowa",
                    156,
                    1,
                    "09-145",
                    "Zaluski",
                    "Polska"
            );
            Candidate candidate2 = new Candidate(
                    List.of("Julia"),
                    "Zduńska",
                    "juliaz@gmail.com",
                    null,
                    LocalDate.of(2005, 10, 17),
                    List.of(canadr2),
                    null,
                    4,
                    "Informatics"
            );
            OurCompanyCandidate ourCompanyCandidate1 = new OurCompanyCandidate(
                    candidate2,
                    5000,
                    "It intern",
                    "chcialabym aplikowac na stanowisko ...."
            );
            canadr2.setPerson(ourCompanyCandidate1);

            // =========== zapis do bazy ===============
            adressRepository.save(cadr1);
            adressRepository.save(cadr2);
            adressRepository.save(empadr1);
            adressRepository.save(canadr1);
//            adressRepository.save(canadr2);

            companyRepository.save(company1);
            companyRepository.save(company2);

            candidateRepository.save(candidate1);
            candidateRepository.save(candidate2);

            employeeRepository.save(employee1);

            ourCompanyCandidateRepository.save(ourCompanyCandidate1);

        };

    }

        //todo wypisac wszystko

        //todo zdecydowac co z usuwaniem, czy tworzyc metody czy nie wgl
        //todo zawsze przy zamykaniu zapisywanie stanu do bazy, przy przerwaniu tez
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