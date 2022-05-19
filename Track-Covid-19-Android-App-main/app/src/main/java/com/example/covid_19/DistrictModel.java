package com.example.covid_19;

public class DistrictModel {

    private String districtName, districtTotalCases, districtActive,districtTotalDeaths, districtRecovered;

    public DistrictModel() {
    }

    public DistrictModel(String districtName, String districtTotalCases, String districtActive, String districtTotalDeaths, String districtRecovered) {
        this.districtName = districtName;
        this.districtTotalCases = districtTotalCases;
        this.districtActive = districtActive;
        this.districtTotalDeaths = districtTotalDeaths;
        this.districtRecovered = districtRecovered;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrictTotalCases() {
        return districtTotalCases;
    }

    public void setDistrictTotalCases(String districtTotalCases) {
        this.districtTotalCases = districtTotalCases;
    }

    public String getDistrictActive() {
        return districtActive;
    }

    public void setDistrictActive(String districtActive) {
        this.districtActive = districtActive;
    }

    public String getDistrictTotalDeaths() {
        return districtTotalDeaths;
    }

    public void setDistrictTotalDeaths(String districtTotalDeaths) {
        this.districtTotalDeaths = districtTotalDeaths;
    }

    public String getDistrictRecovered() {
        return districtRecovered;
    }

    public void setDistrictRecovered(String districtRecovered) {
        this.districtRecovered = districtRecovered;
    }
}