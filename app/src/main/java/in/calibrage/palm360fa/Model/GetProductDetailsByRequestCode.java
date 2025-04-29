package in.calibrage.palm360fa.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetProductDetailsByRequestCode {

    @SerializedName("listResult")
    @Expose
    private List<ListResult> listResult;
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
    private List<Object> validationErrors;
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
    @SerializedName("productId")
    @Expose
    private Integer productId;
    @SerializedName("productCode")
    @Expose
    private String productCode;
    @SerializedName("godownCode")
    @Expose
    private String godownCode;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("bagCost")
    @Expose
    private Double bagCost;
    @SerializedName("farmerCode")
    @Expose
    private String farmerCode;
    @SerializedName("bagSize")
    @Expose
    private Double bagSize;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("amount")
    @Expose
    private Double amount;
    @SerializedName("gstPercentage")
    @Expose
    private Double gstPercentage;
    @SerializedName("cgstPercentage")
    @Expose
    private Double cgstPercentage;
    @SerializedName("sgstPercentage")
    @Expose
    private Double sgstPercentage;
    @SerializedName("cgst")
    @Expose
    private Double cgst;
    @SerializedName("sgst")
    @Expose
    private Double sgst;
    @SerializedName("basePrice")
    @Expose
    private Double basePrice;
    @SerializedName("transPortGSTPercentage")
    @Expose
    private Double transPortGSTPercentage;
    @SerializedName("transPortCGSTPercentage")
    @Expose
    private Double transPortCGSTPercentage;
    @SerializedName("transPortSGSTPercentage")
    @Expose
    private Double transPortSGSTPercentage;
    @SerializedName("transPortCGST")
    @Expose
    private Double transPortCGST;
    @SerializedName("transPortSGST")
    @Expose
    private Double transPortSGST;
    @SerializedName("transportBasePrice")
    @Expose
    private Double transportBasePrice;
    @SerializedName("transPortAmount")
    @Expose
    private Double transPortAmount;
    @SerializedName("transPortTotalAmount")
    @Expose
    private Double transPortTotalAmount;
    @SerializedName("transPortCost")
    @Expose
    private Double transPortCost;
    @SerializedName("totalAmount")
    @Expose
    private Double totalAmount;
    @SerializedName("closedDate")
    @Expose
    private Object closedDate;
    @SerializedName("requestTotalAmount")
    @Expose
    private Double requestTotalAmount;
    @SerializedName("requestTotalTransport")
    @Expose
    private Double requestTotalTransport;

    public String getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getGodownCode() {
        return godownCode;
    }

    public void setGodownCode(String godownCode) {
        this.godownCode = godownCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getBagCost() {
        return bagCost;
    }

    public void setBagCost(Double bagCost) {
        this.bagCost = bagCost;
    }

    public String getFarmerCode() {
        return farmerCode;
    }

    public void setFarmerCode(String farmerCode) {
        this.farmerCode = farmerCode;
    }

    public Double getBagSize() {
        return bagSize;
    }

    public void setBagSize(Double bagSize) {
        this.bagSize = bagSize;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getGstPercentage() {
        return gstPercentage;
    }

    public void setGstPercentage(Double gstPercentage) {
        this.gstPercentage = gstPercentage;
    }

    public Double getCgstPercentage() {
        return cgstPercentage;
    }

    public void setCgstPercentage(Double cgstPercentage) {
        this.cgstPercentage = cgstPercentage;
    }

    public Double getSgstPercentage() {
        return sgstPercentage;
    }

    public void setSgstPercentage(Double sgstPercentage) {
        this.sgstPercentage = sgstPercentage;
    }

    public Double getCgst() {
        return cgst;
    }

    public void setCgst(Double cgst) {
        this.cgst = cgst;
    }

    public Double getSgst() {
        return sgst;
    }

    public void setSgst(Double sgst) {
        this.sgst = sgst;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public Double getTransPortGSTPercentage() {
        return transPortGSTPercentage;
    }

    public void setTransPortGSTPercentage(Double transPortGSTPercentage) {
        this.transPortGSTPercentage = transPortGSTPercentage;
    }

    public Double getTransPortCGSTPercentage() {
        return transPortCGSTPercentage;
    }

    public void setTransPortCGSTPercentage(Double transPortCGSTPercentage) {
        this.transPortCGSTPercentage = transPortCGSTPercentage;
    }

    public Double getTransPortSGSTPercentage() {
        return transPortSGSTPercentage;
    }

    public void setTransPortSGSTPercentage(Double transPortSGSTPercentage) {
        this.transPortSGSTPercentage = transPortSGSTPercentage;
    }

    public Double getTransPortCGST() {
        return transPortCGST;
    }

    public void setTransPortCGST(Double transPortCGST) {
        this.transPortCGST = transPortCGST;
    }

    public Double getTransPortSGST() {
        return transPortSGST;
    }

    public void setTransPortSGST(Double transPortSGST) {
        this.transPortSGST = transPortSGST;
    }

    public Double getTransportBasePrice() {
        return transportBasePrice;
    }

    public void setTransportBasePrice(Double transportBasePrice) {
        this.transportBasePrice = transportBasePrice;
    }

    public Double getTransPortAmount() {
        return transPortAmount;
    }

    public void setTransPortAmount(Double transPortAmount) {
        this.transPortAmount = transPortAmount;
    }

    public Double getTransPortTotalAmount() {
        return transPortTotalAmount;
    }

    public void setTransPortTotalAmount(Double transPortTotalAmount) {
        this.transPortTotalAmount = transPortTotalAmount;
    }

    public Double getTransPortCost() {
        return transPortCost;
    }

    public void setTransPortCost(Double transPortCost) {
        this.transPortCost = transPortCost;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Object getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(Object closedDate) {
        this.closedDate = closedDate;
    }

    public Double getRequestTotalAmount() {
        return requestTotalAmount;
    }

    public void setRequestTotalAmount(Double requestTotalAmount) {
        this.requestTotalAmount = requestTotalAmount;
    }

    public Double getRequestTotalTransport() {
        return requestTotalTransport;
    }

    public void setRequestTotalTransport(Double requestTotalTransport) {
        this.requestTotalTransport = requestTotalTransport;
    }

}}