package in.calibrage.palm360fa.Model;

public class Vehicle_data {
    private  int seasonTypeId;
    private int  vehicleTypeId;
    private  int villageId;
    private  int destinationId;
    private  double price;
    private int hiringbasisId;

    public Vehicle_data(int seasonTypeId, int vehicleTypeId, int villageId, int destinationId, double price, int hiringbasisId) {
        this.seasonTypeId = seasonTypeId;
        this.vehicleTypeId = vehicleTypeId;
        this.villageId = villageId;
        this.destinationId = destinationId;
        this.price = price;
        this.hiringbasisId = hiringbasisId;
    }

    public void setHiringbasisId(int hiringbasisId) {
        this.hiringbasisId = hiringbasisId;
    }

    public int getHiringbasisId() {
        return hiringbasisId;
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
}
