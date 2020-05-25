package ru.spbpu.repositories.system;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import ru.spbpu.models.system.Item;

@RepositoryRestResource
@CrossOrigin(origins = "*", allowedHeaders = "*")
public interface ItemRepository extends CrudRepository<Item, Long> {
}
