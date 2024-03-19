import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Robocode extends JFrame implements ActionListener {
    private static final int GRID_SIZE = 10;

    private JButton[][] gridButtons;
    private int robotRow, robotCol, targetRow, targetCol;

    public Robocode() {
        setTitle("Robocode");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        robotRow = 0;
        robotCol = 0;
        targetRow = 5;
        targetCol = 5;

        JPanel gridPanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
        gridButtons = new JButton[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(40, 40));
                button.setEnabled(false);
                gridPanel.add(button);
                gridButtons[i][j] = button;
            }
        }

        gridButtons[robotRow][robotCol].setText("R");
        gridButtons[targetRow][targetCol].setText("T");

        add(gridPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        JButton upButton = new JButton("Up");
        upButton.addActionListener(this);
        controlPanel.add(upButton);

        JButton downButton = new JButton("Down");
        downButton.addActionListener(this);
        controlPanel.add(downButton);

        JButton leftButton = new JButton("Left");
        leftButton.addActionListener(this);
        controlPanel.add(leftButton);

        JButton rightButton = new JButton("Right");
        rightButton.addActionListener(this);
        controlPanel.add(rightButton);

        add(controlPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String direction = e.getActionCommand();

        switch (direction) {
            case "Up":
                moveRobot(-1, 0);
                break;
            case "Down":
                moveRobot(1, 0);
                break;
            case "Left":
                moveRobot(0, -1);
                break;
            case "Right":
                moveRobot(0, 1);
                break;
        }
    }

    private void moveRobot(int dRow, int dCol) {
        int newRow = robotRow + dRow;
        int newCol = robotCol + dCol;

        if (isValidPosition(newRow, newCol)) {
            gridButtons[robotRow][robotCol].setText("T");
            robotRow = newRow;
            robotCol = newCol;
            gridButtons[robotRow][robotCol].setText("R");

            if (robotRow == targetRow && robotCol == targetCol) {
                JOptionPane.showMessageDialog(this, "Target reached! You win!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < GRID_SIZE && col >= 0 && col < GRID_SIZE;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RobocodeGUI game = new RobocodeGUI();
            game.setVisible(true);
        });
    }
}
