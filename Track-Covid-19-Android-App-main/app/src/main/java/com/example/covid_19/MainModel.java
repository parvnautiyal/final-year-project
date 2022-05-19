package com.example.covid_19;

public class MainModel {
    Integer picLogo;
    String symptomName;

    public MainModel(Integer picLogo, String symptomName) {
        this.picLogo = picLogo;
        this.symptomName = symptomName;
    }

    public Integer getPicLogo() {
        return picLogo;
    }

    public void setPicLogo(Integer picLogo) {
        this.picLogo = picLogo;
    }

    public String getSymptomName() {
        return symptomName;
    }

    public void setSymptomName(String symptomName) {
        this.symptomName = symptomName;
    }
}
