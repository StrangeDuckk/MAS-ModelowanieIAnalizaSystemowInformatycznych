import java.util.ArrayList;
import java.util.List;

public class Departments {
    private Company company;
    private Adress adress;

    private static List<Adress> Departments = new ArrayList<>();
    private String name;
    private int numberOfEmployees;
    private String fields;//ex: It, Logistic, Accountancy, ...

    private Departments(String name, int numberOfEmployees, String fields){
        //todo konstruktor z setterow
        this.name = name;
        this.numberOfEmployees = numberOfEmployees;
        this.fields = fields;
    }

    //to metody prywatne (wykonywane z company albo adress):
    // todo metoda na remove all connections
    //todo dodawanie polaczen ze sprawdzeniem czy maja poprawne wartosci
    //todo referencje zwrotne z powiazanych obiektor do inicjowania klasy asocjacyjnej

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


Asocjacja z atrybutem.
todo 8.1. Jej zastosowanie ma sens najczęściej w przypadku relacji wiele-do-wiele.
todo 8.2. Należy wprowadzić nową klasę asocjacyjną zawierającą atrybuty asocjacji i
dwie wymagane relacje wiele-do-jednego z powiązanymi klasami.
todo 8.3. Powiązane klasy nie mają bezpośredniego powiązania. Obie powinny posiadać asocjacje
do klasy pośredniczącej.
todo 8.4. W czasie inicjalizacji obiektu klasy asocjacyjnej należy upewnić się, że wymagane
atrybuty i referencje do powiązanych obiektów mają prawidłowe wartości. Należy ustawić
referencje zwrotne z powiązanych obiektów do inicjalizowanego obiektu klasy asocjacyjnej.
todo 8.5. Nie jest wymagana możliwość zmiany powiązań w obiekcie klasy asocjacyjnej. Jeżeli
istnieją tam metody do ustawienia powiązanych obiektów to powinny pozostać prywatne.
todo 8.6. Należy upewnić się, że metody usuwające asocjację z atrybutem usuną wszystkie cztery
referencje pomiędzy powiązanymi obiektami.
 */
}
