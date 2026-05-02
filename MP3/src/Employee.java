import java.time.LocalDate;
import java.util.List;

public class Employee extends Person{
    //todo do sprawdzenia
    private Double salary;
    private String currentOccupationPosition;

    // ===================== KONSTRUKTOR =======================
    protected Employee(
            List<String> name,
            String surname,
            String email,
            String phoneNumber,
            LocalDate dateOfBirth,
            Double salary,
            String currentOccupationPosition
    ) {
        super(name, surname, email, phoneNumber, dateOfBirth);

        setSalary(salary);
        setCurrentOccupationPosition(currentOccupationPosition);
    }

    // ===================== METODY =======================
    @Override
    public String getCurrentOccupation() {
        return Position();
    }
    public String Position() {
        return currentOccupationPosition;
    }

    // ===================== SETTERY =======================
    private void setSalary(Double salary) {
        if(salary == null){
            throw new IllegalArgumentException("salary cannot be null");
        }
        if(salary<0){
            throw new IllegalArgumentException("salary cannot be < 0");
        }
        this.salary = salary;
    }
    private void setCurrentOccupationPosition(String currentOccupationPosition) {
        if(currentOccupationPosition == null || currentOccupationPosition.isBlank()){
            throw new IllegalArgumentException("current occupation position cannot be blank or null");
        }
        this.currentOccupationPosition=currentOccupationPosition;
    }

    // ===================== GETTERY =======================
    public Double getSalary() {
        return salary;
    }

    // ===================== TOSTRING =======================
    @Override
    public String toString() {
        return "Employee{" +
                "salary=" + salary +
                ", currentOccupationPosition='" + currentOccupationPosition + '\'' +
                '}';
        //todo + informacje z person
    }
}
