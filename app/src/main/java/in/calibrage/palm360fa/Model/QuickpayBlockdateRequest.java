package in.calibrage.palm360fa.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuickpayBlockdateRequest {

    @SerializedName("stateCode")
    @Expose
    private String stateCode;
    @SerializedName("isQuickPayBlocked")
    @Expose
    private Boolean isQuickPayBlocked;
    @SerializedName("districtId")
    @Expose
    private Integer districtId;
    @SerializedName("docDate")
    @Expose
    private String docDate;

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public Boolean getIsQuickPayBlocked() {
        return isQuickPayBlocked;
    }

    public void setIsQuickPayBlocked(Boolean isQuickPayBlocked) {
        this.isQuickPayBlocked = isQuickPayBlocked;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public String getDocDate() {
        return docDate;
    }

    public void setDocDate(String docDate) {
        this.docDate = docDate;
    }

}
