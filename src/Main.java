import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Launch the GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new InputForm();
        });
    }
}