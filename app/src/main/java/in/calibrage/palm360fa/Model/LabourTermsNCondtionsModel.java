package in.calibrage.palm360fa.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LabourTermsNCondtionsModel {

    @SerializedName("listResult")
    @Expose
    private List<ListResult> listResult = null;
    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("affectedRecords")
    @Expose
    private Integer affectedRecords;
    @SerializedName("endUserMessage")
    @Expose
    private String endUserMessage;
    @SerializedName("validationErrors")
    @Expose
    private List<Object> validationErrors = null;
    @SerializedName("exception")
    @Expose
    private Object exception;

    public List<ListResult> getListResult() {
        return listResult;
    }

    public void setListResult(List<ListResult> listResult) {
        this.listResult = listResult;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Integer getAffectedRecords() {
        return affectedRecords;
    }

    public void setAffectedRecords(Integer affectedRecords) {
        this.affectedRecords = affectedRecords;
    }

    public String getEndUserMessage() {
        return endUserMessage;
    }

    public void setEndUserMessage(String endUserMessage) {
        this.endUserMessage = endUserMessage;
    }

    public List<Object> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(List<Object> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public Object getException() {
        return exception;
    }

    public void setException(Object exception) {
        this.exception = exception;
    }


    public class ListResult {

        @SerializedName("serviceTypeId")
        @Expose
        private Integer serviceTypeId;
        @SerializedName("desc")
        @Expose
        private String desc;
        @SerializedName("c1")
        @Expose
        private Double c1;
        @SerializedName("c2")
        @Expose
        private Double c2;
        @SerializedName("c3")
        @Expose
        private Double c3;
        @SerializedName("c4")
        @Expose
        private Double c4;
        @SerializedName("c5")
        @Expose
        private Double c5;
        @SerializedName("c6")
        @Expose
        private Double c6;
        @SerializedName("c7")
        @Expose
        private Double c7;
        @SerializedName("c8")
        @Expose
        private Double c8;
        @SerializedName("c9")
        @Expose
        private Double c9;
        @SerializedName("c10")
        @Expose
        private Double c10;
        @SerializedName("c11")
        @Expose
        private Double c11;
        @SerializedName("c12")
        @Expose
        private Double c12;
        @SerializedName("c13")
        @Expose
        private Double c13;
        @SerializedName("c14")
        @Expose
        private Double c14;
        @SerializedName("c15")
        @Expose
        private Double c15;
        @SerializedName("c16")
        @Expose
        private Double c16;
        @SerializedName("c17")
        @Expose
        private Double c17;
        @SerializedName("c18")
        @Expose
        private Double c18;
        @SerializedName("c19")
        @Expose
        private Double c19;
        @SerializedName("c20")
        @Expose
        private Double c20;
        @SerializedName("c21")
        @Expose
        private Double c21;
        @SerializedName("c22")
        @Expose
        private Double c22;
        @SerializedName("c23")
        @Expose
        private Double c23;
        @SerializedName("c24")
        @Expose
        private Double c24;
        @SerializedName("c25")
        @Expose
        private Double c25;
        @SerializedName("c26")
        @Expose
        private Double c26;
        @SerializedName("c27")
        @Expose
        private Double c27;
        @SerializedName("c28")
        @Expose
        private Double c28;
        @SerializedName("c29")
        @Expose
        private Double c29;
        @SerializedName("c30")
        @Expose
        private Double c30;

        public Integer getServiceTypeId() {
            return serviceTypeId;
        }

        public void setServiceTypeId(Integer serviceTypeId) {
            this.serviceTypeId = serviceTypeId;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public Double getC1() {
            return c1;
        }

        public void setC1(Double c1) {
            this.c1 = c1;
        }

        public Double getC2() {
            return c2;
        }

        public void setC2(Double c2) {
            this.c2 = c2;
        }

        public Double getC3() {
            return c3;
        }

        public void setC3(Double c3) {
            this.c3 = c3;
        }

        public Double getC4() {
            return c4;
        }

        public void setC4(Double c4) {
            this.c4 = c4;
        }

        public Double getC5() {
            return c5;
        }

        public void setC5(Double c5) {
            this.c5 = c5;
        }

        public Double getC6() {
            return c6;
        }

        public void setC6(Double c6) {
            this.c6 = c6;
        }

        public Double getC7() {
            return c7;
        }

        public void setC7(Double c7) {
            this.c7 = c7;
        }

        public Double getC8() {
            return c8;
        }

        public void setC8(Double c8) {
            this.c8 = c8;
        }

        public Double getC9() {
            return c9;
        }

        public void setC9(Double c9) {
            this.c9 = c9;
        }

        public Double getC10() {
            return c10;
        }

        public void setC10(Double c10) {
            this.c10 = c10;
        }

        public Double getC11() {
            return c11;
        }

        public void setC11(Double c11) {
            this.c11 = c11;
        }

        public Double getC12() {
            return c12;
        }

        public void setC12(Double c12) {
            this.c12 = c12;
        }

        public Double getC13() {
            return c13;
        }

        public void setC13(Double c13) {
            this.c13 = c13;
        }

        public Double getC14() {
            return c14;
        }

        public void setC14(Double c14) {
            this.c14 = c14;
        }

        public Double getC15() {
            return c15;
        }

        public void setC15(Double c15) {
            this.c15 = c15;
        }

        public Double getC16() {
            return c16;
        }

        public void setC16(Double c16) {
            this.c16 = c16;
        }

        public Double getC17() {
            return c17;
        }

        public void setC17(Double c17) {
            this.c17 = c17;
        }

        public Double getC18() {
            return c18;
        }

        public void setC18(Double c18) {
            this.c18 = c18;
        }

        public Double getC19() {
            return c19;
        }

        public void setC19(Double c19) {
            this.c19 = c19;
        }

        public Double getC20() {
            return c20;
        }

        public void setC20(Double c20) {
            this.c20 = c20;
        }

        public Double getC21() {
            return c21;
        }

        public void setC21(Double c21) {
            this.c21 = c21;
        }

        public Double getC22() {
            return c22;
        }

        public void setC22(Double c22) {
            this.c22 = c22;
        }

        public Double getC23() {
            return c23;
        }

        public void setC23(Double c23) {
            this.c23 = c23;
        }

        public Double getC24() {
            return c24;
        }

        public void setC24(Double c24) {
            this.c24 = c24;
        }

        public Double getC25() {
            return c25;
        }

        public void setC25(Double c25) {
            this.c25 = c25;
        }

        public Double getC26() {
            return c26;
        }

        public void setC26(Double c26) {
            this.c26 = c26;
        }

        public Double getC27() {
            return c27;
        }

        public void setC27(Double c27) {
            this.c27 = c27;
        }

        public Double getC28() {
            return c28;
        }

        public void setC28(Double c28) {
            this.c28 = c28;
        }

        public Double getC29() {
            return c29;
        }

        public void setC29(Double c29) {
            this.c29 = c29;
        }

        public Double getC30() {
            return c30;
        }

        public void setC30(Double c30) {
            this.c30 = c30;
        }

    }
}