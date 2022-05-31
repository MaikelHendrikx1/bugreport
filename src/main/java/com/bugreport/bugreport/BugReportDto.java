package com.bugreport.bugreport;

public class BugReportDto {
    public Integer id;

    public Integer pageId;

    public String title;

    public String description;

    public Integer userId;

    public BugReportDto() {
    }

    public BugReportDto(String title, String description, Integer pageId, Integer userId) {
        this.title = title;
        this.description = description;
        this.pageId = pageId;
        this.userId = userId;
    }

    public BugReportDto(BugReport br) {
        this.id = br.id;
        this.pageId = br.pageId;
        this.title = br.title;
        this.description = br.description;
        this.userId = br.userId;
    }
}
