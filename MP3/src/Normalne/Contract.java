package Normalne;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Contract {
    private static List<Contract> contractList = new ArrayList<>();
    private int contractNumber;
    private LocalDate startDate;

    // ===================== KONSTRUKTOR =======================
    public Contract(int contractNumber, LocalDate startDate) {
        setContractNumber(contractNumber);
        setStartDate(startDate);

        contractList.add(this);
    }

    // ===================== METODY =======================
    public abstract String getContractType();

    // ===================== SETTERY =======================
    private void setContractNumber(int contractNumber) {
        if(contractNumber<0){
            throw new IllegalArgumentException("cannot add <0 contract Number");
        }
        for (Contract c:contractList) {
            if(c.getContractNumber() == contractNumber){
                throw new IllegalArgumentException("This contract number is already used");
            }
        }
        this.contractNumber = contractNumber;
    }
    private void setStartDate(LocalDate startDate) {
        if(startDate == null){
            throw new IllegalArgumentException("cannot add null startDate");
        }
        this.startDate = startDate;
    }

    // ===================== GETTERY =======================
    public static List<Contract> getContractList() {
        return Collections.unmodifiableList(contractList);
    }
    public int getContractNumber() {
        return contractNumber;
    }
    public LocalDate getStartDate() {
        return startDate;
    }

    // ===================== TOSTRING =======================
    @Override
    public String toString() {
        return "Contract{" +
                "contractNumber=" + contractNumber +
                ", startDate=" + startDate +
                '}';
    }
}
