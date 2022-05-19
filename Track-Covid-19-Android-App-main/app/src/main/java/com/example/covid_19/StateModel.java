package com.example.covid_19;

public class StateModel {

    private String totalcases , totaldeaths , active , recovered, statecode,state;

    public StateModel() {
    }

    public StateModel(String state,String totalcases, String totaldeaths, String active, String recovered, String statecode) {
        this.state = state;
        this.totalcases = totalcases;
        this.totaldeaths = totaldeaths;
        this.active = active;
        this.recovered = recovered;
        this.statecode = statecode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStatecode() {
        return statecode;
    }

    public void setStatecode(String statecode) {
        this.statecode = statecode;
    }

    public String getTotalcases() {
        return totalcases;
    }

    public void setTotalcases(String totalcases) {
        this.totalcases = totalcases;
    }

    public String getTotaldeaths() {
        return totaldeaths;
    }

    public void setTotaldeaths(String totaldeaths) {
        this.totaldeaths = totaldeaths;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }
}
