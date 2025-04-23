public class InputHandler {
    private String projectPath;
    private String repoName;
    private String description;
    private boolean isPublic;

    public void setInputData(String projectPath, String repoName, 
                          String description, boolean isPublic) {
        this.projectPath = projectPath;
        this.repoName = repoName;
        this.description = description;
        this.isPublic = isPublic;
    }

    public boolean validateInputs() {
        return !projectPath.isEmpty() && 
               !repoName.isEmpty() && 
               !description.isEmpty();
    }

    public String getProjectPath() { return projectPath; }
    public String getRepoName() { return repoName; }
    public String getDescription() { return description; }
    public boolean isPublic() { return isPublic; }
}