public class GameController {
    private final Game game;
    private int count = 1;
    private int c = 1;


    public GameController(Game game) {
        this.game = game;
    }

    public void runGameLoop() {
        while (true) {

                game.moveRoad(count);

            while (c <= 1) {
                game.repaint();
                try {
                    Thread.sleep(5);
                } catch (Exception e) {
                    System.out.println(e);
                }
                c++;
            }
            c = 1;
            count++;

            if(game.nOpponent < 4 && count % 200 == 0){ //if there is less than 4 cars and count timer reaches 200
                game.imageLoc[game.nOpponent] = "images/car_left_"+((int)((Math.random()*100)%3)+1)+".png"; //assign images to the opponent cars
                game.lx[game.nOpponent] = 499; //set opponent cars start positions
                int p = (int)(Math.random()*100)%4; //create a random number that is the remainder of a number between 0 and 100 is divided by 4.
                if(p == 0){     //if the remainder is 0
                    p = 250;    //place the car in the fourth lane
                }
                else if(p == 1){ //if the remainder is 1
                    p = 300;    //place the car in the second lane
                }
                else if(p == 2){ //if the remainder is 2
                    p = 185;    //place the car in the third lane
                }
                else{           //otherwise
                    p = 130;    //place the car in the fourth  lane
                }
                game.ly[game.nOpponent] = p; //assign lane position to car
                game.speedOpponent[game.nOpponent] = (int)(Math.random()*100)%2 + 2; //sets the speed of the new opponent car to a random number that is the remainder of a number between 0 and 100, plus 2
                game.nOpponent++; //add the car to the game
            }
        }
    }
}
