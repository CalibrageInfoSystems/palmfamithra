package in.calibrage.palm360fa.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ExportPayments {


    @SerializedName("bankDetails")
    @Expose
    private BankDetails bankDetails;
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

    public BankDetails getBankDetails() {
        return bankDetails;
    }

    public void setBankDetails(BankDetails bankDetails) {
        this.bankDetails = bankDetails;
    }

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

    public ExportPayments(BankDetails bankDetails, Double totalQuanitity, Double totalGRAmount, Double totalAdjusted, int totalAmount, Object totalBalance, List<PaymentResponce> paymentResponce) {
        this.bankDetails = bankDetails;
        this.totalQuanitity = totalQuanitity;
        this.totalGRAmount = totalGRAmount;
        this.totalAdjusted = totalAdjusted;
        this.totalAmount = totalAmount;
        this.totalBalance = totalBalance;
        this.paymentResponce = paymentResponce;
    }

    public static class PaymentResponce {

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
        private String memo;
        @SerializedName("obAmount")
        @Expose
        private Double obAmount;
        @SerializedName("amount")
        @Expose
        private int amount;
        @SerializedName("adhoc_Rate")
        @Expose
        private int adhocRate;
        @SerializedName("invoice_Rate")
        @Expose
        private int invoiceRate;
        @SerializedName("ob")
        @Expose
        private Double ob;
        @SerializedName("cb")
        @Expose
        private Double cb;
        @SerializedName("gR_Amount")
        @Expose
        private int gRAmount;
        @SerializedName("adjusted")
        @Expose
        private int adjusted;
        @SerializedName("balance")
        @Expose
        private String balance;

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

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public Double getObAmount() {
            return obAmount;
        }

        public void setObAmount(Double obAmount) {
            this.obAmount = obAmount;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getAdhocRate() {
            return adhocRate;
        }

        public void setAdhocRate(int adhocRate) {
            this.adhocRate = adhocRate;
        }

        public int getInvoiceRate(int invoiceRate) {
            return this.invoiceRate;
        }

        public void setInvoiceRate(int invoiceRate) {
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

        public int getGRAmount() {
            return gRAmount;
        }

        public void setGRAmount(int gRAmount) {
            this.gRAmount = gRAmount;
        }

        public int getAdjusted() {
            return adjusted;
        }

        public void setAdjusted(int adjusted) {
            this.adjusted = adjusted;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public PaymentResponce()
        {

        }
    }
    public static class BankDetails {

        @SerializedName("farmerCode")
        @Expose
        private String farmerCode;
        @SerializedName("accountHolderName")
        @Expose
        private String accountHolderName;
        @SerializedName("accountNumber")
        @Expose
        private String accountNumber;
        @SerializedName("bankName")
        @Expose
        private String bankName;
        @SerializedName("branchName")
        @Expose
        private String branchName;
        @SerializedName("ifscCode")
        @Expose
        private String ifscCode;
        @SerializedName("guardianName")
        @Expose
        private String guardianName;
        @SerializedName("state")
        @Expose
        private String state;
        @SerializedName("district")
        @Expose
        private String district;
        @SerializedName("mandal")
        @Expose
        private String mandal;
        @SerializedName("village")
        @Expose
        private String village;

        public String getFarmerCode() {
            return farmerCode;
        }

        public void setFarmerCode(String farmerCode) {
            this.farmerCode = farmerCode;
        }

        public String getAccountHolderName() {
            return accountHolderName;
        }

        public void setAccountHolderName(String accountHolderName) {
            this.accountHolderName = accountHolderName;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getBranchName() {
            return branchName;
        }

        public void setBranchName(String branchName) {
            this.branchName = branchName;
        }

        public String getIfscCode() {
            return ifscCode;
        }

        public void setIfscCode(String ifscCode) {
            this.ifscCode = ifscCode;
        }

        public String getGuardianName() {
            return guardianName;
        }

        public void setGuardianName(String guardianName) {
            this.guardianName = guardianName;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getMandal() {
            return mandal;
        }

        public void setMandal(String mandal) {
            this.mandal = mandal;
        }

        public String getVillage() {
            return village;
        }

        public void setVillage(String village) {
            this.village = village;
        }

        public BankDetails() {
            this.farmerCode = farmerCode;
            this.accountHolderName = accountHolderName;
            this.accountNumber = accountNumber;
            this.bankName = bankName;
            this.branchName = branchName;
            this.ifscCode = ifscCode;
            this.guardianName = guardianName;
            this.state = state;
            this.district = district;
            this.mandal = mandal;
            this.village = village;
        }
    }}