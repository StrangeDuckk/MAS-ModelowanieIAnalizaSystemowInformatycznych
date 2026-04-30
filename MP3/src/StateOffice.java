public class StateOffice {
    private String state;
    private String country;
    private String minister;
    private Boolean military;

    // ===================== Konstruktor =======================
    public StateOffice(String state, String country, String minister, Boolean military) {
        setState(state);
        setCountry(country);
        setMinister(minister);
        setMilitary(military);
    }

    // ===================== SETTERY =======================
    private void setState(String state) {
        if(state == null || state.isBlank()){
            throw new IllegalArgumentException("state cannot be null or blank");
        }
        this.state = state;
    }
    private void setCountry(String country) {
        if(country == null || country.isBlank()){
            throw new IllegalArgumentException("country cannot be null or blank");
        }
        this.country = country;
    }
    private void setMinister(String minister) {
        if(minister == null || minister.isBlank()){
            throw new IllegalArgumentException("minister cannot be null or blank");
        }
        this.minister = minister;
    }
    private void setMilitary(Boolean military) {
        if(military == null){
            throw new IllegalArgumentException("military cannot be null");
        }
        this.military = military;
    }

    // ===================== GETTERY =======================
    public String getState() {
        return state;
    }
    public String getCountry() {
        return country;
    }
    public String getMinister() {
        return minister;
    }
    public Boolean getMilitary() {
        return military;
    }

    // ===================== TOSTRING =======================
    @Override
    public String toString() {
        return "StateOffice{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", minister='" + minister + '\'' +
                ", military=" + military +
                '}';
    }
}
