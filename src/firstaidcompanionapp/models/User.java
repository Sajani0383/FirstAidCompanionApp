package firstaidcompanionapp.models;

public class User {
    private String name;
    private String phone;
    private String bloodGroup;
    private String allergies;

    public User() {}

    public User(String name, String phone, String bloodGroup, String allergies) {
        this.name = name;
        this.phone = phone;
        this.bloodGroup = bloodGroup;
        this.allergies = allergies;
    }

    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getBloodGroup() { return bloodGroup; }
    public String getAllergies() { return allergies; }

    public void setName(String name) { this.name = name; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }
    public void setAllergies(String allergies) { this.allergies = allergies; }
}
