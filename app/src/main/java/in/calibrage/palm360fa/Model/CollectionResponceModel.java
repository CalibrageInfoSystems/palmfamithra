package in.calibrage.palm360fa.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CollectionResponceModel {

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

        @SerializedName("collectioData")
        @Expose
        private List<CollectioDatum> collectioData = null;
        @SerializedName("collectionCount")
        @Expose
        private List<CollectionCount> collectionCount = null;

        public List<CollectioDatum> getCollectioData() {
            return collectioData;
        }

        public void setCollectioData(List<CollectioDatum> collectioData) {
            this.collectioData = collectioData;
        }

        public List<CollectionCount> getCollectionCount() {
            return collectionCount;
        }

        public void setCollectionCount(List<CollectionCount> collectionCount) {
            this.collectionCount = collectionCount;
        }

    }
    public class CollectionCount {

        @SerializedName("collectionsWeight")
        @Expose
        private Double collectionsWeight;
        @SerializedName("collectionsCount")
        @Expose
        private Integer collectionsCount;
        @SerializedName("paidCollectionsWeight")
        @Expose
        private Double paidCollectionsWeight;
        @SerializedName("unPaidCollectionsWeight")
        @Expose
        private Double unPaidCollectionsWeight;

        public Double getCollectionsWeight() {
            return collectionsWeight;
        }

        public void setCollectionsWeight(Double collectionsWeight) {
            this.collectionsWeight = collectionsWeight;
        }

        public Integer getCollectionsCount() {
            return collectionsCount;
        }

        public void setCollectionsCount(Integer collectionsCount) {
            this.collectionsCount = collectionsCount;
        }

        public Double getPaidCollectionsWeight() {
            return paidCollectionsWeight;
        }

        public void setPaidCollectionsWeight(Double paidCollectionsWeight) {
            this.paidCollectionsWeight = paidCollectionsWeight;
        }

        public Double getUnPaidCollectionsWeight() {
            return unPaidCollectionsWeight;
        }

        public void setUnPaidCollectionsWeight(Double unPaidCollectionsWeight) {
            this.unPaidCollectionsWeight = unPaidCollectionsWeight;
        }

    }
    public static class CollectioDatum {

        @SerializedName("u_colnid")
        @Expose
        private String uColnid;
        @SerializedName("u_farcode")
        @Expose
        private String uFarcode;
        @SerializedName("u_plotid")
        @Expose
        private String uPlotid;
        @SerializedName("u_apaystat")
        @Expose
        private String uApaystat;
        @SerializedName("canceled")
        @Expose
        private String canceled;
        @SerializedName("docStatus")
        @Expose
        private String docStatus;
        @SerializedName("docDate")
        @Expose
        private String docDate;
        @SerializedName("whsCode")
        @Expose
        private String whsCode;
        @SerializedName("whsName")
        @Expose
        private String whsName;
        @SerializedName("quantity")
        @Expose
        private Double quantity;
        @SerializedName("docEntry")
        @Expose
        private Integer docEntry;
        @SerializedName("updateDate")
        @Expose
        private Object updateDate;
        @SerializedName("farmerName")
        @Expose
        private String farmerName;
        @SerializedName("vehicalNumber")
        @Expose
        private Object vehicalNumber;
        @SerializedName("driverName")
        @Expose
        private Object driverName;

        public  String getUColnid() {
            return uColnid;
        }

        public void setUColnid(String uColnid) {
            this.uColnid = uColnid;
        }

        public String getUFarcode() {
            return uFarcode;
        }

        public void setUFarcode(String uFarcode) {
            this.uFarcode = uFarcode;
        }

        public String getUPlotid() {
            return uPlotid;
        }

        public void setUPlotid(String uPlotid) {
            this.uPlotid = uPlotid;
        }

        public String getUApaystat() {
            return uApaystat;
        }

        public void setUApaystat(String uApaystat) {
            this.uApaystat = uApaystat;
        }

        public String getCanceled() {
            return canceled;
        }

        public void setCanceled(String canceled) {
            this.canceled = canceled;
        }

        public String getDocStatus() {
            return docStatus;
        }

        public void setDocStatus(String docStatus) {
            this.docStatus = docStatus;
        }

        public  String getDocDate() {
            return docDate;
        }

        public void setDocDate(String docDate) {
            this.docDate = docDate;
        }

        public String getWhsCode() {
            return whsCode;
        }

        public void setWhsCode(String whsCode) {
            this.whsCode = whsCode;
        }

        public String getWhsName() {
            return whsName;
        }

        public void setWhsName(String whsName) {
            this.whsName = whsName;
        }

        public Double getQuantity() {
            return quantity;
        }

        public void setQuantity(Double quantity) {
            this.quantity = quantity;
        }

        public Integer getDocEntry() {
            return docEntry;
        }

        public void setDocEntry(Integer docEntry) {
            this.docEntry = docEntry;
        }

        public Object getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(Object updateDate) {
            this.updateDate = updateDate;
        }

        public String getFarmerName() {
            return farmerName;
        }

        public void setFarmerName(String farmerName) {
            this.farmerName = farmerName;
        }

        public Object getVehicalNumber() {
            return vehicalNumber;
        }

        public void setVehicalNumber(Object vehicalNumber) {
            this.vehicalNumber = vehicalNumber;
        }

        public Object getDriverName() {
            return driverName;
        }

        public void setDriverName(Object driverName) {
            this.driverName = driverName;
        }

    }
}
