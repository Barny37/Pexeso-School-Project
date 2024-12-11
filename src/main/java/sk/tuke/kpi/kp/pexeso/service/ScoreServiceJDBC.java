package sk.tuke.kpi.kp.pexeso.service;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.kp.pexeso.entity.Score;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ScoreServiceJDBC implements ScoreService {
    public static final String JDBC_URL = "jdbc:postgresql://localhost/postgres";
    public static final String JDBC_USER = "postgres";
    public static final String JDBC_PASSWORD = "lenovobarny";
    public static final String INSERT_STATEMENT = "INSERT INTO score (player, num_of_cards, moves, score) VALUES(?,?,?,?)";
    public static final String DELETE_STATEMENT = "DELETE FROM score";
    public static final String SELECT_TOPSCORE = "SELECT player, num_of_cards, score.moves, score.score, score FROM score ORDER BY score.score DESC LIMIT 10;";
    @Override
    public List<Score> getTopScore() {

        /*ESTABLISHING CONNECTION*/
        try(var connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            var statement = connection.createStatement();
            var rs = statement.executeQuery(SELECT_TOPSCORE);
        ){
            /*CREATING LIST TO RETURN*/
            var scores = new ArrayList<Score>();

            /*ADDING SCORES FROM DATABASE TO THE LIST*/
            while(rs.next()){
                scores.add(new Score(rs.getString(1), rs.getInt(2), rs.getInt(3),rs.getInt(4)));
            }

            return scores;
        } catch (SQLException e) {
            System.out.println("Table does not exists!");
            throw new GameStudioException(e);
        }
    }
    @Override
    public void addScore(@NotNull Score score) {
        try (var connection = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD);
             var statemennt = connection.prepareStatement(INSERT_STATEMENT)
        ){
            statemennt.setString(1,score.getPlayer());
            statemennt.setInt(2,score.getNum_of_cards());
            statemennt.setInt(3,score.getMove());
            statemennt.setInt(4,score.getScore());
            //statemennt.executeUpdate();
        }catch (SQLException e){
            throw new GameStudioException(e);
        }
    }

    public void prinDatabse(){
        try {
            var scoreA = getTopScore();
            scoreA.forEach((score) -> System.out.println("Name: " + score.getPlayer() +" Number of crads have: "+ score.getNum_of_cards() +" Number of move: "+ score.getMove() +"and his score: "+score.getScore()));

        }catch (Exception e){
            throw new GameStudioException(e);
        };
    }
    @Override
    public void reset() {
        try (var connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             var statement = connection.createStatement()
        ) {
            statement.executeUpdate(DELETE_STATEMENT);
        } catch (SQLException e) {
            throw new GameStudioException(e);
        }
    }

    }


