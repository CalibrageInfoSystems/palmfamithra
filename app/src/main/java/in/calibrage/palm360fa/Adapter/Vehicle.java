package in.calibrage.palm360fa.Adapter;

public class Vehicle {
    private int vehicletype_id;
    private String vehicletype;
    private String  driver;
    private int driver_id;
    private double Amount;
    private String time;
    private int paymenttypeid;
    private String currentlyrent;
    private String willingtorent;
    private String othervehicletext;

    public Vehicle(int vehicletype_id,String vehicletype, String driver,int driver_id, double amount, String time,int paymenttypeid, String currentlyrent, String willingtorent, String othervehicletext) {
        this.vehicletype_id = vehicletype_id;
        this.vehicletype = vehicletype;
        this.driver = driver;
        Amount = amount;
        this.time = time;
        this.driver_id =driver_id;
        this.paymenttypeid = paymenttypeid;
        this.currentlyrent = currentlyrent;
        this.willingtorent = willingtorent;
        this.othervehicletext = othervehicletext;
    }

    public String getVehicletype() {
        return vehicletype;
    }

    public void setVehicletype(String vehicletype) {
        this.vehicletype = vehicletype;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public String getCurrentlyrent() {
        return currentlyrent;
    }

    public void setCurrentlyrent(String currentlyrent) {
        this.currentlyrent = currentlyrent;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWillingtorent() {
        return willingtorent;
    }

    public void setWillingtorent(String willingtorent) {
        this.willingtorent = willingtorent;
    }

    public int getVehicletype_id() {
        return vehicletype_id;
    }

    public void setVehicletype_id(int vehicletype_id) {
        this.vehicletype_id = vehicletype_id;
    }

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public int getPaymenttypeid() {
        return paymenttypeid;
    }

    public void setPaymenttypeid(int paymenttypeid) {
        this.paymenttypeid = paymenttypeid;
    }

    public String getOthervehicletext() {
        return othervehicletext;
    }

    public void setOthervehicletext(String othervehicletext) {
        this.othervehicletext = othervehicletext;
    }
}
