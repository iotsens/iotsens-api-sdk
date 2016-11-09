package com.iotsens.sdk.sensors;

public class SensorBasic {

    private String uniqueId;
    private boolean enable;
    private Double longitude;
    private Double latitude;
    private Category category;
    private SourceTemplate sourceTemplate;
    private Integer sourceTemplateVersion;

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getSourceTemplateVersion() {
        return sourceTemplateVersion;
    }

    public void setSourceTemplateVersion(Integer sourceTemplateVersion) {
        this.sourceTemplateVersion = sourceTemplateVersion;
    }

    public SourceTemplate getSourceTemplate() {
        return sourceTemplate;
    }

    public void setSourceTemplate(SourceTemplate sourceTemplate) {
        this.sourceTemplate = sourceTemplate;
    }

    @Override
    public String toString() {
        return "SensorBasic{" +
                "uniqueId='" + uniqueId + '\'' +
                ", enable=" + enable +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", category=" + category +
                ", sourceTemplate=" + sourceTemplate +
                ", sourceTemplateVersion=" + sourceTemplateVersion +
                '}';
    }
}