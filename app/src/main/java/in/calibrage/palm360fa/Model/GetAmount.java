package in.calibrage.palm360fa.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetAmount {

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

        @SerializedName("harvestCost")
        @Expose
        private Double harvestCost;
        @SerializedName("prunningCost")
        @Expose
        private Double prunningCost;
        @SerializedName("servicePercentage")
        @Expose
        private Integer servicePercentage;
        @SerializedName("pruningWithIntercropCost")
        @Expose
        private Double pruningWithIntercropCost;
        @SerializedName("harvestingWithIntercropCost")
        @Expose
        private Double harvestingWithIntercropCost;

        public Double getHarvestCost() {
            return harvestCost;
        }

        public void setHarvestCost(Double harvestCost) {
            this.harvestCost = harvestCost;
        }

        public Double getPrunningCost() {
            return prunningCost;
        }

        public void setPrunningCost(Double prunningCost) {
            this.prunningCost = prunningCost;
        }

        public Double getUnKnown1Cost() {
            return pruningWithIntercropCost;
        }

        public void setUnKnown1Cost(Double unKnown1Cost) {
            this.pruningWithIntercropCost = unKnown1Cost;
        }
        public Integer getservicePercentage() {
            return servicePercentage;
        }

        public void setservicePercentage(Integer servicePercentage) {
            this.servicePercentage = servicePercentage;
        }

        public Double getUnKnown2Cost() {
            return harvestingWithIntercropCost;
        }

        public void setUnKnown2Cost(Double unKnown2Cost) {
            this.harvestingWithIntercropCost = unKnown2Cost;
        }

    }
}