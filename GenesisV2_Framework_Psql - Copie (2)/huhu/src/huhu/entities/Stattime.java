package huhu.entities;

import veda.godao.annotations.Column;
import veda.godao.annotations.PrimaryKey;
import veda.godao.annotations.Table;

@Table("stattime")

public class Stattime {
    @PrimaryKey
    @Column("id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer o) {
        id = o;
    }

    @Column("matchid")
    private Integer matchid;

    public Integer getMatchid() {
        return matchid;
    }

    public void setMatchid(Integer o) {
        matchid = o;
    }

    @Column("player")
    private Integer player;

    public Integer getPlayer() {
        return player;
    }

    public void setPlayer(Integer o) {
        player = o;
    }

    @Column("shottime")
    private String shottime;

    public String getShottime() {
        return shottime;
    }

    public void setShottime(String o) {
        shottime = o;
    }

    public Stattime() {
    }

    public Stattime(Integer o) {
        id = o;
    }

}
