package entities;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "salles")

public class salle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String code;

    @OneToMany(mappedBy = "salle", fetch = FetchType.EAGER)
    private List<machine> machines;

    public salle(String code) {
        this.code = code;
    }

    public salle() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<machine> getMachines() {
        return machines;
    }

    public void setMachines(List<machine> machines) {
        this.machines = machines;
    }
}
