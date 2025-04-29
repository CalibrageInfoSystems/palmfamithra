package in.calibrage.palm360fa.Model;

public class SelectedProducts {
    private Integer Quandity;
    private String Productname;
    private Double amount;
    private double withGSTamount;
    private double gst;
    private double eachproductcost;
    private double size;
    private  String product_code;
    private Integer ProductID;
    private Double TranportPrice;
    private double transgst;

    public SelectedProducts(Integer quandity, String productname, Double amount, double withGSTamount, double gst, double eachproductcost, double size, String product_code, Integer productID, Double tranportPrice, double transgst) {
        Quandity = quandity;
        Productname = productname;
        this.amount = amount;
        this.withGSTamount = withGSTamount;
        this.gst = gst;
        this.eachproductcost = eachproductcost;
        this.size = size;
        this.product_code = product_code;
        ProductID = productID;
        TranportPrice = tranportPrice;
        this.transgst = transgst;
    }

    public Integer getQuandity() {
        return Quandity;
    }

    public void setQuandity(Integer quandity) {
        Quandity = quandity;
    }

    public String getProductname() {
        return Productname;
    }

    public void setProductname(String productname) {
        Productname = productname;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public double getWithGSTamount() {
        return withGSTamount;
    }

    public void setWithGSTamount(double withGSTamount) {
        this.withGSTamount = withGSTamount;
    }

    public double getGst() {
        return gst;
    }

    public void setGst(double gst) {
        this.gst = gst;
    }

    public double getEachproductcost() {
        return eachproductcost;
    }

    public void setEachproductcost(double eachproductcost) {
        this.eachproductcost = eachproductcost;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public Integer getProductID() {
        return ProductID;
    }

    public void setProductID(Integer productID) {
        ProductID = productID;
    }

    public Double getTranportPrice() {
        return TranportPrice;
    }

    public void setTranportPrice(Double tranportPrice) {
        TranportPrice = tranportPrice;
    }

    public double getTransgst() {
        return transgst;
    }

    public void setTransgst(double transgst) {
        this.transgst = transgst;
    }
}
