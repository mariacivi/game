import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessNumberGame extends JFrame implements ActionListener {
    private int randomNumber;
    private JTextField guessField;
    private JButton guessButton;
    private JLabel resultLabel;

    public GuessNumberGame() {
        setTitle("Guess the Number Game");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        randomNumber = new Random().nextInt(100) + 1; // Generate random number between 1 and 100

        JLabel instructionLabel = new JLabel("Guess a number between 1 and 100:");
        guessField = new JTextField(10);
        guessButton = new JButton("Guess");
        guessButton.addActionListener(this);
        resultLabel = new JLabel("");

        JPanel inputPanel = new JPanel();
        inputPanel.add(instructionLabel);
        inputPanel.add(guessField);
        inputPanel.add(guessButton);

        add(inputPanel, BorderLayout.NORTH);
        add(resultLabel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int guess = Integer.parseInt(guessField.getText());
            if (guess == randomNumber) {
                resultLabel.setText("Congratulations! You guessed the number.");
                guessButton.setEnabled(false);
            } else if (guess < randomNumber) {
                resultLabel.setText("Try a higher number.");
            } else {
                resultLabel.setText("Try a lower number.");
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Please enter a valid number.");
        }
        guessField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GuessNumberGame game = new GuessNumberGame();
            game.setVisible(true);
        });
    }
}
