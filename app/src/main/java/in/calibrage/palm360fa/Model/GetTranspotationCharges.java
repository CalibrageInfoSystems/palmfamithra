package in.calibrage.palm360fa.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetTranspotationCharges {
    @SerializedName("transpotationCharges")
    @Expose
    private List<TranspotationCharge> transpotationCharges = null;
    @SerializedName("trasportRates")
    @Expose
    private List<TrasportRate> trasportRates = null;

    public List<TranspotationCharge> getTranspotationCharges() {
        return transpotationCharges;
    }

    public void setTranspotationCharges(List<TranspotationCharge> transpotationCharges) {
        this.transpotationCharges = transpotationCharges;
    }

    public List<TrasportRate> getTrasportRates() {
        return trasportRates;
    }

    public void setTrasportRates(List<TrasportRate> trasportRates) {
        this.trasportRates = trasportRates;
    }
    public class TranspotationCharge {

        @SerializedName("collectionCode")
        @Expose
        private String collectionCode;
        @SerializedName("farmerCode")
        @Expose
        private String farmerCode;
        @SerializedName("farmerName")
        @Expose
        private String farmerName;
        @SerializedName("tonnageCost")
        @Expose
        private Double tonnageCost;
        @SerializedName("rate")
        @Expose
        private Double rate;
        @SerializedName("qty")
        @Expose
        private Double qty;
        @SerializedName("receiptGeneratedDate")
        @Expose
        private String receiptGeneratedDate;

        public String getCollectionCode() {
            return collectionCode;
        }

        public void setCollectionCode(String collectionCode) {
            this.collectionCode = collectionCode;
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

        public Double getTonnageCost() {
            return tonnageCost;
        }

        public void setTonnageCost(Double tonnageCost) {
            this.tonnageCost = tonnageCost;
        }

        public Double getRate() {
            return rate;
        }

        public void setRate(Double rate) {
            this.rate = rate;
        }

        public Double getQty() {
            return qty;
        }

        public void setQty(Double qty) {
            this.qty = qty;
        }

        public String getReceiptGeneratedDate() {
            return receiptGeneratedDate;
        }

        public void setReceiptGeneratedDate(String receiptGeneratedDate) {
            this.receiptGeneratedDate = receiptGeneratedDate;
        }

    }


    public class TrasportRate {

        @SerializedName("farmerCode")
        @Expose
        private String farmerCode;
        @SerializedName("village")
        @Expose
        private String village;
        @SerializedName("mandal")
        @Expose
        private String mandal;
        @SerializedName("rate")
        @Expose
        private double rate;

        public String getFarmerCode() {
            return farmerCode;
        }

        public void setFarmerCode(String farmerCode) {
            this.farmerCode = farmerCode;
        }

        public String getVillage() {
            return village;
        }

        public void setVillage(String village) {
            this.village = village;
        }

        public String getMandal() {
            return mandal;
        }

        public void setMandal(String mandal) {
            this.mandal = mandal;
        }

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }

    }
}