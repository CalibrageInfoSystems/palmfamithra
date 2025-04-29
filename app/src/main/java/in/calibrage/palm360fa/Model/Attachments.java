package in.calibrage.palm360fa.Model;

public class Attachments {
    private  String name;
    private int AttachmentID;
    private int UOMID;
    private int postition;

    public Attachments(String name, int attachmentID, int postition, Double price, int attached_id,int UOMID) {
        this.name = name;
        AttachmentID = attachmentID;
        this.postition = postition;
        Price = price;
        this.attached_id = attached_id;
        this.UOMID = UOMID;
    }

    private Double Price;
    private  int attached_id;




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public int getAttached_id() {
        return attached_id;
    }

    public void setAttached_id(int attached_id) {
        this.attached_id = attached_id;
    }

    public int getPostition() {
        return postition;
    }

    public void setPostition(int postition) {
        this.postition = postition;
    }

    public int getAttachmentID() {
        return AttachmentID;
    }

    public void setAttachmentID(int attachmentID) {
        AttachmentID = attachmentID;
    }

    public int getUOMID() {
        return UOMID;
    }

    public void setUOMID(int UOMID) {
        this.UOMID = UOMID;
    }
}
