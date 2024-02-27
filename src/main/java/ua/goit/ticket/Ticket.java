package ua.goit.ticket;

import jakarta.persistence.*;
import lombok.Data;
import ua.goit.client.Client;
import ua.goit.planet.Planet;

@Table(name = "ticket")
@Entity
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Basic
    @Column(name = "created_at")
    private java.time.ZonedDateTime createdAt;

//    @ToString.Exclude
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "from_planet_id")
    private Planet fromPlanet;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "to_planet_id")
    private Planet toPlanet;

    @Column(name = "DELETED")
    private int deleted = 0;

    public void setDeleted() {
        this.deleted = 1;
    }

/*    @ManyToOne(cascade=CascadeType.ALL)
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }*/

}