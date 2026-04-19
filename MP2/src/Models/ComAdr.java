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
    ComAdr(Company com, Adress adr, LocalDate from, LocalDate to){
        setCompany(com);
        setAdress(adr);
        setFromDate(from);
        setToDate(to);

        comAdr.add(this);
        com.addComAdr(this);
        adr.addComAdr(this);
    }

    // ============= relacje ===============
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
        return "Company: " + this.company.getName() + ", Adress: " + this.adress.getRoad() +
                " => From: " + this.from + (Objects.equals(this.to, LocalDate.of(9999, 12, 31)) ? ", till today\n" : (", to: " + this.to));
    }
}