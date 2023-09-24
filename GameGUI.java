import javax.swing.*;

public class GameGUI extends JFrame {
    GameGUI()  {
        JFrame frame = new JFrame("Car Racing Game");
        Game game = new Game();
        frame.add(game);
        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        GameController gameController = new GameController(game);
//        gameController.runGameLoop();

//        int count = 1, c = 1;
//        while(true) {
//            game.moveRoad(count);   //move the road
//            while (c <= 1) {
//                game.repaint();     //redraw road to match new locations
//                try {
//                    Thread.sleep(5);    //wait so that the road appears to be moving continously
//                } catch (Exception e) {
//                    System.out.println(e);
//                }
//                c++;
//            }
//            c = 1;
//            count++; //increment count value
//            if (game.nOpponent < 4 && count % 200 == 0) { //if there is less than 4 cars and count timer reaches 200
//                game.imageLoc[game.nOpponent] = "images/car_left_" + ((int) ((Math.random() * 100) % 3) + 1) + ".png"; //assign images to the opponent cars
//                game.lx[game.nOpponent] = 499; //set opponent cars start positions
//                int p = (int) (Math.random() * 100) % 4; //create a random number that is the remainder of a number between 0 and 100 is divided by 4.
//                if (p == 0) {     //if the remainder is 0
//                    p = 250;    //place the car in the fourth lane
//                } else if (p == 1) { //if the remainder is 1
//                    p = 300;    //place the car in the second lane
//                } else if (p == 2) { //if the remainder is 2
//                    p = 185;    //place the car in the third lane
//                } else {           //otherwise
//                    p = 130;    //place the car in the fourth  lane
//                }
//                game.ly[game.nOpponent] = p; //assign lane position to car
//                game.speedOpponent[game.nOpponent] = (int) (Math.random() * 100) % 2 + 2; //sets the speed of the new opponent car to a random number that is the remainder of a number between 0 and 100, plus 2
//                game.nOpponent++; //add the car to the game
//            }
//        }
//        JFrame frame = new JFrame("Car Racing Game");   //creating a new JFrame window to display the game
//        Game game = new Game(); //creating a new instance of a Game
//        frame.add(game);		//Graphics2D components are added to JFrame Window
//        frame.setSize(500,500); //setting size of screen to 500x500
//        frame.setVisible(true); //allows the JFrame and its children to displayed on the screen
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        GameController gameController = new GameController(game);
//        gameController.runGameLoop();
    }
}
