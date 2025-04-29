package in.calibrage.palm360fa.Views.transport.Model;

public class Item {
    private String name;
    private int Id;
    private Boolean value;

    public Item(int id,String name, Boolean value) {
        this.name = name;
        Id = id;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }
}
