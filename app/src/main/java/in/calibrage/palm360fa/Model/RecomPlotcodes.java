package in.calibrage.palm360fa.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecomPlotcodes {

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
        @SerializedName("farmerCode")
        @Expose
        private String farmerCode;
        @SerializedName("palmArea")
        @Expose
        private Double palmArea;
        @SerializedName("plotArea")
        @Expose
        private Double plotArea;
        @SerializedName("plotAreainAcres")
        @Expose
        private Double plotAreainAcres;
        @SerializedName("palmAreainAcres")
        @Expose
        private Double palmAreainAcres;
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
        @SerializedName("stateCode")
        @Expose
        private String stateCode;
        @SerializedName("statusTypeId")
        @Expose
        private Integer statusTypeId;
        @SerializedName("statusType")
        @Expose
        private String statusType;
        @SerializedName("age")
        @Expose
        private Integer age;
        @SerializedName("clusterId")
        @Expose
        private Integer clusterId;
        @SerializedName("clusterName")
        @Expose
        private String clusterName;
        @SerializedName("surveyNumber")
        @Expose
        private String surveyNumber;
        @SerializedName("farmerName")
        @Expose
        private String farmerName;
        @SerializedName("contactNumber")
        @Expose
        private String contactNumber;
        @SerializedName("mobileNumber")
        @Expose
        private Object mobileNumber;

        public String getPlotcode() {
            return plotcode;
        }

        public void setPlotcode(String plotcode) {
            this.plotcode = plotcode;
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

        public Double getPlotArea() {
            return plotArea;
        }

        public void setPlotArea(Double plotArea) {
            this.plotArea = plotArea;
        }

        public Double getPlotAreainAcres() {
            return plotAreainAcres;
        }

        public void setPlotAreainAcres(Double plotAreainAcres) {
            this.plotAreainAcres = plotAreainAcres;
        }

        public Double getPalmAreainAcres() {
            return palmAreainAcres;
        }

        public void setPalmAreainAcres(Double palmAreainAcres) {
            this.palmAreainAcres = palmAreainAcres;
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

        public Integer getClusterId() {
            return clusterId;
        }

        public void setClusterId(Integer clusterId) {
            this.clusterId = clusterId;
        }

        public String getClusterName() {
            return clusterName;
        }

        public void setClusterName(String clusterName) {
            this.clusterName = clusterName;
        }

        public String getSurveyNumber() {
            return surveyNumber;
        }

        public void setSurveyNumber(String surveyNumber) {
            this.surveyNumber = surveyNumber;
        }

        public String getFarmerName() {
            return farmerName;
        }

        public void setFarmerName(String farmerName) {
            this.farmerName = farmerName;
        }

        public String getContactNumber() {
            return contactNumber;
        }

        public void setContactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
        }

        public Object getMobileNumber() {
            return mobileNumber;
        }

        public void setMobileNumber(Object mobileNumber) {
            this.mobileNumber = mobileNumber;
        }

        public String getStateCode() {
            return stateCode;
        }

        public void setStateCode(String stateCode) {
            this.stateCode = stateCode;
        }
    }
}