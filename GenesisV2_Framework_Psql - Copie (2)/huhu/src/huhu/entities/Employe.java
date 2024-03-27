package huhu.entities;

import veda.godao.annotations.Column;
import veda.godao.annotations.PrimaryKey;
import veda.godao.annotations.Table;

@Table("employe")

public class Employe {
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

    @veda.godao.annotations.ForeignKey(recursive = true)
    @Column("id_direction")
    private Direction direction;

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction o) {
        direction = o;
    }

    @veda.godao.annotations.ForeignKey(recursive = true)
    @Column("id_category")
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category o) {
        category = o;
    }

    public Employe() {
    }

    public Employe(Integer o) {
        id = o;
    }

    public String getLabel() {
        return nom;
    }
}
