import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;
public class Main {

    public static void main(String[] args) {
        // Create a new JFrame instance
        JFrame frame = new JFrame("My First GUI");
        frame.setSize(500,600);
        frame.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setBackground(Color.lightGray);
        frame.add(panel);
        JLabel title = new JLabel();
        title.setText("Welcome to QGIT!");
        title.setBounds(250, 50, 300, 100);
        title.setForeground(Color.darkGray);
        File file = new File("Fredoka.ttf"); 
        Font font = null;
        try { 
            font = Font.createFont(Font.TRUETYPE_FONT, file).deriveFont(50f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
        } catch (Exception e) {
            System.err.println("Error loading font: " + e.getMessage());
            font = new Font("SansSerif", Font.PLAIN, 50);
        }
        title.setFont(font);
        panel.add(title);
        frame.setVisible(true);
    }
}