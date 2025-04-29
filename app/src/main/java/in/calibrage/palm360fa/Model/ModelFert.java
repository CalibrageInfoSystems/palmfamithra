package in.calibrage.palm360fa.Model;

import java.util.ArrayList;

public class ModelFert {


    private String name;
    private double discountedPrice;
    private int price;
    private String imageUrl;
    private String description;
    private double size;
    private String uomType;
    private ArrayList<String> powers;
    private int mQuantity,avail_quantity;
    private String mAmount,product_code;
    private String gst;
    private int Id;
    private double transPortActualPriceInclGST;
    private double transportGSTPercentage;


    public ModelFert() {
        this.name = name;
        this.discountedPrice = discountedPrice;
        this.price = price;
        this.imageUrl = imageUrl;
        this.description = description;
        this.size = size;
        this.uomType = uomType;
        this.powers = powers;
        this.mAmount= mAmount;
        this.mQuantity = 0;
        this.Id=Id;
        this.gst=gst;
        this.avail_quantity =avail_quantity;
        this.product_code = product_code;

    }

    public void setmQuantity(int mQuantity) {
        this.mQuantity = mQuantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getUomType() {
        return uomType;
    }

    public void setUomType(String uomType) {
        this.uomType = uomType;
    }

    public ArrayList<String> getPowers() {
        return powers;
    }

    public void setPowers(ArrayList<String> powers) {
        this.powers = powers;
    }



    public void addToQuantity(){
        this.mQuantity += 1;
    }

    public void removeFromQuantity(){
        if(this.mQuantity > 0){
            this.mQuantity -= 1;
        }
    }
    public int getmQuantity(){
        return mQuantity;
    }

    public String getmAmount() {
        return mAmount;
    }

    public void setmAmount(String mAmount) {
        this.mAmount = mAmount;
    }


    public int getId(){
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
    public String getgst() {
        return gst;
    }

    public void setgst(String gst) {
        this.gst = gst;
    }

    public int getAvail_quantity() {
        return avail_quantity;
    }

    public void setAvail_quantity(int avail_quantity) {
        this.avail_quantity = avail_quantity;
    }


    public String getProduct_code() {
        return product_code;
    }



    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public double getTransPortActualPriceInclGST() {
        return transPortActualPriceInclGST;
    }

    public void setTransPortActualPriceInclGST(double transPortActualPriceInclGST) {
        this.transPortActualPriceInclGST = transPortActualPriceInclGST;
    }

    public double getTransportGSTPercentage() {
        return transportGSTPercentage;
    }

    public void setTransportGSTPercentage(double transportGSTPercentage) {
        this.transportGSTPercentage = transportGSTPercentage;
    }
}
