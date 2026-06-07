package edupjamas.s30338.entity.kwalifikowana;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class CV {
    @Id
    @NotBlank(message = "cvNumber is mandatory")
    @Pattern(
            regexp = "^[^@\\s]+_[^@\\s]+_[0-9]+$",
            message = "Format: Surname_Name_number"
    )
    @Setter(AccessLevel.NONE)//uniemozliwienie zmiany atrybutu po utworzeniu obiektu
    @Column(updatable = false)
    private String cvNumber; //{surname_name_number}

    @ElementCollection
    private List<String> education = new ArrayList<>();
    @ElementCollection
    private List<String> experience = new ArrayList<>();
    @ElementCollection
    private List<String> cvCourses = new ArrayList<>();//{courseName, field, degree}

    //cv * - 1 application
    @ManyToOne
    @JoinColumn(name = "applicationId", nullable = false)//klucz w application
    private Application application;

    protected CV(
            String cvNumber,
            List<String> education,
            List<String> experience,
            List<String> cvCourses
    ) {
        setCvNumber(cvNumber);
        setEducation(education);
        setExperience(experience);
        setCvCourses(cvCourses);
    }

    private void setCvNumber(String cvNumber) {
        if (cvNumber == null || cvNumber.isBlank()) {
            throw new IllegalArgumentException(
                    "CV number cannot be null or blank"
            );
        }

        if (!cvNumber.matches("^[^@\\s]+_[^@\\s]+_[0-9]+$")) {
            throw new IllegalArgumentException(
                    "CV number must have format Surname_Name_number"
            );
        }

        this.cvNumber = cvNumber;
    }
    private void setEducation(List<String> education) {
        if (education == null) {
            return;
        }

        for (String item : education) {
            if (item == null || item.isBlank()) {
                throw new IllegalArgumentException(
                        "Education entry cannot be null or blank"
                );
            }
        }

        this.education = new ArrayList<>(education);
    }

    private void setExperience(List<String> experience) {
        if (experience == null) {
            return;
        }

        for (String item : experience) {
            if (item == null || item.isBlank()) {
                throw new IllegalArgumentException(
                        "Experience entry cannot be null or blank"
                );
            }
        }

        this.experience = new ArrayList<>(experience);
    }
    private void setCvCourses(List<String> cvCourses) {
        if (cvCourses == null) {
            return;
        }

        for (String item : cvCourses) {
            if (item == null || item.isBlank()) {
                throw new IllegalArgumentException(
                        "Course entry cannot be null or blank"
                );
            }
        }

        this.cvCourses = new ArrayList<>(cvCourses);
    }

    protected void setApplication(Application application) {
        if(application != null) {
            this.application = application;
        }
    }
}
