package in.calibrage.palm360fa.Model;

public class Vehicletype {
    private  int position;
    private String VehicleName;
    private int VehicleId;

    public String getVehicleName() {
        return VehicleName;
    }

    public int getVehicleId() {
        return VehicleId;
    }

    public void setVehicleName(String vehicleName) {
        VehicleName = vehicleName;
    }

    public void setVehicleId(int vehicleId) {
        VehicleId = vehicleId;
    }

    public Vehicletype(int position,String vehicleName, int vehicleId) {
        this.position = position;
        this.VehicleName = vehicleName;
        this.VehicleId = vehicleId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
