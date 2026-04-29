public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}

/*
 * kahoot
 *
 * disjoint -> abstract w rodzicu, dwie podklasy za pomoca extends
 * ovverride, metoda w rodzicu ktora ma abstract, w podklasach wykorzystywana
 * metody biznesowe, one musza miec jakis sens, nie moga tylko wypisywac, zwrocic cos ze pomoca return
 *
 * wielodziedziczenie:
 * metody z interfacami i atrybuty, za pomoca interface robi sie wiele dziedziczen
 *
 * enumSet w overlapping-> kolekcja enumow w predefiniowanych stringach, nazwy rol
 * np pracownik -> manager,kasjer,zaopatrzeniowiec
 *
 * wieloaspektowe:
 * asocjacja z kompozycja do klasy abstrakcyjnej
 *
 * dynamic:
 * zastapienie kompozycja albo smart object copying
 *
 * pzy overlapping -> metody z rola nie moga byc uzywane jesli nie mamy roli
 * np szef ma metode usun pracownika i nie mozna uzyc tej metody z pozycji pracownika
 * */

/*
nie trzeba serializacji


przynajmniej dwie podklasy wszedzie

uml help:
disjoint:
Abstract w nadklasie
Metoda w nasklasie tez abstract i przekazana do nizszych klas
I ta metoda powinna cos tam obliczac a nie być tylko zwracajaca

overlapping: -> ktos jest na raz obydwiema klasami np student i pracownik, ktore sa pod person
Splaszczenie hieerarchii, zaleca ze jeśli nie mamy za duzo asocjacji w podklasach
Czyli bierzemy wszystkie podklasy i wrzucamy ja w jedna, rozroznienie które atrybuty sa ktorej roli i ktore metody sa ktorej roli, tutaj wpadaja ROLE
- EnumSet:
Setter dla np. Pola szefa wykonywane tylko przez obiekt który ma role szef, w kazdym setterze ify sprawdzajace role i to przez enumSet nie klasowy, bo każdy obiekt ma swój wlasny role
Można ale nie trzeba metody która dodaje role, jeśli już by się ja robilo dobrze, nasz nie wymaga zmiany roli
- kompozycja:
przez asocjacje, od razu przy tworzeniu klasy macierzystej tworzy sie podklase
Wazne żeby najpeirw tworzyc person a potem podklasy

wielodziedziczenie:
Zwykle dziedziczenie u gory (disjoint)
I stworzenie dziedziczenia dla ponizszej klasy,
Można z jednej klasy zrobic dziedziczenie i z drugiej jako interface tak jak nizej
- Polaczenei interface z klasa przez kropkowana linie, strzalka ta sama,
Trzeba utworztyc interface na podstawie klasy i ta gorna kalsa tez musi go zaimplementowac
- Przez atrybut powinno być pobranie obiektu z gory,
Druga opcja to polaczenie 1-1 z druga klasa i wtedy mamy przekazane wszystkie atrybuty tej lodzi
i to dla bezposredniego dziedziczenie pol
To się robi dla bezposredniego dziedziczenia pol i wtedy copy-paste atrybutow z boat albo pole boat
Nadal robi się interface nawet jeśli robimy asocjacje 1-1

Wieloaspektowe:
podobne do wielodziedziczenia ale
- 1 opcja -> kompozycja i 2 oddzielne dziedziczenia
Dodanie klasy która będzie lacznikiem, można trzymac tam atrybuty ale nie trzeba
W momencie utworzenia person, trzeba tez utworzyc obiekt type
- 2 opcja -> splaszczenie hierarchii
Wyrzucenie calej asocjacji i zapakowanie do person, typ będzie bezposrednio zaimplementowany w klasie person
Wtedy tez wchodza enumSety

//todo dynamic przez splaszczenie
Dynamiczne:
Dziedziczenie zwykle z pewnymi metodami np. SmartObjectCopying. W mainie utworzony student może zostac studentem
Można zrobic tak ze np. Student może przejsc tylko na pracownika ale pracownik nie zostanie już studentem nigdy, ale można tez cykliczne tylko musi być poprawne wszystko (usuwanie obiektow z ekstensji)
- Zwykle dziedziczenie, zrobienie metod changeToEmployee()/changeToStudent() i stworzenie na tej podstawie atrybutow drugiej osoby
Trzeba pamietac wtedy o usuniecie obiektu student z ekstensji
Zwykle kopiowanie
Ekstensje tylko w podklasach ALBO ekstensja tylko w nadklasie
Lepiej stworzyc w podklasach bo inaczej jest misz masz, mozna zapanowac ale nie idealne rozwiazanie
- XOR: Albo jeden albo drugi obiekt, nigdy dwa na raz, najzwyklejsza kompozycja dla kazdej z dwoch podklas
warunek jest taki, ze jak zmiana employee to nie mozliwe zeby zrobic dwa pola w person na raz i employee i student
trzeba sie upewnic ze przy przechodzeniu z jednej na druga to ta pierwsza jest nullem
- splaszczenie hierarchii: troche leniwa opcja, dyskryminator jako enum,
sprawdzenie rol przy wykonywaniu metod itd
nie enumSet tylko pojedyncze pole z enumem


 */