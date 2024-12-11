package sk.tuke.kpi.kp.pexeso.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Score implements Serializable {

    @Id
    @GeneratedValue
    private int id_score;

    private String player;
    private int num_of_cards;
    private int move;
    private int score;


    public Score(){

    }
    public Score(String player, int num_of_cards, int move, int score) {
        this.player=player;
        this.num_of_cards=num_of_cards;
        this.move=move;
        this.score=score;
    }

    public String getPlayer() {
        return player;
    }

    public int getNum_of_cards() {
        return num_of_cards;
    }

    public int getMove() {
        return move;
    }

    public int getScore() {
        return score;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public void setNum_of_cards(int num_of_cards) {
        this.num_of_cards = num_of_cards;
    }

    public void setMove(int move) {
        this.move = move;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "PexesoScore{" +
                "id_score =" + id_score +
                "player='" + player + '\'' +
                ", num_of_cards=" + num_of_cards +
                ", move=" + move +
                ", score=" + score +
                '}';
    }
}
