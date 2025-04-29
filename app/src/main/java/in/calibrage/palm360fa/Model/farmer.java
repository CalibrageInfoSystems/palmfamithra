package in.calibrage.palm360fa.Model;

public class farmer {
    private int id;
    private String farmercode;
    private String Mobilenumber;
    private String Farmername;
    private String farmecodewithname;
    private int villageId;

    public farmer(int villageId, String farmercode, String mobilenumber, String farmername,String farmecodewithname) {

        this.farmercode = farmercode;
        Mobilenumber = mobilenumber;
        Farmername = farmername;
        this.farmecodewithname = farmecodewithname;
        this.villageId = villageId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFarmercode() {
        return farmercode;
    }

    public void setFarmercode(String farmercode) {
        this.farmercode = farmercode;
    }

    public String getMobilenumber() {
        return Mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        Mobilenumber = mobilenumber;
    }

    public String getFarmername() {
        return Farmername;
    }

    public void setFarmername(String farmername) {
        Farmername = farmername;
    }

    public String getFarmecodewithname() {
        return farmecodewithname;
    }

    public void setFarmecodewithname(String farmecodewithname) {
        this.farmecodewithname = farmecodewithname;
    }

    public int getVillageId() {
        return villageId;
    }

    public void setVillageId(int villageId) {
        this.villageId = villageId;
    }
}