import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class JobOffer {
    private Company company;
    private static List<JobOffer> JobOffers = new ArrayList<>();

    private String jobInfo;
    private Double salary;
    private Integer experienceInYears;//todo dopytac czy integer

    public JobOffer(String jobInfo, Double salary, Integer experienceInYears, Company company){
        //todo konstruktor setterowy
        this.jobInfo = jobInfo;
        this.salary = salary;
        this.experienceInYears = experienceInYears;

        //todo prawidlowo polaczenie przez metode
        this.company = company;
    }
}
/*
6. Dla każdej asocjacji należy utworzyć metody w obu powiązanych klasach, które umożliwią:
todo 6.1. Pobranie powiązanego obiektu lub obiektów (getter). W przypadku kolekcji należy
zapewnić, że nie będzie ona modyfikowana poza klasą, podobnie jak w przypadku ekstensji
lub atrybutu powtarzalnego.
todo 6.2. Utworzenie nowego powiązania. Metoda ta powinna automatycznie ustawić referencję
zwrotną.
todo 6.3. Usunięcie istniejącego powiązania. Metoda ta powinna automatycznie usunąć referencję
zwrotną.
todo 6.4. Jeżeli istnieje metoda do zastąpienia istniejącego powiązania z na inny obiekt, należy
upewnić się, że obie referencje ze starego powiązania zostaną usunięte przed utworzeniem
nowej relacji.

10. Kompozycja
todo 10.1. Stanowi silną relację pomiędzy klasą stanowiącą “całość” i klasą reprezentującą
“część”.
todo 10.2. Obiekt “część” nie może istnieć bez powiązania do obiektu “całość”. W trakcie
inicjalizacji “części” należy stworzyć odpowiednią relację do obiektu “całości”.
todo 10.3. Dany obiekt “części” nie może być współdzielony pomiędzy obiektami “całość”. W
trakcie tworzenia relacji należy upewnić się, że dana “część” nie jest już powiązana z żadnym
innym obiektem “całość”.
todo 10.4. W trakcie usuwania “całości” należy usunąć “część”. W tym celu należy usunąć obiekt
“części” z ekstensji i jego wszystkie powiązania z innymi klasami.
 */
