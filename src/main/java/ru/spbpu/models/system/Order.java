package ru.spbpu.models.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.spbpu.models.AbstractDomain;
import ru.spbpu.models.actors.Buyer;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Table(name = "orders")
@Entity(name = "Order")
@EqualsAndHashCode(callSuper = true)
public class Order extends AbstractDomain {
    @OneToOne
    private Item item;

    private String trackingId;

    @ManyToOne
    @JsonIgnore
    private Buyer buyer;

    public Order(Item item, String trackingId, Buyer buyer) {
        this.item = item;
        this.trackingId = trackingId;
        this.buyer = buyer;
    }
}
