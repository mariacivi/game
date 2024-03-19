import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RaguelokeGame extends JFrame implements ActionListener {

    private JTextArea outputTextArea;
    private JButton exploreButton, searchButton, caveButton, exitButton;

    public RaguelokeGame() {
        setTitle("Ragueloke");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1));

        exploreButton = new JButton("Esplora");
        exploreButton.addActionListener(this);
        buttonPanel.add(exploreButton);

        searchButton = new JButton("Cerca indizi");
        searchButton.addActionListener(this);
        buttonPanel.add(searchButton);

        caveButton = new JButton("Vai alla caverna oscura");
        caveButton.addActionListener(this);
        buttonPanel.add(caveButton);

        exitButton = new JButton("Esci");
        exitButton.addActionListener(this);
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.EAST);

        outputTextArea.append("Benvenuto in Ragueloke!\n");
        outputTextArea.append("Sei perso in un mondo misterioso. Devi trovare un modo per tornare a casa.\n");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exploreButton) {
            esplora();
        } else if (e.getSource() == searchButton) {
            cercaIndizi();
        } else if (e.getSource() == caveButton) {
            vaiAllaCavernaOscura();
        } else if (e.getSource() == exitButton) {
            JOptionPane.showMessageDialog(this, "Arrivederci! Grazie per aver giocato a Ragueloke!");
            System.exit(0);
        }
    }

    private void esplora() {
        outputTextArea.append("Esplori i dintorni e trovi una strana pietra luminosa.\n");
        outputTextArea.append("Cosa vuoi fare con la pietra?\n");
        String[] options = {"Prendi la pietra", "Lascia la pietra"};
        int choice = JOptionPane.showOptionDialog(this, "Cosa vuoi fare con la pietra?", "Esplora",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (choice == JOptionPane.YES_OPTION) {
            outputTextArea.append("Hai preso la pietra. Potrebbe essere utile.\n");
        } else {
            outputTextArea.append("Decidi di lasciare la pietra.\n");
        }
    }

    private void cercaIndizi() {
        Random rand = new Random();
        int randomNumber = rand.nextInt(3);
        switch (randomNumber) {
            case 0:
                outputTextArea.append("Trovi un foglio di carta con un messaggio criptico scritto sopra.\n");
                break;
            case 1:
                outputTextArea.append("Trovi delle impronte misteriose sul terreno.\n");
                break;
            case 2:
                outputTextArea.append("Trovi un rotolo di pergamena con un antico sigillo.\n");
                break;
            default:
                break;
        }
    }

    private void vaiAllaCavernaOscura() {
        outputTextArea.append("Ti avventuri nella caverna oscura...\n");
        outputTextArea.append("Dopo un po', senti un rumore inquietante e decidi di tornare indietro.\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RaguelokeGameGUI game = new RaguelokeGameGUI();
            game.setVisible(true);
        });
    }
}
