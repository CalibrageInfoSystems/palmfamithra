package in.calibrage.palm360fa.Model;

public class VehicleTypes {
    private  int position;
    private  int vehicleid;

    public VehicleTypes(int position, int vehicleid, String vehiclename) {
        this.position = position;
        this.vehicleid = vehicleid;
        this.vehiclename = vehiclename;
    }

    private String vehiclename;

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
}
