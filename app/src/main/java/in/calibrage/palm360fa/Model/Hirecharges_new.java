package in.calibrage.palm360fa.Model;

import java.util.ArrayList;

public class Hirecharges_new {
    private  int new_position;
    private ArrayList<Hirecharges> hirecharge_details = null;

    public Hirecharges_new(int new_position, ArrayList<Hirecharges> hirecharge_details) {
        this.new_position = new_position;
        this.hirecharge_details = hirecharge_details;
    }



    public int getNew_position() {
        return new_position;
    }

    public void setNew_position(int new_position) {
        this.new_position = new_position;
    }

    public ArrayList<Hirecharges> getHirecharge_details() {
        return hirecharge_details;
    }

    public void setHirecharge_details(ArrayList<Hirecharges> hirecharge_details) {
        this.hirecharge_details = hirecharge_details;
    }
}
