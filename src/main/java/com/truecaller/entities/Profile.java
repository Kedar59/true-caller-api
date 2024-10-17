package com.truecaller.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection="Profile")
@CompoundIndexes({
        @CompoundIndex(name = "phone_country_unique", def = "{'phoneNumber': 1, 'countryCode': 1}", unique = true)
})
public class Profile {
    @Id
    private String id;

    private String phoneNumber;

    private String countryCode;

    private String name;
    private boolean isVerified;

    private String location;
    private int numberOfSpamCallReports;
    private int numberOfSpamSMSReports;

    private Date timestamp;

    public Profile() {
    }

    public Profile(String id, String phoneNumber, String countryCode, String name,
                   boolean isVerified, String location, int numberOfSpamCallReports,
                   int numberOfSpamSMSReports, Date timestamp) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.countryCode = countryCode;
        this.name = name;
        this.isVerified = isVerified;
        this.location = location;
        this.numberOfSpamCallReports = numberOfSpamCallReports;
        this.numberOfSpamSMSReports = numberOfSpamSMSReports;
        this.timestamp = timestamp;
    }
    public Profile(String phoneNumber,String countryCode,int numberOfSpamCallReports,int numberOfSpamSMSReports){
        this.phoneNumber = phoneNumber;
        this.countryCode = countryCode;
        this.numberOfSpamCallReports = numberOfSpamCallReports;
        this.numberOfSpamSMSReports = numberOfSpamSMSReports;
        this.name = "UNKNOWN";
        this.location = "UNKNOWN";
        this.isVerified = false;
        this.timestamp = new Date();
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNumberOfSpamCallReports() {
        return numberOfSpamCallReports;
    }

    public void setNumberOfSpamCallReports(int numberOfSpamCallReports) {
        this.numberOfSpamCallReports = numberOfSpamCallReports;
    }

    public int getNumberOfSpamSMSReports() {
        return numberOfSpamSMSReports;
    }

    public void setNumberOfSpamSMSReports(int numberOfSpamSMSReports) {
        this.numberOfSpamSMSReports = numberOfSpamSMSReports;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}