package in.calibrage.palm360fa.Model;

public class ServiceType {

    private String statusType;
    private String measurement;
    private Double price;

    public ServiceType(String statusType, String measurement, Double price) {
        this.statusType = statusType;
        this.measurement = measurement;
        this.price = price;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
