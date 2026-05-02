package Normalne;

import Normalne.Contract;

import java.time.LocalDate;

public class B2BContract extends Contract {
    private Double hourlyRate;
    private String vatNumber;

    // ===================== KONSTRUKTOR =======================
    public B2BContract(int contractNumber, LocalDate startDate, Double hourlyRate, String vatNumber) {
        super(contractNumber, startDate);

        setHourlyRate(hourlyRate);
        setVatNumber(vatNumber);
    }

    // ===================== MEOTDY =======================
    @Override
    public String getContractType() {
        return "B2B";
    }

    @Override
    public Double countBrutto() {
        return hourlyRate*160;//brutto netto miesiecznie
    }

    // ===================== SETTERY =======================
    private void setHourlyRate(Double hourlyRate) {
        if(hourlyRate == null || hourlyRate.isInfinite() || hourlyRate.isNaN()){
            throw new IllegalArgumentException("Cannot add null or Inifinite or NaN hourlyRate");
        }
        if(hourlyRate<0){
            throw new IllegalArgumentException("Cannot add hourlyRate < 0");
        }
        this.hourlyRate = hourlyRate;
    }
    private void setVatNumber(String vatNumber) {
        if(vatNumber == null || vatNumber.isBlank()){
            throw new IllegalArgumentException("vatNUmber cannot be null or blank");
        }
        if (vatNumber.length()<10){
            throw new IllegalArgumentException("vat number has to have at least 10 digits");
        }
        this.vatNumber = vatNumber;
    }

    // ===================== GETTERY =======================
    public Double getHourlyRate() {
        return hourlyRate;
    }
    public String getVatNumber() {
        return vatNumber;
    }

    // ===================== TOSTRING =======================
    @Override
    public String toString() {
        return super.toString()+
                "B2BContract{" +
                "hourlyRate=" + hourlyRate +
                ", vatNumber='" + vatNumber + '\'' +
                '}';
    }
}
