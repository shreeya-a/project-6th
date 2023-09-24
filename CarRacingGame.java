import javax.swing.*;

public class CarRacingGame {

    public static void main(String[] args) {


        JFrame frame = new JFrame("Car Racing Game");
        Game game = new Game();
        frame.add(game);
        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GameController gameController = new GameController(game);
        gameController.runGameLoop();
    }
}
