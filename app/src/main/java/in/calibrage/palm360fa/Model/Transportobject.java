package in.calibrage.palm360fa.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Transportobject {

    @SerializedName("transportTypeId")
    @Expose
    private Integer transportTypeId;
    @SerializedName("vehicleTypeId")
    @Expose
    private Integer vehicleTypeId;
    @SerializedName("vehicleNumber")
    @Expose
    private String vehicleNumber;
    @SerializedName("driverName")
    @Expose
    private String driverName;
    @SerializedName("godownId")
    @Expose
    private String godownId;
    @SerializedName("nurseryId")
    @Expose
    private String nurseryId;
    @SerializedName("toLocation")
    @Expose
    private Integer toLocation;
    @SerializedName("paymentTypeId")
    @Expose
    private Integer paymentTypeId;
    @SerializedName("plotTransportDetails")
    @Expose
    private List<PlotTransportDetail> plotTransportDetails = null;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("requestCode")
    @Expose
    private String requestCode;
    @SerializedName("farmerCode")
    @Expose
    private String farmerCode;
    @SerializedName("plotCode")
    @Expose
    private String plotCode;
    @SerializedName("reqCreatedDate")
    @Expose
    private String reqCreatedDate;
    @SerializedName("statusTypeId")
    @Expose
    private Integer statusTypeId;
    @SerializedName("isFarmerRequest")
    @Expose
    private Boolean isFarmerRequest;
    @SerializedName("createdByUserId")
    @Expose
    private Integer createdByUserId;
    @SerializedName("createdDate")
    @Expose
    private String createdDate;
    @SerializedName("updatedByUserId")
    @Expose
    private Integer updatedByUserId;
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
    @SerializedName("requestTypeId")
    @Expose
    private Integer requestTypeId;
    @SerializedName("issueTypeId")
    @Expose
    private Integer issueTypeId;
    @SerializedName("farmerName")
    @Expose
    private String farmerName;
    @SerializedName("plotVillage")
    @Expose
    private String plotVillage;
    @SerializedName("palmArea")
    @Expose
    private Double palmArea;
    @SerializedName("serverUpdatedStatus")
    @Expose
    private Boolean serverUpdatedStatus;
    @SerializedName("yearofPlanting")
    @Expose
    private String yearofPlanting;
    @SerializedName("totalCostWithoutServiceCharge")
    @Expose
    private Double totalCostWithoutServiceCharge;
    @SerializedName("serviceChargeAmount")
    @Expose
    private Double serviceChargeAmount;
    @SerializedName("parentRequestCode")
    @Expose
    private String parentRequestCode;
    @SerializedName("recoveryFarmerCode")
    @Expose
    private String recoveryFarmerCode;
    @SerializedName("clusterId")
    @Expose
    private Integer clusterId;
    @SerializedName("stateCode")
    @Expose
    private String stateCode;
    @SerializedName("stateName")
    @Expose
    private String stateName;

    public Integer getTransportTypeId() {
        return transportTypeId;
    }

    public void setTransportTypeId(Integer transportTypeId) {
        this.transportTypeId = transportTypeId;
    }

    public Integer getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(Integer vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getGodownId() {
        return godownId;
    }

    public void setGodownId(String godownId) {
        this.godownId = godownId;
    }

    public String getNurseryId() {
        return nurseryId;
    }

    public void setNurseryId(String nurseryId) {
        this.nurseryId = nurseryId;
    }

    public Integer getToLocation() {
        return toLocation;
    }

    public void setToLocation(Integer toLocation) {
        this.toLocation = toLocation;
    }

    public Integer getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(Integer paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public List<PlotTransportDetail> getPlotTransportDetails() {
        return plotTransportDetails;
    }

    public void setPlotTransportDetails(List<PlotTransportDetail> plotTransportDetails) {
        this.plotTransportDetails = plotTransportDetails;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }

    public String getFarmerCode() {
        return farmerCode;
    }

    public void setFarmerCode(String farmerCode) {
        this.farmerCode = farmerCode;
    }

    public String getPlotCode() {
        return plotCode;
    }

    public void setPlotCode(String plotCode) {
        this.plotCode = plotCode;
    }

    public String getReqCreatedDate() {
        return reqCreatedDate;
    }

    public void setReqCreatedDate(String reqCreatedDate) {
        this.reqCreatedDate = reqCreatedDate;
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

    public Integer getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(Integer createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getUpdatedByUserId() {
        return updatedByUserId;
    }

    public void setUpdatedByUserId(Integer updatedByUserId) {
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

    public Integer getRequestTypeId() {
        return requestTypeId;
    }

    public void setRequestTypeId(Integer requestTypeId) {
        this.requestTypeId = requestTypeId;
    }

    public Integer getIssueTypeId() {
        return issueTypeId;
    }

    public void setIssueTypeId(Integer issueTypeId) {
        this.issueTypeId = issueTypeId;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public String getPlotVillage() {
        return plotVillage;
    }

    public void setPlotVillage(String plotVillage) {
        this.plotVillage = plotVillage;
    }

    public Double getPalmArea() {
        return palmArea;
    }

    public void setPalmArea(Double palmArea) {
        this.palmArea = palmArea;
    }

    public Boolean getServerUpdatedStatus() {
        return serverUpdatedStatus;
    }

    public void setServerUpdatedStatus(Boolean serverUpdatedStatus) {
        this.serverUpdatedStatus = serverUpdatedStatus;
    }

    public String getYearofPlanting() {
        return yearofPlanting;
    }

    public void setYearofPlanting(String yearofPlanting) {
        this.yearofPlanting = yearofPlanting;
    }

    public Double getTotalCostWithoutServiceCharge() {
        return totalCostWithoutServiceCharge;
    }

    public void setTotalCostWithoutServiceCharge(Double totalCostWithoutServiceCharge) {
        this.totalCostWithoutServiceCharge = totalCostWithoutServiceCharge;
    }

    public Double getServiceChargeAmount() {
        return serviceChargeAmount;
    }

    public void setServiceChargeAmount(Double serviceChargeAmount) {
        this.serviceChargeAmount = serviceChargeAmount;
    }

    public String getParentRequestCode() {
        return parentRequestCode;
    }

    public void setParentRequestCode(String parentRequestCode) {
        this.parentRequestCode = parentRequestCode;
    }

    public String getRecoveryFarmerCode() {
        return recoveryFarmerCode;
    }

    public void setRecoveryFarmerCode(String recoveryFarmerCode) {
        this.recoveryFarmerCode = recoveryFarmerCode;
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

    public void setRequestplotDetails(List<PlotTransportDetail> plotRepo) {
        this.plotTransportDetails = plotRepo;
    }

    public static class PlotTransportDetail {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("requestCode")
        @Expose
        private String requestCode;
        @SerializedName("plotCode")
        @Expose
        private String plotCode;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getRequestCode() {
            return requestCode;
        }

        public void setRequestCode(String requestCode) {
            this.requestCode = requestCode;
        }

        public String getPlotCode() {
            return plotCode;
        }

        public void setPlotCode(String plotCode) {
            this.plotCode = plotCode;
        }

    }
}