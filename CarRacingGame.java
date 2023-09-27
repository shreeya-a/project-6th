import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CarRacingGame extends JFrame {
    JFrame frame1 = null;
    JFrame frame;
    String input = "";
    JPanel mainPanel;
    private JButton startButton;
    //private JButton settingsButton;
    private JButton exitButton;
    private JButton ldButton; // leaderboard button
    JLabel label = new JLabel();
    private JTable leaderboardTable;

    public CarRacingGame() {
        setTitle("GAME");
        setSize(500, 500);

//        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel();
        //mainPanel.setBackground(Color.BLUE);
        mainPanel.setBounds(0, 32, 500, 500);
        mainPanel.setLayout(null);

        JLabel titleLabel = new JLabel("2-D CAR GAME");
        titleLabel.setForeground(Color.darkGray);
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 36));
        titleLabel.setBounds(100, 27, 300, 50);
        mainPanel.add(titleLabel);

        //JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        startButton = new JButton("START");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.WHITE);
        startButton.setFont(new Font("Verdana", Font.BOLD, 10));
        startButton.setBounds(190, 100, 120, 40);
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == startButton) {
                    game();
                }
            }
        });

        ldButton = new JButton("LEADERBOARD");
        ldButton.setBackground(Color.black);
        ldButton.setForeground(Color.WHITE);
        ldButton.setFont(new Font("Verdana", Font.BOLD, 10));
        ldButton.setBounds(190, 150, 120, 40);
        ldButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == ldButton) {
                    leaderBoardTable();
                }
            }
        });

        exitButton = new JButton("EXIT");
        exitButton.setFont(new Font("Verdana", Font.BOLD, 10));
        exitButton.setBackground(Color.red);
        exitButton.setForeground(Color.WHITE);
        exitButton.setBounds(190, 210, 120, 40);
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == exitButton) {
                    int confirmExit = JOptionPane.showConfirmDialog(mainPanel, "Do you really want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
                    if (confirmExit == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                }
            }
        });

        mainPanel.add(startButton);
        mainPanel.add(ldButton);
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

    public void game() {
        final String playerName;
//         String  input = "";
        input = JOptionPane.showInputDialog("Player name:");
        playerName = input;
        Thread gameThread = new Thread(() -> {
            Game game = new Game(playerName);
//            frameGUI(game);
            frame = new JFrame("Car Racing Game");
            frame.add(game);
            frame.setSize(500, 500);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);

            GameController gc = new GameController(game);
            gc.runGameLoop();

        });
        gameThread.start();

    }




        public ArrayList<Player> playerList(){
            ArrayList<Player> playerList = new ArrayList<>();
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url ="jdbc:mysql://localhost:3307/cargame";
                Connection conn = DriverManager.getConnection(url,"root","root");

//                String query1 = "SELECT TOP 15 player, score from player ORDER BY score desc )";
                String query1 = "SELECT player, score FROM player ORDER BY score DESC LIMIT 15";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query1);
                Player player;
                while (rs.next()) {
                    player = new Player(rs.getString("player"), rs.getInt("score"));
                    playerList.add(player);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            System.out.println("connection success");
            return playerList;
        }


    public void leaderBoardTable() {
        ArrayList<Player> list = playerList();

            int sn = 1;
            Object[][] row = new Object[list.size()][3];
            for (int i = 0; i<list.size(); i++){
                row[i][0]= sn;
                sn++;
                row[i][1]=list.get(i).getName();
                row[i][2]=list.get(i).getScore();
            }
//
        String[] columnNames = {"SN","Player Name", "Score"};


//         Create the JTable and populate it with data
                leaderboardTable = new JTable(row,columnNames);
        leaderboardTable.setFont(new Font("Verdana", Font.PLAIN, 14));
        
//        leaderboardTable.setCell(Color.blue);
        JTableHeader header = leaderboardTable.getTableHeader();
        Font headerFont = new Font("Arial",Font.BOLD,14);
        header.setFont(headerFont);
        header.setBackground(Color.green);

        int rowHeight = 20; // Adjust the height as needed
        leaderboardTable.setRowHeight(rowHeight);



        // Create a JScrollPane to hold the JTable
        JScrollPane scrollPane = new JScrollPane(leaderboardTable);
        scrollPane.setBounds(10, 10, 300, 400); // Adjust the bounds as needed
        scrollPane.setBackground(Color.LIGHT_GRAY);

        JFrame frame2 = new JFrame("LeaderBoard");
        frame2.add(scrollPane);
        frame2.setSize(500, 400);
        frame2.setBackground(Color.white);
        frame2.setVisible(true);
        frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame2.setLocationRelativeTo(null);
//        mainPanel.add(scrollPane); // Add the JScrollPane to the main panel

        }

