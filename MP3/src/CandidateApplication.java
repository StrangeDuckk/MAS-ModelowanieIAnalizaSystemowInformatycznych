import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class CandidateApplication {
    private static List<CandidateApplication> candidateApplicationList = new ArrayList<>();

    private String cvCandidate;
    private ENUMS.CandidateApplicationType applicationType;

    // =========== PRIORITY ===========
    private String recommendingPerson;
    private String acquaintanceDegree;

    // =========== NORMAL ===========
    private List<String> skills = new ArrayList<>();

    // =========== KONSTRUKTOR ===========
    protected CandidateApplication(String cvCandidate, ENUMS.CandidateApplicationType applicationType){
        setCvCandidate(cvCandidate);
        setApplicationType(applicationType);

        candidateApplicationList.add(this);
    }

    // =========== SETTERY ===========
    private void setCvCandidate(String cvCandidate) {
        if(cvCandidate == null || cvCandidate.isBlank()){
            throw new IllegalArgumentException("cvCandidate cannot be null or blank");
        }
        this.cvCandidate = cvCandidate;
    }
    private void setApplicationType(ENUMS.CandidateApplicationType applicationType) {
        if(applicationType == null){
            throw new IllegalArgumentException("applicationType cannot be null");
        }
        this.applicationType = applicationType;
    }
    protected void setPriorityData(String recommendingPerson, String acquaintanceDegree){
        if (this.applicationType != ENUMS.CandidateApplicationType.PRIORITY){
            throw new IllegalStateException("To set this data it has to have a PRIORITY type");
        }
        //czyszczenie
        this.skills = null;

        setRecommendingPerson(recommendingPerson);
        setAcquaintanceDegree(acquaintanceDegree);
    }
    protected void setNormalData(List<String> skills){
        if(this.applicationType!= ENUMS.CandidateApplicationType.NORMAL){
            throw new IllegalStateException("To set this data it has to have a NORMAL type");
        }
        //czyszczenie
        this.recommendingPerson = null;
        this.acquaintanceDegree = null;

        setSkillsList(skills);
    }
    private void setRecommendingPerson(String recommendingPerson) {
        if(recommendingPerson == null || recommendingPerson.isBlank()){
            throw new IllegalArgumentException("recommendingPerson cannot be null or blank");
        }
        this.recommendingPerson= recommendingPerson;
    }
    private void setAcquaintanceDegree(String acquaintanceDegree) {
        if(acquaintanceDegree == null || acquaintanceDegree.isBlank()){
            throw new IllegalArgumentException("acquaintanceDegree cannot be null or blank");
        }
        this.acquaintanceDegree= acquaintanceDegree;
    }
    private void setSkillsList(List<String> skills) {
        if(skills == null) {
            this.skills = null;
            return;
        }
        if(skills.isEmpty()){
            throw new IllegalArgumentException("Skills list cannot be empty");
        }
        this.skills = new ArrayList<>();

        for (String skill:skills) {
            setSkill(skill);
        }
    }
    private void setSkill(String skill) {
        if(skill == null || skill.isBlank()){
            throw new IllegalArgumentException("each skill cannot be null or blank");
        }
        this.skills.add(skill);
    }

    // =========== GETTERY ===========
    public List<CandidateApplication> getCandidateApplicationList() {
        return Collections.unmodifiableList(candidateApplicationList);
    }
    public String getCvCandidate() {
        return cvCandidate;
    }
    public ENUMS.CandidateApplicationType getApplicationType() {
        return applicationType;
    }
    // ---------- PRIORITY -----------
    public String getRecommendingPerson() {
        if(this.applicationType!= ENUMS.CandidateApplicationType.PRIORITY){
            throw new IllegalStateException("To get Recommending Person type has to be PRIORITY");
        }
        return recommendingPerson;
    }
    public String getAcquaintanceDegree() {
        if(this.applicationType!= ENUMS.CandidateApplicationType.PRIORITY){
            throw new IllegalStateException("To get Acquaintance Degree type has to be PRIORITY");
        }
        return acquaintanceDegree;
    }
    // ---------- NORMAL -----------
    public List<String> getSkills(){
        if(this.applicationType!= ENUMS.CandidateApplicationType.NORMAL){
            throw new IllegalStateException("To get Skills list type has to be NORMAL");
        }
        return Collections.unmodifiableList(this.skills);
    }

    // ============== toString ===================

    @Override
    public String toString() {
        return "CandidateApplication{ CvCandidate: "+this.cvCandidate+", ApplicationType: "+this.applicationType.toString()+
                ((this.applicationType== ENUMS.CandidateApplicationType.PRIORITY)?
                        "\nRecommending person: "+this.recommendingPerson+", acquaintance degree: "+this.acquaintanceDegree:
                        "\nSkills: "+this.skills.toString()) + "}";
    }
}
