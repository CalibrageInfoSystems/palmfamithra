package in.calibrage.palm360fa.Model;

public class Nutrient_Model {
    private String NutrientDeficiencyName,Nameofchemicalapplied,RecommendedFertilizer,UOMName,Dosage,RegisteredDate,comments;

    public Nutrient_Model(String nutrientDeficiencyName, String nameofchemicalapplied,
                          String recommendedFertilizer, String uomName, String dosage, String registeredDate, String comments) {
        NutrientDeficiencyName = nutrientDeficiencyName;
        Nameofchemicalapplied = nameofchemicalapplied;
        RecommendedFertilizer = recommendedFertilizer;
        UOMName = uomName;
        Dosage = dosage;
        RegisteredDate = registeredDate;
        this.comments = comments;
    }

    public String getNutrientDeficiencyName() {
        return NutrientDeficiencyName;
    }

    public void setNutrientDeficiencyName(String nutrientDeficiencyName) {
        NutrientDeficiencyName = nutrientDeficiencyName;
    }

    public String getNameofchemicalapplied() {
        return Nameofchemicalapplied;
    }

    public void setNameofchemicalapplied(String nameofchemicalapplied) {
        Nameofchemicalapplied = nameofchemicalapplied;
    }

    public String getRecommendedFertilizer() {
        return RecommendedFertilizer;
    }

    public void setRecommendedFertilizer(String recommendedFertilizer) {
        RecommendedFertilizer = recommendedFertilizer;
    }

    public String getUOMName() {
        return UOMName;
    }

    public void setUOMName(String UOMName) {
        this.UOMName = UOMName;
    }

    public String getDosage() {
        return Dosage;
    }

    public void setDosage(String dosage) {
        Dosage = dosage;
    }

    public String getRegisteredDate() {
        return RegisteredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        RegisteredDate = registeredDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
