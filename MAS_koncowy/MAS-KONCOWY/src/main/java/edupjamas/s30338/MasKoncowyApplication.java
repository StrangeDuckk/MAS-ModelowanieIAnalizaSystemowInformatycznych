package edupjamas.s30338;

import edupjamas.s30338.ENUMS.JobOfferTypeEnum;
import edupjamas.s30338.entity.Wielodziedziczenie.Candidate;
import edupjamas.s30338.entity.Wielodziedziczenie.Employee;
import edupjamas.s30338.entity.Wielodziedziczenie.OurCompanyCandidate;
import edupjamas.s30338.entity.kompozycja.Company;
import edupjamas.s30338.entity.kompozycja.JobOffer;
import edupjamas.s30338.entity.zAtrybutem.Adress;
import edupjamas.s30338.gui.START;
import edupjamas.s30338.repository.*;
import javafx.application.Application;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.Local;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class MasKoncowyApplication {
    public static void main(String[] args) {
        var context = SpringApplication.run(MasKoncowyApplication.class, args);

        Company.setMinCountrySalary(4000.0);

        // ======== wylistowanie wszystkich ofert ==========
        wylistowanieWszystkichOfert(context);

        // ======== wylistowanie wszystkich firm ==========
        wylistowanieWszystkichFirm(context);

        // ======== wylistowanie wszystkich kandydatow ==========
        wylistowanieWszystkichKandydatow(context);

        // ======== wylistowanie aplikacji konkretnego kandydata, po person_id ==========
        wylistowanieKandydataPoId(context,2);

        Application.launch(START.class, args);

    }



    // ==================== dodanie podstawowych danych do bazy JEDNOKROTNIE==============================
//    @Bean
//    CommandLineRunner initData(
//            CandidateRepository candidateRepository,
//            CompanyRepository companyRepository,
//            EmployeeRepository employeeRepository,
//            AdressRepository adressRepository,
//            OurCompanyCandidateRepository ourCompanyCandidateRepository,
//            ApplicationRepository applicationRepository
//    ) {
//        return args -> {
//            Adress cadr1 = new Adress(
//                    "Domaniewska",
//                    39,
//                    0,
//                    "02-672",
//                    "Warszawa",
//                    "Polska"
//            );
//            Company company1 = Company.createCompanyNormalOffice(
//                    "Crayon",
//                    4200.0,
//                    "IT",
//                    "Zbigniew Borgosz",
//                    1200000.0,
//                    cadr1,
//                    LocalDate.of(2022, 9, 1),
//                    null
//            );
//
//
//            Adress cadr2 =  new Adress(
//                    "Odrodzenia",
//                    26,
//                    0,
//                    "42-504",
//                    "Będzin-Łagisza",
//                    "Polska"
//            );
//            Company company2 = Company.createCompanyNormalAndStateOffice(
//                    "MATBUD",
//                    4000.0,
//                    "Budowlanka",
//                    "Mateusz Włodarski",
//                    100000,
//                    "Mazowieckie",
//                    "Polska",
//                    "Minister infrastruktury",
//                    false,
//                    cadr2,
//                    LocalDate.of(2002, 1, 9),
//                    null
//            );
//            JobOffer jobOffer1 = company1.addActiveJobOffer(
//                    "Senior Administrator Chmury",
//                    "IT",
//                    "Serior",
//                    15000,
//                    LocalDate.of(2026, 6, 27),
//                    3
//            );
//            JobOffer jobOffer2 = company1.addActiveJobOffer(
//                    "Junior Java Developer",
//                    "IT",
//                    "Junior",
//                    5000,
//                    LocalDate.of(2026, 7, 1),
//                    20
//            );
//            JobOffer jobOffer3 = company1.addFinishedJobOffer(
//                    "Specjalista HR",
//                    "HR",
//                    "Senior",
//                    10000,
//                    LocalDate.of(2026, 5, 1),
//                    3
//            );
//            JobOffer jobOffer4 = company2.addActiveJobOffer(
//                    "Brukarz",
//                    "Budowlanka",
//                    "Junior",
//                    4000,
//                    LocalDate.of(2026, 7, 1),
//                    10
//            );
//
//            Adress canadr1 = new Adress(
//                    "Płocka",
//                    59,
//                    1,
//                    "09-100",
//                    "Płońsk",
//                    "Polska"
//            );
//            Candidate candidate1 = new Candidate(
//                    List.of("Zofia", "Maria"),
//                    "Wrońska",
//                    "zofia.wronska@gmail.com",
//                    null,
//                    LocalDate.of(2004, 1, 1),
//                    List.of(canadr1),
//                    null,
//                    3,
//                    "Informatic"
//            );
//            canadr1.setPerson(candidate1);
//
//            Adress empadr1 = new Adress(
//                    "Kazimierska",
//                    12,
//                    5,
//                    "00-123",
//                    "Krakow",
//                    "Polska"
//            );
//            Employee employee1 = new Employee(
//                    List.of("Anna"),
//                    "Wesolowska",
//                    "aw@wp.pl",
//                    "+48 555-555-555",
//                    LocalDate.of(1990, 5, 16),
//                    List.of(empadr1),
//                    null,
//                    4040.4,
//                    "Hr specialist"
//            );
//            empadr1.setPerson(employee1);
//
//            Adress canadr2 = new Adress(
//                    "Towarowa",
//                    156,
//                    1,
//                    "09-145",
//                    "Zaluski",
//                    "Polska"
//            );
//            Candidate candidate2 = new Candidate(
//                    List.of("Julia"),
//                    "Zduńska",
//                    "juliaz@gmail.com",
//                    null,
//                    LocalDate.of(2005, 10, 17),
//                    List.of(canadr2),
//                    null,
//                    4,
//                    "Informatics"
//            );
//            OurCompanyCandidate ourCompanyCandidate1 = new OurCompanyCandidate(
//                    candidate2,
//                    5000,
//                    "It intern",
//                    "chcialabym aplikowac na stanowisko ...."
//            );
//            canadr2.setPerson(ourCompanyCandidate1);
//
//            // Aplikacje kandydatow
//            edupjamas.s30338.entity.kwalifikowana.Application application1 = new edupjamas.s30338.entity.kwalifikowana.Application(
//                    LocalDate.of(2026,1,6),
//                    17000.0,
//                    candidate1,
//                    jobOffer1,
//                    List.of("PJATK"),
//                    List.of("1 rok jako google cloud administrator"),
//                    null
//            );
//            edupjamas.s30338.entity.kwalifikowana.Application application2 = new edupjamas.s30338.entity.kwalifikowana.Application(
//                    LocalDate.of(2026,5,2),
//                    12000.0,
//                    ourCompanyCandidate1,
//                    jobOffer1,
//                    List.of("PW"),
//                    null,
//                    null
//            );
//            edupjamas.s30338.entity.kwalifikowana.Application application3 = new edupjamas.s30338.entity.kwalifikowana.Application(
//                    LocalDate.of(2026,6,6),
//                    5000.0,
//                    ourCompanyCandidate1,
//                    jobOffer4,
//                    List.of("PW"),
//                    List.of("praca wakacyjna jako brukarz"),
//                    null
//            );
//
//            // =========== zapis do bazy ===============
//            adressRepository.save(cadr1);
//            adressRepository.save(cadr2);
//            adressRepository.save(empadr1);
//            adressRepository.save(canadr1);
//
//            companyRepository.save(company1);
//            companyRepository.save(company2);
//            employeeRepository.save(employee1);
//
//            candidateRepository.save(candidate1);
//            candidateRepository.save(candidate2);
//            ourCompanyCandidateRepository.save(ourCompanyCandidate1);
//            applicationRepository.save(application1);
//            applicationRepository.save(application2);
//            applicationRepository.save(application3);
//        };
//
//    }
        //todo zawsze przy zamykaniu zapisywanie stanu do bazy, przy przerwaniu tez
    //todo zawsze po zapisaniu formularza uaktualinienie do bazy

    private static void wylistowanieWszystkichOfert(ConfigurableApplicationContext context) {
        System.out.println("======== wylistowanie wszystkich ofert ==========");

        JobOfferRepository repo = context.getBean(JobOfferRepository.class);

        repo.findAll().forEach(System.out::println);
        System.out.println("======== ============================ ==========");
    }
    private static void wylistowanieWszystkichFirm(ConfigurableApplicationContext context) {
        System.out.println("======== wylistowanie wszystkich firm ==========");

        CompanyRepository repo = context.getBean(CompanyRepository.class);

        repo.findAll().forEach(System.out::println);
        System.out.println("======== ============================ ==========");
    }
    private static void wylistowanieWszystkichKandydatow(ConfigurableApplicationContext context) {
        System.out.println("======== wylistowanie wszystkich kandydatow ==========");

        CandidateRepository repo = context.getBean(CandidateRepository.class);

        repo.findAll().forEach(System.out::println);
        System.out.println("======== ============================ ==========");

    }
    private static void wylistowanieKandydataPoId(ConfigurableApplicationContext context, long person_id) {
        System.out.println("======== wylistowanie kandydata po ID:"+ person_id +" ==========");

        CandidateRepository repo = context.getBean(CandidateRepository.class);

        repo.findById(person_id)
                        .ifPresentOrElse(
                                System.out::println,
                                ()-> System.out.println("Nie znalezniono kandydata o Id: "+person_id)
                        );
        System.out.println("======== ============================ ==========");

    }

    // ============= zmiana stanu oferty pracy z Active na Finished ===============
    @Bean
    CommandLineRunner jobOfferStatusUpdater(JobOfferRepository jobOfferRepository){
        return args -> {
            LocalDate today = LocalDate.now();

            List<JobOffer> offers = jobOfferRepository.findAllWithApplications();

            for (JobOffer offer: offers) {
                // ----------- Finished ----------------
                if(offer.getJobOfferTypeENUM() == JobOfferTypeEnum.ACTIVE){
                    int liczbaOdpowiedziOdKandydatow = offer.getApplications().size();

                    if(offer.getPlannedFinish().isBefore(today)){
                        offer.changeToFinished(today, liczbaOdpowiedziOdKandydatow);
                    }
                }

                // ----------- Archived ----------------
                if(offer.getJobOfferTypeENUM() == JobOfferTypeEnum.FINISHED){
                    if(offer.getEndDate().plusYears(1).isBefore(today)){
                        offer.changeToArchived(today);
                    }
                }
            }

            jobOfferRepository.saveAll(offers);
        };
    }

}


/*
todo FX
 */