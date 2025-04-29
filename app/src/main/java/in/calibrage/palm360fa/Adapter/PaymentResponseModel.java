package in.calibrage.palm360fa.Adapter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PaymentResponseModel {

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

        @SerializedName("totalQuanitity")
        @Expose
        private Double totalQuanitity;
        @SerializedName("totalGRAmount")
        @Expose
        private Double totalGRAmount;
        @SerializedName("totalAdjusted")
        @Expose
        private Double totalAdjusted;
        @SerializedName("totalAmount")
        @Expose
        private int totalAmount;
        @SerializedName("totalBalance")
        @Expose
        private Object totalBalance;
        @SerializedName("paymentResponce")
        @Expose
        private List<PaymentResponce> paymentResponce = null;

        public Double getTotalQuanitity() {
            return totalQuanitity;
        }

        public void setTotalQuanitity(Double totalQuanitity) {
            this.totalQuanitity = totalQuanitity;
        }

        public Double getTotalGRAmount() {
            return totalGRAmount;
        }

        public void setTotalGRAmount(Double totalGRAmount) {
            this.totalGRAmount = totalGRAmount;
        }

        public Double getTotalAdjusted() {
            return totalAdjusted;
        }

        public void setTotalAdjusted(Double totalAdjusted) {
            this.totalAdjusted = totalAdjusted;
        }

        public int getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(int totalAmount) {
            this.totalAmount = totalAmount;
        }

        public Object getTotalBalance() {
            return totalBalance;
        }

        public void setTotalBalance(Object totalBalance) {
            this.totalBalance = totalBalance;
        }

        public List<PaymentResponce> getPaymentResponce() {
            return paymentResponce;
        }

        public void setPaymentResponce(List<PaymentResponce> paymentResponce) {
            this.paymentResponce = paymentResponce;
        }

    }

    public class PaymentResponce {

        @SerializedName("cardCode")
        @Expose
        private String cardCode;
        @SerializedName("cardName")
        @Expose
        private String cardName;
        @SerializedName("u_village")
        @Expose
        private String uVillage;
        @SerializedName("u_mandal")
        @Expose
        private String uMandal;
        @SerializedName("u_FHname")
        @Expose
        private String uFHname;
        @SerializedName("bankName")
        @Expose
        private String bankName;
        @SerializedName("bank_Account")
        @Expose
        private String bankAccount;
        @SerializedName("branch")
        @Expose
        private String branch;
        @SerializedName("refDate")
        @Expose
        private String refDate;
        @SerializedName("quantity")
        @Expose
        private Double quantity;
        @SerializedName("memo")
        @Expose
        private Object memo;
        @SerializedName("obAmount")
        @Expose
        private Double obAmount;
        @SerializedName("amount")
        @Expose
        private Double amount;
        @SerializedName("adhoc_Rate")
        @Expose
        private Double adhocRate;
        @SerializedName("invoice_Rate")
        @Expose
        private Double invoiceRate;
        @SerializedName("ob")
        @Expose
        private Double ob;
        @SerializedName("cb")
        @Expose
        private Double cb;
        @SerializedName("gR_Amount")
        @Expose
        private Double gRAmount;
        @SerializedName("adjusted")
        @Expose
        private Double adjusted;
        @SerializedName("balance")
        @Expose
        private Double balance;

        public String getCardCode() {
            return cardCode;
        }

        public void setCardCode(String cardCode) {
            this.cardCode = cardCode;
        }

        public String getCardName() {
            return cardName;
        }

        public void setCardName(String cardName) {
            this.cardName = cardName;
        }

        public String getUVillage() {
            return uVillage;
        }

        public void setUVillage(String uVillage) {
            this.uVillage = uVillage;
        }

        public String getUMandal() {
            return uMandal;
        }

        public void setUMandal(String uMandal) {
            this.uMandal = uMandal;
        }

        public String getUFHname() {
            return uFHname;
        }

        public void setUFHname(String uFHname) {
            this.uFHname = uFHname;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getBankAccount() {
            return bankAccount;
        }

        public void setBankAccount(String bankAccount) {
            this.bankAccount = bankAccount;
        }

        public String getBranch() {
            return branch;
        }

        public void setBranch(String branch) {
            this.branch = branch;
        }

        public String getRefDate() {
            return refDate;
        }

        public void setRefDate(String refDate) {
            this.refDate = refDate;
        }

        public Double getQuantity() {
            return quantity;
        }

        public void setQuantity(Double quantity) {
            this.quantity = quantity;
        }

        public Object getMemo() {
            return memo;
        }

        public void setMemo(Object memo) {
            this.memo = memo;
        }

        public Double getObAmount() {
            return obAmount;
        }

        public void setObAmount(Double obAmount) {
            this.obAmount = obAmount;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }

        public Double getAdhocRate() {
            return adhocRate;
        }

        public void setAdhocRate(Double adhocRate) {
            this.adhocRate = adhocRate;
        }

        public Double getInvoiceRate() {
            return invoiceRate;
        }

        public void setInvoiceRate(Double invoiceRate) {
            this.invoiceRate = invoiceRate;
        }

        public Double getOb() {
            return ob;
        }

        public void setOb(Double ob) {
            this.ob = ob;
        }

        public Double getCb() {
            return cb;
        }

        public void setCb(Double cb) {
            this.cb = cb;
        }

        public Double getGRAmount() {
            return gRAmount;
        }

        public void setGRAmount(Double gRAmount) {
            this.gRAmount = gRAmount;
        }

        public Double getAdjusted() {
            return adjusted;
        }

        public void setAdjusted(Double adjusted) {
            this.adjusted = adjusted;
        }

        public Double getBalance() {
            return balance;
        }

        public void setBalance(Double balance) {
            this.balance = balance;
        }

    }

}



