package in.calibrage.palm360fa.Views.transport.Model;

import java.util.ArrayList;

public class Transporthirebasis {

    private int transportFFBTypeId;
    private int hirebasisId;
    private String transportFFBDesc;


    private String ownerName;
    private String ownerMobileNumber;
    private String owneraddress;
    private ArrayList<Vehicledata>  leanData;
    private ArrayList<Vehicledata>  peakdata;

    public Transporthirebasis(int transportFFBTypeId, String transportFFBDesc, String ownerName, String ownerMobileNumber, String owneraddress, ArrayList<Vehicledata> leanData, ArrayList<Vehicledata> peakdata,int hirebasisId) {
        this.transportFFBTypeId = transportFFBTypeId;
        this.transportFFBDesc = transportFFBDesc;
        this.ownerName = ownerName;
        this.ownerMobileNumber = ownerMobileNumber;
        this.owneraddress = owneraddress;
        this.leanData = leanData;
        this.peakdata = peakdata;
        this.hirebasisId = hirebasisId;
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

    public String getOwneraddress() {
        return owneraddress;
    }

    public void setOwneraddress(String owneraddress) {
        this.owneraddress = owneraddress;
    }

    public ArrayList<Vehicledata> getLeanData() {
        return leanData;
    }

    public void setLeanData(ArrayList<Vehicledata> leanData) {
        this.leanData = leanData;
    }

    public ArrayList<Vehicledata> getPeakdata() {
        return peakdata;
    }

    public void setPeakdata(ArrayList<Vehicledata> peakdata) {
        this.peakdata = peakdata;
    }

    public int getHirebasisId() {
        return hirebasisId;
    }

    public void setHirebasisId(int hirebasisId) {
        this.hirebasisId = hirebasisId;
    }
}
