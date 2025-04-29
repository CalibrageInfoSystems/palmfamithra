package in.calibrage.palm360fa.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteObject {

    @SerializedName("requestCode")
    @Expose
    private String requestCode;
    @SerializedName("statusTypeId")
    @Expose
    private Integer statusTypeId;
    @SerializedName("updatedByUserId")
    @Expose
    private Object updatedByUserId;
    @SerializedName("updatedDate")
    @Expose
    private String updatedDate;

    public String getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }

    public Integer getStatusTypeId() {
        return statusTypeId;
    }

    public void setStatusTypeId(Integer statusTypeId) {
        this.statusTypeId = statusTypeId;
    }

    public Object getUpdatedByUserId() {
        return updatedByUserId;
    }

    public void setUpdatedByUserId(Object updatedByUserId) {
        this.updatedByUserId = updatedByUserId;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

}