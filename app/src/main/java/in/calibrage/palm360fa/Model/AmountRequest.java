package in.calibrage.palm360fa.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AmountRequest {

    @SerializedName("serviceTypeIds")
    @Expose
    private String serviceTypeIds;
    @SerializedName("dateOfPlanting")
    @Expose
    private String dateOfPlanting;

    public String getServiceTypeIds() {
        return serviceTypeIds;
    }

    public void setServiceTypeIds(String serviceTypeIds) {
        this.serviceTypeIds = serviceTypeIds;
    }

    public String getDateOfPlanting() {
        return dateOfPlanting;
    }

    public void setDateOfPlanting(String dateOfPlanting) {
        this.dateOfPlanting = dateOfPlanting;
    }

}