import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class DigitDetectiveGUI {

	private static final int MIN = 1;
	private static final int MAX = 100;
	private static final int MAX_ATTEMPTS = 10;

	private int numberToGuess;
	private int attempts;

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
		frame.setSize(300, 200);
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

		// Add key listener to guess field
		guessField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					handleGuess();
				}
			}
		});

		// Make frame visible
		frame.setVisible(true);
	}

	private void handleGuess() {
		String text = guessField.getText().trim();
		if (text.isEmpty()) {
			resultArea.append("Please enter a number.\n");
			return;
		}

		try {
			int userGuess = Integer.parseInt(text);
			if (userGuess < MIN || userGuess > MAX) {
				resultArea.append("Your guess of " + userGuess + " is out of range. Please guess between " + MIN
						+ " and " + MAX + ".\n");
				return;
			}

			attempts++;
			int remainingAttempts = MAX_ATTEMPTS - attempts;
			attemptsLabel.setText(String.valueOf(remainingAttempts));

			if (userGuess < numberToGuess) {
				resultArea.append("Your guess of " + userGuess + " is too low! Try again.\n");
			} else if (userGuess > numberToGuess) {
				resultArea.append("Your guess of " + userGuess + " is too high! Try again.\n");
			} else {
				resultArea.append("Congratulations! You guessed the number " + numberToGuess + " in " + attempts
						+ " attempts.\n");
				guessButton.setEnabled(false);
				return;
			}

			if (remainingAttempts == 0) {
				resultArea.append("Sorry, you've used all your attempts. The number was " + numberToGuess + ".\n");
				guessButton.setEnabled(false);
			}

		} catch (NumberFormatException ex) {
			resultArea.append("Invalid input. Please enter a valid number.\n");
		}
		guessField.setText("");
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(DigitDetectiveGUI::new);
	}
}
