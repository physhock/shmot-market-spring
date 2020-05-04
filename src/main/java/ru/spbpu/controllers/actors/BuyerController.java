package ru.spbpu.controllers.actors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.spbpu.models.actors.User;
import ru.spbpu.models.system.Item;
import ru.spbpu.repositories.system.ItemRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author fshkolni
 */
@RestController
@RequestMapping("buyer")
public class BuyerController {

    private ItemRepository itemRepository;


//    @GetMapping("{itemId}/bet")
//    @CrossOrigin(origins = "*", allowedHeaders = "*")
//    public ResponseEntity bet(@RequestBody final Map<String, Object> data) {
//        return ResponseEntity.ok().build();
//    }
}
