import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CodingGame extends JFrame implements ActionListener {
    private JTextArea problemTextArea;
    private JTextField solutionTextField;
    private JButton submitButton;

    private String currentProblem;

    public CodingGame() {
        setTitle("Codingame");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        problemTextArea = new JTextArea();
        problemTextArea.setEditable(false);
        add(new JScrollPane(problemTextArea), BorderLayout.CENTER);

        solutionTextField = new JTextField();
        add(solutionTextField, BorderLayout.NORTH);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        add(submitButton, BorderLayout.SOUTH);

        initializeProblems();
        displayProblem();
    }

    private void initializeProblems() {
        // Inizializza i problemi di Codingame
        // Qui potresti caricare i problemi da un file o da una risorsa esterna
        // Per questo esempio, definiamo un problema hardcoded
        currentProblem = "Write a function to calculate the factorial of a number.";
        problemTextArea.setText(currentProblem);
    }

    private void displayProblem() {
        problemTextArea.setText(currentProblem);
        solutionTextField.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String solution = solutionTextField.getText().trim();
            if (!solution.isEmpty()) {
                // Verifica la soluzione (per semplicità, controlla solo se è corretta)
                if (checkSolution(solution)) {
                    JOptionPane.showMessageDialog(this, "Correct solution! You solved the problem.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Incorrect solution. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                // Passa al prossimo problema
                // Qui potresti implementare la logica per passare al prossimo problema
                // In questo esempio, ricarichiamo lo stesso problema
                displayProblem();
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a solution.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean checkSolution(String solution) {
        // Implementa la logica per verificare la soluzione
        // In questo esempio, controlliamo se la soluzione contiene "factorial"
        return solution.toLowerCase().contains("factorial");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CodingGame game = new CodingGame();
            game.setVisible(true);
        });
    }
}
