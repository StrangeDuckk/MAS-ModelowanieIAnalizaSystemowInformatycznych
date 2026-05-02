package Normalne;

import Normalne.Contract;

import java.time.LocalDate;

public class FullTimeContract extends Contract {
    private Double monthlySalary;
    private int vacationDays;

    // ===================== KONSTRUKTOR =======================
    public FullTimeContract(int contractNumber, LocalDate startDate, Double monthlySalary, int vacationDays) {
        super(contractNumber, startDate);
        setMonthlySalary(monthlySalary);
        setVacationDays(vacationDays);
    }

    // ===================== METODY =======================
    @Override
    public String getContractType() {
        return "FULL_TIME";
    }

    @Override
    public Double countBrutto() {
        return monthlySalary*1.23;//miesiecznie brutto
    }

    // ===================== SETTERY =======================
    private void setMonthlySalary(Double monthlySalary) {
        if(monthlySalary == null || monthlySalary.isInfinite() || monthlySalary.isNaN()){
            throw new IllegalArgumentException("Cannot add null or Inifinite or NaN salary");
        }
        if(monthlySalary<0){
            throw new IllegalArgumentException("Cannot add salary < 0");
        }
        this.monthlySalary = monthlySalary;
    }
    private void setVacationDays(int vacationDays) {
        if (vacationDays<0){
            throw new IllegalArgumentException("cannot add < 0 vacation days");
        }
        this.vacationDays = vacationDays;
    }

    // ===================== GETTERY =======================
    public Double getMonthlySalary() {
        return monthlySalary;
    }
    public int getVacationDays() {
        return vacationDays;
    }

    // ===================== TOSTRING =======================
    @Override
    public String toString() {
        return super.toString()+
                "FullTimeContract{" +
                "monthlySalary=" + monthlySalary +
                ", vacationDays=" + vacationDays +
                '}';
    }
}
