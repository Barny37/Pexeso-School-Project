package sk.tuke.kpi.kp.pexeso;

import sk.tuke.kpi.kp.pexeso.entity.Score;
import sk.tuke.kpi.kp.pexeso.service.GameStudioException;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;

public class GameField implements Serializable{
    private static final String FILE = "score.bin";
    private final Card[] cards;
    private final Card[][] field;
    private int numOfCards;
    private int rows;
    private int colums;
    private final int[] pickedRow;
    private final int[] pickedColum;
    private boolean foundedPair;
    private GameState gameState;



    public GameField(){
        gameState=GameState.SETINGUP;
        pickedColum = new int [2];
        pickedRow = new int [2];
        numOfCards = 0;
        rows = 8;
        colums = 8;
        field = new Card[8][8];
        cards = new Card[64];
        foundedPair = false;
    }

    public void setNumOfCards(int numOfCards) {
        this.numOfCards = numOfCards;
    }
    public void countCards(){
        numOfCards=rows*colums;
    }
    public void setAllSpecialNum(){
        var photos = getNames();
        int num = 1;
        int photoNum=0;
        for(int i=0;i<numOfCards;i+=2){
            cards[i]= new Card(num,photos[photoNum]);
            cards[i+1]= new Card(num,photos[photoNum]);
            photoNum++;
            num++;
        }
    }

    public void setUpGame(){
        countCards();
        setAllSpecialNum();
        shuffleCards();
    }
    private String[] getNames(){
        Path filePath = Path.of("D:/TUKE21.22/LS/Komponentove_programovanie/src/main/resources/static/images.pexeso/names.txt");
        String[] fileContent = new String[32];
        int i=0;
            try (BufferedReader br = new BufferedReader(new FileReader(String.valueOf(filePath)))) {
                String sCurrentLine;
                while ((sCurrentLine = br.readLine()) != null) {
                    fileContent[i] = sCurrentLine.toString();
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        return fileContent;
    }

    public void makeCardsInField(){
        for (int x=0;x<rows;x++){
            for (int y=0;y<colums;y++){
                field[x][y] = new Card(0,null);

            }
        }
    }
    public void shuffleCards(){
        makeCardsInField();
        Random random = new Random();
        int randomX ;
        int randomY ;
        for (int i=0;i<numOfCards;i++){
            randomY = random.nextInt(colums);
            randomX = random.nextInt(rows);

            while(field[randomX][randomY].getSpecialNumber()!=0){
                randomY = random.nextInt(colums);
                randomX = random.nextInt(rows);
            }
            field[randomX][randomY].setSpecialNumber(cards[i].getSpecialNumber());
            field[randomX][randomY].setImage(cards[i].getImage());
        }
    }

    public boolean pickedStatsCheck(int check){
        if (pickedColum[check]<0 || pickedColum[check]>colums-1){
            return false;
        }else if (pickedRow[check]<0 || pickedRow[check]>rows-1){
            return false;
        }else {
            return true;
        }
    }
    public boolean sameCardCheck(){

        if(pickedColum[0] == pickedColum[1] && pickedRow[0]==pickedRow[1]){
            return false;
        }else {
            return true;
        }
    }
    public boolean foundedCardCheck(int check){
        if (field[pickedRow[check]][pickedColum[check]].getCardState()==CardState.FOUNDED){
            return false;
        }else{
            return true;
        }
    }
    public void turnedCardCheck(){
        if (field[pickedRow[0]][pickedColum[0]].getSpecialNumber()==field[pickedRow[1]][pickedColum[1]].getSpecialNumber()){
            field[pickedRow[0]][pickedColum[0]].changeFoundState();
            field[pickedRow[1]][pickedColum[1]].changeFoundState();
            clearPickedParameters();
            foundedPair=true;
        }else{
            field[pickedRow[0]][pickedColum[0]].changeTurnedState();
            field[pickedRow[1]][pickedColum[1]].changeTurnedState();
            clearPickedParameters();
            foundedPair=false;
        }
    }
    public void clearPickedParameters(){
        for (int j=0;j<2;j++){
            pickedRow[j]=-1;
            pickedColum[j]=-1;
        }
    }
    public GameState isWon(){
        int foundedCounter = 0;
        for (int x = 0;x<rows;x++){
            for (int y = 0;y<colums;y++){
                if (field[y][x].getCardState()==CardState.FOUNDED){
                    foundedCounter++;
                }
            }
        }
        if (foundedCounter==numOfCards){
            gameState=GameState.WON;
            return GameState.WON;
        }else {
            return GameState.PLAYING;
        }
    }

    public boolean checkField(){
        int i=0;
        for (int row=0;row < rows;row++){
            for (int colum =0;colum<colums;colum++){
               if (field[row][colum].getCardState()==CardState.OPENED) {
                   pickedRow[i] = row;
                   pickedColum[i] = colum;
                   i++;
               }
            }
        }
        if (i==2){
            return true;
        }else{
            return false;
        }
    }

    public void gameLogic(){
        if (checkField()){
            turnedCardCheck();
        }
    }

    public int getNumOfCards() {
        return colums*rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setColums(int colums) {
        this.colums = colums;
    }

    public int getRows() {
        return rows;
    }

    public int getColums() {
        return colums;
    }

    public Card getItemFromField(int x, int y){
        return field[x][y];
    }

    public int getPickedColum(int arrayPlace) {
        return pickedColum[arrayPlace];
    }

    public int getPickedRow(int arrayPlace) {
        return pickedRow[arrayPlace];
    }

    public void setPickedColum(int arrayPlace,int number) {
        pickedColum[arrayPlace]=number;
    }

    public void setPickedRow(int arrayPlace,int number) {
        pickedRow[arrayPlace] = number;
    }
    public boolean getFoundedPair(){
        return foundedPair;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
