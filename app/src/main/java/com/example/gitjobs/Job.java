package com.example.gitjobs;

import java.io.Serializable;
import java.util.Date;

public class Job implements Serializable {
    private String title;
    private String date;
    private String type;
    private String company;
    private String location;
    private String description;
    String companyLogo;

    public Job(String title, String date, String type, String company, String location, String description, String companyLogo){
        this.title = title;
        this.date = date;
        this.type = type;
        this.company = company;
        this.location = location;
        this.description = description;
        this.companyLogo = companyLogo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
