public class Adress {
    //todo przetlumaczyc na ang nazwy
    private String ulica;
    private int numerDomu;
    private int numerMieszkania;//todo przesloniecie konstruktora bez nr mieszkania
    private String kodPocztowy; //kody zaczynaja sie od 0 i maja "-" w srodku
    private String miasto;
    private String kraj;

    public Adress(
            String ulica,
            int numerDomu,
            int numerMieszkania,
            String kodPocztowy,
            String miasto,
            String kraj
    ){
        setUlica(ulica);
        setNumerDomu(numerDomu);
        setNumerMieszkania(numerMieszkania);
        setKodPocztowy(kodPocztowy);
        setMiasto(miasto);
        setKraj(kraj);
    }

    public Adress(
            String ulica,
            int numerDomu,
            String kodPocztowy,
            String miasto,
            String kraj
    ){
        setUlica(ulica);
        setNumerDomu(numerDomu);
        setNumerMieszkania(0); //dla braku nr mieszkania i poprawnego wypisywania w toString
        setKodPocztowy(kodPocztowy);
        setMiasto(miasto);
        setKraj(kraj);
    }

    public String getUlica() {
        return ulica;
    }
    public void setUlica(String ulica) {
        this.ulica = ulica;
    }
    public int getNumerDomu() {
        return numerDomu;
    }
    public void setNumerDomu(int numerDomu) {
        this.numerDomu = numerDomu;
    }
    public int getNumerMieszkania() {
        return numerMieszkania;
    }
    public void setNumerMieszkania(int numerMieszkania) {
        this.numerMieszkania = numerMieszkania;
    }
    public String getKodPocztowy() {
        return kodPocztowy;
    }
    public void setKodPocztowy(String kodPocztowy) {
        //todo regex
        this.kodPocztowy = kodPocztowy;
    }
    public String getMiasto() {
        return miasto;
    }
    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }
    public String getKraj() {
        return kraj;
    }
    public void setKraj(String kraj) {
        this.kraj = kraj;
    }

    @Override
    public String toString() {
        String temp = this.ulica+" " + this.numerDomu;

        if(this.numerMieszkania > 0){
            temp +="/"+this.numerMieszkania;
        }

        temp += ", "+this.kodPocztowy+" "+this.miasto+", "+this.kraj;

        return temp;
    }
}
