package sk.tuke.kpi.kp.pexeso.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Players implements Serializable {

    @Id
    @GeneratedValue
    private int id_player;

    private String logins;
    private String passwords;

    public Players(){

    }

    public Players( String logins, String passwords) {
        this.logins = logins;
        this.passwords = passwords;
    }

    public int getId_player() {
        return id_player;
    }

    public void setId_player(int id_player) {
        this.id_player = id_player;
    }

    public String getLogins() {
        return logins;
    }

    public void setLogins(String logins) {
        this.logins = logins;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    @Override
    public String toString() {
        return "Players{" +
                "id_playerl=" + id_player +
                ", logins='" + logins + '\'' +
                ", passwords='" + passwords + '\'' +
                '}';
    }
}
