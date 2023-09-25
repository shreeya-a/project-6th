import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CarRacingGame extends JFrame {
    private JButton startButton;
    //private JButton settingsButton;
    private JButton exitButton;
    JLabel label = new JLabel();

    public CarRacingGame(){
        setTitle("GAME");
        setSize(500, 500);

//        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        //mainPanel.setBackground(Color.BLUE);
        mainPanel.setBounds(0, 32, 500, 500);
        mainPanel.setLayout(null);

        JLabel titleLabel = new JLabel("2-D CAR GAME");
        titleLabel.setForeground(Color.darkGray);
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 36));
        titleLabel.setBounds(370, 27, 300, 50);
        mainPanel.add(titleLabel);

        //JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        startButton = new JButton("START");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.WHITE);
        startButton.setFont(new Font("Verdana", Font.BOLD, 15));
        startButton.setBounds(200, 100, 100, 50);
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource() == startButton){
//                    String input = "";
//                    input = JOptionPane.showInputDialog("Player name:");
//                    if (input == null){
//                        input = "Player";
//                    }
                    game();
                }
            }
        });
        exitButton = new JButton("EXIT");
        exitButton.setFont(new Font("Verdana", Font.BOLD, 15));
        exitButton.setBackground(Color.red);
        exitButton.setForeground(Color.WHITE);
        exitButton.setBounds(200, 200, 100, 50);
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource() == exitButton){
                    int confirmExit = JOptionPane.showConfirmDialog(mainPanel, "Do you really want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
                    if (confirmExit == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                }
            }
        });

        mainPanel.add(startButton);
        //mainPanel.add(settingsButton);
        mainPanel.add(exitButton);
        ImageIcon image = new ImageIcon();
        label.setIcon(loadImage("CAR.jpg"));
        label.setBounds(0, 0, this.getWidth(), this.getHeight());
        mainPanel.add(label);
        getContentPane().add(mainPanel);
        setVisible(true);
    }
    private ImageIcon loadImage(String path) {
        Image image = new ImageIcon("CAR.jpg").getImage();
        Image scaled = image.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    public void game(){
        final String playerName;
         String  input = "";
        input = JOptionPane.showInputDialog("Player name:");
        playerName = input;
        Thread gameThread = new Thread(()->{
            Game game = new Game(playerName);
            JFrame frame = new JFrame("Car Racing Game");
            frame.add(game);
            frame.setSize(500, 500);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


            GameController gc = new GameController(game);
            gc.runGameLoop();
        });
        gameThread.start();

    }


    public static void main(String[] args) {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        String url ="jdbc:mysql://localhost:3307/cargame";
////        connect to database
//        try {
//            Connection conn = DriverManager.getConnection(url,"root","root");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("connection success");
        CarRacingGame crg = new CarRacingGame();
    }
}
