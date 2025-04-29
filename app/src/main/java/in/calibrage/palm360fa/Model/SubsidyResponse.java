package in.calibrage.palm360fa.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class SubsidyResponse {

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

        @SerializedName("totalSubsidyAmount")
        @Expose
        private Double totalSubsidyAmount;
        @SerializedName("usedSubsidyAmount")
        @Expose
        private Double usedSubsidyAmount;
        @SerializedName("remainingAmount")
        @Expose
        private Double remainingAmount;
        @SerializedName("expectedBenchmark")
        @Expose
        private Double expectedBenchmark;
        @SerializedName("currentNetWeight")
        @Expose
        private Double currentNetWeight;
        @SerializedName("previousNetWeight")
        @Expose
        private Double previousNetWeight;

        public Double getTotalSubsidyAmount() {
            return totalSubsidyAmount;
        }

        public void setTotalSubsidyAmount(Double totalSubsidyAmount) {
            this.totalSubsidyAmount = totalSubsidyAmount;
        }

        public Double getUsedSubsidyAmount() {
            return usedSubsidyAmount;
        }

        public void setUsedSubsidyAmount(Double usedSubsidyAmount) {
            this.usedSubsidyAmount = usedSubsidyAmount;
        }

        public Double getRemainingAmount() {
            return remainingAmount;
        }

        public void setRemainingAmount(Double remainingAmount) {
            this.remainingAmount = remainingAmount;
        }

        public Double getExpectedBenchmark() {
            return expectedBenchmark;
        }

        public void setExpectedBenchmark(Double expectedBenchmark) {
            this.expectedBenchmark = expectedBenchmark;
        }

        public Double getCurrentNetWeight() {
            return currentNetWeight;
        }

        public void setCurrentNetWeight(Double currentNetWeight) {
            this.currentNetWeight = currentNetWeight;
        }

        public Double getPreviousNetWeight() {
            return previousNetWeight;
        }

        public void setPreviousNetWeight(Double previousNetWeight) {
            this.previousNetWeight = previousNetWeight;
        }

    }
}