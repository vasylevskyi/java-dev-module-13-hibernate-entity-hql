package ua.goit.client;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.Where;
import ua.goit.ticket.Ticket;

import java.util.List;

@Table(name = "client")
@Entity
@Where(clause = "DELETED = 0")
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column(name = "DELETED")
    private int deleted = 0;

/*    @Getter
    @OneToMany(mappedBy = "client")
    private List<Ticket> tickets;*/

    public void setDeleted() {
        this.deleted = 1;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", deleted=" + deleted +
//                ", tickets=" + tickets +
                '}';
    }
}