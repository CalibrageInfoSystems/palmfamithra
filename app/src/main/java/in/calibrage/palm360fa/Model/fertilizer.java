package in.calibrage.palm360fa.Model;

public class fertilizer {
    private String ploteCode;
    private String fertilizer_sourse;
    private String dosage;
    private String fertilizer_name;
    private String date;
    private String frequency;



    public fertilizer() {
        this.ploteCode = ploteCode;
        this.fertilizer_sourse = fertilizer_sourse;
        this.dosage = dosage;
        this.fertilizer_name = fertilizer_name;
        this.date = date;
        this.frequency = frequency;

    }

    public String getPloteCode() {
        return ploteCode;
    }

    public void setPloteCode(String ploteCode) {
        this.ploteCode = ploteCode;
    }

    public String getfertilizer_sourse() {
        return fertilizer_sourse;
    }

    public void setfertilizer_sourse(String fertilizer_sourse) {
        this.fertilizer_sourse = fertilizer_sourse;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getfertilizer_name() {
        return fertilizer_name;
    }

    public void setfertilizer_name(String fertilizer_name) {
        this.fertilizer_name = fertilizer_name;
    }

    public String getdate() {
        return date;
    }

    public void setdate(String date) {
        this.date = date;
    }

    public String getfrequency() {
        return frequency;
    }

    public void setfrequency(String frequency) {
        this.frequency = frequency;
    }

//    public String getRecommededOn() {
//        return recommededOn;
//    }
//
//    public void setRecommededOn(String recommededOn) {
//        this.recommededOn = recommededOn;
//    }
}
