package ru.spbpu.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.spbpu.models.actors.User;
import ru.spbpu.services.MarketplaceService;

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

    @PostMapping("login")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<? extends User> login(@RequestBody final Map<String, Object> data) {
        return marketplaceService.login(data)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
