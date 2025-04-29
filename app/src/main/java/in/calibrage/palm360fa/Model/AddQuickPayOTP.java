package in.calibrage.palm360fa.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddQuickPayOTP {

    @SerializedName("farmerCode")
    @Expose
    private String farmerCode;
    @SerializedName("farmerName")
    @Expose
    private String farmerName;
    @SerializedName("phoneNumbers")
    @Expose
    private String phoneNumbers;
    @SerializedName("otp")
    @Expose
    private Integer otp;
    @SerializedName("createdByUserId")
    @Expose
    private Integer createdByUserId;

    public String getFarmerCode() {
        return farmerCode;
    }

    public void setFarmerCode(String farmerCode) {
        this.farmerCode = farmerCode;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public String getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(String phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public Integer getOtp() {
        return otp;
    }

    public void setOtp(Integer otp) {
        this.otp = otp;
    }

    public Integer getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(Integer createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

}
