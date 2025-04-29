package in.calibrage.palm360fa.Model;

public class Hirecharges {
    private  Integer ccid;
    private  double Price;
    private int position;
    private  int village_id;
    private int vehicleid;
    private  int mainlist_po;
    private int hirebasisid;
    private int opthirebasisid;

    public Hirecharges(Integer ccid, double price, Integer optccid, double optPrice, int village_id,int vehicleid,int hirebasisid,int opthirebasisid) {

        this.ccid = ccid;
        Price = price;

        this.optccid = optccid;
        this.optPrice = optPrice;
        this.village_id = village_id;
        this.vehicleid = vehicleid;
        this.hirebasisid = hirebasisid;
        this.opthirebasisid  = opthirebasisid;
    }

    private  Integer optccid;
    private  double optPrice;


    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public Integer getCcid() {
        return ccid;
    }

    public void setCcid(Integer ccid) {
        this.ccid = ccid;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Integer getOptccid() {
        return optccid;
    }

    public void setOptccid(Integer optccid) {
        this.optccid = optccid;
    }

    public double getOptPrice() {
        return optPrice;
    }

    public void setOptPrice(double optPrice) {
        this.optPrice = optPrice;
    }



    public int getVillage_id() {
        return village_id;
    }

    public void setVillage_id(int village_id) {
        this.village_id = village_id;
    }


    public int getVehicleid() {
        return vehicleid;
    }

    public void setVehicleid(int vehicleid) {
        this.vehicleid = vehicleid;
    }

    public int getMainlist_po() {
        return mainlist_po;
    }

    public void setMainlist_po(int mainlist_po) {
        this.mainlist_po = mainlist_po;
    }

    public int getHirebasisid() {
        return hirebasisid;
    }

    public void setHirebasisid(int hirebasisid) {
        this.hirebasisid = hirebasisid;
    }

    public int getOpthirebasisid() {
        return opthirebasisid;
    }

    public void setOpthirebasisid(int opthirebasisid) {
        this.opthirebasisid = opthirebasisid;
    }
}
