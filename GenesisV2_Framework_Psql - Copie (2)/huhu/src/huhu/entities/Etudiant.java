package huhu.entities;

import veda.godao.annotations.Column;
import veda.godao.annotations.PrimaryKey;
import veda.godao.annotations.Table;

@Table("etudiant")

public class Etudiant {
    @PrimaryKey
    @Column("id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer o) {
        id = o;
    }

    @Column("numero")
    private String numero;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String o) {
        numero = o;
    }

    @Column("nom")
    private String nom;

    public String getNom() {
        return nom;
    }

    public void setNom(String o) {
        nom = o;
    }

    @Column("admission_status")
    private Integer admissionStatus;

    public Integer getAdmissionStatus() {
        return admissionStatus;
    }

    public void setAdmissionStatus(Integer o) {
        admissionStatus = o;
    }

    public Etudiant() {
    }

    public Etudiant(Integer o) {
        id = o;
    }

}
