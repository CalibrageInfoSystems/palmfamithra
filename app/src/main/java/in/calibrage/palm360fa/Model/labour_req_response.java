

        package in.calibrage.palm360fa.Model;

        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

        import java.util.List;

        public class labour_req_response {

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

                @SerializedName("requestCode")
                @Expose
                private String requestCode;
                @SerializedName("requestTypeId")
                @Expose
                private Integer requestTypeId;
                @SerializedName("requestType")
                @Expose
                private String requestType;
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
                @SerializedName("reqCreatedDate")
                @Expose
                private String reqCreatedDate;
                @SerializedName("statusTypeId")
                @Expose
                private Integer statusTypeId;
                @SerializedName("statusType")
                @Expose
                private String statusType;
                @SerializedName("isFarmerRequest")
                @Expose
                private Boolean isFarmerRequest;
                @SerializedName("startDate")
                @Expose
                private String startDate;
                @SerializedName("durationId")
                @Expose
                private Integer durationId;
                @SerializedName("duration")
                @Expose
                private String duration;
                @SerializedName("leaderId")
                @Expose
                private Object leaderId;
                @SerializedName("leader")
                @Expose
                private Object leader;
                @SerializedName("pin")
                @Expose
                private Integer pin;
                @SerializedName("jobDoneDate")
                @Expose
                private Object jobDoneDate;
                @SerializedName("createdByUserId")
                @Expose
                private Object createdByUserId;
                @SerializedName("createdBy")
                @Expose
                private Object createdBy;
                @SerializedName("createdDate")
                @Expose
                private String createdDate;
                @SerializedName("updatedByUserId")
                @Expose
                private Object updatedByUserId;
                @SerializedName("updatedBy")
                @Expose
                private Object updatedBy;
                @SerializedName("updatedDate")
                @Expose
                private String updatedDate;
                @SerializedName("netWeight")
                @Expose
                private Object netWeight;
                @SerializedName("netWeightIntercrop")
                @Expose
                private Object netWeightIntercrop;
                @SerializedName("totalCost")
                @Expose
                private Object totalCost;
                @SerializedName("collectionIds")
                @Expose
                private Object collectionIds;
                @SerializedName("isIntercrop")
                @Expose
                private Object isIntercrop;
                @SerializedName("comments")
                @Expose
                private String comments;
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
                @SerializedName("treesCount")
                @Expose
                private Object treesCount;
                @SerializedName("serviceTypes")
                @Expose
                private String serviceTypes;
                @SerializedName("serviceTypeIds")
                @Expose
                private String serviceTypeIds;
                @SerializedName("serviceChargePercentage")
                @Expose
                private Double serviceChargePercentage;
                @SerializedName("assignedDate")
                @Expose
                private String assignedDate;
                @SerializedName("treesCountWithIntercrop")
                @Expose
                private Object treesCountWithIntercrop;
                @SerializedName("yearofPlanting")
                @Expose
                private String yearofPlanting;
                @SerializedName("harvestingAmountWithCharge")
                @Expose
                private Double harvestingAmountWithCharge;
                @SerializedName("pruningAmountWithChange")
                @Expose
                private Double pruningAmountWithChange;
                @SerializedName("pruningWithIntercropAmountAndCharge")
                @Expose
                private Double pruningWithIntercropAmountAndCharge;
                @SerializedName("harvestingWithIntercropAmountAndCharge")
                @Expose
                private Double harvestingWithIntercropAmountAndCharge;

                public String getRequestCode() {
                    return requestCode;
                }

                public void setRequestCode(String requestCode) {
                    this.requestCode = requestCode;
                }

                public Integer getRequestTypeId() {
                    return requestTypeId;
                }

                public void setRequestTypeId(Integer requestTypeId) {
                    this.requestTypeId = requestTypeId;
                }

                public String getRequestType() {
                    return requestType;
                }

                public void setRequestType(String requestType) {
                    this.requestType = requestType;
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

                public String getReqCreatedDate() {
                    return reqCreatedDate;
                }

                public void setReqCreatedDate(String reqCreatedDate) {
                    this.reqCreatedDate = reqCreatedDate;
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

                public Boolean getIsFarmerRequest() {
                    return isFarmerRequest;
                }

                public void setIsFarmerRequest(Boolean isFarmerRequest) {
                    this.isFarmerRequest = isFarmerRequest;
                }

                public String getStartDate() {
                    return startDate;
                }

                public void setStartDate(String startDate) {
                    this.startDate = startDate;
                }

                public Integer getDurationId() {
                    return durationId;
                }

                public void setDurationId(Integer durationId) {
                    this.durationId = durationId;
                }

                public String getDuration() {
                    return duration;
                }

                public void setDuration(String duration) {
                    this.duration = duration;
                }

                public Object getLeaderId() {
                    return leaderId;
                }

                public void setLeaderId(Object leaderId) {
                    this.leaderId = leaderId;
                }

                public Object getLeader() {
                    return leader;
                }

                public void setLeader(Object leader) {
                    this.leader = leader;
                }

                public Integer getPin() {
                    return pin;
                }

                public void setPin(Integer pin) {
                    this.pin = pin;
                }

                public Object getJobDoneDate() {
                    return jobDoneDate;
                }

                public void setJobDoneDate(Object jobDoneDate) {
                    this.jobDoneDate = jobDoneDate;
                }

                public Object getCreatedByUserId() {
                    return createdByUserId;
                }

                public void setCreatedByUserId(Object createdByUserId) {
                    this.createdByUserId = createdByUserId;
                }

                public Object getCreatedBy() {
                    return createdBy;
                }

                public void setCreatedBy(Object createdBy) {
                    this.createdBy = createdBy;
                }

                public String getCreatedDate() {
                    return createdDate;
                }

                public void setCreatedDate(String createdDate) {
                    this.createdDate = createdDate;
                }

                public Object getUpdatedByUserId() {
                    return updatedByUserId;
                }

                public void setUpdatedByUserId(Object updatedByUserId) {
                    this.updatedByUserId = updatedByUserId;
                }

                public Object getUpdatedBy() {
                    return updatedBy;
                }

                public void setUpdatedBy(Object updatedBy) {
                    this.updatedBy = updatedBy;
                }

                public String getUpdatedDate() {
                    return updatedDate;
                }

                public void setUpdatedDate(String updatedDate) {
                    this.updatedDate = updatedDate;
                }

                public Object getNetWeight() {
                    return netWeight;
                }

                public void setNetWeight(Object netWeight) {
                    this.netWeight = netWeight;
                }

                public Object getNetWeightIntercrop() {
                    return netWeightIntercrop;
                }

                public void setNetWeightIntercrop(Object netWeightIntercrop) {
                    this.netWeightIntercrop = netWeightIntercrop;
                }

                public Object getTotalCost() {
                    return totalCost;
                }

                public void setTotalCost(Object totalCost) {
                    this.totalCost = totalCost;
                }

                public Object getCollectionIds() {
                    return collectionIds;
                }

                public void setCollectionIds(Object collectionIds) {
                    this.collectionIds = collectionIds;
                }

                public Object getIsIntercrop() {
                    return isIntercrop;
                }

                public void setIsIntercrop(Object isIntercrop) {
                    this.isIntercrop = isIntercrop;
                }

                public String getComments() {
                    return comments;
                }

                public void setComments(String comments) {
                    this.comments = comments;
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

                public Double getPruningWithIntercropAmount() {
                    return pruningWithIntercropAmount;
                }

                public void setPruningWithIntercropAmount(Double pruningWithIntercropAmount) {
                    this.pruningWithIntercropAmount = pruningWithIntercropAmount;
                }

                public Double getHarvestingWithIntercropAmount() {
                    return harvestingWithIntercropAmount;
                }

                public void setHarvestingWithIntercropAmount(Double harvestingWithIntercropAmount) {
                    this.harvestingWithIntercropAmount = harvestingWithIntercropAmount;
                }

                public Object getTreesCount() {
                    return treesCount;
                }

                public void setTreesCount(Object treesCount) {
                    this.treesCount = treesCount;
                }

                public String getServiceTypes() {
                    return serviceTypes;
                }

                public void setServiceTypes(String serviceTypes) {
                    this.serviceTypes = serviceTypes;
                }

                public String getServiceTypeIds() {
                    return serviceTypeIds;
                }

                public void setServiceTypeIds(String serviceTypeIds) {
                    this.serviceTypeIds = serviceTypeIds;
                }

                public Double getServiceChargePercentage() {
                    return serviceChargePercentage;
                }

                public void setServiceChargePercentage(Double serviceChargePercentage) {
                    this.serviceChargePercentage = serviceChargePercentage;
                }

                public String getAssignedDate() {
                    return assignedDate;
                }

                public void setAssignedDate(String assignedDate) {
                    this.assignedDate = assignedDate;
                }

                public Object getTreesCountWithIntercrop() {
                    return treesCountWithIntercrop;
                }

                public void setTreesCountWithIntercrop(Object treesCountWithIntercrop) {
                    this.treesCountWithIntercrop = treesCountWithIntercrop;
                }

                public String getYearofPlanting() {
                    return yearofPlanting;
                }

                public void setYearofPlanting(String yearofPlanting) {
                    this.yearofPlanting = yearofPlanting;
                }

                public Double getHarvestingAmountWithCharge() {
                    return harvestingAmountWithCharge;
                }

                public void setHarvestingAmountWithCharge(Double harvestingAmountWithCharge) {
                    this.harvestingAmountWithCharge = harvestingAmountWithCharge;
                }

                public Double getPruningAmountWithChange() {
                    return pruningAmountWithChange;
                }

                public void setPruningAmountWithChange(Double pruningAmountWithChange) {
                    this.pruningAmountWithChange = pruningAmountWithChange;
                }

                public Double getPruningWithIntercropAmountAndCharge() {
                    return pruningWithIntercropAmountAndCharge;
                }

                public void setPruningWithIntercropAmountAndCharge(Double pruningWithIntercropAmountAndCharge) {
                    this.pruningWithIntercropAmountAndCharge = pruningWithIntercropAmountAndCharge;
                }

                public Double getHarvestingWithIntercropAmountAndCharge() {
                    return harvestingWithIntercropAmountAndCharge;
                }

                public void setHarvestingWithIntercropAmountAndCharge(Double harvestingWithIntercropAmountAndCharge) {
                    this.harvestingWithIntercropAmountAndCharge = harvestingWithIntercropAmountAndCharge;
                }

            }
        }