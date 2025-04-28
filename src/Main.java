import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InputForm inputForm = new InputForm();
            InputHandler inputHandler = new InputHandler();
            
            new Timer(3000, e -> {
                inputHandler.setInputData(
                    inputForm.getProjectPath(),
                    inputForm.getRepoName(),
                    inputForm.getDescription(),
                    inputForm.isPublic()
                );

                GitClient gc = new GitClient(inputHandler);
                
                System.out.println("\nCollected Input Data:");
                System.out.println("Project Path: " + inputHandler.getProjectPath());
                System.out.println("Repo Name: " + inputHandler.getRepoName());
                System.out.println("Description: " + inputHandler.getDescription());
                System.out.println("Visibility: " + (inputHandler.isPublic() ? "Public" : "Private"));
                System.out.println("Valid: " + inputHandler.validateInputs());

                ((Timer)e.getSource()).stop();
            }).start();
        });
    }
}