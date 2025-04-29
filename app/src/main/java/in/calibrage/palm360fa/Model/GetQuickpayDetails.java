 package in.calibrage.palm360fa.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

 public class GetQuickpayDetails {

     @SerializedName("farmerCode")
     @Expose
     private String farmerCode;
     @SerializedName("quantity")
     @Expose
     private Double quantity;
     @SerializedName("isSpecialPay")
     @Expose
     private Boolean isSpecialPay;
     @SerializedName("stateCode")
     @Expose
     private String stateCode;

     @SerializedName("districtId")
     @Expose
     private Integer districtId;
     @SerializedName("docDate")
     @Expose
     private String docDate;

     public String getFarmerCode() {
         return farmerCode;
     }

     public void setFarmerCode(String farmerCode) {
         this.farmerCode = farmerCode;
     }

     public Double getQuantity() {
         return quantity;
     }

     public void setQuantity(Double quantity) {
         this.quantity = quantity;
     }

     public Boolean getIsSpecialPay() {
         return isSpecialPay;
     }

     public void setIsSpecialPay(Boolean isSpecialPay) {
         this.isSpecialPay = isSpecialPay;
     }

     public String getStateCode() {
         return stateCode;
     }

     public void setStateCode(String stateCode) {
         this.stateCode = stateCode;
     }
     public Integer getDistrictId() {
         return districtId;
     }

     public void setDistrictId(Integer districtId) {
         this.districtId = districtId;
     }

     public String getDocDate() {
         return docDate;
     }

     public void setDocDate(String docDate) {
         this.docDate = docDate;
     }
 }