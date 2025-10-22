package firstaidcompanionapp.models;

public class FirstAidGuide {
    private String title;
    private String instructions;

    public FirstAidGuide(String title, String instructions) {
        this.title = title;
        this.instructions = instructions;
    }

    public String getTitle() { return title; }
    public String getInstructions() { return instructions; }
}
