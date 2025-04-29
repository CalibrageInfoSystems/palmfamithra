package in.calibrage.palm360fa.Views.transport.Model;

public class Vehicledata {
    private  int seasonTypeId;
    private int  vehicleTypeId;
    private  int villageId;
    private  int destinationId;
    private  double price;
    private int destinationId2;
    private double price2;

    public Vehicledata(int seasonTypeId, int vehicleTypeId, int villageId, int destinationId, double price,  int destinationId2, double price2) {
        this.seasonTypeId = seasonTypeId;
        this.vehicleTypeId = vehicleTypeId;
        this.villageId = villageId;
        this.destinationId = destinationId;
        this.price = price;

        this.destinationId2 = destinationId2;
        this.price2 = price2;
    }

    public int getSeasonTypeId() {
        return seasonTypeId;
    }

    public void setSeasonTypeId(int seasonTypeId) {
        this.seasonTypeId = seasonTypeId;
    }

    public int getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(int vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public int getVillageId() {
        return villageId;
    }

    public void setVillageId(int villageId) {
        this.villageId = villageId;
    }

    public int getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(int destinationId) {
        this.destinationId = destinationId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

   

    public int getDestinationId2() {
        return destinationId2;
    }

    public void setDestinationId2(int destinationId2) {
        this.destinationId2 = destinationId2;
    }

    public double getPrice2() {
        return price2;
    }

    public void setPrice2(double price2) {
        this.price2 = price2;
    }
}
