package ru.spbpu.models.actors;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.spbpu.models.system.Ask;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Table(name = "sellers")
@Entity(name = "Seller")
@EqualsAndHashCode(callSuper = true)
public class Seller extends User {
    @OneToMany
    private List<Ask> asks;

    public Seller(String username, String password, UserStatus userStatus, List<Ask> asks) {
        super(username, password, userStatus);
        this.asks = asks;
    }
}
