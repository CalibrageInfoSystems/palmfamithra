package in.calibrage.palm360fa.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Addvendorobject {

    @SerializedName("vendorTrasportationDetails")
    @Expose
    private VendorTrasportationDetails vendorTrasportationDetails;
    @SerializedName("vendorVillageDetails")
    @Expose
    private List<VendorVillageDetail> vendorVillageDetails = null;
    @SerializedName("vendorVehicleDetails")
    @Expose
    private List<VendorVehicleDetail> vendorVehicleDetails = null;
    @SerializedName("vendorHireCharges")
    @Expose
    private List<VendorHireCharge> vendorHireCharges = null;
    @SerializedName("vendorTractorAttachments")
    @Expose
    private List<VendorTractorAttachment> vendorTractorAttachments = null;

    public Addvendorobject(VendorTrasportationDetails headerModel, List<VendorVillageDetail> villagedata, List<VendorTractorAttachment> Tractordata, List<VendorVehicleDetail> vehicledata, List<VendorHireCharge> hirechargedataedata) {
  this.vendorTrasportationDetails=headerModel;
        this.vendorVillageDetails=villagedata;
        this.vendorTractorAttachments=Tractordata;
        this.vendorVehicleDetails=vehicledata;
        this.vendorHireCharges=hirechargedataedata;
    }



    public VendorTrasportationDetails getVendorTrasportationDetails() {
        return vendorTrasportationDetails;
    }

    public void setVendorTrasportationDetails(VendorTrasportationDetails vendorTrasportationDetails) {
        this.vendorTrasportationDetails = vendorTrasportationDetails;
    }

    public List<VendorVillageDetail> getVendorVillageDetails() {
        return vendorVillageDetails;
    }

    public void setVendorVillageDetails(List<VendorVillageDetail> vendorVillageDetails) {
        this.vendorVillageDetails = vendorVillageDetails;
    }

    public List<VendorVehicleDetail> getVendorVehicleDetails() {
        return vendorVehicleDetails;
    }

    public void setVendorVehicleDetails(List<VendorVehicleDetail> vendorVehicleDetails) {
        this.vendorVehicleDetails = vendorVehicleDetails;
    }

    public List<VendorHireCharge> getVendorHireCharges() {
        return vendorHireCharges;
    }

    public void setVendorHireCharges(List<VendorHireCharge> vendorHireCharges) {
        this.vendorHireCharges = vendorHireCharges;
    }

    public List<VendorTractorAttachment> getVendorTractorAttachments() {
        return vendorTractorAttachments;
    }

    public void setVendorTractorAttachments(List<VendorTractorAttachment> vendorTractorAttachments) {
        this.vendorTractorAttachments = vendorTractorAttachments;
    }




    public static class VendorHireCharge {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("vendorTransportationServiceId")
        @Expose
        private Integer vendorTransportationServiceId;
        @SerializedName("villageId")
        @Expose
        private Integer villageId;
        @SerializedName("destinationId")
        @Expose
        private Integer destinationId;
        @SerializedName("price")
        @Expose
        private Double price;
        @SerializedName("createdByUserId")
        @Expose
        private Integer createdByUserId;
        @SerializedName("createdDate")
        @Expose
        private String createdDate;
        @SerializedName("vehicleTypeId")
        @Expose
        private Integer vehicleTypeId;
        @SerializedName("hiringTypeId")
        @Expose
        private Integer hiringTypeId;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getVendorTransportationServiceId() {
            return vendorTransportationServiceId;
        }

        public void setVendorTransportationServiceId(Integer vendorTransportationServiceId) {
            this.vendorTransportationServiceId = vendorTransportationServiceId;
        }

        public Integer getVillageId() {
            return villageId;
        }

        public void setVillageId(Integer villageId) {
            this.villageId = villageId;
        }

        public Integer getDestinationId() {
            return destinationId;
        }

        public void setDestinationId(Integer destinationId) {
            this.destinationId = destinationId;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
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

        public Integer getVehicleTypeId() {
            return vehicleTypeId;
        }

        public void setVehicleTypeId(Integer vehicleTypeId) {
            this.vehicleTypeId = vehicleTypeId;
        }

        public Integer getHiringTypeId() {
            return hiringTypeId;
        }

        public void setHiringTypeId(Integer hiringTypeId) {
            this.hiringTypeId = hiringTypeId;
        }}
    public static class VendorTractorAttachment {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("vendorTransportationServiceId")
        @Expose
        private Integer vendorTransportationServiceId;
        @SerializedName("tractorAttachmentTypeId")
        @Expose
        private Integer tractorAttachmentTypeId;
        @SerializedName("tractorAttachmentSubTypeId")
        @Expose
        private Integer tractorAttachmentSubTypeId;
        @SerializedName("charges")
        @Expose
        private Double charges;
        @SerializedName("createdByUserId")
        @Expose
        private Integer createdByUserId;
        @SerializedName("createdDate")
        @Expose
        private String createdDate;
        @SerializedName("tractorAttachmentTypeDesc")
        @Expose
        private String tractorAttachmentTypeDesc;
        @SerializedName("tractorAttachmentUOMTypeId")
        @Expose
        private Integer tractorAttachmentUOMTypeId;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getVendorTransportationServiceId() {
            return vendorTransportationServiceId;
        }

        public void setVendorTransportationServiceId(Integer vendorTransportationServiceId) {
            this.vendorTransportationServiceId = vendorTransportationServiceId;
        }

        public Integer getTractorAttachmentTypeId() {
            return tractorAttachmentTypeId;
        }

        public void setTractorAttachmentTypeId(Integer tractorAttachmentTypeId) {
            this.tractorAttachmentTypeId = tractorAttachmentTypeId;
        }

        public Integer getTractorAttachmentSubTypeId() {
            return tractorAttachmentSubTypeId;
        }

        public void setTractorAttachmentSubTypeId(Integer tractorAttachmentSubTypeId) {
            this.tractorAttachmentSubTypeId = tractorAttachmentSubTypeId;
        }

        public Double getCharges() {
            return charges;
        }

        public void setCharges(Double charges) {
            this.charges = charges;
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

        public String getTractorAttachmentTypeDesc() {
            return tractorAttachmentTypeDesc;
        }

        public void setTractorAttachmentTypeDesc(String tractorAttachmentTypeDesc) {
            this.tractorAttachmentTypeDesc = tractorAttachmentTypeDesc;
        }

        public Integer getTractorAttachmentUOMTypeId() {
            return tractorAttachmentUOMTypeId;
        }

        public void setTractorAttachmentUOMTypeId(Integer tractorAttachmentUOMTypeId) {
            this.tractorAttachmentUOMTypeId = tractorAttachmentUOMTypeId;
        }

    }

    public static class VendorTrasportationDetails {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("oilPalmInFactoryZone")
        @Expose
        private Boolean oilPalmInFactoryZone;
        @SerializedName("farmerCode")
        @Expose
        private String farmerCode;
        @SerializedName("farmerName")
        @Expose
        private String farmerName;
        @SerializedName("ownershipTypeId")
        @Expose
        private Integer ownershipTypeId;
        @SerializedName("ownerName")
        @Expose
        private String ownerName;
        @SerializedName("ownerAddress")
        @Expose
        private String ownerAddress;
        @SerializedName("ownerMobileNumber")
        @Expose
        private String ownerMobileNumber;
        @SerializedName("hiringServiceDurationTypeId")
        @Expose
        private Integer hiringServiceDurationTypeId;
        @SerializedName("hiringProblems")
        @Expose
        private String hiringProblems;
        @SerializedName("isAdvanceGiven")
        @Expose
        private Boolean isAdvanceGiven;
        @SerializedName("paymentPeriodTypeId")
        @Expose
        private Integer paymentPeriodTypeId;
        @SerializedName("paymentPeriodDesc")
        @Expose
        private String paymentPeriodDesc;
        @SerializedName("paymentTypeId")
        @Expose
        private Integer paymentTypeId;
        @SerializedName("doesWaitingFeeCharged")
        @Expose
        private Boolean doesWaitingFeeCharged;
        @SerializedName("waitingChargeDetails")
        @Expose
        private String waitingChargeDetails;
        @SerializedName("interestToApp")
        @Expose
        private Boolean interestToApp;
        @SerializedName("comments")
        @Expose
        private String comments;
        @SerializedName("tractorAttachments")
        @Expose
        private Boolean tractorAttachments;
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
        @SerializedName("paymentTypeDesc")
        @Expose
        private String paymentTypeDesc;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Boolean getOilPalmInFactoryZone() {
            return oilPalmInFactoryZone;
        }

        public void setOilPalmInFactoryZone(Boolean oilPalmInFactoryZone) {
            this.oilPalmInFactoryZone = oilPalmInFactoryZone;
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

        public Integer getOwnershipTypeId() {
            return ownershipTypeId;
        }

        public void setOwnershipTypeId(Integer ownershipTypeId) {
            this.ownershipTypeId = ownershipTypeId;
        }

        public String getOwnerName() {
            return ownerName;
        }

        public void setOwnerName(String ownerName) {
            this.ownerName = ownerName;
        }

        public String getOwnerAddress() {
            return ownerAddress;
        }

        public void setOwnerAddress(String ownerAddress) {
            this.ownerAddress = ownerAddress;
        }

        public String getOwnerMobileNumber() {
            return ownerMobileNumber;
        }

        public void setOwnerMobileNumber(String ownerMobileNumber) {
            this.ownerMobileNumber = ownerMobileNumber;
        }

        public Integer getHiringServiceDurationTypeId() {
            return hiringServiceDurationTypeId;
        }

        public void setHiringServiceDurationTypeId(Integer hiringServiceDurationTypeId) {
            this.hiringServiceDurationTypeId = hiringServiceDurationTypeId;
        }

        public String getHiringProblems() {
            return hiringProblems;
        }

        public void setHiringProblems(String hiringProblems) {
            this.hiringProblems = hiringProblems;
        }

        public Boolean getIsAdvanceGiven() {
            return isAdvanceGiven;
        }

        public void setIsAdvanceGiven(Boolean isAdvanceGiven) {
            this.isAdvanceGiven = isAdvanceGiven;
        }

        public Integer getPaymentPeriodTypeId() {
            return paymentPeriodTypeId;
        }

        public void setPaymentPeriodTypeId(Integer paymentPeriodTypeId) {
            this.paymentPeriodTypeId = paymentPeriodTypeId;
        }

        public String getPaymentPeriodDesc() {
            return paymentPeriodDesc;
        }

        public void setPaymentPeriodDesc(String paymentPeriodDesc) {
            this.paymentPeriodDesc = paymentPeriodDesc;
        }

        public Integer getPaymentTypeId() {
            return paymentTypeId;
        }

        public void setPaymentTypeId(Integer paymentTypeId) {
            this.paymentTypeId = paymentTypeId;
        }

        public Boolean getDoesWaitingFeeCharged() {
            return doesWaitingFeeCharged;
        }

        public void setDoesWaitingFeeCharged(Boolean doesWaitingFeeCharged) {
            this.doesWaitingFeeCharged = doesWaitingFeeCharged;
        }

        public String getWaitingChargeDetails() {
            return waitingChargeDetails;
        }

        public void setWaitingChargeDetails(String waitingChargeDetails) {
            this.waitingChargeDetails = waitingChargeDetails;
        }

        public Boolean getInterestToApp() {
            return interestToApp;
        }

        public void setInterestToApp(Boolean interestToApp) {
            this.interestToApp = interestToApp;
        }

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public Boolean getTractorAttachments() {
            return tractorAttachments;
        }

        public void setTractorAttachments(Boolean tractorAttachments) {
            this.tractorAttachments = tractorAttachments;
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

        public String getPaymentTypeDesc() {
            return paymentTypeDesc;
        }

        public void setPaymentTypeDesc(String paymentTypeDesc) {
            this.paymentTypeDesc = paymentTypeDesc;
        }

    }
    public static class VendorVehicleDetail {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("vendorTransportationServiceId")
        @Expose
        private Integer vendorTransportationServiceId;
        @SerializedName("vehicleTypeId")
        @Expose
        private Integer vehicleTypeId;
        @SerializedName("vehicleTypeDesc")
        @Expose
        private String vehicleTypeDesc;
        @SerializedName("vehicleCount")
        @Expose
        private Integer vehicleCount;
        @SerializedName("createdByUserId")
        @Expose
        private Integer createdByUserId;
        @SerializedName("createdDate")
        @Expose
        private String createdDate;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getVendorTransportationServiceId() {
            return vendorTransportationServiceId;
        }

        public void setVendorTransportationServiceId(Integer vendorTransportationServiceId) {
            this.vendorTransportationServiceId = vendorTransportationServiceId;
        }

        public Integer getVehicleTypeId() {
            return vehicleTypeId;
        }

        public void setVehicleTypeId(Integer vehicleTypeId) {
            this.vehicleTypeId = vehicleTypeId;
        }

        public String getVehicleTypeDesc() {
            return vehicleTypeDesc;
        }

        public void setVehicleTypeDesc(String vehicleTypeDesc) {
            this.vehicleTypeDesc = vehicleTypeDesc;
        }

        public Integer getVehicleCount() {
            return vehicleCount;
        }

        public void setVehicleCount(Integer vehicleCount) {
            this.vehicleCount = vehicleCount;
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
    }

    public static class VendorVillageDetail {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("vendorTransportationServiceId")
        @Expose
        private Integer vendorTransportationServiceId;
        @SerializedName("villageId")
        @Expose
        private Integer villageId;
        @SerializedName("createdByUserId")
        @Expose
        private Integer createdByUserId;
        @SerializedName("createdDate")
        @Expose
        private String createdDate;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getVendorTransportationServiceId() {
            return vendorTransportationServiceId;
        }

        public void setVendorTransportationServiceId(Integer vendorTransportationServiceId) {
            this.vendorTransportationServiceId = vendorTransportationServiceId;
        }

        public Integer getVillageId() {
            return villageId;
        }

        public void setVillageId(Integer villageId) {
            this.villageId = villageId;
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

    }}