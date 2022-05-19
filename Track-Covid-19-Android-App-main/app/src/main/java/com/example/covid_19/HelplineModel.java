package com.example.covid_19;

public class HelplineModel{
    private String stateCodeHelpline , stateNameHelpline;
    private String phone;

    public HelplineModel(String stateCodeHelpline, String stateNameHelpline, String phone) {
        this.stateCodeHelpline = stateCodeHelpline;
        this.stateNameHelpline = stateNameHelpline;
        this.phone = phone;
    }

    public String getStateCodeHelpline() {
        return stateCodeHelpline;
    }

    public void setStateCodeHelpline(String stateCodeHelpline) {
        this.stateCodeHelpline = stateCodeHelpline;
    }

    public String getStateNameHelpline() {
        return stateNameHelpline;
    }

    public void setStateNameHelpline(String stateNameHelpline) {
        this.stateNameHelpline = stateNameHelpline;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
