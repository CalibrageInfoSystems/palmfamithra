package in.calibrage.palm360fa.Model;

public class pest {
    private String Pest,PestChemicals,RecommendedChemical,Dosage,UOMName,Comments;

    public pest(String pest, String pestChemicals, String recommendedChemical, String dosage, String uomName, String comments) {
        Pest = pest;
        PestChemicals = pestChemicals;
        RecommendedChemical = recommendedChemical;
        Dosage = dosage;
        UOMName = uomName;
        Comments = comments;
    }

    public String getPest() {
        return Pest;
    }

    public void setPest(String pest) {
        Pest = pest;
    }

    public String getPestChemicals() {
        return PestChemicals;
    }

    public void setPestChemicals(String pestChemicals) {
        PestChemicals = pestChemicals;
    }

    public String getRecommendedChemical() {
        return RecommendedChemical;
    }

    public void setRecommendedChemical(String recommendedChemical) {
        RecommendedChemical = recommendedChemical;
    }

    public String getDosage() {
        return Dosage;
    }

    public void setDosage(String dosage) {
        Dosage = dosage;
    }

    public String getUOMName() {
        return UOMName;
    }

    public void setUOMName(String UOMName) {
        this.UOMName = UOMName;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }
}
