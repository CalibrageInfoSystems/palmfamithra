package in.calibrage.palm360fa.Model;

public class fertilizerRecommendation {
    private String LastUpdatedDate,comments,Dosage,UOMame,recommended_fertilizer;


    public fertilizerRecommendation() {
        LastUpdatedDate = LastUpdatedDate;
        this.comments = comments;
        Dosage = Dosage;
        UOMame = UOMame;
        this.recommended_fertilizer = recommended_fertilizer;
    }

    public String getLastUpdatedDate() {
        return LastUpdatedDate;
    }

    public void setLastUpdatedDate(String lastUpdatedDate) {
        LastUpdatedDate = lastUpdatedDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDosage() {
        return Dosage;
    }

    public void setDosage(String dosage) {
        Dosage = dosage;
    }

    public String getUOMame() {
        return UOMame;
    }

    public void setUOMame(String UOMame) {
        this.UOMame = UOMame;
    }

    public String getRecommended_fertilizer() {
        return recommended_fertilizer;
    }

    public void setRecommended_fertilizer(String recommended_fertilizer) {
        this.recommended_fertilizer = recommended_fertilizer;
    }
}
