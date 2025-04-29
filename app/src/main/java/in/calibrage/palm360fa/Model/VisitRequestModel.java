package in.calibrage.palm360fa.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VisitRequestModel {

    @SerializedName("requestHeader")
    @Expose
    private RequestHeader requestHeader;
    @SerializedName("visitRepo")
    @Expose
    private List<VisitRepo> visitRepo = null;
    @SerializedName("reason")
    @Expose
    private String reason;

    public RequestHeader getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader(RequestHeader requestHeader) {
        this.requestHeader = requestHeader;
    }

    public List<VisitRepo> getVisitRepo() {
        return visitRepo;
    }

    public void setVisitRepo(List<VisitRepo> visitRepo) {
        this.visitRepo = visitRepo;
    }
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


    public static class RequestHeader {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("requestCode")
        @Expose
        private Object requestCode;
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
        @SerializedName("plotVillage")
        @Expose
        private String plotVillage;
        @SerializedName("palmArea")
        @Expose
        private String palmArea;
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
        @SerializedName("yearofPlanting")
        @Expose
        private String yearofPlanting;
        @SerializedName("totalCost")
        @Expose
        private Double totalCost;
        @SerializedName("comments")
        @Expose
        private Object comments;
        @SerializedName("cropMaintainceDate")
        @Expose
        private String cropMaintainceDate;
        @SerializedName("issueTypeId")
        @Expose
        private Integer issueTypeId;

        @SerializedName("clusterId")
        @Expose
        private Integer clusterId;
        @SerializedName("stateCode")
        @Expose
        private String stateCode;
        @SerializedName("stateName")
        @Expose
        private String stateName;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Object getRequestCode() {
            return requestCode;
        }

        public void setRequestCode(Object requestCode) {
            this.requestCode = requestCode;
        }

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

        public Object getComments() {
            return comments;
        }

        public void setComments(Object comments) {
            this.comments = comments;
        }

        public String getCropMaintainceDate() {
            return cropMaintainceDate;
        }

        public void setCropMaintainceDate(String cropMaintainceDate) {
            this.cropMaintainceDate = cropMaintainceDate;
        }

        public Integer getIssueTypeId() {
            return issueTypeId;
        }

        public void setIssueTypeId(Integer issueTypeId) {
            this.issueTypeId = issueTypeId;
        }

        public RequestHeader() {
            this.id = id;
            this.requestCode = requestCode;
            this.requestTypeId = requestTypeId;
            this.farmerCode = farmerCode;
            this.plotCode = plotCode;
            this.reqCreatedDate = reqCreatedDate;
            this.statusTypeId = statusTypeId;
            this.isFarmerRequest = isFarmerRequest;
            this.createdByUserId = createdByUserId;
            this.createdDate = createdDate;
            this.updatedByUserId = updatedByUserId;
            this.updatedDate = updatedDate;
            this.totalCost = totalCost;
            this.comments = comments;
            this.cropMaintainceDate = cropMaintainceDate;
            this.issueTypeId = issueTypeId;
        }

        public String getPlotVillage() {
            return plotVillage;
        }

        public void setPlotVillage(String plotVillage) {
            this.plotVillage = plotVillage;
        }

        public String getPalmArea() {
            return palmArea;
        }

        public void setPalmArea(String palmArea) {
            this.palmArea = palmArea;
        }

        public String getYearofPlanting() {
            return yearofPlanting;
        }

        public void setYearofPlanting(String yearofPlanting) {
            this.yearofPlanting = yearofPlanting;
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

    public static class VisitRepo {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("requestCode")
        @Expose
        private Object requestCode;
        @SerializedName("fileLocation")
        @Expose
        private Object fileLocation;
        @SerializedName("fileName")
        @Expose
        private String fileName;
        @SerializedName("fileExtension")
        @Expose
        private String fileExtension;
        @SerializedName("isActive")
        @Expose
        private Boolean isActive;
        @SerializedName("createdByUserId")
        @Expose
        private Integer createdByUserId;
        @SerializedName("createdDate")
        @Expose
        private String createdDate;
        @SerializedName("fileTypeId")
        @Expose
        private Integer fileTypeId;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Object getRequestCode() {
            return requestCode;
        }

        public void setRequestCode(Object requestCode) {
            this.requestCode = requestCode;
        }

        public Object getFileLocation() {
            return fileLocation;
        }

        public void setFileLocation(Object fileLocation) {
            this.fileLocation = fileLocation;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getFileExtension() {
            return fileExtension;
        }

        public void setFileExtension(String fileExtension) {
            this.fileExtension = fileExtension;
        }

        public Boolean getIsActive() {
            return isActive;
        }

        public void setIsActive(Boolean isActive) {
            this.isActive = isActive;
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

        public Integer getFileTypeId() {
            return fileTypeId;
        }

        public void setFileTypeId(Integer fileTypeId) {
            this.fileTypeId = fileTypeId;
        }


        public VisitRepo( ) {
            this.id = id;
            this.requestCode = requestCode;
            this.fileLocation = fileLocation;
            this.fileName = fileName;
            this.fileExtension = fileExtension;
            this.isActive = isActive;
            this.createdByUserId = createdByUserId;
            this.createdDate = createdDate;
            this.fileTypeId = fileTypeId;
        }
    }


    public VisitRequestModel(RequestHeader requestHeader, List<VisitRepo> visitRepo) {
        this.requestHeader = requestHeader;
        this.visitRepo = visitRepo;
    }

    public VisitRequestModel() {
    }
}