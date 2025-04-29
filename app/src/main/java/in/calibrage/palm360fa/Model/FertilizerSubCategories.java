package in.calibrage.palm360fa.Model;


import java.util.List;

public class FertilizerSubCategories {
    private List<SubCategory> listResult;
    private boolean isSuccess;
    private int affectedRecords;
    private String endUserMessage;
    private List<String> validationErrors;
    private Object exception;

    // Getters and setters

    public List<SubCategory> getListResult() {
        return listResult;
    }

    public void setListResult(List<SubCategory> listResult) {
        this.listResult = listResult;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public int getAffectedRecords() {
        return affectedRecords;
    }

    public void setAffectedRecords(int affectedRecords) {
        this.affectedRecords = affectedRecords;
    }

    public String getEndUserMessage() {
        return endUserMessage;
    }

    public void setEndUserMessage(String endUserMessage) {
        this.endUserMessage = endUserMessage;
    }

    public List<String> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(List<String> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public Object getException() {
        return exception;
    }

    public void setException(Object exception) {
        this.exception = exception;
    }

    public class SubCategory {
        private int categoryId;
        private String name;
        private String description;

        // Getters and setters

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
