package Models;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Company {
    private List<JobOffer> jobOffers = new ArrayList<>();//kompozycja, company zarzadza lista
    private List<ComAdr> comAdr = new ArrayList<>();
    private static List<Company> companies = new ArrayList<>();

    private String name;
    private String shortInfo;

    // ============= Konstruktor =============

    public Company(
            String name,
            String shortInfo
    ){
        setName(name);
        setShortInfo(shortInfo);

        companies.add(this);
    }
    // ============= relacje ==================
    public List<JobOffer> getJobOffers(){ //6.1
        return Collections.unmodifiableList(this.jobOffers);
    }
    public JobOffer createJobOffer(String jobInfo, Double salary, int experienceInYears){//10.1, 10.2
        return new JobOffer(jobInfo,salary,experienceInYears,this);
    }
    protected void addJobOffer(JobOffer jobOffer){
        if( jobOffer == null){
            throw new IllegalArgumentException("Joboffer cannot be null");
        }
        if(jobOffer.getCompany() != null && jobOffer.getCompany() != this){//10.3
            throw new IllegalArgumentException("JobOffer already belongs to a company: "+jobOffer.getCompany());
        }
        if(this.jobOffers.contains(jobOffer)){
            return;//zakonczenie
        }

        this.jobOffers.add(jobOffer);
//        jobOffer.setCompany(this);
    }
    protected void removeJobOffer(JobOffer jobOffer){//10.4
        if(jobOffer == null){
            throw new IllegalArgumentException("Cannot remove null jobOffer");
        }
        if(!this.jobOffers.contains(jobOffer)){
            return;//zakonczenie
        }

        this.jobOffers.remove(jobOffer);
        jobOffer.removeCompany();
    }
    public void removeCompany(){
        //usuniecie i company i JobOffer
        for(JobOffer job: new ArrayList<>(this.jobOffers)){
            job.removeCompany();
        }
        companies.remove(this);
    }

    public List<ComAdr> getComAdr(){
        return Collections.unmodifiableList(this.comAdr);
    }
    protected void setComAdr(List<ComAdr> comAdr) {
        if(comAdr == null){
            throw new IllegalArgumentException("ComAdr list cannot be null");
        }
        for (ComAdr ca: comAdr) {
            addComAdr(ca);
        }

    }
    protected void addComAdr(ComAdr ca) {
        if(ca == null){
            throw new IllegalArgumentException("ComAfr argument cannot be null");
        }
        if(ca.getCompany() == this && this.comAdr.contains(ca)){
            return;// zakonczenie
        }
        this.comAdr.add(ca);
    }
    protected void removeComAdr(ComAdr comAdr){
        if(comAdr == null){
            throw new IllegalArgumentException("Cannot remove null ComAdr connection");
        }
        if(this.comAdr == null || !this.comAdr.contains(comAdr)){
            return;
        }

        ComAdr tempComAdr = comAdr;
        this.comAdr.remove(comAdr);
        tempComAdr.removeAllConnections();
    }

    // ============= gettery i settery ===========
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("Organisation name cannot be null or blank");
        }
        this.name = name;
    }
    public String getShortInfo(){
        return this.shortInfo;
    }
    public void setShortInfo(String shortInfo) {
        if(shortInfo == null || shortInfo.isBlank()){
            throw new IllegalArgumentException("Short info cannot be null");
        }
        this.shortInfo = shortInfo;
    }

    // ============= funkcje ================

    @Override
    public String toString() {
        String temp = "Company: name:'" + name + '\n' +"shortInfo: " + shortInfo + '\n';
        if(this.jobOffers != null){
            for (JobOffer job: this.jobOffers) {
                temp += job.toString();
            }
        }
        if(this.comAdr != null){
            for(ComAdr ca: this.comAdr){
                temp+= ca.toString();
            }
        }

        return temp;
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