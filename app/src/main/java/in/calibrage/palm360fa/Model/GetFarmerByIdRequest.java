package in.calibrage.palm360fa.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetFarmerByIdRequest {

    @SerializedName("searchKey")
    @Expose
    private String searchKey;
    @SerializedName("Id")
    @Expose
    private Object id;

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }


}
