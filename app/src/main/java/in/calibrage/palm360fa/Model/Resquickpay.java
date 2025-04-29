

package in.calibrage.palm360fa.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Resquickpay {
    @SerializedName("listResult")
    @Expose
    private List<ListResult> listResult = null;
    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("affectedRecords")
    @Expose
    private Integer affectedRecords;
    @SerializedName("endUserMessage")
    @Expose
    private String endUserMessage;
    @SerializedName("validationErrors")
    @Expose
    private List<Object> validationErrors = null;
    @SerializedName("exception")
    @Expose
    private Object exception;

    public List<ListResult> getListResult() {
        return listResult;
    }

    public void setListResult(List<ListResult> listResult) {
        this.listResult = listResult;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Integer getAffectedRecords() {
        return affectedRecords;
    }

    public void setAffectedRecords(Integer affectedRecords) {
        this.affectedRecords = affectedRecords;
    }

    public String getEndUserMessage() {
        return endUserMessage;
    }

    public void setEndUserMessage(String endUserMessage) {
        this.endUserMessage = endUserMessage;
    }

    public List<Object> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(List<Object> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public Object getException() {
        return exception;
    }

    public void setException(Object exception) {
        this.exception = exception;
    }


    public class ListResult {

        @SerializedName("requestCode")
        @Expose
        private String requestCode;
        @SerializedName("farmerName")
        @Expose
        private String farmerName;
        @SerializedName("requestTypeId")
        @Expose
        private Integer requestTypeId;
        @SerializedName("requestType")
        @Expose
        private String requestType;
        @SerializedName("farmerCode")
        @Expose
        private String farmerCode;
        @SerializedName("plotCode")
        @Expose
        private Object plotCode;
        @SerializedName("reqCreatedDate")
        @Expose
        private String reqCreatedDate;
        @SerializedName("statusTypeId")
        @Expose
        private Integer statusTypeId;
        @SerializedName("statusType")
        @Expose
        private String statusType;
        @SerializedName("isFarmerRequest")
        @Expose
        private Boolean isFarmerRequest;
        @SerializedName("totalCost")
        @Expose
        private Double totalCost;
        @SerializedName("comments")
        @Expose
        private Object comments;
        @SerializedName("cropMaintainceDate")
        @Expose
        private Object cropMaintainceDate;
        @SerializedName("issueTypeId")
        @Expose
        private Object issueTypeId;
        @SerializedName("plotVillage")
        @Expose
        private Object plotVillage;
        @SerializedName("palmArea")
        @Expose
        private Object palmArea;
        @SerializedName("issueType")
        @Expose
        private Object issueType;
        @SerializedName("isHavingImage")
        @Expose
        private String isHavingImage;
        @SerializedName("isHavingAudio")
        @Expose
        private String isHavingAudio;
        @SerializedName("fileExtension")
        @Expose
        private String fileExtension;
        @SerializedName("fileLocation")
        @Expose
        private String fileLocation;
        @SerializedName("fileName")
        @Expose
        private String fileName;
        @SerializedName("fileUrl")
        @Expose
        private String fileUrl;

        public String getRequestCode() {
            return requestCode;
        }

        public void setRequestCode(String requestCode) {
            this.requestCode = requestCode;
        }

        public String getFarmerName() {
            return farmerName;
        }

        public void setFarmerName(String farmerName) {
            this.farmerName = farmerName;
        }

        public Integer getRequestTypeId() {
            return requestTypeId;
        }

        public void setRequestTypeId(Integer requestTypeId) {
            this.requestTypeId = requestTypeId;
        }

        public String getRequestType() {
            return requestType;
        }

        public void setRequestType(String requestType) {
            this.requestType = requestType;
        }

        public String getFarmerCode() {
            return farmerCode;
        }

        public void setFarmerCode(String farmerCode) {
            this.farmerCode = farmerCode;
        }

        public Object getPlotCode() {
            return plotCode;
        }

        public void setPlotCode(Object plotCode) {
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

        public String getStatusType() {
            return statusType;
        }

        public void setStatusType(String statusType) {
            this.statusType = statusType;
        }

        public Boolean getIsFarmerRequest() {
            return isFarmerRequest;
        }

        public void setIsFarmerRequest(Boolean isFarmerRequest) {
            this.isFarmerRequest = isFarmerRequest;
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

        public Object getCropMaintainceDate() {
            return cropMaintainceDate;
        }

        public void setCropMaintainceDate(Object cropMaintainceDate) {
            this.cropMaintainceDate = cropMaintainceDate;
        }

        public Object getIssueTypeId() {
            return issueTypeId;
        }

        public void setIssueTypeId(Object issueTypeId) {
            this.issueTypeId = issueTypeId;
        }

        public Object getPlotVillage() {
            return plotVillage;
        }

        public void setPlotVillage(Object plotVillage) {
            this.plotVillage = plotVillage;
        }

        public Object getPalmArea() {
            return palmArea;
        }

        public void setPalmArea(Object palmArea) {
            this.palmArea = palmArea;
        }

        public Object getIssueType() {
            return issueType;
        }

        public void setIssueType(Object issueType) {
            this.issueType = issueType;
        }

        public String getIsHavingImage() {
            return isHavingImage;
        }

        public void setIsHavingImage(String isHavingImage) {
            this.isHavingImage = isHavingImage;
        }

        public String getIsHavingAudio() {
            return isHavingAudio;
        }

        public void setIsHavingAudio(String isHavingAudio) {
            this.isHavingAudio = isHavingAudio;
        }

        public String getFileExtension() {
            return fileExtension;
        }

        public void setFileExtension(String fileExtension) {
            this.fileExtension = fileExtension;
        }

        public String getFileLocation() {
            return fileLocation;
        }

        public void setFileLocation(String fileLocation) {
            this.fileLocation = fileLocation;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getFileUrl() {
            return fileUrl;
        }

        public void setFileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
        }
    }
}
