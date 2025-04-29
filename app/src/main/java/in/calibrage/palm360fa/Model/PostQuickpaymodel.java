
package in.calibrage.palm360fa.Model;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostQuickpaymodel {

    @SerializedName("farmerCode")
    @Expose
    private String farmerCode;
    @SerializedName("farmerName")
    @Expose
    private String farmerName;
    @SerializedName("isFarmerRequest")
    @Expose
    private Boolean isFarmerRequest;
    @SerializedName("reqCreatedDate")
    @Expose
    private String reqCreatedDate;
    @SerializedName("netWeight")
    @Expose
    private Double netWeight;
    @SerializedName("closingBalance")
    @Expose
    private Double closingBalance;
    @SerializedName("fileLocation")
    @Expose
    private String fileLocation;
    @SerializedName("signatureName")
    @Expose
    private String signatureName;
    @SerializedName("signatureExtension")
    @Expose
    private String signatureExtension;
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
    @SerializedName("whsCode")
    @Expose
    private String whsCode;
    @SerializedName("clusterId")
    @Expose
    private Integer clusterId;
    @SerializedName("isSpecialPay")
    @Expose
    private Boolean isSpecialPay;
    @SerializedName("collectionCodes")
    @Expose
    private String collectionCodes;
    @SerializedName("collectionIds")
    @Expose
    private String collectionIds;
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
    @SerializedName("ffbCost")
    @Expose
    private Double ffbCost;

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

    public Boolean getIsFarmerRequest() {
        return isFarmerRequest;
    }

    public void setIsFarmerRequest(Boolean isFarmerRequest) {
        this.isFarmerRequest = isFarmerRequest;
    }

    public String getReqCreatedDate() {
        return reqCreatedDate;
    }

    public void setReqCreatedDate(String reqCreatedDate) {
        this.reqCreatedDate = reqCreatedDate;
    }

    public Double getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(Double netWeight) {
        this.netWeight = netWeight;
    }

    public Double getClosingBalance() {
        return closingBalance;
    }

    public void setClosingBalance(Double closingBalance) {
        this.closingBalance = closingBalance;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public String getSignatureName() {
        return signatureName;
    }

    public void setSignatureName(String signatureName) {
        this.signatureName = signatureName;
    }

    public String getSignatureExtension() {
        return signatureExtension;
    }

    public void setSignatureExtension(String signatureExtension) {
        this.signatureExtension = signatureExtension;
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

    public String getWhsCode() {
        return whsCode;
    }

    public void setWhsCode(String whsCode) {
        this.whsCode = whsCode;
    }

    public Integer getClusterId() {
        return clusterId;
    }

    public void setClusterId(Integer clusterId) {
        this.clusterId = clusterId;
    }

    public Boolean getIsSpecialPay() {
        return isSpecialPay;
    }

    public void setIsSpecialPay(Boolean isSpecialPay) {
        this.isSpecialPay = isSpecialPay;
    }

    public String getCollectionCodes() {
        return collectionCodes;
    }

    public void setCollectionCodes(String collectionCodes) {
        this.collectionCodes = collectionCodes;
    }

    public String getCollectionIds() {
        return collectionIds;
    }

    public void setCollectionIds(String collectionIds) {
        this.collectionIds = collectionIds;
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

    public Double getFfbCost() {
        return ffbCost;
    }

    public void setFfbCost(Double ffbCost) {
        this.ffbCost = ffbCost;
    }
}