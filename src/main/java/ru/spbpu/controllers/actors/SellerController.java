package ru.spbpu.controllers.actors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.spbpu.models.system.Ask;
import ru.spbpu.services.SellerService;

@RestController
@RequestMapping("seller")
public class SellerController {

    private final SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @PostMapping("ask/{sellerId}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Long> ask(@RequestBody final Ask ask, @PathVariable Long sellerId) {
        return new ResponseEntity<>(sellerService.placeAsk(ask, sellerId), HttpStatus.OK);
    }

}
