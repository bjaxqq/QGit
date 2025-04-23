import javax.swing.*;
import java.awt.*;
import java.io.File;

public class InputForm {
    private JFrame frame;
    private JPanel panel;
    private Font fredokaFont;
    
    private JTextField projectPathField;
    private JTextField repoNameField;
    private JTextArea descriptionArea;
    private JRadioButton publicRadio;
    private JRadioButton privateRadio;
    private InputHandler inputHandler = new InputHandler();

    public InputForm() {
        initializeFont();
        initialize();
    }

    private void initializeFont() {
        try {
            File fontFile = new File("assets/fonts/Fredoka.ttf");
            fredokaFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fredokaFont);
        } catch (Exception e) {
            System.err.println("Error loading font: " + e.getMessage());
            fredokaFont = new Font("SansSerif", Font.PLAIN, 12);
        }
    }

    private void initialize() {
        frame = new JFrame("QGIT");
        frame.setSize(500, 600);
        frame.setLocationRelativeTo(null);
        
        panel = new JPanel();
        panel.setBackground(Color.lightGray);
        panel.setLayout(new GridBagLayout());
        frame.add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Welcome to QGIT!");
        title.setForeground(Color.darkGray);
        title.setFont(fredokaFont.deriveFont(Font.BOLD, 24f));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(title, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridy = 1;
        JLabel pathLabel = new JLabel("*Project Path:");
        pathLabel.setFont(fredokaFont.deriveFont(14f));
        panel.add(pathLabel, gbc);

        gbc.gridx = 1;
        projectPathField = new JTextField(20);
        projectPathField.setFont(fredokaFont.deriveFont(14f));
        panel.add(projectPathField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel repoLabel = new JLabel("*Repository Name:");
        repoLabel.setFont(fredokaFont.deriveFont(14f));
        panel.add(repoLabel, gbc);

        gbc.gridx = 1;
        repoNameField = new JTextField(20);
        repoNameField.setFont(fredokaFont.deriveFont(14f));
        panel.add(repoNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel descLabel = new JLabel("Description:");
        descLabel.setFont(fredokaFont.deriveFont(14f));
        panel.add(descLabel, gbc);

        gbc.gridx = 1;
        descriptionArea = new JTextArea(3, 20);
        descriptionArea.setFont(fredokaFont.deriveFont(14f));
        descriptionArea.setLineWrap(true);
        panel.add(new JScrollPane(descriptionArea), gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel visibilityLabel = new JLabel("*Visibility:");
        visibilityLabel.setFont(fredokaFont.deriveFont(14f));
        panel.add(visibilityLabel, gbc);

        gbc.gridx = 1;
        JPanel visibilityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        publicRadio = new JRadioButton("Public", true);
        privateRadio = new JRadioButton("Private");
        
        publicRadio.setFont(fredokaFont.deriveFont(14f));
        privateRadio.setFont(fredokaFont.deriveFont(14f));
        
        ButtonGroup visibilityGroup = new ButtonGroup();
        visibilityGroup.add(publicRadio);
        visibilityGroup.add(privateRadio);
        visibilityPanel.add(publicRadio);
        visibilityPanel.add(privateRadio);
        panel.add(visibilityPanel, gbc);


        JButton submitButton = new JButton("Submit");
        submitButton.setFont(fredokaFont.deriveFont(14f));
        submitButton.setBackground(Color.darkGray);
        submitButton.setForeground(Color.white);
        submitButton.setPreferredSize(new Dimension(100, 30));

        // When user presses submit
        submitButton.addActionListener(e -> {
            if (projectPathField.getText().isEmpty() || repoNameField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill in all fields with a *.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } 
            String projectPath = getProjectPath();
            String repoName = getRepoName();
            String description = getDescription();
            boolean isPublicRepo = isPublic();
            inputHandler.setInputData(projectPath, repoName, description, isPublicRepo);
            
            System.out.println("Project Path: " + projectPath);
            System.out.println("Repository Name: " + repoName);
            System.out.println("Description: " + description);
            System.out.println("Visibility: " + (isPublicRepo ? "Public" : "Private"));

            // Clear fields after submission
            projectPathField.setText("");
            repoNameField.setText("");
            descriptionArea.setText("");
            publicRadio.setSelected(true);
            privateRadio.setSelected(false);

            
        });
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(submitButton, gbc);


        frame.setVisible(true);
    }

    public String getProjectPath() { return projectPathField.getText(); }
    public String getRepoName() { return repoNameField.getText(); }
    public String getDescription() { return descriptionArea.getText(); }
    public boolean isPublic() { return publicRadio.isSelected(); }
}