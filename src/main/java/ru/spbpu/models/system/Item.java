package ru.spbpu.models.system;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import ru.spbpu.models.AbstractDomain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Item")
@EqualsAndHashCode(callSuper = true)
@Table(name = "items",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "size"})}
)
public class Item extends AbstractDomain {
    private String name;
    private String size;
}
