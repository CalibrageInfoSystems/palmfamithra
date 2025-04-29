package in.calibrage.palm360fa.Model;

public class VillagesData {
   private int position;
    private int village_id;


    public VillagesData(int position, int village_id, String village_name) {
        this.position = position;
        this.village_id = village_id;
        this.village_name = village_name;
    }

    private String village_name;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getVillage_id() {
        return village_id;
    }

    public void setVillage_id(int village_id) {
        this.village_id = village_id;
    }

    public String getVillage_name() {
        return village_name;
    }

    public void setVillage_name(String village_name) {
        this.village_name = village_name;
    }
}
