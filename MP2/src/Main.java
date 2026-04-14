
public class Main {
    public static void main(String[] args) {

    }
}
/*
nie trzeba robic ekstencsji ale przy kwalifikacyjnej trzeba zrobic usuwanie przy usuwaniu klasy

dzialanie na referencjach a nie oryginalach
jak sie uda to trwalosc (czyli zapisanie do binarki) ale nie bedzie oceniane

setterow i getterow i tostringow nie trzba pokazywac na diagramie
+ metod np potrzebnych do przechodzenia pomiedzy klasami nie trzeba
wszystko co nie wplywa na logike do historyjki to nie trzeba robic metod

metody biznesowe (logika biznesowa) -> metoda statyczna, nie robila nic z konstrukcja
ale obliczala cos i zwracala na podstawie ekstensji -> zawsze trzeba je pokazac

Kahoot:

asocjacja kwalifikowana mapa/slownik

kompozycja nie moze byc dzielona z innymi klasami np jedna sala w budynku B nie moze byc dzielona tez z budynkiem A

dodawanie 1>*
(zdjecie company employee), chcemy polaczyc je, tylko za pierwszym razem sie w ten sposob utworza. c1-*e.
asocjacja zadziala tylko za 1 razem, jak dodaje sie do pracownika inne employee to trzeba usunac go z listy w company
przez metode w company ktora usuwa pracownikow

referencja zwrotna -> jak tworzymy polaczenie to trzeba sie upewnic ze po dwoch stronach obiektu musi byc referencja

asocjacja z atrybutem -> musi miec 2 pola z referencjami do tabel pomiedzy ktorymi jest i potem
atrybuty z dodatkowymi informacjami

wiele do wiele -> drugie zdjecie. addCompany powinien byc public. takich rzeczy jak na zdjeciu nei chcemy

kompozycja -> 3 zdjecie -> sprawdzenei czy building jest nullem. w building jest rooms i to jest
zewnetrzna ekstensja sprawdzajaca jakie pokoje sa w building
kompozycja w ramach oddzielnej klasy to musi byc konstruktor prywatny. tworzenie tylko przez budynek

ogolnie: z obu stron musi byc mozliwosc tworzenia polaczen i ify zeby zapobiec np duplikatom




z UML Help:

z atrybutem -> asocjacja wiele-wiele, wiec 4 referencje, w kazdej klasie do kazdej i nie ma dodawanie dodatkowych polaczen do niej
jak usuwanie to po wylaczeniu polaczen po prostu zgubienei ich, zeby garbage collector je zebral


kwalifikowana -> slownik/hash mapa: w miare sensowne klucze sa potrzebne, nie uzywac ID

kompozycji nie robic jako klasa wewnetrzna

ONEX
 */
//todo dzialanie na referencjach a nei oryginalach