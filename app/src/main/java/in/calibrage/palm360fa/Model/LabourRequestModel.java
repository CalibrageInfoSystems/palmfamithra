
package in.calibrage.palm360fa.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LabourRequestModel {

    @SerializedName("fromDate")
    @Expose
    private Object fromDate;
    @SerializedName("toDate")
    @Expose
    private Object toDate;
    @SerializedName("farmerCode")
    @Expose
    private String farmerCode;

    public Object getFromDate() {
        return fromDate;
    }

    public void setFromDate(Object fromDate) {
        this.fromDate = fromDate;
    }

    public Object getToDate() {
        return toDate;
    }

    public void setToDate(Object toDate) {
        this.toDate = toDate;
    }

    public String getFarmerCode() {
        return farmerCode;
    }

    public void setFarmerCode(String farmerCode) {
        this.farmerCode = farmerCode;
    }

}