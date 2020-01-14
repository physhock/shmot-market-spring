package ru.spbpu.models.system;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.spbpu.models.AbstractDomain;
import ru.spbpu.models.actors.Administrator;

import javax.persistence.*;

@Data
@Table(name = "deals")
@Entity(name = "Deal")
@EqualsAndHashCode(callSuper = true)
public class Deal extends AbstractDomain {
    @OneToOne
    private Ask ask;
    @OneToOne
    private Bet bet;
    @OneToOne
    private Administrator administrator;

    @Column(name = "deal_status")
    @Enumerated(EnumType.ORDINAL)
    private DealStatus dealStatus;

    public Deal(Ask ask, Bet bet, Administrator administrator) {
        this.ask = ask;
        this.bet = bet;
        this.administrator = administrator;
        this.dealStatus = DealStatus.OPEN;
    }

    public enum DealStatus {
        OPEN,
        APPROVED,
        CLOSED;

        public String getName() {
            return String.valueOf(DealStatus.this);
        }
    }
}
