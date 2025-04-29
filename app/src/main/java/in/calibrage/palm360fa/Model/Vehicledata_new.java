package in.calibrage.palm360fa.Model;

import java.util.ArrayList;

public class Vehicledata_new {
    private  int new_position;

    public Vehicledata_new(int new_position, ArrayList<Vehicle_data> vehicledetails_details) {
        this.new_position = new_position;
        this.vehicledetails_details = vehicledetails_details;
    }

    private ArrayList<Vehicle_data>vehicledetails_details = null;

    public int getNew_position() {
        return new_position;
    }

    public void setNew_position(int new_position) {
        this.new_position = new_position;
    }

    public ArrayList<Vehicle_data> getVehicledetails_details() {
        return vehicledetails_details;
    }

    public void setVehicledetails_details(ArrayList<Vehicle_data> vehicledetails_details) {
        this.vehicledetails_details = vehicledetails_details;
    }
}
