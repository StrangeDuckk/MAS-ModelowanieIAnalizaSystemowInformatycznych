package edupjamas.s30338.entity.kwalifikowana;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@Getter
@Setter
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

    protected CV() {}
}
