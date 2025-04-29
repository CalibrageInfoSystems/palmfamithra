package in.calibrage.palm360fa.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VisitplotDetailsModel {

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

        @SerializedName("plotcode")
        @Expose
        private String plotcode;
        @SerializedName("fPlotcode")
        @Expose
        private String fPlotcode;
        @SerializedName("farmerCode")
        @Expose
        private String farmerCode;
        @SerializedName("palmArea")
        @Expose
        private Double palmArea;
        @SerializedName("dateOfPlanting")
        @Expose
        private String dateOfPlanting;
        @SerializedName("landMark")
        @Expose
        private String landMark;
        @SerializedName("villageId")
        @Expose
        private Integer villageId;
        @SerializedName("villageName")
        @Expose
        private String villageName;
        @SerializedName("mandalId")
        @Expose
        private Integer mandalId;
        @SerializedName("mandalName")
        @Expose
        private String mandalName;
        @SerializedName("districtId")
        @Expose
        private Integer districtId;
        @SerializedName("districtName")
        @Expose
        private String districtName;
        @SerializedName("stateId")
        @Expose
        private Integer stateId;
        @SerializedName("stateName")
        @Expose
        private String stateName;
        @SerializedName("plotActive")
        @Expose
        private Boolean plotActive;
        @SerializedName("farmerHistoryActive")
        @Expose
        private Boolean farmerHistoryActive;
        @SerializedName("statusTypeId")
        @Expose
        private Integer statusTypeId;
        @SerializedName("statusType")
        @Expose
        private String statusType;
        @SerializedName("age")
        @Expose
        private Integer age;

        public String getPlotcode() {
            return plotcode;
        }

        public void setPlotcode(String plotcode) {
            this.plotcode = plotcode;
        }

        public String getFPlotcode() {
            return fPlotcode;
        }

        public void setFPlotcode(String fPlotcode) {
            this.fPlotcode = fPlotcode;
        }

        public String getFarmerCode() {
            return farmerCode;
        }

        public void setFarmerCode(String farmerCode) {
            this.farmerCode = farmerCode;
        }

        public Double getPalmArea() {
            return palmArea;
        }

        public void setPalmArea(Double palmArea) {
            this.palmArea = palmArea;
        }

        public String getDateOfPlanting() {
            return dateOfPlanting;
        }

        public void setDateOfPlanting(String dateOfPlanting) {
            this.dateOfPlanting = dateOfPlanting;
        }

        public String getLandMark() {
            return landMark;
        }

        public void setLandMark(String landMark) {
            this.landMark = landMark;
        }

        public Integer getVillageId() {
            return villageId;
        }

        public void setVillageId(Integer villageId) {
            this.villageId = villageId;
        }

        public String getVillageName() {
            return villageName;
        }

        public void setVillageName(String villageName) {
            this.villageName = villageName;
        }

        public Integer getMandalId() {
            return mandalId;
        }

        public void setMandalId(Integer mandalId) {
            this.mandalId = mandalId;
        }

        public String getMandalName() {
            return mandalName;
        }

        public void setMandalName(String mandalName) {
            this.mandalName = mandalName;
        }

        public Integer getDistrictId() {
            return districtId;
        }

        public void setDistrictId(Integer districtId) {
            this.districtId = districtId;
        }

        public String getDistrictName() {
            return districtName;
        }

        public void setDistrictName(String districtName) {
            this.districtName = districtName;
        }

        public Integer getStateId() {
            return stateId;
        }

        public void setStateId(Integer stateId) {
            this.stateId = stateId;
        }

        public String getStateName() {
            return stateName;
        }

        public void setStateName(String stateName) {
            this.stateName = stateName;
        }

        public Boolean getPlotActive() {
            return plotActive;
        }

        public void setPlotActive(Boolean plotActive) {
            this.plotActive = plotActive;
        }

        public Boolean getFarmerHistoryActive() {
            return farmerHistoryActive;
        }

        public void setFarmerHistoryActive(Boolean farmerHistoryActive) {
            this.farmerHistoryActive = farmerHistoryActive;
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

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

    }
}
