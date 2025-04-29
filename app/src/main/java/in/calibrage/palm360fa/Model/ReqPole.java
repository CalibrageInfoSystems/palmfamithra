package in.calibrage.palm360fa.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReqPole {


        @SerializedName("fromDate")
        @Expose
        private String fromDate;
        @SerializedName("toDate")
        @Expose
        private String toDate;
        @SerializedName("farmerCode")
        @Expose
        private String farmerCode;
        @SerializedName("requestTypeId")
        @Expose
        private Integer requestTypeId;

        public String getFromDate() {
            return fromDate;
        }

        public void setFromDate(String fromDate) {
            this.fromDate = fromDate;
        }

        public String getToDate() {
            return toDate;
        }

        public void setToDate(String toDate) {
            this.toDate = toDate;
        }

        public String getFarmerCode() {
            return farmerCode;
        }

        public void setFarmerCode(String farmerCode) {
            this.farmerCode = farmerCode;
        }

        public Integer getRequestTypeId() {
            return requestTypeId;
        }

        public void setRequestTypeId(Integer requestTypeId) {
            this.requestTypeId = requestTypeId;
        }

    }