package sk.tuke.kpi.kp.pexeso;


import org.springframework.beans.factory.annotation.Autowired;
import sk.tuke.kpi.kp.pexeso.entity.Comment;
import sk.tuke.kpi.kp.pexeso.entity.Rating;
import sk.tuke.kpi.kp.pexeso.entity.Score;
import sk.tuke.kpi.kp.pexeso.service.CommentService;
import sk.tuke.kpi.kp.pexeso.service.RatingService;
import sk.tuke.kpi.kp.pexeso.service.ScoreService;

import java.io.Serializable;
import java.sql.Timestamp;


public class GameUI implements Serializable {
    private final GameField gameField;
    private final Player player;

    @Autowired
    private ScoreService scoreService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private RatingService ratingService;

    public GameUI(GameField gameField) {
        super();
        this.gameField = gameField;
        player = new Player(gameField);
    }

    public void showMenu(){

    }

    public void showPlayerStats(){
        System.out.println("Your name: "+player.getName()+"\t\tYour moves: "+player.getMoves()+"\t\t Your score: "+player.getScore()+"\n");
    }
    public void showPlayerScore(){
        System.out.print("Your score: "+player.getScore());
    }

    public void printLastPicked(int whichprint){
        if (whichprint == 0){
            System.out.println("Your first choose this position in fied:["+gameField.getPickedRow(0)+"]["+gameField.getPickedColum(0)+"]");
        }else {
            System.out.println("Your second choose this position in fied:["+gameField.getPickedRow(1)+"]["+gameField.getPickedColum(1)+"]");
        }
    }
    private void printTopScore(){
        var scores = scoreService.getTopScore();
        System.out.println("---------------------------------------------------------------");
        for (int i = 0; i < scores.size(); i++) {
            var score = scores.get(i);
            System.out.printf("ID: "+ (i + 1) +" Moves: "+ score.getMove()+ " Number of Cards: "+score.getNum_of_cards()+" Name: "+score.getPlayer()+" Score: "+score.getScore()+"\n");
        }
        System.out.println("---------------------------------------------------------------");

    }

    private void printTopRating(){
        var ratings = ratingService.getTopRatings();
        System.out.println("---------------------------------------------------------------");
        for (int i = 0; i < ratings.size(); i++) {
            var rating = ratings.get(i);
            System.out.printf("ID: "+ (i + 1)+" Name: "+ rating.getPlayer()+" Rating: "+rating.getRating()+" Date: "+rating.getDate()+"\n");
        }
        System.out.println("---------------------------------------------------------------");

    }

    private void printTopCooment(){
        var comments =commentService.getTopComments();
        System.out.println("---------------------------------------------------------------");
        for (int i = 0; i < comments.size(); i++) {
            var comment = comments.get(i);
            System.out.printf("ID: "+ (i + 1)+" Name: "+ comment.getPlayer()+" Comment: "+comment.getComment()+" Date: "+comment.getDate()+"\n");
        }
        System.out.println("---------------------------------------------------------------");

    }
    public void printField(){
        for(int i=0;i<gameField.getRows();i++){
            if (i == 0) {
                System.out.print("  ");
                for (int a = 0; a < gameField.getColums(); a++) {
                    System.out.print("  ");
                    System.out.print(a);

                }
                System.out.print("\n");
                System.out.print("  | ");
                for (int a = 0; a < gameField.getColums(); a++) {
                    System.out.print("-  ");
                }
                System.out.println("|");

            }
            System.out.print(i);
            System.out.print(' ');
            System.out.print("| ");
            for (int j=0;j<gameField.getColums();j++) {
                    gameField.getItemFromField(i,j).imageChange();
                    System.out.print(' ');
                }

            System.out.println("|");
        }
        System.out.print("  | ");
        for(int i=0;i<gameField.getColums();i++){
            System.out.print("-  ");
        }
        System.out.println("|\n");

    }

    public Player getPlayer() {
        return player;
    }


    public GameField getGameField() {
        return gameField;
    }

    public void playingGame(){
        gameField.setUpGame();
        showPlayerStats();
        printField();
        while (gameField.isWon()==GameState.WON){
            gameField.clearPickedParameters();
            for (int i=0 ; i < 2 ; i++){
                player.inputHandler(i);
                player.countMove();
                if (i==0){
                    printField();
                    showPlayerStats();
                }
            }
            printField();
            gameField.turnedCardCheck();
            player.countScore();
            showPlayerStats();
        }
        scoreService.addScore(new Score(player.getName(), getGameField().getNumOfCards(), player.getMoves(), player.getScore()));
        System.out.println("You won "+player.getName()+" with "+player.getMoves()+" moves and your score is "+player.getScore()+"\n");
        System.out.println("If you want to see database with score write 'y' if dont write 'n' if you put wrong input you must put input one more time");
        boolean database = player.databaseHandler();
        if (database){
            printTopScore();
        }
        System.out.println("If you want to see database with ratings write 'y' if dont write 'n' if you put wrong input you must put input one more time");
        database = player.databaseHandler();
        if (database){
            printTopRating();
        }
        System.out.println("If you want to write a rating write 'y' if dont write 'n' if you put wrong input you must put input one more time");
        database = player.databaseHandler();
        if (database){
            System.out.println("Rating must be number from 0 to 5 and you can put number like 3.6");
            double rating= player.ratingHandler();
            ratingService.addRatings(new Rating(player.getName(),rating,new Timestamp(System.currentTimeMillis())));
        }
        System.out.println("If you want to see database with comments write 'y' if dont write 'n' if you put wrong input you must put input one more time");
        database = player.databaseHandler();
        if (database){
            printTopCooment();
        }
        System.out.println("If you want to write a comment write 'y' if dont write 'n' if you put wrong input you must put input one more time");
        database = player.databaseHandler();
        if (database){
            System.out.println("You can now write your comment");
            String comment= player.commentHandler();
            commentService.addComments(new Comment(player.getName(),comment,new Timestamp(System.currentTimeMillis())));
        }
    }
}


