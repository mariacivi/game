import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class CampoMinato extends JFrame implements ActionListener {

    private final int NUM_ROWS = 8;
    private final int NUM_COLS = 8;
    private final int NUM_MINES = 10;

    private JButton[][] buttons;
    private boolean[][] mines;
    private boolean[][] revealed;
    private JPanel gamePanel;

    public CampoMinato() {
        setTitle("Campo Minato");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setupGame();
    }

    private void setupGame() {
        gamePanel = new JPanel(new GridLayout(NUM_ROWS, NUM_COLS));
        buttons = new JButton[NUM_ROWS][NUM_COLS];
        mines = new boolean[NUM_ROWS][NUM_COLS];
        revealed = new boolean[NUM_ROWS][NUM_COLS];

        // Initialize mines
        Random random = new Random();
        int minesPlaced = 0;
        while (minesPlaced < NUM_MINES) {
            int row = random.nextInt(NUM_ROWS);
            int col = random.nextInt(NUM_COLS);
            if (!mines[row][col]) {
                mines[row][col] = true;
                minesPlaced++;
            }
        }
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(40, 40));
                button.setActionCommand(i + "," + j);
                button.addActionListener(this);
                gamePanel.add(button);
                buttons[i][j] = button;
            }
        }

        add(gamePanel, BorderLayout.CENTER);
    }

    private void reveal(int row, int col) {
        if (row < 0 || row >= NUM_ROWS || col < 0 || col >= NUM_COLS || revealed[row][col])
            return;

        revealed[row][col] = true;
        buttons[row][col].setEnabled(false);

        if (mines[row][col]) {
            buttons[row][col].setText("*");
            JOptionPane.showMessageDialog(this, "Game Over! Hai colpito una mina.", "Game Over", JOptionPane.ERROR_MESSAGE);
            revealMines();
        } else {
            int count = countAdjacentMines(row, col);
            if (count > 0) {
                buttons[row][col].setText(Integer.toString(count));
            } else {
                for (int i = row - 1; i <= row + 1; i++) {
                    for (int j = col - 1; j <= col + 1; j++) {
                        reveal(i, j);
                    }
                }
            }
        }
    }

    private int countAdjacentMines(int row, int col) {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < NUM_ROWS && j >= 0 && j < NUM_COLS && mines[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    private void revealMines() {
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                if (mines[i][j]) {
                    buttons[i][j].setText("*");
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] command = e.getActionCommand().split(",");
        int row = Integer.parseInt(command[0]);
        int col = Integer.parseInt(command[1]);
        reveal(row, col);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CampoMinatoGUI campoMinatoGUI = new CampoMinatoGUI();
            campoMinatoGUI.setVisible(true);
        });
    }
}
