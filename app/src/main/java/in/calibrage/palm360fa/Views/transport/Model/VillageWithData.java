package in.calibrage.palm360fa.Views.transport.Model;

import java.util.ArrayList;

public class VillageWithData {
    int villageId;
    String villageName;
    ArrayList<Transporthirebasis>  allListinfo;

    public VillageWithData(int villageId, String villageName, ArrayList<Transporthirebasis> allListinfo) {
        this.villageId = villageId;
        this.villageName = villageName;
        this.allListinfo = allListinfo;
    }

    public int getVillageId() {
        return villageId;
    }

    public void setVillageId(int villageId) {
        this.villageId = villageId;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public ArrayList<Transporthirebasis> getAllListinfo() {
        return allListinfo;
    }

    public void setAllListinfo(ArrayList<Transporthirebasis> allListinfo) {
        this.allListinfo = allListinfo;
    }
}
