import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Job implements Serializable {
    private static List<Job> jobs = new ArrayList<>();

    private String jobTitle;
    private String department;//It, accounting, hr, itp
    private String shortInfo;
    private String degree; //example: mid/senior/junior

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
        if (jobTitle == null || jobTitle.isEmpty()){
            throw new IllegalArgumentException("Argument has to have any value");
        }
        this.jobTitle = jobTitle;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        if (department == null || department.isEmpty()){
            throw new IllegalArgumentException("Argument has to have any value");
        }
        this.department = department;
    }
    public String getShortInfo() {
        return shortInfo;
    }
    public void setShortInfo(String shortInfo) {
        if (shortInfo == null || shortInfo.isEmpty()){
            throw new IllegalArgumentException("Argument has to have any value");
        }
        this.shortInfo = shortInfo;
    }
    public String getDegree() {
        return degree;
    }
    public void setDegree(String degree) {
        this.degree = degree;
    }
    public static List<Job> getJobs() {
        return Collections.unmodifiableList(jobs);
    }

    @Override
    public String toString() {
        return "Job:" + jobTitle
                + ", at department:" + department
                + ", " + shortInfo
                + ((this.degree != null) ?", degree='" + degree +'.':".");
    }
    // ------------------ Serializacja -------------------
    public static void writeExtentJobs(ObjectOutputStream stream) throws IOException {
        stream.writeObject(jobs);
    }
    public static void readExtentJobs(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        jobs = (ArrayList<Job>) stream.readObject();
    }
}
