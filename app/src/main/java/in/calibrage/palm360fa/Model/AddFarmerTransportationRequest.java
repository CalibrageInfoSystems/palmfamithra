package in.calibrage.palm360fa.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AddFarmerTransportationRequest {


        @SerializedName("farmerTrasportationDetails")
        @Expose
        private FarmerTrasportationDetails farmerTrasportationDetails;
        @SerializedName("farmerVehicleDetails")
        @Expose
        private List<FarmerVehicleDetail> farmerVehicleDetails = null;
        @SerializedName("farmerTransportHireCharges")
        @Expose
        private List<FarmerTransportHireCharge> farmerTransportHireCharges = null;
        @SerializedName("farmerTransportHireBasis")
        @Expose
        private List<FarmerTransportHireBasi> farmerTransportHireBasis = null;
        @SerializedName("farmerServices")
        @Expose
        private List<FarmerService> farmerServices = null;

        public FarmerTrasportationDetails getFarmerTrasportationDetails() {
            return farmerTrasportationDetails;
        }

        public void setFarmerTrasportationDetails(FarmerTrasportationDetails farmerTrasportationDetails) {
            this.farmerTrasportationDetails = farmerTrasportationDetails;
        }

        public List<FarmerVehicleDetail> getFarmerVehicleDetails() {
            return farmerVehicleDetails;
        }

        public void setFarmerVehicleDetails(List<FarmerVehicleDetail> farmerVehicleDetails) {
            this.farmerVehicleDetails = farmerVehicleDetails;
        }

        public List<FarmerTransportHireCharge> getFarmerTransportHireCharges() {
            return farmerTransportHireCharges;
        }

        public void setFarmerTransportHireCharges(List<FarmerTransportHireCharge> farmerTransportHireCharges) {
            this.farmerTransportHireCharges = farmerTransportHireCharges;
        }

        public List<FarmerTransportHireBasi> getFarmerTransportHireBasis() {
            return farmerTransportHireBasis;
        }

        public void setFarmerTransportHireBasis(List<FarmerTransportHireBasi> farmerTransportHireBasis) {
            this.farmerTransportHireBasis = farmerTransportHireBasis;
        }

        public List<FarmerService> getFarmerServices() {
            return farmerServices;
        }

        public void setFarmerServices(List<FarmerService> farmerServices) {
            this.farmerServices = farmerServices;
        }


    public AddFarmerTransportationRequest(FarmerTrasportationDetails farmerTrasportationDetails, List<FarmerVehicleDetail> farmerVehicleDetails, List<FarmerTransportHireCharge> farmerTransportHireCharges, List<FarmerTransportHireBasi> farmerTransportHireBasis, List<FarmerService> farmerServices) {
        this.farmerTrasportationDetails = farmerTrasportationDetails;
        this.farmerVehicleDetails = farmerVehicleDetails;
        this.farmerTransportHireCharges = farmerTransportHireCharges;
        this.farmerTransportHireBasis = farmerTransportHireBasis;
        this.farmerServices = farmerServices;
    }

    public static class FarmerService {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("farmerTransportationServiceId")
        @Expose
        private Integer farmerTransportationServiceId;
        @SerializedName("serviceTypeId")
        @Expose
        private Integer serviceTypeId;
        @SerializedName("serviceDesc")
        @Expose
        private String serviceDesc;
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

        public Integer getFarmerTransportationServiceId() {
            return farmerTransportationServiceId;
        }

        public void setFarmerTransportationServiceId(Integer farmerTransportationServiceId) {
            this.farmerTransportationServiceId = farmerTransportationServiceId;
        }

        public Integer getServiceTypeId() {
            return serviceTypeId;
        }

        public void setServiceTypeId(Integer serviceTypeId) {
            this.serviceTypeId = serviceTypeId;
        }

        public String getServiceDesc() {
            return serviceDesc;
        }

        public void setServiceDesc(String serviceDesc) {
            this.serviceDesc = serviceDesc;
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

    public static class FarmerTransportHireBasi {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("farmerTransportationServiceId")
        @Expose
        private Integer farmerTransportationServiceId;
        @SerializedName("transportFFBTypeId")
        @Expose
        private Integer transportFFBTypeId;
        @SerializedName("transportFFBDesc")
        @Expose
        private String transportFFBDesc;
        @SerializedName("hiringTypeId")
        @Expose
        private Integer hiringTypeId;
        @SerializedName("ownerName")
        @Expose
        private String ownerName;
        @SerializedName("ownerAddress")
        @Expose
        private String ownerAddress;
        @SerializedName("ownerMobileNumber")
        @Expose
        private String ownerMobileNumber;
        @SerializedName("createdByUserId")
        @Expose
        private Integer createdByUserId;
        @SerializedName("createdDate")
        @Expose
        private String createdDate;
        @SerializedName("villageId")
        @Expose
        private Integer villageId;

        public Integer getVillageId() {
            return villageId;
        }

        public void setVillageId(Integer villageId) {
            this.villageId = villageId;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getFarmerTransportationServiceId() {
            return farmerTransportationServiceId;
        }

        public void setFarmerTransportationServiceId(Integer farmerTransportationServiceId) {
            this.farmerTransportationServiceId = farmerTransportationServiceId;
        }

        public Integer getTransportFFBTypeId() {
            return transportFFBTypeId;
        }

        public void setTransportFFBTypeId(Integer transportFFBTypeId) {
            this.transportFFBTypeId = transportFFBTypeId;
        }

        public String getTransportFFBDesc() {
            return transportFFBDesc;
        }

        public void setTransportFFBDesc(String transportFFBDesc) {
            this.transportFFBDesc = transportFFBDesc;
        }

        public Integer getHiringTypeId() {
            return hiringTypeId;
        }

        public void setHiringTypeId(Integer hiringTypeId) {
            this.hiringTypeId = hiringTypeId;
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

    public static class FarmerTransportHireCharge {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("farmerTransportationServiceId")
        @Expose
        private Integer farmerTransportationServiceId;
        @SerializedName("seasonTypeId")
        @Expose
        private Integer seasonTypeId;
        @SerializedName("vehicleTypeId")
        @Expose
        private Integer vehicleTypeId;
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

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getFarmerTransportationServiceId() {
            return farmerTransportationServiceId;
        }

        public void setFarmerTransportationServiceId(Integer farmerTransportationServiceId) {
            this.farmerTransportationServiceId = farmerTransportationServiceId;
        }

        public Integer getSeasonTypeId() {
            return seasonTypeId;
        }

        public void setSeasonTypeId(Integer seasonTypeId) {
            this.seasonTypeId = seasonTypeId;
        }

        public Integer getVehicleTypeId() {
            return vehicleTypeId;
        }

        public void setVehicleTypeId(Integer vehicleTypeId) {
            this.vehicleTypeId = vehicleTypeId;
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

    }

    public static class FarmerTrasportationDetails {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("farmerCode")
        @Expose
        private String farmerCode;
        @SerializedName("farmerName")
        @Expose
        private String farmerName;
        @SerializedName("ownVehicle")
        @Expose
        private Boolean ownVehicle;
        @SerializedName("hiringProblems")
        @Expose
        private String hiringProblems;
        @SerializedName("labourTypeId")
        @Expose
        private Integer labourTypeId;
        @SerializedName("labourCharges")
        @Expose
        private Double labourCharges;
        @SerializedName("labourChargesTypeId")
        @Expose
        private Integer labourChargesTypeId;
        @SerializedName("paymentTypeId")
        @Expose
        private Integer paymentTypeId;
        @SerializedName("paymentTypeDesc")
        @Expose
        private String paymentTypeDesc;
        @SerializedName("transportServiceFrom3F")
        @Expose
        private Integer transportServiceFrom3F;
        @SerializedName("suggestions")
        @Expose
        private String suggestions;
        @SerializedName("hireTransportOtherThanTransport")
        @Expose
        private Boolean hireTransportOtherThanTransport;
        @SerializedName("otherServices")
        @Expose
        private String otherServices;
        @SerializedName("knownAkshaya")
        @Expose
        private Boolean knownAkshaya;
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
        @SerializedName("farmerMobileNumber")
        @Expose
        private String farmerMobileNumber;
        @SerializedName("villageId")
        @Expose
        private Integer villageId;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
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

        public Boolean getOwnVehicle() {
            return ownVehicle;
        }

        public void setOwnVehicle(Boolean ownVehicle) {
            this.ownVehicle = ownVehicle;
        }

        public String getHiringProblems() {
            return hiringProblems;
        }

        public void setHiringProblems(String hiringProblems) {
            this.hiringProblems = hiringProblems;
        }

        public Integer getLabourTypeId() {
            return labourTypeId;
        }

        public void setLabourTypeId(Integer labourTypeId) {
            this.labourTypeId = labourTypeId;
        }

        public Double getLabourCharges() {
            return labourCharges;
        }

        public void setLabourCharges(Double labourCharges) {
            this.labourCharges = labourCharges;
        }

        public Integer getLabourChargesTypeId() {
            return labourChargesTypeId;
        }

        public void setLabourChargesTypeId(Integer labourChargesTypeId) {
            this.labourChargesTypeId = labourChargesTypeId;
        }

        public Integer getPaymentTypeId() {
            return paymentTypeId;
        }

        public void setPaymentTypeId(Integer paymentTypeId) {
            this.paymentTypeId = paymentTypeId;
        }

        public String getPaymentTypeDesc() {
            return paymentTypeDesc;
        }

        public void setPaymentTypeDesc(String paymentTypeDesc) {
            this.paymentTypeDesc = paymentTypeDesc;
        }

        public Integer getTransportServiceFrom3F() {
            return transportServiceFrom3F;
        }

        public void setTransportServiceFrom3F(Integer transportServiceFrom3F) {
            this.transportServiceFrom3F = transportServiceFrom3F;
        }

        public String getSuggestions() {
            return suggestions;
        }

        public void setSuggestions(String suggestions) {
            this.suggestions = suggestions;
        }

        public Boolean getHireTransportOtherThanTransport() {
            return hireTransportOtherThanTransport;
        }

        public void setHireTransportOtherThanTransport(Boolean hireTransportOtherThanTransport) {
            this.hireTransportOtherThanTransport = hireTransportOtherThanTransport;
        }

        public String getOtherServices() {
            return otherServices;
        }

        public void setOtherServices(String otherServices) {
            this.otherServices = otherServices;
        }

        public Boolean getKnownAkshaya() {
            return knownAkshaya;
        }

        public void setKnownAkshaya(Boolean knownAkshaya) {
            this.knownAkshaya = knownAkshaya;
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

        public String getFarmerMobileNumber() {
            return farmerMobileNumber;
        }

        public void setFarmerMobileNumber(String farmerMobileNumber) {
            this.farmerMobileNumber = farmerMobileNumber;
        }

        public Integer getVillageId() {
            return villageId;
        }

        public void setVillageId(Integer villageId) {
            this.villageId = villageId;
        }

    }
    public static class FarmerVehicleDetail {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("farmerTransportationServiceId")
        @Expose
        private Integer farmerTransportationServiceId;
        @SerializedName("vehicleTypeId")
        @Expose
        private Integer vehicleTypeId;
        @SerializedName("vehicleTypeDesc")
        @Expose
        private String vehicleTypeDesc;
        @SerializedName("driverTypeId")
        @Expose
        private Integer driverTypeId;
        @SerializedName("driverPrice")
        @Expose
        private Double driverPrice;
        @SerializedName("driverPaymentTypeId")
        @Expose
        private Integer driverPaymentTypeId;
        @SerializedName("isVehicleRented")
        @Expose
        private Boolean isVehicleRented;
        @SerializedName("willingToRentVehicle")
        @Expose
        private Boolean willingToRentVehicle;
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

        public Integer getFarmerTransportationServiceId() {
            return farmerTransportationServiceId;
        }

        public void setFarmerTransportationServiceId(Integer farmerTransportationServiceId) {
            this.farmerTransportationServiceId = farmerTransportationServiceId;
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

        public Integer getDriverTypeId() {
            return driverTypeId;
        }

        public void setDriverTypeId(Integer driverTypeId) {
            this.driverTypeId = driverTypeId;
        }

        public Double getDriverPrice() {
            return driverPrice;
        }

        public void setDriverPrice(Double driverPrice) {
            this.driverPrice = driverPrice;
        }

        public Integer getDriverPaymentTypeId() {
            return driverPaymentTypeId;
        }

        public void setDriverPaymentTypeId(Integer driverPaymentTypeId) {
            this.driverPaymentTypeId = driverPaymentTypeId;
        }

        public Boolean getIsVehicleRented() {
            return isVehicleRented;
        }

        public void setIsVehicleRented(Boolean isVehicleRented) {
            this.isVehicleRented = isVehicleRented;
        }

        public Boolean getWillingToRentVehicle() {
            return willingToRentVehicle;
        }

        public void setWillingToRentVehicle(Boolean willingToRentVehicle) {
            this.willingToRentVehicle = willingToRentVehicle;
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

    }

