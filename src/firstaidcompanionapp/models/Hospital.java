package firstaidcompanionapp.models;

public class Hospital {
    private String name;
    private String address;
    private String url;

    public Hospital(String name, String address, String url) {
        this.name = name;
        this.address = address;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return name + " - " + address;
    }
}
