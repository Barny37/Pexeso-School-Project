package sk.tuke.kpi.kp.pexeso.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Rating implements Serializable {

    @Id
    @GeneratedValue
    private int id_rating;
    private String player;
    private double rating;
    private Date date;

    public Rating(){

    }
    public Rating(String player, double rating, Date date){
        this.player=player;
        this.rating=rating;
        this.date=date;
    }

    public Date getDate() {
        return date;
    }

    public String getPlayer() {
        return player;
    }

    public double getRating() {
        return rating;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id_rating=" + id_rating +
                ", player='" + player + '\'' +
                ", rating=" + rating +
                ", date=" + date +
                '}';
    }
}
