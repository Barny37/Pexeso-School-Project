package sk.tuke.kpi.kp.pexeso;

import org.springframework.beans.factory.annotation.Autowired;
import sk.tuke.kpi.kp.pexeso.entity.Score;

import sk.tuke.kpi.kp.pexeso.service.ScoreService;

import java.io.Serializable;
import java.util.*;

public class Player implements Serializable {

    private int moves;
    private String name;
    private int score;
    private int inARow;
    private int scoreAddition;

    private final GameField gameField;
    public Player(GameField gameField){
        this.gameField=gameField;
        moves = 0;
        name = "Player";
        score = 0;
        inARow = 0;
        scoreAddition = 100;
    }
    public Player getPlayer(){return this;}
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public int getMoves() {
        return moves;
    }

    public void menuInputHandler(){
        Scanner input = new Scanner(System.in);
        String name;
        do{
            System.out.println("Write your name(minimal lenght 3 letters):");
            name = input.nextLine();
        }while (name.length()<3);
        setName(name);
        int rows;
        int colums;
        do {
            System.out.println("Please put number of rows and colums when will be there only even number of cards");
            System.out.println("Enter a number of rows with cards(4 is minimum 8 is maximum)");
            rows = input.nextInt();
            while (rows>8 || rows<4){
                System.out.println("You put wrong number please enter a number of rows with cards(4 is minimum 8 is maximum)");
                rows = input.nextInt();
            }
            System.out.println("Enter a number of colums with cards(4 is minimum 8 is maximum)");
            colums = input.nextInt();
            while (colums>8 || colums<4){
                System.out.println("You put wrong number please enter a number of colums with cards(4 is minimum 8 is maximum)");
                colums = input.nextInt();

            }
        }while((rows*colums)%2!=0);
        gameField.setNumOfCards(rows*colums);
        gameField.setColums(colums);
        gameField.setRows(rows);
    }

    public boolean databaseHandler() {
        Scanner input = new Scanner(System.in);
        char confirm;
        do {
            confirm = input.next().toLowerCase(Locale.ROOT).charAt(0);
        } while (confirm != 'y' && confirm != 'n');
            if (confirm == 'y') {
                return true;
            }else {
                return false;
            }

    }

    public String commentHandler(){
        Scanner input = new Scanner(System.in);
        String comment;
        comment = input.nextLine();
        return comment;
    }
    public Double ratingHandler(){
        Scanner input = new Scanner(System.in);
        double rating;
        do {
            rating = input.nextDouble();
        }while (rating>5 || rating<=0);
        return rating;
    }
    public void inputHandler(int i){
        Scanner input = new Scanner(System.in);
        int parameters[];

            do{
                System.out.println("Please enter coordinates "+(i+1)+" card which you want to turn(for example:(row)0 0(colum))");
                parameters = inputSpliter();
                gameField.setPickedRow(i,parameters[0]);
                gameField.setPickedColum(i,parameters[1]);

            }while (!gameField.pickedStatsCheck(i) || !gameField.foundedCardCheck(i)|| !gameField.sameCardCheck());
            gameField.getItemFromField(gameField.getPickedRow(i),gameField.getPickedColum(i)).changeTurnedState();
    }

    public int[] inputSpliter(){
        Scanner input = new Scanner(System.in);
        String s[]= input.nextLine().split(" ");

        while (s.length !=2 ){
            System.out.println("Wrong input please put input one more time");
            s= input.nextLine().split(" ");
        }
        int a[] = new int[s.length];
        for(int i =0 ;i < s.length;i++){
            if (Objects.equals(s[i], "")) continue;
            a[i]= Integer.parseInt(s[i]);
        }
        return a;
    }
    public void countScore(){
        if (gameField.getFoundedPair()){
            inARow++;
            score+=scoreAddition*inARow;
            scoreAddition = 100;
        }else {
            inARow=0;
            scoreAddition-=10;
            if (scoreAddition<10){
                scoreAddition=10;
            }
        }

    }





    public void setInARow(int inARow) {
        this.inARow = inARow;
    }

    public void setScoreAddition(int scoreAddition) {
        this.scoreAddition = scoreAddition;
    }

    public int getInARow() {
        return inARow;
    }

    public int getScoreAddition() {
        return scoreAddition;
    }

    public int getScore() {
        return score;
    }

    public void countMove(){
        moves++;
    }
}
