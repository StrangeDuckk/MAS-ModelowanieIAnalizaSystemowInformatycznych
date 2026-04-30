import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Company {
    private static List<Company> companyList = new ArrayList<>();

    private String name;
    private String adress;
    private List<String> potrzebneStanowiska;

    // ===================== Kompozycja OVERLAPPING =======================
    private NormalOffice normalOffice;
    private StateOffice stateOffice;

    // ===================== Konstruktor =======================
    public Company(String name,
                   String adress,
                   List<String> potrzebneStanowiska,
                   NormalOffice normalOffice,
                   StateOffice stateOffice
    ) {
        setName(name);
        setAdress(adress);
        setPotrzebneStanowiskaList(potrzebneStanowiska);

        if(normalOffice == null && stateOffice == null){
            throw new IllegalArgumentException("Company must have at least one role");
        }
        this.normalOffice = normalOffice;
        this.stateOffice = stateOffice;

        companyList.add(this);
    }

    // ===================== SETTERY =======================
    private void setName(String name) {
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("name cannot be null or blank");
        }
        this.name = name;
    }
    private void setAdress(String adress) {
        if(adress == null || adress.isBlank()){
            throw new IllegalArgumentException("adress cannot be null or blank");
        }
        this.adress = adress;
    }
    private void setPotrzebneStanowiskaList(List<String> potrzebneStanowiska) {
        if(potrzebneStanowiska == null){
            this.potrzebneStanowiska = null;
            return;
        }
        if(potrzebneStanowiska.isEmpty()){
            throw new IllegalArgumentException("potrzebne stanowiska list cannot be null");
        }
        this.potrzebneStanowiska = new ArrayList<>();
        for (String stanowisko: potrzebneStanowiska) {
            setPotrzebneStanowisko(stanowisko);
        }
    }
    private void setPotrzebneStanowisko(String stanowisko) {
        if(stanowisko == null || stanowisko.isBlank()){
            throw new IllegalArgumentException("stanowisko cannot be null or blank");
        }
        this.potrzebneStanowiska.add(stanowisko);
    }

    // ===================== GETTERY =======================

    public static List<Company> getCompanyList() {
        return Collections.unmodifiableList(companyList);
    }
    public String getName() {
        return name;
    }
    public String getAdress() {
        return adress;
    }
    public List<String> getPotrzebneStanowiska() {
        return Collections.unmodifiableList(potrzebneStanowiska);
    }
    public NormalOffice getNormalOffice() {
        if(this.normalOffice == null){
            throw new IllegalStateException("no normal office role");
        }
        return normalOffice;
    }
    public StateOffice getStateOffice() {
        if(this.stateOffice == null){
            throw new IllegalStateException("no state office role");
        }
        return stateOffice;
    }

    // ===================== TOSTRING =======================
    @Override
    public String toString() {
        return "Company{ name: "+this.name+", adress: "+this.adress+
                ", potrzebne stanowiska: "+(this.potrzebneStanowiska.isEmpty()?"none":this.potrzebneStanowiska.toString())+
                "\n"+(this.normalOffice== null?"":"Normal office: "+this.normalOffice.toString())+
                "\n"+(this.stateOffice== null?"":"State office: "+this.stateOffice.toString())+"}";
    }
}
