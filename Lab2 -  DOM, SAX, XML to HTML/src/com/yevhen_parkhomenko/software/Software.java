package com.yevhen_parkhomenko.software;

public class Software {
    private String title;
    private String annotation;
    private String type;
    private String version;
    private String author;
    private String termsOfUse;
    private String location;

    public Software(String title, String annotation, String type, String version, String author, String termsOfUse, String location) {
        this.title = title;
        this.annotation = annotation;
        this.type = type;
        this.version = version;
        this.author = author;
        this.termsOfUse = termsOfUse;
        this.location = location;
    }

    public Software(){}

    public String getTitle() {
        return title;
    }

    public String getAnnotation() {
        return annotation;
    }

    public String getType() { return type; }

    public String getVersion() { return version; }

    public String getAuthor() {
        return author;
    }

    public String getTermsOfUse() { return termsOfUse; }

    public String getLocation() {
        return location;
    }
}
