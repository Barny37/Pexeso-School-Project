package sk.tuke.kpi.kp.pexeso.entity;

import org.checkerframework.checker.units.qual.C;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Comment {
    @Id
    @GeneratedValue
    private int id_comment;
    private String player;
    private String comment;
    private Date date;

    public Comment(){

    }
    public Comment(String player,String comment, Date date){
        this.player=player;
        this.comment=comment;
        this.date=date;
    }

    public String getPlayer() {
        return player;
    }

    public int getId_comment() {
        return id_comment;
    }

    public Date getDate() {
        return date;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Comment{" +
                ", player='" + player + '\'' +
                ", comment='" + comment + '\'' +
                ", date=" + date +
                '}';
    }
}
