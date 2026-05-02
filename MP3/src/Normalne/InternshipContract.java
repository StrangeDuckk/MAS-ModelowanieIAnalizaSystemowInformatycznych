package Normalne;

import java.time.LocalDate;

public class InternshipContract extends Contract {
    private String universityName;
    private int durationInSemesters;

    // ===================== KONSTRUKTOR =======================
    public InternshipContract(int contractNumber, LocalDate startDate, String universityName, int durationInSemesters) {
        super(contractNumber, startDate);
        setUniversityName(universityName);
        setDurationInSemesters(durationInSemesters);
    }

    // ===================== METODY =======================
    @Override
    public String getContractType() {
        return "INTERNSHIP";
    }

    @Override
    public Double countBrutto() {
        return 0.00;//nie sa oplacani
    }

    // ===================== SETTER =======================
    private void setUniversityName(String universityName) {
        if(universityName == null || universityName.isBlank()){
            throw new IllegalArgumentException("universityName cannot be null or blank");
        }
        this.universityName = universityName;
    }
    private void setDurationInSemesters(int durationInSemesters) {
        if(durationInSemesters < 0){
            throw new IllegalArgumentException("durationInSemesters cannot be < 0");
        }
        this.durationInSemesters = durationInSemesters;
    }

    // ===================== GETTER =======================
    public String getUniversityName() {
        return universityName;
    }
    public int getDurationInSemesters() {
        return durationInSemesters;
    }

    // ===================== TOSTRING =======================
    @Override
    public String toString() {
        return super.toString()+
                "InternshipContract{" +
                "universityName='" + universityName + '\'' +
                ", durationInSemesters=" + durationInSemesters +
                '}';
    }
}
