import java.util.ArrayList;
import java.util.List;

public class Company {
    private List<Departments> Departments = new ArrayList<>();

    private static List<Adress> Companies = new ArrayList<>();
    private String name;
    private String shortInfo;

    public Company(String name, String si){
        //todo zmienic na setterowy konstruktor
        this.name = name;
        this.shortInfo = si;

        //tworzenei przez metody i potem dolaczenie departamentu stworzonego
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


 */