package com.bugreport.bugreport;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BugReport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    public Integer pageId;

    public String title;

    public String description;

    public Integer userId;

    public BugReport() {
    }
    
    public BugReport(String title, String description, Integer pageId, Integer userId) {
        this.title = title;
        this.description = description;
        this.pageId = pageId;
        this.userId = userId;
    }

    public BugReport(BugReportDto br) {
        this.id = br.id;
        this.pageId = br.pageId;
        this.title = br.title;
        this.description = br.description;
        this.userId = br.userId;
    }
}
