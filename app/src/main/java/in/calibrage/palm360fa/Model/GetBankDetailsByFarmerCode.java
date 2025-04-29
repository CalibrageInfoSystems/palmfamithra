  package in.calibrage.palm360fa.Model;

  import com.google.gson.annotations.Expose;
  import com.google.gson.annotations.SerializedName;

  import java.util.List;

  public class GetBankDetailsByFarmerCode {

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

          @SerializedName("farmerCode")
          @Expose
          private String farmerCode;
          @SerializedName("accountHolderName")
          @Expose
          private String accountHolderName;
          @SerializedName("accountNumber")
          @Expose
          private String accountNumber;
          @SerializedName("bankName")
          @Expose
          private String bankName;
          @SerializedName("branchName")
          @Expose
          private String branchName;
          @SerializedName("ifscCode")
          @Expose
          private String ifscCode;
          @SerializedName("guardianName")
          @Expose
          private String guardianName;
          @SerializedName("state")
          @Expose
          private String state;
          @SerializedName("district")
          @Expose
          private String district;
          @SerializedName("mandal")
          @Expose
          private String mandal;
          @SerializedName("village")
          @Expose
          private String village;

          public String getFarmerCode() {
              return farmerCode;
          }

          public void setFarmerCode(String farmerCode) {
              this.farmerCode = farmerCode;
          }

          public String getAccountHolderName() {
              return accountHolderName;
          }

          public void setAccountHolderName(String accountHolderName) {
              this.accountHolderName = accountHolderName;
          }

          public String getAccountNumber() {
              return accountNumber;
          }

          public void setAccountNumber(String accountNumber) {
              this.accountNumber = accountNumber;
          }

          public String getBankName() {
              return bankName;
          }

          public void setBankName(String bankName) {
              this.bankName = bankName;
          }

          public String getBranchName() {
              return branchName;
          }

          public void setBranchName(String branchName) {
              this.branchName = branchName;
          }

          public String getIfscCode() {
              return ifscCode;
          }

          public void setIfscCode(String ifscCode) {
              this.ifscCode = ifscCode;
          }

          public String getGuardianName() {
              return guardianName;
          }

          public void setGuardianName(String guardianName) {
              this.guardianName = guardianName;
          }

          public String getState() {
              return state;
          }

          public void setState(String state) {
              this.state = state;
          }

          public String getDistrict() {
              return district;
          }

          public void setDistrict(String district) {
              this.district = district;
          }

          public String getMandal() {
              return mandal;
          }

          public void setMandal(String mandal) {
              this.mandal = mandal;
          }

          public String getVillage() {
              return village;
          }

          public void setVillage(String village) {
              this.village = village;
          }

      }
  }