import javax.swing.*;
import java.awt.*;

public class WinningAnimation extends JFrame {

    public WinningAnimation() {
        // Set up the frame
        setTitle("Congratulations!");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create and add the animated GIF to the frame
        JLabel animationLabel = new JLabel(new ImageIcon(getClass().getResource("congrats.gif"))); // Update the path to your GIF
        add(animationLabel);

        // Make frame visible
        setVisible(true);
        
        // Optionally, close the frame after the animation completes
        Timer timer = new Timer(5000, e -> dispose()); // Adjust duration as needed
        timer.setRepeats(false);
        timer.start();
    }

    public static void showWinningAnimation() {
        SwingUtilities.invokeLater(WinningAnimation::new);
    }
}
