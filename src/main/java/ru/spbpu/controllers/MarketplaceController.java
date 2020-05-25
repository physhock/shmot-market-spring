package ru.spbpu.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.spbpu.models.actors.User;
import ru.spbpu.models.system.Ask;
import ru.spbpu.models.system.Bet;
import ru.spbpu.models.system.Item;
import ru.spbpu.services.MarketplaceService;

import java.util.List;
import java.util.Map;

/**
 * @author fshkolni
 */
@RestController
@RequestMapping("marketplace")
public class MarketplaceController {

    private final MarketplaceService marketplaceService;

    public MarketplaceController(MarketplaceService marketplaceService) {
        this.marketplaceService = marketplaceService;
    }

    @CrossOrigin
    @PostMapping("login")
    public ResponseEntity<? extends User> login(@RequestBody final Map<String, Object> data) {
        return marketplaceService.login(data)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @CrossOrigin
    @GetMapping("items")
    public ResponseEntity<List<Item>> loadItems() {
        return new ResponseEntity<>(marketplaceService.getAllItems(), HttpStatus.OK);
    }

    @GetMapping("getLowestAsks")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<Ask>> getLowestAsk() {
        return new ResponseEntity<>(marketplaceService.getLowestAsk(), HttpStatus.OK);
    }

    @GetMapping("getHighestBets")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<Bet>> getHighestBets() {
        return new ResponseEntity<>(marketplaceService.getHighestBets(), HttpStatus.OK);
    }
}
