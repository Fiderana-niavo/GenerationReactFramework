package huhu.entities;

import veda.godao.annotations.Column;
import veda.godao.annotations.PrimaryKey;
import veda.godao.annotations.Table;

@Table("etu")

public class Etu {
    @PrimaryKey
    @Column("id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer o) {
        id = o;
    }

    @Column("mlle")
    private Integer mlle;

    public Integer getMlle() {
        return mlle;
    }

    public void setMlle(Integer o) {
        mlle = o;
    }

    @Column("nom")
    private String nom;

    public String getNom() {
        return nom;
    }

    public void setNom(String o) {
        nom = o;
    }

    public Etu() {
    }

    public Etu(Integer o) {
        id = o;
    }

}
