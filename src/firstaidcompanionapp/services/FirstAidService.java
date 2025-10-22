package firstaidcompanionapp.services;

public class FirstAidService {

    private final DatabaseService databaseService;

    public FirstAidService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public String getFirstAidInstruction(String symptom) {
        return databaseService.getInstruction(symptom);
    }
}

