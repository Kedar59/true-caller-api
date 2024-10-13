package com.truecaller.entities;

import com.truecaller.projections.CallerID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "SpamReport")
public class SpamReport {

    @Id
    private String id;
    private String reporterNumber;
    private String reporterCountryCode;
    private String spammerNumber;
    private String spammerCountryCode;

    private boolean isSpamSMS;
    private boolean isSpamCall;

    private String reason;
    private Date reportTimestamp;

    // Default constructor
    public SpamReport() {
    }

    public SpamReport(String id, String reporterNumber, String reporterCountryCode, String spammerNumber,
                      String spammerCountryCode, boolean isSpamSMS, boolean isSpamCall,
                      String reason, Date reportTimestamp) {
        this.id = id;
        this.reporterNumber = reporterNumber;
        this.reporterCountryCode = reporterCountryCode;
        this.spammerNumber = spammerNumber;
        this.spammerCountryCode = spammerCountryCode;
        this.isSpamSMS = isSpamSMS;
        this.isSpamCall = isSpamCall;
        this.reason = reason;
        this.reportTimestamp = reportTimestamp;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReporterNumber() {
        return reporterNumber;
    }

    public void setReporterNumber(String reporterNumber) {
        this.reporterNumber = reporterNumber;
    }

    public String getReporterCountryCode() {
        return reporterCountryCode;
    }

    public void setReporterCountryCode(String reporterCountryCode) {
        this.reporterCountryCode = reporterCountryCode;
    }

    public String getSpammerNumber() {
        return spammerNumber;
    }

    public void setSpammerNumber(String spammerNumber) {
        this.spammerNumber = spammerNumber;
    }

    public String getSpammerCountryCode() {
        return spammerCountryCode;
    }

    public void setSpammerCountryCode(String spammerCountryCode) {
        this.spammerCountryCode = spammerCountryCode;
    }

    public boolean isSpamSMS() {
        return isSpamSMS;
    }

    public void setSpamSMS(boolean spamSMS) {
        isSpamSMS = spamSMS;
    }

    public boolean isSpamCall() {
        return isSpamCall;
    }

    public void setSpamCall(boolean spamCall) {
        isSpamCall = spamCall;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getReportTimestamp() {
        return reportTimestamp;
    }

    public void setReportTimestamp(Date reportTimestamp) {
        this.reportTimestamp = reportTimestamp;
    }
}