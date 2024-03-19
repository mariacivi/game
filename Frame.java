
import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    public Frame() {
        // Layout
        setLayout(new BorderLayout());

        // Snake
        add(new Snake(), BorderLayout.CENTER);

        // Finestra
        setTitle("Snake");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}