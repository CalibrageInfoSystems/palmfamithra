package in.calibrage.palm360fa.Adapter;

public class VehicleTypesWithCount {
    private  int position;
    private  int vehicleid;
    private String vehiclename;

    public VehicleTypesWithCount(int position, int vehicleid, String vehiclename, int noofvehicles) {
        this.position = position;
        this.vehicleid = vehicleid;
        this.vehiclename = vehiclename;
        Noofvehicles = noofvehicles;
    }

    private  int Noofvehicles;
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getVehicleid() {
        return vehicleid;
    }

    public void setVehicleid(int vehicleid) {
        this.vehicleid = vehicleid;
    }

    public String getVehiclename() {
        return vehiclename;
    }

    public void setVehiclename(String vehiclename) {
        this.vehiclename = vehiclename;
    }

    public int getNoofvehicles() {
        return Noofvehicles;
    }

    public void setNoofvehicles(int noofvehicles) {
        Noofvehicles = noofvehicles;
    }
}

