package in.calibrage.palm360fa.Model;

public class Request_settings {
    String name;
    int image;
    public Request_settings(String name, int image) {
        this.name = name;
        this.image=image;
    }

    public String getName() {
        return name;
    }

    public Integer getImage() {
        return image;
    }


}


