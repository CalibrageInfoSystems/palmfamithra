

package in.calibrage.palm360fa.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuickPayModel {

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

    @SerializedName("u_colnid")
    @Expose
    private String uColnid;
    @SerializedName("u_farcode")
    @Expose
    private String uFarcode;
    @SerializedName("u_plotid")
    @Expose
    private String uPlotid;
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

    @SerializedName("stateCode")
    @Expose
    private String stateCode;

    @SerializedName("stateName")
    @Expose
    private String stateName;

    @SerializedName("districtId")
    @Expose
    private Integer districtId;

    @SerializedName("districtName")
    @Expose
    private String districtName;

    @SerializedName("isCollectionBlocked")
    @Expose
    private Boolean isCollectionBlocked;

    public Boolean getCollectionBlocked() {
        return isCollectionBlocked;
    }

    public void setCollectionBlocked(Boolean collectionBlocked) {
        isCollectionBlocked = collectionBlocked;
    }


    public String getUColnid() {
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

    public String getDocStatus() {
        return docStatus;
    }

    public void setDocStatus(String docStatus) {
        this.docStatus = docStatus;
    }

    public String getDocDate() {
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

}



}