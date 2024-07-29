import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class DigitDetectiveGUI {

    // Constants
    private static final int MIN = 1;
    private static final int MAX = 100;
    private static final int MAX_ATTEMPTS = 10;

    // Game variables
    private int numberToGuess;
    private int attempts;

    // GUI Components
    private JFrame frame;
    private JTextField guessField;
    private JTextArea resultArea;
    private JButton guessButton;
    private JLabel attemptsLabel;

    public DigitDetectiveGUI() {
        // Initialize game variables
        Random random = new Random();
        numberToGuess = random.nextInt(MAX - MIN + 1) + MIN;
        attempts = 0;

        // Create the GUI
        frame = new JFrame("Digit Detective");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLayout(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));

        JLabel promptLabel = new JLabel("Enter your guess:");
        guessField = new JTextField();
        guessButton = new JButton("Guess");

        inputPanel.add(promptLabel);
        inputPanel.add(guessField);
        inputPanel.add(new JLabel("Attempts Left:"));
        attemptsLabel = new JLabel(String.valueOf(MAX_ATTEMPTS));
        inputPanel.add(attemptsLabel);

        // Result area
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Add components to frame
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(guessButton, BorderLayout.SOUTH);

        // Add button listener
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleGuess();
            }
        });

        // Make frame visible
        frame.setVisible(true);
    }

    private void handleGuess() {
        try {
            int userGuess = Integer.parseInt(guessField.getText());
            if (userGuess < MIN || userGuess > MAX) {
                resultArea.append("Your guess is out of range. Please guess between " + MIN + " and " + MAX + ".\n");
                return;
            }

            attempts++;
            int remainingAttempts = MAX_ATTEMPTS - attempts;
            attemptsLabel.setText(String.valueOf(remainingAttempts));

            if (userGuess < numberToGuess) {
                resultArea.append("Too low! Try again.\n");
            } else if (userGuess > numberToGuess) {
                resultArea.append("Too high! Try again.\n");
            } else {
                resultArea.append("Congratulations! You guessed the number in " + attempts + " attempts.\n");
                guessButton.setEnabled(false);
                return;
            }

            if (remainingAttempts == 0) {
                resultArea.append("Sorry, you've used all your attempts. The number was " + numberToGuess + ".\n");
                guessButton.setEnabled(false);
            }

        } catch (NumberFormatException ex) {
            resultArea.append("Invalid input. Please enter a number.\n");
        }
        guessField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DigitDetectiveGUI::new);
    }
}
