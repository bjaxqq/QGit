import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import git.tools.client.GitSubprocessClient;
import github.tools.client.GitHubApiClient;
import github.tools.client.RequestParams;
import github.tools.responseObjects.CreateRepoResponse;

public class GitClient {
    private InputHandler input;
    private String username;
    private String token;
    private String projectPath;
    private String repoName;
    private String description;
    private boolean isPublic;
    private GitHubApiClient gitHubApiClient;
    private GitSubprocessClient gitSubprocessClient;

    // Constructor for testing with hardcoded values
    public GitClient(String token) {
        this.username = "bbailor";
        this.token = token;
        this.projectPath = "C:\\Users\\bensa\\College\\25SP\\CSC109\\testRepoFolder";
        this.repoName = "testRepo";
        this.description = "This is a repo made to test a project for CSC109";
        this.isPublic = false;
        executeWorkflow();
    }

    // Main constructor using input from GUI
    public GitClient(InputHandler input) {
        this.input = input;
        this.username = input.getUsername();
        this.token = input.getToken();
        this.projectPath = input.getProjectPath();
        this.repoName = input.getRepoName();
        this.description = input.getDescription();
        this.isPublic = input.isPublic();
        executeWorkflow();
    }

    // Main workflow executor
    private void executeWorkflow() {
        try {
            // Initialize Git client for the specified path
            gitSubprocessClient = new GitSubprocessClient(projectPath);
            
            // Step 1: Create local repository structure
            createLocalRepo();
            
            // Step 2: Create remote GitHub repository
            createGithubRepo();
            
            // Step 3: Push local content to GitHub
            pushToGithub();
            
            // Show success message
            showSuccessMessage();
        } catch (Exception e) {
            handleError(e);
        }
    }

    // Creates local Git repository and files
    private void createLocalRepo() throws Exception {
        // Initialize empty Git repository
        gitSubprocessClient.gitInit();
        
        //dont need to make empty files, the proper files were already made

        // Create empty README.md if requested

        //creates gitignore and readme files.
        if (input != null && input.addReadme()) {
            createReadMe();
        }
        
        // Create empty .gitignore if requested


        if (input != null && input.addGitignore()) {
            createGitIgnore();
        }
        

        // Make initial commit
        gitSubprocessClient.gitAddAll();
        gitSubprocessClient.gitCommit("Initial commit");
    }

    //Creates a gitignore file with some basic files to be ignored.
    public void createGitIgnore()
    {
        String[] gitignoreContents = {
            "#JAVA",
            "bin/",
            "build/",
            "out/",
            ".mtj.tmp/",
            "*.class",
            "*.jar",
            "*.war",
            "*.iml",
            "*.ear",
            "*.nar",
            "hs_err_pid*",
            "replay_pid*",
            "",
            "#VSCODE",
            ".vscode/",
            ".code-workspace",
            "",
            "#MISC",
            "*.log"
        };

        String gitAddFile = gitSubprocessClient.gitAddFile(projectPath);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(projectPath + "/.gitignore"))) 
        {
            for (String line : gitignoreContents) {
                writer.write(line);
                writer.newLine();
            }
        } 
        catch (IOException e)
        {
            System.err.println("An error occurred while creating the .gitignore file.");
        }        
    }

    //Creates a readme file with the repo name as a markdown header as content.
    public void createReadMe()
    {
        String readmeContents = "#" + repoName;

        String gitAddFile = gitSubprocessClient.gitAddFile(projectPath);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(projectPath + "/README.md"))) 
        {
            writer.write(readmeContents);
            writer.newLine();
        } 
        catch (IOException e)
        {
            System.err.println("An error occurred while creating the .gitignore file.");
        }      
    }

    // Generic file creation method

    //dont need

    // private void createFile(String filename, String content) throws IOException {
    //     try (BufferedWriter writer = new BufferedWriter(new FileWriter(projectPath + "/" + filename))) {
    //         if (content != null && !content.isEmpty()) {
    //             writer.write(content);
    //         }
    //     }
    //     gitSubprocessClient.gitAddFile(filename);
    // }

    // Creates the GitHub repository
    private void createGithubRepo() {
        gitHubApiClient = new GitHubApiClient(username, token);
        
        RequestParams params = new RequestParams();
        params.addParam("name", repoName);
        params.addParam("description", description);
        params.addParam("private", !isPublic);
        
        CreateRepoResponse response = gitHubApiClient.createRepo(params);
        if (response == null) {
            throw new RuntimeException("Failed to create GitHub repository");
        }
    }

    // Pushes to GitHub
    private void pushToGithub() {
        String remoteUrl = "https://github.com/" + username + "/" + repoName + ".git";
        gitSubprocessClient.gitRemoteAdd("origin", remoteUrl);

        //did not show up on github for some reason
        //gitSubprocessClient.gitPush("master");

        gitSubprocessClient.runGitCommand("push origin master");
    }

    // Shows success message
    private void showSuccessMessage() {
        String message = "Repository created successfully!\n" +
                       "GitHub URL: https://github.com/" + username + "/" + repoName;
        
        System.out.println(message);
        if (input != null) {
            JOptionPane.showMessageDialog(null, message, "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Handles errors
    private void handleError(Exception e) {
        System.err.println("Error occurred: " + e.getMessage());
        e.printStackTrace();
        if (input != null) {
            JOptionPane.showMessageDialog(null, 
                "Error creating repository: " + e.getMessage(),
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
}