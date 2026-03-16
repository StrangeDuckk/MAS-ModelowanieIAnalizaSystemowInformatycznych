import java.util.ArrayList;

public class Experience {
    private static ArrayList<Experience> experiences = new ArrayList<>();

    private double years;
    private String companyName;
    private String jobTitle;
    private String degree;
    private String shortInfo;

    public Experience(
            double years,
            String companyName,
            String jobTitle,
            String degree,
            String shortInfo
    ){
        setYears(years);
        setCompanyName(companyName);
        setJobTitle(jobTitle);
        setDegree(degree);
        setShortInfo(shortInfo);

        experiences.add(this);
        //todo zapis do pliku bin
    }

    public static ArrayList<Experience> getExperiences() {
        return experiences;
    }

    public double getYears() {
        return years;
    }
    public void setYears(double years) {
        this.years = years;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getJobTitle() {
        return jobTitle;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    public String getDegree() {
        return degree;
    }
    public void setDegree(String degree) {
        this.degree = degree;
    }
    public String getShortInfo() {
        return shortInfo;
    }
    public void setShortInfo(String shortInfo) {
        this.shortInfo = shortInfo;
    }
}
