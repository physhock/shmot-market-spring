package ru.spbpu.models.actors;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.spbpu.models.system.Bet;
import ru.spbpu.models.system.Order;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Table(name = "buyers")
@Entity(name = "Buyer")
@EqualsAndHashCode(callSuper = true)
public class Buyer extends User {
    @OneToMany
    private List<Order> orders;
    @OneToMany
    private List<Bet> bets;

    public Buyer(String username, String password, UserStatus userStatus, List<Order> orders, List<Bet> bets) {
        super(username, password, userStatus);
        this.orders = orders;
        this.bets = bets;
    }
}
