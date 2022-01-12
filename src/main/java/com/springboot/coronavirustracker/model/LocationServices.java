package com.springboot.coronavirustracker.model;

public class LocationServices {
    private String state;
    private String Country;
    private String latestTotalCases;
    private String diffFromPreviousDay;

    public String getDiffFromPreviousDay() {
        return diffFromPreviousDay;
    }

    public void setDiffFromPreviousDay(String diffFromPreviousDay) {
        this.diffFromPreviousDay = diffFromPreviousDay;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getLatestTotalCases() {
        return latestTotalCases;
    }

    public void setLatestTotalCases(String latestTotalCases) {
        this.latestTotalCases = latestTotalCases;
    }

    @Override
    public String toString() {
        return "LocationServices{" +
                "state='" + state + '\'' +
                ", Country='" + Country + '\'' +
                ", latestTotalCases='" + latestTotalCases + '\'' +
                '}';
    }
}
