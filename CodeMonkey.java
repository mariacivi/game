import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CodeMonkey extends JFrame implements ActionListener {
    private static final int GRID_SIZE = 5;

    private JButton[][] gridButtons;
    private int monkeyRow, monkeyCol;

    public CodeMonkey() {
        setTitle("CodeMonkey");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));

        gridButtons = new JButton[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(40, 40));
                button.setEnabled(false);
                add(button);
                gridButtons[i][j] = button;
            }
        }

        // Inizializza il personaggio CodeMonkey nella posizione iniziale
        monkeyRow = 0;
        monkeyCol = 0;
        gridButtons[monkeyRow][monkeyCol].setText("Monkey");

        // Inizializza il bottone di esecuzione per eseguire le istruzioni
        JButton executeButton = new JButton("Execute");
        executeButton.addActionListener(this);
        add(executeButton);

        // Imposta il pulsante di direzione per spostare CodeMonkey
        JButton upButton = new JButton("Up");
        upButton.addActionListener(this);
        add(upButton);

        JButton downButton = new JButton("Down");
        downButton.addActionListener(this);
        add(downButton);

        JButton leftButton = new JButton("Left");
        leftButton.addActionListener(this);
        add(leftButton);

        JButton rightButton = new JButton("Right");
        rightButton.addActionListener(this);
        add(rightButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Execute":
                // Esegui le istruzioni di movimento
                // Questo è solo un esempio, implementa la logica del gioco
                // adattata alle istruzioni di movimento
                break;
            case "Up":
                moveMonkey(-1, 0);
                break;
            case "Down":
                moveMonkey(1, 0);
                break;
            case "Left":
                moveMonkey(0, -1);
                break;
            case "Right":
                moveMonkey(0, 1);
                break;
        }
    }

    private void moveMonkey(int dRow, int dCol) {
        int newRow = monkeyRow + dRow;
        int newCol = monkeyCol + dCol;

        if (isValidPosition(newRow, newCol)) {
            gridButtons[monkeyRow][monkeyCol].setText("");
            monkeyRow = newRow;
            monkeyCol = newCol;
            gridButtons[monkeyRow][monkeyCol].setText("Monkey");
        }
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < GRID_SIZE && col >= 0 && col < GRID_SIZE;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CodeMonkey game = new CodeMonkey();
            game.setVisible(true);
        });
    }
}

