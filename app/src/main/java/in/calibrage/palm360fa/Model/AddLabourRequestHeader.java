  package in.calibrage.palm360fa.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

  public class AddLabourRequestHeader {

      @SerializedName("farmerCode")
      @Expose
      private String farmerCode;
      @SerializedName("farmerName")
      @Expose
      private String farmerName;
      @SerializedName("plotCode")
      @Expose
      private String plotCode;
      @SerializedName("plotVillage")
      @Expose
      private String plotVillage;
      @SerializedName("palmArea")
      @Expose
      private Double palmArea;
      @SerializedName("isFarmerRequest")
      @Expose
      private Boolean isFarmerRequest;
      @SerializedName("comments")
      @Expose
      private String comments;
      @SerializedName("preferredDate")
      @Expose
      private String preferredDate;
      @SerializedName("durationId")
      @Expose
      private Integer durationId;
      @SerializedName("serviceTypes")
      @Expose
      private String serviceTypes;
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
      @SerializedName("amount")
      @Expose
      private Double amount;
      @SerializedName("harvestingAmount")
      @Expose
      private Double harvestingAmount;
      @SerializedName("pruningAmount")
      @Expose
      private Double pruningAmount;
      @SerializedName("pruningWithIntercropAmount")
      @Expose
      private Double pruningWithIntercropAmount;
      @SerializedName("harvestingWithIntercropAmount")
      @Expose
      private Double harvestingWithIntercropAmount;

      @SerializedName("yearofPlanting")
      @Expose
      private String yearofPlanting;
      @SerializedName("clusterId")
      @Expose
      private Integer clusterId;
      @SerializedName("ownPole")
      @Expose
      private Boolean ownPole;
      @SerializedName("services")
      @Expose
      private String services;
      @SerializedName("package")
      @Expose
      private String _package;
      @SerializedName("stateCode")
      @Expose
      private String stateCode;
      @SerializedName("stateName")
      @Expose
      private String stateName;
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

      public String getPlotVillage() {
          return plotVillage;
      }

      public void setPlotVillage(String plotVillage) {
          this.plotVillage = plotVillage;
      }

      public Double getPalmArea() {
          return palmArea;
      }

      public void setPalmArea(Double palmArea) {
          this.palmArea = palmArea;
      }

      public Boolean getIsFarmerRequest() {
          return isFarmerRequest;
      }

      public void setIsFarmerRequest(Boolean isFarmerRequest) {
          this.isFarmerRequest = isFarmerRequest;
      }

      public String getComments() {
          return comments;
      }

      public void setComments(String comments) {
          this.comments = comments;
      }

      public String getPreferredDate() {
          return preferredDate;
      }

      public void setPreferredDate(String preferredDate) {
          this.preferredDate = preferredDate;
      }

      public Integer getDurationId() {
          return durationId;
      }

      public void setDurationId(Integer durationId) {
          this.durationId = durationId;
      }

      public String getServiceTypes() {
          return serviceTypes;
      }

      public void setServiceTypes(String serviceTypes) {
          this.serviceTypes = serviceTypes;
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

      public Double getAmount() {
          return amount;
      }

      public void setAmount(Double amount) {
          this.amount = amount;
      }

      public Double getHarvestingAmount() {
          return harvestingAmount;
      }

      public void setHarvestingAmount(Double harvestingAmount) {
          this.harvestingAmount = harvestingAmount;
      }

      public Double getPruningAmount() {
          return pruningAmount;
      }

      public void setPruningAmount(Double pruningAmount) {
          this.pruningAmount = pruningAmount;
      }

      public Double getUnKnown1Amount() {
          return pruningWithIntercropAmount;
      }

      public void setUnKnown1Amount(Double unKnown1Amount) {
          this.pruningWithIntercropAmount = unKnown1Amount;
      }

      public Double getUnKnown2Amount() {
          return harvestingWithIntercropAmount;
      }

      public void setUnKnown2Amount(Double unKnown2Amount) {
          this.harvestingWithIntercropAmount = unKnown2Amount;
      }

      public String getYearofPlanting() {
          return yearofPlanting;
      }

      public void setYearofPlanting(String yearofPlanting) {
          this.yearofPlanting = yearofPlanting;
      }

      public Integer getClusterId() {
          return clusterId;
      }

      public void setClusterId(Integer clusterId) {
          this.clusterId = clusterId;
      }

      public Boolean getOwnPole() {
          return ownPole;
      }

      public void setOwnPole(Boolean ownPole) {
          this.ownPole = ownPole;
      }
      public String getServices() {
          return services;
      }

      public void setServices(String services) {
          this.services = services;
      }

      public String getPackage() {
          return _package;
      }

      public void setPackage(String _package) {
          this._package = _package;
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
  }