package com.truecaller.projections;

public class CallerID {
    private String number;
    private String countryCode;

    public CallerID(){}
    public CallerID(String number, String countryCode) {
        this.number = number;
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "CallerID{" +
                "number='" + number + '\'' +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
