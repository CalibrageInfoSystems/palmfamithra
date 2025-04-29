package in.calibrage.palm360fa.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoanRequest {

    @SerializedName("requestTypeId")
    @Expose
    private Integer requestTypeId;
    @SerializedName("farmerCode")
    @Expose
    private String farmerCode;
    @SerializedName("farmerName")
    @Expose
    private String farmerName;
    @SerializedName("plotCode")
    @Expose
    private String plotCode;
    @SerializedName("requestCreatedDate")
    @Expose
    private String requestCreatedDate;
    @SerializedName("statusTypeId")
    @Expose
    private Integer statusTypeId;
    @SerializedName("isFarmerRequest")
    @Expose
    private Boolean isFarmerRequest;
    @SerializedName("createdByUserId")
    @Expose
    private Object createdByUserId;
    @SerializedName("createdDate")
    @Expose
    private String createdDate;
    @SerializedName("updatedByUserId")
    @Expose
    private Object updatedByUserId;
    @SerializedName("updatedDate")
    @Expose
    private String updatedDate;
    @SerializedName("totalCost")
    @Expose
    private Double totalCost;
    @SerializedName("comments")
    @Expose
    private String comments;
    @SerializedName("cropMaintainceDate")
    @Expose
    private String cropMaintainceDate;
    @SerializedName("clusterId")
    @Expose
    private Integer clusterId;

    @SerializedName("stateCode")
    @Expose
    private String stateCode;
    @SerializedName("stateName")
    @Expose
    private String stateName;
    public Integer getRequestTypeId() {
        return requestTypeId;
    }

    public void setRequestTypeId(Integer requestTypeId) {
        this.requestTypeId = requestTypeId;
    }

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


    public String getPlotCode() {
        return plotCode;
    }

    public void setPlotCode(String plotCode) {
        this.plotCode = plotCode;
    }

    public String getRequestCreatedDate() {
        return requestCreatedDate;
    }

    public void setRequestCreatedDate(String requestCreatedDate) {
        this.requestCreatedDate = requestCreatedDate;
    }

    public Integer getStatusTypeId() {
        return statusTypeId;
    }

    public void setStatusTypeId(Integer statusTypeId) {
        this.statusTypeId = statusTypeId;
    }

    public Boolean getIsFarmerRequest() {
        return isFarmerRequest;
    }

    public void setIsFarmerRequest(Boolean isFarmerRequest) {
        this.isFarmerRequest = isFarmerRequest;
    }

    public Object getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(Object createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Object getUpdatedByUserId() {
        return updatedByUserId;
    }

    public void setUpdatedByUserId(Object updatedByUserId) {
        this.updatedByUserId = updatedByUserId;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCropMaintainceDate() {
        return cropMaintainceDate;
    }

    public void setCropMaintainceDate(String cropMaintainceDate) {
        this.cropMaintainceDate = cropMaintainceDate;
    }

    public Integer getClusterId() {
        return clusterId;
    }

    public void setClusterId(Integer clusterId) {
        this.clusterId = clusterId;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
}