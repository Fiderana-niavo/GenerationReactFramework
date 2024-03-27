package huhu.entities;

import veda.godao.annotations.Column;
import veda.godao.annotations.PrimaryKey;
import veda.godao.annotations.Table;

@Table("category")

public class Category {
    @PrimaryKey
    @Column("id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer o) {
        id = o;
    }

    @Column("nom")
    private String nom;

    public String getNom() {
        return nom;
    }

    public void setNom(String o) {
        nom = o;
    }

    public Category() {
    }

    public Category(Integer o) {
        id = o;
    }

    public String getLabel() {
        return nom;
    }
}
