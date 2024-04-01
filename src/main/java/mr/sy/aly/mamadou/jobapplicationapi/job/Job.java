package mr.sy.aly.mamadou.jobapplicationapi.job;

import jakarta.persistence.*;
import mr.sy.aly.mamadou.jobapplicationapi.company.Company;

@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Title;
    private String Description;
    private Double minimumSalary;
    private Double maximumSalary;
    private String location;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @ManyToOne
    private Company company;

    public Job() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Double getMinimumSalary() {
        return minimumSalary;
    }

    public void setMinimumSalary(Double minimumSalary) {
        this.minimumSalary = minimumSalary;
    }

    public Double getMaximumSalary() {
        return maximumSalary;
    }

    public void setMaximumSalary(Double maximumSalary) {
        this.maximumSalary = maximumSalary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
