 package in.calibrage.palm360fa.Model;

 import com.google.gson.annotations.Expose;
 import com.google.gson.annotations.SerializedName;

 import java.util.List;

 public class GetLabourPackageDiscount {

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

     @SerializedName("id")
     @Expose
     private Integer id;
     @SerializedName("packageTypeId")
     @Expose
     private Integer packageTypeId;
     @SerializedName("desc")
     @Expose
     private String desc;
     @SerializedName("discountPercentage")
     @Expose
     private Integer discountPercentage;
     @SerializedName("isActive")
     @Expose
     private Boolean isActive;
     @SerializedName("createdByUserId")
     @Expose
     private Integer createdByUserId;
     @SerializedName("createdBy")
     @Expose
     private String createdBy;
     @SerializedName("createdDate")
     @Expose
     private String createdDate;
     @SerializedName("updatedByUserId")
     @Expose
     private Integer updatedByUserId;
     @SerializedName("updatedDate")
     @Expose
     private String updatedDate;
     @SerializedName("updatedBy")
     @Expose
     private String updatedBy;
     @SerializedName("sortOrder")
     @Expose
     private Integer sortOrder;

     public Integer getId() {
         return id;
     }

     public void setId(Integer id) {
         this.id = id;
     }

     public Integer getPackageTypeId() {
         return packageTypeId;
     }

     public void setPackageTypeId(Integer packageTypeId) {
         this.packageTypeId = packageTypeId;
     }

     public String getDesc() {
         return desc;
     }

     public void setDesc(String desc) {
         this.desc = desc;
     }

     public Integer getDiscountPercentage() {
         return discountPercentage;
     }

     public void setDiscountPercentage(Integer discountPercentage) {
         this.discountPercentage = discountPercentage;
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

     public String getCreatedBy() {
         return createdBy;
     }

     public void setCreatedBy(String createdBy) {
         this.createdBy = createdBy;
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

     public String getUpdatedBy() {
         return updatedBy;
     }

     public void setUpdatedBy(String updatedBy) {
         this.updatedBy = updatedBy;
     }

     public Integer getSortOrder() {
         return sortOrder;
     }

     public void setSortOrder(Integer sortOrder) {
         this.sortOrder = sortOrder;
     }

 } }