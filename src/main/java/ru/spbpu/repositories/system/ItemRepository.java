package ru.spbpu.repositories.system;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.spbpu.models.system.Item;

@RepositoryRestResource
public interface ItemRepository extends CrudRepository<Item, Long> {
}
