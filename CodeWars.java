import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class CodeWars extends JFrame implements ActionListener {
    private JTextArea problemTextArea;
    private JTextField solutionTextField;
    private JButton checkButton;

    private Map<String, String> problems;

    public CodeWars() {
        setTitle("CodeWars");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        problemTextArea = new JTextArea();
        problemTextArea.setEditable(false);
        add(new JScrollPane(problemTextArea), BorderLayout.CENTER);

        solutionTextField = new JTextField();
        add(solutionTextField, BorderLayout.NORTH);

        checkButton = new JButton("Check");
        checkButton.addActionListener(this);
        add(checkButton, BorderLayout.SOUTH);

        initializeProblems();

        displayProblem();
    }

    private void initializeProblems() {
        problems = new HashMap<>();
        problems.put("Problem 1", "Write a function that returns the sum of two numbers.");
        problems.put("Problem 2", "Write a function that reverses a string.");
        // Aggiungere altri problemi qui
    }

    private void displayProblem() {
        String currentProblem = (String) problems.keySet().toArray()[0];
        problemTextArea.setText(problems.get(currentProblem));
        solutionTextField.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String currentProblem = (String) problems.keySet().toArray()[0];
        String solution = solutionTextField.getText().trim();

        if (solution.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a solution.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verifica se la soluzione è corretta
        boolean isCorrect = checkSolution(currentProblem, solution);
        if (isCorrect) {
            JOptionPane.showMessageDialog(this, "Correct solution!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Incorrect solution. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Passa al prossimo problema
        problems.remove(currentProblem);
        if (problems.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Congratulations! You completed all problems.", "Success", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        } else {
            displayProblem();
        }
    }

    private boolean checkSolution(String problem, String solution) {
        // Implementare la verifica della soluzione qui
        // Questo è solo un esempio, implementa la tua logica di verifica
        switch (problem) {
            case "Problem 1":
                return solution.equals("function sum(a, b) {\n    return a + b;\n}");
            case "Problem 2":
                return solution.equals("function reverseString(str) {\n    return str.split('').reverse().join('');\n}");
            default:
                return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CodeWarsGUI game = new CodeWarsGUI();
            game.setVisible(true);
        });
    }
}

