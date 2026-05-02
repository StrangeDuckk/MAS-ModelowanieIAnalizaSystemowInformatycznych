package Overlapping;

public class NormalOffice {
    private String field;
    private String ceo;
    private Double kapitalFirmy;

    // ===================== Konstruktor =======================
    public NormalOffice(String field, String ceo, Double kapitalFirmy) {
        setField(field);
        setCeo(ceo);
        setKapitalFirmy(kapitalFirmy);
    }

    // ===================== SETTERY =======================
    private void setField(String field) {
        if(field == null || field.isBlank()){
            throw new IllegalArgumentException("field cannot be null or blank");
        }
        this.field = field;
    }
    private void setCeo(String ceo) {
        if(ceo == null || ceo.isBlank()){
            throw new IllegalArgumentException("ceo cannot be null or blank");
        }
        this.ceo = ceo;
    }
    private void setKapitalFirmy(Double kapitalFirmy) {
        if(kapitalFirmy == null){
            throw new IllegalArgumentException("kapitalFirmy cannot be null");
        }
        if(kapitalFirmy<0){
            throw new IllegalArgumentException("kapitalFirmy cannot be <0");
        }
        this.kapitalFirmy = kapitalFirmy;
    }

    // ===================== GETTERY =======================
    public String getField() {
        return field;
    }
    public String getCeo() {
        return ceo;
    }
    public Double getKapitalFirmy() {
        return kapitalFirmy;
    }

    // ===================== TOSTRING =======================

    @Override
    public String toString() {
        return "NormalOffice{" +
                "field='" + field + '\'' +
                ", ceo='" + ceo + '\'' +
                ", kapitalFirmy=" + kapitalFirmy +
                '}';
    }
}
