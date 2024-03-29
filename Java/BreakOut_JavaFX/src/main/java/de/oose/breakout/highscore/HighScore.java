package de.oose.breakout.highscore;

import javax.persistence.*;
import java.util.Date;

@NamedQuery(name = "HighScore.getByName", query = "SELECT h FROM HighScore h WHERE h.name = :name")
@Entity
public class HighScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Integer score;
    private String name;

    @Temporal(TemporalType.DATE)
    private Date date;

    public HighScore() {
    }

    public HighScore(Integer score, String name) {
        this.score = score;
        this.name = name;
        this.date = new Date();
    }

    public long getId() {
        return id;
    }

    public String formatForHallOfFame() {
        return score + " " + name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "HighScore [" + id + " " + score + " " + name + " " + date + "]";
    }

}
