package com.truecaller.projections;

public class ValidateOtpDTO {
    private String mobileNumber;
    private String otp;

    public ValidateOtpDTO(){}
    public ValidateOtpDTO(String mobileNumber, String otp) {
        this.mobileNumber = mobileNumber;
        this.otp = otp;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
