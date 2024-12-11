package sk.tuke.kpi.kp.pexeso;

public class Pexeso {

    public static void main(String[] args) {
        GameField gameField = new GameField();
        GameUI gameUI = new GameUI(gameField);
        gameUI.playingGame();
        gameUI.getPlayer();

    }


}
