package employe.entities;
import veda.godao.annotations.Column;
import veda.godao.annotations.Table;
import veda.godao.annotations.PrimaryKey;

@Table("entreprise")

public class Entreprise {
    @PrimaryKey
@Column("identreprise")
private Integer identreprise;
public Integer getIdentreprise(){ return identreprise; }
public void setIdentreprise(Integer o){ identreprise=o; }
@Column("nomentreprise")
private String nomentreprise;
public String getNomentreprise(){ return nomentreprise; }
public void setNomentreprise(String o){ nomentreprise=o; }

    public Entreprise(){}
public Entreprise(Integer o){ identreprise=o; }

}

