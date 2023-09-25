import java.sql.*;

public class DB {

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


        ResultSet highscore = statement.executeQuery("SELECT * from player where score = (SELECT max(score) from player)");

//        ResultSet resultSet = statement.executeQuery("SELECT * from player where id =0");
        while (highscore.next()) {
            System.out.print(highscore.getInt("score") + " ");

        }



    }


}
