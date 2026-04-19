package Models;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ComAdr {
    private Company company;
    private Adress adress;
    private static List<ComAdr> comAdr = new ArrayList<>();

    private LocalDate from;
    private LocalDate to;// moze byc null

    // ============== Konstruktor ===============
    private ComAdr(Company com, Adress adr, LocalDate from, LocalDate to){
        setCompany(com);
        setAdress(adr);
        setFromDate(from);
        setToDate(to);

        comAdr.add(this);
        com.addComAdr(this);
        adr.addComAdr(this);
    }

    // ============= relacje ===============
    public static ComAdr create(Company company, Adress adress, LocalDate from, LocalDate to){
        return new ComAdr(company,adress,from,to);
    }
    private void setCompany(Company company){
        if(company == null){
            throw new IllegalArgumentException("Company cannot be null");
        }
        if(company.getComAdr().contains(this)){
            return; // zakonczenie
        }
        this.company = company;
        company.addComAdr(this);
    }
    private void setAdress(Adress adr) {
        if(adr == null){
            throw new IllegalArgumentException("Adress cannot be null");
        }
        if(adr.getComAdr().contains(this)){
            return;//zakonczenie
        }
        this.adress = adr;
        adr.addComAdr(this);
    }

    protected void removeAllConnections(){
        //na raz z Company i Adress
        if(this.company != null){
            Company tempComp = this.company;
            this.company = null;
            tempComp.removeComAdr(this);
        }
        if(this.adress != null){
            Adress tempAdr = this.adress;
            this.adress = null;
            tempAdr.removeComAdr(this);
        }

        comAdr.remove(this);
    }
    // ============ gettery i settery ===============
    public static List<ComAdr> getComAdr() {
        return Collections.unmodifiableList(comAdr);
    }
    public Company getCompany(){
        return this.company;
    }
    public Adress getAdress(){
        return this.adress;
    }
    public LocalDate getFromDate() {
        return from;
    }
    private void setFromDate(LocalDate from) {
        if(from == null){
            throw new IllegalArgumentException("Date from cannot be null");
        }
        if(from.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Date has to be from past");
        }
        this.from=from;
    }
    public LocalDate getToDate() {
        return to;
    }
    private void setToDate(LocalDate to) {
        if(to == null){
            this.to = LocalDate.of(9999,12,31);//data nei moze byc null ale moze byc niesamowicie odlegla
            return;
        }
        if(to.isBefore(this.from)){
            throw new IllegalArgumentException("TO date has to be before FROM");
        }

        this.to = to;
    }
    // ============ funkcje =================

    @Override
    public String toString() {
        return "From: "+this.from+ (Objects.equals(this.to, LocalDate.of(9999, 12, 31)) ? ", till today\n": (", to: " + this.to));
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
