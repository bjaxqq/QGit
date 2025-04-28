import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import git.tools.client.GitSubprocessClient;
import github.tools.client.GitHubApiClient;
import github.tools.client.RequestParams;
import github.tools.responseObjects.*;


public class GitClient 
{
    private InputHandler input;

    private String username;
    private String token;
    
    private String projectPath;
    private String repoName;
    private String description;
    private boolean isPublic;

    private String repoLink;

    private GitHubApiClient gitHubApiClient;
    private GitSubprocessClient gitSubprocessClient;

    public GitClient(String t)
    {
        username = "bbailor06";
        token = t;
        projectPath = "C:\\Users\\bensa\\College\\25SP\\CSC109\\testRepoFolder";
        repoName = "testRepo";
        description = "This is a repo made to test a project for CSC109";
        isPublic = false;

        repoLink = "https://github.com/" + "/" + username + "/" + repoName;

        createGitRepo();
        createGitIgnore();
        createReadMe();
        addRemoteOrigin();
    }

    public GitClient(InputHandler input)
    {
        this.input = input;

        //token = input.getToken();
        //username = input.getUsername();

        username = "bbailor06";
        token = "";

        projectPath = input.getProjectPath();
        repoName = input.getRepoName();
        description = input.getDescription();
        isPublic = input.isPublic();
    }

    public void createGitRepo()
    {
        gitSubprocessClient = new GitSubprocessClient(projectPath);

        String gitInit = gitSubprocessClient.gitInit();
    }

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

    public void initCommit()
    {
        String gitAddAll = gitSubprocessClient.gitAddAll();

        String commitMessage = "Initial commit.";
        String commit = gitSubprocessClient.gitCommit(commitMessage);

        String push = gitSubprocessClient.gitPush("master");
    }

    public void createGithubRepo()
    {
        RequestParams requestParams = new RequestParams();

        gitHubApiClient = new GitHubApiClient(username, token);

        requestParams.addParam("name", repoName);
        requestParams.addParam("description", description);
        requestParams.addParam("private", !isPublic);
        
        CreateRepoResponse createRepo = gitHubApiClient.createRepo(requestParams);
    }

    public void addRemoteOrigin()
    {
        String gitRemoteAdd = gitSubprocessClient.gitRemoteAdd("origin", repoLink);
    }

    






}
