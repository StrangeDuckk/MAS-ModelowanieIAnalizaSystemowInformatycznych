import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Job {
    private static ArrayList<Job> jobs = new ArrayList<>();

    private String jobTitle;
    private String department;//It, accounting, hr, itp
    private String shortInfo;
    private String degree; //mid/senior/junior

    public Job(
            String jobTitle,
            String department,
            String shortInfo,
            String degree
    ){
        setJobTitle(jobTitle);
        setDepartment(department);
        setShortInfo(shortInfo);
        setDegree(degree);

        jobs.add(this);
    }
    public Job(
            String jobTitle,
            String department,
            String shortInfo
    ){
        setJobTitle(jobTitle);
        setDepartment(department);
        setShortInfo(shortInfo);
        setDegree(null);

        jobs.add(this);
    }

    public String getJobTitle() {
        return jobTitle;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getShortInfo() {
        return shortInfo;
    }
    public void setShortInfo(String shortInfo) {
        this.shortInfo = shortInfo;
    }
    public String getDegree() {
        return degree;
    }
    public void setDegree(String degree) {
        this.degree = degree;
    }
    public static List<Job> getJobs() {
        return Collections.unmodifiableList(jobs);//todo zobaczyc czy gut czy zwrocenie kopii
    }

    @Override
    public String toString() {
        return "Job:" + jobTitle
                + ", at department:" + department
                + ", " + shortInfo
                + (!this.degree.equals("NONE")?", degree='" + degree +'.':".");
    }
}
