  package in.calibrage.palm360fa.Model;

  import com.google.gson.annotations.Expose;
  import com.google.gson.annotations.SerializedName;

  import java.util.List;

  public class Costconfigres {

      @SerializedName("result")
      @Expose
      private Result result;
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

      public Result getResult() {
          return result;
      }

      public void setResult(Result result) {
          this.result = result;
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


  public class Result {

      @SerializedName("createdBy")
      @Expose
      private String createdBy;
      @SerializedName("id")
      @Expose
      private Integer id;
      @SerializedName("configName")
      @Expose
      private String configName;
      @SerializedName("cost")
      @Expose
      private Double cost;
      @SerializedName("createdByUserId")
      @Expose
      private Integer createdByUserId;
      @SerializedName("createdDate")
      @Expose
      private String createdDate;

      public String getCreatedBy() {
          return createdBy;
      }

      public void setCreatedBy(String createdBy) {
          this.createdBy = createdBy;
      }

      public Integer getId() {
          return id;
      }

      public void setId(Integer id) {
          this.id = id;
      }

      public String getConfigName() {
          return configName;
      }

      public void setConfigName(String configName) {
          this.configName = configName;
      }

      public Double getCost() {
          return cost;
      }

      public void setCost(Double cost) {
          this.cost = cost;
      }

      public Integer getCreatedByUserId() {
          return createdByUserId;
      }

      public void setCreatedByUserId(Integer createdByUserId) {
          this.createdByUserId = createdByUserId;
      }

      public String getCreatedDate() {
          return createdDate;
      }

      public void setCreatedDate(String createdDate) {
          this.createdDate = createdDate;
      }

  }}