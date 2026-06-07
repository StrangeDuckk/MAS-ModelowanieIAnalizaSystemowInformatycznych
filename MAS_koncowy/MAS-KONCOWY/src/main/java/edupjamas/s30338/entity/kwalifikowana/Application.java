package edupjamas.s30338.entity.kwalifikowana;

import edupjamas.s30338.entity.Wielodziedziczenie.Person;
import edupjamas.s30338.entity.kompozycja.JobOffer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;

    @NotNull
    private LocalDate data;

    @NotNull
    private double candidatesSalaryProposition;

    // ===================== RELACJE =======================

    //cv * - 1 application
    @OneToMany(mappedBy = "application")//wskazanie na nazwe zmiennej w cv
    @MapKey(name = "cvNumber")//id z cv
    private Map<String, CV> cvs = new HashMap<>();//{surname_name_number}

    //(jobOffer 1 ---- 1..*) Application 1..* ---- 1 Person
    @ManyToOne(optional = false)
    @JoinColumn(name = "personId")
    @ToString.Exclude
    private Person person;

    //jobOffer 1 ---- 1..* Application( 1..* ---- 1 Person)
    @ManyToOne(optional = false)
    @JoinColumn(name = "jobOfferId")
    @ToString.Exclude
    private JobOffer jobOffer;

    public Application(
            LocalDate data,
            double candidatesSalaryProposition,
            Person person,
            JobOffer jobOffer,
            List<String> education,
            List<String> experience,
            List<String> cvCourses
    ) {
        setData(data);
        setCandidatesSalaryProposition(candidatesSalaryProposition);
        setPerson(person);
        setJobOffer(jobOffer);
        addCV(education,experience,cvCourses);
    }
    public Application(
            LocalDate data,
            double candidatesSalaryProposition,
            JobOffer jobOffer,
            List<String> education,
            List<String> experience,
            List<String> cvCourses
    ) {
        setData(data);
        setCandidatesSalaryProposition(candidatesSalaryProposition);
        setJobOffer(jobOffer);
        addCV(education,experience,cvCourses);
    }

    // ===================== SETTERY =======================
    private void setData(LocalDate data) {
        if (data == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        this.data = data;
    }
    private void setCandidatesSalaryProposition(double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException(
                    "Candidate salary proposition cannot be < 0"
            );
        }
        this.candidatesSalaryProposition = salary;
    }
    public void setPerson(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("Person cannot be null");
        }
        this.person = person;
    }
    private void setJobOffer(JobOffer jobOffer) {
        if (jobOffer == null) {
            throw new IllegalArgumentException("JobOffer cannot be null");
        }
        this.jobOffer = jobOffer;
    }
    private void addCV(List<String> education, List<String> experience, List<String> cvCourses) {
        if(education == null && experience == null && cvCourses == null){
            return; //pozwalam na brak cv
        }

        Map<String, CV> tempCV = new HashMap<>();


        String cvName = person.getName().get(0)+"_"+person.getSurname()+"_";

        if(person.getApplications().isEmpty() || person.getApplications().get(person.getApplications().size()-1).getCvs().isEmpty())
            cvName+="0";
        else
            cvName+= String.valueOf(person.getApplications()
                    .stream()
                    .mapToInt(app -> app.getCvs().size())
                    .sum()); //index o 1 wiekszy od ostatniego
//                    .getLast().getCvs().size()+1);

        CV cv = new CV(
                cvName,
                education,
                experience,
                cvCourses
        );

        tempCV.put(cvName, cv);
        cv.setApplication(this);

        this.cvs = tempCV;
    }
}
