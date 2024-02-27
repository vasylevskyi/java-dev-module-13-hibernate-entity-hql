package ua.goit.planet;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Where;

@Table(name = "planet")
@Entity
@Where(clause = "DELETED = 0")
@Data
public class Planet {
    @Id
    private String id;

    @Column
    private String name;

    @Column(name = "DELETED")
    private int deleted = 0;

    public void setDeleted() {
        this.deleted = 1;
    }
}
