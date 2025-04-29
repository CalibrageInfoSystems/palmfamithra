package in.calibrage.palm360fa.Model;

public class Transporthirebasis {
   private int transportFFBTypeId;
    private String transportFFBDesc;
    private String ownerName;
    private String ownerMobileNumber;
    private String owneraddress;
    public Transporthirebasis(int transportFFBTypeId, String transportFFBDesc, String ownerName, String owneraddress, String ownerMobileNumber) {
        this.transportFFBTypeId = transportFFBTypeId;
        this.transportFFBDesc = transportFFBDesc;
        this.ownerName = ownerName;
        this.owneraddress = owneraddress;
        this.ownerMobileNumber = ownerMobileNumber;
    }

    public void setOwneraddress(String owneraddress) {
        this.owneraddress = owneraddress;
    }

    public String getOwneraddress() {
        return owneraddress;
    }

    public int getTransportFFBTypeId() {
        return transportFFBTypeId;
    }

    public void setTransportFFBTypeId(int transportFFBTypeId) {
        this.transportFFBTypeId = transportFFBTypeId;
    }

    public String getTransportFFBDesc() {
        return transportFFBDesc;
    }

    public void setTransportFFBDesc(String transportFFBDesc) {
        this.transportFFBDesc = transportFFBDesc;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerMobileNumber() {
        return ownerMobileNumber;
    }

    public void setOwnerMobileNumber(String ownerMobileNumber) {
        this.ownerMobileNumber = ownerMobileNumber;
    }
}
