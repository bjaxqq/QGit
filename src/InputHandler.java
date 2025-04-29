public class InputHandler {
    private String username;
    private String token;
    private String projectPath;
    private String repoName;
    private String description;
    private boolean isPublic;
    private boolean addReadme;
    private String readmeContent;
    private boolean addGitignore;
    private String gitignoreContent;

    // Sets all input data
    public void setInputData(String username, String token, String projectPath, 
                           String repoName, String description, boolean isPublic,
                           boolean addReadme, String readmeContent,
                           boolean addGitignore, String gitignoreContent) {
        this.username = username;
        this.token = token;
        this.projectPath = projectPath;
        this.repoName = repoName;
        this.description = description;
        this.isPublic = isPublic;
        this.addReadme = addReadme;
        this.readmeContent = readmeContent;
        this.addGitignore = addGitignore;
        this.gitignoreContent = gitignoreContent;
    }

    // Validates required fields
    public boolean validateInputs() {
        return !username.isEmpty() && 
               !token.isEmpty() &&
               !projectPath.isEmpty() && 
               !repoName.isEmpty();
    }

    // Getters
    public String getUsername() { return username; }
    public String getToken() { return token; }
    public String getProjectPath() { return projectPath; }
    public String getRepoName() { return repoName; }
    public String getDescription() { return description; }
    public boolean isPublic() { return isPublic; }
    public boolean addReadme() { return addReadme; }
    public String getReadmeContent() { return readmeContent; }
    public boolean addGitignore() { return addGitignore; }
    public String getGitignoreContent() { return gitignoreContent; }
}