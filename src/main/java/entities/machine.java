package entities;

import java.util.Date;
import javax.persistence.*;

@Entity
@NamedNativeQuery(name = "findBetweenDateNative", query = "select * from machine where dateAchat between :d1 and :d2", resultClass = machine.class)
@NamedQuery(name = "findBetweenDate", query = "from machine  where dateAchat between :d1 and :d2")
public class machine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String ref;

    @Temporal(TemporalType.DATE)
    private Date dateAchat;

    @ManyToOne
    private salle salle;

    public machine(String ref, Date dateAchat, salle salle) {
        this.ref = ref;
        this.dateAchat = dateAchat;
        this.salle = salle;
    }

    public machine() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Date getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }

    public salle getSalle() {
        return salle;
    }

    public void setSalle(salle salle) {
        this.salle = salle;
    }
}
