package test2.entities;
import veda.godao.annotations.Column;
import veda.godao.annotations.Table;
import veda.godao.annotations.PrimaryKey;

@Table("marque")

public class Marque {
    @PrimaryKey
@Column("idmarque")
private Integer idmarque;
public Integer getIdmarque(){ return idmarque; }
public void setIdmarque(Integer o){ idmarque=o; }
@Column("nommarque")
private String nommarque;
public String getNommarque(){ return nommarque; }
public void setNommarque(String o){ nommarque=o; }

    public Marque(){}
public Marque(Integer o){ idmarque=o; }

}

