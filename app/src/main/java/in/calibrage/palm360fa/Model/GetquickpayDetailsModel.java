package in.calibrage.palm360fa.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetquickpayDetailsModel {

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

        @SerializedName("quantity")
        @Expose
        private Double quantity;
        @SerializedName("ffbFlatCharge")
        @Expose
        private Double ffbFlatCharge;
        @SerializedName("ffbCost")
        @Expose
        private Double ffbCost;
        @SerializedName("convenienceCharge")
        @Expose
        private Double convenienceCharge;
        @SerializedName("closingBalance")
        @Expose
        private Double closingBalance;
        @SerializedName("quickPay")
        @Expose
        private Double quickPay;
        @SerializedName("total")
        @Expose
        private Double total;

        public Double getQuantity() {
            return quantity;
        }

        public void setQuantity(Double quantity) {
            this.quantity = quantity;
        }

        public Double getFfbFlatCharge() {
            return ffbFlatCharge;
        }

        public void setFfbFlatCharge(Double ffbFlatCharge) {
            this.ffbFlatCharge = ffbFlatCharge;
        }

        public Double getFfbCost() {
            return ffbCost;
        }

        public void setFfbCost(Double ffbCost) {
            this.ffbCost = ffbCost;
        }

        public Double getConvenienceCharge() {
            return convenienceCharge;
        }

        public void setConvenienceCharge(Double convenienceCharge) {
            this.convenienceCharge = convenienceCharge;
        }

        public Double getClosingBalance() {
            return closingBalance;
        }

        public void setClosingBalance(Double closingBalance) {
            this.closingBalance = closingBalance;
        }

        public Double getQuickPay() {
            return quickPay;
        }

        public void setQuickPay(Double quickPay) {
            this.quickPay = quickPay;
        }

        public Double getTotal() {
            return total;
        }

        public void setTotal(Double total) {
            this.total = total;
        }

    }
}