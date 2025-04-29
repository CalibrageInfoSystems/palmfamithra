package in.calibrage.palm360fa.Model;

public class product {

    private String name;
    private int quantity;
    private int amount;
    private String id;
    private int gst;


    public product(String name, int quantity, int amount, int gst) {
        this.name = name;
        this.quantity = quantity;
        this.amount = amount;
        this.id = id;
        this.gst=gst;

    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public int getquantity() {
        return quantity;
    }

    public void setquantity(int quantity) {
        this.quantity = quantity;
    }

    public int getamount() {
        return amount;
    }

    public void setamount(int location) {
        this.amount = location;
    }


    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }

    public int getgst() {
        return gst;
    }

    public void setgst(int gst) {
        this.gst = gst;
    }
}


