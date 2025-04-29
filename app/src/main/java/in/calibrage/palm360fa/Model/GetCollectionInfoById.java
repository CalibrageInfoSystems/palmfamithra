package in.calibrage.palm360fa.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetCollectionInfoById {

    @SerializedName("result")
    @Expose
    private Result result;
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

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
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


    public class Result {

        @SerializedName("code")
        @Expose
        private String code;
        @SerializedName("collectionCenterCode")
        @Expose
        private String collectionCenterCode;
        @SerializedName("collectionCenter")
        @Expose
        private String collectionCenter;
        @SerializedName("vehicleNumber")
        @Expose
        private String vehicleNumber;
        @SerializedName("driverName")
        @Expose
        private String driverName;
        @SerializedName("grossWeight")
        @Expose
        private Double grossWeight;
        @SerializedName("tareWeight")
        @Expose
        private Double tareWeight;
        @SerializedName("netWeight")
        @Expose
        private Double netWeight;
        @SerializedName("receiptGeneratedDate")
        @Expose
        private String receiptGeneratedDate;
        @SerializedName("receiptImg")
        @Expose
        private String receiptImg;
        @SerializedName("operatorName")
        @Expose
        private String operatorName;
        @SerializedName("comments")
        @Expose
        private String comments;
        @SerializedName("totalBunches")
        @Expose
        private Integer totalBunches;
        @SerializedName("acceptedBunches")
        @Expose
        private Integer acceptedBunches;
        @SerializedName("rejectedBunches")
        @Expose
        private Integer rejectedBunches;
        @SerializedName("graderName")
        @Expose
        private String graderName;
        @SerializedName("receiptCode")
        @Expose
        private String receiptCode;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCollectionCenterCode() {
            return collectionCenterCode;
        }

        public void setCollectionCenterCode(String collectionCenterCode) {
            this.collectionCenterCode = collectionCenterCode;
        }

        public String getCollectionCenter() {
            return collectionCenter;
        }

        public void setCollectionCenter(String collectionCenter) {
            this.collectionCenter = collectionCenter;
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

        public Double getGrossWeight() {
            return grossWeight;
        }

        public void setGrossWeight(Double grossWeight) {
            this.grossWeight = grossWeight;
        }

        public Double getTareWeight() {
            return tareWeight;
        }

        public void setTareWeight(Double tareWeight) {
            this.tareWeight = tareWeight;
        }

        public Double getNetWeight() {
            return netWeight;
        }

        public void setNetWeight(Double netWeight) {
            this.netWeight = netWeight;
        }

        public String getReceiptGeneratedDate() {
            return receiptGeneratedDate;
        }

        public void setReceiptGeneratedDate(String receiptGeneratedDate) {
            this.receiptGeneratedDate = receiptGeneratedDate;
        }

        public String getReceiptImg() {
            return receiptImg;
        }

        public void setReceiptImg(String receiptImg) {
            this.receiptImg = receiptImg;
        }

        public String getOperatorName() {
            return operatorName;
        }

        public void setOperatorName(String operatorName) {
            this.operatorName = operatorName;
        }

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public Integer getTotalBunches() {
            return totalBunches;
        }

        public void setTotalBunches(Integer totalBunches) {
            this.totalBunches = totalBunches;
        }

        public Integer getAcceptedBunches() {
            return acceptedBunches;
        }

        public void setAcceptedBunches(Integer acceptedBunches) {
            this.acceptedBunches = acceptedBunches;
        }

        public Integer getRejectedBunches() {
            return rejectedBunches;
        }

        public void setRejectedBunches(Integer rejectedBunches) {
            this.rejectedBunches = rejectedBunches;
        }

        public String getGraderName() {
            return graderName;
        }

        public void setGraderName(String graderName) {
            this.graderName = graderName;
        }

        public String getReceiptCode() {
            return receiptCode;
        }

        public void setReceiptCode(String receiptCode) {
            this.receiptCode = receiptCode;
        }

    }
}