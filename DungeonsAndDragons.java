import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class DungeonsAndDragons extends JFrame implements ActionListener {
    private JTextField playerNameField;
    private JButton rollButton, attackButton;
    private JTextArea logTextArea;

    private Random random;

    public DungeonsAndDragons() {
        setTitle("Dungeons and Dragons");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        random = new Random();

        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new GridLayout(3, 2));

        JLabel nameLabel = new JLabel("Player Name:");
        playerNameField = new JTextField();
        playerPanel.add(nameLabel);
        playerPanel.add(playerNameField);

        JLabel rollLabel = new JLabel("Roll Result:");
        logTextArea = new JTextArea();
        logTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logTextArea);
        playerPanel.add(rollLabel);
        playerPanel.add(scrollPane);

        rollButton = new JButton("Roll");
        rollButton.addActionListener(this);
        playerPanel.add(rollButton);

        attackButton = new JButton("Attack");
        attackButton.addActionListener(this);
        playerPanel.add(attackButton);

        add(playerPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rollButton) {
            int rollResult = random.nextInt(20) + 1; // Roll a d20
            logTextArea.append("Roll Result: " + rollResult + "\n");
        } else if (e.getSource() == attackButton) {
            int attackResult = random.nextInt(20) + 1; // Roll a d20 for attack
            logTextArea.append("Attack Result: " + attackResult + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DungeonsAndDragonsGUI game = new DungeonsAndDragonsGUI();
            game.setVisible(true);
        });
    }
}
