import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {
    private JButton playButton;
    private JButton exitButton;
    private JTextField nameTextField; // Add the JTextField for entering the player's name

    public MainMenu(GameController gameController) {
        playButton = new JButton("Play");
        exitButton = new JButton("Exit");

        nameTextField = new JTextField(20); // Specify the desired columns for the text field

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Start the game with the player's name when the Play button is clicked
                String playerName = nameTextField.getText();
//                gameController.startGame(playerName);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Exit the game when the Exit button is clicked
                System.exit(0);
            }
        });

        add(new JLabel("Enter your name:")); // Add a label for the text field
        add(nameTextField); // Add the text field
        add(playButton);
        add(exitButton);
    }
}
