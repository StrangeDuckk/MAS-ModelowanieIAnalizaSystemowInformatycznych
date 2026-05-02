package Wieloaspektowe;

import Wieloaspektowe.CandidateApplication;

import java.time.LocalDate;
import java.util.List;

public class Added extends CandidateApplication {
    private LocalDate receivingDate;

    // ============== KONSTRUKTORY ==============
    // -------------- PRIORITY -----------------
    public Added(String cv,
                 ENUMS.CandidateApplicationType type,
                 LocalDate receivingDate,
                 String recommendingPerson,
                 String acquiantanceDegree){
        super(cv, type);

        if(type == ENUMS.CandidateApplicationType.PRIORITY){
            setPriorityData(recommendingPerson,acquiantanceDegree);
        }
        else{
            throw new IllegalStateException("This data can be added only for PRIORITY type");
        }

        setReceivingDate(receivingDate);
    }
    // -------------- NORMAL -----------------
    public Added(String cv,
                 ENUMS.CandidateApplicationType type,
                 LocalDate receivingDate,
                 List<String> skills){
        super(cv, type);

        if(type == ENUMS.CandidateApplicationType.NORMAL){
            setNormalData(skills);
        }
        else{
            throw new IllegalStateException("This data can be added only for NORMAL priority");
        }

        setReceivingDate(receivingDate);
    }

    // ============== SETTERY ==============
    private void setReceivingDate(LocalDate receivingDate) {
        if(receivingDate == null){
            throw new IllegalArgumentException("Date cannot be null");
        }
        if(receivingDate.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Receiving date has to be before now");
        }
        this.receivingDate = receivingDate;
    }

    // ============== SETTERY ==============
    public LocalDate getReceivingDate() {
        return receivingDate;
    }
    // ============== toString ==============

    @Override
    public String toString() {
        return super.toString()+"\nApplication State: Added, receiving date: "+ this.receivingDate;
    }
}