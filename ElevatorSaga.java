import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ElevatorSaga extends JFrame implements ActionListener {
    private JButton upButton, downButton;
    private JLabel currentFloorLabel;
    private int currentFloor;

    public ElevatorSaga() {
        setTitle("Elevator Saga");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        currentFloor = 1;

        upButton = new JButton("Up");
        upButton.addActionListener(this);
        downButton = new JButton("Down");
        downButton.addActionListener(this);

        currentFloorLabel = new JLabel("Current Floor: " + currentFloor);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(upButton);
        buttonPanel.add(downButton);

        add(currentFloorLabel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == upButton) {
            if (currentFloor < 5) { // Supponiamo che ci siano solo 5 piani
                currentFloor++;
                currentFloorLabel.setText("Current Floor: " + currentFloor);
            }
        } else if (e.getSource() == downButton) {
            if (currentFloor > 1) {
                currentFloor--;
                currentFloorLabel.setText("Current Floor: " + currentFloor);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ElevatorSaga game = new ElevatorSaga();
            game.setVisible(true);
        });
    }
}
