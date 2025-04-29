import java.awt.*;
import java.io.File;
import javax.swing.*;

public class InputForm {
    private JFrame frame;
    private JPanel panel;
    private Font fredokaFont;
    
    // Form fields
    private JTextField usernameField;
    private JPasswordField tokenField;
    private JTextField projectPathField;
    private JTextField repoNameField;
    private JTextArea descriptionArea;
    private JRadioButton publicRadio;
    private JRadioButton privateRadio;
    private JCheckBox addReadmeCheck;
    private JCheckBox addGitignoreCheck;

    public InputForm() {
        initializeFont();
        initialize();
    }

    // Loads custom font
    private void initializeFont() {
        try {
            fredokaFont = Font.createFont(Font.TRUETYPE_FONT, 
                new File("assets/fonts/Fredoka.ttf"));
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(fredokaFont);
        } catch (Exception e) {
            fredokaFont = new Font("SansSerif", Font.PLAIN, 12);
        }
    }

    // Initializes and lays out the GUI components
    private void initialize() {
        createMainWindow();
        createLogo();
        createTitle();
        createInputFields();
        createVisibilityOptions();
        createFileOptions();
        createSubmitButton();
        createDisclaimer();
        
        frame.setVisible(true);
    }

    // Creates the main window
    private void createMainWindow() {
        frame = new JFrame("QGIT");
        frame.setSize(500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        
        panel = new JPanel();
        panel.setBackground(Color.lightGray);
        panel.setLayout(new GridBagLayout());
        frame.add(new JScrollPane(panel));
    }

    // Adds the logo image
    private void createLogo() {
        try {
            ImageIcon logoIcon = new ImageIcon("assets/images/QGitLogo.png");
            panel.add(new JLabel(logoIcon), 
                createConstraints(0, 0, 2, GridBagConstraints.CENTER));
        } catch (Exception e) {
            System.err.println("Error loading logo: " + e.getMessage());
        }
    }

    // Adds the title
    private void createTitle() {
        JLabel title = new JLabel("Welcome to QGIT!");
        title.setForeground(Color.darkGray);
        title.setFont(fredokaFont.deriveFont(Font.BOLD, 24f));
        panel.add(title, createConstraints(0, 1, 2, GridBagConstraints.CENTER));
    }

    // Creates all input fields
    private void createInputFields() {
        // Username
        panel.add(new JLabel("*GitHub Username:"), createConstraints(0, 2, 1, GridBagConstraints.WEST));
        usernameField = createTextField(1, 2);
        
        // Token
        panel.add(new JLabel("*GitHub Token:"), createConstraints(0, 3, 1, GridBagConstraints.WEST));
        tokenField = new JPasswordField(20);
        tokenField.setFont(fredokaFont.deriveFont(14f));
        panel.add(tokenField, createConstraints(1, 3, 1, GridBagConstraints.WEST));
        
        // Project Path
        panel.add(new JLabel("*Project Path:"), createConstraints(0, 4, 1, GridBagConstraints.WEST));
        projectPathField = createTextField(1, 4);
        
        // Repository Name
        panel.add(new JLabel("*Repository Name:"), createConstraints(0, 5, 1, GridBagConstraints.WEST));
        repoNameField = createTextField(1, 5);
        
        // Description
        panel.add(new JLabel("Description:"), createConstraints(0, 6, 1, GridBagConstraints.WEST));
        descriptionArea = new JTextArea(3, 20);
        descriptionArea.setFont(fredokaFont.deriveFont(14f));
        panel.add(new JScrollPane(descriptionArea), createConstraints(1, 6, 1, GridBagConstraints.WEST));
    }

    // Creates visibility radio buttons
    private void createVisibilityOptions() {
        panel.add(new JLabel("*Visibility:"), createConstraints(0, 7, 1, GridBagConstraints.WEST));
        
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
        panel.add(visibilityPanel, createConstraints(1, 7, 1, GridBagConstraints.WEST));
    }

    // Creates file options checkboxes
    private void createFileOptions() {
        // README checkbox
        addReadmeCheck = new JCheckBox("Add README.md", true);
        addReadmeCheck.setFont(fredokaFont.deriveFont(14f));
        panel.add(addReadmeCheck, createConstraints(0, 8, 2, GridBagConstraints.WEST));
        
        // .gitignore checkbox
        addGitignoreCheck = new JCheckBox("Add .gitignore", true);
        addGitignoreCheck.setFont(fredokaFont.deriveFont(14f));
        panel.add(addGitignoreCheck, createConstraints(0, 9, 2, GridBagConstraints.WEST));
    }

    // Creates submit button
    private void createSubmitButton() {
        JButton submitButton = new JButton("Create Repository");
        submitButton.setFont(fredokaFont.deriveFont(14f));
        submitButton.setBackground(new Color(46, 125, 50));
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(e -> submitForm());
        panel.add(submitButton, createConstraints(0, 10, 2, GridBagConstraints.CENTER));
    }

    // Creates disclaimer
    private void createDisclaimer() {
        JLabel disclaimer = new JLabel("<html><center>Prototype version - not for commercial use</center></html>");
        disclaimer.setFont(fredokaFont.deriveFont(Font.ITALIC, 12f));
        disclaimer.setForeground(Color.GRAY);
        panel.add(disclaimer, createConstraints(0, 11, 2, GridBagConstraints.CENTER));
    }

    // Helper method to create text fields
    private JTextField createTextField(int gridx, int gridy) {
        JTextField field = new JTextField(20);
        field.setFont(fredokaFont.deriveFont(14f));
        panel.add(field, createConstraints(gridx, gridy, 1, GridBagConstraints.WEST));
        return field;
    }

    // Helper method to create GridBagConstraints
    private GridBagConstraints createConstraints(int gridx, int gridy, int gridwidth, int anchor) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridwidth;
        gbc.anchor = anchor;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        return gbc;
    }

    // Handles form submission
    private void submitForm() {
        if (validateInputs()) {
            InputHandler inputHandler = new InputHandler();
            inputHandler.setInputData(
                usernameField.getText(),
                new String(tokenField.getPassword()),
                projectPathField.getText(),
                repoNameField.getText(),
                descriptionArea.getText(),
                publicRadio.isSelected(),
                addReadmeCheck.isSelected(),
                "", // Empty README content
                addGitignoreCheck.isSelected(),
                ""  // Empty .gitignore content
            );
            new GitClient(inputHandler);
        }
    }

    // Validates required fields
    private boolean validateInputs() {
        if (usernameField.getText().isEmpty() || 
            tokenField.getPassword().length == 0 ||
            projectPathField.getText().isEmpty() || 
            repoNameField.getText().isEmpty()) {
            
            JOptionPane.showMessageDialog(frame, 
                "Please fill in all required fields (*)", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}