import java.sql.*;

public class DB {

    int highscore;
    DB(String playerName , int score) throws ClassNotFoundException, SQLException {

        if (playerName.isEmpty()){
            playerName = "Player";
        }

            Class.forName("com.mysql.cj.jdbc.Driver");
      String url ="jdbc:mysql://localhost:3307/cargame";
//        connect to database

            Connection conn = DriverManager.getConnection(url,"root","root");

        System.out.println("connection success");

//        insert data to database
        Statement statement = conn.createStatement();

        int enterSet = statement.executeUpdate("INSERT INTO player (player, score) values ('"+playerName+"', '"+score+"')");
//        statement.executeUpdate("UPDATE player set score= '"+score+"' where player = '"+playerName+"'");
//        statement.executeUpdate("DELETE from player where score=0");


        ResultSet resultSet = statement.executeQuery(" (SELECT max(score) as highscore from player)");
        while (resultSet.next()) {
            highscore = resultSet.getInt("highscore");
        }



    }
    public int Highscore() {
        System.out.println(highscore);
        return this.highscore;

    }



}
