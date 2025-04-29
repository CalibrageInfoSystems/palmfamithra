 package in.calibrage.palm360fa.Model;

 import com.google.gson.annotations.Expose;
 import com.google.gson.annotations.SerializedName;

 import java.util.List;

 public class FertRequest {

     @SerializedName("id")
     @Expose
     private Integer id;
     @SerializedName("requestTypeId")
     @Expose
     private Integer requestTypeId;
     @SerializedName("farmerCode")
     @Expose
     private String farmerCode;
     @SerializedName("farmerName")
     @Expose
     private String farmerName;
     @SerializedName("plotCode")
     @Expose
     private String plotCode;
     @SerializedName("requestCreatedDate")
     @Expose
     private String requestCreatedDate;
     @SerializedName("isFarmerRequest")
     @Expose
     private Boolean isFarmerRequest;
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
     @SerializedName("godownId")
     @Expose
     private Integer godownId;
     @SerializedName("paymentModeType")
     @Expose
     private Integer paymentModeType;
     @SerializedName("fileName")
     @Expose
     private String fileName;
     @SerializedName("fileLocation")
     @Expose
     private String fileLocation;
     @SerializedName("fileExtension")
     @Expose
     private String fileExtension;
     @SerializedName("totalCost")
     @Expose
     private Double totalCost;
     @SerializedName("subcidyAmount")
     @Expose
     private Double subcidyAmount;
     @SerializedName("paybleAmount")
     @Expose
     private Double paybleAmount;
     @SerializedName("TransportPayableAmount")
     @Expose
     private Double TransportPayableAmount;
     @SerializedName("comments")
     @Expose
     private String comments;
     @SerializedName("cropMaintainceDate")
     @Expose
     private String cropMaintainceDate;
     @SerializedName("issueTypeId")
     @Expose
     private Integer issueTypeId;
     @SerializedName("clusterId")
     @Expose
     private Integer clusterId;
     @SerializedName("stateCode")
     @Expose
     private String stateCode;
     @SerializedName("stateName")
     @Expose
     private String stateName;

     @SerializedName("godownCode")
     @Expose
     private String godownCode;
     @SerializedName("isImmediatePayment")
     @Expose
     private Boolean isImmediatePayment;
     @SerializedName("requestProductDetails")
     @Expose
     private List<RequestProductDetail> requestProductDetails = null;

     public Integer getId() {
         return id;
     }

     public void setId(Integer id) {
         this.id = id;
     }

     public Integer getRequestTypeId() {
         return requestTypeId;
     }

     public void setRequestTypeId(Integer requestTypeId) {
         this.requestTypeId = requestTypeId;
     }

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

     public String getPlotCode() {
         return plotCode;
     }

     public void setPlotCode(String plotCode) {
         this.plotCode = plotCode;
     }

     public String getRequestCreatedDate() {
         return requestCreatedDate;
     }

     public void setRequestCreatedDate(String requestCreatedDate) {
         this.requestCreatedDate = requestCreatedDate;
     }

     public Boolean getIsFarmerRequest() {
         return isFarmerRequest;
     }

     public void setIsFarmerRequest(Boolean isFarmerRequest) {
         this.isFarmerRequest = isFarmerRequest;
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

     public Integer getGodownId() {
         return godownId;
     }

     public void setGodownId(Integer godownId) {
         this.godownId = godownId;
     }

     public Integer getPaymentModeType() {
         return paymentModeType;
     }

     public void setPaymentModeType(Integer paymentModeType) {
         this.paymentModeType = paymentModeType;
     }

     public String getFileName() {
         return fileName;
     }

     public void setFileName(String fileName) {
         this.fileName = fileName;
     }

     public String getFileLocation() {
         return fileLocation;
     }

     public void setFileLocation(String fileLocation) {
         this.fileLocation = fileLocation;
     }

     public String getFileExtension() {
         return fileExtension;
     }

     public void setFileExtension(String fileExtension) {
         this.fileExtension = fileExtension;
     }

     public Double getTotalCost() {
         return totalCost;
     }

     public void setTotalCost(Double totalCost) {
         this.totalCost = totalCost;
     }

     public Double getSubcidyAmount() {
         return subcidyAmount;
     }

     public void setSubcidyAmount(Double subcidyAmount) {
         this.subcidyAmount = subcidyAmount;
     }

     public Double getPaybleAmount() {
         return paybleAmount;
     }

     public void setPaybleAmount(Double paybleAmount) {
         this.paybleAmount = paybleAmount;
     }

     public String getComments() {
         return comments;
     }

     public void setComments(String comments) {
         this.comments = comments;
     }

     public String getCropMaintainceDate() {
         return cropMaintainceDate;
     }

     public void setCropMaintainceDate(String cropMaintainceDate) {
         this.cropMaintainceDate = cropMaintainceDate;
     }

     public Integer getIssueTypeId() {
         return issueTypeId;
     }

     public void setIssueTypeId(Integer issueTypeId) {
         this.issueTypeId = issueTypeId;
     }

     public String getGodownCode() {
         return godownCode;
     }

     public void setGodownCode(String godownCode) {
         this.godownCode = godownCode;
     }

     public List<RequestProductDetail> getRequestProductDetails() {
         return requestProductDetails;
     }

     public void setRequestProductDetails(List<RequestProductDetail> requestProductDetails) {
         this.requestProductDetails = requestProductDetails;
     }

     public Integer getClusterId() {
         return clusterId;
     }

     public void setClusterId(Integer clusterId) {
         this.clusterId = clusterId;
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
     public Boolean getIsImmediatePayment() {
         return isImmediatePayment;
     }

     public void setIsImmediatePayment(Boolean isImmediatePayment) {
         this.isImmediatePayment = isImmediatePayment;
     }
     public Double getTransportPayableAmount() {
         return TransportPayableAmount;
     }

     public void setTransportPayableAmount(Double transportPayableAmount) {
         TransportPayableAmount = transportPayableAmount;
     }


     public static class RequestProductDetail {

         @SerializedName("productId")
         @Expose
         private Integer productId;
         @SerializedName("quantity")
         @Expose
         private Integer quantity;
         @SerializedName("bagCost")
         @Expose
         private Double bagCost;
         @SerializedName("size")
         @Expose
         private Double size;
         @SerializedName("gstPersentage")
         @Expose
         private Double gstPersentage;
         @SerializedName("productCode")
         @Expose
         private String productCode;
         @SerializedName("transGstPercentage")
         @Expose
         private Double transGstPercentage;
         @SerializedName("transportCost")
         @Expose
         private Double transportCost;

         public Integer getProductId() {
             return productId;
         }

         public void setProductId(Integer productId) {
             this.productId = productId;
         }

         public Integer getQuantity() {
             return quantity;
         }

         public void setQuantity(Integer quantity) {
             this.quantity = quantity;
         }

         public Double getBagCost() {
             return bagCost;
         }

         public void setBagCost(Double bagCost) {
             this.bagCost = bagCost;
         }

         public Double getSize() {
             return size;
         }

         public void setSize(Double size) {
             this.size = size;
         }

         public Double getGstPersentage() {
             return gstPersentage;
         }

         public void setGstPersentage(Double gstPersentage) {
             this.gstPersentage = gstPersentage;
         }

         public String getProductCode() {
             return productCode;
         }

         public void setProductCode(String productCode) {
             this.productCode = productCode;
         }

         public Double getTransGstPercentage() {
             return transGstPercentage;
         }

         public void setTransGstPercentage(Double transGstPercentage) {
             this.transGstPercentage = transGstPercentage;
         }

         public Double getTransporCost() {
             return transportCost;
         }

         public void setTransporCost(Double transporCost) {
             this.transportCost = transporCost;
         }
     }}