//public void leaderBoardTable() {
//    ArrayList<Player> list = playerList();
//    String[] columnNames = {"Player Name", "Score"};
//
//    // Create a custom PlayerTableModel with the retrieved data
//    PlayerTableModel model = new PlayerTableModel(list);
//
//    // Create the JTable and set the custom model
//    leaderboardTable = new JTable(model);
//    leaderboardTable.setFont(new Font("Verdana", Font.PLAIN, 14));
//
//    // Create a JScrollPane to hold the JTable
//    JScrollPane scrollPane = new JScrollPane(leaderboardTable);
//    scrollPane.setBounds(50, 270, 400, 150); // Adjust the bounds as needed
//
//    mainPanel.add(scrollPane); // Add the JScrollPane to the main panel
//}



//    private void frameGUI(Game game) {
//        frame = new JFrame("Car Racing Game");
//        frame1 = frame;
//        frame.add(game);
//        frame.setSize(500, 500);
//        frame.setVisible(true);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLocationRelativeTo(null);
//
//        GameController gc = new GameController(game);
//        gc.runGameLoop();
//    }

    public void restartGame() {
        frame.dispose();
    }

    public static void main(String[] args) {
        CarRacingGame crg = new CarRacingGame();
    }
}


////
//public class CarRacingGame extends JFrame {
//    JFrame frame1 = null;
//    static JFrame frame;
//    String  input = "";
//    private JButton startButton;
//    //private JButton settingsButton;
//    private JButton exitButton;
//    JLabel label = new JLabel();
//
//    public void CarRacingGames(){
//        setTitle("GAME");
//        setSize(500, 500);
//
////        setSize(1000, 500);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//
//        JPanel mainPanel = new JPanel();
//        //mainPanel.setBackground(Color.BLUE);
//        mainPanel.setBounds(0, 32, 500, 500);
//        mainPanel.setLayout(null);
//
//        JLabel titleLabel = new JLabel("2-D CAR GAME");
//        titleLabel.setForeground(Color.darkGray);
//        titleLabel.setFont(new Font("Verdana", Font.BOLD, 36));
//        titleLabel.setBounds(370, 27, 300, 50);
//        mainPanel.add(titleLabel);
//
//        //JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
//        startButton = new JButton("START");
//        startButton.setBackground(Color.black);
//        startButton.setForeground(Color.WHITE);
//        startButton.setFont(new Font("Verdana", Font.BOLD, 15));
//        startButton.setBounds(200, 100, 100, 50);
//        startButton.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if(e.getSource() == startButton){
////                    String input = "";
////                    input = JOptionPane.showInputDialog("Player name:");
////                    if (input == null){
////                        input = "Player";
////                    }
//                    game();
//                }
//            }
//        });
//        exitButton = new JButton("EXIT");
//        exitButton.setFont(new Font("Verdana", Font.BOLD, 15));
//        exitButton.setBackground(Color.red);
//        exitButton.setForeground(Color.WHITE);
//        exitButton.setBounds(200, 200, 100, 50);
//        exitButton.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if(e.getSource() == exitButton){
//                    int confirmExit = JOptionPane.showConfirmDialog(mainPanel, "Do you really want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
//                    if (confirmExit == JOptionPane.YES_OPTION) {
//                        System.exit(0);
//                    }
//                }
//            }
//        });
//
//        mainPanel.add(startButton);
//        //mainPanel.add(settingsButton);
//        mainPanel.add(exitButton);
//        ImageIcon image = new ImageIcon();
//        label.setIcon(loadImage("CAR.jpg"));
//        label.setBounds(0, 0, this.getWidth(), this.getHeight());
//        mainPanel.add(label);
//        getContentPane().add(mainPanel);
//        setVisible(true);
//    }
//    private ImageIcon loadImage(String path) {
//
////
////        java.net.URL imgURL = getClass().getResource("/Graphics/Others/clock.jpg");
////        ImageIcon icon = new ImageIcon(imgURL);
//        Image image = new ImageIcon("CAR.jpg").getImage();
//        Image scaled = image.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
//        return new ImageIcon(scaled);
//    }
//
//    public void game(){
//        final String playerName;
////         String  input = "";
//        input = JOptionPane.showInputDialog("Player name:");
//        playerName = input;
//        Thread gameThread = new Thread(()->{
//            Game game = new Game(playerName);
//            frame = new JFrame("Car Racing Game");
//            frame.add(game);
//            frame.setSize(500, 500);
//            frame.setVisible(true);
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setLocationRelativeTo(null);
//
//            GameController gc = new GameController(game);
//            gc.runGameLoop();
//        });
//        frame1=frame;
//        gameThread.start();
//    }
////    public void restartGame() {
////
////        frame.dispose();
//////        frame.setVisible(false);
//////        game();
////    }
//
//    public void restartGame(String playerName, JOptionPane option) {
//        frame.dispose(); // Close the current frame
////        game();
//
//
//
//    }
//    public static void main(String[] args) {
//        CarRacingGame crg = new CarRacingGame();
//        crg.CarRacingGames();
//    }
//}
