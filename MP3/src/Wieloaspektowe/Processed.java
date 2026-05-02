package Wieloaspektowe;

import Wieloaspektowe.CandidateApplication;

import java.time.LocalDate;
import java.util.List;

public class Processed extends CandidateApplication {
    private String whoProcessed;
    private LocalDate processionDate;
    private ENUMS.ResultType resultType;

    // ============== KONSTRUKTORY ==============
    // -------------- PRIORITY -----------------
    public Processed(String cvCandidate,
                        ENUMS.CandidateApplicationType applicationType,
                        String recommendingPerson,
                        String acquaintanceDegree,
                        String whoProcessed,
                        LocalDate processionDate,
                        ENUMS.ResultType resultType) {
        super(cvCandidate, applicationType);

        if(applicationType == ENUMS.CandidateApplicationType.PRIORITY){
            setPriorityData(recommendingPerson,acquaintanceDegree);
        }
        else{
            throw new IllegalStateException("This data can be added only for PRIORITY type");
        }

        setWhoProcessed(whoProcessed);
        setProcessionDate(processionDate);
        setResultType(resultType);
    }
    // -------------- NORMAL -----------------
    public Processed(String cvCandidate,
                        ENUMS.CandidateApplicationType applicationType,
                        List<String> skills,
                        String whoProcessed,
                        LocalDate processionDate,
                        ENUMS.ResultType resultType) {
        super(cvCandidate, applicationType);

        if(applicationType == ENUMS.CandidateApplicationType.NORMAL){
            setNormalData(skills);
        }
        else{
            throw new IllegalStateException("This data can be added only for NORMAL type");
        }

        setWhoProcessed(whoProcessed);
        setProcessionDate(processionDate);
        setResultType(resultType);
    }

    // ============== SETTERY ==============
    private void setWhoProcessed(String whoProcessed) {
        if(whoProcessed == null || whoProcessed.isBlank()){
            throw new IllegalArgumentException("Who processed field cannot be null");
        }
        this.whoProcessed = whoProcessed;
    }
    private void setProcessionDate(LocalDate processionDate) {
        if(processionDate == null){
            throw new IllegalArgumentException("processionDate cannot be null");
        }
        if(processionDate.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("processionDate cannot be after now");
        }
        this.processionDate = processionDate;
    }
    private void setResultType(ENUMS.ResultType resultType) {
        if(resultType == null){
            throw new IllegalStateException("Result type state cannot be null");
        }
        this.resultType = resultType;
    }

    // ============== GETTERY ==============
    public String getWhoProcessed() {
        return whoProcessed;
    }
    public LocalDate getProcessionDate() {
        return processionDate;
    }
    public ENUMS.ResultType getResultType() {
        return resultType;
    }

    // ============== toString ==============
    @Override
    public String toString() {
        return super.toString()+"\nApplication State: Processed, who processed: "+this.whoProcessed+
                ", procession date: "+this.processionDate+", with result: "+this.resultType;
    }
}
