package in.calibrage.palm360fa.Model;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FarmerOtpResponceModel {

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

        @SerializedName("farmerDetails")
        @Expose
        private List<FarmerDetail> farmerDetails = null;
        @SerializedName("bannerDetails")
        @Expose
        private Object bannerDetails;
        @SerializedName("categoriesDetails")
        @Expose
        private List<CategoriesDetail> categoriesDetails = null;

        public List<FarmerDetail> getFarmerDetails() {
            return farmerDetails;
        }

        public void setFarmerDetails(List<FarmerDetail> farmerDetails) {
            this.farmerDetails = farmerDetails;
        }

        public Object getBannerDetails() {
            return bannerDetails;
        }

        public void setBannerDetails(Object bannerDetails) {
            this.bannerDetails = bannerDetails;
        }

        public List<CategoriesDetail> getCategoriesDetails() {
            return categoriesDetails;
        }

        public void setCategoriesDetails(List<CategoriesDetail> categoriesDetails) {
            this.categoriesDetails = categoriesDetails;
        }

    }
    public class FarmerDetail {

        @SerializedName("code")
        @Expose
        private String code;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("firstName")
        @Expose
        private String firstName;
        @SerializedName("middleName")
        @Expose
        private String middleName;
        @SerializedName("lastName")
        @Expose
        private String lastName;
        @SerializedName("guardianName")
        @Expose
        private String guardianName;
        @SerializedName("motherName")
        @Expose
        private String motherName;
        @SerializedName("contactNumber")
        @Expose
        private String contactNumber;
        @SerializedName("mobileNumber")
        @Expose
        private Object mobileNumber;
        @SerializedName("contactNumbers")
        @Expose
        private String contactNumbers;
        @SerializedName("dob")
        @Expose
        private String dob;
        @SerializedName("age")
        @Expose
        private Integer age;
        @SerializedName("email")
        @Expose
        private Object email;
        @SerializedName("villageName")
        @Expose
        private String villageName;
        @SerializedName("pinCode")
        @Expose
        private Integer pinCode;
        @SerializedName("mandalName")
        @Expose
        private String mandalName;
        @SerializedName("districtName")
        @Expose
        private String districtName;
        @SerializedName("stateName")
        @Expose
        private String stateName;
        @SerializedName("stateCode")
        @Expose
        private String stateCode;
        @SerializedName("stateId")
        @Expose
        private Integer stateId;
        @SerializedName("moduleTypeId")
        @Expose
        private Integer moduleTypeId;
        @SerializedName("farmerPictureLocation")
        @Expose
        private String farmerPictureLocation;

        @SerializedName("addressLine1")
        @Expose
        private String addressLine1;
        @SerializedName("addressLine2")
        @Expose
        private String addressLine2;
        @SerializedName("addressLine3")
        @Expose
        private Object addressLine3;
        @SerializedName("landmark")
        @Expose
        private String landmark;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("clusterId")
        @Expose
        private Integer clusterId;
        @SerializedName("clusterName")
        @Expose
        private String clusterName;
        @SerializedName("villageId")
        @Expose
        private Integer villageId;

        @SerializedName("districtId")
        @Expose
        private Integer districtId;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getMiddleName() {
            return middleName;
        }

        public void setMiddleName(String middleName) {
            this.middleName = middleName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getGuardianName() {
            return guardianName;
        }

        public void setGuardianName(String guardianName) {
            this.guardianName = guardianName;
        }

        public String getMotherName() {
            return motherName;
        }

        public void setMotherName(String motherName) {
            this.motherName = motherName;
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

        public String getContactNumbers() {
            return contactNumbers;
        }

        public void setContactNumbers(String contactNumbers) {
            this.contactNumbers = contactNumbers;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public String getVillageName() {
            return villageName;
        }

        public void setVillageName(String villageName) {
            this.villageName = villageName;
        }

        public Integer getPinCode() {
            return pinCode;
        }

        public void setPinCode(Integer pinCode) {
            this.pinCode = pinCode;
        }

        public String getMandalName() {
            return mandalName;
        }

        public void setMandalName(String mandalName) {
            this.mandalName = mandalName;
        }

        public String getDistrictName() {
            return districtName;
        }

        public void setDistrictName(String districtName) {
            this.districtName = districtName;
        }

        public String getStateName() {
            return stateName;
        }

        public void setStateName(String stateName) {
            this.stateName = stateName;
        }

        public String getStateCode() {
            return stateCode;
        }

        public void setStateCode(String stateCode) {
            this.stateCode = stateCode;
        }

        public Integer getStateId() {
            return stateId;
        }

        public void setStateId(Integer stateId) {
            this.stateId = stateId;
        }

        public Integer getModuleTypeId() {
            return moduleTypeId;
        }

        public void setModuleTypeId(Integer moduleTypeId) {
            this.moduleTypeId = moduleTypeId;
        }

        public String getFarmerPictureLocation() {
            return farmerPictureLocation;
        }

        public void setFarmerPictureLocation(String farmerPictureLocation) {
            this.farmerPictureLocation = farmerPictureLocation;
        }

        public String getAddressLine1() {
            return addressLine1;
        }

        public void setAddressLine1(String addressLine1) {
            this.addressLine1 = addressLine1;
        }

        public String getAddressLine2() {
            return addressLine2;
        }

        public void setAddressLine2(String addressLine2) {
            this.addressLine2 = addressLine2;
        }

        public Object getAddressLine3() {
            return addressLine3;
        }

        public void setAddressLine3(Object addressLine3) {
            this.addressLine3 = addressLine3;
        }

        public String getLandmark() {
            return landmark;
        }

        public void setLandmark(String landmark) {
            this.landmark = landmark;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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

        public Integer getVillageId() {
            return villageId;
        }

        public void setVillageId(Integer villageId) {
            this.villageId = villageId;
        }

        public Integer getDistrictId() {
            return districtId;
        }

        public void setDistrictId(Integer districtId) {
            this.districtId = districtId;
        }
    }
    public class BannerDetail {

        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("imageURL")
        @Expose
        private String imageURL;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImageURL() {
            return imageURL;
        }

        public void setImageURL(String imageURL) {
            this.imageURL = imageURL;
        }

    }
    public class CategoriesDetail {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("teluguName")
        @Expose
        private String teluguName;
        @SerializedName("hindiName")
        @Expose
        private String hindiName;
        @SerializedName("remarks")
        @Expose
        private String remarks;
        @SerializedName("isActive")
        @Expose
        private Boolean isActive;
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

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTeluguName() {
            return teluguName;
        }

        public void setTeluguName(String teluguName) {
            this.teluguName = teluguName;
        }

        public String getHindiName() {
            return hindiName;
        }

        public void setHindiName(String hindiName) {
            this.hindiName = hindiName;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
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

    }

}