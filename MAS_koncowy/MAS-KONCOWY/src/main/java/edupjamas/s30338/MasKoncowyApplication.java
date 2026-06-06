package edupjamas.s30338;

import edupjamas.s30338.gui.START;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MasKoncowyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MasKoncowyApplication.class, args);

        Application.launch(START.class, args);

        //todo ustawienie company.minCountrysalary
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