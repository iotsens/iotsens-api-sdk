package com.iotsens.sdk.sensors;

public class SourceTemplate {

    private Integer id;
    private String templateName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    @Override
    public String toString() {
        return "SourceTemplate{" +
                "id=" + id +
                ", templateName='" + templateName + '\'' +
                '}';
    }
}
