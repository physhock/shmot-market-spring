package ru.spbpu.models.system;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.spbpu.models.AbstractDomain;
import ru.spbpu.models.actors.Seller;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Table(name = "asks")
@Entity(name = "Ask")
@EqualsAndHashCode(callSuper = true)
public class Ask extends AbstractDomain {
    @OneToOne
    private Item item;

    private int ask;

    @ManyToOne
    private Seller seller;

    public Ask(Item item, int ask, Seller seller) {
        this.item = item;
        this.ask = ask;
        this.seller = seller;
    }
}
