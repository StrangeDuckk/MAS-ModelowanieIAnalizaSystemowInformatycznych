import java.util.ArrayList;
import java.util.List;

public abstract class CandidateApplication {
    private List<CandidateApplication> candidateApplicationList = new ArrayList<>();
    
    private String cvCandidate;
    private ENUMS.CandidateApplicationType ApplicationType;

    // =========== PRIORITY ===========
    private String recommendingPerson;
    private String acquaintanceDegree;

    // =========== NORMAL ===========
    private List<String> skills = new ArrayList<>();
}
