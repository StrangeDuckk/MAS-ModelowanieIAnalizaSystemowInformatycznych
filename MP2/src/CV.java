import java.util.ArrayList;
import java.util.List;

public class CV {
    private Candidate candidate;

    private static List<CV> Cvs = new ArrayList<>();
    private String cvNumber; //Name_Surname_number
    private List<String> education; //only names
    private List<String> experience;//short info

    public CV(String cvNumber, List<String> education, List<String> experience, Candidate candidate){
        //todo setterowy konstruktor
        this.cvNumber = cvNumber;
        this.education = education;
        this.experience = experience;

        //todo polaczenie przez metode
        this.candidate = candidate;
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

9. Asocjacja kwalifikowana
todo 9.1. Umożliwia “szybki” dostęp do powiązanego obiektu za pomocą kwalifikatora. Najczęściej
jest nim wymagany i unikalny atrybut powiązanej klasy.
todo 9.2. Zamiast zbioru referencji do powiązanych obiektów należy zastosować mapę (słownik),
której kluczem jest kwalifikator a wartością referencja do powiązanego obiektu.
todo 9.3. Jeżeli zmiana atrybutu będącego kwalifikatorem jest możliwa, to należy automatycznie
uaktualnić powiązanie wykorzystujące ten kwalifikator.
 */