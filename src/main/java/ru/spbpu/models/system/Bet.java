package ru.spbpu.models.system;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.spbpu.models.AbstractDomain;
import ru.spbpu.models.actors.Buyer;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Table(name = "bets")
@Entity(name = "Bet")
@EqualsAndHashCode(callSuper = true)
public class Bet extends AbstractDomain {
    @OneToOne
    private Item item;

    private int bet;

    @OneToOne
    private Buyer buyer;

    public Bet(Item item, int bet, Buyer buyer) {
        this.item = item;
        this.bet = bet;
        this.buyer = buyer;
    }
}
